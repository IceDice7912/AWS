package com.mulcam.ai.web.vo;

import com.mulcam.ai.util.CafeException;

public class MemberVO {
	
	private String name;
	private String id;
	private String pw;
	private String gender;
	private int age;
	private String email;
	private String address;
	private String favorite;
	private String job;

	public MemberVO(String name, String id, String pw, String gender, int age, String email, String address,
			String favorite, String job) {
		super();
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.address = address;
		this.favorite = favorite;
		this.job = job;
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) throws CafeException {
		if(name!=null) {
			this.name = name;
		}else {
			throw new CafeException("이름이 입력되지 않았습니다");
		}
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) throws CafeException {
		if(id!=null) {
			this.id = id;
		}else {
			throw new CafeException("아이디가 입력되지 않았습니다");
		}
	}
	
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) throws CafeException {
		if(pw!=null) {
			this.pw = pw;
		}else {
			throw new CafeException("패스워드가 입력되지 않았습니다");
		}
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) throws CafeException {
		if(gender!=null) {
			this.gender = gender;
		}else {
			throw new CafeException("성별이 입력되지 않았습니다");
		}
	}	
	public int getAge() {
		return age;
	}
	public void setAge(int age) throws CafeException {
		Integer ach = null;
		ach = age;
		if(ach!=null) {
			this.age = age;
		}else {
			throw new CafeException("나이가 입력되지 않았습니다");
		}
	}	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) throws CafeException {
		if(email!=null) {
			this.email = email;
		}else {
			throw new CafeException("이메일이 입력되지 않았습니다");
		}
	}	
	public String getAddress() {
		return address;
	}
	public void setAddrss(String address) throws CafeException {
		if(address!=null) {
			this.address = address;
		}else {
			throw new CafeException("pw가 입력되지 않았습니다");
		}
	}	
	
	public String getFavorite() {
		return favorite;
	}
	public void setFavorite(String favorite) throws CafeException {
		if(favorite!=null) {
			this.favorite = favorite;
		}else {
			throw new CafeException("좋아하는 장르가 입력되지 않았습니다");
		}
	}	
	public String getJob() {
		return job;
	}
	public void setJob(String job) throws CafeException {
		if(job!=null) {
			this.job = job;
		}else {
			throw new CafeException("직업이 입력되지 않았습니다");
		}
	}
	
	
	public MemberVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "MemberVO [name=" + name + ", id=" + id + ", pw=" + pw + ", gender=" + gender + ", age=" + age
				+ ", email=" + email + ", address=" + address + ", favorite=" + favorite + ", job=" + job + "]";
	}
		
	
}
