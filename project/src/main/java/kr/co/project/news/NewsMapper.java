package kr.co.project.news;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NewsMapper {

	int insert(NewsVO vo);
	
	/*
	 * 리스트
	 * 토탈카운트
	 * 조회
	 * 조회수 증가
	 * 수정
	 * 삭제
	 */
	int getTotalCount(NewsVO vo);
	List<NewsVO> getList(NewsVO vo);
	NewsVO view(NewsVO vo);
	boolean update(NewsVO vo);
	boolean delete(NewsVO vo);
}
