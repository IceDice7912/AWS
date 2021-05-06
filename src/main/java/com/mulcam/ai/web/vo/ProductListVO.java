package com.mulcam.ai.web.vo;

public class ProductListVO {
	private String title, author, publisher, category, imgurl;
	private int price;
	
		
	public ProductListVO(String title, String author, String publisher, String category, String imgurl, int price) {
		super();
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.category = category;
		this.imgurl = imgurl;
		this.price = price;
	}
	
		
	
	public ProductListVO() {
		super();
	}

	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}


	@Override
	public String toString() {
		return "[title=" + title + ", author=" + author + ", publisher=" + publisher + ", category="
				+ category + ", imgurl=" + imgurl + ", price=" + price + "]";
	}
	
	
}
