var page_num = 1;
function next_click(){	
	page_num++;
}
function prev_click(){	
	page_num--;
}


$(document).ready(function(){
	$.post("../../../productListAll.jes", {
		page_num : page_num
	},
		function(data, status){
			let json_msg = data;
			let js_msg = JSON.parse(json_msg);
			let my_array = js_msg.data;
			let sumMsg='';
			var i;
			for (i=0; i<my_array.length; i++){
				sumMsg += "<ul class='prod_li'> <li> <div class='prodSet'> <p class='prod_img'> <span class='prod_imgSet'> <a href=''> <img src='" + my_array[i].book.imgurl 
						+ "border='0'> </a>	</span> </p> </div> <div class='prod_info'> <div class='prod_name'> <a href='../../html/ProductDetail/ProductDetail.html'>"
						+ my_array[i].book.title + "</a><br><br> </div> <div class='prod_pubGrp'> <span class='prod_auth'> <a href=''>" 
						+ my_array[i].book.author + "</a> 저 	</span> <em class='divi'>｜</em> <span class='prod_pub'>"
						+ my_array[i].book.publisher + "</span> <em class='divi'>｜</em> </div> <div class='prod_price'> <em class='price'>" 
						+ my_array[i].book.price + "</em> 원 </div> </div> <div class='prod_btn'> <a href='#a' class='btn1'>카트에 넣기</a>	<a href='#a' class='btn2'>바로 구매하기</a></div></li></ul>";
			}
			$('#prod_list').html(sumMsg);
				
		});	
	
	
	$("#nextBtn").click(function(){		
		if(page_num > 24){
			alert("목록이 끝났습니다.");
		}
		else{
			$('#paging').text(page_num);
			$.post("../../../productListAll.jes", {
				page_num : page_num
			},
				function(data, status){
					let json_msg = data;
					let js_msg = JSON.parse(json_msg);
					let my_array = js_msg.data;
					let sumMsg='';
					var i;
					for (i=0; i<my_array.length; i++){
						sumMsg += "<ul class='prod_li'> <li> <div class='prodSet'> <p class='prod_img'> <span class='prod_imgSet'> <a href=''> <img src='" + my_array[i].book.imgurl 
								+ "border='0'> </a>	</span> </p> </div> <div class='prod_info'> <div class='prod_name'> <a href='../../html/ProductDetail/ProductDetail.html'>"
								+ my_array[i].book.title + "</a><br><br> </div> <div class='prod_pubGrp'> <span class='prod_auth'> <a href=''>" 
								+ my_array[i].book.author + "</a> 저 	</span> <em class='divi'>｜</em> <span class='prod_pub'>"
								+ my_array[i].book.publisher + "</span> <em class='divi'>｜</em> </div> <div class='prod_price'> <em class='price'>" 
								+ my_array[i].book.price + "</em> 원 </div> </div> <div class='prod_btn'> <a href='#a' class='btn1'>카트에 넣기</a>	<a href='#a' class='btn2'>바로 구매하기</a></div></li></ul>";
					}
					$('#prod_list').html(sumMsg);
						
				});	
		}
				
	});
	
	
	
	
	$("#previousBtn").click(function(){		
		if(page_num == 0){
			alert("첫 페이지입니다");
		}
		else{
			$('#paging').text(page_num);
			$.post("../../../productListAll.jes", {
				page_num : page_num
			},
				function(data, status){
					let json_msg = data;
					let js_msg = JSON.parse(json_msg);
					let my_array = js_msg.data;
					let sumMsg='';
					var i;
					for (i=0; i<my_array.length; i++){
						sumMsg += "<ul class='prod_li'> <li> <div class='prodSet'> <p class='prod_img'> <span class='prod_imgSet'> <a href=''> <img src='" + my_array[i].book.imgurl 
								+ "border='0'> </a>	</span> </p> </div> <div class='prod_info'> <div class='prod_name'> <a href='../../html/ProductDetail/ProductDetail.html'>"
								+ my_array[i].book.title + "</a><br><br> </div> <div class='prod_pubGrp'> <span class='prod_auth'> <a href=''>" 
								+ my_array[i].book.author + "</a> 저 	</span> <em class='divi'>｜</em> <span class='prod_pub'>"
								+ my_array[i].book.publisher + "</span> <em class='divi'>｜</em> </div> <div class='prod_price'> <em class='price'>" 
								+ my_array[i].book.price + "</em> 원 </div> </div> <div class='prod_btn'> <a href='#a' class='btn1'>카트에 넣기</a>	<a href='#a' class='btn2'>바로 구매하기</a></div></li></ul>";
					}
					$('#prod_list').html(sumMsg);
						
				});		
		}
			
	});


});




