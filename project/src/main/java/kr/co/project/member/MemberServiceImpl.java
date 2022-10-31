package kr.co.project.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.SendMail;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberMapper mapper;
	
	@Override
	public int insert(MemberVO vo) {
		return mapper.insert(vo);
	}

	@Override
	public int emailDupCheck(String email) {
		
		return mapper.emailDupCheck(email);
	}

	@Override
	public boolean loginCheck(MemberVO vo, HttpSession sess) {
		boolean r = false;
		MemberVO loginInfo = mapper.loginCheck(vo);
		if (loginInfo != null) {
			r = true;
			// 로그인 성공시 세션에 저장
			sess.setAttribute("loginInfo", loginInfo);
		}
		return r;
	}

	@Override
	public MemberVO findEmail(MemberVO vo) {
		return mapper.findEmail(vo);
	}
	
	@Override
	public MemberVO findPwd(MemberVO vo) {
		// find
		MemberVO mv = mapper.findEmail(vo);
		
		if (mv != null) {
			// 임시비밀번호 생성 (영문 2자, 숫자 2자)
			String temp = "";
			for (int i=0; i<2; i++) {
				temp += (char)(Math.random()*26 + 65);
			}
			for (int i=0; i<2; i++) {
				temp += (int)(Math.random()*9);
			}
			
			// 임시비밀번호 업데이트
			vo.setPwd(temp);
			mapper.updateTempPwd(vo);
			
			// email 발송
//			SendMail.sendMail("toi90@naver.com", vo.getEmail(), "임시비밀번호 발급", "임시비밀번호 : "+temp);
			SendMail.sendMail("toi90@naver.com", "anjp0304@gmail.com", "임시비밀번호 발급", "임시비밀번호 : "+temp);
			
			return mv;
		} else {
			return null;
		}
	}

}
