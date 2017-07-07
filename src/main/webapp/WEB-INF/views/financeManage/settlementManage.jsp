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
	/* #checkTable a {
		position:relative;
	}
	#checkTable a:hover:before {
		position:absolute;
		top:20px;
		left:0;
		content:attr(title);
		color:#000000;
		border:1px solid #242424;
		background-color:#E5E5E5;
	} */
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
								<a id="" role="button" data-toggle="modal" href="#">结算管理</a>
							</li>
						</ul><!-- .breadcrumb -->
						<div class="accessBar" style="display: inline-block;">
							<div style="display: inline-block;">
								<shiro:hasPermission name="settlement:find">
									<a class="" id="settlement" data-toggle="modal" href="#" title="团队结算">
										<i class="icon-legal bigger-100"></i>
										团队结算
									</a>
								</shiro:hasPermission>
								<shiro:hasPermission name="settlement:change">
									<a class="" id="settlementChange" data-toggle="modal" href="#" title="结算调整">
										<i class="icon-medkit bigger-100"></i>
										结算调整
									</a>
								</shiro:hasPermission>
							</div>
						</div>
						<div class="nav-search" id="nav-search">
							<form class="form-search" action="${path }settlementManage" method="get">
								<span class="input-icon">
									<input name="key" placeholder="搜索 ..." class="nav-search-input" autocomplete="off" type="text" value="${key }" />
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
									<th aria-label="Price: activate to sort column ascending" style="width: 10%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
										团号
									</th>
									<th aria-label="Price: activate to sort column ascending" style="width: 10%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
										线路
									</th>
									<th aria-label="Price: activate to sort column ascending" style="width: 8%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
										成本总计
									</th>
									<th aria-label="Price: activate to sort column ascending" style="width: 8%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
										应付总计
									</th>
									<th aria-label="Price: activate to sort column ascending" style="width: 8%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
										实付总计
									</th>
									<th aria-label="Price: activate to sort column ascending" style="width: 8%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
										报账总计
									</th>
									<th aria-label="Price: activate to sort column ascending" style="width: 8%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
										应收总计
									</th>
									<th aria-label="Price: activate to sort column ascending" style="width: 8%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
										实收总计
									</th>
									<th aria-label="Price: activate to sort column ascending" style="width: 8%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
										实际毛利
									</th>
									<th aria-label="Price: activate to sort column ascending" style="width: 8%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
										实际毛利率
									</th>
									<th aria-label="Price: activate to sort column ascending" style="width: 8%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
										团控人
									</th>
									<th aria-label="Price: activate to sort column ascending" style="width: 8%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
										状态
									</th>
								</tr>
							</thead>
	
							<tbody id="table" aria-relevant="all" aria-live="polite" role="alert">
	<!-- 列表循环 -->				
								<c:forEach var="settlement" items="${settlements }" varStatus="status">
									<tr id="${settlement.localTourTable.id }">
										<td class="center  sorting_1">
												<label>
													<input class="ace" type="checkbox">
													<span class="lbl"></span>
												</label>
										</td>
										<td><a id="editTour" role="button" data-toggle="modal" href="#edit">${settlement.localTourTable.tourNo }</a></td>
										<td>${settlement.localTourTable.tourName }</td>
										<td>${settlement.costSum }</td>
										<td>${settlement.willPaySum }</td>
										<td>${settlement.realPaySum }</td>
										<td>${settlement.reimbursementSum }</td>
										<td>${settlement.willIncomeSum }</td>
										<td>${settlement.realIncomeSum }</td>
										<td>${settlement.realGrossProfit }</td>
										<td>${settlement.realGrossMargin }</td>
										<td>${settlement.userRealName }</td>
										<td>${settlement.status }</td>
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
											<a href="${path }settlementManage?page=${pageNo-1 }&key=${key }"><i class="icon-double-angle-left"></i></a>
										</li>
										<c:choose>
											<c:when test="${pageNo>6 }">
												<li <c:if test="${pageNo==page }">class="active"</c:if>>
														<a href="${path }settlementManage?page=${1 }&key=${key }">1</a>
												</li>
												<li>
													<a>...</a>
												</li>
												<c:forEach var="page" begin="${pageNo-5 }" end="${pageNo+4>pageMax?pageMax:pageNo+4 }">
													<li <c:if test="${pageNo==page }">class="active"</c:if>>
														<a href="${path }settlementManage?page=${page }&key=${key }">${page }</a>
													</li>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<c:forEach var="page" begin="1" end="${pageMax>10?10:pageMax }">
													<li <c:if test="${pageNo==page }">class="active"</c:if>>
														<a href="${path }settlementManage?page=${page }&key=${key }">${page }</a>
													</li>
												</c:forEach>
											</c:otherwise>
										</c:choose>
										<li <c:choose><c:when test="${pageNo==pageMax }">class="next disabled"</c:when><c:otherwise>class="next"</c:otherwise></c:choose>>
											<a href="${path }settlementManage?page=${pageNo+1 }&key=${key }"><i class="icon-double-angle-right"></i></a>
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
				<div aria-hidden="true" style="display: none;" id="check" class="modal fade" tabindex="-1">
					<div class="modal-dialog" style="width: 80%;">
						<div class="modal-content">
					        <div class="modal-header no-padding">
								<div class="table-header">
									结算审核
						 		</div>
						  	</div>
							<div class="modal-body no-padding">
					         	<div class="tab-content no-border padding-6" style="z-index: 1400;">
						         	<table class="table table-striped table-bordered table-hover no-margin">
										<thead>
											<tr>
												<th style="width: 6%;">&nbsp;</th>
												<th style="width: 18.8%;">团队预算</th>
												<th style="width: 18.8%;">团队执行</th>
												<th style="width: 18.8%;">应收应付</th>
												<th style="width: 18.8%;">实收实付</th>
												<th style="width: 18.8%;">团队报账</th>
											</tr>
										</thead>
										<tbody id="checkTable">
											<tr id="cost">
												<td>成本</td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
											</tr>
											<tr id="income">
												<td>收入</td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
											</tr>
											<tr id="grossProfit">
												<td>毛利</td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
											</tr>
											<tr id="grossMargin">
												<td>毛利率</td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
											</tr>
										</tbody>
									</table>
					       		</div>
					       	</div>
							<div class="modal-footer no-margin-top">
								<shiro:hasPermission name="settlement:cancel">
									<button id="cancel" class="btn btn-sm btn-danger pull-left" data-dismiss="modal">
										<i class="icon-remove"></i>
										驳回
									</button>
								</shiro:hasPermission>
								<shiro:hasPermission name="settlement:update">
									<button id="ok" class="btn btn-sm btn-success pull-right" data-dismiss="modal">
										<i class="icon-qrcode"></i>
										同意
									</button>
								</shiro:hasPermission>
						 	 </div>
						</div><!-- /.modal-content -->
					</div><!-- /.modal -->
				</div>
