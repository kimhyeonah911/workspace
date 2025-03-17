package com.kh.boot.mappers;

import com.kh.boot.domain.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.ArrayList;

@Mapper
public interface BoardMapper {
    int selectBoardCount();
    ArrayList<Board> selectBoardList(RowBounds rowBounds);
    int insertBoard(Board board);
    int increaseCount(@Param("bno") int boardNo);
    Board selectBoard(@Param("bno") int boardNo);
    int insertReply(Reply reply);
    ArrayList<Reply> selectReplyList(@Param("refBno") int refBno);
    int updateBoard(Board board);
    int deleteBoard(@Param("bno") int boardNo);
    ArrayList<Board> selecttop5BoardList(RowBounds rowBounds);
    ArrayList<PictureBoard> selectPBoardList();
    int insertPBoard(PictureBoard Pboard);
    int insertAttachment(Attachment attachment);
    int increasePCount(@Param("bno") int pboardNo);
    PictureBoard selectPBoard(@Param("bno") int pboardNo);
    ArrayList<Attachment> selectAttachmentList(@Param("bno") int pboardNo);
    int updatePBoard(PictureBoard Pboard);
    int updateAttachment(Attachment attachment);
}
