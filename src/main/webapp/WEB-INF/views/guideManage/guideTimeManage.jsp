<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String path = request.getContextPath()+"/"; %>

<jsp:include page="../../../resources/include/header.jsp"></jsp:include>

<style type="text/css">
	.calendar a{
		display: inline-block;
		width: 3.22%;
		text-align: center;
		color: black;
		text-decoration: none;
		
	}
</style>
<link rel="stylesheet" href="${path }resources/assets/css/jquery-ui-1.10.3.full.min.css">

<jsp:include page="../../../resources/include/pageSettings.jsp"></jsp:include>
<jsp:include page="../../../resources/include/sider.jsp"></jsp:include>
<!-- 正文开始 -->
				<div class="main-content">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="icon-user"></i>
								<a id="add" href="#">新增排团</a>
							</li>

							
							
						</ul><!-- .breadcrumb -->

						<div class="nav-search" id="nav-search">
							<form class="form-search" action="${path }supplierInfoManage" method="get">
								<span class="input-icon">
									<input name="key" placeholder="搜索 ..." class="nav-search-input" id="nav-search-input" autocomplete="off" type="text" value="${key }" />
									<i class="icon-search nav-search-icon"></i>
								</span>
							</form>
						</div><!-- #nav-search -->
					</div>
					
					<div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid"><table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
						<thead>
							<tr role="row">
								<th aria-label="Price: activate to sort column ascending" style="width: 5%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
									姓名
								</th>
								<th aria-label="Price: activate to sort column ascending" style="width: 90%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
									日历
									<span class="ui-spinner ui-widget ui-widget-content ui-corner-all" style="width: 71px;">
										<input aria-valuenow="2" role="spinbutton" autocomplete="off" class="ui-spinner-input" id="year" name="value" type="text" style="width:80%;">
										<a id="plusY" aria-disabled="false" role="button" tabindex="-1" class="ui-spinner-button ui-spinner-up ui-corner-tr ui-button ui-widget ui-state-default ui-button-text-only btn btn-success" style="left:50px">
											<i class="icon-plus bigger-150" style="margin-top: 2px;width: 100%;"></i>
										</a>
										<a id="minusY" aria-disabled="false" role="button" tabindex="-1" class="ui-spinner-button ui-spinner-down ui-corner-br ui-button ui-widget ui-state-default ui-button-text-only btn btn-danger" style="left:50px">
											<i class="icon-minus bigger-150" style="margin-top: 2px;width: 100%;"></i>
										</a>
									</span>
									年
									<span class="ui-spinner ui-widget ui-widget-content ui-corner-all" style="width: 50px;">
										<input aria-valuenow="2" role="spinbutton" autocomplete="off" class="ui-spinner-input" id="month" name="value" type="text" style="width:80%;">
										<a id="plusM" aria-disabled="false" role="button" tabindex="-1" class="ui-spinner-button ui-spinner-up ui-corner-tr ui-button ui-widget ui-state-default ui-button-text-only btn btn-success" style="left:30px">
											<i class="icon-plus bigger-150" style="margin-top: 2px;width: 100%;"></i>
										</a>
										<a id="minusM" aria-disabled="false" role="button" tabindex="-1" class="ui-spinner-button ui-spinner-down ui-corner-br ui-button ui-widget ui-state-default ui-button-text-only btn btn-danger" style="left:30px">
											<i class="icon-minus bigger-150" style="margin-top: 2px;width: 100%;"></i>
										</a>
									</span>
									月
								</th>
								<th aria-label="" style="width: 5%;" colspan="1" rowspan="1" role="columnheader" class="sorting_disabled">
									操作
								</th>
							</tr>
						</thead>

						<tbody id="table" aria-relevant="all" aria-live="polite" role="alert">
<!-- 列表循环 -->			
							
<!-- 列表循环结束 -->								
						</tbody>
					</table>
<!-- 分页查询开始 -->					
					
<!-- 分页查询结束 -->							
					</div>
				</div>
<!-- 正文结束 -->	

