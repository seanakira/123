<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ page import="com.cts.localtour.entity.UserTable" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String path = request.getContextPath()+"/"; %>

<jsp:include page="../../../resources/include/header.jsp"></jsp:include>
<style type="text/css">
	#ui-datepicker-div a{
		text-align: center;
	}
	#ui-datepicker-div span{
		text-align: center;
	}
</style>

<link rel="stylesheet" href="${path }resources/assets/css/jquery-ui-1.10.3.full.min.css">
<link rel="stylesheet" href="${path }resources/assets/css/chosen.css" />

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
								<a id="createTour" role="button" data-toggle="modal" href="#">收款管理</a>
							</li>
						</ul><!-- .breadcrumb -->
						<div class="accessBar" style="display: inline-block;">
							<a class="" id="invoice" data-toggle="modal" href="#" title="付款申请" style="padding-left: 10px;">
								<i class="icon-file-alt bigger-100"></i>
								开发票
							</a>
						</div>
						<div class="nav-search" id="nav-search">
							<form class="form-search" action="${path }revenueManage" method="get">
								<span class="input-icon">
									<input name="key" placeholder="搜索 ..." class="nav-search-input" id="nav-search-input" autocomplete="off" type="text" value="${key }" />
									<i class="icon-search nav-search-icon"></i>
								</span>
							</form>
						</div><!-- #nav-search -->
					</div>
					
					<div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid">
						<table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
							<thead>
								<tr role="row">
									<th aria-label="" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">
										<label>
											<input class="ace selectAll" type="checkbox">
											<span class="lbl"></span>
										</label>
									</th>
									<th aria-label="Price: activate to sort column ascending" style="width: 20%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
										团号
									</th>
									<th aria-label="Price: activate to sort column ascending" style="width: 20%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
										线路
									</th>
									<th aria-label="Price: activate to sort column ascending" style="width: 10%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
										应收总计
									</th>
									<th aria-label="Price: activate to sort column ascending" style="width: 10%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
										实收总计
									</th>
									<th aria-label="Price: activate to sort column ascending" style="width: 10%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
										开票金额
									</th>
									<th aria-label="Price: activate to sort column ascending" style="width: 10%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
										出团日期
									</th>
									<th aria-label="Price: activate to sort column ascending" style="width: 10%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
										状态
									</th>
									<th aria-label="Price: activate to sort column ascending" style="width: 10%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
										团控人
									</th>
								</tr>
							</thead>
	
							<tbody id="table" aria-relevant="all" aria-live="polite" role="alert">
	<!-- 列表循环 -->				
								<c:forEach var="revenue" items="${revenues }" varStatus="status">
									<tr id="${revenue.localTourTable.id }">
										<td class="center  sorting_1">
												<label>
													<input class="ace" type="checkbox">
													<span class="lbl"></span>
												</label>
										</td>
										<td><a id="editTour" role="button" data-toggle="modal" href="#edit">${revenue.localTourTable.tourNo }</a></td>
										<td>${revenue.localTourTable.tourName }</td>
										<td>${revenue.willIncome }</td>
										<td>${revenue.realIncome }</td>
										<td>${revenue.invoice }</td>
										<td>${revenue.localTourTable.startTime }</td>
										<td>${revenue.status }</td>
										<td>${revenue.userRealName }</td>
									</tr>
								</c:forEach>
	<!-- 列表循环结束 -->								
							</tbody>
						</table>
						
<!-- 分页查询开始 -->					
						<div class="row">
							<div class="col-sm-6">
								<div id="sample-table-2_info" class="dataTables_info">共  ${counts } 个结果 </div>
							</div>
							<div class="col-sm-6">
								<div class="dataTables_paginate paging_bootstrap">
									<ul class="pagination">
										<li <c:choose><c:when test="${pageNo==1 }">class="prev disabled"</c:when><c:otherwise>class="prev"</c:otherwise></c:choose>>
											<a href="/localtour/revenueManage?page=${pageNo-1 }&key=${key }"><i class="icon-double-angle-left"></i></a>
										</li>
										<c:forEach var="page" begin="1" end="${pageMax }">
											<li <c:if test="${pageNo==page }">class="active"</c:if>>
												<a href="/localtour/revenueManage?page=${page }&key=${key }">${page }</a>
											</li>
										</c:forEach>
										<li <c:choose><c:when test="${pageNo==pageMax }">class="next disabled"</c:when><c:otherwise>class="next"</c:otherwise></c:choose>>
											<a href="/localtour/revenueManage?page=${pageNo+1 }&key=${key }"><i class="icon-double-angle-right"></i></a>
										</li>
									</ul>
								</div>
							</div>
						</div>
