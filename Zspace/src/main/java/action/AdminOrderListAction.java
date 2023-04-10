package action;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.order_checkDAO;

public class AdminOrderListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		int count=0;
		List order_checkList=null;
		
		order_checkDAO dbPro=new order_checkDAO();
		count=dbPro.getOrderCount();
		
		if(count>0) {
			order_checkList=dbPro.getAllOrders();
			System.out.println("list.jsp의 order_checkList="+order_checkList);
		}
		
		request.setCharacterEncoding("utf-8");
		request.setAttribute("order_checkList",order_checkList);
		

		
		return "/admin_page.jsp";

	}

}
