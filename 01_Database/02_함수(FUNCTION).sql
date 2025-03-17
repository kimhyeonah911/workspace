select emp_id, emp_name salary -- 3 
from employee                  -- 1
where dept_code = 'D2';        -- 2

/*
<order by절>
select문 가장 마지막 줄에 작성, 실행 순서 또한 가장 마지막에 실행한다.

[표현법]
select 조회할 칼럼 ... from 조회할 테이블 where 조건식 
order by 정렬 기준이 될 컬럼명 | 별칭 | 컬럼 순번 [asc | desc] [nulls first | nulls last]

- asc : 오름차순(작은 값으로 시작해서 값이 점점 커지는 것) -> 기본값
- desc : 내림차순(큰 값으로 시작해서 값이 점점 작아직는 것)

-- null은 기본적으로 가장 큰 값으로 분류해서 정렬한다.
-- nulls first : 정렬하고자 하는 컬럼값에 null이 있을 경우 해당 데이터 맨 앞에 배치(desc일 때 기본값)
-- nulls last : 정렬하고자 하는 컬럼값에 null이 있을 경우 해당 데이터 맨 위에 배치(asc일 때 기본값)
*/

select * from employee;
--order by bonus;
--order by bonus asc;
--order by bonus asc nulls first;
--order by bonus desc; --nulls first가 기본값으로 적용
--정렬 기준에 컬럼값이 동일한 경우 그 다음차순을 위해서 여러개를 제시할 수 있다.
--order by bonus desc, salary asc;

-- 전 사원의 사원명, 연봉 조회(이 때 연봉을 기준으로 내림차순 정렬)
select emp_name, salary * 12 as 연봉 from employee;
--order by salary * 12 desc;
--order by 연봉 desc;
--order by 2 desc;

/*
<함수 function>
전달된 컬럼값을 읽어들여서 함수를 실행한 결과를 반환

- 단일행 함수 : N개의 값을 읽어들여서 N개의 결과값을 리턴(매행마다 함수 실행 결과를 반환)
- 그룹 함수 : N개의 값을 읽어들여서 1개의 결과값을 리턴(그룹을 지어 그룹별로 함수 실행 결과를 반환)

>> select 절에 단일행 함수와 그룹 함수를 함께 사용하지 못한다.
왜? 결과 행의 갯수가 서로 다르기 때문에

>> 함수식을 사용할 수 있는 위치 : select절 where절 order by절 group by절 having절
*/

/*
<문자 처리 함수>
*length(컬럼 | '문자열') : 해당 문자열의 글자 수를 반환
*lengthb(컬럼 | '문자열') : 해당 문자열의 바이트 수를 반환

'최' '나' 'ㄱ' 한글은 글자당 3 byte
영문자, 숫자, 특수문자 글자당 1 byte
*/

select length ('오라클'), lengthb ('오라클') from dual;
select length ('oracle'), lengthb ('oracle') from dual;
select emp_name, length(emp_name), lengthb(emp_name), email, length(email), lengthb(email) from employee;

/*
*instr
문자열로부터 특정 문자의 시작 위치를 찾아서 반환

instr(컬럼 | '문자열', '찾고자하는 문자', ['찾을 위치의 시작값', 순번]) -> 결과는 number로 나옴
*/

select instr ('aabaacaabbaa', 'b') from dual;
--찾을 위치의 시작값 : 1, 순번 : 1 => 기본값
select instr ('aabaacaabbaa', 'b', 1) from dual;
select instr ('aabaacaabbaa', 'b', -1) from dual; -- 뒤에서부터 찾지만 읽을 때는 원래대로 읽어준다.
select instr ('aabaacaabbaa', 'b', 1, 2) from dual;
select instr ('aabaacaabbaa', 'b', 1, 3) from dual;

select email, instr(email, '_') as "location", instr(email, '@') as "@위치" from employee;

/*
*substr  자주 사용
문자열에서 특정 문자열을 추출해서 반환

[표현법]
substr(컬럼 | '문자열', 추출할 시작 위치, 추출문자 개수)
*/

select substr('showmethemoney', 7) from dual; --7번째 위치부터 끝까지
select substr('showmethemoney', 5, 2) from dual;
select substr('showmethemoney', 1, 6) from dual;
select substr('showmethemoney', -8, 3) from dual;

select emp_name, emp_no, substr(emp_no, 8, 1) as "성별" from employee;

