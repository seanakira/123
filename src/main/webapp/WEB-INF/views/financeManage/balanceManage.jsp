<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ page import="com.cts.localtour.entity.UserTable" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
								<a id="createTour" role="button" data-toggle="modal" href="#">核销管理</a>
							</li>
						</ul><!-- .breadcrumb -->
						<div class="accessBar" style="display: inline-block;">
							<shiro:hasPermission name="balance:find">
								<a class="blue" id="reimbursement" data-toggle="modal" href="#" title="团队报账">
									<i class="icon-suitcase bigger-100"></i>
									团队核销
								</a>
							</shiro:hasPermission>
						</div>
						<div class="nav-search" id="nav-search">
							<form class="form-search" action="${path }balanceManage" method="get">
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
									<th aria-label="Price: activate to sort column ascending" style="width: 15%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
										团号
									</th>
									<th aria-label="Price: activate to sort column ascending" style="width: 15%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
										线路
									</th>
									<th aria-label="Price: activate to sort column ascending" style="width: 10%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
										应付总计
									</th>
									<th aria-label="Price: activate to sort column ascending" style="width: 10%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
										实付总计
									</th>
									<th aria-label="Price: activate to sort column ascending" style="width: 10%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
										应收总计
									</th>
									<th aria-label="Price: activate to sort column ascending" style="width: 10%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
										实收总计
									</th>
									<th aria-label="Price: activate to sort column ascending" style="width: 10%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
										报账总计
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
								<c:forEach var="balance" items="${balances }" varStatus="status">
									<tr id="${balance.localTourTable.id }">
										<td class="center  sorting_1">
												<label>
													<input class="ace" type="checkbox">
													<span class="lbl"></span>
												</label>
											</td>
										<td><a id="findTour" role="button" data-toggle="modal" href="#">${balance.localTourTable.tourNo }</a></td>
										<td>${balance.localTourTable.tourName }</td>
										<td>${balance.willPaySum }</td>
										<td>${balance.realPaySum }</td>
										<td>${balance.willIncomeSum }</td>
										<td>${balance.realIncomeSum }</td>
										<td>${balance.reimbursementSum }</td>
										<td>${balance.status }</td>
										<td>${balance.userRealName }</td>
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
											<a href="${path }balanceManage?page=${pageNo-1 }&key=${key }"><i class="icon-double-angle-left"></i></a>
										</li>
										<c:forEach var="page" begin="1" end="${pageMax }">
											<li <c:if test="${pageNo==page }">class="active"</c:if>>
												<a href="${path }balanceManage?page=${page }&key=${key }">${page }</a>
											</li>
										</c:forEach>
										<li <c:choose><c:when test="${pageNo==pageMax }">class="next disabled"</c:when><c:otherwise>class="next"</c:otherwise></c:choose>>
											<a href="${path }balanceManage?page=${pageNo+1 }&key=${key }"><i class="icon-double-angle-right"></i></a>
										</li>
									</ul>
								</div>
							</div>
						</div>
<!-- 分页查询结束 -->							
					</div>
				</div>
