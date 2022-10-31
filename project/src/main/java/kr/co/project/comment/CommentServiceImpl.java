package kr.co.project.comment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentMapper mapper;
	
	@Override
	public Map index(CommentVO vo) {
		int totalCount = mapper.getTotalCount(vo);
		
		int totalPage = totalCount / vo.getRowPerPage();	
		if (totalCount % vo.getRowPerPage() > 0) totalPage++;
		
		int startIdx = (vo.getPage()-1) * vo.getRowPerPage();
		vo.setStartIdx(startIdx);
		List<CommentVO> list = mapper.getList(vo);
		
		int endPage = (int)Math.ceil(vo.getPage() / 10.0)*10;
		int startPage = endPage - 9;
		if (endPage > totalPage) endPage = totalPage;
		boolean prev = startPage > 1 ? true : false;
		boolean next = endPage < totalPage ? true : false;
		
		Map map = new HashMap();
		map.put("list", list);
		map.put("totalCount", totalCount);
		map.put("totalPage", totalPage);
		map.put("startIdx", startIdx);
		map.put("endPage", endPage);
		map.put("startPage", startPage);
		map.put("prev", prev);
		map.put("next", next);
		
		return map;
	}

	@Override
	public int insert(CommentVO vo) {
		return mapper.insert(vo);
	}

	@Override
	public int delete(CommentVO vo) {
		return mapper.delete(vo.getNo());
	}

}
