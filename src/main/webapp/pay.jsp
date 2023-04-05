<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제페이지</title>
<link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">
<link href="/Zspace/css/pjh.css" rel="stylesheet">
<link href="/Zspace/css/tool.css" rel="stylesheet">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- 검색이미지 -->
﻿<link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined|
			Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp"rel="stylesheet">
</head>
<body>
<jsp:include page="sideBar.jsp" />

  <div id="contents">
	<div id="pay_header">
	  <h4 class="text-center">ORDER</h4><p>
	  <hr>
	</div>
	
	<div class="pay_ordermenu" >
	 <table class="pay_ordertable ">
	   <thead class="pay_ordertalbehead">
		 <tr height="30px" width="100%">	
		  <th width="15%"Gmain>이미지</th>
		  <th width="25%">상품정보</th>
		  <th width="10%">판매가</th>
		  <th width="10%">수량</th>
		  <th width="10%">적립금</th>
		  <th width="10%">배송구분</th>
		  <th width="10%">배송비</th>
		  <th width="10%">합계</th>
		 </tr>
	   </thead>
	   <tbody class="pay_ordertalbebody  text-center">
		 <tr height="60px">
		  <td><a href="#" class="text-center">이미지</a></td>
		  <td>${article.item_name}<br>
		   	  상품 옵션</td>
		  <td>${article.item_pay}원</td>
		  <td>${article.item_su}개</td>
		  <td>${article.mem_keep}원</td>
		  <td>기본배송</td>
		  <td>3,000원</td>
		  <td>${article.pay_amt}원</td>
		 </tr>
	   </tbody>
	   <tfoot class="pay_ordertalbefoot">
		 <tr height="30px">
		  <td class="text-right" colspan="9">
		    <div class="pay_right">
		      상품구매금액 <strong>22,900 원</strong>
		      + 배송비 <strong>3,000 원</strong>
		      - 상품할인금액 <span>0 원</span>
		      = 합계 : <strong><span>26,400</span>원</strong> &nbsp;
		    </div>
		  </td>
		 </tr>
	   </tfoot>
	 </table>
	</div>
	
	<div class="pay_back">
	  <button class="pay_prev" onclick="history.back()">이전페이지</button>
	</div>
	
	<div class="pay_tobody"><hr></div>
	  <B>배송 정보</B>
	  <hr>
	<div class="pay_bodytable">
	  <table class="pay_bodytable0">
	  <tbody class="pay_bodytable1">
	   <tr>
	     <th>배송지 선택</th>
	     <td>
	       <div class="pay_bodytable1area">
	         <input type="radio"><label>회원 정보와 동일</label>&nbsp;
	         <input type="radio"><label>새로운 배송지</label>&nbsp;
	         <span>
	           최근배송지
	           <input type="radio"><label>홍길동</label>
	         </span>
	     </div>
	    </td>
	   </tr>
	   
	   </tbody>
	   <tbody class="pay_bodytable2">
	    <tr>
	      <th>받으시는 분</th>
	      <td>
	        <div class="pay_bodytable2area">
	          <input type="text" size="15">
	        </div>
	      </td>
	    </tr>
	   </tbody>
	   <tbody class="pay_bodytable3">
	    <tr>
	      <th>주소</th>   
		  <td>
		    <div class="pay_bodytable3area"><p>
		      <input size="6" maxlength="6" type="text">
		       <span> <a href="#">우편번호</a></span>
		       <p>
		      <input size="40" readonly="1" value type="text">
		       <span class="grid">기본주소</span>
		       <p>
		      <input placeholder size="40" value type="type">
		       <span class="grid">나머지주소</span>
		    </div>
		  </td>
	    </tr>
	   </tbody>
	   <tbody class="pay_bodytable4">
	    <tr>
	      <th>일반전화</th>
	      <td>
	       <div class="pay_bodytable4area">
	         <select>
	          <option>02</option>
	          <option>031</option>
	          <option>055</option>
	         </select>
	        -
	         <input size="4" type="text">
	        -
	         <input size="4" type="text">
	       </div>
	      </td>
	    </tr>
	   </tbody>
	   <tbody class="pay_bodytable5">
	    <tr>
	      <th>휴대전화</th>
	      <td>
	       <div class="pay_bodytable5area">
	        <select>
	          <option>010</option>
	          <option>011</option>
	          <option>016</option>
	        </select>
	   		-
	   		<input size="4" type="text">
	   		-
	   		<input size="4" type="text">
	       </div>
	      </td>
	    </tr>
	   </tbody>
	   <tbody class="pay_bodytable6">
	    <tr>
	      <th>배송메세지</th>
	      <td>
	       <div class="pay_bodytable6area">
	    	<textarea maxlength="255" cols="70"></textarea>
	       </div>
	      </td>
	    </tr>
	   </tbody>
	 </table>
	</div>
	<div background-color="gray" width="50%" class="text-left pay_totalpayleft">
	  <b>결제 수단</b>
	</div>
	
	<div background-color="gray" width="50%" class="text-right pay_totalpayright">
	  <b>결제 예정 금액</b>
	</div>
	
	<div class="pay_bottomleft"><p>
	  <input type="radio" name="pay">카드 결제<p>
	  <input type="radio" name="pay">휴대폰 결제<p>
	  <input type="radio" name="pay">무통장 입금<p>
	  <input type="radio" name="pay">삼성 페이<p>
	</div>
	
	<div class="pay_bottomright">
	 <div>
	  <div><b>총 주문 금액</b><p> 26000원</div>
	  <div><b>총 할인</b><p> -3000원</div>
	  <div><b>총 결제예정 금액</b><p> =23000원<p></div>
	 </div> 
	</div>
	<div class="pay_last">
 	 <div>
	  <b>총 결제 금액</b><p>
	    23000원<p>
	  <button>결제하기</button>
	 </div>
	</div>
  </div> <!-- <div id="pay_contents"> -->

<!-- footer구역 -->
	<footer class="footer">
		<div class="footerdiv" >
			공간의 집 | KIC | 012-345-6789 | 주소 : 서울특별시 강남구<p><p>
	  		사업자번호 011-22-345678 | ourhome@aaa.com <p>				
		</div>
	</footer>

<!-- -------------------------------------------------------------------------------------------------------------------------------------------------------- -->




	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="/Zspace/js/bootstrap.min.js"></script>

</body>
</html>