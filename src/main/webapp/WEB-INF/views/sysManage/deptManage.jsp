<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String path = request.getContextPath()+"/"; %>

<jsp:include page="../../../resources/include/header.jsp"></jsp:include>
<jsp:include page="../../../resources/include/sider.jsp"></jsp:include>
<jsp:include page="../../../resources/include/pageSettings.jsp"></jsp:include>

	
				<div class="main-content">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

						<ul class="breadcrumb">
							<li id="menu">
								<i class="icon-user"></i>
								<a id="addDept" href="#">新增部门</a>
							</li>

							
							
						</ul><!-- .breadcrumb -->

						<div class="nav-search" id="nav-search">
							<form class="form-search" action="/localtour/deptManage/search">
								<span class="input-icon">
									<input id="search" name="key" placeholder="搜索 ..." class="nav-search-input" id="nav-search-input" autocomplete="off" type="text">
									<i class="icon-search nav-search-icon"></i>
									
								</span>
							</form>
						</div><!-- #nav-search -->
					</div>

					<!-- /.page-content -->
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
									部门名
								</th>
								<th aria-label="Price: activate to sort column ascending" style="width: 187px;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
									上级部门
								</th>
								<th aria-label="Clicks: activate to sort column ascending" style="width: 204px;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="hidden-480 sorting">
									部门等级
								</th>
								
								<th aria-label="Status: activate to sort column ascending" style="width: 246px;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="hidden-480 sorting">
									状态
								</th>
								<th aria-label="" style="width: 263px;" colspan="1" rowspan="1" role="columnheader" class="sorting_disabled">
									操作
								</th>
							</tr>
						</thead>

							<tbody id="deptTable" aria-relevant="all" aria-live="polite" role="alert">
<!-- 		增加模板			 -->		
								<tr id="addDeptTr" hidden="">
									<td class="center  sorting_1">
										<label>
											<input class="ace" type="checkbox">
											<span class="lbl"></span>
										</label>
									</td>
	
									<td  class=" ">
										<input name="deptName" type="text" />
									</td>
									<td class=" ">
										上级部门
										<input type="hidden" name="upperDeptID" value="1" />
									</td>
									<td class="hidden-480 ">
										等级
										<input type="hidden" name="deptLevel" value="2" />
									</td>
	
									<td class="hidden-480 " id="">
											<span class="label label-sm label-success">有效</span>
									</td>
	
									<td class=" ">
										
									</td>
								</tr>
								
							<c:forEach var="deptViewMode" items="${deptViewModes }" varStatus="status">
								<tr id="deptTr${status.index }" <%-- <c:if test="${status.index%2!=0 }"> --%>class="style:{background-color:#f9f9f9;}"<%-- </c:if> --%>>
									<td class="center  sorting_1">
										<label>
											<input class="ace" type="checkbox">
											<span class="lbl"></span>
										</label>
									</td>
	
									<td id="dept${deptViewMode.deptTable.id }" class=" ">
										<a id="name${deptViewMode.deptTable.id }" role="button" data-toggle="modal" href="#modal-table" onclick="getUser('${deptViewMode.deptTable.id }','${deptViewMode.deptTable.deptName }')">${deptViewMode.deptTable.deptName }</a>
									</td>
									<td class="">
										${deptViewMode.upperDeptName }
									</td>
									<td class="hidden-480 ">
										${deptViewMode.deptTable.deptLevel }
										<input id="upperDeptId${deptViewMode.deptTable.id }" type="hidden" value="${deptViewMode.deptTable.upperDeptId }" />
										<input id="deptLevel${deptViewMode.deptTable.id }" type="hidden" value="${deptViewMode.deptTable.deptLevel }" />
									</td>
	
									<td class="hidden-480 " id="enable${deptViewMode.deptTable.id }">
									<c:choose>
										<c:when test="${deptViewMode.deptTable.enable }">
											<span class="label label-sm label-success">有效</span>
										</c:when>
										<c:otherwise>
											<span class="label label-sm label-warning">无效</span>
										</c:otherwise>
									</c:choose>
									</td>
	
									<td class=" ">
										<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
											<a id="apply" class="blue" href="#" onclick="apply('${deptViewMode.deptTable.id }')">
												<i class="icon-zoom-in bigger-130"></i>
											</a>
	
											<a id="edit" class="green" href="#" onclick="edit('${deptViewMode.deptTable.id }')">
												<i class="icon-pencil bigger-130"></i>
											</a>
											<span id="del${deptViewMode.deptTable.id }">
												<c:choose>
													<c:when test='${deptViewMode.deptTable.enable }'>
														<a class="red" href="#" onclick="del('${deptViewMode.deptTable.id }',false)">
															<i class="icon-trash bigger-130"></i>
														</a>
													</c:when>
													<c:otherwise>
														<a class="green" href="#" onclick="del('${deptViewMode.deptTable.id }',true)">
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
																<i class="icon-zoom-in bigger-120"></i>
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
							</tbody>
							</table>
								<div class="row"><div class="col-sm-6">
									<div id="sample-table-2_info" class="dataTables_info">共  ${counts } 个结果 </div>
								</div><div class="col-sm-6">
								<div class="dataTables_paginate paging_bootstrap">
									<ul class="pagination">
										<li <c:choose><c:when test="${pageNo==1 }">class="prev disabled"</c:when><c:otherwise>class="prev"</c:otherwise></c:choose>>
											<a href="/localtour/deptManage?page=${pageNo-1 }"><i class="icon-double-angle-left"></i></a>
											</li>
										<c:forEach var="page" begin="1" end="${pageMax }">
											<li <c:if test="${pageNo==page }">class="active"</c:if>>
												<a href="/localtour/deptManage?page=${page }">${page }</a>
											</li>
										</c:forEach>
										<li <c:choose><c:when test="${pageNo==pageMax }">class="next disabled"</c:when><c:otherwise>class="next"</c:otherwise></c:choose>>
											<a href="/localtour/deptManage?page=${pageNo+1 }"><i class="icon-double-angle-right"></i></a>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
