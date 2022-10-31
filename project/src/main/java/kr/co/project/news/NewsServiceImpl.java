package kr.co.project.news;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.project.board.BoardVO;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	NewsMapper mapper;
	
	@Override
	public Map getList(NewsVO vo) {
		int totalCount = mapper.getTotalCount(vo);	// 총 게시물 수
		// 총 페이지 수
		int totalPage = totalCount / vo.getRowPerPage();	
		if (totalCount % vo.getRowPerPage() > 0) totalPage++;
		
		// 시작 인덱스
		int startIdx = (vo.getPage()-1) * vo.getRowPerPage();
		vo.setStartIdx(startIdx);

		// 페이징 처리
		int endPage = (int)Math.ceil(vo.getPage() / 10.0)*10;
		int startPage = endPage - 9;
		if (endPage > totalPage) endPage = totalPage;
		boolean prev = startPage > 1 ? true : false;
		boolean next = endPage < totalPage ? true : false;
		
		Map map = new HashMap();
		map.put("list", mapper.getList(vo));
		map.put("totalCount", totalCount);
		map.put("totalPage", totalPage);
		map.put("endPage", endPage);
		map.put("startPage", startPage);
		map.put("prev", prev);
		map.put("next", next);
		return map;
	}

}
