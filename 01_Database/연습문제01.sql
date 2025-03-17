-- 1
select department_name as "학과 명", category as "계열" from tb_department;


-- 2
select department_name || '의 정원은 ' || capacity || '명 입니다.' as "학과별 정원" from tb_department;


-- 3
select student_name from tb_student join tb_department using (department_no) 
where department_name = '국어국문학과' and substr(student_ssn, 8, 1) = '2' and absence_yn = 'Y';


-- 4
select student_name from tb_student where student_no in ('A513079', 'A513090', 'A513091', 'A513110', 'A513119') 
order by student_name desc;


-- 5
select department_name, category from tb_department where capacity between 20 and 30;


-- 6
select professor_name from tb_professor where department_no is null;


-- 7
select student_name from tb_student where department_no is null;


-- 8
select class_no from tb_class where preattending_class_no is not null;


-- 9
select distinct category from tb_department;


-- 10
select student_no, student_name, student_ssn from tb_student 
where entrance_date like '02%' and student_address like '%전주%' and absence_yn = 'N';


-----------------------------------------------------------------------------
-- 함수
-- 1
select student_no as "학번", student_name as "이름", entrance_date as "입학년도" from tb_student 
where department_no = 002 order by entrance_date;


-- 2
select professor_name, professor_ssn from tb_professor where professor_name not like '___';


-- 3
select professor_name as "교수이름", to_number(to_char(sysdate, 'yyyy')) - (1900 + to_number(substr(professor_ssn, 1, 2))) as "교수 나이" 
from tb_professor order by to_number(to_char(sysdate, 'yyyy')) - (1900 + to_number(substr(professor_ssn, 1, 2)));

-- 4
select substr(professor_name, -2, 2) as "이름" from tb_professor;


-- 5 
select student_no, student_name from tb_student
where to_char(entrance_date, 'yyyy') >= 
2024 - ((to_number(to_char(sysdate, 'yyyy')) - (to_number(case when substr(student_ssn, 8, 1) in ('1', '2') then '1900' 
when substr(student_ssn, 8, 1) in ('3', '4') then '2000' end + to_number(substr(student_ssn, 1, 2))))) - 20);


-- 6
select next_day('20/12/24', 6) from dual; -- 금요일


-- 7 
select extract(year from to_date('99/10/11', 'yy/mm/dd')) as "yy 99 년", extract(month from to_date('99/10/11', 'yy/mm/dd')) as "yy 99 월", 
extract(day from to_date('99/10/11', 'yy/mm/dd')) as "yy 99 일", extract(year from to_date('49/10/11', 'yy/mm/dd')) as "yy 49 년",
extract(year from to_date('49/10/11', 'yy/mm/dd')) as "yy 49 월", extract(year from to_date('49/10/11', 'yy/mm/dd')) as "yy 49 일" from dual;

select extract(year from to_date('99/10/11', 'rr/mm/dd')) as "rr 99 년", extract(month from to_date('99/10/11', 'rr/mm/dd')) as "rr 99 월", 
extract(day from to_date('99/10/11', 'rr/mm/dd')) as "rr 99 일", extract(year from to_date('49/10/11', 'rr/mm/dd')) as "rr 49 년",
extract(year from to_date('49/10/11', 'rr/mm/dd')) as "rr 49 월", extract(year from to_date('49/10/11', 'rr/mm/dd')) as "rr 49 일" from dual;


-- 8
select student_no, student_name from tb_student where student_no not like 'A%';


-- 9 
select round(avg(point), 1) as "평점" from tb_student join tb_grade using (student_no) where student_no = 'A517178';


-- 10
select department_no as "학과번호", count(department_no) as "학생수(명)" from tb_student group by department_no order by department_no;


-- 11
select count(*) from tb_student where coach_professor_no is null;


-- 12
select substr(term_no, 1, 4) as "년도", round(avg(point), 1) as "평점" from tb_grade where student_no = 'A112113' 
group by substr(term_no, 1, 4);


-- 13 
select department_no as "학과코드명", count(decode(absence_yn, 'Y', 1)) as "휴학생 수" 
from tb_student
group by department_no order by department_no;

