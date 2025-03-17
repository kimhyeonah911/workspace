package com.kh.boot.service;

import com.kh.boot.domain.vo.*;

import java.util.ArrayList;

public interface BoardService {
    //총게시글 수
    int selectBoardCount();
    //게시글 정보(페이징)
    ArrayList<Board> selectBoardList(PageInfo pi);
    //게시글 추가
    int insertBoard(Board board);
    //게시글 카운트 + 1
    int increaseCount(int boardNo);
    //게시글 조회
    Board selectBoard(int boardNo);
    //댓글추가
    int insertReply(Reply reply);
    //댓글조회
    ArrayList<Reply> selectReplyList(int refBno);
    //게시글 수정
    int updateBoard(Board board);
    int deleteBoard(int boardNo);
    ArrayList<Board> selecttop5BoardList();
    ArrayList<PictureBoard> selectPBoardList();
    int insertPBoard(PictureBoard Pboard);
    int insertAttachment(Attachment attachment);
    int increasePCount(int pboardNo);
    PictureBoard selectPBoard(int pboardNo);
    ArrayList<Attachment> selectAttachmentList(int pboardNo);
    int updatePBoard(PictureBoard Pboard);
    int updateAttachment(Attachment attachment);

}
