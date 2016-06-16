<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String path = request.getContextPath()+"/"; %>

<jsp:include page="../../../resources/include/header.jsp"></jsp:include>

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
									<td><a id="" role="button" data-toggle="modal" href="#modify">${localTour.localTourTable.tourNo }</a></td>
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
									<td>${localTour.userName }</td>
									<td id="${localTour.localTourTable.id }">
										<c:if test="${localTour.localTourTable.status==0 }">
											<a id="edit" class="green" href="#" title="编辑">
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
											<a class="grey" id="addCost" href="#" title="成本收入变更">
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
											<a href="/localtour/supplierInfoManage?page=${pageNo-1 }&key=${key }"><i class="icon-double-angle-left"></i></a>
										</li>
										<c:forEach var="page" begin="1" end="${pageMax }">
											<li <c:if test="${pageNo==page }">class="active"</c:if>>
												<a href="/localtour/supplierInfoManage?page=${page }&key=${key }">${page }</a>
											</li>
										</c:forEach>
										<li <c:choose><c:when test="${pageNo==pageMax }">class="next disabled"</c:when><c:otherwise>class="next"</c:otherwise></c:choose>>
											<a href="/localtour/supplierInfoManage?page=${pageNo+1 }&key=${key }"><i class="icon-double-angle-right"></i></a>
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
					<div class="modal-dialog" style="width:80%;">
						<div class="modal-content">
							<div class="modal-header no-padding">
								<div id="headerName" class="table-header">
									新增团队
								</div>
							</div>

							<div class="modal-body no-padding">
								<table class="table table-striped table-bordered table-hover no-margin-bottom no-border-top">
									<thead>
									</thead>

									<tbody id="">
										<tr>
											<td>团号</td>
											<td><input type="text"></td>
											<td>团名</td>
											<td><input type="text"></td>
											<td>业务类型</td>
											<td>
												<select style="display: none;" class="width-80 chosen-select" data-placeholder="Choose a Country...">
													<option value="">&nbsp;</option>
												</select>
											</td>
										</tr>
										<tr>
											<td>团队类型</td>
											<td>
												<select style="display: none;" class="width-80 chosen-select" data-placeholder="Choose a Country...">
													<option value="">&nbsp;</option>
												</select>
											</td>
											<td>国家/地区</td>
											<td>
												<select style="display: none;" class="width-80 chosen-select" data-placeholder="Choose a Country...">
													<option value="">&nbsp;</option>
												</select>
											</td>
											<td>游客类型</td>
											<td>
												<select style="display: none;" class="width-80 chosen-select" data-placeholder="Choose a Country...">
													<option value="">&nbsp;</option>
												</select>
											</td>
										</tr>
										<tr>
											<td>组团社</td>
											<td>
												<select style="display: none;" class="width-80 chosen-select" data-placeholder="Choose a Country...">
													<option value="">&nbsp;</option>
												</select>
											</td>
											<td>组团人</td>
											<td><input type="text"></td>
											<td>全陪人数</td>
											<td><input class="counts" type="text"></td>
										</tr>
										<tr>
											<td>成人数</td>
											<td><input class="counts" type="text"></td>
											<td>儿童数</td>
											<td><input class="counts" type="text"></td>
											<td>人数合计</td>
											<td></td>
										</tr>
										<tr>
											<td>开始日期</td>
											<td><input id="datepickerStart" class="form-control" type="text"></td>
											<td>结束日期</td>
											<td><input id="datepickerEnd" class="form-control" type="text"></td>
											<td>导游</td>
											<td>
												<select style="display: none;" multiple="multiple" class="width-80 chosen-select" id="form-field-select-4" data-placeholder="可选多个...">
													<option value="">&nbsp;</option>
												</select>
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
						</div><!-- /.modal-content -->
					</div><!-- /.modal-dialog -->
				</div>