<!-- 增加模板 -->
				<div aria-hidden="true" style="display: none;" id="addModel" class="modal fade" tabindex="-1">
					<div class="modal-dialog" style="width:500px;margin-top: 10%;">
						<div class="modal-content">
							<div class="modal-header no-padding">
								<div id="headerName" class="table-header">
									增加派团信息
								</div>
								<div class="modal-body no-padding">
									<table class="table table-striped table-bordered table-hover no-margin-bottom no-border-top">
										<thead>
										</thead>
										<tbody id="">
											<tr>
												<td>
													<input style="width:100%;" id="startTime" class="form-control hasDatepicker" type="text">
												</td>
												<td>
													<input style="width:100%;" id="endTime" class="form-control hasDatepicker" type="text">
												</td>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="modal-footer no-margin-top">
									<button class="btn btn-sm btn-danger pull-left" data-dismiss="modal">
										<i class="icon-remove"></i>
										取消
									</button>
									<button class="btn btn-sm btn-success pull-right" data-dismiss="modal">
										<i class="icon-save"></i>
										保存
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>
<!-- 增加模板 结束-->
<jsp:include page="../../../resources/include/footer.jsp"></jsp:include>

<script src="${path }resources/assets/js/jquery-ui-1.10.3.full.min.js"></script>

