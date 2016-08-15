<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String path = request.getContextPath()+"/"; %>

<jsp:include page="../../../resources/include/header.jsp"></jsp:include>
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
								<a id="" role="button" data-toggle="modal" href="#create">新增团队</a>
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
								<th style="width: 8%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader">
									团号
								</th>
								<th style="width: 8%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader">
									团名
								</th>
								<th style="width: 3%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader">
									收款金额
								</th>
								<th style="width: 3%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader">
									预估收入
								</th>
								<th style="width: 3%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader">
									提交日期
								</th>
								<th style="width: 3%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader">
									出团日期
								</th>
								<th style="width: 3%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader">
									团控人
								</th>
								<th style="width: 5%;" colspan="1" rowspan="1" role="columnheader" class="sorting_disabled">
									操作
								</th>
							</tr>
						</thead>

							<tbody id="table" aria-relevant="all" aria-live="polite" role="alert">
<!-- 		增加模板			 -->
							<tr id="addCollectModel" hidden="">
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td  class="">
									<input type="text">
								</td>
							</tr>
<!-- 增加模板结束 -->		
<!-- 列表循环 -->								
							<%-- <c:forEach var="supplier" items="${suppliers }" varStatus="status">
								<tr>
									<td class="center  sorting_1">
										<label>
											<input class="ace" type="checkbox">
											<span class="lbl"></span>
										</label>
									</td>
									<td class="">${supplier.supplierTable.id }</td>
									<td class="">${supplier.supplierTable.supplierName }</td>
									<td class="">
										${supplier.regionName }
										<span hidden="">${supplier.supplierTable.regionId }</span>
									</td>
									<td>
										<c:forEach var="supplierScopeName" items="${supplier.supplierScopeNames }">
											${supplierScopeName }
										</c:forEach>
										<span hidden="">
											<c:forEach var="supplierScopeId" items="${supplier.supplierScopeIds }" varStatus="status">
												<c:choose>
													<c:when test="${status.count==1 }">${supplierScopeId}</c:when><c:otherwise>,${supplierScopeId }</c:otherwise>
												</c:choose>
											</c:forEach>
										</span>
									</td>
									<td class="">${supplier.supplierTable.phone }</td>
									<td class="hidden-480 " id="">
									<c:choose>
										<c:when test="${supplier.supplierTable.enable }">
											<span class="label label-sm label-success">有效</span>
										</c:when>
										<c:otherwise>
											<span class="label label-sm label-warning">无效</span>
										</c:otherwise>
									</c:choose>
									</td>
	
									<td class=" ">
										<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
											
	
											<a id="edit" class="green" href="#">
												<i class="icon-pencil bigger-130"></i>
											</a>
											<span id="">
												<c:choose>
													<c:when test='${supplier.supplierTable.enable }'>
														<a id="${supplier.supplierTable.id }" class="red" href="#">
															<i class="icon-trash bigger-130"></i>
														</a>
													</c:when>
													<c:otherwise>
														<a id="${supplier.supplierTable.id }" class="green" href="#">
															<i class="icon-undo bigger-130"></i>
														</a>
													</c:otherwise>
												</c:choose>
											</span>
										</div>
	
										<div class="visible-xs visible-sm hidden-md hidden-lg">
											<div class="inline position-relative">
												<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown">
													<i class="icon-caret-down icon-only bigger-120"></i>
												</button>
	
												<ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">
													
	
													<li>
														<a data-original-title="Edit" href="#" class="tooltip-success" data-rel="tooltip" title="">
															<span class="green">
																<i class="icon-edit bigger-120"></i>
															</span>
														</a>
													</li>
	
													<li>
														<a data-original-title="Delete" href="#" class="tooltip-error" data-rel="tooltip" title="">
															<span class="red">
																<i class="icon-trash bigger-120"></i>
															</span>
														</a>
													</li>
												</ul>
											</div>
										</div>
									</td>
								</tr>
							</c:forEach> --%>
							<tr>
								<td><a id="" role="button" data-toggle="modal" href="#view" data-backdrop="static">JRT1606016D-30</a></td>
								<td>青岛3日游</td>
								<td>10600</td>
								<td>14000</td>
								<td>05/28/2016</td>
								<td>06/28/2016</td>
								<td>张三</td>
								<td>
									<a id="edit" class="green" href="#">
												<i class="icon-thumbs-up bigger-130"></i>
									</a>
									<span>&nbsp;&nbsp;</span>
									<a  class="red" href="#">
											<i class="icon-thumbs-down bigger-130"></i>
									</a>
								</td>
								
							</tr>
<!-- 列表循环结束 -->								
						</tbody>
					</table>
