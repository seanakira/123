<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ page import="com.cts.localtour.entity.UserTable" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<% 
	String path = request.getContextPath()+"/";
	UserTable user = (UserTable)SecurityUtils.getSubject().getPrincipal();
%>
<jsp:include page="../../../resources/include/header.jsp"></jsp:include>
<style type="text/css">
	#ui-datepicker-div a{
		text-align: center;
	}
	#ui-datepicker-div span{
		text-align: center;
	}
	.accessBar{
		display: inline-block;
	}
	.accessBar a{
		padding-left: 10px;
	}
	.brown{
		color:brown;
	}
	.searchExtra{
		position: absolute;
		right: 5px;
		background-color: #f9f9f9;
		z-index: 1500;
		padding-left: 10px;
		border: 1px solid #aaa;
		display: none;
		width: 450px;
	}
	
	@media only screen and (max-width:1280px) {
		.gainsboro{
			display: none;
			font-size: 12px;
		}
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
								<i class="icon-flag"></i>
								<a role="button" data-toggle="modal" href="#">团队管理</a>
							</li>
						</ul><!-- .breadcrumb -->
						<div class="accessBar">
							<shiro:hasPermission name="localTour:save">
								<a class="blue" id="createTour" data-toggle="modal" href="#create" title="新增团队">
									<i class="icon-plus bigger-100"></i>
									新增团队
								</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="localTour:update">
								<a class="blue" id="editTour" data-toggle="modal" href="#" title="修改团队">
									<i class="icon-pencil bigger-100"></i>
									修改团队
								</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="localTour:auditing">
								<a class="blue" id="auditing" data-toggle="modal" href="#" title="提交审核">
									<i class="icon-hand-right bigger-100"></i>
									提交审核
								</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="localTour:unAuditing">
								<a class="blue" id="unAuditing" data-toggle="modal" href="#" title="退回编辑">
									<i class="icon-hand-left bigger-100"></i>
									退回编辑
								</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="localTour:finance">
								<a class="blue" id="finance" data-toggle="modal" href="#" title="报送财务">
									<i class="icon-share-alt bigger-100"></i>
									报送财务
								</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="localTour:findLoan">
								<a class="blue" id="lend" data-toggle="modal" href="#" title="导游借款">
									<i class="icon-user bigger-100"></i>
									导游借款
								</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="localTour:findPay">
								<a class="blue" id="pay" data-toggle="modal" href="#" title="付款申请">
									<i class="icon-file-alt bigger-100"></i>
									付款申请
								</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="localTour:findChange">
								<a class="blue" id="chanageCost" data-toggle="modal" href="#" title="成本收入变更">
									<i class="icon-tasks bigger-100"></i>
									成本收入变更
								</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="localTour:findBorrowInvoice">
								<a class="blue" id="loanInvoice" data-toggle="modal" href="#" title="预借发票">
									<i class="icon-building bigger-100"></i>
									预借发票
								</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="localTour:findRefund">
								<a class="blue" id="refund" data-toggle="modal" href="#" title="退款申请">
									<i class="icon-tablet bigger-100"></i>
									退款申请
								</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="localTour:balance">
								<a class="blue" id="balance" data-toggle="modal" href="#" title="申请结算">
									<i class="icon-table bigger-100"></i>
									申请结算
								</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="localTour:balance">
								<a class="blue" id="unBalance" data-toggle="modal" href="#" title="取消结算">
									<i class="icon-table bigger-100"></i>
									取消结算
								</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="localTour:findReimbursement">
								<a class="blue" id="reimbursement" data-toggle="modal" href="#" title="报账填写">
									<i class="icon-columns bigger-100"></i>
									报账填写
								</a>
							</shiro:hasPermission>
								<a class="blue" id="auditingReimbursement" data-toggle="modal" href="#" title="提交报账">
									<i class="icon-suitcase bigger-100"></i>
									提交报账
								</a>
							<shiro:hasPermission name="localTour:del">
								<a class="blue" id="delete" data-toggle="modal" href="#" title="取消团队">
									<i class="icon-trash bigger-100"></i>
									取消团队
								</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="localTour:recover">
								<a class="blue" id="recover" data-toggle="modal" href="#" title="恢复团队">
									<i class="icon-undo bigger-100"></i>
									恢复团队
								</a>
							</shiro:hasPermission>
							<div id="print" class="btn-group" style="top: -2px;font-size: 12.5px;">
								<button data-toggle="dropdown" class="blue" style="border-width: 0px;background-color: rgba(255,255,255,0);">
										<i class="icon-print bigger-100"></i>打印单据
									<span class="icon-caret-down icon-on-right"></span>
								</button>

								<ul class="dropdown-menu dropdown-inverse">
									<li>
										<a id="lendPrintButton" data-toggle="modal" href="#">打印导游借款凭证</a>
									</li>

									<li>
										<a id="payPrintButton" data-toggle="modal" href="#">打印供应商付款凭证</a>
									</li>
									
									<li class="divider"></li>
									
									<li>
										<a id="incomePrintButton" data-toggle="modal" href="#">打印缴款单</a>
									</li>
									
									<li>
										<a id="invoicePrintButton" data-toggle="modal" href="#">打印预借发票单</a>
									</li>
									
									<li>
										<a id="refundPrintButton" data-toggle="modal" href="#">打印退款单</a>
									</li>
									
									<li class="divider"></li>
									
									<li>
										<a id="reimbursementPrintButton" data-toggle="modal" href="#" target="_blank">打印报账单</a>
									</li>
								</ul>
							</div>
						</div>
						<div class="nav-search" id="nav-search">
							<form class="form-search" action="${path }localTourManage" method="get">
								<span class="input-icon">
									<input name="key" placeholder="搜索 ..." class="nav-search-input" autocomplete="off" type="text" value="${key }" />
									<i class="icon-search nav-search-icon"></i>
								</span>
								<div class="searchExtra">
									开始日期：
									<div style="display: inline-block;margin-right: 10px;margin-top: 10px;"><input id="start" name="start" class="datepicker" type="text" style="width: 100px;"></div>
									部门：
									<div style="display: inline-block;margin-right: 10px;margin-top: 15px;"><select id="select" name="deptIds" style="display: none;" multiple="multiple" class="chosen-select" data-placeholder="可选多个...">
										<option value="">&nbsp;</option>
										<c:forEach var="dept" items="${depts }">
											<option value="${dept.id }">${dept.deptName }</option>
										</c:forEach>
									</select></div>
									结束日期：
									<div style="display: inline-block;margin-right: 10px;margin-top: 10px;"><input id="end" name="end" class="datepicker" type="text" style="width: 100px;"></div>
									人员：
									<div style="display: inline-block;margin-right: 10px;margin-top: 15px;"><select id="select" name="userIds" style="display: none;" multiple="multiple" class="chosen-select" data-placeholder="可选多个...">
										<option value="">&nbsp;</option>
										<c:forEach var="user" items="${users }">
											<option value="${user.id }">${user.realName }</option>
										</c:forEach>
									</select></div>
									团队状态：
									<div style="display: inline-block;margin-right: 10px;margin-top: 15px;"><select name="status"><option value="-1">&nbsp;</option><option value="7">已报账</option><option value="6">未报账</option></select></div>
									<div style="display: inline-block;margin-right: 10px;margin-top: 15px; width: 240px">
										<i class="icon-remove bigger-150 grey" style="width: 49%;padding: 10px;display: inline-block;"></i>
										<i class="icon-ok bigger-150 grey" style="width: 49%;;padding: 10px;display: inline-block;"></i>
									</div>
								</div>
							</form>
						</div><!-- #nav-search -->
					</div>
					
										<div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid"><table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
						<thead>
							<tr role="row">
								<th aria-label="" style="width: 2%;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">
									<label>
										<input class="ace selectAll" type="checkbox">
										<span class="lbl"></span>
									</label>
								</th>
								<th aria-label="Price: activate to sort column ascending" style="width: 10%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
									团号
								</th>
								
								<c:choose>
									<c:when test="${sessionScope.isMice }">
										<th aria-label="Price: activate to sort column ascending" style="width: 15%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
											活动名称
										</th>
									</c:when>
									<c:otherwise>
										<th aria-label="Price: activate to sort column ascending" style="width: 15%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
											线路
										</th>
									</c:otherwise>
								</c:choose>
								<th aria-label="Price: activate to sort column ascending" style="width: 5%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
									成人
								</th>
								<th aria-label="Price: activate to sort column ascending" style="width: 5%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
									儿童
								</th>
								<th aria-label="Price: activate to sort column ascending" style="width: 5%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
									天数
								</th>
								<th aria-label="Price: activate to sort column ascending" style="width: 10%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
									开始日期
								</th>
								<th aria-label="Price: activate to sort column ascending" style="width: 10%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
									结束日期
								</th>
								<th aria-label="Price: activate to sort column ascending" style="width: 5%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
									状态
								</th>
								<th aria-label="Price: activate to sort column ascending" style="width: 5%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
									团控人
								</th>
							</tr>
						</thead>

						<tbody id="table" aria-relevant="all" aria-live="polite" role="alert">
<!-- 列表循环 -->				
							<c:forEach var="localTour" items="${localTours }" varStatus="status">
								<tr id="${localTour.localTourTable.id }">
									<td class="center sorting_1">
											<label>
												<input class="ace" type="checkbox">
												<span class="lbl"></span>
											</label>
										</td>
									<td><a id="findTour" role="button" data-toggle="modal" href="#find">${localTour.localTourTable.tourNo }</a></td>
									<td>${localTour.localTourTable.tourName }</td>
									<td>${localTour.localTourTable.adultNo }</td>
									<td>${localTour.localTourTable.childrenNo }</td>
									<td>${localTour.days }</td>
									<td>${localTour.localTourTable.startTime }</td>
									<td>${localTour.localTourTable.endTime }</td>
									<td><c:choose><c:when test="${localTour.localTourTable.enable }">${localTour.status }</c:when><c:otherwise>已取消</c:otherwise></c:choose></td>
									<td>${localTour.realName }</td>
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
											<a href="${path }localTourManage?page=${pageNo-1 }&key=${key }&start=${start }&end=${end }&deptIds=${deptIds }&userIds=${userIds }&status=${status }"><i class="icon-double-angle-left"></i></a>
										</li>
										<c:choose>
											<c:when test="${pageNo>6 }">
												<li <c:if test="${pageNo==page }">class="active"</c:if>>
														<a href="${path }localTourManage?page=${1 }&key=${key }&start=${start }&end=${end }&deptIds=${deptIds }&userIds=${userIds }&status=${status }">1</a>
												</li>
												<li>
													<a>...</a>
												</li>
												<c:forEach var="page" begin="${pageNo-5 }" end="${pageNo+4>pageMax?pageMax:pageNo+4 }">
													<li <c:if test="${pageNo==page }">class="active"</c:if>>
														<a href="${path }localTourManage?page=${page }&key=${key }&start=${start }&end=${end }&deptIds=${deptIds }&userIds=${userIds }&status=${status }">${page }</a>
													</li>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<c:forEach var="page" begin="1" end="${pageMax>10?10:pageMax }">
													<li <c:if test="${pageNo==page }">class="active"</c:if>>
														<a href="${path }localTourManage?page=${page }&key=${key }&start=${start }&end=${end }&deptIds=${deptIds }&userIds=${userIds }&status=${status }">${page }</a>
													</li>
												</c:forEach>
											</c:otherwise>
										</c:choose>
										<li <c:choose><c:when test="${pageNo==pageMax }">class="next disabled"</c:when><c:otherwise>class="next"</c:otherwise></c:choose>>
											<a href="${path }localTourManage?page=${pageNo+1 }&key=${key }&start=${start }&end=${end }&deptIds=${deptIds }&userIds=${userIds }&status=${status }"><i class="icon-double-angle-right"></i></a>
										</li>
									</ul>
								</div>
							</div>
						</div>
<!-- 分页查询结束 -->							
					</div>
				</div>
<!-- 正文结束 -->	
<!-- 新增模板 -->
				<div aria-hidden="true" style="display: none;" id="create" class="modal fade" tabindex="-1">
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
											<a data-toggle="tab" href="#tourInfo">
												<i class="blue icon-laptop bigger-120"></i>
												基本信息
											</a>
										</li>
					
					
										<li <c:if test="${sessionScope.isMice }">style="display: none;"</c:if>>
											<a class="trips" data-toggle="tab" href="#trips">
												<i class="orange icon-calendar bigger-120"></i>
												行程
											</a>
										</li>
										
										
										<li>
											<a data-toggle="tab" href="#costs">
												<i class="green icon-credit-card bigger-120"></i>
												成本
											</a>
										</li>
										<li>
											<a data-toggle="tab" href="#incomes">
												<i class="pink icon-briefcase bigger-120"></i>
												收入
											</a>
										</li>
									</ul>
					         	</div>
					         	
					         	<div class="tab-content no-border padding-6" style="z-index: 1400;">
					         		<div id="tourInfo" class="tab-pane fade in active">
					         			<table class="table table-striped table-bordered table-hover no-margin-bottom no-border-top">
											<thead>
											</thead>
		
											<tbody>
												<tr>
													<td>团号*</td>
													<td><input class="form-control" type="text"></td>
													<td>团名*</td>
													<td><input class="form-control" type="text"></td>
													<td>业务类型*</td>
													<td style="width: 23%;">
														<select style="display: none;" class="width-20 chosen-select form-control businessType" data-placeholder="Choose a Country...">
															<option value="">&nbsp;</option>
														</select>
													</td>
												</tr>
												<tr>
													<td>团队类型*</td>
													<td>
														<select style="display: none;" class="width-20 chosen-select form-control tourType" data-placeholder="Choose a Country...">
															<option value="">&nbsp;</option>
														</select>
													</td>
													<td>国家/地区*</td>
													<td>
														<select style="display: none;" class="width-20 chosen-select region" data-placeholder="Choose a Country...">
															<option value="">&nbsp;</option>
														</select>
													</td>
													<td>游客类型*</td>
													<td>
														<select style="display: none;" class="width-20 chosen-select visitorType" data-placeholder="Choose a Country...">
															<option value="">&nbsp;</option>
														</select>
													</td>
												</tr>
												<tr>
													<td>组团社*</td>
													<td>
														<select  id="customer" style="display: none;" class="width-20 chosen-select customerAgency" data-placeholder="Choose a Country...">
															<option value="">&nbsp;</option>
														</select>
													</td>
													<td>组团人*</td>
													<td><input class="form-control" type="text"></td>
													<td>全陪人数</td>
													<td><input class="counts form-control" type="text"></td>
												</tr>
												<tr>
													<td>成人数*</td>
													<td><input class="counts form-control" type="text"></td>
													<td>儿童数</td>
													<td><input class="counts form-control" type="text"></td>
													<td>人数合计</td>
													<td style="vertical-align: middle;"></td>
												</tr>
												<tr>
													<td>开始日期*</td>
													<td><input id="datepickerStart" class="form-control datepicker" type="text"></td>
													<td>结束日期*</td>
													<td><input id="datepickerEnd" class="form-control datepicker" type="text"></td>
													<td>导游</td>
													<td id="guideTd">
														<input id="guide" class="form-control" type="text" placeholder="可选多个">
														<!-- <select style="display: none;" multiple="multiple" class="width-20 chosen-select" id="form-field-select-4" data-placeholder="可选多个...">
															<option value="">&nbsp;</option>
														</select> -->
													</td>
												</tr>
												<tr>
													<td>备注</td>
													<td><input class="form-control" type="text"></td>
												</tr>
											</tbody>
										</table>
										<div class="modal-header no-padding" <c:if test="${sessionScope.isMice }">style="display: none;"</c:if>>
											<div class="table-header">
												抵离信息
												<a class="white addArrDep" href="#"><i class="icon-plus bigger-100"></i></a>
											</div>
										</div>
										<table id="arrDepTable" class="table table-striped table-bordered table-hover no-margin-bottom no-border-top" <c:if test="${sessionScope.isMice }">style="display: none;"</c:if>>
											<thead>
												<tr>
													<th>出发地</th>
													<th>抵达交通</th>
													<th>抵达时间</th>
													<th>抵达班次</th>
													<th>抵达地</th>
													<th aria-label="" style="width: 45px;" colspan="1" rowspan="1" role="columnheader" class="sorting_disabled">
														操作
													</th>
												</tr>
											</thead>
		
											<tbody class="arrInfo">
												<tr id="arrModel" style="display: none;" >
													<td>
														<select style="display: none;" class="width-20 chosen-select region" data-placeholder="Choose a Country...">
															<option value="">&nbsp;</option>
														</select>
													</td>
													<td>
														<select class="traffic">
															<option>&nbsp;</option>
															<option>飞机</option>
															<option>火车</option>
															<option>轮船</option>
															<option>大巴</option>
														</select>
													</td>
													<td><input id="arrTime" class="form-control datepicker" type="text"></td>
													<td><input class="form-control" type="text"></td>
													<td>
														<select style="display: none;" class="width-20 chosen-select region" data-placeholder="Choose a Country...">
															<option value="">&nbsp;</option>
														</select>
													</td>
													<td>
														<a class="red delLine" href="#">
															<i class="icon-trash bigger-130"></i>
														</a>
													</td>
												</tr>
											</tbody>
											<thead>
												<tr>
													<th style="width: 19%;">前往地</th>
													<th style="width: 19%;">离开交通</th>
													<th style="width: 19%;">离开时间</th>
													<th style="width: 19%;">离开班次</th>
													<th style="width: 19%;">离开地</th>
													<th aria-label="" style="width: 5%;" colspan="1" rowspan="1" role="columnheader" class="sorting_disabled">
														操作
													</th>
												</tr>
											</thead>
											<tbody class="departInfo">
												<tr id="departModel"  style="display: none;">
													<td>
														<select style="display: none;" class="width-20 chosen-select region" data-placeholder="Choose a Country...">
															<option value="">&nbsp;</option>
														</select>
													</td>
													<td>
														<select class="traffic">
															<option>&nbsp;</option>
															<option>飞机</option>
															<option>火车</option>
															<option>轮船</option>
															<option>大巴</option>
														</select>
													</td>
													<td><input id="departTime" class="form-control datepicker" type="text"></td>
													<td><input class="form-control" type="text"></td>
													<td>
														<select style="display: none;" class="width-20 chosen-select region" data-placeholder="Choose a Country...">
															<option value="">&nbsp;</option>
														</select>
													</td>
													<td>
														<a class="red delLine" href="#">
															<i class="icon-trash bigger-130"></i>
														</a>
													</td>
												</tr>
											</tbody>
										</table>
					         		</div><!-- 修改tab结束 -->
					         		<div id="costs" class="tab-pane fade costTable">
					         			<div class="tabbable tabs-left">
					         			<ul class="nav nav-tabs" style="width: 10%;text-align: center;">
											<li class="active">
												<a data-toggle="tab" href="#flight0">
													<i class="red icon-large icon-plane"></i>
													机票
												</a>
											</li>
											<li>
												<a data-toggle="tab" href="#hotel0">
													<i class="pink icon-large icon-building"></i>
													订房
												</a>
											</li>
											<li>
												<a data-toggle="tab" href="#meal0">
													<i class="orange icon-large icon-food"></i>
													订餐
												</a>
											</li>
											<li>
												<a data-toggle="tab" href="#ticket0">
													<i class="green icon-large icon-hdd"></i>
													门票
												</a>
											</li>
											<li>
												<a data-toggle="tab" href="#shuttle0">
													<i class="dark icon-large icon-truck"></i>
													订车
												</a>
											</li>
											<li>
												<a data-toggle="tab" href="#tickets0">
													<i class="blue icon-large icon-list-alt"></i>
													票务
												</a>
											</li>
											<li>
												<a data-toggle="tab" href="#comprehensive0">
													<i class="purple icon-large icon-money"></i>
													综费
												</a>
											</li>
											<li>
												<a data-toggle="tab" href="#other0">
													<i class="black icon-large icon-leaf"></i>
													其他
												</a>
											</li>
										</ul>
										<div class="tab-content no-padding" style="z-index: 1400;display: inline-block;float: right;right: -4px;width: 90%;overflow: visible;">
											<div id="flight0" class="tab-pane in active">
												<table class="table table-striped table-bordered table-hover no-margin">
													<thead>
														<tr>
															<th style="width: 10%;">日期</th>
															<th style="width: 15%;">内容</th>
															<th style="width: 15%;">供应商*</th>
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
															<th style="width: 10%;">明细备注</th>
															<th style="width: 5%;">
																<a class="blue addCost" href="#">
																	<i class="icon-plus bigger-130"></i>
																</a>
															</th>
														</tr>
													</thead>
													<tbody>
														<tr id="costModel" style="display:none;">
										            		<td>
										            			<input id="costTime" class="form-control datepicker" type="text">
										            		</td>
										            		<td>
										            			<select style="display: none;" class="width-20 chosen-select" data-placeholder="Choose a Country...">
																	<option value="">&nbsp;</option>
																</select>
															</td>
										            		<td>
										            			<select style="display: none;" class="width-20 chosen-select" data-placeholder="Choose a Country...">
																	<option value="">&nbsp;</option>
																</select>
										            		</td>
										            		<td><input class="form-control costPlus" type="text"></td>
										            		<td><input class="form-control costPlus" type="text"></td>
										            		<td><input class="form-control costPlus" type="text"></td>
										            		<td style="vertical-align: middle;"></td>
										            		<td>
										            			<select style="display: none;" class="width-20 chosen-select" data-placeholder="Choose a Country...">
																	<option value="">&nbsp;</option>
																</select>
															</td>
															<td><input class="form-control" placeholder="" type="text"></td>
															<td style="vertical-align: middle;">
																<a class="red delLine" href="#">
																	<i class="icon-trash bigger-130"></i>
																</a>
															</td>
										            	</tr>
													</tbody>
									            </table>
											</div>
											
											<div id="hotel0" class="tab-pane">
												<table class="table table-striped table-bordered table-hover no-margin">
													<thead>
														<tr>
															<th style="width: 10%;">日期</th>
															<th style="width: 15%;">内容</th>
															<th style="width: 15%;">供应商*</th>
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
															<th style="width: 10%;">明细备注</th>
															<th style="width: 5%;">
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
											
											<div id="meal0" class="tab-pane">
												<table class="table table-striped table-bordered table-hover no-margin">
													<thead>
														<tr>
															<th style="width: 10%;">日期</th>
															<th style="width: 15%;">内容</th>
															<th style="width: 15%;">供应商*</th>
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
															<th style="width: 10%;">明细备注</th>
															<th style="width: 5%;">
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
											
											<div id="ticket0" class="tab-pane">
												<table class="table table-striped table-bordered table-hover no-margin">
													<thead>
														<tr>
															<th style="width: 10%;">日期</th>
															<th style="width: 15%;">内容</th>
															<th style="width: 15%;">供应商*</th>
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
															<th style="width: 10%;">明细备注</th>
															<th style="width: 5%;">
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
											
											<div id="shuttle0" class="tab-pane">
												<table class="table table-striped table-bordered table-hover no-margin">
													<thead>
														<tr>
															<th style="width: 10%;">日期</th>
															<th style="width: 15%;">内容</th>
															<th style="width: 15%;">供应商*</th>
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
															<th style="width: 10%;">明细备注</th>
															<th style="width: 5%;">
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
											
											<div id="tickets0" class="tab-pane">
												<table class="table table-striped table-bordered table-hover no-margin">
													<thead>
														<tr>
															<th style="width: 10%;">日期</th>
															<th style="width: 15%;">内容</th>
															<th style="width: 15%;">供应商*</th>
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
															<th style="width: 10%;">明细备注</th>
															<th style="width: 5%;">
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
											
											<div id="comprehensive0" class="tab-pane">
												<table class="table table-striped table-bordered table-hover no-margin">
													<thead>
														<tr>
															<th style="width: 10%;">日期</th>
															<th style="width: 15%;">内容</th>
															<th style="width: 15%;">供应商*</th>
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
															<th style="width: 10%;">明细备注</th>
															<th style="width: 5%;">
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
											
											<div id="other0" class="tab-pane">
												<table class="table table-striped table-bordered table-hover no-margin">
													<thead>
														<tr>
															<th style="width: 10%;">日期</th>
															<th style="width: 15%;">内容</th>
															<th style="width: 15%;">供应商*</th>
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
															<th style="width: 10%;">明细备注</th>
															<th style="width: 5%;">
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
					         		
					         		<div id="trips" class="tab-pane fade">
					         			<div class="tabbable tabs-left">
						         			<ul class="nav nav-tabs" id="myTab3">
												
											</ul>
											<div class="tab-content no-padding">
												<div id="tripModel" class="tab-pane in active" style="display:none;">
													<table class="table table-striped table-bordered table-hover no-margin-bottom no-border-top">
														<tr>
															<td>行程：</td>
															<td><textarea class="form-control" rows="8"></textarea></td>
														</tr>
														<tr><td style="width: 60px;">餐食：</td><td><input type="text" class="col-xs-30"></td></tr>
														<tr><td>住宿：</td><td><input type="text" class="col-xs-30"></td></tr>
														<tr><td>交通：</td><td><input type="text" class="col-xs-30"></td></tr>
														<tr><td>备注：</td><td><textarea class="form-control" rows="1"></textarea></td></tr>
													</table>
												</div>
						         			</div><!-- tab content 结束 -->
					         			</div><!-- 左tab结束 -->
					         		</div><!-- 行程tab结束 -->
					         		
					         		<div id="incomes" class="tab-pane fade">
					         			<div class="tabbable tabs-left">
											<div class="tab-content no-padding" style="z-index: 1400;overflow: visible;">
												<table class="table table-striped table-bordered table-hover incomeTable">
														<thead>
															<tr>
																<th style="width: 15%">日期</th>
																<th>客户*</th>
																<th style="width: 10%">收入</th>
																<th style="width: 10%">实收</th>
																<th style="width: 10%">已开发票</th>
																<th style="width: 20%">备注</th>
																<th style="width: 5%">
																	<a class="blue addIncome" href="#">
																		<i class="icon-plus bigger-130"></i>
																	</a>
																</th>
															</tr>
														</thead>
														<tbody>
										            		<tr id="incomeModel" style="display: none;">
											            		<td>
											            			<input id="incomeTime" class="form-control datepicker" type="text">
											            		</td>
											            		<td>
											            			<!-- <select style="display: none;" class="width-20 chosen-select customerAgency" data-placeholder="Choose a Country...">
																		<option value="">&nbsp;</option>
																	</select> -->
																</td>
											            		<td><input class="form-control incomePlus" type="text"></td>
											            		<td style="vertical-align: middle;"></td>
											            		<td style="vertical-align: middle;"></td>
											            		<td><input class="form-control incomePlus" type="text"></td>
																<td style="vertical-align: middle;">
																	<a class="red delLine" href="#">
																		<i class="icon-trash bigger-130"></i>
																	</a>
																</td>
											            	</tr>
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
								<shiro:hasPermission name="localTour:save">
									<button id="saveNew" class="btn btn-sm btn-success pull-right" data-dismiss="modal">
										<i class="icon-save"></i>
										保存
									</button>
								</shiro:hasPermission>
						 	 </div>
						</div><!-- /.modal-content -->
					</div><!-- /.modal -->
				</div>
<!-- 新增结束 -->
<!-- 查看模板 -->
				<div aria-hidden="true" style="display: none;" id="find" class="modal fade" tabindex="-1">
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
											<a data-toggle="tab" href="#tourInfo2">
												<i class="blue icon-laptop bigger-120"></i>
												基本信息
											</a>
										</li>
					
					
										<li <c:if test="${sessionScope.isMice }">style="display: none;"</c:if>>
											<a data-toggle="tab" href="#trips2">
												<i class="orange icon-calendar bigger-120"></i>
												行程
											</a>
										</li>
										
										
										<li>
											<a data-toggle="tab" href="#costs2">
												<i class="green icon-credit-card bigger-120"></i>
												成本
											</a>
										</li>
										<li>
											<a data-toggle="tab" href="#incomes2">
												<i class="pink icon-briefcase bigger-120"></i>
												收入
											</a>
										</li>
									</ul>
					         	</div>
					         	
					         	<div class="tab-content no-border padding-6" style="z-index: 1400;">
					         		<div id="tourInfo2" class="tab-pane fade in active">
					         			<table class="table table-striped table-bordered table-hover no-margin-bottom no-border-top">
											<thead>
											</thead>
		
											<tbody>
												<tr>
													<td>团号*</td>
													<td class="tourInfo"></td>
													<td>团名*</td>
													<td class="tourInfo"></td>
													<td>业务类型*</td>
													<td class="tourInfo"></td>
												</tr>
												<tr>
													<td>团队类型*</td>
													<td class="tourInfo"></td>
													<td>国家/地区*</td>
													<td class="tourInfo"></td>
													<td>游客类型*</td>
													<td class="tourInfo"></td>
												</tr>
												<tr>
													<td>组团社*</td>
													<td class="tourInfo"></td>
													<td>组团人*</td>
													<td class="tourInfo"></td>
													<td>全陪人数</td>
													<td class="tourInfo"></td>
												</tr>
												<tr>
													<td>成人数*</td>
													<td class="tourInfo"></td>
													<td>儿童数</td>
													<td class="tourInfo"></td>
													<td>人数合计</td>
													<td class="tourInfo"></td>
												</tr>
												<tr>
													<td>开始日期*</td>
													<td class="tourInfo"></td>
													<td>结束日期*</td>
													<td class="tourInfo"></td>
													<td>导游</td>
													<td class="tourInfo"></td>
												</tr>
												<tr>
													<td>备注</td>
													<td class="tourInfo"></td>
												</tr>
											</tbody>
										</table>
										<div class="modal-header no-padding" <c:if test="${sessionScope.isMice }">style="display: none;"</c:if>>
											<div class="table-header">
												抵离信息
												<a class="white addArrDep" href="#"><i class="icon-plus bigger-100"></i></a>
											</div>
										</div>
										<table id="arrDepTable" class="table table-striped table-bordered table-hover no-margin-bottom no-border-top" <c:if test="${sessionScope.isMice }">style="display: none;"</c:if>>
											<thead>
												<tr>
													<th>出发地</th>
													<th>抵达交通</th>
													<th>抵达时间</th>
													<th>抵达班次</th>
													<th>抵达地</th>
												</tr>
											</thead>
		
											<tbody class="arrInfo">
											</tbody>
											<thead>
												<tr>
													<th style="width: 19%;">前往地</th>
													<th style="width: 19%;">离开交通</th>
													<th style="width: 19%;">离开时间</th>
													<th style="width: 19%;">离开班次</th>
													<th style="width: 19%;">离开地</th>
												</tr>
											</thead>
											<tbody class="departInfo">
											</tbody>
										</table>
					         		</div><!-- 修改tab结束 -->
					         		<div id="costs2" class="tab-pane fade costTable">
					         			<div class="tabbable tabs-left">
					         			<ul class="nav nav-tabs" style="width: 10%;text-align: center;">
											<li class="active">
												<a data-toggle="tab" href="#flight2">
													<i class="red icon-large icon-plane"></i>
													机票
												</a>
											</li>
											<li>
												<a data-toggle="tab" href="#hotel2">
													<i class="pink icon-large icon-building"></i>
													订房
												</a>
											</li>
											<li>
												<a data-toggle="tab" href="#meal2">
													<i class="orange icon-large icon-food"></i>
													订餐
												</a>
											</li>
											<li>
												<a data-toggle="tab" href="#ticket2">
													<i class="green icon-large icon-hdd"></i>
													门票
												</a>
											</li>
											<li>
												<a data-toggle="tab" href="#shuttle2">
													<i class="dark icon-large icon-truck"></i>
													订车
												</a>
											</li>
											<li>
												<a data-toggle="tab" href="#tickets2">
													<i class="blue icon-large icon-list-alt"></i>
													票务
												</a>
											</li>
											<li>
												<a data-toggle="tab" href="#comprehensive2">
													<i class="purple icon-large icon-money"></i>
													综费
												</a>
											</li>
											<li>
												<a data-toggle="tab" href="#other2">
													<i class="black icon-large icon-leaf"></i>
													其他
												</a>
											</li>
										</ul>
										<div class="tab-content no-padding" style="z-index: 1400;display: inline-block;float: right;right: -4px;width: 90%;overflow: visible;">
											<div id="flight2" class="tab-pane in active">
												<table class="table table-striped table-bordered table-hover no-margin">
													<thead>
														<tr>
															<th style="width: 8%;">日期</th>
															<th style="width: 14%;">内容</th>
															<th style="width: 14%;">供应商*</th>
															<th style="width: 8%;">成本</th>
															<th style="width: 8%;">数量</th>
															<th style="width: 8%;">天数</th>
															<th style="width: 8%;">成本小计</th>
															<th style="width: 8%;">实付</th>
															<th style="width: 8%;">借款人</th>
															<th style="width: 8%;">明细备注</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
									            </table>
											</div>
											
											<div id="hotel2" class="tab-pane">
												<table class="table table-striped table-bordered table-hover no-margin">
													<thead>
														<tr>
															<th style="width: 8%;">日期</th>
															<th style="width: 14%;">内容</th>
															<th style="width: 14%;">供应商*</th>
															<th style="width: 8%;">成本</th>
															<th style="width: 8%;">数量</th>
															<th style="width: 8%;">天数</th>
															<th style="width: 8%;">成本小计</th>
															<th style="width: 8%;">实付</th>
															<th style="width: 8%;">借款人</th>
															<th style="width: 8%;">明细备注</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
									            </table>
											</div>
											
											<div id="meal2" class="tab-pane">
												<table class="table table-striped table-bordered table-hover no-margin">
													<thead>
														<tr>
															<th style="width: 8%;">日期</th>
															<th style="width: 14%;">内容</th>
															<th style="width: 14%;">供应商*</th>
															<th style="width: 8%;">成本</th>
															<th style="width: 8%;">数量</th>
															<th style="width: 8%;">天数</th>
															<th style="width: 8%;">成本小计</th>
															<th style="width: 8%;">实付</th>
															<th style="width: 8%;">借款人</th>
															<th style="width: 8%;">明细备注</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
									            </table>
											</div>
											
											<div id="ticket2" class="tab-pane">
												<table class="table table-striped table-bordered table-hover no-margin">
													<thead>
														<tr>
															<th style="width: 8%;">日期</th>
															<th style="width: 14%;">内容</th>
															<th style="width: 14%;">供应商*</th>
															<th style="width: 8%;">成本</th>
															<th style="width: 8%;">数量</th>
															<th style="width: 8%;">天数</th>
															<th style="width: 8%;">成本小计</th>
															<th style="width: 8%;">实付</th>
															<th style="width: 8%;">借款人</th>
															<th style="width: 8%;">明细备注</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
									            </table>
											</div>
											
											<div id="shuttle2" class="tab-pane">
												<table class="table table-striped table-bordered table-hover no-margin">
													<thead>
														<tr>
															<th style="width: 8%;">日期</th>
															<th style="width: 14%;">内容</th>
															<th style="width: 14%;">供应商*</th>
															<th style="width: 8%;">成本</th>
															<th style="width: 8%;">数量</th>
															<th style="width: 8%;">天数</th>
															<th style="width: 8%;">成本小计</th>
															<th style="width: 8%;">实付</th>
															<th style="width: 8%;">借款人</th>
															<th style="width: 8%;">明细备注</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
									            </table>
											</div>
											
											<div id="tickets2" class="tab-pane">
												<table class="table table-striped table-bordered table-hover no-margin">
													<thead>
														<tr>
															<th style="width: 8%;">日期</th>
															<th style="width: 14%;">内容</th>
															<th style="width: 14%;">供应商*</th>
															<th style="width: 8%;">成本</th>
															<th style="width: 8%;">数量</th>
															<th style="width: 8%;">天数</th>
															<th style="width: 8%;">成本小计</th>
															<th style="width: 8%;">实付</th>
															<th style="width: 8%;">借款人</th>
															<th style="width: 8%;">明细备注</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
									            </table>
											</div>
											
											<div id="comprehensive2" class="tab-pane">
												<table class="table table-striped table-bordered table-hover no-margin">
													<thead>
														<tr>
															<th style="width: 8%;">日期</th>
															<th style="width: 14%;">内容</th>
															<th style="width: 14%;">供应商*</th>
															<th style="width: 8%;">成本</th>
															<th style="width: 8%;">数量</th>
															<th style="width: 8%;">天数</th>
															<th style="width: 8%;">成本小计</th>
															<th style="width: 8%;">实付</th>
															<th style="width: 8%;">借款人</th>
															<th style="width: 8%;">明细备注</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
									            </table>
											</div>
											
											<div id="other2" class="tab-pane">
												<table class="table table-striped table-bordered table-hover no-margin">
													<thead>
														<tr>
															<th style="width: 8%;">日期</th>
															<th style="width: 14%;">内容</th>
															<th style="width: 14%;">供应商*</th>
															<th style="width: 8%;">成本</th>
															<th style="width: 8%;">数量</th>
															<th style="width: 8%;">天数</th>
															<th style="width: 8%;">成本小计</th>
															<th style="width: 8%;">实付</th>
															<th style="width: 8%;">借款人</th>
															<th style="width: 8%;">明细备注</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
									            </table>
											</div>
											<span id="changeCostBlue" class="blue">*蓝色为成本收入变更</span>
											<span id="reimbursementCostRed" class="red" style="">*红色为报账新增成本</span>
										</div><!-- tab content 结束 -->
					         			</div><!-- 左tab 结束 -->
					         		</div><!-- 成本tab结束 -->
					         		
					         		<div id="trips2" class="tab-pane fade">
					         			<div class="tabbable tabs-left">
						         			<ul class="nav nav-tabs" id="myTab3">
											</ul>
											<div class="tab-content no-padding">
												<div id="tripModel" class="tab-pane in active" style="display:none;">
													<table class="table table-striped table-bordered table-hover no-margin-bottom no-border-top">
														<tr><td style="width: 60px;">行程：</td><td class="tourInfo"></td></tr>
														<tr><td>餐食：</td><td class="tourInfo"></td></tr>
														<tr><td>住宿：</td><td class="tourInfo"></td></tr>
														<tr><td>交通：</td><td class="tourInfo"></td></tr>
														<tr><td>备注：</td><td class="tourInfo"></td></tr>
													</table>
												</div>
						         			</div><!-- tab content 结束 -->
					         			</div><!-- 左tab结束 -->
					         		</div><!-- 行程tab结束 -->
					         		
					         		<div id="incomes2" class="tab-pane fade">
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
															</tr>
														</thead>
														<tbody>
											            </tbody>
										            </table>
												         			
						         			</div><!-- tab content 结束 -->
						         			<span id="changeIncomeBlue" class="blue">*蓝色为成本收入变更</span>
						         			<span id="reimbursementIncomeRed" class="red" style="">*红色为报账调整应收</span>
					         			</div><!-- 左tab 结束 -->
					         		</div><!-- 收入tab结束 -->
					         		
					         	</div>
					         	
					            
					         </div>
							<div id="totalAll" class="modal-footer no-margin-top">
								
						 	</div>
						</div><!-- /.modal-content -->
					</div><!-- /.modal -->
				</div>
<!-- 查看结束 -->

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
											<a data-toggle="tab" href="#tourInfo3">
												<i class="blue icon-laptop bigger-120"></i>
												基本信息
											</a>
										</li>
										<li <c:if test="${sessionScope.isMice }">style="display: none;"</c:if>>
											<a id="editTrip" data-toggle="tab" href="#trips3">
												<i class="orange icon-calendar bigger-120"></i>
												行程
											</a>
										</li>
										
										
										<li>
											<a data-toggle="tab" href="#costs3">
												<i class="green icon-credit-card bigger-120"></i>
												成本
											</a>
										</li>
										<li>
											<a data-toggle="tab" href="#incomes3">
												<i class="pink icon-briefcase bigger-120"></i>
												收入
											</a>
										</li>
									</ul>
					         	</div>
					         	
					         	<div class="tab-content no-border padding-6" style="z-index: 1400;">
					         		<div id="tourInfo3" class="tab-pane fade in active">
					         			<table class="table table-striped table-bordered table-hover no-margin-bottom no-border-top">
											<thead>
											</thead>
		
											<tbody>
												<tr>
													<td>团号*</td>
													<td class="tourInfo"><input type="text" class="form-control" /></td>
													<td>团名*</td>
													<td class="tourInfo"><input type="text" class="form-control" /></td>
													<td>业务类型*</td>
													<td style="width: 23%;" class="tourInfo">
														<select style="display: none;" class="businessType" data-placeholder="Choose a Country...">
															<option value="">&nbsp;</option>
														</select>
													</td>
												</tr>
												<tr>
													<td>团队类型*</td>
													<td class="tourInfo">
														<select style="display: none;" class="tourType" data-placeholder="Choose a Country...">
															<option value="">&nbsp;</option>
														</select>
													</td>
													<td>国家/地区*</td>
													<td class="tourInfo">
														<select style="display: none;" class="region" data-placeholder="Choose a Country...">
															<option value="">&nbsp;</option>
														</select>
													</td>
													<td>游客类型*</td>
													<td class="tourInfo">
														<select style="display: none;" class="visitorType" data-placeholder="Choose a Country...">
															<option value="">&nbsp;</option>
														</select>
													</td>
												</tr>
												<tr>
													<td>组团社*</td>
													<td class="tourInfo">
														<select id="customer" style="display: none;" class="customerAgency" data-placeholder="Choose a Country...">
															<option value="">&nbsp;</option>
														</select>
													</td>
													<td>组团人*</td>
													<td class="tourInfo"><input type="text" class="form-control" /></td>
													<td>全陪人数</td>
													<td class="tourInfo"><input type="text" class="form-control" /></td>
												</tr>
												<tr>
													<td>成人数*</td>
													<td class="tourInfo"><input type="text" class="form-control" /></td>
													<td>儿童数</td>
													<td class="tourInfo"><input type="text" class="form-control" /></td>
													<td>人数合计</td>
													<td class="tourInfo"></td>
												</tr>
												<tr>
													<td>开始日期*</td>
													<td class="tourInfo"><input type="text" class="form-control datepicker" /></td>
													<td>结束日期*</td>
													<td class="tourInfo"><input type="text" class="form-control datepicker" /></td>
													<td>导游</td>
													<td id="guideTd" class="tourInfo">
														<select style="display: none;" multiple="multiple" class="width-20 chosen-select" id="guides" data-placeholder="可选多个...">
															<option value="">&nbsp;</option>
														</select>
													</td>
												</tr>
												<tr>
													<td>备注</td>
													<td class="tourInfo"><input type="text" class="form-control" /></td>
												</tr>
											</tbody>
										</table>
										<div class="modal-header no-padding">
											<div class="table-header" <c:if test="${sessionScope.isMice }">style="display: none;"</c:if>>
												抵离信息
												<a class="white addArrDep" href="#"><i class="icon-plus bigger-100"></i></a>
											</div>
										</div>
										<table id="arrDepTable" class="table table-striped table-bordered table-hover no-margin-bottom no-border-top" <c:if test="${sessionScope.isMice }">style="display: none;"</c:if>>
											<thead>
												<tr>
													<th>出发地</th>
													<th>抵达交通</th>
													<th>抵达时间</th>
													<th>抵达班次</th>
													<th>抵达地</th>
													<th aria-label="" style="width: 5%;" colspan="1" rowspan="1" role="columnheader" class="sorting_disabled">
														操作
													</th>
												</tr>
											</thead>
		
											<tbody class="arrInfo">
											</tbody>
											<thead>
												<tr>
													<th style="width: 19%;">前往地</th>
													<th style="width: 19%;">离开交通</th>
													<th style="width: 19%;">离开时间</th>
													<th style="width: 19%;">离开班次</th>
													<th style="width: 19%;">离开地</th>
													<th aria-label="" style="width: 5%;" colspan="1" rowspan="1" role="columnheader" class="sorting_disabled">
														操作
													</th>
												</tr>
											</thead>
											<tbody class="departInfo">
											</tbody>
										</table>
					         		</div><!-- 修改tab结束 -->
					         		<div id="costs3" class="tab-pane fade costTable">
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
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
															<th style="width: 10%;">明细备注</th>
															<th style="width: 5%;">
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
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
															<th style="width: 10%;">明细备注</th>
															<th style="width: 5%;">
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
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
															<th style="width: 10%;">明细备注</th>
															<th style="width: 5%;">
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
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
															<th style="width: 10%;">明细备注</th>
															<th style="width: 5%;">
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
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
															<th style="width: 10%;">明细备注</th>
															<th style="width: 5%;">
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
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
															<th style="width: 10%;">明细备注</th>
															<th style="width: 5%;">
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
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
															<th style="width: 10%;">明细备注</th>
															<th style="width: 5%;">
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
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
															<th style="width: 10%;">明细备注</th>
															<th style="width: 5%;">
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
					         		
					         		<div id="trips3" class="tab-pane fade">
					         			<div class="tabbable tabs-left">
						         			<ul class="nav nav-tabs" id="myTab3">
											</ul>
											<div class="tab-content no-padding">
												<div id="editTripModel" class="tab-pane in active" style="display:none;">
													<table class="table table-striped table-bordered table-hover no-margin-bottom no-border-top">
														<tr><td style="width: 60px;">行程：</td><td class="tourInfo"><textarea class="form-control" rows="8"></textarea></td></tr>
														<tr><td>餐食：</td><td class="tourInfo"><input style="width:100%;" class="form-control" type="text"></td></tr>
														<tr><td>住宿：</td><td class="tourInfo"><input style="width:100%;" class="form-control" type="text"></td></tr>
														<tr><td>交通：</td><td class="tourInfo"><input style="width:100%;" class="form-control" type="text"></td></tr>
														<tr><td>备注：</td><td class="tourInfo"><textarea class="form-control" rows="1"></textarea></td></tr>
													</table>
												</div>
						         			</div><!-- tab content 结束 -->
					         			</div><!-- 左tab结束 -->
					         		</div><!-- 行程tab结束 -->
					         		
					         		<div id="incomes3" class="tab-pane fade">
					         			<div class="tabbable tabs-left">
											<div class="tab-content no-padding" style="z-index: 1400;overflow: visible;">
												<table class="table table-striped table-bordered table-hover incomeTable">
														<thead>
															<tr>
																<th style="width: 15%">日期</th>
																<th>客户*</th>
																<th style="width: 10%">收入</th>
																<th style="width: 10%">实收</th>
																<th style="width: 10%">已开发票</th>
																<th style="width: 20%">备注</th>
																<th style="width: 5%">
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
								<shiro:hasPermission name="localTour:update">
									<button id="saveEdit" class="btn btn-sm btn-success pull-right" data-dismiss="modal">
										<i class="icon-save"></i>
										保存
									</button>
								</shiro:hasPermission>
						 	 </div>
						</div><!-- /.modal-content -->
					</div><!-- /.modal -->
				</div>
<!-- 编辑结束 -->
<!-- 成本变更模板-->
				<div aria-hidden="true" style="display: none;" id="cost" class="modal fade" tabindex="-1">
					<div class="modal-dialog" style="width: 80%;">
						<div class="modal-content">
					        <div class="modal-header no-padding">
								<div class="table-header">
									成本变更
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
															<th style="width: 5%;">成本</th>
															<th style="width: 5%;">数量</th>
															<th style="width: 5%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
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
															<th style="width: 5%;">成本</th>
															<th style="width: 5%;">数量</th>
															<th style="width: 5%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
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
															<th style="width: 5%;">成本</th>
															<th style="width: 5%;">数量</th>
															<th style="width: 5%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
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
															<th style="width: 5%;">成本</th>
															<th style="width: 5%;">数量</th>
															<th style="width: 5%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
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
															<th style="width: 5%;">成本</th>
															<th style="width: 5%;">数量</th>
															<th style="width: 5%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
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
															<th style="width: 5%;">成本</th>
															<th style="width: 5%;">数量</th>
															<th style="width: 5%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
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
															<th style="width: 5%;">成本</th>
															<th style="width: 5%;">数量</th>
															<th style="width: 5%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
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
															<th style="width: 5%;">成本</th>
															<th style="width: 5%;">数量</th>
															<th style="width: 5%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
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
																<th style="width: 10%">已开发票</th>
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
								<shiro:hasPermission name="localTour:changeApplication">
									<button id="saveChange" class="btn btn-sm btn-success pull-right" data-dismiss="modal">
										<i class="icon-save"></i>
										保存
									</button>
								</shiro:hasPermission>
						 	 </div>
						</div><!-- /.modal-content -->
					</div><!-- /.modal -->
				</div>
<!-- 成本变更结束 -->
<!-- 导游借款模板-->
				<div aria-hidden="true" style="display: none;" id="lendModel" class="modal fade" tabindex="-1">
					<div class="modal-dialog" style="width: 80%;">
						<div class="modal-content">
					        <div class="modal-header no-padding">
								<div class="table-header">
									导游借款
						 		</div>
						  	</div>
							<div class="modal-body no-padding">
					         	<div class="tab-content no-border padding-6" style="z-index: 1400;">
					         		<div class="tab-pane fade in active costTable">
					         			<div style="background-color: silver;font-size: 14px;padding: 3px;padding-left: 10px;color: white;">可借款项</div>
					         			<table class="table table-striped table-bordered table-hover no-margin">
											<thead>
												<tr>
													<th style="width: 10%;">日期</th>
													<th style="width: 10%;">借款额</th>
													<th style="width: 20%;">明细备注</th>
													<th style="width: 10%;">财务操作人</th>
													<th style="width: 10%;">状态</th>
												</tr>
											</thead>
											<tbody id="canLoans">
											</tbody>
							            </table>
							            <div style="background-color: silver;font-size: 14px;padding: 3px;padding-left: 10px;color: white;">借款记录</div>
							            <table class="table table-striped table-bordered table-hover no-margin">
											<thead>
												<tr>
													<th style="width: 10%;">日期</th>
													<th style="width: 10%;">借款额</th>
													<th style="width: 20%;">明细备注</th>
													<th style="width: 10%;">财务操作人</th>
													<th style="width: 10%;">状态</th>
												</tr>
											</thead>
											<tbody id="isLoans">
											</tbody>
							            </table>
					         		</div><!-- 成本tab结束 -->
					         	</div>
					         </div>
							<div class="modal-footer no-margin-top">
								<button class="btn btn-sm btn-danger pull-left" data-dismiss="modal">
									<i class="icon-remove"></i>
									取消
								</button>
								<shiro:hasPermission name="localTour:loanApplication">
									<button id="loanApplication" class="btn btn-sm btn-success pull-right" data-dismiss="modal">
										<i class="icon-save"></i>
										借款申请
									</button>
								</shiro:hasPermission>
						 	 </div>
						</div><!-- /.modal-content -->
					</div><!-- /.modal -->
				</div>
<!-- 导游借款结束 -->
<!-- 付款申请模板-->
				<div aria-hidden="true" style="display: none;" id="payModel" class="modal fade" tabindex="-1">
					<div class="modal-dialog" style="width: 80%;">
						<div class="modal-content">
					        <div class="modal-header no-padding">
								<div class="table-header">
									付款申请
						 		</div>
						  	</div>
							<div class="modal-body no-padding">
					         	<div class="tab-content no-border padding-6" style="z-index: 1400;">
					         		<div class="tab-pane fade in active costTable">
					         			<div style="background-color: silver;font-size: 14px;padding: 3px;padding-left: 10px;color: white;">可付款项</div>
					         			<table class="table table-striped table-bordered table-hover no-margin">
											<thead>
												<tr>
													<th aria-label="" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">
														<label>
															<input class="ace selectAllPay" type="checkbox">
															<span class="lbl"></span>
														</label>
													</th>
													<th style="width: 10%;">日期</th>
													<th style="width: 15%;">内容</th>
													<th style="width: 15%;">供应商</th>
													<th style="width: 7%;">成本</th>
													<th style="width: 7%;">数量</th>
													<th style="width: 7%;">天数</th>
													<th style="width: 7%;">成本小计</th>
													<th style="width: 7%;">申请金额</th>
													<th style="width: 7%;">借款人</th>
													<th style="width: 10%;">明细备注</th>
													<th style="width: 7%;">状态</th>
												</tr>
											</thead>
											<tbody id="canPays">
											</tbody>
							            </table>
							            <div style="background-color: silver;font-size: 14px;padding: 3px;padding-left: 10px;color: white;">付款记录</div>
							            <table class="table table-striped table-bordered table-hover no-margin">
											<thead>
												<tr>
													<th style="width: 10%;">日期</th>
													<th style="width: 15%;">内容</th>
													<th style="width: 15%;">供应商</th>
													<th style="width: 7%;">成本</th>
													<th style="width: 7%;">数量</th>
													<th style="width: 7%;">天数</th>
													<th style="width: 7%;">成本小计</th>
													<th style="width: 7%;">申请金额</th>
													<th style="width: 7%;">借款人</th>
													<th style="width: 10%;">明细备注</th>
													<th style="width: 7%;">状态</th>
												</tr>
											</thead>
											<tbody id="isPays">
											</tbody>
							            </table>
					         		</div><!-- 成本tab结束 -->
					         	</div>
					         </div>
							<div class="modal-footer no-margin-top">
								<button class="btn btn-sm btn-danger pull-left" data-dismiss="modal">
									<i class="icon-remove"></i>
									取消
								</button>
								<a href="#" class="autoAddLend" style="margin:  20px;">一键填充金额</a>
								<shiro:hasPermission name="localTour:payApplication">
									<button id="payApplication" class="btn btn-sm btn-success pull-right" data-dismiss="modal">
										<i class="icon-save"></i>
										付款申请
									</button>
								</shiro:hasPermission>
						 	 </div>
						</div><!-- /.modal-content -->
					</div><!-- /.modal -->
				</div>
<!-- 付款申请结束 -->
<!-- 预借发票模板-->
				<div aria-hidden="true" style="display: none;" id="loanInvoiceModel" class="modal fade" tabindex="-1">
					<div class="modal-dialog" style="width: 80%;">
						<div class="modal-content">
					        <div class="modal-header no-padding">
								<div class="table-header">
									预借发票申请
						 		</div>
						  	</div>
							<div class="modal-body no-padding">
					         	<div class="tab-content no-border padding-6" style="z-index: 1400;">
					         		<div class="tab-pane fade in active costTable">
					         			<table class="table table-striped table-bordered table-hover no-margin">
											<thead>
												<tr>
													<th style="width: 10%;">日期</th>
													<th style="width: 20%;">抬头</th>
													<th style="width: 10%;">内容</th>
													<th style="width: 10%;">金额*</th>
													<th style="width: 45%;">发票信息*</th>
													<th style="width: 5%;">
														<a class="blue addLoanInvoice" href="#">
															<i class="icon-plus bigger-130"></i>
														</a>
													</th>
												</tr>
											</thead>
											<tbody id="loanInvoices">
											</tbody>
							            </table>
					         		</div><!-- 成本tab结束 -->
					         	</div>
					         </div>
							<div class="modal-footer no-margin-top">
								<button class="btn btn-sm btn-danger pull-left" data-dismiss="modal">
									<i class="icon-remove"></i>
									取消
								</button>
								<shiro:hasPermission name="localTour:borrowInvoiceApplication">
									<button id="loanInvoiceApplication" class="btn btn-sm btn-success pull-right" data-dismiss="modal">
										<i class="icon-save"></i>
										申请
									</button>
								</shiro:hasPermission>
						 	 </div>
						</div><!-- /.modal-content -->
					</div><!-- /.modal -->
				</div>
<!-- 预借发票结束 -->
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
										<li>
											<a data-toggle="tab" href="#incomes5">
												<i class="pink icon-briefcase bigger-120"></i>
												收入
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
											<div id="reimbursementPrintDiv" class="tab-content no-padding" style="z-index: 1400;display: inline-block;float: right;right: -4px;width: 90%;overflow: visible;">
												<style type="text/css">
													@media print{
														.printFrame{
															height: 12.8cm;
															margin-bottom: 1cm;
														}
														.printFrame table{
															height: 90%;
														}
														table{
															font-size: 10px;
															border-collapse: collapse;
															width: 100%;
														}
														td{
															border: 1px solid;
														}
														.h3{
															font-size: 14px;
															text-align: center;
															margin: 0px;
														}
														.h4{
															font-size: 12px;
															margin: 0px;
														}
														.h4 span{
															float: right;
														}
														#changeCostBlue{
															display: none;
														}
														#reimbursementCostRed{
															display: none;
														}
														.autograph{
															font-size: 10px;
															width: 16%;
															display: inline-block;
														}
													}
												</style>
												<div id="flight5" class="tab-pane in active">
													<table class="table table-striped table-bordered table-hover no-margin">
														<thead>
															<tr>
																<th style="width: 5%;">日期</th>
																<th style="width: 8%;">内容</th>
																<th style="width: 5%;">供应商*</th>
																<th style="width: 5%;">成本</th>
																<th style="width: 5%;">数量</th>
																<th style="width: 5%;">天数</th>
																<th style="width: 5%;">预估成本</th>
																<th style="width: 5%;">已汇金额</th>
																<th style="width: 9%;">报账金额</th>
																<th style="width: 5%;">明细备注</th>
																<th style="width: 5%;">导游借款</th>
																<th style="width: 5%;">挂账</th>
																<th style="width: 5%;">付款状态</th>
																<th style="width: 1%;">
																	<a class="blue addReimbursement" href="#">
																		<i class="icon-plus bigger-130"></i>
																	</a>
																</th>
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
																<th style="width: 5%;">日期</th>
																<th style="width: 8%;">内容</th>
																<th style="width: 5%;">供应商*</th>
																<th style="width: 5%;">成本</th>
																<th style="width: 5%;">数量</th>
																<th style="width: 5%;">天数</th>
																<th style="width: 5%;">预估成本</th>
																<th style="width: 5%;">已汇金额</th>
																<th style="width: 9%;">报账金额</th>
																<th style="width: 5%;">明细备注</th>
																<th style="width: 5%;">导游借款</th>
																<th style="width: 5%;">挂账</th>
																<th style="width: 5%;">付款状态</th>
																<th style="width: 1%;">
																	<a class="blue addReimbursement" href="#">
																		<i class="icon-plus bigger-130"></i>
																	</a>
																</th>
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
																<th style="width: 5%;">日期</th>
																<th style="width: 8%;">内容</th>
																<th style="width: 5%;">供应商*</th>
																<th style="width: 5%;">成本</th>
																<th style="width: 5%;">数量</th>
																<th style="width: 5%;">天数</th>
																<th style="width: 5%;">预估成本</th>
																<th style="width: 5%;">已汇金额</th>
																<th style="width: 9%;">报账金额</th>
																<th style="width: 5%;">明细备注</th>
																<th style="width: 5%;">导游借款</th>
																<th style="width: 5%;">挂账</th>
																<th style="width: 5%;">付款状态</th>
																<th style="width: 1%;">
																	<a class="blue addReimbursement" href="#">
																		<i class="icon-plus bigger-130"></i>
																	</a>
																</th>
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
																<th style="width: 5%;">日期</th>
																<th style="width: 8%;">内容</th>
																<th style="width: 5%;">供应商*</th>
																<th style="width: 5%;">成本</th>
																<th style="width: 5%;">数量</th>
																<th style="width: 5%;">天数</th>
																<th style="width: 5%;">预估成本</th>
																<th style="width: 5%;">已汇金额</th>
																<th style="width: 9%;">报账金额</th>
																<th style="width: 5%;">明细备注</th>
																<th style="width: 5%;">导游借款</th>
																<th style="width: 5%;">挂账</th>
																<th style="width: 5%;">付款状态</th>
																<th style="width: 1%;">
																	<a class="blue addReimbursement" href="#">
																		<i class="icon-plus bigger-130"></i>
																	</a>
																</th>
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
																<th style="width: 5%;">日期</th>
																<th style="width: 8%;">内容</th>
																<th style="width: 5%;">供应商*</th>
																<th style="width: 5%;">成本</th>
																<th style="width: 5%;">数量</th>
																<th style="width: 5%;">天数</th>
																<th style="width: 5%;">预估成本</th>
																<th style="width: 5%;">已汇金额</th>
																<th style="width: 9%;">报账金额</th>
																<th style="width: 5%;">明细备注</th>
																<th style="width: 5%;">导游借款</th>
																<th style="width: 5%;">挂账</th>
																<th style="width: 5%;">付款状态</th>
																<th style="width: 1%;">
																	<a class="blue addReimbursement" href="#">
																		<i class="icon-plus bigger-130"></i>
																	</a>
																</th>
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
																<th style="width: 5%;">日期</th>
																<th style="width: 8%;">内容</th>
																<th style="width: 5%;">供应商*</th>
																<th style="width: 5%;">成本</th>
																<th style="width: 5%;">数量</th>
																<th style="width: 5%;">天数</th>
																<th style="width: 5%;">预估成本</th>
																<th style="width: 5%;">已汇金额</th>
																<th style="width: 9%;">报账金额</th>
																<th style="width: 5%;">明细备注</th>
																<th style="width: 5%;">导游借款</th>
																<th style="width: 5%;">挂账</th>
																<th style="width: 5%;">付款状态</th>
																<th style="width: 1%;">
																	<a class="blue addReimbursement" href="#">
																		<i class="icon-plus bigger-130"></i>
																	</a>
																</th>
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
															<th style="width: 5%;">日期</th>
															<th style="width: 8%;">内容</th>
															<th style="width: 5%;">供应商*</th>
															<th style="width: 5%;">成本</th>
															<th style="width: 5%;">数量</th>
															<th style="width: 5%;">天数</th>
															<th style="width: 5%;">预估成本</th>
															<th style="width: 5%;">已汇金额</th>
															<th style="width: 9%;">报账金额</th>
															<th style="width: 5%;">明细备注</th>
															<th style="width: 5%;">导游借款</th>
															<th style="width: 5%;">挂账</th>
															<th style="width: 5%;">付款状态</th>
															<th style="width: 1%;">
																<a class="blue addReimbursement" href="#">
																	<i class="icon-plus bigger-130"></i>
																</a>
															</th>
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
															<th style="width: 5%;">日期</th>
															<th style="width: 8%;">内容</th>
															<th style="width: 5%;">供应商*</th>
															<th style="width: 5%;">成本</th>
															<th style="width: 5%;">数量</th>
															<th style="width: 5%;">天数</th>
															<th style="width: 5%;">预估成本</th>
															<th style="width: 5%;">已汇金额</th>
															<th style="width: 9%;">报账金额</th>
															<th style="width: 5%;">明细备注</th>
															<th style="width: 5%;">导游借款</th>
															<th style="width: 5%;">挂账</th>
															<th style="width: 5%;">付款状态</th>
															<th style="width: 1%;">
																<a class="blue addReimbursement" href="#">
																	<i class="icon-plus bigger-130"></i>
																</a>
															</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
									            </table>
											</div>
											<span id="changeCostBlue" class="blue">*蓝色为成本收入变更</span>
											<span id="reimbursementCostRed" class="red">*红色为报账新增成本</span>
						         			</div><!-- tab content 结束 -->
					         			</div><!-- 左tab 结束 -->
					         		</div><!-- 成本tab结束 -->
					         		<div id="incomes5" class="tab-pane fade">
					         			<div class="tabbable tabs-left">
											<div class="tab-content no-padding" style="z-index: 1400;overflow: visible;">
												<table class="table table-striped table-bordered table-hover incomeTable">
														<thead>
															<tr>
																<th style="width: 15%">日期</th>
																<th>客户*</th>
																<th style="width: 10%">收入</th>
																<th style="width: 10%">实收</th>
																<th style="width: 10%">已开发票</th>
																<th style="width: 20%">备注</th>
																<th style="width: 1%">
																	<a class="blue reimbursementIncomeAdd" href="#">
																		<i class="icon-plus bigger-130"></i>
																	</a>
																</th>
															</tr>
														</thead>
														<tbody>
											            </tbody>
										            </table>
										            <span id="changeIncomeBlue" class="blue" style="">*蓝色为成本收入变更</span>
												    <span id="reimbursementIncomeRed" class="red" style="">*红色为报账调整应收</span>   			
						         			</div><!-- tab content 结束 -->
					         			</div><!-- 左tab 结束 -->
					         		</div><!-- 收入tab结束 -->
					         	</div>
					         	<div class="tab-content no-border padding-6">
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
													<td style="width: 10%;">预估成本</td>
													<td id="willCostSum" style="width: 15%;"></td>
													<td style="width: 10%;">报账总计</td>
													<td id="reimbursementSum" style="width: 15%;"></td>
													<td style="width: 10%;">挂账总计</td>
													<td id="billSum" style="width: 15%;"></td>
													<td style="width: 10%;">人头*</td>
													<td id="headAmount" style="width: 15%;"><input class="form-control" style="width:100%;" type="text"></td>
												</tr>
												<tr>
													<td style="width: 10%;">应收款</td>
													<td id="willIncomeSum" style="width: 15%;"></td>
													<td style="width: 10%;">已收款</td>
													<td id="realIncomeSum" style="width: 15%;"></td>
													<td style="width: 10%;">报账毛利</td>
													<td id="realGrossProfit" style="width: 15%;"></td>
													<td style="width: 10%;">报账毛利率</td>
													<td id="realGrossMargin" style="width: 15%;"></td>
												</tr>
											</tbody>
							            </table>
					         		</div><!-- 成本tab结束 -->
					         	</div>
					        </div>
							<div class="modal-footer no-margin-top">
								<button class="btn btn-sm btn-danger pull-left" data-dismiss="modal">
									<i class="icon-remove"></i>
									取消
								</button>
								<a href="#" id="autoAddReimbursement" style="margin:  20px;">一键填充报账金额</a>
								<shiro:hasPermission name="localTour:reimbursementApplication">
									<button id="reimbursementApplication" class="btn btn-sm btn-success pull-right" data-dismiss="modal">
										<i class="icon-save"></i>
										保存
									</button>
								</shiro:hasPermission>
								<!-- <button id="reimbursementPrintButton" class="btn btn-sm btn-success pull-right" data-dismiss="modal" style="margin-right: 10px;">
									<i class="icon-save"></i>
									打印报账单
								</button> -->
						 	 </div>
						</div><!-- /.modal-content -->
					</div><!-- /.modal -->
				</div>
