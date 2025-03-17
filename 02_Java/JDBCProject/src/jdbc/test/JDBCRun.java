package jdbc.test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JDBCRun {
	/*
	 * JDBC를 사용하기 위해서는 자바 프로젝트에 jdbc 드라이버를 추가해줘야한다.
	 * 프로젝트 우클릭 -> Properties -> 라이브러리 -> add external jar -> ojbc.jar 추가
	 * 
	 * JDBC용 객체
	 * - Connection : DB의 연결 정보를 담고 있는 객체
	 * - [Prepared]Statement : 연결된 DB에 sql문을 전달해서 실행하고 결과를 받아내는 객체
	 * - ResultSet : SELECT문 실행 후 조회된 결과물을 담는 객체
	 * 
	 * JDBC 사용 순서
	 * 1) JDBC Driver 등록 : JDBC인터페이스가 특정 DBMS가 제공하는 클래스를 사용할 수 있도록 등록
	 * 2) Connection 생성 : 연결하고자 하는 DB정보를 입력해서 해당 DB와 연결할 수 있는 객체 생성
	 * 3) Statement 생성 : Connection객체를 이용해서 생성(SQL문을 실행하고 결과를 받는 객체)
	 * 4) SQL문 전달해서 실행 : Statement객체를 이용해서 SQL문 실행
	 * 5) 결과받기 > SELETE문 실행(6-1) => ResultSet객체(조회된 결과를 담아준다.), DML(INSERT, UPDATE, DELETE)문 실행(6-2) => int
	 * 6-1) ResultSet객체에 담겨있는 데이터들을 하나씩 추출해서 차근차근 옮겨담기
	 * 6-2) 트랜잭션 처리(성공했으면 commit, 실패했다면 rollback 실행)
	 * 7) 다 사용한 JDBC객체를 반드시 자원 반납(close) => 생성된 역순으로
	 */
	public static void main(String[] args) {
		/*
		// 1. 각자 pc(localhost)에 jdbc계정 연결 후 test 테이블에 데이터 insert 해보기
		// insert -> 처리된 행 수(int) -> 트랜잭션
		
		// 필요한 변수 세팅하기
		int result = 0; //처리된 행 수 받아줄 변수
		Connection conn = null; //DB연결정보 보관 객체
		Statement stmt = null; //sql문을 전달해서 실행 후 결과를 받아올 객체
		
		Scanner sc = new Scanner(System.in);
		System.out.print("번호 : ");
		int num = sc.nextInt();
		System.out.print("이름 : ");
		String name = sc.next();
		sc.nextLine();
		
		//실행할 SQL문(완전한 상태로 만들자) | (SQL 뒤에 ; 없어야한다!!!!!!!!!!!)
		String sql = "INSERT INTO TEST VALUES(" + num + ", '" + name +"', SYSDATE)";
		
		try {
			//1) JDBC Driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver"); //오타 | ojdbc.jar 파일 추가하지 않은 경우
			System.out.println("OracleDriver 등록 성공");
			
			//2) Connection생성(url, 계정명, 비밀번호)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "C##JDBC", "JDBC");
			conn.setAutoCommit(false);
			
			//3) Statement 생성
			stmt = conn.createStatement();
			
			//4, 5) sql문 전달하면서 결과받기(처리된 행 수)
			result = stmt.executeUpdate(sql);
			//내가 실행할 SQL이 DML -> stmt.executeUpdate : int
			//내가 실행할 SQL이 SELETE -> stmt.executeQuery : ResultSet
			
			//6) 트랜잭션 처리
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//7) 다 쓴 자원 반납(생성과 역순으로)
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		if(result > 0) {
			System.out.println("데이터 삽입 성공");
		}else {
			System.out.println("데이터 삽입 실패");
		}
		*/
		
		
		//2. 내 pc DB상에 JDBC계정에서 TEST테이블의 모든 데이터 조회
		//SELECT -> 결과 ResultSet -> ResultSet으로부터 데이터 추출
		
		//필요한 변수 세팅
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		List<Test> list = new ArrayList<>();
		
		//실행할 SQL문 작성
		String sql = "SELECT * FROM TEST";
		
		try {
			//1) JDBC Driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("OracleDriver 등록 성공");
			
			//2) Connection생성(url, 계정명, 비밀번호)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "C##JDBC", "JDBC");
			conn.setAutoCommit(false);
			
			//3) Statement 생성
			stmt = conn.createStatement();
			
			//4, 5)SQL문 전달하면서 결과받기(ResultSet)
			rset = stmt.executeQuery(sql);
			
			//rset.next() => rset의 다음행이 있는지 없는지를 알려주고 + 한 행을 내려준다.
			while(rset.next()) {
				int tno = rset.getInt("TNO");
				String tname = rset.getString("TNAME");
				Date tdate = rset.getDate("TDATE");
				
				list.add(new Test(tno, tname, tdate));
				
				System.out.println(tno + ", " + tname + ", " + tdate);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(list.size()>0) {
			System.out.println(list);
		}else {
			System.out.println("데이터가 없습니다.");
		}
		
		
		/*
		//3. preparedStatement객체 사용하기 -> SQL문의 형태를 먼저 정의하고 각 데이터는 추후에 넣는 방식
		//필요한 변수 세팅
		Scanner sc = new Scanner(System.in);
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		System.out.print("수정할 TNO를 입력하세요 : ");
		int tno = sc.nextInt();
		sc.nextLine();
		
		System.out.print("새로운 TNAME을 입력하세요 : ");
		String newName = sc.nextLine();
		
		System.out.print("새로운 TDATE(YYYY-MM-DD)를 입력하세요 : ");
		String newDate = sc.nextLine();
				
		//실행할 SQL문 작성
		//String sql = "UPDATE TEST SET TNAME = '" + newName + "', TDATE = TO_DATE('"
		//		+ newDate + "', 'YYYY-MM-DD') WHERE TNO = " + tno;
		
		String sql = "UPDATE TEST SET TNAME=?, TDATE=TO_DATE(?, 'YYYY-MM-DD') WHERE TNO = ?";
		
		try {
			//1) JDBC Driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("OracleDriver 등록 성공");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "C##JDBC", "JDBC");
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(sql); // 미완성된 sql문 전달해서 pstmt객체 생성
			
			//미완성된 sql 완성시키기(파라미터 설정 -> ?갯수만큼)
			pstmt.setString(1, newName);
			pstmt.setString(2, newDate);
			pstmt.setInt(3, tno);
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(result > 0) {
			System.out.println(result + "개의 행 UPDATE");
		}else {
			System.out.println("UPDATE 실패");
		}
		*/
	}
}
