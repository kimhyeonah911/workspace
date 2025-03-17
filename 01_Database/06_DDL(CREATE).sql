/*
*데이터 정의어
오라클에서 제공하는 객체를 새로 만들고(create), 구조를 변경하고(alter), 구조 자체를 삭제(delete) 하는 언어
즉, 실제 데이터 값이 아닌 규칙 자체를 정의하는 언어

오라클에서 객체(구조) : 테이블, 뷰, 시퀀스, 인덱스, 패키지, 트리거, 프로시저, 함수, 동의어, 사용자

<create>
객체를 새로 생성하는 구문
*/

/*
1. 테이블 생성
- 테이블이란 : 행과 열로 구성되는 가장 기본적인 데이터베이스 객체
             모든 데이터들은 테이블을 통해 저장된다.
             (DBMS용어 중 하나로, 데이터를 일종의 표 형태로 표현한 것)
             
[표현식]
create table 테이블명컬럼명 자료형(크기), 컬럼명 자료형(크기), 컬럼명 자료형(크기)... )

*자료형
- 숫자(number)
- 문자(char(바이트크기) | varchar2(바이트크기)) -> 반드시 크기를 지정해 줘야 함
   - char : 최대 2000바이트까지 지정 가능 / 고정길이(고정된 글자수의 데이터가 담길 경우)
   - varchar : 최대 4000바이트까지 지정 가능 / 가변길이(몇 글자의 데이터가 들어올지 모르는 경우)
- 날짜(date)
*/
-- 회원에 대한 데이터를 담기 위한 테이블 member 생성
create table member(
mem_no number,
mem_id varchar2(20), --user01, alwaysjone
mem_pwd varchar2(20),
mem_name varchar2(20),
gender char(3), --남, 여
phone varchar2(13),
email varchar2(50),
mem_date date
);

select * from member;

-----------------------------------------------------------------------
/*
2. 컬럼에 주석달기(컬럼에 대한 간단한 설명)

[표현법]
comment on column 테이블명.컬럼명 is '주석내용';
*/
comment on column member.mem_no is '회원번호';
comment on column member.mem_id is '회원아이디';
comment on column member.mem_pwd is '회원비밀번호';
comment on column member.mem_name is '회원이름';
comment on column member.gender is '성별(남/여)';
comment on column member.phone is '전화번호';
comment on column member.email is '이메일';
comment on column member.mem_date is '회원가입일';

-------------------------------------------------------------------------
/*
3. 테이블 삭제

[표현법]
drop table 테이블명;
*/
drop table member;

-- insert into 테이블명 values (값, 값, 값 ...);
insert into member values(1, 'user1', 'pass1', '홍길동', '남', '010-1111-2222', 'aaa@naver.com', '24/12/23');

select * from member;

insert into member values(1, 'user1', 'pass1', null, '남', null, null, '24/12/23');

select * from member;

----------------------------------------------------------------------------
/*
<제약조건>
- 원하는 데이터값(유효한 형식의 값)만 유지하기 위해서 특정 컬럼에 설정하는 제약
- 데이터 무결성 보장을 목적으로 한다.

종류 : not null, unique, check, primary key, foreign key
*/

/*
*not null
해당 컬럼에 반드시 값이 존재해야만 하는 경우(즉, 절대 null이 들어오면 안 되는 경우)
삽입/수정 시 null값을 허용하지 않도록 제한

제약조건을 부여하는 방식은 크게 2가지가 있다.(컬럼 레벨 방식, 테이블 레벨 방식)
not null제약 조건은 무조건 컬럼 레벨 방식만 가능하다.
*/
create table member(
mem_no number not null,
mem_id varchar2(20) not null,
mem_pwd varchar2(20) not null,
mem_name varchar2(20) not null,
gender char(3),
phone varchar2(13),
email varchar2(50),
mem_date date
);

insert into member values(1, 'user1', 'pass1', '홍길동', '남', '010-1111-2222', 'aaa@naver.com', '24/12/23');

select * from member;

insert into member values(2, 'user2', 'pass2', '홍길순', null, null, null, null);

select * from member;

insert into member values(3, null, 'pass3', '홍길삼', null, null, null, null);