<!-- 正文结束 -->	
<!-- 团队报账模板-->
				<div aria-hidden="true" style="display: none;" id="reimbursementModel" class="modal fade" tabindex="-1">
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
											<a data-toggle="tab" href="#costs5">
												<i class="green icon-credit-card bigger-120"></i>
												成本
											</a>
										</li>
									</ul>
					         	</div>
					         	<div class="tab-content no-border padding-6" style="z-index: 1400;">
					         		<div id="costs5" class="tab-pane fade in active costTable">
					         			<div class="tabbable tabs-left">
						         			<ul class="nav nav-tabs" style="width: 10%;text-align: center;">
												<li class="active">
													<a data-toggle="tab" href="#flight5">
														<i class="red icon-large icon-plane"></i>
														机票
													</a>
												</li>
												<li>
													<a data-toggle="tab" href="#hotel5">
														<i class="pink icon-large icon-building"></i>
														订房
													</a>
												</li>
												<li>
													<a data-toggle="tab" href="#meal5">
														<i class="orange icon-large icon-food"></i>
														订餐
													</a>
												</li>
												<li>
													<a data-toggle="tab" href="#ticket5">
														<i class="green icon-large icon-hdd"></i>
														门票
													</a>
												</li>
												<li>
													<a data-toggle="tab" href="#shuttle5">
														<i class="dark icon-large icon-truck"></i>
														订车
													</a>
												</li>
												<li>
													<a data-toggle="tab" href="#tickets5">
														<i class="blue icon-large icon-list-alt"></i>
														票务
													</a>
												</li>
												<li>
													<a data-toggle="tab" href="#comprehensive5">
														<i class="purple icon-large icon-money"></i>
														综费
													</a>
												</li>
												<li>
													<a data-toggle="tab" href="#other5">
														<i class="black icon-large icon-leaf"></i>
														其他
													</a>
												</li>
											</ul>
											<div class="tab-content no-padding" style="z-index: 1400;display: inline-block;float: right;right: -4px;width: 90%;overflow: visible;">
												<div id="flight5" class="tab-pane in active">
													<table class="table table-striped table-bordered table-hover no-margin">
														<thead>
															<tr>
																<th style="width: 10%;">日期</th>
																<th style="width: 15%;">内容</th>
																<th style="width: 15%;">供应商*</th>
																<th style="width: 10%;">成本小计</th>
																<th style="width: 10%;">电汇金额</th>
																<th style="width: 8%;">报账金额</th>
																<th style="width: 10%;">明细备注</th>
																<th style="width: 8%;">导游借款</th>
																<th style="width: 8%;">挂账</th>
																<th style="width: 8%;">状态</th>
															</tr>
														</thead>
														<tbody>
														</tbody>
										            </table>
												</div>
												
												<div id="hotel5" class="tab-pane">
													<table class="table table-striped table-bordered table-hover no-margin">
														<thead>
															<tr>
																<th style="width: 10%;">日期</th>
																<th style="width: 15%;">内容</th>
																<th style="width: 15%;">供应商*</th>
																<th style="width: 10%;">成本小计</th>
																<th style="width: 10%;">电汇金额</th>
																<th style="width: 8%;">报账金额</th>
																<th style="width: 10%;">明细备注</th>
																<th style="width: 8%;">导游借款</th>
																<th style="width: 8%;">挂账</th>
																<th style="width: 8%;">状态</th>
															</tr>
														</thead>
														<tbody>
														</tbody>
										            </table>
												</div>
												
												<div id="meal5" class="tab-pane">
													<table class="table table-striped table-bordered table-hover no-margin">
														<thead>
															<tr>
																<th style="width: 10%;">日期</th>
																<th style="width: 15%;">内容</th>
																<th style="width: 15%;">供应商*</th>
																<th style="width: 10%;">成本小计</th>
																<th style="width: 10%;">电汇金额</th>
																<th style="width: 10%;">借款人</th>
																<th style="width: 10%;">明细备注</th>
																<th style="width: 10%;">导游借款</th>
																<th style="width: 6%;">挂账</th>
																<th style="width: 6%;">状态</th>
															</tr>
														</thead>
														<tbody>
														</tbody>
										            </table>
												</div>
												
												<div id="ticket5" class="tab-pane">
													<table class="table table-striped table-bordered table-hover no-margin">
														<thead>
															<tr>
																<th style="width: 10%;">日期</th>
																<th style="width: 15%;">内容</th>
																<th style="width: 15%;">供应商*</th>
																<th style="width: 10%;">成本小计</th>
																<th style="width: 10%;">电汇金额</th>
																<th style="width: 10%;">借款人</th>
																<th style="width: 10%;">明细备注</th>
																<th style="width: 10%;">导游借款</th>
																<th style="width: 6%;">挂账</th>
																<th style="width: 6%;">状态</th>
															</tr>
														</thead>
														<tbody>
														</tbody>
										            </table>
												</div>
												
												<div id="shuttle5" class="tab-pane">
													<table class="table table-striped table-bordered table-hover no-margin">
														<thead>
															<tr>
																<th style="width: 10%;">日期</th>
																<th style="width: 15%;">内容</th>
																<th style="width: 15%;">供应商*</th>
																<th style="width: 10%;">成本小计</th>
																<th style="width: 10%;">电汇金额</th>
																<th style="width: 10%;">报账金额</th>
																<th style="width: 10%;">明细备注</th>
																<th style="width: 10%;">导游借款</th>
																<th style="width: 6%;">挂账</th>
																<th style="width: 6%;">状态</th>
															</tr>
														</thead>
														<tbody>
														</tbody>
										            </table>
												</div>
												
												<div id="tickets5" class="tab-pane">
													<table class="table table-striped table-bordered table-hover no-margin">
														<thead>
															<tr>
																<th style="width: 10%;">日期</th>
																<th style="width: 15%;">内容</th>
																<th style="width: 15%;">供应商*</th>
																<th style="width: 10%;">成本小计</th>
																<th style="width: 10%;">电汇金额</th>
																<th style="width: 10%;">报账金额</th>
																<th style="width: 10%;">明细备注</th>
																<th style="width: 10%;">导游借款</th>
																<th style="width: 6%;">挂账</th>
																<th style="width: 6%;">状态</th>
															</tr>
														</thead>
														<tbody>
														</tbody>
										            </table>
												</div>
												<div id="comprehensive5" class="tab-pane">
												<table class="table table-striped table-bordered table-hover no-margin">
													<thead>
														<tr>
															<th style="width: 10%;">日期</th>
															<th style="width: 15%;">内容</th>
															<th style="width: 15%;">供应商*</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">电汇金额</th>
															<th style="width: 10%;">报账金额</th>
															<th style="width: 10%;">明细备注</th>
															<th style="width: 10%;">导游借款</th>
															<th style="width: 6%;">挂账</th>
															<th style="width: 6%;">状态</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
									            </table>
											</div>
											
											<div id="other5" class="tab-pane">
												<table class="table table-striped table-bordered table-hover no-margin">
													<thead>
														<tr>
															<th style="width: 10%;">日期</th>
															<th style="width: 15%;">内容</th>
															<th style="width: 15%;">供应商*</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">电汇金额</th>
															<th style="width: 10%;">报账金额</th>
															<th style="width: 10%;">明细备注</th>
															<th style="width: 10%;">导游借款</th>
															<th style="width: 6%;">挂账</th>
															<th style="width: 6%;">状态</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
									            </table>
											</div>
											<span id="changeCostBlue" class="blue">*蓝色为成本收入变更</span>
											<span id="reimbursementCostRed" class="red" style="display:none">*红色为报账新增成本</span>	
						         			</div><!-- tab content 结束 -->
					         			</div><!-- 左tab 结束 -->
					         		</div><!-- 成本tab结束 -->
					         	</div>
					         	<div class="tab-content no-border padding-6" style="z-index: 1400;">
					         		<div class="tab-pane fade in active costTable">
					         			<div style="background-color: silver;font-size: 14px;padding: 3px;padding-left: 10px;color: white;">导游借款</div>
					         			<table class="table table-striped table-bordered table-hover no-margin">
											<thead>
												<tr>
													<th style="width: 10%;">日期</th>
													<th style="width: 15%;">借款额</th>
													<th style="width: 40%;">明细备注</th>
													<th style="width: 10%;">财务操作人</th>
													<th style="width: 10%;">状态</th>
												</tr>
											</thead>
											<tbody id="loanTable">
											</tbody>
							            </table>
							            <div style="background-color: silver;font-size: 14px;padding: 3px;padding-left: 10px;color: white;">统计信息</div>
							            <table class="table table-striped table-bordered table-hover no-margin">
											<tbody>
												<tr>
													<td style="width: 10%;">实付总计</td>
													<td id="realPaySum" style="width: 15%;"></td>
													<td style="width: 10%;">应收总计</td>
													<td id="willIncomeSum" style="width: 15%;"></td>
													<td style="width: 10%;">报账总计</td>
													<td id="reimbursementSum" style="width: 15%;"></td>
													<td style="width: 10%;">人头*</td>
													<td id="headAmount" style="width: 15%;"><input class="form-control" style="width:100%;" type="text"></td>
												</tr>
											</tbody>
							            </table>
					         		</div><!-- 成本tab结束 -->
					         	</div>
					        </div>
							<div class="modal-footer no-margin-top">
								<shiro:hasPermission name="balance:cancel">
									<button id="cancel" class="btn btn-sm btn-danger pull-left" data-dismiss="modal">
										<i class="icon-remove"></i>
										退回报账
									</button>
								</shiro:hasPermission>
								<shiro:hasPermission name="balance:update">
									<button id="reimbursementApplication" class="btn btn-sm btn-success pull-right" data-dismiss="modal">
										<i class="icon-save"></i>
										核销并提交结算
									</button>
								</shiro:hasPermission>
						 	 </div>
						</div><!-- /.modal-content -->
					</div><!-- /.modal -->
				</div>