<!-- 团队收款查看开始 -->	
				<div aria-hidden="true" style="display: none;" id="view" class="modal fade" tabindex="-1">
					<div class="modal-dialog" style="width:80%;">
						<div class="modal-content">
							
							<div class="modal-header no-padding">
								<div id="headerName" class="table-header">
									团队收入管理
								
									<span id="addCollect" class="pull-right">
										<a href="#" style="color: #FFFFFF">	
												<i class="icon-plus bigger-100"></i>
												新增收入
										</a>
									</span>
								</div>
								
							</div>
							
							<div class="modal-body no-padding">
								<table id="tableCollect" class="table table-striped table-bordered table-hover no-margin">
													<thead>
														<tr>
															<th style="width: 15%;">收入编号</th>
															<th style="width: 10%;">款项类型</th>
															<th style="width: 15%;">客户名称</th>
															<th style="width: 10%;">预算收入</th>
															<th style="width: 10%;">已收金额</th>
															<th style="width: 15%;">备注</th>
															<th style="width: 10%;">操作</th>
														</tr>
													</thead>
													<tbody>
														<tr>
															<td>JRT1606016D-30-S1</td>
															<td>收款从客户</td>
															<td>北京xxx旅行社</td>
															<td>6000</td>
															<td>3000</td>
															<td></td>
															<td></td>
														</tr>
														
														<tr>
															<td>JRT1606016D-30-S2</td>
															<td>收款从客户</td>
															<td>上海xxx旅行社</td>
															<td>8000</td>
															<td>8000</td>
															<td></td>
															<td></td>
														</tr>
														
														<tr>
															<td>JRT1606016D-30-S3</td>
															<td>退款回客户</td>
															<td>上海xxx旅行社</td>
															<td></td>
															<td>-400</td>
															<td>一位游客退团</td>
															<td></td>
														</tr>
													</tbody>
									            </table>
							</div>
							</div>
						</div>
					</div>
<!-- 团队收款查看结束 -->	
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
<!-- 新增实收模板 -->
				<div aria-hidden="true" style="display: none;" id="addActualCollect" class="modal fade" tabindex="-1">
					<div class="modal-dialog" style="width:40%;">
						<div class="modal-content">
							<div class="modal-header no-padding">
								<div id="headerName" class="table-header">
									分批实付
								</div>
							</div>

							<div class="modal-body no-padding">
								<table class="table table-striped table-bordered table-hover no-margin-bottom no-border-top">
									<thead>
										<tr>
											<td>分批实收金额</td>
											<td>现付</td>
											<td>汇款</td>
											<td>挂账</td>
											<td>日期</td>
										</tr>
									</thead>

									<tbody id="">
										<tr>
											<td>3500</td>
											<td class="center  sorting_1">
												<label>
													<input class="ace" type="checkbox">
													<span class="lbl"></span>
												</label>
											</td>
											<td class="center  sorting_1">
												<label>
													<input class="ace" type="checkbox">
													<span class="lbl"></span>
												</label>
											</td>
											<td class="center  sorting_1">
												<label>
													<input class="ace" type="checkbox">
													<span class="lbl"></span>
												</label>
											</td>
											<td>05/29/2016</td>
										</tr>
										
										<tr>
											<td>1600</td>
											<td class="center  sorting_1">
												<label>
													<input class="ace" type="checkbox">
													<span class="lbl"></span>
												</label>
											</td>
											<td class="center  sorting_1">
												<label>
													<input class="ace" type="checkbox">
													<span class="lbl"></span>
												</label>
											</td>
											<td class="center  sorting_1">
												<label>
													<input class="ace" type="checkbox">
													<span class="lbl"></span>
												</label>
											</td>
											<td>06/02/2016</td>
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

<!-- 下拉搜索开始 -->
<link rel="stylesheet" href="${path }resources/assets/css/chosen.css" />
				<div id="select">
					<select hidden="" style="display: none;" class="" data-placeholder="Choose a Country...">
						<option value="">&nbsp;</option>
						<c:forEach var="region" items="${regions }" varStatus="status">
						<option value="${region.id }">${region.regionName }</option>
						</c:forEach>												
					</select>
				</div>
<!-- 下拉搜索结束 -->					
<!-- 多选模板开始 -->
				<div id="multiple">
					<select hidden="" style="display: none;" multiple="multiple" class="" data-placeholder="可选择多个范围...">
						<c:forEach var="supplierScope" items="${ supplierScopes}">
							<option value="${supplierScope.id }">${supplierScope.supplierScopeName }&nbsp;</option>
						</c:forEach>
					</select>
				</div>
<!-- 多选模板结束 -->										
<jsp:include page="../../../resources/include/footer.jsp"></jsp:include>

<!-- 下拉搜索依赖 -->
<script src="${path }resources/assets/js/chosen.jquery.min.js"></script>
<!-- 日历组件依赖 -->
<script src="${path }resources/assets/js/jquery-ui-1.10.3.full.min.js"></script>

<script type="text/javascript">
	$(function(){
		
	/* 初始化 */
			$("#statementManage").addClass("open");
			$("#statementManage").children("ul").attr("style","display:block");
			$("#collectManage").addClass("active");
			$("#addModel").children("td").eq(3).html($("#select").html());
			$("#addModel").children("td").eq(4).html($("#multiple").html());
			$("#datepickerStart").datepicker({
				showOtherMonths: true,
				selectOtherMonths: false,
			});
			/* 新增收入 */		
			$("#addCollect").click(function(){
				$("#tableCollect").append("<tr class='collectModel'>"+$("#addCollectModel").html()+"</tr>");
			});
	});
	
</script>
