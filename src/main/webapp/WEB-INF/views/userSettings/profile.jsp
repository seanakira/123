<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<jsp:include page="../../../resources/include/header.jsp"></jsp:include>
<jsp:include page="../../../resources/include/sider.jsp"></jsp:include>
<jsp:include page="../../../resources/include/pageSettings.jsp"></jsp:include>

 
	<div class="main-content">
		<div class="breadcrumbs" id="breadcrumbs">
			<script type="text/javascript">
				try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
			</script>

			<ul class="breadcrumb">
				<li>
					<i class="icon-globe"></i>
					<a href="#">修改资料</a>
				</li>
			</ul><!-- .breadcrumb -->

			<div class="nav-search" id="nav-search">
				<form class="form-search">
					<span class="input-icon">
						<input placeholder="搜索 ..." class="nav-search-input" id="nav-search-input" autocomplete="off" type="text">
						<i class="icon-search nav-search-icon"></i>
					</span>
				</form>
			</div><!-- #nav-search -->
		</div>

		
		<div class="page-content">
			<div id="user-profile-3" class="user-profile row">
				<div class="col-sm-offset-1 col-sm-10">
					<div class="space"></div>

					<form class="form-horizontal">
						<div class="tabbable">
							<ul class="nav nav-tabs padding-16">
								<li class="active">
									<a data-toggle="tab" href="#edit-basic">
										<i class="green icon-edit bigger-125"></i>
										基本信息
									</a>
								</li>

								<li>
									<a data-toggle="tab" href="#edit-password">
										<i class="blue icon-key bigger-125"></i>
										密码修改
									</a>
								</li>
							</ul>

							<div class="tab-content profile-edit-tab-content">
								<div id="edit-basic" class="tab-pane in active">
									<h4 class="header blue bolder smaller">基本信息</h4>

									<div class="row">
										<div class="col-xs-12 col-sm-4">
											<input type="file" />
										</div>

										<div class="vspace-xs"></div>

										<div class="col-xs-12 col-sm-8">
											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-right" for="form-field-username">用户名：</label>
						
												<div class="col-sm-8">
													<label class="col-sm-8 control-label no-padding-right" for="form-field-username">zhangsandezhanghao</label>
												</div>
											</div>

											<div class="space-4"></div>

											<div class="form-group">
												<label class="col-sm-4 control-label no-padding-left" for="form-field-name">姓名：</label>

												<div class="col-sm-8">
													<label class="col-sm-8 control-label no-padding-left" for="form-field-name">张三</label>
												</div>
											</div>
										</div>
									</div>

									<hr />


									<div class="space-4"></div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right">所在部门：</label>

										<div class="col-sm-9">
											<label class="col-sm-4 control-label no-padding-right">港中旅国际(山东)旅行社</label>
										</div>
									</div>
									
									<div class="space-4"></div>
									
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right">员工职位：</label>

										<div class="col-sm-9">
											<label class="col-sm-4 control-label no-padding-right">业务经理</label>
										</div>
									</div>

									<div class="space"></div>
									<h4 class="header blue bolder smaller">联系方式</h4>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-email">电子邮件：</label>

										<div class="col-sm-9">
											<span class="input-icon input-icon-right">
												<input type="email" id="form-field-email" value="example@example.com" />
												<i class="icon-envelope"></i>
											</span>
										</div>
									</div>

									<div class="space-4"></div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-website">qq号码：</label>

										<div class="col-sm-9">
											<span class="input-icon input-icon-right">
												<input type="url" id="form-field-website" value="123456789" />
												<i class="icon-comment"></i>
											</span>
										</div>
									</div>

									<div class="space-4"></div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-phone">电话号码：</label>

										<div class="col-sm-9">
											<span class="input-icon input-icon-right">
												<input class="input-medium input-mask-phone" type="text" id="form-field-phone" />
												<i class="icon-phone icon-flip-horizontal"></i>
											</span>
										</div>
									</div>

									<div class="space"></div>
								</div>

								<div id="edit-password" class="tab-pane">
									<div class="space-10"></div>
									
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-pass1">请输入原密码：</label>

										<div class="col-sm-9">
											<input type="password" id="form-field-pass1" />
										</div>
									</div>
									
									<div class="space-4"></div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-pass1">请输入新密码：</label>

										<div class="col-sm-9">
											<input type="password" id="form-field-pass1" />
										</div>
									</div>

									<div class="space-4"></div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-pass2">再次输入新密码：</label>

										<div class="col-sm-9">
											<input type="password" id="form-field-pass2" />
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button class="btn btn-info" type="button">
									<i class="icon-ok bigger-110"></i>
									确认
								</button>

								&nbsp; &nbsp;
								<button class="btn" type="reset">
									<i class="icon-undo bigger-110"></i>
									清空
								</button>
							</div>
						</div>
					</form>
				</div><!-- /span -->
			</div><!-- /user-profile -->			

		</div><!-- /.page-content -->
	</div><!-- /.main-content -->

				
				<!-- Modal -->
				<div class="modal fade" id="userModal" tabindex="-1" role="dialog" 
				   aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog">
				      <div class="modal-content">
				         <div class="modal-header">
				            <button type="button" class="close" 
				               data-dismiss="modal" aria-hidden="true">
				                  &times;
				            </button>
				            <h4 class="modal-title" id="myModalLabel">
				            	姓名
				            </h4>
				         </div>
				         <div class="modal-body">
				          		 <div class="profile-user-info profile-user-info-striped">
												<div class="profile-info-row">
													<div class="profile-info-name"> 所在部门 </div>

													<div class="profile-info-value">
														<span id="deptSpan">部门</span>
													</div>
												</div>

												<div class="profile-info-row">
													<div class="profile-info-name"> 职务 </div>

													<div class="profile-info-value">
														<span id="positionSpan">职务</span>
													</div>
												</div>

												<div class="profile-info-row">
													<div class="profile-info-name"> 电话 </div>

													<div class="profile-info-value">
														<span id="telSpan">电话</span>
													</div>
												</div>

												<div class="profile-info-row">
													<div class="profile-info-name"> qq号 </div>

													<div class="profile-info-value">
														<span id="qqSpan">qq号</span>
													</div>
												</div>
											</div>
				         </div>
				         <div class="modal-footer">
				            <button type="button" class="btn btn-default" 
				               data-dismiss="modal">关闭
				            </button>
				         </div>
				      </div><!-- /.modal-content -->
				</div><!-- /.modal -->


