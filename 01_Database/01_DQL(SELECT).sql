/*
테이블
- 데이터베이스에서 데이터를 저장하는 기본개념
- 행과 열로 구성된 데이터 집합

컬럼
- 테이블 내의 각 데이터 속성을 정의하는 필드
- 컬럼은 테이블에서 저장할 때 속성 = 값 으로 저장한다.

=> 테이블은 여러 컬럼으로 구성되고, 각 컬럼은 테이블이 표현하는 데이터의 세부적인 속성을 나타냄.

<select> 
select 가지고 오고 싶은 정보 from 테이블;
select 컬럼1, 컬럼2, 컬럼3 ... from 테이블;
*/

--모든 사원의 정보를 보여줘
select * from employee;

--모든 사원의 이름, 주민등록번호, 핸드폰번호
select emp_name, emp_no, phone from employee;

--------------실습----------------
--job 테이블의 직급명 컬러만 조회
select job_name from job;

--department 테이블의 모든 컬럼 조회
select * from department;

--department 테이블의 부서코드, 부서명 조회
select dept_id, dept_title from department;

--employee 테이블의 사원명, 이메일, 전화번호, 입사일, 급여 조회
select emp_name, email, phone, hire_date, salary from employee;

-- <컬럼값을 통한 산술연산>
-- select절 컬럼명 작성부분에 산술연산을 할 수 있다.

-- employee 테이블의 사원명, 사원의 연봉(급여 * 12)를 조회
select emp_name, salary * 12 from employee;

-- employee 테이블의 사원명, 급여, 사원의 보너스 포함 연봉((급여 + (급여 * 보너스)) * 12)를 조회
select emp_name, salary, bonus, ((salary + (salary * bonus)) * 12) from employee;
-- 데이터베이스에서 null은 빈 값을 의미한다.
-- 모든 연산에 null이 포함된 경우 결과는 null이 된다.

-- employee 테이블의 사원명, 입사일, 근무일수를 조회
-- 데이터베이스에서는 날짜를 계산할 때 덧셈 뺄셈이 가능하다.
-- 현재시간 - 입사일 = 근무한 시간
-- date - date => 결과는 무조건 일로 표시가 된다.
-- 코드 실행 시 현재 날짜를 표시하는 상수 : sysdate [년/월/일/시/분/초]
select emp_name, hire_date, sysdate - hire_date from employee;

// dual 테이블은 오라클에서 제공하는 가상테이블이다.
select sysdate from dual;

/*
<컬럼에 별칭 지정하기>
산술연산을 하게 되면 컬럼명이 지저분해진다. 이때 컬럼명에 별칭을 부여해서 깔끔하게 가져올 수 있다.
[표현법]
컬럼명 별칭 / 컬럼명 as 별칭 / 컬럼명 "별칭" / 컬럼명 as "별칭"
*/

select emp_name 사원명, salary as 급여, bonus "사원의 보너스",(salary * 12) as "연봉" from employee;

/*
<리터럴>
임의로 지정한 문자열('')
*/
-- employee 테이블의 사번, 사원명, 급여
select emp_id, emp_name, salary, '원' as "단위" from employee;

/*
<연결연산자 : ||>
여러 컬럼값들을 마치 하나의 컬럼처럼 연결할 수 있다.
*/
-- employee 테이블의 사번, 이름, 급여를 하나의 컬럼으로 조회
select emp_id || emp_name || salary from employee; 

-- employee 테이블에서 모든 사원의 월급을 조회
-- 다음과 같이 결과가 나오도록 출력하라
-- xx의 월급은 xx원입니다.
select emp_name || '의 월급은 ' || salary || '원입니다.' from employee;

/*
<distinct>
중복제거 - 컬럼에 표시된 값들을 한 번씩만 조회하고자 할 때 사용
*/

select distinct job_code from employee;
select distinct dept_code from employee;
-- select distinct job_code, distinct dept_code from employee;
-- 위처럼 작성시 에러가 발생한다. distinct는 한 명령어에서 한 번만 사용 가능
select distinct job_code, dept_code from employee;
-- 위처럼 사용시 (job_code, dept_code)를 쌍으로 묶어서 중복을 제거한 값을 보여준다.

