package kr.co.project.comment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {

	int insert(CommentVO vo);
	int getTotalCount(CommentVO vo);
	List<CommentVO> getList(CommentVO vo);
	int delete(int no);
}
