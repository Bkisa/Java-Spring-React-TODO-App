-- User tablosu oluşturma
drop table if exists users cascade;

create table if not exists users (
     user_id bigserial primary key,
     username varchar(250) not null,
     password varchar(250) not null,
     role varchar(100) not null
);

-- Todos tablosu oluşturma
drop table if exists todos cascade;

create table if not exists todos (
    todo_id bigserial primary key,
    name varchar(250) not null,
    completed boolean not null default false,
    end_date date,
    user_id bigint references users(user_id) -- Foreign key olarak user_id ekleniyor
);

truncate table todos restart identity cascade;
truncate table users restart identity cascade;

-- Silme prosedürü
drop procedure if exists sp_delete_todo_by_id cascade;
create or replace procedure sp_delete_todo_by_id(bigint)
    language plpgsql
as
'
    begin
        delete from todos where todo_id = $1;
    end
';

-- Güncelleme prosedürü
drop procedure if exists sp_update_todo cascade;
create or replace procedure sp_update_todo(p_id bigint, p_name varchar(250), p_completed boolean, p_end_date date)
    language plpgsql
as
'
    begin
        update todos set name = p_name, completed = p_completed, end_date = p_end_date where todo_id = p_id;
    end
';

-- Ekleme fonksiyonu
drop function if exists insert_todo cascade;
create or replace function insert_todo(p_name varchar(250), p_completed boolean, p_end_date date)
    returns bigint
as
'
    begin
        insert into todos (name, completed, end_date, user_id) values (p_name, p_completed, p_end_date, null);
        return currval($$todos_todo_id_seq$$::regclass);
    end
' language plpgsql;

-- Tüm todosları bulma fonksiyonu
drop function if exists find_all_todos cascade;
create or replace function find_all_todos()
    returns table (todo_id bigint, name varchar(250),completed boolean,end_date date,user_id bigint)
as
'
    begin
        return query
            select t.todo_id as id,
                   t.name as todo_name,
                   t.completed as todo_completed,
                   t.end_date as todo_end_date,
                   t.user_id as user_id
            from todos t;
    end
' language plpgsql;

-- ID'ye göre todo bulma fonksiyonu
drop function if exists find_todo_by_id cascade;
create or replace function find_todo_by_id(p_id bigint)
    returns table (todo_id bigint, name varchar(250),completed boolean,end_date date,user_id bigint)
as
'
begin
    return query
        select t.todo_id as id,
               t.name as todo_name,
               t.completed as todo_completed,
               t.end_date as todo_end_date,
               t.user_id as user_id
        from todos t
        where t.todo_id = p_id;
end
' language plpgsql;




-- İsme göre todo bulma fonksiyonu
drop function if exists find_todo_by_name;
create or replace function find_todo_by_name(p_name varchar(250))
    returns table (todo_id bigint, name varchar(250),completed boolean,end_date date,user_id bigint)
as
'
BEGIN
    return query
        select t.todo_id, t.name, t.completed, t.end_date, t.user_id
        from todos t
        where t.name = p_name;
end;
' language plpgsql;




-- Tarihe göre todo bulma fonksiyonu
drop function if exists find_todo_by_end_date cascade;
create or replace function find_todo_by_end_date(p_end_date date)
    returns table (id bigint, todo_name varchar(250), todo_completed boolean, todo_end_date date)
as
'
    begin
        return query select todo_id as id, name as todo_name, completed as todo_completed, end_date as todo_end_date from todos where end_date = p_end_date;
    end
' language plpgsql;

-- Tamamlanmış todosları silme prosedürü
drop procedure if exists sp_delete_completed_todos cascade;
create or replace procedure sp_delete_completed_todos()
    language plpgsql
as
'
    begin
        delete from todos where completed = true;
    end
';

-- Tüm todosları silme prosedürü
drop procedure if exists sp_delete_all cascade;
create or replace procedure sp_delete_all()
    language plpgsql
as
'
    begin
        delete from todos;
    end
';


-- Tamamlanmamış todosları bulma prosedürü
drop function if exists find_all_not_completed cascade;
create or replace function find_all_not_completed()
    returns table (
                      todo_id bigint,
                      name varchar(250),
                      completed boolean,
                      end_date date,
                      user_id bigint
                  )
    language plpgsql
as '
begin
    return query
        select t.todo_id,
               t.name,
               t.completed,
               t.end_date,
               t.user_id
        from todos t
        where t.completed = false;
end;
';

drop function if exists find_all_completed cascade;
create or replace function find_all_completed()
    returns table (
                      todo_id bigint,
                      name varchar(250),
                      completed boolean,
                      end_date date,
                      user_id bigint
                  )
    language plpgsql
as '
    begin
        return query
            select t.todo_id,
                   t.name,
                   t.completed,
                   t.end_date,
                   t.user_id
            from todos t
            where t.completed = true;
    end;
';