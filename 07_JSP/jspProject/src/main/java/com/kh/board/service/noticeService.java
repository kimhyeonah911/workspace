package com.kh.board.service;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.board.model.dao.noticeDao;
import com.kh.board.model.vo.Notice;
import com.kh.common.vo.PageInfo;

public class noticeService {
	public int selectListCount() {
		Connection conn = getConnection();
		
		int listCount = new noticeDao().selectListCount(conn);
		close(conn);
		
		return listCount;
	}
	
	public ArrayList<Notice> selectList(PageInfo pi){
		Connection conn = getConnection();
		ArrayList<Notice> list = new noticeDao().selectList(conn, pi);
		
		close(conn);
		return list;
		
	}
}
