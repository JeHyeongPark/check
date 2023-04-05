package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

public class REVIEWboardDAO {
	
	//1.연결할 클래스 객체선언
		private DBConnectionMgr pool=null;
		private Connection con=null;
		private PreparedStatement pstmt=null; //
		private ResultSet rs=null;//select는 반환값이 있기 때문에 그 반환값을 담기 위함
		private String sql=""; //실행시킬 sql구문 저장용
		
		//2. 생성자를 통해서 서로 연결
		public REVIEWboardDAO() {
			try {
				pool=DBConnectionMgr.getInstance(); //DB연결얻어오기
			}catch(Exception e) {
				System.out.println("DB접속 오류=>"+e); //오류확인을 위한 출력문
			}
		}//생성자

		//페이징처리
		public int getArticleCount() {
			int x =0;//초기 레코드 수
			try {	
				con=pool.getConnection();	//연결객체를 얻어오는 
				System.out.println("con=>" +con); //접속정보 확인 출력문
				sql ="select count(*) from review_post"; 
				pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery(); //select 문을 사용으로 내용변화가 없으므로 사용됨
				if(rs.next()) { //값이있을경우
					x=rs.getInt(1);
				}
			}catch(Exception e) {
				System.out.println("getArticleCount() 에러발생=>"+e);
			}finally {
				pool.freeConnection(con,pstmt,rs);
			}
			return x;
		}
//글목록
		public List getArticles(int start, int end) {
			ArrayList<REVIEWboardDTO> articleList =null; //수정
			String sql=null;
			System.out.println("getArticles =>"+start+","+end);
			try {
				con=pool.getConnection();	//연결객체를 얻어오는 구문
//				sql = "select * from review_post WHERE ROWNUM >= ? AND ROWNUM <= ? ORDER BY post_num DESC";
				sql = "SELECT * FROM (SELECT rownum rn, b.* FROM (SELECT * FROM review_post ORDER BY post_num DESC) b WHERE rownum <= ?) WHERE rn > ?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, end);
				pstmt.setInt(2, start - 1);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					articleList = new ArrayList(end);
					do {
						REVIEWboardDTO article=new REVIEWboardDTO();
						article=makeArticleFromResult();
						articleList.add(article);
					}while(rs.next());
				}
			}catch(Exception e) {
				System.out.println("getArticles() 에러발생=>"+e);
			}finally {
				pool.freeConnection(con,pstmt,rs);
			}
			return articleList;
		}
		
	
		//페이징 계산을 해주는 구문
		public Hashtable pageList(String pageNum, int count){
			
			Hashtable<String,Integer> pgList = new Hashtable<String,Integer>();
			//1.ListAction에서 소스코드를 복사 => 편집
			
		     int pageSize=10;//numPerPage->페이지당 보여주는 게시물수(=레코드수) 10
		     int blockSize=5;//pagePerBlock->블럭당 보여주는 페이지수 5
		     
		    if(pageNum==null){
		    	pageNum="1";//default(무조건 1페이지는 선택하지 않아도 보여줘야 하기때문에),가장 최근의 글
		    }
		    int currentPage=Integer.parseInt(pageNum);//"1"->1 현재페이지(=nowPage)
		    //메서드 호출->시작 레코드번호
		    //                  (1-1)*10+1=1,(2-1)*10+1=11,(3-1)*10+1=21)
		    int startRow=(currentPage-1)*pageSize+1; //시작 레코드 번호
		    int endRow=currentPage*pageSize;//1*10=10,2*10=20,3*10=30 ->마지막 레코드번호
		    int number=0;//beginPerPage->페이지별로 시작하는 맨 처음에 나오는 게시물번호
		    
		    System.out.println("현재 레코드수(count)=>"+count);
		    
		    number=count-(currentPage-1)*pageSize;
		    System.out.println("페이지별 number=>"+number);
		    //============================================
		    //모델1에서의 list.jsp에서 소스를 편집
		    int pageCount=count/pageSize+(count%pageSize==0?0:1);
	 	   //2.시작페이지
	 	   int startPage=0;
	 	   if(currentPage%blockSize!=0){//1~9,11~19,21~29
	 	      startPage=currentPage/blockSize*blockSize+1;
	 	   }else{//10%10=0,(10,20,30,40~)
	 			             //((10/10)-1)*10+1=1, 20=>11
	 		  startPage=((currentPage/blockSize)-1)*blockSize+1; 
	 	   }
	 	   //종료페이지
	 	   int endPage=startPage+blockSize-1;//1+10-1=10,11+10-1=20
	 	   System.out.println
	 	    ("startPage=>"+startPage+",endPage="+endPage);
	 	   //블럭별로 구분해서 링크걸어서 출력
	 	   //     11     >          10      //마지막페이지=총페이지수
	 	   if(endPage > pageCount)  endPage=pageCount;
	 	   //페이징 처리에 대한 계산 결과 => Hashtable에 저장 -> ListAction전달
	 	   //->메모리저장->공유->list.jsp전달
	 	   pgList.put("pageSize", pageSize);
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
		
		//글쓰기 및 답글
		public void insertArticle (REVIEWboardDTO article) {
			
			int post_num=article.getPost_num();
			Timestamp post_date = new Timestamp(new Date().getTime());
			int maxNum=0;
			System.out.println("insertArticle 메서드의 내부 post_num =>"+post_num);
			
			try {
				con=pool.getConnection();
				sql="select max(post_num) from review_post";
				pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					maxNum=rs.getInt(1); 
				}else {
					maxNum=1; //기존데이터가 없을경우 1로 시작
				}
				post_num= maxNum +1;
//				article.setPost_num(rs.getInt("post_num"));
				System.out.println("post_num =>"+post_num);
				String sql="insert into review_post(post_num,mem_id,post_title,post_cnt,rated,post_date) values(?,?,?,?,?,?)";
				pstmt= con.prepareStatement(sql);
				pstmt.setInt(1, post_num);
//				pstmt.setInt(1, article.getPost_num());
/*				pstmt.setInt(2, article.getItem_num());
				pstmt.setInt(3, article.getPost_view());
				pstmt.setInt(4, article.getCount());
*/				pstmt.setString(2, article.getMem_id());
//				pstmt.setString(6, article.getAdmin_id());
				pstmt.setString(3, article.getPost_title());
				pstmt.setString(4, article.getPost_cnt());
				pstmt.setString(5, article.getRated());
				pstmt.setTimestamp(6, post_date);

				
				int insert = pstmt.executeUpdate();
				
				System.out.println("게시판 글쓰기 성공"+insert);
				System.out.println("post_num=>"+post_num);
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				pool.freeConnection(con,pstmt,rs);
			}
		}
		
		//글 상세보기
		public REVIEWboardDTO getArticle(int post_num) {
			REVIEWboardDTO article = null;
			try {
				con=pool.getConnection();
				sql = "select * from review_post where post_num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, post_num);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					article = makeArticleFromResult();
				}
			}catch(Exception e) {
				System.out.println("글상세보기 에러=>"+e);
			}
			return article;
		}
