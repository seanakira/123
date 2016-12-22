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
							<shiro:hasPermission name="localTour:balance">
								<a class="blue" id="balance" data-toggle="modal" href="#" title="申请结算">
									<i class="icon-table bigger-100"></i>
									申请结算
								</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="localTour:findReimbursement">
								<a class="blue" id="reimbursement" data-toggle="modal" href="#" title="团队报账">
									<i class="icon-suitcase bigger-100"></i>
									团队报账
								</a>
							</shiro:hasPermission>
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
						</div>
						<div class="nav-search" id="nav-search">
							<form class="form-search" action="${path }localTourManage" method="get">
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
								<th aria-label="" style="width: 2%;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">
									<label>
										<input class="ace selectAll" type="checkbox">
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
											<a href="/localtour/localTourManage?page=${pageNo-1 }&key=${key }"><i class="icon-double-angle-left"></i></a>
										</li>
										<c:forEach var="page" begin="1" end="${pageMax }">
											<li <c:if test="${pageNo==page }">class="active"</c:if>>
												<a href="/localtour/localTourManage?page=${page }&key=${key }">${page }</a>
											</li>
										</c:forEach>
										<li <c:choose><c:when test="${pageNo==pageMax }">class="next disabled"</c:when><c:otherwise>class="next"</c:otherwise></c:choose>>
											<a href="/localtour/localTourManage?page=${pageNo+1 }&key=${key }"><i class="icon-double-angle-right"></i></a>
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
					
					
										<li>
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
										<div class="modal-header no-padding">
											<div class="table-header">
												抵离信息
												<a class="white addArrDep" href="#"><i class="icon-plus bigger-100"></i></a>
											</div>
										</div>
										<table id="arrDepTable" class="table table-striped table-bordered table-hover no-margin-bottom no-border-top">
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
												<a data-toggle="tab" href="#flight">
													<i class="red icon-large icon-plane"></i>
													机票
												</a>
											</li>
											<li>
												<a data-toggle="tab" href="#hotel">
													<i class="pink icon-large icon-building"></i>
													订房
												</a>
											</li>
											<li>
												<a data-toggle="tab" href="#meal">
													<i class="orange icon-large icon-food"></i>
													订餐
												</a>
											</li>
											<li>
												<a data-toggle="tab" href="#ticket">
													<i class="green icon-large icon-hdd"></i>
													门票
												</a>
											</li>
											<li>
												<a data-toggle="tab" href="#shuttle">
													<i class="dark icon-large icon-truck"></i>
													订车
												</a>
											</li>
											<li>
												<a data-toggle="tab" href="#tickets">
													<i class="blue icon-large icon-list-alt"></i>
													票务
												</a>
											</li>
											<li>
												<a data-toggle="tab" href="#comprehensive">
													<i class="purple icon-large icon-money"></i>
													综费
												</a>
											</li>
											<li>
												<a data-toggle="tab" href="#other">
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
																<th style="width: 10%">已开发票金额</th>
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
											            			<select style="display: none;" class="width-20 chosen-select customerAgency" data-placeholder="Choose a Country...">
																		<option value="">&nbsp;</option>
																	</select>
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
					
					
										<li>
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
										<div class="modal-header no-padding">
											<div class="table-header">
												抵离信息
												<a class="white addArrDep" href="#"><i class="icon-plus bigger-100"></i></a>
											</div>
										</div>
										<table id="arrDepTable" class="table table-striped table-bordered table-hover no-margin-bottom no-border-top">
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
															<th style="width: 10%;">日期</th>
															<th style="width: 15%;">内容</th>
															<th style="width: 15%;">供应商*</th>
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
															<th style="width: 10%;">明细备注</th>
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
															<th style="width: 10%;">日期</th>
															<th style="width: 15%;">内容</th>
															<th style="width: 15%;">供应商*</th>
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
															<th style="width: 10%;">明细备注</th>
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
															<th style="width: 10%;">日期</th>
															<th style="width: 15%;">内容</th>
															<th style="width: 15%;">供应商*</th>
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
															<th style="width: 10%;">明细备注</th>
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
															<th style="width: 10%;">日期</th>
															<th style="width: 15%;">内容</th>
															<th style="width: 15%;">供应商*</th>
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
															<th style="width: 10%;">明细备注</th>
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
															<th style="width: 10%;">日期</th>
															<th style="width: 15%;">内容</th>
															<th style="width: 15%;">供应商*</th>
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
															<th style="width: 10%;">明细备注</th>
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
															<th style="width: 10%;">日期</th>
															<th style="width: 15%;">内容</th>
															<th style="width: 15%;">供应商*</th>
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
															<th style="width: 10%;">明细备注</th>
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
															<th style="width: 10%;">日期</th>
															<th style="width: 15%;">内容</th>
															<th style="width: 15%;">供应商*</th>
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
															<th style="width: 10%;">明细备注</th>
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
															<th style="width: 10%;">日期</th>
															<th style="width: 15%;">内容</th>
															<th style="width: 15%;">供应商*</th>
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
															<th style="width: 10%;">明细备注</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
									            </table>
											</div>
											<span id="changeCostBlue" class="blue">*蓝色为成本收入变更</span>
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
																<th style="width: 10%">已开发票金额</th>
																<th style="width: 20%">备注</th>
															</tr>
														</thead>
														<tbody>
											            </tbody>
										            </table>
												         			
						         			</div><!-- tab content 结束 -->
						         			<span id="changeIncomeBlue" class="blue">*蓝色为成本收入变更</span>
					         			</div><!-- 左tab 结束 -->
					         		</div><!-- 收入tab结束 -->
					         		
					         	</div>
					         	
					            
					         </div>
							<div class="modal-footer no-margin-top">
								
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
					
					
										<li>
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
											<div class="table-header">
												抵离信息
												<a class="white addArrDep" href="#"><i class="icon-plus bigger-100"></i></a>
											</div>
										</div>
										<table id="arrDepTable" class="table table-striped table-bordered table-hover no-margin-bottom no-border-top">
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
																<th style="width: 10%">已开发票金额</th>
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
															<th style="width: 10%;">
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
															<th style="width: 10%;">
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
															<th style="width: 10%;">
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
															<th style="width: 10%;">
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
															<th style="width: 10%;">
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
															<th style="width: 10%;">
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
															<th style="width: 10%;">
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
															<th style="width: 10%;">
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
																<th style="width: 10%">已开发票金额</th>
																<th style="width: 20%">备注</th>
																<th style="width: 10%">
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
										提交待审
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
													<td style="width: 10%;">导游已借款</td>
													<td id="total" style="width: 15%;"></td>
													<td style="width: 10%;">借款项总计</td>
													<td id="maxLoan" style="width: 15%;"></td>
													<td style="width: 10%;">报账总计</td>
													<td id="reimbursementSum" style="width: 15%;"></td>
													<td style="width: 10%;">备注*</td>
													<td id="headAmount" style="width: 15%;"><input class="form-control" style="width:100%;" type="text"></td>
												</tr>
												<tr>
													<td style="width: 10%;">供应商电汇项</td>
													<td id="willCost" style="width: 15%;"></td>
													<td style="width: 10%;">已收款</td>
													<td id="realIncome" style="width: 15%;"></td>
													<td style="width: 10%;">实际毛利</td>
													<td id="realGrossProfit" style="width: 15%;"></td>
													<td style="width: 10%;">实际毛利率</td>
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
										保存并提交待审
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

