package com.kh.boot.controller;

import com.kh.boot.domain.vo.Attachment;
import com.kh.boot.domain.vo.Board;
import com.kh.boot.domain.vo.PageInfo;
import com.kh.boot.domain.vo.PictureBoard;
import com.kh.boot.service.BoardService;
import com.kh.boot.utils.Template;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("list.bo")
    public String selectBoardList(@RequestParam(defaultValue = "1") int cpage, Model model){
        int boardCount = boardService.selectBoardCount();
        PageInfo pi = new PageInfo(boardCount, cpage, 10, 5);
        ArrayList<Board> list = boardService.selectBoardList(pi);

        model.addAttribute("list", list);
        model.addAttribute("pi", pi);
        return "board/boardListView";
    }

    @GetMapping("enrollForm.bo")
    public String enrollForm(){return "board/boardEnrollForm";}

    @PostMapping("insert.bo")
    public String insertBoard(Board board, MultipartFile upfile, HttpSession session, Model model){
        if(!upfile.getOriginalFilename().equals("")){
            String changeName = Template.saveFile(upfile, session, "/resources/uploadfile/");

            board.setChangeName("/resources/uploadfile/" + changeName);
            board.setOriginName(upfile.getOriginalFilename());
        } else{
            board.setChangeName(null);
            board.setOriginName(null);
        }

        int result = boardService.insertBoard(board);

        if(result > 0){
            session.setAttribute("alertMsg", "게시글 작성 성공");
            return "redirect:/list.bo";
        } else{
            model.addAttribute("errorMsg", "게시글 작성 실패");
            return "common/errorPage";

        }
    }

    @GetMapping("detail.bo")
    public String selectBoardDetail(@RequestParam(value = "bno") int boardNo, Model model){
        int plusCount = boardService.increaseCount(boardNo);
        
        if(plusCount > 0){
            Board board = boardService.selectBoard(boardNo);
            model.addAttribute("b", board);
            return "board/boardDetailView";
        } else{
            model.addAttribute("errorMsg", "게시글 조회 실패");
            return "common/errorPage";
        }
    }

    @GetMapping("updateForm.bo")
    public String updateForm(int bno, Model model){
        Board board = boardService.selectBoard(bno);
        model.addAttribute("b", board);
        return "board/boardUpdateForm";
    }

    @PostMapping("update.bo")
    public String updateBoard(Board board, MultipartFile reupfile, HttpSession session, Model model){
        if(reupfile != null && !reupfile.getOriginalFilename().equals("")){
            new File(session.getServletContext().getRealPath(board.getChangeName())).delete();
            String changeName = Template.saveFile(reupfile, session, "/resources/uploadfile/");

            board.setChangeName("/resources/uploadfile/" + changeName);
            board.setOriginName(reupfile.getOriginalFilename());
        } else{
            if(board.getChangeName() != null){
                board.setChangeName(board.getChangeName());
                board.setOriginName(board.getOriginName());
            } else{
                board.setChangeName(null);
                board.setOriginName(null);
            }
        }
        System.out.println(board);

        int result = boardService.updateBoard(board);

        if(result > 0){
            session.setAttribute("alertMsg", "게시글 수정 성공");
            model.addAttribute("b", board);
            return "board/boardDetailView";
        } else{
            model.addAttribute("errorMsg", "게시글 수정 실패");
            return "common/errorPage";

        }
    }

    @GetMapping("deleteForm.bo")
    public String deleteForm(Board board, ModelAndView mv, HttpSession session){
        int boardNo = board.getBoardNo();
        int result = boardService.deleteBoard(boardNo);
        if(result > 0){
            session.setAttribute("alertMsg", "성공적으로 삭제가 완료되었습니다.");
            return "redirect:/";
        }else {
            mv.addObject("errorMsg", "게시글 삭제가 실패하였습니다.");
            mv.setViewName("/common/errorPage");
            return "common/errorPage";
        }
    }

    @GetMapping("plist.bo")
    public String selectPBoardList(Model model){
        ArrayList<PictureBoard> list = boardService.selectPBoardList();

        model.addAttribute("list", list);
        return "board/PboardView";
    }

    @GetMapping("PenrollForm.bo")
    public String PenrollForm(){return "board/PboardEnrollForm";}

    @PostMapping("Pinsert.bo")
    public String insertPBoard(PictureBoard Pboard, MultipartFile[] upfile, HttpSession session, Model model){
        ArrayList<Attachment> Alist = new ArrayList<>();
        int result1 = boardService.insertPBoard(Pboard);;
        int pboardNo = Pboard.getPboardNo();
        int result2 = 0;
        for(int i = 0; i < upfile.length; i++){
            if(!upfile[i].getOriginalFilename().equals("")){
                Attachment newAttachment = new Attachment();
                String changeName = Template.saveFile(upfile[i], session, "/resources/uploadfile/");

                newAttachment.setChangeName("/resources/uploadfile/" + changeName);
                newAttachment.setOriginName(upfile[i].getOriginalFilename());

                if(i == 0) {
                    newAttachment.setFileLevel(1);
                } else{
                    newAttachment.setFileLevel(2);
                }
                newAttachment.setRefBno(pboardNo);

                Alist.add(newAttachment);
            }
        }

        if(result1 > 0){
            for (Attachment att : Alist) {
                result2 = boardService.insertAttachment(att);
            }
        } else{
            model.addAttribute("errorMsg", "게시글 작성 실패");
        }

        if(result2 > 0){
            session.setAttribute("alertMsg", "게시글 작성 성공");
            return "redirect:/plist.bo";
        } else{
            new File(session.getServletContext().getRealPath(Pboard.getChangeName())).delete();
            model.addAttribute("errorMsg", "게시글 작성 실패");
            return "common/errorPage";

        }
    }

    @GetMapping("detail.pbo")
    public String selectPBoardDetail(@RequestParam(value = "pno") int pboardNo, Model model){
        int plusCount = boardService.increasePCount(pboardNo);

        if(plusCount > 0){
            PictureBoard pBoard = boardService.selectPBoard(pboardNo);
            model.addAttribute("p", pBoard);
            ArrayList<Attachment> list = boardService.selectAttachmentList(pboardNo);
            model.addAttribute("list", list);
            return "board/PboardDetailView";
        } else{
            model.addAttribute("errorMsg", "게시글 조회 실패");
            return "common/errorPage";
        }
    }

    @GetMapping("updateForm.pbo")
    public String updateFormPbo(@RequestParam(value = "pno") int pboardNo, Model model){
        PictureBoard pBoard = boardService.selectPBoard(pboardNo);
        model.addAttribute("p", pBoard);
        ArrayList<Attachment> Alist = boardService.selectAttachmentList(pboardNo);
        model.addAttribute("list", Alist);
        return "board/PboardUpdateForm";
    }

    @PostMapping("update.pbo")
    public String updatePBoard(PictureBoard Pboard, MultipartFile[] reupfile, HttpSession session, Model model) {
        ArrayList<Attachment> Alist = new ArrayList<>();
        int pboardNo = Pboard.getPboardNo();
        for (int i = 0; i < reupfile.length; i++) {
            if (reupfile[i] != null && !reupfile[i].getOriginalFilename().equals("")) {
                Attachment newAttachment = new Attachment();
                String changeName = Template.saveFile(reupfile[i], session, "/resources/uploadfile/");

                newAttachment.setChangeName("/resources/uploadfile/" + changeName);
                newAttachment.setOriginName(reupfile[i].getOriginalFilename());

                if (i == 0) {
                    newAttachment.setFileLevel(1);
                } else {
                    newAttachment.setFileLevel(2);
                }
                newAttachment.setRefBno(pboardNo);

                Alist.add(newAttachment);
            } else {

            }
        }

        int result1 = boardService.updatePBoard(Pboard);
        int result2 = 0;

        if (result1 > 0 && result2 > 0) {
            for (Attachment att : Alist) {
                result2 = boardService.updateAttachment(att);
            }
        } else {
            model.addAttribute("errorMsg", "게시글 수정 실패");
        }

        if (result2 > 0) {
            session.setAttribute("alertMsg", "게시글 수정 성공");
            return "redirect:/detail.pbo";
        } else {
            new File(session.getServletContext().getRealPath(Pboard.getChangeName())).delete();
            model.addAttribute("errorMsg", "게시글 수정 실패");
            return "common/errorPage";

        }
    }

}
