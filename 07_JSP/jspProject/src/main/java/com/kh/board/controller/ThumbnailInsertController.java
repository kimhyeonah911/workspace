package com.kh.board.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload;

import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.board.service.BoardService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class ThumbnailInsertController
 */
@WebServlet("/insert.th")
public class ThumbnailInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThumbnailInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//enctype이 multipart/form-data일 때 request로 값 추출이 불가
				//System.out.println(request.getParameter("title"));
				//System.out.println(request.getParameter("upfile"));
				
				/**
				 * 파일 업로드를 위해서
				 * commons-fileupload2-core-2.0.0-M2.jar 
				 * commons-fileupload2-jakarta-2.0.0-M1.jar
				 * commons-io-2.16.1.jar 라이브러리를 사용하겠다.
				 * 
				 * commons-fileupload2-core : 주요기능을 구현하는 라이브러리(멀티파트 요청에 대한 처리기능)
				 * commons-fileupload2-jakarta : javax.servlet -> jakarta.servlet 패키지를 사용하게 하는 라이브러리
				 * commons-io : 파일 읽기/쓰기에 대한 스트림(입출력기능)을 구현하고 있는 라이브러리
				 */
				
				//enctype이 multipart/form-data로 전송이 되었는지 체크
				//System.out.println(JakartaServletFileUpload.isMultipartContent(request));
				
				if(JakartaServletFileUpload.isMultipartContent(request)) {
					HttpSession session = request.getSession();
					
					//1. 파일용량제한(byte)
					int fileMaxSize = 1024 * 1024 * 50; //50MB
					int requestMaxSize = 1024 * 1024 * 60; //전체요청 크기제한 
					
					//2. 전달된 파일을 저장시킬 폴더 경로 가져오기
					String savePath = request.getServletContext().getRealPath("/resources/thumbnail_upfile/");
					
					//3. DistFileItemFactory(파일을 임시로 저장) 객체 생성
					DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
					
					//4. JakartaServletFileUpload http요청으로 들어온 파일데이터 파싱 -> 개별 FileItem 객체로 변환
					JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);
					
					upload.setFileSizeMax(fileMaxSize);
					upload.setSizeMax(requestMaxSize);
					
					//요청(request)로부터 파일아이템(요청정보) 파싱
					List<FileItem> formItems =  upload.parseRequest(request);
					
					Board b = new Board();
					ArrayList<Attachment> list = new ArrayList<>(); //첨부파일목록
					
					Member m = (Member)session.getAttribute("loginUser");
					b.setBoardWriter(m.getUserNo());
					
					for(FileItem item : formItems) {
						System.out.println(item);
						//업로드된 데이터가 일반 폼 필드인지, 파일인지를 구분할 수 있음
						if(item.isFormField()) { //일반파라미터
							switch(item.getFieldName()) {
							case "title":
								b.setBoardTitle(item.getString(Charset.forName("UTF-8")));
								break;
							case "content":
								b.setBoardContent(item.getString(Charset.forName("UTF-8")));
								break;
							}
						} else { //파일
							String originName = item.getName();
							
							if(originName.length() > 0) { //파일업로드를 했을 때
								//파일명이 겹치면 덮어씌우기 때문에 고유한 파일명 만듬
								String tmpName = "kh_" + System.currentTimeMillis() + (int)(Math.random()*100000)+1;
								String type = originName.substring(originName.lastIndexOf("."));
								String changeName = tmpName + type; //서버에 저장할 파일명
								
								File f = new File(savePath, changeName);
								item.write(f.toPath()); //지정한 경로에 파일 업로드
			
								Attachment at = new Attachment();
								at.setOriginName(originName);
								at.setChangeName(changeName);
								at.setFilePath("resources/thumbnail_upfile/");
								
								at.setFileLevel(item.getFieldName().equals("file1")? 1:2);
								list.add(at);
							}
						}
					}
					
					int result = new BoardService().insertThumbnailBoard(b, list);
					if(result>0) {
						request.getSession().setAttribute("alertMsg", "사진게시글 작성 성공");
						response.sendRedirect(request.getContextPath() + "/list.th");
					} else {
						for(Attachment at : list) {
							new File(savePath + at.getChangeName()).delete();
							
							request.setAttribute("errorMsg", "게시글 작성에 실패하였습니다.");
							request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
						}
					}
				}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
