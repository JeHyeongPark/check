package action;

//DB에 관련된 날짜, 시간
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CANCELboardDAO;
import model.CANCELboardDTO;

public class CANCELUpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		//한글처리
	  request.setCharacterEncoding("utf-8");
	  //추가(수정된 게시물로 페이지를 이동시키기 위해서)
	  int post_num=Integer.parseInt(request.getParameter("post_num"));		
	  String pageNum=request.getParameter("pageNum");
	  System.out.println("UpdateProAction에서의 pageNum="+pageNum +"post_num"+post_num);
	  //------------------------------------------------------
	  CANCELboardDTO article=new CANCELboardDTO();
	  
	  article.setPost_num(Integer.parseInt(request.getParameter("post_num")));
	  article.setMem_id(request.getParameter("mem_id"));
	  article.setAdmin_id(request.getParameter("admin_id"));
	  article.setPost_title(request.getParameter("post_title"));
	  article.setPost_cnt(request.getParameter("post_cnt")); //글내용
	
	  //DTO저장 끝
	  CANCELboardDAO dbPro=new CANCELboardDAO();
	  int check=dbPro.updateArticle(article); //1:성공 / 0: 실패
	  System.out.println("updateProAction 확인 중 ");
	  
	  //2개의 공유값이 필요
	  request.setAttribute("post_num",post_num);
	  request.setAttribute("pageNum", pageNum);
	  request.setAttribute("check", check);
	  
	  return "/CANCELupdatepro.jsp";
	}

}