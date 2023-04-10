<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.*, java.util.*"%>
<%-- <meta http-equiv="Refresh" content="0;url=/Zspace/orderList.do?mem_id=${mem_id}"> --%>
<%String  mem_id = (String)session.getAttribute("mem_id"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>

  <div id="contents">
	  <h1>주문이 완료되었습니다.</h1>
	  <a href="/Zspace/orderList.do?mem_id=${mem_id}">주문확인</a>
  </div>

</body>
</html>