package kr.co.project.reply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	ReplyMapper mapper;
	
	@Override
	public Map index(ReplyVO vo) {
		int totalCount = mapper.getTotalCount(vo);	// 총 게시물 수
		// 총 페이지 수
		int totalPage = totalCount / vo.getRowPerPage();	
		if (totalCount % vo.getRowPerPage() > 0) totalPage++;
		
		// 시작 인덱스
		int startIdx = (vo.getPage()-1) * vo.getRowPerPage();
		vo.setStartIdx(startIdx);
		List<ReplyVO> list = mapper.getList(vo);

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
	public ReplyVO view(int no) {
		mapper.updateViewcount(no);
		return mapper.view(no);
	}

	@Override
	public ReplyVO edit(int no) {
		return mapper.view(no);
	}

	@Override
	public boolean update(ReplyVO vo) {
		return mapper.update(vo) > 0 ? true : false;
	}

	@Override
	public boolean delete(int no) {
		return mapper.delete(no) > 0 ? true : false;
	}

	@Override
//	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
	public boolean insert(ReplyVO vo) {
		boolean r = mapper.insert(vo) > 0 ? true : false;
		if (r) mapper.gnoUpdate(vo.getNo());
		
		return r;
	}

	@Override
	public boolean reply(ReplyVO vo) {
		mapper.onoUpdate(vo);	// 부모의 gno와 같고, 부모의 ono보다 큰 ono에 +1
		vo.setOno(vo.getOno()+1);
		vo.setNested(vo.getNested()+1);
		
		return mapper.reply(vo) > 0 ? true : false;
	}

}
