<%@page contentType="text/html;charset=UTF-8"
		pageEncoding="UTF-8"%>
<!--  
2023.02.25 작성자 : 김인아

-->
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title>공간의 집 메인페이지</title>

<!-- 부트스트랩 -->
<link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">
<link href="/Zspace/css/kim.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined|
			Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp"rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="/Zspace/js/slidekim.js"></script>
<style>

@font-face {
  font-family: 'S-CoreDream-3Light';
  src: url('S-CoreDream-3Light.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}

body{
    font-family:'S-CoreDream-3Light';
}
</style>
</head>

<body>


		<div id="leftbar">
			<!-- 사이드바 내용시작부분 -->
			<div class="logo">
				<a href="/Zspace/mainpage/MainPage.jsp"><img src="/Zspace/icon/rogo.png"></a>
			</div>

			<div class="menu_wrap" >
				<table class="menu_group" ><tr>				
					<th align="center">
					  <a href="/Zspace/FRONT/login.jsp">로그인</a>  | 
					  <a href="/Zspace/FRONT/mem_join.jsp">회원가입</a> 
					  <p>
					  <a href="#cart_num.do?type=">장바구니</a> | 
					  <a href="#.wish_list.do?type=">관심상품</a> | 
					  <a href="#.mypage.do?type=">마이페이지</a>
					</th>	
				</tr></table>		
		<div class="search_menu">		      
		<input type="text" id="keyword">
		   <i class="material-icons"> 
		     <a href="/Zspace/search.do">search</a> 
		   </i>		 
	  </div>
				
				<div class="side_list">
				<!-- 좌측 -->
				<div class=" side_wrap">
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
				</div>

				<!-- 우측 -->
				<div class="side_wrap ">
					<div class="menu_cell">
						<label for="ch6"><a href="#FAQ">공지사항</a></label>						
					</div>
					<div class="menu_cell">
							<label for="ch8"><a href="#.EVENT">이벤트</a></label>		
					</div>
					
					<!-- 게시판 -->
					<div class="menu_cell">
						<label for="ch3"><a href="#.FABRIC">게시판</a></label>
						<ul class="sub_menu">
							<li><a href="/Zspace/list.do">질문게시판</a></li>
							<li><a href="#.REVIEW ">리뷰게시판</a></li>
							<li><a href="#.CANCEL">취소게시판</a></li>
							<li><a href="#.">커뮤니티</a></li>
						</ul>
					</div>
					
			
				</div><!-- <div class="side_wrap  col-md-6 "> -->
				
				
				</div><!--<div class="side_list">  -->
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
			
			</div><!--menu_wrap  -->
			
		</div><!-- sidebar -->
		<!-- ========본문시작===========-->
			<div id="container">
		
		<div class="conent">
		  <div class="slideshow-container">
			<article class="m_slide ">
				<ul><!-- 1500*644 -->					
					<li><a href="#" title=""><img src="../poto/cont_mt/vis1.jpg" alt=""></a></li>
					<li><a href="#" title=""><img src="../poto/cont_mt/vis4.jpg" alt=""></a></li>
					<li><a href="#" title=""><img src="../poto/cont_mt/vis3.jpg" alt=""></a></li>
					<li><a href="#" title=""><img src="../poto/cont_mt/vis2.jpg" alt=""></a></li>
				</ul>
				<!-- 좌우버튼 클릭시 해당방향이동  -->
				<div class="slide-btns"> <!--  버튼 큰범위-->				
					<div class="btn_wrap">
						<button id="btnPrev">
							<i class="fas fa-angle-left"></i>
						</button>
						<button id="btnNext">
							<i class="fas fa-angle-right"></i>
						</button>
					</div>
				</div>				
			  </article>
		  </div> <!-- slideshow-container-->
		


			<!-- 제품목록 -->
				<div class="conent_list " align="center">
				<h3>오늘의 베스트 상품</h3>
					<div class="list_imgs"> <!-- 이미지 목록 -->		
							<div class=" col-md-4 warp">
								<div class="text">편안한의자</p>가격:135000</p>추천</div>								
								<img src="../poto/의자/chair01_3.jpg" class=" img " width="100%">
							</div>						
							<div class="col-md-4 warp">
								<div class="text"> 편안한의자</p>가격:270000</p>추천 </div>
								<img src="../poto/의자/chair04_3.jpg" class="img" width="100%">
							</div>
							
							<div class="col-md-4 warp">
								<div class="text">파란의자</p>가격:850000</p></div>
								<img src="../poto/의자/chair01_1.jpg" class="img" width="100%">
							</div>					
					
							<div class=" col-md-4 warp">
								<div class="text">편안한의자</p>가격:135000</p>추천</div>
								<img src="../poto/의자/chair01_4.jpg" class="img" width="100%">
							</div>
							
							<div class="col-md-4 warp">
								<div class="text"> 편안한의자</p>가격:270000</p>추천 </div>
								<img src="../poto/의자/chair03_3.jpg" class="img" width="100%">
							</div>
							
							<div class="col-md-4 warp">
								<div class="text"> 파란의자</p>가격:850000</p> </div>
								<img src="../poto/의자/chair02_1.jpg" class="img" width="100%">
							</div>					
	
							<div class=" col-md-4 warp">
								<div class="text"> 편안한의자</p>가격:135000</p>추천 </div>
								<img src="../poto/의자/chair05_3.jpg" class="img" width="100%">
							</div>
							
							<div class="col-md-4 warp">
								<div class="text"> 편안한의자</p>가격:270000</p>추천</div>
								<img src="../poto/의자/chair02_3.jpg" class="img" width="100%">
							</div>
							
							<div class="col-md-4 warp">
								<div class="text"> 파란의자</p>가격:850000</p> </div>
								<img src="../poto/의자/chair03_1.jpg" class="img" width="100%">
							</div>					
					</di9v>
				</div><!-- conent_list 제품리스트 -->
		
		</div><!-- conent 본문시작부분 -->
			</div><!-- div id="container" -->
			
  <footer class="footer"> <!-- footer구역 -->
	<div class="footerdiv" >
	  공간의 집 | KIC | 012-345-6789 | 주소 : 서울특별시 강남구<p>
	  사업자번호 011-22-345678 | ourhome@aaa.com <p>			
	</div>
  </footer>
		
		
		
		<!-- class="container-fluid navbar-bottom" 이렇게 넣어도 되는거 같음 양옆여백이조금있음-->
		
		<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
			<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
		<script src="../js/bootstrap.min.js"></script>


		</body>
</html>