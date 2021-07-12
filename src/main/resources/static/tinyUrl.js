$(document).ready(function(){
	$("#loading").hide();
	$("#redirectDiv").hide();
	$('#getShortUrl').click(function(){
		var url=$('#url').val();
		if(url!=null && url!=""){
			$("#loading").show();
			$.ajax({
				url:"http://localhost:8080/tinyurl?url="+url,
				success:function(result){
					if(result!=null && result!=""){
						$("#redirectDiv").show();
						var redirectUrl=result.url;
						if(!redirectUrl.indexOf("http://")>-1){
							redirectUrl="http://"+redirectUrl;	
						}
						$("#shortHand").text(result.shortHand);
						$("#redirect").attr("href", redirectUrl);	
					}
					$("#loading").hide();
					}});
					
		}else {
			alert("url cant be blank");
		}
	})
	$("#goToUrl").click(function(){
		var shortHandCode=$("#shortHandCode").val();
		$.ajax({
			url:"http://localhost:8080/tinyurl/redirect?tinUrl="+shortHandCode,
			success:function(result){
				if(result!=null && result!=""){
					if(!result.indexOf("http://")>-1){
							result="http://"+result;	
						}
					window.open(result,'_blank');
					
				}else {
					window.alert("Urlcode not found");
				}
			},
			error:function(result){
				window.alert("URl code not found");
			}
		});
	})
	
	$("form").submit(function(e){
        e.preventDefault();
    });

 		$("#getAllButton").click(function(){
			$.ajax({
				url:"http://localhost:8080/tinyurl/getall",
				success:function(result){
					if(result!=null && result.length>0){
						var rows=result.length;
						var cols=4;
						var table_body = '<table border="1">';
						table_body+='<thead><th>Id</th><th>Url</th><th>ShortHand</th><th>Used</th></thead>'
              for(var i=0;i<rows;i++){
                	table_body+='<tr>';
                	for(var j=0;j<cols;j++){
					  	table_body +='<td>';
		                    if(j==0){
								table_body +=result[i].id;	
							}
							if(j==1){
								table_body +=result[i].url;
							}
							if(j==2){
								table_body +=result[i].shortHand;
							}
							if(j==3){
								table_body +=result[i].used;
							}
					
                    	table_body +='</td>';
       			}
                table_body+='</tr>';
              }
		                table_body+='</table>';
		               $('#tableDiv').html(table_body);
					}
				}
			});		
            });
})