package com.mulcam.ai.web.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mulcam.ai.web.vo.RecommendVO;

@Mapper
@Repository("recommendDAO")
public interface RecommendDAO {

	public String findCategory(String title);

	public RecommendVO recommendBook(String isbn);	
	

}
