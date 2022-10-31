package kr.co.project.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOExceptionWithCause;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberController {
	
	@Autowired
	MemberService service;
	
	@GetMapping("/member/join.do")
	public String join() {
		return "member/join";
	}
	
	@PostMapping("member/join.do")
	public String join(MemberVO vo, Model model) {
		if(service.insert(vo)>0) {
			model.addAttribute("msg", "정상적으로 회원가입되었습니다.");
			model.addAttribute("url", "login.do");
		} else {
			model.addAttribute("msg", "회원 가입 오류");
		}
		return "common/alert";
	}
	
	@GetMapping("/member/emailDupCheck.do")
	public void emailDupCheck(@RequestParam String email, HttpServletResponse res) throws IOException {
		int count = service.emailDupCheck(email);
		boolean r = false;
		if (count == 1) r = true;
		
		PrintWriter out = res.getWriter();
		out.print(r);
		out.flush();
	}
	
	@GetMapping("/member/login.do")
	public String login() {
		return "member/login";
	}
	
	@PostMapping("/member/login.do")
	public String login(MemberVO vo, HttpSession sess, Model model) {
		if(service.loginCheck(vo, sess)) {
			return "redirect:/board/index.do";
		} else {
			model.addAttribute("msg", "이메일, 비밀번호를 확인해주세요");
			return "common/alert";
		}
	}
	
	@GetMapping("/member/logout.do")
	public String logout(Model model, HttpServletRequest req) {
		HttpSession sess = req.getSession();
		sess.invalidate();			// 세션초기화 (세션의 모든 데이터 삭제)
//		sess.removeAttribute("loginInfo");	// 세션에 있는 해당 값만 삭제, 해당값이 없으면 에러가 남
		model.addAttribute("msg", "로그아웃되었습니다");
		model.addAttribute("url", "/project/board/index.do");
		return "common/alert";
	}
	
	@GetMapping("/member/findEmail.do")
	public String findEmail() {
		return "member/findEmail"; 
	}
	
	@PostMapping("/member/findEmail.do")
	public String findEmail(MemberVO param, Model model) {
		MemberVO vo = service.findEmail(param);
		if(vo != null) {
			model.addAttribute("result", vo.getEmail());
		}
		return "common/result"; 
	}

	@GetMapping("/member/findPwd.do")
	public String findPwd() {
		return "member/findPwd"; 
	}
	
	@PostMapping("/member/findPwd.do")
	public String findPwd(MemberVO param, Model model) {
		MemberVO vo = service.findPwd(param);
		if(vo != null) {
			model.addAttribute("result", vo.getEmail());
		}
		return "common/result"; 
	}
}