<!-- 分页查询结束 -->							
					</div>
				</div>
<!-- 正文结束 -->	

<!-- 编辑模板 -->
				<div aria-hidden="true" style="display: none;" id="edit" class="modal fade" tabindex="-1">
					<div class="modal-dialog" style="width: 80%;">
						<div class="modal-content">
					        <div class="modal-header no-padding">
								<div class="table-header">
									团队信息
						 		</div>
						  	</div>
							<div class="modal-body no-padding">
					         	<div class="tabbable">
					         		<ul class="nav nav-tabs padding-18" id="myTab">
										<li class="active">
											<a data-toggle="tab" href="#incomes">
												<i class="pink icon-briefcase bigger-120"></i>
												收入
											</a>
										</li>
									</ul>
					         	</div>
					         	<div class="tab-content no-border padding-6" style="z-index: 1400;">
					         		<div id="incomes" class="tab-pane fade active in">
					         			<div class="tabbable tabs-left">
											<div class="tab-content no-padding" style="z-index: 1400;">
												<table class="table table-striped table-bordered table-hover incomeTable">
														<thead>
															<tr>
																<th style="width: 15%">日期</th>
																<th>客户*</th>
																<th style="width: 10%">收入</th>
																<th style="width: 10%">实收</th>
																<th style="width: 20%">备注</th>
																<th style="width: 15%">财务确认人</th>
															</tr>
														</thead>
														<tbody>
														</tbody>
										            </table>
												         			
						         			</div><!-- tab content 结束 -->
						         			<span id="changeIncomeBlue" class="blue" style="">*蓝色为成本收入变更</span>
					         			</div><!-- 左tab 结束 -->
					         		</div>
					         	</div>
					         </div>
							<div class="modal-footer no-margin-top">
								<button class="btn btn-sm btn-danger pull-left" data-dismiss="modal">
									<i class="icon-remove"></i>
									取消
								</button>
								<button id="saveEdit" class="btn btn-sm btn-success pull-right" data-dismiss="modal">
									<i class="icon-save"></i>
									收款确认
								</button>
						 	 </div>
						</div><!-- /.modal-content -->
					</div><!-- /.modal -->
				</div>
<!-- 编辑结束 -->
<!-- 发票模板 -->
				<div aria-hidden="true" style="display: none;" id="invoiceModel" class="modal fade" tabindex="-1">
					<div class="modal-dialog" style="width: 80%;">
						<div class="modal-content">
					         <div class="modal-header no-padding">
								<div class="table-header">
									发票信息
									<a id="addInvoice" class="white" style="position: absolute;left: 80px;" href="#">
										<i class="icon-plus bigger-100"></i>
										新增
									</a>
						 		</div>
						  	</div>
						  	<div class="modal-body no-padding">
					         	<table class="table table-striped table-bordered table-hover no-margin">
									<thead>
										<tr>
											<th style="width: 15%;">日期</th>
											<th style="width: 15%;">票号*</th>
											<th style="width: 15%;">抬头*</th>
											<th style="width: 15%;">内容</th>
											<th style="width: 15%;">金额*</th>
											<th style="width: 15%;">开票人</th>
										</tr>
									</thead>
									<tbody id="invoiceTable">
									</tbody>
					            </table>
					            <table class="table table-striped table-bordered table-hover no-margin" style="width: 60%;">
									<tbody>
										<tr>
											<td style="width: 25%;">开票总计</td>
											<td id="total" style="width: 25%;"></td>
											<td style="width: 25%;">最大开票额</td>
											<td id="maxIssue" style="width: 25%;"></td>
										</tr>
									</tbody>
					            </table>
					        </div>
							<div class="modal-footer no-margin-top">
								<button class="btn btn-sm btn-danger pull-left" data-dismiss="modal">
									<i class="icon-remove"></i>
									取消
								</button>
								<button id="saveInvoice" class="btn btn-sm btn-success pull-right" >
									<i class="icon-save"></i>
									开票
								</button>
						 	 </div>
						</div><!-- /.modal-content -->
					</div><!-- /.modal -->
				</div>