<!-- 团队报账结束 -->
<!-- 打印借款付款模板-->
				<div aria-hidden="true" style="display: none;" id="lendPrintModel" class="modal fade" tabindex="-1">
					<div class="modal-dialog" style="width: 80%;">
						<div class="modal-content">
					        <div class="modal-header no-padding">
								<div class="table-header">
									凭证打印
						 		</div>
						  	</div>
							<div class="modal-body no-padding">
					         	<div class="tab-content no-border padding-6" style="z-index: 1400;">
					         		<div id="printArea" class="tab-pane fade in active costTable">
					         			<style type="text/css">
											@media print{
												table{
													font-size: 12px;
													border-collapse: collapse;
													margin-top: 10px;
													width: 100%;
												}
												td{
													border: 1px solid;
												}
												h3{
													text-align: center;
													margin:0px;
												}
												span{
													position: absolute;
													top:20px;
													right:10px;
													font-size: 12px;
												}
												input{
													border: 0px;
												}
											}
											
										</style>
										<span class="pull-right"></span>
					         			<table class="table table-striped table-bordered table-hover no-margin">
									      <tbody>
										        <tr>
											        <td style="width: 10%;">团号</td>
											        <td style="width: 20%;" class="printInfo"></td>
											        <td style="width: 10%;">团名</td>
											        <td style="width: 20%;" class="printInfo"></td>
											        <td style="width: 10%;">部门</td>
											        <td style="width: 20%;" class="printInfo"></td>
												</tr>
												<tr>
											        <td>总金额</td>
											        <td class="printInfo"></td>
											        <td>大写</td>
											        <td class="printInfo"></td>
											        <td>借款方式</td>
											        <td><input style="width: 100%;" type="text"></td>
												</tr>
												<tr>
											        <td>打印日期</td>
											        <td class="printInfo"></td>
											        <td>打印人</td>
											        <td class="printInfo"></td>
											        <td>备注</td>
											        <td><input style="width: 100%;" type="text"></td>
												</tr>
											</tbody>
										</table>
					         		</div><!-- 成本tab结束 -->
					         	</div>
					         </div>
							<div class="modal-footer no-margin-top">
								<button class="btn btn-sm btn-danger pull-left" data-dismiss="modal">
									<i class="icon-remove"></i>
									取消
								</button>
								<button id="lendPrint" class="btn btn-sm btn-success pull-right" data-dismiss="modal">
									<i class="icon-print"></i>
									打印
								</button>
						 	 </div>
						</div><!-- /.modal-content -->
					</div><!-- /.modal -->
				</div>
