/*
DQL(QUERY 데이터 질의어) : SELECT
DML(MANIPULATION 데이터 조작어) : INSERT, UPDATE, DELETE
DDL(DEFINITION 데이터 정의어) : CREATE, ALTER, DROP
DCL(CONTROL 데이터 제어어) : GRANT, REVOKE
TCL(TRANSACTION 트랜잭션 제어어) : COMMIT, ROLLBACK

<DML>
데이터 조작어
테이블 값을 삽입(INSERT), 수정(UPDATE), 삭제(DELETE)하는 구문
*/

/*
1. INSERT
테이블에 새로운 행일 추가하는 구문

[표현식]
1) insert into 테이블명 values (값, 값, 값, 값 ...)
테이블의 모든 컬럼에 대한 값을 직접 제시해서 한 행을 insert하고자 할 때
컬럼의 순번을 지켜서 values에 값을 나열해야 한다.

부족하게 값을 제시할 경우, 값을 더 많이 제시한 경우 -> 에러 발생
*/
select * from employee;

insert into employee 
values(900, '이소근', '880914-1456789', 'sg8809@naver.com', '01078945656', 'D7', 'J5', 4000000, 0.2, 200, sysdate, null, 'N');

/*
2) insert into 테이블명 (컬럼, 컬럼, 컬럼 ...) values (값, 값, 값, 값 ...)
테이블에 내가 선택한 걸럼에 대한 값만 insert 할 수 있다.
그래도 한 행 단위로 추가되기 때문에 선택 안 된 컬럼은 기본적으로 null이 들어감
-> not null 제약 조건이 걸려있는 컬럼은 반드시 직접 값을 넣어줘야 한다.
단, 기본값이 지정되어 있으면 null이 아닌 기본값이 들어간다.
*/
insert into employee(emp_id, emp_name, emp_no, job_code, hire_date) values (901, '최지원', '440701-1234567', 'J7', sysdate);

insert 
into employee(
                    emp_id,
                    emp_name,
                    emp_no,
                    job_code,
                    hire_date
                    )
            values(
                   902,
                   '김개똥',
                   '870105-224510',
                   'J7',
                   sysdate
                   );
                   
---------------------------------------------------------------------------
/*
3) insert into 테이블명 (서브쿼리)
values에 직접 값을 명시하는 것 대신 서브쿼리로 조회된 값을 통째로 insert 가능
*/
create table emp_01(
emp_id number,
emp_name varchar2(20),
dept_title varchar2(20)
);

select * from emp_01;

insert into emp_01(select emp_id, emp_name, dept_title from employee left join department on (dept_code = dept_id));

--------------------------------------------------------------------------
/*
2. insert all
두 개 이상의 테이블에 각각 insert 할 때 
이때 사용되는 서브쿼리가 동일한 경우

[표현식]
insert all into 테이블명1 values (컬럼, 컬럼, 컬럼 ...) into 테이블명2 values (컬럼, 컬럼, 컬럼 ...) 서브쿼리;
*/
create table emp_dept as (select emp_id, emp_name, dept_code, hire_date from employee where 1 = 0);

create table emp_manager as (select emp_id, emp_name, manager_id from employee where 1 = 0);

-- 부서코드가 D1인 사원들의 사번, 이름, 부서코드, 입사일, 사수사번 조회
select emp_id, emp_name, dept_code, hire_date, manager_id from employee where dept_code = 'D1';

insert all into emp_dept values (emp_id, emp_name, dept_code, hire_date) into emp_manager values (emp_id, emp_name, manager_id)
(select emp_id, emp_name, dept_code, hire_date, manager_id from employee where dept_code = 'D1');

select * from emp_dept;
select * from emp_manager;

----------------------------------------------------------------------------
/*
3. update
테이블에 기록되어있는 기존의 데이터를 수정하는 구문

[표현식]
update 테이블명 set 컬럼 = '값', 컬럼 = '값' ... 
[where 조건] -> 생략시 전체 모든 행의 데이터가 변경

*업데이트시에도 제약조건은 잘 확인해야 한다.
*/
create table dept_table as (select * from department);

select * from dept_table;

-- D9 부서의 부서명을 전략기획팀으로 변경
update dept_table set dept_title = '전략기획팀' where dept_id = 'D9';

create table emp_salary as (select emp_id, emp_name, dept_code, salary, bonus from employee);

select * from emp_salary;

-- emp_salary 테이블에서 노옹철 사원의 급여를 100만원으로 변경
update emp_salary set salary = 1000000 where emp_name = '노옹철';

-- emp_salary 테이블에서 선동일 사원의 급여를 700만원, 보너스를 0.2로 변경
update emp_salary set salary = 7000000, bonus = 0.2 where emp_name = '선동일';

-- emp_salary 테이블에서 전체 사원의 급여를 기존 급여에 10프로 인상된 금액으로 변경
update emp_salary set salary = salary * 1.1;

/*
update에서 서브쿼리 사용

[표현법]
update 테이블명 set 컬럼명 = (서브쿼리) where 조건
*/
-- 방명수 사원의 급여와 보너스 값을 유재식 사원의 급여와 보너스 값으로 변경
update emp_salary set salary = (select salary from emp_salary where emp_name = '유재식'), 
bonus = (select bonus from emp_salary where emp_name = '유재식') where emp_name = '방명수';

-- asia지역에서 근무하는 사원들의 보너스 값을 0.3으로 변경
update emp_salary set bonus = 0.3 where emp_id in (select emp_id from emp_salary join department on (dept_code = dept_id) 
join location on (location_id = local_code) where local_name like 'ASIA%');

commit;

--------------------------------------------------------------------------
/*
4. delete
테이블에 기록된 데이터를 삭제하는 구문(한 행 단위로 삭제가 된다.)

[표현식]
delete from 테이블명 
[where 조건] -> 생략시 전체 모든 행의 데이터가 삭제
*/
delete from employee;

select * from employee;
rollback;

delete from employee where emp_name = '이소근';

delete from employee where emp_id = '901';

select * from employee;

commit;

