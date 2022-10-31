package kr.co.project.board;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardVO {

	private int no;
	private String title;
	private String content;
	private Timestamp regdate;
	private int viewcount;
	private int member_no;
	private String filename_org;
	private String filename_real;
	
	private String member_name;
	private String comment_count;
	private int page;
	private String stype;
	private String sword;
	private int startIdx;
	private int rowPerPage;
	
	public BoardVO() {
//		this.page = 1;
//		this.rowPerPage = 10;
		this(1, 10);
	}
	
	public BoardVO(int page, int rowPerPage) {
		this.page = page;
		this.rowPerPage = rowPerPage;
	}
	
}
