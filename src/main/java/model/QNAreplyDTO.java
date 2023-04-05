package model;

import java.sql.Timestamp;

public class QNAreplyDTO {

  private int reply_num;
  private String admin_id, reply_cnt;
  private Timestamp reply_date;
  private int post_num;
  private String post_cnt;
  
public int getReply_num() {
	return reply_num;
}
public void setReply_num(int reply_num) {
	this.reply_num = reply_num;
}
public String getAdmin_id() {
	return admin_id;
}
public void setAdmin_id(String admin_id) {
	this.admin_id = convert(admin_id);
}
public String getReply_cnt() {
	return reply_cnt;
}
public void setReply_cnt(String reply_cnt) {
	this.reply_cnt = convert(reply_cnt);
}
public Timestamp getReply_date() {
	return reply_date;
}
public void setReply_date(Timestamp reply_date) {
	this.reply_date = reply_date;
}
public int getPost_num() {
	return post_num;
}
public void setPost_num(int post_num) {
	this.post_num = post_num;
}
public String getPost_cnt() {
	return post_cnt;
}
public void setPost_cnt(String post_cnt) {
	this.post_cnt = convert(post_cnt);
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
