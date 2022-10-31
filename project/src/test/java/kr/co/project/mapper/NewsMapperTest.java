package kr.co.project.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.project.news.NewsMapper;
import kr.co.project.news.NewsVO;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/config/servlet-context.xml")
@Log4j
public class NewsMapperTest {

	@Autowired
	private NewsMapper mapper;

//	@Test
	public void testObj() {
		log.info(mapper);
	}
	
	@Test
	public void testInsert( ) {
		int cnt = 0;
		for (int i=0; i<100; i++) {
			NewsVO vo = new NewsVO();
			vo.setTitle("제목 테스트" + i);
			vo.setContent("내용 테스트" + i);
			vo.setWriter("테스트작성자" + i);
			cnt += mapper.insert(vo);
		}
		log.info("cnt : " + cnt);
	}
	
//	@Test
	public void getCount() {
		NewsVO vo = new NewsVO();
		vo.setSearchType("content");
		vo.setSearchWord("내용");
		log.info(mapper.getTotalCount(vo));
	}
//	@Test
	public void getList() {
		NewsVO vo = new NewsVO();
		vo.setSearchType("content");
		vo.setSearchWord("내용");
		log.info(mapper.getList(vo));
	}
	
//	@Test
	public void getView() {
		NewsVO vo = new NewsVO();
		vo.setNo(1);
		log.info(mapper.view(vo));
	}
	
//	@Test
	public void update() {
		NewsVO vo = new NewsVO();
		vo.setNo(1);
		vo.setTitle("제목 수정 테스트");
		vo.setContent("내용 수정 테스트");
		log.info(mapper.update(vo));
	}
	
	
}
