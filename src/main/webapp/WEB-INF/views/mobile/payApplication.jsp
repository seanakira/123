<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% String path = request.getContextPath()+"/"; %>

<jsp:include page="../../../resources/include/header.jsp"></jsp:include>
<style type="text/css">
	@media only screen and (max-width:460px) {
		.menu-toggler{
			display: none;
		}
		.ace-nav{
			position: absolute;
			right: 0px;
			top: -1px;
		}
		.ace-settings-container{
			display: none;
		}
		.breadcrumb{
			position: absolute;
			left: -80px;
			top: 10px;
		}
		.nav-search{
			display: none;
		}
		.row{
			display: none;
		}
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
								<a id="add" href="<%=path %>localTourManage?key=${tour.tourNo }">${tour.tourNo }  ${tour.tourName }</a>
							</li>

							
							
						</ul><!-- .breadcrumb -->

						<div class="nav-search" id="nav-search">
							<form class="form-search" action="${path }userManage" method="get">
								<span class="input-icon">
									<input name="key" placeholder="搜索 ..." class="nav-search-input" id="nav-search-input" autocomplete="off" type="text" value="${key }" />
									<i class="icon-search nav-search-icon"></i>
								</span>
							</form>
						</div><!-- #nav-search -->
					</div>
					
					<div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid">
					<c:if test="${full.costs.size()==0&&full.changeCosts.size()==0 }">
							<div style="margin-left: 10px;"><span class="red">没有查询到借款记录</span></div>
					</c:if>
<!-- 列表循环 -->		<c:forEach var="cost" items="${full.costs }">
						<div>
							<table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
								<thead>
									<tr role="row">
										
									</tr>
								</thead>
	
								<tbody id="table" aria-relevant="all" aria-live="polite" role="alert">
									<tr>
										<td style="width: 20%">日期</td>
										<td style="width: 30%">${cost.costTable.costDate }</td>
										<td style="width: 20%">内容</td>
										<td style="width: 30%">${cost.contentName }</td>
									</tr>
									<tr>
										<td style="width: 20%">供应商</td>
										<td style="width: 30%">${cost.supplierName }</td>
										<td style="width: 20%">成本单价</td>
										<td style="width: 30%">${cost.costTable.cost }</td>
									</tr>
									<tr>
										<td style="width: 20%">数量</td>
										<td style="width: 30%">${cost.costTable.count }</td>
										<td style="width: 20%">天数</td>
										<td style="width: 30%">${cost.costTable.days }</td>
									</tr>
									<tr>
										<td style="width: 20%">合计</td>
										<td style="width: 30%" class="red"><fmt:formatNumber value="${cost.costTable.cost*cost.costTable.count*cost.costTable.days }" pattern="#0.00"></fmt:formatNumber></td>
										<td style="width: 20%">申请人</td>
										<td style="width: 30%">${cost.payApplicationerRealName }</td>
									</tr>
									<tr>
										<td style="width: 20%">备注</td>
										<td style="width: 30%">${cost.costTable.remark }</td>
										<td style="width: 20%">状态</td>
										<td style="width: 30%">${cost.payStatus }</td>
									</tr>
							</tbody>
						</table>
						<div id="${cost.costTable.id }" class="action" style="margin: 5px;"><button class="btn btn-sm btn-danger cancalCost">驳回</button><button class="btn btn-sm btn-success pull-right changeCost">同意</button></div>
						<HR style="margin-top: 0px;">
					</div>
					</c:forEach>
<!-- 列表循环结束 -->	
<!-- 列表循环 -->		<c:forEach var="changeCosts" items="${full.changeCosts }">
						<div>
							<table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
								<thead>
									<tr role="row">
										
									</tr>
								</thead>
	
								<tbody id="table" aria-relevant="all" aria-live="polite" role="alert" class="blue">
									<tr>
										<td style="width: 20%">日期</td>
										<td style="width: 30%">${changeCosts.costTable.costDate }</td>
										<td style="width: 20%">内容</td>
										<td style="width: 30%">${changeCosts.contentName }</td>
									</tr>
									<tr>
										<td style="width: 20%">供应商</td>
										<td style="width: 30%">${changeCosts.supplierName }</td>
										<td style="width: 20%">成本单价</td>
										<td style="width: 30%">${changeCosts.costTable.cost }</td>
									</tr>
									<tr>
										<td style="width: 20%">数量</td>
										<td style="width: 30%">${changeCosts.costTable.count }</td>
										<td style="width: 20%">天数</td>
										<td style="width: 30%">${changeCosts.costTable.days }</td>
									</tr>
									<tr>
										<td style="width: 20%">合计</td>
										<td style="width: 30%" class="red"><fmt:formatNumber value="${changeCosts.costTable.cost*changeCosts.costTable.count*changeCosts.costTable.days }" pattern="#0.00"></fmt:formatNumber></td>
										<td style="width: 20%">申请人</td>
										<td style="width: 30%">${changeCosts.payApplicationerRealName }</td>
									</tr>
									<tr>
										<td style="width: 20%">备注</td>
										<td style="width: 30%">${changeCosts.costTable.remark }</td>
										<td style="width: 20%">状态</td>
										<td style="width: 30%">${changeCosts.payStatus }</td>
									</tr>
							</tbody>
						</table>
						<div id="${changeCosts.costTable.id }" class="action" style="margin: 5px;"><button class="btn btn-sm btn-danger cancalCost">驳回</button><button class="btn btn-sm btn-success pull-right changeCost">同意</button></div>
						<HR style="margin-top: 0px;">
					</div>
					</c:forEach>
<!-- 列表循环结束 -->	
					<c:if test="${full.changeCosts.size()>0 }">
							<span id="changeCostBlue" class="blue" style="">*蓝色为成本收入变更</span>
					</c:if>
					<div style="text-align: center;">
						<a id="lended" href="<%=path %>mobile/payApplication?tourId=${tour.id }&status=-1">点击查看本团付款申请记录</a>
					</div>
<!-- 分页查询开始 -->					
						<div class="row">
							<div class="col-sm-6">
								<div id="sample-table-2_info" class="dataTables_info">共  ${counts } 个结果 </div>
							</div>
							<div class="col-sm-6">
								<div class="dataTables_paginate paging_bootstrap">
									<ul class="pagination">
										<li <c:choose><c:when test="${pageNo==1 }">class="prev disabled"</c:when><c:otherwise>class="prev"</c:otherwise></c:choose>>
											<a href="/localtour/userManage?page=${pageNo-1 }&key=${key }"><i class="icon-double-angle-left"></i></a>
										</li>
										<c:forEach var="page" begin="1" end="${pageMax }">
											<li <c:if test="${pageNo==page }">class="active"</c:if>>
												<a href="/localtour/userManage?page=${page }&key=${key }">${page }</a>
											</li>
										</c:forEach>
										<li <c:choose><c:when test="${pageNo==pageMax }">class="next disabled"</c:when><c:otherwise>class="next"</c:otherwise></c:choose>>
											<a href="/localtour/userManage?page=${pageNo+1 }&key=${key }"><i class="icon-double-angle-right"></i></a>
										</li>
									</ul>
								</div>
							</div>
						</div>
<!-- 分页查询结束 -->							
					</div>
				</div>

<!-- 正文结束 -->	

<jsp:include page="../../../resources/include/footer.jsp"></jsp:include>

<script type="text/javascript">
	$(function(){
		/* 获取url参数 */
        if(getUrlParam("status")==-1){//参数值
        	$("#lended").remove();
        	$(".action").remove();
        }
        
		$(".btn-success").click(function(){
			var id = $(this).parent().attr("id");
			var change = false;
			if($(this).parent().prev().children("tbody").attr("class")=="blue"){
				change = true;
			}
			$(this).parent().parent().remove();
			var myData = {id:id,change:change};
			 $.ajax({
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"/localtour/mobile/payApplicationOk",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        }
			 });
		});
		
		$(".btn-danger").click(function(){
			var id = $(this).parent().attr("id");
			$(this).parent().parent().remove();
			var myData = {id:id};
			 $.ajax({
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"/localtour/mobile/payApplicationCancel",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        }
			 });
		});
	});
	function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) return unescape(r[2]); return null; //返回参数值
    }
</script>