insert into member values(3, 'user2', 'pass3', '홍길삼', null, null, null, null);
-- 아이디가 중복되었음에도 불구하고 잘 추가가 된다.

------------------------------------------------------------------------
/*
*unique 제약조건
해당 컬럼에 중복값이 들어가서는 안 될 경우 사용한다.
컬럼값에 중복값을 제한하는 제약조건이다.
삽입/수정 시 기존에 있는 데이터값 중 중복값이 있을 경우 오류를 발생시킨다.
*/
create table mem_unique(
mem_no number not null,
mem_id varchar2(20) not null unique, -- 컬럼 레벨 방식
mem_pwd varchar2(20) not null,
mem_name varchar2(20) not null,
gender char(3), 
phone varchar2(13),
email varchar2(50),
mem_date date,
unique(email) -- 테이블 레벨 방식
);

insert into mem_unique values(1, 'user1', 'pass1', '홍길동', '남', '010-1111-2222', 'aaa@naver.com', '24/12/23');

select * from mem_unique;

insert into mem_unique values(2, 'user1', 'pass2', '홍길동', '여', '010-1111-3333', 'bbb@naver.com', '24/12/23');
-- unique 제약 조건에 위배되었으므로 insert 실패 
--> 오류구분을 제약조건명으로 알려준다.
--> 쉽게 어떤 제약조건이 위반인지 알기 어렵다.
--> 제약조건 부여시 제약조건명을 지정할 수 있다.(지정하지 않으면 시스템에서 자동으로 부여함)

-------------------------------------------------------------------------
/*
*제약조건 부여 시 제약조건명까지 부여하는 방법
> 컬럼 레벨 방식
create table 테이블명(
    컬럼명 자료형 constraint 제약조건명 제약조건
)

> 테이블 레벨 방식
create table 테이블명(
    컬럼명 자료형,
    컬럼명 자료형,
    constraint 제약조건명 제약조건(컬럼명)
)
*/
create table mem_unique(
mem_no number constraint memno_nt not null,
mem_id varchar2(20) constraint memid_nt not null unique, -- 컬럼 레벨 방식
mem_pwd varchar2(20) constraint mempwd_nt not null,
mem_name varchar2(20) constraint memname_nt not null,
gender char(3), 
phone varchar2(13),
email varchar2(50),
mem_date date,
-- constraint memid_uq unique(mem_id) -- 테이블 레벨 방식
);

insert into mem_unique values(1, 'user1', 'pass1', '홍길동', '남', '010-1111-2222', 'aaa@naver.com', '24/12/23');

select * from mem_unique;

insert into mem_unique values(2, 'user2', 'pass2', '홍길순', '여', null, null, null);

insert into mem_unique values(3, 'user3', 'pass3', '홍길삼', null, null, null, null);

insert into mem_unique values(4, 'user3', 'pass4', '홍길사', null, null, null, null);

--------------------------------------------------------------------------
/*
*check(조건식)
해당 컬럼에 들어올 수 있는 값에 대한 조건을 제시해줄 수 있다.
해당 조건에 만족하는 데이터값만 담길 수 있음.
*/
create table mem_check(
mem_no number not null,
mem_id varchar2(20) not null unique, 
mem_pwd varchar2(20) not null,
mem_name varchar2(20) not null,
gender char(3) check(gender in('남', '여')), -- 컬럼 레벨 방식
phone varchar2(13),
email varchar2(50),
mem_date date,
--check(gender in('남', '여')) -- 테이블 레벨 방식
);

insert into mem_check values(1, 'user1', 'pass1', '홍길동', '남', '010-1111-2222', 'aaa@naver.com', '24/12/23');

select * from mem_check;

insert into mem_check values(2, 'user2', 'pass2', '홍길순', '여자', '010-1111-3333', 'bbb@naver.com', '24/12/23');
-- check 제약 조건 때문에 예외가 발생한다.
-- gender 컬럼에는 check 제약 조건을 만족하는 값을 넣어야한다.
-- 단, null은 값이 없다는 뜻이기 때문에 가능하다.

insert into mem_check values(2, 'user2', 'pass2', '홍길순', '여', '010-1111-3333', 'bbb@naver.com', '24/12/23');