<script type="text/javascript">
	$(function(){
	/* 初始化 */
		var color =["lightblue","lightcoral","lightseagreen","lightsalmon","lightgreen","lightgray","lightpink","aquamarine","burlywood","cornflowerblue"];
		$("#guideManage").addClass("open");
		$("#guideManage").children("ul").attr("style","display:block");
		$("#guideTimeManage").addClass("active");
		var date = new Date();
		var days = getDays(date.getFullYear(),date.getMonth() + 1);
		$("#plusM").prev().val(date.getMonth()+1);
		$("#plusY").prev().val(date.getFullYear());
		var from = new Date(date.getFullYear(),date.getMonth(),1);
		var to = new Date(date.getFullYear(),date.getMonth()+1,0);
		var myData = {"from":from,"to":to};
		$.ajax({  
			type: "GET",  
	        contentType:"application/json;charset=utf-8",  
	        url:"/localtour/guideTimeManage/initialize",
	        data:myData,
	        dataType: "json",  
	        async: false,  
	        success:function(data){
	        	$("#table").html("");
	        	$.each(data,function(){
	        		$("#table").append("<tr id='guideId"+this.guideId+"'>"+
	        								"<td>"+
	        									this.realName+
	        								"</td>"+
	        								"<td class='calendar'></td>"+
	        								"<td>"+
		        								"<a class='green' id='add' href='#addModel' role='button' data-toggle='modal' title='手动派团'>"+
													"<i class='icon-plus bigger-130'></i>"+
												"</a>"+
	        								"</td>"+
	        							"</tr>");
	        		var info = $("#guideId"+this.guideId).children("td").eq(1);
	        		for (var int = 1; int <= days; int++) {
						info.append("<a>"+int+"</a>");
					}
	        		var index = 0;
	        		$.each(this.tourInfo,function(tourNo,tourInfo){
	        			var start = new Date(tourInfo.startTime);
	        			var end = new Date(tourInfo.endTime);
	        			var startTime = start.getDate();
	        			var endTime = end.getDate();
	        			if(start.getMonth()+1<$("#month").val()){
	        				startTime = 1;
	        			}
	        			if(end.getMonth()+1>$("#month").val()){
	        				endTime=endTime=to.getDate();
	        			}
	        			for (var int2 = startTime-1; int2 < endTime; int2++) {
	        				info.children("a").eq(int2).attr({"style":"background-color: "+color[index]+";","title":tourNo,"class":"tourInfo"});
						}
	        			index++;
	        			if(index>=color.length){
	        				index=0;
	        			}
	        		});
	        	});
	        	$(".tourInfo").tooltip({
    				show: null,
    				position: {
    					my: "left top",
    					at: "left bottom"
    				},
    				open: function( event, ui ) {
    					ui.tooltip.animate({ top: ui.tooltip.position().top + 10 }, "fast" );
    				}
    			});
	        } 
		}); 
		$(".green").tooltip({
			show: null,
			position: {
				my: "left top",
				at: "left bottom"
			},
			open: function( event, ui ) {
				ui.tooltip.animate({ top: ui.tooltip.position().top + 10 }, "fast" );
			}
		});
		$("#startTime").datepicker({
			showOtherMonths: true,
			selectOtherMonths: false,
			//isRTL:true,
			/* changeMonth: true,
			changeYear: true,
			
			showButtonPanel: true,
			beforeShow: function() {
				//change button colors
				var datepicker = $(this).datepicker( "widget" );
				setTimeout(function(){
					var buttons = datepicker.find('.ui-datepicker-buttonpane')
					.find('button');
					buttons.eq(0).addClass('btn btn-xs');
					buttons.eq(1).addClass('btn btn-xs btn-success');
					buttons.wrapInner('<span class="bigger-110" />');
				}, 0);
			}  */
		});
		$("#endTime").datepicker({
			showOtherMonths: true,
			selectOtherMonths: false,
			//isRTL:true,
			/* changeMonth: true,
			changeYear: true,
			
			showButtonPanel: true,
			beforeShow: function() {
				//change button colors
				var datepicker = $(this).datepicker( "widget" );
				setTimeout(function(){
					var buttons = datepicker.find('.ui-datepicker-buttonpane')
					.find('button');
					buttons.eq(0).addClass('btn btn-xs');
					buttons.eq(1).addClass('btn btn-xs btn-success');
					buttons.wrapInner('<span class="bigger-110" />');
				}, 0);
			}  */
		});
	/* 年月查询 */
		$("#plusM").click(function(){
			var input = $("#month");
			var month = input.val();
			month++;
			if(month==13){
				month=1;
			}
			input.val(month);
			days = getDays($("#year").val(),month);
			$("#month").change();
		});
		
		$("#minusM").click(function(){
			var input = $("#month");
			var month = input.val();
			month--;
			if(month==0){
				month=12;
			}
			input.val(month);
			days = getDays($("#year").val(),month);
			$("#month").change();
		});
		
		$("#plusY").click(function(){
			var input = $("#year");
			var year = input.val();
			year++;
			input.val(year);
			days = getDays(year,$("#month").val());
			$("#year").change();
		});
		
		$("#minusY").click(function(){
			var input = $("#year");
			var year = input.val();
			year--;
			input.val(year);
			days = getDays(year,$("#month").val());
			$("#year").change();
		});
		
		$(".ui-spinner-input").change(function(){
			var year = $("#year").val();
			var month = $("#month").val();
			var from = new Date(year,month-1,1);
			var to = new Date(year,month,0);
			var myData = {"from":from,"to":to};
			$("#table").html("<i class='icon-spinner icon-spin orange' style='font-size: 500%;position: absolute;left: 50%;top: 200%;'></i>");
			$.ajax({  
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"/localtour/guideTimeManage/initialize",
		        data:myData,
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	$("#table").html("");
		        	$.each(data,function(){
		        		$("#table").append("<tr id='guideId"+this.guideId+"'>"+
		        								"<td>"+
		        									this.realName+
		        								"</td>"+
		        								"<td class='calendar'></td>"+
		        								"<td></td>"+
		        							"</tr>");
		        		var info = $("#guideId"+this.guideId).children("td").eq(1);
		        		for (var int = 1; int <= days; int++) {
							info.append("<a>"+int+"</a>");
						}
		        		var index = 0;
		        		$.each(this.tourInfo,function(tourNo,tourInfo){
		        			var start = new Date(tourInfo.startTime);
		        			var end = new Date(tourInfo.endTime);
		        			var startTime = start.getDate();
		        			var endTime = end.getDate();
		        			if(start.getMonth()+1<$("#month").val()){
		        				startTime = 1;
		        			}
		        			if(end.getMonth()+1>$("#month").val()){
		        				endTime=endTime=to.getDate();
		        			}
		        			
		        			for (var int2 = startTime-1; int2 < endTime; int2++) {
		        				info.children("a").eq(int2).attr({"style":"background-color: "+color[index]+";","title":tourNo,"class":"tourInfo"});
							}
		        			index++;
		        			if(index>=color.length){
		        				index=0;
		        			}
		        		});
		        	});
		        	$(".tourInfo").tooltip({
        				show: null,
        				position: {
        					my: "left top",
        					at: "left bottom"
        				},
        				open: function( event, ui ) {
        					ui.tooltip.animate({ top: ui.tooltip.position().top + 10 }, "fast" );
        				}
        			});
		        }  
			});
		});
		
	/* 回车保存 */		
		$("#table").delegate("#submit","keydown",function(event){
			if(event.keyCode==13){
				var obj = $(this).parents("tr");
				var input = $(this);
				var td = obj.children("td");
				var supplierName = obj.find("input").eq(1).val();
				var regionId = obj.find("select").eq(0).val();
				var supplierScopeIds =obj.find("select").eq(1).val().toString();
				var regionName = obj.find("select").eq(0).find("option:selected").text();
				var supplierScopeName = obj.find("select").eq(1).find("option:selected").text();
				var phone = obj.find("input").eq(-1).val();
				var supplier = {supplierName:supplierName,regionId:regionId,phone:phone};
				var myData = JSON.stringify(supplier);
				var supplierId;
				
			 	$.ajax({  
			        type: "POST",  
			        contentType:"application/json;charset=utf-8",  
			        url:"/localtour/supplierInfoManage/save",  
			        data:myData,  
			        dataType: "json",  
			        async: false,  
			        success:function(data){
			        	supplierId = data;
			        	td.eq(2).html(supplierName);
			        	td.eq(3).html(regionName+"<span hidden=''>"+regionId+"</span>");
			        	td.eq(4).html(supplierScopeName);
			        	input.parent().html(phone);
			        }  
				 });
				var ids = {supplierId:supplierId,supplierScopeIds:supplierScopeIds};
			  	$.ajax({  
			        type: "GET",  
			        contentType:"application/json;charset=utf-8",  
			        url:"/localtour/supplierBusiness/save",  
			        data:ids,  
			        dataType: "json",  
			        async: false,  
			        success:function(data){
			        	
			        }  
				});
				obj.next().find("input").eq(1).focus().select();
			}
		});
	
	
	/* 删除 */
		$("#table").delegate(".red","click",function(){
			var obj = $(this);
			var myData = {id:obj.attr("id")};
			$.ajax({  
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"/localtour/supplierInfoManage/del",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	obj.attr("class","green");
		        	obj.children("i").attr("class","icon-undo bigger-130");
		        	var span = obj.parents("td").prev().children("span");
		        	span.attr("class","label label-sm label-warning");
		        	span.text("无效");
		        }  
			}); 
		});
	/* 恢复 */
		$("#table").delegate("span .green","click",function(){
			var obj = $(this);
			var myData = {id:obj.attr("id")};
			$.ajax({  
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"/localtour/supplierInfoManage/recover",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	obj.attr("class","red");
		        	obj.children("i").attr("class","icon-trash bigger-130");
		        	var span = obj.parents("td").prev().children("span");
		        	span.attr("class","label label-sm label-success");
		        	span.text("有效");
		        }  
			}); 
		});
	/* 编辑 */
		$("#table").delegate("#edit","click",function(){
			var obj = $(this);
			var td = obj.parents("td").siblings();
			var info = {id:td.eq(-1).children("a").attr("id"),
						supplierName:td.eq(2).text(),
						regionId:td.eq(3).children("span").text(),
						phone:td.eq(5).text(),
						supplierScopeIds:td.eq(4).find("span").text().split("")};
			td.eq(2).html("<input id='update' type='text' value='"+info.supplierName+"' style='width:150px' />");
			td.eq(3).html($("#select").html());
			td.eq(3).children("select").attr("class","width-20 chosen-select");
			td.eq(3).children("select").val(info.regionId);
			td.eq(4).html($("#multiple").html());
			td.eq(4).children("select").attr("class","width-20 chosen-select");
			td.eq(4).children("select").val(info.supplierScopeIds);
			$(".chosen-select").chosen();
			td.eq(3).find("select").next().attr("style","width:150px;");
			td.eq(4).find("input").attr("style","height:25px;");
			td.eq(5).html("<input id='update' type='text' value='"+info.phone+"' style='width:150px' />");
			obj.html("<i class='icon-save bigger-130'></i>").attr({"id":"save","class":"grey"});
		});
	/*回车更新 */		
		$("#table").delegate("#update","keydown",function(event){
			if(event.keyCode==13){
				var obj = $(this).parents("tr");
				var td = $(this).parents("td").siblings();
				var params = $(this).parents("tr").find("input");
		 		var supplierName = params.eq(1).val();
				var id = obj.find(".red").attr("id");
				var regionId = obj.find("select").val();
				var regionName = obj.find("select").eq(0).find("option:selected").text();
				var supplierScopeIds =obj.find("select").eq(1).val().toString();
				var supplierScopeName = obj.find("select").eq(1).find("option:selected").text();
				var phone = td.eq(5).children("input").val();
				var supplier = {id:id,supplierName:supplierName,regionId:regionId,phone:phone};
				var myData = JSON.stringify(supplier);
				var supplierId = id;
				$.ajax({  
			        type: "POST",  
			        contentType:"application/json;charset=utf-8",  
			        url:"/localtour/supplierInfoManage/update",  
			        data:myData,  
			        dataType: "json",  
			        async: false,  
			        success:function(data){
			        	td.eq(2).parent().html(supplierName);
			        	td.eq(3).html(regionName+"<span hidden=''>"+regionId+"</span>");
			        	td.eq(4).html(supplierScopeName+"<span hidden=''>"+supplierScopeIds+"</span>");
			        	td.eq(5).html(phone);
			        }  
				 }); 
				var ids = {supplierId:supplierId,supplierScopeIds:supplierScopeIds};
			  	$.ajax({  
			        type: "GET",  
			        contentType:"application/json;charset=utf-8",  
			        url:"/localtour/supplierBusiness/update",  
			        data:ids,  
			        dataType: "json",  
			        async: false,  
			        success:function(data){
			        	
			        }  
				});
				obj.find("a").eq(0).html("<i class='icon-pencil bigger-130'></i>").attr({"id":"edit","class":"green"});
				obj.next().find("input").eq(1).focus().select();
			}
		});
		/* 按钮更新 */
		$("#table").delegate("#save","click",function(){
			var obj = $(this).parents("tr");
			var td = $(this).parents("td").siblings();
			var params = $(this).parents("tr").find("input");
	 		var supplierName = params.eq(1).val();
			var id = obj.find(".red").attr("id");
			var regionId = obj.find("select").val();
			var regionName = obj.find("select").eq(0).find("option:selected").text();
			var supplierScopeIds =obj.find("select").eq(1).val().toString();
			var supplierScopeName = obj.find("select").eq(1).find("option:selected").text();
			var phone = td.eq(5).children("input").val();
			var supplier = {id:id,supplierName:supplierName,regionId:regionId,phone:phone};
			var myData = JSON.stringify(supplier);
			var supplierId = id;
			$.ajax({  
		        type: "POST",  
		        contentType:"application/json;charset=utf-8",  
		        url:"/localtour/supplierInfoManage/update",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	td.eq(2).html(supplierName);
		        	td.eq(3).html(regionName+"<span hidden=''>"+regionId+"</span>");
		        	td.eq(4).html(supplierScopeName+"<span hidden=''>"+supplierScopeIds+"</span>");
		        	td.eq(5).html(phone);
		        }  
			 }); 
			var ids = {supplierId:supplierId,supplierScopeIds:supplierScopeIds};
		  	$.ajax({  
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"/localtour/supplierBusiness/update",  
		        data:ids,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	
		        }  
			});
			obj.find("a").eq(0).html("<i class='icon-pencil bigger-130'></i>").attr({"id":"edit","class":"green"});
			obj.next().find("input").eq(1).focus().select();
		});
		
	});
	function getDays(Year,Month){
	    Month--;
	    var d = new Date(Year,Month,1);
	    d.setDate(d.getDate()+32-d.getDate());
	    return (32-d.getDate());
	}
	
</script>