<!-- 发票结束 -->
	
<jsp:include page="../../../resources/include/footer.jsp"></jsp:include>

<!-- 下拉搜索依赖 -->
<script src="${path }resources/assets/js/chosen.jquery.min.js"></script>
<!-- 日历组件依赖 -->
<script src="${path }resources/assets/js/jquery-ui-1.10.3.full.min.js"></script>

<script type="text/javascript">
	$(function(){
	/* 初始化 */
		/* 样式 */
		$("#financeManage").addClass("open");
		$("#financeManage").children("ul").attr("style","display:block");
		$("#revenueManage").addClass("active");
		$(".modal-dialog").attr("style","width:80%;");
		/* 提示 */
		$("a").tooltip({
			show: null,
			position: {
				my: "left top",
				at: "left bottom"
			},
			open: function( event, ui ) {
				ui.tooltip.animate({ top: ui.tooltip.position().top + 10 }, "fast" );
			}
		});
	/* 点击本行选中复选框 */
		$("#table").find("td").not(".sorting_1").click(function(){
			var checkbox = $(this).siblings().eq(0).find("input");
			if(checkbox.prop("checked")){
				checkbox.prop("checked",false);
			}else{
				checkbox.prop("checked",true);
			}
		});
		/* 全选 */
		$(".selectAll").click(function(){
			if($(this).prop("checked")){
				$("#table").find("input").prop("checked",true);
			}else{
				$("#table").find("input").prop("checked",false);
			}
		});
	/* 收入 */
	/* 编辑 */
		/* 编辑读取 */
 		$("#table").delegate("#editTour","click",function(){
 			if($(this).parent().prev().find("input").prop("checked")){
 				$(this).parent().prev().find("input").prop("checked",false);
 			}else{
 				$(this).parent().prev().find("input").prop("checked",true);
 			}
			var myData = {tourId:$(this).parent().parent().attr("id")};
			$("#saveEdit").parent().attr("id",myData.tourId);
			var tourUserName = $(this).parent().prev().text();
			$.ajax({
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"/localtour/revenueManage/find",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
					/* 设置收入 */
					$("#incomes").find("tbody").html("");
		        	$.each(data.incomes,function(){
		        		var realIncome = $("<td></td>");
		        		var remark = $("<td></td>");
		        		if(this.incomeTable.realIncome==0){
		        			realIncome.html('<input class="realIncome" type="text" value="0"/>');
		        			remark.html('<input type="text" value="'+this.incomeTable.remark+'"/>');
		        		}else{
		        			realIncome.html(this.incomeTable.realIncome);
		        			remark.html(this.incomeTable.remark);
		        		}
		        		var tr = $('<tr>'+
										'<td>'+this.incomeTable.incomeDate.replace(/-/g,'/')+'</td>'+
										'<td>'+this.customerAgencyName+'</td>'+
										'<td>'+this.incomeTable.income+'</td>'+
										'<td>'+realIncome.html()+'</td>'+
										'<td>'+remark.html()+'</td>'+
										'<td id="'+this.incomeTable.id+'">'+this.handlerRealName+'</td>'+
									'</tr>');
		        		$("#incomes").find("tbody").append(tr);
		        	});
		        	
		        	/* 设置收入变更 */
		        	if(data.changeIncomes.length > 0){
		        		$("#changeCostBlue").attr("style","");
		        	}else{
		        		$("#changeCostBlue").attr("style","display:none");
		        	}
		        	$.each(data.changeIncomes,function(){
		        		var realIncome = $("<td></td>");
		        		var remark = $("<td></td>");
		        		if(this.incomeTable.realIncome==0){
		        			realIncome.html('<input class="realIncome" type="text" value="0"/>');
		        			remark.html('<input type="text" value="'+this.incomeTable.remark+'"/>');
		        		}else{
		        			realIncome.html(this.incomeTable.realIncome);
		        			remark.html(this.incomeTable.remark);
		        		}
		        		var tr = $('<tr class="blue">'+
										'<td>'+this.incomeTable.incomeDate.replace(/-/g,'/')+'</td>'+
										'<td>'+this.customerAgencyName+'</td>'+
										'<td>'+this.incomeTable.income+'</td>'+
										'<td>'+realIncome.html()+'</td>'+
										'<td>'+remark.html()+'</td>'+
										'<td id="'+this.incomeTable.id+'">'+this.handlerRealName+'</td>'+
									'</tr>');
		        		$("#incomes").find("tbody").append(tr);
		        	});
		        	
		        	/* 提示 */
		        	$("#edit").find("a").tooltip({
	        			show: null,
	        			position: {
	        				my: "left top",
	        				at: "left bottom"
	        			},
	        			open: function( event, ui ) {
	        				ui.tooltip.animate({ top: ui.tooltip.position().top + 10 }, "fast" );
	        			}
	        		});
		        	
		        	$("#edit").find(".datepicker").datepicker({
		    			showOtherMonths: true,
		    			selectOtherMonths: false,
		    		});
		        	$("#edit").find("select").chosen();
		        	$(".chosen-select").next().attr("style","width:100%;");
					$(".chosen-select").next().find("input").attr("style","height:100%;");
		        }  
			});
		});
 		/* 双击自动填充 */
    	$("#incomes").delegate(".realIncome","dblclick",function(){
    		$(this).val($(this).parent().prev().text());
    	});
    	
	/*更新 */
		$("#saveEdit").click(function(){
			var tourId = $(this).parent().attr("id");
			var incomeTables = new Array();
			var incomeTrs = $("#edit").find("#incomes").find("tbody").find("tr").not(".blue");
			var realIncomeSum = 0;
			for (var int = 0; int < incomeTrs.length; int++) {
				var tds = incomeTrs.eq(int).children("td");
				if(tds.eq(3).children("input").length!=0){
					var id = tds.last().attr("id");
					var realIncome = tds.eq(3).children("input").val();
					var remark = tds.eq(4).children("input").val();
					incomeTables.push({
						id:id,
						realIncome:realIncome,
						remark:remark});
					realIncomeSum = realIncomeSum+parseFloat(realIncome);
				}else{
					realIncomeSum = realIncomeSum+parseFloat(tds.eq(3).text());
				}
			}
			var changeIncomeTables = new Array();
			var changeIncomeTrs = $("#edit").find("#incomes").find("tbody").find("tr.blue");
			for (var int = 0; int < changeIncomeTrs.length; int++) {
				var tds = changeIncomeTrs.eq(int).children("td");
				if(tds.eq(3).children("input").length!=0){
					var id = tds.last().attr("id");
					var realIncome = tds.eq(3).children("input").val();
					var remark = tds.eq(4).children("input").val();
					changeIncomeTables.push({
						id:id,
						realIncome:realIncome,
						remark:remark});
					realIncomeSum = realIncomeSum + parseFloat(realIncome);
				}else{
					realIncomeSum = realIncomeSum + parseFloat(tds.eq(3).text());
				}
			}
			var full = {incomeTables:incomeTables, changeIncomeTables:changeIncomeTables};
			var myData = JSON.stringify(full);
			$.ajax({
		        type: "POST",  
		        contentType:"application/json;charset=utf-8",  
		        url:"/localtour/revenueManage/update",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	if(data==-1){
		        		alert("保存失败，提交数据异常");
		        	}
		        }
			 });
			/* 设置列表数字 */
			$("#table").find("#"+tourId).children("td").eq(4).text(realIncomeSum.toFixed(2));
		});
	/* 开发票 */
		$("#invoice").click(function(){
			var checkbox = $("#table").find("input:checked");
			if(checkbox.length==0){
				alert("请选择一个团队");
				$(this).attr("href","#");
			}else if(checkbox.length>1){
				alert("只能选择一个团队");
				$(this).attr("href","#");
			}else{
				$(this).attr("href","#invoiceModel");
				var tourId = checkbox.parent().parent().parent().attr("id");
				$("#saveInvoice").parent().attr("id",tourId);
				var myData = {tourId:tourId};
				$.ajax({
			        type: "GET",  
			        contentType:"application/json;charset=utf-8",  
			        url:"/localtour/invoiceManage/find",  
			        data:myData,  
			        dataType: "json",
			        async: false,
			        success:function(data){
			        	$("#invoiceTable").html("");
			        	$.each(data,function(){
			        		$("#invoiceTable").append('<tr class="issued">'+
			        										'<td>'+this.invoiceTable.issueDate+'</td>'+
			        										'<td>'+this.invoiceTable.invoiceNo+'</td>'+
			        										'<td>'+this.invoiceTable.invoiceName+'</td>'+
			        										'<td>'+this.invoiceTable.invoiceContent+'</td>'+
			        										'<td>'+this.invoiceTable.invoiceAmount+'</td>'+
			        										'<td>'+this.issueUserRealName+'</td>'+
			        								+'</tr>');
			        	});
			        	$("#maxIssue").text($("#table").find("#"+tourId).children("td").eq(4).text());
			        	$("#total").text($("#table").find("#"+tourId).children("td").eq(5).text());
			        }
				});
			}
		});
	/* 增加发票 */
		$("#addInvoice").click(function(){
			var date = (new Date()).toLocaleDateString();
    		var tr = $('<tr>'+
							'<td><input style="width:100%;" class="form-control datepicker" type="text" value="'+date+'"></td>'+
							'<td><input style="width:100%;" class="form-control" type="text"></td>'+
							'<td><input style="width:100%;" class="form-control" type="text"></td>'+
							'<td><input style="width:100%;" class="form-control" type="text"></td>'+
							'<td><input style="width:100%;" class="form-control invoiceAmount" type="text" placeholder="双击快速添加金额"></td>'+
							'<td><%=((UserTable)session.getAttribute("user")).getRealName() %><input type="hidden" value="<%=((UserTable)session.getAttribute("user")).getId() %>"></td>'+
						'</tr>');
    		tr.find(".datepicker").datepicker({
    			showOtherMonths: true,
    			selectOtherMonths: false,
    		});
    		$("#invoiceTable").append(tr);
		});
		/* 开票 */
		$("#saveInvoice").click(function(){
			var tourId = $(this).parent().attr("id");
			var trs = $("#invoiceTable").children("tr").not(".issued");
			var invoices = new Array();
			var errorCode = 0;
			var total = 0;
			$.each(trs,function(){
				var inputs = $(this).find("input");
				if(isNaN(parseInt(inputs.eq(1).val()))||inputs.eq(1).val().length!=8){
					errorCode = -2;
				}
				invoices.push({
					tourId:tourId,
					issueDate:new Date(inputs.eq(0).val()),
					invoiceNo:inputs.eq(1).val(),
					invoiceName:inputs.eq(2).val(),
					invoiceContent:inputs.eq(3).val(),
					invoiceAmount:inputs.eq(4).val(),
					issueUserId:inputs.eq(5).val()
				});
				total = (total + parseFloat(inputs.eq(4).val())).toFixed(2);
			});
			var myData = JSON.stringify(invoices);
			$.ajax({
		        type: "POST",  
		        contentType:"application/json;charset=utf-8",  
		        url:"/localtour/invoiceManage/save",  
		        data:myData,  
		        dataType: "json",
		        async: false,
		        success:function(data){
		        	if(data==-1){
		        		$("#saveInvoice").attr("data-dismiss","");
		        		alert("*号为必填项，请检查");
		        	}else if(data==-2){
		        		$("#saveInvoice").attr("data-dismiss","");
		        		alert("票号重复，请检查");
		        	}else{
		        		$("#saveInvoice").attr("data-dismiss","modal");
		        		$("#table").find("#"+tourId).children("td").eq(5).text((parseFloat($("#table").find("#"+tourId).children("td").eq(5).text())+parseFloat(total)).toFixed(2));
		        	}
		        }
			});
			if(errorCode == -2){
				$("#saveInvoice").attr("data-dismiss","");
				alert("票号必须为8位数字");
			}
		});
		/* 自动计算 */
		$("#invoiceTable").delegate(".invoiceAmount","dblclick",function(){
			var maxIssue = parseFloat($("#maxIssue").text());
			var total = parseFloat($("#total").text());
			var value = maxIssue-total;
			$(this).val(value.toFixed(2));
			$("#total").text((total+value).toFixed(2));
		});
		$("#invoiceTable").delegate(".invoiceAmount","blur",function(){
			var tds = $("#invoiceTable").find("tr td:nth-child(5)");
			var total = 0;
			$.each(tds,function(){
				if($(this).children("input").length==0){
					total = total + parseFloat($(this).text());
				}else{
					total = total + parseFloat($(this).children("input").val());
				}
			});
			$("#total").text(total.toFixed(2));
		});
	});
	
	
</script>