// 리스트 DB내용 불러오기
		private REVIEWboardDTO makeArticleFromResult() throws SQLException {
			// TODO Auto-generated method stub
			REVIEWboardDTO article=new REVIEWboardDTO();
			article.setPost_num(rs.getInt("post_num"));
/*			article.setItem_num(rs.getInt("item_num"));
			article.setPost_view(rs.getInt("post_view"));
			article.setCount(rs.getInt("count"));
*/			article.setMem_id(rs.getString("mem_id"));
//			article.setAdmin_id(rs.getString("admin_id"));
			article.setPost_title(rs.getString("post_title"));
			article.setPost_cnt(rs.getString("post_cnt"));
			article.setRated(rs.getString("rated"));
		    article.setPost_date(rs.getTimestamp("post_date"));

			return article;
		}
		
	
		
		//글 수정
		public int updateArticle(REVIEWboardDTO article) {
			int x=-1;
			try {
				con=pool.getConnection();	//연결객체를 얻어오는 구문
				sql ="update review_post set post_title=?, post_cnt=?, item_img=?, rated=? where post_num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, article.getPost_title());
				pstmt.setString(2, article.getPost_cnt());
				pstmt.setString(3, article.getItem_img());
				pstmt.setString(4, article.getRated());
				pstmt.setInt(5, article.getPost_num());

				int update=pstmt.executeUpdate();
				
				System.out.println("게시판 글수정 성공 확인(update):"+update);
				
			}catch(Exception e) {
				System.out.println("updateArticle()=>"+e);
			}finally {
				pool.freeConnection(con,pstmt,rs);
			}
			return x;
		}
		
		//글삭제
		public int deleteArticle(int post_num)  {
			int x=-1;
			try {
				con=pool.getConnection();
				sql="delete from review_post where post_num=? ";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, post_num);
				int delete =pstmt.executeUpdate();
				
				System.out.println("게시판 글 삭제 성공(delete):"+delete);
				rs=pstmt.executeQuery();
			}catch(Exception e) {
				System.out.println("deleteArticle()=>"+e);
			}finally {
				pool.freeConnection(con,pstmt,rs);
			}
			return x;
		}
		
	
	
		   
			//이전글 보기
			public REVIEWboardDTO getBeforeArticle(int post_num) {
				REVIEWboardDTO article = null;
				try {
					con=pool.getConnection();
					sql ="select * from (select r.*, ROW_NUMBER() OVER (ORDER BY post_num DESC) AS rn from review_post r where post_num < ?) where rn = 1";

					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, post_num);
					rs=pstmt.executeQuery();
					if(rs.next()) {
						article = makeArticleFromResult();
					}
				}catch(Exception e) {
					System.out.println("이전글보기 에러=>"+e);
				}
				return article;
			}

			//다음글 보기
			public REVIEWboardDTO getAfterArticle(int post_num) {
				REVIEWboardDTO article = null;
				try {
					con=pool.getConnection();
					sql ="select * from (select r.*, ROW_NUMBER() OVER (ORDER BY post_num ASC) AS rn from review_post r where post_num > ?) where rn = 1";

					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, post_num);
					rs=pstmt.executeQuery();
					if(rs.next()) {
						article = makeArticleFromResult();
					}
				}catch(Exception e) {
					System.out.println("다음글보기 에러=>"+e);
				}
				return article;
			}
			
			
			public int getArticleSearchCount(String search,String searchtext) { //getMemberCount() ->MemberDAO에서 작성
				int x=0;//총레코드갯수를 저장
				try {
					con=pool.getConnection();
					System.out.println("con=>"+con);
					//-----------------------------------------------
					if (search == null || search == "") { // 검색분야 선택X(검색어를 입력하지 않은 경우)
						sql = "select count(*) from review_post order by post_num desc";
					} else { // 검색분야(제목,작성자,내용)
					    if (search.equals("post_title")) {
					        sql = "select count(*) from review_post where post_title like '%"+searchtext+"%'";
					    } else if (search.equals("mem_id")) { // 작성자
					        sql = "select count(*) from review_post where mem_id like '%"+searchtext+"%'";
					    } else { // 내용
					        sql = "select count(*) from review_post where post_cnt like '%"+searchtext+"%'";
					    }
					}
					System.out.println("getArticleSearchCount 검색sql=>"+sql);
					//------------------------------------------------
					pstmt=con.prepareStatement(sql);
					rs=pstmt.executeQuery();
					if(rs.next()) {//보여주는 결과가 있다면
						x=rs.getInt(1);//변수명=rs.get자료형(필드명 또는 인덱스번호)=>필드명X(그룹함수)
					}
				}catch(Exception e) {
					System.out.println("getArticleSearchCount() 에러유발=>"+e);
				}finally {
					pool.freeConnection(con, pstmt, rs);
				}
				return x;
			}




