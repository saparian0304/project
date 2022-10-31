package kr.co.project.reply;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyMapper {

	public int insert(ReplyVO vo);
	public int getTotalCount(ReplyVO vo);
	List<ReplyVO> getList(ReplyVO vo);
	ReplyVO view(int no);
	
	void updateViewcount(int no);
	int update(ReplyVO vo);
	int gnoUpdate(int gno);
	int onoUpdate(ReplyVO vo);
	int reply(ReplyVO vo);
	int delete(int no);
	
}