<!-- 团队报账结束 -->
	
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
		$("#balanceManage").addClass("active");
		$(".modal-dialog").attr("style","width:70%;");
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
				$("#table").find("input").prop("checked",false);
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
		/*团队报账*/
		$("#reimbursement").click(function(){
			var checkbox = $("#table").find("input:checked");
			if(checkbox.length==0){
				alert("请选择一个团队");
				$(this).attr("href","#");
			}else if(checkbox.length>1){
				alert("只能选择一个团队");
				$(this).attr("href","#");
			}else{
				$(this).attr("href","#reimbursementModel");
				var tourId = checkbox.parent().parent().parent().attr("id");
				$("#reimbursementApplication").parent().attr("id",tourId);
				var myData = {tourId:tourId};
				find(myData);
			}
		});
		function find(myData){
			$.ajax({
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"${path }balanceManage/find",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
					/* 设置成本 */
		        	var flight = $("#flight5").find("tbody");
		        	var hotel = $("#hotel5").find("tbody");
		        	var meal = $("#meal5").find("tbody");
		        	var ticket = $("#ticket5").find("tbody");
		        	var shuttle = $("#shuttle5").find("tbody");
		        	var tickets = $("#tickets5").find("tbody");
		        	var comprehensive = $("#comprehensive5").find("tbody");
		        	var other = $("#other5").find("tbody");
		        	flight.html("");
		        	hotel.html("");
		        	meal.html("");
		        	ticket.html("");
		        	shuttle.html("");
		        	tickets.html("");
		        	comprehensive.html("");
		        	other.html("");
		        	/*设置人头*/
		        	var headAmount = $("<td></td>");
		        	if(data.reimbursementTable==undefined){
		        		headAmount.html("");
		        	}else{
		        		headAmount.html(data.reimbursementTable.headAmount);
		        	}
		        	$("#headAmount").html(headAmount.html());
		        	/* 设置成本 */
		        	$.each(data.costs,function(){
		        		var guideLoan = $("<td></td>");
		        		var bill = $("<td></td>");
	        			if(this.costTable.bill){
	        				bill.html('<i class="icon-ok bigger-130"></i>');
	        			}
	        			if(this.costTable.lend){
	        				guideLoan.html('<i class="icon-ok bigger-130"></i>');
	        			}
		        		var tbody;
		        		if(this.costTable.supplierScopeId==1){
		        			tbody = flight;
		        		}else if(this.costTable.supplierScopeId==2){
		        			tbody = hotel;
		        		}else if(this.costTable.supplierScopeId==3){
		        			tbody = meal;
		        		}else if(this.costTable.supplierScopeId==4){
		        			tbody = ticket;
		        		}else if(this.costTable.supplierScopeId==5){
		        			tbody = shuttle;
		        		}else if(this.costTable.supplierScopeId==6){
		        			tbody = tickets;
		        		}else if(this.costTable.supplierScopeId==7){
		        			tbody = comprehensive;
		        		}else if(this.costTable.supplierScopeId==8){
		        			tbody = other;
		        		}
		        		var tr = $('<tr id="'+this.costTable.id+'">'+
										'<td>'+(this.costTable.costDate==null?"":this.costTable.costDate.replace(/-/g,'/'))+'</td>'+
										'<td>'+this.contentName+'</td>'+
										'<td>'+this.supplierName+'</td>'+
										'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
										'<td>'+this.costTable.realCost.toFixed(2)+'</td>'+
										'<td>'+this.costTable.reimbursement.toFixed(2)+'</td>'+
										'<td>'+this.costTable.remark+'</td>'+
										'<td>'+guideLoan.html()+'</td>'+
										'<td>'+bill.html()+'</td>'+
										'<td style="vertical-align: middle;">'+this.payStatus+'</td>'+
									'</tr>');
		        		tbody.append(tr);
		        	});
		        	
		        	/* 设置成本变更 */
		        	if(data.changeCosts.length > 0){
		        		$("#changeCostBlue").attr("style","");
		        	}else{
		        		$("#changeCostBlue").attr("style","display:none");
		        	}
		        	$.each(data.changeCosts,function(){
		        		var guideLoan = $("<td></td>");
		        		var bill = $("<td></td>");
	        			if(this.costTable.bill){
	        				bill.html('<i class="icon-ok bigger-130"></i>');
	        			}
	        			if(this.costTable.lend){
	        				guideLoan.html('<i class="icon-ok bigger-130"></i>');
	        			}
		        		var tbody;
		        		if(this.costTable.supplierScopeId==1){
		        			tbody = flight;
		        		}else if(this.costTable.supplierScopeId==2){
		        			tbody = hotel;
		        		}else if(this.costTable.supplierScopeId==3){
		        			tbody = meal;
		        		}else if(this.costTable.supplierScopeId==4){
		        			tbody = ticket;
		        		}else if(this.costTable.supplierScopeId==5){
		        			tbody = shuttle;
		        		}else if(this.costTable.supplierScopeId==6){
		        			tbody = tickets;
		        		}else if(this.costTable.supplierScopeId==7){
		        			tbody = comprehensive;
		        		}else if(this.costTable.supplierScopeId==8){
		        			tbody = other;
		        		}
		        		var tr = $('<tr class="blue" id="'+this.costTable.id+'">'+
		        						'<td>'+(this.costTable.costDate==null?"":this.costTable.costDate.replace(/-/g,'/'))+'</td>'+
										'<td>'+this.contentName+'</td>'+
										'<td>'+this.supplierName+'</td>'+
										'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
										'<td>'+this.costTable.realCost.toFixed(2)+'</td>'+
										'<td>'+this.costTable.reimbursement.toFixed(2)+'</td>'+
										'<td>'+this.costTable.remark+'</td>'+
										'<td>'+guideLoan.html()+'</td>'+
										'<td>'+bill.html()+'</td>'+
										'<td style="vertical-align: middle;">'+this.payStatus+'</td>'+
									'</tr>');
		        		tbody.append(tr);
		        	});
		        	
		        	/* 设置成本变更 */
		        	if(data.reimbursementCosts.length > 0){
		        		$("#reimbursementCostRed").attr("style","");
		        	}else{
		        		$("#reimbursementCostRed").attr("style","display:none");
		        	}
		        	$.each(data.reimbursementCosts,function(){
		        		var bill = $("<td></td>");
	        			if(this.costTable.bill){
	        				bill.html('<i class="icon-ok bigger-130"></i>');
	        			}
		        		var tbody;
		        		if(this.costTable.supplierScopeId==1){
		        			tbody = flight;
		        		}else if(this.costTable.supplierScopeId==2){
		        			tbody = hotel;
		        		}else if(this.costTable.supplierScopeId==3){
		        			tbody = meal;
		        		}else if(this.costTable.supplierScopeId==4){
		        			tbody = ticket;
		        		}else if(this.costTable.supplierScopeId==5){
		        			tbody = shuttle;
		        		}else if(this.costTable.supplierScopeId==6){
		        			tbody = tickets;
		        		}else if(this.costTable.supplierScopeId==7){
		        			tbody = comprehensive;
		        		}else if(this.costTable.supplierScopeId==8){
		        			tbody = other;
		        		}
		        		var tr = $('<tr class="red" id="'+this.costTable.id+'">'+
		        						'<td>'+(this.costTable.costDate==null?"":this.costTable.costDate.replace(/-/g,'/'))+'</td>'+
										'<td>'+this.contentName+'</td>'+
										'<td>'+this.supplierName+'</td>'+
										'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
										'<td> </td>'+
										'<td>'+this.costTable.reimbursement.toFixed(2)+'</td>'+
										'<td>'+this.costTable.remark+'</td>'+
										'<td></td>'+
										'<td>'+bill.html()+'</td>'+
										'<td style="vertical-align: middle;">报账新增</td>'+
									'</tr>');
		        		tbody.append(tr);
		        	});
		        	
		        	/* 设置借款 */
		        	$("#loanTable").html("");
		        	$.each(data.loans,function(){
		        		var tr;
	        			tr = $('<tr>'+
	        						'<td>'+this.loanTable.loanDate+'</td>'+
	        						'<td>'+this.loanTable.loanAmount.toFixed(2)+'</td>'+
	        						'<td>'+this.loanTable.remark+'</td>'+
	        						'<td>'+this.lenderRealName+'</td>'+
	        						'<td>'+this.status+'</td>'+
	        					'</tr>');
		        		$("#loanTable").append(tr);
		        	});
		        	/* 设置借款总计、最大借款额 */
		        	$("#reimbursementSum").text(data.reimbursementSum);
		        	$("#realPaySum").text(data.realPaySum);
		        	$("#willIncomeSum").text(data.willIncomeSum);
		        }  
			});
		}
		
		$("#reimbursementApplication").click(function(){
			var tourId = $(this).parent().attr("id")
			var myData = {tourId:tourId};
			$("#table").find("#"+tourId).remove();
			$.ajax({
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"${path }balanceManage/update",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	
		        }
			});
		});
		
		$("#cancel").click(function(){
			var tourId = $(this).parent().attr("id")
			var myData = {tourId:tourId};
			$("#table").find("#"+tourId).remove();
			$.ajax({
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"${path }balanceManage/cancel",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	
		        }
			});
		});
	});
	
</script>
