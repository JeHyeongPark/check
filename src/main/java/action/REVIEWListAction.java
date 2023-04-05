package action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.REVIEWboardDAO;
import action.*;

import java.util.*;//List

//요청명령어에 해당되는 명령어 처리클래스=액션클래스=컨트롤러클래스
public class REVIEWListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		// 1./list.jsp에서 처리했던 자바코드를 ->결과를 request에 담고 이동->jsp
		String pageNum=request.getParameter("pageNum");	
		 //추가(검색분야,검색어)
		 String search=request.getParameter("search");//검색분야
		 String searchtext=request.getParameter("searchtext");
		 System.out.println("ListAction의 매개변수 확인");
		 System.out.println("pageNum=>"+pageNum
				                       +",search=>"+search+",searchtext=>"+searchtext);
	 
	  int count=0; //총 레코드 수
	  List articleList=null; //화면에 출력할레코드를 저장할 변수
	  
	  REVIEWboardDAO dbPro=new REVIEWboardDAO();
	  count=dbPro.getArticleSearchCount(search,searchtext); //select count(*) from board
	  System.out.println("현재 레코드 수(count)="+count);
	  
	  
	  Hashtable<String,Integer> pgList=dbPro.pageList(pageNum, count);
	  if (count > 0){
		  System.out.println(pgList.get("startRow")+","+pgList.get("endRow"));
		  articleList=dbPro.getBoardArticles(pgList.get("startRow"),//첫번째레코드번호
				                                            pgList.get("endRow"),//불러올 갯수
				                                            search,searchtext);//검색분야,검색어
		  System.out.println("ListAction의 articleList=>"+articleList);
	  }else {//count=0
		  articleList=Collections.EMPTY_LIST;//비어있는 List객체반환
	  }


	  

		request.setAttribute("search", search);//${search} 검색어
		request.setAttribute("searchtext", searchtext);//검색어
		request.setAttribute("pgList", pgList);//페이징 처리 10개 정보
		request.setAttribute("articleList", articleList);//$
		
		

		//3.공유해서 이동할 수있도록 페이지를 지정
		return "/REVIEWpost.jsp";
		
	}

}
