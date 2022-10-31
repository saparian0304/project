package kr.co.project.mapper;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.project.board.BoardVO;
import kr.co.project.comment.CommentMapper;
import kr.co.project.comment.CommentVO;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/config/servlet-context.xml")
@Log4j
public class CommentMapperTests {

	@Autowired
	private CommentMapper mapper;

//	@Test
	public void testObj() {
		log.info(mapper);
	}
	
	@Test
	public void testInsert() {
		
		CommentVO vo = new CommentVO();
		for (int i=1; i<13; i++) {
			vo.setContent("댓글" + i);
			vo.setBoard_no(149);
			vo.setMember_no(10);
			vo.setTablename("board");
			mapper.insert(vo);
		}
		
	}
	
	
	
}
