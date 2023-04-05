<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>상품검색페이지</title>
<link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">
<link href="/Zspace/css/kim.css" rel="stylesheet">
<link href="/Zspace/css/ssj.css" rel="stylesheet">
<link href="/Zspace/css/asj.css" rel="stylesheet">﻿
<link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined|
			Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp"rel="stylesheet">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<% int Book_count=0; %>
</script>
</head>
<body>
  <div id="leftbar"> <!-- 전체 사이드바 구역 -->
    <div class="logo"> <!-- 로고 -->
	  <a href="/Zspace/mainpage/MainPage.jsp"><img src="/Zspace/icon/rogo.png"></a>
	</div> <!-- 로고 -->
	
	<div class="menu_wrap"> <!-- 로고제외 사이드 바 내용 -->
	  <table class="menu_group"> <!-- 로그인, 마이페이지 -->
	   <tr>				
		<th align="center">
		  <a href="/Zspace/FRONT/login.jsp">로그인</a>  | 
		  <a href="/Zspace/FRONT/mem_join.jsp">회원가입</a> 
		  <p>
		  <a href="#cart_num.do?type=">장바구니</a> | 
		  <a href="#.wish_list.do?type=">관심상품</a> | 
		  <a href="#.mypage.do?type=">마이페이지</a>
		</th>	
	   </tr>
	  </table><!-- 로그인, 마이페이지 -->
	  
	  <!-- 검색창 -->				
	  <div class="search_menu">		      
		<input type="text" name="searchtext">
		   <i class="material-icons"><a href="/Zspace/search.do">search</a> 
		   </i>		 
	  </div>
	  
	  
	  <div class="side_list"> <!-- 사이드바 내 카테고리, 게시판 영역 -->
	    <div class=" side_wrap"> <!-- 사이드바 내 좌측 카테고리 영역-->
		  <div class="menu_cell">
			<label for="ch1"><a href="#.DECORATION">데코</a></label>
			<ul class="sub_menu">
			  <li><a href="#Lamp">조명</a></li>
			  <li><a href="#Frame">액자</a></li>
			  <li><a href="#.Candle">캔들</a></li>
			  <li><a href="#.Flower">식물/조화</a></li>
			</ul>
		  </div>
		  <div class="menu_cell">
			<label for="ch2"><a href="#.Furniture">가구</a></label>
			<ul class="sub_menu">						
			  <li><a href="#.Chair">의자</a></li>
			  <li><a href="#.Table">테이블</a></li>
			  <li><a href="#.Dresser">서랍장</a></li>
			</ul>
		  </div>
		  <div class="menu_cell">
		    <label for="ch3"><a href="#.FABRIC"> 패브릭</a></label>
			<ul class="sub_menu">
			  <li><a href="#.rug">러그</a></li>
			  <li><a href="#.blind">블라인드</a></li>
			  <li><a href="#.blanket">이불</a></li>
			</ul>
		  </div>
  		  <div class="menu_cell">
			<label for="ch4"><a href="#.KITCHEN">주방</a></label>
			<ul class="sub_menu">
			  <li><a href="#.Pantry">팬트리</a></li>
			  <li><a href="#.Cooking">조리도구</a></li>
			  <li><a href="#.dishes">식기</a></li>
			</ul>
		  </div>
		</div> <!-- 사이드바 내 좌측 카테고리 영역-->
		
		<div class="side_wrap"><!-- 사이드바 내 우측 게시판 영역-->
		  <div class="menu_cell">
			<label for="ch6"><a href="#FAQ">공지사항</a></label>						
		  </div>
		  <div class="menu_cell">
			<label for="ch8"><a href="#.EVENT">이벤트</a></label>		
		  </div>
		  <div class="menu_cell">
			<label for="ch3"><a href="#.FABRIC">게시판</a></label>
			<ul class="sub_menu">
			  <li><a href="/Zspace/list.do">질문게시판</a></li>
			  <li><a href="#.REVIEW ">리뷰게시판</a></li>
			  <li><a href="#.CANCEL">취소게시판</a></li>
			  <li><a href="#.">커뮤니티</a></li>
			</ul>
		  </div>
		</div><!-- 사이드바 내 우측 카테고리-->
 	  </div> <!-- <div class="side_list"> 사이드바 내 카테고리, 게시판 영역 --> 
	  		
	  <div class="slid_footer" style="white-space:pre"> <!-- 사이드 내 하단 -->
