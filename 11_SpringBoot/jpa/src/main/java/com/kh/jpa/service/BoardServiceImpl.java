package com.kh.jpa.service;

import com.kh.jpa.dto.BoardDto;
import com.kh.jpa.entity.Board;
import com.kh.jpa.entity.BoardTag;
import com.kh.jpa.entity.Member;
import com.kh.jpa.entity.Tag;
import com.kh.jpa.enums.CommonEnums;
import com.kh.jpa.repository.BoardRepository;
import com.kh.jpa.repository.MemberRepository;
import com.kh.jpa.repository.TagRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final TagRepository tagRepository;
    private final String UPLOAD_PATH = "C:\\dev_tool";

    @Transactional(readOnly = true)
    @Override
    public Page<BoardDto.Response> getBoardList(Pageable pageable) {
        //getContent() 실제 데이터 리스트 반환
        //getTotalPages() 전체 페이지 개수
        //getTotalElements() 전체 데이터 수
        //getSize() 페이지당 데이터 수
        Page<Board> page = boardRepository.findByStatus(CommonEnums.Status.Y, pageable);
        return page.map(BoardDto.Response::toSimpleDto);
    }

    @Transactional(readOnly = true)
    @Override
    public BoardDto.Response getBoardDetail(Long boardNo) {
        return boardRepository.findById(boardNo)
                .map(BoardDto.Response::toDto)
                .orElseThrow(()-> new EntityNotFoundException("게시글을 찾을 수 없습니다."));
    }

    @Override
    public Long createBoard(BoardDto.Create createDto) throws IOException {
        //게시글 작성
        //작성자 찾기 -> 객체지향코드를 작성할 것이기 때문에 key 를 직접 외래키로 insert 하지 않고 작성자를 찾아 참조해준다.
        Member member = memberRepository.findOne(createDto.getBoard_writer())
                .orElseThrow(() -> new EntityNotFoundException("회원을 찾을 수 없습니다."));

        String changeName = null;
        String originName = null;

        if(createDto.getFile() != null && !createDto.getFile().isEmpty()) {
            originName = createDto.getFile().getOriginalFilename();
            changeName = UUID.randomUUID().toString() + "_" + originName;

            File uploadDir = new File(UPLOAD_PATH);
            if(!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            createDto.getFile().transferTo(new File(UPLOAD_PATH + changeName));
        }
        Board board = createDto.toEntity();
        board.changeMember(member);
        board.changeFile(originName, changeName);

        if(createDto.getTags() != null && !createDto.getTags().isEmpty()) {
            //tag가 왔다.
            for(String tagName : createDto.getTags()) {
                //tag를 이름으로 조회해서 없으면 새로 만들어라
                Tag tag = tagRepository.findByTagName(tagName)
                        .orElseGet(() -> tagRepository.save(Tag.builder()
                                .tagName(tagName)
                                .build()));
                BoardTag boardTag = BoardTag.builder()
                        .tag(tag)
                        .build();

                boardTag.changeBoard(board);
            }
        }
        boardRepository.save(board);
        return board.getBoardNo();
    }

    @Override
    public BoardDto.Response updateBoard(Long boardNo, BoardDto.Update updateDto) throws IOException {
        Board board = boardRepository.findById(boardNo)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다"));
        String originName = board.getOriginName();
        String changeName = board.getChangeName();

        if(updateDto.getFile() != null && !updateDto.getFile().isEmpty()) {
            originName = updateDto.getFile().getOriginalFilename();
            changeName = UUID.randomUUID().toString() + "_" + originName;

            File uploadDir = new File(UPLOAD_PATH);
            if(!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            updateDto.getFile().transferTo(new File(UPLOAD_PATH + changeName));
        }

        board.changeTitle(updateDto.getBoard_title());
        board.changeContent(updateDto.getBoard_content());
        board.changeFile(originName, changeName);

        if(updateDto.getTags() != null && !updateDto.getTags().isEmpty()) {
            //기존 BoardTags -> 연결이 끊기면 필요 X
            //연결된 boardTag의 영속성을 제거한다 -> orphanRemoval = true 설정이 되어있다면 실제 db 에서 제거
            board.getBoardTags().clear();

            for(String tagName : updateDto.getTags()) {
                Tag tag = tagRepository.findByTagName(tagName)
                        .orElseGet(() -> tagRepository.save(Tag.builder()
                                .tagName(tagName)
                                .build()));
                BoardTag boardTag = BoardTag.builder()
                        .tag(tag)
                        .build();

                boardTag.changeBoard(board);
            }
        }
        return BoardDto.Response.toDto(board);
    }

    @Override
    public void deleteBoard(Long boardNo) {
        Board board = boardRepository.findById(boardNo)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));
        if(board.getChangeName() != null){
            new File(UPLOAD_PATH + board.getOriginName()).delete();
        }
        boardRepository.delete(board);
    }

    @Override
    public BoardDto.Response countBoard(Long boardNo) {
        Board board = boardRepository.findById(boardNo)
                .orElseThrow(() -> new EntityNotFoundException("게시물을 찾을 수 없습니다."));
        board.boardUpdateCount();
        return BoardDto.Response.toDto(board);
    }
}