drop table if exists todos;

create table if not exists todos (
    todo_id bigserial primary key,
    name varchar(250) not null,
    completed boolean not null default false,
    end_date date
);

truncate table todos restart identity cascade;

drop procedure if exists sp_delete_todo_by_id;
create or replace procedure sp_delete_todo_by_id(bigint)
    language plpgsql
as
'
    begin
        delete from todos where todo_id = $1;
    end
';

drop procedure if exists sp_update_todo;
create or replace procedure sp_update_todo(p_id bigint, p_name varchar(250), p_completed boolean, p_end_date date)
    language plpgsql
as
'
    begin
        update todos set name = p_name, completed = p_completed, end_date = p_end_date where todo_id = p_id;
    end
';

drop function if exists insert_todo;
create or replace function insert_todo(p_name varchar(250), p_completed boolean, p_end_date date)
    returns bigint
as
'
    begin
        insert into todos (name, completed, end_date) values (p_name, p_completed, p_end_date);
        return currval($$todos_todo_id_seq$$::regclass);
    end
' language plpgsql;

drop function if exists find_all_todos;
create or replace function find_all_todos()
    returns table (id bigint, todo_name varchar(250), todo_completed boolean, todo_end_date date)
as
'
    begin
        return query select todo_id as id, name as todo_name, completed as todo_completed, end_date as todo_end_date from todos;
    end
' language plpgsql;

drop function if exists find_todo_by_id;
create or replace function find_todo_by_id(p_id bigint)
    returns table (id bigint, todo_name varchar(250), todo_completed boolean, todo_end_date date)
as
'
    begin
        return query select todo_id as id, name as todo_name, completed as todo_completed, end_date as todo_end_date from todos where todo_id = p_id;
    end
' language plpgsql;

drop function if exists find_todo_by_name;
create or replace function find_todo_by_name(p_name varchar(250))
    returns table (id bigint, todo_name varchar(250), todo_completed boolean, todo_end_date date)
as
'
    begin
        return query select todo_id as id, name as todo_name, completed as todo_completed, end_date as todo_end_date from todos where name = p_name;
    end
' language plpgsql;

drop function if exists find_todo_by_end_date;
create or replace function find_todo_by_end_date(p_end_date date)
    returns table (id bigint, todo_name varchar(250), todo_completed boolean, todo_end_date date)
as
'
    begin
        return query select todo_id as id, name as todo_name, completed as todo_completed, end_date as todo_end_date from todos where end_date = p_end_date;
    end
' language plpgsql;

drop procedure if exists sp_delete_completed_todos;
create or replace procedure sp_delete_completed_todos()
    language plpgsql
as
'
    begin
        delete from todos where completed = true;
    end
';

drop procedure if exists sp_delete_all;
create or replace procedure sp_delete_all()
    language plpgsql
as
'
    begin
        delete from todos;
    end
';
