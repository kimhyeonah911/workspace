/*
<group by>
그룹 기준을 제시할 수 있는 구문(해당 그룹 기준별로 여러 그룹으로 묶을 수 있음)
여러 개의 값들을 하나의 그룹으로 묶어서 처리하는 목적으로 사용
*/
select sum(salary) from employee; -- 전체 사원을 하나의 그룹으로 묶어서 값을 구한 것

-- 각 부서별로 급여 총합
-- 그룹 : 부서
select dept_code, sum(salary) from employee group by dept_code;

-- 각 부서별 사원수
select dept_code, count(*) from employee group by dept_code;

select dept_code, count(*), sum(salary) -- 3
from employee -- 1
group by dept_code -- 2 
order by dept_code; -- 4

-- 각 직급별 총사원수, 보너스를 받은 사람 수, 급여합, 평균급여, 최저시급, 최고급여(정렬 = 직급 내림차순)
select job_code, count(*), count(bonus), sum(salary), avg(salary), min(salary), max(salary) 
from employee group by job_code order by job_code desc;

-- group by절에 함수식 사용가능(그룹을 나눌 수 있는 기준만 넣어주면 된다.)
select decode(substr(emp_no, 8, 1), '1', '남', '2', '여'), count(*) from employee group by substr(emp_no, 8, 1);

-- group by절에 여러 컬럼을 기술할 수 있음
select dept_code, job_code, count(*) from employee group by dept_code, job_code;

------------------------------------------------------------------------
/*
[having 절]
그룹에 대한 조건을 제시할 때 사용되는 구문(주로 그룹함수식을 가지고 조건을 만든다.)
*/
-- 각 부서별 평균 급여(부서코드, 평균급여)
select dept_code, round(avg(salary)) from employee group by dept_code;

-- 각 부서별 평균 급여가 300만원 이상인 부서들만 조회(부서코드, 평균급여)
select dept_code, round(avg(salary)) from employee group by dept_code having avg(salary) >= 3000000;

-- 직급별 직급코드, 총급여합(단, 직급별 총급여합이 1000만원 이상인 직급만 조회)
select job_code, sum(salary) from employee group by job_code having sum(salary) >= 10000000;

-- 부서별 보너스를 받는 사원이 없는 부서의 부서코드
select dept_code, count(bonus) from employee group by dept_code having count(bonus) = 0;

-------------------------------------------------------------------------------
/*
select *                                  -- 5
from 테이블                                -- 1
where 조건식                               -- 2
group by 그룹의 기준이 되는 컬럼 | 함수식     -- 3
having 조건식                              -- 4
order by 정렬기준 컬럼                      -- 6
(오라클에는 존재x)limit
*/ 



