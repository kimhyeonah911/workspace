/*
<시퀀스 SEQUENCE>
자동으로 숫자를 발생시켜주는 역할을 하는 객체
정수값을 순차적으로 일정하게 증가시키면서 생성해준다.

ex) 회원번호, 사원번호, 게시글번호 ...
*/
/*
1. 시퀀스 객체 생성

[표현법]
create sequence 시퀀스명 
[start with 시작숫자] -> 처음 발생시킬 시작값 지정(기본값1) 
[increment by 숫자] -> 몇씩 증가시킬 건지(기본값1)
[maxvalue 숫자] -> 최대값 지정(기본값 매우 크다)
[minvalue 숫자] -> 최소값 지정(기본값 1)
[cycle | nocycle] -> 값 순환 여부(기본값 nocycle)
[cache | nocache] -> 캐시메모리 할당(기본값 chche 20)

* 캐시메모리 : 미리 발생될 값들을 생성해서 저장해두는 공간 
매번 호출될 때마다 새로운 번호를 생성하는 게 아니라 캐시메모리공간에 미리 생성된 값들을 가져다 쓸 수 있다. -> 속도가 빨라진다.

테이블명 : tb_
뷰명 : vw_
시퀀스 : seq_
트리거 : trg_
*/
create sequence seq_test;

-- [참고] 현재 계정이 소유한 시퀀스들의 구조를 보고 싶을 때
select * from user_sequences;

create sequence seq_empno start with 200 increment by 5 maxvalue 310 nocycle nocache;

/*
2. 시퀀스 사용
시퀀스명.currval : 현재 시퀀스 값(마지막으로 성공한 시퀀스명.nextval값)
시퀀스명.nextval : 시퀀스 값에 일정한 값을 증가시켜 발생한 값 
                  현재 시퀀스 값에 increment by 값 만큼 증가한 값
*/
select * from user_sequences;

select seq_empno.currval from dual;
--> nextval를 한 번도 수행하지 않은 이상 currval를 할 수 없음
--> 왜? currval는 마지막으로 성공한 nextval의 값을 저장해서 보여주는 임시값

select seq_empno.nextval from dual; -- 300
select seq_empno.currval from dual; -- 300
select seq_empno.nextval from dual; -- 305
select seq_empno.nextval from dual; -- 310
select seq_empno.nextval from dual; -- 315 에러 발생 -> 최대값이 310

/*
3. 시퀀스 구조 변경
alter sequence 시퀀스 
[increment by 숫자] 
[maxvalue 숫자] 
[minvalue 숫자] 
[cycle | nocycle] 
[cache 바이트수| nocache]

* start with는 변경불가
*/
alter sequence seq_empno increment by 10 maxvalue 400;

select * from user_sequences;

select seq_empno.nextval from dual;

-- 4.시퀀스 삭제
drop sequence seq_empno;

-------------------------------------------------------------------------
-- 사원 번호로 사용할 시퀀스
create sequence seq_eid start with 400;

insert into employee (emp_id, emp_name, emp_no, job_code, hire_date) 
values (seq_eid.nextval, '김말똥', '111111-1111111', 'J6', sysdate);

select * from employee;

insert into employee (emp_id, emp_name, emp_no, job_code, hire_date) 
values (seq_eid.nextval, '김말숙', '111111-2111111', 'J6', sysdate);