package kr.co.project.newspaper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsPaperServiceImpl implements NewsPaperService {

	@Autowired
	NewsPaperMapper mapper;
	
	@Override
	public Map index(NewsPaperVO vo) {
		int totalCount = mapper.getTotalCount(vo);	// 총 게시물 수
		// 총 페이지 수
		int totalPage = totalCount / vo.getRowPerPage();	
		if (totalCount % vo.getRowPerPage() > 0) totalPage++;
		
		// 시작 인덱스
		int startIdx = (vo.getPage()-1) * vo.getRowPerPage();
		vo.setStartIdx(startIdx);
		List<NewsPaperVO> list = mapper.getList(vo);

		// 페이징 처리
		int endPage = (int)Math.ceil(vo.getPage() / 10.0)*10;
		int startPage = endPage - 9;
		if (endPage > totalPage) endPage = totalPage;
		boolean prev = startPage > 1 ? true : false;
		boolean next = endPage < totalPage ? true : false;
		
		Map map = new HashMap();
//		map.put("page", vo.getPage());		// BoardVO로 파라미터를 받기때문에 다시 넣어줄 필요가 없음 (객체 생성시 생성자에 의해 페이지번호 자동 설정)
		map.put("totalCount", totalCount);
		map.put("totalPage", totalPage);
		map.put("list", list);
		map.put("endPage", endPage);
		map.put("startPage", startPage);
		map.put("prev", prev);
		map.put("next", next);
		return map;
	}

	@Override
	public NewsPaperVO view(int no) {
		mapper.updateViewcount(no);
		return mapper.view(no);
	}

	@Override
	public NewsPaperVO edit(int no) {
		return mapper.view(no);
	}

	@Override
	public boolean update(NewsPaperVO vo) {
		return mapper.update(vo) > 0 ? true : false;
	}

	@Override
	public boolean delete(int no) {
		return mapper.delete(no) > 0 ? true : false;
	}

	@Override
	public boolean insert(NewsPaperVO vo) {
		return mapper.insert(vo) > 0 ? true : false;
	}

}
