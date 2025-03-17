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

    .list-area td, .list-area th{
        border: 1px solid rgb(156, 156, 156);
    }

	.list-area select, .list-area input, .list-area textarea{
		width: 100%;
		box-sizing: border-box;
		background: white;
		border: 1px solid rgb(156, 156, 156);
	}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>

	<div class="outer">
		<br>
		<h2 align="center">일반게시글 수정하기</h2>
		<br>

		<form action="${pageContext.request.contextPath}/update.bo" method="POST" enctype="multipart/form-data">
			<table align="center" class="list-area">
				<input type="hidden" name="bno" value="${b.boardNo}">
				<tr>
					<th width="70">카테고리</th>
					<td width="500">
						<select name="category" class="form-select">
							<c:forEach var="c" items="${list}">
								<c:choose>
									<c:when test="${c.categoryNo== b.categoryNo}">
										<option value="${c.categoryNo}" selected>${c.categoryName}</option>
									</c:when>
									<c:otherwise>
										<option value="${c.categoryNo}">${c.categoryName}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" class="form-control" name="title" required value="${b.boardTitle}"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea name="content" class="form-control" rows="10" style="resize: none;">${b.boardContent}</textarea></td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td>
						<c:if test="${at != null}">
							${at.originName}
							<input type="hidden" name="originFileNo" value="${at.fileNo}">
						</c:if>
						<input type="file" class="form-control" name="upfile">
					</td>
				</tr>
			</table>

			<br>

			<div align="center">
				<button type="submit" class="btn btn-sm btn-basic">수정하기</button>
				<button type="reset" class="btn btn-sm btn-basic">취소하기</button>
			</div>
		</form>
	</div>
</body>
</html>