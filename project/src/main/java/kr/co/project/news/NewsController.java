package kr.co.project.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewsController {
	
	@Autowired
	NewsService service;
	
	@GetMapping("/news/index.do")
	public String index(NewsVO vo, Model model) {
		model.addAttribute("data", service.getList(vo));
		
		return "news/index";
	}
}