/*
<where 절>
조회하고자 하는 테이블로부터 특정 조건에 만족하는 데이터만 조회할 때 사용
조건식에서도 다양한 연산자를 사용할 수 있다.
[표현법]
select 컬럼, 컬럼, 컬럼 ... from 테이블명 where 조건;

>>비교연산자<<
>, <, >=, <= : 대소비교
= : 양쪽이 동일하다.
!=, ^=, <> : 양쪽이 다르다.
*/
-- employee 테이블에서 부서코드가 D9인 사원들만 조회(모든 컬럼)
select * from employee where dept_code = 'D9';

-- employee에서 부서코드가 'D1'인 사원들의 사원명, 급여, 부서코드 조회
select emp_name, salary, dept_code from employee where dept_code = 'D1';

-- 월급이 400만원 이상인 사원들의 사원명, 부서코드, 급여 조회
select emp_name, salary, dept_code from employee where salary >= 4000000;

----------------------실습-----------------------
-- 급여가 300만원 이상인 사원들의 사원명, 급여, 입사일, 연봉(별칭 -> 연봉) 조회
select emp_name, salary, hire_date, salary * 12 as 연봉 from employee where salary >= 3000000;

-- 연봉이 5천만원 이상인 사원들의 사원명, 급여, 연봉(별칭->연봉), 부서코드 조회
select emp_name, salary, salary * 12 as 연봉, dept_code from employee where salary * 12 >= 50000000;

-- 직급코드가 'J3'가 아닌 사원들의 사번, 사원명, 직급코드, 퇴사여부 조회
select emp_id, emp_name, job_code, ent_yn from employee where job_code != 'J3';

-- 급여가 350만원 이상 600만원 이하인 모든 사원의 사원명, 사번, 급여 조회
-- 중간에 and, or로 조건을 연결할 수 있다.
select emp_name, emp_id, salary from employee where salary >= 3500000 and salary <= 6000000;

/*
<and, or 연산자>
조건을 여러개 연결할 때 사용한다.

[표현법]
조건 a and 조건 b -> 조건 a와 조건 b가 모두 만족하는 값만 참으로 한다.
조건 a or 조건 b -> 조건 a와 조건 b 중 하나만 만족해도 값을 참으로 한다.

<between and>
조건식에 사용되는 구문
몇 이상 몇 이하인 범위에 대한 조건을 제시할 때 주로 사용하는 연산자(이상, 이하만 가능)

[표현법]
비교대상 컬럼 between 하안값 and 상한값;
*/
-- 급여가 350만원 이상 600만원 이하인 모든 사원의 사원명, 사번, 급여 조회
select emp_name, emp_id, salary from employee 
--where salary >= 3500000 and salary <= 6000000;
where salary not between 3500000 and 6000000;
-- not : 논리 부정 연산자
-- 컬럼명 앞에 또는 between 앞에 선언 가능

-- 입사일이 '90/01/01' 이상'01/01/01' 이하인 사원들을 전체 조회
select * from employee where hire_date between '90/01/01' and '01/01/01';

-- null을 비교연산할 때는 =를 사용할 수 없다.
-- null 값을 비교할 때는 is null, is not null을 사용한다.
select * from employee 
--where bonus is null;
where bonus is not null;

/*
<like>
비교하고자 하는 컬럼값이 내가 제시한 특정 패턴에 만족할 경우 조회

[표현법]
비교할 대상 컬럼 like '특정패턴';

특정 패턴을 제시할 때 와일드 카드라는 특정패턴이 정의되어있다.
1. '%' : 포함문자 검색(0글자 이상 전부 조회)
ex) 비교할 대상 컬럼 like '문자%' : 비교할 대상 컬럼 값 중에서 해당 문자로 시작하는 값들을 전부 가져온다.
    비교할 대상 컬럼 like '%문자' : 비교할 대상 컬럼 값 중에서 해당 문자로 끝나는 값들을 전부 가져온다.
    비교할 대상 컬럼 like '%문자%' : 비교할 대상 컬럼 값 중에서 해당 문자가 포함된 값들을 전부 가져온다.

2. '_' : 1글자를 대체 검색할 때 사용
ex) 비교할 대상 컬럼 like '_문자' : 비교할 대상 컬럼 값 문자 앞에 아무 글자나 딱 한글자가 있는 값을 조회
    비교할 대상 컬럼 like '문자_' : 비교할 대상 컬럼 값 문자 뒤에 아무 글자나 딱 한글자가 있는 값을 조회
    비교할 대상 컬럼 like '_문자_' : 비교할 대상 컬럼 값 문자 앞뒤에 아무 글자나 딱 한글자가 있는 값을 조회
    
    비교할 대상 컬럼 like '__문자___' : 내가 원하는 형태로 값을 조정할 수 있다.
*/
select emp_name, salary from employee where emp_name like '전%';
select emp_name, salary from employee where emp_name like '%연';
select emp_name, salary from employee where emp_name like '%하%';

