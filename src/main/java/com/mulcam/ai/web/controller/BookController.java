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
import javax.swing.ListModel;

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
import com.mulcam.ai.web.service.BookService;
import com.mulcam.ai.web.service.MemberService;
import com.mulcam.ai.web.vo.BoardVO;
import com.mulcam.ai.web.vo.BookVO;
import com.mulcam.ai.web.vo.MemberVO;


@Controller
public class BookController {
	
	@Autowired 
	BookService bookService;
	
	@GetMapping("bookList")
	public String showBook(Model model) throws Exception{

		System.out.println("책 목록 호출됨");
		
		List<BookVO> bookList = new ArrayList<>();
		bookList = bookService.bookList();
		model.addAttribute("bookList", bookList);
		System.out.println("리턴하려는 bookList : " + bookList.toString());
		return "bookList"; 
		
	}
	
	
	@GetMapping("selectbookisbn")
	public String Bookisbn(Model model) throws Exception{

		System.out.println("선택학 isbn에 대한 책 정보 출력");
		
		List<BookVO> selectbookisbn = new ArrayList<>();
		selectbookisbn = bookService.selectbookisbn();
		model.addAttribute("selectbookisbn", selectbookisbn);
		System.out.println("선택한 isbn에 대한 book 정보 : " + selectbookisbn.toString());
		return "selectbookisbn"; 
		
	}
	


}
