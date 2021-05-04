package com.mulcam.ai.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mulcam.ai.web.dao.BookDAO;
import com.mulcam.ai.web.vo.BookVO;
import com.mulcam.ai.web.vo.MemberVO;

@Service
public class BookService {
	@Autowired
	BookDAO bookDAO;

	public List<BookVO> bookList() {
		return bookDAO.bookList();
	}

	public List<BookVO> selectbookisbn() {
		return bookDAO.selectbookisbn();
	}

}