-- 14
select student_name as "동일이름", count(student_name) as "동명인 수" from tb_student 
group by student_name having count(student_name) >= 2 order by student_name;


-- 15
select decode(grouping(substr(term_no, 1, 4)), 0, substr(term_no, 1, 4), 1, ' ') as "년도", 
decode(grouping(substr(term_no, 5, 2)), 0, substr(term_no, 5, 2), 1, ' ') as "학기", 
round(avg(point), 1) as "평점" from tb_grade
where student_no = 'A112113' group by rollup(substr(term_no, 1, 4), substr(term_no, 5, 2)) order by substr(term_no, 1, 4);


--------------------------------------------------------------------------------
-- select - option
-- 1
select student_name as "학생 이름", student_address as "주소지" from tb_student order by student_name;


-- 2
select student_name, student_ssn from tb_student where absence_yn = 'Y' order by substr(student_ssn, 1, 6) desc;


-- 3
select student_name as "학생이름", student_no as "학번", student_address as "거주지 주소" from tb_student
where student_no not like 'A%' and student_address like '경기도%' or student_no not like 'A%' 
and student_address like '강원도%' order by student_name;


-- 4
select professor_name, professor_ssn from tb_professor join tb_department using (department_no) 
where department_name = '법학과' order by substr(professor_ssn, 1, 6);


-- 5
select student_no, point from tb_grade 
where term_no = '200402' and class_no = 'C3118100' order by point desc, student_no asc;


-- 6
select student_no, student_name, department_name from tb_student join tb_department using (department_no) order by student_name;


-- 7
select class_name, department_name from tb_class join tb_department using (department_no);


-- 8
select class_name, professor_name from tb_class join tb_class_professor using (class_no) join tb_professor using (professor_no);


-- 9
select class_name, professor_name from tb_class c join tb_class_professor using (class_no) 
join tb_professor using (professor_no) join tb_department d on (c.department_no = d.department_no)
where category = '인문사회';


-- 10
select student_no as "학번", student_name as "학생 이름", round(avg(point), 1) as "전체 평점" 
from tb_grade join tb_student using (student_no) join tb_department using (department_no) where department_name = '음악학과' 
group by student_no, student_name;


-- 11
select department_name, student_name, professor_name from tb_department join tb_student using (department_no)
join tb_professor on (coach_professor_no = professor_no) where student_no = 'A313047';


-- 12
select distinct student_name, term_no from tb_student join tb_grade using (student_no) join tb_class using (department_no)
where substr(term_no, 1, 4) = 2007 and class_name = '인간관계론';


-- 13
select class_name, department_name from tb_class left join tb_class_professor using (class_no) join tb_department using (department_no)
where category = '예체능' and professor_no is null order by department_name;


-- 14
select student_name as "학생이름", (case when coach_professor_no is null then '지도교수 미지정' else professor_name end) as "지도교수"
from tb_student s left join tb_professor on (coach_professor_no = professor_no) join tb_department d on (s.department_no = d.department_no) 
where d.department_name = '서반아어학과' order by student_no;;


-- 15
select student_no as "학번", student_name as "이름", department_name as "학과 이름", round(avg(point), 1) as "평점" 
from tb_student join tb_department using (department_no) join tb_grade using (student_no)
where absence_yn = 'N' group by student_no, student_name, department_name having round(avg(point), 1) >= 4;


-- 16
select class_no, class_name, avg(point) from tb_class join tb_department using (department_no) join tb_grade using (class_no)
where department_name = '환경조경학과' group by class_no, class_name;


-- 17
select student_name, student_address from tb_student
where department_no = (select department_no from tb_student where student_name = '최경희');


-- 18
select student_no, student_name from
(select student_no, student_name, round(avg(point), 1) 
from tb_grade join tb_student using (student_no) join tb_department using (department_no) where department_name = '국어국문학과' 
group by student_no, student_name order by round(avg(point), 1) desc) where rownum = 1; 


-- 19
select department_name as "계열 학과명", round(avg(point), 1) as "전공평점"
from tb_department join tb_student using (department_no) join tb_grade using (student_no)
where category = (select category from tb_department where department_name = '환경조경학과')
group by department_name order by department_name;

