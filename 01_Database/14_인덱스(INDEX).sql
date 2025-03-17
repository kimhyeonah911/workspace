/*
<index>
* 인덱스 : 데이터를 빠르게 검색하기 위한 데이터의 정렬과, 탐색같은 dbms 성능향상을 목적으로 생성하는 객체
- 검색속도가 향상됩니다. (select문에서 where조건으로 데이터를 빠르게 검색)
단, 모든 경우에 향상되는 것은 아니다. (insert, update, delete 작업 시 인덱스도 함께 업데이트 되어 성능저하될 수 있음)
*/

select * from user_mock_data;

--user_index_data와 동일한 user_mock_data 생성
create table user_index_data as select * from user_mock_data;

-- 제약조건 pk 추가 -> 인덱스 생성
alter table user_index_data add constraint pk_user_index_id primary key(id);

-- 제약 조건 uq 추가
alter table user_index_data add constraint uq_user_index_email unique(email);

-- 인덱스 설정 안 된 테이블(user_mock_data)
explain plan for select * from user_mock_data where id = 30000;

select * from table(dbms_xplan.display);

/*
------------------------------------------------------------------------------------
| Id  | Operation         | Name           | Rows  | Bytes | Cost (%CPU)| Time     |
------------------------------------------------------------------------------------
|   0 | SELECT STATEMENT  |                |     5 |   665 |   137   (1)| 00:00:01 |
|*  1 |  TABLE ACCESS FULL| USER_MOCK_DATA |     5 |   665 |   137   (1)| 00:00:01 |
------------------------------------------------------------------------------------
 
cost : 실행 예상 비용(자원을 얼마나 소비하는가) -> 값이 낮을수록 적은 비용으로 검색을 수행할 수 있음.
rows : 실행 계획에서 access된 row 수
bytes : 실행 계획에서 access된 bytes 수

table access full(full 스캔) : 전체 테이블을 탐색하여 결과를 도출하게 될 것이다.
*/
-- 인덱스 설정된 테이블(user_index_data)
explain plan for select * from user_index_data where id = 30000;

select * from table(dbms_xplan.display);
/*
------------------------------------------------------------------------------------------------
| Id  | Operation                   | Name             | Rows  | Bytes | Cost (%CPU)| Time     |
------------------------------------------------------------------------------------------------
|   0 | SELECT STATEMENT            |                  |     1 |    63 |     2   (0)| 00:00:01 |
|   1 |  TABLE ACCESS BY INDEX ROWID| USER_INDEX_DATA  |     1 |    63 |     2   (0)| 00:00:01 |
|*  2 |   INDEX UNIQUE SCAN         | PK_USER_INDEX_ID |     1 |       |     1   (0)| 00:00:01 |
------------------------------------------------------------------------------------------------
index (unique) scan : 인덱스 객체를 참조하여 탐색 후 결과를 도출하게 될 것을 예상한다.
table access (by index rowid) : 인덱스 객체로 참조한 index rowid로 탐색하여 결과를 도출하게 될 것을 예상한다.
*/
create index user_index_first_name on user_index_data(first_name);

-- 두 컬럼으로 조회
explain plan for select * from user_mock_data where id = 49977 and first_name = 'Gaston';

select * from table(dbms_xplan.display);

-- 두가지 이상으로 조건 조회를 자주할 때
-- 결합 인덱스 생성
create index user_index_firstname_id on user_index_data(id, first_name); 