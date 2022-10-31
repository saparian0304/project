package kr.co.project.newspaper;

import java.util.Map;

public interface NewsPaperService {

	// 목록
	Map index(NewsPaperVO vo);
	
	// 상세
	NewsPaperVO view(int no);
	
	// 수정 폼
	NewsPaperVO edit(int no);
	
	// 수정처리
	boolean update(NewsPaperVO vo);
	
	// 삭제처리
	boolean delete(int no);
	
	// 등록 처리
	boolean insert(NewsPaperVO vo);
	
}
