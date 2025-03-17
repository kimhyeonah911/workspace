<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <jsp:include page="common/header.jsp"/>
    <div class="content">
        <br><br>

        <div class="innerOuter">
            <h4>게시글 Top 5</h4>
            <br>
            <table id="top5-board-list" class="table table-hover" align="center">
                <thead>
                    <tr>
                        <th>글번호</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>조회수</th>
                        <th>작성일</th>
                        <th>첨부파일</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td colspan="6" rowspan="4" align="center">
                            <div class="spinner-border text-primary"></div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <br><br>
    </div>
    <script>
        //서버로부터 조회수가 높은 게시글 5개를 조회해서 가져오기(ajax)
        //tbody요소에 추가
        window.onload = function () {
            getTop5BoardList(drawBoardList);
        }

        function getTop5BoardList(callback) {
            $.ajax({
                url: "/api/board/top5",
                type: "GET",
                success: callback,
                error: function () {
                    console.log("ajax error");
                }
            })
        }

        function drawBoardList(boardList) {
            console.log(boardList);
            const contentBody = document.querySelector("#top5-board-list tbody");
            contentBody.innerHTML = "";

            for(const board of boardList){
                const boardTr = document.createElement("tr"); //<tr></tr>
                boardTr.setAttribute("onclick", "location.href='detail.bo?bno="+board.board_no+"'");
                contentBody.appendChild(boardTr);

                const boardNoTd = document.createElement('td');
                boardNoTd.innerText = board.board_no;
                boardTr.appendChild(boardNoTd);

                const boardTitleTd = document.createElement('td');
                boardTitleTd.innerText = board.board_title;
                boardTr.appendChild(boardTitleTd);

                const boardWriterTd = document.createElement('td');
                boardWriterTd.innerText = board.board_writer;
                boardTr.appendChild(boardWriterTd);

                const countTd = document.createElement('td');
                countTd.innerText = board.count;
                boardTr.appendChild(countTd);

                const createDateTd = document.createElement('td');
                createDateTd.innerText = board.create_date;
                boardTr.appendChild(createDateTd);

                const fileTd = document.createElement('td');
                if(board.origin_name){
                    fileTd.innerText ="★";
                }
                boardTr.appendChild(fileTd);

            }
        }
    </script>
    <jsp:include page="common/footer.jsp"/>
</body>
</html>
