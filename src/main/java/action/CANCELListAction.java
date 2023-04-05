package action;

import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

//list.jsp에서 작업한 자바코드 : ListAction처리 => 컨트롤러를거쳐 /list.jsp에 전해줌
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CANCELboardDAO;

public class CANCELListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
	//int post_num=Integer.parseInt(request.getParameter("post_num"));
	 
	  String pageNum=request.getParameter("pageNum");
	  /* if(pageNum==null) {
		  pageNum="1";
	  } */
	
	  String search=request.getParameter("search");
	  String searchtext=request.getParameter("searchtext");
	  System.out.println("ListAction의 매개변수 확인");
	  System.out.println("pageNum="+pageNum+", search="+search+", searchtext="+searchtext);
	  //여기에 pageNum은 null값이 당연한 값임...
	  
	  int count=0; //총 레코드 수
	  List articleList=null; //화면에 출력할 레코드 저장
	   
	  CANCELboardDAO dbPro=new CANCELboardDAO();
	  count=dbPro.getArticleSearchCount(search, searchtext);
	  System.out.println("ListAction의 현재 레코드 수(count)="+count);
	  
	  //                                      1) 화면에 출력할 페이지 번호, 2)출력할 레코드 개수 
	  Hashtable<String, Integer> pgList=dbPro.pageList(pageNum, count);
	  
	  //자료불러오는곳
	  if(count>0) { //레코드가 하나라도 있다면
		  System.out.println(pgList.get("startRow")+","+pgList.get("endRow"));
		  articleList=dbPro.getBoardArticles(pgList.get("startRow"), //첫번째 레코드 번호 
				  							 pgList.get("endRow"), //불러올 갯수pageSize디폴트10=>endRow
				                             search, searchtext); //검색분야, 검색어
		 System.out.println("ListAction의 articleList="+articleList);
	  }else { //count=0
		  articleList=Collections.EMPTY_LIST; //비어있는 List객체 반환
	  }
	  
	//2. 처리한 결과를 공유(서버메모리에 저장)-> 이동할 페이지에 공유해서 사용  
	  request.setAttribute("search", search); //검색 분야
	  request.setAttribute("searchtext", searchtext); //검색어
	  request.setAttribute("pgList", pgList); //페이징 처리 10개가 들어있음
	  request.setAttribute("articleList", articleList); //${articleList}
	  //request.setAttribute("post_type", post_type); 
	  
	//3.공유해서 이동할 수 있도록 페이지를 지정
	 return  "/CANCELpost.jsp"; //컨트롤러가 view를 받아서 이동시키면서 공유O
	}
}
