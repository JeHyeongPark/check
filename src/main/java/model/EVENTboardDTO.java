package model;

import java.sql.Timestamp;

public class EVENTboardDTO {

	private int post_num, item_num, post_view;
	private String admin_id, post_title, post_cnt, item_img;
	private Timestamp post_date;

	/*
	 * //답글 private int ref; //글, 그룹번호(=게시물 번호) private int re_step; //답변글의 순서
	 * 지정(=같은 그룹의 답변글 순서)
	 * 
	 * //댓글 private int reply_num; private String reply_cnt; private Timestamp
	 * reply_date;
	 */

	public int getPost_num() {
		return post_num;
	}

	public void setPost_num(int post_num) {
		this.post_num = post_num;
	}

	public int getItem_num() {
		return item_num;
	}

	public void setItem_num(int item_num) {
		this.item_num = item_num;
	}

	public int getPost_view() {
		return post_view;
	}

	public void setPost_view(int post_view) {
		this.post_view = post_view;
	}

	public String getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(String admin_id) {
		this.admin_id = convert(admin_id);
	}

	public String getPost_title() {
		return post_title;
	}

	public void setPost_title(String post_title) {
		this.post_title = convert(post_title);
	}

	public String getPost_cnt() {
		return post_cnt;
	}

	public void setPost_cnt(String post_cnt) {
		this.post_cnt = convert(post_cnt);
	}

	public String getItem_img() {
		return item_img;
	}

	public void setItem_img(String item_img) {
		this.item_img = convert(item_img);
	}

	public Timestamp getPost_date() {
		return post_date;
	}

	public void setPost_date(Timestamp post_date) {
		this.post_date = post_date;
	}
	//이 클래스에서만 사용하기위해서 접근지정자 private <,>,(,)=>변경메서드
	private static String convert(String name) {
		if(name!=null){
	    	//2.입력받은 문자열중에서 자바스크립트 구문을 실행시킬수 있는 특수기호를 입력X(<,>)
	    	//문자열메서드->replaceAll(1.변경전문자열,2.변경후 문자열)
	    	
	    	name=name.replaceAll("<","&lt");
	    	name=name.replaceAll(">","&gt");
	    	//추가 eval(" " or ' ')
	    	name=name.replaceAll("\\(","&#40");
	    	name=name.replaceAll("\\)","&#41");
	    	//"test"  'test'
	    	name=name.replaceAll("\"","&quot");
	    	name=name.replaceAll("\'","&apos");
	    }else{ //name==null
	    	return null; //입력을 하지 않았다면 더 이상 실행X
	    }
		return name;
	}

}