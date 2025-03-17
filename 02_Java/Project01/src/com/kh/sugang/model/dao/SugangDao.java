package com.kh.sugang.model.dao;

import static com.kh.sugang.common.SugangTemplate.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.sugang.model.vo.Student;
import com.kh.sugang.model.vo.StudentClass;
import com.kh.sugang.model.vo.SugangClass;

public class SugangDao {
	private Properties prop = new Properties();
	
	public SugangDao() {
		super();
		try {
			prop.loadFromXML(new FileInputStream("resources1/query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertClass(SugangClass c, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertClass");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, c.getCname());
			pstmt.setString(2, c.getCdepartment());
			pstmt.setString(3, c.getCcompletion());
			pstmt.setInt(4, c.getCcredit());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateClass(int Cno, String Cname, String Cdepartment, String Ccompletion, int Ccredit, Connection conn) {
		int result = 0; 
		PreparedStatement pstmt = null; 
		
		String sql = prop.getProperty("updateClass");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, Cname);
			pstmt.setString(2, Cdepartment);
			pstmt.setString(3, Ccompletion);
			pstmt.setInt(4, Ccredit);
			pstmt.setInt(5, Cno);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteClass(int Cno, Connection conn) {
		int result = 0; 
		PreparedStatement pstmt = null; 
		
		String sql = prop.getProperty("deleteClass");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, Cno);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int insertStudent(Student s, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertStudent");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, s.getNo());
			pstmt.setString(2, s.getPw());
			pstmt.setString(3, s.getName());
			pstmt.setInt(4, s.getAge());
			pstmt.setInt(5, s.getGrade());
			pstmt.setString(6, s.getDepartment());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public ArrayList<SugangClass> selectClass(Connection conn){
		ResultSet rset = null;
		ArrayList<SugangClass> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectClass");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				SugangClass s = new SugangClass();
				s.setCno(rset.getInt("CLASS_NO"));
				s.setCname(rset.getString("CLASS_NAME"));
				s.setCdepartment(rset.getString("CLASS_DEPARTMENT"));
				s.setCcompletion(rset.getString("COMPLETION"));
				s.setCcredit(rset.getInt("CREDIT"));
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public ArrayList<SugangClass> searchClass(String Cdepartment, Connection conn){
		ResultSet rset = null;
		ArrayList<SugangClass> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("searchClass");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Cdepartment);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				SugangClass s = new SugangClass();
				s.setCno(rset.getInt("CLASS_NO"));
				s.setCname(rset.getString("CLASS_NAME"));
				s.setCdepartment(rset.getString("CLASS_DEPARTMENT"));
				s.setCcompletion(rset.getString("COMPLETION"));
				s.setCcredit(rset.getInt("CREDIT"));
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public int addClass(int stu_no, String Cname, Connection conn) {
		int result = 0; 
		PreparedStatement pstmt = null; 
		
		String sql = prop.getProperty("addClass");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, stu_no);
			pstmt.setString(2, Cname);
			pstmt.setString(3, Cname);
			pstmt.setString(4, Cname);
			pstmt.setString(5, Cname);
			pstmt.setString(6, Cname);
			pstmt.setInt(7, stu_no);
			pstmt.setString(8, Cname);
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public ArrayList<SugangClass> selectStudentClass(int stu_no, Connection conn){
		ResultSet rset = null;
		ArrayList<SugangClass> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectStudentClass");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, stu_no);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				SugangClass s = new SugangClass();
				s.setCno(rset.getInt("CLASS_NO"));
				s.setCname(rset.getString("CLASS_NAME"));
				s.setCdepartment(rset.getString("CLASS_DEPARTMENT"));
				s.setCcompletion(rset.getString("COMPLETION"));
				s.setCcredit(rset.getInt("CREDIT"));
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public int deleteStudentClass(int stu_no, String Cname, Connection conn) {
		int result = 0; 
		PreparedStatement pstmt = null; 
		
		String sql = prop.getProperty("deleteStudentClass");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, stu_no);
			pstmt.setString(2, Cname);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public ArrayList<StudentClass> selectStudentClassAll(int stu_no, Connection conn){
		ResultSet rset = null;
		ArrayList<StudentClass> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectStudentClass");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, stu_no);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				StudentClass s = new StudentClass();
				s.setNo(stu_no);
				s.setCno(rset.getInt("CLASS_NO"));
				s.setCname(rset.getString("CLASS_NAME"));
				s.setCdepartment(rset.getString("CLASS_DEPARTMENT"));
				s.setCcompletion(rset.getString("COMPLETION"));
				s.setCcredit(rset.getInt("CREDIT"));
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public ArrayList<Student> selectStudent(Connection conn){
		ResultSet rset = null;
		ArrayList<Student> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectStudent");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Student s = new Student();
				s.setNo(rset.getInt("STUDENT_NO"));
				s.setPw(rset.getString("STUDENT_PW"));
				s.setName(rset.getString("STUDENT_NAME"));
				s.setAge(rset.getInt("STUDENT_AGE"));
				s.setGrade(rset.getInt("STUDENT_GRADE"));
				s.setDepartment(rset.getString("STUDENT_DEPARTMENT"));
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
		
	}
	
}
