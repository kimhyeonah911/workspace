--일반 사용자 계정을 생성하는 구문(오직 관리자 계정에서만 사용할 수 있음)
--[표현법] create user c##계정명 identified by 비밀번호;
create user C##SPRING identified by SPRING;
--sql은 대소문자를 구분하지 않음 다만 비밀번호는 대소문자를 구분함

--위에서 생성된 일반 사용자 계정에 최소한의 권한(접속, 데이터관리) 부여
--[표현법] grant 권한1, 권한2 ... to 계정;
grant connect, resource to C##SPRING;

ALTER USER C##SPRING DEFAULT TABLESPACE USERS QUOTA UNLIMITED ON USERS;