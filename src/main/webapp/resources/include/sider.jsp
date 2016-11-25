<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% String path = request.getContextPath()+"/"; %>

		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>

				<div class="sidebar" id="sidebar">
					<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
					</script>

					<div class="sidebar-shortcuts" id="sidebar-shortcuts">
						<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
							<button class="btn btn-success">
								<i class="icon-signal"></i>
							</button>

							<button class="btn btn-info">
								<i class="icon-pencil"></i>
							</button>

							<button class="btn btn-warning">
								<i class="icon-group"></i>
							</button>

							<button class="btn btn-danger">
								<i class="icon-cogs"></i>
							</button>
						</div>

						<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
							<span class="btn btn-success"></span>

							<span class="btn btn-info"></span>

							<span class="btn btn-warning"></span>

							<span class="btn btn-danger"></span>
						</div>
					</div><!-- #sidebar-shortcuts -->

					<ul class="nav nav-list">
						<li id="operatingStatus">
							<a href="<%=path %>operatingStatus">
								<i class="icon-dashboard"></i>
								<span class="menu-text"> 经营状况 </span>
							</a>
						</li>
						
						<li id="toDoManage">
							<a href="#" class="dropdown-toggle">
								<i class="icon-suitcase"></i>
								<span class="menu-text"> 待办事项</span>
								<b class="arrow icon-angle-down"></b>
							</a>
							<ul class="submenu">
								<li id="toDoReimbursementApplication">
									<a href="<%=path %>reimbursementApplication">
										<i class="icon-double-angle-right"></i>
										报账审核
									</a>
								</li>
							</ul>
						</li>
						
						<li id="gourpManage">
							<a href="#" class="dropdown-toggle">
								<i class="icon-flag"></i>
								<span class="menu-text"> 团队管理 </span>
								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li id="localTourManage">
									<a href="<%=path %>localTourManage">
										<i class="icon-double-angle-right"></i>
										地接团队
									</a>
								</li>
								<li id="billCheckManage">
									<a href="<%=path %>billCheckManage">
										<i class="icon-double-angle-right"></i>
										签单管理
									</a>
								</li>
							</ul>
						</li>
						
						<li id="guideManage">
							<a href="#" class="dropdown-toggle">
								<i class="icon-github-alt"></i>
								<span class="menu-text"> 导游管理 </span>

								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li id="guideTimeManage">
									<a href="<%=path %>guideTimeManage">
										<i class="icon-double-angle-right"></i>
										导游排团表
									</a>
								</li>
								<li id="guideManager">
									<a href="<%=path %>guideManage">
										<i class="icon-double-angle-right"></i>
										导游管理
									</a>
								</li>
							</ul>
						</li>

						<li id="financeManage">
							<a href="#" class="dropdown-toggle">
								<i class="icon-desktop"></i>
								<span class="menu-text"> 结算管理</span>

								<b class="arrow icon-angle-down"></b>
							</a>
							
							<ul class="submenu">
								<li id="payManage">
									<a href="<%=path %>payManage">
										<i class="icon-double-angle-right"></i>
										付款管理
									</a>
								</li>
							
								<li id="revenueManage">
									<a href="<%=path %>revenueManage">
										<i class="icon-double-angle-right"></i>
										收款管理
									</a>
								</li>
								
								<li id="balanceManage">
									<a href="<%=path %>balanceManage">
										<i class="icon-double-angle-right"></i>
										核销管理
									</a>
								</li>
								
								<li id="settlementManage">
									<a href="<%=path %>settlementManage">
										<i class="icon-double-angle-right"></i>
										结算管理
									</a>
								</li>
								
							</ul>
						</li>

						<li id="customerManage">
							<a href="#" class="dropdown-toggle">
								<i class="icon-group"></i>
								<span class="menu-text"> 客户管理 </span>

								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li id="customerAgencyManage">
									<a href="<%=path %>customerAgencyManage">
										<i class="icon-double-angle-right"></i>
										组团社管理
									</a>
								</li>

								<li>
									<a href="jqgrid.html">
										<i class="icon-double-angle-right"></i>
										个人客户
									</a>
								</li>
							</ul>
						</li>

						<li id="supplierManage">
							<a href="#" class="dropdown-toggle">
								<i class="icon-edit"></i>
								<span class="menu-text"> 供应商管理 </span>

								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li id="supplierInfoManage">
									<a href="<%=path %>supplierInfoManage">
										<i class="icon-double-angle-right"></i>
										供应商信息管理
									</a>
								</li>
							</ul>
						</li>
						
						<li id="statisticalAnalysis">
							<a href="#" class="dropdown-toggle">
								<i class="icon-bar-chart"></i>
								<span class="menu-text"> 统计分析 </span>

								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li id="compreStat">
									<a href="<%=path %>supplierInfoManage">
										<i class="icon-double-angle-right"></i>
										综合业务统计
									</a>
								</li>
								<li id="orderStat">
									<a href="<%=path %>supplierInfoManage">
										<i class="icon-double-angle-right"></i>
										订单收支明细统计
									</a>
								</li>
								<li id="customerCompanyStat">
									<a href="<%=path %>supplierInfoManage">
										<i class="icon-double-angle-right"></i>
										公司客户业务统计
									</a>
								</li>
								<li id="customerPersonStat">
									<a href="<%=path %>supplierInfoManage">
										<i class="icon-double-angle-right"></i>
										个人客户业务统计
									</a>
								</li>
							</ul>
						</li>

						<li id="dataManage">
							<a href="#" class="dropdown-toggle">
								<i class="icon-list-alt"></i>
								<span class="menu-text"> 数据管理 </span>
								<b class="arrow icon-angle-down"></b>
							</a>
							<ul class="submenu">
								<li id="businessTypeManage">
									<a href="<%=path %>businessTypeManage">
										<i class="icon-double-angle-right"></i>
										业务类型
									</a>
								</li>

								<li id="tourTypeManage"">
									<a href="<%=path %>tourTypeManage">
										<i class="icon-double-angle-right"></i>
										团队类型
									</a>
								</li>
								
								<li id="visitorTypeManage">
									<a href="<%=path %>visitorTypeManage">
										<i class="icon-double-angle-right"></i>
										游客类型
									</a>
								</li>
								
								<li id="regionManage">
									<a href="<%=path %>regionManage">
										<i class="icon-double-angle-right"></i>
										国家地区
									</a>
								</li>
								
								<li id="supplierScopeManage">
									<a href="<%=path %>supplierScopeManage">
										<i class="icon-double-angle-right"></i>
										供应范围
									</a>
								</li>
								
								<li id="contentManage">
									<a href="<%=path %>contentManage">
										<i class="icon-double-angle-right"></i>
										供应内容
									</a>
								</li>
							</ul>
						</li>

						<li id="systemManage">
							<a href="#" class="dropdown-toggle">
								<i class="icon-cog"></i>
								<span class="menu-text"> 系统管理 </span>
								<b class="arrow icon-angle-down"></b>
							</a>
							<ul class="submenu">
								<li id="userManage">
									<a href="<%=path %>userManage">
										<i class="icon-double-angle-right"></i>
										用户管理
									</a>
								</li>

								<li id="deptManage">
									<a href="<%=path %>deptManage">
										<i class="icon-double-angle-right"></i>
										部门管理
									</a>
								</li>
								
								<li id="deptStructure">
									<a href="<%=path %>deptStructure">
										<i class="icon-double-angle-right"></i>
										部门结构
									</a>
								</li>
							</ul>
						</li>

					</ul><!-- /.nav-list -->

					<div class="sidebar-collapse" id="sidebar-collapse">
						<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
					</div>

					<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
					</script>
				</div>
