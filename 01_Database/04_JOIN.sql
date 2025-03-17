/*
<join> 
두 개 이상의 테이블에서 데이터를 조회하고자 할 때 사용되는 구문
조회 결과는 하나의 결과(result set)를 반환한다.

관계형 데이터베이스에서는 최소한의 데이터를 각각의 테이블에 담고 있음
(무작정 다 조회해 오는 것이 아니라 각 테이블간 연결고리(외래키)를 통해 데이터를 매칭시켜 조회해야 한다.)

join은 크게 "오라클 전용구문" "ansi 구문"

[용어정리]
   오라클 전용 구문    |     ansi구문
----------------------------------------
       등가조인       |      내부조인
    (equal join)     |   (inner join) 
----------------------------------------
       포괄조인       |      외부조인
----------------------------------------
  자체조인/비등가조인  |
*/
-- 전체 사원들의 사번, 사원명, 부서코드, 부서명
select emp_id, emp_name, dept_code from employee;
select dept_id, dept_title from department;
select emp_id, emp_name, dept_code, dept_title from employee, department where dept_code = dept_id;

/*
1. 등가조인(equal join) / 내부조인(inner join)
연결시키는 컬럼의 값이 일치하는 행들만 조회(일치하는 값이 없는 행은 조회 제외)
*/
--> 오라클 전용구문
-- from절에 조회하고자 하는 테이블을 나열(,구분)
-- where절에 매칭시킬 컬럼에 대한 조건을 제시

-- 1. 연결할 두 컬럼명이 다른 경우(employee : dept_code / department : dept_id)
-- 전체 사원들의 사번, 사원명, 부서코드, 부서명
select emp_id, emp_name, dept_code, dept_title from employee, department where dept_code = dept_id;

-- 2. 연결할 두 컬럼명이 동일한 경우
-- 전체 사원들의 사번, 사원명, 직급코드, 직급명
select emp_id, emp_name, j.job_code, job_name from employee e, job j where e.job_code = j.job_code;

-- 추가적인 조건 제시
-- 직급에 대리인 사원의 사번, 사원명, 직급명, 급여 조회
select emp_id, emp_name, job_name, salary from employee e, job j 
where e.job_code = j.job_code and job_name = '대리';

--> ansi구문
-- from절에 기준이 되는 테이블 하나만 기술한다.
-- join절에 같이 조인하고자 하는 테이블 기술 + 매칭 시킬 컬럼에 대한 조건
-- (inner) join using / join on

-- 1. 연결할 두 컬럼명이 다른 경우(employee : dept_code / department : dept_id)
-- 전체 사원들의 사번, 사원명, 부서코드, 부서명
select emp_id, emp_name, dept_code, dept_title from employee join department on(dept_code = dept_id);

-- 2. 연결할 두 컬럼명이 동일한 경우
-- 전체 사원들의 사번, 사원명, 직급코드, 직급명
select emp_id, emp_name, job_code, job_name from employee join job using (job_code);

-- 추가적인 조건 제시
-- 직급에 대리인 사원의 사번, 사원명, 직급명, 급여 조회
select emp_id, emp_name, job_name, salary from employee join job using (job_code) where job_name = '대리';

-----------------------실습-------------------------
-- 부서가 인사관리부인 사원들의 사번, 이름, 보너스 조회
select emp_id, emp_name, bonus from employee join department on (dept_code = dept_id) where dept_title = '인사관리부';

-- department와 location 테이블을 참고하여 전체 부서의 부서코드, 부서명, 지역코드, 지역명 조회
select dept_id, dept_title, national_code, local_name from department join location on (location_id = local_code);

-- 보너스를 받는 사원들의 사번, 사원명, 보너스, 부서명 조회
select emp_id, emp_name, bonus, dept_title from employee join department on (dept_code = dept_id) where bonus is not null;

-- 부서가 총무부가 아닌 사원들의 사원명, 급여 조회
select emp_name, salary from employee join department on (dept_code = dept_id) where dept_title != '총무부'; 

