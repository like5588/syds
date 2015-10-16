function delcfm(_this){
	if(confirm("是否删除？")){
		$(_this).parent().remove();
		if($("#photo_list_div").html() == "" || $("#photo_list_div").html() == null || $("#photo_list_div").html() == "\n\r"){
			$("#photo_list_form").hide();
		}
	}
}
//上传图片
var image_div_content="<div class=\"imgContent\">"
		+ "<img src=\"\${imgSrc}\" class=\"img\" style=\"clip:rect(\${rect_top}px,\${rect_right}px,\${rect_botton}px,\${rect_left}px);margin-top:\${marginTop}\">"
		+ "<div class=\"closeLayer\"  onClick=\"delcfm(this)\">"
		+ "<img src=\"../images/close.png\" style=\"width: 14px;\">"
		+ "</div>" 
		+ "<div class=\"imgDesc\">" 
		+ "<div class=\"form-group\" style=\"padding-top: 1px;\">"
		+ "<label>作品名称:</label>"
		+ "<input type=\"text\" class=\"form-control\" name=\"photoName\" placeholder=\"请输入作品名称\">"
		+ "<label class=\"radio-inline\">"
		+ "<input type=\"radio\" name=\"photoType_\${num}\" value=\"1\" checked=\"checked\">纪实类 </label>"
		+ "<label class=\"radio-inline\">"
		+ "<input type=\"radio\" name=\"photoType_\${num}\" value=\"2\">艺术类</label>"
		+ "</div></div></div>" ;