public List getBoardArticles(int start,int end,String search,String searchtext) {//getMemberList(int start,int end){
	
	List articleList=null;//ArrayList articleList=null;//(O)
	
	try {
		con=pool.getConnection();
	    //-----------------------------------------------------------------
	    if (search == null || search.equals("")) { // 검색분야 선택X(검색어를 입력하지 않은 경우)
	        sql = "SELECT * FROM (SELECT rownum rn, b.* FROM (SELECT * FROM review_post ORDER BY post_num DESC) b WHERE rownum <= ?) WHERE rn > ?";
	    } else { 
	        if (search.equals("post_title")) {
	        	sql = "SELECT * FROM (SELECT rownum rn, b.* FROM (SELECT * FROM review_post where post_title like '%" + searchtext + "%' ORDER BY post_num DESC) b WHERE rownum <= ?) WHERE rn > ?";
	        } else if (search.equals("mem_id")) { 
	        	sql = "SELECT * FROM (SELECT rownum rn, b.* FROM (SELECT * FROM review_post where mem_id like '%" + searchtext + "%' ORDER BY post_num DESC) b WHERE rownum <= ?) WHERE rn > ?";
	        } else { 
	        	sql = "SELECT * FROM (SELECT rownum rn, b.* FROM (SELECT * FROM review_post where post_cnt like '%" + searchtext + "%' ORDER BY post_num DESC) b WHERE rownum <= ?) WHERE rn > ?";
	        }
	    }
		//------------------------------------------------------------------
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, end);
		pstmt.setInt(2, start-1);
		rs=pstmt.executeQuery();
		if(rs.next()) {//보여주는 결과가 있다면
			articleList=new ArrayList(end);//10=>end갯수만큼 데이터를 담을 공간을 만들어라
			do {
			  REVIEWboardDTO article=new REVIEWboardDTO();//MemberDTO=>필드별로 담을것.
			  article.setPost_num(rs.getInt("post_num"));
			  article.setMem_id(rs.getString("mem_id"));
			  article.setPost_title(rs.getString("post_title"));
			  article.setPost_cnt(rs.getString("post_cnt"));//글내용
			  article.setRated(rs.getString("rated"));
			  article.setPost_date(rs.getTimestamp("post_date"));
			  //추가
			  articleList.add(article);//생략하면 데이터가 저장X->for문 에러유발
			}while(rs.next());
		}
	}catch(Exception e) {
		System.out.println("getBoardArticles() 에러유발=>"+e);
	}finally {
		pool.freeConnection(con, pstmt, rs);
	}
	return articleList;
}








}
