package kr.co.project.news;

import java.sql.Date;

import lombok.Data;

@Data
public class NewsVO {

	public int no;
	public String title;
	public String content;
	public String writer;
	public Date postdate;
	public int viewcount;
	
	public String SearchType;
	public String SearchWord;
	private int startIdx;
	private int rowPerPage;
	private int page;
	
	public NewsVO() {
		this(1, 10);
	}
	
	public NewsVO(int page, int rowPerPage) {
		this.page = page;
		this.rowPerPage = rowPerPage;
	}
	
}
