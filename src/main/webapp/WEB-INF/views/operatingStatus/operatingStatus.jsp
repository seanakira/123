<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
					<a href="#">经营状况</a>
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
		<div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid"><table aria-describedby="sample-table-2_info" id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
						<!-- <div class="page-header align-right">
								<small>
									<i class="icon-double-angle-right"></i>
									
								</small>
						</div>/.page-header -->
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<div class="col-sm-6">
									<div class="widget-box transparent">
										<div class="widget-header widget-header-flat">
											<h4 class="lighter">
												<i class="icon-signal"></i>
												业务统计
											</h4>

											<div class="widget-toolbar">
												<a href="#" data-action="collapse">
													<i class="icon-chevron-up"></i>
												</a>
											</div>
										</div>

										<div class="widget-body">
											<div class="widget-main padding-4">
												<div id="sales-charts"></div>
											</div><!-- /widget-main -->
										</div><!-- /widget-body -->
									</div><!-- /widget-box -->
								</div>	
								
								
								
							<div class="col-sm-6">
								<div class="widget-box ">
									<div class="widget-header">
										<h4 class="lighter smaller">
											<i class="icon-comment blue"></i>
											部门会话
										</h4>
									</div>

									<div class="widget-body">
										<div class="widget-main no-padding">
											<div class="dialogs">
												<div class="itemdiv dialogdiv">
													<div class="user">
														<img alt="Alexa's Avatar" src="assets/avatars/avatar1.png">
													</div>

													<div class="body">
														<div class="time">
															<i class="icon-time"></i>
															<span class="green">4秒钟前</span>
														</div>

														<div class="name">
															<a href="#">Alexa</a>
														</div>
														<div class="text">大家好啊</div>

														<div class="tools">
															<a href="#" class="btn btn-minier btn-info">
																<i class="icon-only icon-share-alt"></i>
															</a>
														</div>
													</div>
												</div>

												<div class="itemdiv dialogdiv">
													<div class="user">
														<img alt="John's Avatar" src="assets/avatars/avatar.png">
													</div>

													<div class="body">
														<div class="time">
															<i class="icon-time"></i>
															<span class="blue">38秒以前</span>
														</div>

														<div class="name">
															<a href="#">John</a>
														</div>
														<div class="text">框架很好用嘛</div>

														<div class="tools">
															<a href="#" class="btn btn-minier btn-info">
																<i class="icon-only icon-share-alt"></i>
															</a>
														</div>
													</div>
												</div>

												<div class="itemdiv dialogdiv">
													<div class="user">
														<img alt="Bob's Avatar" src="assets/avatars/user.jpg">
													</div>

													<div class="body">
														<div class="time">
															<i class="icon-time"></i>
															<span class="orange">2分钟以前</span>
														</div>

														<div class="name">
															<a href="#">Bob</a>
															<span class="label label-info arrowed arrowed-in-right">admin</span>
														</div>
														<div class="text">欢迎大家使用ACE后台管理系统.</div>

														<div class="tools">
															<a href="#" class="btn btn-minier btn-info">
																<i class="icon-only icon-share-alt"></i>
															</a>
														</div>
													</div>
												</div>

												<div class="itemdiv dialogdiv">
													<div class="user">
														<img alt="Jim's Avatar" src="assets/avatars/avatar4.png">
													</div>

													<div class="body">
														<div class="time">
															<i class="icon-time"></i>
															<span class="grey">3分钟以前</span>
														</div>

														<div class="name">
															<a href="#">Jim</a>
														</div>
														<div class="text">大家多提提BUG</div>

														<div class="tools">
															<a href="#" class="btn btn-minier btn-info">
																<i class="icon-only icon-share-alt"></i>
															</a>
														</div>
													</div>
												</div>

												<div class="itemdiv dialogdiv">
													<div class="user">
														<img alt="Alexa's Avatar" src="assets/avatars/avatar1.png">
													</div>

													<div class="body">
														<div class="time">
															<i class="icon-time"></i>
															<span class="green">4分钟以前</span>
														</div>

														<div class="name">
															<a href="#">Alexa</a>
														</div>
														<div class="text">继续支持ACE后台系统</div>

														<div class="tools">
															<a href="#" class="btn btn-minier btn-info">
																<i class="icon-only icon-share-alt"></i>
															</a>
														</div>
													</div>
												</div>
											</div>

											<form>
												<div class="form-actions">
													<div class="input-group">
														<input placeholder="Type your message here ..." type="text" class="form-control" name="message">
														<span class="input-group-btn">
															<button class="btn btn-sm btn-info no-radius" type="button">
																<i class="icon-share-alt"></i>
																发送
															</button>
														</span>
													</div>
												</div>
											</form>
										</div><!-- /widget-main -->
									</div><!-- /widget-body -->
								</div><!-- /widget-box -->
							</div>
						
							<div class="col-sm-12">
								<div class="wysiwyg-toolbar btn-toolbar center    "> <div class="btn-group">  <a class="btn btn-sm  dropdown-toggle" data-toggle="dropdown" title="" data-original-title="Font"><i class="icon-font"></i><i class="icon-angle-down icon-on-right"></i></a>  <ul class="dropdown-menu dropdown-light"> <li><a data-edit="fontName Arial" style="font-family:'Arial'">Arial</a></li>  <li><a data-edit="fontName Courier" style="font-family:'Courier'">Courier</a></li>  <li><a data-edit="fontName Comic Sans MS" style="font-family:'Comic Sans MS'">Comic Sans MS</a></li>  <li><a data-edit="fontName Helvetica" style="font-family:'Helvetica'">Helvetica</a></li>  <li><a data-edit="fontName Open Sans" style="font-family:'Open Sans'">Open Sans</a></li>  <li><a data-edit="fontName Tahoma" style="font-family:'Tahoma'">Tahoma</a></li>  <li><a data-edit="fontName Verdana" style="font-family:'Verdana'">Verdana</a></li>  </ul> </div> <div class="btn-group">  <a class="btn btn-sm  dropdown-toggle" data-toggle="dropdown" title="" data-original-title="Font Size"><i class="icon-text-height"></i>&nbsp;<i class="icon-angle-down icon-on-right"></i></a>  <ul class="dropdown-menu dropdown-light">  <li><a data-edit="fontSize 1"><font size="1">Small</font></a></li>  <li><a data-edit="fontSize 3"><font size="3">Normal</font></a></li>  <li><a data-edit="fontSize 5"><font size="5">Huge</font></a></li>  </ul>  </div> <div class="btn-group">  <a class="btn btn-sm btn-info" data-edit="bold" title="" data-original-title="Bold (Ctrl/Cmd+B)"><i class="icon-bold"></i></a>  <a class="btn btn-sm btn-info" data-edit="italic" title="" data-original-title="Italic (Ctrl/Cmd+I)"><i class="icon-italic"></i></a>  <a class="btn btn-sm btn-info" data-edit="strikethrough" title="" data-original-title="Strikethrough"><i class="icon-strikethrough"></i></a>  <a class="btn btn-sm btn-info" data-edit="underline" title="" data-original-title="Underline"><i class="icon-underline"></i></a>  </div> <div class="btn-group">  <a class="btn btn-sm btn-success" data-edit="insertunorderedlist" title="" data-original-title="Bullet list"><i class="icon-list-ul"></i></a>  <a class="btn btn-sm btn-success" data-edit="insertorderedlist" title="" data-original-title="Number list"><i class="icon-list-ol"></i></a>  <a class="btn btn-sm btn-purple" data-edit="outdent" title="" data-original-title="Reduce indent (Shift+Tab)"><i class="icon-indent-left"></i></a>  <a class="btn btn-sm btn-purple" data-edit="indent" title="" data-original-title="Indent (Tab)"><i class="icon-indent-right"></i></a>  </div> <div class="btn-group">  <a class="btn btn-sm btn-primary active" data-edit="justifyleft" title="" data-original-title="Align Left (Ctrl/Cmd+L)"><i class="icon-align-left"></i></a>  <a class="btn btn-sm btn-primary" data-edit="justifycenter" title="" data-original-title="Center (Ctrl/Cmd+E)"><i class="icon-align-center"></i></a>  <a class="btn btn-sm btn-primary" data-edit="justifyright" title="" data-original-title="Align Right (Ctrl/Cmd+R)"><i class="icon-align-right"></i></a>  <a class="btn btn-sm btn-inverse" data-edit="justifyfull" title="" data-original-title="Justify (Ctrl/Cmd+J)"><i class="icon-align-justify"></i></a>  </div> <div class="btn-group">  <div class="inline position-relative"> <a class="btn btn-sm btn-pink dropdown-toggle" data-toggle="dropdown" title="" data-original-title="Hyperlink"><i class="icon-link"></i></a>  <div class="dropdown-menu dropdown-caret pull-right">							<div class="input-group">								<input class="form-control" placeholder="URL" type="text" data-edit="createLink">								<span class="input-group-btn">									<button class="btn btn-sm btn-primary" type="button">Add</button>								</span>							</div>						</div> </div> <a class="btn btn-sm btn-pink" data-edit="unlink" title="" data-original-title="Remove Hyperlink"><i class="icon-unlink"></i></a>  </div> <div class="btn-group">  <div class="inline position-relative"> <a class="btn btn-sm btn-success dropdown-toggle" data-toggle="dropdown" title="" data-original-title="Insert picture"><i class="icon-picture"></i></a>  <div class="dropdown-menu dropdown-caret pull-right">							<div class="input-group">								<input class="form-control" placeholder="Image URL" type="text" data-edit="insertImage">								<span class="input-group-btn">									<button class="btn btn-sm btn-primary" type="button">Insert</button>								</span>							</div><div class="space-2"></div>							 <div class="center">								<button class="btn btn-sm btn-success wysiwyg-choose-file" type="button"><i class="icon-file"></i> Choose Image …</button>								<input type="file" data-edit="insertImage">							  </div> </div> </div> </div> <div class="btn-group">  <select class="hide wysiwyg_colorpicker" title="Change Color" style="display: none;">  <option value="#ac725e">#ac725e</option>  <option value="#d06b64">#d06b64</option>  <option value="#f83a22">#f83a22</option>  <option value="#fa573c">#fa573c</option>  <option value="#ff7537">#ff7537</option>  <option value="#ffad46">#ffad46</option>  <option value="#42d692">#42d692</option>  <option value="#16a765">#16a765</option>  <option value="#7bd148">#7bd148</option>  <option value="#b3dc6c">#b3dc6c</option>  <option value="#fbe983">#fbe983</option>  <option value="#fad165">#fad165</option>  <option value="#92e1c0">#92e1c0</option>  <option value="#9fe1e7">#9fe1e7</option>  <option value="#9fc6e7">#9fc6e7</option>  <option value="#4986e7">#4986e7</option>  <option value="#9a9cff">#9a9cff</option>  <option value="#b99aff">#b99aff</option>  <option value="#c2c2c2">#c2c2c2</option>  <option value="#cabdbf">#cabdbf</option>  <option value="#cca6ac">#cca6ac</option>  <option value="#f691b2">#f691b2</option>  <option value="#cd74e6">#cd74e6</option>  <option value="#a47ae2">#a47ae2</option>  <option value="#444444">#444444</option>  </select><div class="dropdown dropdown-colorpicker"><a data-toggle="dropdown" class="dropdown-toggle" href="#"><span class="btn-colorpicker" style="background-color: rgb(66, 214, 146);" data-original-title="" title=""></span></a><ul class="dropdown-menu dropdown-caret pull-right"><li><a class="colorpick-btn" href="#" style="background-color:#ac725e;" data-color="#ac725e"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#d06b64;" data-color="#d06b64"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#f83a22;" data-color="#f83a22"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#fa573c;" data-color="#fa573c"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#ff7537;" data-color="#ff7537"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#ffad46;" data-color="#ffad46"></a></li><li><a class="colorpick-btn selected" href="#" style="background-color:#42d692;" data-color="#42d692"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#16a765;" data-color="#16a765"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#7bd148;" data-color="#7bd148"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#b3dc6c;" data-color="#b3dc6c"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#fbe983;" data-color="#fbe983"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#fad165;" data-color="#fad165"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#92e1c0;" data-color="#92e1c0"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#9fe1e7;" data-color="#9fe1e7"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#9fc6e7;" data-color="#9fc6e7"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#4986e7;" data-color="#4986e7"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#9a9cff;" data-color="#9a9cff"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#b99aff;" data-color="#b99aff"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#c2c2c2;" data-color="#c2c2c2"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#cabdbf;" data-color="#cabdbf"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#cca6ac;" data-color="#cca6ac"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#f691b2;" data-color="#f691b2"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#cd74e6;" data-color="#cd74e6"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#a47ae2;" data-color="#a47ae2"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#444444;" data-color="#444444"></a></li></ul></div>  <input style="display:none;" disabled="" class="hide" type="text" data-edit="foreColor">  </div> <div class="btn-group">  <a class="btn btn-sm btn-grey" data-edit="undo" title="" data-original-title="Undo (Ctrl/Cmd+Z)"><i class="icon-undo"></i></a>  <a class="btn btn-sm btn-grey" data-edit="redo" title="" data-original-title="Redo (Ctrl/Cmd+Y)"><i class="icon-repeat"></i></a>  </div> </div>
								<div class="wysiwyg-editor" id="editor1" contenteditable="true"></div>
							</div>
								
								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->

				</div>
				</div>
				
<jsp:include page="../../../resources/include/footer.jsp"></jsp:include>
<!-- Required Javascript -->
<!-- page specific plugin scripts -->
	<!-- script src="/localtour/resources/assets/js/fuelux/data/fuelux.tree-sampledata.js"></script> -->
	<script src="assets/js/bootstrap-wysiwyg.min.js"></script>
	
	
	<!-- inline scripts related to this page -->

		<script type="text/javascript">
		$(function(){
			/* 初始化 */
					$("#operatingStatus").addClass("active");
		});
		
		
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
	

