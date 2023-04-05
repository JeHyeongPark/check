package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EVENTboardDAO;
import model.EVENTboardDTO;

public class EVENTUpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		//한글처리
	  
	  request.setCharacterEncoding("utf-8");
	  //추가(수정된 게시물로 페이지를 이동시키기 위해서)
	  int post_num=Integer.parseInt(request.getParameter("post_num"));
	  String pageNum=request.getParameter("pageNum");
	    System.out.println("UpdateProAction에서의 pageNum=>"+pageNum);//0
	  System.out.println("UpdateProAction에서의 pageNum="+pageNum);
	  //------------------------------------------------------
	  EVENTboardDTO article=new EVENTboardDTO();
	  
	  article.setPost_num(Integer.parseInt(request.getParameter("post_num")));
	  article.setAdmin_id(request.getParameter("admin_id"));
	  article.setPost_title(request.getParameter("post_title"));
	  article.setPost_cnt(request.getParameter("post_cnt")); //글내용
	  //article.setPost_date(new Timestamp(System.currentTimeMillis()));
	  /*
	  article.setRef(Integer.parseInt(request.getParameter("ref"))); 
	  article.setRe_step(Integer.parseInt(request.getParameter("re_step"))); 
	  article.setReply_cnt(request.getParameter("reply_cnt"));
	  article.setReply_date(new Timestamp(System.currentTimeMillis()));
	  */
	  //DTO저장 끝
	  EVENTboardDAO dbPro=new EVENTboardDAO();
	  int check=dbPro.updateArticle(article); //1:성공 / 0: 실패
	  System.out.println("upProAction 확인 중 ");
	  //2개의 공유값이 필요
	   request.setAttribute("post_num", post_num);
	  request.setAttribute("pageNum", pageNum);
	  request.setAttribute("check", check);
	  
	  return "/EVENTupdatepro.jsp";
	}

}