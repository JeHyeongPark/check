package action;

//DB에 관련된 날짜, 시간
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.REVIEWboardDAO;
import model.REVIEWboardDTO;

public class REVIEWWriteProAction implements CommandAction {

   @Override
   public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
      // TODO Auto-generated method stub

     request.setCharacterEncoding("utf-8");
     REVIEWboardDTO article=new REVIEWboardDTO();
     
     article.setPost_num(Integer.parseInt(request.getParameter("post_num")));
     article.setMem_id(request.getParameter("mem_id"));
     article.setPost_title(request.getParameter("post_title"));
     article.setPost_cnt(request.getParameter("post_cnt"));
     article.setItem_img(request.getParameter("item_img")); 
     article.setRated(request.getParameter("rated"));

     
     //DTO저장 끝
     REVIEWboardDAO dbPro=new REVIEWboardDAO();
     dbPro.insertArticle(article);
      
     return "/REVIEWwritepro.jsp";
   }
   

}