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
								<a id="createTour" role="button" data-toggle="modal" href="#create">新增团队</a>
							</li>

							
							
						</ul><!-- .breadcrumb -->

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
								<th aria-label="" style="width: 10%;" colspan="1" rowspan="1" role="columnheader" class="sorting_disabled">
									操作
								</th>
							</tr>
						</thead>

						<tbody id="table" aria-relevant="all" aria-live="polite" role="alert">
<!-- 列表循环 -->				
							<c:forEach var="localTour" items="${localTours }" varStatus="status">
								<tr>
									<td class="center  sorting_1">
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
									<td>
										<c:choose>
											<c:when test="${localTour.localTourTable.enable }">
												${localTour.status }
											</c:when>
											<c:otherwise>
												已取消
											</c:otherwise>
										</c:choose>
									</td>
									<td>${localTour.realName }</td>
									<td id="${localTour.localTourTable.id }">
										<c:if test="${localTour.localTourTable.status==0 }">
											<a id="edit" class="green"  data-toggle="modal" href="#modify" title="编辑">
												<i class="icon-pencil bigger-130"></i>
											</a>
										</c:if>
										<c:if test="${localTour.localTourTable.status==0 }">
											<a class="pink action" id="auditing" href="#" title="提交审核">
												<i id="1" class="icon-hand-right bigger-130"></i>
											</a>
										</c:if>
										<c:if test="${localTour.localTourTable.status==1 }">
											<a class="pink action" id="unAuditing" href="#" title="退回编辑">
												<i id="0" class="icon-hand-left bigger-130"></i>
											</a>
										</c:if>
										<c:if test="${localTour.localTourTable.status==1 }">
											<a class="orange action" id="finance" href="#" title="报送财务">
												<i id="2" class="icon-share-alt bigger-130"></i>
											</a>
										</c:if>
										<c:if test="${localTour.localTourTable.status==1||localTour.localTourTable.status==2||localTour.localTourTable.status==3||localTour.localTourTable.status==4||localTour.localTourTable.status==5 }">
											<a class="grey" id="chanageCost" href="#" title="成本收入变更">
												<i class="icon-tasks bigger-130"></i>
											</a>
										</c:if>
										<c:if test="${localTour.localTourTable.status==5 }">
											<a class="blue action" id="balance" href="#" title="申请结算">
												<i id="6" class="icon-table bigger-130"></i>
											</a>
										</c:if>
										<c:if test="${localTour.localTourTable.status==1||localTour.localTourTable.status==2||localTour.localTourTable.status==3||localTour.localTourTable.status==4||localTour.localTourTable.status==5 }">
											<div class="btn-group" style="top: -3px;">
												<button data-toggle="dropdown" class="" style="border-width: 0px;background-color: rgba(255,255,255,0);">
														<i class="icon-print bigger-130"></i>
													<span class="icon-caret-down icon-on-right"></span>
												</button>
		
												<ul class="dropdown-menu dropdown-default" style="left: -120px;">
													<li>
														<a href="#">打印借款单</a>
													</li>
		
													<li>
														<a href="#">打印行程确认单</a>
													</li>
		
													<li>
														<a href="#">打印出团通知书</a>
													</li>
		
													<li class="divider"></li>
		
													<li>
														<a href="#">打印订房单</a>
													</li>
													<li>
														<a href="#">打印预借发票申请</a>
													</li>
												</ul>
											</div>
										</c:if>
										<c:if test="${localTour.localTourTable.status==0 }">
											<c:choose>
												<c:when test="${localTour.localTourTable.enable }">
													<a  class="red" id="delete" href="#" title="取消团队">
														<i class="icon-trash bigger-130"></i>
													</a>
												</c:when>
												<c:otherwise>
													<a  class="green" id="recover" href="#" title="恢复团队">
														<i class="icon-undo bigger-130"></i>
													</a>
												</c:otherwise>
											</c:choose>
											
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
													<td><input type="text"></td>
													<td>团名*</td>
													<td><input type="text"></td>
													<td>业务类型*</td>
													<td>
														<select style="display: none;" class="width-20 chosen-select" data-placeholder="Choose a Country...">
															<option value="">&nbsp;</option>
														</select>
													</td>
												</tr>
												<tr>
													<td>团队类型*</td>
													<td>
														<select style="display: none;" class="width-20 chosen-select" data-placeholder="Choose a Country...">
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
														<select style="display: none;" class="width-20 chosen-select" data-placeholder="Choose a Country...">
															<option value="">&nbsp;</option>
														</select>
													</td>
												</tr>
												<tr>
													<td>组团社*</td>
													<td>
														<select  id="customer" style="display: none;" class="width-20 chosen-select" data-placeholder="Choose a Country...">
															<option value="">&nbsp;</option>
														</select>
													</td>
													<td>组团人*</td>
													<td><input type="text"></td>
													<td>全陪人数</td>
													<td><input class="counts" type="text"></td>
												</tr>
												<tr>
													<td>成人数*</td>
													<td><input class="counts" type="text"></td>
													<td>儿童数</td>
													<td><input class="counts" type="text"></td>
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
														<input id="guide" type="text" placeholder="可选多个">
														<!-- <select style="display: none;" multiple="multiple" class="width-20 chosen-select" id="form-field-select-4" data-placeholder="可选多个...">
															<option value="">&nbsp;</option>
														</select> -->
													</td>
												</tr>
												<tr>
													<td>备注</td>
													<td><input type="text"></td>
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
													<td><input type="text"></td>
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
													<td><input type="text"></td>
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
											<div id="flight" class="tab-pane in active">
												<table class="table table-striped table-bordered table-hover no-margin">
													<thead>
														<tr>
															<th style="width: 15%;">日期</th>
															<th style="width: 17.5%;">内容</th>
															<th style="width: 17.5%;">供应商*</th>
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
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
															<td style="vertical-align: middle;">
																<a class="red delLine" href="#">
																	<i class="icon-trash bigger-130"></i>
																</a>
															</td>
										            	</tr>
													</tbody>
									            </table>
											</div>
											
											<div id="hotel" class="tab-pane">
												<table class="table table-striped table-bordered table-hover no-margin">
													<thead>
														<tr>
															<th style="width: 15%;">日期</th>
															<th style="width: 17.5%;">内容</th>
															<th style="width: 17.5%;">供应商*</th>
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
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
											
											<div id="meal" class="tab-pane">
												<table class="table table-striped table-bordered table-hover no-margin">
													<thead>
														<tr>
															<th style="width: 15%;">日期</th>
															<th style="width: 17.5%;">内容</th>
															<th style="width: 17.5%;">供应商*</th>
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
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
											
											<div id="ticket" class="tab-pane">
												<table class="table table-striped table-bordered table-hover no-margin">
													<thead>
														<tr>
															<th style="width: 15%;">日期</th>
															<th style="width: 17.5%;">内容</th>
															<th style="width: 17.5%;">供应商*</th>
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
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
											
											<div id="shuttle" class="tab-pane">
												<table class="table table-striped table-bordered table-hover no-margin">
													<thead>
														<tr>
															<th style="width: 15%;">日期</th>
															<th style="width: 17.5%;">内容</th>
															<th style="width: 17.5%;">供应商*</th>
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
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
											
											<div id="tickets" class="tab-pane">
												<table class="table table-striped table-bordered table-hover no-margin">
													<thead>
														<tr>
															<th style="width: 15%;">日期</th>
															<th style="width: 17.5%;">内容</th>
															<th style="width: 17.5%;">供应商*</th>
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
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
											
											<div id="comprehensive" class="tab-pane">
												<table class="table table-striped table-bordered table-hover no-margin">
													<thead>
														<tr>
															<th style="width: 15%;">日期</th>
															<th style="width: 17.5%;">内容</th>
															<th style="width: 17.5%;">供应商*</th>
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
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
											
											<div id="other" class="tab-pane">
												<table class="table table-striped table-bordered table-hover no-margin">
													<thead>
														<tr>
															<th style="width: 15%;">日期</th>
															<th style="width: 17.5%;">内容</th>
															<th style="width: 17.5%;">供应商*</th>
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
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
															<td><textarea class="form-control" id="form-field-8" rows="8"></textarea></td>
														</tr>
														<tr><td style="width: 60px;">餐食：</td><td><input type="text" id="form-field-1" class="col-xs-30"></td></tr>
														<tr><td>住宿：</td><td><input type="text" id="form-field-1" class="col-xs-30"></td></tr>
														<tr><td>交通：</td><td><input type="text" id="form-field-1" class="col-xs-30"></td></tr>
														<tr><td>备注：</td><td><textarea class="form-control" id="form-field-8" rows="1"></textarea></td></tr>
													</table>
												</div>
						         			</div><!-- tab content 结束 -->
					         			</div><!-- 左tab结束 -->
					         		</div><!-- 行程tab结束 -->
					         		
					         		<div id="incomes" class="tab-pane fade">
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
											            			<select style="display: none;" class="width-20 chosen-select" data-placeholder="Choose a Country...">
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
								<button id="saveNew" class="btn btn-sm btn-success pull-right" data-dismiss="modal">
									<i class="icon-save"></i>
									保存
								</button>
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
															<th style="width: 15%;">日期</th>
															<th style="width: 17.5%;">内容</th>
															<th style="width: 17.5%;">供应商*</th>
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
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
															<th style="width: 15%;">日期</th>
															<th style="width: 17.5%;">内容</th>
															<th style="width: 17.5%;">供应商*</th>
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
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
															<th style="width: 15%;">日期</th>
															<th style="width: 17.5%;">内容</th>
															<th style="width: 17.5%;">供应商*</th>
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
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
															<th style="width: 15%;">日期</th>
															<th style="width: 17.5%;">内容</th>
															<th style="width: 17.5%;">供应商*</th>
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
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
															<th style="width: 15%;">日期</th>
															<th style="width: 17.5%;">内容</th>
															<th style="width: 17.5%;">供应商*</th>
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
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
															<th style="width: 15%;">日期</th>
															<th style="width: 17.5%;">内容</th>
															<th style="width: 17.5%;">供应商*</th>
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
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
															<th style="width: 15%;">日期</th>
															<th style="width: 17.5%;">内容</th>
															<th style="width: 17.5%;">供应商*</th>
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
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
															<th style="width: 15%;">日期</th>
															<th style="width: 17.5%;">内容</th>
															<th style="width: 17.5%;">供应商*</th>
															<th style="width: 10%;">成本</th>
															<th style="width: 10%;">数量</th>
															<th style="width: 10%;">天数</th>
															<th style="width: 10%;">成本小计</th>
															<th style="width: 10%;">借款人</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
									            </table>
											</div>
											         			
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
				<div aria-hidden="true" style="display: none;" id="modify" class="modal fade" tabindex="-1">
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
											<a data-toggle="tab" href="#trips">
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
		
											<tbody id="">
												<tr>
													<td>团号*</td>
													<td><input type="text"></td>
													<td>团名*</td>
													<td><input type="text"></td>
													<td>业务类型*</td>
													<td>
														<select style="display: none;" class="width-20 chosen-select" data-placeholder="Choose a Country...">
															<option value="">&nbsp;</option>
														</select>
													</td>
												</tr>
												<tr>
													<td>团队类型*</td>
													<td>
														<select style="display: none;" class="width-20 chosen-select" data-placeholder="Choose a Country...">
															<option value="">&nbsp;</option>
														</select>
													</td>
													<td>国家/地区*</td>
													<td>
														<select style="display: none;" class="width-20 chosen-select" data-placeholder="Choose a Country...">
															<option value="">&nbsp;</option>
														</select>
													</td>
													<td>游客类型*</td>
													<td>
														<select style="display: none;" class="width-20 chosen-select" data-placeholder="Choose a Country...">
															<option value="">&nbsp;</option>
														</select>
													</td>
												</tr>
												<tr>
													<td>组团社*</td>
													<td>
														<select style="display: none;" class="width-20 chosen-select" data-placeholder="Choose a Country...">
															<option value="">&nbsp;</option>
														</select>
													</td>
													<td>组团人*</td>
													<td><input type="text"></td>
													<td>全陪人数</td>
													<td><input class="counts" type="text"></td>
												</tr>
												<tr>
													<td>成人数*</td>
													<td><input class="counts" type="text"></td>
													<td>儿童数</td>
													<td><input class="counts" type="text"></td>
													<td>人数合计</td>
													<td></td>
												</tr>
												<tr>
													<td>开始日期*</td>
													<td><input id="datepickerStart" class="form-control" type="text"></td>
													<td>结束日期*</td>
													<td><input id="datepickerEnd" class="form-control" type="text"></td>
													<td>导游</td>
													<td id="guideTd">
														<input id="guide" type="text" placeholder="可选多个">
														<!-- <select style="display: none;" multiple="multiple" class="width-20 chosen-select" id="form-field-select-4" data-placeholder="可选多个...">
															<option value="">&nbsp;</option>
														</select> -->
													</td>
												</tr>
												<tr>
													<td>备注</td>
													<td><input type="text"></td>
												</tr>
											</tbody>
										</table>
										<div class="modal-header no-padding">
											<div class="table-header">
												抵离信息
												<a class="white" href="#"><i class="icon-plus bigger-100"></i></a>
											</div>
										</div>
										<table class="table table-striped table-bordered table-hover no-margin-bottom no-border-top">
											<thead>
												<tr>
													<th>出发地</th>
													<th>抵达交通</th>
													<th>抵达时间</th>
													<th>抵达班次</th>
													<th>抵达地</th>
													<th aria-label="" style="width: 10%;" colspan="1" rowspan="1" role="columnheader" class="sorting_disabled">
														操作
													</th>
												</tr>
											</thead>
		
											<tbody id="">
												<tr>
													<td>
														<select>
															<option>广州</option>
														</select>
													</td>
													<td>
														<select>
															<option>飞机</option>
														</select>
													</td>
													<td><input id="arrTime" class="form-control" type="text"></td>
													<td><input type="text"></td>
													<td>
														<select>
															<option>烟台</option>
														</select>
													</td>
													<td>
														<a id="edit" class="green" href="#">
																<i class="icon-pencil bigger-130"></i>
														</a>
														<a class="red" href="#">
																<i class="icon-trash bigger-130"></i>
														</a>
													</td>
												</tr>
											</tbody>
											<thead>
												<tr>
													<th>前往地</th>
													<th>离开交通</th>
													<th>离开时间</th>
													<th>离开班次</th>
													<th>离开地</th>
													<th aria-label="" style="width: 10%;" colspan="1" rowspan="1" role="columnheader" class="sorting_disabled">
														操作
													</th>
												</tr>
											</thead>
											<tbody id="">
												<tr>
													<td>
														<select>
															<option>广州</option>
														</select>
													</td>
													<td>
														<select>
															<option>飞机</option>
														</select>
													</td>
													<td><input id="departTime" class="form-control" type="text"></td>
													<td><input type="text"></td>
													<td>
														<select>
															<option>青岛</option>
														</select>
													</td>
													<td>
														<a id="edit" class="green" href="#">
															<i class="icon-pencil bigger-130"></i>
														</a>
														<a class="red" href="#">
															<i class="icon-trash bigger-130"></i>
														</a>
													</td>
												</tr>
											</tbody>
										</table>
					         		</div><!-- 修改tab结束 -->
					         		<div id="costs" class="tab-pane fade">
					         			<div class="tabbable tabs-left">
					         			<ul class="nav nav-tabs">
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
										<div class="tab-content no-padding">
											<div id="flight" class="tab-pane in active">
												<table></table>
											</div>
											
											<div id="hotel" class="tab-pane">
												<table class="table table-striped table-bordered table-hover">
													<thead>
														<tr>
															<th>日期</th>
															<th>内容</th>
															<th>供应商</th>
															<th>成本</th>
															<th>数量</th>
															<th>天数</th>
															<th>成本小计</th>
															<!-- <th>已借金额</th> -->
															<th>借款人</th>
														</tr>
													</thead>
									            	<tr>
									            		<td>2016/05/27</td>
									            		<td>标准间(不含早)</td>
									            		<td>青岛香格里拉大酒店</td>
									            		<td>500</td>
									            		<td>10</td>
									            		<td>2</td>
									            		<td>10000</td>
									            		<td>15000</td>
									            	</tr>
									            </table>
											</div>
											
											<div id="meal" class="tab-pane">
												<table></table>
											</div>
											
											<div id="ticket" class="tab-pane">
												<table></table>
											</div>
											
											<div id="shuttle" class="tab-pane">
												<table></table>
											</div>
											
											<div id="tickets" class="tab-pane">
												<table></table>
											</div>
											
											<div id="comprehensive" class="tab-pane">
												<table></table>
											</div>
											
											<div id="other" class="tab-pane">
												<table></table>
											</div>
											         			
					         			</div><!-- tab content 结束 -->
					         			</div><!-- 左tab 结束 -->
					         		</div><!-- 成本tab结束 -->
					         		
					         		<div id="trips" class="tab-pane fade">
					         			<div class="tabbable tabs-left">
					         			<ul class="nav nav-tabs" id="myTab3">
											<li class="active">
												<a data-toggle="tab" href="#day1">
													2016.05.27
												</a>
											</li>
											<li>
												<a data-toggle="tab" href="#day2">
													2016.05.28
												</a>
											</li>
											<li>
												<a data-toggle="tab" href="#day3">
													2016.05.29
												</a>
											</li>
											<li>
												<a data-toggle="tab" href="#day4">
													2016.05.30
												</a>
											</li>
										</ul>
										<div class="tab-content no-padding">
											<div id="day1" class="tab-pane in active">
												<div class="modal-header no-padding">
													<div class="table-header">
														日序：1
											 		</div>
											  	 </div>
												<table class="table table-striped table-bordered table-hover no-margin-bottom no-border-top">
													<tr>
														<td>行程：</td>
														<td><textarea class="form-control" id="form-field-8" placeholder="Default Text" rows="8"></textarea></td>
													</tr>
													<tr><td>餐食：</td><td><input type="text" id="form-field-1" placeholder="餐食" class="col-xs-30"></td></tr>
													<tr><td>住宿：</td><td><input type="text" id="form-field-1" placeholder="住宿" class="col-xs-30"></td></tr>
													<tr><td>交通：</td><td><input type="text" id="form-field-1" placeholder="交通" class="col-xs-30"></td></tr>
													<tr><td>备注：</td><td><textarea class="form-control" id="form-field-8" placeholder="Default Text" rows="1"></textarea></td></tr>
												</table>
											</div>
											
											<div id="day2" class="tab-pane">
												<table></table>
											</div>
											
											<div id="day3" class="tab-pane">
												<table></table>
											</div>
											
											<div id="day4" class="tab-pane">
												<table></table>
											</div>
					         			</div><!-- tab content 结束 -->
					         			</div><!-- 左tab结束 -->
					         		</div><!-- 行程tab结束 -->
					         		
					         		<div id="incomes" class="tab-pane fade">
					         			<div class="tabbable tabs-left">
										<div class="tab-content no-padding">
											
											
												<table class="table table-striped table-bordered table-hover">
													<thead>
														<tr>
															<th style="width: 10%">日期</th>
															<th>客户</th>
															<th style="width: 10%">收入</th>
															<th style="width: 10%">实收</th>
															<th style="width: 10%">已开发票金额</th>
															<th>内容</th>
														</tr>
													</thead>
									            	<tr>
									            		<td>2016/05/27</td>
									            		<td>标准间(不含早)</td>
									            		<td>青岛香格里拉大酒店</td>
									            		<td>500</td>
									            		<td>750</td>
									            		<td><i class="green icon-ok bigger-130"></i></td>
									            	</tr>
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
								<button class="btn btn-sm btn-success pull-right" data-dismiss="modal">
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
		$("#gourpManage").addClass("open");
		$("#gourpManage").children("ul").attr("style","display:block");
		$("#localTourManage").addClass("active");
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
		        	selects.not(".traffic").html('<option value="">&nbsp;</option>');
		        	$.each(data.businessTypes,function(){
		        		selects.eq(0).append('<option value="'+this.id+'">'+this.businessTypeName+'</option>');
		        	});
		        	$.each(data.tourTypes,function(){
		        		selects.eq(1).append('<option value="'+this.id+'">'+this.tourTypeName+'</option>');
		        	});
		        	$.each(data.regions,function(){
		        		$(".region").append('<option value="'+this.id+'">'+this.regionName+'</option>');
		        	});
		        	$.each(data.visitorTypes,function(){
		        		selects.eq(3).append('<option value="'+this.id+'">'+this.visitorTypeName+'</option>');
		        	});
		        	$.each(data.customerAgencys,function(){
		        		selects.eq(4).append('<option value="'+this.id+'">'+this.customerAgencyName+'</option>');
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
			$("#guideTd").html('<input id="guide" type="text" placeholder="可选多个" style="width:100%;">');
		});
		$("#datepickerEnd").click(function(){
			$("#guideTd").html('<input id="guide" type="text" placeholder="可选多个" style="width:100%;">');
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
			if($(this).parents("div").attr("id")=="flight"){
				tr.children("td").last().append('<input type="hidden" value="1" />');
				$.each(selectInfo.flightContents,function(){
	        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
	        	});
	        	$.each(selectInfo.flightSuppliers,function(){
	        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
	        	});
			}else if($(this).parents("div").attr("id")=="hotel"){
				tr.children("td").last().append('<input type="hidden" value="2" />');
				$.each(selectInfo.hotelContents,function(){
	        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
	        	});
	        	$.each(selectInfo.hotelSuppliers,function(){
	        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
	        	});
			}else if($(this).parents("div").attr("id")=="meal"){
				tr.children("td").last().append('<input type="hidden" value="3" />');
				$.each(selectInfo.mealContents,function(){
	        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
	        	});
	        	$.each(selectInfo.mealSuppliers,function(){
	        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
	        	});
			}else if($(this).parents("div").attr("id")=="ticket"){
				tr.children("td").last().append('<input type="hidden" value="4" />');
				$.each(selectInfo.ticketContents,function(){
	        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
	        	});
	        	$.each(selectInfo.ticketSuppliers,function(){
	        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
	        	});
			}else if($(this).parents("div").attr("id")=="shuttle"){
				tr.children("td").last().append('<input type="hidden" value="5" />');
				$.each(selectInfo.shuttleContents,function(){
	        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
	        	});
	        	$.each(selectInfo.shuttleSuppliers,function(){
	        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
	        	});
			}else if($(this).parents("div").attr("id")=="tickets"){
				tr.children("td").last().append('<input type="hidden" value="6" />');
				$.each(selectInfo.ticketsContents,function(){
	        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
	        	});
	        	$.each(selectInfo.ticketsSuppliers,function(){
	        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
	        	});
			}else if($(this).parents("div").attr("id")=="comprehensive"){
				tr.children("td").last().append('<input type="hidden" value="7" />');
				$.each(selectInfo.comprehensiveContents,function(){
	        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
	        	});
	        	$.each(selectInfo.comprehensiveSuppliers,function(){
	        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
	        	});
			}else if($(this).parents("div").attr("id")=="other"){
				tr.children("td").last().append('<input type="hidden" value="8" />');
				$.each(selectInfo.otherContents,function(){
	        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
	        	});
	        	$.each(selectInfo.otherSuppliers,function(){
	        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
	        	});
			}
			var guides = $("#guides");
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
	/* 收入 */
		$(".addIncome").click(function(){
			var tbody = $(this).parents("table").children("tbody");
			tbody.append("<tr>"+$("#incomeModel").html()+"</tr>");
			var tr = tbody.children("tr").not("#incomeModel").last();
			tr.find("#incomeTime").attr("id","").datepicker({
				showOtherMonths: true,
				selectOtherMonths: false,
			});
			tr.children("td").eq(1).text($("#customer").find("option:selected").text());
			
		});
	/* 删除 */
		$("#table").delegate("#delete","click",function(){
			var obj = $(this);
			var td = obj.parent();
			var myData = {id:td.attr("id")};
			$.ajax({  
		        type: "GET",
		        contentType:"application/json;charset=utf-8",
		        url:"/localtour/localTourManage/del",
		        data:myData,
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	obj.attr({"class":"green","id":"recover"});
		        	obj.children("i").attr("class","icon-undo bigger-130");
		        	td.prev().prev().text("已取消");
		        }  
			});
		}); 
	/* 恢复 */
		$("#table").delegate("#recover","click",function(){
			var obj = $(this);
			var td = obj.parent();
			var myData = {id:td.attr("id")};
			$.ajax({  
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"/localtour/localTourManage/recover",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	obj.attr({"class":"red","id":"delete"});
		        	obj.children("i").attr("class","icon-trash bigger-130");
		        	var span = obj.parents("td").prev().children("span");
		        	td.prev().prev().text("新建");
		        }  
			}); 
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
			var guideIds  = selects.eq(5).val()+"";
			var remark = inputs.eq(14).val();
			var userId = '<%=((UserTable)session.getAttribute("user")).getId()%>';
			var deptId = '<%=((UserTable)session.getAttribute("user")).getDeptId()%>';
			var status = 0;
			var enable = true;
			var days = (endTime-startTime)/1000/60/60/24+1;
			var localTourTable={tourNo:tourNo,tourName:tourName,businessTypeId:businessTypeId,tourTypeId:tourTypeId,regionId:regionId,visitorTypeId:visitorTypeId,
						   customerAgencyId:customerAgencyId,organizor:organizor,qpGuideNo:qpGuideNo,adultNo:adultNo,childrenNo:childrenNo,startTime:startTime,
						   endTime:endTime,guideIds:guideIds,remark:remark,userId:userId,deptId:deptId,status:status,enable:enable};
			
			var arrTrs = $("#create").find(".arrInfo").children("tr").not("#arrModel");
			var arrTables = new Array();
			for (var int = 0; int < arrTrs.length; int++) {
				var arrSelects = arrTrs.eq(int).find("select");
				var arrInputs = arrTrs.eq(int).find("input");
				if(arrSelects.eq(0).val()!=""||arrSelects.eq(1).children("option:selected").text()!=""||arrInputs.eq(2).val()!=""||arrInputs.eq(3).val()!=""||arrSelects.eq(2).val()!=""){
					arrTables[int]={originId:arrSelects.eq(0).val(),
							arrTraffic:arrSelects.eq(1).children("option:selected").text(),
							arrTime:new Date(arrInputs.eq(2).val()),
							arrTrafficNo:arrInputs.eq(3).val(),
							arrRegionId:arrSelects.eq(2).val()};	
					}
				}
			
			var departTrs = $("#create").find(".departInfo").children("tr").not("#departModel");
			var departTables = new Array();
			for (var int = 0; int < departTrs.length; int++) {
				var departSelects = departTrs.eq(int).find("select");
				var departInputs = departTrs.eq(int).find("input");
				if(departSelects.eq(0).val()!=""||departSelects.eq(1).children("option:selected").text()!=""||departInputs.eq(2).val()!=""||departInputs.eq(3).val()!=""||departSelects.eq(2).val()!=""){
					departTables[int] = {destId:departSelects.eq(0).val(),
							departTraffic:departSelects.eq(1).children("option:selected").text(),
							departTime:new Date(departInputs.eq(2).val()),
							departTrafficNo:departInputs.eq(3).val(),
							departRegionId:departSelects.eq(2).val()};
				}
			}
			
			var tripTables = new Array();
			var tripsTbody = $("#create").find("#trips").find("div").not("#tripModel").children("table").children("tbody");
			for (var int = 0; int < tripsTbody.length; int++) {
				var tripInputs =  tripsTbody.eq(int).find("input");
				var tripTextareas =  tripsTbody.eq(int).find("textarea");
				if(tripTextareas.eq(0).val()!=""||tripInputs.eq(0).val()!=""||tripInputs.eq(1).val()!=""||tripInputs.eq(2).val()!=""||tripTextareas.eq(1).val()!=""){
					tripTables[int] = {number:int,trip:tripTextareas.eq(0).val(),meal:tripInputs.eq(0).val(),stay:tripInputs.eq(1).val(),traffic:tripInputs.eq(2).val(),remark:tripTextareas.eq(1).val()};
				}
			}
			
			var costTables = new Array();
			var costTrs = $("#create").find("#costs").find("tbody").find("tr").not("#costModel");
			for (var int = 0; int < costTrs.length; int++) {
				var costInputs = costTrs.eq(int).find("input");
				var costSelects = costTrs.eq(int).find("select");
				costTables[int] = {costDate:new Date(costInputs.eq(0).val()),
						contentId:costSelects.eq(0).val(),
						supplierId:costSelects.eq(1).val(),
						cost:costInputs.eq(3).val(),
						count:costInputs.eq(4).val(),
						days:costInputs.eq(5).val(),
						borrowUserId:costSelects.eq(2).val(),
						supplierScopeId:costInputs.eq(6).val()};
			}
			
			var incomeTables = new Array();
			var incomeTrs = $("#create").find("#incomes").find("tbody").find("tr").not("#incomeModel");
			for (var int = 0; int < incomeTrs.length; int++) {
				var incomeInputs = incomeTrs.eq(int).find("input");
				incomeTables[int] = {incomeDate:new Date(incomeInputs.eq(0).val()),customerAgencyId:customerAgencyId,income:incomeInputs.eq(1).val(),remark:incomeInputs.eq(2).val()};
			}
			
			var fullLocalTourViewModel = {localTourTable:localTourTable,arrTables:arrTables,departTables:departTables,tripTables:tripTables,costTables:costTables,incomeTables:incomeTables};
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
		        		$("#table").prepend('<tr>'+
						        				'<td class="center  sorting_1">'+
													'<label>'+
														'<input class="ace" type="checkbox">'+
														'<span class="lbl"></span>'+
													'</label>'+
												'</td>'+
												'<td><a id="" role="button" data-toggle="modal" href="#modify">'+tourNo+'</a></td>'+
												'<td>'+tourName+'</td>'+
												'<td>'+adultNo+'</td>'+
												'<td>'+childrenNo+'</td>'+
												'<td>'+days+'</td>'+
												'<td>'+startTime.getFullYear()+'-'+startTime.getMonth()+'-'+startTime.getDate()+'</td>'+
												'<td>'+endTime.getFullYear()+'-'+endTime.getMonth()+'-'+endTime.getDate()+'</td>'+
												'<td>新建</td>'+
												'<td>'+'<%=((UserTable)session.getAttribute("user")).getRealName()%>'+'</td>'+
												'<td id="'+data+'">'+
													'<a id="edit" class="green" href="#" title="编辑">'+
														'<i class="icon-pencil bigger-130"></i>'+
													'</a>'+
													'<a class="pink action" id="auditing" href="#" title="提交审核" style="margin-left:3px;">'+
														'<i id="1" class="icon-hand-right bigger-130"></i>'+
													'</a>'+
													'<a title="取消团队" class="red" id="delete" href="#" style="margin-left:3px;">'+
														'<i class="icon-trash bigger-130"></i>'+
														
													'</a>'+
												'</td>'+
		        							'</tr>');
		        	}else{
		        		alert("请检查团号是否重复，*号为必填项");
		        	}
		        }  
			 });
		});
	/* 查看 */
		$("#table").delegate("#findTour","click",function(){
			var myData = {tourId:$(this).parent().siblings().last().attr("id")};
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
		        	td.eq(14).text(data.tourInfo.guideNames);
		        	td.eq(15).text(data.localTourTable.remark);
		        	var arrInfo = $("#find").find(".arrInfo");
		        	arrInfo.html("");
		        	$.each(data.arrs,function(){
		        		arrInfo.append('<tr>'+
		        							'<td>'+this.region1+'</td>'+
		        							'<td>'+this.arrTable.arrTraffic+'</td>'+
		        							'<td>'+this.arrTable.arrTime+'</td>'+
		        							'<td>'+this.arrTable.arrTrafficNo+'</td>'+
		        							'<td>'+this.region2+'</td>'+
		        						'</tr>');
		        	});
		        	var departInfo = $("#find").find(".departInfo");
		        	departInfo.html("");
		        	$.each(data.departs,function(){
		        		departInfo.append('<tr>'+
		        							'<td>'+this.region1+'</td>'+
		        							'<td>'+this.departTable.departTraffic+'</td>'+
		        							'<td>'+this.departTable.departTime+'</td>'+
		        							'<td>'+this.departTable.departTrafficNo+'</td>'+
		        							'<td>'+this.region2+'</td>'+
		        						'</tr>');
		        	});
		        	var startTime = new Date(data.localTourTable.startTime);
					var endTime = new Date(data.localTourTable.endTime);
					var days = (endTime-startTime)/1000/60/60/24+1;
					var ul = $("#find").find("#myTab3");
					var div = ul.parent().children("div");
					var tripModel = $("#find").find("#tripModel");
					ul.html("");
					div.html("");
					div.append('<div id="tripModel" style="display:none;">'+tripModel.html()+'</div>');
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
		        								'<td>'+this.costTable.cost*this.costTable.count*this.costTable.days+'</td>'+
		        								'<td>'+this.borrowUserName+'</td>'+
		        					'</tr>');
		        		}else if(this.costTable.supplierScopeId==2){
		        			hotel.append('<tr>'+
			    								'<td>'+this.costTable.costDate+'</td>'+
			    								'<td>'+this.contentName+'</td>'+
			    								'<td>'+this.supplierName+'</td>'+
			    								'<td>'+this.costTable.cost+'</td>'+
			    								'<td>'+this.costTable.count+'</td>'+
			    								'<td>'+this.costTable.days+'</td>'+
			    								'<td>'+this.costTable.cost*this.costTable.count*this.costTable.days+'</td>'+
			    								'<td>'+this.borrowUserName+'</td>'+
			    					'</tr>');
		        		}else if(this.costTable.supplierScopeId==3){
		        			meal.append('<tr>'+
			    								'<td>'+this.costTable.costDate+'</td>'+
			    								'<td>'+this.contentName+'</td>'+
			    								'<td>'+this.supplierName+'</td>'+
			    								'<td>'+this.costTable.cost+'</td>'+
			    								'<td>'+this.costTable.count+'</td>'+
			    								'<td>'+this.costTable.days+'</td>'+
			    								'<td>'+this.costTable.cost*this.costTable.count*this.costTable.days+'</td>'+
			    								'<td>'+this.borrowUserName+'</td>'+
			    					'</tr>');		        			
		        		}else if(this.costTable.supplierScopeId==4){
		        			ticket.append('<tr>'+
			    								'<td>'+this.costTable.costDate+'</td>'+
			    								'<td>'+this.contentName+'</td>'+
			    								'<td>'+this.supplierName+'</td>'+
			    								'<td>'+this.costTable.cost+'</td>'+
			    								'<td>'+this.costTable.count+'</td>'+
			    								'<td>'+this.costTable.days+'</td>'+
			    								'<td>'+this.costTable.cost*this.costTable.count*this.costTable.days+'</td>'+
			    								'<td>'+this.borrowUserName+'</td>'+
			    					'</tr>');		        			
		        		}else if(this.costTable.supplierScopeId==5){
		        			shuttle.append('<tr>'+
			    								'<td>'+this.costTable.costDate+'</td>'+
			    								'<td>'+this.contentName+'</td>'+
			    								'<td>'+this.supplierName+'</td>'+
			    								'<td>'+this.costTable.cost+'</td>'+
			    								'<td>'+this.costTable.count+'</td>'+
			    								'<td>'+this.costTable.days+'</td>'+
			    								'<td>'+this.costTable.cost*this.costTable.count*this.costTable.days+'</td>'+
			    								'<td>'+this.borrowUserName+'</td>'+
			    					'</tr>');	
		        		}else if(this.costTable.supplierScopeId==6){
		        			tickets.append('<tr>'+
			    								'<td>'+this.costTable.costDate+'</td>'+
			    								'<td>'+this.contentName+'</td>'+
			    								'<td>'+this.supplierName+'</td>'+
			    								'<td>'+this.costTable.cost+'</td>'+
			    								'<td>'+this.costTable.count+'</td>'+
			    								'<td>'+this.costTable.days+'</td>'+
			    								'<td>'+this.costTable.cost*this.costTable.count*this.costTable.days+'</td>'+
			    								'<td>'+this.borrowUserName+'</td>'+
			    					'</tr>');
		        		}else if(this.costTable.supplierScopeId==7){
		        			comprehensive.append('<tr>'+
			    								'<td>'+this.costTable.costDate+'</td>'+
			    								'<td>'+this.contentName+'</td>'+
			    								'<td>'+this.supplierName+'</td>'+
			    								'<td>'+this.costTable.cost+'</td>'+
			    								'<td>'+this.costTable.count+'</td>'+
			    								'<td>'+this.costTable.days+'</td>'+
			    								'<td>'+this.costTable.cost*this.costTable.count*this.costTable.days+'</td>'+
			    								'<td>'+this.borrowUserName+'</td>'+
			    					'</tr>');
		        		}else if(this.costTable.supplierScopeId==8){
		        			other.append('<tr>'+
			    								'<td>'+this.costTable.costDate+'</td>'+
			    								'<td>'+this.contentName+'</td>'+
			    								'<td>'+this.supplierName+'</td>'+
			    								'<td>'+this.costTable.cost+'</td>'+
			    								'<td>'+this.costTable.count+'</td>'+
			    								'<td>'+this.costTable.days+'</td>'+
			    								'<td>'+this.costTable.cost*this.costTable.count*this.costTable.days+'</td>'+
			    								'<td>'+this.borrowUserName+'</td>'+
			    					'</tr>');
		        		}
		        	});
		        	var tbody = $("#incomes2").find("tbody");
		        	tbody.html("");
		        	$.each(data.incomes,function(){
		        		tbody.append('<tr>'+
		        							'<td>'+this.incomeTable.incomeDate+'</td>'+
		        							'<td>'+this.customerAgencyName+'</td>'+
		        							'<td>'+this.incomeTable.income+'</td>'+
		        							'<td>'+this.incomeTable.realIncome+'</td>'+
		        							'<td>'+this.invoiceAmount+'</td>'+
		        							'<td>'+this.incomeTable.remark+'</td>'+
		        					'</tr>');
		        	});
		        }  
			});
		});
	/* 编辑 */
		
	/*回车更新 */		
		/* $("#table").delegate("#update","keydown",function(event){
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
		}); */
		/* 按钮更新 */
		/* $("#table").delegate("#save","click",function(){
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
		}); */
			
	
	});
	
</script>