<!-- 	用户查看	 -->		
				<div aria-hidden="true" style="display: none;" id="modal-table" class="modal fade" tabindex="-1">
					<div class="modal-dialog" style="width: 1000px">
						<div class="modal-content">
							<div class="modal-header no-padding">
								<div id="headerName" class="table-header">
									
									
								</div>
							</div>

							<div class="modal-body no-padding">
								<table class="table table-striped table-bordered table-hover no-margin-bottom no-border-top">
									<thead>
										<tr>
											<th>用户名</th>
											<th>姓名</th>
											<th>职位</th>
											<th>电话</th>
											<th>qq</th>
											<th>状态</th>
										</tr>
									</thead>

									<tbody id="userTable">
																
									</tbody>
								</table>
							</div>

							<div class="modal-footer no-margin-top">
								<button class="btn btn-sm btn-danger pull-left" data-dismiss="modal">
									<i class="icon-remove"></i>
									Close
								</button>

								<ul class="pagination pull-right no-margin">
									<li class="prev disabled">
										<a href="#">
											<i class="icon-double-angle-left"></i>
										</a>
									</li>

									<li class="active">
										<a href="#">1</a>
									</li>

									<li>
										<a href="#">2</a>
									</li>

									<li>
										<a href="#">3</a>
									</li>

									<li class="next">
										<a href="#">
											<i class="icon-double-angle-right"></i>
										</a>
									</li>
								</ul>
							</div>
						</div><!-- /.modal-content -->
					</div><!-- /.modal-dialog -->
				</div>
<!--       部门树    -->
				<div id="treeView" hidden="">
				  <div id="" class="widget-body" style="position: absolute;width: 300px;margin-left: -9px;margin-top: 20px;display:none;" > 
				   <div class="widget-main padding-8"> 
				    <div id="tree0" class="tree tree-selectable"> 
				  
				    </div> 
				   </div> 
				  </div>
				</div>



<jsp:include page="../../../resources/include/footer.jsp"></jsp:include>
<script type="text/javascript">

/* 新增 */
	var addID = 1;
	$(function(){
		$("#addDept").click(function(){
			$("#deptTable").prepend("<tr id='add"+addID+"'>"+$("#addDeptTr").html()+"</tr>");
			if(addID==1){
				$("#menu").append("<a class='blue' href='#' onclick='save()'><i class='icon-save bigger-130'></i>  保存</a>");
			}
			addID++;
		});
		$(".nav-list").children("li").attr('class','');
	    $("#systemManage").addClass("open");
	    $("#systemManage").children("ul").attr("style","display:block");
	    $("#deptManage").addClass("active");
	});
	
	function save(){
		var depts = [];
		for (var int = 1; int < addID; int++) {
			deptName = $("#add"+int).find("input[name='deptName']").val();
			upperDeptId = $("#add"+int).find("input[name='upperDeptID']").val();
			deptLevel= $("#add"+int).find("input[name='deptLevel']").val();
			var dept = {deptName:deptName,upperDeptId:upperDeptId,deptLevel:deptLevel,enable:true};
			depts[int-1] = dept;
		}
		var myData = JSON.stringify(depts);
			 $.ajax({  
			        type: "POST",  
			        contentType:"application/json;charset=utf-8",  
			        url:"/localtour/deptManage/save",  
			        data:myData,  
			        dataType: "json",  
			        async: false,  
			        success:function(data){  
			        	location.reload();
			        }  
			 });  
	}
	
	function apply(id){
		alert(id);
	}
