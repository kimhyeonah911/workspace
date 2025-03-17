/*
<pl/sql>

오라클 자체에 내장되어 있는 절차적 언어
sql문장 내에서 변수의 정의, 조건(if), 반복(for, while)등을 지원하여 sql 단점을 보완
다수의 sql문을 한 번에 실행 가능

pl/sql 구조(블록구조)
- [선언부] : declare로 시작, 변수나 상수를 선언 및 초기화하는 부분
- 실행부 : begin으로 시작, sql문 또는 제어문 등의 로직을 기술하는 부분
- [예외처리부] : excrption으로 시작, 예외 발생 시 해결하기 위한 구문
*/
set serveroutput on;

begin dbms_output.put_line('hello oracle');
end;
/

----------------------------------------------------------------------------
/*
1. declare 선언부
변수 및 상수 선언하는 공간
일반타입변수, 래퍼런스타입변수, row타입변수

1_1) 일반타입 변수 선언 및 초기화
[표현식] 변수명 [constant] 자료형 [:=값]
*/
declare 
eid number;
ename varchar2(20);
pi constrant number := 3.14;
begin
eid := 800;
ename := '최지원';

dbms_output.put_line('eid : ' || eid);
dbms_output.put_line('ename : ' || ename);
dbms_output.put_line('pi : ' || pi);
end;
/

declare 
eid number;
ename varchar2(20);
pi constrant number := 3.14;
begin
eid := &번호;
ename := '&이름';

dbms_output.put_line('eid : ' || eid);
dbms_output.put_line('ename : ' || ename);
dbms_output.put_line('pi : ' || pi);
end;
/

---------------------------------------------------------------------------
-- 1_2) 래퍼런스 타입 변수 선언 및 초기화(어떤 테이블의 어떤 컬럼의 데이터타입을 참조해서 그 타입으로 지정
declare 
eid employee.emp_id%type;
ename employee.emp_name%type;
sal employee.salary%type;
begin
-- eid := 800;
-- ename := '최지원';
-- sal := 1000000;
select emp_id, emp_name, salary into eid, ename, sal from employee where emp_id = &사번;

dbms_output.put_line('eid : ' || eid);
dbms_output.put_line('ename : ' || ename);
dbms_output.put_line('sal : ' || sal);
end;
/

-----------------------------------실습----------------------------------------
-- 래퍼런스타입 변수로 eid, ename, jcode, sal, detitle을 선언하고
-- 각 자료형 employee(emp_id, emp_name, job_code, salary), department(dept_title)등을 참조하도록
-- 사용자가 입력한 사번의 사번, 사원명, 직급코드, 급여, 부서명 조회 후 각 변수에 담아서 출력
declare 
eid employee.emp_id%type;
ename employee.emp_name%type;
jcode employee.job_code%type;
sal employee.salary%type;
detitle department.dept_title%type;
begin
select emp_id, emp_name, job_code, salary, dept_title into eid, ename, jcode, sal, detitle
from employee join department on (dept_id = dept_code) where emp_id = &사번;

dbms_output.put_line('eid : ' || eid);
dbms_output.put_line('ename : ' || ename);
dbms_output.put_line('jcode : ' || jcode);
dbms_output.put_line('sal : ' || sal);
dbms_output.put_line('detitle : ' || detitle);
end;
/

-----------------------------------------------------------------------------
-- 1_3) row타입 변수 선언
-- 테이블의 한 행에 대한 모든 컬럼값을 한 번에 담을 수 있는 변수
-- [표현식] 변수명 테이블명 %rowtype
declare e employee%rowtype;
begin select * into e from employee where emp_id = &사번;

dbms_output.put_line('사원명 : ' || e.emp_name);
dbms_output.put_line('급여 : ' || e.salary);
dbms_output.put_line('보너스 : ' || e.bonus);
end;
/