<script type="text/javascript">$(function(){
	/* 初始化 */
	/* 样式 */
	$("#gourpManage").addClass("open");
	$("#gourpManage").children("ul").attr("style","display:block");
	$("#localTourManage").addClass("active");
	$("#create").find("input").attr("style","width:100%;");
	$("#create").find("select").attr("style","width:100%;");
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
	var selects = $("#create").find("select");
	var inited = false;
	function init(){
		$.ajax({  
	        type: "GET",  
	        contentType:"application/json;charset=utf-8",  
	        url:"/localtour/localTourManage/getCreateInfo",
	        dataType: "json",  
	        async: false,  
	        success:function(data){
	        	selects.not(".traffic").html('<option value="">&nbsp;</option>');
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
	        	selects.eq(0).chosen();
	        	selects.eq(1).chosen();
	        	selects.eq(2).chosen();
	        	selects.eq(3).chosen();
	        	selects.eq(4).chosen();
				$(".chosen-select").next().attr("style","width:100%;");
				$(".chosen-select").next().find("input").attr("style","height:100%;");
	        }  
		});
	}
	/* 新增团队初始化选项 */
	$("#createTour").click(function(){
		if(inited==false){
			init();
			inited = true;
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
			/* 控制按钮显示 */
			var status = $(this).siblings().eq(7).text();
			if(status=="新建"){
				$("#editTour").show();
				$("#auditing").show();
				$("#unAuditing").hide();
				$("#finance").hide();
				$("#lend").hide();
				$("#pay").hide();
				$("#chanageCost").hide();
				$("#loanInvoice").hide();
				$("#balance").hide();
				$("#reimbursement").hide();
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
				$("#balance").hide();		/* 结算 */
				$("#reimbursement").hide();	/* 报账 */
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
				$("#balance").hide();		/* 结算 */
				$("#reimbursement").hide();	/* 报账 */
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
				$("#balance").hide();		/* 结算 */
				$("#reimbursement").hide();	/* 报账 */
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
				$("#balance").hide();		/* 结算 */
				$("#reimbursement").hide();	/* 报账 */
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
				$("#balance").show();		/* 结算 */
				$("#reimbursement").hide();	/* 报账 */
				$("#delete").hide();		/* 删除 */
				$("#recover").hide();		/* 恢复 */
			}else if(status=="结算中"){
				$("#editTour").hide();		/* 修改 */
				$("#auditing").hide();		/* 提交 */
				$("#unAuditing").hide();	/* 退回 */
				$("#finance").hide();		/* 报送 */
				$("#lend").hide();			/* 借款 */
				$("#pay").show();			/* 付款 */
				$("#chanageCost").hide();	/* 变更 */
				$("#loanInvoice").hide();	/* 借票 */
				$("#balance").hide();		/* 结算 */
				$("#reimbursement").show();	/* 报账 */
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
				$("#loanInvoice").hide();	/* 借票 */
				$("#balance").hide();		/* 结算 */
				$("#reimbursement").hide();	/* 报账 */
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
				$("#loanInvoice").hide();	/* 借票 */
				$("#balance").hide();		/* 结算 */
				$("#reimbursement").hide();	/* 报账 */
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
				$("#balance").hide();		/* 结算 */
				$("#reimbursement").hide();	/* 报账 */
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
				$("#balance").hide();		/* 结算 */
				$("#reimbursement").hide();	/* 报账 */
				$("#delete").hide();		/* 删除 */
				$("#recover").show();		/* 恢复 */
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
	        url:"/localtour/guideTimeManage/checkTime",  
	        data:myData,  
	        dataType: "json",  
	        async: false,  
	        success:function(data){
        		$.each(data,function(){
	        		select.append('<option value="'+this.guideTable.id+'">'+this.userTable.realName+'</option>');
	        	});
	        },
	        error:function(){
	        	alert("团期选择错误，无法匹配导游");
	        }
		});
		select.chosen();
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
		arr.find("tr:last").find("select").chosen();
		dep.find("tr:last").find("select").chosen();
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
								date.getFullYear()+'/'+date.getMonth()+'/'+date.getDate()+
							'</a>'+
					  	  '</li>');
				div.append('<div id="day'+int+'" class="tab-pane in active">'+tripModel+'</div>');
			}else{
				ul.append('<li>'+
						'<a data-toggle="tab" href="#day'+int+'">'+
							date.getFullYear()+'/'+date.getMonth()+'/'+date.getDate()+
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
		var guides = $(this).parents(".costTable").prev().find("#guides");
		var names = guides.find("option:selected");
		if(guides.val()!=undefined){
			for (var int = 0; int < guides.val().length; int++) {
				selects.eq(2).append('<option value="'+guides.val()[int]+'">'+names.eq(int).text()+'</option>');
			} 
		}
		selects.eq(2).append('<option value="'+'<%=user.getId()%>'+'">'+'<%=user.getRealName()%>'+'</option>');
		selects.chosen();
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
		var select = tr.children("td").eq(1).children("select");
		select.val($(this).parents(".tab-pane").siblings().first().find("#customer").val());
		select.chosen();
		select.next().attr("style","width:100%;");
		select.next().find("input").attr("style","height:100%;");
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
		        url:"/localtour/localTourManage/del",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	if(data){
			        	checkbox.parent().parent().parent().find("td").eq(8).text("已取消");
			        	$("#editTour").hide();		/* 修改 */
						$("#auditing").hide();		/* 提交 */
						$("#unAuditing").hide();	/* 退回 */
						$("#finance").hide();		/* 报送 */
						$("#lend").hide();			/* 借款 */
						$("#pay").hide();			/* 付款 */
						$("#chanageCost").hide();	/* 变更 */
						$("#loanInvoice").hide();	/* 借票 */
						$("#balance").hide();		/* 结算 */
						$("#reimbursement").hide();	/* 报账 */
						$("#delete").hide();		/* 删除 */
						$("#recover").show();		/* 恢复 */
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
		        url:"/localtour/localTourManage/recover",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	if(data){
			        	checkbox.parent().parent().parent().find("td").eq(8).text("新建");
			        	$("#editTour").show();
						$("#auditing").show();
						$("#unAuditing").hide();
						$("#finance").hide();
						$("#lend").hide();
						$("#pay").hide();
						$("#chanageCost").hide();
						$("#loanInvoice").hide();
						$("#balance").hide();
						$("#reimbursement").hide();
						$("#delete").show();
						$("#recover").hide();
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
		        url:"/localtour/localTourManage/auditing",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	if(data){
			        	checkbox.parent().parent().parent().find("td").eq(8).text("已提交");
			        	$("#editTour").hide();		/* 修改 */
						$("#auditing").hide();		/* 提交 */
						$("#unAuditing").show();	/* 退回 */
						$("#finance").show();		/* 报送 */
						$("#lend").hide();			/* 借款 */
						$("#pay").hide();			/* 付款 */
						$("#chanageCost").hide();	/* 变更 */
						$("#loanInvoice").hide();	/* 借票 */
						$("#balance").hide();		/* 结算 */
						$("#reimbursement").hide();	/* 报账 */
						$("#delete").hide();		/* 删除 */
						$("#recover").hide();		/* 恢复 */
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
		        url:"/localtour/localTourManage/unAuditing",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	if(data){
			        	checkbox.parent().parent().parent().find("td").eq(8).text("新建");
			        	$("#editTour").show();
						$("#auditing").show();
						$("#unAuditing").hide();
						$("#finance").hide();
						$("#lend").hide();
						$("#pay").hide();
						$("#chanageCost").hide();
						$("#loanInvoice").hide();
						$("#balance").hide();
						$("#reimbursement").hide();
						$("#delete").show();
						$("#recover").hide();
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
		        url:"/localtour/localTourManage/finance",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	if(data){
			        	checkbox.parent().parent().parent().find("td").eq(8).text("已报送");
			        	$("#editTour").hide();		/* 修改 */
						$("#auditing").hide();		/* 提交 */
						$("#unAuditing").hide();	/* 退回 */
						$("#finance").hide();		/* 报送 */
						$("#lend").hide();			/* 借款 */
						$("#pay").hide();			/* 付款 */
						$("#chanageCost").show();	/* 变更 */
						$("#loanInvoice").show();	/* 借票 */
						$("#balance").hide();		/* 结算 */
						$("#reimbursement").hide();	/* 报账 */
						$("#delete").hide();		/* 删除 */
						$("#recover").hide();		/* 恢复 */
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
				customerAgencyId:incomeTrs.eq(int).find("select").val(),
				income:incomeInputs.eq(2).val(),
				remark:incomeInputs.eq(3).val()});
		}
		
		var fullLocalTourViewModel = {localTourTable:localTourTable,guideTimeTables:guideTimeTables,arrTables:arrTables,departTables:departTables,tripTables:tripTables,costTables:costTables,incomeTables:incomeTables};
		var myData = JSON.stringify(fullLocalTourViewModel);
		$.ajax({
	        type: "POST",  
	        contentType:"application/json;charset=utf-8",  
	        url:"/localtour/localTourManage/save",  
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
	        		tr.click(function(){
	        			var checkbox = $(this).find("input");
	        			if(checkbox.prop("checked")){
	        				checkbox.prop("checked",false);
	        			}else{
	        				$("#table").find("input").prop("checked",false);
	        				checkbox.prop("checked",true);
	        			}
	        		});
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
	        url:"/localtour/localTourManage/find",  
	        data:myData,  
	        dataType: "json",  
	        async: false,  
	        success:function(data){
	        	var td = $("#find").find(".tourInfo");
	        	td.eq(0).text(data.localTourTable.tourNo);
	        	td.eq(1).text(data.localTourTable.tourName);
	        	td.eq(2).text(data.tourInfo.businessTypeName);
	        	td.eq(3).text(data.tourInfo.tourTypeName);
	        	td.eq(4).text(data.tourInfo.regionName);
	        	td.eq(5).text(data.tourInfo.visitorTypeName);
	        	td.eq(6).text(data.tourInfo.customerAgencyName);
	        	td.eq(7).text(data.localTourTable.organizor);
	        	td.eq(8).text(data.localTourTable.qpGuideNo);
	        	td.eq(9).text(data.localTourTable.adultNo);
	        	td.eq(10).text(data.localTourTable.childrenNo);
	        	td.eq(11).text(data.localTourTable.qpGuideNo+data.localTourTable.adultNo+data.localTourTable.childrenNo);
	        	td.eq(12).text(data.localTourTable.startTime);
	        	td.eq(13).text(data.localTourTable.endTime);
	        	td.eq(14).text("");
	        	$.each(data.guideTimes,function(index,guideTime){
	        		if(index==0){
	        			td.eq(14).append(guideTime.realName);
	        		}else{
	        			td.eq(14).append("，"+guideTime.realName);
	        		}
	        	});
	        	td.eq(15).text(data.localTourTable.remark);
	        	var arrInfo = $("#find").find(".arrInfo");
	        	arrInfo.html("");
	        	$.each(data.arrs,function(){
	        		arrInfo.append('<tr>'+
	        							'<td>'+this.origin+'</td>'+
	        							'<td>'+this.arrTable.arrTraffic+'</td>'+
	        							'<td>'+this.arrTable.arrTime+'</td>'+
	        							'<td>'+this.arrTable.arrTrafficNo+'</td>'+
	        							'<td>'+this.arrRegion+'</td>'+
	        						'</tr>');
	        	});
	        	var departInfo = $("#find").find(".departInfo");
	        	departInfo.html("");
	        	$.each(data.departs,function(){
	        		departInfo.append('<tr>'+
	        							'<td>'+this.dest+'</td>'+
	        							'<td>'+this.departTable.departTraffic+'</td>'+
	        							'<td>'+this.departTable.departTime+'</td>'+
	        							'<td>'+this.departTable.departTrafficNo+'</td>'+
	        							'<td>'+this.departRegion+'</td>'+
	        						'</tr>');
	        	});
	        	var startTime = new Date(data.localTourTable.startTime);
				var endTime = new Date(data.localTourTable.endTime);
				var days = (endTime-startTime)/1000/60/60/24+1;
				var ul = $("#find").find("#myTab3");
				var div = ul.parent().children("div");
				var tripModel = $("#find").find("#tripModel");
				ul.html("");
				div.html('<div id="tripModel" style="display:none;">'+tripModel.html()+'</div>');
	        	for (var int = 0; int < days; int++) {
					var date = new Date(startTime.getTime()+1000*60*60*24*int);
					if(int==0){
						ul.append('<li class="active">'+
									'<a data-toggle="tab" href="#day'+int+'">'+
										date.getFullYear()+'/'+date.getMonth()+'/'+date.getDate()+
									'</a>'+
							  	  '</li>');
						var flag = false;
						$.each(data.tripTables,function(){
							if(this.number==int){
								td.eq(16).text(this.trip);
				        		td.eq(17).text(this.meal);
				        		td.eq(18).text(this.stay);
				        		td.eq(19).text(this.traffic);
				        		td.eq(20).text(this.remark);
				        		div.append('<div id="day'+this.number+'" class="tab-pane in active">'+tripModel.html()+'</div>');
				        		flag=true;
							}
						});
						if(flag==false){
							td.eq(16).text("");
			        		td.eq(17).text("");
			        		td.eq(18).text("");
			        		td.eq(19).text("");
			        		td.eq(20).text("");
			        		div.append('<div id="day'+int+'" class="tab-pane in active">'+tripModel.html()+'</div>');
						}
					}else{
						ul.append('<li>'+
								'<a data-toggle="tab" href="#day'+int+'">'+
									date.getFullYear()+'/'+date.getMonth()+'/'+date.getDate()+
								'</a>'+
						  	  '</li>');
						var flag = false;
						$.each(data.tripTables,function(){
							if(this.number==int){
								td.eq(16).text(this.trip);
				        		td.eq(17).text(this.meal);
				        		td.eq(18).text(this.stay);
				        		td.eq(19).text(this.traffic);
				        		td.eq(20).text(this.remark);
				        		div.append('<div id="day'+this.number+'" class="tab-pane">'+tripModel.html()+'</div>');
				        		flag=true;
							}
						});
						if(flag==false){
							td.eq(16).text("");
			        		td.eq(17).text("");
			        		td.eq(18).text("");
			        		td.eq(19).text("");
			        		td.eq(20).text("");
			        		div.append('<div id="day'+int+'" class="tab-pane">'+tripModel.html()+'</div>');
						}
					}
				}
	        	var flight = $("#flight2").find("tbody");
	        	var hotel = $("#hotel2").find("tbody");
	        	var meal = $("#meal2").find("tbody");
	        	var ticket = $("#ticket2").find("tbody");
	        	var shuttle = $("#shuttle2").find("tbody");
	        	var tickets = $("#tickets2").find("tbody");
	        	var comprehensive = $("#comprehensive2").find("tbody");
	        	var other = $("#other2").find("tbody");
	        	flight.html("");
	        	hotel.html("");
	        	meal.html("");
	        	ticket.html("");
	        	shuttle.html("");
	        	tickets.html("");
	        	comprehensive.html("");
	        	other.html("");
	        	$.each(data.costs,function(){
	        		if(this.costTable.supplierScopeId==1){
	        			flight.append('<tr>'+
	        								'<td>'+this.costTable.costDate+'</td>'+
	        								'<td>'+this.contentName+'</td>'+
	        								'<td>'+this.supplierName+'</td>'+
	        								'<td>'+this.costTable.cost+'</td>'+
	        								'<td>'+this.costTable.count+'</td>'+
	        								'<td>'+this.costTable.days+'</td>'+
	        								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
	        								'<td>'+this.borrowUserName+'</td>'+
	        								'<td>'+this.costTable.remark+'</td>'+
	        					'</tr>');
	        		}else if(this.costTable.supplierScopeId==2){
	        			hotel.append('<tr>'+
		    								'<td>'+this.costTable.costDate+'</td>'+
		    								'<td>'+this.contentName+'</td>'+
		    								'<td>'+this.supplierName+'</td>'+
		    								'<td>'+this.costTable.cost+'</td>'+
		    								'<td>'+this.costTable.count+'</td>'+
		    								'<td>'+this.costTable.days+'</td>'+
		    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
		    								'<td>'+this.borrowUserName+'</td>'+
		    								'<td>'+this.costTable.remark+'</td>'+
		    					'</tr>');
	        		}else if(this.costTable.supplierScopeId==3){
	        			meal.append('<tr>'+
		    								'<td>'+this.costTable.costDate+'</td>'+
		    								'<td>'+this.contentName+'</td>'+
		    								'<td>'+this.supplierName+'</td>'+
		    								'<td>'+this.costTable.cost+'</td>'+
		    								'<td>'+this.costTable.count+'</td>'+
		    								'<td>'+this.costTable.days+'</td>'+
		    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
		    								'<td>'+this.borrowUserName+'</td>'+
		    								'<td>'+this.costTable.remark+'</td>'+
		    					'</tr>');		        			
	        		}else if(this.costTable.supplierScopeId==4){
	        			ticket.append('<tr>'+
		    								'<td>'+this.costTable.costDate+'</td>'+
		    								'<td>'+this.contentName+'</td>'+
		    								'<td>'+this.supplierName+'</td>'+
		    								'<td>'+this.costTable.cost+'</td>'+
		    								'<td>'+this.costTable.count+'</td>'+
		    								'<td>'+this.costTable.days+'</td>'+
		    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
		    								'<td>'+this.borrowUserName+'</td>'+
		    								'<td>'+this.costTable.remark+'</td>'+
		    					'</tr>');		        			
	        		}else if(this.costTable.supplierScopeId==5){
	        			shuttle.append('<tr>'+
		    								'<td>'+this.costTable.costDate+'</td>'+
		    								'<td>'+this.contentName+'</td>'+
		    								'<td>'+this.supplierName+'</td>'+
		    								'<td>'+this.costTable.cost+'</td>'+
		    								'<td>'+this.costTable.count+'</td>'+
		    								'<td>'+this.costTable.days+'</td>'+
		    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
		    								'<td>'+this.borrowUserName+'</td>'+
		    								'<td>'+this.costTable.remark+'</td>'+
		    					'</tr>');	
	        		}else if(this.costTable.supplierScopeId==6){
	        			tickets.append('<tr>'+
		    								'<td>'+this.costTable.costDate+'</td>'+
		    								'<td>'+this.contentName+'</td>'+
		    								'<td>'+this.supplierName+'</td>'+
		    								'<td>'+this.costTable.cost+'</td>'+
		    								'<td>'+this.costTable.count+'</td>'+
		    								'<td>'+this.costTable.days+'</td>'+
		    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
		    								'<td>'+this.borrowUserName+'</td>'+
		    								'<td>'+this.costTable.remark+'</td>'+
		    					'</tr>');
	        		}else if(this.costTable.supplierScopeId==7){
	        			comprehensive.append('<tr>'+
		    								'<td>'+this.costTable.costDate+'</td>'+
		    								'<td>'+this.contentName+'</td>'+
		    								'<td>'+this.supplierName+'</td>'+
		    								'<td>'+this.costTable.cost+'</td>'+
		    								'<td>'+this.costTable.count+'</td>'+
		    								'<td>'+this.costTable.days+'</td>'+
		    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
		    								'<td>'+this.borrowUserName+'</td>'+
		    								'<td>'+this.costTable.remark+'</td>'+
		    					'</tr>');
	        		}else if(this.costTable.supplierScopeId==8){
	        			other.append('<tr>'+
		    								'<td>'+this.costTable.costDate+'</td>'+
		    								'<td>'+this.contentName+'</td>'+
		    								'<td>'+this.supplierName+'</td>'+
		    								'<td>'+this.costTable.cost+'</td>'+
		    								'<td>'+this.costTable.count+'</td>'+
		    								'<td>'+this.costTable.days+'</td>'+
		    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
		    								'<td>'+this.borrowUserName+'</td>'+
		    								'<td>'+this.costTable.remark+'</td>'+
		    					'</tr>');
	        		}
	        	});
	        	
	        	if(data.changeCosts.length > 0){
	        		$("#changeCostBlue").attr("style","");
	        	}else{
	        		$("#changeCostBlue").attr("style","display:none");
	        	}
	        	if(data.changeIncomes.length > 0){
	        		$("#changeIncomeBlue").attr("style","");
	        	}else{
	        		$("#changeIncomeBlue").attr("style","display:none");
	        	}
	        	$.each(data.changeCosts,function(){
	        		if(this.costTable.supplierScopeId==1){
	        			flight.append('<tr class="blue">'+
	        								'<td>'+this.costTable.costDate+'</td>'+
	        								'<td>'+this.contentName+'</td>'+
	        								'<td>'+this.supplierName+'</td>'+
	        								'<td>'+this.costTable.cost+'</td>'+
	        								'<td>'+this.costTable.count+'</td>'+
	        								'<td>'+this.costTable.days+'</td>'+
	        								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
	        								'<td>'+this.borrowUserName+'</td>'+
	        								'<td>'+this.costTable.remark+'</td>'+
	        					'</tr>');
	        		}else if(this.costTable.supplierScopeId==2){
	        			hotel.append('<tr>'+
		    								'<td>'+this.costTable.costDate+'</td>'+
		    								'<td>'+this.contentName+'</td>'+
		    								'<td>'+this.supplierName+'</td>'+
		    								'<td>'+this.costTable.cost+'</td>'+
		    								'<td>'+this.costTable.count+'</td>'+
		    								'<td>'+this.costTable.days+'</td>'+
		    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
		    								'<td>'+this.borrowUserName+'</td>'+
		    								'<td>'+this.costTable.remark+'</td>'+
		    					'</tr>');
	        		}else if(this.costTable.supplierScopeId==3){
	        			meal.append('<tr>'+
		    								'<td>'+this.costTable.costDate+'</td>'+
		    								'<td>'+this.contentName+'</td>'+
		    								'<td>'+this.supplierName+'</td>'+
		    								'<td>'+this.costTable.cost+'</td>'+
		    								'<td>'+this.costTable.count+'</td>'+
		    								'<td>'+this.costTable.days+'</td>'+
		    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
		    								'<td>'+this.borrowUserName+'</td>'+
		    								'<td>'+this.costTable.remark+'</td>'+
		    					'</tr>');		        			
	        		}else if(this.costTable.supplierScopeId==4){
	        			ticket.append('<tr>'+
		    								'<td>'+this.costTable.costDate+'</td>'+
		    								'<td>'+this.contentName+'</td>'+
		    								'<td>'+this.supplierName+'</td>'+
		    								'<td>'+this.costTable.cost+'</td>'+
		    								'<td>'+this.costTable.count+'</td>'+
		    								'<td>'+this.costTable.days+'</td>'+
		    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
		    								'<td>'+this.borrowUserName+'</td>'+
		    								'<td>'+this.costTable.remark+'</td>'+
		    					'</tr>');		        			
	        		}else if(this.costTable.supplierScopeId==5){
	        			shuttle.append('<tr>'+
		    								'<td>'+this.costTable.costDate+'</td>'+
		    								'<td>'+this.contentName+'</td>'+
		    								'<td>'+this.supplierName+'</td>'+
		    								'<td>'+this.costTable.cost+'</td>'+
		    								'<td>'+this.costTable.count+'</td>'+
		    								'<td>'+this.costTable.days+'</td>'+
		    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
		    								'<td>'+this.borrowUserName+'</td>'+
		    								'<td>'+this.costTable.remark+'</td>'+
		    					'</tr>');	
	        		}else if(this.costTable.supplierScopeId==6){
	        			tickets.append('<tr>'+
		    								'<td>'+this.costTable.costDate+'</td>'+
		    								'<td>'+this.contentName+'</td>'+
		    								'<td>'+this.supplierName+'</td>'+
		    								'<td>'+this.costTable.cost+'</td>'+
		    								'<td>'+this.costTable.count+'</td>'+
		    								'<td>'+this.costTable.days+'</td>'+
		    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
		    								'<td>'+this.borrowUserName+'</td>'+
		    								'<td>'+this.costTable.remark+'</td>'+
		    					'</tr>');
	        		}else if(this.costTable.supplierScopeId==7){
	        			comprehensive.append('<tr>'+
		    								'<td>'+this.costTable.costDate+'</td>'+
		    								'<td>'+this.contentName+'</td>'+
		    								'<td>'+this.supplierName+'</td>'+
		    								'<td>'+this.costTable.cost+'</td>'+
		    								'<td>'+this.costTable.count+'</td>'+
		    								'<td>'+this.costTable.days+'</td>'+
		    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
		    								'<td>'+this.borrowUserName+'</td>'+
		    								'<td>'+this.costTable.remark+'</td>'+
		    					'</tr>');
	        		}else if(this.costTable.supplierScopeId==8){
	        			other.append('<tr>'+
		    								'<td>'+this.costTable.costDate+'</td>'+
		    								'<td>'+this.contentName+'</td>'+
		    								'<td>'+this.supplierName+'</td>'+
		    								'<td>'+this.costTable.cost+'</td>'+
		    								'<td>'+this.costTable.count+'</td>'+
		    								'<td>'+this.costTable.days+'</td>'+
		    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
		    								'<td>'+this.borrowUserName+'</td>'+
		    								'<td>'+this.costTable.remark+'</td>'+
		    					'</tr>');
	        		}
	        	});
	        	var tbody = $("#incomes2").find("tbody");
	        	tbody.html("");
	        	$.each(data.incomes,function(){
	        		var realIncome = this.incomeTable.realIncome==null?0:this.incomeTable.realIncome;
	        		var invoiceAmount = this.invoiceAmount==null?0:this.invoiceAmount;
	        		tbody.append('<tr>'+
	        							'<td>'+this.incomeTable.incomeDate+'</td>'+
	        							'<td>'+this.customerAgencyName+'</td>'+
	        							'<td>'+this.incomeTable.income+'</td>'+
	        							'<td>'+realIncome+'</td>'+
	        							'<td>'+invoiceAmount+'</td>'+
	        							'<td>'+this.incomeTable.remark+'</td>'+
	        					'</tr>');
	        	});
	        	$.each(data.changeIncomes,function(){
	        		var realIncome = this.incomeTable.realIncome==null?0:this.incomeTable.realIncome;
	        		var invoiceAmount = this.invoiceAmount==null?0:this.invoiceAmount;
	        		tbody.append('<tr class="blue">'+
	        							'<td>'+this.incomeTable.incomeDate+'</td>'+
	        							'<td>'+this.customerAgencyName+'</td>'+
	        							'<td>'+this.incomeTable.income+'</td>'+
	        							'<td>'+realIncome+'</td>'+
	        							'<td>'+invoiceAmount+'</td>'+
	        							'<td>'+this.incomeTable.remark+'</td>'+
	        					'</tr>');
	        	});
	        }  
		});
	});
/* 编辑 */
	/* 点击日期删除导游 */
	$("#edit").find(".datepicker").click(function(){
		$("#edit").find("#guideTd").html('<input id="guide" type="text" class="form-control" placeholder="可选多个" style="width:100%;">');
		var myData = {tourId:$("#saveEdit").parent().attr("id")};
		$.ajax({
	        type: "GET",
	        contentType:"application/json;charset=utf-8",  
	        url:"/localtour/guideTimeManage/delete",  
	        data:myData,  
	        dataType: "json",
	        async: false,  
	        success:function(data){
        		
	        }
		});
	});
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
	        url:"/localtour/guideTimeManage/checkTime",  
	        data:myData,  
	        dataType: "json",  
	        async: false,  
	        success:function(data){
        		$.each(data,function(){
	        		select.append('<option value="'+this.guideTable.id+'">'+this.userTable.realName+'</option>');
	        	});
	        },
	        error:function(){
	        	alert("团期选择错误，无法匹配导游");
	        }
		});
		select.chosen();
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
			        url:"/localtour/localTourManage/find",  
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
						$.ajax({
					        type: "GET",  
					        contentType:"application/json;charset=utf-8",  
					        url:"/localtour/guideTimeManage/checkTime",  
					        data:myData,  
					        dataType: "json",  
					        async: false,  
					        success:function(guides){
					        	var guideIds = new Array();
								$.each(data.guideTimes,function(index){
									select.append('<option value="'+this.guideId+'">'+this.realName+'</option>');
									guideIds[index] = this.guideId;
					        	});
				        		$.each(guides,function(){
					        		select.append('<option value="'+this.guideTable.id+'">'+this.userTable.realName+'</option>');
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
		    						borrowUser.append('<option value="'+guides.val()[int]+'">'+names.eq(int).text()+'</option>');
		    					} 
		    				}
		    				borrowUser.append('<option value="'+data.localTourTable.userId+'">'+tourUserName+'</option>');
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
			    			inputs.eq(0).val(this.incomeTable.incomeDate.replace(/-/g,'/'));
			    			tr.find("select").eq(0).val(this.incomeTable.customerAgencyId);
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
												date.getFullYear()+'/'+date.getMonth()+'/'+date.getDate()+
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
											date.getFullYear()+'/'+date.getMonth()+'/'+date.getDate()+
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
			        	$("#edit").find("select").chosen();
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
								date.getFullYear()+'/'+date.getMonth()+'/'+date.getDate()+
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
							date.getFullYear()+'/'+date.getMonth()+'/'+date.getDate()+
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
		        url:"/localtour/localTourManage/findChangeCost",  
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
										'<td>'+this.costTable.costDate+'</td>'+
										'<td>'+this.contentName+'</td>'+
										'<td>'+this.supplierName+'</td>'+
										'<td>'+this.costTable.cost+'</td>'+
										'<td>'+this.costTable.count+'</td>'+
										'<td>'+this.costTable.days+'</td>'+
										'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
										'<td>'+this.borrowUserName+'</td>'+
										'<td>'+this.costTable.remark+'</td>'+
								'</tr>');
		        		if(this.costTable.remittanced){
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
		        		}
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
		        		var tr = $('<tr class="look">'+
		        						'<td>'+this.incomeTable.incomeDate+'</td>'+
		        						'<td>'+this.customerAgencyName+'</td>'+
		        						'<td>'+this.incomeTable.income+'</td>'+
		        						'<td>'+realIncome+'</td>'+
		        						'<td>'+invoiceAmount+'</td>'+
		        						'<td>'+this.incomeTable.remark+'</td>'+
		        					'</tr>');
		        		if(this.incomeTable.incomed){
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
		        		}
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
				customerAgencyId:incomeSelects.eq(0).val(),
				income:incomeInputs.eq(2).val(),
				remark:incomeInputs.eq(3).val()
			});
		}
		var changeCostIncomeViewModel = {costTables:costTables,incomeTables:incomeTables};
		var myData = JSON.stringify(changeCostIncomeViewModel);
		$.ajax({
	        type: "POST",  
	        contentType:"application/json;charset=utf-8",  
	        url:"/localtour/localTourManage/saveChangeCost",  
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
		        url:"/localtour/localTourManage/findLend",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	var canLoans = $('<tbody></tbody>');
		        	var isLoans = $('<tbody></tbody>');
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
	        						'<td>'+this.loanTable.remark+'</td>'+
	        						'<td>'+this.lenderRealName+'</td>'+
	        						'<td>'+this.status+'</td>'+
	        					'</tr>');
		        			canLoans.append(tr);
		        		}
		        	});
		        	if(canLoans.html()==""){
	        			$("#canLoans").parent().html('<span class="red">无可借款项</span>');
	        		}else {
	        			$("#canLoans").html(canLoans.html());
	        		}
					if(isLoans.html()==""){
						$("#isLoans").parent().html('<span class="red">无借款记录</span>');
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
		var ids = [];
		var trs = $("#canLoans").children("tr");
		$.each(trs,function(index){
			ids[index] = $(this).attr("id");
		});
		var myData = {tourId:tourId,ids:ids.toString()};
		$.ajax({
	        type: "GET",  
	        contentType:"application/json;charset=utf-8",  
	        url:"/localtour/localTourManage/loanApplication",  
	        data:myData,  
	        dataType: "json",  
	        async: false,  
	        success:function(data){
	        	
	        }  
		 });
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
		$("#canPays").delegate("tr td:not(.center):not(tr td:nth-child(9))","click",function(){
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
		        url:"/localtour/localTourManage/findPay",  
		        data:myData,  
		        dataType: "json",
		        async: false,
		        success:function(data){
		        	var canPays = $('<tbody></tbody>');
		        	var isPays = $('<tbody></tbody>');
		        	$.each(data.costs,function(){
		        		if(this.costTable.payStatus==0&&!this.costTable.remittanced&&!this.costTable.lend&&!this.costTable.bill){
		        			var tr = $('<tr id="'+this.costTable.id+'">'+
		        							'<td class="center sorting_1"><label><input class="ace" type="checkbox"><span class="lbl"></span></label></td>'+
			        						'<td>'+this.costTable.costDate+'</td>'+
			        						'<td>'+this.contentName+'</td>'+
			        						'<td>'+this.supplierName+'</td>'+
			        						'<td>'+this.costTable.cost+'</td>'+
			        						'<td>'+this.costTable.count+'</td>'+
			        						'<td>'+this.costTable.days+'</td>'+
			        						'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
			        						'<td><input style="width:100%;" type="text" value="'+this.costTable.realCost+'"></td>'+
			        						'<td>'+this.borrowUserName+'</td>'+
			        						'<td>'+this.costTable.remark+'</td>'+
			        						'<td>'+this.payStatus+'</td>'+
			        					'</tr>');
		        			canPays.append(tr);
		        		}else if(!this.costTable.remittanced&&!this.costTable.lend&&!this.costTable.bill){
		        			var tr = $('<tr>'+
			        						'<td>'+this.costTable.costDate+'</td>'+
			        						'<td>'+this.contentName+'</td>'+
			        						'<td>'+this.supplierName+'</td>'+
			        						'<td>'+this.costTable.cost+'</td>'+
			        						'<td>'+this.costTable.count+'</td>'+
			        						'<td>'+this.costTable.days+'</td>'+
			        						'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
			        						'<td>'+this.costTable.realCost+'</td>'+
			        						'<td>'+this.borrowUserName+'</td>'+
			        						'<td>'+this.costTable.remark+'</td>'+
			        						'<td>'+this.payStatus+'</td>'+
			        					'</tr>');
		        			isPays.append(tr);
		        		}
		        	});
		        	var canPaysChangeCount = 0;
		        	var isPaysChangeCount = 0;
		        	$.each(data.changeCosts,function(){
		        		if(this.costTable.payStatus==0&&!this.costTable.remittanced&&!this.costTable.lend&&!this.costTable.bill&&this.costTable.status==3){
		        			var tr = $('<tr id="'+this.costTable.id+'" class="blue">'+
		        							'<td class="center sorting_1"><label><input class="ace" type="checkbox"><span class="lbl"></span></label></td>'+
			        						'<td>'+this.costTable.costDate+'</td>'+
			        						'<td>'+this.contentName+'</td>'+
			        						'<td>'+this.supplierName+'</td>'+
			        						'<td>'+this.costTable.cost+'</td>'+
			        						'<td>'+this.costTable.count+'</td>'+
			        						'<td>'+this.costTable.days+'</td>'+
			        						'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
			        						'<td><input style="width:100%;" type="text" value="'+this.costTable.realCost+'"></td>'+
			        						'<td>'+this.borrowUserName+'</td>'+
			        						'<td>'+this.costTable.remark+'</td>'+
			        						'<td>'+this.payStatus+'</td>'+
			        					'</tr>');
		        			canPays.append(tr);
		        			canPaysChangeCount++;
		        		}else if(!this.costTable.remittanced&&!this.costTable.lend&&!this.costTable.bill&&this.costTable.status==3){
		        			var tr = $('<tr class="blue">'+
			        						'<td>'+this.costTable.costDate+'</td>'+
			        						'<td>'+this.contentName+'</td>'+
			        						'<td>'+this.supplierName+'</td>'+
			        						'<td>'+this.costTable.cost+'</td>'+
			        						'<td>'+this.costTable.count+'</td>'+
			        						'<td>'+this.costTable.days+'</td>'+
			        						'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
			        						'<td>'+this.costTable.realCost+'</td>'+
			        						'<td>'+this.borrowUserName+'</td>'+
			        						'<td>'+this.costTable.remark+'</td>'+
			        						'<td>'+this.payStatus+'</td>'+
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
	        			$("#canPays").html(canPays.html());
	        			if(canPaysChangeCount!=0){
	        				canPaysP.after(span);
	        			}
	        		}
					if(isPays.html()==""){
						$("#isPays").parent().html('<span class="red">无付款记录</span>');
	        		}else{
	        			$("#isPays").html(isPays.html());
	        			if(isPaysChangeCount!=0){
	        				isPaysP.after(span);
	        			}
	        		}
		        }
			});
		}
	});
	/*提交付款申请*/
	$("#payApplication").click(function(){
		var tourId = $(this).parent().attr("id");
		var checkbox = $("#canPays").find("input:checked");
		if(checkbox.length==0){
			$("#payApplication").attr("data-dismiss","");
			alert("没有选择要申请的付款项");
		}else{
			$("#payApplication").attr("data-dismiss","modal");
			var costTables = new Array();
			var changeCostTables = new Array();
			$.each(checkbox,function(){
				var tr = $(this).parent().parent().parent();
				if(tr.attr("class")=="blue"){
					changeCostTables.push({id:tr.attr("id"),
										tourId:tourId,
										realCost:tr.children("td").eq(8).children("input").val()});
				}else{
					costTables.push({id:tr.attr("id"),
								tourId:tourId,
								realCost:tr.children("td").eq(8).children("input").val()});
				}
			});
			var full = {costTables:costTables,changeCostTables:changeCostTables};
			var myData = JSON.stringify(full);
			$.ajax({
		        type: "POST",  
		        contentType:"application/json;charset=utf-8",  
		        url:"/localtour/localTourManage/payApplication",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	
		        }  
			 });
		}
	});
	/*点击付款input金额自动填充*/
	$("#payModel").delegate("input:not(.ace)","click",function(){
		$(this).val(parseFloat($(this).parent().prev().text()));
	});
	
	
	/*预借发票*/
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
		        url:"/localtour/localTourManage/findBorrowInvoice",  
		        data:myData,  
		        dataType: "json",
		        async: false,
		        success:function(data){
		        	$.each(data,function(){
		        		$("#loanInvoices").append('<tr class="loanInvoice">'+
														'<td>'+this.loanInvoiceTable.issueDate+'</td>'+
														'<td>'+this.loanInvoiceTable.invoiceName+'</td>'+
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
		$("#loanInvoices").append(tr);
	});
	/*预借发票申请*/
	$("#loanInvoiceApplication").click(function(){
		var tourId = $(this).parent().attr("id");
		var loanInvoices = new Array();
		var trs = $("#loanInvoices").children("tr").not(".loanInvoice");
		$.each(trs,function(){
			var inputs = $(this).find("input");
			loanInvoices.push({
				tourId:tourId,
				issueDate:new Date(inputs.eq(0).val()),
				invoiceName:inputs.eq(1).val(),
				invoiceContent:inputs.eq(2).val(),
				invoiceAmount:inputs.eq(3).val(),
				remark:$(this).find("textarea").val()
			});
		});
		var myData = JSON.stringify(loanInvoices);
		$.ajax({
			type: "POST",  
	        contentType:"application/json;charset=utf-8",  
	        url:"/localtour/localTourManage/saveBorrowInvoice",  
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
		        url:"/localtour/localTourManage/balance",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	if(data){
			        	checkbox.parent().parent().parent().find("td").eq(8).text("结算中");
			        	$("#editTour").hide();		/* 修改 */
						$("#auditing").hide();		/* 提交 */
						$("#unAuditing").hide();	/* 退回 */
						$("#finance").hide();		/* 报送 */
						$("#lend").hide();			/* 借款 */
						$("#pay").show();			/* 付款 */
						$("#chanageCost").hide();	/* 变更 */
						$("#loanInvoice").hide();	/* 借票 */
						$("#balance").hide();		/* 结算 */
						$("#reimbursement").show();	/* 报账 */
						$("#delete").hide();		/* 删除 */
						$("#recover").hide();		/* 恢复 */
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
			find(myData);
		}
	});
	function find(myData){
		$.ajax({
	        type: "GET",  
	        contentType:"application/json;charset=utf-8",  
	        url:"/localtour/reimbursementManage/findReimbursement",  
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
	        	var willCost = 0;
	        	var realIncome = 0;
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
	        		headAmount.html('<input id="headAmount" class="form-control" style="width:100%;" type="text">');
	        	}else{
	        		headAmount.html(data.reimbursementTable.headAmount);
	        	}
	        	$("#headAmount").html(headAmount.html());
	        	/* 设置成本 */
	        	$.each(data.costs,function(){
	        		var reimbursement = $("<td></td>");
	        		var guideLoan = $("<td></td>");
	        		var bill = $("<td></td>");
        			if(this.costTable.bill){
        				bill.html('<i class="icon-ok bigger-130"></i>');
        			}
        			if(this.costTable.lend){
        				guideLoan.html('<i class="icon-ok bigger-130"></i>');
        				maxLoan = (parseFloat(maxLoan) + this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2);
        			}
       				if(this.costTable.reimbursement==null){
       					reimbursement.html('<input id="'+this.costTable.id+'" class="form-control" style="width:100%;" type="text">');
       				}else{
       					reimbursement.html(this.costTable.reimbursement);
       					reimbursementSum = reimbursementSum + this.costTable.reimbursement;
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
									'<td>'+this.costTable.costDate.replace(/-/g,'/')+'</td>'+
									'<td>'+this.contentName+'</td>'+
									'<td>'+this.supplierName+'</td>'+
									'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
									'<td>'+this.costTable.realCost.toFixed(2)+'</td>'+
									'<td>'+reimbursement.html()+'</td>'+
									'<td>'+this.costTable.remark+'</td>'+
									'<td>'+guideLoan.html()+'</td>'+
									'<td>'+bill.html()+'</td>'+
									'<td style="vertical-align: middle;">'+this.payStatus+'</td>'+
								'</tr>');
	        		tbody.append(tr);
	        		willCost = willCost + (this.costTable.cost*this.costTable.count*this.costTable.days);
	        	});
	        	
	        	/* 设置成本变更 */
	        	if(data.changeCosts.length > 0){
	        		$("#changeCostBlue").attr("style","");
	        	}else{
	        		$("#changeCostBlue").attr("style","display:none");
	        	}
	        	$.each(data.changeCosts,function(){
	        		var reimbursement = $("<td></td>");
	        		var guideLoan = $("<td></td>");
	        		var bill = $("<td></td>");
	        		
        			if(this.costTable.bill){
        				bill.html('<i class="icon-ok bigger-130"></i>');
        			}
        			if(this.costTable.lend){
        				guideLoan.html('<i class="icon-ok bigger-130"></i>');
        				maxLoan = (parseFloat(maxLoan) + this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2);
        			}
       				if(this.costTable.reimbursement==null){
       					reimbursement.html('<input id="'+this.costTable.id+'" class="form-control" style="width:100%;" type="text">');
       				}else{
       					reimbursement.html(this.costTable.reimbursement);
       					reimbursementSum = reimbursementSum + this.costTable.reimbursement;
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
	        		var tr = $('<tr class="blue"  id="'+this.costTable.id+'">'+
			        				'<td>'+this.costTable.costDate.replace(/-/g,'/')+'</td>'+
									'<td>'+this.contentName+'</td>'+
									'<td>'+this.supplierName+'</td>'+
									'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
									'<td>'+this.costTable.realCost.toFixed(2)+'</td>'+
									'<td>'+reimbursement.html()+'</td>'+
									'<td>'+this.costTable.remark+'</td>'+
									'<td>'+guideLoan.html()+'</td>'+
									'<td>'+bill.html()+'</td>'+
									'<td style="vertical-align: middle;">'+this.payStatus+'</td>'+
								'</tr>');
	        		tbody.append(tr);
	        		willCost = willCost + (this.costTable.cost*this.costTable.count*this.costTable.days);
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
        			total = total + this.loanTable.loanAmount;
	        		$("#loanTable").append(tr);
	        	});
	        	/* 设置借款总计、最大借款额 */
	        	$("#total").text(total.toFixed(2));
	        	$("#maxLoan").text(maxLoan);
	        	$("#reimbursementSum").text(reimbursementSum.toFixed(2));
	        	$("#willCost").text(willCost.toFixed(2));
	        	$("#realIncome").text(data.realIncome.toFixed(2));
	        	$("#realGrossProfit").text((data.realIncome-reimbursementSum).toFixed(2));
	        	$("#realGrossMargin").text(((data.realIncome-reimbursementSum)/data.realIncome*100).toFixed(2)+"%");
	        	/* 设置自动计算 */
	        	$("#costs5").find("input").blur(function(){
	        		var reimbursementSum = 0;
	        		$.each($("#costs5").find("input"),function(){
	        			var val = 0;
	        			if(!isNaN(parseFloat($(this).val()))){
	        				val = parseFloat($(this).val());
	        			}
	        			reimbursementSum = reimbursementSum + val;
	        		});
	        		$("#reimbursementSum").text(reimbursementSum);
	        		$("#realGrossProfit").text((parseFloat($("#realIncome").text())-reimbursementSum).toFixed(2));
	        		$("#realGrossMargin").text(parseFloat(($("#realGrossProfit").text())/(parseFloat($("#realIncome").text()))*100).toFixed(2)+"%");
	        	});
	        }  
		});
	}
	
	/*自动填充报账金额*/
	$("#autoAddReimbursement").click(function(){
		var inputs = $("#costs5").find("input");
		if(inputs.length==0){
			
		}else{
			var reimbursementSum = 0;
			$.each(inputs,function(){
				var reimbursement = parseFloat($(this).parent().prev().text());
				$(this).val(reimbursement.toFixed(2));
				reimbursementSum = reimbursementSum + reimbursement;
			});
			$("#reimbursementSum").text(reimbursementSum.toFixed(2));
			$("#realGrossProfit").text((parseFloat($("#realIncome").text())-reimbursementSum).toFixed(2));
        	$("#realGrossMargin").text(((parseFloat($("#realIncome").text())-reimbursementSum)/parseFloat($("#realIncome").text())*100).toFixed(2)+"%");
		}
	});
	
	/*保存报账*/
	$("#reimbursementApplication").click(function(){
		var tourId= $("#reimbursementApplication").parent().attr("id");
		if($("#headAmount").children("input").length==0){
			
		}else if(isNaN(parseFloat($("#headAmount").children("input").val()))){
			$("#reimbursementApplication").attr("data-dismiss","");
			alert("*号为必填项，请输入数字");
		}else{
			var inputs = $("#costs5").find("input");
			var costTables = new Array();
			var changeCostTables = new Array();
			$.each(inputs,function(){
				if($(this).parent().parent().attr("class")!="blue"){
					costTables.push({id:$(this).parent().parent().attr("id"),
									reimbursement:$(this).val()==""?0:$(this).val()});
				}else{
					changeCostTables.push({id:$(this).parent().parent().attr("id"),
										reimbursement:$(this).val()==""?0:$(this).val()});
				}
			});
			var reimbursementTable = {tourId:tourId,headAmount:$("#headAmount").children("input").val()};
			var fullReimbursementViewModel = {costTables:costTables,changeCostTables:changeCostTables,reimbursementTable:reimbursementTable};
			var myData = JSON.stringify(fullReimbursementViewModel);
			$.ajax({
				type: "POST",  
		        contentType:"application/json;charset=utf-8",  
		        url:"/localtour/reimbursementManage/updateReimbursement",  
		        data:myData,  
		        dataType: "json",
		        async: false,
		        success:function(data){
		        	if(data==-1){
		        		$("#reimbursementApplication").attr("data-dismiss","");
		        		alert("保存失败");
		        	}else if(data==-2){
		        		$("#reimbursementApplication").attr("data-dismiss","");
		        		alert("*号为必填项");
		        	}else{
		        		$("#reimbursementApplication").attr("data-dismiss","modal");
		        	}
		        }
			});
		}
	});
});


</script>