<b>TEL</b> | 012-345-6789
<b>영업시간</b> | AM 10:00 - PM 17:00 
		공휴일.토.일 휴무
<span>
<b>BANK INFO</b>
<b>예금주</b> | (주)공간의집<br>
<b>농협</b> | 111-0303-0333-33
<b>신한</b> | 144-144-333333
<b>국민</b> | 555555-44-445555
<b>하나</b> | 281-919999-99999
</span>
	  </div> <!-- 사이드 내 하단 -->
    </div><!-- <div class="menu_wrap"> 로고제외 사이드바 내용 -->
  </div><!-- 전체 사이드바 구역 -->


  <div id="contents"><!-- 메인 -->
   <div><h1>상품 검색</h1></div>
  	 <div margin-bottom="40px"> <!-- 검색 form -->
  	   <form name="searchfm" action="/Zspace/search.do">
	    <table class="item_search_tb" align="center"  width="500px" height="200px">
	      <tr height="40px" valign="bottom">
	        <td width="120px" align="right">상품분류</td>
	        <td class="choose" colspan="2" align="left" width="380px" > 
	          <select name="search" style="width:160px">
	        	<option value="choose" selected><b>카테고리</b></option>
         		<option value="category_menu">데코</option>
      	 		<option value="category_menu">가구</option>
      	 		<option value="category_menu">패브릭</option>
      	 		<option value="category_menu">주방</option>
			  </select>
			</td>
	      </tr>
	      <tr height="20px"></tr>
	      <tr height="40px" valign="top">
	        <td width="120px" align="right">상품명</td>
	        <td class="choose" colspan="2" width="380px">
	          <input type="text" name="searchtext" width="120px" placeholder="검색어를 입력하세요.">&nbsp;
	          <button type="submit" class="btn btn-secondary">검색</button>
	        </td>
	      </tr>
	    </table>
	   </form>
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
	<div margin-top="100px">
	 <!-- <div>
		<a href="#">정확도순</a> | <a href="#">낮은가격순</a> | <a href="#">평점높은순</a>
	  </div>  --> 
	  <p>
	  <table class="table  table-hover ">
		<%--<% if(Book_count ==0) {--%> 
	    <colgroup>
		  <col width=15%>
		  <col width=70%>
		  <col width=15%>
	    </colgroup>
	    <tr class="text-center">
		  <td>이미지</td>
		  <td>상품명</td>
		  <td>가 격</td>
		</tr>
		<tr class="text-center" valign="middle">
		  <div>
			<td><a href="#" class="itemSerch_imgline">
			   <img class="itemSerch_img" src="/Zspace/project/lamp/1lamp(1).jpg"></a>
		    </td>
			<td align="left"><a href="/Zspace/serarch.do?category_menu=${item.category_menu}&item_num=${item.item_num}" style="text-style:none">${item.item_name}</a></td>
			<td>${item.item_pay}</td>
		  </div>
		</tr>
	  </table>
	</div>
	<div class="page" >
	  <a href="#" aria-hidden="true" role="button">이전</a>
	  <a href="#" aria-current="true" role="menuitem">1</a>
	  <a href="#" aria-hidden="true" role="button">다음</a>
	</div>
			<!-- </div> -->
		</div>
		<!-- 전체컨테이너 -->
  <footer class="footer"> <!-- footer구역 -->
	<div class="footerdiv" >
	  공간의 집 | KIC | 012-345-6789 | 주소 : 서울특별시 강남구<p>
	  사업자번호 011-22-345678 | ourhome@aaa.com <p>			
	</div>
  </footer>
		<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
		<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
		<script src="../js/bootstrap.min.js"></script>
</body>
</html>
