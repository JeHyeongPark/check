<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
String mem_id = (String) session.getAttribute("mem_id");
%>
<!DOCTYPE html>
<html lang="ko">
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">
<link href="/Zspace/css/tool.css" rel="stylesheet">
<link href="/Zspace/css/bgh.css" rel="stylesheet">

<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script>
	$(document).ready(function() {
		$("#cbx_chkAll").click(function() {
			if ($("#cbx_chkAll").is(":checked"))
				$("input[name=chk]").prop("checked", true);
			else
				$("input[name=chk]").prop("checked", false);
		});
		$("input[name=chk]").click(function() {
			var total = $("input[name=chk]").length;
			var checked = $("input[name=chk]:checked").length;
			if (total != checked)
				$("#cbx_chkAll").prop("checked", false);
			else
				$("#cbx_chkAll").prop("checked", true);
		});
	});
</script>
</head>
<body>
	<jsp:include page="sideBar.jsp" />

	<div id="contents">
		<!-- 장바구니 이름-->
		<div class="cart_num_sangpum">
			<h2>장바구니</h2>
			<p>(일반상품)</p>
			<hr />
		</div>
		<br>
		<!-- 테이블표-->
		<div class="cart_num_main">
			<table border="1" summary="">
				<colgroup>
					<col width="4%" />
					<col width="5%" />
					<col width="29%" />
					<col width="9%" />
					<col width="6%" />
					<col width="10%" />
					<col width="10%" />
					<col width="15%" />
				</colgroup>
				<tr>
					<!-- 테이블표 상단에 색상을 #f4efe1;-->
				<thead class="cart_num_color">
					<tr>
						<th class="text-center"><input type="checkbox"
							id="cbx_chkAll" /></th>
						<th scope="col" class="text-center">이미지</th>
						<th scope="col" class="text-center">상품정보</th>
						<th scope="col" class="text-center">판매가</th>
						<th scope="col" class="text-center">수량</th>
						<th scope="col" class="text-center">배송구분</th>
						<th scope="col" class="text-center">합계</th>
						<th scope="col" class="text-center">선택</th>
					</tr>
				</thead>
				<c:forEach var="cart" items="${cartlist}">
					<tbody class="text-center"
						class="xans-element- xans-myshop xans-myshop-wishlistitem">
						<tr class="xans-record-">
							<th class="text-center"><input type="checkbox" name="chk">
							<td style="line-height: 0"><img
								src="${cart.item_img}(1).jpg" width="100" alt="#" /></td>
							<td>${cart.item_name }</td>
							<td><fmt:formatNumber value="${cart.item_pay}"
									pattern="#,###" /></td>
							<td>1</td>
							<td>기본배송</td>
							<td><c:set var="sum" value="0" /> <c:forEach var="cartlist"
									items="${cartlist}">
									<c:set var="sum" value="${cart.item_pay}" />
								</c:forEach> <fmt:formatNumber value="${sum}" pattern="#,###" /></td>
							<td><input type="button" style="width: 80px; height: 25px"
								value="주문하기"
								onclick="location.href='/Zspace/order.do?item_num=${cart.item_num}&mem_id=${mem_id }'">

								<input type="button" style="width: 80px; height: 25px"
								value="삭제하기"
								onclick="location.href='/Zspace/cartDelete.do?item_num=${cart.item_num}&mem_id=${mem_id}'">
							</td>
						</tr>
					</tbody>
				</c:forEach>
			</table>
		</div>
		<br>
		<!--  상품 주문하기,삭제하기 부분  -->
		<div class="cart_num_ec-base-button ">
			<button type="submit" style="width: 135px; height: 30px" value=""
			onclick="location.href='/Zspace/orderAll.do?item_num=${cart.item_num}&mem_id=${mem_id }'">전체상품주문하기</button>
			</form>
			<button type="button" style="width: 100px; height: 30px" value=""
				onclick="location.href='/Zspace/cartAllDelete.do?item_num=${cart.item_num}&mem_id=${mem_id}'">전체삭제하기</button>
		</div>
		<!--총 금액부분-->
		<div class="cart_num_gBorder">
			<table border="1" summary="">
				<colgroup>
					<col style="width: 220px;" />
					<col style="width: 250px;" class="displaynone" />
					<col style="width: 250px;" />
				</colgroup>
				<thead>
					<tr>
						<th scope="col" class="text-center">총 상품금액</th>
						<th scope="col" class="text-center">배송비</th>
						<th scope="col" class="text-center">결제금액</th>
					</tr>
				</thead>
				<thead>
					<!--  
					<c:set var="total" value="${sum}" />
					<c:set var="pay_total" value="${total + 3000}" />
					<tr>
						<td class="text-center"><fmt:formatNumber value="${ total}"
								pattern="#,###" /></td>
						<td class="text-center" >+3.000</td>
						<td class="text-center"><fmt:formatNumber
								value="${ pay_total}" pattern="#,###" /></td>
								-->
					<tr>
						<td><c:set var="sum" value="0"/>
						      <c:forEach var="cart" items="${cartlist}"> 
						      <c:set var="sum" value="${sum+(cart.item_pay)}"/> </c:forEach>  
						      <fmt:formatNumber value="${sum}" pattern="#,###"/>
						      </td>
						<td>3,000</td>
						 <td><c:set var="sum" value="0"/>
                              <c:forEach var="cart" items="${cartlist}"> 
                              <c:set var="sum" value="${sum+(cart.item_pay)}"/> 
                              </c:forEach>  
                              <fmt:formatNumber value="${sum+3000}" pattern="#,###"/> 
                              </td>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<jsp:include page="/footer.jsp" />
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</body>
</html>