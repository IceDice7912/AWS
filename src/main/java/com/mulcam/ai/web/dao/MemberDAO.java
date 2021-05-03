package com.mulcam.ai.web.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import com.mulcam.ai.web.vo.MemberVO;

@Mapper
@Repository("memberDAO")
public interface MemberDAO {
	
	public void memberInsert(MemberVO memberVO) ;

	public String login(MemberVO memberVO);

	public List<HashMap<String, Object>> memberList();


}
