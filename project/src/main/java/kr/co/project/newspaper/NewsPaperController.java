package kr.co.project.newspaper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NewsPaperController {
	
	@Autowired
	NewsPaperService service;
	
	@RequestMapping("/newspaper/index.do")
	public String index(Model model, NewsPaperVO vo) {
		model.addAttribute("data", service.index(vo));
		return "newspaper/index";
	}
	
	@GetMapping("/newspaper/write.do")
	public String write() {
		return "newspaper/write";
	}
	
	@PostMapping("newspaper/insert.do")
	public String insert(NewsPaperVO vo, Model model) {
		if (service.insert(vo)) { 
			model.addAttribute("msg", "정상적으로 저장되었습니다.");
			model.addAttribute("url", "index.do");
			return "common/alert";
		} else {
			model.addAttribute("msg", "저장에 실패했습니다.");
			return "common/alert";
		}
	}
	
	@GetMapping("newspaper/view.do")
	public String view(NewsPaperVO vo, Model model) {
		model.addAttribute("data", service.view(vo.getNo()));
		return "newspaper/view";
	}
	
	@GetMapping("/newspaper/edit.do")
	public String edit(NewsPaperVO vo, Model model) {
		NewsPaperVO data = service.edit(vo.getNo());
		model.addAttribute("data", data);
		return "newspaper/edit";
	}
	
	@PostMapping("/newspaper/update.do")
	public String update(NewsPaperVO vo, Model model) {
		if (service.update(vo)) {
			model.addAttribute("msg", "정상적으로 수정되었습니다.");
			model.addAttribute("url", "view.do?no="+vo.getNo());
			return "common/alert";
		} else {
			model.addAttribute("msg", "수정 실패");
			return "common/alert";
		}
	}
	@GetMapping("/newspaper/delete.do")
	public String delete(NewsPaperVO vo, Model model) {
		if (service.delete(vo.getNo())) {
			model.addAttribute("msg", "정상적으로 삭제되었습니다.");
			model.addAttribute("url", "index.do");
			return "common/alert";
		} else {
			model.addAttribute("msg", "삭제 실패");
			return "common/alert";
		}
	}
}