<jsp:include page="../../../resources/include/footer.jsp"></jsp:include>
<!-- Required Javascript -->
<!-- page specific plugin scripts -->
	<!-- script src="/localtour/resources/assets/js/fuelux/data/fuelux.tree-sampledata.js"></script> -->
	<script src="/localtour/resources/assets/js/fuelux/fuelux.tree.min.js"></script>
	
	<!-- inline scripts related to this page -->

		<script type="text/javascript">
		
		$(".nav-list").children("li").attr('class','');
		
		$(function(){
			$('.tree-folder').delegate(".tree-folder-header","click",function(){
				var val = $(this).attr("value");
				if ($(this).attr("expand")=="1"){
					$(this).find('i').attr("class","icon-minus");
					$(this).attr("expand","0");
					$.ajax({
				        type: "GET",  
				        contentType:"application/json;charset=utf-8", 
				        data:"deptId="+val,
				        url:"/localtour/deptStructure/getUserTree", 
				        dataType: "json",  
				        async: false,  
				        success:function(data){
				        	if(null!=data){
				        		for (var index = 0; index < data.length; index++) {
										$("#content"+val).append("<div class='tree-item' style='display:block' value='"+data[index].id+"' select='0'><i class='icon-remove'></i><div class='tree-item-name'>"+data[index].realName+"</div></div>");
								}
				        	}
				        }
				    }) 
				    $.ajax({
				        type: "GET",  
				        contentType:"application/json;charset=utf-8", 
				        data:"upperDeptId="+val,
				        url:"/localtour/deptStructure/getTree", 
				        dataType: "json",  
				        async: false,  
				        success:function(data){
				        	if(null!=data){
				        		for (var index = 0; index < data.length; index++) {
 									$("#content"+val).append("<div class='tree-folder' style='display:block;' ><div class='tree-folder-header' value='"+data[index].id+"' expand='1'><i class='icon-plus'></i><div class='tree-folder-name'>"+data[index].deptName+"</div></div><div class='tree-folder-content' id='content"+data[index].id+"'></div></div>");
								}
				        	}
				        	
				        }
				    }) 
				}
				else{
					$(this).find('i').attr("class","icon-plus");
					$(this).attr("expand","1");
					$("#content"+val).empty();
				}
			}).delegate(".tree-item","click",function(){
				var val = $(this).attr("value");
				if ($(this).attr("select")=="0"){
					$('.tree-selected').attr('class','tree-item').attr('select','0');
					$('.icon-ok').attr('class','icon-remove');
					$(this).attr("class","tree-item tree-selected");
					$(this).find('i').attr("class","icon-ok");
					$(this).attr("select","1");
				}
				else{
					$(this).attr("class","tree-item");
					$(this).find('i').attr("class","icon-remove");
					$(this).attr("select","0");
				}
			})
			
		});
		
		$(function(){
			$('.btn-view').click(function(){
				var val = $(".tree-selected").attr("value");
				$.ajax({
			        type: "GET",  
			        contentType:"application/json;charset=utf-8", 
			        data:"userId="+val,
			        url:"/localtour/deptStructure/getUserView", 
			        dataType: "json",  
			        async: false,  
			        success:function(data){
			        	if(null!=data){
			        		$(".modal-title").empty();
							$(".modal-title").append(data.userTable.realName);
							$("#deptSpan").empty();
							$("#deptSpan").append(data.deptName);
							$("#positionSpan").empty();
							$("#positionSpan").append(data.userTable.position);
							$("#telSpan").empty();
							$("#telSpan").append(data.userTable.phone);
							$("#qqSpan").empty();
							$("#qqSpan").append(data.userTable.qq);
			        	}
			        	
			        }
			    }) 
			})
		})
		
		

		/**
		$('#tree1').on('loaded', function (evt, data) {
		});

		$('#tree1').on('opened', function (evt, data) {
		});

		$('#tree1').on('closed', function (evt, data) {
		});

		$('#tree1').on('selected', function (evt, data) {
		});
		**/
		
		
		</script>
	