--------------------------------------------------------------------------------
-- ddl
-- 1
create table tb_category(
        name varchar2(10),
        use_yn char(1) default 'Y'
);


-- 2
create table tb_class_type(
        no varchar2(5) primary key,
        name varchar2(10)
);


-- 3
alter table tb_category add primary key (name);


-- 4 
alter table tb_class_type modify name not null;


-- 5
alter table tb_category modify name varchar(20);
alter table tb_class_type modify no varchar(10);


-- 6
alter table tb_category rename column name to category_name;
alter table tb_class_type rename column no to class_type_no;


-- 7
alter table tb_category rename constraint sys_c008594 to pk_category_name;
alter table tb_class_type rename constraint sys_c008593 to pk_class_type_no;


-- 8
insert into tb_category values ('공학', 'Y');
insert into tb_category values ('자연과학', 'Y');
insert into tb_category values ('의학', 'Y');
insert into tb_category values ('예체능', 'Y');
insert into tb_category values ('인문사회', 'Y');
commit;


-- 9 foreign key : alter table 테이블명 add foreign key (컬럼명) references 참조할 테이블명 (컬럼명);
alter table tb_department add constraint fk_department_category foreign key (category) references tb_category (category_name);


-- 10
grant create view to c##test;
create view vw_student as (select student_no, student_name, student_address from tb_student);


-- 11
create view vw_coach_professor as (select student_name, department_name, professor_name from tb_student 
join tb_department using (department_no) join tb_professor on (coach_professor_no = professor_no)) order by department_name;


-- 12
create view vw_count_student as (select department_name, count(student_name) as "stydent_count" from tb_student 
join tb_department using (department_no) group by department_name);


-- 13 
update vw_student set student_name = '알바' where student_no = 'A213046';


-- 14
-- with read only를 사용하여 읽기만 가능하게 함


-- 15
create view vw_class as (select class_no as "과목번호", class_name as "과목이름", count(class_no) as "누적수강생수(명)" 
from tb_class join tb_grade using (class_no) 
where substr(term_no, 1, 4) in (2007, 2006, 2005) group by class_no, class_name) order by count(class_no) desc;

select * from vw_class where rownum <= 3;

--------------------------------------------------------------------------------
-- dml
-- 1
insert into tb_class_type values (01, '전공필수');
insert into tb_class_type values (02, '전공선택');
insert into tb_class_type values (03, '교양필수');
insert into tb_class_type values (04, '교양선택');
insert into tb_class_type values (05, '논문지도');


-- 2
create table tb_student_common(
         student_no varchar2(10),
         student_name varchar2(20),
         student_address varchar2(500)
);

comment on column tb_student_common.student_no is '학번';
comment on column tb_student_common.student_name is '학생이름';
comment on column tb_student_common.student_address is '주소';

insert into tb_student_common(select student_no, student_name, student_address from tb_student);


-- 3
create table tb_student_korean(
        student_no varchar2(10),
        student_name varchar2(20),
        student_birth_year number,
        professor_name varchar2(20)
); 

comment on column tb_student_korean.student_no is '학번';
comment on column tb_student_korean.student_name is '학생이름';
comment on column tb_student_korean.student_birth_year is '출생년도';
comment on column tb_student_korean.professor_name is '교수이름';

insert into tb_student_korean(select student_no, student_name, to_char(to_date(substr(student_ssn, 1, 6)), 'yyyy'), professor_name
from tb_student join tb_department using (department_no) join tb_professor on (professor_no = coach_professor_no) 
where department_name = '국어국문학과');


-- 4
update tb_department set capacity = capacity * 1.1;

        
-- 5
update tb_student set student_address = '서울시 종로구 숭인동 181-21' where student_no = 'A413042';


-- 6 
update tb_student set student_ssn = substr(student_ssn, 1, 6);


-- 7
update tb_grade set point = 3.5 where student_no in (select student_no from tb_grade join tb_student using (student_no) 
where student_name = '김명훈') and term_no = 200501 and  
class_no in (select class_no from tb_grade join tb_class using (class_no) where class_name = '피부생리학');


-- 8
delete from tb_grade 
where student_no = (select student_no from tb_grade join tb_student using (student_no) where absence_yn = 'Y');