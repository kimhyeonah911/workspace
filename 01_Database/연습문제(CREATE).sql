--실습문제 --
--도서관리 프로그램을 만들기 위한 테이블들 만들기--
--이때, 제약조건에 이름을 부여할 것
--   각 컬럼에 주석달기

DROP TABLE TB_MEMBER;
DROP TABLE TB_BOOK;
DROP TABLE TB_PUBLISHER;
DROP TABLE TB_RENT;

/*
    1. 출판사들에 대한 데이터를 담기위한 출판사 테이블(TB_PUBLISHER)
    컬럼 : PUB_NO(출판사 번호) - 기본키(PUBLISHER_PK)
          PUB_NAME(출판사명) -- NOT NULL(PUBLISHER_NN)
          PHONE(출판사전화번호) -- 제약조건없음
*/
create table tb_publisher(
pub_no number constraint publisher_pk primary key,
pub_name varchar2(30) constraint publisher_nn not null,
phone varchar2(13) 
);

comment on column tb_publisher.pub_no is '출판사번호';
comment on column tb_publisher.pub_name is '출판사명';
comment on column tb_publisher.phone is '출판사전화번호';


--샘플 3개정도 생성
insert into tb_publisher values (1, 'kh', '010-1111-1111');
insert into tb_publisher values (2, '길벗', '010-1111-2222');
insert into tb_publisher values (3, '시나공', '010-1111-3333');


/*
    2. 도서들에 대한 데이터를 담기위한 도서 테이블(TB_BOOK)
    컬럼 : BK_NO(도서번호)--기본키(BOOK_PK)
          BK_TITLE(도서명)--NOT NULL(BOOK__NN_TITLE)
          BK_AUTHOR(저자명)--NOT NULL(BOOK__NN_AUTHOR)
          BK_PRICE(가격)-- 제약조건없음
          BK_PUB_NO(출판사 번호)--외래키(BOOK_FK)(TB_PUBLISHER테이블을 참조)
                                이때 참조하고 있는 부모데이터 삭제시 자식데이터도 삭제 되도록 옵션지정
                                
*/
create table tb_book(
bk_no number constraint book_pk primary key,
bk_title varchar2(30) constraint book_nn_title not null,
bk_author varchar2(30) constraint book_nn_auther not null,
bk_price varchar2(30),
bk_pub_no number constraint book_fk references tb_publisher(pub_no) on delete cascade
);

comment on column tb_book.bk_no is '도서번호';
comment on column tb_book.bk_title is '도서명';
comment on column tb_book.bk_author is '저자명';
comment on column tb_book.bk_price is '가격';
comment on column tb_book.bk_pub_no is '출판사번호';


--5개 정도의 샘플 데이터 추가하기
insert into tb_book values(101, '어린 왕자', '앙투안 드 생텍쥐페리', '11000', '1');
insert into tb_book values(102, '작은 왕자', '앵투안 드 생텍쥐페리', '9900', '2');
insert into tb_book values(103, '다큰 왕자', '랑투안 드 생텍쥐페리', '13500', '3');
insert into tb_book values(104, '그냥 왕자', '란투안 드 생텍쥐페리', '12000', '3');
insert into tb_book values(105, '아는 왕자', '랭투안 드 생텍쥐페리', '8000', '1');




/*
    3. 회원에 대한 데이터를 담기위한 회원 테이블(TB_MEMBER)
    컬럼명 : MEMBER_NO(회원번호) -- 기본키(MEMBER_PK)
            MEMBER_ID(아이디) -- 중복금지(MEMBER_UQ_ID)
            MEMBER_PWD(비밀번호) -- NOT NULL(MEMBER_NN_PWD)
            MEMBER_NAME(회원명) -- NOT NULL(MEMBER_NN_NAME)
            GENDER(성별) -- M또는 F로 입력되도록 제한(MEMBER_CK_GEN)
            ADDRESS(주소) -- 제약조건없음
            PHONE(연락처)-- 제약조건없음
            STATUS(탈퇴여부) -- 기본값을 N으로 지정, 그리고 N또는 Y만 입력되도록 제약조건 설정(MEMBER_CK_STA)
            ENROLL_DATE(가입일) -- 기본값으로 SYSDATE, NOT NULL 제약조건(MEMBER_NN_EN)
*/
create table tb_member(
member_no number constraint member_pk primary key,
member_id varchar2(30) constraint member_uq_id unique,
member_pwd varchar2(30) constraint member_nn_pwd not null,
member_name varchar2(30) constraint member_nn_name not null,
gender char(1) constraint member_ck_gen check (gender in ('M', 'F')),
address varchar2(50),
phone varchar2(13),
status char(1) default 'N' constraint member_ck_sta check (status in ('N', 'Y')),
enroll_date date default sysdate constraint member_nn_en not null
);

comment on column tb_member.member_no is '회원번호';
comment on column tb_member.member_id is '아이디';
comment on column tb_member.member_pwd is '비밀번호';
comment on column tb_member.member_name is '회원명';
comment on column tb_member.gender is '성별';
comment on column tb_member.address is '주소';
comment on column tb_member.phone is '연락처';
comment on column tb_member.status is '탈퇴여부';
comment on column tb_member.enroll_date is '가입일';


--5개 정도의 샘플 데이터 추가하기
insert into tb_member values(1, 'user1', 'pass1', '홍길동', 'M', '서울시 강남구', '010-1111-1111', default, '2023/09/11');
insert into tb_member values(2, 'user2', 'pass2', '홍길순', 'F', '서울시 종로구', '010-1111-2222', default, default);
insert into tb_member values(3, 'user3', 'pass3', '홍길삼', 'M', '서울시 영등포구', '010-1111-3333', 'Y', '2022/03/26');
insert into tb_member values(4, 'user4', 'pass4', '홍길사', 'M', '서울시 용산구', '010-1111-4444', default, default);
insert into tb_member values(5, 'user5', 'pass5', '홍길오', 'F', '서울시 마포구', '010-1111-5555', 'Y', '2024/11/7');

/*
    4.어떤 회원이 어떤 도서를 대여했는지에 대한 대여목록 테이블(TB_RENT)
    컬럼명 : RENT_NO(대여번호)-- 기본키(RENT_PK)
            RENT_MEM_NO(대여회원번호)-- 외래키(RENT_FK_MEM) TB_MEMBER와 참조하도록
                                        이때 부모 데이터 삭제시 자식데이터 값이 NULL이 되도록 지정
            RENT_BOOK_NO(대여도서번호)-- 외래키(RENT_FK_BOOK) TB_BOOK와 참조하도록
                                        이때 부모 데이터 삭제시 자식데이터 값이 NULL이 되도록 지정
            RENT_DATE(대여일) -- 기본값 SYSDATE
*/
create table tb_rent(
rent_no number constraint rent_pk primary key,
rent_mem_no number constraint rent_fk_mem references tb_member(member_no) on delete set null,
rent_book_no number constraint rent_fk_book references tb_book(bk_no) on delete set null,
rent_date date default sysdate
);

comment on column tb_rent.rent_no is '대여번호';
comment on column tb_rent.rent_mem_no is '대여회원번호';
comment on column tb_rent.rent_book_no is '대여도서번호';
comment on column tb_rent.rent_date is '대여일';


--3개 정도의 샘플 데이터 추가하기
insert into tb_rent values(201, '1', '101', default);
insert into tb_rent values(202, '2', '103', '2023/07/31');
insert into tb_rent values(203, '4', '105', default);
