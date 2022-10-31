package kr.co.project.newspaper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NewsPaperMapper {

	public int insert(NewsPaperVO vo);
	public int getTotalCount(NewsPaperVO vo);
	List<NewsPaperVO> getList(NewsPaperVO vo);
	NewsPaperVO view(int no);
	
	void updateViewcount(int no);
	int update(NewsPaperVO vo);
	int delete(int no);
	
}
