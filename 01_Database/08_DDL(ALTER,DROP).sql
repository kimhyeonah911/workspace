/*
DDL : 데이터 정의어 

객체를 생성(CREATE), 변경(ALTER), 삭제(DROP)하는 구문
*/

/*
alter : 객체를 변경하는 구문

[표현식]
alter table 테이블명 변경할 내용

*변경할 내용
1) 컬럼 추가/수정/삭제
2) 제약조건 추가/삭제 -> 수정불가
3) 컬럼명/제약조건명/테이블명 변경
*/
select * from dept_table;

-- 1)컬럼 추가
-- dept_table에 cname컬럼 추가
alter table dept_table add cname varchar2(20);

-- lname 컬럼 추가 -> 기본값(한국)
alter table dept_table add lname varchar2(20) default '한국';
select * from dept_table;

-- 1_2)컬럼 수정
-- 데이터타입 수정 : modify 컬럼명 바꾸고자 하는 데이터 타입
-- default 값 수정 : modify 컬럼명 default 변경할 기본값

alter table dept_table modify dept_id char(5);
alter table dept_table modify dept_id number; -- 형식 오류가 발생

-- dept_title 컬럼을 varchar2(40)로 변경
alter table dept_table modify dept_title varchar2(40); 
-- lname 컬럼의 기본값을 '미국'으로 변경
alter table dept_table modify lname default '미국';

-- 다중 변경 가능
alter table dept_table modify dept_title varchar2(40) modify lname default '미국';

-- 1_3)컬럼 삭제(drop column) : drop column 삭제하고자 하는 컬럼
create table dept_copy as (select * from dept_table);

select * from dept_copy;

alter table dept_copy drop column dept_id;
alter table dept_copy drop column cname;
alter table dept_copy drop column lname;
alter table dept_copy drop column dept_title;

alter table dept_copy drop column location_id;
-- 마지막 컬럼은 삭제할 수 없다.

------------------------------------------------------------------------
-- 2)제약조건 추가/삭제(수정은 삭제하고 다시 추가하면 된다.)
/*
2_1)
- primary key : alter table 테이블명 add primary key (컬럼명);
- foreign key : alter table 테이블명 add foreign key (컬럼명) references 참조할 테이블명 (컬럼명);
- unique : alter table 테이블명 add unique (컬럼명);
- check : alter table 테이블명 add check (컬럼에 대한 조건식);
- not null : alter table 테이블명 modify 컬럼명 not null;

제약조건명 지정하고자 한다면 [constraint 제약조건명] 제약조건
*/
-- dept_table 테이블에 dept_id에 primary key 제약조건 추가, dept_title에 unique 제약조건 추가, lname에 not null 제약조건 추가
alter table dept_table add constraint dtable_pk primary key (dept_id) add constraint dtable_uq unique (dept_title)
modify lname constraint dtable_nn not null;

-- 2_2)제약조건 삭제 : drop constraint 제약조건명(not null 삭제 안 됨)
alter table dept_table drop constraint dtable_pk;
alter table dept_table drop constraint dtable_uq modify lname null;

---------------------------------------------------------------------------
-- 테이블 삭제
drop table dept_table;
-- 어딘가에 참조되고 있는 부모 테이블은 함부로 삭제가 되지 않는다.
-- 만약 지우고자 한다면 
-- 1. 자식테이블 먼저 전부 삭제
-- 2. 그냥 부모 테이블만 삭제하는데 제약조건까지 삭제하는 방법
-- drop table 테이블명 cascade constraint

create table dept_table as (select * from department);
select * from dept_table;

-- 3)컬럼명/제약조건명/테이블명 변경
-- 3_1)컬럼명 변경 : rename column 기존컬럼명 to 바꿀컬럼명
-- dept_title -> dept_name
alter table dept_table rename column dept_title to dept_name;

-- 3_2)제약조건명 변경 : rename constraint 기존제약조건명 to 바꿀제약조건명
alter table dept_table rename constraint sys_c008501 to dtable_id_nn;

-- 3_3)테이블명 변경 : rename to 바꿀테이블명
alter table dept_table rename to dept_test;