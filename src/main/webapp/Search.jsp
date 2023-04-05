<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>상품검색페이지</title>
<link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">
<link href="/Zspace/css/tool.css" rel="stylesheet">
<link href="/Zspace/css/ssj.css" rel="stylesheet">
<link href="/Zspace/css/asj.css" rel="stylesheet">﻿
<link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined|
			Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp"rel="stylesheet">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(function() {
  // select 요소와 option 요소들을 가져옵니다.
  const categorySelect = $('#search');
  const options = categorySelect.find('option');

  // select 요소의 값이 변경될 때마다 실행되는 이벤트 핸들러 함수를 작성합니다.
  categorySelect.change(function() {
    // 선택된 옵션의 값을 가져옵니다.
    const selectedValue = options.filter(':selected').val();
    // 선택된 옵션 값에 따라서 category_menu 변수를 설정합니다.
    let category_num;
    switch (selectedValue) {
      case '1': category_num = 1;
        break;
      case '2': category_num = 2;
        break;
      case '3': category_num = 3;
        break;
      case '4': category_num = 4;
        break;
      default:
        category_menu = '';
        break;
    }
});
</script>
</head>
<body>
<jsp:include page="sideBar.jsp" />

  <div id="contents"><!-- 메인 -->
   <div><h1>상품 검색</h1></div>
  	 <div margin-bottom="40px"> <!-- 검색 form -->
  	   <form name="searchfm" action="/Zspace/search.do">
	    <table class="item_search_tb" valign="center"  width="500px" height="200px">
	      <tr height="40px" valign="bottom">
	        <td width="120px" align="right">상품분류</td>
	        <td class="choose" colspan="2" align="left" width="380px" > 
	          <select name="search" style="width:160px">
	        	<option value="choose" selected><b>카테고리</b></option>
         		<option value='1' >데코</option>
      	 		<option value='2' >가구</option>
      	 		<option value='3' >패브릭</option>
      	 		<option value='4' >주방</option>
			  </select>
			</td>
	      </tr>
	      <tr height="40px" valign="top">
	        <td width="120px" align="right">상품명</td>
	        <td class="choose" colspan="2" width="380px">
	          <input type="text" name="searchtext" width="120px" placeholder="검색어를 입력하세요.">&nbsp;
	          <button type="submit" class="btn btn-secondary">검색</button>
	          <!-- onclick="location.href='/Zspace/search.do'" -->
	          
	        </td>
	      </tr>
	    </table>
	  </div> <p> <!-- 검색 form -->
	<div>
	  <div class="post_header">
		<h3>${item.category_menu}</h3>
		<!-- <div class="color">
			<a href="#" class="btn btn-default btn-xs" role="button">무료배송</a>
			<a href="#" class="btn btn-default btn-xs" role="button">배송유형</a>
			<a href="#" class="btn btn-default btn-xs" role="button">가격대</a>
		</div> -->
	  </div>
	</div>
	<hr>
	<!-- 게시글 영역 -->
	<!-- <div class="row"> -->
	
	<div margin-top="100px"> <!-- 검색 내용 출력 테이블 -->
		 <!-- <div>
			<a href="#">정확도순</a> | <a href="#">낮은가격순</a> | <a href="#">평점높은순</a>
		  </div>  --> 
	 <p>
  	 <c:if test="${pgList.count>0}">
	  <table class="table">
		<%--<% if(Book_count ==0) {--%> 
	    <colgroup>
		  <col width=20%>
		  <col width=65%>
		  <col width=15%>
	    </colgroup>
	    <tr >
		  <th class="text-center">이미지</th>
		  <th class="text-center">상품명</th>
		  <th class="text-center">가 격</th>
		</tr>
		<c:forEach var="item" items="${itemList}">
		<tr class="text-center table-hover" valign="middle">
		  <td>${item.item_img}</td>
				<!-- 위랑 바꾸기
				  <td><a href="#" class="itemSerch_imgline">
				    <img class="itemSerch_img" src=""></a>
			      </td> -->
		  <td align="left">${item.item_name}</td>
			  <!-- 위에 추가하기 <a href="/Zspace/search.do?category_menu=${item.category_menu}&item_num=${item.item_num}" style="text-style:none"> -->
		  <td>${item.item_pay}</td>
		</tr>
		</c:forEach>
	  </table>	
	 </c:if>
	</div>
	
	   </form>
	<!--<div class="page"> <!-- 페이징 처리 
	 <c:if test="${pgList.startPage>pgList.blockSize}">
      <a href="/Zspace/search.do?pageNum=${pgList.startPage-pgList.blockSize}&search=${search}&searchtext=${searchtext}">[이전]</a>
     </c:if>
     <c:forEach var="i" begin="${pgList.startPage}" end="${pgList.endPage}">
       <a href="/Zspace/search.do?pageNum=${i}&search=${search}&searchtext=${searchtext}">
         <c:if test="${pgList.currentPage==i}">
           <font color="red"><b>[${i}]</b></font>
         </c:if>
       </a>
     </c:forEach>
     <c:if test="${pgList.endPage<pgList.pageCount}">
       <a href="/Zspace/search.do?pageNum=${pgList.startPage+pgList.blockSize}&search=${search}&searchtext=${searchtext}">[다음]</a>
     </c:if>
	</div>  페이징 처리 -->	
  </div><!-- 전체컨테이너 -->

<jsp:include page="footer.jsp" />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="Zspace/js/bootstrap.min.js"></script>
</body>
</html>