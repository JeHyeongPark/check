package action;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.REVIEWboardDAO;

public class REVIEWDeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		//추가(삭제된 게시물로 페이지를 이동시키기 위해
		String pageNum=request.getParameter("pageNum");
	
		int post_num=Integer.parseInt(request.getParameter("post_num"));
		System.out.println("DeleteProAction에서의 pageNum="+pageNum+",num="+post_num);
       
		REVIEWboardDAO dbPro=new REVIEWboardDAO();
		int check=dbPro.deleteArticle(post_num);
	

	
		//2개의 공유값이 필요
		request.setAttribute("pageNum",pageNum);
		request.setAttribute("check", check);
		

	    int pageSize=10;
	    int blockSize=5;

	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");

	 if(pageNum==null){
		 pageNum="1";//default(무조건 1페이지는 선택하지 않아도 보여줘야 되기때문에)
	 }
	 int currentPage=Integer.parseInt(pageNum);//"1"->1 (=nowPage(현재페이지))
	 //                    (1-1)*10+1=1,(2-1)*10+1=11,(3-1)*10+1=21
	 int startRow=(currentPage-1)*pageSize+1;//시작 레코드 번호
	 System.out.println(startRow);
	 int endRow=currentPage*pageSize;//1*10=10,2*10=20,3*10=30(마지막 레코드번호)
	 
	 int count=0; //총 레코드 수
	  int number=0; //beginPerPage : 페이지별로 시작하는 맨처음나오는 게시물 번호
	  
	  List articleList=null; //화면ㄴ에 출력할레코드를 저장할 변수
	  
	  dbPro=new REVIEWboardDAO();
	  count=dbPro.getArticleCount(); //select count(*) from board
	  System.out.println("현재 레코드 수(count)="+count);
	  
	  if(count >0) {//레코드 수가 한개라도 있다면
		  articleList=dbPro.getArticles(startRow, pageSize); //endRow(x)
		  System.out.println("list.jsp의 articleList="+articleList);
	  }
	  number=count-(currentPage-1)*pageSize;
	  System.out.println("페이지별 number="+number);
	  
	  request.setCharacterEncoding("utf-8");
	   //2.처리한 결과를 공유(서버메모리에 저장)->이동할 페이지에 공유해서 사용(request)
		request.setAttribute("currentPage", currentPage);//${currentPage}
		request.setAttribute("startRow", startRow);
		request.setAttribute("count", count);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("blockSize", blockSize);
		request.setAttribute("number", number);
		request.setAttribute("articleList", articleList);
		
		
		return "/REVIEWdeletepro.jsp";
	}

}
