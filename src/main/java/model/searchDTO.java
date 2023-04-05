package model;


public class searchDTO {

	private int category_num;
	private String category_menu, category_depth;
	
	private int item_pay;
	private String item_img, item_name;
	
	
	public int getCategory_num() {
		return category_num;
	}
	public void setCategory_num(int category_num) {
		this.category_num = category_num;
	}
	public String getCategory_menu() {
		return category_menu;
	}
	public void setCategory_menu(String category_menu) {
		this.category_menu = convert(category_menu);
	}
	public String getCategory_depth() {
		return category_depth;
	}
	public void setCategory_depth(String category_depth) {
		this.category_depth = convert(category_depth);
	}
	public int getItem_pay() {
		return item_pay;
	}
	public void setItem_pay(int item_pay) {
		this.item_pay = item_pay;
	}
	public String getItem_img() {
		return item_img;
	}
	public void setItem_img(String item_img) {
		this.item_img = convert(item_img);
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = convert(item_name);
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
