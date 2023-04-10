package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

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

  //회원정보 불러오기
  public ArrayList<OrderPayDTO> getOrderMemList(int item_num, String mem_id){
	 ArrayList<OrderPayDTO> orderList=new ArrayList<>();
	 Timestamp post_date = new Timestamp(new Date().getTime());
	 int order_num=0;
	 OrderPayDTO order= new OrderPayDTO();
	 // System.out.println("getCartList 시작");
	  try {
		con=pool.getConnection();
	   sql="select i.*, m.* from item_depth i, mem_depth m where i.item_num=? and m.mem_id=?";

	    pstmt=con.prepareStatement(sql);
	    pstmt.setInt(1, item_num);
	    pstmt.setString(2, mem_id);
	    System.out.println("getOrderMemList()에서 회원정보 불러오기");
	    rs=pstmt.executeQuery();
	    if(rs.next()) {

	    	order=makeOrderMemResult();
	    	System.out.println("OrderMemList"+order);
	    	orderList.add(order);
	    }

	    con = pool.getConnection();
		//관심상품 번호 조회
		//sql = "select nvl(max(order_num),0)+1 as max_num from order_check";
		sql="select max(order_num) from order_check";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();

	  }catch (Exception e){
		  System.out.println("getOrderMemList sql 에러: "+e);
	  }finally {
			pool.freeConnection(con, pstmt, rs);
	  }
	  return orderList;
  }

  //주문하기
  public int insertOrder (OrderPayDTO order) {
	  LocalDate today = LocalDate.now();
	  String order_date=LocalDate.now().toString();
	  int check=0;
	  int pay_num=0;
	 /* 	if (pay_num==0){
		  pay_num=1; //default를 무조건 1페이지로 설정
		}*/
	  try {
		con=pool.getConnection();
		sql="select max(pay_num) from pay";
	//	sql = "select nvl(max(pay_num),0)+1 as max_num from pay";
		pstmt=con.prepareStatement(sql);
		rs=pstmt.executeQuery();

		if(rs.next()) {//보여주는 결과가 있다면
			pay_num=rs.getInt(1)+1;//최대값+1
		}else {
			pay_num=1;//테이블에 한개의 데이터가 없다면
		}
		/*
		if(rs.next()) {
			pay_num = rs.getInt("max_num");
			System.out.println("pay_num : " + pay_num);
		}*/

	    sql="insert into pay (pay_num, mem_id, order_date, pay_meth, pay_amt, pay_total, item_name) values(?,?,?,?,?,?,?)";
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, pay_num); //주문 번호
		pstmt.setString(2, order.getMem_id()); //주문자 아이디
		pstmt.setString(3, today.toString()); //주문 날짜
		pstmt.setString(4, order.getPay_meth()); //결제방법
		pstmt.setInt(5, order.getPay_amt()); //결제상품 합계 금액
		pstmt.setInt(6, order.getPay_total()); // 총 결제 가격
		pstmt.setString(7, order.getItem_name()); // 총 결제 가격
		check=1;
		int insert=pstmt.executeUpdate();
	    System.out.println("주문하기 성공 확인: "+insert);
	}catch(Exception e) {
		System.out.println("insertOrder 오류확인 :"+ e);
	}finally {
		pool.freeConnection(con, pstmt, rs);
	}
	  return check;
  }

  //주문 내용 전송하기
  public int getOrderInsert(OrderPayDTO order){
	  LocalDate today = LocalDate.now(); // 오늘 날짜
	  int order_num = 1; // 초기 주문번호
	  int increment = 1; // 주문번호 증가량
	  // 현재 날짜와 주문번호를 결합하여 문자열로 생성
		int x=0; //성공유무
		try {
			con = pool.getConnection();
			//관심상품 번호 조회
			//sql = "select nvl(max(order_num),0)+1 as max_num from order_check";
			sql="select max(order_num) as max_num from order_check";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if(rs.next()) {//보여주는 결과가 있다면
				String max_num = rs.getString("max_num");
				 if(max_num != null) {
				        order_num = Integer.parseInt(max_num) + increment;

				        System.out.println("좋겠다.....");
				 }

			}else {
				//order_num = increment;
				String max_num = rs.getString("max_num");
				String ordercode = max_num + increment;
			}
				String ordercode = String.format("%s%05d", today.toString(), order_num+increment);
				 order.setOrder_num(ordercode);
				//order.setOrder_num(today.toString()+ "00001");

			System.out.println("오늘날짜"+today+"주문번호"+ordercode);
			//주문번호, 회원 아이디
			//con.setAutoCommit(false); //트랜잭션 처리
			sql = "insert into order_check (order_date, mem_id, item_img, item_su, item_name, pay_total, pay_amt, item_num, order_num) ";
			sql+= "values(?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, today.toString()); //주문 날짜
			pstmt.setString(2, order.getMem_id()); //주문자 아이디
			pstmt.setString(3, order.getItem_img()); //결제방법
		    System.out.println("불러오니...?");
			pstmt.setInt(4, order.getItem_su()); //상품 수
			pstmt.setString(5, order.getItem_name()); //상품이름
			pstmt.setInt(6, order.getPay_amt()); //결제상품 합계 금액
			pstmt.setInt(7, order.getPay_total()); // 총 결제 가격
			pstmt.setInt(8, order.getItem_num()); //상품번호
			pstmt.setString(9, ordercode);
			int insert=pstmt.executeUpdate();
		    System.out.println("주문 결과 보냈을까?: "+insert);
			//con.commit();
			x = 1;
		} catch (Exception e) {
			System.out.println("getOrderInsert()에러유발->" + e);
		} finally {// 3.메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
  /*
	if(rs.next()) {//보여주는 결과가 있다면
		String ordercode = today.toString() + (order_num + increment);
		order.setOrder_date(ordercode);
	}else {
		order_num=1;
	}*/
/*
	if(rs.next()) {
		order_num = rs.getInt("max_num");
		System.out.println("order_num : " + order_num);
	}*/

  //pay에 해당하는 모든 정보
  private OrderPayDTO makeOrderMemResult() throws Exception {
	    OrderPayDTO order = new OrderPayDTO();
	    order.setItem_num(rs.getInt("item_num"));
	    order.setItem_name(rs.getString("item_name"));
	    order.setItem_pay(rs.getInt("item_pay"));
	    order.setItem_su(rs.getInt("item_su"));
	    order.setItem_img(rs.getString("item_img"));
	    order.setMem_id(rs.getString("mem_id"));
	    //order.setMem_pwd(rs.getString("mem_pwd"));
	    order.setMem_name(rs.getString("mem_name"));
	    order.setMem_phone(rs.getString("mem_phone"));
	    order.setMem_email(rs.getString("mem_email"));
	    order.setMem_zipcode(rs.getInt("mem_zipcode"));
	    order.setMem_addr1(rs.getString("mem_addr1"));
	    order.setMem_addr2(rs.getString("mem_addr2"));

	  //  order.setPay_num(rs.getInt("pay_num"));
	   /*order.setPay_meth(rs.getString("pay_meth"));
	    order.setPay_amt(rs.getInt("pay_amt"));
	    order.setPay_total(rs.getInt("pay_total"));
	    order.setOrder_date(rs.getTimestamp("order_date"));
	    //order.setPay_list(rs.getString("pay_list"));
	    //order.setWish_list_code(rs.getInt("wish_list_code"));
	    //order.setCart_num(rs.getInt("cart_num"));*/

	    return order;
	}


  /*pay에 해당하는 모든 정보
    private OrderPayDTO makeOrderCartResult() throws Exception {
	    OrderPayDTO order = new OrderPayDTO();
	    order.setMem_id(rs.getString("mem_id"));
	    order.setOrder_date(rs.getString("order_date"));
	    order.setPay_list(rs.getString("pay_list"));
	    order.setPay_meth(rs.getString("pay_meth"));
	    order.setPay_num(rs.getInt("pay_num"));
	    order.setPay_amt(rs.getInt("pay_amt"));
	    order.setPay_total(rs.getInt("pay_total"));
	    order.setWish_list_code(rs.getInt("wish_list_code"));
	    order.setCart_num(rs.getInt("cart_num"));
	    order.setItem_num(rs.getInt("item_num"));
	    order.setItem_su(rs.getInt("item_su"));
	    order.setItem_pay(rs.getInt("item_pay"));
	    order.setItem_img(rs.getString("item_img"));
	    order.setItem_name(rs.getString("item_name"));
	    return order;
	}*/


}