-- 사원들 중 여자 사원들만 emp_name, emp_no를 조회
select emp_name, emp_no from employee where substr(emp_no, 8, 1) = '2' or substr(emp_no, 8, 1) = '4';
-- 사원들 중 남자 사원들만 emp_name, emp_no를 조회
select emp_name, emp_no from employee where substr(emp_no, 8, 1) = '1' or substr(emp_no, 8, 1) = '3';

-- 함수 중첩 사용 가능
-- 이메일 아이디 부분만 추출
-- 사원 목록에서 사원명, 이메일, 아이디 조회
select emp_name, email, substr(email, 1, instr(email, '@') - 1) from employee;

/*
*lpad / rpad
문자열을 조회할 때 통일감 있게 조회하고자 할 때 사용

[표현법]
lpad / rpad (string, 최종적으로 반환할 문자열의 길이, [덧붙이고자 하는 문자])
*/

-- 30만큼의 길이 중 email 컬럼값은 오른쪽으로 정렬하고 나머지 부분은 공백으로 채운다.
select emp_name, lpad(email,30) from employee;
select emp_name, lpad(email,30, '#') from employee;
select emp_name, rpad(email,30, '#') from employee;

-- 사원들의 사원명, 주민등록번호("123456-1xxxxxx")
--select emp_name, rpad(substr(emp_no, 1, 8), 14, 'x') from employee;
select emp_name, substr(emp_no, 1, 8) || 'xxxxxx' from employee;

/*
*ltrim / rtrim
문자열에서 특정 문자를 제거한 나머지를 반환

[표현법]
ltrim / rtrim(컬럼 | '문자열', [제거하고자 하는 문자들])

문자열의 왼쪽 혹은 오른쪽에서 제거하고자 하는 문자들을 찾아서 제거한 나머지 문자열 반환
*/
select ltrim('     K   H  ') from dual; -- 앞에서부터 다른 문자가 나올 때까지 공백 제거
select ltrim('123123KH123', '123') from dual;
select ltrim('ACABACCAKH', 'ABC') from dual; -- 제거하고자 하는 문자는 문자열이 아닌 문자들

/*
*trim
문자열의 앞/뒤/양쪽에 있는 저장한 문자들을 제거한 나머지 문자열 반환

[표현법]
trim([leading | trailing | both] 제거하고자 하는 문자열 from 문자열)
*/
select trim('     K   H   ') from dual;
select trim(both 'z' from 'zzzzzzzzzzzkhzzzzzzzz') from dual; -- 양쪽
select trim(leading 'z' from 'zzzzzzzzzzzkhzzzzzzzz') from dual; -- ltrim 유사
select trim(trailing 'z' from 'zzzzzzzzzzzkhzzzzzzzz') from dual; -- rtrim 유사

/*
*lower / upper / initcap
lower : 다 소문자로 변경한 문자열 반환
uppper : 다 대문자로 변경한 문자열 반환
initcap : 띄어쓰기 기준 첫글자마다 대문자로 변경한 문자열 반환
*/
select lower('Welcome To My KH') from dual;
select upper('Welcome To My KH') from dual;
select initcap('Welcome To My KH') from dual;

/*
*concat
문자열 두개 전달받아서 하나로 합친 후 반환

[표현법]
concat(string1, string2)
*/
select concat('가나다', 'ABC') from dual;
select '가나다' || 'ABC' from dual;

/*
*replace
특정문자열에서 특정부분을 다른 부분으로 교체
[표현법]
replace(문자열, 찾을 문자열, 변경할 문자열)
*/
select email, replace(email, 'C##SERVER', 'kh') from employee;

/*
<숫자 처리 함수>
*abs
숫자의 절대값을 구하는 함수
*/
select abs(-10), abs(-5,4) from dual;

/*
*mod
두 수를 나눈 나머지 값을 반환

[표현법]
mod(number, number)
*/
select mod(10, 3) from dual;
select mod(10.9, 3) from dual;

/*
*round
반올림한 결과를 반환

[표현법]
round(number, [위치])
*/
select round(123.656) from dual; -- 기본자수는 소수점 첫번째 자리에서 반올림
select round(123.656, 1) from dual; -- 양수로 증가할수록 소수점 뒤로 한칸씩 이동
select round(123.656, -1) from dual; -- 음수로 감소할수록 소수점 앞자리로 이동

/*
*ceil
올림처리를 위한 함수

[표현법]
ceil(number)
*/
select ceil(123.456) from dual;

/*
trunnc, floor
버림처리 함수

[표현법]
trunc (number, [위치])
floor (number)
*/
select trunc(123.952) from dual;
select trunc(123.952, 1) from dual;
select trunc(123.952, -1) from dual;