<!-- 编辑结束 -->
<!-- 结算调整模板 -->
				<div aria-hidden="true" style="display: none;" id="changeModel" class="modal fade" tabindex="-1">
					<div class="modal-dialog" style="width: 80%;">
						<div class="modal-content">
					        <div class="modal-header no-padding">
								<div class="table-header">
									结算调整
						 		</div>
						  	</div>
							<div class="modal-body no-padding">
					         	<div class="tabbable">
					         		<ul class="nav nav-tabs padding-18" id="myTab">
										<li class="active">
											<a data-toggle="tab" href="#costs4">
												<i class="green icon-credit-card bigger-120"></i>
												成本
											</a>
										</li>
										<li>
											<a data-toggle="tab" href="#incomes4">
												<i class="pink icon-briefcase bigger-120"></i>
												收入
											</a>
										</li>
									</ul>
					         	</div>
					         	
					         	<div class="tab-content no-border padding-6" style="z-index: 1400;">
					         		<div id="costs4" class="tab-pane fade in active costTable">
					         			<div class="tabbable tabs-left">
					         			<ul class="nav nav-tabs" style="width: 10%;text-align: center;">
											<li class="active">
												<a data-toggle="tab" href="#flight4">
													<i class="red icon-large icon-plane"></i>
													机票
												</a>
											</li>
											<li>
												<a data-toggle="tab" href="#hotel4">
													<i class="pink icon-large icon-building"></i>
													订房
												</a>
											</li>
											<li>
												<a data-toggle="tab" href="#meal4">
													<i class="orange icon-large icon-food"></i>
													订餐
												</a>
											</li>
											<li>
												<a data-toggle="tab" href="#ticket4">
													<i class="green icon-large icon-hdd"></i>
													门票
												</a>
											</li>
											<li>
												<a data-toggle="tab" href="#shuttle4">
													<i class="dark icon-large icon-truck"></i>
													订车
												</a>
											</li>
											<li>
												<a data-toggle="tab" href="#tickets4">
													<i class="blue icon-large icon-list-alt"></i>
													票务
												</a>
											</li>
											<li>
												<a data-toggle="tab" href="#comprehensive4">
													<i class="purple icon-large icon-money"></i>
													综费
												</a>
											</li>
											<li>
												<a data-toggle="tab" href="#other4">
													<i class="black icon-large icon-leaf"></i>
													其他
												</a>
											</li>
										</ul>
										<div class="tab-content no-padding" style="z-index: 1400;display: inline-block;float: right;right: -4px;width: 90%;overflow: visible;">
											<div id="flight4" class="tab-pane in active">
												<table class="table table-striped table-bordered table-hover no-margin">
													<thead>
														<tr>
															<th style="width: 10%;">日期</th>
															<th style="width: 15%;">内容</th>
															<th style="width: 15%;">供应商*</th>
															<th style="width: 5%;">成本*</th>
															<th style="width: 5%;">数量*</th>
															<th style="width: 5%;">天数*</th>
															<th style="width: 10%;">成本调整</th>
															<th style="width: 10%;">明细备注</th>
															<th style="width: 1%;">
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
											
											<div id="hotel4" class="tab-pane">
												<table class="table table-striped table-bordered table-hover no-margin">
													<thead>
														<tr>
															<th style="width: 10%;">日期</th>
															<th style="width: 15%;">内容</th>
															<th style="width: 15%;">供应商*</th>
															<th style="width: 5%;">成本*</th>
															<th style="width: 5%;">数量*</th>
															<th style="width: 5%;">天数*</th>
															<th style="width: 10%;">成本调整</th>
															<th style="width: 10%;">明细备注</th>
															<th style="width: 1%;">
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
											
											<div id="meal4" class="tab-pane">
												<table class="table table-striped table-bordered table-hover no-margin">
													<thead>
														<tr>
															<th style="width: 10%;">日期</th>
															<th style="width: 15%;">内容</th>
															<th style="width: 15%;">供应商*</th>
															<th style="width: 5%;">成本*</th>
															<th style="width: 5%;">数量*</th>
															<th style="width: 5%;">天数*</th>
															<th style="width: 10%;">成本调整</th>
															<th style="width: 10%;">明细备注</th>
															<th style="width: 1%;">
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
											
											<div id="ticket4" class="tab-pane">
												<table class="table table-striped table-bordered table-hover no-margin">
													<thead>
														<tr>
															<th style="width: 10%;">日期</th>
															<th style="width: 15%;">内容</th>
															<th style="width: 15%;">供应商*</th>
															<th style="width: 5%;">成本*</th>
															<th style="width: 5%;">数量*</th>
															<th style="width: 5%;">天数*</th>
															<th style="width: 10%;">成本调整</th>
															<th style="width: 10%;">明细备注</th>
															<th style="width: 1%;">
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
											
											<div id="shuttle4" class="tab-pane">
												<table class="table table-striped table-bordered table-hover no-margin">
													<thead>
														<tr>
															<th style="width: 10%;">日期</th>
															<th style="width: 15%;">内容</th>
															<th style="width: 15%;">供应商*</th>
															<th style="width: 5%;">成本*</th>
															<th style="width: 5%;">数量*</th>
															<th style="width: 5%;">天数*</th>
															<th style="width: 10%;">成本调整</th>
															<th style="width: 10%;">明细备注</th>
															<th style="width: 1%;">
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
											
											<div id="tickets4" class="tab-pane">
												<table class="table table-striped table-bordered table-hover no-margin">
													<thead>
														<tr>
															<th style="width: 10%;">日期</th>
															<th style="width: 15%;">内容</th>
															<th style="width: 15%;">供应商*</th>
															<th style="width: 5%;">成本*</th>
															<th style="width: 5%;">数量*</th>
															<th style="width: 5%;">天数*</th>
															<th style="width: 10%;">成本调整</th>
															<th style="width: 10%;">明细备注</th>
															<th style="width: 1%;">
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
											
											<div id="comprehensive4" class="tab-pane">
												<table class="table table-striped table-bordered table-hover no-margin">
													<thead>
														<tr>
															<th style="width: 10%;">日期</th>
															<th style="width: 15%;">内容</th>
															<th style="width: 15%;">供应商*</th>
															<th style="width: 5%;">成本*</th>
															<th style="width: 5%;">数量*</th>
															<th style="width: 5%;">天数*</th>
															<th style="width: 10%;">成本调整</th>
															<th style="width: 10%;">明细备注</th>
															<th style="width: 1%;">
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
											
											<div id="other4" class="tab-pane">
												<table class="table table-striped table-bordered table-hover no-margin">
													<thead>
														<tr>
															<th style="width: 10%;">日期</th>
															<th style="width: 15%;">内容</th>
															<th style="width: 15%;">供应商*</th>
															<th style="width: 5%;">成本*</th>
															<th style="width: 5%;">数量*</th>
															<th style="width: 5%;">天数*</th>
															<th style="width: 10%;">成本调整</th>
															<th style="width: 10%;">明细备注</th>
															<th style="width: 1%;">
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
					         		<div id="incomes4" class="tab-pane fade">
					         			<div class="tabbable tabs-left">
											<div class="tab-content no-padding" style="z-index: 1400;overflow: visible;">
												<table class="table table-striped table-bordered table-hover incomeTable">
														<thead>
															<tr>
																<th style="width: 15%">日期</th>
																<th>客户*</th>
																<th style="width: 10%">收入</th>
																<th style="width: 10%">实收</th>
																<th style="width: 20%">备注</th>
																<th style="width: 1%">
																	<a class="blue addIncome" href="#">
																		<i class="icon-plus bigger-130"></i>
																	</a>
																</th>
															</tr>
														</thead>
														<tbody>
											            </tbody>
										            </table>
												         			
						         			</div><!-- tab content 结束 -->
					         			</div><!-- 左tab 结束 -->
					         		</div><!-- 收入tab结束 -->
					         	</div>
					         	
					            
					         </div>
							<div class="modal-footer no-margin-top">
								<button class="btn btn-sm btn-danger pull-left" data-dismiss="modal">
									<i class="icon-remove"></i>
									取消
								</button>
								<button id="saveChange" class="btn btn-sm btn-success pull-right" data-dismiss="modal">
									<i class="icon-save"></i>
									保存
								</button>
						 	 </div>
						</div><!-- /.modal-content -->
					</div><!-- /.modal -->
				</div>
