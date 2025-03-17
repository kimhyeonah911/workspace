<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
<style>
	#enrollForm>table {width:100%;}
	#enrollForm>table * {margin:5px;}
</style>
</head>
<body>

	<jsp:include page="../common/header.jsp" />

    <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>게시글 작성하기</h2>
            <br>

            <form id="enrollForm" method="post" action="Pinsert.bo" enctype="multipart/form-data">
                <table align="center">
                    <tr>
                        <th><label for="title">제목</label></th>
                        <td colspan="3"><input type="text" id="title" class="form-control" name="PboardTitle" required></td>
                    </tr>
                    <tr>
                        <th><label for="writer">작성자</label></th>
                        <td colspan="3"><input type="text" id="writer" class="form-control" value="${loginUser.userId }" name="PboardWriter" readonly></td>
                    </tr>
                    <tr>
                        <th><label for="thumbnail-img">대표이미지</label></th>
                        <td colspan="3"><img id="thumbnail-img" width="250" height="170" onclick="chooseFile('file1')"></td>
                    </tr>
                    <tr>
                        <th>상세이미지</th>
                        <td>
                            <img id="content-img1" width="150" height="120" onclick="chooseFile('file2')">
                            <img id="content-img2" width="150" height="120" onclick="chooseFile('file3')">
                            <img id="content-img3" width="150" height="120" onclick="chooseFile('file4')">
                        </td>
                    </tr>
                </table>
                <div style="display: none;">
                    <input type="file" name="upfile" id="file1" required onchange="loadImg(this, '#thumbnail-img')">
                    <input type="file" name="upfile" id="file2" onchange="loadImg(this, '#content-img1')">
                    <input type="file" name="upfile" id="file3" onchange="loadImg(this, '#content-img2')">
                    <input type="file" name="upfile" id="file4" onchange="loadImg(this, '#content-img3')">
                </div>
                <br>

                <script>
                    function chooseFile(Selector){
                        const fileInput = document.querySelector("#" + Selector);
                        fileInput.click();
                    }

                    function loadImg(changeInput, targetImg){
                        //파일객체 -> files -> 선택된 파일들이 담겨있음
                        console.log(changeInput.files[0]);
                        const img = document.querySelector(targetImg);
                        console.log(img);

                        if(changeInput.files.length > 0){ //파일을 선택했을 때
                            //파일을 읽어들일 객체
                            const reader = new FileReader();

                            //해당 파일을 읽어들여 해당 파일만의 고유한 url을 부여
                            //url : Base64로 인코딩된 데이터 url(파일을 실제로 표현하는 형식인 바이너리 코드를 텍스트문자열로 인코딩한 방식)
                            reader.readAsDataURL(changeInput.files[0]);

                            //파일 읽어드리기를 완료했을 때 이벤트핸들러를 실행
                            reader.onload = function(ev){
                                img.src = ev.target.result; //이미지 요소에 불러온 파일의 url을 넣어준다.
                            }
                        } else{ //파일이 있었는데 선택 후 취소했을 때
                            img.src = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSBu4muHCL76F9_n9pAp9mYQ4DQQxPDvFrqUg&s";
                        }
                    }
                </script>

                <div align="center">
                    <button type="submit" class="btn btn-primary">등록하기</button>
                    <button type="reset" class="btn btn-danger">취소하기</button>
                </div>
            </form>
        </div>
        <br><br>
 
    </div>
    
    <jsp:include page="../common/footer.jsp" />

</body>
</html>