-- 사원들 중에 이름에 중간에 '하'라는 글자가 포함된 사원의 이름, 전화번호 출력
select emp_name, phone from employee where emp_name like '_하_';

-- 사원들 중에서 전화번호 3번째 자리가 1인 사원들의 사번, 사원명, 전화번호 조회
select emp_id, emp_name, phone from employee where phone like '__1%';

-- 이메일 중 _앞의 글자가 3글자인 사원들의 사번, 이름, 이메일 조회
select emp_id, emp_name, email from employee 
-- where email like '____%'; -> 와일드 카드로 인식되기 때문에 정상적으로 출력할 수 없다.
-- 와일드 카드와 일반 문자를 구분해줘야 한다.
-- 데이터 값으로 취급하고 싶은 와일드 카드 문자 앞에 나만의 탈출 문자를 제시해서 탈출시켜주면 된다.
-- escape option을 등록해서 사용
where email like '___/_%' escape '/';

/*
<in>
where절에 비교대상 컬럼 값이 내가 제시한 목록 중에 일치하는 값이 있는지를 검사하는 문법

[표현법]
비교대상 컬럼 in ('값', '값', '값' ...)
*/
-- 부서코드가 D6이거나 D8이거나 D5인 부서원들의 이름, 부서코드, 급여 조회
select emp_name, dept_code, salary from employee where dept_code in ('D6', 'D8', 'D5');

/*
<연산자 우선순위>
1. 산술연산자
2. 연결연산자
3. 비교연산자
4. is null / like / in
5. between A and B
6. not
7. and
8. or
*/

---------------------실습-----------------------
-- 이름이 '연'으로 끝나는 사원들의 사원명, 입사일 조회
select emp_name, hire_date from employee where emp_name like '%연';

-- 전화번호 처음 3자리가 010이 아닌 사원들의 사원명, 전화번호 조회
select emp_name, phone from employee where phone not like '010%';

-- 이름에 '하'가 포함되어있고 급여가 240만원 이상인 사원들의 사원명, 급여 조회
select emp_name, salary from employee where emp_name like '%하%' and salary >= 2400000;

-- 부서(dert)테이블에서 해외영업부인 부서들의 부서코드, 부서명 조회
select dept_id, dept_title from department where dept_title like '해외영업%';

-- 사수(manager)가 없고 부서배치도 받지 않은 사원들의 사원명, 사번, 부서코드 조회
select emp_name, emp_id, dept_code from employee where manager_id is null and dept_code is null;

-- 연봉(급여 * 12)이 3천만원 이상이고 보너스를 받지 않는 사원들의 사번, 사원명, 급여, 보너스 조회
select emp_id, emp_name, salary, bonus from employee where salary * 12 >= 30000000 and bonus is null;

-- 입사일이 '95/01/01' 이상이고 부서배치를 받지 않은 사원들의 사번, 사원명, 입사일, 부서코드 조회
select emp_id, emp_name, hire_date, dept_code from employee where hire_date >= '95/01/01' and dept_code is null;

-- 급여가 200만원 이상이고 500만원 이하인 사원 중에서 입사일이 '01/01/01' 이상이고 보너스를 받지 않은 사원들의 사번, 사원명, 급여, 입사일, 보너스 조회
select emp_id, emp_name, salary, hire_date, bonus from employee 
where salary between 2000000 and 5000000 and hire_date >= '01/01/01' and bonus is null;

-- 보너스를 포함 연봉(salary + (salary * bonus) * 12)이 null이 아니고 이름에 '하'가 포함된 사원들의 사번, 사원명, 급여, 보너스 포함 연봉 조회
select emp_id, emp_name, salary, (salary + (salary * bonus)) from employee 
where (salary + (salary * bonus)) is not null and emp_name like '%하%';

