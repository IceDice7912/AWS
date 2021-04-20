package com.mulcam.ai.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mulcam.ai.web.service.MemberService;
import com.mulcam.ai.web.vo.MemberVO;

@Controller
public class MemberController {
	
	@Autowired 
	MemberService memberService;
	
	
	@RequestMapping(value = "logout.jes", 
			method= {RequestMethod.POST},
			produces = "application/text; charset=utf8")			
	@ResponseBody
	public String logout(HttpServletRequest request,
			HttpServletResponse response){
		
			HttpSession session=request.getSession(false);
			session.invalidate();
			return "";
		
	}	
	
	@RequestMapping(value = "login.jes", 
			method= RequestMethod.POST,
			produces = "application/text; charset=utf8")			
	@ResponseBody
	public String login(HttpServletRequest request,
			HttpServletResponse response){
		String name=request.getParameter("name");
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		String gender=request.getParameter("gender");
		int age = Integer.parseInt(request.getParameter("age"));
		String email=request.getParameter("email");
		String address=request.getParameter("address");		
		String favorite=request.getParameter("favorite");		
		String job=request.getParameter("job");		

		
		JSONObject json=new JSONObject();
		
		try {
			MemberVO m=new MemberVO(name, id, pw, gender, age, email, address, favorite, job); 
			System.out.println(m);
			String name1=memberService.login(m);
			if(name1!=null) {
				HttpSession session=request.getSession();
				session.setAttribute("member", m);
				json.put("name", name1);// {"name":"전은수"}
			}else {
				json.put("msg", "로그인 실패");// {"msg":"로그인 실패"}
			}
		}catch(Exception e) {
			e.printStackTrace();
			json.put("msg", e.getMessage());// {"msg":"NullPointerException"}
		}
		
		return json.toJSONString();

	}

	
	@RequestMapping(value = "memberInsert.jes", 
			method= {RequestMethod.POST},
			produces = "application/text; charset=utf8")	
	@ResponseBody
	public void memberInsert(HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		String name=request.getParameter("name");
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		String gender=request.getParameter("gender");
		int age = Integer.parseInt(request.getParameter("age"));
		String email=request.getParameter("email");
		String address=request.getParameter("address");
		String favorite=request.getParameter("favorite");
		String job=request.getParameter("job");

		try {
			MemberVO m=new MemberVO(name, id, pw, gender, age, email, address, favorite, job); 
			memberService.memberInsert(m);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}	

}
