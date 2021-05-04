$(document).ready(function(){

	$("#orderBtn").click(function(){
		
		let confirm_data=confirm("다음과 같이 주문하시겠습니까?\n"+items);		
		if(confirm_data){
			const basket = $.cookie("basket");

			$.post("../order.jes",
				  basket,
				  function(data, status){	
				  	console.log(data);
				  	const obj=JSON.parse(data);
				  	if(obj.order_group_no){	
				  		alert("주문완료:[주문번호]"+obj.order_group_no);	
				  		$.removeCookie("basket", { path: '/' });// 장바구니 쿠키 삭제	
				  	}else{
				  		alert(obj.msg);
				  	}
				  	window.close();			   
				  }
			);//end post() 			
		}		
	});


	$("#anotherBtn").click(function(){		
		window.close();
	});

	$("#cancelBtn").click(function(){
		alert("장바구니를 모두 비웁니다");		
		$.removeCookie("basket", { path: '/' });// 장바구니 쿠키 삭제
		window.close();
	});

	$(".orderForm").click(function(event){
		let choice_product_name=event.target.name;
		//alert(choice_product_name);
		let basket=$.cookie("basket");
		let obj=null;
		if(basket){ //쿠키에 장바구니가 있으면
			obj=JSON.parse(basket);//json을 javascript 객체로 바꿔서
			let flag=true;
			obj.product.forEach(function(item){ // 하나씩의 아이템(품목)을 꺼내서
				
				if(item.name==choice_product_name){//품목 이름이 클릭한 상품의 이름과 같으면
					item.quantity +=1; //상품의 수량을 1증가
					//alert("if:"+item.name+":"+item.quantity);
					flag=false;
				}
			});
			if(flag){//품목 이름이 클릭한 상품의 이름과 다르면
					obj.product.push({name:choice_product_name,quantity:1});//새 품목 추가
					//alert("else:"+obj.product[obj.product.length-1].name+":"+obj.product[obj.product.length-1].quantity);
			}
		}else{ //쿠키에 장바구니가 없으면 
			obj={
				product:[{name:event.target.name,quantity:1}] //첫 품목 추가
			};
		}
		basket=JSON.stringify(obj); //자바스크립트 객체를 json 형식으로 바꿔서
		$.cookie("basket",basket, { path: '/' });// 장바구니 쿠키 저장
		window.open('html/orderForm.html', '_blank', 'toolbar=yes,scrollbars=yes,resizable=yes,top=50,left=500,width=600,height=350');
	});


$("#loginBtn").click(function(){//로그인 처리	
		
		var id=$("#id").val();
		var pw=$("#pw").val();
		
		$.post("../login.jes",
			  {			   
			    id:id,
			    pw:pw
			  },
			  function(data, status){	
				  var obj=JSON.parse(data);
				  	if(obj.name){
				  		data ="<input type='button' value='로그아웃' id='logoutBtn' class='btn btn-outline-secondary'>"
				  		$.cookie("logined",data,{expires: 1, path: '/' }); // cookie expires in 10 days 
				  		window.opener.document.getElementById("logoutDiv").innerHTML=data;
				  		window.opener.document.getElementById("logoutDiv2").innerHTML="";
				  		window.opener.document.getElementById("logoutDiv3").innerHTML="";
				  		alert(obj.name+"님 로그인 되셨습니다.");
				  		window.close();
					}else{
						alert(obj.msg);
						location.reload();	
					}							   
			  }//end function
		);//end post() 
	});//end 로그인 처리



$(document).on("click", "#logoutBtn", function(event) { //로그아웃 처리
	
	alert("로그아웃합니다.");
	$.post("logout.jes",
		  {			   
		   
		  },
		  function(data, status){		  	
		  	
		  	$.removeCookie("logined");	    
			location.reload();						   
		  }
	);//end post() 
});//end 로그아웃 처리
	
	
	$("#memberInsertBtn").click(function(){//회원 가입 처리
		
		var name=$("#name").val();
		var id=$("#id").val();
		var pw=$("#pw").val();
		var gender=$('input[name="gender"]:checked').val();
		var favorite=$("#favorite").val();	

		if( (name.length==0 || name== "") || (id.length == 0 || id== "") ||  (pw.length == 0 || pw== "") ) {
			alert("모든 항목은 필수로 입력하셔야하 합니다.");
		} else {	
			
		$.post("../memberInsert.jes",
			  {
			    name:name,
			    id:id,
			    pw:pw,
			    gender:gender,
			    favorite:favorite,
			  },
			  function(data, status){
			    alert( data);
			    window.close();
			  });
		
	}
	});
	
	
	
	$("#chatBtn").click( function () { // 챗봇-텍스트 대화
	    var chat=$("#chat").val();
	    if ((chat.length == 0 || chat == "")) {
	        alert("뭐라도 입력하시오.");
	    } else {
	        $.post("../../chat.jes", {
	            chat: chat
	        }, function (data, status) {
			    $("p").append(data+"<br>");
	        });
			alert(chat);
	    }
	});
	
	
	$("#sayBtn").click( function () { // 챗봇-음성 대화

	    alert("음성으로 챗봇에게 할 말을 녹음합니다. (제한시간 4초)");
	    	
	    	$.post("../../stt.jes", {
	    		
	    	}, function(data) {
	            alert(data);
	            $('say').empty();
			    $('say').append(data);	            
	    	});
	});	
	
	
	
	$("#reco_btn").click( function () { // 챗봇-음성 대화

	    var title = $("#book_title").val();
	    alert(title+ "와 유사한 책을 추천할게요");
	    
	    $.post("../../recommend.jes",
				  {			   
				    title:title,
				  },
				  function(data, status){	
					  alert(data);
				  }//end function
			);//end post() 

	});		
	
	
	
$("#orderBtn").click(function(){
		
		let confirm_data=confirm("다음과 같이 주문하시겠습니까?\n"+items);		
		if(confirm_data){
			alert("주문완료");
		}
		window.close();
	});

	$("#anotherBtn").click(function(){		
		window.close();
	});

	$("#cancelBtn").click(function(){
		alert("장바구니를 모두 비웁니다");		
		$.removeCookie("basket", { path: '/' });// 장바구니 쿠키 삭제
		window.close();
	});

	$(".orderForm").click(function(event){
		let choice_product_name=event.target.name;
		//alert(choice_product_name);
		let basket=$.cookie("basket");
		let obj=null;
		if(basket){ //쿠키에 장바구니가 있으면
			obj=JSON.parse(basket);//json을 javascript 객체로 바꿔서
			let flag=true;
			obj.product.forEach(function(item){ // 하나씩의 아이템(품목)을 꺼내서
				
				if(item.name==choice_product_name){//품목 이름이 클릭한 상품의 이름과 같으면
					item.quantity +=1; //상품의 수량을 1증가
					//alert("if:"+item.name+":"+item.quantity);
					flag=false;
				}
			});
			if(flag){//품목 이름이 클릭한 상품의 이름과 다르면
					obj.product.push({name:choice_product_name,quantity:1});//새 품목 추가
					//alert("else:"+obj.product[obj.product.length-1].name+":"+obj.product[obj.product.length-1].quantity);
			}
		}else{ //쿠키에 장바구니가 없으면 
			obj={
				product:[{name:event.target.name,quantity:1}] //첫 품목 추가
			};
		}
		basket=JSON.stringify(obj); //자바스크립트 객체를 json 형식으로 바꿔서
		$.cookie("basket",basket, { path: '/' });// 장바구니 쿠키 저장
		window.open('html/orderForm.html', '_blank', 'toolbar=yes,scrollbars=yes,resizable=yes,top=50,left=500,width=600,height=350');
	});

	
	
	
	$("#faceSubmitBtn").click( function () { // 페이스 테스트
			alert("찍은 사진을 서버에 전송해서 정보를 읽어옵니다.");
			var star;
			var personinfo;
			var ages1;
			var agecut = /[^0-9]/gi;
			var ages2;
			var agei;
			var gender1;
			var gendercut = /[^a-z]/gi;
			var gender2;
			
			$.post("../../face-celebrity.jes", 
					{

					}, function(data, status){
			            star = data;
			            $('star').empty();
					    $('star').append(star);
					});
			$.post("../../face-face.jes",
					{

					}, function(data, status){
			            
			            personinfo = data;
			            
			            ages1 = (personinfo).replaceAll("~", "");			            
			            ages2 = (ages1).replaceAll(agecut, "");			            
			            agei = Number(ages2);			            
			            agei = parseInt(agei / 100);
			            $('age').empty();
					    $('age').append(agei);  
					    
					    gender1 = (personinfo).replaceAll("~", "");
			            gender2 = (gender1).replaceAll(gendercut, "");
			            if(gender2=="male")
			          	  gender2 = "남성";
			            if(gender2=="female")
			          	  gender2 = "여성";
			            if(gender2=="child")
			          	  gender2 = "어린이";
			            $('gender').empty();
					    $('gender').append(gender2);	            
					});
		});

	
});