---------------------------------------------------------------------------
/*
primary key(기본키) 제약조건
테이블에서 각 행(row)를 식별하기 위해 사용될 컬럼에 부여하는 제약조건(식별자 역할)

ex) 회원번호, 학번, 군번, 부서코드, 직급코드, 주민등록번호, 택배운송장번호, 예약번호 ...
primary key -> not null + unique

한 테이블당 오직 한 개만 설정 가능
*/
create table mem_pri(
mem_no number constraint memno_pk primary key, -- 컬럼 레벨 방식
mem_id varchar2(20) not null, 
mem_pwd varchar2(20) not null,
mem_name varchar2(20) not null,
gender char(3) check(gender in('남', '여')), 
phone varchar2(13),
email varchar2(50),
mem_date date,
unique(mem_id)
-- primary key(mem_no) -- 테이블 레벨 방식
);
insert into mem_pri values(1, 'user1', 'pass1', '홍길동', '남', '010-1111-2222', 'aaa@naver.com', '24/12/23');

select * from mem_pri;

insert into mem_pri values(1, 'user2', 'pass2', '홍길순', '여', '010-1111-3333', 'aaa@naver.com', '24/12/23');
-- 기본키에 중복값을 담으려고 할 때(unique 제약 조건 위반)

insert into mem_pri values(null, 'user2', 'pass2', '홍길순', '여', '010-1111-3333', 'aaa@naver.com', '24/12/23');
-- 기본키에 null을 담으려고 할 때(not null 제약 조건 위배)

insert into mem_pri values(2, 'user2', 'pass2', '홍길순', '여', '010-1111-3333', 'aaa@naver.com', '24/12/23');

-- 복합키 : 두 개의 컬럼을 동시에 하나의 primary key로 지정하는 것
create table mem_pri2(
mem_no number primary key, -- 컬럼 레벨 방식
mem_id varchar2(20) not null, 
mem_pwd varchar2(20) not null,
mem_name varchar2(20) not null,
gender char(3) check(gender in('남', '여')), 
phone varchar2(13),
email varchar2(50),
mem_date date
);
insert into mem_pri values(1, 'user1', 'pass1', '홍길동', '남', '010-1111-2222', 'aaa@naver.com', '24/12/23');

insert into mem_pri values(2, 'user2', 'pass2', '홍길순', '여', '010-1111-3333', 'aaa@naver.com', '24/12/23');

insert into mem_pri values(3, 'user3', 'pass3', '홍길삼', '남', '010-1111-4444', 'aaa@naver.com', '24/12/23');

insert into mem_pri values(4, 'user4', 'pass4', '홍길사', '남', '010-1111-5555', 'aaa@naver.com', '24/12/23');

-- 복합키 사용 예(어떤 회원이 어떤 상품을 찜했는지에 대한 데이터를 보관하는 테이블)
create table tb_like(
mem_no number,
product_name varchar2(20),
like_date date,
primary key(mem_no, product_name)
);

-- 회원 4명(1, 2, 3, 4번)
-- 상품 2개(자전거 a, 자전거 b)

insert into tb_like values(1, '자전거a', sysdate); -- 홍길동이 자전거a 좋아요 누름
insert into tb_like values(1, '자전거b', sysdate);
insert into tb_like values(1, '자전거a', sysdate);
insert into tb_like values(2, '자전거a', sysdate);

-----------------------------------------------------------------------
-- 회원 등급에 대한 테이터를 보관하는 테이블
create table mem_grade(
grade_code number primary key,
grade_name varchar2(30) not null
);

insert into mem_grade values(10, '일반회원');
insert into mem_grade values(20, '우수회원');
insert into mem_grade values(30, '특별회원');
insert into mem_grade values(40, 'VIP 회원');

select * from mem_grade;

create table mem(
mem_no number primary key, -- 컬럼 레벨 방식
mem_id varchar2(20) not null, 
mem_pwd varchar2(20) not null,
mem_name varchar2(20) not null,
gender char(3) check(gender in('남', '여')), 
phone varchar2(13),
email varchar2(50),
mem_date date,
grade_id number
);

insert into mem values(1, 'user1', 'pass1', '홍길동', null, '010-1111-2222', 'aaa@naver.com', '24/12/23', null);

insert into mem values(2, 'user2', 'pass2', '홍길순', null, '010-1111-3333', 'bbb@naver.com', '24/12/23', 10);

