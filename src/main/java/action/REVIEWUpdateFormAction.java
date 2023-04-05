package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.REVIEWboardDAO;
import model.REVIEWboardDTO;

public class REVIEWUpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
	
		
		//1.
		//content.jsp=>글수정버튼 클릭=>updateForm.jsp?num=3&page=1
		int post_num = Integer.parseInt(request.getParameter("post_num"));
		String pageNum = request.getParameter("pageNum");
		System.out.println("updateFormAction.jsp의 post_num=>"+post_num+", pageNum=>"+pageNum);
		
		REVIEWboardDAO bdPro = new REVIEWboardDAO(); //메서드 호출목적
		REVIEWboardDTO article = bdPro.getArticle(post_num);//조회수 증가x
		
		
		//2.서버 메모리 저장
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("article", article);

		
		
		
		return "/REVIEWupdateform.jsp";
	}

}
