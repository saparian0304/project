package kr.co.project.mongodb;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StudentController {
	
	@Autowired
	StudentDAO sdao;
	
	@RequestMapping("/student/index")
	public void index() {}
	
	@RequestMapping(value="/student/insert")
	@ResponseBody
	public Map<String, Object> insert(@RequestBody Map map) {
		return sdao.insert(map);
	}
	
	@RequestMapping("/student/list")
	@ResponseBody
	public List<Map> list(@RequestBody Map map) {
		return sdao.list(map);
	}
}
