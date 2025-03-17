<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>
<style>
	table * {margin:5px;}
	table {width:100%;}
</style> 
</head>
<body onload="init()">
	<jsp:include page="../common/header.jsp" />

    <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>게시글 상세보기</h2>
            <br>

            <a class="btn btn-secondary" style="float:right;" href="plist.bo">목록으로</a>
            <br><br>

            <table id="contentArea" algin="center" class="table">
                <tr>
                    <th>제목</th>
                    <td colspan="2">${p.pboardTitle }</td>
                    <th>조회수</th>
                    <td colspan="1">${p.count }</td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td colspan="2">${p.pboardWriter }</td>
                    <th>작성일</th>
                    <td colspan="1">${p.createDate }</td>
                </tr>
                <tr>
                    <td colspan="6">
                        <div class="thumbnail" align="center">
                            <img width="400px" height="300px" src="${pageContext.request.contextPath}${p.changeName}" alt="썸네일이미지">
                        </div>
                        <c:forEach var="p" items="${list}">
                            <div class="thumbnail" align="center">
                                <img width="400px" height="300px" src="${pageContext.request.contextPath}${p.changeName}" alt="썸네일이미지">
                            </div>
                        </c:forEach>

                    </td>
                </tr>

            </table>
            <br>

   			
   			<!-- 수정하기, 삭제하기 버튼은 이 글이 본인이 작성한 글일 경우에만 보여져야 함 -->
            <div align="center">
            	<c:if test="${loginUser.userId eq p.pboardWriter}">
	                <a class="btn btn-primary" onclick="postFormSubmit('edit')">수정하기</a>
	                <a class="btn btn-danger" onclick="postFormSubmit('delete')">삭제하기</a>
                </c:if>
            </div>
            <br><br>

            <form action="" method="GET" id="getForm">
                <input type="hidden" name="pno" value="${p.pboardNo}">
            </form>

            <script>
                function postFormSubmit(type){
                    const formEl = document.querySelector("#getForm");
                    switch(type){
                        case "edit" : {
                            //formEl.action = "updateForm.bo";
                            $(formEl).attr("action", "updateForm.pbo");
                        }break;
                        case "delete":{
                            //formEl.action = "delete.bo";
                            $(formEl).attr("action", "delete.pbo")
                        }break;
                    }
                    
                    $(formEl).submit();
                }
            </script>
           

 
            <table id="replyArea" class="table" align="center">
                <thead>
                	<c:choose>
                		<c:when test="${empty loginUser }">
		                    <tr>
		                        <th colspan="2">
		                            <textarea class="form-control" readonly cols="55" rows="2" style="resize:none; width:100%;">로그인 후 이용 가능합니다.</textarea>
		                        </th>
		                        <th style="vertical-align:middle"><button class="btn btn-secondary disabled">등록하기</button></th>
		                    </tr>
	                	</c:when>
	                    <c:otherwise>
		                    <tr> 
		                        <th colspan="2">
		                            <textarea class="form-control" id="content" cols="55" rows="2" style="resize:none; width:100%;"></textarea>
		                        </th>
		                        <th style="vertical-align:middle"><button class="btn btn-secondary" onclick="addReply();">등록하기</button></th>
		                    </tr>
	                    </c:otherwise>
                    </c:choose>
                
                    
                    
                    <tr>
                        <td colspan="3">댓글(<span id="rcount">0</span>)</td>
                    </tr>
    
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
        <br><br>
        <script>
            function init(){
                drawReplyList({refBno: ${b.boardNo}});
            }

            function addReply(){
                //댓글내용, 작성자, 게시글번호
                const boardNo = ${b.boardNo};
                const userId = "${loginUser.userId}";
                const content = document.querySelector("#content").value;

                insertReply({
                    refBno: boardNo,
                    replyWriter: userId,
                    replyContent: content
                }, drawReplyList);
            }

            function drawReplyList(data){
                getReplyList({refBno: data.refBno}, function(replyList){
                    const contentBody = document.querySelector("#replyArea tbody");
                    contentBody.innerHTML = "";

                    for(const reply of replyList){
                        const replyTr = document.createElement("tr"); //<tr></tr>
                        contentBody.appendChild(replyTr);

                        const userIdTd = document.createElement('td');
                        userIdTd.innerText = reply.user_id;
                        replyTr.appendChild(userIdTd);

                        const contentTd = document.createElement('td');
                        contentTd.innerText = reply.reply_content;
                        replyTr.appendChild(contentTd);

                        const createDateTd = document.createElement('td');
                        createDateTd.innerText = reply.create_date;
                        replyTr.appendChild(createDateTd);
                    }
                })

            }

            function getReplyList(data, callback){
                $.ajax({
                    url: "/api/board/replyList",
                    type: "GET",
                    //data.refBno 전달해서 댓글리스트를 가져오기
                    data: data,
                    success: function(res){
                        console.log(res);
                        callback(res);
                    }, error: function(){
                        console.log("조회실패");
                    }
                    //data를 이용해서 댓글 목록을 불러오고 화면에 맞게 그려주기
                });
            }

            function insertReply(data, callback){
                $.ajax({
                    url: "/api/board/reply",
                    type: "POST",
                    data: data,
                    success: function(res){
                        if(res ==="success"){
                            callback(data);
                        } else{
                            console.log("reply insert error");
                        }
                    }, error: function(){
                        console.log("reply insert ajax error");
                    }
                })
            }
        </script>
    </div>
    
    <jsp:include page="../common/footer.jsp" />
</body>
</html>