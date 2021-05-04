package com.mulcam.ai.web.vo;

import com.mulcam.ai.util.CafeException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookVO {
	
	private String title;
	private String Author;
	private int Price;
	private String Publisher;
	private int Isbn;
	private String Category;
	private String Imgurl;
	private String Detail;
	
	public BookVO() throws CafeException {
		super();
	}
	
	public BookVO(int Isbn) throws CafeException {
		super();
		setIsbn(Isbn);
	}	
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}

	public String getPublisher() {
		return Publisher;
	}

	public void setPublisher(String publisher) {
		Publisher = publisher;
	}

	public int getIsbn() {
		return Isbn;
	}

	public void setIsbn(int isbn) {
		Isbn = isbn;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getImgurl() {
		return Imgurl;
	}

	public void setImgurl(String imgurl) {
		Imgurl = imgurl;
	}

	public String getDetail() {
		return Detail;
	}

	public void setDetail(String detail) {
		Detail = detail;
	}

	@Override
	public String toString() {
		return "BookVO [title=" + title + ", Author=" + Author + ", Price=" + Price + ", Publisher=" + Publisher
				+ ", Isbn=" + Isbn + ", Category=" + Category + ", Imgurl=" + Imgurl + ", Detail=" + Detail + "]";
	}

}
