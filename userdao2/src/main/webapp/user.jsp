<%--<%@ page import="kr.ac.jejunu.UserDao" %>--%>
<%--<%@ page import="org.springframework.context.ApplicationContext" %>--%>
<%--<%@ page import="org.springframework.context.annotation.AnnotationConfigApplicationContext" %>--%>
<%--<%@ page import="kr.ac.jejunu.User" %>--%>
<%--<%@ page contentType="text/html; charset=UTF-8" %>--%>
<%--<%--%>
<%--    ApplicationContext applicationContext = new AnnotationConfigApplicationContext("kr.ac.jejunu");--%>
<%--    UserDao userDao = applicationContext.getBean("userDao", UserDao.class);--%>
<%--    User user = userDao.findById(1);--%>
<%--%>--%>
<%--<html>--%>
<%--<h1>--%>
<%--    Hello hello hi <%=user.getName()%>!!!!!--%>
<%--</h1>--%>
<%--</html>--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>글쓰기</title>
    <style>
        @import url(http://fonts.googleapis.com/earlyaccess/nanumgothic.css);
        body {
            font-family: 'Nanum Gothic', sans-serif;
        }
    </style>

    <script type="text/javascript">
        function move(url) {
            location.href=url;
        }
        function boardWriteCheck() {
            var form = document.BoardWriteForm;
            return true;
        }
    </script>
</head>

<body>

<table summary="글쓰기 전체 테이블">
    <form name="BoardWriteForm" method="post" action="Board_Write_action.jsp"
          onsubmit="return boardWriteCheck();" >

        <colgroup>
            <col width="20%">
            <col width="80%">
        </colgroup>


        <table summary="테이블 구성" >
            <caption>게시판 글쓰기</caption>
            <tr>
                <td>작성자</td>
                <td><input type=text name=name size=10 maxlength=8></td>
            </tr>
            <tr>
                <td>E-Mail</td>
                <td><input type=text name=email size=30></td>
            </tr>
            <tr>
                <td>홈페이지</td>
                <td><input type=text name=homepage size=30></td>
            </tr>
            <tr>
                <td>제 목</td>
                <td><input type=text name=title></td>
            </tr>
            <tr>
                <td>내 용</td>
                <td><textarea name=content rows ="10" cols="100"></textarea></td>
            </tr>
            <tr>
                <td>비밀번호</td>
                <td><input type=password name=password size=15 maxlength=15></td>
            </tr>
            <tr>
                <td colspan=2><hr size=1></td>
            </tr>
            <tr>
                <td colspan="2"><div align="center">
                    <input type="submit" value="등록" >&nbsp;&nbsp;
                    <input type="button" value="뒤로" onclick="move('Board_List.jsp');"></div>
                </td>
            </tr>
        </table>
    </form>
</table>

</body>
</html>