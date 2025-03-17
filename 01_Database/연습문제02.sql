--1. 70년대 생(1970~1979) 중 여자이면서 전씨인 사원의 이름과 주민번호, 부서 명, 직급 조회
select emp_name, emp_no, dept_title, job_name from employee join department on (dept_code = dept_id) join job using (job_code)
where substr(emp_no, 1, 2) between 70 and 79 and substr(emp_no, 8, 1) in ('2', '4') and emp_name like '전%';

--3. 이름에 ‘형’이 들어가는 사원의 사원 코드, 사원 명, 직급 조회  
select emp_id, emp_name, job_name from employee join job using (job_code) where emp_name like '%형%';

--4. 부서코드가 D5이거나 D6인 사원의 사원 명, 직급 명, 부서 코드, 부서 명 조회
select emp_name, job_name, dept_code, dept_title from employee join job using (job_code) join department on (dept_code = dept_id)
where dept_code in ('D5', 'D6');

--5. 보너스를 받는 사원의 사원 명, 부서 명, 지역 명 조회
select emp_name, dept_title, local_name from employee join department on (dept_code = dept_id) join location on (local_code = location_id)
where bonus is not null;

--6. 사원 명, 직급 명, 부서 명, 지역 명 조회
select emp_name, job_name, dept_title, local_name 
from employee join job using (job_code) join department on (dept_code = dept_id) join location on (local_code = location_id);

--7. 한국이나 일본에서 근무 중인 사원의 사원 명, 부서 명, 지역 명, 국가 명 조회
select emp_name, dept_title, local_name, national_name 
from employee join department on (dept_code = dept_id) join location on (local_code = location_id) join national using (national_code)
where national_code in ('KO', 'JP');

--8. 같은 부서에서 일하는 다른 사원의 이름 조회(자체조인 활용)
select e.emp_name , e.dept_code , d.emp_name from employee e join employee d on (e.dept_code = e.dept_code) 
where e.emp_id != d.emp_id order by e.emp_id;

--9. 보너스가 없고 직급 코드가 J4이거나 J7인 사원의 이름, 직급 명, 급여 조회(NVL 이용)
select emp_name, job_name, salary from employee join job using (job_code) where nvl(bonus, 0) = 0 and job_code in ('J4', 'J7');

--10. 부서 명과 부서 별 급여 합계 조회
select dept_title, sum(salary) from employee join department on (dept_code = dept_id)
group by dept_code, dept_title order by dept_code;

--11. 부서 별 급여 합계가 전체 급여 총 합의 20%보다 많은 부서의 부서 명, 부서 별 급여 합계 조회
select dept_title, sum(salary) from employee join department on (dept_code = dept_id)
group by dept_code, dept_title having sum(salary) > (select sum(salary) from employee) * 0.2 order by dept_code;

--12. 나이 상 가장 막내의 사원 코드, 사원 명, 나이, 부서 명, 직급 명 조회
select emp_id, emp_name, 나이, job_name, dept_code 
from (select rownum, emp_id, emp_name, (extract(year from sysdate) - case when substr(emp_no, 1, 2) >= 50 then 1900 + substr(emp_no, 1, 2) else 2000 + substr(emp_no, 1, 2) end) as "나이", job_name, dept_code 
from employee join job using (job_code) join department on (dept_code = dept_id) order by 나이) where rownum = 1 ;
--막내인 사람이 두 명인 경우
select emp_id, emp_name, floor((sysdate - to_date(substr(emp_no, 1, 6), 'rr/mm/dd'))/365) as "나이", dept_title, job_name
from employee join department on (dept_code = dept_id) join job using(job_code) where floor((sysdate - to_date(substr(emp_no, 1, 6), 'rr/mm/dd'))/365) = (select min(floor((sysdate - to_date(substr(emp_no, 1, 6), 'rr/mm/dd'))/365) from employee);