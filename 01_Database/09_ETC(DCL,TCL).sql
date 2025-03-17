/*
<DCL : 데이터 제어문>
계정에게 시스템 권한 또는 객체 접근 권한을 부여하거나 회수하는 구문

> 시스템 권한 : DB에 접근하는 권한, 객체를 생성할 수 있는 권한
> 객체 접근 권한 : 특정 객체를 조작할 수 있는 권한

create user 계정명 identyfied by 비밀번호;
grant 권한(resource, connect) to 계정;

revoke 권한 to 계정;
*/
select * from role_sys_privs;

/*
<TCL : 트랜잭션 제어문>
    
* 트랜잭션
- 데이터베이스의 논리적 연산단위
- 데이터의 변경사항(dml)등을 하나의 트랜잭션에 묶어서 처리한다.
dml문 한개를 수행할 때 트랜잭션이 존재하지 않는다면 트랜잭션을 만들어서 묶음
                              존재한다면 헤딩 트랜잭션에 묶어서 처리
commit하기 전까지 변경사항들을 하나의 트랜잭션에 담는다.
- 트랜잭션에 대상이 되는 sql : insert, update, delete
    
commit(트랜잭션 종료 처리 후 확정)
rollback(트랜잭션 취소)
savepoint(임시저장)

commit : 한 트랜잭션에 담겨있는 변경사항들을 실제 DB에 반영하겠다.
rollback : 한 트랜잭션에 담겨있는 변겨사항들을 삭제(취소)한 후 마지막 COMMIT시점으로 돌아감.
savepoint 포인트명 : 현 시점에 해당 포인트명으로 임시저장을 해두겠다.
*/
-- 오토커밋여부 확인
show autocommit; 

--autocommit 수동설정 / on -> 자동(dml문 실행마다 무조건 commit)
set autocommit off; 

drop table emp_01;

create table emp_01 as (select emp_id, emp_name, dept_title from employee join department on (dept_code = dept_id));   
select * from emp_01;

--사번이 200, 201번인 사람 삭제
delete from emp_01 where emp_id in (200, 201);
rollback;
select * from emp_01;

-- 사번이 200, 201번인 사람 삭제
delete from emp_01 where emp_id in (200, 201);
commit;
rollback;
select * from emp_01;

-- 사번이 217, 216번, 214인 사람 삭제
delete from emp_01 where emp_id in (214, 216, 217);
select * from emp_01;
savepoint sp;

insert into emp_01 values(801, '김말똥', '기술지원부');
insert into emp_01 values(802, '김말순', '창업지원부');
rollback to sp; 
commit;
select * from emp_01;

insert into emp_01 values(801, '김말똥', '기술지원부');
insert into emp_01 values(802, '김말순', '창업지원부');

create table test(tid number);

rollback;
select * from emp_01;


-- DDL문(CREATE, ALTER, DROP)을 수행하는 순간 기존 트랜잭션은 무조건 COMMIT됨(실제 DB에 반영됨)
-- 즉, DDL문 수행전 변경사항들이 있다면 정확하게 픽스하고 해라!
/*
ACID속성 : 트랜잭션의 일관성과 실뢰성을 보장하기위해 지켜야하는 네가지 핵심 속성
원자성 : 트랜잭션 내의 작업이 모두 완료(COMMIT)되거나, 모두 취소(ROLLBACK)되어야 함
일관성 : 트랜잭션이 실행되기 전과 후에 데이터베이스의 상태가 항상 일관성을 유지해야 함
독립성 : 동시에 실행되는 트랜잭션이 서로에게 영향을 미치지 않도록 독립적으로 처리되어야 함
지속성 : 트랜잭션이 성공적으로 완료된 후에는 시스템 장애가 발생하더라도 영구적으로 저장되어야 함
*/

