package com.kh.jdbc.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

//static으로 import시 static 메서드를 직접 가져올 수 있음
import static com.kh.common.JDBCTemplate.*;

import com.kh.jdbc.model.dao.MemberDao;
import com.kh.jdbc.model.vo.Member;

public class MemberService {
	private MemberDao md = new MemberDao();
	//1) JDBC driver 등록
	//2) Connection 객체 생성
	//3) Connection 객체 처리
	
	public int insertMember(Member m) {
		Connection conn = getConnection();
		int result = md.insertMember(m, conn);
		
		if(result > 0 ) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public ArrayList<Member> selectList() {
		Connection conn = getConnection();
		ArrayList<Member> list = md.selectList(conn);
		close(conn);
		
		return list;
	}
	
	public int updateMember(String userId, String email, String phone, String address, String hobby) {
		Connection conn = getConnection();
		int result = md.updateMember(userId, email, phone, address, hobby, conn);
		
		if(result > 0 ) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	
	public int deleteMember(String userId) {
		Connection conn = getConnection();
		int result = md.deleteMember(userId, conn);
		
		if(result > 0 ) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public ArrayList<Member> selectByUserName(String keyword) {
		Connection conn = getConnection();
		ArrayList<Member> list = md.selectByUserName(keyword, conn);
		close(conn);
		
		return list;
	}
	
}
