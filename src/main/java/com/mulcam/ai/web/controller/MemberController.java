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
			method= {RequestMethod.GET,RequestMethod.POST},
			produces = "application/text; charset=utf8")			
	@ResponseBody
	public String logout(HttpServletRequest request,
			HttpServletResponse response){
		
			HttpSession session=request.getSession(false);
			session.invalidate();
			return "";
		
	}	    
	
	@RequestMapping(value = "login.jes", 
			method= {RequestMethod.GET,RequestMethod.POST},
			produces = "application/text; charset=utf8")			
	@ResponseBody
	public String login(HttpServletRequest request,
			HttpServletResponse response){
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");

		  
		JSONObject json=new JSONObject();
		
		try {
			MemberVO m=new MemberVO(id, pw); 
			System.out.println(m);
			String name=memberService.login(m);
			if(name!=null) {
				HttpSession session=request.getSession();
				session.setAttribute("member", m);
				json.put("name", name);// {"name":"전은수"}
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
			method=  {RequestMethod.GET,RequestMethod.POST},
	 		produces = "application/text; charset=utf8")	
	@ResponseBody
	public String memberInsert(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String name=request.getParameter("name");
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		String gender=request.getParameter("gender");
		String ages=request.getParameter("age");		
		int age;
		if(ages!=null) {
			age=Integer.parseInt(ages); 
		} else {
				System.out.println("어째서인지 자꾸 age가 null 처리되어서 어쩔 수 없이 0 으로 셋팅합니다.");
				age=0;
		} 
		String email=request.getParameter("email"); 
		String address=request.getParameter("address");
		String favorite=request.getParameter("favorite");
		String job=request.getParameter("job");
		System.out.println(name);
		System.out.println(id);
		System.out.println(pw);
		System.out.println(gender);
		System.out.println(age);
		System.out.println(email);	
		System.out.println(address);
		System.out.println(favorite);
		System.out.println(job);	

		try {
			MemberVO m=new MemberVO(name, id, pw, gender, age, email, address, favorite, job); 
			memberService.memberInsert(m);
			return name+"님 회원가입 되셨습니다";
		}catch(Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	

}
