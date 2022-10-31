package kr.co.project.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.project.board.BoardMapper;
import kr.co.project.board.BoardVO;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/config/servlet-context.xml")
@Log4j
public class BoardMapperTests {

	@Autowired
	private BoardMapper mapper;

//	@Test
	public void testObj() {
		log.info(mapper);
	}
	
	@Test
	public void testInsert() {
		BoardVO vo = new BoardVO();
		vo.setTitle("제목 : test1");
		vo.setContent("내용 : test1");
//		vo.setFilename_org("original name test");
		
		int r = 0;
		for (int j=1; j<=10; j++) {
			for (int i=1; i<=10; i++) {
				vo.setTitle("제목 : test" + i);
				vo.setContent("내용 : test" + i);
				r += mapper.insert(vo);
			}
		}
		
		log.info("등록 개수 : " + r);
	}
	
//	@Test
	public void count() {
		Map map = new HashMap();
		map.put("stype", "title");
		map.put("sword", "제목");
//		int totalCount = mapper.getTotalCount(map);
//		log.info("총 카운트 : " + totalCount);
	}
	
//	@Test
	public void list() {
		Map map = new HashMap();
//		map.put("stype", "title");
//		map.put("sword", "제목");
		map.put("startIdx", 10);
		map.put("rowPerPage", 10);
//		List<BoardVO> list = mapper.getList(map);
//		log.info(list);
//		list.forEach(vo -> log.info(vo));
	}
	
//	@Test
	public void view() {
		mapper.updateViewcount(2);
		log.info(mapper.view(3));
	}
	
//	@Test
	public void update() {
		BoardVO vo = new BoardVO();
		vo.setTitle("제목 수정");
		vo.setContent("내용 수정");
		vo.setNo(3);
		log.info(mapper.update(vo));
	}
	
//	@Test
	public void delete() {
		log.info("delete : " +mapper.delete(23));
	}
	
}
