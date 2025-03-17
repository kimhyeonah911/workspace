package com.kh.mybatis.board.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.board.model.vo.Reply;
import com.kh.mybatis.common.PageInfo;

public interface BoardService {
	int selectListCount();
	ArrayList<Board> selectList(PageInfo pi);
	int selectSearchCount(HashMap<String, String> map);
	int updateBoardCount(int boardNo);
	Board selectBoard(int boardNo);
	ArrayList<Board> selectSearchList(HashMap<String, String> map, PageInfo pi);
	ArrayList<Reply> selectReply(int boardNo);
	int insertReply(Reply r);
}
