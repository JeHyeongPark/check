<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">
<link href="/Zspace/css/tool.css" rel="stylesheet">
<link href="/Zspace/css/pjh.css" rel="stylesheet">

</head>
<body>
<jsp:include page="sideBar.jsp" />  

<div id="contents">
    <h4>마이페이지</h4><p>
	<hr>


	<div class="myPage_order">
	<a href="#">
	  <h4>
	    <strong>ORDER</strong>
	  </h4>
	  주문하신 상품의 주문내역을 확인하실 수 있습니다.
	</a>
	</div>
	
	<div class="myPage_profile">
	<a href="#">
	  <h4>
	    <strong>PROFILE</strong>
	  </h4>
	  회원이신 고객님의 개인정보를 관리하는 공간입니다.
	</a>
	</div>
	
    <div class="myPage_cart">
	<a href="#">
	  <h4>
	    <strong>CART</strong>
	  </h4>
	  장바구니에 들어있는 상품의 목록을 보여드립니다. 
	</a>
	</div>
	
	<div class="myPage_wishlist">
	<a href="#">
	  <h4>
	    <strong>WISHLIST</strong>
	  </h4>
	  관심상품으로 등록하신 상품의 목록을 보여드립니다.
	</a>
	</div>
	
	<div class="myPage_point">
	<a href="#">
	  <h4>
	    <strong>적립금</strong>
	  </h4>
	  적립금은 상품 구매시 사용하실 수 있습니다.
	</a>
	</div>
	
	<div class="myPage_post">
	<a href="#">
	  <h4>
	    <strong>POST</strong>
	  </h4>
	  고객님께서 작성하신 게시물을 관리하는 공간입니다.
	</a>
	</div>
</div>




<jsp:include page="footer.jsp" />  

<!-- -------------------------------------------------------------------------------------------------------------------------------------------------------- -->




	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="/Zspace/js/bootstrap.min.js"></script>
</body>
</html>