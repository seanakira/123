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
								<a id="createTour" role="button" data-toggle="modal" href="#">付款管理</a>
							</li>
						</ul><!-- .breadcrumb -->
	
						<div class="nav-search" id="nav-search">
							<form class="form-search" action="${path }payManage" method="get">
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
									<th aria-label="" style="width: 2%;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">
										<label>
											<input class="ace" type="checkbox">
											<span class="lbl"></span>
										</label>
									</th>
									<th aria-label="Price: activate to sort column ascending" style="width: 10%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
										团号
									</th>
									<th aria-label="Price: activate to sort column ascending" style="width: 15%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
										线路
									</th>
									<th aria-label="Price: activate to sort column ascending" style="width: 5%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
										导游借款
									</th>
									<th aria-label="Price: activate to sort column ascending" style="width: 5%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
										供应商电汇
									</th>
									<th aria-label="Price: activate to sort column ascending" style="width: 5%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
										实付总计
									</th>
									<th aria-label="Price: activate to sort column ascending" style="width: 5%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
										应付金额
									</th>
									<th aria-label="Price: activate to sort column ascending" style="width: 5%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
										预估成本
									</th>
									<th aria-label="Price: activate to sort column ascending" style="width: 10%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
										出团日期
									</th>
									<th aria-label="Price: activate to sort column ascending" style="width: 5%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
										状态
									</th>
									<th aria-label="Price: activate to sort column ascending" style="width: 5%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
										团控人
									</th>
									<th aria-label="" style="width: 5%;" colspan="1" rowspan="1" role="columnheader" class="sorting_disabled">
										操作
									</th>
								</tr>
							</thead>
	
							<tbody id="table" aria-relevant="all" aria-live="polite" role="alert">
	<!-- 列表循环 -->				
								<c:forEach var="pay" items="${pays }" varStatus="status">
									<tr">
										<td class="center  sorting_1">
												<label>
													<input class="ace" type="checkbox">
													<span class="lbl"></span>
												</label>
										</td>
										<td id="${pay.localTourTable.id }"><a id="editTour" role="button" data-toggle="modal" href="#edit">${pay.localTourTable.tourNo }</a></td>
										<td>${pay.localTourTable.tourName }</td>
										<td>${pay.loan }</td>
										<td>${pay.remittance }</td>
										<td>${pay.realPay }</td>
										<td>${pay.loan+pay.remittance }</td>
										<td>${pay.cost }</td>
										<td>${pay.localTourTable.startTime }</td>
										<td>
											<c:choose>
												<c:when test="${pay.localTourTable.enable }">
													${pay.status }
												</c:when>
												<c:otherwise>
													已取消
												</c:otherwise>
											</c:choose>
										</td>
										<td>${pay.realName }</td>
										<td id="${pay.localTourTable.id }">
											<c:if test="${pay.localTourTable.status==2 }">
												<a class="orange action" href="#" title="取消报送">
													<i id="0" class="icon-reply bigger-130"></i>
												</a>
											</c:if>
										</td>
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
											<a href="/localtour/payManage?page=${pageNo-1 }&key=${key }"><i class="icon-double-angle-left"></i></a>
										</li>
										<c:forEach var="page" begin="1" end="${pageMax }">
											<li <c:if test="${pageNo==page }">class="active"</c:if>>
												<a href="/localtour/payManage?page=${page }&key=${key }">${page }</a>
											</li>
										</c:forEach>
										<li <c:choose><c:when test="${pageNo==pageMax }">class="next disabled"</c:when><c:otherwise>class="next"</c:otherwise></c:choose>>
											<a href="/localtour/payManage?page=${pageNo+1 }&key=${key }"><i class="icon-double-angle-right"></i></a>
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
											<a data-toggle="tab" href="#costs3">
												<i class="green icon-credit-card bigger-120"></i>
												成本
											</a>
										</li>
									</ul>
					         	</div>
					         	
					         	<div class="tab-content no-border padding-6" style="z-index: 1400;">
					         		<div id="costs3" class="tab-pane fade in active costTable">
					         			<div class="tabbable tabs-left">
						         			<ul class="nav nav-tabs" style="width: 10%;text-align: center;">
												<li class="active">
													<a data-toggle="tab" href="#flight3">
														<i class="red icon-large icon-plane"></i>
														机票
													</a>
												</li>
												<li>
													<a data-toggle="tab" href="#hotel3">
														<i class="pink icon-large icon-building"></i>
														订房
													</a>
												</li>
												<li>
													<a data-toggle="tab" href="#meal3">
														<i class="orange icon-large icon-food"></i>
														订餐
													</a>
												</li>
												<li>
													<a data-toggle="tab" href="#ticket3">
														<i class="green icon-large icon-hdd"></i>
														门票
													</a>
												</li>
												<li>
													<a data-toggle="tab" href="#shuttle3">
														<i class="dark icon-large icon-truck"></i>
														订车
													</a>
												</li>
												<li>
													<a data-toggle="tab" href="#tickets3">
														<i class="blue icon-large icon-list-alt"></i>
														票务
													</a>
												</li>
												<li>
													<a data-toggle="tab" href="#comprehensive3">
														<i class="purple icon-large icon-money"></i>
														综费
													</a>
												</li>
												<li>
													<a data-toggle="tab" href="#other3">
														<i class="black icon-large icon-leaf"></i>
														其他
													</a>
												</li>
											</ul>
											<div class="tab-content no-padding" style="z-index: 1400;display: inline-block;float: right;right: -4px;width: 90%;overflow: visible;">
												<div id="flight3" class="tab-pane in active">
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
																<th style="width: 2%;">
																	<a class="blue addCost" href="#">
																		<i class="icon-plus bigger-130"></i>
																	</a>
																</th>
															</tr>
														</thead>
														<tbody>
														</tbody>
										            </table>
												</div>
												
												<div id="hotel3" class="tab-pane">
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
																<th style="width: 2%;">
																	<a class="blue addCost" href="#">
																		<i class="icon-plus bigger-130"></i>
																	</a>
																</th>
															</tr>
														</thead>
														<tbody>
														</tbody>
										            </table>
												</div>
												
												<div id="meal3" class="tab-pane">
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
																<th style="width: 2%;">
																	<a class="blue addCost" href="#">
																		<i class="icon-plus bigger-130"></i>
																	</a>
																</th>
															</tr>
														</thead>
														<tbody>
														</tbody>
										            </table>
												</div>
												
												<div id="ticket3" class="tab-pane">
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
																<th style="width: 2%;">
																	<a class="blue addCost" href="#">
																		<i class="icon-plus bigger-130"></i>
																	</a>
																</th>
															</tr>
														</thead>
														<tbody>
														</tbody>
										            </table>
												</div>
												
												<div id="shuttle3" class="tab-pane">
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
																<th style="width: 2%;">
																	<a class="blue addCost" href="#">
																		<i class="icon-plus bigger-130"></i>
																	</a>
																</th>
															</tr>
														</thead>
														<tbody>
														</tbody>
										            </table>
												</div>
												
												<div id="tickets3" class="tab-pane">
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
																<th style="width: 2%;">
																	<a class="blue addCost" href="#">
																		<i class="icon-plus bigger-130"></i>
																	</a>
																</th>
															</tr>
														</thead>
														<tbody>
														</tbody>
										            </table>
												</div>
												<div id="comprehensive3" class="tab-pane">
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
																<th style="width: 2%;">
																	<a class="blue addCost" href="#">
																		<i class="icon-plus bigger-130"></i>
																	</a>
																</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
									            </table>
											</div>
											
											<div id="other3" class="tab-pane">
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
																<th style="width: 2%;">
																	<a class="blue addCost" href="#">
																		<i class="icon-plus bigger-130"></i>
																	</a>
																</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
									            </table>
											</div>		
						         			</div><!-- tab content 结束 -->
					         			</div><!-- 左tab 结束 -->
					         		</div><!-- 成本tab结束 -->
					         	</div>
					         </div>
							<div class="modal-footer no-margin-top">
								<button class="btn btn-sm btn-danger pull-left" data-dismiss="modal">
									<i class="icon-remove"></i>
									取消
								</button>
								<button id="saveEdit" class="btn btn-sm btn-success pull-right" data-dismiss="modal">
									<i class="icon-save"></i>
									保存
								</button>
						 	 </div>
						</div><!-- /.modal-content -->
					</div><!-- /.modal -->
				</div>
