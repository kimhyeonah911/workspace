<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<head>
  <title>Title</title>
  <style>
    .thumbnail{
      box-sizing: border-box;
      display: inline-block;
      padding: 10px;
    }

    .thumbnail:hover{
      background-color: #f5f5f5;

    }

    .thumbnail img{
      border: 1px solid gray;
    }
  </style>
</head>
<body>
  <jsp:include page="../common/header.jsp" />
  <div class="content">
    <br><br>

    <div class="innerOuter">
      <h2>사진게시판</h2>
      <br>
      <c:if test="${not empty loginUser}">
        <a class="btn btn-secondary" style="float:right;" href="PenrollForm.bo">글쓰기</a>
        <br>
      </c:if>
      <br>

      <c:forEach var="p" items="${list}">
        <div class="thumbnail" align="center" onclick="location.href='detail.pbo?pno=${p.pboardNo}'">
          <img width="200px" height="150px" src="${pageContext.request.contextPath}${p.changeName}" alt="썸네일이미지">
          <p>
            <span>${p.pboardTitle }</span><br>
            <span>${p.createDate }</span>
          </p>
        </div>
      </c:forEach>

    </div>

    <br><br>
  </div>
</body>
</html>
