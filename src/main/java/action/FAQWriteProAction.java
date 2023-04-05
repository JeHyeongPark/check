package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FAQboardDAO;
import model.FAQboardDTO;

public class FAQWriteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
	
		FAQboardDTO article = new FAQboardDTO();
		 article.setPost_num(Integer.parseInt(request.getParameter("post_num")));
	     article.setAdmin_id(request.getParameter("admin_id"));
	     article.setPost_title(request.getParameter("post_title"));
	     article.setPost_cnt(request.getParameter("post_cnt"));

		 FAQboardDAO dbPro=new FAQboardDAO();
		dbPro.insertArticle(article); 
		
		 return "/FAQwritepro.jsp";
	   }
}


