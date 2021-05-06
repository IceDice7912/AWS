$(document).ready(function(){
	let topicBookList="<ul>";
	
	$.post("../../selectbookisbn",
			  null,
			  function(data, status){	
				alert("Data : " + data + "\n" + "Status : " + status)
			  	data=JSON.parse(data);			  	
			  	data.forEach(function(item,index){
			  		console.log(">>>"+index);
			  		var total;
			  		total = index + "번째 책" + "\n" + 
			  				"제목 : " + item.title + "\n" + 
			  				"작가 : " + item.author + "\n" + 
			  				"가격 : " + item.price + "\n" + 
			  				"ISBN : " + item.isbn + "\n" + 
			  				"카테고리 : " + item.category + "\n" + 
			  				"이미지 주소 : " + item.imgurl + "\n" + 
			  				"설명 : " + item.detail + "\n";
			  		
			  		alert(total);
			  		
			  		topicBookList +="<li><a href='#'><img src='"+item.imgurl+"' ><em>"+item.isbn+"</em></a></li>";
			  	});
			  	topicBookList +="</ul>";
			  	$("#topicBook").html(topicBookList);
			  }
			  	
		);//end post()
	
	
});
