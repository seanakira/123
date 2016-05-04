<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String path = request.getContextPath()+"/"; %>

<jsp:include page="../../../resources/include/header.jsp"></jsp:include>
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
								<a id="add" href="#">新增业务类型</a>
							</li>

							
							
						</ul><!-- .breadcrumb -->

						<div class="nav-search" id="nav-search">
							<form class="form-search" action="${path }businessTypeManage" method="get">
								<span class="input-icon">
									<input name="key" placeholder="搜索 ..." class="nav-search-input" id="nav-search-input" autocomplete="off" type="text">
									<i class="icon-search nav-search-icon"></i>
								</span>
							</form>
						</div><!-- #nav-search -->
					</div>
					
										<div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid"><table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
						<thead>
							<tr role="row">
								<th aria-label="" style="width: 98px;" colspan="1" rowspan="1" role="columnheader" class="center sorting_disabled">
									<label>
										<input class="ace" type="checkbox">
										<span class="lbl"></span>
									</label>
								</th>
								<th aria-label="Domain: activate to sort column ascending" style="width: 256px;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
									id
								</th>
								<th aria-label="Price: activate to sort column ascending" style="width: 187px;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
									业务类型
								</th>
								<th aria-label="Clicks: activate to sort column ascending" style="width: 204px;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="hidden-480 sorting">
									有效
								</th>
								
								<th aria-label="" style="width: 100px;" colspan="1" rowspan="1" role="columnheader" class="sorting_disabled">
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
							<c:forEach var="businessType" items="${businessTypes }" varStatus="status">
								<tr id="" <%-- <c:if test="${status.index%2!=0 }"> --%>class="style:{background-color:#f9f9f9;}"<%-- </c:if> --%>>
									<td class="center  sorting_1">
										<label>
											<input class="ace" type="checkbox">
											<span class="lbl"></span>
										</label>
									</td>
									<td class="">${businessType.id }</td>
									<td class="">${businessType.businessTypeName }</td>
									<td class="hidden-480 " id="">
									<c:choose>
										<c:when test="${businessType.enable }">
											<span class="label label-sm label-success">有效</span>
										</c:when>
										<c:otherwise>
											<span class="label label-sm label-warning">无效</span>
										</c:otherwise>
									</c:choose>
									</td>
	
									<td class=" ">
										<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
											<a id="reset" class="blue" href="#">
												<i class="icon-refresh bigger-120"></i>
											</a>
	
											<a id="edit" class="green" href="#">
												<i class="icon-pencil bigger-130"></i>
											</a>
											<span id="">
												<c:choose>
													<c:when test='${businessType.enable }'>
														<a id="${businessType.id }" class="red" href="#">
															<i class="icon-trash bigger-130"></i>
														</a>
													</c:when>
													<c:otherwise>
														<a id="${businessType.id }" class="green" href="#">
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
														<a data-original-title="View" href="#" class="tooltip-info" data-rel="tooltip" title="">
															<span class="blue">
																<i class="icon-refresh bigger-120"></i>
															</span>
														</a>
													</li>
	
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
											<a href="/localtour/userManage?page=${pageNo-1 }"><i class="icon-double-angle-left"></i></a>
										</li>
										<c:forEach var="page" begin="1" end="${pageMax }">
											<li <c:if test="${pageNo==page }">class="active"</c:if>>
												<a href="/localtour/userManage?page=${page }">${page }</a>
											</li>
										</c:forEach>
										<li <c:choose><c:when test="${pageNo==pageMax }">class="next disabled"</c:when><c:otherwise>class="next"</c:otherwise></c:choose>>
											<a href="/localtour/userManage?page=${pageNo+1 }"><i class="icon-double-angle-right"></i></a>
										</li>
									</ul>
								</div>
							</div>
						</div>
<!-- 分页查询结束 -->							
					</div>
				</div>
<!--部门树模板开始 -->
				<div id="treeView" hidden="">
				  <div id="" class="widget-body" style="position: absolute;width: 300px;margin-left: -9px;margin-top: 20px;display:none;" > 
				   <div class="widget-main padding-8"> 
				    <div id="tree0" class="tree tree-selectable"> 
				  
				    </div> 
				   </div> 
				  </div>
				</div>
<!--部门树模板结束 -->
<!-- 正文结束 -->	