<!-- 编辑结束 -->
	
<jsp:include page="../../../resources/include/footer.jsp"></jsp:include>

<!-- 下拉搜索依赖 -->
<script src="${path }resources/assets/js/chosen.jquery.min.js"></script>
<!-- 日历组件依赖 -->
<script src="${path }resources/assets/js/jquery-ui-1.10.3.full.min.js"></script>

<script type="text/javascript">
	$(function(){
	/* 初始化 */
		/* 样式 */
		$("#statementManage").addClass("open");
		$("#statementManage").children("ul").attr("style","display:block");
		$("#payManage").addClass("active");
		$("#create").find("input").attr("style","width:100%;");
		$("#create").find("select").attr("style","width:100%;");
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
		
	/* 初始化选项 */
		/* 全局 */
		var selectInfo;
		/* 新建初始化 */
		var selects = $("#create").find("select");
		$.ajax({  
	        type: "GET",  
	        contentType:"application/json;charset=utf-8",  
	        url:"/localtour/localTourManage/getCreateInfo",
	        dataType: "json",  
	        async: false,  
	        success:function(data){
	        	selectInfo = {flightContents : data.flightContents,
								flightSuppliers : data.flightSuppliers,
								hotelContents : data.hotelContents,
								hotelSuppliers : data.hotelSuppliers,
								mealContents : data.mealContents,
								mealSuppliers : data.mealSuppliers,
								ticketContents : data.ticketContents,
								ticketSuppliers : data.ticketSuppliers,
								shuttleContents : data.shuttleContents,
								shuttleSuppliers : data.shuttleSuppliers,
								ticketsContents : data.ticketsContents,
								ticketsSuppliers : data.ticketsSuppliers,
								comprehensiveContents : data.comprehensiveContents,
								comprehensiveSuppliers : data.comprehensiveSuppliers,
								otherContents : data.otherContents,
								otherSuppliers : data.otherSuppliers};
	        }  
		});
	
	/* 成本 */
		/* 成本小计 */
		$(".costTable").delegate(".costPlus","blur",function(){
			var product = 1;
			var costPlus = $(this).parents("tr").find(".costPlus");
			costPlus.each(function(){
				if(isNaN(parseInt($(this).val()))){
					product = product * 0;
				}else{
					product = product * parseInt($(this).val());
				}
			})
			costPlus.last().parent().next().text(product);
		});
		/* 成本增加 */
		$(".addCost").click(function(){
			var tbody = $(this).parents("table").children("tbody");
			tbody.append("<tr>"+$("#costModel").html()+"</tr>");
			var tr = tbody.children("tr").not("#costModel").last();
			tr.find("#costTime").attr("id","").datepicker({
				showOtherMonths: true,
				selectOtherMonths: false,
			});
			var selects = tr.find("select");
			if($(this).parents("div").attr("id")=="flight"||$(this).parents("div").attr("id")=="flight3"){
				tr.children("td").last().append('<input type="hidden" value="1" />');
				$.each(selectInfo.flightContents,function(){
	        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
	        	});
	        	$.each(selectInfo.flightSuppliers,function(){
	        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
	        	});
			}else if($(this).parents("div").attr("id")=="hotel"||$(this).parents("div").attr("id")=="hotel3"){
				tr.children("td").last().append('<input type="hidden" value="2" />');
				$.each(selectInfo.hotelContents,function(){
	        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
	        	});
	        	$.each(selectInfo.hotelSuppliers,function(){
	        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
	        	});
			}else if($(this).parents("div").attr("id")=="meal"||$(this).parents("div").attr("id")=="meal3"){
				tr.children("td").last().append('<input type="hidden" value="3" />');
				$.each(selectInfo.mealContents,function(){
	        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
	        	});
	        	$.each(selectInfo.mealSuppliers,function(){
	        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
	        	});
			}else if($(this).parents("div").attr("id")=="ticket"||$(this).parents("div").attr("id")=="ticket3"){
				tr.children("td").last().append('<input type="hidden" value="4" />');
				$.each(selectInfo.ticketContents,function(){
	        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
	        	});
	        	$.each(selectInfo.ticketSuppliers,function(){
	        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
	        	});
			}else if($(this).parents("div").attr("id")=="shuttle"||$(this).parents("div").attr("id")=="shuttle3"){
				tr.children("td").last().append('<input type="hidden" value="5" />');
				$.each(selectInfo.shuttleContents,function(){
	        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
	        	});
	        	$.each(selectInfo.shuttleSuppliers,function(){
	        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
	        	});
			}else if($(this).parents("div").attr("id")=="tickets"||$(this).parents("div").attr("id")=="tickets3"){
				tr.children("td").last().append('<input type="hidden" value="6" />');
				$.each(selectInfo.ticketsContents,function(){
	        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
	        	});
	        	$.each(selectInfo.ticketsSuppliers,function(){
	        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
	        	});
			}else if($(this).parents("div").attr("id")=="comprehensive"||$(this).parents("div").attr("id")=="comprehensive3"){
				tr.children("td").last().append('<input type="hidden" value="7" />');
				$.each(selectInfo.comprehensiveContents,function(){
	        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
	        	});
	        	$.each(selectInfo.comprehensiveSuppliers,function(){
	        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
	        	});
			}else if($(this).parents("div").attr("id")=="other"||$(this).parents("div").attr("id")=="other3"){
				tr.children("td").last().append('<input type="hidden" value="8" />');
				$.each(selectInfo.otherContents,function(){
	        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
	        	});
	        	$.each(selectInfo.otherSuppliers,function(){
	        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
	        	});
			}
			var guides = $(this).parents(".costTable").prev().find("#guides");
			var names = guides.find("option:selected");
			if(guides.val()!=undefined){
				for (var int = 0; int < guides.val().length; int++) {
					selects.eq(2).append('<option value="'+guides.val()[int]+'">'+names.eq(int).text()+'</option>');
				} 
			}
			selects.eq(2).append('<option value="'+'<%=((UserTable)session.getAttribute("user")).getId()%>'+'">'+'<%=((UserTable)session.getAttribute("user")).getRealName()%>'+'</option>');
			selects.chosen();
			selects.next().attr("style","width:100%;");
		});
	
	/* 改状态 */
		$("#table").delegate(".action","click",function(){
			var obj = $(this);
			var td = obj.parent();
			var myData = {id:td.attr("id")};
			var status = obj.children("i").attr("id");
			td.html('<i class="icon-spinner icon-spin grey bigger-130"></i>');
			$.ajax({
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"/localtour/localTourManage/chanageStatus/"+status,  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	window.location.reload();
		        }  
			});
		});
	
	/* 编辑 */
		/* 编辑读取 */
 		$("#table").delegate("#editTour","click",function(){
			var myData = {tourId:$(this).parent().attr("id")};
			$("#saveEdit").parent().attr("id",myData.tourId);
			var tourUserName = $(this).parent().prev().text();
			$.ajax({
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"/localtour/localTourManage/find",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
					/* 设置成本 */
		        	var flight = $("#flight3").find("tbody");
		        	var hotel = $("#hotel3").find("tbody");
		        	var meal = $("#meal3").find("tbody");
		        	var ticket = $("#ticket3").find("tbody");
		        	var shuttle = $("#shuttle3").find("tbody");
		        	var tickets = $("#tickets3").find("tbody");
		        	var comprehensive = $("#comprehensive3").find("tbody");
		        	var other = $("#other3").find("tbody");
		        	flight.html("");
		        	hotel.html("");
		        	meal.html("");
		        	ticket.html("");
		        	shuttle.html("");
		        	tickets.html("");
		        	comprehensive.html("");
		        	other.html("");
		        	$.each(data.costs,function(){
		        		var realCost = $("<td>/td<>");
		        		if(this.costTable.isRemittance){
		        			realCost.html(this.costTable.realCost);
		        		}else{
		        			realCost.html("<input id='remittance' class='form-control' type='text' value='"+this.costTable.realCost+"' />");
		        		}
		        		var guideLoan = $("<td></td>");
		        		if(this.costTable.realCost==0){
		        			guideLoan.html('<label><input class="ace" type="checkbox"><span class="lbl"></span></label>');
		        		}
		        		if(this.costTable.supplierScopeId==1){
		        			flight.append('<tr>'+
			    								'<td>'+this.costTable.costDate.replace(/-/g,'/')+'</td>'+
			    								'<td>'+this.contentName+'</td>'+
			    								'<td>'+this.supplierName+'</td>'+
			    								'<td>'+this.costTable.cost*this.costTable.count*this.costTable.days+'</td>'+
			    								'<td>'+realCost.html()+'</td>'+
			    								'<td>'+this.borrowUserName+'</td>'+
			    								'<td><input class="form-control" type="text" value="'+this.costTable.remark+'" /></td>'+
			    								'<td>'+guideLoan.html()+'</td>'+
			    								'<td id="'+this.costTable.id+'"style="vertical-align: middle;"><a class="green" href="#" title="汇款确认"><i class="icon-ok bigger-130"></i></a><input type="hidden" value="1" /></td>'+
			    					'</tr>');
		        		}else if(this.costTable.supplierScopeId==2){
		        			hotel.append('<tr>'+
					        					'<td>'+this.costTable.costDate.replace(/-/g,'/')+'</td>'+
			    								'<td>'+this.contentName+'</td>'+
			    								'<td>'+this.supplierName+'</td>'+
			    								'<td>'+this.costTable.cost*this.costTable.count*this.costTable.days+'</td>'+
			    								'<td>'+realCost.html()+'</td>'+
			    								'<td>'+this.borrowUserName+'</td>'+
			    								'<td><input class="form-control" type="text" value="'+this.costTable.remark+'" /></td>'+
			    								'<td><label><input class="ace" type="checkbox"><span class="lbl"></span></label></td>'+
			    								'<td id="'+this.costTable.id+'"style="vertical-align: middle;"><a class="green" href="#" title="汇款确认"><i class="icon-ok bigger-130"></i></a><input type="hidden" value="1" /></td>'+
			    					'</tr>');
		        		}else if(this.costTable.supplierScopeId==3){
		        			meal.append('<tr>'+
					        					'<td>'+this.costTable.costDate.replace(/-/g,'/')+'</td>'+
			    								'<td>'+this.contentName+'</td>'+
			    								'<td>'+this.supplierName+'</td>'+
			    								'<td>'+this.costTable.cost*this.costTable.count*this.costTable.days+'</td>'+
			    								'<td>'+realCost.html()+'</td>'+
			    								'<td>'+this.borrowUserName+'</td>'+
			    								'<td><input class="form-control" type="text" value="'+this.costTable.remark+'" /></td>'+
			    								'<td><label><input class="ace" type="checkbox"><span class="lbl"></span></label></td>'+
			    								'<td id="'+this.costTable.id+'"style="vertical-align: middle;"><a class="green" href="#" title="汇款确认"><i class="icon-ok bigger-130"></i></a><input type="hidden" value="1" /></td>'+
			    					'</tr>');
		        		}else if(this.costTable.supplierScopeId==4){
		        			ticket.append('<tr>'+
					        					'<td>'+this.costTable.costDate.replace(/-/g,'/')+'</td>'+
			    								'<td>'+this.contentName+'</td>'+
			    								'<td>'+this.supplierName+'</td>'+
			    								'<td>'+this.costTable.cost*this.costTable.count*this.costTable.days+'</td>'+
			    								'<td>'+realCost.html()+'</td>'+
			    								'<td>'+this.borrowUserName+'</td>'+
			    								'<td><input class="form-control" type="text" value="'+this.costTable.remark+'" /></td>'+
			    								'<td><label><input class="ace" type="checkbox"><span class="lbl"></span></label></td>'+
			    								'<td id="'+this.costTable.id+'"style="vertical-align: middle;"><a class="green" href="#" title="汇款确认"><i class="icon-ok bigger-130"></i></a><input type="hidden" value="1" /></td>'+
			    					'</tr>');
		        		}else if(this.costTable.supplierScopeId==5){
		        			shuttle.append('<tr>'+
					        					'<td>'+this.costTable.costDate.replace(/-/g,'/')+'</td>'+
			    								'<td>'+this.contentName+'</td>'+
			    								'<td>'+this.supplierName+'</td>'+
			    								'<td>'+this.costTable.cost*this.costTable.count*this.costTable.days+'</td>'+
			    								'<td>'+realCost.html()+'</td>'+
			    								'<td>'+this.borrowUserName+'</td>'+
			    								'<td><input class="form-control" type="text" value="'+this.costTable.remark+'" /></td>'+
			    								'<td><label><input class="ace" type="checkbox"><span class="lbl"></span></label></td>'+
			    								'<td id="'+this.costTable.id+'"style="vertical-align: middle;"><a class="green" href="#" title="汇款确认"><i class="icon-ok bigger-130"></i></a><input type="hidden" value="1" /></td>'+
			    					'</tr>');
		        		}else if(this.costTable.supplierScopeId==6){
		        			tickets.append('<tr>'+
					        					'<td>'+this.costTable.costDate.replace(/-/g,'/')+'</td>'+
			    								'<td>'+this.contentName+'</td>'+
			    								'<td>'+this.supplierName+'</td>'+
			    								'<td>'+this.costTable.cost*this.costTable.count*this.costTable.days+'</td>'+
			    								'<td>'+realCost.html()+'</td>'+
			    								'<td>'+this.borrowUserName+'</td>'+
			    								'<td><input class="form-control" type="text" value="'+this.costTable.remark+'" /></td>'+
			    								'<td><label><input class="ace" type="checkbox"><span class="lbl"></span></label></td>'+
			    								'<td id="'+this.costTable.id+'"style="vertical-align: middle;"><a class="green" href="#" title="汇款确认"><i class="icon-ok bigger-130"></i></a><input type="hidden" value="1" /></td>'+
			    					'</tr>');
		        		}else if(this.costTable.supplierScopeId==7){
		        			comprehensive.append('<tr>'+
					        					'<td>'+this.costTable.costDate.replace(/-/g,'/')+'</td>'+
			    								'<td>'+this.contentName+'</td>'+
			    								'<td>'+this.supplierName+'</td>'+
			    								'<td>'+this.costTable.cost*this.costTable.count*this.costTable.days+'</td>'+
			    								'<td>'+realCost.html()+'</td>'+
			    								'<td>'+this.borrowUserName+'</td>'+
			    								'<td><input class="form-control" type="text" value="'+this.costTable.remark+'" /></td>'+
			    								'<td><label><input class="ace" type="checkbox"><span class="lbl"></span></label></td>'+
			    								'<td id="'+this.costTable.id+'"style="vertical-align: middle;"><a class="green" href="#" title="汇款确认"><i class="icon-ok bigger-130"></i></a><input type="hidden" value="1" /></td>'+
			    					'</tr>');
		        		}else if(this.costTable.supplierScopeId==8){
		        			other.append('<tr>'+
					        					'<td>'+this.costTable.costDate.replace(/-/g,'/')+'</td>'+
			    								'<td>'+this.contentName+'</td>'+
			    								'<td>'+this.supplierName+'</td>'+
			    								'<td>'+this.costTable.cost*this.costTable.count*this.costTable.days+'</td>'+
			    								'<td>'+realCost.html()+'</td>'+
			    								'<td>'+this.borrowUserName+'</td>'+
			    								'<td><input class="form-control" type="text" value="'+this.costTable.remark+'" /></td>'+
			    								'<td><label><input class="ace" type="checkbox"><span class="lbl"></span></label></td>'+
			    								'<td id="'+this.costTable.id+'"style="vertical-align: middle;"><a class="green" href="#" title="汇款确认"><i class="icon-ok bigger-130"></i></a><input type="hidden" value="1" /></td>'+
			    					'</tr>');
		        		}
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
		        	/* 点击电汇金额判断0 */
		        	$("#costs3").delegate("#remittance","blur",function(){
		        		if($(this).val()!=0){
		        			$(this).parent().siblings().eq(-2).html("");
		        		}else{
		        			$(this).parent().siblings().eq(-2).html('<label><input class="ace" type="checkbox"><span class="lbl"></span></label>');
		        			
		        		}
		        	});
		        	/* 勾选导游借款 */
		        	$("#costs3").delegate(".ace","click",function(){
		        		if($(this).prop("checked")){
		        			$(this).parent().parent().siblings().last().children("a").remove();
		        			$(this).parent().parent().siblings().eq(4).html("");
		        		}else{
		        			$(this).parent().parent().siblings().eq(4).html('<input id="remittance" class="form-control" value="0" type="text">');
		        			var a = $('<a class="green" href="#" title="汇款确认"><i class="icon-ok bigger-130"></i></a>');
		        			a.tooltip({
			        			show: null,
			        			position: {
			        				my: "left top",
			        				at: "left bottom"
			        			},
			        			open: function( event, ui ) {
			        				ui.tooltip.animate({ top: ui.tooltip.position().top + 10 }, "fast" );
			        			}
			        		});
		        			$(this).parent().parent().siblings().last().prepend(a);
		        		}
		        	});
		        	$("#costs3").find("tbody").removeAttr("delIds");
		        	/* 电汇金额失去焦点事件 */
					/* 要删除的ID */
		        	$("#edit").find(".delLine").click(function(){
		        		var parent = $(this).parent().parent().parent();
		        		if(parent.attr("delIds")==undefined){
		        			parent.attr("delIds",$(this).parent().attr("id"));
		        		}else{
		        			parent.attr("delIds",parent.attr("delIds")+","+$(this).parent().attr("id"));
		        		}
		        		
		        	});
		        	
		        	$("#edit").find(".datepicker").datepicker({
		    			showOtherMonths: true,
		    			selectOtherMonths: false,
		    		});
		        	$("#edit").find("select").chosen();
		        	$(".chosen-select").next().attr("style","width:100%;");
					$(".chosen-select").next().find("input").attr("style","height:100%;");
					$(".traffic").next().attr("style","width:100%;");
					$(".traffic").next().find("input").attr("style","height:100%;");
		        }  
			});
		});
		
	/*更新 */
		/* 全局行程删除id */
		var tripDelIds = new Array();
		$("#saveEdit").click(function(){
			var inputs = $("#edit").find("#tourInfo3").find("input");
			var selects = $("#edit").find("#tourInfo3").find("select");
			var id = $(this).parent().attr("id");
			var tourNo = inputs.eq(0).val();
			var tourName = inputs.eq(1).val();
			var businessTypeId = selects.eq(0).val();
			var tourTypeId = selects.eq(1).val();
			var regionId = selects.eq(2).val();
			var visitorTypeId = selects.eq(3).val();
			var customerAgencyId = selects.eq(4).val();
			var organizor = inputs.eq(7).val();
			var qpGuideNo = inputs.eq(8).val();
			var adultNo = inputs.eq(9).val();
			var childrenNo = inputs.eq(10).val();
			var startTime = new Date(inputs.eq(11).val());
			var endTime = new Date(inputs.eq(12).val());
			var guideIds  = selects.eq(5).val();
			var remark = inputs.eq(14).val();
			var days = (endTime-startTime)/1000/60/60/24+1;
			var localTourTable={id:id,tourNo:tourNo,tourName:tourName,businessTypeId:businessTypeId,tourTypeId:tourTypeId,regionId:regionId,visitorTypeId:visitorTypeId,
						   customerAgencyId:customerAgencyId,organizor:organizor,qpGuideNo:qpGuideNo,adultNo:adultNo,childrenNo:childrenNo,startTime:startTime,
						   endTime:endTime,remark:remark};
			
			var guideTimeTables = new Array();
			if(guideIds!=null){
				for (var int = 0; int < guideIds.length; int++) {
					guideTimeTables.push({
							tourId:id,
							guideId:guideIds[int],
							startTime:startTime,
							endTime:endTime});
				}
			}
			
			var arrTrs = $("#edit").find(".arrInfo").children("tr").not("#arrModel");
			var arrTables = new Array();
			for (var int = 0; int < arrTrs.length; int++) {
				var arrSelects = arrTrs.eq(int).find("select");
				var arrInputs = arrTrs.eq(int).find("input");
				if(arrSelects.eq(0).val()!=""||arrSelects.eq(1).children("option:selected").html()!="&nbsp;"||arrInputs.eq(2).val()!=""||arrInputs.eq(3).val()!=""||arrSelects.eq(2).val()!=""){
					arrTables.push({
						id:arrTrs.eq(int).find("td").last().attr("id"),
						tourId:id,
						originId:arrSelects.eq(0).val(),
						arrTraffic:arrSelects.eq(1).children("option:selected").text(),
						arrTime:new Date(arrInputs.eq(2).val()),
						arrTrafficNo:arrInputs.eq(3).val(),
						arrRegionId:arrSelects.eq(2).val()});	
				}
			}
			
			var departTrs = $("#edit").find(".departInfo").children("tr").not("#departModel");
			var departTables = new Array();
			for (var int = 0; int < departTrs.length; int++) {
				var departSelects = departTrs.eq(int).find("select");
				var departInputs = departTrs.eq(int).find("input");
				if(departSelects.eq(0).val()!=""||departSelects.eq(1).children("option:selected").html()!="&nbsp;"||departInputs.eq(2).val()!=""||departInputs.eq(3).val()!=""||departSelects.eq(2).val()!=""){
					departTables.push({
							id:departTrs.eq(int).find("td").last().attr("id"),
							tourId:id,
							destId:departSelects.eq(0).val(),
							departTraffic:departSelects.eq(1).children("option:selected").text(),
							departTime:new Date(departInputs.eq(2).val()),
							departTrafficNo:departInputs.eq(3).val(),
							departRegionId:departSelects.eq(2).val()});
				}
			}
			
			var tripTables = new Array();
			var tripsTbody = $("#trips3").find("div").not("#editTripModel").children("table").children("tbody");
			for (var int = 0; int < tripsTbody.length; int++) {
				var tripInputs =  tripsTbody.eq(int).find("input");
				var tripTextAreas =  tripsTbody.eq(int).find("textarea");
				if(tripsTbody.eq(int).attr("id")!=undefined||tripTextAreas.eq(0).val()!=""||tripInputs.eq(0).val()!=""||tripInputs.eq(1).val()!=""||tripInputs.eq(2).val()!=""||tripTextAreas.eq(1).val()!=""){
					tripTables.push({
							id:tripsTbody.eq(int).attr("id"),
							tourId:id,
							number:int,
							trip:tripTextAreas.eq(0).val(),
							meal:tripInputs.eq(0).val(),
							stay:tripInputs.eq(1).val(),
							traffic:tripInputs.eq(2).val(),
							remark:tripTextAreas.eq(1).val()});
					if(tripsTbody.eq(int).attr("id")!=undefined){
						tripDelIds.shift();
					}
					if(tripTextAreas.eq(0).val()==""&&tripInputs.eq(0).val()==""&&tripInputs.eq(1).val()==""&&tripInputs.eq(2).val()==""&&tripTextAreas.eq(1).val()==""){
						tripDelIds.push(tripsTbody.eq(int).attr("id"));
					}
				}
			}
			
			var costTables = new Array();
			var costTrs = $("#edit").find("#costs3").find("tbody").find("tr").not("#costModel");
			for (var int = 0; int < costTrs.length; int++) {
				var costInputs = costTrs.eq(int).find("input");
				var costSelects = costTrs.eq(int).find("select");
				costTables.push({
						id:costTrs.eq(int).children("td").last().attr("id"),
						tourId:id,
						costDate:new Date(costInputs.eq(0).val()),
						contentId:costSelects.eq(0).val(),
						supplierId:costSelects.eq(1).val(),
						cost:costInputs.eq(3).val(),
						count:costInputs.eq(4).val(),
						days:costInputs.eq(5).val(),
						borrowUserId:costSelects.eq(2).val(),
						supplierScopeId:costInputs.last().val(),
						remark:costInputs.eq(7).val()});
			}
			
			var incomeTables = new Array();
			var incomeTrs = $("#edit").find("#incomes3").find("tbody").find("tr").not("#incomeModel");
			for (var int = 0; int < incomeTrs.length; int++) {
				var incomeInputs = incomeTrs.eq(int).find("input");
				incomeTables.push({
					id:incomeTrs.eq(int).children("td").last().attr("id"),
					tourId:id,
					incomeDate:new Date(incomeInputs.eq(0).val()),
					customerAgencyId:incomeTrs.eq(int).find("select").val(),
					income:incomeInputs.eq(2).val(),
					remark:incomeInputs.eq(3).val()});
			}
			
			/* 成本删除ID拼装 */
			var costTbodys = $("#costs3").find("tbody");
			var costDelIds = "";
			var firstCost = true;
			$.each(costTbodys,function(){
				if($(this).attr("delIds")!=undefined){
					if(firstCost==true){
						costDelIds = $(this).attr("delIds");
						firstCost = false;
					}else{
						costDelIds = costDelIds+","+$(this).attr("delIds");
					}
				} 
			});
			var delIds = {
					"ArrTable":$("#edit").find(".arrInfo").attr("delids"),
					"DepartTable":$("#edit").find(".departInfo").attr("delids"),
					"TripTable":tripDelIds.toString(),
					"CostTable":costDelIds,
					"IncomeTable":$("#incomes3").find("tbody").attr("delids")};
			
			var fullLocalTourViewModel = {localTourTable:localTourTable,guideTimeTables:guideTimeTables,arrTables:arrTables,departTables:departTables,tripTables:tripTables,costTables:costTables,incomeTables:incomeTables,delIds:delIds};
			var myData = JSON.stringify(fullLocalTourViewModel);
			$.ajax({
		        type: "POST",  
		        contentType:"application/json;charset=utf-8",  
		        url:"/localtour/localTourManage/update",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	if(data==-1){
		        		alert("保存失败，请检查团号是否重复，基本信息必填项是否完整，开始日期是否大于结束日期");
		        	}else if(data==-2){
		        		alert("保存失败，请填写出发地或抵达地");
		        	}else if(data==-3){
		        		alert("保存失败，请填写前往地或离开");
		        	}else if(data==-4){
		        		alert("保存失败，请填写供应商或内容");
		        	}else if(data==-5){
		        		alert("保存失败，请填写客户");
		        	}
		        }
			 });
		});
	
	});
	
</script>
