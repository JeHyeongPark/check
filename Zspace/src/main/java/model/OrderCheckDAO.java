package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import model.DBConnectionMgr;

public class OrderCheckDAO {
	
	private DBConnectionMgr pool = null;
	private Connection con = null;
	private PreparedStatement pstmt = null; // SQL실행목적 (변경할 것만 고르기)
	private ResultSet rs = null; // select
	private String sql = " "; // 실행시킬 SQL구문 저장 목적

	// 사용테이블 order_table
	// 0) 생성자를 통해 연결
	public OrderCheckDAO() {
		try {
			pool = DBConnectionMgr.getInstance();
		} catch (Exception e) {
			System.out.println("DB접속 오류: " + e);
		}
	}
	// Order_check 에서 공통으로 사용할 부분
	// 사용할때 요렇게 order=makeOrdercheckResult();
	private OrderCheckDTO makeOrdercheckResult() throws Exception {
		OrderCheckDTO order = new OrderCheckDTO();
		order.setPay_amt(rs.getInt("pay_amt"));
		order.setPay_amt(rs.getInt("pay_total"));
		order.setItem_su(rs.getInt("item_su"));
		order.setItem_num(rs.getInt("item_num"));
		order.setItem_img(rs.getString("item_img"));
		order.setItem_name(rs.getString("item_name"));// String
		order.setOrder_date(rs.getDate("order_date"));// 오늘날짜->코딩

		return order;
	}


	//다른곳 검색범위 정하기
	public List<OrderCheckDTO> getArticleSearchDate(String startDate, String endDate) { 
		List orderSearch = null;
		//int orderSearch=0;//주문목록넣을 변수
		try {
			con = pool.getConnection();			
			// -----------------------------------------------
			//if (startDate != null && endDate != null) {// 값이 없는게 아니라면!
				sql ="SELECT * FROM order_check WHERE order_date BETWEEN #{startDate} "
						+ "AND #{endDate} ORDER BY order_date";				
				// :start부분은 값을 바인딩하기위해 만들어둔자리
				// null이 아닐경우에만 실행하기 위해 안쪽에둠
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, startDate);
				pstmt.setString(2, endDate);
				rs = pstmt.executeQuery();
			//} else if (rs.next()) {// 화면에 보여줄 데이터가 있으면
			  while(rs.next()) {//여기는 값을 1개가아닌 여러개를 불러올떄 while을 사용한다.
				
				orderSearch = new ArrayList(); // orderSearch 변수명초기화
				System.out.println("보여줄데이터 orderSearch=>" + orderSearch);
				System.out.println("보여줄데이터 orderSearch.size()=>" + orderSearch.size());
				OrderCheckDTO order = makeOrdercheckResult();
				orderSearch.add(order);// 생략하면 데이터 저장X => for문X(Null)	
			  }
			//}//if
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return orderSearch;
	
	}
	//orderCheck에 필요한 자료 불러오기
	public List<OrderCheckDTO> orderCheck(String mem_id) {// 매서드명 생성
		List<OrderCheckDTO> orderList = new ArrayList();// 공간만들기
		try {
			//sql = "SELECT  Order_Check.* FROM Order_Check Inner JOIN item_depth ON Order_Check.item_num  = item_depth.item_num where Order_Check.mem_id=?";
			/*
			sql="SELECT oc.pay_amt,oc.order_date,oc.pay_total,oc.mem_id, oc.item_num,oc.item_name,oc.item_img ,"
					+ "ii.item_num,ii.item_img, ii.item_name, p.pay_amt ,p.pay_total, md.mem_id "
					+ "FROM order_check oc INNER JOIN mem_depth md ON oc.mem_id = md.mem_id INNER JOIN pay p ON oc.pay_amt=p.pay_amt  INNER JOIN item_info ii ON oc.item_num = ii.item_num WHERE oc.mem_id =?";
		*/
			sql="SELECT DISTINCT oc.* FROM Order_Check oc Inner JOIN Pay p ON oc.order_date  = p.order_date  where oc.mem_id=?";
					
			con = pool.getConnection();
			System.out.println("con->" + con);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				OrderCheckDTO ocdto = new OrderCheckDTO();
				ocdto = makeOrdercheckResult();
				System.out.println("검색된 ocdto=>"+ocdto);
				orderList.add(ocdto);
			}
		} catch (Exception e) {
			System.out.println("order_check()에러유발->" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return orderList;
	}
	
}

// 2) 교환내역 select

// 3) 반품내역 select

// 4) 주문취소 select->update
