package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.mem_updateDAO;
import action.*;

public class Mem_deleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
	
	String mem_id=(String)(request.getParameter("mem_id"));
	
	mem_updateDAO dbPro=new mem_updateDAO();
	int check=dbPro.deleteMemState(mem_id);
	
	request.setAttribute("mem_id", mem_id);
	request.setAttribute("check", check);
	
	return "/mem_delpro.jsp";
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