insert into mem values(3, 'user3', 'pass3', '홍길삼', null, '010-1111-4444', 'ccc@naver.com', '24/12/23', 30);

insert into mem values(4, 'user4', 'pass4', '홍길사', null, '010-1111-5555', 'ddd@naver.com', '24/12/23', 20);

-- 유효하지 않은 회원 등급 번호가 정상적으로 insert된다.
insert into mem values(5, 'user5', 'pass5', '홍길오', null, '010-1111-6666', 'eee@naver.com', '24/12/23', 50);

/*
foreign_key(외래키) 제약조건
다른 테이블에 존재하는 값만 들어와야 되는 특정 컬럼에 부여하는 제약조건
-> 다른 테이블을 참조한다고 표현
-> 주로 forign key 제약 조건으로 인해 테이블간 관계가 형성된다.

> 컬럼 레벨 방식
컬럼명 자료형 references 참조할 테이블명 [참조할 컬럼명]

> 테이블 레벨 방식
foreign key(컬럼명) references 참조할 테이블명 [참조할 컬럼명]

-> 참조할 컬럼명 생략시 참조할 테이블의 primary key로 지정된 컬럼이 매칭된다.
*/

drop table mem;

create table mem(
mem_no number primary key,
mem_id varchar2(20) not null, 
mem_pwd varchar2(20) not null,
mem_name varchar2(20) not null,
gender char(3) check(gender in('남', '여')), 
phone varchar2(13),
email varchar2(50),
mem_date date,
grade_id number references mem_grade(grade_code) -- 컬럼 레벨 방식
-- foreign key grade_id references mem_grade(grade_code) -- 테이블 레벨 방식
);

insert into mem values(1, 'user1', 'pass1', '홍길동', null, '010-1111-2222', 'aaa@naver.com', '24/12/23', null);

insert into mem values(2, 'user2', 'pass2', '홍길순', null, '010-1111-3333', 'bbb@naver.com', '24/12/23', 10);

insert into mem values(3, 'user3', 'pass3', '홍길삼', null, '010-1111-4444', 'ccc@naver.com', '24/12/23', 30);

insert into mem values(4, 'user4', 'pass4', '홍길사', null, '010-1111-5555', 'ddd@naver.com', '24/12/23', 20);

insert into mem values(5, 'user5', 'pass5', '홍길오', null, '010-1111-6666', 'eee@naver.com', '24/12/23', 50);
-- primary key를 찾을 수 없다는 오류가 발생

select * from mem;
-- mem_grade(부모테이블) -|-------<-mem(자식테이블)
-- 1:N 관계 : 1쪽이 부모 테이블 N이 자식 테이블

delete from mem_grade where grade_code = 10;
-- mem 테이블에서 10이라는 값을 사용하고 있기 때문에 삭제가 안 됨

delete from mem_grade where grade_code = 40;
-- mem 테이블에서 40이라는 값을 사용하고 있지 않기 때문에 삭제가 된다.

-- 자식 테이블에서 이미 사용하고 있는 값이 있을 경우
-- 부모 테이블로부터 무조건 삭제가 안 되는 "삭제제한" 옵션이 걸려있다.

--------------------------------------------------------------------------
/*
자식테이블 생성 시 외래키 제약조건 부여할 때 삭제옵션 지정 가능
*삭제옵션 : 부모테이블의 데이터 삭제 시 그 데이터를 사용하고 있는 자식 테이블의 값을 어떻게 할 것인가?

- on delete restricted (기본값) : 삭제제한옵션, 자식데이터로부터 쓰이는 부모데이터는 삭제가 안 됨
- on delete set null : 부모데이터 삭제 시 해당 데이터를 사용하고 있는 자식데이터의 값을 null로 변경
- on delete cascade : 부모데이터 삭제 시 해당 데이터를 사용하고 있는 자식데이터도 모두 삭제
*/
drop table mem;

create table mem(
mem_no number primary key, -- 컬럼 레벨 방식
mem_id varchar2(20) not null, 
mem_pwd varchar2(20) not null,
mem_name varchar2(20) not null,
gender char(3) check(gender in('남', '여')), 
phone varchar2(13),
email varchar2(50),
mem_date date,
grade_id number references mem_grade(grade_code) on delete set null
);

