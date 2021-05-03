package com.mulcam.ai.web.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.mulcam.ai.util.CafeException;
import com.mulcam.ai.web.dao.MemberDAO;
import com.mulcam.ai.web.vo.BoardVO;
import com.mulcam.ai.web.vo.MemberVO;

@Service
public class MemberService {
	@Autowired
	MemberDAO memberDAO;
	
	public void memberInsert(MemberVO m) throws CafeException{
		memberDAO.memberInsert(m);
	}

	public String login(MemberVO m) throws CafeException{
		return memberDAO.login(m);		
	}

	public List<HashMap<String, Object>> memberList() {
		// TODO Auto-generated method stub
		return memberDAO.memberList();
	}


}