/* 	删除 */
	function del(id,enable){
	var dept={id:id,enable:enable};
	var myData = JSON.stringify(dept);
		 $.ajax({  
		        type: "POST",  
		        contentType:"application/json;charset=utf-8",  
		        url:"/localtour/deptManage/del",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){  
		        	if(enable){
		        		$("#enable"+id).html("<span class='label label-sm label-success'>有效</span>");
		        		$("#del"+id).html("<a class='red' href='#' onclick='del("+id+",false)'><i class='icon-trash bigger-130'></i></a>");
		        	}else{
		        		$("#enable"+id).html("<span class='label label-sm label-warning'>无效</span>");
		        		$("#del"+id).html("<a class='green' href='#' onclick='del("+id+",true)'><i class='icon-undo bigger-130'></i></a>");
		        	}
		        }  
		 });  
	}
	/* 编辑 */
	function edit(id){
		if($("#name"+id).html()!=""){
			var deptName = $("#name"+id).html();
		}
		$("#dept"+id).html("<input id='name"+id+"' type='text' value='"+deptName+"' onblur='update("+id+")' onkeydown='if(event.keyCode==13){update("+id+")}'  />");
		var html = $("#dept"+id).next().html();
		$("#dept"+id).next().html("<a herf='#' style='cursor:pointer;' onclick='showTree(this)' >"+html+"</a>");
	}
	
	function update(id){
		var deptName = $("#name"+id).val();
		var upperDeptId = $("#upperDeptId"+id).val();
		var deptLevel = $("#deptLevel"+id).val();
		var dept={id:id,deptName:deptName,upperDeptId:upperDeptId,deptLevel:deptLevel};
		var myData = JSON.stringify(dept);
			 $.ajax({  
			        type: "POST",  
			        contentType:"application/json;charset=utf-8",  
			        url:"/localtour/deptManage/update",  
			        data:myData,  
			        dataType: "json",  
			        async: false,  
			        success:function(data){
			        	$("#dept"+id).html("<a id='name"+id+"' role='button' data-toggle='modal' href='#modal-table' onclick='getUser('"+id+"','"+deptName+"')'>"+deptName+"</a>");
			        }  
			 });  
	}
	
	function getUser(id,deptName){
		$("#headerName").html("<button type='button' class='close' data-dismiss='modal' aria-hidden='true'><span class='white'>×</span></button>"+deptName);
		var deptID={id:id};
		$.ajax({  
	        type: "GET",  
	        contentType:"application/json;charset=utf-8",  
	        url:"/localtour/deptManage/getUserByDept",  
	        data:deptID,  
	        dataType: "json",  
	        async: false,  
	        success:function(data){
	        	$("#userTable").empty();
	        	$(data).each(function(i,user){
	        		$("#userTable").append("<tr><th>"+user.userName+"</th><th>"+user.realName+"</th><th>"+user.position+"</th><th>"+user.phone+"</th><th>"+user.qq+"</th><th>"+user.enable+"</th></tr>");
	        	});
	        }  
		});  
	}
	
	function showTree(obj){
		var html = $("#treeView").html();
		$(obj).parent().append(html);
		$(obj).next().attr("id","deptTree");
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
 		var text = $(obj).text();
 	 	$(obj).parent().parent().siblings().click(function(){
 	 		$("#deptTree").remove();
 	 		$(obj).parent().parent().siblings().unbind();
 	 		$(obj).parent().html(text);
 		});
 		$(obj).parent().siblings().click(function(){
 	 		$("#deptTree").remove();
 	 		$(obj).parent().siblings().unbind();
 	 		$(obj).parent().parent().siblings().unbind();
 	 		$(obj).parent().html(text);
 		});
 		
	}
	
	function setUpperDept(obj,upid,level){
		var id = $(obj).parents("td").prev().attr("id").substr(4);
		$("#upperDeptId"+id).val(upid);
		$("#deptLevel"+id).val(level+1);
		update(id);
		var parent = $(obj).parents("td");
		$("#deptTree").remove();
		var text = $(obj).text();
		parent.html(text);
		parent.siblings().unbind();
		parent.parent().siblings().unbind();
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