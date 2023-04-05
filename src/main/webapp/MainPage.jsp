<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!--  
2023.02.25 작성자 : 김인아
2023.03.13 메인페이지 사이드 footer조정완료
2023.03.26 side ->jstl로 변환  이거 안적혀있으면 안되어 있는거
-->
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>공간의 집</title>
<link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">
<link href="/Zspace/css/kim.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.js"></script>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<script src="/Zspace/js/slidekim.js"></script>

<!-- 메인페이지,사이드바 css -->
<link rel="stylesheet" href="/Zspace/css/kim.css">
<link rel="stylesheet" href="/Zspace/css/tool.css">

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
<jsp:include page="sideBar.jsp" />  

		<!-- ========본문시작===========-->
	<div id="contents">
		<div class="slideshow-container">
				<article class="m_slide ">
				<ul><!-- 1500*644 -->					
					<li><a href="#.1rug(10).jpg" title=""><img src="/Zspace/project/slide/vis6.jpg" alt=""></a></li>
					<li><a href="#.3table(8)" title=""><img src="/Zspace/project/slide/5table(3).jpg" alt=""></a></li>
					<li><a href="#" title=""><img src="/Zspace/project/slide/1rug(10).jpg" alt=""></a></li>
					<li><a href="#" title=""><img src="/Zspace/project/slide/3table(8).jpg" alt=""></a></li>
				</ul>
				
				<div class="slide-btns"> 				
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
		
		<div class="conent">
		 	 
			<!-- 제품목록 -->
				<div class="conent_list " align="center">
				<p style="font-family: 'S-CoreDream-3Light', sans-serif;">오늘의 베스트 상품</p>
					<div class="list_imgs"> <!-- 이미지 목록 -->		
							<div class=" col-md-4 warp">
															
								<img src="/Zspace/project/blind/1blind1.jpg" class=" img " width="100%">
									<div class="text">제품명1</p>가격:135000</p>추천</div>	
								</img>
							</div>						
							<div class="col-md-4 warp">
								<div class="text"> 제품명2</p>가격:270000</p>추천 </div>
								<img src="/Zspace/project/blanket/2blanket1.jpg" class="img" width="100%">
							</div>
							
							<div class="col-md-4 warp">
								<div class="text">제품명3</p>가격:850000</p></div>
								<img src="/Zspace/project/rug/1rug(3).jpg" class="img" width="100%">
							</div>					
					
							<div class=" col-md-4 warp">
								<div class="text">제품명4</p>가격:135000</p>추천</div>
								<img src="/Zspace/project/dresser/2dresser(1).jpg" class="img" width="100%">
							</div>
							
							<div class="col-md-4 warp">
								<div class="text"> 제품명5</p>가격:270000</p>추천 </div>
								<img src="/Zspace/project/lamp/1lamp(1).jpg" class="img" width="100%">
							</div>
							
							<div class="col-md-4 warp">
								<div class="text"> 제품명6</p>가격:850000</p> </div>
								<img src="/Zspace/project/blanket/4blanket1.jpg" class="img" width="100%">
							</div>					
	
							<div class=" col-md-4 warp">
								<div class="text"> 제품명7</p>가격:135000</p>추천 </div>
								<img src="/Zspace/project/blind/3blind1.jpg" class="img" width="100%">
							</div>
							
							<div class="col-md-4 warp">
								<div class="text"> 제품명8</p>가격:270000</p>추천</div>
								<img src="/Zspace/project/dresser/1dresser(1).jpg" class="img" width="100%">
							</div>
							
							<div class="col-md-4 warp">
								<div class="text"> 제품명9</p>가격:850000</p> </div>
								<img src="/Zspace/project/dresser/3dresser(1).jpg" class="img" width="100%">
							</div>					
					</div>
				</div>
				<!-- 제품리스트 -->
		<!-- footer 하단부분 밀려서 지움-->
			<div class="ftsizeout">
			      <span class="ftsizein" >
			         공간의 집 | KIC | 012-345-6789 | 주소 서울특별시 강남구<p>
			            사업자번호 011-220345678 | ourhome@aaa.com<p>         
			      </span>
			</div>
		
		</div><!-- conent 본문시작부분 -->
		
			
			<!--  <footer> <jsp:include page="footer.jsp" /> </footer> -->
			
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>		
		<script src="/Zspace/js/bootstrap.min.js"></script>
	</div><!-- container -->
	
		</body>
</html>