<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="com.cts.localtour.entity.UserTable"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<% 
	String path = request.getContextPath()+"/"; 
	UserTable user = (UserTable)SecurityUtils.getSubject().getPrincipal();
%>

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
								<a href="<%=path %>localTourManage?key=${tour.tourNo }">${tour.tourNo }  ${tour.tourName }</a>
							</li>
						</ul><!-- .breadcrumb -->
						<!-- <button class="btn btn-sm btn-primary pull-right changeCost" style="margin-top: 3px;margin-right: -7px;">全部同意</button> -->
						
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
					<c:if test="${refunds.size()==0 }">
							<div style="margin-left: 10px;"><span class="red">没有查询到退款记录</span></div>
					</c:if>
<!-- 列表循环 -->		<c:forEach var="refund" items="${refunds }">
						<c:choose>
							<c:when test="${sessionScope.isMice }">
								<c:choose>
									<c:when test="<%=user.getPosition().indexOf(\"中心总经理\")>-1 %>">
										<c:if test="${refund.refundTable.refundAmount>10000||refund.applicationerName==\"林志刚\" }">
											<div>
												<table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
													<thead>
														<tr role="row">
															
														</tr>
													</thead>
						
													<tbody id="table" aria-relevant="all" aria-live="polite" role="alert">
														<tr>
															<td style="width: 20%">日期</td>
															<td style="width: 30%">${refund.refundTable.refundDate }</td>
															<td style="width: 20%">客户</td>
															<td style="width: 30%">${refund.customerAgencyName }</td>
														</tr>
														<tr>
															<td style="width: 20%">内容</td>
															<td style="width: 30%">${refund.refundTable.refundContent }</td>
															<td style="width: 20%">方式</td>
															<td style="width: 30%">${refund.refundTable.refundWays }</td>
														</tr>
														<tr>
															<td style="width: 20%" class="red">金额</td>
															<td style="width: 30%">${refund.refundTable.refundAmount }</td>
															<td style="width: 20%">备注</td>
															<td style="width: 30%">${refund.refundTable.remark }</td>
														</tr>
														<tr>
															<td style="width: 20%">申请人</td>
															<td style="width: 30%">${refund.applicationerName }</td>
															<td style="width: 20%">状态</td>
															<td style="width: 30%">${refund.remark }</td>
														</tr>
													</tbody>
												</table>
												<div id="${refund.refundTable.id }" class="action" style="margin: 5px;"><button class="btn btn-sm btn-danger">驳回</button><button class="btn btn-sm btn-success pull-right">同意</button></div>
												<HR style="margin-top: 0px;">
											</div>
										</c:if>
									</c:when>
									<c:otherwise>
										<c:if test="${refund.refundTable.refundAmount<=10000 }">
											<div>
												<table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
													<thead>
														<tr role="row">
															
														</tr>
													</thead>
						
													<tbody id="table" aria-relevant="all" aria-live="polite" role="alert">
														<tr>
															<td style="width: 20%">日期</td>
															<td style="width: 30%">${refund.refundTable.refundDate }</td>
															<td style="width: 20%">客户</td>
															<td style="width: 30%">${refund.customerAgencyName }</td>
														</tr>
														<tr>
															<td style="width: 20%">内容</td>
															<td style="width: 30%">${refund.refundTable.refundContent }</td>
															<td style="width: 20%">方式</td>
															<td style="width: 30%">${refund.refundTable.refundWays }</td>
														</tr>
														<tr>
															<td style="width: 20%" class="red">金额</td>
															<td style="width: 30%">${refund.refundTable.refundAmount }</td>
															<td style="width: 20%">备注</td>
															<td style="width: 30%">${refund.refundTable.remark }</td>
														</tr>
														<tr>
															<td style="width: 20%">申请人</td>
															<td style="width: 30%">${refund.applicationerName }</td>
															<td style="width: 20%">状态</td>
															<td style="width: 30%">${refund.status }</td>
														</tr>
													</tbody>
												</table>
												<div id="${refund.refundTable.id }" class="action" style="margin: 5px;"><button class="btn btn-sm btn-danger">驳回</button><button class="btn btn-sm btn-success pull-right">同意</button></div>
												<HR style="margin-top: 0px;">
											</div>
										</c:if>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
									<div>
										<table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
											<thead>
												<tr role="row">
													
												</tr>
											</thead>
				
											<tbody id="table" aria-relevant="all" aria-live="polite" role="alert">
											<tr>
												<td style="width: 20%">日期</td>
												<td style="width: 30%">${refund.refundTable.refundDate }</td>
												<td style="width: 20%">客户</td>
												<td style="width: 30%">${refund.customerAgencyName }</td>
											</tr>
											<tr>
												<td style="width: 20%">内容</td>
												<td style="width: 30%">${refund.refundTable.refundContent }</td>
												<td style="width: 20%">方式</td>
												<td style="width: 30%">${refund.refundTable.refundWays }</td>
											</tr>
											<tr>
												<td style="width: 20%" class="red">退款金额</td>
												<td style="width: 30%" class="red">${refund.refundTable.refundAmount }</td>
												<td style="width: 20%">备注</td>
												<td style="width: 30%">${refund.refundTable.remark }</td>
											</tr>
											<tr>
												<td style="width: 20%">申请人</td>
												<td style="width: 30%">${refund.applicationerName }</td>
												<td style="width: 20%">状态</td>
												<td style="width: 30%">${refund.status }</td>
											</tr>
										</tbody>
									</table>
									<div id="${refund.refundTable.id }" class="action" style="margin: 5px;"><button class="btn btn-sm btn-danger">驳回</button><button class="btn btn-sm btn-success pull-right">同意</button></div>
									<HR style="margin-top: 0px;">
								</div>
							</c:otherwise>
						</c:choose>
					</c:forEach>
<!-- 列表循环结束 -->	
					<div style="text-align: center;">
						<a id="refunded" href="<%=path %>mobile/refundApplication?id=${tour.id }&status=-1">点击查看本团退款申请记录</a>
					</div>
							
					</div>
				</div>

<!-- 正文结束 -->	

<jsp:include page="../../../resources/include/footer.jsp"></jsp:include>

<script type="text/javascript">
	$(function(){
		/* 获取url参数 */
        if(getUrlParam("status")==-1){//参数值
        	$("#refunded").remove();
        	$(".action").remove();
        }
        
		$(".btn-success").click(function(){
			var id = $(this).parent().attr("id");
			$(this).parent().parent().remove();
			var myData = {id:id};
			$.ajax({
			      type: "GET",  
			      contentType:"application/json;charset=utf-8",  
			      url:"${path }mobile/refundApplicationOk",  
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
		        url:"${path }mobile/refundApplicationCancel",  
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
