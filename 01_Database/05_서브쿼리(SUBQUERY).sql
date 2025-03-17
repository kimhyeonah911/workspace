/*
*서브쿼리
- 하나의 SQL문 안에 포함된 또 다른 select문
- 메인 SQL문을 위해 보조 역할을 하는 쿼리
*/
-- 서브 쿼리 예시 1) 노옹철 사원과 같은 부서에 속한 사원들 전체 조회
select * from employee where dept_coode = 'D9';
-- 노옹철 사원의 부서는?
select dept_code from employee where emp_name = '노옹철';
-- 위에 두 쿼리를 하나의 쿼리로 변경하자
select * from employee where dept_code = (select dept_code from employee where emp_name = '노옹철');

-- 서브 쿼리 예시 2) 전 직원의 평균급여보다 더 많은 급여를 받는 사원들의 사번, 이름, 직급코드, 급여 조회
-- 전 직원의 평균 급여
select round(avg(salary)) from employee;

--3047663보다 더 많은 급여를 받는 사원의 사번, 이름, 직급코드, 급여 조회
select emp_id, emp_name, job_code, salary from employee where salary >= (select round(avg(salary)) from employee);

/*
*서브쿼리 구분
서브쿼리를 수행한 결과값이 몇 행 몇 열로 나오느냐에 따라서 분류
-- 단일행 서브쿼리 : 서브쿼리의 조회 결과값이 오로지 한 개일 때
-- 다중행 서브쿼리 : 서브쿼리의 조회 결과값이 여러행일 때(여러행 한열)
-- 다중행 서브쿼리 : 서브쿼리의 조회 결과값이 한 행이지만 컬럼이 여러개일 때
-- 다중행 다중열 서브쿼리 : 서브쿼리의 조회 결과값이 여러행 여러 컬럼일 때

>> 서브쿼리의 결과값에 따라서 서브쿼리 앞쪽에 연산자가 달라진다.
*/

/*
1. 단일행 서브쿼리
서브쿼리 결과로 값이 1일 때 사용한다(한행 한열)
일반적으로 비교연산자 사용 가능
= != > <= ...
*/
-- 1)전 직원의 평균 급여보다 급여를 더 적게 받는 사원들의 사원명, 직급코드, 급여 조회
select emp_name, job_code, salary from employee where salary < (select avg(salary) from employee);

-- 2)최저 급여를 받는 사원의 사번, 이름, 급여, 입사일 조회
select emp_id, emp_name, salary, hire_date from employee where salary = (select min(salary) from employee);

-- 3)노옹철 사원의 급여보다 많이 받는 사원들의 사번, 이름, 부서명, 급여 조회
select emp_id, emp_name, dept_title, salary from employee left join department on (dept_code = dept_id) 
where salary > (select salary from employee where emp_name = '노옹철');

-- 4)부서별 급여합이 가장 큰 부서의 부서코드, 급여합
select dept_code, sum(salary) from employee group by dept_code having sum(salary) = (select max(sum(salary)) 
from employee group by dept_code);

-- 5)'전지연'사원과 같은 부서의 사람들의 사번, 사원명, 전화번호, 입사일, 부서명 조회(단, 전지연 사원은 제외)
select emp_id, emp_name, phone, hire_date, dept_title from employee join department on (dept_code = dept_id) 
where dept_code = (select dept_code from employee where emp_name = '전지연') and emp_name != '전지연';

--------------------------------------------------------------------------
/*
2. 다중행 서브쿼리
서브쿼리를 수행한 결과값이 여러행일 때(컬럼은 한 개)
in, any, all 같은 다중행 연산자와 함께 사용

비교대상 in (서브쿼리) : 여러 개의 결과값 중에서 한 개라도 일치하는 값이 있다면 조회
비교대상 > any (서브쿼리) : 여러 개의 결과값 중에서 한 개라도 클 경우 조회
비교대상 < any (서브쿼리) : 여러 개의 결과값 중에서 한 개라도 작을 경우 조회
비교대상 > all (서브쿼리) : 여러 개의 모든 결과값들 보다 클 경우 조회
비교대상 < all (서브쿼리) : 여러 개의 모든 결과값들 보다 작을 경우 조회
*/
-- 1)유재식 또는 윤은해 사원과 같은 직급인 사원들의 사번, 사원명, 직급코드, 급여 조회
select emp_id, emp_name, job_code, salary from employee where job_code in (select job_code 
from employee where emp_name in ('유재식', '윤은해'));

-- 2) 대리직급임에도 과장 직급의 급여들 중에서 최소 급여보다도 많이 받는 사원들의 사번, 이름, 직급명, 급여 조회
select emp_id, emp_name, job_name, salary from employee join job using (job_code) 
where job_name = '대리' and salary > any(select salary from employee join job using (job_code) where job_name = '과장');