<!-- 新增结束 -->
<!-- 编辑模板 -->
				<div aria-hidden="true" style="display: none;" id="modify" class="modal fade" tabindex="-1">
					<div class="modal-dialog" style="width: 80%;">
					      <div class="modal-content">
					         <div class="modal-header no-padding">
								<div id="headerName" class="table-header">
									团队信息
						 		</div>
						  	 </div>
					         <div class="modal-body no-padding">
					         	<div class="tabbable">
					         		<ul class="nav nav-tabs padding-18" id="myTab">
										<li class="active">
											<a data-toggle="tab" href="#tour-tab-1">
												<i class="blue icon-laptop bigger-120"></i>
												基本信息
											</a>
										</li>
					
					
										<li>
											<a data-toggle="tab" href="#tour-tab-3">
												<i class="orange icon-calendar bigger-120"></i>
												行程
											</a>
										</li>
										
										
										<li>
											<a data-toggle="tab" href="#tour-tab-2">
												<i class="green icon-credit-card bigger-120"></i>
												成本
											</a>
										</li>
										<li>
											<a data-toggle="tab" href="#tour-tab-4">
												<i class="pink icon-briefcase bigger-120"></i>
												收入
											</a>
										</li>
									</ul>
					         	</div>
					         	
					         	<div class="tab-content no-border padding-6">
					         		<div id="tour-tab-1" class="tab-pane fade in active">
					         			<table class="table table-striped table-bordered table-hover no-margin-bottom no-border-top">
											<thead>
											</thead>
											<tbody id="">
												<tr>
													<td>团号</td>
													<td><input type="text"></td>
													<td>团名</td>
													<td><input type="text"></td>
													<td>导游</td>
													<td>
														<select>
															<option>李四</option>
														</select>
													</td>
												</tr>
												<tr>
													<td>业务类型</td>
													<td>
														<select>
															<option>地接</option>
														</select>
													</td>
													<td>团队类型</td>
													<td>
														<select>
															<option>横向地接</option>
														</select>
													</td>
													<td>国家/地区</td>
													<td>
														<select>
															<option>广州</option>
														</select>
													</td>
												</tr>
												<tr>
													<td>游客类型</td>
													<td>
														<select>
															<option>外国人</option>
														</select>
													</td>
													<td>组团社</td>
													<td>
														<select>
															<option>广州旅行社</option>
														</select>
													</td>
													<td>组团人</td>
													<td><input type="text"></td>
												</tr>
												<tr>
													<td>成人数</td>
													<td><input type="text"></td>
													<td>儿童数</td>
													<td><input type="text"></td>
													<td>全陪人数</td>
													<td><input type="text"></td>
												</tr>
												<tr>
													<td>开始日期</td>
													<td><input id="datepickerStart" class="form-control" type="text"></td>
													<td>结束日期</td>
													<td><input id="datepickerEnd" class="form-control" type="text"></td>
													<td>人数合计</td>
													<td></td>
												</tr>			
											</tbody>
										</table>
								<div class="modal-header no-padding">
								<div id="headerName" class="table-header">
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
					         		<div id="tour-tab-2" class="tab-pane fade">
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
															<th>报价</th>
															<th>数量</th>
															<th>天数</th>
															<th>成本小计</th>
															<th>报价小计</th>
														</tr>
													</thead>
									            	<tr>
									            		<td>2016/05/27</td>
									            		<td>标准间(不含早)</td>
									            		<td>青岛香格里拉大酒店</td>
									            		<td>500</td>
									            		<td>750</td>
									            		<td>10</td>
									            		<td>2</td>
									            		<td>10000</td>
									            		<td>15000</td>
									            	</tr>
									            	<tr>
									            		<td>2016/05/27</td>
									            		<td>标准间(不含早)</td>
									            		<td>青岛香格里拉大酒店</td>
									            		<td>500</td>
									            		<td>750</td>
									            		<td>10</td>
									            		<td>2</td>
									            		<td>10000</td>
									            		<td>15000</td>
									            	</tr>
									            	<tr>
									            		<td>2016/05/27</td>
									            		<td>标准间(不含早)</td>
									            		<td>青岛香格里拉大酒店</td>
									            		<td>500</td>
									            		<td>750</td>
									            		<td>10</td>
									            		<td>2</td>
									            		<td>10000</td>
									            		<td>15000</td>
									            	</tr>
									            	<tr>
									            		<td>2016/05/27</td>
									            		<td>标准间(不含早)</td>
									            		<td>青岛香格里拉大酒店</td>
									            		<td>500</td>
									            		<td>750</td>
									            		<td>10</td>
									            		<td>2</td>
									            		<td>10000</td>
									            		<td>15000</td>
									            	</tr>
									            	<tr>
									            		<td>2016/05/27</td>
									            		<td>标准间(不含早)</td>
									            		<td>青岛香格里拉大酒店</td>
									            		<td>500</td>
									            		<td>750</td>
									            		<td>10</td>
									            		<td>2</td>
									            		<td>10000</td>
									            		<td>15000</td>
									            	</tr>
									            	<tr>
									            		<td>2016/05/27</td>
									            		<td>标准间(不含早)</td>
									            		<td>青岛香格里拉大酒店</td>
									            		<td>500</td>
									            		<td>750</td>
									            		<td>10</td>
									            		<td>2</td>
									            		<td>10000</td>
									            		<td>15000</td>
									            	</tr>
									            	<tr>
									            		<td>2016/05/27</td>
									            		<td>标准间(不含早)</td>
									            		<td>青岛香格里拉大酒店</td>
									            		<td>500</td>
									            		<td>750</td>
									            		<td>10</td>
									            		<td>2</td>
									            		<td>10000</td>
									            		<td>15000</td>
									            	</tr>
									            	<tr>
									            		<td>2016/05/27</td>
									            		<td>标准间(不含早)</td>
									            		<td>青岛香格里拉大酒店</td>
									            		<td>500</td>
									            		<td>750</td>
									            		<td>10</td>
									            		<td>2</td>
									            		<td>10000</td>
									            		<td>15000</td>
									            	</tr>
									            	<tr>
									            		<td>2016/05/27</td>
									            		<td>标准间(不含早)</td>
									            		<td>青岛香格里拉大酒店</td>
									            		<td>500</td>
									            		<td>750</td>
									            		<td>10</td>
									            		<td>2</td>
									            		<td>10000</td>
									            		<td>15000</td>
									            	</tr>
									            	<tr>
									            		<td>2016/05/27</td>
									            		<td>标准间(不含早)</td>
									            		<td>青岛香格里拉大酒店</td>
									            		<td>500</td>
									            		<td>750</td>
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
					         		
					         		<div id="tour-tab-3" class="tab-pane fade">
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
													<div id="headerName" class="table-header">
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
					         		
					         		<div id="tour-tab-4" class="tab-pane fade">
					         			<div class="tabbable tabs-left">
										<div class="tab-content no-padding">
											
											
												<table class="table table-striped table-bordered table-hover">
													<thead>
														<tr>
															<th>日期</th>
															<th>内容</th>
															<th>客户</th>
															<th>收入</th>
															<th>已开发票金额</th>
															<th>是否预借</th>
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
			$("#gourpManage").addClass("open");
			$("#gourpManage").children("ul").attr("style","display:block");
			$("#localTourManage").addClass("active");
			$("#create").find("input").attr("style","width:100%;");
			$("#create").find("select").attr("style","width:100%;");
			$("#edit").tooltip({
				show: null,
				position: {
					my: "left top",
					at: "left bottom"
				},
				open: function( event, ui ) {
					ui.tooltip.animate({ top: ui.tooltip.position().top + 10 }, "fast" );
				}
			});
			$("#delete").tooltip({
				show: null,
				position: {
					my: "left top",
					at: "left bottom"
				},
				open: function( event, ui ) {
					ui.tooltip.animate({ top: ui.tooltip.position().top + 10 }, "fast" );
				}
			});
			$("#recover").tooltip({
				show: null,
				position: {
					my: "left top",
					at: "left bottom"
				},
				open: function( event, ui ) {
					ui.tooltip.animate({ top: ui.tooltip.position().top + 10 }, "fast" );
				}
			});
			$("#addCost").tooltip({
				show: null,
				position: {
					my: "left top",
					at: "left bottom"
				},
				open: function( event, ui ) {
					ui.tooltip.animate({ top: ui.tooltip.position().top + 10 }, "fast" );
				}
			});
			$("#auditing").tooltip({
				show: null,
				position: {
					my: "left top",
					at: "left bottom"
				},
				open: function( event, ui ) {
					ui.tooltip.animate({ top: ui.tooltip.position().top + 10 }, "fast" );
				}
			});
			$("#unAuditing").tooltip({
				show: null,
				position: {
					my: "left top",
					at: "left bottom"
				},
				open: function( event, ui ) {
					ui.tooltip.animate({ top: ui.tooltip.position().top + 10 }, "fast" );
				}
			});
			$("#finance").tooltip({
				show: null,
				position: {
					my: "left top",
					at: "left bottom"
				},
				open: function( event, ui ) {
					ui.tooltip.animate({ top: ui.tooltip.position().top + 10 }, "fast" );
				}
			});
			$("#balance").tooltip({
				show: null,
				position: {
					my: "left top",
					at: "left bottom"
				},
				open: function( event, ui ) {
					ui.tooltip.animate({ top: ui.tooltip.position().top + 10 }, "fast" );
				}
			});
			$("#datepickerStart").datepicker({
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
			$("#datepickerEnd").datepicker({
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
				} */
			});
			$("#arrTime").datepicker({
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
				} */
			});
			$("#departTime").datepicker({
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
				} */
			});
			$("#createTour").click(function(){
				var selects = $("#create").find("select");
				$.ajax({  
			        type: "GET",  
			        contentType:"application/json;charset=utf-8",  
			        url:"/localtour/localTourManage/getCreateInfo",
			        dataType: "json",  
			        async: false,  
			        success:function(data){
			        	selects.html('<option value="">&nbsp;</option>');
			        	$.each(data.businessTypes,function(){
			        		selects.eq(0).append('<option value="'+this.id+'">'+this.businessTypeName+'</option>');
			        	});
			        	$.each(data.tourTypes,function(){
			        		selects.eq(1).append('<option value="'+this.id+'">'+this.tourTypeName+'</option>');
			        	});
			        	$.each(data.regions,function(){
			        		selects.eq(2).append('<option value="'+this.id+'">'+this.regionName+'</option>');
			        	});
			        	$.each(data.visitorTypes,function(){
			        		selects.eq(3).append('<option value="'+this.id+'">'+this.visitorTypeName+'</option>');
			        	});
			        	$.each(data.customerAgencys,function(){
			        		selects.eq(4).append('<option value="'+this.id+'">'+this.customerAgencyName+'</option>');
			        	});
			        	$(".chosen-select").chosen();
						$(".chosen-select").next().attr("style","width:100%;");
						$(".chosen-select").next().find("input").attr("style","height:100%;");
			        }  
				});
			});
			$(".counts").blur(function(){
				var sum = 0;
				$(".counts").each(function(){
					if(isNaN(parseInt($(this).val()))){
					}else{
						sum = sum + parseInt($(this).val());
					}
					
				})
				
				$(".counts").last().parent().next().next().text(sum);
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
