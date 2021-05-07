var category;
var setCookie = function ci(value, exp){	
	var date = new Date();
	date.setTime(date.getTime() + exp*24*60*60*1000);
	document.cookie = "select_product_isbn" + '=' + value + ';expires=' + date.toUTCString() + ';path=/';
};

$(document).ready(function(){
	
	$(".catBtn").click(function(){
		category = $(this).attr('value');
		$.post("../../../productListCategory.jes", {
			category : category
		},
			function(data, status){
			let json_msg = data;
			let js_msg = JSON.parse(json_msg);
			let my_array = js_msg.data;
			let sumMsg='';
			for (i=0; i<my_array.length; i++){
				sumMsg += "<ul class='prod_li'> <li> <div class='prodSet'> <p class='prod_img'> <span class='prod_imgSet'> <a href='#'> <img src='" + my_array[i].book.imgurl 
						+ "border='0'> </a>	</span> </p> </div> <div class='prod_info'> <div class='prod_name'> <a>"
						+ my_array[i].book.title + "</a><br><br> </div> <div class='prod_pubGrp'> <span class='prod_auth'>" 
						+ my_array[i].book.author + "</a> 저 	</span> <em class='divi'>｜</em> <span class='prod_pub'>"
						+ my_array[i].book.publisher + "</span> <em class='divi'>｜</em> </div> <div class='prod_price'> <em class='price'>" 
						+ my_array[i].book.price + "</em> 원 </div> </div> <div class='prod_btn'> <a href='#' class='btn1'>카트에 넣기</a><a class='btn2' href='#' onclick=alert("+my_array[i].book.isbn+")+setCookie("+my_array[i].book.isbn+","+1+")+window.open('../../html/ProductDetail/ProductDetail.html')>바로 구매하기</a></div></li></ul>";
			
//				alert(i+"번째 책 isbn : " + my_array[i].book.isbn);
				
			}
			$('#prod_list').html(sumMsg);
		
			});	

			});	
});