<!-- 打印结束 -->
<!-- 打印缴款单模板-->
				<div aria-hidden="true" style="display: none;" id="incomePrintModel" class="modal fade" tabindex="-1">
					<div class="modal-dialog" style="width: 80%;">
						<div class="modal-content">
					        <div class="modal-header no-padding">
								<div class="table-header">
									凭证打印
						 		</div>
						  	</div>
							<div class="modal-body no-padding">
					         	<div class="tab-content no-border padding-6" style="z-index: 1400;">
					         		<div id="printArea" class="tab-pane fade in active costTable">
					         			<style type="text/css">
											@media print{
												table{
													font-size: 12px;
													border-collapse: collapse;
													margin-top: 10px;
													width: 100%;
												}
												td{
													border: 1px solid;
												}
												h3{
													text-align: center;
													margin:0px;
												}
												span{
													position: absolute;
													top:20px;
													right:10px;
													font-size: 12px;
												}
												input{
													border: 0px;
												}
											}
											
										</style>
										<span class="pull-right"></span>
					         			<table class="table table-striped table-bordered table-hover no-margin">
									      <tbody>
										        <tr>
											        <td style="width: 10%;">团号</td>
											        <td style="width: 20%;" class="printInfo"></td>
											        <td style="width: 10%;">团名</td>
											        <td style="width: 20%;" class="printInfo"></td>
											        <td style="width: 10%;">部门</td>
											        <td style="width: 20%;" class="printInfo"></td>
												</tr>
												<tr>
											        <td>客户</td>
											        <td class="printInfo"></td>
											        <td>收款项目</td>
											        <td><input style="width: 100%;" type="text"></td>
											        <td>收款方式</td>
											        <td><input style="width: 100%;" type="text"></td>
												</tr>
												<tr>
											        <td>金额</td>
											        <td><input style="width: 100%;" type="text"></td>
											        <td>大写</td>
											        <td class="printInfo"></td>
											        <td>打印人</td>
											        <td class="printInfo"></td>
												</tr>
											</tbody>
										</table>
					         		</div><!-- 成本tab结束 -->
					         	</div>
					         </div>
							<div class="modal-footer no-margin-top">
								<button class="btn btn-sm btn-danger pull-left" data-dismiss="modal">
									<i class="icon-remove"></i>
									取消
								</button>
								<button id="incomePrint" class="btn btn-sm btn-success pull-right" data-dismiss="modal">
									<i class="icon-print"></i>
									打印
								</button>
						 	 </div>
						</div><!-- /.modal-content -->
					</div><!-- /.modal -->
				</div>
<!-- 打印结束 -->
<!-- 打印预借发票模板-->
				<div aria-hidden="true" style="display: none;" id="invoicePrintModel" class="modal fade" tabindex="-1">
					<div class="modal-dialog" style="width: 80%;">
						<div class="modal-content">
					        <div class="modal-header no-padding">
								<div class="table-header">
									凭证打印
						 		</div>
						  	</div>
							<div class="modal-body no-padding">
					         	<div class="tab-content no-border padding-6" style="z-index: 1400;">
					         		<div id="printArea" class="tab-pane fade in active costTable">
					         			<style type="text/css">
											@media print{
												table{
													font-size: 12px;
													border-collapse: collapse;
													margin-top: 10px;
													width: 100%;
												}
												td{
													border: 1px solid;
												}
												h3{
													text-align: center;
													margin:0px;
												}
												span{
													position: absolute;
													top:20px;
													right:10px;
													font-size: 12px;
												}
												input{
													border: 0px;
												}
											}
											
										</style>
										<span class="pull-right">第0次打印</span>
					         			<table class="table table-striped table-bordered table-hover no-margin">
									      <tbody>
										        <tr>
											        <td style="width: 10%;">团号</td>
											        <td style="width: 20%;" class="printInfo"></td>
											        <td style="width: 10%;">团名</td>
											        <td style="width: 20%;" class="printInfo"></td>
											        <td style="width: 10%;">部门</td>
											        <td style="width: 20%;" class="printInfo"></td>
												</tr>
												<tr>
											        <td>客户</td>
											        <td class="printInfo"></td>
											        <td>总开票额</td>
											        <td class="printInfo"></td>
											        <td>大写</td>
											        <td class="printInfo"></td>
												</tr>
												<tr>
											        <td>打印时间</td>
											        <td class="printInfo"></td>
											        <td>打印人</td>
											        <td class="printInfo"></td>
											        <td>备注</td>
											        <td><input style="width: 100%;" type="text"></td>
												</tr>
											</tbody>
										</table>
					         		</div><!-- 成本tab结束 -->
					         	</div>
					         </div>
							<div class="modal-footer no-margin-top">
								<button class="btn btn-sm btn-danger pull-left" data-dismiss="modal">
									<i class="icon-remove"></i>
									取消
								</button>
								<button id="invoicePrint" class="btn btn-sm btn-success pull-right" data-dismiss="modal">
									<i class="icon-print"></i>
									打印
								</button>
						 	 </div>
						</div><!-- /.modal-content -->
					</div><!-- /.modal -->
				</div>
<!-- 打印结束 -->
<!-- 退款模板-->
				<div aria-hidden="true" style="display: none;" id="refundModel" class="modal fade" tabindex="-1">
					<div class="modal-dialog" style="width: 80%;">
						<div class="modal-content">
					        <div class="modal-header no-padding">
								<div class="table-header">
									退款申请
						 		</div>
						  	</div>
							<div class="modal-body no-padding">
					         	<div class="tab-content no-border padding-6" style="z-index: 1400;">
					         		<div class="tab-pane fade in active costTable">
					         			<table class="table table-striped table-bordered table-hover no-margin">
											<thead>
												<tr>
													<th style="width: 10%;">日期</th>
													<th style="width: 25%;">客户</th>
													<th style="width: 10%;">退款内容</th>
													<th style="width: 10%;">退款方式</th>
													<th style="width: 10%;">金额*</th>
													<th style="width: 25%;">退款账户信息</th>
													<th style="width: 10%;">
														<a class="blue addRefund" href="#">
															<i class="icon-plus bigger-130"></i>
														</a>
													</th>
												</tr>
											</thead>
											<tbody id="refunds">
											</tbody>
							            </table>
					         		</div><!-- 成本tab结束 -->
					         	</div>
					         </div>
							<div class="modal-footer no-margin-top">
								<button class="btn btn-sm btn-danger pull-left" data-dismiss="modal">
									<i class="icon-remove"></i>
									取消
								</button>
								<shiro:hasPermission name="localTour:refundApplication">
									<button id="refundApplication" class="btn btn-sm btn-success pull-right" data-dismiss="modal">
										<i class="icon-save"></i>
										申请
									</button>
								</shiro:hasPermission>
						 	 </div>
						</div><!-- /.modal-content -->
					</div><!-- /.modal -->
				</div>
<!-- 退款结束 -->
<!-- 打印退款单模板-->
				<div aria-hidden="true" style="display: none;" id="refundPrintModel" class="modal fade" tabindex="-1">
					<div class="modal-dialog" style="width: 80%;">
						<div class="modal-content">
					        <div class="modal-header no-padding">
								<div class="table-header">
									凭证打印
						 		</div>
						  	</div>
							<div class="modal-body no-padding">
					         	<div class="tab-content no-border padding-6" style="z-index: 1400;">
					         		<div id="printArea" class="tab-pane fade in active">
					         			<style type="text/css">
											@media print{
												table{
													font-size: 12px;
													border-collapse: collapse;
													margin-top: 10px;
													width: 100%;
												}
												td{
													border: 1px solid;
												}
												h3{
													text-align: center;
													margin:0px;
												}
												span{
													position: absolute;
													top:20px;
													right:10px;
													font-size: 12px;
												}
												input{
													border: 0px;
												}
											}
											
										</style>
										<span class="pull-right">第1次打印</span>
					         			<table class="table table-striped table-bordered table-hover no-margin">
									      <tbody>
										        <tr>
											        <td style="width: 10%;">团号</td>
											        <td style="width: 20%;" class="printInfo"></td>
											        <td style="width: 10%;">团名</td>
											        <td style="width: 20%;" class="printInfo"></td>
											        <td style="width: 10%;">部门</td>
											        <td style="width: 20%;" class="printInfo"></td>
												</tr>
												<tr>
											        <td>客户</td>
											        <td class="printInfo"></td>
											        <td>退款金额</td>
											        <td class="printInfo"></td>
											        <td>大写</td>
											        <td class="printInfo"></td>
												</tr>
												<tr>
											        <td>打印时间</td>
											        <td class="printInfo"></td>
											        <td>打印人</td>
											        <td class="printInfo"></td>
											        <td>备注</td>
											        <td><input style="width: 100%;" type="text"></td>
												</tr>
											</tbody>
										</table>
					         		</div><!-- 成本tab结束 -->
					         	</div>
					         </div>
							<div class="modal-footer no-margin-top">
								<button class="btn btn-sm btn-danger pull-left" data-dismiss="modal">
									<i class="icon-remove"></i>
									取消
								</button>
								<button id="refundPrint" class="btn btn-sm btn-success pull-right" data-dismiss="modal">
									<i class="icon-print"></i>
									打印
								</button>
						 	 </div>
						</div><!-- /.modal-content -->
					</div><!-- /.modal -->
				</div>
<!-- 打印结束 -->
<!-- 打印提示 -->
				<div aria-hidden="true" style="display: none;" id="printAlert" class="modal fade" tabindex="-1">
					<div class="modal-dialog" style="width: 80%;">
						<div class="modal-content" style="background-color:rgba(0,0,0,0.2);border: 1px solid rgba(0,0,0,0);text-align: center;">
						</div><!-- /.modal-content -->
					</div><!-- /.modal -->
				</div>
<!-- 打印提示结束 -->
<jsp:include page="../../../resources/include/footer.jsp"></jsp:include>

<!-- 下拉搜索依赖 -->
<script src="${path }resources/assets/js/chosen.jquery.min.js"></script>
<!-- 日历组件依赖 -->
<script src="${path }resources/assets/js/jquery-ui-1.10.3.full.min.js"></script>
<!-- 打印组件依赖 -->
<script src="${path }resources/assets/js/jquery.PrintArea.js"></script>
<!-- 这个插件还提供了一些参数可配置，使用的方法：$（element).printArea(option).
参数设置：
1.mode：模式，当点击打印按钮时触发模式，默认为iframe，当设置为popup则会新开一个窗口页面打印。
2.popTitle：设置新开窗口的标题，默认为空。
3.popClose：完成打印后是否关闭窗口，默认为false。  -->

<!-- 本页JS -->
<script src="${path }resources/assets/js/localtour/localTourManage.js"></script>

