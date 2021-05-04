package com.mulcam.ai.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mulcam.ai.web.vo.BookVO;

@Mapper
@Repository("bookDAO")
public interface BookDAO {

	public List<BookVO> bookList();

	public List<BookVO> selectbookisbn();

}
