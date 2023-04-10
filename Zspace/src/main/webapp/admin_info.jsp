<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보</title>
<link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/Zspace/css/sidebar.css">
<link rel="stylesheet" type="text/css" href="/Zspace/css/silde.css">
<link rel="stylesheet" type="text/css" href="/Zspace/css/mine.css">


</head>
<body>
<!-- 사이드바영역 03.12  -->
<!-- 	<div id="container"> -->
		<jsp:include page="sideBar.jsp" flush="false" />


<div id="faqPost_contents">
  <div id="faqPost_header">
    <h4 class="text-center">회원정보</h4><p>
	<hr>
  </div>
  
<div class="faqPost_main">
<b>회원목록(전체 회원수:${pgList.count})</b>
  <table class="table">
  
  <!-- 0309 소스추가 -->
			<c:if test="${count==0}"> 
			  <table border="1" width="700" cellpadding="0" cellspacing="0" align="center">
			   <tr>
			    <td align="center">가입한 회원이 없습니다.</td>
			   </tr>
			  </table> 
			</c:if>
			<!--게시글이 존재하지 않을때 출력문 -->
 
			<thead>
				<tr>
					<td class="text-center">아이디</td>
					<td class="text-center">이름</td>
					<td class="text-center">휴대폰번호</td>
					<td class="text-center">이메일</td>
					<td class="text-center">주소</td>
				</tr>
			</thead>
			<tbody>
		  <c:forEach var="article" items="${articleList}">
			    <tr>
			      <td class="text-center"><a href="/Zspace/admin_infoView.do?mem_id=${article.mem_id}">${article.mem_id}</a></td>
			      <td class="text-center">${article.mem_name}</td>
			      <td class="text-center">${article.mem_phone}</td>
			      <td class="text-center">${article.mem_email}</td>
			      <td class="text-center">${article.mem_addr1 } , ${article.mem_addr2 }</td>
			    </tr>
			  </c:forEach>
			</tbody>
		</table>
</div>

<!-- 페이징 처리 -->
 
<p>
<div class="faqPost_paging"> 
	<c:if test="${pgList.startPage > pgList.blockSize}">
     <a href="/Zspace/list.do?pageNum=${pgList.startPage-pgList.blockSize}&search=${search}&searchtext=${searchtext}">[이전]</a>
     <a>이전</a>
</c:if>

<c:forEach var="i" begin="${pgList.startPage}" end="${pgList.endPage}">
     <a href="/Zspace/list.do?pageNum=${i}&search=${search}&searchtext=${searchtext}">
     	<c:if test="${pgList.currentPage==i }">
     		<font color="red"><b>[${i}]</b></font>
     	</c:if>
     	<c:if test="${pgList.currentPage!=i }">
     		[${i}]
     	</c:if>
     	</a>
</c:forEach>

<c:if test="${pgList.endPage <pgList.pageCount}">
      <a href="/Zspace/list.do?pageNum=${pgList.startPage+pgList.blockSize}&search=${search}&searchtext=${searchtext}">[다음]</a>
 </c:if>  
<p>
</div>
<p>

<!-- 검색구역 -->
  <form id="faqPost_boardSearchForm">
  <div class="faqPost_bottomsearch">
        검색어 &nbsp;
        <select>
          <option value="post_num">아이디</option>
          <option value="post_title">이름</option> 
        </select>
        <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="검색를 입력하세요" >
        <button><a href="/Zspace/admin_info.do?">검색</a></button>     
  </div>
 </form>
 


</div><!-- contents -->





<!-- footer구역 -->
	<footer class="faqPost_footer">
		<div class="faqPost_footerdiv" >
			상호명 | 대표자 | 전화번호 | 주소 서울특별시 강남구<p>
				사업자번호 xxx-xx-xxxxx<p>
 				이메일 | 교환,반품 주소지<p>			
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