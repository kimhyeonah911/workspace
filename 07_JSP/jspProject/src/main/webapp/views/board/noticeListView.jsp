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

    .list-area{
        border: 1px solid rgb(156, 156, 156);
        text-align: center;
    }

    .list-area tbody tr:hover{
        background: rgb(253, 248, 255);
        cursor: pointer;
        font-size: 18px;
    }

    .list-area td, .list-area th{
        border: 1px solid rgb(156, 156, 156);
    }

    .btn-primary{
        background: white;
    }

</style>
</head>
<body>
    <%@ include file="../common/menubar.jsp" %>
    
    <div class="outer table-responsive">
        <br>
        <h2 align="center">공지사항</h2>
        <br>

        <table align="center" class="list-area table-sm">
            <thead>
                <th width="70">글번호</th>
                <th width="200">제목</th>
                <th width="100">작성자</th>
                <th width="70">조회수</th>
                <th width="100">작성일</th>
            </thead>
            <tbody>
            	<c:forEach var="n" items="${list}">
                <tr>
                    <td>${n.noticeNo}</td>
                    <td>${n.noticeTitle}</td>
                    <td>${n.userId}</td>
                    <td>${n.count}</td>
                    <td>${n.createDate}</td>
                </tr>
                </c:forEach>
            </tbody>
        </table>

        <br><br>

        <div align="center">
        	<c:if test="${pi.currentPage > 1 }">
            	<button class="btn btn-sm btn-basic" onclick="location.href='${pageContext.request.contextPath}/list.no?cpage=${pi.currentPage-1 }'">&lt;이전</button>
            </c:if>
            
            <c:forEach var="p" begin="${pi.startPage}" end="${pi.endPage }">
            	<c:choose>
            		<c:when test="${p==pi.currentPage }">
            			<button class="btn btn-sm btn-basic" disabled></button>
            		</c:when>
            		<c:otherwise>
            			<button class="btn btn-sm btn-basic" onclick="location.href='${pageContext.request.contextPath}/list.no?cpage=${p }'">${p }</button>
            		</c:otherwise>
            	</c:choose>
            </c:forEach>
            
            <c:if test="${pi.currentPage < pi.maxPage }">
            	<button class="btn btn-sm btn-basic" onclick="location.href='${pageContext.request.contextPath}/list.no?cpage=${pi.currentPage+1 }'">다음&gt;</button>
            </c:if>
        </div>
    </div>
</body>
</html>