-------------------------------------------------------------------------
/*
3. 다중열 서브쿼리
결과값은 한 행이지만 나열된 컬럼수가 여러개일 경우(두 개 이상의 컬럼)
*/

-- 1)하이유 사원과 같은 부서코드, 같은 직급코드에 해당하는 사원의 사원명, 부서코드, 직급코드, 입사일 조회
select emp_name, dept_code, job_code, hire_date from employee where dept_code = (select dept_code 
from employee where emp_name = '하이유') and job_code = (select job_code from employee where emp_name = '하이유');

-- 다중열 서브쿼리
-- 튜플 비교 : 두 개 이상의 열(컬럼)을 조합해서 비교하는 방식
select emp_name, dept_code, job_code, hire_date from employee 
where (dept_code, job_code) = (select dept_code, job_code from employee where emp_name = '하이유');

-- 2)박나라 사원과 같은 직급코드, 같은 사수를 가지고 있는 사원들의 사번, 사원명, 직급코드, 사수번호 조회(단 박나라 사원은 제외)
select emp_id, emp_name, job_code, manager_id from employee 
where (job_code, manager_id) = (select job_code, manager_id from employee where emp_name = '박나라') and emp_name != '박나라';

-------------------------------------------------------------------------
/*
4. 다중행 다중열 서브쿼리
서브쿼리의 조회 결과값이 여러행 여러열일 경우(주로 튜플 비교와 함께 사용)
*/
-- 1)각 직급별 최소급여를 받는 사원의 사번, 사원명, 직급코드, 급여 조회
select emp_id, emp_name, job_code, salary from employee 
where (job_code, salary) in (select job_code, min(salary) from employee group by job_code);

-- 2)각 부서별 최고급여를 받는 사원들의 사번, 사원명, 부서코드, 급여 조회
select emp_id, emp_name, dept_code, salary from employee 
where (dept_code, salary) in (select dept_code, max(salary) from employee group by dept_code);

-- 3) 각 부서별 급여 합계가 전체 급여 총 합의 20%보다 많은 부서의 부서명, 부서별 급여 합계 조회
select dept_title, sum(salary) from employee join department on (dept_code = dept_id) 
group by dept_title having sum(salary) > (select sum(salary) * 0.2 from employee);

-------------------------------------------------------------------------
/*
5. 인라인 뷰
from절에서 서브쿼리를 작성한 것
서브쿼리에 수행 결과를 마치 테이블처럼 사용한다.
*/
-- 1)사원들의 사번, 이름, 보너스 포함 연봉, 부서코드 조회(단, 보너스 포함 연봉이 null이면 안 되고 연봉이 3000만원 이상인 사원들만 조회)
select rownum, emp_id, emp_name, (salary + (nvl(bonus, 0) * salary )) * 12 as "연봉", dept_code
from employee where (salary + (nvl(bonus, 0) * salary )) * 12 >= 30000000 order by 연봉 desc;
--> 인라인 뷰를 주로 사용하는 경우 -> top-n분석 : 상위 몇 개만 조회

-- 전 직원 중 급여가 가장 높은 5명만 조회
-- rownum : 오라클에서 제공해주는 컬럼, 조회된 순서대로 1부터 순번을 부여해주는 컬럼
-- rownum은 from 절에서 이미 결정된다.
select rownum, emp_name, salary from employee where rownum <= 5 order by salary desc;
--> order by절이 수행된 결과를 가지고 rownum 부여 -> 상위 5명
select rownum, emp_name, salary 
from (select rownum, emp_name, salary from employee order by salary desc) where rownum <=5;

-- 가장 최근에 입사한 사원 5명 조회(사원명, 급여, 입사일)
select rownum, emp_name, salary, hire_date 
from (select rownum, emp_name, salary, hire_date from employee order by hire_date desc) where rownum <= 5;

-- 각 부서별 평균 급여가 높은 3개 부서 조회
-- 인라인 뷰에 있는 산술식으로 된 컬럼을 가져오기 위해서는 별칭을 부여해야 한다.
select salary_avg
from (select dept_code, avg(salary) salary_avg from employee group by dept_code order by avg(salary) desc) where rownum <= 3;

-- 부서별 평균 급여가 270만원을 초과하는 부서들에 대해서 부서코드, 부서별 총 급여합, 부서별 평균급여, 부서별 사원수 조회
select dept_code, salary_sum, salary_avg, count_all
from (select dept_code, sum(salary) salary_sum, avg(salary) salary_avg, count(*) count_all from employee group by dept_code having avg(salary) >= 2700000);