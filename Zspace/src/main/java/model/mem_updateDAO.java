package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class mem_updateDAO {

	private DBConnectionMgr pool=null;
	private Connection con=null;
	private PreparedStatement pstmt=null; 
	private ResultSet rs=null;
	private String sql="";
	
	
	
	//DB연결
	public mem_updateDAO() {
		try {
			pool=DBConnectionMgr.getInstance();
		}catch(Exception e) {
		}
	}
	
	
	//리스트 db내용 불러오기
	private mem_updateDTO makeArticleFromResult() throws SQLException {
		mem_updateDTO article=new mem_updateDTO();
		
		article.setMem_id(rs.getString("mem_id"));
		article.setMem_pwd(rs.getString("mem_pwd"));
		article.setMem_name(rs.getString("mem_name"));
		article.setMem_phone(rs.getString("mem_phone"));
		article.setMem_email(rs.getString("mem_email"));
		article.setMem_zipcode(rs.getString("mem_zipcode"));
		article.setMem_addr1(rs.getString("mem_addr1"));
		article.setMem_addr2(rs.getString("mem_addr2"));
	
		

		return article;
	}
	
	
	
	
	//글 상세보기
	public mem_updateDTO getArticle(String mem_id) {
		mem_updateDTO article = null;
		
		try {
			con=pool.getConnection();
			sql ="select * from mem_depth where mem_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				article = makeArticleFromResult();
			}
			System.out.println("getArticle(SQL)="+rs);
			System.out.println("getArticle()="+article);
		}catch(Exception e) {
		}finally {
			pool.freeConnection(con,pstmt,rs);
		}
		return article;
	}
	
	
	
		
	//상태 변경
		public int updateMemState (mem_updateDTO order) {
			int update=0;
			try {
				con=pool.getConnection();
				sql="update mem_depth set mem_pwd=?,mem_addr1=?,mem_addr2=?,mem_phone=?,mem_email=?,mem_zipcode=? where mem_id = ?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1,order.getMem_pwd());
				pstmt.setString(2, order.getMem_addr1());
				pstmt.setString(3, order.getMem_addr2());
				pstmt.setString(4,order.getMem_phone());
				pstmt.setString(5,order.getMem_email());
				pstmt.setString(6,order.getMem_zipcode());
				pstmt.setString(7, order.getMem_id());
				
				update=pstmt.executeUpdate();
				
				rs=pstmt.executeQuery();
			}catch(Exception e) {
			}finally {
				pool.freeConnection(con,pstmt,rs);
			}
			return update;
		}
		
		//멤버 삭제
		public int deleteMemState (String mem_id) {
			int delete=0;
			try {
				con=pool.getConnection();
				sql="delete from mem_depth where mem_id=? ";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, mem_id);
				delete =pstmt.executeUpdate();
				
				rs=pstmt.executeQuery();
			}catch(Exception e) {
			}finally {
				pool.freeConnection(con,pstmt,rs);
			}
			return delete;
		}
	
	
		//비밀번호 초기화
			public int resetMemState (mem_updateDTO order) {
				int update=0;
				try {
					con=pool.getConnection();
					sql="update mem_depth set mem_pwd=? where mem_id = ?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1,order.getMem_pwd());
					pstmt.setString(2, order.getMem_id());
					
					update=pstmt.executeUpdate();
					rs=pstmt.executeQuery();
				}catch(Exception e) {
				}finally {
					pool.freeConnection(con,pstmt,rs);
				}
				return update;
			}
			
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
