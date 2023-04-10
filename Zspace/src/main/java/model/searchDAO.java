package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;

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

  //페이징 처리
  public int getItemCount() {
	int x=0;
	try {
	  con=pool.getConnection();
	  System.out.println("con="+con);
	  sql="select count * from item_info";
	  pstmt=con.prepareStatement(sql);
	  rs=pstmt.executeQuery();
	  	if(rs.next()) {
	  	  x=rs.getInt(1);
		}
	}catch (Exception e){
	  System.out.println("getitemCount() 에러유발="+e);
	}finally {
	  pool.freeConnection(con, pstmt, rs);
	}
	  return x;
	}

/*
  //검색어에 따른 레코드수(검색분야,검색어)
  public int ItemSearchCount(String search, String searchtext) {//getMemberCount() -> MemberDAO()
		int x=0; //총 레코드의 갯수를 저장
		try {
		  con=pool.getConnection();
		  System.out.println("con="+con);

		  if(search==null||search=="choose") {//검색분야 선택x
		  sql="select * from item_info where item_name like '%"+searchtext+"%'";
		  System.out.println("searchtext일 때 확인");
		  }else {//검색분야(카테고리 번호)
			  if(search.equals("category_num")) {
				sql = "select * from item_info inner join category on item_info.category_num = category.category_num ";
				sql += "where category.category_num like '%"+search+"%' and item_info.item_name like '%"+searchtext+"%'";
				 System.out.println("getItemSearchCount의 sql호출 완료");
			  }
		  }
		  pstmt=con.prepareStatement(sql);
		  rs=pstmt.executeQuery();
		  	if(rs.next()) { //보여주는 결과가 있다면
		  	  x=rs.getInt(1);
			}
		}catch (Exception e){
		  System.out.println("getItemSearchCount() 에러유발="+e);
		}finally {
		  pool.freeConnection(con, pstmt, rs);
		}
		  return x;
		}

  //검색 후 목록
  public List ItemSearchList(int start,int end, String search, String searchtext) {
	ArrayList<searchDTO> itemList=null;
	  try {
		con=pool.getConnection();
		if(search==null||search=="choose") { //검색분야
		  sql="select * from item_info where item_name like '%"+searchtext+"%' order by item_name asc";
		}else { //제목+본문
			if(search.equals("catefory_num")) {
			sql="select * from item_info inner join category on item_info.category_num = category.category_num";
			sql+=" where category.category_num like '%"+search +"%'and item_info.item_name like '%"+ searchtext +"%' order by item_info.item_name asc";
			}
		}
		System.out.println("ItemSearchList의 sql호출");

		pstmt=con.prepareStatement(sql);
		rs=pstmt.executeQuery();
		//기존에 데이터가 있으면 누적해서 쌓아 올려야한다.
		if(rs.next()) {//화면에 보여줄 데이터가 있으면
		  itemList=new ArrayList(end); //end갯수만큼 공간을 생성해라
		  do {
			searchDTO item=new searchDTO(); //회원리스트면 MemberDTO
			item=makeItemsearchResult();
			itemList.add(item);
		  }while(rs.next());
		}
	  }catch(Exception e) {
		System.out.println("ItemSearchList 에러발생=>"+e);
	  }finally {
		pool.freeConnection(con, pstmt, rs);
	  }
		return itemList;
  }*/

  // 상품을 검색하고 나온 결과 목록
  public ArrayList<searchDTO> ItemSearchList(int start,int end, String search, String searchtext){
	ArrayList<searchDTO> itemList=null;
	try {
		con=pool.getConnection();
		if(search==null || search=="") { //검색카테고리가 없으면?
			sql="SELECT * FROM item_info where item_name like '%"+ searchtext +"%' order by item_info.item_name ";
			System.out.println("ItemSearchList 검색어 없을 때 sql");
		}else {
		  if(searchtext.equals("item_name")) { //상품명만 입력 시
				sql="select * from item_info where item_name like '%"+ searchtext +"%' order by item_info.item_name";
				 System.out.println("ItemSearchLis에서 상품명일 때 sql");
		  }else{ //카테고리 선택 시
			sql="select * from item_info inner join category on item_info.category_num = category.category_num ";
			sql+= "where category.category_num like '%"+ search +"%'and item_info.item_name like '%"+ searchtext +"%' order by item_info.item_name";
			System.out.println("ItemSearchLis에서 검색어 있을 때 sql");
		  }
		}
		System.out.println("itemSearchList() 확인!"+sql);
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
		if(search==null || search.equals("choose")) { //검색분야(카테고리) 선택하지 않은 경우
			  sql= "SELECT * FROM item_info WHERE item_name LIKE '%" + searchtext + "%'";
			  //sql="SELECT item_info.*, Category.* FROM item_info JOIN Category on item_info.category_num = category.category_num";
			  System.out.println("이너조인 확인");
		}else { //상품명만
		  if(search.equals("item_name")) { //상품명만
			  sql="select count(*) from item_info where item_name like '%"+ searchtext +"%'";
			  System.out.println("상품명");
		  }else{
			  sql="select count(distinct item_info.item_name) from item_info inner join category on item_info.category_num = category.category_num where item_info.item_name like '%"+
					searchtext +"%' and category.category_num like '%"+ search +"%'";
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
/*
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
  }*/

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
		  int pageCount=count/pageSize+(count%pageSize==0?0:1);
		  //2) 시작페이지
		  int startPage=0;
		  if(currentPage%blockSize!=0){
			  startPage=currentPage/blockSize*blockSize+1;
		  }else{
			startPage=((currentPage/blockSize)-1)*blockSize+1;
		  } //종료페이지
		  int endPage=startPage+blockSize-1;
		  System.out.println("startPage="+startPage+", endPage="+endPage);
		  //블럭별로 구분해서 링크 걸어 출력
		  if(endPage>pageCount) endPage=pageCount;
		 	//페이징 처리에 대한 계산 결과 : Hashtable에 저장해서 ListAction에 전달한 뒤
			//메모리에 저장 후 공유해서 Search.jsp에서 불러다 사용
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
	  item.setItem_num(rs.getInt("item_num"));
	  item.setItem_pay(rs.getInt("item_pay"));
	  item.setItem_img(rs.getString("item_img"));
	  item.setItem_name(rs.getString("item_name"));

	 return item;
  }
}
