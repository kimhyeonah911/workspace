package com.kh.sugang.service;

import static com.kh.sugang.common.SugangTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.sugang.model.dao.SugangDao;
import com.kh.sugang.model.vo.Student;
import com.kh.sugang.model.vo.StudentClass;
import com.kh.sugang.model.vo.SugangClass;

public class SugangService {

	public int insertClass(SugangClass c) {
		Connection conn = getConnection();
		int result = new SugangDao().insertClass(c, conn);
		
		if(result > 0 ) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int updateClass(int Cno, String Cname, String Cdepartment, String Ccompletion, int Ccredit) {
		Connection conn = getConnection();
		int result = new SugangDao().updateClass(Cno, Cname,Cdepartment, Ccompletion, Ccredit, conn);
		
		if(result > 0 ) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int deleteClass(int Cno) {
		Connection conn = getConnection();
		int result = new SugangDao().deleteClass(Cno, conn);
		
		if(result > 0 ) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int insertStudent(Student s) {
		Connection conn = getConnection();
		int result = new SugangDao().insertStudent(s, conn);
		
		if(result > 0 ) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public ArrayList<SugangClass> selectClass() {
		Connection conn = getConnection();
		ArrayList<SugangClass> list = new SugangDao().selectClass(conn);
		close(conn);
		
		return list;
	}
	
	public ArrayList<SugangClass> searchClass(String Cdepartment) {
		Connection conn = getConnection();
		ArrayList<SugangClass> list = new SugangDao().searchClass(Cdepartment, conn);
		close(conn);
		
		return list;
	}
	
	public int addClass(int stu_no, String Cname) {
		Connection conn = getConnection();
		int result = new SugangDao().addClass(stu_no, Cname, conn);
		
		if(result > 0 ) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public ArrayList<SugangClass> selectStudentClass(int stu_no) {
		Connection conn = getConnection();
		ArrayList<SugangClass> list = new SugangDao().selectStudentClass(stu_no, conn);
		close(conn);
		
		return list;
	}
	
	public int deleteStudentClass(int stu_no, String Cname) {
		Connection conn = getConnection();
		int result = new SugangDao().deleteStudentClass(stu_no, Cname, conn);
		
		if(result > 0 ) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public ArrayList<StudentClass> selectStudentClassAll(int stu_no) {
		Connection conn = getConnection();
		ArrayList<StudentClass> list = new SugangDao().selectStudentClassAll(stu_no, conn);
		close(conn);
		
		return list;
	}
	
	public ArrayList<Student> selectStudent() {
		Connection conn = getConnection();
		ArrayList<Student> list = new SugangDao().selectStudent(conn);
		close(conn);
		
		return list;
	}
	
}