/*
<날짜 처리 함수>
*/
-- *sysdate : 시스템의 현재 날짜 및 시간을 반환
select sysdate from dual;

-- *months_between : 두 날짜 사이의 개월 수
-- 사원들의 사원명, 입사일, 근무일수, 근무개월 수 조회
select emp_name, hire_date, floor(sysdate - hire_date), floor(months_between(sysdate, hire_date)) from employee;

-- *add_months : 특정 날짜에 number 개월수를 더해서 반환
select add_months(sysdate, 7) from dual;

-- 사원테이블에서 사원명, 입사일, 수습기간종료일을 조회
select emp_name, hire_date, add_months(hire_date,3) from employee;

-- *next_day(date, 요일(문자 | 숫자)) : 특정 날짜 이후 가장 가까운 요일의 날짜를 반환
select next_day(sysdate, '토요일') from dual;
select next_day(sysdate, '토') from dual;
select next_day(sysdate, 7) from dual; -- 1 : 일 ~ 7 : 토

alter session set nls_language = american;
select next_day(sysdate, 'saturday') from dual;
alter session set nls_language = korea;

-- *last_day(date) : 해당 월의 마지막 날짜 구해서 반환
select last_day(sysdate) from dual;

/*
*extract : 특정 날짜로부터 년|월|일 값을 추출해서 반환하는 함수

[표현법]
extract(year from date) : 연도만 추출
extract(month from date) : 월만 추출
extract(day from date) : 일만 추출
*/
-- 사원의 사원명, 입사년도, 입사월, 입사일 조회
select emp_name, extract(year from hire_date) as "입사년도",
extract(month from hire_date) as "입사월", extract(day from hire_date) as "입사일" from employee;

----------------------------------------------------------------
/*
[형변환 함수]
*to_char : 숫자타입 또는 날짜타입의 값을 문자타입으로 변환시켜주는 함수

[표현법]
to_char(숫자 | 문자, [포멧])
*/
-- 숫자 -> 문자
select to_char(1234) from dual;
select to_char(12, '99999') from dual; -- 9의 자리수만큼 공간 확보
select to_char(12, '00000') from dual; -- 0의 자리수만큼 공간 확보, 빈칸을 0으로 채움
select to_char(2000000, 'l9999999') from dual; -- 현재 설정된 나라의 로컬 화폐 단위 나타냄
select to_char(2000000, '9,999,999') from dual;

-- 날짜타입 -> 문자타입
select sysdate from dual;
select to_char(sysdate) from dual;
select to_char(sysdate, 'pm hh:mi:ss') from dual; -- am, pm 어떤 것을 사용하건 형식을 정해주는 것
select to_char(sysdate, 'yyyy--mm--dd day dy') from dual;
select to_char(sysdate, 'yyyy--mm--dd dy pm hh:mi:ss') from dual;
select to_char(sysdate, 'yyyy"년" mm"월" dd"일" day pm hh"시"mi"분"ss"초"') from dual;

-- 사원들의 이름, 입사날짜(0000년 00월 00일) 조회
select emp_name, to_char(hire_date, 'yyyy"년" mm"월" dd"일"') from employee;

-- 년도와 관련된 포맷
select to_char(sysdate, 'yyyy'), 
to_char(sysdate, 'yy'), 
to_char(sysdate, 'rrrr'), -- rr룰이 따로 존재한다.

to_char(sysdate, 'year') from dual;
select to_char(to_date('24', 'rrrr'), 'yyyy'), -- 현재 세기(2000)으로 해석
to_char(to_date('60', 'rrrr'), 'yyyy') -- 50년 이상이므로 과거로 해석
from dual;

-- 월과 관련된 포맷 
select to_char(sysdate, 'mm'), to_char(sysdate, 'mon'), to_char(sysdate, 'month') from dual;

-- 일과 관련된 포맷
select to_char(sysdate, 'ddd'), -- 오늘이 이번년도에 몇번째 일수
to_char(sysdate, 'dd'), -- 오늘일자
to_char(stsdate, 'd') -- 요일
from dual;

-- 요일을 나타내는 포맷
select to_char(sysdate, 'day'), to_char(sysdate, 'dy') from dual;  

---------------------------------------------------------------------
/*
to_date : 숫자타입 또는 문자타입을 날짜 타입으로 변경하는 함수

[표현법]
to_date(숫자 | 문자, [포맷]) -> date
*/
select to_date(20100101) from dual;
select to_date(610101) from dual; -- 기본적으로 연도를 2자리만 입력시 rr룰이 적용된다.
select to_date(051010) from dual; -- 0으로 시작하는 숫자는 없다.
select to_date('051010') from dual;
select to_date('020505 120500') from dual; -- 포맷을 정해주게 되어있음
select to_date('020505 230500', 'yymmdd hh24miss') from dual;

