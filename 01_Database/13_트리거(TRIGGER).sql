/*
<트리거>
내가 지정한 테이블에 insert, update, delete 등 dml문에 의한 변경사항이 생길 때 자동으로 매번 실행할 내용을 미리 정의해둘 수 있다.

ex) 회원 탈퇴 시 기존의 회원 테이블에 데이터 delete 한 후 곧바로 탈퇴한 회원들만 따로 보관하는 테이블에 자동으로 insert 시켜야 한다.
신고횟수가 일정수를 넘었을 때 묵시적으로 해당 회원을 블랙리스트로 처리하게끔
입출고에 대한 데이터 기록(insert) 될 때마다 해당 상품에 대한 재고수량을 매번 수정(update) 해야 한다.

* 트리거의 종류
- sql문의 실행 시기에 따른 분류
- before triger : 내가 지정한 테이블에 이벤트가 발생되기 전에 트리거 실행
- after triger : 내가 지정한 테이블에 이벤트가 발생된 후에 트리거 실행

- sql문에 의해 영향을 받는 각 행에 따른 분류
- 문장 트리거 : 이벤트가 발생한 sql문에 대해 딱 한 번만 트리거 실행
- 행 트리거 : 해당 sql문을 실행할 때마다 매번 트리거 실행(for each row 옵션 기술)
> :old - before update(수정 전 자료), before delete(삭제 전 자료)
> :new - after insert(추가된 자료), after update(수정 후 자료)

[표현식]
create [or replace] triger 트리거명 before | after 
insert | update | delete on 테이블명
[declare 변수 선언]
begin 실행할 내용
[exception 예외처리]
end;
*/
-- employee 테이블에 새로운 행이 insert될 때마다 자동으로 출력되는 트리거 정의
set serveroutput on;

create or replace trigger trg_01 after insert on employee 
begin
dbms_output.put_line('신입사원님 안녕하세요.');
end;
/

insert into employee(emp_id, emp_name, emp_no, dept_code, job_code, hire_date)
values(seq_eid.nextval, '김지원', '111111-2222222', 'D7', 'J7', sysdate);

------------------------------------------------------------------------
-- 상품 입고 및 출고 관련해서 트리거를 사용해보자
-- 필요한 테이블, 시퀀스 생성

-- 1. 상품에 대한 데이터를 보관할 테이블(tb_product)
create table tb_product(
pcode number primary key, -- 상품 번호
pname varchar2(30) not null, -- 상품명
brand varchar2(30) not null, -- 브랜드
price number,          -- 가격
stock number default 0 -- 재고
);

create sequence seq_pcode start with 200 increment by 5;

insert into tb_product values(seq_pcode.nextval, '갤럭시s25', '삼성', 1500000, default);
insert into tb_product values(seq_pcode.nextval, '아이폰16', '애플', 1400000, 10);
insert into tb_product values(seq_pcode.nextval, '대륙폰', '샤오미', 800000, 20);

commit;

-- 2. 상품 입출고 상세 이력 테이블 생성(tb_prodetail)
-- 어떤 상품이 어떤 날짜에 몇 개가 입고 또는 출고가 되는지 데이터를 기록
create table tb_prodetail(
decode number primary key, -- 이력 번호
pcode number references tb_product, -- 상품 번호
pdate date not null, -- 입출고 날짜
amount number not null, -- 입출고 수량
status char(6) check(status in ('입고', '출고'))
);

create sequence seq_decode;

-- 200번 상품이 오늘 날짜로 10개 입고
insert into tb_prodetail values(seq_Decode.nextval, 200, sysdate, 10, '입고');

-- 200번 상품의 재고 수량을 10 증가
update tb_product set stock = stock + 10 where pcode = 200;

-- 205번 상품이 오늘 날짜로 20개 입고
insert into tb_prodetail values(seq_Decode.nextval, 205, sysdate, 20, '입고');

-- 205번 상품의 재고 수량을 20 증가
update tb_product set stock = stock + 20 where pcode = 205;

-- tb_prodetail 테이블에 insert 이벤트 발생 시 tb_product 테이블에 매번 자동으로 재고 수량을 update하게 트리거 생성
/*
-- 상품이 입고 -> 해당 상품 찾아서 재고 수량 증가 update
update tb_product set stock = stock + 입고된 수량(insert된 자료의 amount) where pcode = 입고된 상품번호 (insert된 자료의 decode);

-- 상품이 출고 -> 해당 상품 찾아서 재고 수량 감소 update
update tb_product set stock = stock - 입고된 수량(insert된 자료의 amount) where pcode = 출고된 상품번호 (insert된 자료의 decode);
*/
create or replace trigger trg_01 after insert on tb_prodetail for each row
begin
if(:new.status = '입고')
then update tb_product
set stock = stock + :new.amount
where pcode = :new.decode;
else
update tb_product
set stock = stock - :new.amount
where pcode = :new.decode;
end if;
end;
/

-- 210번 상품이 오늘 날짜로 7개 입고
insert into tb_prodetail values(seq_decode.nextval, 210, sysdate, 7, '입고');

-- 205번 상품이 오늘 날짜로 5개 출고
insert into tb_prodetail values(seq_decode.nextval, 205, sysdate, 5, '출고');