-----------------------------------------------------------------
/*
2. 포괄조인 / 외부조인(outer join)
두 테이블간의 join시 일치하지 않는 행도 포함시켜 조회가능
단, 반드시 left/right를 지정해야 된다.(어떤 테이블이 기준이냐)
*/
-- 외부 조인과 비교할만한 내부조인
-- 사원명, 부서명, 급여, 연봉
-- 부서 배치를 받지 않은 2명의 사원정보가 누락
select emp_name, dept_title, salary, salary * 12 from employee join department on (dept_code = dept_id);
-- 1) left (outer) join : 두 테이블 중 왼편에 기술된 테이블을 기준으로 join
select emp_name, dept_title, salary, salary * 12 from employee left join department on (dept_code = dept_id);
-- 오라클 구문
select emp_name, dept_title, salary, salary * 12 from employee, department where dept_code = dept_id(+);

-- 2) right (outer) join : 두 테이블 중 오른편에 기술된 테이블을 기준으로 join
select emp_name, dept_title, salary, salary * 12 from employee right join department on (dept_code = dept_id);
-- 오라클 구문
select emp_name, dept_title, salary, salary * 12 from employee, department where dept_code(+) = dept_id;

-- 3) full (outer) join : 두 테이블이 가진 모든 행을 조회할 수 있다.
select emp_name, dept_title, salary, salary * 12 from employee full join department on (dept_code = dept_id);

/*
3. 비등가 조인
매칭시킬 컬럼에 대한 조건 작성시 '='를 사용하지 않는 조인문
ansi구문 사용시 join on만 사용가능

범위(수치적 범위, 기간적 범위) 기반 조인을 구현할 때 주로 사용한다.
*/
-- 사원명, 급여, 급여레벨 조회
select emp_name, salary, sal_level from employee join sal_grade on (salary >= min_sal and salary <= max_sal);
-- 오라클
select emp_name, salary, sal_level from employee, sal_grade where salary between min_sal and max_sal;

--------------------------------------------------------------------------
/*
4. 자체조인(self join)
같은 테이블을 다시 한 번 조인하는 경우
- 하나의 테이블에서 서로 다른 두 개의 행을 비교해야 하는 경우 사용
- 동일한 테이블을 조인하는 것이기 때문에 별칭을 사용해서 구분해야 한다.
*/
-- 전체 사원의 사원사번, 사원명, 사원부서코드 -> employee e, 사수사번, 사수의 사원명, 사수의 부서코드 -> employee m
select e.emp_id, e.emp_name, e.dept_code, m.emp_id, m.emp_name, m.dept_code 
from employee e left join employee m on (e.manager_id = m.emp_id);
-- 오라클
select e.emp_id, e.emp_name, e.dept_code, m.emp_id, m.emp_name, m.dept_code, employee e 
where e.manager_id = m.emp_id(+);

-----------------------------------------------------------------------------
/*
<다중조인>
2개 이상의 테이블을 가지고 join할 수 있다.
*/
-- 사번, 사원명, 부서명, 직급명
select emp_id, emp_name, dept_title, job_name from employee 
join department on (dept_code = dept_id) join job using(job_code);
-- 오라클
select emp_id, emp_name, dept_title, job_name from employee e, department d, job j 
where e.dept_code = d.dept_id and e.job_code = j.job_code;

-- 사번, 사원명, 부서명, 지역명 조회
select emp_id, emp_name, dept_title, local_name from employee 
join department on (dept_code = dept_id) join location on (location_id = local_code);

-- 사번, 사원명, 부서명, 직급명, 지역명, 국가명, 급여등급 조회
select emp_id, emp_name, dept_title, job_name, local_name, national_name, sal_level from employee 
join department on (dept_code = dept_id) join job using(job_code) join location on (location_id = local_code)
join national using (national_code) join sal_grade on (salary between min_sal and max_sal);

----------------------------------------------------------------------------------
/*
내추럴 조인(natural join)
두 테이블 간에 공통된 컬럼 이름을 자동으로 기준을 정해서 조인하는 방식
on이나 using 조건을 지정하지 않아도 두 테이블간 공통된 컬럼을 기반으로 조인

동일한 컬럼이 있을 경우에만 정상적인 조인이 가능하며 명시적이지 않고 조인 조건을 정확하게 기술하기 어렵다.
*/
select * from employee natural join department;