--------------------------------------------------------------------------
-- 2) begin 실행부
-- <조건문>
-- 1) if 조건식 then 실행내용 end if; (if문 단독으로 사용할 때

-- 입력받은 사번에 해당하는 사원의 사번, 사원명, 직급코드, 급여, 보너스 조회
declare 
eid employee.emp_id%type;
ename employee.emp_name%type;
jcode employee.job_code%type;
sal employee.salary%type;
bonus department.bonus%type;
begin
select emp_id, emp_name, job_code, salary, nvl(bonus, 0) into eid, ename, jcode, sal, bonus
from employee where emp_id = &사번;

dbms_output.put_line('사번 : ' || eid);
dbms_output.put_line('이름 : ' || ename);
dbms_output.put_line('급여 : ' || sal);

if bonus = 0 then dbms_output.put_line('보너스를 지급받지 않는 사원입니다.');
end if;

dbms_output.put_line('보너스 : ' || bonus);
end;
/

-- 2)if 조건식 then 실행내용 else 실행내용 end if; (if-else)
-- declare
-- 래퍼런스 변수(eid, ename, dtitle, ncode) 참조(emp_id, emp_name, dept_title, national_code)
-- 일반타입 변수 (team 문자열) - 국내팀, 해외팀 분리해서 저장
-- begin
-- 사용자가 입력한 사번의 사원 정보를 가져와 사번, 이름, 부서명, 근무국가코드 조회 후 각 변수에 대입
-- ncode값이 ko일 경우 -> team -> 국내팀 ko가 아닐 경우 -> team -> 해외팀 대입
-- 사번, 이름, 부서명, 소속(team)을 출력
declare 
eid employee.emp_id%type;
ename employee.emp_name%type;
dtitle department.dept_title%type;
ncode location.national_code%type;
team varchar2(10);
begin
select emp_id, emp_name, dept_title, national_code into eid, ename, dtitle, ncode
from employee join department on (dept_code = dept_id) join location on (location_id = local_code) where emp_id = &사번;

dbms_output.put_line('사번 : ' || eid);
dbms_output.put_line('이름 : ' || ename);
dbms_output.put_line('부서명 : ' || dtitle);
dbms_output.put_line('국가근무코드 : ' || ncode);

if ncode = 'KO' then team := '국내팀';
else team := '해외팀';
end if;

dbms_output.put_line('사번 : ' || eid);
dbms_output.put_line('이름 : ' || ename);
dbms_output.put_line('부서명 : ' || dtitle);
dbms_output.put_line('소속 : ' || team);
end;
/

-- 3) if 조건식1 then 실행내용1 eslif 조건식2 then 실행내용2 ... (else 실행내용) end if;
declare 
score number;
grade  varchar2(1);
begin
score := &점수;

if score >= 90  then grade := 'A';
elsif score >= 80 then grade := 'B';
elsif score >= 70 then grade := 'C';
elsif score >= 60 then grade := 'D';
else grade := 'F';
end if;

dbms_output.put_line('당신의 점수는 ' || score || '점이며, 학점은 ' || grade || '학점입니다.');
end;
/

---------------------------------------------------------------------------
/*
<반복문>
1) basic loop문

[표현식]
loop 반복적으로 실행할 구문 *반복문을 빠져나갈 수 있는 구문 end loop;

*반복문을 삐져나갈 수 있는 구문
1) if 조건식 then exit; end if;
2) exit when 조건식;
*/
declare 
i number := 0;
begin
loop
-- if i = 10 then exit; end if;

dbms_output.put_line(i);
i := i+1;

exit when i = 10;
end loop;
end;
/

----------------------------------------------------------------------------
/*
2) for loop문(횟수가 정해진 반복)

[표현식]
for 변수 in [reverse] 초기값 .. 최종값 loop 반복적으로 실행할 문장 end loop;
*/
begin for i in reverse 1 .. 10
loop 
dbms_output.put_line(i);
end loop;
end;
/

drop table test;

create table test(
tno number primary key,
tdate date);

create sequence seq_tno;

begin
for i in 1 .. 100
loop insert into test value(seq_tno.nextval, sysdate);
end loop;
end;
/

---------------------------------------------------------------------------
/*
whele loop문

[표현식]
while 반복문이 수행될 조건 loop 반복할 명령어 end loop;
*/
declare 
i number := 0;
begin
while i < 10
loop
dbms_output.put_line(i);
i := i + 1;
end loop;
end;
/

--------------------------------------------------------------------------
/*
3. 예외처리부
예외(exception) : 실행 중 발생하는 오류

exception when 예외명1 then 처리구문1; 
          when 예외명2 then 처리구문2; ...
    
* 시스템 예외(오라클에서 미리 정해둔 예외)
- no_date_found : select한 결과가 한 행도 없을 때
- too_many_rows : select한 결과가 여러 행일 때
- zero_divide : 0으로 나눌 때
- dup_val_on_index : unique 제약 조건 위배
...
*/
-- 사용자가 입력한 수로 나눗셈한 결과를 출력
declare result number;
begin result := 10/&숫자;
dbms_output.put_line('결과 : ' || result);
exception
-- when zero_divide then dbms_output.put_line('나누기 연산 시 0으로 나눌 수 없습니다.');
when others then dbms_output.put_line('예외가 발생하였습니다.');
end;
/

alter table employee add primary key(emp_id);

begin update employee
set emp_id = '&변경할사번'
where emp_name = '노옹철'
exception
when dup_val_on_index then dbms_output.put_line('이미 존재하는 사원입니다.');
end;
/