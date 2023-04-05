package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrderPayDAO {

  private DBConnectionMgr pool=null; //1)연결할 클래스 객체 선언
	  //공통
  private Connection con=null; 
  private PreparedStatement pstmt=null;
  private ResultSet rs=null; //select
  private String sql=""; //실행시킬 SQL구문 저장용
		
  public OrderPayDAO() {
	try {
	  pool=DBConnectionMgr.getInstance();
	}catch(Exception e) {
		System.out.println("DB 접속 오류="+e);
	}
  }//생성자
  
  //주문하기
  public void insertOrder(OrderPayDTO order) {
	try {
		con=pool.getConnection();
		String sql="insert into pay (pay_num, mem_id, item_su, pay_date) values(?,?,?,now())";
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1,order.getPay_num()); //주문 번호
		pstmt.setString(2, order.getMem_id()); //주문자 아이디
		pstmt.setInt(3, order.getItem_su()); //상품개수
		pstmt.setTimestamp(4,order.getPay_date());
	}catch(Exception e) {
		System.out.println("insertOrder 오류확인 :"+ e);
	}finally {
		pool.freeConnection(con, pstmt);
	}
  }
  
  
  
  
  
  
}
