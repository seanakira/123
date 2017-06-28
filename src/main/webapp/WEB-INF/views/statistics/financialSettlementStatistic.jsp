<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<% String path = request.getContextPath()+"/"; %>

<jsp:include page="../../../resources/include/header.jsp"></jsp:include>
<jsp:include page="../../../resources/include/pageSettings.jsp"></jsp:include>
<jsp:include page="../../../resources/include/sider.jsp"></jsp:include>

<link rel="stylesheet" href="${path }resources/assets/css/jquery-ui-1.10.3.full.min.css">
<link rel="stylesheet" href="${path }resources/assets/css/chosen.css" />
<style type="text/css">
	#ui-datepicker-div a{
		text-align: center;
	}
	#ui-datepicker-div span{
		text-align: center;
	}
	。default{
		position: relative;
		top: -7px;
		height: 30px;
	}
	。chosen-container{
		top: -2px;
	}
</style>
<!-- 正文开始 -->
				<div class="main-content">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="icon-user"></i>
								<a href="#">财务结算表</a>
							</li>
						</ul><!-- .breadcrumb -->
						<div class="accessBar" style="display: inline-block;">
							开始日期：
							<div style="display: inline-block;margin-right: 10px;margin-top: 2px;"><input id="start" class="datepicker" type="text" style="width: 100px;"></div>
							结束日期：
							<div style="display: inline-block;margin-right: 10px;margin-top: 2px;"><input id="end" class="datepicker" type="text" style="width: 100px;"></div>
							部门：
							<div style="display: inline-block;margin-right: 10px;margin-top: 2px;"><select id="depts" style="display: none;" multiple="multiple" class="chosen-select" data-placeholder="可选多个...">
								<option value="">&nbsp;</option>
								<c:forEach var="dept" items="${depts }">
									<option value="${dept.id }">${dept.deptName }</option>
								</c:forEach>
							</select></div>
							人员：
							<div style="display: inline-block;margin-right: 10px;margin-top: 2px;"><select id="users" style="display: none;" multiple="multiple" class="chosen-select" data-placeholder="可选多个...">
								<option value="">&nbsp;</option>
								<c:forEach var="user" items="${users }">
									<option value="${user.id }">${user.realName }</option>
								</c:forEach>
							</select></div>
							 团号：
							<div style="display: inline-block;margin-right: 10px;margin-top: 2px;"><input id="tourNo" style="width: 100px;" type="text"></div>
							 状态：
							<div style="display: inline-block;margin-right: 10px;margin-top: 2px;" style="width: 100px;"><select id="status"><option value="">&nbsp;</option><option value="9">未结算</option><option value="10">已结算</option></select></div>
							<button id="find" class="btn btn-xs btn-success" style="width: 70px;position: relative;top: -3px;">查询</button>
							<button id="down" class="btn btn-xs btn-success" style="width: 70px;position: relative;top: -3px;margin-left: 10px;">导出Excel</button>
						</div>
					</div>
					<div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid"><table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
						<table id="excel" aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
							<thead>
								<tr role="row">
									<th style="width: 20%;">
										团号
									</th>
									<th style="width: 20%;">
										组团社
									</th>
									<th style="width: 8%;">
										应收
									</th>
									<th style="width: 8%;">									
										报账成本
									<th style="width: 8%;">
										报账毛利
									</th>
									<th style="width: 8%;">
										报账毛利率
									</th>
									<th style="width: 8%;">
										人数
									</th>
									<th style="width: 8%;">
										部门
									</th>
									<th style="width: 8%;">
										团控
									</th>
								</tr>
							</thead>
							<tbody id="table" aria-relevant="all" aria-live="polite" role="alert">
							</tbody>
						</table>
					</div>
				</div>
