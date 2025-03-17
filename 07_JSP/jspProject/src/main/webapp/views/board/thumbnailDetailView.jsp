<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<style>
    .outer{
		background: rgb(251, 242, 255);
		color: rgb(156, 156, 156);
	    width: 1000px;
	    margin: auto;
	    margin-top: 50px;
	    padding: 10px 0px 50px;
    }

    .outer table{
        border: 1px solid rgb(156, 156, 156);
    }

    .outer table th, .outer table td{
        border: 1px solid rgb(156, 156, 156);
    }

    .outer > form input, .outer > form textarea{
        width: 100%;
        height: 100%;
        box-sizing: border-box;
    }

    .outer img:hover{
        cursor: pointer;
        background: rgb(253, 247, 255);
    }
</style>
</head>
<body>
    <%@ include file="../common/menubar.jsp" %>
    <div class="outer">
        <br>
        <h2 align="center">사진게시글 상세보기</h2>
        <br>
            <table border="1" align="center">
                <tr>
                    <th>제목</th>
                    <td colspan="3">
                        ${b.boardTitle}
                    </td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td>${b.userId}</td>
                    <th>작성일</th>
                    <td>${b.createDate}</td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td colspan="3">
                        ${b.boardContent}
                    </td>
                </tr>
                <tr>
                    <th>대표이미지</th>
                    <td colspan="3">
                        <img id="thumbnail-img" width="250" height="170" src="${pageContext.request.contextPath}/${list[0].filePath}${list[0].changeName}">
                    </td>
                </tr>
                <tr>
                    <th>상세이미지</th>
                    <c:forEach var="at" items="${list}">
                        <c:if test="${at.fileLevel == 2}">
                            <td><img id="content-img1" width="150" height="120" src="${pageContext.request.contextPath}/${at.filePath}${at.changeName}"></td>
                        </c:if>
                    </c:forEach>
                </tr>
            </table>

            <br>

            <div align="center">
                <a href="${pageContext.request.contextPath}/list.th" class="btn btn-sm btn-basic">목록가기</ㅁ>
            </div>

    </div>
</body>
</html>