package com.kh.member.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.member.model.vo.Member;

import static com.kh.common.JDBCTemplate.*;

public class MemberDao {
	private Properties prop = new Properties();
	
	
	public MemberDao() {
		String filePath = MemberDao.class.getResource("/db/sql/member-mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public Member loginMember(Connection conn, String userId, String userPwd) {
		//select -> Member 조회 -> ResultSet(1개 또는 0개)
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("loginMember");
		
		try {
			pstmt = conn.prepareStatement(sql); //미완성 sql
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			rset = pstmt.executeQuery(); //조회결과가 있다면 한 행 | 없다면 반환x
			
			if(rset.next()) {
				m = new Member(
							rset.getInt("USER_NO"),
							rset.getString("USER_ID"),
							rset.getString("USER_PWD"),
							rset.getString("USER_NAME"),
							rset.getString("PHONE"),
							rset.getString("EMAIL"),
							rset.getString("ADDRESS"),
							rset.getString("INTEREST"),
							rset.getDate("ENROLL_DATE"),
							rset.getDate("MODIFY_DATE"),
							rset.getString("STATUS")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return m;
	}
	
	public int insertMember(Connection conn, Member m) {
		//insert -> 처리된 행 수 -> 반환
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = prop.getProperty("insertMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getPhone());
			pstmt.setString(5, m.getEmail());
			pstmt.setString(6, m.getAddress());
			pstmt.setString(7, m.getInterest());
			
			result = pstmt.executeUpdate(); //조회결과가 있다면 한 행 | 없다면 반환x
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = prop.getProperty("updateMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getPhone());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getAddress());
			pstmt.setString(4, m.getInterest());
			pstmt.setString(5, m.getUserId());
			
			result = pstmt.executeUpdate(); 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updatePwdMember(Connection conn, String userId, String userPwd) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = prop.getProperty("updatePwdMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userPwd);
			pstmt.setString(2, userId);
			
			result = pstmt.executeUpdate(); 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public Member selectMemberByUserId(Connection conn, String userId) {
		//select -> Member 조회(id) -> ResultSet(1개 또는 0개)
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectMemberByUserId");
		
		try {
			pstmt = conn.prepareStatement(sql); //미완성 sql
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery(); //조회결과가 있다면 한 행 | 없다면 반환x
			
			if(rset.next()) {
				m = new Member(
							rset.getInt("USER_NO"),
							rset.getString("USER_ID"),
							rset.getString("USER_PWD"),
							rset.getString("USER_NAME"),
							rset.getString("PHONE"),
							rset.getString("EMAIL"),
							rset.getString("ADDRESS"),
							rset.getString("INTEREST"),
							rset.getDate("ENROLL_DATE"),
							rset.getDate("MODIFY_DATE"),
							rset.getString("STATUS")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return m;
	}
	
	public int deleteMember(Connection conn, String userId) {
		//insert -> 처리된 행 수 -> 반환
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = prop.getProperty("deleteMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			result = pstmt.executeUpdate(); //조회결과가 있다면 한 행 | 없다면 반환x
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int idCheck(Connection conn, String checkId) {
		//select -> 같은 ID로 되어있는 멤버 숫자로만 조회 -> resultSet
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("idCheck");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, checkId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				count = rset.getInt("COUNT");
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return count;
	}
}