---------------------------------------------------------------------
/*
to_number : 문자타입의 데이터를 숫자타입으로 변환시켜주는 함수

[표현법]
to_number(문자, [포맷])
*/
select to_number('065431642511') from dual;
select '1000' + '3000' from dual; -- 문자열 -> 숫자로 자동 형변환이 된다.
select '100,000' + '30,000' from dual;
select to_number('100,000', '999,999') + to_number('30,000', '999,999') from dual;

/*
[null처리 함수]
*/
-- *nvl(컬럼, 해당 컬럼이 null일 경우 보여줄 값)
select emp_name, nvl(bonus, 0) from employee;

-- 전 사원의 이름, 보너스 포함 연봉 조회
select emp_name, (salary + (salary * nvl(bonus, 0))) * 12 from employee;

-- *nvl2(컬럼, 반환값1, 반환값2)
-- 반환값1 : 해당 컬럼이 존재하면 보여줄 값
-- 반환값2 : 해당 컬럼이 존재하지 않으면 보여줄 값
select emp_name, bonus, nvl2(bonus, 'o', 'x') from employee;

-- *nullif(비교대상1, 비교대상2)
-- 두 값이 일치하면 null, 일치하지 않으면 비교대상 1 반환
select nullif('123', '123') from dual;
select nullif('123', '456') from dual;

----------------------------------------------------------------------------
/*
[선택함수]
*decode(비교하고자 하는 대상, 비교값1, 결과값1, 비교값2, 결과값2 ...)
*/
-- 사번, 사원명, 주민등록번호, 성별
select emp_id, emp_name, emp_no, 
decode(substr(emp_no, 8, 1), '1', '남', '2', '여', '3', '남', '4', '여') as "성별" from employee;

-- 직원의 성명, 기존급여, 인상된 급여 조회(각 직급별로 차등 인상)
-- J7 직급은 급여를 10% 인상(salary * 1.1), J6 직급은 급여를 15% 인상(salary * 1.15)
-- J5 직급은 급여를 20% 인상(salary * 1.2), 그 외 직급은 급여를 5% 인상(salary * 1.05)
select emp_name, salary as "인상 전", 
decode(job_code, 'J7', salary * 1.1, 'J6', salary * 1.15, 'J5', salary * 1.2, salary * 1.05) as "인상 후" 
from employee;

/*
*case when then

[표현법]
case when 조건식1 then 결과값1 when 조건식2 then 결과값2 ... 결과값 end
*/
select emp_name, salary, 
case when salary >= 5000000 then '고급' when salary >= 3000000 then '중급' else '초급' end from employee;

-------------------------------------------------------------------------------------
-- 1. sum(숫자타임컬럼) : 해당 컬럼값들의 총 합계를 구해서 반환
-- 직원들의 모든 급여의 합을 구해라
select sum(salary) from employee;
-- 남자 사원들의 총 급여
select sum(salary) from employee where substr(emp_no, 8, 1) in ('1' , '3');
-- 부서코드가 D5인 부서 사람들의 총 연봉(급여 * 12)
select sum(salary * 12) from employee where dept_code = 'D5';

-- 2. avg(number) : 해당 컬럼값들의 평균을 구해서 반환
select avg(salary) from employee;

-- 3. min(모든 타입 가능) : 해당 컬럼값 중 가장 작은 값을 구해서 반환
select min(emp_name), min(salary), min(hire_date) from employee;

-- 4. max(모든 타입 가능) : 해당 컬럼값 중 가장 큰 값을 구해서 반환
select max(emp_name), max(salary), max(hire_date) from employee;

-- 5. count(* | 컬럼 | distinct 컬럼) : 해당 조건에 맞는 행의 개수를 세서 반환
-- count(*) : 조회된 결과에 모든 행의 개수를 세서 반환
-- count(컬럼) : 제시한 해당 컬럼값이 null이 아닌 것만 행의 수를 세서 반환
-- count(distinct 컬럼) : 해당 컬럼값 중복을 제거한 후 행의 개수를 세서 반환

-- 전체 사원 수
select count(*) from employee;
select count(*) from employee where substr(emp_no, 8, 1) in ('2', '4');

-- 보너스를 받는 사원의 수
select count(bonus) from employee;
select count(*) from employee where bonus is not null;

-- 현재 사원들이 총 몇 개의 부서에 분포되어 있는지를 구해라
select count(distinct dept_code) from employee;
