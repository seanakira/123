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
									借款金额
								</th>
								<th style="width: 3%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader">
									打款金额
								</th>
								<th style="width: 3%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader">
									出账金额
								</th>
								<th style="width: 3%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader">
									预估成本
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
							<tr id="addModel" hidden="">
								<td class="center  sorting_1">
									<label>
										<input class="ace" type="checkbox">
										<span class="lbl"></span>
									</label>
								</td>
								<td class="">
									
								</td>
								<td class="">
									<input type="text">
								</td>
								<td  class="">
									
								</td>	
								<td  class="">
									
								</td>
								<td  class="">
									<input id="submit" type="text">
								</td>
								<td class="hidden-480 ">
										<span class="label label-sm label-success">有效</span>
								</td>
								<td class="">
									
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
								<td><a id="" role="button" data-toggle="modal" href="#view">JRT1606016D-30</a></td>
								<td>青岛3日游</td>
								<td>4000</td>
								<td>10000</td>
								<td>14000</td>
								<td>18000</td>
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
<!-- 团队付款查看开始 -->	
				<div aria-hidden="true" style="display: none;" id="view" class="modal fade" tabindex="-1">
					<div class="modal-dialog" style="width:80%;">
						<div class="modal-content">
							
							<div class="modal-header no-padding">
								<div id="headerName" class="table-header">
									团队成本管理
								
								<a href="" style="color: #FFFFFF">	
									<span class="pull-right">
												<i class="icon-plus bigger-100"></i>
												新增成本
									</span>
								</a>
								</div>
								
							</div>
							
							<div class="modal-body no-padding">
								<table class="table table-striped table-bordered table-hover no-margin">
													<thead>
														<tr>
															<th style="width: 15%;">成本编号</th>
															<th style="width: 15%;">类别</th>
															<th style="width: 15%;">名称</th>
															<th style="width: 10%;">预算成本</th>
															<th style="width: 5%;">是否借款</th>
															<th style="width: 10%;">已付金额</th>
															<th style="width: 10%;">未付金额</th>
															<th style="width: 15%;">操作</th>
														</tr>
													</thead>
													<tbody>
														<tr>
															<td>JRT1606016D-30-C1</td>
															<td>餐费</td>
															<td>青岛小饭店</td>
															<td>6000</td>
															<td><input type="checkbox" checked="checked"></td>
															<td></td>
															<td></td>
															<td></td>
														</tr>
														
														<tr>
															<td>JRT1606016D-30-C2</td>
															<td>房费</td>
															<td>青岛大酒店</td>
															<td>12000</td>
															<td>
																<input type="checkbox">
																<span class="lbl"></span>
															</td>
															<td>10000</td>
															<td>2000</td>
															<td></td>
														</tr>
													</tbody>
									            </table>
								<div class="modal-header no-padding">
									<div id="headerName" class="table-header">
										<span style="text-align:left;display:inline">
											团队借款管理
										</span> 
										&nbsp;
										<span class="pull-mid">借款预算总额：6000	可借款额度：2000</span>
										<a href="" style="color: #FFFFFF">
											<span class="pull-right">
											<i class="icon-plus bigger-100"></i>
											新增借款
											</span>
										</a>
									</div>
								</div>
								<table class="table table-striped table-bordered table-hover no-margin">
									<thead>
										<tr>
											<th>
												借款编号
											</th>
											<th>
												借款金额
											</th>
											<th>
												借款日期
											</th>
											<th>
												借款人备注(不填为团控)
											</th>
										</tr>
									</thead>
									<tr>
										<td>JRT1606016D-30-J1</td>
										<td>4000</td>
										<td>06/01/2016</td>
										<td>李四</td>
									</tr>
								</table>
							</div>
							</div>
						</div>
					</div>
				</div>
<!-- 团队付款查看开始 -->	
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
			$("#payManage").addClass("active");
			$("#addModel").children("td").eq(3).html($("#select").html());
			$("#addModel").children("td").eq(4).html($("#multiple").html());
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
	/* 新增 */		
			$("#add").click(function(){
				var isChange = false;
				$("#table").prepend("<tr>"+$("#addModel").html()+"</tr>");
				$("#table").find("select").eq(0).attr("class","width-20 chosen-select");
				$("#table").find("select").eq(1).attr("class","width-20 chosen-select");
				$(".chosen-select").chosen();
				$("#table").find("select").eq(0).next().attr("style","width:150px;");
				$("#table").find("select").eq(1).next().attr("style","width:410px;");
				$("#table").find("select").next().find("input").attr("style","height:25px;");
				$("#table").find("input").not("#submit").keydown(function(event){
					if(event.keyCode==13&&isChange||event.keyCode==13&&$("#table").find("input").index($(this))==1){
						$(this).parents("td").next().find("input").focus().select();
						isChange = false;
					}
				});
				$("#table").find("select").change(function(){isChange = true;});
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
	
</script>
