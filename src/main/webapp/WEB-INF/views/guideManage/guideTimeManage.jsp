<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String path = request.getContextPath()+"/"; %>

<jsp:include page="../../../resources/include/header.jsp"></jsp:include>
<style type="text/css">
	.calendar a{
		display: inline-block;
		width: 3.22%;
		text-align: center;
		color: black;
		text-decoration: none;
		background-color: lightblue;
	}
</style>
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
								<a id="add" href="#">新增排团</a>
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
								<th aria-label="Price: activate to sort column ascending" style="width: 5%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
									姓名
								</th>
								<th aria-label="Price: activate to sort column ascending" style="width: 90%;" colspan="1" rowspan="1" aria-controls="sample-table-2" tabindex="0" role="columnheader" class="sorting">
									日历
								</th>
								<th aria-label="" style="width: 5%;" colspan="1" rowspan="1" role="columnheader" class="sorting_disabled">
									操作
								</th>
							</tr>
						</thead>

						<tbody id="table" aria-relevant="all" aria-live="polite" role="alert">
<!-- 列表循环 -->			
							
<!-- 列表循环结束 -->								
						</tbody>
					</table>
<!-- 分页查询开始 -->					
					
<!-- 分页查询结束 -->							
					</div>
				</div>
<!-- 正文结束 -->	

<jsp:include page="../../../resources/include/footer.jsp"></jsp:include>

<script type="text/javascript">
	$(function(){
		$("#add").click(function(){
			var myData = {id:99};
			$.ajax({  
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"/localtour/guideTimeManage/initialize",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	
		        }  
			}); 
		});
	/* 初始化 */
			$("#guideManage").addClass("open");
			$("#guideManage").children("ul").attr("style","display:block");
			$("#guideTimeManage").addClass("active");
			var d = new Date();
			var days = getDays(d.getFullYear(),d.getMonth() + 1);
			$.ajax({  
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"/localtour/guideTimeManage/initialize",
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	$.each(data,function(){
		        		
		        	});		        		
		        }  
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
	function getDays(Year,Month){
	    Month--;
	    var d = new Date(Year,Month,1);
	    d.setDate(d.getDate()+32-d.getDate());
	    return (32-d.getDate());
	}
	
</script>
