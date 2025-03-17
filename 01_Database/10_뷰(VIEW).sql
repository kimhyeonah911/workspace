/*
<view 뷰>

select문 (쿼리문)을 저장해서 사용할 수 있는 객체
(자주 사용하는 select문을 저장해두면 긴 select문을 매번 다시 기술할 필요없이 사용할 수 있다.)
가상테이블 -> 실제 데이터가 담겨 있는 것은 아니다(논리테이블)
*/
-- 한국에서 근무하는 사원들의 사번, 이름, 부서명, 급여, 근무국가명 조회
select emp_id, emp_name, dept_title, salary, national_name from employee join department on (dept_code = dept_id) 
join location on (location_id = local_code) join national using (national_code) where national_name = '한국';

-- 러시아에서 근무하는 사원들의 사번, 이름, 부서명, 급여, 근무국가명 조회
select emp_id, emp_name, dept_title, salary, national_name from employee join department on (dept_code = dept_id) 
join location on (location_id = local_code) join national using (national_code) where national_name = '러시아';

-- 일본에서 근무하는 사원들의 사번, 이름, 부서명, 급여, 근무국가명 조회
select emp_id, emp_name, dept_title, salary, national_name from employee join department on (dept_code = dept_id) 
join location on (location_id = local_code) join national using (national_code) where national_name = '일본';

/*
1. view 생성 방법

[표현법]
create view 뷰명 as 서브쿼리
*/
create view vw_employee as (select emp_id, emp_name, dept_title, salary, national_name from employee 
join department on (dept_code = dept_id) join location on (location_id = local_code) join national using (national_code));

-- grant create view to c##kh; -- 뷰 생성 권한

select * from vw_employee;

-- 실제 실행되는 것은 서브쿼리(인라인뷰)로 실행된다고 볼 수 있다.
select * from (select emp_id, emp_name, dept_title, salary, national_name from employee join department on (dept_code = dept_id) 
join location on (location_id = local_code) join national using (national_code));

-- 한국에서 근무하는 사원들의 사번, 이름, 부서명, 급여, 근무국가명 조회
select emp_id, emp_name, dept_title, salary, national_name from vw_employee where national_name = '한국';

-- 러시아에서 근무하는 사원들의 사번, 이름, 부서명, 급여, 근무국가명 조회
select emp_id, emp_name, dept_title, salary, national_name from vw_employee where national_name = '러시아';

-- 일본에서 근무하는 사원들의 사번, 이름, 부서명, 급여, 근무국가명 조회
select emp_id, emp_name, dept_title, salary, national_name from vw_employee where national_name = '일본';

-- create or replace
-- view가 없을 때는 생성, 이미 존재한다면 수정할 수 있다.

create or replace view vw_employee as (select emp_id, emp_name, dept_title, salary, national_name from employee 
join department on (dept_code = dept_id) join location on (location_id = local_code) join national using (national_code));

select * from vw_employee;

-------------------------------------------------------------------------
/*
뷰 컬럼에 별칭 부여
서브쿼리의 select절에 함수식이나 산술연산식이 기술되어있다면 반드시 별칭을 부여해야 한다.
*/
create or replace view vw_emp_job as (select emp_id, emp_name, job_name, decode(substr(emp_no, 8, 1), '1', '남', '2', '여') as "gender", 
extract(year from sysdate) - extract(year from hire_date) as "serviceyear" from employee join job using (job_code));

select * from vw_emp_job;

select * from vw_emp_job where serviceyear >= 20;

drop view vw_emp_job;

-------------------------------------------------------------------------
-- 생성된 뷰를 통해서 dml(insert, update, delete) 사용 가능하다.
-- 뷰를 통해서 조작하게 되면 실제 데이터가 담겨있는 테이블에 반영된다.
create or replace view vw_job as (select job_code, job_name from job);

select * from vw_job; -- 논리테이블(실제 데이터가 담겨있지 않다.)
select * from job;

insert into vw_job values('J8', '인턴');

update vw_job set job_name = '알바' where job_code = 'J8';

select * from vw_job;
select * from job;

-------------------------------------------------------------------------
/*
* dml 명령어로 조작이 불가능한 경우

1) 뷰에 정의되어있지 않은 컬럼을 조작하려는 경우
2) 뷰에 정의되어있지 않은 컬럼 중에 베이스 테이블 상에 not null 제약 조건이 지정되어 있는 경우
3) 산술연산식이나 함수식을 사용한 경우
4) 그룹함수나 group by절을 포함한 경우
5) distinct구문이 포함된 경우
6) join을 이용해서 여러테이블을 연관시켜놓은 경우

대부분 뷰는 조회를 목적으로 사용한다. 그냥 뷰를 통한 dml은 안 쓰는 게 좋다.
*/

/*
view 옵션

[상세표현식]
create [or replace] [force | noforce] view 뷰명 as 서브쿼리 [with check option] [with read only]

> force : 서브쿼리에 기술된 테이블이 존재하지 않아도 뷰를 생성
> noforce : 서브쿼리에 기술된 테이블이 존재하는 테이블이여야만 한다.(기본값)
> with check option : dml시 서브쿼리에 기술된 조건에 부합한 값으로만 dml이 가능하도록
> with read only : 뷰에 대해서 조회만 가능하도록
*/
-- force | noforce
create or replace noforce view vw_emp as (select tcode, tname, tcontent from tt);

-- 서브쿼리에 기술된 테이블이 존재하지 않아도 뷰가 우선은 생성이 된다.
create or replace force view vw_emp as (select tcode, tname, tcontent from tt);

select * from vw_emp;

create table tt (
     tcode number, 
     tname varchar2(20),
     tcontent varchar2(30)
);
select * from vw_emp;

-- with check option
create or replace view vw_emp as (select * from employee where salary >= 3000000);

select * from vw_emp;

-- 200번 사원 급여를 200만원으로 변경
update vw_emp set salary = 2000000 where emp_id = 200;

rollback;

create or replace view vw_emp as (select * from employee where salary >= 3000000) with check option;

update vw_emp set salary = 2000000 where emp_id = 200;

-- with read only : 뷰에 대해서 조회만 가능하도록 설정
create or replace view vw_emp as (select emp_id, emp_name, bonus from employee where bonus is not null) with read only;

select * from vw_emp;

delete from vw_emp where emp_id = 200;