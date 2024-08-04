drop table if exists todos;

create table if not exists todos (
     todo_id bigserial primary key,
     name varchar(250) not null,
     completed boolean not null default false
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
create or replace procedure sp_update_todo(bigint, varchar(250), boolean)
language plpgsql
as
'
    begin
        update todos set name = $2, completed = $3 where todo_id = $1;
    end
';

drop function if exists insert_todo;
create or replace function insert_todo(varchar(250), boolean)
returns bigint
as
'
    begin
        insert into todos (name, completed) values ($1, $2);
        return currval($$todos_todo_id_seq$$::regclass);
    end
' language plpgsql;


drop function if exists find_all_todos;
create or replace function find_all_todos()
    returns table (id bigint, todo_name varchar(250), completed boolean)
as
'
    begin
        return query select todo_id, name, todos.completed from todos;
    end
' language plpgsql;


drop function if exists find_todo_by_id;
create or replace function find_todo_by_id(bigint)
    returns table (id bigint, todo_name varchar(250), completed boolean)
as
'
    begin
        return query select todo_id, name, todos.completed from todos where todo_id = $1;
    end
' language plpgsql;



drop function if exists find_todo_by_name;
create or replace function find_todo_by_name(varchar(250))
    returns table (id bigint, todo_name varchar(250), completed boolean)
as
'
    begin
        return query select todo_id, name, todos.completed from todos where name = $1;
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

