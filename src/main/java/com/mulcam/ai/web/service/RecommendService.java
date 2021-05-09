package com.mulcam.ai.web.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mulcam.ai.web.dao.RecommendDAO;
import com.mulcam.ai.web.vo.RecommendVO;


@Service
public class RecommendService {
	@Autowired 
	RecommendDAO recommendDAO;
	
	public String findCategory(String title) {
		System.out.println("RecommendService 수행중");
		return recommendDAO.findCategory(title);
	}
	
	public ArrayList<RecommendVO> recommendBook(String isbn) {
		return recommendDAO.recommendBook(isbn);
	}

}
