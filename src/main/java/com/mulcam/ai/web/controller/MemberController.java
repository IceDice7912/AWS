package com.mulcam.ai.web.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;

import org.apache.catalina.startup.SetAllPropertiesRule;
import org.apache.ibatis.javassist.compiler.MemberResolver;
import org.apache.jasper.tagplugins.jstl.core.Out;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.sym.Name;
import com.mulcam.ai.web.service.MemberService;
import com.mulcam.ai.web.vo.BoardVO;
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
			
			System.out.println("로그아웃 버튼 눌려졌음.");
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

		try {
			MemberVO m=new MemberVO(name, id, pw, gender, age, email, address, favorite, job); 
			memberService.memberInsert(m);
			return name+"님 회원가입 되셨습니다";
		}catch(Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	
	@RequestMapping(value = "memberList.jes", 
			method=  {RequestMethod.GET,RequestMethod.POST},
	 		produces = "application/text; charset=utf8")	
	@ResponseBody
	public String memberList(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
			
			List<HashMap<String, Object>> m;
		
			List<Object> NAME = new ArrayList<Object>();
			List<Object> ID = new ArrayList<Object>();
			List<Object> PW = new ArrayList<Object>();
			List<Object> GENDER = new ArrayList<Object>();
			List<Object> AGE = new ArrayList<Object>();
			List<Object> EMAIL = new ArrayList<Object>();
			List<Object> ADDRESS = new ArrayList<Object>();
			List<Object> FAVORITE = new ArrayList<Object>();
			List<Object> JOB = new ArrayList<Object>();
			
			System.out.println("맴버 리스트를  출력합니다.");
		
		try {
			//Select * from Member 결과를 m 리스트에 담음.
			m= memberService.memberList();
//			System.out.println(m);
			
			//m 리스트에서 정보들 뽑아와서 그걸 또 각자 리스트에 담기(배열로하면 동적 할당이 안됨) //
			for(int i=0; i<m.size(); i++) {
				NAME.add(((Map) m.get(i)).get("NAME").toString());
				ID.add(((Map) m.get(i)).get("ID").toString());
				PW.add(((Map) m.get(i)).get("PW").toString());
				GENDER.add(((Map) m.get(i)).get("GENDER").toString());
				AGE.add(((Map) m.get(i)).get("AGE").toString());
				EMAIL.add(((Map) m.get(i)).get("EMAIL").toString());
				ADDRESS.add(((Map) m.get(i)).get("ADDRESS").toString());
				FAVORITE.add(((Map) m.get(i)).get("FAVORITE").toString());
				JOB.add(((Map) m.get(i)).get("JOB").toString());
				
				
			}			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("테스트 - 이름들만 출력");
		for(int i=0; i<NAME.size(); i++) {
			System.out.print(NAME.get(i) + "  ");
		}
		
		String NAMES = NAME.toString();
		return NAMES;	
		
	}
	

}