<!-- 结算调整模板 结束-->
<jsp:include page="../../../resources/include/footer.jsp"></jsp:include>

<!-- 下拉搜索依赖 -->
<script src="${path }resources/assets/js/chosen.jquery.min.js"></script>
<!-- 日历组件依赖 -->
<script src="${path }resources/assets/js/jquery-ui-1.10.3.full.min.js"></script>
<!-- 二维码依赖 -->
<script src="${path }resources/assets/js/jquery.qrcode.min.js"></script>
<script type="text/javascript">
	$(function(){
	/* 初始化 */
		/* 样式 */
		$("#financeManage").addClass("open");
		$("#financeManage").children("ul").attr("style","display:block");
		$("#settlementManage").addClass("active");
		$(".modal-dialog").attr("style","width:80%;");
		$("#settlement").hide();
		$("#settlementChange").hide();
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
				/* 设置按钮 */
				if($(this).siblings().last().text()=="待结算"){
					$("#settlement").show();
				}else{
					$("#settlement").hide();
				}
				if($(this).siblings().last().text()=="已结算"){
					$("#settlementChange").show();
				}else{
					$("#settlementChange").hide();
				}
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
	
	/*查看*/
		$("#settlement").click(function(){
			var checkbox = $("#table").find("input:checked");
			if(checkbox.length==0){
				alert("请选择一个团队");
				$(this).attr("href","#");
			}else if(checkbox.length>1){
				alert("只能选择一个团队");
				$(this).attr("href","#");
			}else{
				$(this).attr("href","#check");
				var tourId = checkbox.parent().parent().parent().attr("id");
				$("#ok").parent().attr("id",tourId);
				var myData = {tourId:tourId};
				$.ajax({
			        type: "GET",  
			        contentType:"application/json;charset=utf-8",  
			        url:"${path }settlementManage/find",  
			        data:myData,  
			        dataType: "json",  
			        async: false,  
			        success:function(data){
			        	$("#cost").children("td").eq(1).html('<a title="'+data.budgetCostSumInfo.replace(/,/g,"<br>")+'">'+data.budgetCostSum+'</a>');
			        	$("#cost").children("td").eq(2).html('<a title="'+data.executeCostSumInfo.replace(/,/g,"<br>")+'">'+data.executeCostSum+'</a>');
			        	$("#cost").children("td").eq(3).html('<a title="'+data.willCostSumInfo.replace(/,/g,"<br>")+'">'+data.willCostSum+'</a>');
			        	$("#cost").children("td").eq(4).html('<a title="'+data.realCostSumInfo.replace(/,/g,"<br>")+'">'+data.realCostSum+'</a>');
			        	$("#cost").children("td").eq(5).html('<a title="'+data.reimbursementCostSumInfo.replace(/,/g,"<br>")+'">'+data.reimbursementCostSum+'</a>');
			        	$("#income").children("td").eq(1).html('<a title="'+data.budgetIncomeSumInfo.replace(/,/g,"<br>")+'">'+data.budgetIncomeSum+'</a>');
			        	$("#income").children("td").eq(2).html('<a title="'+data.executeIncomeSumInfo.replace(/,/g,"<br>")+'">'+data.executeIncomeSum+'</a>');
			        	$("#income").children("td").eq(3).html('<a title="'+data.willIncomeSumInfo.replace(/,/g,"<br>")+'">'+data.willIncomeSum+'</a>');
			        	$("#income").children("td").eq(4).html('<a title="'+data.realIncomeSumInfo.replace(/,/g,"<br>")+'">'+data.realIncomeSum+'</a>');
			        	$("#income").children("td").eq(5).html('<a title="'+data.reimbursementIncomeSumInfo.replace(/,/g,"<br>")+'">'+data.reimbursementIncomeSum+'</a>');
			        	$("#grossProfit").children("td").eq(1).html(data.budgetGrossProfit);
			        	$("#grossProfit").children("td").eq(2).html(data.executeGrossProfit);
			        	$("#grossProfit").children("td").eq(3).html(data.willGrossProfit);
			        	$("#grossProfit").children("td").eq(4).html(data.realGrossProfit);
			        	$("#grossProfit").children("td").eq(5).html(data.reimbursementGrossProfit);
			        	$("#grossMargin").children("td").eq(1).html(data.budgetGrossMargin+"%");
			        	$("#grossMargin").children("td").eq(2).html(data.executeGrossMargin+"%");
			        	$("#grossMargin").children("td").eq(3).html(data.willGrossMargin+"%");
			        	$("#grossMargin").children("td").eq(4).html(data.realGrossMargin+"%");
			        	$("#grossMargin").children("td").eq(5).html(data.reimbursementGrossMargin+"%");
			        }  
				});
			}
		});
		
		$("#ok").click(function(){
			var tourId = $(this).parent().attr("id");
			$("#table").find("#"+tourId).remove();
			var myData = {tourId:tourId};
			$.ajax({
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"${path }settlementManage/update",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	
		        }  
			});
		});
		
		$("#cancel").click(function(){
			var tourId = $(this).parent().attr("id");
			$("#table").find("#"+tourId).remove();
			var myData = {tourId:tourId};
			$.ajax({
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"${path }settlementManage/cancel",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	
		        }  
			});
		});
		
		
		/* 选项初始化 */
		var inited = false;
		function init(){
			$.ajax({  
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"${path }settlementManage/settlementChangeInfo",
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
		}
		
		/* 结算变更 */
		$("#settlementChange").click(function(){
	 		var checkbox = $("#table").find("input:checked");
			if(checkbox.length==0){
				alert("请选择一个团队");
				$(this).attr("href","#");
			}else if(checkbox.length>1){
				alert("只能选择一个团队");
				$(this).attr("href","#");
			}else{
				if(inited==false){
					init();
					inited = true;
				}
				$(this).attr("href","#changeModel");
				var tourId = checkbox.parent().parent().parent().attr("id");
				$("#saveChange").parent().attr("id",tourId);
				var myData = {tourId:tourId};
				$.ajax({
			        type: "GET",  
			        contentType:"application/json;charset=utf-8",  
			        url:"${path }settlementManage/settlementChange",  
			        data:myData,  
			        dataType: "json",  
			        async: false,  
			        success:function(data){
			        	var flight = $("#flight4").find("tbody");
			        	var hotel = $("#hotel4").find("tbody");
			        	var meal = $("#meal4").find("tbody");
			        	var ticket = $("#ticket4").find("tbody");
			        	var shuttle = $("#shuttle4").find("tbody");
			        	var tickets = $("#tickets4").find("tbody");
			        	var comprehensive = $("#comprehensive4").find("tbody");
			        	var other = $("#other4").find("tbody");
			        	flight.html("");
			        	hotel.html("");
			        	meal.html("");
			        	ticket.html("");
			        	shuttle.html("");
			        	tickets.html("");
			        	comprehensive.html("");
			        	other.html("");
			        	$.each(data.costs,function(){
			        		var tr = $('<tr class="look">'+
											'<td>'+(this.costTable.costDate==null?"":this.costTable.costDate)+'</td>'+
											'<td>'+this.contentName+'</td>'+
											'<td>'+this.supplierName+'</td>'+
											'<td>'+this.costTable.cost+'</td>'+
											'<td>'+this.costTable.count+'</td>'+
											'<td>'+this.costTable.days+'</td>'+
											'<td>'+this.costTable.reimbursement+'</td>'+
											'<td>'+this.costTable.remark+'</td>'+
											'<td></td>'+
									'</tr>');
			        		if(this.costTable.supplierScopeId==1){
			        			flight.append(tr);
			        		}else if(this.costTable.supplierScopeId==2){
			        			hotel.append(tr);
			        		}else if(this.costTable.supplierScopeId==3){
			        			meal.append(tr);   			
			        		}else if(this.costTable.supplierScopeId==4){
			        			ticket.append(tr);        			
			        		}else if(this.costTable.supplierScopeId==5){
			        			shuttle.append(tr);
			        		}else if(this.costTable.supplierScopeId==6){
			        			tickets.append(tr);
			        		}else if(this.costTable.supplierScopeId==7){
			        			comprehensive.append(tr);
			        		}else if(this.costTable.supplierScopeId==8){
			        			other.append(tr);
			        		}
			        	});
			        	var incomes = $("#incomes4").find("tbody");
			        	incomes.html("");
			        	$.each(data.incomes,function(){
			        		var tr = $('<tr class="look">'+
			        						'<td>'+(this.incomeTable.incomeDate==null?"":this.incomeTable.incomeDate)+'</td>'+
			        						'<td>'+this.customerAgencyName+'<input type="hidden" value="'+this.incomeTable.customerAgencyId+'" />'+'</td>'+
			        						'<td>'+this.incomeTable.income+'</td>'+
			        						'<td></td>'+
			        						'<td>'+this.incomeTable.remark+'</td>'+
			        						'<td></td>'+
			        					'</tr>');
			        		incomes.append(tr);
			        	});
			        }  
				});
			}
	 	});
		
		$("#saveChange").click(function(){
			var costTables = new Array();
			var tourId = $(this).parent().attr("id");
			var costTrs = $("#costs4").find("tbody").find("tr").not(".look");
			for (var int = 0; int < costTrs.length; int++) {
				var costInputs = costTrs.eq(int).find("input");
				var costSelects = costTrs.eq(int).find("select");
				costTables.push({
					tourId:tourId,
					costDate:new Date(costInputs.eq(0).val()),
					contentId:costSelects.eq(0).val(),
					supplierId:costSelects.eq(1).val(),
					cost:costInputs.eq(3).val(),
					count:costInputs.eq(4).val(),
					days:costInputs.eq(5).val(),
					reimbursement:costInputs.eq(6).val(),
					supplierScopeId:costInputs.last().val()
				});
			}
			var incomeTables = new Array();
			var incomeTrs = $("#incomes4").find("tbody").find("tr").not(".look");
			for (var int = 0; int < incomeTrs.length; int++) {
				var incomeInputs = incomeTrs.eq(int).find("input");
				var incomeSelects = incomeTrs.eq(int).find("select");
				incomeTables.push({
					tourId:tourId,
					incomeDate:new Date(incomeInputs.eq(0).val()),
					customerAgencyId:incomeInputs.eq(1).val(),
					income:incomeInputs.eq(2).val()
				});
			}
			var settlementChangeViewModel = {costTables:costTables,incomeTables:incomeTables};
			var myData = JSON.stringify(settlementChangeViewModel);
			$.ajax({
		        type: "POST",  
		        contentType:"application/json;charset=utf-8",  
		        url:"${path }settlementManage/settlementChangeSave",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	if(data==-1){
		        		$("#saveChange").attr("data-dismiss","");
		        		alert("保存失败，请填写必填项");
		        	}else{
		        		$("#saveChange").attr("data-dismiss","modal");
		        	}
		        }  
			 });
		});
		
		/* 成本增加 */
		$(".addCost").click(function(){
			var tbody = $(this).parents("table").children("tbody");
			var tr = $('<tr><td><input id="costTime" class="form-control datepicker" type="text"></td>'+
            		'<td><select style="display: none;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>'+
            		'<td><select style="display: none;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>'+
            		'<td><input class="form-control costPlus" type="text"></td>'+
            		'<td><input class="form-control costPlus" type="text"></td>'+
            		'<td><input class="form-control costPlus" type="text"></td>'+
            		'<td><input class="form-control" type="text"></td>'+
            		'<td>财务结算调整</td>'+
					'<td style="vertical-align: middle;"><a class="red delLine" href="#"><i class="icon-trash bigger-130"></i></a></td></tr>');
			
			tr.find("#costTime").attr("id","").datepicker({
				showOtherMonths: true,
				selectOtherMonths: false,
			});
			var selects = tr.find("select");
			var type = $(this).parents("div").attr("id");
			if(type.substr(0,type.length-1)=="flight"){
				tr.children("td").last().append('<input type="hidden" value="1" />');
				$.each(selectInfo.flightContents,function(){
	        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
	        	});
	        	$.each(selectInfo.flightSuppliers,function(){
	        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
	        	});
	        	tr.find("input").eq(2).val(tr.parents(".tab-content").find("#guideTd").parent().prev().children("td:last").text());
	        	tr.find("input").eq(3).val(1);
			}else if(type.substr(0,type.length-1)=="hotel"){
				tr.children("td").last().append('<input type="hidden" value="2" />');
				$.each(selectInfo.hotelContents,function(){
	        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
	        	});
	        	$.each(selectInfo.hotelSuppliers,function(){
	        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
	        	});
	        	tr.find("input").eq(2).val((tr.parents(".tab-content").find("#guideTd").parent().prev().children("td:last").text()/2).toFixed(0));
	        	tr.find("input").eq(3).val(1);
			}else if(type.substr(0,type.length-1)=="meal"){
				tr.children("td").last().append('<input type="hidden" value="3" />');
				$.each(selectInfo.mealContents,function(){
	        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
	        	});
	        	$.each(selectInfo.mealSuppliers,function(){
	        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
	        	});
	        	tr.find("input").eq(2).val(tr.parents(".tab-content").find("#guideTd").parent().prev().children("td:last").text());
			}else if(type.substr(0,type.length-1)=="ticket"){
				tr.children("td").last().append('<input type="hidden" value="4" />');
				$.each(selectInfo.ticketContents,function(){
	        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
	        	});
	        	$.each(selectInfo.ticketSuppliers,function(){
	        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
	        	});
	        	tr.find("input").eq(2).val(tr.parents(".tab-content").find("#guideTd").parent().prev().children("td:last").text());
	        	tr.find("input").eq(3).val(1);
			}else if(type.substr(0,type.length-1)=="shuttle"){
				tr.children("td").last().append('<input type="hidden" value="5" />');
				$.each(selectInfo.shuttleContents,function(){
	        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
	        	});
	        	$.each(selectInfo.shuttleSuppliers,function(){
	        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
	        	});
	        	tr.find("input").eq(2).val(1);
	        	tr.find("input").eq(3).val(1);
			}else if(type.substr(0,type.length-1)=="tickets"){
				tr.children("td").last().append('<input type="hidden" value="6" />');
				$.each(selectInfo.ticketsContents,function(){
	        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
	        	});
	        	$.each(selectInfo.ticketsSuppliers,function(){
	        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
	        	});
	        	tr.find("input").eq(2).val(tr.parents(".tab-content").find("#guideTd").parent().prev().children("td:last").text());
	        	tr.find("input").eq(3).val(1);
			}else if(type.substr(0,type.length-1)=="comprehensive"){
				tr.children("td").last().append('<input type="hidden" value="7" />');
				$.each(selectInfo.comprehensiveContents,function(){
	        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
	        	});
	        	$.each(selectInfo.comprehensiveSuppliers,function(){
	        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
	        	});
	        	tr.find("input").eq(2).val(1);
	        	tr.find("input").eq(3).val(1);
			}else if(type.substr(0,type.length-1)=="other"){
				tr.children("td").last().append('<input type="hidden" value="8" />');
				$.each(selectInfo.otherContents,function(){
	        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
	        	});
	        	$.each(selectInfo.otherSuppliers,function(){
	        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
	        	});
			}
			selects.chosen({no_results_text: "查无结果", search_contains: true});
			selects.next().attr("style","width:100%;");
			tbody.append(tr);
		});
	
		/* 收入增加 */
		$(".addIncome").click(function(){
			var tbody = $(this).parents("table").children("tbody");
			var tr = $('<tr><td><input id="incomeTime" class="form-control datepicker" type="text"></td>'+
        		'<td style="vertical-align: middle;"></td>'+
        		'<td><input class="form-control incomePlus" type="text"></td>'+
        		'<td style="vertical-align: middle;"></td>'+
        		'<td style="vertical-align: middle;">财务结算调整</td>'+
				'<td style="vertical-align: middle;"><a class="red delLine" href="#"><i class="icon-trash bigger-130"></i></a></td></tr>')
			tr.find("#incomeTime").attr("id","").datepicker({
				showOtherMonths: true,
				selectOtherMonths: false,
			});
			tbody.append(tr);
			if(tr.prev().html()==undefined){
				if($(this).parents(".tab-pane").siblings().first().find("#customer").html()==undefined){
					$.ajax({
				        type: "GET",  
				        contentType:"application/json;charset=utf-8",  
				        url:"${path }settlementManage/findCustomer",  
				        data:{tourId:$("#table").find("input:checked").parent().parent().parent().attr("id")},  
				        dataType: "json",  
				        async: false,  
				        success:function(data){
				        	tr.children("td").eq(1).html(data.customerAgencyName+'<input type="hidden" value="'+data.id+'" />');
				        }
					});
				}else{
					var select = $(this).parents(".tab-pane").siblings().first().find("#customer");
					tr.children("td").eq(1).html(select.find('option:selected').text()+'<input type="hidden" value="'+select.val()+'" />');
				}
			}else{
				tr.children("td").eq(1).html(tr.prev().children("td").eq(1).html());
			}
		});
		/* 删除一行 */
		$("#changeModel").delegate(".delLine","click",function(){
			$(this).parents("tr").remove();
		});
		
		/* 成本小计 */
		$("#costs4").delegate(".costPlus","blur",function(){
			var product = 1;
			var costPlus = $(this).parents("tr").find(".costPlus");
			costPlus.each(function(){
				if(isNaN(parseFloat($(this).val()))){
					product = product * 0;
				}else{
					product = product * parseFloat($(this).val());
				}
			})
			costPlus.last().parent().next().children("input").val(product.toFixed(2));
		});
	});
	
</script>