<jsp:include page="../../../resources/include/footer.jsp"></jsp:include>

<script type="text/javascript">
	$(function(){
	/* 初始化 */
			$("#dataManage").addClass("open");
			$("#dataManage").children("ul").attr("style","display:block");
			$("#businessTypeManage").addClass("active");
	/* 新增 */		
			$("#add").click(function(){
				$("#table").prepend("<tr>"+$("#addModel").html()+"</tr>");
				$("#table").find("input").not("#submit").keydown(function(event){
					if(event.keyCode==13){
						if($(this).parent().next().children("input").attr("id")=="deptId"){
							$(this).parent().next().next().children("input").focus().select();
						}else{
							$(this).parent().next().children("input").focus().select();
						}
				}
			});
		});
	/* 回车保存 */		
		$("#table").delegate("#submit","keydown",function(event){
			if(event.keyCode==13){
				var obj = $(this).parents("tr");
				var input = $(this);
				var businessTypeName = $(this).val();
				var businessType = {businessTypeName:businessTypeName};
				var myData = JSON.stringify(businessType);
			 	$.ajax({  
			        type: "POST",  
			        contentType:"application/json;charset=utf-8",  
			        url:"/localtour/businessTypeManage/save",  
			        data:myData,  
			        dataType: "json",  
			        async: false,  
			        success:function(data){
			        	input.parent().html(businessTypeName);
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
		        url:"/localtour/userManage/del",  
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
		        url:"/localtour/userManage/recover",  
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
						userName:td.eq(1).children("a").text(),
						realName:td.eq(2).text(),
						deptId:td.eq(3).children("input").val(),
						deptName:td.eq(3).text(),
						position:td.eq(4).text(),
						phone:td.eq(5).text(),
						qq:td.eq(6).text()};
			var myData = JSON.stringify(info);
			td.eq(1).html("<input type='text' value='"+info.userName+"' style='width:150px' />");
			td.eq(2).html("<input type='text' value='"+info.realName+"' style='width:150px' />");
			td.eq(3).html("<a id='showTree' herf='#' style='cursor:pointer'>"+info.deptName+"</a>");
			td.eq(3).append("<input id='deptId' value='"+info.deptId+"' type='hidden' style='width:150px' />");
			td.eq(4).html("<input type='text' value='"+info.position+"' style='width:100px' />");
			td.eq(5).html("<input type='text' value='"+info.phone+"' style='width:100px' />");
			td.eq(6).html("<input id='update' type='text' value='"+info.qq+"' style='width:100px' />");
			obj.html("<i class='icon-save bigger-130'></i>").attr({"id":"save","class":"grey"});
		});
		/* 更新 */		
		$("#table").delegate("#update","keydown",function(event){
			if(event.keyCode==13){
				var obj = $(this).parents("tr");
				var params = $(this).parents("tr").find("input");
		 		var userName = params.eq(1).val();
				var realName = params.eq(2).val();
				var deptId = params.eq(3).val();
				var deptName =  params.eq(3).parent().text();
				var position = params.eq(4).val();
				var phone = params.eq(5).val();
				var qq = params.eq(6).val();
				var id = obj.find(".red").attr("id");
				var user = {id:id,userName:userName,realName:realName,deptId:deptId,position:position,phone:phone,qq:qq};
				var myData = JSON.stringify(user);
				$.ajax({  
			        type: "POST",  
			        contentType:"application/json;charset=utf-8",  
			        url:"/localtour/userManage/update",  
			        data:myData,  
			        dataType: "json",  
			        async: false,  
			        success:function(data){
			        	params.eq(1).parent().html("<a id='' role='button' data-toggle='modal' href='#modal-table'>"+userName+"</a>");
			        	params.eq(2).parent().html(realName);
			        	params.eq(3).parent().html(deptName).append("<input value='"+deptId+"' type='hidden'>");
			        	params.eq(4).parent().html(position);
			        	params.eq(5).parent().html(phone);
			        	params.eq(6).parent().html(qq);
			        }  
				 }); 
				obj.next().find("input").eq(1).focus().select();
			}
		});
		/* 按钮更新 */
		$("#table").delegate("#save","click",function(){
			var obj = $(this).parents("tr");
			var params = $(this).parents("tr").find("input");
	 		var userName = params.eq(1).val();
			var realName = params.eq(2).val();
			var deptId = params.eq(3).val();
			var deptName =  params.eq(3).parent().text();
			var position = params.eq(4).val();
			var phone = params.eq(5).val();
			var qq = params.eq(6).val();
			var id = obj.find(".red").attr("id");
			var user = {id:id,userName:userName,realName:realName,deptId:deptId,position:position,phone:phone,qq:qq};
			var myData = JSON.stringify(user);
			$.ajax({  
		        type: "POST",  
		        contentType:"application/json;charset=utf-8",  
		        url:"/localtour/userManage/update",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	params.eq(1).parent().html("<a id='' role='button' data-toggle='modal' href='#modal-table'>"+userName+"</a>");
		        	params.eq(2).parent().html(realName);
		        	params.eq(3).parent().html(deptName).append("<input value='"+deptId+"' type='hidden'>");
		        	params.eq(4).parent().html(position);
		        	params.eq(5).parent().html(phone);
		        	params.eq(6).parent().html(qq);
		        }  
			 }); 
			$(this).html("<i class='icon-pencil bigger-130'></i>").attr({"id":"edit","class":"green"});
		});
		/* 重置密码 */		
		$("#table").delegate("#reset","click",function(){
			var obj = $(this).parents("tr");
			var id = obj.find(".red").attr("id");
			var myData = {id:id};
			$.ajax({  
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"/localtour/userManage/reset",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
					alert("密码重置成功");
		        }  
			 }); 
			
		});
		/* 更改部门 */
		$("#table").delegate("#showTree","click",function(){
			var obj = $(this);
			var html = $("#treeView").html();
			$(obj).parent().append(html);
			$(obj).next().next().attr("id","deptTree");
			$("#tree0").empty();
	 			 $.ajax({  
				        type: "POST",  
				        contentType:"application/json;charset=utf-8",  
				        url:"/localtour/deptManage/getTree", 
				        dataType: "json",  
				        async: false,  
				        success:function(data){
				        	var maxLevel = 0;
				        	for (var int = 0; int < data.length; int++) {
				        		if(data[int].deptLevel>maxLevel){
				        			maxLevel=data[int].deptLevel;
				        		}
				        	}
				        	for (var level = 1; level <= maxLevel; level++) {            /* 等级循环 */
				        		for (var index = 0; index < data.length; index++) {       
					        		if(data[index].deptLevel==level){					 /*寻找当前等级的部门 */
										if(level==1){
											$("#tree0").append("<div class='tree-folder' style='display: block;'><div class='tree-folder-header'><i class='icon-minus' onclick='sildeDept(this)'></i><div class='tree-folder-name' onclick='setUpperDept(this,"+data[index].id+","+data[index].deptLevel+")'>"+data[index].deptName+"</div></div><div id='tree"+data[index].id+"' style='display: block;' class='tree-folder-content'></div><div class='tree-loader' style='display: none;'><div class='tree-loading'><i class='icon-refresh icon-spin blue'></i></div></div></div>");
											
										}else{
											$("#tree"+data[index].upperDeptId).append("<div class='tree-folder' style='display: block;'><div class='tree-folder-header'><i class='icon-minus' onclick='sildeDept(this)'></i><div class='tree-folder-name' onclick='setUpperDept(this,"+data[index].id+","+data[index].deptLevel+")'>"+data[index].deptName+"</div></div><div id='tree"+data[index].id+"' style='display: block;' class='tree-folder-content'></div><div class='tree-loader' style='display: none;'><div class='tree-loading'><i class='icon-refresh icon-spin blue'></i></div></div></div>");
											
										}
					        		}
					        	}
							}

				 			$("#deptTree").slideDown();
				        }  
				 }); 
		});
		
	
	
	});
	function setUpperDept(obj,deptId,level){
		$(obj).parents("td").children("input").val(deptId);
		$(obj).parents("td").children("a").text($(obj).html());
		$("#deptTree").remove();
	}
	
	function sildeDept(obj){
		if($(obj).attr("class")=="icon-minus"){
			$(obj).attr("class","icon-plus");
		}else{
			$(obj).attr("class","icon-minus");
		}
		$(obj).parent().next().slideToggle();
	}
	
</script>