insert into mem values(1, 'user1', 'pass1', '홍길동', null, '010-1111-2222', 'aaa@naver.com', '24/12/23', null);

insert into mem values(2, 'user2', 'pass2', '홍길순', null, '010-1111-3333', 'bbb@naver.com', '24/12/23', 10);

insert into mem values(3, 'user3', 'pass3', '홍길삼', null, '010-1111-4444', 'ccc@naver.com', '24/12/23', 30);

insert into mem values(4, 'user4', 'pass4', '홍길사', null, '010-1111-5555', 'ddd@naver.com', '24/12/23', 20);

delete from mem_grade where grade_code = 10;

drop table mem;

create table mem(
mem_no number primary key, -- 컬럼 레벨 방식
mem_id varchar2(20) not null, 
mem_pwd varchar2(20) not null,
mem_name varchar2(20) not null,
gender char(3) check(gender in('남', '여')), 
phone varchar2(13),
email varchar2(50),
mem_date date,
grade_id number references mem_grade(grade_code) on delete cascade
);

insert into mem values(1, 'user1', 'pass1', '홍길동', null, '010-1111-2222', 'aaa@naver.com', '24/12/23', null);

insert into mem values(2, 'user2', 'pass2', '홍길순', null, '010-1111-3333', 'bbb@naver.com', '24/12/23', 10);

insert into mem values(3, 'user3', 'pass3', '홍길삼', null, '010-1111-4444', 'ccc@naver.com', '24/12/23', 30);

insert into mem values(4, 'user4', 'pass4', '홍길사', null, '010-1111-5555', 'ddd@naver.com', '24/12/23', 20);

delete from mem_grade where grade_code = 10;

----------------------------------------------------------------------
/*
<default 기본값> *제약조건은 아니다.
컬럼을 선정하지 않고 insert시에 null이 아닌 기본값을 insert하고자 한다.
미리 값을 세팅해둘 수 있다.

컬럼값 자료형 default 기본값
*/
drop table member;

create table member(
mem_no number primary key,
mem_name varchar2(20) not null,
mem_age number,
hobby varchar2(20) default '없음',
enroll_date date default sysdate
);

-- insert into 테이블명 values (값1, 값2, ...)
insert into member values(1, '빵빵이', 20, '운동', '20/01/01');
insert into member values(2, '옥지', 20, null, null);
insert into member values(3, '최지원', 19, default, default);

-- insert into 테이블명 (컬럼1, 컬럼2, ...) values (값1, 값, ...)
insert into member(mem_no, mem_name) values(4, '최지투');
--> 선택되지 않은 컬럼에는 기본적으로 null이 적용됨
--> 단, 해당 컬럼에 default값이 부여되어 있을 경우 null이 아닌 default값이 들어감

--------------------------------------------------------------------------
-- 관리자 계정으로 생성
create user c##kh identified by kh;
grant connect, resource to c##kh;
alter user c##kh default tablespace users quota unlimited on users;
-- kh 계정으로 전환

--------------------------------------------------------------------------
-- 테이블을 복제할 수 있다. 여기서부터 kh계정 이용
create table employee_copy as (select * from employee);

drop table employee_copy;

-- 테이블 구조만 복사
create table employee_copy as (select * from employee where 1=0);

--------------------------------------------------------------------------
/*
테이블이 다 생성된 후에 제약조건을 추가하는 방법
alter table 테이블명 변경할 내용

- primary key : alter table 테이블명 add primary key (컬럼명);
- foreign key : alter table 테이블명 add foreign key (컬럼명) references 참조할 테이블명(컬럼명);
- unique : alter table 테이블명 add unique (컬럼명);
- check : alter table 테이블명 add check (컬럼에 대한 조건식);
- not null : alter table 테이블명 modify 컬럼명 not null;
*/

-- employee_copy 테이블에 primary key 제약조건 추가(emp_id)
alter table employee_copy add primary key(emp_id);

-- employee 테이블에 dept_code에 외래키 제약조건 추가
alter table employee add foreign key(dept_code) references department;

-- employee 테이블에 job_code에 외래키 제약조건 추가
alter table employee add foreign key(job_code) references job;

-- department 테이블에 location_id에 외래키 제약조건 추가
alter table department add foreign key(location_id) references location;