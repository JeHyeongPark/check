package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class searchDAO {

	  private DBConnectionMgr pool=null; //(DBConnectionMgr)에 접근하기 위해
	  private Connection con=null; //이미 만들어진 연결객체(Connection)를 얻어오는 것
	  private PreparedStatement pstmt=null; //SQL실행목적 (변경할 것만 고르기)
	  private ResultSet rs=null; //select
	  private String sql=""; //실행시킬 SQL구문 저장 목적

  public searchDAO() {
	try {
		pool=DBConnectionMgr.getInstance();
	}catch(Exception e){
		System.out.println("DB접속 오류: "+e);
	}
  }

  // 상품을 검색하고 나온 결과 목록
  public ArrayList<searchDTO> ItemSearchList(int start,int end, String search, String searchtext){
	ArrayList<searchDTO> itemList=null;
	try {
		con=pool.getConnection();
		if(search==null || search=="") { //검색어가 없으면?
			sql="SELECT item_info.*, Category.* FROM item_info, Category";
			System.out.println("ItemSearchList 검색어 없을 때 sql");
		}else {
		  if(searchtext.equals("item_name")) { //상품명만 입력 시
				sql="select count(*) from item_info where item_name like '%"+ searchtext +"%' order by item_info.item_name desc";
				 System.out.println("ItemSearchLis에서 상품명일 때 sql");
		  }else{ //카테고리 선택 시
			sql="select * from item_info inner join category on item_info.category_num = category.category_num where category.category_num like '"+
			     search +"'and item_info.item_name like '%"+ searchtext +"%' order by item_info.item_name desc";
			System.out.println("ItemSearchLis에서 검색어 있을 때 sql");
		  }
		}
		System.out.println("itemSearchList() 확인!");
		pstmt=con.prepareStatement(sql);
		rs=pstmt.executeQuery();

		if(rs.next()) {
			itemList=new ArrayList<searchDTO>(end);
			do {
			  searchDTO item=new searchDTO();
			  item=makeItemsearchResult();
			  itemList.add(item);
			}while(rs.next());
		}
	}catch(Exception e) {
		System.out.println("ItemSearchList() 에러: "+e);
	}finally {
		pool.freeConnection(con, pstmt, rs);
	}
	  return itemList;
  }


  //상품들을 검색하자
  public int ItemSearchCount(String search, String searchtext){
    int x=0;
    try {
    	con=pool.getConnection();
		System.out.println("con="+con);
		if(search==null || search=="") { //검색분야(카테고리) 선택하지 않은 경우
			  sql= "SELECT count(*) FROM item_info WHERE item_name LIKE '%" + searchtext + "%'";
			  //sql="SELECT item_info.*, Category.* FROM item_info JOIN Category on item_info.category_num = category.category_num";
			  System.out.println("이너조인 확인");
		}else { //상품명만
		  if(search.equals("item_name")) { //상품명만
			  sql="select count(*) from item_info where item_name like '%"+ searchtext +"%'";
			  System.out.println("상품명");
		  }else{
			  sql="select count(*) from item_info inner join category on item_info.category_num = category.category_num where item_info.item_name like '%"+
					searchtext +"%' and category.category_num like '"+ search +"'";
			System.out.println("카테고리+상품명");
		  }
		}
		System.out.println("ItemSearchCount() 확인!");
		pstmt=con.prepareStatement(sql);
		rs=pstmt.executeQuery();
		if(rs.next()) { //결과가 있다면
		  x=rs.getInt(1);
		}
	}catch(Exception e) {
		System.out.println("ItemSearchCount() 오류: "+e);
	}finally {
		pool.freeConnection(con, pstmt, rs);
	}
    return x;
  }

  public List AllItemList(int start, int end) {
	  ArrayList<searchDTO> itemList=null;
	 String sql=null;
		System.out.println("AllItemList =>"+start+","+end);
	try {
	  con=pool.getConnection();
	 sql = "SELECT * FROM item_info WHERE ROWNUM >= ? AND ROWNUM <= ? ORDER BY item_name DESC";
	  //sql = "SELECT item_info.*, Category.* FROM item_info, Category WHERE ROWNUM >= ? AND ROWNUM <= ? ORDER BY item_name DESC";
	  //sql = "SELECT item_info.*, Category.* FROM item_info, Category";
	  //sql="select * from board order by ref desc,re_step limit ?,?";
	  // sql="SELECT * FROM qna_post ORDER BY post_num desc";
	 // sql="select * from qna_post where re_step >= ? and re_step <= ? order by ref desc";

	  pstmt=con.prepareStatement(sql);
	  pstmt.setInt(1, start-1); //-1을 주면 전 페이지와 현재 페이지의 글이 하나 겹침
	  pstmt.setInt(2, end);
	  rs=pstmt.executeQuery();
	  if(rs.next()) { //누적개념
		itemList=new ArrayList(end);
		do {
		  searchDTO item=new searchDTO();
		  item=makeItemsearchResult();
		  itemList.add(item); //생략하면 데이터가 저장되지 않음
		}while(rs.next());
	  }
	}catch(Exception e) {
	  System.out.println("AllItemList() 글목록보기 에러 발생: "+e);
	}finally {
	  pool.freeConnection(con, pstmt, rs);
	}
	return itemList;
  }

  public Hashtable pageList (String pageNum, int count) {
		Hashtable<String,Integer> pgList=new Hashtable<String, Integer>();

		int pageSize=5;
		int blockSize=3;
		if (pageNum==null){
		  pageNum="1";
		}
		  int currentPage=Integer.parseInt(pageNum);
		  int startRow=(currentPage-1)*pageSize+1;
		  int endRow=currentPage*pageSize;
		  int number=0;

		  System.out.println("현재 레코드 수(count)="+count);

		  number=count-(currentPage-1)*pageSize;
		  System.out.println("페이지별 number="+number);

		  //1) 총 페이지 수 구하기
			//              122/10=12.2+1.0=13.2 ==> int 13 //(122%10=2 ==>0이 아니니까 1)
		  int pageCount=count/pageSize+(count%pageSize==0?0:1);
		  //2) 시작페이지
		  int startPage=0;
		  if(currentPage%blockSize!=0){//1~9, 11~19, 21~29,
			  startPage=currentPage/blockSize*blockSize+1;
		  }else{ //10%10=0,(10,20,30,40~)
					  //		((10/10)-1)*10+1=1
			startPage=((currentPage/blockSize)-1)*blockSize+1;
		  } //종료페이지
		  int endPage=startPage+blockSize-1; //1+10-1=10, 11+10-1=20
		  System.out.println("startPage="+startPage+", endPage="+endPage);
		  //블럭별로 구분해서 링크 걸어 출력
			   //    11 > 10      //마지막페이지=총페이지 수
		  if(endPage>pageCount) endPage=pageCount;
		 	//페이징 처리에 대한 계산 결과 : Hashtable에 저장해서 ListAction에 전달한 뒤
			//메모리에 저장 후 공유해서 list.jsp에서 불러다 사용
			pgList.put("pageSize", pageSize); //pgList.get(key명) : key, value값 다르면 찾기 힘듦
			pgList.put("blockSize", blockSize);
			pgList.put("currentPage", currentPage);
			pgList.put("startRow", startRow);
			pgList.put("endRow", endRow);
			pgList.put("count", count);
			pgList.put("number", number);
			pgList.put("startPage", startPage);
			pgList.put("endPage", endPage);
			pgList.put("pageCount", pageCount);
			return pgList;
		}


  //search에 해당하는 모든 정보
  private searchDTO makeItemsearchResult() throws Exception{
	  searchDTO item=new searchDTO();
	  item.setCategory_num(rs.getInt("category_num"));
	  item.setCategory_menu(rs.getString("category_menu"));
	  item.setCategory_depth(rs.getString("category_depth"));
	  item.setItem_pay(rs.getInt("item_pay"));
	  item.setItem_img(rs.getString("item_img"));
	  item.setItem_name(rs.getString("item_name"));

	 return item;
  }
}
