<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% String path = request.getContextPath()+"/";%>
<!DOCTYPE html>
<html lang="zh">
	<head>
		<meta charset="utf-8" />
		<title>系统登录</title>
		<meta name="keywords" content="港中旅国际（山东）旅行社有限公司" />
		<meta name="description" content="港中旅国际（山东）旅行社有限公司" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<!-- basic styles -->

		<link href="<%=path %>resources/assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="<%=path %>resources/assets/css/font-awesome.min.css" />

		<!--[if IE 7]>
		  <link rel="stylesheet" href="<%=path %>assets/css/font-awesome-ie7.min.css" />
		<![endif]-->

		<!-- page specific plugin styles -->

		<!-- fonts -->

		<link rel="stylesheet" href="<%=path %>resources/assets/css/fontsfromgoogle.css" />

		<!-- ace styles -->

		<link rel="stylesheet" href="<%=path %>resources/assets/css/ace.min.css" />
		<link rel="stylesheet" href="<%=path %>resources/assets/css/ace-rtl.min.css" />

		<!--[if lte IE 8]>
		  <link rel="stylesheet" href="<%=path %>assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

		<!--[if lt IE 9]>
		<script src="assets/js/html5shiv.js"></script>
		<script src="assets/js/respond.min.js"></script>
		<![endif]-->
		<!--[if IE]><script type="text/javascript">alert("请勿使用IE内核浏览器")</script> <![endif]-->
        <style type="text/css">
        	.main-container{
				padding-top:100px;
			}
			.login-layout {
			    background-color: #fff;
			    background: url(<%=request.getAttribute("img")%>) no-repeat center;
			}
			
			.login-layout .widget-box {
			    background-color: #CECECE;
			}
			.grey{
				color: #6f6f6f !important;
			}
			h5{
				
			}
			#login-box{
				padding: 1px;
				width:90%;
				background: rgba(0,0,0,0.1);
				left: 5%;
			}
			.login-layout .widget-box .widget-main{
				padding: 10px 36px 10px 36px;
				background: rgba(0,0,0,0.1);
			}
			.widget-body{
				background: rgba(0,0,0,0.1);
			}
			.button{
				background: rgba(0,0,0,0.1);
				border: rgba(230,230,230,0.3) solid 1px;
				color: rgb(230,230,230);
				padding: 3px 10px 3px 10px;
				width: 100px;
			}
			.side{
				background: rgba(0,0,0,0.5);
			    float: right;
			    height: 100%;
			    overflow-y: scroll;
			    overflow-x: hidden;
			    position: absolute;
			    top: 0px;
			    right: 0px;
			    padding: 10px;
			}
			#hide{
				position: fixed;
				top: 50%;
				right: 398px;
				color: white;
				width: 50px;
				height: 50px;
				text-align: center;
				background-color: rgba(0,0,0,0.5);
			}
			.input{
				background-color:transparent;
			}
			@media only screen and (max-width:800px) {
				.side{
					display: none;
				}
				#hide{
					display: none;
				}
			}
        </style>
	</head>

	<body class="login-layout">
		<div id="hide"><i class="icon-angle-right bigger-300"></i></div>
		<div class="side">
			${imgInfo }
		</div>
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<div class="position-relative">
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
										  <h4 class="center"> <img alt="logo" src="<%=path %>resources/assets/avatars/logo.png"><span style="color: rgb(230,230,230);">星途业务管理系统</span> </h4>
										  <div class="lbl red" style="margin-top: 25px;">${msg }</div>
										  <div class="space-6"></div>
										  <form action="<%=path %>admin/login" method="post">
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input name="userName" type="text" class="form-control input" placeholder="用户名" />
															<i class="icon-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input name="pwd" type="password" class="form-control input" placeholder="密码" />
															<i class="icon-lock"></i>
														</span>
													</label>

													<div class="space"></div>

													<div class="clearfix" style="text-align: center;">
														<!-- <label class="inline">
															<input type="checkbox" class="ace" />
															<span class="lbl"> 记住密码 </span>
														</label> -->

														<button type="submit" class="button">
															<i class="icon-key"></i>
															登陆
														</button>
													</div>
													
													<div class="space-4">
													</div>
												</fieldset>
											</form>

											<!-- <div class="social-or-login center">
												<span class="bigger-110"></span>
												<h6 class="blue center">&copy; 港中旅国际（山东）旅行社有限公司 </h6>
											</div> -->

											
										</div><!-- /widget-main -->

										
									</div><!-- /widget-body -->
								</div><!-- /login-box -->
								<div id="forgot-box" class="forgot-box widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header red lighter bigger">
												<i class="icon-key"></i>
												Retrieve Password
											</h4>

											<div class="space-6"></div>
											<p>
												Enter your email and to receive instructions
											</p>

											<form>
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="email" class="form-control" placeholder="Email" />
															<i class="icon-envelope"></i>
														</span>
													</label>

													<div class="clearfix">
														<button type="button" class="width-35 pull-right btn btn-sm btn-danger">
															<i class="icon-lightbulb"></i>
															Send Me!
														</button>
													</div>
												</fieldset>
											</form>
										</div><!-- /widget-main -->

										<div class="toolbar center">
											<a href="#" onclick="show_box('login-box'); return false;" class="back-to-login-link">
												Back to login
												<i class="icon-arrow-right"></i>
											</a>
										</div>
									</div><!-- /widget-body -->
								</div><!-- /forgot-box -->

								<div id="signup-box" class="signup-box widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header green lighter bigger">
												<i class="icon-group blue"></i>
												New User Registration
											</h4>

											<div class="space-6"></div>
											<p> Enter your details to begin: </p>

											<form>
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="email" class="form-control" placeholder="Email" />
															<i class="icon-envelope"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" placeholder="Username" />
															<i class="icon-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" placeholder="Password" />
															<i class="icon-lock"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" placeholder="Repeat password" />
															<i class="icon-retweet"></i>
														</span>
													</label>

													<label class="block">
														<input type="checkbox" class="ace" />
														<span class="lbl">
															I accept the
															<a href="#">User Agreement</a>
														</span>
													</label>

													<div class="space-24"></div>

													<div class="clearfix">
														<button type="reset" class="width-30 pull-left btn btn-sm">
															<i class="icon-refresh"></i>
															Reset
														</button>

														<button type="button" class="width-65 pull-right btn btn-sm btn-success">
															Register
															<i class="icon-arrow-right icon-on-right"></i>
														</button>
													</div>
												</fieldset>
											</form>
										</div>

										<div class="toolbar center">
											<a href="#" onclick="show_box('login-box'); return false;" class="back-to-login-link">
												<i class="icon-arrow-left"></i>
												Back to login
											</a>
										</div>
									</div><!-- /widget-body -->
								</div><!-- /signup-box -->
							</div><!-- /position-relative -->
						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div>
		</div><!-- /.main-container -->
		<!-- basic scripts -->

		<!--[if !IE]> -->

		<script src="//apps.bdimg.com/libs/jquery/2.0.3/jquery.min.js"></script>

		<!-- <![endif]-->

		<!--[if IE]>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<![endif]-->

		<!--[if !IE]> -->

		<script type="text/javascript">
			window.jQuery || document.write("<script src='<%=path %>resources/assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='<%=path %>assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->

		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='<%=path %>resources/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>

		<!-- inline scripts related to this page -->

		<script type="text/javascript">
			function show_box(id) {
			 jQuery('.widget-box.visible').removeClass('visible');
			 jQuery('#'+id).addClass('visible');
			}
			$(function(){
				$("#hplaDL").remove();
				$("#hpBingAppQR").remove();
				var a = $("#hpla").find("a");
				$.each(a,function(){
					/* $(this).attr("href","http://cn.bing.com"+$(this).attr("href")); */
					$(this).attr("href","https://www.baidu.com/s?wd="+($(this).find("span").length==0?$(this).prev().prev().find("span").last().text():$(this).find("span").last().text()));
					$(this).attr("target","_blank");
				})
				$(".input").css({"background-color":"transparent","color":"rgb(230,230,230)","border-color": "rgba(230,230,230,0.5)"});
				$(".hplaPvd").text(" ");
				$(".hplaCopy").remove();
			});
			$("#hide").click(function(){
				$(".side").fadeToggle("fast");
				$("#hide").css("right",$("#hide").css("right")=="398px"?"0px":"398px");
				$("#hide").children("i").attr("class",$("#hide").children("i").attr("class")=="icon-angle-right bigger-300"?"icon-angle-left bigger-300":"icon-angle-right bigger-300")
			});
		</script>
</body>
</html>