<script type="text/javascript">$(function(){
	/* AJAX 默认选项 结合shiro */
	$.ajaxSetup({
		error: function(XMLHttpRequest, textStatus, errorMsg){
			if(textStatus=="parsererror"){
          		alert("登陆超时！请重新登陆！");
                window.location.href = '${path }';
	        } else if(textStatus=="error"){
				if(XMLHttpRequest.status==400){
	        		alert("请检查必填项，或其他数据是否正确，（如：天数只可填写整数）");
	        	}else if(XMLHttpRequest.status==500){
	        		alert("服务器错误，请记录具体操作流程，联系管理员");
	        	}else if(XMLHttpRequest.status==404){
	        		/* alert("提交错误，找不到页面"); */
	        	}else{
	        		alert("其他错误，请记录具体操作流程，联系管理员  "+textStatus+":"+XMLHttpRequest.status);
	        	}
	        }
		}
	});
	/* 初始化 */
	/* 样式 */
	$("#gourpManage").addClass("open");
	$("#gourpManage").children("ul").attr("style","display:block");
	$("#localTourManage").addClass("active");
	$("#create").find("input").attr("style","width:100%;");
	$("#create").find("select").attr("style","width:100%;");
	$(".modal-dialog").attr("style","width:90%;");
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
	
	/* 日历初始化 */
	$(".datepicker").not("#arrTime,#departTime,#costTime,#incomeTime").datepicker({
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
	
	$(".accessBar").children("a").not("#createTour").hide();
/* 初始化选项 */
	/* 全局 */
	var selectInfo;
	/* 新建初始化 */
	
	var inited = false;
	function init(){
		$.ajax({  
	        type: "GET",  
	        contentType:"application/json;charset=utf-8",  
	        url:"${path }localTourManage/getCreateInfo",
	        dataType: "json",  
	        async: false,  
	        success:function(data){
	        	$.each(data.businessTypes,function(){
	        		$(".businessType").append('<option value="'+this.id+'">'+this.businessTypeName+'</option>');
	        	});
	        	$.each(data.tourTypes,function(){
	        		$(".tourType").append('<option value="'+this.id+'">'+this.tourTypeName+'</option>');
	        	});
	        	$.each(data.regions,function(){
	        		$(".region").append('<option value="'+this.id+'">'+this.regionName+'</option>');
	        	});
	        	$.each(data.visitorTypes,function(){
	        		$(".visitorType").append('<option value="'+this.id+'">'+this.visitorTypeName+'</option>');
	        	});
	        	$.each(data.customerAgencys,function(){
	        		$(".customerAgency").append('<option value="'+this.id+'">'+this.customerAgencyName+'</option>');
	        	});
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
	/* 新增团队初始化选项 */
	$("#createTour").click(function(){
		if(inited==false){
			var selects = $("#create").find("select");
			selects.not(".traffic").html('<option value="">&nbsp;</option>');
			init();
			selects.eq(0).chosen({no_results_text: "查无结果", search_contains: true});
        	selects.eq(1).chosen({no_results_text: "查无结果", search_contains: true});
        	selects.eq(2).chosen({no_results_text: "查无结果", search_contains: true});
        	selects.eq(3).chosen({no_results_text: "查无结果", search_contains: true});
        	selects.eq(4).chosen({no_results_text: "查无结果", search_contains: true});
        	$(".chosen-select").next().attr("style","width:100%;");
			$(".chosen-select").next().find("input").attr("style","height:100%;");
			inited = true;
		}
	});
	
	/* 搜索 按钮事件*/
	$(".nav-search-input").focus(function(){
		$(".searchExtra").slideDown();
		$(".searchExtra").focus();
	});
	$(".nav-search-input").keydown(function(event){
		if(event.which == 13){
			$(".form-search").submit();
		}
	});
	$(".searchExtra").find(".icon-remove").click(function(){
		$(".searchExtra").slideUp();
	});
	$(".searchExtra").find(".icon-ok").click(function(){
		$(".form-search").submit();
	});
	
	$(".searchExtra").find("select").chosen({no_results_text: "查无结果", search_contains: true});
	$(".searchExtra").find("select").eq(0).next().attr("style","width: 200px;top: -3px;")
	$(".searchExtra").find("select").eq(0).next().find("li").attr("style","height: 25px;");
	$(".searchExtra").find("select").eq(0).next().find("input").attr("style","height: 25px;position: relative;");
	$(".searchExtra").find("select").eq(1).next().attr("style","width: 200px;top: -3px;")
	$(".searchExtra").find("select").eq(1).next().find("li").attr("style","height: 25px;");
	$(".searchExtra").find("select").eq(1).next().find("input").attr("style","height: 25px;position: relative;");
	$(".searchExtra").find("select").eq(2).next().attr("style","width: 100px;top: -3px;");
	/* 点击本行复选框选中本行 */
	$("#table").delegate(".ace","click",function(){
		var checkbox = $(this);
		if(checkbox.prop("checked")){
			$("#table").find("input").prop("checked",false);
			checkbox.prop("checked",true);
			var status = $(this).parent().parent().siblings().eq(7).text();
			changeButton(status);
		}
	});
	
	/* 点击本行选中复选框 */
	$("#table").delegate("td:not(.sorting_1)","click",function(){
		var checkbox = $(this).siblings().eq(0).find("input");
		if(checkbox.prop("checked")){
			checkbox.prop("checked",false);
		}else{
			$("#table").find("input").prop("checked",false);
			checkbox.prop("checked",true);
			/* 控制按钮显示 */
			var status = $(this).siblings().eq(7).text();
			changeButton(status);
		}
	});
	
	/* 改变按钮 */
	function changeButton(status){
		if(status=="新建"){
			$("#editTour").show();
			$("#auditing").show();
			$("#unAuditing").hide();
			$("#finance").hide();
			$("#lend").hide();
			$("#pay").hide();
			$("#chanageCost").hide();
			$("#loanInvoice").hide();
			$("#refund").hide();
			$("#balance").hide();
			$("#unBalance").hide();
			$("#reimbursement").hide();
			$("#auditingReimbursement").hide();
			$("#delete").show();
			$("#recover").hide();
		}else if(status=="已提交"){
			$("#editTour").hide();		/* 修改 */
			$("#auditing").hide();		/* 提交 */
			$("#unAuditing").show();	/* 退回 */
			$("#finance").show();		/* 报送 */
			$("#lend").hide();			/* 借款 */
			$("#pay").hide();			/* 付款 */
			$("#chanageCost").hide();	/* 变更 */
			$("#loanInvoice").hide();	/* 借票 */
			$("#refund").hide();		/* 退款 */
			$("#balance").hide();		/* 结算 */
			$("#unBalance").hide();		/* 取消结算 */
			$("#reimbursement").hide();	/* 报账 */
			$("#auditingReimbursement").hide();
			$("#delete").hide();		/* 删除 */
			$("#recover").hide();		/* 恢复 */
		}else if(status=="已报送"){
			$("#editTour").hide();		/* 修改 */
			$("#auditing").hide();		/* 提交 */
			$("#unAuditing").hide();	/* 退回 */
			$("#finance").hide();		/* 报送 */
			$("#lend").hide();			/* 借款 */
			$("#pay").hide();			/* 付款 */
			$("#chanageCost").show();	/* 变更 */
			$("#loanInvoice").show();	/* 借票 */
			$("#refund").show();		/* 退款 */
			$("#balance").hide();		/* 结算 */
			$("#unBalance").hide();		/* 取消结算 */
			$("#reimbursement").hide();	/* 报账 */
			$("#auditingReimbursement").hide();
			$("#delete").hide();		/* 删除 */
			$("#recover").hide();		/* 恢复 */
		}else if(status=="可借款"){
			$("#editTour").hide();		/* 修改 */
			$("#auditing").hide();		/* 提交 */
			$("#unAuditing").hide();	/* 退回 */
			$("#finance").hide();		/* 报送 */
			$("#lend").show();			/* 借款 */
			$("#pay").show();			/* 付款 */
			$("#chanageCost").show();	/* 变更 */
			$("#loanInvoice").show();	/* 借票 */
			$("#refund").show();		/* 退款 */
			$("#balance").hide();		/* 结算 */
			$("#unBalance").hide();		/* 取消结算 */
			$("#reimbursement").hide();	/* 报账 */
			$("#auditingReimbursement").hide();
			$("#delete").hide();		/* 删除 */
			$("#recover").hide();		/* 恢复 */
		}else if(status=="进行中"){
			$("#editTour").hide();		/* 修改 */
			$("#auditing").hide();		/* 提交 */
			$("#unAuditing").hide();	/* 退回 */
			$("#finance").hide();		/* 报送 */
			$("#lend").show();			/* 借款 */
			$("#pay").show();			/* 付款 */
			$("#chanageCost").show();	/* 变更 */
			$("#loanInvoice").show();	/* 借票 */
			$("#refund").show();		/* 退款 */
			$("#balance").hide();		/* 结算 */
			$("#unBalance").hide();		/* 取消结算 */
			$("#reimbursement").hide();	/* 报账 */
			$("#auditingReimbursement").hide();
			$("#delete").hide();		/* 删除 */
			$("#recover").hide();		/* 恢复 */
		}else if(status=="已结束"){
			$("#editTour").hide();		/* 修改 */
			$("#auditing").hide();		/* 提交 */
			$("#unAuditing").hide();	/* 退回 */
			$("#finance").hide();		/* 报送 */
			$("#lend").show();			/* 借款 */
			$("#pay").show();			/* 付款 */
			$("#chanageCost").show();	/* 变更 */
			$("#loanInvoice").show();	/* 借票 */
			$("#refund").show();		/* 退款 */
			$("#balance").show();		/* 结算 */
			$("#unBalance").hide();		/* 取消结算 */
			$("#reimbursement").hide();	/* 报账 */
			$("#auditingReimbursement").hide();
			$("#delete").hide();		/* 删除 */
			$("#recover").hide();		/* 恢复 */
		}else if(status=="结算中"){
			$("#editTour").hide();		/* 修改 */
			$("#auditing").hide();		/* 提交 */
			$("#unAuditing").hide();	/* 退回 */
			$("#finance").hide();		/* 报送 */
			$("#lend").hide();			/* 借款 */
			$("#pay").hide();			/* 付款 */
			$("#chanageCost").hide();	/* 变更 */
			$("#loanInvoice").show();	/* 借票 */
			$("#refund").hide();		/* 退款 */
			$("#balance").hide();		/* 结算 */
			$("#unBalance").show();		/* 取消结算 */
			$("#reimbursement").show();	/* 报账 */
			$("#auditingReimbursement").show();
			$("#delete").hide();		/* 删除 */
			$("#recover").hide();		/* 恢复 */
		}else if(status=="已报账"){
			$("#editTour").hide();		/* 修改 */
			$("#auditing").hide();		/* 提交 */
			$("#unAuditing").hide();	/* 退回 */
			$("#finance").hide();		/* 报送 */
			$("#lend").hide();			/* 借款 */
			$("#pay").hide();			/* 付款 */
			$("#chanageCost").hide();	/* 变更 */
			$("#loanInvoice").show();	/* 借票 */
			$("#refund").hide();		/* 退款 */
			$("#balance").hide();		/* 结算 */
			$("#unBalance").hide();		/* 取消结算 */
			$("#reimbursement").hide();	/* 报账 */
			$("#auditingReimbursement").hide();
			$("#delete").hide();		/* 删除 */
			$("#recover").hide();		/* 恢复 */
		}else if(status=="待核销"){
			$("#editTour").hide();		/* 修改 */
			$("#auditing").hide();		/* 提交 */
			$("#unAuditing").hide();	/* 退回 */
			$("#finance").hide();		/* 报送 */
			$("#lend").hide();			/* 借款 */
			$("#pay").hide();			/* 付款 */
			$("#chanageCost").hide();	/* 变更 */
			$("#loanInvoice").hide();	/* 借票 */
			$("#refund").hide();		/* 退款 */
			$("#balance").hide();		/* 结算 */
			$("#unBalance").hide();		/* 取消结算 */
			$("#reimbursement").hide();	/* 报账 */
			$("#auditingReimbursement").hide();
			$("#delete").hide();		/* 删除 */
			$("#recover").hide();		/* 恢复 */
		}else if(status=="已核销"){
			$("#editTour").hide();		/* 修改 */
			$("#auditing").hide();		/* 提交 */
			$("#unAuditing").hide();	/* 退回 */
			$("#finance").hide();		/* 报送 */
			$("#lend").hide();			/* 借款 */
			$("#pay").hide();			/* 付款 */
			$("#chanageCost").hide();	/* 变更 */
			$("#loanInvoice").show();	/* 借票 */
			$("#refund").hide();		/* 退款 */
			$("#balance").hide();		/* 结算 */
			$("#unBalance").hide();		/* 取消结算 */
			$("#reimbursement").hide();	/* 报账 */
			$("#auditingReimbursement").hide();
			$("#delete").hide();		/* 删除 */
			$("#recover").hide();		/* 恢复 */
		}else if(status=="已结算"){
			$("#editTour").hide();		/* 修改 */
			$("#auditing").hide();		/* 提交 */
			$("#unAuditing").hide();	/* 退回 */
			$("#finance").hide();		/* 报送 */
			$("#lend").hide();			/* 借款 */
			$("#pay").hide();			/* 付款 */
			$("#chanageCost").hide();	/* 变更 */
			$("#loanInvoice").hide();	/* 借票 */
			$("#refund").hide();		/* 退款 */
			$("#balance").hide();		/* 结算 */
			$("#unBalance").hide();		/* 取消结算 */
			$("#reimbursement").hide();	/* 报账 */
			$("#auditingReimbursement").hide();
			$("#delete").hide();		/* 删除 */
			$("#recover").hide();		/* 恢复 */
		}else if(status=="已取消"){
			$("#editTour").hide();		/* 修改 */
			$("#auditing").hide();		/* 提交 */
			$("#unAuditing").hide();	/* 退回 */
			$("#finance").hide();		/* 报送 */
			$("#lend").hide();			/* 借款 */
			$("#pay").hide();			/* 付款 */
			$("#chanageCost").hide();	/* 变更 */
			$("#loanInvoice").hide();	/* 借票 */
			$("#refund").hide();		/* 退款 */
			$("#balance").hide();		/* 结算 */
			$("#unBalance").hide();		/* 取消结算 */
			$("#reimbursement").hide();	/* 报账 */
			$("#auditingReimbursement").hide();
			$("#delete").hide();		/* 删除 */
			$("#recover").show();		/* 恢复 */
		}
	}
	
	/* 全选 */
	$(".selectAll").click(function(){
		if($(this).prop("checked")){
			$("#table").find("input").prop("checked",true);
		}else{
			$("#table").find("input").prop("checked",false);
		}
	});
	/* 人数计算 */
	$(".counts").blur(function(){
		var sum = 0;
		var counts = $(this).parents("tbody").find(".counts");
		counts.each(function(){
			if(isNaN(parseInt($(this).val()))){
			}else{
				sum = sum + parseInt($(this).val());
			}
		});
		counts.last().parent().next().next().text(sum);
	});
	/* 导游空闲查询 */
	$("#create").delegate("#guide","click",function(){
		var td = $(this).parents("td");
		td.html("");
		td.html('<select style="display: none;" multiple="multiple" class="width-20 chosen-select" id="guides" data-placeholder="可选多个...">'+
				'<option value="">&nbsp;</option>'+
				'</select>');
		var select = $("#create").find("select").eq(5);
		var myData = {startTime:$("#datepickerStart").val(),endTime:$("#datepickerEnd").val()};
		$.ajax({  
	        type: "GET",  
	        contentType:"application/json;charset=utf-8",  
	        url:"${path }guideTimeManage/checkTime",  
	        data:myData,  
	        dataType: "json",  
	        async: false,  
	        success:function(data){
        		$.each(data,function(){
	        		select.append('<option value="'+this.guideTable.id+'" name="'+this.userTable.id+'">'+this.userTable.realName+'</option>');
	        	});
	        },
	        error:function(){
	        	alert("团期选择错误，无法匹配导游");
	        }
		});
		select.chosen({no_results_text: "查无结果", search_contains: true});
		select.next().attr("style","width:100%;");
		select.next().find("input").attr("style","height:100%;");
		td.find("input").focus().select();
	});
	
	$("#datepickerStart").click(function(){
		$("#create").find("#guideTd").html('<input id="guide" type="text" class="form-control" placeholder="可选多个" style="width:100%;">');
	});
	$("#datepickerEnd").click(function(){
		$("#create").find("#guideTd").html('<input id="guide" type="text" class="form-control" placeholder="可选多个" style="width:100%;">');
	});
/* 抵离信息*/
	/* 增加 */
	$(".addArrDep").click(function(){
		var arr = $(this).parent().parent().parent().find(".arrInfo");
		var dep = $(this).parent().parent().parent().find(".departInfo");
		arr.append("<tr>"+$("#arrModel").html()+"</tr>");
		dep.append("<tr>"+$("#departModel").html()+"</tr>");
		arr.find("tr:last").find("select").chosen({no_results_text: "查无结果", search_contains: true});
		dep.find("tr:last").find("select").chosen({no_results_text: "查无结果", search_contains: true});
		arr.find("tr:last").find("#arrTime").attr("id","").datepicker({
			showOtherMonths: true,
			selectOtherMonths: false,
		});
		dep.find("tr:last").find("#departTime").attr("id","").datepicker({
			showOtherMonths: true,
			selectOtherMonths: false,
		});
	});
	/* 删除一行 */
	$("table").delegate(".delLine","click",function(){
		$(this).parents("tr").remove();
	});
/* 行程初始化 */
	var tripModel = $("#tripModel").html();
	$(".trips").click(function(){
		var startTime = new Date($(this).parents("div").next().find("#datepickerStart").val());
		var endTime = new Date($(this).parents("div").next().find("#datepickerEnd").val());
		var days = (endTime-startTime)/1000/60/60/24+1;
		var ul = $(this).parent().parent().parent().next().find("#myTab3");
		var div = ul.parent().children("div");
		ul.html("");
		div.html("");
		for (var int = 0; int < days; int++) {
			var date = new Date(startTime.getTime()+1000*60*60*24*int);
			if(int==0){
				ul.append('<li class="active">'+
							'<a data-toggle="tab" href="#day'+int+'">'+
								date.getFullYear()+'/'+(date.getMonth()+1)+'/'+date.getDate()+
							'</a>'+
					  	  '</li>');
				div.append('<div id="day'+int+'" class="tab-pane in active">'+tripModel+'</div>');
			}else{
				ul.append('<li>'+
						'<a data-toggle="tab" href="#day'+int+'">'+
							date.getFullYear()+'/'+(date.getMonth()+1)+'/'+date.getDate()+
						'</a>'+
				  	  '</li>');
				div.append('<div id="day'+int+'" class="tab-pane">'+tripModel+'</div>');
			}
		}
	});
/* 成本 */
	/* 成本小计 */
	$(".costTable").delegate(".costPlus","blur",function(){
		var product = 1;
		var costPlus = $(this).parents("tr").find(".costPlus");
		costPlus.each(function(){
			if(isNaN(parseFloat($(this).val()))){
				product = product * 0;
			}else{
				product = product * parseFloat($(this).val());
			}
		})
		costPlus.last().parent().next().text(product.toFixed(2));
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
		var guides = $(this).parents(".costTable").prev().find("#guides");
		var names = guides.find("option:selected");
		if(guides.val()!=undefined){
			for (var int = 0; int < guides.val().length; int++) {
				selects.eq(2).append('<option value="'+names.eq(int).attr("name")+'">'+names.eq(int).text()+'</option>');
			} 
		}
		selects.eq(2).append('<option value="'+'<%=user.getId()%>'+'">'+'<%=user.getRealName()%>'+'</option>');
		selects.chosen({no_results_text: "查无结果", search_contains: true});
		selects.next().attr("style","width:100%;");
	});
/* 收入 */
	$(".addIncome").click(function(){
		var tbody = $(this).parents("table").children("tbody");
		tbody.append("<tr>"+$("#incomeModel").html()+"</tr>");
		var tr = tbody.children("tr").not("#incomeModel").last();
		tr.find("#incomeTime").attr("id","").datepicker({
			showOtherMonths: true,
			selectOtherMonths: false,
		});
		/* tr.children("td").eq(1).text($(this).parents(".tab-pane").siblings().first().find("#customer").find("option:selected").text()); */
		/* var select = tr.children("td").eq(1).children("select");
		select.val($(this).parents(".tab-pane").siblings().first().find("#customer").val());
		select.chosen({no_results_text: "查无结果", search_contains: true});
		select.next().attr("style","width:100%;");
		select.next().find("input").attr("style","height:100%;"); */
		if(tr.prev().not("#incomeModel").html()==undefined){
			if($(this).parents(".tab-pane").siblings().first().find("#customer").html()==undefined){
				$.ajax({
			        type: "GET",  
			        contentType:"application/json;charset=utf-8",  
			        url:"${path }localTourManage/findCustomer",  
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
			tr.children("td").eq(1).html(tr.prev().not("#incomeModel").children("td").eq(1).html());
		}
		
	});
/* 删除 */
	$("#delete").click(function(){
 		var checkbox = $("#table").find("input:checked");
		if(checkbox.length==0){
			alert("请选择一个团队");
			$(this).attr("href","#");
		}else if(checkbox.length>1){
			alert("只能选择一个团队");
			$(this).attr("href","#");
		}else{
			var tourId = checkbox.parent().parent().parent().attr("id");
			var myData = {id:tourId};
			$.ajax({
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"${path }localTourManage/del",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	if(data){
			        	checkbox.parent().parent().parent().find("td").eq(8).text("已取消");
			        	changeButton("已取消");
		        	}else{
		        		alert("操作失败");
		        	}
		        }
			});
		}
 	});
/* 恢复 */
	$("#recover").click(function(){
 		var checkbox = $("#table").find("input:checked");
		if(checkbox.length==0){
			alert("请选择一个团队");
			$(this).attr("href","#");
		}else if(checkbox.length>1){
			alert("只能选择一个团队");
			$(this).attr("href","#");
		}else{
			var tourId = checkbox.parent().parent().parent().attr("id");
			var myData = {id:tourId};
			$.ajax({
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"${path }localTourManage/recover",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	if(data){
			        	checkbox.parent().parent().parent().find("td").eq(8).text("新建");
			        	changeButton("新建");
		        	}else{
		        		alert("操作失败");
		        	}
		        }
			});
		}
 	});
	
/* 提交审核 */
 	$("#auditing").click(function(){
 		var checkbox = $("#table").find("input:checked");
		if(checkbox.length==0){
			alert("请选择一个团队");
			$(this).attr("href","#");
		}else if(checkbox.length>1){
			alert("只能选择一个团队");
			$(this).attr("href","#");
		}else{
			var tourId = checkbox.parent().parent().parent().attr("id");
			var myData = {id:tourId};
			$.ajax({
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"${path }localTourManage/auditing",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	if(data){
			        	checkbox.parent().parent().parent().find("td").eq(8).text("已提交");
			        	changeButton("已提交");
		        	}else{
		        		alert("操作失败");
		        	}
		        }
			});
		}
 	});
 	
 /*退回编辑 */
 	$("#unAuditing").click(function(){
 		var checkbox = $("#table").find("input:checked");
		if(checkbox.length==0){
			alert("请选择一个团队");
			$(this).attr("href","#");
		}else if(checkbox.length>1){
			alert("只能选择一个团队");
			$(this).attr("href","#");
		}else{
			var tourId = checkbox.parent().parent().parent().attr("id");
			var myData = {id:tourId};
			$.ajax({
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"${path }localTourManage/unAuditing",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	if(data){
			        	checkbox.parent().parent().parent().find("td").eq(8).text("新建");
			        	changeButton("新建");
		        	}else{
		        		alert("操作失败");
		        	}
		        }
			});
		}
 	});
/*报送财务 */
 	$("#finance").click(function(){
 		var checkbox = $("#table").find("input:checked");
		if(checkbox.length==0){
			alert("请选择一个团队");
			$(this).attr("href","#");
		}else if(checkbox.length>1){
			alert("只能选择一个团队");
			$(this).attr("href","#");
		}else{
			var tourId = checkbox.parent().parent().parent().attr("id");
			var myData = {id:tourId};
			$.ajax({
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"${path }localTourManage/finance",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	if(data){
			        	checkbox.parent().parent().parent().find("td").eq(8).text("已报送");
			        	changeButton("已报送");
		        	}else{
		        		alert("操作失败");
		        	}
		        }
			});
		}
 	});

/* 保存 */
	$("#saveNew").click(function(){
		var inputs = $("#create").find("#tourInfo").find("input");
		var selects = $("#create").find("#tourInfo").find("select");
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
		var userId = '<%=user.getId()%>';
		var deptId = '<%=user.getDeptId()%>';
		var status = 0;
		var enable = true;
		var days = (endTime-startTime)/1000/60/60/24+1;
		var localTourTable={tourNo:tourNo,tourName:tourName,businessTypeId:businessTypeId,tourTypeId:tourTypeId,regionId:regionId,visitorTypeId:visitorTypeId,
					   customerAgencyId:customerAgencyId,organizor:organizor,qpGuideNo:qpGuideNo,adultNo:adultNo,childrenNo:childrenNo,startTime:startTime,
					   endTime:endTime,remark:remark,userId:userId,deptId:deptId,status:status,enable:enable};
		var guideTimeTables = new Array();
		if(guideIds!=null){
			for (var int = 0; int < guideIds.length; int++) {
				guideTimeTables.push({
						guideId:guideIds[int],
						startTime:startTime,
						endTime:endTime});
			}
		}
		var arrTrs = $("#create").find(".arrInfo").children("tr").not("#arrModel");
		var arrTables = new Array();
		for (var int = 0; int < arrTrs.length; int++) {
			var arrSelects = arrTrs.eq(int).find("select");
			var arrInputs = arrTrs.eq(int).find("input");
			if(arrSelects.eq(0).val()!=""||arrSelects.eq(1).children("option:selected").html()!="&nbsp;"||arrInputs.eq(2).val()!=""||arrInputs.eq(3).val()!=""||arrSelects.eq(2).val()!=""){
				arrTables.push({
						originId:arrSelects.eq(0).val(),
						arrTraffic:arrSelects.eq(1).children("option:selected").text(),
						arrTime:new Date(arrInputs.eq(2).val()),
						arrTrafficNo:arrInputs.eq(3).val(),
						arrRegionId:arrSelects.eq(2).val()});	
				}
			}
		
		var departTrs = $("#create").find(".departInfo").children("tr").not("#departModel");
		var departTables = new Array();
		for (var int = 0; int < departTrs.length; int++) {
			var departSelects = departTrs.eq(int).find("select");
			var departInputs = departTrs.eq(int).find("input");
			if(departSelects.eq(0).val()!=""||departSelects.eq(1).children("option:selected").html()!="&nbsp;"||departInputs.eq(2).val()!=""||departInputs.eq(3).val()!=""||departSelects.eq(2).val()!=""){
				departTables.push(
						{destId:departSelects.eq(0).val(),
						departTraffic:departSelects.eq(1).children("option:selected").text(),
						departTime:new Date(departInputs.eq(2).val()),
						departTrafficNo:departInputs.eq(3).val(),
						departRegionId:departSelects.eq(2).val()});
			}
		}
		
		var tripTables = new Array();
		var tripsTbody = $("#create").find("#trips").find("div").not("#tripModel").children("table").children("tbody");
		for (var int = 0; int < tripsTbody.length; int++) {
			var tripInputs =  tripsTbody.eq(int).find("input");
			var tripTextAreas =  tripsTbody.eq(int).find("textarea");
			if(tripTextAreas.eq(0).val()!=""||tripInputs.eq(0).val()!=""||tripInputs.eq(1).val()!=""||tripInputs.eq(2).val()!=""||tripTextAreas.eq(1).val()!=""){
				tripTables.push({
					number:int,
					trip:tripTextAreas.eq(0).val(),
					meal:tripInputs.eq(0).val(),
					stay:tripInputs.eq(1).val(),
					traffic:tripInputs.eq(2).val(),
					remark:tripTextAreas.eq(1).val()});
			}
		}
		
		var costTables = new Array();
		var costTrs = $("#create").find("#costs").find("tbody").find("tr").not("#costModel");
		for (var int = 0; int < costTrs.length; int++) {
			var costInputs = costTrs.eq(int).find("input");
			var costSelects = costTrs.eq(int).find("select");
			costTables.push({costDate:new Date(costInputs.eq(0).val()),
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
		var incomeTrs = $("#create").find("#incomes").find("tbody").find("tr").not("#incomeModel");
		for (var int = 0; int < incomeTrs.length; int++) {
			var incomeInputs = incomeTrs.eq(int).find("input");
			incomeTables.push({
				incomeDate:new Date(incomeInputs.eq(0).val()),
				customerAgencyId:incomeInputs.eq(1).val(),
				income:incomeInputs.eq(2).val(),
				remark:incomeInputs.eq(3).val()});
		}
		
		var fullLocalTourViewModel = {localTourTable:localTourTable,guideTimeTables:guideTimeTables,arrTables:arrTables,departTables:departTables,tripTables:tripTables,costTables:costTables,incomeTables:incomeTables};
		var myData = JSON.stringify(fullLocalTourViewModel);
		$.ajax({
	        type: "POST",  
	        contentType:"application/json;charset=utf-8",  
	        url:"${path }localTourManage/save",  
	        data:myData,  
	        dataType: "json",  
	        async: false,  
	        success:function(data){
	        	if(data>0){
	        		$("#saveNew").attr("data-dismiss","modal");
	        		var tr = $('<tr id="'+data+'">'+
			        				'<td class="center  sorting_1">'+
									'<label>'+
										'<input class="ace" type="checkbox">'+
										'<span class="lbl"></span>'+
									'</label>'+
									'</td>'+
									'<td><a id="findTour" role="button" data-toggle="modal" href="#find">'+tourNo+'</a></td>'+
									'<td>'+tourName+'</td>'+
									'<td>'+adultNo+'</td>'+
									'<td>'+childrenNo+'</td>'+
									'<td>'+days+'</td>'+
									'<td>'+startTime.getFullYear()+'-'+startTime.getMonth()+'-'+startTime.getDate()+'</td>'+
									'<td>'+endTime.getFullYear()+'-'+endTime.getMonth()+'-'+endTime.getDate()+'</td>'+
									'<td>新建</td>'+
									'<td>'+'<%=user.getRealName()%>'+'</td>'+
								'</tr>');
	        		/* tr.click(function(){
	        			var checkbox = $(this).find("input");
	        			if(checkbox.prop("checked")){
	        				checkbox.prop("checked",false);
	        			}else{
	        				$("#table").find("input").prop("checked",false);
	        				checkbox.prop("checked",true);
	        			}
	        		}); */
	        		$("#table").prepend(tr);
	        	}else{
	        		$("#saveNew").attr("data-dismiss","");
	        		alert("请检查团号是否重复，*号为必填项");
	        	}
	        }  
		 });
	});
/* 查看 */
	$("#table").delegate("#findTour","click",function(){
		if($(this).parent().prev().find("input").prop("checked")){
			$(this).parent().prev().find("input").prop("checked",false);
		}else{
			$(this).parent().prev().find("input").prop("checked",true);
		}
		var myData = {tourId:$(this).parent().parent().attr("id")};
		$.ajax({
	        type: "GET",  
	        contentType:"application/json;charset=utf-8",  
	        url:"${path }localTourManage/find",  
	        data:myData,  
	        dataType: "json",  
	        async: false,  
	        success:function(data){
	        	check(data);
	        }  
		});
	});
/* 编辑 */
	/* 点击日期删除导游 */
	/* $("#edit").find(".datepicker").click(function(){
		$("#edit").find("#guideTd").html('<input id="guide" type="text" class="form-control" placeholder="可选多个" style="width:100%;">');
		var myData = {tourId:$("#saveEdit").parent().attr("id")};
		$.ajax({
	        type: "GET",
	        contentType:"application/json;charset=utf-8",  
	        url:"${path }guideTimeManage/delete",  
	        data:myData,  
	        dataType: "json",
	        async: false,  
	        success:function(data){
        		
	        }
		});
	}); */
	/* 选择导游 */
	$("#edit").delegate("#guide","click",function(){
		var td = $(this).parents("td");
		td.html("");
		td.html('<select style="display: none;" multiple="multiple" class="width-20 chosen-select" id="guides" data-placeholder="可选多个...">'+
					'<option value="">&nbsp;</option>'+
				'</select>');
		var select = $("#edit").find("select").eq(5);
		var myData = {startTime:td.siblings().eq(1).children("input").val(),endTime:td.siblings().eq(3).children("input").val()};
		$.ajax({
	        type: "GET",
	        contentType:"application/json;charset=utf-8",  
	        url:"${path }guideTimeManage/checkTime",  
	        data:myData,  
	        dataType: "json",  
	        async: false,  
	        success:function(data){
        		$.each(data,function(){
	        		select.append('<option value="'+this.guideTable.id+'" name="'+this.userTable.id+'">'+this.userTable.realName+'</option>');
	        	});
	        },
	        error:function(){
	        	alert("团期选择错误，无法匹配导游");
	        }
		});
		select.chosen({no_results_text: "查无结果", search_contains: true});
		select.next().attr("style","width:100%;");
		select.next().find("input").attr("style","height:100%;");
		td.find("input").focus().select();
	});
	/* 编辑读取 */
		$("#editTour").click(function(){
			var checkbox = $("#table").find("input:checked");
			if(checkbox.length==0){
				alert("请选择一个团队");
				$(this).attr("href","#");
			}else if(checkbox.length>1){
				alert("只能选择一个团队");
				$(this).attr("href","#");
			}else{
				/* 初始化选项 */
				if(inited==false){
					init();
					inited = true;
				}
				$(this).attr("href","#edit");
				var tourId = checkbox.parent().parent().parent().attr("id");
				var myData = {tourId:tourId};
				$("#saveEdit").parent().attr("id",tourId);
				var tourUserName = $("#table").find("#"+tourId).children("td").last().text();
				$.ajax({
			        type: "GET",  
			        contentType:"application/json;charset=utf-8",  
			        url:"${path }localTourManage/find",  
			        data:myData,  
			        dataType: "json",  
			        async: false,  
			        success:function(data){
			        	/* 全局数据 */
			        	editInfo = data;
			        	/* 设置团队 */
			        	var selects = $("#edit").find("select");
			        	var td = $("#edit").find(".tourInfo");
			        	td.eq(0).children("input").val(data.localTourTable.tourNo);
			        	td.eq(1).children("input").val(data.localTourTable.tourName);
			        	
			        	var businessType = $("<select></select>");
			        	businessType.html(td.eq(2).children("select").html());
			        	businessType.attr({"style":"display: none;","class":"width-20 chosen-select form-control visitorType","data-placeholder":"Choose a Country..."});
			        	td.eq(2).html("").append(businessType.val(data.localTourTable.businessTypeId));
			        	
			        	var tourType = $("<select></select>");
			        	tourType.html(td.eq(3).children("select").html());
			        	tourType.attr({"style":"display: none;","class":"width-20 chosen-select form-control visitorType","data-placeholder":"Choose a Country..."});
			        	td.eq(3).html("").append(tourType.val(data.localTourTable.tourTypeId));
			        	
			        	var region = $("<select></select>");
			        	region.html(td.eq(4).children("select").html());
			        	region.attr({"style":"display: none;","class":"width-20 chosen-select form-control visitorType","data-placeholder":"Choose a Country..."});
			        	td.eq(4).html("").append(region.val(data.localTourTable.regionId));
			        	
			        	var visitorType = $("<select></select>");
			        	visitorType.html(td.eq(5).children("select").html());
			        	visitorType.attr({"style":"display: none;","class":"width-20 chosen-select form-control visitorType","data-placeholder":"Choose a Country..."});
			        	td.eq(5).html("").append(visitorType.val(data.localTourTable.visitorTypeId));
			        	
			        	var customerAgency = $("<select></select>");
			        	customerAgency.html(td.eq(6).children("select").html());
			        	customerAgency.attr({"id":"customer","style":"display: none;","class":"width-20 chosen-select form-control visitorType","data-placeholder":"Choose a Country..."});
			        	td.eq(6).html("").append(customerAgency.val(data.localTourTable.customerAgencyId));
			        	
			        	td.eq(7).children("input").val(data.localTourTable.organizor);
			        	td.eq(8).children("input").val(data.localTourTable.qpGuideNo);
			        	td.eq(9).children("input").val(data.localTourTable.adultNo);
			        	td.eq(10).children("input").val(data.localTourTable.childrenNo);
			        	td.eq(11).text(data.localTourTable.qpGuideNo+data.localTourTable.adultNo+data.localTourTable.childrenNo);
			        	td.eq(12).children("input").val((data.localTourTable.startTime).replace(/-/g,"/"));
			        	td.eq(13).children("input").val((data.localTourTable.endTime).replace(/-/g,"/"));
			        	td.eq(15).children("input").val(data.localTourTable.remark);
			        	/* 设置导游 */
			        	var select = td.eq(14).children("select");
						var myData = {startTime:(data.localTourTable.startTime).replace(/-/g,"/"),endTime:(data.localTourTable.endTime).replace(/-/g,"/")};
						select.empty();
						$.ajax({
					        type: "GET",  
					        contentType:"application/json;charset=utf-8",  
					        url:"${path }guideTimeManage/checkTime",  
					        data:myData,  
					        dataType: "json",  
					        async: false,  
					        success:function(guides){
					        	var guideIds = new Array();
								$.each(data.guideTimes,function(index){
									select.append('<option value="'+this.guideId+'" name="'+this.userId+'">'+this.realName+'</option>');
									guideIds[index] = this.guideId;
					        	});
				        		$.each(guides,function(){
					        		select.append('<option value="'+this.guideTable.id+'" name="'+this.userTable.id+'">'+this.userTable.realName+'</option>');
					        	});
				        		select.val(guideIds);
					        },
					        error:function(){
					        	alert("团期选择错误，无法匹配导游");
					        }
						});
						/* 设置抵离信息 */
			        	var arrInfo = $("#edit").find(".arrInfo");
			        	arrInfo.html("");
			        	$.each(data.arrs,function(index){
			        		var tr = $("<tr></tr>");
			        		tr.html($("#arrModel").html());
			        		var selects = tr.find("select");
			        		var input = tr.find("input");
			        		selects.eq(0).val(this.arrTable.originId);
			        		selects.eq(1).val(this.arrTable.arrTraffic);
			        		input.eq(0).val(this.arrTable.arrTime.replace(/-/g,"/"));
			        		input.eq(1).val(this.arrTable.arrTrafficNo);
			        		selects.eq(2).val(this.arrTable.arrRegionId);
			        		tr.children("td").last().attr("id",this.arrTable.id);
			        		arrInfo.append(tr);
			        		$("#edit").find("#arrTime").attr("id","");
			        	});
			        	
			        	var departInfo = $("#edit").find(".departInfo");
			        	departInfo.html("");
			        	$.each(data.departs,function(index){
			        		var tr = $("<tr></tr>");
			        		tr.html($("#departModel").html());
			        		var selects = tr.find("select");
			        		var input = tr.find("input");
			        		selects.eq(0).val(this.departTable.destId);
			        		selects.eq(1).val(this.departTable.departTraffic);
			        		input.eq(0).val(this.departTable.departTime.replace(/-/g,"/"));
			        		input.eq(1).val(this.departTable.departTrafficNo);
			        		selects.eq(2).val(this.departTable.departRegionId);
			        		tr.children("td").last().attr("id",this.departTable.id);
			        		departInfo.append(tr);
			        		$("#edit").find("#departTime").attr("id","");
			        	});
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
			        		var guides = $("#edit").find("#guides");
		    				var names = guides.find("option:selected");
		    				var borrowUser = $('<select style="width:100%;" class="width-20 chosen-select"><option value="">&nbsp;</option></select>');
		    				if(guides.val()!=undefined){
		    					for (var int = 0; int < guides.val().length; int++) {
		    						borrowUser.append('<option value="'+names.eq(int).attr("name")+'">'+names.eq(int).text()+'</option>');
		    					} 
		    				}
		    				borrowUser.append('<option value="'+data.localTourTable.userId+'">'+tourUserName+'</option>');
		    				if(this.costTable.costDate==null){
		    					this.costTable.costDate="";
		    				}
			        		if(this.costTable.supplierScopeId==1){
			        			flight.append('<tr>'+
				    								'<td><input value="'+this.costTable.costDate.replace(/-/g,'/')+'" style="width:100%;" class="datepicker form-control" type="text" /></td>'+
				    								'<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>'+
				    								'<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>'+
				    								'<td><input value="'+this.costTable.cost+'" style="width:100%;" class="form-control" type="text" /></td>'+
				    								'<td><input value="'+this.costTable.count+'" style="width:100%;" class="form-control" type="text" /></td>'+
				    								'<td><input value="'+this.costTable.days+'" style="width:100%;" class="form-control" type="text" /></td>'+
				    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
				    								'<td></td>'+
				    								'<td><input value="'+this.costTable.remark+'" style="width:100%;" class="form-control" type="text" /></td>'+
				    								'<td id="'+this.costTable.id+'"style="vertical-align: middle;"><a class="red delLine" href="#"><i class="icon-trash bigger-130"></i></a><input type="hidden" value="1" /></td>'+
				    					'</tr>');
				    			flight.find("tr").last().children("td").eq(7).append(borrowUser);
								var selects = flight.find("tr").last().find("select");
								$.each(selectInfo.flightContents,function(){
					        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
					        	});
					        	$.each(selectInfo.flightSuppliers,function(){
					        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
					        	});
					        	selects.eq(0).val(this.costTable.contentId);
					        	selects.eq(1).val(this.costTable.supplierId);
					        	selects.eq(2).val(this.costTable.borrowUserId);
			        		}else if(this.costTable.supplierScopeId==2){
			        			hotel.append('<tr>'+
				    								'<td><input value="'+this.costTable.costDate.replace(/-/g,'/')+'" style="width:100%;" class="datepicker form-control" type="text" /></td>'+
				    								'<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>'+
				    								'<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>'+
				    								'<td><input value="'+this.costTable.cost+'" style="width:100%;" class="form-control" type="text" /></td>'+
				    								'<td><input value="'+this.costTable.count+'" style="width:100%;" class="form-control" type="text" /></td>'+
				    								'<td><input value="'+this.costTable.days+'" style="width:100%;" class="form-control" type="text" /></td>'+
				    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
				    								'<td></td>'+
				    								'<td><input value="'+this.costTable.remark+'" style="width:100%;" class="form-control" type="text" /></td>'+
				    								'<td id="'+this.costTable.id+'"style="vertical-align: middle;"><a class="red delLine" href="#"><i class="icon-trash bigger-130"></i></a><input type="hidden" value="2" /></td>'+
				    					'</tr>');
			        			hotel.find("tr").last().children("td").eq(7).append(borrowUser);
								var selects = hotel.find("tr").last().find("select");
								$.each(selectInfo.hotelContents,function(){
			    	        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
			    	        	});
			    	        	$.each(selectInfo.hotelSuppliers,function(){
			    	        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
			    	        	});
			    	        	selects.eq(0).val(this.costTable.contentId);
			    	        	selects.eq(1).val(this.costTable.supplierId);
			    	        	selects.eq(2).val(this.costTable.borrowUserId);
			        		}else if(this.costTable.supplierScopeId==3){
			        			meal.append('<tr>'+
				    								'<td><input value="'+this.costTable.costDate.replace(/-/g,'/')+'" style="width:100%;" class="datepicker form-control" type="text" /></td>'+
				    								'<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>'+
				    								'<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>'+
				    								'<td><input value="'+this.costTable.cost+'" style="width:100%;" class="form-control" type="text" /></td>'+
				    								'<td><input value="'+this.costTable.count+'" style="width:100%;" class="form-control" type="text" /></td>'+
				    								'<td><input value="'+this.costTable.days+'" style="width:100%;" class="form-control" type="text" /></td>'+
				    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
				    								'<td></td>'+
				    								'<td><input value="'+this.costTable.remark+'" style="width:100%;" class="form-control" type="text" /></td>'+
				    								'<td id="'+this.costTable.id+'"style="vertical-align: middle;"><a class="red delLine" href="#"><i class="icon-trash bigger-130"></i></a><input type="hidden" value="3" /></td>'+
				    					'</tr>');
				    			meal.find("tr").last().children("td").eq(7).append(borrowUser);
								var selects = meal.find("tr").last().find("select");
								$.each(selectInfo.mealContents,function(){
					        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
					        	});
					        	$.each(selectInfo.mealSuppliers,function(){
					        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
					        	});
					        	selects.eq(0).val(this.costTable.contentId);
					        	selects.eq(1).val(this.costTable.supplierId);
					        	selects.eq(2).val(this.costTable.borrowUserId);
			        		}else if(this.costTable.supplierScopeId==4){
			        			ticket.append('<tr>'+
				    								'<td><input value="'+this.costTable.costDate.replace(/-/g,'/')+'" style="width:100%;" class="datepicker form-control" type="text" /></td>'+
				    								'<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>'+
				    								'<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>'+
				    								'<td><input value="'+this.costTable.cost+'" style="width:100%;" class="form-control" type="text" /></td>'+
				    								'<td><input value="'+this.costTable.count+'" style="width:100%;" class="form-control" type="text" /></td>'+
				    								'<td><input value="'+this.costTable.days+'" style="width:100%;" class="form-control" type="text" /></td>'+
				    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
				    								'<td></td>'+
				    								'<td><input value="'+this.costTable.remark+'" style="width:100%;" class="form-control" type="text" /></td>'+
				    								'<td id="'+this.costTable.id+'"style="vertical-align: middle;"><a class="red delLine" href="#"><i class="icon-trash bigger-130"></i></a><input type="hidden" value="4" /></td>'+
				    					'</tr>');
				    			ticket.find("tr").last().children("td").eq(7).append(borrowUser);
								var selects = ticket.find("tr").last().find("select");
								$.each(selectInfo.ticketContents,function(){
					        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
					        	});
					        	$.each(selectInfo.ticketSuppliers,function(){
					        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
					        	});
					        	selects.eq(0).val(this.costTable.contentId);
					        	selects.eq(1).val(this.costTable.supplierId);
					        	selects.eq(2).val(this.costTable.borrowUserId);
			        		}else if(this.costTable.supplierScopeId==5){
			        			shuttle.append('<tr>'+
				    								'<td><input value="'+this.costTable.costDate.replace(/-/g,'/')+'" style="width:100%;" class="datepicker form-control" type="text" /></td>'+
				    								'<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>'+
				    								'<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>'+
				    								'<td><input value="'+this.costTable.cost+'" style="width:100%;" class="form-control" type="text" /></td>'+
				    								'<td><input value="'+this.costTable.count+'" style="width:100%;" class="form-control" type="text" /></td>'+
				    								'<td><input value="'+this.costTable.days+'" style="width:100%;" class="form-control" type="text" /></td>'+
				    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
				    								'<td></td>'+
				    								'<td><input value="'+this.costTable.remark+'" style="width:100%;" class="form-control" type="text" /></td>'+
				    								'<td id="'+this.costTable.id+'"style="vertical-align: middle;"><a class="red delLine" href="#"><i class="icon-trash bigger-130"></i></a><input type="hidden" value="5" /></td>'+
				    					'</tr>');
				    			shuttle.find("tr").last().children("td").eq(7).append(borrowUser);
								var selects = shuttle.find("tr").last().find("select");
								$.each(selectInfo.shuttleContents,function(){
					        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
					        	});
					        	$.each(selectInfo.shuttleSuppliers,function(){
					        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
					        	});
					        	selects.eq(0).val(this.costTable.contentId);
					        	selects.eq(1).val(this.costTable.supplierId);
					        	selects.eq(2).val(this.costTable.borrowUserId);
			        		}else if(this.costTable.supplierScopeId==6){
			        			tickets.append('<tr>'+
				    								'<td><input value="'+this.costTable.costDate.replace(/-/g,'/')+'" style="width:100%;" class="datepicker form-control" type="text" /></td>'+
				    								'<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>'+
				    								'<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>'+
				    								'<td><input value="'+this.costTable.cost+'" style="width:100%;" class="form-control" type="text" /></td>'+
				    								'<td><input value="'+this.costTable.count+'" style="width:100%;" class="form-control" type="text" /></td>'+
				    								'<td><input value="'+this.costTable.days+'" style="width:100%;" class="form-control" type="text" /></td>'+
				    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
				    								'<td></td>'+
				    								'<td><input value="'+this.costTable.remark+'" style="width:100%;" class="form-control" type="text" /></td>'+
				    								'<td id="'+this.costTable.id+'"style="vertical-align: middle;"><a class="red delLine" href="#"><i class="icon-trash bigger-130"></i></a><input type="hidden" value="6" /></td>'+
				    					'</tr>');
				    			tickets.find("tr").last().children("td").eq(7).append(borrowUser);
								var selects = tickets.find("tr").last().find("select");
								$.each(selectInfo.ticketsContents,function(){
					        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
					        	});
					        	$.each(selectInfo.ticketsSuppliers,function(){
					        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
					        	});
					        	selects.eq(0).val(this.costTable.contentId);
					        	selects.eq(1).val(this.costTable.supplierId);
					        	selects.eq(2).val(this.costTable.borrowUserId);
			        		}else if(this.costTable.supplierScopeId==7){
			        			comprehensive.append('<tr>'+
				    								'<td><input value="'+this.costTable.costDate.replace(/-/g,'/')+'" style="width:100%;" class="datepicker form-control" type="text" /></td>'+
				    								'<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>'+
				    								'<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>'+
				    								'<td><input value="'+this.costTable.cost+'" style="width:100%;" class="form-control" type="text" /></td>'+
				    								'<td><input value="'+this.costTable.count+'" style="width:100%;" class="form-control" type="text" /></td>'+
				    								'<td><input value="'+this.costTable.days+'" style="width:100%;" class="form-control" type="text" /></td>'+
				    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
				    								'<td></td>'+
				    								'<td><input value="'+this.costTable.remark+'" style="width:100%;" class="form-control" type="text" /></td>'+
				    								'<td id="'+this.costTable.id+'"style="vertical-align: middle;"><a class="red delLine" href="#"><i class="icon-trash bigger-130"></i></a><input type="hidden" value="7" /></td>'+
				    					'</tr>');
				    			comprehensive.find("tr").last().children("td").eq(7).append(borrowUser);
								var selects = comprehensive.find("tr").last().find("select");
								$.each(selectInfo.comprehensiveContents,function(){
					        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
					        	});
					        	$.each(selectInfo.comprehensiveSuppliers,function(){
					        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
					        	});
					        	selects.eq(0).val(this.costTable.contentId);
					        	selects.eq(1).val(this.costTable.supplierId);
					        	selects.eq(2).val(this.costTable.borrowUserId);
			        		}else if(this.costTable.supplierScopeId==8){
			        			other.append('<tr>'+
				    								'<td><input value="'+this.costTable.costDate.replace(/-/g,'/')+'" style="width:100%;" class="datepicker form-control" type="text" /></td>'+
				    								'<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>'+
				    								'<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>'+
				    								'<td><input value="'+this.costTable.cost+'" style="width:100%;" class="form-control" type="text" /></td>'+
				    								'<td><input value="'+this.costTable.count+'" style="width:100%;" class="form-control" type="text" /></td>'+
				    								'<td><input value="'+this.costTable.days+'" style="width:100%;" class="form-control" type="text" /></td>'+
				    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
				    								'<td></td>'+
				    								'<td><input value="'+this.costTable.remark+'" style="width:100%;" class="form-control" type="text" /></td>'+
				    								'<td id="'+this.costTable.id+'"style="vertical-align: middle;"><a class="red delLine" href="#"><i class="icon-trash bigger-130"></i></a><input type="hidden" value="8" /></td>'+
				    					'</tr>');
				    			other.find("tr").last().children("td").eq(7).append(borrowUser);
								var selects = other.find("tr").last().find("select");
								$.each(selectInfo.otherContents,function(){
					        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
					        	});
					        	$.each(selectInfo.otherSuppliers,function(){
					        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
					        	});
					        	selects.eq(0).val(this.costTable.contentId);
					        	selects.eq(1).val(this.costTable.supplierId);
					        	selects.eq(2).val(this.costTable.borrowUserId);
			        		}
			        	});
			        	$("#costs3").find("tbody").removeAttr("delIds");
			        	
			        	/* 设置收入 */
			        	var tbody = $("#incomes3").find("tbody");
			        	tbody.html("");
			        	$.each(data.incomes,function(){
			        		var tr = $("<tr></tr>");
			        		tr.html($("#incomeModel").html());
			    			tbody.append(tr);
			    			var inputs = tr.find("input");
			    			var td = tr.children("td");
			    			this.incomeTable.incomeDate
			    			inputs.eq(0).val(this.incomeTable.incomeDate==null?"":this.incomeTable.incomeDate.replace(/-/g,'/'));
			    			tr.find("td").eq(1).html(this.customerAgencyName+'<input type="hidden" value="'+this.incomeTable.customerAgencyId+'" />');
			    			inputs.eq(1).val(this.incomeTable.income);
			    			td.eq(3).html(this.incomeTable.realIncome);
			    			td.eq(4).html(this.invoiceAmount);
			    			inputs.eq(2).val(this.incomeTable.remark);
			    			td.last().attr("id",this.incomeTable.id);
			    			tr.find("#incomeTime").attr("id","")
			        	});

			        	/* 点击编辑设置行程 */
			        	var startTime = new Date(data.localTourTable.startTime);
						var endTime = new Date(data.localTourTable.endTime);
						var days = (endTime-startTime)/1000/60/60/24+1;
						var ul = $("#edit").find("#myTab3");
						var div = ul.parent().children("div");
						var editTripModel = $("#editTripModel");
						ul.html("");
						tripDelIds = [];
						div.html('<div id="editTripModel" style="display:none;">'+editTripModel.html()+'</div>');
			        	for (var int = 0; int < days; int++) {
							var date = new Date(startTime.getTime()+1000*60*60*24*int);
							if(int==0){
								ul.append('<li class="active">'+
											'<a data-toggle="tab" href="#editDay'+int+'">'+
												date.getFullYear()+'/'+(date.getMonth()+1)+'/'+date.getDate()+
											'</a>'+
									  	  '</li>');
								var flag = false;
								$.each(editInfo.tripTables,function(){
									if(this.number==int){
										div.append('<div id="editDay'+this.number+'" class="tab-pane in active">'+editTripModel.html()+'</div>');
										tripDelIds.push(this.id);
										var divLast = div.children("div:last");
										divLast.find("tbody").attr("id",this.id);
										divLast.find("textarea").eq(0).text(this.trip);
										divLast.find("input").eq(0).attr("value",this.meal);
										divLast.find("input").eq(1).attr("value",this.stay);
										divLast.find("input").eq(2).attr("value",this.traffic);
										divLast.find("textarea").eq(1).text(this.remark);
						        		flag=true;
									}
								});
								if(flag==false){
									div.append('<div id="editDay'+int+'" class="tab-pane in active">'+editTripModel.html()+'</div>');
									var divLast = div.children("div:last");
									divLast.find("textarea").eq(0).text("");
									divLast.find("input").eq(0).val("");
									divLast.find("input").eq(1).val("");
									divLast.find("input").eq(2).val("");
									divLast.find("textarea").eq(1).text("");
								}
							}else{
								ul.append('<li>'+
										'<a data-toggle="tab" href="#editDay'+int+'">'+
											date.getFullYear()+'/'+(date.getMonth()+1)+'/'+date.getDate()+
										'</a>'+
								  	  '</li>');
								var flag = false;
								$.each(editInfo.tripTables,function(){
									if(this.number==int){
										div.append('<div id="editDay'+this.number+'" class="tab-pane">'+editTripModel.html()+'</div>');
										tripDelIds.push(this.id);
										var divLast = div.children("div:last");
										divLast.find("tbody").attr("id",this.id);
										divLast.find("textarea").eq(0).text(this.trip);
										divLast.find("input").eq(0).attr("value",this.meal);
										divLast.find("input").eq(1).attr("value",this.stay);
										divLast.find("input").eq(2).attr("value",this.traffic);
										divLast.find("textarea").eq(1).text(this.remark);
						        		flag=true;
									}
								});
								if(flag==false){
									div.append('<div id="editDay'+int+'" class="tab-pane">'+editTripModel.html()+'</div>');
									var divLast = div.children("div:last");
									divLast.find("textarea").eq(0).text("");
									divLast.find("input").eq(0).val("");
									divLast.find("input").eq(1).val("");
									divLast.find("input").eq(2).val("");
									divLast.find("textarea").eq(1).text("");
								}
							}
						}

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
			        	$("#edit").find("select").chosen({no_results_text: "查无结果", search_contains: true});
			        	$(".chosen-select").next().attr("style","width:100%;");
						$(".chosen-select").next().find("input").attr("style","height:100%;");
						$(".traffic").next().attr("style","width:100%;");
						$(".traffic").next().find("input").attr("style","height:100%;");
			        }  
				});
			}
	});
	/* 点击行程设置行程 */
		var editInfo;
		$("#editTrip").click(function(){
			tripInitialize();
		});
		/* 编辑点击时间设置行程 */
		$("#tourInfo3").find("tbody").eq(0).find(".datepicker").change(function(){
			tripInitialize();
		});
		function tripInitialize(){
			var startTime = new Date($("#edit").find(".datepicker").eq(0).val());
		var endTime = new Date($("#edit").find(".datepicker").eq(1).val());
		var days = (endTime-startTime)/1000/60/60/24+1;
		var ul = $("#edit").find("#myTab3");
		var div = ul.parent().children("div");
		var editTripModel = $("#editTripModel");
		ul.html("");
		div.html('<div id="editTripModel" style="display:none;">'+editTripModel.html()+'</div>');
    	for (var int = 0; int < days; int++) {
			var date = new Date(startTime.getTime()+1000*60*60*24*int);
			if(int==0){
				ul.append('<li class="active">'+
							'<a data-toggle="tab" href="#editDay'+int+'">'+
								date.getFullYear()+'/'+(date.getMonth()+1)+'/'+date.getDate()+
							'</a>'+
					  	  '</li>');
				var flag = false;
				$.each(editInfo.tripTables,function(){
					if(this.number==int){
						div.append('<div id="editDay'+this.number+'" class="tab-pane in active">'+editTripModel.html()+'</div>');
						var divLast = div.children("div:last");
						divLast.find("tbody").attr("id",this.id);
						divLast.find("textarea").eq(0).text(this.trip);
						divLast.find("input").eq(0).attr("value",this.meal);
						divLast.find("input").eq(1).attr("value",this.stay);
						divLast.find("input").eq(2).attr("value",this.traffic);
						divLast.find("textarea").eq(1).text(this.remark);
		        		flag=true;
					}
				});
				if(flag==false){
					div.append('<div id="editDay'+int+'" class="tab-pane in active">'+editTripModel.html()+'</div>');
					var divLast = div.children("div:last");
					divLast.find("textarea").eq(0).text("");
					divLast.find("input").eq(0).val("");
					divLast.find("input").eq(1).val("");
					divLast.find("input").eq(2).val("");
					divLast.find("textarea").eq(1).text("");
				}
			}else{
				ul.append('<li>'+
						'<a data-toggle="tab" href="#editDay'+int+'">'+
							date.getFullYear()+'/'+(date.getMonth()+1)+'/'+date.getDate()+
						'</a>'+
				  	  '</li>');
				var flag = false;
				$.each(editInfo.tripTables,function(){
					if(this.number==int){
						div.append('<div id="editDay'+this.number+'" class="tab-pane">'+editTripModel.html()+'</div>');
						var divLast = div.children("div:last");
						divLast.find("tbody").attr("id",this.id);
						divLast.find("textarea").eq(0).text(this.trip);
						divLast.find("input").eq(0).attr("value",this.meal);
						divLast.find("input").eq(1).attr("value",this.stay);
						divLast.find("input").eq(2).attr("value",this.traffic);
						divLast.find("textarea").eq(1).text(this.remark);
		        		flag=true;
					}
				});
				if(flag==false){
					div.append('<div id="editDay'+int+'" class="tab-pane">'+editTripModel.html()+'</div>');
					var divLast = div.children("div:last");
					divLast.find("textarea").eq(0).text("");
					divLast.find("input").eq(0).val("");
					divLast.find("input").eq(1).val("");
					divLast.find("input").eq(2).val("");
					divLast.find("textarea").eq(1).text("");
				}
			}
		}
		}
/*更新 */
	/* 全局行程删除id */
	var tripDelIds = new Array();
	$("#saveEdit").click(function(){
		$("#saveEdit").attr("data-dismiss","modal");
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
				customerAgencyId:incomeInputs.eq(1).val(),
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
	        url:"${path }localTourManage/update",  
	        data:myData,  
	        dataType: "json",  
	        async: false,  
	        success:function(data){
	        	if(data==-1){
	        		$("#saveEdit").attr("data-dismiss","");
	        		alert("保存失败，请检查团号是否重复，基本信息必填项是否完整，开始日期是否大于结束日期");
	        	}else if(data==-2){
	        		$("#saveEdit").attr("data-dismiss","");
	        		alert("保存失败，请填写出发地或抵达地");
	        	}else if(data==-3){
	        		$("#saveEdit").attr("data-dismiss","");
	        		alert("保存失败，请填写前往地或离开");
	        	}else if(data==-4){
	        		$("#saveEdit").attr("data-dismiss","");
	        		alert("保存失败，请填写供应商");
	        	}else if(data==-5){
	        		$("#saveEdit").attr("data-dismiss","");
	        		alert("保存失败，请填写客户");
	        	}
	        }
		 });
	});
	
	/* 成本变更 */
	$("#chanageCost").click(function(){
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
			$(this).attr("href","#cost");
			var tourId = checkbox.parent().parent().parent().attr("id");
			$("#saveChange").parent().attr("id",tourId);
			var myData = {tourId:tourId};
			$.ajax({
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"${path }localTourManage/findChangeCost",  
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
		        		var tr = $('<tr class="look blue">'+
										'<td>'+(this.costTable.costDate==null?"":this.costTable.costDate)+'</td>'+
										'<td>'+this.contentName+'</td>'+
										'<td>'+this.supplierName+'</td>'+
										'<td>'+this.costTable.cost+'</td>'+
										'<td>'+this.costTable.count+'</td>'+
										'<td>'+this.costTable.days+'</td>'+
										'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
										'<td>'+this.borrowUserName+'</td>'+
										'<td>'+this.costTable.remark+'</td>'+
								'</tr>');
		        		/* if(this.costTable.remittanced){
		        			tr.append('<td>已汇款</td>')
		        		}else{
		        			if(this.costTable.status==0){
			        			tr.append('<td>新建</td>')
			        		}else if(this.costTable.status==1){
			        			tr.append('<td>待审核</td>')
			        		}else if(this.costTable.status==2){
			        			tr.append('<td>已审核</td>')
			        		}else if(this.costTable.status==3){
			        			tr.append('<td>已批准</td>')
			        		}
		        		} */
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
		        		var realIncome = this.incomeTable.realIncome==null?0:this.incomeTable.realIncome;
		        		var invoiceAmount = this.invoiceAmount==null?0:this.invoiceAmount;
		        		var tr = $('<tr class="look blue">'+
		        						'<td>'+(this.incomeTable.incomeDate==null?"":this.incomeTable.incomeDate)+'</td>'+
		        						'<td>'+this.customerAgencyName+'<input type="hidden" value="'+this.incomeTable.customerAgencyId+'" />'+'</td>'+
		        						'<td>'+this.incomeTable.income+'</td>'+
		        						'<td>'+realIncome+'</td>'+
		        						'<td>'+invoiceAmount+'</td>'+
		        						'<td>'+this.incomeTable.remark+'</td>'+
		        					'</tr>');
		        		/* if(this.incomeTable.incomed){
		        			tr.append('<td>已收款</td>')
		        		}else{
		        			if(this.incomeTable.status==0){
			        			tr.append('<td>新建</td>')
			        		}else if(this.incomeTable.status==1){
			        			tr.append('<td>待审核</td>')
			        		}else if(this.incomeTable.status==2){
			        			tr.append('<td>已审核</td>')
			        		}else if(this.incomeTable.status==3){
			        			tr.append('<td>已批准</td>')
			        		}
		        		} */
		        		incomes.append(tr);
		        	});
		        }  
			});
		}
 	});
	
	/* 提交待审 */
	$("#cost").delegate("#saveChange","click",function(){
		var costTables = new Array();
		var tourId = $(this).parent().attr("id");
		var costTrs = $("#cost").find("#costs4").find("tbody").find("tr").not(".look");
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
				borrowUserId:costSelects.eq(2).val(),
				supplierScopeId:costInputs.last().val(),
				remark:costInputs.eq(7).val()});
		}
		var incomeTables = new Array();
		var incomeTrs = $("#cost").find("#incomes4").find("tbody").find("tr").not(".look");
		for (var int = 0; int < incomeTrs.length; int++) {
			var incomeInputs = incomeTrs.eq(int).find("input");
			var incomeSelects = incomeTrs.eq(int).find("select");
			incomeTables.push({
				tourId:tourId,
				incomeDate:new Date(incomeInputs.eq(0).val()),
				customerAgencyId:incomeInputs.eq(1).val(),
				income:incomeInputs.eq(2).val(),
				remark:incomeInputs.eq(3).val()
			});
		}
		var changeCostIncomeViewModel = {costTables:costTables,incomeTables:incomeTables};
		var myData = JSON.stringify(changeCostIncomeViewModel);
		$.ajax({
	        type: "POST",  
	        contentType:"application/json;charset=utf-8",  
	        url:"${path }localTourManage/saveChangeCost",  
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
	        		alert("保存失败，请填写供应商");
	        	}else if(data==-5){
	        		alert("保存失败，请填写客户");
	        	}
	        }  
		 });
	});
	
	/* 导游借款 */
	$("#lend").click(function(){
		var checkbox = $("#table").find("input:checked");
		if(checkbox.length==0){
			alert("请选择一个团队");
			$(this).attr("href","#");
		}else if(checkbox.length>1){
			alert("只能选择一个团队");
			$(this).attr("href","#");
		}else{
			$(this).attr("href","#lendModel");
			var tourId = checkbox.parent().parent().parent().attr("id");
			$("#loanApplication").parent().attr("id",tourId);
			var myData = {tourId:tourId};
			$.ajax({
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"${path }localTourManage/findLend",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	var canLoans = $('<tbody></tbody>');
		        	var isLoans = $('<tbody></tbody>');
		        	$("#canLoans").html("");
		        	$("#isLoans").html("");
		        	$.each(data,function(){
		        		if(this.loanTable.status>1){
		        			var tr = $('<tr>'+
	        						'<td>'+this.loanTable.loanDate+'</td>'+
	        						'<td>'+this.loanTable.loanAmount+'</td>'+
	        						'<td>'+this.loanTable.remark+'</td>'+
	        						'<td>'+this.lenderRealName+'</td>'+
	        						'<td>'+this.status+'</td>'+
	        					'</tr>');
		        			isLoans.append(tr);
		        		}else{
		        			var tr = $('<tr id="'+this.loanTable.id+'">'+
	        						'<td>'+this.loanTable.loanDate+'</td>'+
	        						'<td>'+this.loanTable.loanAmount+'</td>'+
	        						'<td><input type="text" value="'+this.loanTable.remark+'" /></td>'+
	        						'<td>'+this.lenderRealName+'</td>'+
	        						'<td>'+this.status+'</td>'+
	        					'</tr>');
		        			canLoans.append(tr);
		        		}
		        	});
		        	if(canLoans.html()==""){
	        			$("#canLoans").append('<span class="red">无可借款项</span>');
	        		}else {
	        			$("#canLoans").html(canLoans.html());
	        		}
					if(isLoans.html()==""){
						$("#isLoans").append('<span class="red">无借款记录</span>');
	        		}else{
	        			$("#isLoans").html(isLoans.html());
	        		}
		        }
			});
		}
	});
	/* 提交借款申请 */
	$("#loanApplication").click(function(){
		var tourId = $(this).parent().attr("id");
		var canTrs = $("#canLoans").children("tr");
		var isTrs = $("#isLoans").children("tr");
		var loans = new Array();
		if(canTrs.length>0){
			$.each(canTrs,function(index){
				loans.push({id:$(this).attr("id"),tourId:tourId,remark:$(this).find("input").val()});
			});
			var myData = JSON.stringify(loans);
			$.ajax({
		        type: "POST",  
		        contentType:"application/json;charset=utf-8",  
		        url:"${path }localTourManage/loanApplication",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	if(!data){
		        		alert("发送企业微信消息失败，经理未收到消息，请稍后再试");
		        	}
		        }  
			 });
		}else if(canTrs.length==0&&isTrs.length>0){
			var myData = {tourId:tourId};
			$.ajax({
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"${path }localTourManage/loanApplicationAgain",
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	if(!data){
		        		alert("发送企业微信消息失败，经理未收到消息，请稍后再试");
		        	}
		        }  
			});
		}
	});
	
	/* 付款申请 */
	var canPaysP = $("#canPays").parent();
	var isPaysP = $("#isPays").parent();
	var canPaysH = canPaysP.html();
	var isPaysH = isPaysP.html();
	var changeCostBlueDown = $("#changeCostBlueDown");
	var changeCostBlueUp = $("#changeCostBlueUp");
	$("#pay").click(function(){
		canPaysP.html(canPaysH);
		isPaysP.html(isPaysH);
		/*付款全选*/
		$("#payModel").find(".selectAllPay").click(function(){
			if($(this).prop("checked")){
				var checkbox = $("#canPays").find("input").prop("checked",true);
			}else{
				var checkbox = $("#canPays").find("input").prop("checked",false);
			}
		});
		/*点击本行选择*/
		$("#canPays").delegate("tr td:not(.center):not(tr td:nth-child(9)):not(tr td:nth-child(4)):not(tr td:nth-child(11))","click",function(){
			var checkbox = $(this).parent().find("input");
			if(checkbox.prop("checked")){
				checkbox.prop("checked",false);
			}else{
				checkbox.prop("checked",true);
			}
		});
		$(".selectAllPay").prop("checked",false);
		var checkbox = $("#table").find("input:checked");
		if(checkbox.length==0){
			alert("请选择一个团队");
			$(this).attr("href","#");
		}else if(checkbox.length>1){
			alert("只能选择一个团队");
			$(this).attr("href","#");
		}else{
			$(this).attr("href","#payModel");
			var tourId = checkbox.parent().parent().parent().attr("id");
			$("#payApplication").parent().attr("id",tourId);
			var myData = {tourId:tourId};
			$.ajax({
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"${path }localTourManage/findPay",  
		        data:myData,  
		        dataType: "json",
		        async: false,
		        success:function(data){
		        	var canPays = $("#canPays");
		        	var isPays = $("#isPays");
		        	if(inited==false){
						init();
						inited = true;
					}
		        	$.each(data.costs,function(){
		        		if(this.costTable.payStatus==0&&!this.costTable.remittanced&&!this.costTable.lend&&!this.costTable.bill){
		        			var td;
		        			if(this.costTable.remark!=null&&this.costTable.remark.indexOf("补余款")>-1){
		        				td = $('<td>'+this.supplierName+'</td>');
		        			}else{
		        				td = $('<td><select style="display: none;" value="'+this.costTable.supplierId+'" class="width-20 chosen-select form-control" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>');
		        			}

		        			var select = td.children("select");
		        			if(this.costTable.supplierScopeId==1){
		        	        	$.each(selectInfo.flightSuppliers,function(){
		        	        		select.append('<option value="'+this.id+'">'+this.supplierName+'</option>');
		        	        	});
		        			}else if(this.costTable.supplierScopeId==2){
		        	        	$.each(selectInfo.hotelSuppliers,function(){
		        	        		select.append('<option value="'+this.id+'">'+this.supplierName+'</option>');
		        	        	});
		        			}else if(this.costTable.supplierScopeId==3){
		        	        	$.each(selectInfo.mealSuppliers,function(){
		        	        		select.append('<option value="'+this.id+'">'+this.supplierName+'</option>');
		        	        	});
		        			}else if(this.costTable.supplierScopeId==4){
		        	        	$.each(selectInfo.ticketSuppliers,function(){
		        	        		select.append('<option value="'+this.id+'">'+this.supplierName+'</option>');
		        	        	});
		        			}else if(this.costTable.supplierScopeId==5){
		        	        	$.each(selectInfo.shuttleSuppliers,function(){
		        	        		select.append('<option value="'+this.id+'">'+this.supplierName+'</option>');
		        	        	});
		        			}else if(this.costTable.supplierScopeId==6){
		        	        	$.each(selectInfo.ticketsSuppliers,function(){
		        	        		select.append('<option value="'+this.id+'">'+this.supplierName+'</option>');
		        	        	});
		        			}else if(this.costTable.supplierScopeId==7){
		        	        	$.each(selectInfo.comprehensiveSuppliers,function(){
		        	        		select.append('<option value="'+this.id+'">'+this.supplierName+'</option>');
		        	        	});
		        			}else if(this.costTable.supplierScopeId==8){
		        	        	$.each(selectInfo.otherSuppliers,function(){
		        	        		select.append('<option value="'+this.id+'">'+this.supplierName+'</option>');
		        	        	});
		        			}
		        			var tr = $('<tr id="'+this.costTable.id+'">'+
		        							'<td class="center sorting_1"><label><input class="ace" type="checkbox"><span class="lbl"></span></label></td>'+
			        						'<td>'+(this.costTable.costDate==null?"":this.costTable.costDate)+'</td>'+
			        						'<td>'+this.contentName+'</td>'+
			        						'<td>'+td.html()+'</td>'+
			        						'<td>'+this.costTable.cost+'</td>'+
			        						'<td>'+this.costTable.count+'</td>'+
			        						'<td>'+this.costTable.days+'</td>'+
			        						'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
			        						'<td><input class="realCost" placeholder="双击自动添加" style="width:100%;" type="text" value="'+this.costTable.realCost+'"></td>'+
			        						'<td>'+this.borrowUserName+'</td>'+
			        						'<td><input style="width:100%;" type="text" value="'+(this.costTable.remark==null?"":this.costTable.remark)+'"></td>'+
			        						'<td>'+this.payStatus+'</td>'+
			        					'</tr>');
		        			canPays.append(tr);
		        			canPays.find("select").last().val(this.costTable.supplierId);
		        		}else if(!this.costTable.lend&&!this.costTable.bill){
		        			var costTable = this.costTable.costDate==null?"":this.costTable.costDate;
		        			var tr = $('<tr id="'+this.costTable.id+'">'+
			        						'<td>'+(this.costTable.costDate==null?"":this.costTable.costDate)+'</td>'+
			        						'<td>'+costTable+'</td>'+
			        						'<td>'+this.supplierName+'</td>'+
			        						'<td>'+this.costTable.cost+'</td>'+
			        						'<td>'+this.costTable.count+'</td>'+
			        						'<td>'+this.costTable.days+'</td>'+
			        						'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
			        						'<td>'+this.costTable.realCost.toFixed(2)+'</td>'+
			        						'<td>'+this.borrowUserName+'</td>'+
			        						'<td>'+(this.costTable.remark==null?"":this.costTable.remark)+'</td>'+
			        						'<td>'+this.payStatus+((this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)==this.costTable.realCost.toFixed(2)?"":"<a href=\"#\" class=\"pull-right supplement\">| 补款</a>")+'</td>'+
			        					'</tr>');
		        			isPays.append(tr);
		        		}
		        	});
		        	var canPaysChangeCount = 0;
		        	var isPaysChangeCount = 0;
		        	$.each(data.changeCosts,function(){
		        		if(this.costTable.payStatus==0&&!this.costTable.remittanced&&!this.costTable.lend&&!this.costTable.bill&&this.costTable.status==3){
		        			var td;
		        			if(this.costTable.remark!=null&&this.costTable.remark.indexOf("补余款")>-1){
		        				td = $('<td>'+this.supplierName+'</td>');
		        			}else{
		        				td = $('<td><select style="display: none;" value="'+this.costTable.supplierId+'" class="width-20 chosen-select form-control" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>');
		        			}
		        			var select = td.children("select");
		        			if(this.costTable.supplierScopeId==1){
		        	        	$.each(selectInfo.flightSuppliers,function(){
		        	        		select.append('<option value="'+this.id+'">'+this.supplierName+'</option>');
		        	        	});
		        			}else if(this.costTable.supplierScopeId==2){
		        	        	$.each(selectInfo.hotelSuppliers,function(){
		        	        		select.append('<option value="'+this.id+'">'+this.supplierName+'</option>');
		        	        	});
		        			}else if(this.costTable.supplierScopeId==3){
		        	        	$.each(selectInfo.mealSuppliers,function(){
		        	        		select.append('<option value="'+this.id+'">'+this.supplierName+'</option>');
		        	        	});
		        			}else if(this.costTable.supplierScopeId==4){
		        	        	$.each(selectInfo.ticketSuppliers,function(){
		        	        		select.append('<option value="'+this.id+'">'+this.supplierName+'</option>');
		        	        	});
		        			}else if(this.costTable.supplierScopeId==5){
		        	        	$.each(selectInfo.shuttleSuppliers,function(){
		        	        		select.append('<option value="'+this.id+'">'+this.supplierName+'</option>');
		        	        	});
		        			}else if(this.costTable.supplierScopeId==6){
		        	        	$.each(selectInfo.ticketsSuppliers,function(){
		        	        		select.append('<option value="'+this.id+'">'+this.supplierName+'</option>');
		        	        	});
		        			}else if(this.costTable.supplierScopeId==7){
		        	        	$.each(selectInfo.comprehensiveSuppliers,function(){
		        	        		select.append('<option value="'+this.id+'">'+this.supplierName+'</option>');
		        	        	});
		        			}else if(this.costTable.supplierScopeId==8){
		        	        	$.each(selectInfo.otherSuppliers,function(){
		        	        		select.append('<option value="'+this.id+'">'+this.supplierName+'</option>');
		        	        	});
		        			}
		        			var tr = $('<tr id="'+this.costTable.id+'" class="blue">'+
		        							'<td class="center sorting_1"><label><input class="ace" type="checkbox"><span class="lbl"></span></label></td>'+
			        						'<td>'+(this.costTable.costDate==null?"":this.costTable.costDate)+'</td>'+
			        						'<td>'+this.contentName+'</td>'+
			        						'<td>'+td.html()+'</td>'+
			        						'<td>'+this.costTable.cost+'</td>'+
			        						'<td>'+this.costTable.count+'</td>'+
			        						'<td>'+this.costTable.days+'</td>'+
			        						'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
			        						'<td><input class="realCost" placeholder="双击自动添加" style="width:100%;" type="text" value="'+this.costTable.realCost+'"></td>'+
			        						'<td>'+this.borrowUserName+'</td>'+
			        						'<td><input style="width:100%;" type="text" value="'+(this.costTable.remark==null?"":this.costTable.remark)+'"></td>'+
			        						'<td>'+this.payStatus+'</td>'+
			        					'</tr>');
		        			canPays.append(tr);
		        			canPays.find("select").last().val(this.costTable.supplierId);
		        			canPaysChangeCount++;
		        		}else if(!this.costTable.lend&&!this.costTable.bill&&this.costTable.status==3){
		        			var tr = $('<tr id="'+this.costTable.id+'" class="blue">'+
			        						'<td>'+(this.costTable.costDate==null?"":this.costTable.costDate)+'</td>'+
			        						'<td>'+this.contentName+'</td>'+
			        						'<td>'+this.supplierName+'</td>'+
			        						'<td>'+this.costTable.cost+'</td>'+
			        						'<td>'+this.costTable.count+'</td>'+
			        						'<td>'+this.costTable.days+'</td>'+
			        						'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
			        						'<td>'+this.costTable.realCost.toFixed(2)+'</td>'+
			        						'<td>'+this.borrowUserName+'</td>'+
			        						'<td>'+(this.costTable.remark==null?"":this.costTable.remark)+'</td>'+
			        						'<td>'+this.payStatus+((this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)==this.costTable.realCost.toFixed(2)?"":"<a href=\"#\" class=\"pull-right supplement\">| 补款</a>")+'</td>'+
			        					'</tr>');
		        			isPays.append(tr);
		        			isPaysChangeCount++;
		        		}
		        	});
		        	var span = $('<span id="changeCostBlue" class="blue" style="">*蓝色为成本收入变更</span>');
		        	$("#changeCostBlue").remove()
		        	if(canPays.html()==""){
	        			$("#canPays").parent().html('<span class="red">无可付款项</span>');
	        		}else {
	        			if(canPaysChangeCount!=0){
	        				canPaysP.after(span);
	        			}
	        		}
					if(isPays.html()==""){
						$("#isPays").parent().html('<span class="red">无付款记录</span>');
	        		}else{
	        			if(isPaysChangeCount!=0){
	        				isPaysP.after(span);
	        			}
	        		}
		        	$("#canPays").find("select").chosen({no_results_text: "查无结果", search_contains: true});
		        	$("#canPays").find("select").next().attr("style","width:100%;");
		        }
			});
		}
	});
	
	/* 补款 */
	$("#payModel").delegate(".supplement","click",function(){
		var tr = $(this).parent().parent().clone();
		var myData = {id:$(this).parent().parent().attr("id"),type:($(this).parent().parent().attr("class")=="blue"?"changeCost":"cost")};
		$.ajax({
	        type: "GET",  
	        contentType:"application/json;charset=utf-8",  
	        url:"${path }localTourManage/paySupplement",  
	        data:myData,  
	        dataType: "json",  
	        async: false,  
	        success:function(data){
	        	if(data!=0){
	        		tr.attr("id",data);
	        		tr.attr("class",myData.type=="changeCost"?"blue":"");
	        		tr.children("td").eq(7).html('<input class="realCost" placeholder="双击自动添加" style="width:100%;" value="'+(parseFloat(tr.children("td").eq(6).text())-parseFloat(tr.children("td").eq(7).text())).toFixed(2)+'" type="text">');
	        		tr.children("td").eq(3).text("");
	        		tr.children("td").eq(4).text("");
	        		tr.children("td").eq(5).text("");
	        		tr.children("td").eq(6).text("");
	        		tr.children("td").eq(8).text("");
	        		tr.children("td").eq(9).html('<input style="width:100%;" value="" type="text">');
	        		tr.children("td").eq(10).text("可付");
	        		tr.prepend('<td class="center sorting_1"><label><input class="ace" type="checkbox"><span class="lbl"></span></label></td>');
	        		$("#payModel").find("#canPays").append(tr);
	        	}
	        }  
		});
	});
	/* 自动填充借款付款金额 */
	$(".autoAddLend").click(function(){
		var inputs = $(this).parent().prev().find("input").not(".ace");
		if(inputs.length==0){
			
		}else{
			$.each(inputs,function(){
				$(this).val($(this).parent().prev().text());
			});
		}
	});
	/*提交付款申请*/
	$("#payApplication").click(function(){
		var tourId = $(this).parent().attr("id");
		var checkbox = $("#canPays").find("input:checked");
		var canTrs = $("#canPays").find("tr");
		var isTrs = $("#isPays").find("tr");
		if(canTrs.length>0){
			var error = 0;
			if(checkbox.length==0){
				$("#payApplication").attr("data-dismiss","modal");
				/* alert("没有选择要申请的付款项"); */
			}else{
				$("#payApplication").attr("data-dismiss","modal");
				var costTables = new Array();
				var changeCostTables = new Array();
				$.each(checkbox,function(){
					var tr = $(this).parent().parent().parent();
					/* if(tr.children("td").eq(8).children("input").val()>parseFloat(tr.children("td").eq(7).text())){
						alert("申请金额不能大于成本小计");
						$("#payApplication").attr("data-dismiss","");
						error = -1;
						return false;
					} */
					if(tr.children("td").eq(8).children("input").val()==0){
						alert("申请金额不能为0");
						$("#payApplication").attr("data-dismiss","");
						error = -3;
						return false;
					}
					if(tr.attr("class")=="blue"){
						changeCostTables.push({id:tr.attr("id"),
											tourId:tourId,
											supplierId:tr.find("select").val(),
											realCost:tr.children("td").eq(8).children("input").val(),
											remark:tr.children("td").eq(10).children("input").val()});
					}else{
						costTables.push({id:tr.attr("id"),
									tourId:tourId,
									supplierId:tr.find("select").val(),
									realCost:tr.children("td").eq(8).children("input").val(),
									remark:tr.children("td").eq(10).children("input").val()});
					}
				});
				if(error==0){
					var full = {costTables:costTables,changeCostTables:changeCostTables};
					var myData = JSON.stringify(full);
					$.ajax({
				        type: "POST",  
				        contentType:"application/json;charset=utf-8",  
				        url:"${path }localTourManage/payApplication",  
				        data:myData,  
				        dataType: "json",  
				        async: false,  
				        success:function(data){
				        	if(data==-1){
				        		alert("申请金额不能大于成本小计");
				        	}else if(data==-2){
				        		alert("发送企业微信消息失败，经理未收到消息，请稍后再试");
				        	}
				        }  
					});
				}
			}
		}
		if(isTrs.length>0){
			var myData = {tourId:tourId};
			$.ajax({
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"${path }localTourManage/payApplicationAgain",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	if(!data){
		        		alert("发送企业微信消息失败，经理未收到消息，请稍后再试");
		        	}
		        }  
			});
		}
		
	});
	/*点击付款input金额自动填充*/
	$("#payModel").delegate("input.realCost","dblclick",function(){
		$(this).val(parseFloat($(this).parent().prev().text()));
	});
	
	/* 退款 */
	$("#refund").click(function(){
		var checkbox = $("#table").find("input:checked");
		if(checkbox.length==0){
			alert("请选择一个团队");
			$(this).attr("href","#");
		}else if(checkbox.length>1){
			alert("只能选择一个团队");
			$(this).attr("href","#");
		}else{
			$("#refunds").html("");
			$(this).attr("href","#refundModel");
			var tourId = checkbox.parent().parent().parent().attr("id");
			$("#refundApplication").parent().attr("id",tourId);
			var myData = {tourId:tourId};
			$.ajax({
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"${path }localTourManage/findRefund",  
		        data:myData,  
		        dataType: "json",
		        async: false,
		        success:function(data){
		        	customerAgencyName = data.customerAgencyName;
		        	$.each(data.refunds,function(){
		        		$("#refunds").append('<tr class="refund">'+
														'<td>'+this.refundTable.refundDate+'</td>'+
														'<td>'+this.customerAgencyName+'</td>'+
														'<td>'+this.refundTable.refundContent+'</td>'+
														'<td>'+this.refundTable.refundWays+'</td>'+
														'<td>'+this.refundTable.refundAmount.toFixed(2)+'</td>'+
														'<td>'+this.refundTable.remark+'</td>'+
														'<td>'+this.status+'</td>'+
													'</tr>');
		        	});
		        }
			});
		}
	});
	/*新增退款*/
	$(".addRefund").click(function(){
		var date = (new Date()).toLocaleDateString();
		var tr = $('<tr>'+
						'<td><input style="width:100%;" class="form-control datepicker" type="text" value="'+date+'"></td>'+
						'<td>'+customerAgencyName+'</td>'+
						'<td><input style="width:100%;" class="form-control" type="text"></td>'+
						'<td><input style="width:100%;" class="form-control" type="text"></td>'+
						'<td><input style="width:100%;" class="form-control" type="text"></td>'+
						'<td><textarea style="width:100%;" class="form-control" rows="1"></textarea></td>'+
						'<td><a class="red delLine" href="#"><i class="icon-trash bigger-130"></i></a></td>'+
					'</tr>');
		tr.find(".datepicker").datepicker({
			showOtherMonths: true,
			selectOtherMonths: false,
		});
		$("#refunds").append(tr);
	});
	
	/* 退款申请 */
	$("#refundApplication").click(function(){
		var tourId = $(this).parent().attr("id");
		var refundTables = new Array();
		var trs = $("#refunds").children("tr").not(".refund");
		if(trs.length>0){
			$.each(trs,function(){
				var inputs = $(this).find("input");
				refundTables.push({
					tourId:tourId,
					refundDate:new Date(inputs.eq(0).val()),
					refundContent:inputs.eq(1).val(),
					refundWays:inputs.eq(2).val(),
					refundAmount:inputs.eq(3).val(),
					remark:$(this).find("textarea").val()
				});
			});
			var myData = JSON.stringify(refundTables);
			$.ajax({
				type: "POST",  
		        contentType:"application/json;charset=utf-8",  
		        url:"${path }localTourManage/refundApplication",  
		        data:myData,  
		        dataType: "json",
		        async: false,
		        success:function(data){
		        	if(data==-1){
		        		$("#loanInvoiceApplication").attr("data-dismiss","");
		        		alert("*号为必填项");
		        	}else if(data==-2){
		        		$("#loanInvoiceApplication").attr("data-dismiss","");
		        		alert("微信发送消息失败，请稍后重试");
		        	}else if(data==-3){
		        		$("#loanInvoiceApplication").attr("data-dismiss","");
		        		alert("退款金额不能大于实收款");
		        	}else{
		        		$("#loanInvoiceApplication").attr("data-dismiss","modal");
		        	}
		        }
			});
		}else{
			var myData = {tourId:tourId};
			$.ajax({
				type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"${path }localTourManage/refundApplicationAgain",  
		        data:myData,  
		        dataType: "json",
		        async: false,
		        success:function(data){
		        	if(!data){
		        		alert("发送企业微信消息失败，经理未收到消息，请稍后再试");
		        	}
		        }
			});
		}
		
	});
	
	/*预借发票*/
	var customerAgencyName;
	$("#loanInvoice").click(function(){
		var checkbox = $("#table").find("input:checked");
		if(checkbox.length==0){
			alert("请选择一个团队");
			$(this).attr("href","#");
		}else if(checkbox.length>1){
			alert("只能选择一个团队");
			$(this).attr("href","#");
		}else{
			$("#loanInvoices").html("");
			$(this).attr("href","#loanInvoiceModel");
			var tourId = checkbox.parent().parent().parent().attr("id");
			$("#loanInvoiceApplication").parent().attr("id",tourId);
			var myData = {tourId:tourId};
			$.ajax({
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"${path }localTourManage/findBorrowInvoice",  
		        data:myData,  
		        dataType: "json",
		        async: false,
		        success:function(data){
		        	customerAgencyName = data.customerAgencyName;
		        	$.each(data.invoices,function(){
		        		$("#loanInvoices").append('<tr class="loanInvoice">'+
														'<td>'+this.loanInvoiceTable.issueDate+'</td>'+
														'<td>'+this.customerAgencyName+'</td>'+
														'<td>'+this.loanInvoiceTable.invoiceContent+'</td>'+
														'<td>'+this.loanInvoiceTable.invoiceAmount+'</td>'+
														'<td>'+this.loanInvoiceTable.remark+'</td>'+
														'<td>'+this.status+'</td>'+
													'</tr>');
		        	});
		        }
			});
		}
	});
	
	/*新增预借发票*/
	$(".addLoanInvoice").click(function(){
		var date = (new Date()).toLocaleDateString();
		var tr = $('<tr>'+
						'<td><input style="width:100%;" class="form-control datepicker" type="text" value="'+date+'"></td>'+
						'<td>'+customerAgencyName+'</td>'+
						'<td><input style="width:100%;" class="form-control" type="text"></td>'+
						'<td><input style="width:100%;" class="form-control" type="text"></td>'+
						'<td><textarea style="width:100%;" class="form-control" rows="1"></textarea></td>'+
						'<td><a class="red delLine" href="#"><i class="icon-trash bigger-130"></i></a></td>'+
					'</tr>');
		tr.find(".datepicker").datepicker({
			showOtherMonths: true,
			selectOtherMonths: false,
		});
		$("#loanInvoices").append(tr);
	});
	
	/*预借发票申请*/
	$("#loanInvoiceApplication").click(function(){
		var tourId = $(this).parent().attr("id");
		var loanInvoices = new Array();
		var trs = $("#loanInvoices").children("tr").not(".loanInvoice");
		if(trs.length>0){
			$.each(trs,function(){
				var inputs = $(this).find("input");
				loanInvoices.push({
					tourId:tourId,
					issueDate:new Date(inputs.eq(0).val()),
					invoiceContent:inputs.eq(1).val(),
					invoiceAmount:inputs.eq(2).val(),
					remark:$(this).find("textarea").val()
				});
			});
			var myData = JSON.stringify(loanInvoices);
			$.ajax({
				type: "POST",  
		        contentType:"application/json;charset=utf-8",  
		        url:"${path }localTourManage/saveBorrowInvoice",  
		        data:myData,  
		        dataType: "json",
		        async: false,
		        success:function(data){
		        	if(data==-1){
		        		$("#loanInvoiceApplication").attr("data-dismiss","");
		        		alert("*号为必填项");
		        	}else{
		        		$("#loanInvoiceApplication").attr("data-dismiss","modal");
		        	}
		        }
			});
		}else{
			var myData = {tourId:tourId};
			$.ajax({
				type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"${path }localTourManage/borrowInvoiceAgain",  
		        data:myData,  
		        dataType: "json",
		        async: false,
		        success:function(data){
		        	if(!data){
		        		alert("发送企业微信消息失败，经理未收到消息，请稍后再试");
		        	}
		        }
			});
		}
		
	});
	
	/*申请结算*/
	$("#balance").click(function(){
		var checkbox = $("#table").find("input:checked");
		if(checkbox.length==0){
			alert("请选择一个团队");
			$(this).attr("href","#");
		}else if(checkbox.length>1){
			alert("只能选择一个团队");
			$(this).attr("href","#");
		}else{
			var tourId = checkbox.parent().parent().parent().attr("id");
			var myData = {id:tourId};
			$.ajax({
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"${path }localTourManage/balance",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	if(data){
			        	checkbox.parent().parent().parent().find("td").eq(8).text("结算中");
			        	changeButton("结算中");
		        	}else{
		        		alert("操作失败");
		        	}
		        }
			});
		}
	});
	
	/* 取消结算 */
	$("#unBalance").click(function(){
		var checkbox = $("#table").find("input:checked");
		if(checkbox.length==0){
			alert("请选择一个团队");
			$(this).attr("href","#");
		}else if(checkbox.length>1){
			alert("只能选择一个团队");
			$(this).attr("href","#");
		}else{
			var tourId = checkbox.parent().parent().parent().attr("id");
			var myData = {id:tourId};
			$.ajax({
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"${path }localTourManage/unBalance",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	if(data){
			        	checkbox.parent().parent().parent().find("td").eq(8).text("已结束");
			        	changeButton("已结束");
		        	}else{
		        		alert("操作失败");
		        	}
		        }
			});
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
			if(inited==false){
				init();
				inited = true;
			}
			$.ajax({
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"${path }reimbursementManage/checkReimbursement",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	if(data){
		        		find(myData);
		        	}else{
		        		alert("不能报账，预借发票金额大于实际收款金额");
		        		$("#reimbursement").attr("href","#");
		        	}
		        }
		    });
		}
	});
	function find(myData){
		$.ajax({
	        type: "GET",  
	        contentType:"application/json;charset=utf-8",  
	        url:"${path }reimbursementManage/findReimbursement",  
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
	        	var total = 0;
	        	var maxLoan = 0;
	        	var reimbursementSum = 0;
	        	var billSum = 0;
	        	var willCostSum = 0;
	        	var willIncomeSum = 0;
	        	var realIncomeSum = 0;
	        	flight.html("");
	        	hotel.html("");
	        	meal.html("");
	        	ticket.html("");
	        	shuttle.html("");
	        	tickets.html("");
	        	comprehensive.html("");
	        	other.html("");
	        	/*设置人头*/
	        	$("#headAmount").html('<input class="form-control" style="width:100%;" type="text" value="'+(data.reimbursementTable==null?"":data.reimbursementTable.headAmount)+'"/>');
	        	
	        	/* 设置成本 */
	        	var flightSelect = 	$('<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>');
	        	var hotelSelect = 	$('<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>');
	        	var mealSelect = 	$('<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>');
	        	var ticketSelect = 	$('<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>');
	        	var shuttleSelect = $('<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>');
	        	var ticketsSelect = $('<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>');
	        	var comprehensiveSelect = $('<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>');
	        	var otherSelect = 	$('<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>');
        		
       			$.each(selectInfo.flightSuppliers,function(){
       				flightSelect.children("select").append('<option value="'+this.id+'">'+this.supplierName+'</option>');
	        	});
       			$.each(selectInfo.hotelSuppliers,function(){
       				hotelSelect.children("select").append('<option value="'+this.id+'">'+this.supplierName+'</option>');
   	        	});
       			$.each(selectInfo.mealSuppliers,function(){
       				mealSelect.children("select").append('<option value="'+this.id+'">'+this.supplierName+'</option>');
               	});
       			$.each(selectInfo.ticketSuppliers,function(){
       				ticketSelect.children("select").append('<option value="'+this.id+'">'+this.supplierName+'</option>');
               	});
       			$.each(selectInfo.shuttleSuppliers,function(){
       				shuttleSelect.children("select").append('<option value="'+this.id+'">'+this.supplierName+'</option>');
               	});
       			$.each(selectInfo.ticketsSuppliers,function(){
       				ticketsSelect.children("select").append('<option value="'+this.id+'">'+this.supplierName+'</option>');
               	});
       			$.each(selectInfo.comprehensiveSuppliers,function(){
       				comprehensiveSelect.children("select").append('<option value="'+this.id+'">'+this.supplierName+'</option>');
               	});
       			$.each(selectInfo.otherSuppliers,function(){
       				otherSelect.children("select").append('<option value="'+this.id+'">'+this.supplierName+'</option>');
               	});

	        	$.each(data.costs,function(){
	        		var reimbursement = $("<td></td>");
	        		var guideLoan = $("<td></td>");
	        		var bill = $("<td><label><input class=\"ace\" type=\"checkbox\"><span class=\"lbl\"></span></label></td>");
        			if(this.costTable.bill){
        				bill.html("<label><input class=\"ace\" type=\"checkbox\" checked=\"true\"><span class=\"lbl\"></span></label>");
        				billSum = billSum + (this.costTable.cost*this.costTable.count*this.costTable.days);
        			}
        			if(this.costTable.lend){
        				guideLoan.html('<i class="icon-ok bigger-130"></i>');
        				maxLoan = maxLoan + (this.costTable.cost*this.costTable.count*this.costTable.days);
        			}else{
        				willCostSum = willCostSum + (this.costTable.cost*this.costTable.count*this.costTable.days);
        			}
        			
       				reimbursementSum = reimbursementSum + (this.costTable.reimbursement==null?0:this.costTable.reimbursement);
	        			
	        		var tbody;
	        		var select;
	        		if(this.costTable.supplierScopeId==1){
	        			tbody = flight;
	        			select = flightSelect;
	        		}else if(this.costTable.supplierScopeId==2){
	        			tbody = hotel;
	        			select = hotelSelect;
	        		}else if(this.costTable.supplierScopeId==3){
	        			tbody = meal;
	        			select = mealSelect;
	        		}else if(this.costTable.supplierScopeId==4){
	        			tbody = ticket;
	        			select = ticketSelect;
	        		}else if(this.costTable.supplierScopeId==5){
	        			tbody = shuttle;
	        			select = shuttleSelect;
	        		}else if(this.costTable.supplierScopeId==6){
	        			tbody = tickets;
	        			select = ticketsSelect;
	        		}else if(this.costTable.supplierScopeId==7){
	        			tbody = comprehensive;
	        			select = comprehensiveSelect;
	        		}else if(this.costTable.supplierScopeId==8){
	        			tbody = other;
	        			select = otherSelect;
	        		}
	        		var tr = $('<tr id="'+this.costTable.id+'">'+
									'<td>'+(this.costTable.costDate==null?"":this.costTable.costDate.replace(/-/g,'/'))+'</td>'+
									'<td>'+this.contentName+'</td>'+
									'<td>'+select.html()+'</td>'+
									'<td>'+this.costTable.cost+'</td>'+
									'<td>'+this.costTable.count+'</td>'+
									'<td>'+this.costTable.days+'</td>'+
									'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
									'<td>'+this.costTable.realCost.toFixed(2)+'</td>'+
									'<td><input type="text" value="'+(this.costTable.reimbursement==null?this.costTable.realCost==0?(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2):this.costTable.realCost:this.costTable.reimbursement)+'" style="width: 100%;" class="reimbursement"/></td>'+
									'<td>'+this.costTable.remark+'</td>'+
									'<td>'+guideLoan.html()+'</td>'+
									'<td>'+bill.html()+'</td>'+
									'<td style="vertical-align: middle;">'+this.payStatus+'</td>'+
									'<td></td>'+
								'</tr>');
	        		tr.find("select").val(this.costTable.supplierId);
	        		tbody.append(tr);
	        	});

	        	/* 设置成本变更 */
	        	if(data.changeCosts.length > 0){
	        		$("#costs5").find("#changeCostBlue").attr("style","");
	        	}else{
	        		$("#costs5").find("#changeCostBlue").attr("style","display:none");
	        	}
	        	$.each(data.changeCosts,function(){
	        		var reimbursement = $("<td></td>");
	        		var guideLoan = $("<td></td>");
	        		var bill = $("<td><label><input class=\"ace\" type=\"checkbox\"><span class=\"lbl\"></span></label></td>");
        			if(this.costTable.bill){
        				bill.html("<label><input class=\"ace\" type=\"checkbox\" checked=\"true\"><span class=\"lbl\"></span></label>");
        				billSum = billSum + (this.costTable.cost*this.costTable.count*this.costTable.days);
        			}
        			if(this.costTable.lend){
        				guideLoan.html('<i class="icon-ok bigger-130"></i>');
        				maxLoan = maxLoan + (this.costTable.cost*this.costTable.count*this.costTable.days);
        			}else{
        				willCostSum = willCostSum + (this.costTable.cost*this.costTable.count*this.costTable.days);
        			}
       				
       				reimbursementSum = reimbursementSum + (this.costTable.reimbursement==null?0:this.costTable.reimbursement);
	        		
	        		var tbody;
	        		var select;
	        		if(this.costTable.supplierScopeId==1){
	        			tbody = flight;
	        			select = flightSelect;
	        		}else if(this.costTable.supplierScopeId==2){
	        			tbody = hotel;
	        			select = hotelSelect;
	        		}else if(this.costTable.supplierScopeId==3){
	        			tbody = meal;
	        			select = mealSelect;
	        		}else if(this.costTable.supplierScopeId==4){
	        			tbody = ticket;
	        			select = ticketSelect;
	        		}else if(this.costTable.supplierScopeId==5){
	        			tbody = shuttle;
	        			select = shuttleSelect;
	        		}else if(this.costTable.supplierScopeId==6){
	        			tbody = tickets;
	        			select = ticketsSelect;
	        		}else if(this.costTable.supplierScopeId==7){
	        			tbody = comprehensive;
	        			select = comprehensiveSelect;
	        		}else if(this.costTable.supplierScopeId==8){
	        			tbody = other;
	        			select = otherSelect;
	        		}
	        		var tr = $('<tr class="blue"  id="'+this.costTable.id+'">'+
			        				'<td>'+(this.costTable.costDate==null?"":this.costTable.costDate.replace(/-/g,'/'))+'</td>'+
									'<td>'+this.contentName+'</td>'+
									'<td>'+select.html()+'</td>'+
									'<td>'+this.costTable.cost+'</td>'+
									'<td>'+this.costTable.count+'</td>'+
									'<td>'+this.costTable.days+'</td>'+
									'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
									'<td>'+this.costTable.realCost.toFixed(2)+'</td>'+
									'<td><input type="text" value="'+(this.costTable.reimbursement==null?this.costTable.realCost==0?(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2):this.costTable.realCost:this.costTable.reimbursement)+'" style="width: 100%;" class="reimbursement"/></td>'+
									'<td>'+this.costTable.remark+'</td>'+
									'<td>'+guideLoan.html()+'</td>'+
									'<td>'+bill.html()+'</td>'+
									'<td style="vertical-align: middle;">'+this.payStatus+'</td>'+
									'<td></td>'+
								'</tr>');
	        		tr.find("select").val(this.costTable.supplierId);
	        		tbody.append(tr);
	        	});

	        	/* 设置报账成本 */
	        	if(data.reimbursementCosts.length > 0){
	        		$("#costs5").find("#reimbursementCostRed").attr("style","");
	        	}else{
	        		$("#costs5").find("#reimbursementCostRed").attr("style","display:none");
	        	}
	        	$.each(data.reimbursementCosts,function(){
	        		var reimbursement = $("<td></td>");
	        		var bill = $("<td><label><input class=\"ace\" type=\"checkbox\"><span class=\"lbl\"></span></label></td>");
        			if(this.costTable.bill){
        				bill.html("<label><input class=\"ace\" type=\"checkbox\" checked=\"true\"><span class=\"lbl\"></span></label>");
        				billSum = billSum + (this.costTable.reimbursement==null?0:this.costTable.reimbursement);
        			}
       				
       				reimbursementSum = reimbursementSum + (this.costTable.reimbursement==null?0:this.costTable.reimbursement);
       				
	        		var tbody;
	        		var select;
	        		if(this.costTable.supplierScopeId==1){
	        			tbody = flight;
	        			select = flightSelect;
	        		}else if(this.costTable.supplierScopeId==2){
	        			tbody = hotel;
	        			select = hotelSelect;
	        		}else if(this.costTable.supplierScopeId==3){
	        			tbody = meal;
	        			select = mealSelect;
	        		}else if(this.costTable.supplierScopeId==4){
	        			tbody = ticket;
	        			select = ticketSelect;
	        		}else if(this.costTable.supplierScopeId==5){
	        			tbody = shuttle;
	        			select = shuttleSelect;
	        		}else if(this.costTable.supplierScopeId==6){
	        			tbody = tickets;
	        			select = ticketsSelect;
	        		}else if(this.costTable.supplierScopeId==7){
	        			tbody = comprehensive;
	        			select = comprehensiveSelect;
	        		}else if(this.costTable.supplierScopeId==8){
	        			tbody = other;
	        			select = otherSelect;
	        		}
	        		var tr = $('<tr class="red" id="'+this.costTable.id+'">'+
			        				'<td>'+(this.costTable.costDate==null?"":this.costTable.costDate.replace(/-/g,'/'))+'</td>'+
									'<td>'+this.contentName+'</td>'+
									'<td>'+select.html()+'</td>'+
									'<td>'+this.costTable.cost+'</td>'+
									'<td>'+this.costTable.count+'</td>'+
									'<td>'+this.costTable.days+'</td>'+
									'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
									'<td></td>'+
									'<td><input type="text" value="'+(this.costTable.reimbursement==null?this.costTable.realCost==0?(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2):this.costTable.realCost:this.costTable.reimbursement)+'" style="width: 100%;" class="reimbursement"/></td>'+
									'<td>'+this.costTable.remark+'</td>'+
									'<td></td>'+
									'<td>'+bill.html()+'</td>'+
									'<td style="vertical-align: middle;">报账新增</td>'+
									'<td></td>'+
								'</tr>');
	        		tr.find("select").val(this.costTable.supplierId);
	        		tbody.append(tr);
	        		willCostSum = willCostSum + (this.costTable.cost*this.costTable.count*this.costTable.days);
	        	});
	        	$("#costs5").find("select").chosen({no_results_text: "查无结果", search_contains: true});
	        	$("#costs5").find("select").next().attr("style","width:100%;");
	        	
	        	/* 设置借款 */
	        	$("#loanTable").html("");
	        	$.each(data.loans,function(){
	        		var tr;
        			tr = $('<tr>'+
        						'<td>'+(this.loanTable.loanDate==null?"":this.loanTable.loanDate.replace(/-/g,'/'))+'</td>'+
        						'<td>'+this.loanTable.loanAmount.toFixed(2)+'</td>'+
        						'<td>'+this.loanTable.remark+'</td>'+
        						'<td>'+this.lenderRealName+'</td>'+
        						'<td>'+this.status+'</td>'+
        					'</tr>');
        			total = total + this.loanTable.loanAmount;
	        		$("#loanTable").append(tr);
	        	});
	        	
	        	/* 设置收入 */
	        	var incomeTable = $("#incomes5").find("tbody");
	        	incomeTable.html("");
	        	$.each(data.incomes,function(){
	        		var tr = $('<tr>'+
        						'<td>'+(this.incomeTable.incomeDate==null?"":this.incomeTable.incomeDate.replace(/-/g,'/'))+'</td>'+
        						'<td>'+this.customerAgencyName+'<input type="hidden" value="'+this.incomeTable.customerAgencyId+'" />'+'</td>'+
        						'<td>'+this.incomeTable.income.toFixed(2)+'</td>'+
        						'<td>'+this.incomeTable.realIncome.toFixed(2)+'</td>'+
        						'<td></td>'+
        						'<td>'+this.incomeTable.remark+'</td>'+
        						'<td></td>'+
        					'</tr>');
        			incomeTable.append(tr);
        			willIncomeSum = willIncomeSum + this.incomeTable.income;
        			realIncomeSum = realIncomeSum + this.incomeTable.realIncome;
	        	});
	        	
	        	/* 设置收入变更 */
	        	if(data.changeIncomes.length > 0){
	        		$("#incomes5").find("#changeIncomeBlue").attr("style","");
	        	}else{
	        		$("#incomes5").find("#changeIncomeBlue").attr("style","display:none");
	        	}
	        	$.each(data.changeIncomes,function(){
	        		var tr = $('<tr>'+
	        					'<td>'+(this.incomeTable.incomeDate==null?"":this.incomeTable.incomeDate.replace(/-/g,'/'))+'</td>'+
        						'<td>'+this.customerAgencyName+'<input type="hidden" value="'+this.incomeTable.customerAgencyId+'" />'+'</td>'+
        						'<td>'+this.incomeTable.income.toFixed(2)+'</td>'+
        						'<td>'+this.incomeTable.realIncome.toFixed(2)+'</td>'+
        						'<td></td>'+
        						'<td>'+this.incomeTable.remark+'</td>'+
        						'<td></td>'+
        					'</tr>');
        			incomeTable.append(tr);
        			willIncomeSum = willIncomeSum + this.incomeTable.income;
        			realIncomeSum = realIncomeSum + this.incomeTable.realIncome;
	        	});

	        	/* 设置报账收入变更 */
	        	if(data.reimbursementIncomes.length > 0){
	        		$("#incomes5").find("#reimbursementIncomeRed").attr("style","");
	        	}else{
	        		$("#incomes5").find("#reimbursementIncomeRed").attr("style","display:none");
	        	}
	        	$.each(data.reimbursementIncomes,function(){
	        		var tr = $('<tr class="red" id="'+this.incomeTable.id+'">'+
	        					'<td></td>'+
        						'<td>'+this.customerAgencyName+'<input type="hidden" value="'+this.incomeTable.customerAgencyId+'" />'+'</td>'+
        						'<td><input class="form-control reimbursementIncome" style="width:100%;" type="text" value="'+this.incomeTable.income.toFixed(2)+'" /></td>'+
        						'<td></td>'+
        						'<td></td>'+
        						'<td><input class="form-control reimbursementIncome" style="width:100%;" type="text" value="'+this.incomeTable.remark+'" /></td>'+
        						'<td></td>'+
        					'</tr>');
        			incomeTable.append(tr);
        			willIncomeSum = willIncomeSum + this.incomeTable.income;
	        	});

	        	/* 设置借款总计、最大借款额 */
	        	/* $("#total").text(total.toFixed(2)); */
	        	$("#billSum").text(billSum.toFixed(2));
	        	$("#reimbursementSum").text(reimbursementSum.toFixed(2));
	        	$("#willCostSum").text((willCostSum+maxLoan).toFixed(2));
	        	$("#willIncomeSum").text(willIncomeSum.toFixed(2));
	        	$("#realIncomeSum").text(realIncomeSum.toFixed(2));
	        	$("#realGrossProfit").text((willIncomeSum-reimbursementSum).toFixed(2));
	        	$("#realGrossMargin").text(((willIncomeSum-reimbursementSum)/willIncomeSum*100).toFixed(2)+"%");
	        	/* 设置自动计算 */
	        	$("#costs5").find(".reimbursement").blur(function(){
	        		var reimbursementSum = 0;
	        		$.each($("#costs5").find(".reimbursement"),function(){
	        			var val = 0;
	        			if(!isNaN(parseFloat($(this).val()))){
	        				val = parseFloat($(this).val());
	        			}
	        			reimbursementSum = reimbursementSum + val;
	        		});
	        		$("#reimbursementSum").text(reimbursementSum.toFixed(2));
	        		$("#realGrossProfit").text((parseFloat($("#willIncomeSum").text())-reimbursementSum).toFixed(2));
	        		$("#realGrossMargin").text(parseFloat(($("#willIncomeSum").text())/(parseFloat($("#willIncomeSum").text()))*100).toFixed(2)+"%");
	        	});
	        	$("#incomes5").find(".reimbursementIncome").blur(function(){
	        		var willIncomeSum = 0;
	        		$.each($("#incomes5").find("tbody").find("tr"),function(){
	        			var val = 0;
	        			if($(this).find("input").length>1){
	        				if(!isNaN(parseFloat($(this).find("input").eq(1).val()))){
		        				val = parseFloat($(this).find("input").eq(1).val());
		        			}
	        			}else{
	        				if(!isNaN(parseFloat($(this).find("td").eq(2).text()))){
		        				val = parseFloat($(this).find("td").eq(2).text());
		        			}
	        			}
	        			willIncomeSum = willIncomeSum + val;
	        		});
	        		$("#willIncomeSum").text(willIncomeSum.toFixed(2));
	        		$("#realGrossProfit").text((willIncomeSum-reimbursementSum).toFixed(2));
		        	$("#realGrossMargin").text(((willIncomeSum-reimbursementSum)/willIncomeSum*100).toFixed(2)+"%");
	        	});
	        }  
		});
	}
	
	$(".addReimbursement").click(function(){
		var tbody = $(this).parents("table").children("tbody");
		tbody.append('<tr class="reimbursementTr">'+
        				'<td>'+
			    			'<input id="costTime" class="form-control datepicker" type="text">'+
			    		'</td>'+
			    		'<td>'+
			    			'<select style="display: none;" class="width-20 chosen-select" data-placeholder="Choose a Country...">'+
								'<option value="">&nbsp;</option>'+
							'</select>'+
						'</td>'+
			    		'<td>'+
			    			'<select style="display: none;" class="width-20 chosen-select" data-placeholder="Choose a Country...">'+
								'<option value="">&nbsp;</option>'+
							'</select>'+
			    		'</td>'+
			    		'<td><input class="form-control costPlus" type="text"></td>'+
			    		'<td><input class="form-control costPlus" type="text"></td>'+
			    		'<td><input class="form-control costPlus" type="text"></td>'+
			    		'<td style="vertical-align: middle;"></td>'+
			    		'<td></td>'+
						'<td><input class="form-control reimbursement" placeholder="" type="text"></td>'+
						'<td><input class="form-control" placeholder="" type="text"></td>'+
						'<td></td>'+
						'<td><label><input class="ace" type="checkbox"><span class="lbl"></span></label></td>'+
						'<td style="vertical-align: middle;">报账新增</td>'+
						'<td style="vertical-align: middle;">'+
							'<a class="red delLine" href="#">'+
								'<i class="icon-trash bigger-130"></i>'+
							'</a>'+
						'</td>'+
			    	'</tr>');
		var tr = tbody.children("tr").not("#costModel").last();
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
		}else if(type.substr(0,type.length-1)=="hotel"){
			tr.children("td").last().append('<input type="hidden" value="2" />');
			$.each(selectInfo.hotelContents,function(){
        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
        	});
        	$.each(selectInfo.hotelSuppliers,function(){
        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
        	});
		}else if(type.substr(0,type.length-1)=="meal"){
			tr.children("td").last().append('<input type="hidden" value="3" />');
			$.each(selectInfo.mealContents,function(){
        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
        	});
        	$.each(selectInfo.mealSuppliers,function(){
        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
        	});
		}else if(type.substr(0,type.length-1)=="ticket"){
			tr.children("td").last().append('<input type="hidden" value="4" />');
			$.each(selectInfo.ticketContents,function(){
        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
        	});
        	$.each(selectInfo.ticketSuppliers,function(){
        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
        	});
		}else if(type.substr(0,type.length-1)=="shuttle"){
			tr.children("td").last().append('<input type="hidden" value="5" />');
			$.each(selectInfo.shuttleContents,function(){
        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
        	});
        	$.each(selectInfo.shuttleSuppliers,function(){
        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
        	});
		}else if(type.substr(0,type.length-1)=="tickets"){
			tr.children("td").last().append('<input type="hidden" value="6" />');
			$.each(selectInfo.ticketsContents,function(){
        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
        	});
        	$.each(selectInfo.ticketsSuppliers,function(){
        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
        	});
		}else if(type.substr(0,type.length-1)=="comprehensive"){
			tr.children("td").last().append('<input type="hidden" value="7" />');
			$.each(selectInfo.comprehensiveContents,function(){
        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
        	});
        	$.each(selectInfo.comprehensiveSuppliers,function(){
        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
        	});
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
		
	});
	
	$(".addReimbursementIncome").click(function(){
		var tbody = $(this).parents("table").children("tbody");
		tbody.append("<tr>"+$("#incomeModel").html()+"</tr>");
		var tr = tbody.children("tr").not("#incomeModel").last();
		tr.find("#incomeTime").attr("id","").datepicker({
			showOtherMonths: true,
			selectOtherMonths: false,
		});
		/* tr.children("td").eq(1).text($(this).parents(".tab-pane").siblings().first().find("#customer").find("option:selected").text()); */
		var select = tr.children("td").eq(1).children("select");
		select.val($(this).parents(".tab-pane").siblings().first().find("#customer").val());
		select.chosen({no_results_text: "查无结果", search_contains: true});
		select.next().attr("style","width:100%;");
		select.next().find("input").attr("style","height:100%;");
	});
	
	/*自动填充报账金额*/
	$("#autoAddReimbursement").click(function(){
		var inputs = $("#costs5").find(".reimbursement");
		if(inputs.length==0){
			
		}else{
			var reimbursementSum = 0;
			$.each(inputs,function(){
				var reimbursement = parseFloat($(this).parent().prev().text());
				if(isNaN(reimbursement)||reimbursement==0){
					reimbursement = parseFloat($(this).parent().prev().prev().text());
				}
				$(this).val(reimbursement.toFixed(2));
				reimbursementSum = reimbursementSum + reimbursement;
			});
			$("#reimbursementSum").text(reimbursementSum.toFixed(2));
			$("#realGrossProfit").text((parseFloat($("#willIncomeSum").text())-reimbursementSum).toFixed(2));
        	$("#realGrossMargin").text(((parseFloat($("#willIncomeSum").text())-reimbursementSum)/parseFloat($("#willIncomeSum").text())*100).toFixed(2)+"%");
		}
	});
	
	/* 失去焦点自动计算报账成本 */
	$("#costs5").delegate(".reimbursement","blur",function(){
		var reimbursementSum = 0;
		$.each($("#costs5").find(".reimbursement"),function(){
			reimbursementSum = reimbursementSum + $(this).val();
		});
		$("#reimbursementModel").find("#reimbursementSum").text(reimbursementSum.toFixed(2));
	});
	
	/* 失去焦点自动计算应收 */
	$("#incomes5").delegate(".reimbursementIncome","blur",function(){
		var willIncomeSum = 0;
		$.each($("#incomes5").find("tbody").find("tr td:nth-child(3)"),function(){
			if($(this).children("input").length>0){
				willIncomeSum = willIncomeSum + parseFloat($(this).children("input").val());
			}else{
				willIncomeSum = willIncomeSum + parseFloat($(this).text());
			}
		});
		$("#reimbursementModel").find("#willIncomeSum").text(willIncomeSum.toFixed(2));
	})

	/* 调整报账收入 */
	$(".reimbursementIncomeAdd").click(function(){
		var tbody = $(this).parents("table").children("tbody");
		tbody.append('<tr class="red"><td></td><td style="vertical-align: middle;"></td><td><input class="form-control reimbursementIncome" style="width:100%;" type="text"></td><td></td><td></td><td><input class="form-control" style="width:100%;" type="text"></td><td style="vertical-align: middle;"><a class="red delLine" href="#"><i class="icon-trash bigger-130"></i></a></td></tr>');
		var tr = tbody.children("tr").not("#incomeModel").last();
		tr.find("#incomeTime").attr("id","").datepicker({
			showOtherMonths: true,
			selectOtherMonths: false,
		});
		/* tr.children("td").eq(1).text($(this).parents(".tab-pane").siblings().first().find("#customer").find("option:selected").text()); */
		/* var select = tr.children("td").eq(1).children("select");
		select.val($(this).parents(".tab-pane").siblings().first().find("#customer").val());
		select.chosen({no_results_text: "查无结果", search_contains: true});
		select.next().attr("style","width:100%;");
		select.next().find("input").attr("style","height:100%;"); */
		if(tr.prev().not("#incomeModel").html()==undefined){
			if($(this).parents(".tab-pane").siblings().first().find("#customer").html()==undefined){
				$.ajax({
			        type: "GET",  
			        contentType:"application/json;charset=utf-8",  
			        url:"${path }localTourManage/findCustomer",  
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
			tr.children("td").eq(1).html(tr.prev().not("#incomeModel").children("td").eq(1).html());
		}
		
	});
	/*保存报账*/
	$("#reimbursementApplication").click(function(){
		var tourId= $("#reimbursementApplication").parent().attr("id");
		if($("#headAmount").children("input").length==0){
			alert("重复报账")
		}else if("${sessionScope.isMice }"=="false"&&isNaN(parseFloat($("#headAmount").children("input").val()))){
			$("#reimbursementApplication").attr("data-dismiss","");
			alert("*号为必填项，请输入数字");
		}else{
			$("#reimbursementApplication").attr("data-dismiss","modal");
			var trs = $("#costs5").find("tbody").find("tr").not(".reimbursementTr");
			var costTables = new Array();
			var changeCostTables = new Array();
			var reimbursementCostTables = new Array();
			var newReimbursementCostTables = new Array();
			var reimbursementIncomeTables = new Array();
			$.each(trs,function(){
				if($(this).attr("class")=="blue"){
					var inputs = $(this).find("input");
					changeCostTables.push({id:$(this).attr("id"),
						supplierId:$(this).find("select").val(),
						reimbursement:inputs.eq(1).val()==""?0:inputs.eq(1).val(),
						bill:inputs.eq(2).prop("checked")});
				}else if($(this).attr("class")=="red"){
					var inputs = $(this).find("input");
					reimbursementCostTables.push({id:$(this).attr("id"),
						supplierId:$(this).find("select").val(),
						reimbursement:inputs.eq(1).val()==""?0:inputs.eq(1).val(),
						bill:inputs.eq(2).prop("checked")});
				}else{
					var inputs = $(this).find("input");
					costTables.push({id:$(this).attr("id"),
						supplierId:$(this).find("select").val(),
						reimbursement:inputs.eq(1).val()==""?0:inputs.eq(1).val(),
						bill:inputs.eq(2).prop("checked")});
				}
			});
			var reimbursementTr = $("#costs5").find("tr.reimbursementTr");
			$.each(reimbursementTr,function(){
				var inputs = $(this).find("input");
				var selects = $(this).find("select");
				newReimbursementCostTables.push({tourId:tourId,
												costDate:new Date(inputs.eq(0).val()),
												contentId:selects.eq(0).val(),
												supplierId:selects.eq(1).val(),
												cost:inputs.eq(3).val(),
												count:inputs.eq(4).val(),
												days:inputs.eq(5).val(),
												reimbursement:inputs.eq(6).val(),
												remark:inputs.eq(7).val(),
												bill:inputs.eq(8).prop("checked"),
												supplierScopeId:inputs.eq(9).val()});
			});
			var incomeTrs = $("#incomes5").find("tr.red");
			$.each(incomeTrs, function(){
				var inputs = $(this).find("input");
				reimbursementIncomeTables.push({id:$(this).attr("id"),
												tourId:tourId,
												customerAgencyId:inputs.eq(0).val(),
												income:inputs.eq(1).val(),
												remark:inputs.eq(2).val()});
			});
			
			var reimbursementTable = {tourId:tourId,headAmount:$("#headAmount").children("input").val()};
			var fullReimbursementViewModel = {costTables:costTables,
					changeCostTables:changeCostTables,
					reimbursementTable:reimbursementTable,
					reimbursementCostTables:reimbursementCostTables,
					newReimbursementCostTables:newReimbursementCostTables,
					reimbursementIncomeTables:reimbursementIncomeTables};
			var myData = JSON.stringify(fullReimbursementViewModel);
			$.ajax({
				type: "POST",  
		        contentType:"application/json;charset=utf-8",  
		        url:"${path }reimbursementManage/updateReimbursement",  
		        data:myData,  
		        dataType: "json",
		        async: false,
		        success:function(data){
		        }
			});
		}
	});
	
	/* 提交报账 */
	$("#auditingReimbursement").click(function(){
		var checkbox = $("#table").find("input:checked");
		if(checkbox.length==0){
			alert("请选择一个团队");
		}else if(checkbox.length>1){
			alert("只能选择一个团队");
		}else{
			$.ajax({
				type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"${path }reimbursementManage/auditingReimbursement",  
		        data:{tourId:checkbox.parent().parent().parent().attr("id")},  
		        dataType: "json",
		        async: false,
		        success:function(data){
					
		        }
			});
			changeButton("待核销");
			checkbox.parent().parent().siblings().eq(-2).text("待核销");
		}
	});
	
	/* 打印报账单 */
	$("#reimbursementPrintButton").click(function(){
		var checkbox = $("#table").find("input:checked");
		if(checkbox.parent().parent().siblings().eq(-2).text()!="已报账"&&checkbox.parent().parent().siblings().eq(-2).text()!="待核销"){
			alert("请先报账填写，并报账提交");
			return;
		}else if(checkbox.length==0){
			alert("请选择一个团队");
			return;
		}else if(checkbox.length>1){
			alert("只能选择一个团队");
			return;
		}else{
			var tourId = checkbox.parent().parent().parent().attr("id");
			if(tourId==$("#reimbursementApplication").parent().attr("id")){
				
			}else{
				$("#reimbursementApplication").parent().attr("id",tourId);
				var myData = {tourId:tourId};
				if(inited==false){
					init();
					inited = true;
				}
				$.ajax({
			        type: "GET",  
			        contentType:"application/json;charset=utf-8",  
			        url:"${path }reimbursementManage/checkReimbursement",  
			        data:myData,  
			        dataType: "json",  
			        async: false,  
			        success:function(data){
			        	if(data){
			        		find(myData);
			        	}else{
			        		alert("不能报账，预借发票金额大于实际收款金额");
			        		$("#reimbursement").attr("href","#");
			        	}
			        }
			    });
			}
		}
		
		
		var tbodys = $("#reimbursementPrintDiv").find("tbody").not(".printTable");
		tbodys.parent().hide();
		tbodys.find("tr td:nth-child(14)").hide();
		var tourNo = checkbox.parent().parent().next().text();
		var tourName = checkbox.parent().parent().next().next().text();
		$.each(tbodys, function(i){
			var trs = $(this).children("tr");
			var table;
			var total = 0;
			$.each(trs,function(index){
				/* 生成表格 */
				if(index%10==0){
					table = $('<div class="printFrame"><table><thead><tr><th style="width: 6%;">日期</th>	<th style="width: 8%;">内容</th><th style="width: 10%;">供应商*</th>	<th style="width: 5%;">成本</th><th style="width: 5%;">数量</th><th style="width: 5%;">天数</th>	<th style="width: 5%;">预估成本</th><th style="width: 5%;">已汇金额</th><th style="width: 5%;">报账金额</th>	<th style="width: 5%;">备注</th><th style="width: 5%;">借款</th><th style="width: 5%;">挂账</th><th style="width: 5%;">状态</th></tr></thead><tbody class="printTable"></tbody></table></div>');
					$("#reimbursementPrintDiv").append(table);
					if(i==0){
						table.prepend('<div class="h4"><div class="title">机票</div><div class="tourInfo">'+tourNo+'  '+tourName+'</div></div>');
					}else if(i==1){
						table.prepend('<div class="h4"><div class="title">订房</div><div class="tourInfo">'+tourNo+'  '+tourName+'</div></div>');
					}else if(i==2){
						table.prepend('<div class="h4"><div class="title">订餐</div><div class="tourInfo">'+tourNo+'  '+tourName+'</div></div>');
					}else if(i==3){
						table.prepend('<div class="h4"><div class="title">门票</div><div class="tourInfo">'+tourNo+'  '+tourName+'</div></div>');
					}else if(i==4){
						table.prepend('<div class="h4"><div class="title">订车</div><div class="tourInfo">'+tourNo+'  '+tourName+'</div></div>');
					}else if(i==5){
						table.prepend('<div class="h4"><div class="title">票务</div><div class="tourInfo">'+tourNo+'  '+tourName+'</div></div>');
					}else if(i==6){
						table.prepend('<div class="h4"><div class="title">综费</div><div class="tourInfo">'+tourNo+'  '+tourName+'</div></div>');
					}else if(i==7){
						table.prepend('<div class="h4"><div class="title">其他</div><div class="tourInfo">'+tourNo+'  '+tourName+'</div></div>');
					}
					/* 添加标题 */
					table.prepend('<div class="h3">团队报账单</div>');
					/* 添加签字栏 */
					table.append('<div><div class="autograph"></div><div class="autographYuan"></div><div class="autograph">总经理：</div><div class="autograph">中心经理：</div><div class="autograph">财务：</div><div class="autograph">经办人：<%=user.getRealName() %></div></div>');
					/* 重置合计数 */
					total = 0;
				}
				/* 隐藏或修改一些不合规的内容 */
				if(i==7){
					if($(this).find("td").eq(1).text()=="酒水"){
						$(this).find("td").eq(1).text("综费");
					}
				}
				
				/* 计算合计 */
				total = total + parseFloat($(this).find("input").eq(1).val());
				table.find(".autograph").eq(0).text("合计："+total.toFixed(2));
				table.find(".autographYuan").text("大写："+moneyTrun(total));
				
				/* 设置input为文字 */
				var inputs = $(this).find("input");
				$.each(inputs, function(){
					if($(this).parent().parent().prev().children("i").length>0){
						$(this).parent().parent().prev().html("是");
					}
					if($(this).attr("class")=="ace"){
						if(($(this).prop("checked"))){
							$(this).parent().html("是");
						}else{
							$(this).parent().html("");
						}
					}else if($(this).attr("type")!="hidden"){
						$(this).parent().html($(this).val());
					}
				});
				
				/* 添加此行 */
				table.children("table").append($(this));
				/* 补充行数 */
				if(index==trs.length-1){
					for (var int = 0; int < (parseInt((index+1)/10)+((index+1)%10>0?1:0))*10-(index+1); int++) {
						table.children("table").append('<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>')
					}
				}
			});
		});
		$("#reimbursementPrintDiv").find(".printTable").children("tr").attr("style","height:9%;")
		
		/* 添加各种表格 */
		/* $("#reimbursementPrintDiv").append("<h4>导游借款表</h4>");
		$("#reimbursementPrintDiv").append('<table class="printLoans">'+$("#loanTable").parent().html()+"</table>"); */
		$("#reimbursementPrintDiv").append('<div class="h4"><div class="title">收入</div><div class="tourInfo">'+tourNo+'  '+tourName+'</div></div>');
		$("#reimbursementPrintDiv").append('<table class="printIncomes">'+$("#incomes5").find("table").html()+"</table>");
		$("#reimbursementPrintDiv").append('<div class="h4">统计信息</div>');
		$("#reimbursementPrintDiv").append('<table class="printStatistical">'+$("#headAmount").parent().parent().parent().html()+"</table>");
		
		/* 修改人头为综费 */
		$("#reimbursementPrintDiv").find("#headAmount").prev().text("综费");
		
		/* 将input转为text */
		var inputs = $("#reimbursementPrintDiv").find("input");
		$.each(inputs, function(){
			if($(this).parent().parent().prev().children("i").length>0){
				$(this).parent().parent().prev().html("是");
			}
			if($(this).attr("class")=="ace"){
				if(($(this).prop("checked"))){
					$(this).parent().html("是");
				}else{
					$(this).parent().html("");
				}
			}else if($(this).attr("type")!="hidden"){
				$(this).parent().html($(this).val());
			}
		});
		/* 再次设置成本样式 */
		var tbodys = $("#reimbursementPrintDiv").find(".printTable");
		/* tbodys.find("tr td:nth-child(10)").hide();
		tbodys.find("tr td:nth-child(5)").hide();
		tbodys.find("tr td:nth-child(6)").hide(); */
		var thead = $("#reimbursementPrintDiv").find(".printTable").parent().children("thead");
		/* thead.find("tr th:nth-child(10)").hide();
		thead.find("tr th:nth-child(5)").hide();
		thead.find("tr th:nth-child(6)").hide(); */
		$("#reimbursementPrintDiv").find("#changeCostBlue").hide();
		$("#reimbursementPrintDiv").find("#reimbursementCostRed").hide();
		
		/* 收入样式 */
		$("#reimbursementPrintDiv").find(".printIncomes").find("tr th:nth-child(7)").hide();
		$("#reimbursementPrintDiv").find(".printIncomes").find("tr td:nth-child(7)").hide();
		
		/* 浏览器弹出提示框 */
		<%-- if(navigator.userAgent.indexOf("Firefox")>0){
			$("#printAlert").find(".modal-dialog").css("width","90%");
			$("#printAlert").find(".modal-content").append('<img style="width: 400px;" alt="提示1" src="<%=path %>resources/assets/images/print/print1.png">'+
					'<img style="width: 240px; margin-left: 30px;" alt="提示2" src="<%=path %>resources/assets/images/print/print2.png">'+
					'<img style="width: 400px; margin-left: 30px;" alt="提示3" src="<%=path %>resources/assets/images/print/print3.png">');
			$(this).attr("href","#printAlert");
		}else if(navigator.userAgent.indexOf("Chrome")>0){
			$("#printAlert").find(".modal-dialog").css("width","50%");
			$("#printAlert").find(".modal-content").append('<img style="width: 600px;" alt="提示1" src="<%=path %>resources/assets/images/print/print4.png">');
			$(this).attr("href","#printAlert");
		} --%>
		
		/* 打印PDF */
		var printDiv = $("#reimbursementPrintDiv").clone();
		printDiv.find("style").remove();
		printDiv.find(".tab-pane").remove();
		printDiv.find("#changeCostBlue").remove();
		printDiv.find("#reimbursementCostRed").remove();
		printDiv.find("input").remove();
		printDiv.find(".printIncomes").find("tr td:nth-child(7)").remove();
		printDiv.find(".printIncomes").find("tr th:nth-child(7)").remove();
		printDiv.find(".printFrame").find("tr td:nth-child(10)").remove();
		printDiv.find(".printFrame").find("tr th:nth-child(10)").remove();
		/* 设置选项 为文本*/
		$.each(printDiv.find("select"),function(){
			if($(this).parent().find("a").text()=="导游服务费"){
				$(this).parent().text("个人")
			}else{
				$(this).parent().text($(this).parent().find("a").text());
			}
		});
		/* 删除多余行 */
		$.each(printDiv.find(".printFrame").find("tr"),function(){
			var tds = $(this).find("td");
			if(parseFloat(tds.eq(8).text())==0&&tds.eq(11).text()=="可付"&&tds.eq(10).text()==""&&tds.eq(9).text()==""){
				$(this).html("<td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>");
			}
		});
		printDiv.prepend('<style type="text/css">.printFrame{height: 14cm}.printFrame table{height: 90%;}table{font-size: 10px;border-collapse: collapse;width: 100%;border-right:1px solid;border-bottom:1px solid;}td{padding-left: 2px;border-top: 1px solid;border-left: 1px solid;}.h3{font-size: 14px;text-align: center;margin: 0px;}.h4{font-size: 12px;padding-bottom: 10px;}.h4 .title{width:50%;float:left;display: inline-block;}.h4 .tourInfo{width:300px;float:right;display: inline-block;text-align: right;}#changeCostBlue{display: none;}#reimbursementCostRed{display: none;}.autograph{width:95px;font-size: 10px;display: inline-block;float:left;}.autographYuan{width:200px;font-size: 10px;display: inline-block;float:left;}.printIncomes tbody tr{height: 30px;}.printStatistical tbody tr{height: 30px;}</style>');
		var a = $(this);
		var tourNo = checkbox.parent().parent().siblings().eq(0).text();
		$.ajax({
			type: "POST",  
	        contentType:"application/json;charset=utf-8",
	        url:"${path }reimbursementManage/printReimbursement",
	        data:{tourNo:tourNo, html:"<div>"+printDiv.html()+"</div>"},
	        dataType: "json",  
	        async: false,  
	        success:function(data){
	        	if(data){
	        		a.attr("target","_blank");
	        		a.attr("href","${path }resources/pdfTemp/"+tourNo+"reimbursement.pdf");
	        	}else{
	        		a.attr("target","");
	        		alert("打印遇到问题,请联系管理员");
	        	}
	        }
		});
		
		/* $("#reimbursementPrintDiv").printArea({
	        mode       : "iframe",
	        standard   : "html5",
	        popTitle   : '',
	        popClose   : false,
	    }); */
		<%-- $("#reimbursementPrintDiv").printArea({
	        mode       : "popup",
	        standard   : "html5",
	        popTitle   : '团队报账单',
	        popClose   : false,
	        extraCss   : "<%=path %>resources/assets/css/print.css",
	        extraHead  : 'asd',
	        retainAttr : ["id","class","style"],
	        printDelay : 500, // tempo de atraso na impressao
	        printAlert : true,
	        printMsg   : 'Aguarde a impressão'
	    }); --%>
	    
		$("#reimbursementPrintDiv").find("table").show();
		$("#reimbursementPrintDiv").find("thead").show();
		$("#reimbursementPrintDiv").find("thead th").show();
		$("#reimbursementPrintDiv").children(".h3").remove();
		$("#reimbursementPrintDiv").children(".h4").remove();
		$(".printLoans").remove();
		$(".printIncomes").remove();
		$(".printStatistical").remove();
		/* alert("正在打印...\n如需调整打印页面请在浏览器的“文件”-“页面设置”-“页边距和页眉/页脚”中设置，\n建议将页边距顶、底、左、右属性调整为5，将页眉页脚左、中、右全部调整为“空白”"); */
		
	});
	
	/* 打印借款凭证 */
	$("#lendPrintButton").click(function(){
		var checkbox = $("#table").find("input:checked");
		if(checkbox.length==0){
			alert("请选择一个团队");
			$(this).attr("href","#");
		}else if(checkbox.length>1){
			alert("只能选择一个团队");
			$(this).attr("href","#");
		}else{
			/* 初始化选项 */
			var a = $(this);
			var myData = {tourId:checkbox.parent().parent().parent().attr("id"),type:"lend"}
			$.ajax({
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"${path }localTourManage/printVoucher",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	var tds = checkbox.parent().parent().siblings();
		        	if(data.loans.length==0){
		        		alert("暂无可打印的导游借款凭证，请确认借款是否已经经过审批或是否已经借出款项");
		        		a.attr("href","#");
		        	}else{
		        		var total = 0;
		        		var printCount = 0;
		        		$("#lendPrintModel").find("#printArea").children("table").not("#printTable").find("td").eq(10).text("借款方式");
		        		$("#lendPrintModel").find("#printTable").remove();
		        		var printTable = $('<table id="printTable" class="table table-striped table-bordered table-hover no-margin"><tbody></tbody></table>');
		        		printTable.append('<tr><td style="width: 1%;"><label><input class="ace selectAll" type="checkbox"><span class="lbl"></span></label></td><td>日期</td><td>金额</td><td>备注</td><td>状态</td><td>财务</td><td>申请人</td><td>经理</td><td>总经理</td></tr>')
		        		$.each(data.loans,function(){
		        			total = total+this.loanTable.loanAmount;
		        			if(printCount<this.loanTable.printCount){
		        				printCount = this.loanTable.printCount;
		        			}
		        			var remark = this.loanTable.remark==null?"":this.loanTable.remark;
		        			printTable.append('<tr id="'+this.loanTable.id+'"><td><label><input class="ace" type="checkbox"><span class="lbl"></span></label></td><td>'+this.loanTable.loanDate+'</td><td>'+this.loanTable.loanAmount.toFixed(2)+'</td><td>'+remark+'</td><td>'+this.status+'</td><td>'+this.lenderRealName+'</td><td>'+this.applicationerRealName+'</td><td>'+this.managerName+'</td><td id="'+this.loanTable.printCount+'">'+this.bossName+'</td></tr>');
		        		});
		        		printCount++;
		        		var printInfos = $("#lendPrintModel").find("#printArea").find(".printInfo");
		        		printInfos.eq(0).text(tds.eq(0).text());
		        		printInfos.eq(1).text(tds.eq(1).text());
		        		printInfos.eq(2).text(data.deptName);
		        		printInfos.eq(3).text(total.toFixed(2));
		        		printInfos.eq(4).text(moneyTrun(total.toFixed(2)));
		        		var date = new Date();
		        		printInfos.eq(5).text(date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate());
		        		printInfos.eq(6).text('<%=user.getRealName()%>');
		        		$("#lendPrintModel").find("#printArea").find("span").text("第"+printCount+"次打印");
		        		$("#lendPrintModel").find("#printArea").append(printTable)
		        		$("#lendPrintModel").find("#printArea").find("input").val("");
		        		a.attr("href","#lendPrintModel");
		        		/* 注册事件 */
			        	printTable.find("tr:not(:first) td:not(tr td:nth-child(1))").click(function(){
			        		$(this).parent().find("input.ace").prop("checked",!$(this).parent().find("input.ace").prop("checked"));
			        		var printCount = 0;
			        		var amount = 0;
			        		$.each(printTable.find("input.ace:checked"),function(){
			        			var tds = $(this).parent().parent().siblings();
			        			if(tds.eq(-1).attr("id")>printCount){
			        				printCount = tds.eq(-1).attr("id");
			        			}
			        			amount = amount + parseFloat(tds.eq(1).text());
			        		});
			        		$("#lendPrintModel").find("#printArea").find("span").eq(0).text("第"+(parseInt(printCount)+1)+"次打印");
				        	/* 设置总金额 */
				        	$("#lendPrintModel").find("#printArea").find(".printInfo").eq(3).text(amount.toFixed(2));
				        	$("#lendPrintModel").find("#printArea").find(".printInfo").eq(4).text(moneyTrun(amount));
			        	});
			        	printTable.find("input.selectAll").click(function(){
			        		printTable.find("input.ace").prop("checked",$(this).prop("checked"));
			        		var printCount = 0;
			        		var amount = 0;
			        		$.each(printTable.find("input.ace:checked:not(.selectAll)"),function(){
			        			var tds = $(this).parent().parent().siblings();
			        			if(tds.eq(-1).attr("id")>printCount){
			        				printCount = tds.eq(-1).attr("id");
			        			}
			        			amount = amount + parseFloat(tds.eq(1).text());
			        		});
			        		$("#lendPrintModel").find("#printArea").find("span").eq(0).text("第"+(parseInt(printCount)+1)+"次打印");
				        	/* 设置总金额 */
				        	$("#lendPrintModel").find("#printArea").find(".printInfo").eq(3).text(amount.toFixed(2));
				        	$("#lendPrintModel").find("#printArea").find(".printInfo").eq(4).text(moneyTrun(amount));
			        	});
			        	printTable.find("input.ace:not(.selectAll)").click(function(){
			        		var printCount = 0;
			        		var amount = 0;
			        		$.each(printTable.find("input.ace:checked"),function(){
			        			var tds = $(this).parent().parent().siblings();
			        			if(tds.eq(-1).attr("id")>printCount){
			        				printCount = tds.eq(-1).attr("id");
			        			}
			        			amount = amount + parseFloat(tds.eq(1).text());
			        		});
			        		$("#lendPrintModel").find("#printArea").find("span").eq(0).text("第"+(parseInt(printCount)+1)+"次打印");
				        	/* 设置总金额 */
				        	$("#lendPrintModel").find("#printArea").find(".printInfo").eq(3).text(amount.toFixed(2));
				        	$("#lendPrintModel").find("#printArea").find(".printInfo").eq(4).text(moneyTrun(amount));
			        	});
		        	}
		        }
			});
		}
	});
	$("#lendPrint").click(function(){
		if($("#lendPrintModel").find("#printArea").find(".red").length==0){
			if($("#printTable").find("input:not(.selectAll):checked").length==0){
				alert("请选择一个打印项");
				$("#lendPrint").attr("data-dismiss","");
			}else{
				$("#lendPrint").attr("data-dismiss","modal");
				var ids = new Array();
				$.each($("#printTable input.ace:not(.selectAll)"),function(){
					if($(this).prop("checked")){
						ids.push($(this).parent().parent().parent().attr("id"));
					}else{
						$(this).parent().parent().parent().hide();
					}
				});
				var myData = {ids:ids.toString()}
				$.ajax({
			        type: "GET",  
			        contentType:"application/json;charset=utf-8",  
			        url:"${path }localTourManage/printCountPlus",
			        data:myData,  
			        dataType: "json",  
			        async: false,  
			        success:function(data){
			        }
				});
				$("#printTable").find("tr td:nth-child(1)").hide();
				var printHtml = $("#lendPrintModel").find("#printArea");
				printHtml.prepend("<h3>导游借款凭证</h3>").printArea({
			        mode       : "iframe",
			        standard   : "html5",
			        popTitle   : '导游借款凭证',
			        popClose   : false,
			    });
				printHtml.find("h3").remove();
				$("#printTable").find("tr,td").show();
				alert("正在打印...\n如需调整打印页面请在浏览器的“文件”-“页面设置”-“页边距和页眉/页脚”中设置，\n建议将页边距顶、底、左、右属性调整为5，将页眉页脚左、中、右全部调整为“空白”");
				printHtml.find("span").text("");
				$("#lendPrintModel").find("#printTable").remove();
			}
		}else{
			if($("#printTable").find("input:not(.selectAll):checked").length==0){
				alert("请选择一个打印项");
				$("#lendPrint").attr("data-dismiss","");
			}else{
				$("#lendPrint").attr("data-dismiss","modal");
				var costIds = new Array();
				var changeCostIds = new Array();
				$.each($("#printTable input.ace:not(.selectAll)"),function(){
					if($(this).prop("checked")){
						if($(this).parent().parent().parent().attr("class")=="blue"){
							changeCostIds.push($(this).parent().parent().parent().attr("id"));
						}else{
							costIds.push($(this).parent().parent().parent().attr("id"));
						}
					}else{
						$(this).parent().parent().parent().hide();
					}
				});
				var myData = {costIds:costIds.toString(), changeCostIds:changeCostIds.toString()}
				$.ajax({
			        type: "GET",  
			        contentType:"application/json;charset=utf-8",  
			        url:"${path }localTourManage/printCountPlus2",
			        data:myData,  
			        dataType: "json",  
			        async: false,  
			        success:function(data){
			        }
				});
				$("#printTable").find("tr td:nth-child(1)").hide();
				var trs = $("#printTable").find("tr:not(:hidden)");
				$.each(trs, function(i){
					if($(this).attr("class")=="red"&&(trs.eq(i+2).attr("class")==undefined&&trs.eq(i+2).attr("id")==undefined||trs.eq(i+2).attr("class")=="red"&&trs.eq(i+2).attr("id")==undefined)){
						$(this).hide();
						$(this).next().hide();
					}
				});
				var printHtml = $("#lendPrintModel").find("#printArea");
				printHtml.prepend("<h3>供应商付款凭证</h3>").printArea({
			        mode       : "iframe",
			        standard   : "html5",
			        popTitle   : '供应商付款凭证',
			        popClose   : false,
			    });
				printHtml.find("h3").remove();
				$("#printTable").find("tr,td").show();
				alert("正在打印...\n如需调整打印页面请在浏览器的“文件”-“页面设置”-“页边距和页眉/页脚”中设置，\n建议将页边距顶、底、左、右属性调整为5，将页眉页脚左、中、右全部调整为“空白”");
				printHtml.find("span").text("");
				$("#lendPrintModel").find("#printTable").remove();
			}
		}
		
	});
	/* 打印供应商付款凭证 */
	$("#payPrintButton").click(function(){
		var checkbox = $("#table").find("input:checked");
		if(checkbox.length==0){
			alert("请选择一个团队");
			$(this).attr("href","#");
		}else if(checkbox.length>1){
			alert("只能选择一个团队");
			$(this).attr("href","#");
		}else{
			/* 初始化选项 */
			var a = $(this);
			var myData = {tourId:checkbox.parent().parent().parent().attr("id"),type:"pay"}
			$.ajax({
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"${path }localTourManage/printVoucher",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	var tds = checkbox.parent().parent().siblings();
		        	if(data.pays==0){
		        		alert("暂无可打印的供应商付款凭证，请确认付款是否已经经过审批或是否已经付出款项");
		        		a.attr("href","#");
		        	}else{
		        		$("#lendPrintModel").find("#printArea").children("table").not("#printTable").find("td").eq(10).text("付款方式");
		        		var total = 0;
		        		var printCount = 0;
		        		$("#lendPrintModel").find("#printTable").remove();
		        		var printTable = $('<table id="printTable" class="table table-striped table-bordered table-hover no-margin"><tbody></tbody></table>');
		        		$.each(data.pays,function(){
		        			var bankName = this.supplierTable.bankName==null?"":this.supplierTable.bankName;
		        			var bankNo = this.supplierTable.bankNo==null?"":this.supplierTable.bankNo;
		        			printTable.append('<tr class="red"><td style="width: 1%;"><label><input class="ace selectAll" type="checkbox"><span class="lbl"></span></label></td><td>供应商名</td><td>'+this.supplierTable.supplierName+'</td><td>开户行</td><td>'+bankName+'</td><td>账号</td><td>'+bankNo+'</td><td></td></tr>')
		        			printTable.append('<tr><td></td><td>日期</td><td>金额</td><td>备注</td><td>状态</td><td>申请人</td><td>经理</td><td>总经理</td></tr>')
		        			$.each(this.costs,function(){
		        				total = total+this.costTable.realCost;
		        				if(printCount<this.costTable.printCount){
			        				printCount = this.costTable.printCount;
			        			}
		        				var remark = this.costTable.remark==null?"":this.costTable.remark;
		        				var costDate = this.costTable.costDate==null?"":this.costTable.costDate;
			        			printTable.append('<tr id="'+this.costTable.id+'"><td><label><input class="ace" type="checkbox"><span class="lbl"></span></label></td><td>'+costDate+'</td><td>'+this.costTable.realCost.toFixed(2)+'</td><td>'+remark+'</td><td>'+this.payStatus+'</td><td>'+this.payApplicationerRealName+'</td><td>'+this.managerName+'</td><td id="'+this.costTable.printCount+'">'+this.bossName+'</td></tr>');
		        			});
							$.each(this.changeCosts,function(){
								total = total+this.costTable.realCost;
		        				if(printCount<this.costTable.printCount){
			        				printCount = this.costTable.printCount;
			        			}
		        				var remark = this.costTable.remark==null?"":this.costTable.remark;
		        				var costDate = this.costTable.costDate==null?"":this.costTable.costDate;
			        			printTable.append('<tr class="blue" id="'+this.costTable.id+'"><td><label><input class="ace" type="checkbox"><span class="lbl"></span></label></td><td>'+costDate+'</td><td>'+this.costTable.realCost.toFixed(2)+'</td><td>'+remark+'</td><td>'+this.payStatus+'</td><td>'+this.payApplicationerRealName+'</td><td>'+this.managerName+'</td><td id="'+this.costTable.printCount+'">'+this.bossName+'</td></tr>');
		        			});
		        		});
		        		printCount++;
		        		var printInfos = $("#lendPrintModel").find("#printArea").find(".printInfo");
		        		printInfos.eq(0).text(tds.eq(0).text());
		        		printInfos.eq(1).text(tds.eq(1).text());
		        		printInfos.eq(2).text(data.deptName);
		        		printInfos.eq(3).text(total.toFixed(2));
		        		printInfos.eq(4).text(moneyTrun(total.toFixed(2)));
		        		var date = new Date();
		        		printInfos.eq(5).text(date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate());
		        		printInfos.eq(6).text('<%=user.getRealName()%>');
		        		$("#lendPrintModel").find("#printArea").find("span").text("第"+printCount+"次打印");
		        		$("#lendPrintModel").find("#printArea").append(printTable)
		        		$("#lendPrintModel").find("#printArea").find("input").val("");
		        		a.attr("href","#lendPrintModel");
		        		/* 注册事件 */
			        	printTable.find("tr:not(.red) td:not(tr td:nth-child(1))").click(function(){
			        		$(this).parent().find("input.ace").prop("checked",!$(this).parent().find("input.ace").prop("checked"));
			        		var printCount = 0;
			        		var amount = 0;
			        		$.each(printTable.find("input.ace:checked"),function(){
			        			var tds = $(this).parent().parent().siblings();
			        			if(tds.eq(-1).attr("id")>printCount){
			        				printCount = tds.eq(-1).attr("id");
			        			}
			        			amount = amount + parseFloat(tds.eq(1).text());
			        		});
			        		$("#lendPrintModel").find("#printArea").find("span").eq(0).text("第"+(parseInt(printCount)+1)+"次打印");
				        	/* 设置总金额 */
				        	$("#lendPrintModel").find("#printArea").find(".printInfo").eq(3).text(amount.toFixed(2));
				        	$("#lendPrintModel").find("#printArea").find(".printInfo").eq(4).text(moneyTrun(amount));
			        	});
			        	printTable.find("input.selectAll").click(function(){
			        		printTable.find("input.ace").prop("checked",$(this).prop("checked"));
			        		var printCount = 0;
			        		var amount = 0;
			        		$.each(printTable.find("input.ace:checked:not(.selectAll)"),function(){
			        			var tds = $(this).parent().parent().siblings();
			        			if(tds.eq(-1).attr("id")>printCount){
			        				printCount = tds.eq(-1).attr("id");
			        			}
			        			amount = amount + parseFloat(tds.eq(1).text());
			        		});
			        		$("#lendPrintModel").find("#printArea").find("span").eq(0).text("第"+(parseInt(printCount)+1)+"次打印");
				        	/* 设置总金额 */
				        	$("#lendPrintModel").find("#printArea").find(".printInfo").eq(3).text(amount.toFixed(2));
				        	$("#lendPrintModel").find("#printArea").find(".printInfo").eq(4).text(moneyTrun(amount));
			        	});
			        	printTable.find("input.ace:not(.selectAll)").click(function(){
			        		var printCount = 0;
			        		var amount = 0;
			        		$.each(printTable.find("input.ace:checked"),function(){
			        			var tds = $(this).parent().parent().siblings();
			        			if(tds.eq(-1).attr("id")>printCount){
			        				printCount = tds.eq(-1).attr("id");
			        			}
			        			amount = amount + parseFloat(tds.eq(1).text());
			        		});
			        		$("#lendPrintModel").find("#printArea").find("span").eq(0).text("第"+(parseInt(printCount)+1)+"次打印");
				        	/* 设置总金额 */
				        	$("#lendPrintModel").find("#printArea").find(".printInfo").eq(3).text(amount.toFixed(2));
				        	$("#lendPrintModel").find("#printArea").find(".printInfo").eq(4).text(moneyTrun(amount));
			        	});
		        	}
		        }
			});
		}
	});
	
	/* 打印缴款单 */
	$("#incomePrintButton").click(function(){
		var checkbox = $("#table").find("input:checked");
		if(checkbox.parent().parent().siblings().eq(-2).text()=="新建"||checkbox.parent().parent().siblings().eq(-2).text()=="已提交"){
			alert("中心经理还未报送财务，暂时不能收款");
		}else if(checkbox.length==0){
			alert("请选择一个团队");
			$(this).attr("href","#");
		}else if(checkbox.length>1){
			alert("只能选择一个团队");
			$(this).attr("href","#");
		}else{
			/* 初始化选项 */
			var a = $(this);
			var myData = {tourId:checkbox.parent().parent().parent().attr("id"),type:"income"}
			$.ajax({
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"${path }localTourManage/printVoucher",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	var tds = checkbox.parent().parent().siblings();
	        		var printInfos = $("#incomePrintModel").find(".printInfo");
	        		printInfos.eq(0).text(tds.eq(0).text());
	        		printInfos.eq(1).text(tds.eq(1).text());
	        		printInfos.eq(2).text(data.deptName);
	        		printInfos.eq(3).text(data.customerAgencyName);
	        		printInfos.eq(4).prev().prev().children("input").val(data.incomeTotal);
	        		printInfos.eq(4).text(moneyTrun(data.incomeTotal));
	        		printInfos.eq(5).text('<%=user.getRealName()%>');
	        		a.attr("href","#incomePrintModel");
		        }
			});
		}
	});
	/* 失去焦点设置大写 */
	$("#incomePrintModel").find("input").eq(2).blur(function(){
		$(this).parent().next().next().text(moneyTrun($(this).val()));
	});
	/* 打印缴款单按钮 */
	$("#incomePrint").click(function(){
		var printHtml = $("#incomePrintModel").find("#printArea");
		printHtml.prepend("<h3>缴款单</h3>").printArea({
	        mode       : "iframe",
	        standard   : "html5",
	        popTitle   : '缴款单',
	        popClose   : false,
	    });
		printHtml.find("h3").remove();
		printHtml.find("span").text("");
		alert("正在打印...\n如需调整打印页面请在浏览器的“文件”-“页面设置”-“页边距和页眉/页脚”中设置，\n建议将页边距顶、底、左、右属性调整为5，将页眉页脚左、中、右全部调整为“空白”");
	});
	
	/* 打印预借发票单 */
	$("#invoicePrintButton").click(function(){
		var checkbox = $("#table").find("input:checked");
		if(checkbox.length==0){
			alert("请选择一个团队");
			$(this).attr("href","#");
		}else if(checkbox.length>1){
			alert("只能选择一个团队");
			$(this).attr("href","#");
		}else{
			var a = $(this);
			var myData = {tourId:checkbox.parent().parent().parent().attr("id"),type:"loanInvoice"}
			$.ajax({
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"${path }localTourManage/printVoucher",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	if(data.loanInvoices.length==0){
		        		alert("暂无可打印的预借发票单，请确认是否已经经过审批或是否已经开出发票");
		    			$(this).attr("href","#");
		        	}else{
		        		$("#invoicePrintModel").find("#printTable").remove();
		        		var tds = checkbox.parent().parent().siblings();
		        		var printInfos = $("#invoicePrintModel").find(".printInfo");
		        		var total = 0;
		        		var printTable = $('<table id="printTable" class="table table-striped table-bordered table-hover no-margin"><thead><tr><th><label><input class="ace selectAll" value="" type="checkbox"><span class="lbl"></span></label></th><th style="width: 10%;">日期</th><th style="width: 20%;">抬头</th><th style="width: 10%;">内容</th><th style="width: 10%;">金额</th><th style="width: 40%;">发票信息</th><th style="width: 10%;">审批人</th></thead><tbody></tbody></table>');
		        		var tbody = printTable.children("tbody");
		        		$.each(data.loanInvoices, function(){
		        			total = total + this.loanInvoiceTable.invoiceAmount;
		        			tbody.append('<tr id="'+this.loanInvoiceTable.id+'">'+
		        								'<td><label><input class="ace" value="" type="checkbox"><span class="lbl"></span></label></td>'+
		        								'<td>'+this.loanInvoiceTable.issueDate+'</td>'+
		        								'<td>'+this.customerAgencyName+'</td>'+
		        								'<td>'+this.loanInvoiceTable.invoiceContent+'</td>'+
		        								'<td>'+this.loanInvoiceTable.invoiceAmount.toFixed(2)+'</td>'+
		        								'<td>'+this.loanInvoiceTable.remark+'</td>'+
		        								'<td id="'+(this.loanInvoiceTable.printCount==null?0:this.loanInvoiceTable.printCount)+'">'+this.managerName+'</td>'+
		        						'</tr>');
		        		});
		        		$("#invoicePrintModel").find("#printArea").append(printTable);
		        		printInfos.eq(0).text(tds.eq(0).text());
		        		printInfos.eq(1).text(tds.eq(1).text());
		        		printInfos.eq(2).text(data.deptName);
		        		printInfos.eq(3).text(data.loanInvoices[0].customerAgencyName);
		        		printInfos.eq(4).text(total.toFixed(2));
		        		printInfos.eq(5).text(moneyTrun(total));
		        		var date = new Date();
		        		printInfos.eq(6).text(date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate());
		        		printInfos.eq(7).text('<%=user.getRealName()%>');
		        		a.attr("href","#invoicePrintModel");
		        		
		        		/* 注册事件 */
			        	printTable.find("tr:not(.red) td:not(tr td:nth-child(1))").click(function(){
			        		$(this).parent().find("input.ace").prop("checked",!$(this).parent().find("input.ace").prop("checked"));
			        		var printCount = 0;
			        		var amount = 0;
			        		$.each(printTable.find("input.ace:checked"),function(){
			        			var tds = $(this).parent().parent().siblings();
			        			if(tds.eq(-1).attr("id")>printCount){
			        				printCount = tds.eq(-1).attr("id");
			        			}
			        			amount = amount + parseFloat(tds.eq(3).text());
			        		});
			        		$("#invoicePrintModel").find("#printArea").find("span").eq(0).text("第"+(parseInt(printCount)+1)+"次打印");
				        	/* 设置总金额 */
				        	$("#invoicePrintModel").find("#printArea").find(".printInfo").eq(4).text(amount.toFixed(2));
				        	$("#invoicePrintModel").find("#printArea").find(".printInfo").eq(5).text(moneyTrun(amount));
			        	});
			        	printTable.find("input.selectAll").click(function(){
			        		printTable.find("input.ace").prop("checked",$(this).prop("checked"));
			        		var printCount = 0;
			        		var amount = 0;
			        		$.each(printTable.find("input.ace:checked:not(.selectAll)"),function(){
			        			var tds = $(this).parent().parent().siblings();
			        			if(tds.eq(-1).attr("id")>printCount){
			        				printCount = tds.eq(-1).attr("id");
			        			}
			        			amount = amount + parseFloat(tds.eq(3).text());
			        		});
			        		$("#invoicePrintModel").find("#printArea").find("span").eq(0).text("第"+(parseInt(printCount)+1)+"次打印");
				        	/* 设置总金额 */
				        	$("#invoicePrintModel").find("#printArea").find(".printInfo").eq(4).text(amount.toFixed(2));
				        	$("#invoicePrintModel").find("#printArea").find(".printInfo").eq(5).text(moneyTrun(amount));
			        	});
			        	printTable.find("input.ace:not(.selectAll)").click(function(){
			        		var printCount = 0;
			        		var amount = 0;
			        		$.each(printTable.find("input.ace:checked"),function(){
			        			var tds = $(this).parent().parent().siblings();
			        			if(tds.eq(-1).attr("id")>printCount){
			        				printCount = tds.eq(-1).attr("id");
			        			}
			        			amount = amount + parseFloat(tds.eq(3).text());
			        		});
			        		$("#invoicePrintModel").find("#printArea").find("span").eq(0).text("第"+(parseInt(printCount)+1)+"次打印");
				        	/* 设置总金额 */
				        	$("#invoicePrintModel").find("#printArea").find(".printInfo").eq(4).text(amount.toFixed(2));
				        	$("#invoicePrintModel").find("#printArea").find(".printInfo").eq(5).text(moneyTrun(amount));
			        	});
		        	}
		        }
			});
		}
	});
	
	/* 打印预借发票按钮 */
	$("#invoicePrint").click(function(){
		if($("#invoicePrintModel").find("#printArea").find("input:not(.selectAll):checked").length==0){
			alert("请选择一个打印项");
			$("#invoicePrint").attr("data-dismiss","");
		}else{
			$("#invoicePrint").attr("data-dismiss","modal");
			var ids = new Array();
			$.each($("#invoicePrintModel #printTable input.ace:not(.selectAll)"),function(){
				if($(this).prop("checked")){
					ids.push($(this).parent().parent().parent().attr("id"));
				}else{
					$(this).parent().parent().parent().hide();
				}
			});
			var myData = {ids:ids.toString(),type:"invoice"}
			$.ajax({
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"${path }localTourManage/printCountPlus",
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        }
			});
			$("#invoicePrintModel").find("#printTable").find("tr td:nth-child(1),tr th:nth-child(1)").hide();
			var printHtml = $("#invoicePrintModel").find("#printArea");
			printHtml.prepend("<h3>预借发票单</h3>").printArea({
		        mode       : "iframe",
		        standard   : "html5",
		        popTitle   : '导游借款凭证',
		        popClose   : false,
		    });
			printHtml.find("h3").remove();
			$("#invoicePrintModel").find("#printTable").find("tr,td").show();
			alert("正在打印...\n如需调整打印页面请在浏览器的“文件”-“页面设置”-“页边距和页眉/页脚”中设置，\n建议将页边距顶、底、左、右属性调整为5，将页眉页脚左、中、右全部调整为“空白”");
		} 
	});
	
	/* 打印退款单 */
	$("#refundPrintButton").click(function(){
		var checkbox = $("#table").find("input:checked");
		if(checkbox.length==0){
			alert("请选择一个团队");
			$(this).attr("href","#");
		}else if(checkbox.length>1){
			alert("只能选择一个团队");
			$(this).attr("href","#");
		}else{
			var a = $(this);
			var myData = {tourId:checkbox.parent().parent().parent().attr("id"),type:"refund"}
			$.ajax({
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"${path }localTourManage/printVoucher",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	if(data.refunds.length==0){
		        		alert("暂无可打印的预借发票单，请确认是否已经经过审批或是否已经退款");
		    			$(this).attr("href","#");
		        	}else{
		        		$("#refundPrintModel").find("#printTable").remove();
		        		var tds = checkbox.parent().parent().siblings();
		        		var printInfos = $("#refundPrintModel").find(".printInfo");
		        		var total = 0;
		        		var printTable = $('<table id="printTable" class="table table-striped table-bordered table-hover no-margin"><thead><tr><td><label><input class="ace selectAll" value="" type="checkbox"><span class="lbl"></span></label></td><td style="width: 10%;">日期</td><td style="width: 20%;">客户</td><td style="width: 10%;">退款内容</td><td style="width: 10%;">退款方式</td><td style="width: 10%;">金额</td><td style="width: 20%;">退款账户信息</td><td style="width: 10%;">经理</td><td style="width: 10%;">总经理</td></tr></thead><tbody></tbody></table>');
		        		var tbody = printTable.children("tbody");
		        		$.each(data.refunds, function(){
		        			total = total + this.refundTable.refundAmount;
		        			tbody.append('<tr id="'+this.refundTable.id+'">'+
		        								'<td><label><input class="ace" value="" type="checkbox"><span class="lbl"></span></label></td>'+
		        								'<td>'+this.refundTable.refundDate+'</td>'+
		        								'<td>'+this.customerAgencyName+'</td>'+
		        								'<td>'+this.refundTable.refundContent+'</td>'+
		        								'<td>'+this.refundTable.refundWays+'</td>'+
		        								'<td>'+this.refundTable.refundAmount.toFixed(2)+'</td>'+
		        								'<td>'+this.refundTable.remark+'</td>'+
		        								'<td>'+this.managerName+'</td>'+
		        								'<td id="'+(this.refundTable.printCount==null?0:this.refundTable.printCount)+'">'+this.bossName+'</td>'+
		        						'</tr>');
		        		});
		        		$("#refundPrintModel").find("#printArea").append(printTable);
		        		printInfos.eq(0).text(tds.eq(0).text());
		        		printInfos.eq(1).text(tds.eq(1).text());
		        		printInfos.eq(2).text(data.deptName);
		        		printInfos.eq(3).text(data.refunds[0].customerAgencyName);
		        		printInfos.eq(4).text(total.toFixed(2));
		        		printInfos.eq(5).text(moneyTrun(total));
		        		var date = new Date();
		        		printInfos.eq(6).text(date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate());
		        		printInfos.eq(7).text('<%=user.getRealName()%>');
		        		a.attr("href","#refundPrintModel");
		        		/* 注册事件 */
			        	printTable.find("tr:not(.red) td:not(tr td:nth-child(1))").click(function(){
			        		$(this).parent().find("input.ace").prop("checked",!$(this).parent().find("input.ace").prop("checked"));
			        		var printCount = 0;
			        		var amount = 0;
			        		$.each(printTable.find("input.ace:checked"),function(){
			        			var tds = $(this).parent().parent().siblings();
			        			if(tds.eq(-1).attr("id")>printCount){
			        				printCount = tds.eq(-1).attr("id");
			        			}
			        			amount = amount + parseFloat(tds.eq(3).text());
			        		});
			        		$("#refundPrintModel").find("#printArea").find("span").eq(0).text("第"+(parseInt(printCount)+1)+"次打印");
				        	/* 设置总金额 */
				        	$("#refundPrintModel").find("#printArea").find(".printInfo").eq(4).text(amount.toFixed(2));
				        	$("#refundPrintModel").find("#printArea").find(".printInfo").eq(5).text(moneyTrun(amount));
			        	});
			        	printTable.find("input.selectAll").click(function(){
			        		printTable.find("input.ace").prop("checked",$(this).prop("checked"));
			        		var printCount = 0;
			        		var amount = 0;
			        		$.each(printTable.find("input.ace:checked:not(.selectAll)"),function(){
			        			var tds = $(this).parent().parent().siblings();
			        			if(tds.eq(-1).attr("id")>printCount){
			        				printCount = tds.eq(-1).attr("id");
			        			}
			        			amount = amount + parseFloat(tds.eq(3).text());
			        		});
			        		$("#refundPrintModel").find("#printArea").find("span").eq(0).text("第"+(parseInt(printCount)+1)+"次打印");
				        	/* 设置总金额 */
				        	$("#refundPrintModel").find("#printArea").find(".printInfo").eq(4).text(amount.toFixed(2));
				        	$("#refundPrintModel").find("#printArea").find(".printInfo").eq(5).text(moneyTrun(amount));
			        	});
			        	printTable.find("input.ace:not(.selectAll)").click(function(){
			        		var printCount = 0;
			        		var amount = 0;
			        		$.each(printTable.find("input.ace:checked"),function(){
			        			var tds = $(this).parent().parent().siblings();
			        			if(tds.eq(-1).attr("id")>printCount){
			        				printCount = tds.eq(-1).attr("id");
			        			}
			        			amount = amount + parseFloat(tds.eq(3).text());
			        		});
			        		$("#refundPrintModel").find("#printArea").find("span").eq(0).text("第"+(parseInt(printCount)+1)+"次打印");
				        	/* 设置总金额 */
				        	$("#refundPrintModel").find("#printArea").find(".printInfo").eq(4).text(amount.toFixed(2));
				        	$("#refundPrintModel").find("#printArea").find(".printInfo").eq(5).text(moneyTrun(amount));
			        	});
		        	}
		        }
			});
		} 
	});
	
	/* 打印退款按钮 */
	$("#refundPrint").click(function(){
		if($("#refundPrintModel").find("#printArea").find("input:not(.selectAll):checked").length==0){
			alert("请选择一个打印项");
			$("#refundPrint").attr("data-dismiss","");
		}else{
			$("#refundPrint").attr("data-dismiss","modal");
			var ids = new Array();
			$.each($("#refundPrintModel #printTable input.ace:not(.selectAll)"),function(){
				if($(this).prop("checked")){
					ids.push($(this).parent().parent().parent().attr("id"));
				}else{
					$(this).parent().parent().parent().hide();
				}
			});
			var myData = {ids:ids.toString(),type:"refund"}
			$.ajax({
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"${path }localTourManage/printCountPlus",
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        }
			});
			$("#refundPrintModel").find("#printTable").find("tr td:nth-child(1),tr th:nth-child(1)").hide();
			var printHtml = $("#refundPrintModel").find("#printArea");
			printHtml.prepend("<h3>退款单</h3>").printArea({
		        mode       : "iframe",
		        standard   : "html5",
		        popTitle   : '退款单',
		        popClose   : false,
		    });
			printHtml.find("h3").remove();
			$("#refundPrintModel").find("#printTable").find("tr,td").show();
			alert("正在打印...\n如需调整打印页面请在浏览器的“文件”-“页面设置”-“页边距和页眉/页脚”中设置，\n建议将页边距顶、底、左、右属性调整为5，将页眉页脚左、中、右全部调整为“空白”");
		} 
	});
	
	/* 数字转汉字大写 */
	function moneyTrun(num) {  
        var strOutput = "";  
        var strUnit = '仟佰拾亿仟佰拾万仟佰拾元角分';  
        num += "00";  
        var intPos = num.indexOf('.');  
        if (intPos >= 0)  
        num = num.substring(0, intPos) + num.substr(intPos + 1, 2);  
        strUnit = strUnit.substr(strUnit.length - num.length);  
        for (var i=0; i < num.length; i++)  
          strOutput += '零壹贰叁肆伍陆柒捌玖'.substr(num.substr(i,1),1) + strUnit.substr(i,1);  
          return strOutput.replace(/零角零分$/, '整').replace(/零[仟佰拾]/g, '零').replace(/零{2,}/g, '零').replace(/零([亿|万])/g, '$1').replace(/零+元/, '元').replace(/亿零{0,3}万/, '亿').replace(/^元/, "零元");  
    }
});
</script>