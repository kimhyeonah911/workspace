package com.kh.boot.service;

import com.kh.boot.domain.vo.*;
import com.kh.boot.mappers.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
    private final BoardMapper boardMapper;
    private PictureBoard pictureBoard;

    @Override
    public int selectBoardCount() {
        return boardMapper.selectBoardCount();
    }

    @Override
    public ArrayList<Board> selectBoardList(PageInfo pi) {
        int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
        RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
        return boardMapper.selectBoardList(rowBounds);
    }

    @Override
    public int insertBoard(Board board) {
        return boardMapper.insertBoard(board);
    }

    @Override
    public int increaseCount(int boardNo) {
        return boardMapper.increaseCount(boardNo);
    }

    @Override
    public Board selectBoard(int boardNo) {
        return boardMapper.selectBoard(boardNo);
    }

    @Override
    public int insertReply(Reply reply) {
        return boardMapper.insertReply(reply);
    }

    @Override
    public ArrayList<Reply> selectReplyList(int refBno) {
        return boardMapper.selectReplyList(refBno);
    }

    @Override
    public int updateBoard(Board board) {
        return boardMapper.updateBoard(board);
    }

    @Override
    public int deleteBoard(int boardNo) {
        return boardMapper.deleteBoard(boardNo);
    }

    @Override
    public ArrayList<Board> selecttop5BoardList() {
        RowBounds rowBounds = new RowBounds(0, 5);
        return boardMapper.selecttop5BoardList(rowBounds);
    }

    @Override
    public ArrayList<PictureBoard> selectPBoardList() {
        return boardMapper.selectPBoardList();
    }

    @Override
    public int insertPBoard(PictureBoard Pboard) {
        return boardMapper.insertPBoard(Pboard);
    }

    @Override
    public int insertAttachment(Attachment attachment) {
        return boardMapper.insertAttachment(attachment);
    }

    @Override
    public int increasePCount(int pboardNo) {
        return boardMapper.increasePCount(pboardNo);
    }

    @Override
    public PictureBoard selectPBoard(int pboardNo) {
        return boardMapper.selectPBoard(pboardNo);
    }

    @Override
    public ArrayList<Attachment> selectAttachmentList(int pboardNo) {
        return boardMapper.selectAttachmentList(pboardNo);
    }

    @Override
    public int updatePBoard(PictureBoard Pboard) {
        return boardMapper.updatePBoard(Pboard);
    }

    @Override
    public int updateAttachment(Attachment attachment) {
        return boardMapper.updateAttachment(attachment);
    }

}
