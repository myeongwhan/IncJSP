<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시판보기</title>
<link rel="stylesheet" href="/clsProj/css/w3.css">
<style>
    .mxw{max-width: 1200px;}
    .bnone{display: none;}
</style>
<script type="text/javascript" src="/clsProj/js/jquery-3.5.0.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#write').click(function(){
			$(location).attr('href', '/clsProj/board/boardWrite.cls');
		});
		
		$('#text').click(function(){
			$(location).attr('href', '/clsProj/board/boardDetail.cls');
		})
	});
</script>
</head>
<body>
    <div class="w3-content mxw">
        <div class="w3-center w3-col ">
            <h2>인크레파스 게시판</h2>
        </div>
        <div class="w3-rwo w3-margin-top">
            <div class="w3-col w3-padding w3-right-align w3-margin">
            	<div class="w3-button w3-red" id="write">글쓰기</div>
            </div>
            <table class="w3-col w3-table-all w3-hoverable">
              <thead>
                <tr class="w3-light-grey">
                  <th>글번호</th>
                  <th>제목</th>
                  <th>작성자</th>
                  <th>작성일</th>
                  <th>조회수</th>
                </tr>
              </thead>
              <c:forEach var="data" items="${LIST }">
	              <!-- ì¬ê¸°ì ë°ë³µë¬¸ì ìì±íì¸ì-->
	              <tr class="w3-text-gray" id="text">
	                <td>${data.bno }</td>
	                <!-- 1 -->
	                <td>${data.title }</td>
	                <!-- 제목입니다 -->
	                <td>${data.id }</td>
	                <!-- 홍길동 -->
	                <td>${data.sdate }</td>
	                <!-- 2020/05/20 -->
	                <td>${data.click }</td>
	                <!-- 0 -->
	              </tr>
              </c:forEach>
              <!--ì¬ê¸°ê¹ì§-->
            </table>
        </div>
      </div>
</body>
</html>