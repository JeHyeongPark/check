<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="action.*"%>
<!DOCTYPE html>
<html>
<head>
<title>글 수정하기</title>
<link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">
<link href="/Zspace/css/jyh.css" rel="stylesheet">
<link href="/Zspace/css/tool.css" rel="stylesheet">
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>

<body>
		<jsp:include page="sideBar.jsp" />    
		
<div class="main">
	<h2>글 수정하기</h2>
	<hr>
	<form method="post" name="updateform5" action="/Zspace/updatePro5.do"> <!-- 0327.content5.do 아니고 수정하는 action으로 넘어가게 -->
		<input type="hidden" name="post_num" value="${article.post_num}">
		<input type="hidden" name="pageNum" value="${pageNum}"> 
		<input type="hidden" name="mem_id" value="${mem_id}"> 
		<input type="text" name="post_title" class="form-control mt-4 mb-2"
			value="${article.post_title}">
			
		<div class="form-group"> 
		<textarea class="form-control" rows="10" name="post_cnt" cols="60" >${article.post_cnt}</textarea>			
		</div>
	
		<button type="submit"
			class="btn btn-secondary mb-3 pull-right write_reg" 
			>글 수정</button>
			
	</form>
<button type="submit"
	class="btn btn-secondary mb-3 pull-right write_cancel"
	onclick="location.href='/Zspace/content5.do? pageNum=${pageNum}&pageNum=${pageNum}'">취소</button>

</div>	<!-- main -->
		
<!-- footer구역 -->
		<jsp:include page="footer.jsp" />    
	

</body>
</html>      