<!-- 正文结束 -->									
<jsp:include page="../../../resources/include/footer.jsp"></jsp:include>
<!-- 日历组件依赖 -->
<script src="${path }resources/assets/js/jquery-ui-1.10.3.full.min.js"></script>
<!-- 下拉搜索依赖 -->
<script src="${path }resources/assets/js/chosen.jquery.min.js"></script>
<!-- 导出excel组件 -->
<script src="${path }resources/assets/js/kayalshri-tableExport/tableExport.js"></script>
<script src="${path }resources/assets/js/kayalshri-tableExport/jquery.base64.js"></script>
<script type="text/javascript">
	$(function(){
	/* 初始化 */
		$("#statisticalAnalysis").addClass("open");
		$("#statisticalAnalysis").children("ul").attr("style","display:block");
		$("#financialSettlementStatistic").addClass("active");
		$("#depts").chosen({no_results_text: "查无结果", search_contains: true});
		$("#depts").next().attr("style","width: 200px;top: -3px;")
		$("#depts").next().find("li").attr("style","height: 25px;");
		$("#depts").next().find("input").attr("style","height: 25px;top: -6px;position: relative;");
		$("#users").chosen({no_results_text: "查无结果", search_contains: true});
		$("#users").next().attr("style","width: 200px;top: -3px;")
		$("#users").next().find("li").attr("style","height: 25px;");
		$("#users").next().find("input").attr("style","height: 25px;top: -6px;position: relative;");
		$("#status").chosen({no_results_text: "查无结果", search_contains: true});
		$("#status").next().attr("style","width:100px;top: -3px;");
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
	/* 导出excel */
		$("#down").click(function(){
			$('#excel').tableExport({type:'excel',escape:'false'});
		});
	/* 查询 */
		var interval;
		$("#find").click(function(){
			if($("#wait").length==0){
				if($("#start").val()==""||$("#end").val()==""){
					alert("没有选择时间起止日期");
					return;
				}
				var table = $("#table");
				table.html("");
				var start = new Date($("#start").val());
				var end = new Date($("#end").val());
				var myData = {deptIds:$("#depts").val()==null?"":$("#depts").val().join(","),userIds:$("#users").val()==null?"":$("#users").val().join(","),start:start,end:end,tourNo:$("#tourNo").val(),status:$("#status").val()};
				/* var progress = $('<div id="wait" style="position: absolute;top: 300px;width: 30%;left: 45%;text-align: center;"><p>系统正在统计数据，请耐心等待</p><i class="icon-spinner icon-spin orange" style="font-size: 500%"></i></div></div>'); */
				var progress = $('<div id="wait" style="position: absolute;top: 200px;width: 30%;left: 35%;text-align: center;"><p>系统正在统计数据，请耐心等待</p><div class="progress" data-percent="0%"><div class="progress-bar" style="width:0%;"></div></div></div></div>');
				var s = 3*(end.getMonth()-start.getMonth()+1);
				$("#sample-table-2_wrapper").append(progress);
				var percent = 0;
				var interval = setInterval(function(){
					if(percent==99){
						clearInterval(interval);
					}
					$(".progress").attr("data-percent",percent+"%");
					$(".progress-bar").attr("style","width:0"+percent+"%");
					percent++;
				},s*10);
				$.ajax({
			        type: "GET",  
			        contentType:"application/json;charset=utf-8",  
			        url:"${path }financialSettlementStatistic/get",
			        data:myData,
			        dataType: "json",
			        async: true,
			        success:function(data){
			        	clearInterval(interval);
			        	$(".progress").attr("data-percent","100%");
						$(".progress-bar").attr("style","width:0"+percent+"100%");
						progress.remove();
						var willIncomeTotal = 0;
						var willCostTotal = 0;
						var peopleNoTotal = 0;
						$.each(data,function(){
							table.append('<tr><td>'+this.tourNo+'</td><td>'+this.customerAgencyName+'</td><td>'+this.willIncomeSum+'</td><td>'+this.willCostSum+'</td><td>'+this.grossProfit+'</td><td>'+this.grossProfitMargin+'</td><td>'+this.peopleNo+'</td><td>'+this.deptName+'</td><td>'+this.userRealName+'</td></tr>');
							willIncomeTotal = willIncomeTotal + this.willIncomeSum;
							willCostTotal = willCostTotal + this.willCostSum;
							peopleNoTotal = peopleNoTotal + this.peopleNo;
						});
			        	if($("#table").html()==""){
							$("#table").html("没有查询到结果");
						}else{
							table.append('<tr><td>合计：'+data.length+' 个团</td><td></td><td>'+willIncomeTotal.toFixed(2)+'</td><td>'+willCostTotal.toFixed(2)+'</td><td>'+(willIncomeTotal-willCostTotal).toFixed(2)+'</td><td>'+((willIncomeTotal-willCostTotal)/willIncomeTotal*100).toFixed(2)+'%</td><td>'+peopleNoTotal+'</td><td></td><td></td></tr>');
						}
			         }
				});
			}
		});
	});
	
</script>
