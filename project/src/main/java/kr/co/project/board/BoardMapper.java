package kr.co.project.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {

	public int insert(BoardVO vo);
	public int getTotalCount(BoardVO vo);
	List<BoardVO> getList(BoardVO vo);
	BoardVO view(int no);
	
	void updateViewcount(int no);
	int update(BoardVO vo);
	int delete(int no);
	
}
