$(function(){
	/* 初始化 */
		/* 样式 */
		$("#gourpManage").addClass("open");
		$("#gourpManage").children("ul").attr("style","display:block");
		$("#localTourManage").addClass("active");
		$("#create").find("input").attr("style","width:100%;");
		$("#create").find("select").attr("style","width:100%;");
		$(".modal-dialog").attr("style","width:80%;");
		/* 提示 */
		$("a").tooltip({
			show: null,
			position: {
				my: "left top",
				at: "left bottom"
			},
			open: function( event, ui ) {
				ui.tooltip.animate({ top: ui.tooltip.position().top + 10 }, "fast" );
			}
		});
		
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
	/* 初始化选项 */
		/* 全局 */
		var selectInfo;
		/* 新建初始化 */
		var selects = $("#create").find("select");
		$.ajax({  
	        type: "GET",  
	        contentType:"application/json;charset=utf-8",  
	        url:"/localtour/localTourManage/getCreateInfo",
	        dataType: "json",  
	        async: false,  
	        success:function(data){
	        	selects.not(".traffic").html('<option value="">&nbsp;</option>');
	        	$.each(data.businessTypes,function(){
	        		$(".businessType").append('<option value="'+this.id+'">'+this.businessTypeName+'</option>');
	        	});
	        	$.each(data.tourTypes,function(){
	        		$(".tourType").append('<option value="'+this.id+'">'+this.tourTypeName+'</option>');
	        	});
	        	$.each(data.regions,function(){
	        		$(".region").append('<option value="'+this.id+'">'+this.regionName+'</option>');
	        	});
	        	$.each(data.visitorTypes,function(){
	        		$(".visitorType").append('<option value="'+this.id+'">'+this.visitorTypeName+'</option>');
	        	});
	        	$.each(data.customerAgencys,function(){
	        		$(".customerAgency").append('<option value="'+this.id+'">'+this.customerAgencyName+'</option>');
	        	});
	        	selectInfo = {flightContents : data.flightContents,
								flightSuppliers : data.flightSuppliers,
								hotelContents : data.hotelContents,
								hotelSuppliers : data.hotelSuppliers,
								mealContents : data.mealContents,
								mealSuppliers : data.mealSuppliers,
								ticketContents : data.ticketContents,
								ticketSuppliers : data.ticketSuppliers,
								shuttleContents : data.shuttleContents,
								shuttleSuppliers : data.shuttleSuppliers,
								ticketsContents : data.ticketsContents,
								ticketsSuppliers : data.ticketsSuppliers,
								comprehensiveContents : data.comprehensiveContents,
								comprehensiveSuppliers : data.comprehensiveSuppliers,
								otherContents : data.otherContents,
								otherSuppliers : data.otherSuppliers};
	        	selects.eq(0).chosen();
	        	selects.eq(1).chosen();
	        	selects.eq(2).chosen();
	        	selects.eq(3).chosen();
	        	selects.eq(4).chosen();
				$(".chosen-select").next().attr("style","width:100%;");
				$(".chosen-select").next().find("input").attr("style","height:100%;");
	        }  
		});
		/* 点击本行选中复选框 */
		$("#table").find("td").not(".sorting_1").click(function(){
			var checkbox = $(this).siblings().eq(0).find("input");
			if(checkbox.prop("checked")){
				checkbox.prop("checked",false);
			}else{
				checkbox.prop("checked",true);
			}
		});
		/* 全选 */
		$(".selectAll").click(function(){
			if($(this).prop("checked")){
				$("#table").find("input").prop("checked",true);
			}else{
				$("#table").find("input").prop("checked",false);
			}
		});
		/* 人数计算 */
		$(".counts").blur(function(){
			var sum = 0;
			var counts = $(this).parents("tbody").find(".counts");
			counts.each(function(){
				if(isNaN(parseInt($(this).val()))){
				}else{
					sum = sum + parseInt($(this).val());
				}
			});
			counts.last().parent().next().next().text(sum);
		});
		/* 导游空闲查询 */
		$("#create").delegate("#guide","click",function(){
			var td = $(this).parents("td");
			td.html("");
			td.html('<select style="display: none;" multiple="multiple" class="width-20 chosen-select" id="guides" data-placeholder="可选多个...">'+
					'<option value="">&nbsp;</option>'+
					'</select>');
			var select = $("#create").find("select").eq(5);
			var myData = {startTime:$("#datepickerStart").val(),endTime:$("#datepickerEnd").val()};
			$.ajax({  
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"/localtour/guideTimeManage/checkTime",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
	        		$.each(data,function(){
		        		select.append('<option value="'+this.guideTable.id+'">'+this.userTable.realName+'</option>');
		        	});
		        },
		        error:function(){
		        	alert("团期选择错误，无法匹配导游");
		        }
			});
			select.chosen();
    		select.next().attr("style","width:100%;");
    		select.next().find("input").attr("style","height:100%;");
    		td.find("input").focus().select();
		});
		
		$("#datepickerStart").click(function(){
			$("#create").find("#guideTd").html('<input id="guide" type="text" class="form-control" placeholder="可选多个" style="width:100%;">');
		});
		$("#datepickerEnd").click(function(){
			$("#create").find("#guideTd").html('<input id="guide" type="text" class="form-control" placeholder="可选多个" style="width:100%;">');
		});
	/* 抵离信息*/
		/* 增加 */
		$(".addArrDep").click(function(){
			var arr = $(this).parent().parent().parent().find(".arrInfo");
			var dep = $(this).parent().parent().parent().find(".departInfo");
			arr.append("<tr>"+$("#arrModel").html()+"</tr>");
			dep.append("<tr>"+$("#departModel").html()+"</tr>");
			arr.find("tr:last").find("select").chosen();
			dep.find("tr:last").find("select").chosen();
			arr.find("tr:last").find("#arrTime").attr("id","").datepicker({
				showOtherMonths: true,
				selectOtherMonths: false,
			});
			dep.find("tr:last").find("#departTime").attr("id","").datepicker({
				showOtherMonths: true,
				selectOtherMonths: false,
			});
		});
		/* 删除一行 */
		$("table").delegate(".delLine","click",function(){
			$(this).parents("tr").remove();
		});
	/* 行程初始化 */
		var tripModel = $("#tripModel").html();
		$(".trips").click(function(){
			var startTime = new Date($(this).parents("div").next().find("#datepickerStart").val());
			var endTime = new Date($(this).parents("div").next().find("#datepickerEnd").val());
			var days = (endTime-startTime)/1000/60/60/24+1;
			var ul = $(this).parent().parent().parent().next().find("#myTab3");
			var div = ul.parent().children("div");
			ul.html("");
			div.html("");
			for (var int = 0; int < days; int++) {
				var date = new Date(startTime.getTime()+1000*60*60*24*int);
				if(int==0){
					ul.append('<li class="active">'+
								'<a data-toggle="tab" href="#day'+int+'">'+
									date.getFullYear()+'/'+date.getMonth()+'/'+date.getDate()+
								'</a>'+
						  	  '</li>');
					div.append('<div id="day'+int+'" class="tab-pane in active">'+tripModel+'</div>');
				}else{
					ul.append('<li>'+
							'<a data-toggle="tab" href="#day'+int+'">'+
								date.getFullYear()+'/'+date.getMonth()+'/'+date.getDate()+
							'</a>'+
					  	  '</li>');
					div.append('<div id="day'+int+'" class="tab-pane">'+tripModel+'</div>');
				}
			}
		});
	/* 成本 */
		/* 成本小计 */
		$(".costTable").delegate(".costPlus","blur",function(){
			var product = 1;
			var costPlus = $(this).parents("tr").find(".costPlus");
			costPlus.each(function(){
				if(isNaN(parseInt($(this).val()))){
					product = product * 0;
				}else{
					product = product * parseInt($(this).val());
				}
			})
			costPlus.last().parent().next().text(product);
		});
		/* 成本增加 */
		$(".addCost").click(function(){
			var tbody = $(this).parents("table").children("tbody");
			tbody.append("<tr>"+$("#costModel").html()+"</tr>");
			var tr = tbody.children("tr").not("#costModel").last();
			tr.find("#costTime").attr("id","").datepicker({
				showOtherMonths: true,
				selectOtherMonths: false,
			});
			var selects = tr.find("select");
			var type = $(this).parents("div").attr("id");
			if(type.substr(0,type.length-1)=="flight"){
				tr.children("td").last().append('<input type="hidden" value="1" />');
				$.each(selectInfo.flightContents,function(){
	        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
	        	});
	        	$.each(selectInfo.flightSuppliers,function(){
	        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
	        	});
			}else if(type.substr(0,type.length-1)=="hotel"){
				tr.children("td").last().append('<input type="hidden" value="2" />');
				$.each(selectInfo.hotelContents,function(){
	        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
	        	});
	        	$.each(selectInfo.hotelSuppliers,function(){
	        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
	        	});
			}else if(type.substr(0,type.length-1)=="meal"){
				tr.children("td").last().append('<input type="hidden" value="3" />');
				$.each(selectInfo.mealContents,function(){
	        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
	        	});
	        	$.each(selectInfo.mealSuppliers,function(){
	        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
	        	});
			}else if(type.substr(0,type.length-1)=="ticket"){
				tr.children("td").last().append('<input type="hidden" value="4" />');
				$.each(selectInfo.ticketContents,function(){
	        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
	        	});
	        	$.each(selectInfo.ticketSuppliers,function(){
	        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
	        	});
			}else if(type.substr(0,type.length-1)=="shuttle"){
				tr.children("td").last().append('<input type="hidden" value="5" />');
				$.each(selectInfo.shuttleContents,function(){
	        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
	        	});
	        	$.each(selectInfo.shuttleSuppliers,function(){
	        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
	        	});
			}else if(type.substr(0,type.length-1)=="tickets"){
				tr.children("td").last().append('<input type="hidden" value="6" />');
				$.each(selectInfo.ticketsContents,function(){
	        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
	        	});
	        	$.each(selectInfo.ticketsSuppliers,function(){
	        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
	        	});
			}else if(type.substr(0,type.length-1)=="comprehensive"){
				tr.children("td").last().append('<input type="hidden" value="7" />');
				$.each(selectInfo.comprehensiveContents,function(){
	        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
	        	});
	        	$.each(selectInfo.comprehensiveSuppliers,function(){
	        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
	        	});
			}else if(type.substr(0,type.length-1)=="other"){
				tr.children("td").last().append('<input type="hidden" value="8" />');
				$.each(selectInfo.otherContents,function(){
	        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
	        	});
	        	$.each(selectInfo.otherSuppliers,function(){
	        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
	        	});
			}
			var guides = $(this).parents(".costTable").prev().find("#guides");
			var names = guides.find("option:selected");
			if(guides.val()!=undefined){
				for (var int = 0; int < guides.val().length; int++) {
					selects.eq(2).append('<option value="'+guides.val()[int]+'">'+names.eq(int).text()+'</option>');
				} 
			}
			selects.eq(2).append('<option value="'+'<%=((UserTable)session.getAttribute("user")).getId()%>'+'">'+'<%=((UserTable)session.getAttribute("user")).getRealName()%>'+'</option>');
			selects.chosen();
			selects.next().attr("style","width:100%;");
		});
	/* 收入 */
		$(".addIncome").click(function(){
			var tbody = $(this).parents("table").children("tbody");
			tbody.append("<tr>"+$("#incomeModel").html()+"</tr>");
			var tr = tbody.children("tr").not("#incomeModel").last();
			tr.find("#incomeTime").attr("id","").datepicker({
				showOtherMonths: true,
				selectOtherMonths: false,
			});
			/* tr.children("td").eq(1).text($(this).parents(".tab-pane").siblings().first().find("#customer").find("option:selected").text()); */
			var select = tr.children("td").eq(1).children("select");
			select.val($(this).parents(".tab-pane").siblings().first().find("#customer").val());
			select.chosen();
			select.next().attr("style","width:100%;");
			select.next().find("input").attr("style","height:100%;");
		});
	/* 删除 */
		$("#table").delegate("#delete","click",function(){
			var obj = $(this);
			var td = obj.parent();
			var myData = {id:td.attr("id")};
			$.ajax({  
		        type: "GET",
		        contentType:"application/json;charset=utf-8",
		        url:"/localtour/localTourManage/del",
		        data:myData,
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	obj.attr({"class":"green","id":"recover"});
		        	obj.children("i").attr("class","icon-undo bigger-130");
		        	td.prev().prev().text("已取消");
		        }  
			});
		}); 
	/* 恢复 */
		$("#table").delegate("#recover","click",function(){
			var obj = $(this);
			var td = obj.parent();
			var myData = {id:td.attr("id")};
			$.ajax({  
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"/localtour/localTourManage/recover",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	obj.attr({"class":"red","id":"delete"});
		        	obj.children("i").attr("class","icon-trash bigger-130");
		        	var span = obj.parents("td").prev().children("span");
		        	td.prev().prev().text("新建");
		        }  
			}); 
		});
	/* 改状态 */
		$("#table").delegate(".action","click",function(){
			var obj = $(this);
			var td = obj.parent();
			var myData = {id:td.attr("id")};
			var status = obj.children("i").attr("id");
			td.html('<i class="icon-spinner icon-spin grey bigger-130"></i>');
			$.ajax({
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"/localtour/localTourManage/chanageStatus/"+status,  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	window.location.reload();
		        }  
			});
		});
	/* 保存 */
		$("#saveNew").click(function(){
			var inputs = $("#create").find("#tourInfo").find("input");
			var selects = $("#create").find("#tourInfo").find("select");
			var tourNo = inputs.eq(0).val();
			var tourName = inputs.eq(1).val();
			var businessTypeId = selects.eq(0).val();
			var tourTypeId = selects.eq(1).val();
			var regionId = selects.eq(2).val();
			var visitorTypeId = selects.eq(3).val();
			var customerAgencyId = selects.eq(4).val();
			var organizor = inputs.eq(7).val();
			var qpGuideNo = inputs.eq(8).val();
			var adultNo = inputs.eq(9).val();
			var childrenNo = inputs.eq(10).val();
			var startTime = new Date(inputs.eq(11).val());
			var endTime = new Date(inputs.eq(12).val());
			var guideIds  = selects.eq(5).val();
			var remark = inputs.eq(14).val();
			var userId = '<%=((UserTable)session.getAttribute("user")).getId()%>';
			var deptId = '<%=((UserTable)session.getAttribute("user")).getDeptId()%>';
			var status = 0;
			var enable = true;
			var days = (endTime-startTime)/1000/60/60/24+1;
			var localTourTable={tourNo:tourNo,tourName:tourName,businessTypeId:businessTypeId,tourTypeId:tourTypeId,regionId:regionId,visitorTypeId:visitorTypeId,
						   customerAgencyId:customerAgencyId,organizor:organizor,qpGuideNo:qpGuideNo,adultNo:adultNo,childrenNo:childrenNo,startTime:startTime,
						   endTime:endTime,remark:remark,userId:userId,deptId:deptId,status:status,enable:enable};
			var guideTimeTables = new Array();
			if(guideIds!=null){
				for (var int = 0; int < guideIds.length; int++) {
					guideTimeTables.push({
							guideId:guideIds[int],
							startTime:startTime,
							endTime:endTime});
				}
			}
			var arrTrs = $("#create").find(".arrInfo").children("tr").not("#arrModel");
			var arrTables = new Array();
			for (var int = 0; int < arrTrs.length; int++) {
				var arrSelects = arrTrs.eq(int).find("select");
				var arrInputs = arrTrs.eq(int).find("input");
				if(arrSelects.eq(0).val()!=""||arrSelects.eq(1).children("option:selected").html()!="&nbsp;"||arrInputs.eq(2).val()!=""||arrInputs.eq(3).val()!=""||arrSelects.eq(2).val()!=""){
					arrTables.push({
							originId:arrSelects.eq(0).val(),
							arrTraffic:arrSelects.eq(1).children("option:selected").text(),
							arrTime:new Date(arrInputs.eq(2).val()),
							arrTrafficNo:arrInputs.eq(3).val(),
							arrRegionId:arrSelects.eq(2).val()});	
					}
				}
			
			var departTrs = $("#create").find(".departInfo").children("tr").not("#departModel");
			var departTables = new Array();
			for (var int = 0; int < departTrs.length; int++) {
				var departSelects = departTrs.eq(int).find("select");
				var departInputs = departTrs.eq(int).find("input");
				if(departSelects.eq(0).val()!=""||departSelects.eq(1).children("option:selected").html()!="&nbsp;"||departInputs.eq(2).val()!=""||departInputs.eq(3).val()!=""||departSelects.eq(2).val()!=""){
					departTables.push(
							{destId:departSelects.eq(0).val(),
							departTraffic:departSelects.eq(1).children("option:selected").text(),
							departTime:new Date(departInputs.eq(2).val()),
							departTrafficNo:departInputs.eq(3).val(),
							departRegionId:departSelects.eq(2).val()});
				}
			}
			
			var tripTables = new Array();
			var tripsTbody = $("#create").find("#trips").find("div").not("#tripModel").children("table").children("tbody");
			for (var int = 0; int < tripsTbody.length; int++) {
				var tripInputs =  tripsTbody.eq(int).find("input");
				var tripTextAreas =  tripsTbody.eq(int).find("textarea");
				if(tripTextAreas.eq(0).val()!=""||tripInputs.eq(0).val()!=""||tripInputs.eq(1).val()!=""||tripInputs.eq(2).val()!=""||tripTextAreas.eq(1).val()!=""){
					tripTables.push({
						number:int,
						trip:tripTextAreas.eq(0).val(),
						meal:tripInputs.eq(0).val(),
						stay:tripInputs.eq(1).val(),
						traffic:tripInputs.eq(2).val(),
						remark:tripTextAreas.eq(1).val()});
				}
			}
			
			var costTables = new Array();
			var costTrs = $("#create").find("#costs").find("tbody").find("tr").not("#costModel");
			for (var int = 0; int < costTrs.length; int++) {
				var costInputs = costTrs.eq(int).find("input");
				var costSelects = costTrs.eq(int).find("select");
				costTables.push({costDate:new Date(costInputs.eq(0).val()),
						contentId:costSelects.eq(0).val(),
						supplierId:costSelects.eq(1).val(),
						cost:costInputs.eq(3).val(),
						count:costInputs.eq(4).val(),
						days:costInputs.eq(5).val(),
						borrowUserId:costSelects.eq(2).val(),
						supplierScopeId:costInputs.last().val(),
						remark:costInputs.eq(7).val()});
			}
			
			var incomeTables = new Array();
			var incomeTrs = $("#create").find("#incomes").find("tbody").find("tr").not("#incomeModel");
			for (var int = 0; int < incomeTrs.length; int++) {
				var incomeInputs = incomeTrs.eq(int).find("input");
				incomeTables.push({
					incomeDate:new Date(incomeInputs.eq(0).val()),
					customerAgencyId:incomeTrs.eq(int).find("select").val(),
					income:incomeInputs.eq(1).val(),
					remark:incomeInputs.eq(2).val()});
			}
			
			var fullLocalTourViewModel = {localTourTable:localTourTable,guideTimeTables:guideTimeTables,arrTables:arrTables,departTables:departTables,tripTables:tripTables,costTables:costTables,incomeTables:incomeTables};
			var myData = JSON.stringify(fullLocalTourViewModel);
			$.ajax({
		        type: "POST",  
		        contentType:"application/json;charset=utf-8",  
		        url:"/localtour/localTourManage/save",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	if(data>0){
		        		$("#table").prepend('<tr>'+
						        				'<td class="center  sorting_1">'+
													'<label>'+
														'<input class="ace" type="checkbox">'+
														'<span class="lbl"></span>'+
													'</label>'+
												'</td>'+
												'<td><a id="findTour" role="button" data-toggle="modal" href="#find">'+tourNo+'</a></td>'+
												'<td>'+tourName+'</td>'+
												'<td>'+adultNo+'</td>'+
												'<td>'+childrenNo+'</td>'+
												'<td>'+days+'</td>'+
												'<td>'+startTime.getFullYear()+'-'+startTime.getMonth()+'-'+startTime.getDate()+'</td>'+
												'<td>'+endTime.getFullYear()+'-'+endTime.getMonth()+'-'+endTime.getDate()+'</td>'+
												'<td>新建</td>'+
												'<td>'+'<%=((UserTable)session.getAttribute("user")).getRealName()%>'+'</td>'+
												'<td id="'+data+'">'+
													'<a id="editTour" class="green" data-toggle="modal" href="#edit" title="编辑">'+
														'<i class="icon-pencil bigger-130"></i>'+
													'</a>'+
													'<a class="pink action" id="auditing" href="#" title="提交审核" style="margin-left:3px;">'+
														'<i id="1" class="icon-hand-right bigger-130"></i>'+
													'</a>'+
													'<a title="取消团队" class="red" id="delete" href="#" style="margin-left:3px;">'+
														'<i class="icon-trash bigger-130"></i>'+
														
													'</a>'+
												'</td>'+
		        							'</tr>');
		        	}else{
		        		alert("请检查团号是否重复，*号为必填项");
		        	}
		        }  
			 });
		});
	/* 查看 */
		$("#table").delegate("#findTour","click",function(){
			if($(this).parent().prev().find("input").prop("checked")){
 				$(this).parent().prev().find("input").prop("checked",false);
 			}else{
 				$(this).parent().prev().find("input").prop("checked",true);
 			}
			var myData = {tourId:$(this).parent().siblings().last().attr("id")};
			$.ajax({
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"/localtour/localTourManage/find",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	var td = $("#find").find(".tourInfo");
		        	td.eq(0).text(data.localTourTable.tourNo);
		        	td.eq(1).text(data.localTourTable.tourName);
		        	td.eq(2).text(data.tourInfo.businessTypeName);
		        	td.eq(3).text(data.tourInfo.tourTypeName);
		        	td.eq(4).text(data.tourInfo.regionName);
		        	td.eq(5).text(data.tourInfo.visitorTypeName);
		        	td.eq(6).text(data.tourInfo.customerAgencyName);
		        	td.eq(7).text(data.localTourTable.organizor);
		        	td.eq(8).text(data.localTourTable.qpGuideNo);
		        	td.eq(9).text(data.localTourTable.adultNo);
		        	td.eq(10).text(data.localTourTable.childrenNo);
		        	td.eq(11).text(data.localTourTable.qpGuideNo+data.localTourTable.adultNo+data.localTourTable.childrenNo);
		        	td.eq(12).text(data.localTourTable.startTime);
		        	td.eq(13).text(data.localTourTable.endTime);
		        	td.eq(14).text("");
		        	$.each(data.guideTimes,function(index,guideTime){
		        		if(index==0){
		        			td.eq(14).append(guideTime.realName);
		        		}else{
		        			td.eq(14).append("，"+guideTime.realName);
		        		}
		        	});
		        	td.eq(15).text(data.localTourTable.remark);
		        	var arrInfo = $("#find").find(".arrInfo");
		        	arrInfo.html("");
		        	$.each(data.arrs,function(){
		        		arrInfo.append('<tr>'+
		        							'<td>'+this.origin+'</td>'+
		        							'<td>'+this.arrTable.arrTraffic+'</td>'+
		        							'<td>'+this.arrTable.arrTime+'</td>'+
		        							'<td>'+this.arrTable.arrTrafficNo+'</td>'+
		        							'<td>'+this.arrRegion+'</td>'+
		        						'</tr>');
		        	});
		        	var departInfo = $("#find").find(".departInfo");
		        	departInfo.html("");
		        	$.each(data.departs,function(){
		        		departInfo.append('<tr>'+
		        							'<td>'+this.dest+'</td>'+
		        							'<td>'+this.departTable.departTraffic+'</td>'+
		        							'<td>'+this.departTable.departTime+'</td>'+
		        							'<td>'+this.departTable.departTrafficNo+'</td>'+
		        							'<td>'+this.departRegion+'</td>'+
		        						'</tr>');
		        	});
		        	var startTime = new Date(data.localTourTable.startTime);
					var endTime = new Date(data.localTourTable.endTime);
					var days = (endTime-startTime)/1000/60/60/24+1;
					var ul = $("#find").find("#myTab3");
					var div = ul.parent().children("div");
					var tripModel = $("#find").find("#tripModel");
					ul.html("");
					div.html('<div id="tripModel" style="display:none;">'+tripModel.html()+'</div>');
		        	for (var int = 0; int < days; int++) {
						var date = new Date(startTime.getTime()+1000*60*60*24*int);
						if(int==0){
							ul.append('<li class="active">'+
										'<a data-toggle="tab" href="#day'+int+'">'+
											date.getFullYear()+'/'+date.getMonth()+'/'+date.getDate()+
										'</a>'+
								  	  '</li>');
							var flag = false;
							$.each(data.tripTables,function(){
								if(this.number==int){
									td.eq(16).text(this.trip);
					        		td.eq(17).text(this.meal);
					        		td.eq(18).text(this.stay);
					        		td.eq(19).text(this.traffic);
					        		td.eq(20).text(this.remark);
					        		div.append('<div id="day'+this.number+'" class="tab-pane in active">'+tripModel.html()+'</div>');
					        		flag=true;
								}
							});
							if(flag==false){
								td.eq(16).text("");
				        		td.eq(17).text("");
				        		td.eq(18).text("");
				        		td.eq(19).text("");
				        		td.eq(20).text("");
				        		div.append('<div id="day'+int+'" class="tab-pane in active">'+tripModel.html()+'</div>');
							}
						}else{
							ul.append('<li>'+
									'<a data-toggle="tab" href="#day'+int+'">'+
										date.getFullYear()+'/'+date.getMonth()+'/'+date.getDate()+
									'</a>'+
							  	  '</li>');
							var flag = false;
							$.each(data.tripTables,function(){
								if(this.number==int){
									td.eq(16).text(this.trip);
					        		td.eq(17).text(this.meal);
					        		td.eq(18).text(this.stay);
					        		td.eq(19).text(this.traffic);
					        		td.eq(20).text(this.remark);
					        		div.append('<div id="day'+this.number+'" class="tab-pane">'+tripModel.html()+'</div>');
					        		flag=true;
								}
							});
							if(flag==false){
								td.eq(16).text("");
				        		td.eq(17).text("");
				        		td.eq(18).text("");
				        		td.eq(19).text("");
				        		td.eq(20).text("");
				        		div.append('<div id="day'+int+'" class="tab-pane">'+tripModel.html()+'</div>');
							}
						}
					}
		        	var flight = $("#flight2").find("tbody");
		        	var hotel = $("#hotel2").find("tbody");
		        	var meal = $("#meal2").find("tbody");
		        	var ticket = $("#ticket2").find("tbody");
		        	var shuttle = $("#shuttle2").find("tbody");
		        	var tickets = $("#tickets2").find("tbody");
		        	var comprehensive = $("#comprehensive2").find("tbody");
		        	var other = $("#other2").find("tbody");
		        	flight.html("");
		        	hotel.html("");
		        	meal.html("");
		        	ticket.html("");
		        	shuttle.html("");
		        	tickets.html("");
		        	comprehensive.html("");
		        	other.html("");
		        	$.each(data.costs,function(){
		        		if(this.costTable.supplierScopeId==1){
		        			flight.append('<tr>'+
		        								'<td>'+this.costTable.costDate+'</td>'+
		        								'<td>'+this.contentName+'</td>'+
		        								'<td>'+this.supplierName+'</td>'+
		        								'<td>'+this.costTable.cost+'</td>'+
		        								'<td>'+this.costTable.count+'</td>'+
		        								'<td>'+this.costTable.days+'</td>'+
		        								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
		        								'<td>'+this.borrowUserName+'</td>'+
		        								'<td>'+this.costTable.remark+'</td>'+
		        					'</tr>');
		        		}else if(this.costTable.supplierScopeId==2){
		        			hotel.append('<tr>'+
			    								'<td>'+this.costTable.costDate+'</td>'+
			    								'<td>'+this.contentName+'</td>'+
			    								'<td>'+this.supplierName+'</td>'+
			    								'<td>'+this.costTable.cost+'</td>'+
			    								'<td>'+this.costTable.count+'</td>'+
			    								'<td>'+this.costTable.days+'</td>'+
			    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
			    								'<td>'+this.borrowUserName+'</td>'+
			    								'<td>'+this.costTable.remark+'</td>'+
			    					'</tr>');
		        		}else if(this.costTable.supplierScopeId==3){
		        			meal.append('<tr>'+
			    								'<td>'+this.costTable.costDate+'</td>'+
			    								'<td>'+this.contentName+'</td>'+
			    								'<td>'+this.supplierName+'</td>'+
			    								'<td>'+this.costTable.cost+'</td>'+
			    								'<td>'+this.costTable.count+'</td>'+
			    								'<td>'+this.costTable.days+'</td>'+
			    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
			    								'<td>'+this.borrowUserName+'</td>'+
			    								'<td>'+this.costTable.remark+'</td>'+
			    					'</tr>');		        			
		        		}else if(this.costTable.supplierScopeId==4){
		        			ticket.append('<tr>'+
			    								'<td>'+this.costTable.costDate+'</td>'+
			    								'<td>'+this.contentName+'</td>'+
			    								'<td>'+this.supplierName+'</td>'+
			    								'<td>'+this.costTable.cost+'</td>'+
			    								'<td>'+this.costTable.count+'</td>'+
			    								'<td>'+this.costTable.days+'</td>'+
			    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
			    								'<td>'+this.borrowUserName+'</td>'+
			    								'<td>'+this.costTable.remark+'</td>'+
			    					'</tr>');		        			
		        		}else if(this.costTable.supplierScopeId==5){
		        			shuttle.append('<tr>'+
			    								'<td>'+this.costTable.costDate+'</td>'+
			    								'<td>'+this.contentName+'</td>'+
			    								'<td>'+this.supplierName+'</td>'+
			    								'<td>'+this.costTable.cost+'</td>'+
			    								'<td>'+this.costTable.count+'</td>'+
			    								'<td>'+this.costTable.days+'</td>'+
			    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
			    								'<td>'+this.borrowUserName+'</td>'+
			    								'<td>'+this.costTable.remark+'</td>'+
			    					'</tr>');	
		        		}else if(this.costTable.supplierScopeId==6){
		        			tickets.append('<tr>'+
			    								'<td>'+this.costTable.costDate+'</td>'+
			    								'<td>'+this.contentName+'</td>'+
			    								'<td>'+this.supplierName+'</td>'+
			    								'<td>'+this.costTable.cost+'</td>'+
			    								'<td>'+this.costTable.count+'</td>'+
			    								'<td>'+this.costTable.days+'</td>'+
			    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
			    								'<td>'+this.borrowUserName+'</td>'+
			    								'<td>'+this.costTable.remark+'</td>'+
			    					'</tr>');
		        		}else if(this.costTable.supplierScopeId==7){
		        			comprehensive.append('<tr>'+
			    								'<td>'+this.costTable.costDate+'</td>'+
			    								'<td>'+this.contentName+'</td>'+
			    								'<td>'+this.supplierName+'</td>'+
			    								'<td>'+this.costTable.cost+'</td>'+
			    								'<td>'+this.costTable.count+'</td>'+
			    								'<td>'+this.costTable.days+'</td>'+
			    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
			    								'<td>'+this.borrowUserName+'</td>'+
			    								'<td>'+this.costTable.remark+'</td>'+
			    					'</tr>');
		        		}else if(this.costTable.supplierScopeId==8){
		        			other.append('<tr>'+
			    								'<td>'+this.costTable.costDate+'</td>'+
			    								'<td>'+this.contentName+'</td>'+
			    								'<td>'+this.supplierName+'</td>'+
			    								'<td>'+this.costTable.cost+'</td>'+
			    								'<td>'+this.costTable.count+'</td>'+
			    								'<td>'+this.costTable.days+'</td>'+
			    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
			    								'<td>'+this.borrowUserName+'</td>'+
			    								'<td>'+this.costTable.remark+'</td>'+
			    					'</tr>');
		        		}
		        	});
		        	
		        	if(data.changeCosts.length > 0){
		        		$("#changeCostBlue").attr("style","");
		        	}else{
		        		$("#changeCostBlue").attr("style","display:none");
		        	}
		        	if(data.changeIncomes.length > 0){
		        		$("#changeIncomeBlue").attr("style","");
		        	}else{
		        		$("#changeIncomeBlue").attr("style","display:none");
		        	}
		        	$.each(data.changeCosts,function(){
		        		if(this.costTable.supplierScopeId==1){
		        			flight.append('<tr class="blue">'+
		        								'<td>'+this.costTable.costDate+'</td>'+
		        								'<td>'+this.contentName+'</td>'+
		        								'<td>'+this.supplierName+'</td>'+
		        								'<td>'+this.costTable.cost+'</td>'+
		        								'<td>'+this.costTable.count+'</td>'+
		        								'<td>'+this.costTable.days+'</td>'+
		        								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
		        								'<td>'+this.borrowUserName+'</td>'+
		        								'<td>'+this.costTable.remark+'</td>'+
		        					'</tr>');
		        		}else if(this.costTable.supplierScopeId==2){
		        			hotel.append('<tr>'+
			    								'<td>'+this.costTable.costDate+'</td>'+
			    								'<td>'+this.contentName+'</td>'+
			    								'<td>'+this.supplierName+'</td>'+
			    								'<td>'+this.costTable.cost+'</td>'+
			    								'<td>'+this.costTable.count+'</td>'+
			    								'<td>'+this.costTable.days+'</td>'+
			    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
			    								'<td>'+this.borrowUserName+'</td>'+
			    								'<td>'+this.costTable.remark+'</td>'+
			    					'</tr>');
		        		}else if(this.costTable.supplierScopeId==3){
		        			meal.append('<tr>'+
			    								'<td>'+this.costTable.costDate+'</td>'+
			    								'<td>'+this.contentName+'</td>'+
			    								'<td>'+this.supplierName+'</td>'+
			    								'<td>'+this.costTable.cost+'</td>'+
			    								'<td>'+this.costTable.count+'</td>'+
			    								'<td>'+this.costTable.days+'</td>'+
			    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
			    								'<td>'+this.borrowUserName+'</td>'+
			    								'<td>'+this.costTable.remark+'</td>'+
			    					'</tr>');		        			
		        		}else if(this.costTable.supplierScopeId==4){
		        			ticket.append('<tr>'+
			    								'<td>'+this.costTable.costDate+'</td>'+
			    								'<td>'+this.contentName+'</td>'+
			    								'<td>'+this.supplierName+'</td>'+
			    								'<td>'+this.costTable.cost+'</td>'+
			    								'<td>'+this.costTable.count+'</td>'+
			    								'<td>'+this.costTable.days+'</td>'+
			    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
			    								'<td>'+this.borrowUserName+'</td>'+
			    								'<td>'+this.costTable.remark+'</td>'+
			    					'</tr>');		        			
		        		}else if(this.costTable.supplierScopeId==5){
		        			shuttle.append('<tr>'+
			    								'<td>'+this.costTable.costDate+'</td>'+
			    								'<td>'+this.contentName+'</td>'+
			    								'<td>'+this.supplierName+'</td>'+
			    								'<td>'+this.costTable.cost+'</td>'+
			    								'<td>'+this.costTable.count+'</td>'+
			    								'<td>'+this.costTable.days+'</td>'+
			    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
			    								'<td>'+this.borrowUserName+'</td>'+
			    								'<td>'+this.costTable.remark+'</td>'+
			    					'</tr>');	
		        		}else if(this.costTable.supplierScopeId==6){
		        			tickets.append('<tr>'+
			    								'<td>'+this.costTable.costDate+'</td>'+
			    								'<td>'+this.contentName+'</td>'+
			    								'<td>'+this.supplierName+'</td>'+
			    								'<td>'+this.costTable.cost+'</td>'+
			    								'<td>'+this.costTable.count+'</td>'+
			    								'<td>'+this.costTable.days+'</td>'+
			    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
			    								'<td>'+this.borrowUserName+'</td>'+
			    								'<td>'+this.costTable.remark+'</td>'+
			    					'</tr>');
		        		}else if(this.costTable.supplierScopeId==7){
		        			comprehensive.append('<tr>'+
			    								'<td>'+this.costTable.costDate+'</td>'+
			    								'<td>'+this.contentName+'</td>'+
			    								'<td>'+this.supplierName+'</td>'+
			    								'<td>'+this.costTable.cost+'</td>'+
			    								'<td>'+this.costTable.count+'</td>'+
			    								'<td>'+this.costTable.days+'</td>'+
			    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
			    								'<td>'+this.borrowUserName+'</td>'+
			    								'<td>'+this.costTable.remark+'</td>'+
			    					'</tr>');
		        		}else if(this.costTable.supplierScopeId==8){
		        			other.append('<tr>'+
			    								'<td>'+this.costTable.costDate+'</td>'+
			    								'<td>'+this.contentName+'</td>'+
			    								'<td>'+this.supplierName+'</td>'+
			    								'<td>'+this.costTable.cost+'</td>'+
			    								'<td>'+this.costTable.count+'</td>'+
			    								'<td>'+this.costTable.days+'</td>'+
			    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
			    								'<td>'+this.borrowUserName+'</td>'+
			    								'<td>'+this.costTable.remark+'</td>'+
			    					'</tr>');
		        		}
		        	});
		        	var tbody = $("#incomes2").find("tbody");
		        	tbody.html("");
		        	$.each(data.incomes,function(){
		        		var realIncome = this.incomeTable.realIncome==null?0:this.incomeTable.realIncome;
		        		tbody.append('<tr>'+
		        							'<td>'+this.incomeTable.incomeDate+'</td>'+
		        							'<td>'+this.customerAgencyName+'</td>'+
		        							'<td>'+this.incomeTable.income+'</td>'+
		        							'<td>'+realIncome+'</td>'+
		        							'<td>'+this.invoiceAmount+'</td>'+
		        							'<td>'+this.incomeTable.remark+'</td>'+
		        					'</tr>');
		        	});
		        	$.each(data.changeIncomes,function(){
		        		var realIncome = this.incomeTable.realIncome==null?0:this.incomeTable.realIncome;
		        		tbody.append('<tr class="blue">'+
		        							'<td>'+this.incomeTable.incomeDate+'</td>'+
		        							'<td>'+this.customerAgencyName+'</td>'+
		        							'<td>'+this.incomeTable.income+'</td>'+
		        							'<td>'+realIncome+'</td>'+
		        							'<td>'+this.invoiceAmount+'</td>'+
		        							'<td>'+this.incomeTable.remark+'</td>'+
		        					'</tr>');
		        	});
		        }  
			});
		});
	/* 编辑 */
		/* 点击日期删除导游 */
		$("#edit").find(".datepicker").click(function(){
			$("#edit").find("#guideTd").html('<input id="guide" type="text" class="form-control" placeholder="可选多个" style="width:100%;">');
			var myData = {tourId:$("#saveEdit").parent().attr("id")};
			$.ajax({
		        type: "GET",
		        contentType:"application/json;charset=utf-8",  
		        url:"/localtour/guideTimeManage/delete",  
		        data:myData,  
		        dataType: "json",
		        async: false,  
		        success:function(data){
	        		
		        }
			});
		});
		/* 选择导游 */
		$("#edit").delegate("#guide","click",function(){
			var td = $(this).parents("td");
			td.html("");
			td.html('<select style="display: none;" multiple="multiple" class="width-20 chosen-select" id="guides" data-placeholder="可选多个...">'+
						'<option value="">&nbsp;</option>'+
					'</select>');
			var select = $("#edit").find("select").eq(5);
			var myData = {startTime:td.siblings().eq(1).children("input").val(),endTime:td.siblings().eq(3).children("input").val()};
			$.ajax({
		        type: "GET",
		        contentType:"application/json;charset=utf-8",  
		        url:"/localtour/guideTimeManage/checkTime",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
	        		$.each(data,function(){
		        		select.append('<option value="'+this.guideTable.id+'">'+this.userTable.realName+'</option>');
		        	});
		        },
		        error:function(){
		        	alert("团期选择错误，无法匹配导游");
		        }
			});
			select.chosen();
    		select.next().attr("style","width:100%;");
    		select.next().find("input").attr("style","height:100%;");
    		td.find("input").focus().select();
		});
		/* 编辑读取 */
 		$("#table").delegate("#editTour","click",function(){
			var myData = {tourId:$(this).parent().attr("id")};
			$("#saveEdit").parent().attr("id",myData.tourId);
			var tourUserName = $(this).parent().prev().text();
			$.ajax({
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"/localtour/localTourManage/find",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	/* 全局数据 */
		        	editInfo = data;
		        	/* 设置团队 */
		        	var selects = $("#edit").find("select");
		        	var td = $("#edit").find(".tourInfo");
		        	td.eq(0).children("input").val(data.localTourTable.tourNo);
		        	td.eq(1).children("input").val(data.localTourTable.tourName);
		        	
		        	var businessType = $("<select></select>");
		        	businessType.html(td.eq(2).children("select").html());
		        	businessType.attr({"style":"display: none;","class":"width-20 chosen-select form-control visitorType","data-placeholder":"Choose a Country..."});
		        	td.eq(2).html("").append(businessType.val(data.localTourTable.businessTypeId));
		        	
		        	var tourType = $("<select></select>");
		        	tourType.html(td.eq(3).children("select").html());
		        	tourType.attr({"style":"display: none;","class":"width-20 chosen-select form-control visitorType","data-placeholder":"Choose a Country..."});
		        	td.eq(3).html("").append(tourType.val(data.localTourTable.tourTypeId));
		        	
		        	var region = $("<select></select>");
		        	region.html(td.eq(4).children("select").html());
		        	region.attr({"style":"display: none;","class":"width-20 chosen-select form-control visitorType","data-placeholder":"Choose a Country..."});
		        	td.eq(4).html("").append(region.val(data.localTourTable.regionId));
		        	
		        	var visitorType = $("<select></select>");
		        	visitorType.html(td.eq(5).children("select").html());
		        	visitorType.attr({"style":"display: none;","class":"width-20 chosen-select form-control visitorType","data-placeholder":"Choose a Country..."});
		        	td.eq(5).html("").append(visitorType.val(data.localTourTable.visitorTypeId));
		        	
		        	var customerAgency = $("<select></select>");
		        	customerAgency.html(td.eq(6).children("select").html());
		        	customerAgency.attr({"id":"customer","style":"display: none;","class":"width-20 chosen-select form-control visitorType","data-placeholder":"Choose a Country..."});
		        	td.eq(6).html("").append(customerAgency.val(data.localTourTable.customerAgencyId));
		        	
		        	td.eq(7).children("input").val(data.localTourTable.organizor);
		        	td.eq(8).children("input").val(data.localTourTable.qpGuideNo);
		        	td.eq(9).children("input").val(data.localTourTable.adultNo);
		        	td.eq(10).children("input").val(data.localTourTable.childrenNo);
		        	td.eq(11).text(data.localTourTable.qpGuideNo+data.localTourTable.adultNo+data.localTourTable.childrenNo);
		        	td.eq(12).children("input").val((data.localTourTable.startTime).replace(/-/g,"/"));
		        	td.eq(13).children("input").val((data.localTourTable.endTime).replace(/-/g,"/"));
		        	td.eq(15).children("input").val(data.localTourTable.remark);
		        	/* 设置导游 */
		        	var select = td.eq(14).children("select");
					var myData = {startTime:(data.localTourTable.startTime).replace(/-/g,"/"),endTime:(data.localTourTable.endTime).replace(/-/g,"/")};
					$.ajax({
				        type: "GET",  
				        contentType:"application/json;charset=utf-8",  
				        url:"/localtour/guideTimeManage/checkTime",  
				        data:myData,  
				        dataType: "json",  
				        async: false,  
				        success:function(guides){
				        	var guideIds = new Array();
							$.each(data.guideTimes,function(index,guideTime){
								select.append('<option value="'+this.guideId+'">'+this.realName+'</option>');
								guideIds[index] = this.guideId;
				        	});
			        		$.each(guides,function(){
				        		select.append('<option value="'+this.guideTable.id+'">'+this.userTable.realName+'</option>');
				        	});
			        		select.val(guideIds);
				        },
				        error:function(){
				        	alert("团期选择错误，无法匹配导游");
				        }
					});
					/* 设置抵离信息 */
		        	var arrInfo = $("#edit").find(".arrInfo");
		        	arrInfo.html("");
		        	$.each(data.arrs,function(index){
		        		var tr = $("<tr></tr>");
		        		tr.html($("#arrModel").html());
		        		var selects = tr.find("select");
		        		var input = tr.find("input");
		        		selects.eq(0).val(this.arrTable.originId);
		        		selects.eq(1).val(this.arrTable.arrTraffic);
		        		input.eq(0).val(this.arrTable.arrTime.replace(/-/g,"/"));
		        		input.eq(1).val(this.arrTable.arrTrafficNo);
		        		selects.eq(2).val(this.arrTable.arrRegionId);
		        		tr.children("td").last().attr("id",this.arrTable.id);
		        		arrInfo.append(tr);
		        		$("#edit").find("#arrTime").attr("id","");
		        	});
		        	
		        	var departInfo = $("#edit").find(".departInfo");
		        	departInfo.html("");
		        	$.each(data.departs,function(index){
		        		var tr = $("<tr></tr>");
		        		tr.html($("#departModel").html());
		        		var selects = tr.find("select");
		        		var input = tr.find("input");
		        		selects.eq(0).val(this.departTable.destId);
		        		selects.eq(1).val(this.departTable.departTraffic);
		        		input.eq(0).val(this.departTable.departTime.replace(/-/g,"/"));
		        		input.eq(1).val(this.departTable.departTrafficNo);
		        		selects.eq(2).val(this.departTable.departRegionId);
		        		tr.children("td").last().attr("id",this.departTable.id);
		        		departInfo.append(tr);
		        		$("#edit").find("#departTime").attr("id","");
		        	});
					
					/* 设置成本 */
		        	var flight = $("#flight3").find("tbody");
		        	var hotel = $("#hotel3").find("tbody");
		        	var meal = $("#meal3").find("tbody");
		        	var ticket = $("#ticket3").find("tbody");
		        	var shuttle = $("#shuttle3").find("tbody");
		        	var tickets = $("#tickets3").find("tbody");
		        	var comprehensive = $("#comprehensive3").find("tbody");
		        	var other = $("#other3").find("tbody");
		        	flight.html("");
		        	hotel.html("");
		        	meal.html("");
		        	ticket.html("");
		        	shuttle.html("");
		        	tickets.html("");
		        	comprehensive.html("");
		        	other.html("");
		        	$.each(data.costs,function(){
		        		var guides = $("#edit").find("#guides");
	    				var names = guides.find("option:selected");
	    				var borrowUser = $('<select style="width:100%;" class="width-20 chosen-select"><option value="">&nbsp;</option></select>');
	    				if(guides.val()!=undefined){
	    					for (var int = 0; int < guides.val().length; int++) {
	    						borrowUser.append('<option value="'+guides.val()[int]+'">'+names.eq(int).text()+'</option>');
	    					} 
	    				}
	    				borrowUser.append('<option value="'+data.localTourTable.userId+'">'+tourUserName+'</option>');
		        		if(this.costTable.supplierScopeId==1){
		        			flight.append('<tr>'+
			    								'<td><input value="'+this.costTable.costDate.replace(/-/g,'/')+'" style="width:100%;" class="datepicker form-control" type="text" /></td>'+
			    								'<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>'+
			    								'<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>'+
			    								'<td><input value="'+this.costTable.cost+'" style="width:100%;" class="form-control" type="text" /></td>'+
			    								'<td><input value="'+this.costTable.count+'" style="width:100%;" class="form-control" type="text" /></td>'+
			    								'<td><input value="'+this.costTable.days+'" style="width:100%;" class="form-control" type="text" /></td>'+
			    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
			    								'<td></td>'+
			    								'<td><input value="'+this.costTable.remark+'" style="width:100%;" class="form-control" type="text" /></td>'+
			    								'<td id="'+this.costTable.id+'"style="vertical-align: middle;"><a class="red delLine" href="#"><i class="icon-trash bigger-130"></i></a><input type="hidden" value="1" /></td>'+
			    					'</tr>');
			    			flight.find("tr").last().children("td").eq(7).append(borrowUser);
							var selects = flight.find("tr").last().find("select");
							$.each(selectInfo.flightContents,function(){
				        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
				        	});
				        	$.each(selectInfo.flightSuppliers,function(){
				        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
				        	});
				        	selects.eq(0).val(this.costTable.contentId);
				        	selects.eq(1).val(this.costTable.supplierId);
				        	selects.eq(2).val(this.costTable.borrowUserId);
		        		}else if(this.costTable.supplierScopeId==2){
		        			hotel.append('<tr>'+
			    								'<td><input value="'+this.costTable.costDate.replace(/-/g,'/')+'" style="width:100%;" class="datepicker form-control" type="text" /></td>'+
			    								'<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>'+
			    								'<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>'+
			    								'<td><input value="'+this.costTable.cost+'" style="width:100%;" class="form-control" type="text" /></td>'+
			    								'<td><input value="'+this.costTable.count+'" style="width:100%;" class="form-control" type="text" /></td>'+
			    								'<td><input value="'+this.costTable.days+'" style="width:100%;" class="form-control" type="text" /></td>'+
			    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
			    								'<td></td>'+
			    								'<td><input value="'+this.costTable.remark+'" style="width:100%;" class="form-control" type="text" /></td>'+
			    								'<td id="'+this.costTable.id+'"style="vertical-align: middle;"><a class="red delLine" href="#"><i class="icon-trash bigger-130"></i></a><input type="hidden" value="2" /></td>'+
			    					'</tr>');
		        			hotel.find("tr").last().children("td").eq(7).append(borrowUser);
    						var selects = hotel.find("tr").last().find("select");
    						$.each(selectInfo.hotelContents,function(){
		    	        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
		    	        	});
		    	        	$.each(selectInfo.hotelSuppliers,function(){
		    	        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
		    	        	});
		    	        	selects.eq(0).val(this.costTable.contentId);
		    	        	selects.eq(1).val(this.costTable.supplierId);
		    	        	selects.eq(2).val(this.costTable.borrowUserId);
		        		}else if(this.costTable.supplierScopeId==3){
		        			meal.append('<tr>'+
			    								'<td><input value="'+this.costTable.costDate.replace(/-/g,'/')+'" style="width:100%;" class="datepicker form-control" type="text" /></td>'+
			    								'<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>'+
			    								'<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>'+
			    								'<td><input value="'+this.costTable.cost+'" style="width:100%;" class="form-control" type="text" /></td>'+
			    								'<td><input value="'+this.costTable.count+'" style="width:100%;" class="form-control" type="text" /></td>'+
			    								'<td><input value="'+this.costTable.days+'" style="width:100%;" class="form-control" type="text" /></td>'+
			    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
			    								'<td></td>'+
			    								'<td><input value="'+this.costTable.remark+'" style="width:100%;" class="form-control" type="text" /></td>'+
			    								'<td id="'+this.costTable.id+'"style="vertical-align: middle;"><a class="red delLine" href="#"><i class="icon-trash bigger-130"></i></a><input type="hidden" value="3" /></td>'+
			    					'</tr>');
			    			meal.find("tr").last().children("td").eq(7).append(borrowUser);
							var selects = meal.find("tr").last().find("select");
							$.each(selectInfo.mealContents,function(){
				        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
				        	});
				        	$.each(selectInfo.mealSuppliers,function(){
				        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
				        	});
				        	selects.eq(0).val(this.costTable.contentId);
				        	selects.eq(1).val(this.costTable.supplierId);
				        	selects.eq(2).val(this.costTable.borrowUserId);
		        		}else if(this.costTable.supplierScopeId==4){
		        			ticket.append('<tr>'+
			    								'<td><input value="'+this.costTable.costDate.replace(/-/g,'/')+'" style="width:100%;" class="datepicker form-control" type="text" /></td>'+
			    								'<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>'+
			    								'<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>'+
			    								'<td><input value="'+this.costTable.cost+'" style="width:100%;" class="form-control" type="text" /></td>'+
			    								'<td><input value="'+this.costTable.count+'" style="width:100%;" class="form-control" type="text" /></td>'+
			    								'<td><input value="'+this.costTable.days+'" style="width:100%;" class="form-control" type="text" /></td>'+
			    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
			    								'<td></td>'+
			    								'<td><input value="'+this.costTable.remark+'" style="width:100%;" class="form-control" type="text" /></td>'+
			    								'<td id="'+this.costTable.id+'"style="vertical-align: middle;"><a class="red delLine" href="#"><i class="icon-trash bigger-130"></i></a><input type="hidden" value="4" /></td>'+
			    					'</tr>');
			    			ticket.find("tr").last().children("td").eq(7).append(borrowUser);
							var selects = ticket.find("tr").last().find("select");
							$.each(selectInfo.ticketContents,function(){
				        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
				        	});
				        	$.each(selectInfo.ticketSuppliers,function(){
				        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
				        	});
				        	selects.eq(0).val(this.costTable.contentId);
				        	selects.eq(1).val(this.costTable.supplierId);
				        	selects.eq(2).val(this.costTable.borrowUserId);
		        		}else if(this.costTable.supplierScopeId==5){
		        			shuttle.append('<tr>'+
			    								'<td><input value="'+this.costTable.costDate.replace(/-/g,'/')+'" style="width:100%;" class="datepicker form-control" type="text" /></td>'+
			    								'<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>'+
			    								'<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>'+
			    								'<td><input value="'+this.costTable.cost+'" style="width:100%;" class="form-control" type="text" /></td>'+
			    								'<td><input value="'+this.costTable.count+'" style="width:100%;" class="form-control" type="text" /></td>'+
			    								'<td><input value="'+this.costTable.days+'" style="width:100%;" class="form-control" type="text" /></td>'+
			    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
			    								'<td></td>'+
			    								'<td><input value="'+this.costTable.remark+'" style="width:100%;" class="form-control" type="text" /></td>'+
			    								'<td id="'+this.costTable.id+'"style="vertical-align: middle;"><a class="red delLine" href="#"><i class="icon-trash bigger-130"></i></a><input type="hidden" value="5" /></td>'+
			    					'</tr>');
			    			shuttle.find("tr").last().children("td").eq(7).append(borrowUser);
							var selects = shuttle.find("tr").last().find("select");
							$.each(selectInfo.shuttleContents,function(){
				        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
				        	});
				        	$.each(selectInfo.shuttleSuppliers,function(){
				        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
				        	});
				        	selects.eq(0).val(this.costTable.contentId);
				        	selects.eq(1).val(this.costTable.supplierId);
				        	selects.eq(2).val(this.costTable.borrowUserId);
		        		}else if(this.costTable.supplierScopeId==6){
		        			tickets.append('<tr>'+
			    								'<td><input value="'+this.costTable.costDate.replace(/-/g,'/')+'" style="width:100%;" class="datepicker form-control" type="text" /></td>'+
			    								'<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>'+
			    								'<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>'+
			    								'<td><input value="'+this.costTable.cost+'" style="width:100%;" class="form-control" type="text" /></td>'+
			    								'<td><input value="'+this.costTable.count+'" style="width:100%;" class="form-control" type="text" /></td>'+
			    								'<td><input value="'+this.costTable.days+'" style="width:100%;" class="form-control" type="text" /></td>'+
			    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
			    								'<td></td>'+
			    								'<td><input value="'+this.costTable.remark+'" style="width:100%;" class="form-control" type="text" /></td>'+
			    								'<td id="'+this.costTable.id+'"style="vertical-align: middle;"><a class="red delLine" href="#"><i class="icon-trash bigger-130"></i></a><input type="hidden" value="6" /></td>'+
			    					'</tr>');
			    			tickets.find("tr").last().children("td").eq(7).append(borrowUser);
							var selects = tickets.find("tr").last().find("select");
							$.each(selectInfo.ticketsContents,function(){
				        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
				        	});
				        	$.each(selectInfo.ticketsSuppliers,function(){
				        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
				        	});
				        	selects.eq(0).val(this.costTable.contentId);
				        	selects.eq(1).val(this.costTable.supplierId);
				        	selects.eq(2).val(this.costTable.borrowUserId);
		        		}else if(this.costTable.supplierScopeId==7){
		        			comprehensive.append('<tr>'+
			    								'<td><input value="'+this.costTable.costDate.replace(/-/g,'/')+'" style="width:100%;" class="datepicker form-control" type="text" /></td>'+
			    								'<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>'+
			    								'<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>'+
			    								'<td><input value="'+this.costTable.cost+'" style="width:100%;" class="form-control" type="text" /></td>'+
			    								'<td><input value="'+this.costTable.count+'" style="width:100%;" class="form-control" type="text" /></td>'+
			    								'<td><input value="'+this.costTable.days+'" style="width:100%;" class="form-control" type="text" /></td>'+
			    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
			    								'<td></td>'+
			    								'<td><input value="'+this.costTable.remark+'" style="width:100%;" class="form-control" type="text" /></td>'+
			    								'<td id="'+this.costTable.id+'"style="vertical-align: middle;"><a class="red delLine" href="#"><i class="icon-trash bigger-130"></i></a><input type="hidden" value="7" /></td>'+
			    					'</tr>');
			    			comprehensive.find("tr").last().children("td").eq(7).append(borrowUser);
							var selects = comprehensive.find("tr").last().find("select");
							$.each(selectInfo.comprehensiveContents,function(){
				        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
				        	});
				        	$.each(selectInfo.comprehensiveSuppliers,function(){
				        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
				        	});
				        	selects.eq(0).val(this.costTable.contentId);
				        	selects.eq(1).val(this.costTable.supplierId);
				        	selects.eq(2).val(this.costTable.borrowUserId);
		        		}else if(this.costTable.supplierScopeId==8){
		        			other.append('<tr>'+
			    								'<td><input value="'+this.costTable.costDate.replace(/-/g,'/')+'" style="width:100%;" class="datepicker form-control" type="text" /></td>'+
			    								'<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>'+
			    								'<td><select style="width:100%;" class="width-20 chosen-select" data-placeholder="Choose a Country..."><option value="">&nbsp;</option></select></td>'+
			    								'<td><input value="'+this.costTable.cost+'" style="width:100%;" class="form-control" type="text" /></td>'+
			    								'<td><input value="'+this.costTable.count+'" style="width:100%;" class="form-control" type="text" /></td>'+
			    								'<td><input value="'+this.costTable.days+'" style="width:100%;" class="form-control" type="text" /></td>'+
			    								'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
			    								'<td></td>'+
			    								'<td><input value="'+this.costTable.remark+'" style="width:100%;" class="form-control" type="text" /></td>'+
			    								'<td id="'+this.costTable.id+'"style="vertical-align: middle;"><a class="red delLine" href="#"><i class="icon-trash bigger-130"></i></a><input type="hidden" value="8" /></td>'+
			    					'</tr>');
			    			other.find("tr").last().children("td").eq(7).append(borrowUser);
							var selects = other.find("tr").last().find("select");
							$.each(selectInfo.otherContents,function(){
				        		selects.eq(0).append('<option value="'+this.id+'">'+this.contentName+'</option>');
				        	});
				        	$.each(selectInfo.otherSuppliers,function(){
				        		selects.eq(1).append('<option value="'+this.id+'">'+this.supplierName+'</option>');
				        	});
				        	selects.eq(0).val(this.costTable.contentId);
				        	selects.eq(1).val(this.costTable.supplierId);
				        	selects.eq(2).val(this.costTable.borrowUserId);
		        		}
		        	});
		        	$("#costs3").find("tbody").removeAttr("delIds");
		        	
		        	/* 设置收入 */
		        	var tbody = $("#incomes3").find("tbody");
		        	tbody.html("");
		        	$.each(data.incomes,function(){
		        		var tr = $("<tr></tr>");
		        		tr.html($("#incomeModel").html());
		    			tbody.append(tr);
		    			var inputs = tr.find("input");
		    			var td = tr.children("td");
		    			inputs.eq(0).val(this.incomeTable.incomeDate.replace(/-/g,'/'));
		    			tr.find("select").eq(0).val(this.incomeTable.customerAgencyId);
		    			inputs.eq(1).val(this.incomeTable.income);
		    			td.eq(3).html(this.incomeTable.realIncome);
		    			td.eq(4).html(this.invoiceAmount);
		    			inputs.eq(2).val(this.incomeTable.remark);
		    			td.last().attr("id",this.incomeTable.id);
		    			tr.find("#incomeTime").attr("id","")
		        	});
		        	
		        	/* 点击编辑设置行程 */
		        	var startTime = new Date(data.localTourTable.startTime);
					var endTime = new Date(data.localTourTable.endTime);
					var days = (endTime-startTime)/1000/60/60/24+1;
					var ul = $("#edit").find("#myTab3");
					var div = ul.parent().children("div");
					var editTripModel = $("#editTripModel");
					ul.html("");
					tripDelIds = [];
					div.html('<div id="editTripModel" style="display:none;">'+editTripModel.html()+'</div>');
		        	for (var int = 0; int < days; int++) {
						var date = new Date(startTime.getTime()+1000*60*60*24*int);
						if(int==0){
							ul.append('<li class="active">'+
										'<a data-toggle="tab" href="#editDay'+int+'">'+
											date.getFullYear()+'/'+date.getMonth()+'/'+date.getDate()+
										'</a>'+
								  	  '</li>');
							var flag = false;
							$.each(editInfo.tripTables,function(){
								if(this.number==int){
									div.append('<div id="editDay'+this.number+'" class="tab-pane in active">'+editTripModel.html()+'</div>');
									tripDelIds.push(this.id);
									var divLast = div.children("div:last");
									divLast.find("tbody").attr("id",this.id);
									divLast.find("textarea").eq(0).text(this.trip);
									divLast.find("input").eq(0).attr("value",this.meal);
									divLast.find("input").eq(1).attr("value",this.stay);
									divLast.find("input").eq(2).attr("value",this.traffic);
									divLast.find("textarea").eq(1).text(this.remark);
					        		flag=true;
								}
							});
							if(flag==false){
								div.append('<div id="editDay'+int+'" class="tab-pane in active">'+editTripModel.html()+'</div>');
								var divLast = div.children("div:last");
								divLast.find("textarea").eq(0).text("");
								divLast.find("input").eq(0).val("");
								divLast.find("input").eq(1).val("");
								divLast.find("input").eq(2).val("");
								divLast.find("textarea").eq(1).text("");
							}
						}else{
							ul.append('<li>'+
									'<a data-toggle="tab" href="#editDay'+int+'">'+
										date.getFullYear()+'/'+date.getMonth()+'/'+date.getDate()+
									'</a>'+
							  	  '</li>');
							var flag = false;
							$.each(editInfo.tripTables,function(){
								if(this.number==int){
									div.append('<div id="editDay'+this.number+'" class="tab-pane">'+editTripModel.html()+'</div>');
									tripDelIds.push(this.id);
									var divLast = div.children("div:last");
									divLast.find("tbody").attr("id",this.id);
									divLast.find("textarea").eq(0).text(this.trip);
									divLast.find("input").eq(0).attr("value",this.meal);
									divLast.find("input").eq(1).attr("value",this.stay);
									divLast.find("input").eq(2).attr("value",this.traffic);
									divLast.find("textarea").eq(1).text(this.remark);
					        		flag=true;
								}
							});
							if(flag==false){
								div.append('<div id="editDay'+int+'" class="tab-pane">'+editTripModel.html()+'</div>');
								var divLast = div.children("div:last");
								divLast.find("textarea").eq(0).text("");
								divLast.find("input").eq(0).val("");
								divLast.find("input").eq(1).val("");
								divLast.find("input").eq(2).val("");
								divLast.find("textarea").eq(1).text("");
							}
						}
					}
		        	
					/* 要删除的ID */
		        	$("#edit").find(".delLine").click(function(){
		        		var parent = $(this).parent().parent().parent();
		        		if(parent.attr("delIds")==undefined){
		        			parent.attr("delIds",$(this).parent().attr("id"));
		        		}else{
		        			parent.attr("delIds",parent.attr("delIds")+","+$(this).parent().attr("id"));
		        		}
		        		
		        	});
		        	
		        	$("#edit").find(".datepicker").datepicker({
		    			showOtherMonths: true,
		    			selectOtherMonths: false,
		    		});
		        	$("#edit").find("select").chosen();
		        	$(".chosen-select").next().attr("style","width:100%;");
					$(".chosen-select").next().find("input").attr("style","height:100%;");
					$(".traffic").next().attr("style","width:100%;");
					$(".traffic").next().find("input").attr("style","height:100%;");
		        }  
			});
		});
		/* 点击行程设置行程 */
 		var editInfo;
 		$("#editTrip").click(function(){
 			tripInitialize();
 		});
 		/* 编辑点击时间设置行程 */
 		$("#tourInfo3").find("tbody").eq(0).find(".datepicker").change(function(){
 			tripInitialize();
 		});
 		function tripInitialize(){
 			var startTime = new Date($("#edit").find(".datepicker").eq(0).val());
			var endTime = new Date($("#edit").find(".datepicker").eq(1).val());
			var days = (endTime-startTime)/1000/60/60/24+1;
			var ul = $("#edit").find("#myTab3");
			var div = ul.parent().children("div");
			var editTripModel = $("#editTripModel");
			ul.html("");
			div.html('<div id="editTripModel" style="display:none;">'+editTripModel.html()+'</div>');
        	for (var int = 0; int < days; int++) {
				var date = new Date(startTime.getTime()+1000*60*60*24*int);
				if(int==0){
					ul.append('<li class="active">'+
								'<a data-toggle="tab" href="#editDay'+int+'">'+
									date.getFullYear()+'/'+date.getMonth()+'/'+date.getDate()+
								'</a>'+
						  	  '</li>');
					var flag = false;
					$.each(editInfo.tripTables,function(){
						if(this.number==int){
							div.append('<div id="editDay'+this.number+'" class="tab-pane in active">'+editTripModel.html()+'</div>');
							var divLast = div.children("div:last");
							divLast.find("tbody").attr("id",this.id);
							divLast.find("textarea").eq(0).text(this.trip);
							divLast.find("input").eq(0).attr("value",this.meal);
							divLast.find("input").eq(1).attr("value",this.stay);
							divLast.find("input").eq(2).attr("value",this.traffic);
							divLast.find("textarea").eq(1).text(this.remark);
			        		flag=true;
						}
					});
					if(flag==false){
						div.append('<div id="editDay'+int+'" class="tab-pane in active">'+editTripModel.html()+'</div>');
						var divLast = div.children("div:last");
						divLast.find("textarea").eq(0).text("");
						divLast.find("input").eq(0).val("");
						divLast.find("input").eq(1).val("");
						divLast.find("input").eq(2).val("");
						divLast.find("textarea").eq(1).text("");
					}
				}else{
					ul.append('<li>'+
							'<a data-toggle="tab" href="#editDay'+int+'">'+
								date.getFullYear()+'/'+date.getMonth()+'/'+date.getDate()+
							'</a>'+
					  	  '</li>');
					var flag = false;
					$.each(editInfo.tripTables,function(){
						if(this.number==int){
							div.append('<div id="editDay'+this.number+'" class="tab-pane">'+editTripModel.html()+'</div>');
							var divLast = div.children("div:last");
							divLast.find("tbody").attr("id",this.id);
							divLast.find("textarea").eq(0).text(this.trip);
							divLast.find("input").eq(0).attr("value",this.meal);
							divLast.find("input").eq(1).attr("value",this.stay);
							divLast.find("input").eq(2).attr("value",this.traffic);
							divLast.find("textarea").eq(1).text(this.remark);
			        		flag=true;
						}
					});
					if(flag==false){
						div.append('<div id="editDay'+int+'" class="tab-pane">'+editTripModel.html()+'</div>');
						var divLast = div.children("div:last");
						divLast.find("textarea").eq(0).text("");
						divLast.find("input").eq(0).val("");
						divLast.find("input").eq(1).val("");
						divLast.find("input").eq(2).val("");
						divLast.find("textarea").eq(1).text("");
					}
				}
			}
 		}
	/*更新 */
		/* 全局行程删除id */
		var tripDelIds = new Array();
		$("#saveEdit").click(function(){
			var inputs = $("#edit").find("#tourInfo3").find("input");
			var selects = $("#edit").find("#tourInfo3").find("select");
			var id = $(this).parent().attr("id");
			var tourNo = inputs.eq(0).val();
			var tourName = inputs.eq(1).val();
			var businessTypeId = selects.eq(0).val();
			var tourTypeId = selects.eq(1).val();
			var regionId = selects.eq(2).val();
			var visitorTypeId = selects.eq(3).val();
			var customerAgencyId = selects.eq(4).val();
			var organizor = inputs.eq(7).val();
			var qpGuideNo = inputs.eq(8).val();
			var adultNo = inputs.eq(9).val();
			var childrenNo = inputs.eq(10).val();
			var startTime = new Date(inputs.eq(11).val());
			var endTime = new Date(inputs.eq(12).val());
			var guideIds  = selects.eq(5).val();
			var remark = inputs.eq(14).val();
			var days = (endTime-startTime)/1000/60/60/24+1;
			var localTourTable={id:id,tourNo:tourNo,tourName:tourName,businessTypeId:businessTypeId,tourTypeId:tourTypeId,regionId:regionId,visitorTypeId:visitorTypeId,
						   customerAgencyId:customerAgencyId,organizor:organizor,qpGuideNo:qpGuideNo,adultNo:adultNo,childrenNo:childrenNo,startTime:startTime,
						   endTime:endTime,remark:remark};
			
			var guideTimeTables = new Array();
			if(guideIds!=null){
				for (var int = 0; int < guideIds.length; int++) {
					guideTimeTables.push({
							tourId:id,
							guideId:guideIds[int],
							startTime:startTime,
							endTime:endTime});
				}
			}
			
			var arrTrs = $("#edit").find(".arrInfo").children("tr").not("#arrModel");
			var arrTables = new Array();
			for (var int = 0; int < arrTrs.length; int++) {
				var arrSelects = arrTrs.eq(int).find("select");
				var arrInputs = arrTrs.eq(int).find("input");
				if(arrSelects.eq(0).val()!=""||arrSelects.eq(1).children("option:selected").html()!="&nbsp;"||arrInputs.eq(2).val()!=""||arrInputs.eq(3).val()!=""||arrSelects.eq(2).val()!=""){
					arrTables.push({
						id:arrTrs.eq(int).find("td").last().attr("id"),
						tourId:id,
						originId:arrSelects.eq(0).val(),
						arrTraffic:arrSelects.eq(1).children("option:selected").text(),
						arrTime:new Date(arrInputs.eq(2).val()),
						arrTrafficNo:arrInputs.eq(3).val(),
						arrRegionId:arrSelects.eq(2).val()});	
				}
			}
			
			var departTrs = $("#edit").find(".departInfo").children("tr").not("#departModel");
			var departTables = new Array();
			for (var int = 0; int < departTrs.length; int++) {
				var departSelects = departTrs.eq(int).find("select");
				var departInputs = departTrs.eq(int).find("input");
				if(departSelects.eq(0).val()!=""||departSelects.eq(1).children("option:selected").html()!="&nbsp;"||departInputs.eq(2).val()!=""||departInputs.eq(3).val()!=""||departSelects.eq(2).val()!=""){
					departTables.push({
							id:departTrs.eq(int).find("td").last().attr("id"),
							tourId:id,
							destId:departSelects.eq(0).val(),
							departTraffic:departSelects.eq(1).children("option:selected").text(),
							departTime:new Date(departInputs.eq(2).val()),
							departTrafficNo:departInputs.eq(3).val(),
							departRegionId:departSelects.eq(2).val()});
				}
			}
			
			var tripTables = new Array();
			var tripsTbody = $("#trips3").find("div").not("#editTripModel").children("table").children("tbody");
			for (var int = 0; int < tripsTbody.length; int++) {
				var tripInputs =  tripsTbody.eq(int).find("input");
				var tripTextAreas =  tripsTbody.eq(int).find("textarea");
				if(tripsTbody.eq(int).attr("id")!=undefined||tripTextAreas.eq(0).val()!=""||tripInputs.eq(0).val()!=""||tripInputs.eq(1).val()!=""||tripInputs.eq(2).val()!=""||tripTextAreas.eq(1).val()!=""){
					tripTables.push({
							id:tripsTbody.eq(int).attr("id"),
							tourId:id,
							number:int,
							trip:tripTextAreas.eq(0).val(),
							meal:tripInputs.eq(0).val(),
							stay:tripInputs.eq(1).val(),
							traffic:tripInputs.eq(2).val(),
							remark:tripTextAreas.eq(1).val()});
					if(tripsTbody.eq(int).attr("id")!=undefined){
						tripDelIds.shift();
					}
					if(tripTextAreas.eq(0).val()==""&&tripInputs.eq(0).val()==""&&tripInputs.eq(1).val()==""&&tripInputs.eq(2).val()==""&&tripTextAreas.eq(1).val()==""){
						tripDelIds.push(tripsTbody.eq(int).attr("id"));
					}
				}
			}
			
			var costTables = new Array();
			var costTrs = $("#edit").find("#costs3").find("tbody").find("tr").not("#costModel");
			for (var int = 0; int < costTrs.length; int++) {
				var costInputs = costTrs.eq(int).find("input");
				var costSelects = costTrs.eq(int).find("select");
				costTables.push({
						id:costTrs.eq(int).children("td").last().attr("id"),
						tourId:id,
						costDate:new Date(costInputs.eq(0).val()),
						contentId:costSelects.eq(0).val(),
						supplierId:costSelects.eq(1).val(),
						cost:costInputs.eq(3).val(),
						count:costInputs.eq(4).val(),
						days:costInputs.eq(5).val(),
						borrowUserId:costSelects.eq(2).val(),
						supplierScopeId:costInputs.last().val(),
						remark:costInputs.eq(7).val()});
			}
			
			var incomeTables = new Array();
			var incomeTrs = $("#edit").find("#incomes3").find("tbody").find("tr").not("#incomeModel");
			for (var int = 0; int < incomeTrs.length; int++) {
				var incomeInputs = incomeTrs.eq(int).find("input");
				incomeTables.push({
					id:incomeTrs.eq(int).children("td").last().attr("id"),
					tourId:id,
					incomeDate:new Date(incomeInputs.eq(0).val()),
					customerAgencyId:incomeTrs.eq(int).find("select").val(),
					income:incomeInputs.eq(2).val(),
					remark:incomeInputs.eq(3).val()});
			}
			
			/* 成本删除ID拼装 */
			var costTbodys = $("#costs3").find("tbody");
			var costDelIds = "";
			var firstCost = true;
			$.each(costTbodys,function(){
				if($(this).attr("delIds")!=undefined){
					if(firstCost==true){
						costDelIds = $(this).attr("delIds");
						firstCost = false;
					}else{
						costDelIds = costDelIds+","+$(this).attr("delIds");
					}
				} 
			});
			var delIds = {
					"ArrTable":$("#edit").find(".arrInfo").attr("delids"),
					"DepartTable":$("#edit").find(".departInfo").attr("delids"),
					"TripTable":tripDelIds.toString(),
					"CostTable":costDelIds,
					"IncomeTable":$("#incomes3").find("tbody").attr("delids")};
			
			var fullLocalTourViewModel = {localTourTable:localTourTable,guideTimeTables:guideTimeTables,arrTables:arrTables,departTables:departTables,tripTables:tripTables,costTables:costTables,incomeTables:incomeTables,delIds:delIds};
			var myData = JSON.stringify(fullLocalTourViewModel);
			$.ajax({
		        type: "POST",  
		        contentType:"application/json;charset=utf-8",  
		        url:"/localtour/localTourManage/update",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	if(data==-1){
		        		alert("保存失败，请检查团号是否重复，基本信息必填项是否完整，开始日期是否大于结束日期");
		        	}else if(data==-2){
		        		alert("保存失败，请填写出发地或抵达地");
		        	}else if(data==-3){
		        		alert("保存失败，请填写前往地或离开");
		        	}else if(data==-4){
		        		alert("保存失败，请填写供应商或内容");
		        	}else if(data==-5){
		        		alert("保存失败，请填写客户");
		        	}
		        }
			 });
		});
		
		/* 成本变更 */
		$("#table").delegate("#chanageCost","click",function(){
			$("#saveChange").parent().attr("id",$(this).parent().attr("id"));
			var myData = {tourId:$(this).parent().attr("id")};
			$.ajax({
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"/localtour/localTourManage/findChangeCost",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	var flight = $("#flight4").find("tbody");
		        	var hotel = $("#hotel4").find("tbody");
		        	var meal = $("#meal4").find("tbody");
		        	var ticket = $("#ticket4").find("tbody");
		        	var shuttle = $("#shuttle4").find("tbody");
		        	var tickets = $("#tickets4").find("tbody");
		        	var comprehensive = $("#comprehensive4").find("tbody");
		        	var other = $("#other4").find("tbody");
		        	flight.html("");
		        	hotel.html("");
		        	meal.html("");
		        	ticket.html("");
		        	shuttle.html("");
		        	tickets.html("");
		        	comprehensive.html("");
		        	other.html("");
		        	$.each(data.costs,function(){
		        		var tr = $('<tr class="look">'+
										'<td>'+this.costTable.costDate+'</td>'+
										'<td>'+this.contentName+'</td>'+
										'<td>'+this.supplierName+'</td>'+
										'<td>'+this.costTable.cost+'</td>'+
										'<td>'+this.costTable.count+'</td>'+
										'<td>'+this.costTable.days+'</td>'+
										'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
										'<td>'+this.borrowUserName+'</td>'+
										'<td>'+this.costTable.remark+'</td>'+
								'</tr>');
		        		if(this.costTable.remittanced){
		        			tr.append('<td>已汇款</td>')
		        		}else{
		        			if(this.costTable.status==0){
			        			tr.append('<td>新建</td>')
			        		}else if(this.costTable.status==1){
			        			tr.append('<td>待审核</td>')
			        		}else if(this.costTable.status==2){
			        			tr.append('<td>已审核</td>')
			        		}else if(this.costTable.status==3){
			        			tr.append('<td>已批准</td>')
			        		}
		        		}
		        		if(this.costTable.supplierScopeId==1){
		        			flight.append(tr);
		        		}else if(this.costTable.supplierScopeId==2){
		        			hotel.append(tr);
		        		}else if(this.costTable.supplierScopeId==3){
		        			meal.append(tr);   			
		        		}else if(this.costTable.supplierScopeId==4){
		        			ticket.append(tr);        			
		        		}else if(this.costTable.supplierScopeId==5){
		        			shuttle.append(tr);
		        		}else if(this.costTable.supplierScopeId==6){
		        			tickets.append(tr);
		        		}else if(this.costTable.supplierScopeId==7){
		        			comprehensive.append(tr);
		        		}else if(this.costTable.supplierScopeId==8){
		        			other.append(tr);
		        		}
		        	});
		        	var incomes = $("#incomes4").find("tbody");
		        	incomes.html("");
		        	$.each(data.incomes,function(){
		        		var realIncome = this.incomeTable.realIncome==null?0:this.incomeTable.realIncome;
		        		var tr = $('<tr class="look">'+
		        						'<td>'+this.incomeTable.incomeDate+'</td>'+
		        						'<td>'+this.customerAgencyName+'</td>'+
		        						'<td>'+this.incomeTable.income+'</td>'+
		        						'<td>'+realIncome+'</td>'+
		        						'<td>'+this.invoiceAmount+'</td>'+
		        						'<td>'+this.incomeTable.remark+'</td>'+
		        					'</tr>');
		        		if(this.incomeTable.incomed){
		        			tr.append('<td>已收款</td>')
		        		}else{
		        			if(this.incomeTable.status==0){
			        			tr.append('<td>新建</td>')
			        		}else if(this.incomeTable.status==1){
			        			tr.append('<td>待审核</td>')
			        		}else if(this.incomeTable.status==2){
			        			tr.append('<td>已审核</td>')
			        		}else if(this.incomeTable.status==3){
			        			tr.append('<td>已批准</td>')
			        		}
		        		}
		        		incomes.append(tr);
		        	});
		        }  
			});
		});
		/* 提交待审 */
		$("#cost").delegate("#saveChange","click",function(){
			var costTables = new Array();
			var tourId = $(this).parent().attr("id");
			var costTrs = $("#cost").find("#costs4").find("tbody").find("tr").not(".look");
			for (var int = 0; int < costTrs.length; int++) {
				var costInputs = costTrs.eq(int).find("input");
				var costSelects = costTrs.eq(int).find("select");
				costTables.push({
					tourId:tourId,
					costDate:new Date(costInputs.eq(0).val()),
					contentId:costSelects.eq(0).val(),
					supplierId:costSelects.eq(1).val(),
					cost:costInputs.eq(3).val(),
					count:costInputs.eq(4).val(),
					days:costInputs.eq(5).val(),
					borrowUserId:costSelects.eq(2).val(),
					supplierScopeId:costInputs.last().val(),
					remark:costInputs.eq(7).val()});
			}
			var incomeTables = new Array();
			var incomeTrs = $("#cost").find("#incomes4").find("tbody").find("tr").not(".look");
			for (var int = 0; int < incomeTrs.length; int++) {
				var incomeInputs = incomeTrs.eq(int).find("input");
				var incomeSelects = incomeTrs.eq(int).find("select");
				incomeTables.push({
					tourId:tourId,
					incomeDate:new Date(incomeInputs.eq(0).val()),
					customerAgencyId:incomeSelects.eq(0).val(),
					income:incomeInputs.eq(2).val(),
					remark:incomeInputs.eq(3).val()
				});
			}
			var changeCostIncomeViewModel = {costTables:costTables,incomeTables:incomeTables};
			var myData = JSON.stringify(changeCostIncomeViewModel);
			$.ajax({
		        type: "POST",  
		        contentType:"application/json;charset=utf-8",  
		        url:"/localtour/localTourManage/saveChangeCost",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	if(data==-1){
		        		alert("保存失败，请检查团号是否重复，基本信息必填项是否完整，开始日期是否大于结束日期");
		        	}else if(data==-2){
		        		alert("保存失败，请填写出发地或抵达地");
		        	}else if(data==-3){
		        		alert("保存失败，请填写前往地或离开");
		        	}else if(data==-4){
		        		alert("保存失败，请填写供应商或内容");
		        	}else if(data==-5){
		        		alert("保存失败，请填写客户");
		        	}
		        }  
			 });
		});
		
		/* 导游借款 */
		$("#table").delegate("#lend","click",function(){
			var tourId = $(this).parent().attr("id");
			$("#loanApplication").parent().attr("id",tourId);
			var myData = {tourId:tourId};
			$.ajax({
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"/localtour/localTourManage/findLend",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	var canLoans = $('<tbody></tbody>');
		        	var isLoans = $('<tbody></tbody>');
		        	$.each(data,function(){
		        		if(this.loanTable.status>1){
		        			var tr = $('<tr>'+
	        						'<td>'+this.loanTable.loanDate+'</td>'+
	        						'<td>'+this.loanTable.loanAmount+'</td>'+
	        						'<td>'+this.loanTable.remark+'</td>'+
	        						'<td>'+this.lenderRealName+'</td>'+
	        						'<td>'+this.status+'</td>'+
	        					'</tr>');
		        			isLoans.append(tr);
		        		}else{
		        			var tr = $('<tr id="'+this.loanTable.id+'">'+
	        						'<td>'+this.loanTable.loanDate+'</td>'+
	        						'<td>'+this.loanTable.loanAmount+'</td>'+
	        						'<td>'+this.loanTable.remark+'</td>'+
	        						'<td>'+this.lenderRealName+'</td>'+
	        						'<td>'+this.status+'</td>'+
	        					'</tr>');
		        			canLoans.append(tr);
		        		}
		        	});
		        	if(canLoans.html()==""){
	        			$("#canLoans").parent().html('<span class="red">无可借款项</span>');
	        		}else {
	        			$("#canLoans").html(canLoans.html());
	        		}
					if(isLoans.html()==""){
						$("#isLoans").parent().html('<span class="red">无借款记录</span>');
	        		}else{
	        			$("#isLoans").html(isLoans.html());
	        		}
		        }
			 });
		});
		/* 提交借款申请 */
		$("#loanApplication").click(function(){
			var tourId = $(this).parent().attr("id");
			var ids = [];
			var trs = $("#canLoans").children("tr");
			$.each(trs,function(index){
				ids[index] = $(this).attr("id");
			});
			var myData = {tourId:tourId,ids:ids.toString()};
			$.ajax({
		        type: "GET",  
		        contentType:"application/json;charset=utf-8",  
		        url:"/localtour/localTourManage/loanApplication",  
		        data:myData,  
		        dataType: "json",  
		        async: false,  
		        success:function(data){
		        	
		        }  
			 });
		});
		
		/* 付款申请 */
		var canPaysP = $("#canPays").parent();
		var isPaysP = $("#isPays").parent();
		var canPaysH = canPaysP.html();
		var isPaysH = isPaysP.html();
		var changeCostBlueDown = $("#changeCostBlueDown");
		var changeCostBlueUp = $("#changeCostBlueUp");
		$("#pay").click(function(){
			canPaysP.html(canPaysH);
			isPaysP.html(isPaysH);
			/*付款全选*/
			$("#payModel").find(".selectAllPay").click(function(){
				if($(this).prop("checked")){
					var checkbox = $("#canPays").find("input").prop("checked",true);
				}else{
					var checkbox = $("#canPays").find("input").prop("checked",false);
				}
			});
			/*点击本行选择*/
			$("#canPays").delegate("tr td:not(.center):not(tr td:nth-child(9))","click",function(){
				var checkbox = $(this).parent().find("input");
				if(checkbox.prop("checked")){
					checkbox.prop("checked",false);
				}else{
					checkbox.prop("checked",true);
				}
			});
			$(".selectAllPay").prop("checked",false);
			var checkbox = $("#table").find("input:checked");
			if(checkbox.length==0){
				alert("请选择一个团队");
				$(this).attr("href","#");
			}else if(checkbox.length>1){
				alert("只能选择一个团队");
				$(this).attr("href","#");
			}else{
				$(this).attr("href","#payModel");
				var tourId = checkbox.parent().parent().siblings().last().attr("id");
				$("#payApplication").parent().attr("id",tourId);
				var myData = {tourId:tourId};
				$.ajax({
			        type: "GET",  
			        contentType:"application/json;charset=utf-8",  
			        url:"/localtour/localTourManage/findPay",  
			        data:myData,  
			        dataType: "json",
			        async: false,
			        success:function(data){
			        	var canPays = $('<tbody></tbody>');
			        	var isPays = $('<tbody></tbody>');
			        	$.each(data.costs,function(){
			        		if(this.costTable.payStatus==0&&!this.costTable.remittanced&&!this.costTable.lend&&!this.costTable.bill){
			        			var tr = $('<tr id="'+this.costTable.id+'">'+
			        							'<td class="center sorting_1"><label><input class="ace" type="checkbox"><span class="lbl"></span></label></td>'+
				        						'<td>'+this.costTable.costDate+'</td>'+
				        						'<td>'+this.contentName+'</td>'+
				        						'<td>'+this.supplierName+'</td>'+
				        						'<td>'+this.costTable.cost+'</td>'+
				        						'<td>'+this.costTable.count+'</td>'+
				        						'<td>'+this.costTable.days+'</td>'+
				        						'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
				        						'<td><input style="width:100%;" type="text" value="'+this.costTable.realCost+'"></td>'+
				        						'<td>'+this.borrowUserName+'</td>'+
				        						'<td>'+this.costTable.remark+'</td>'+
				        						'<td>'+this.payStatus+'</td>'+
				        					'</tr>');
			        			canPays.append(tr);
			        		}else if(!this.costTable.remittanced&&!this.costTable.lend&&!this.costTable.bill){
			        			var tr = $('<tr>'+
				        						'<td>'+this.costTable.costDate+'</td>'+
				        						'<td>'+this.contentName+'</td>'+
				        						'<td>'+this.supplierName+'</td>'+
				        						'<td>'+this.costTable.cost+'</td>'+
				        						'<td>'+this.costTable.count+'</td>'+
				        						'<td>'+this.costTable.days+'</td>'+
				        						'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
				        						'<td>'+this.costTable.realCost+'</td>'+
				        						'<td>'+this.borrowUserName+'</td>'+
				        						'<td>'+this.costTable.remark+'</td>'+
				        						'<td>'+this.payStatus+'</td>'+
				        					'</tr>');
			        			isPays.append(tr);
			        		}
			        	});
			        	var canPaysChangeCount = 0;
			        	var isPaysChangeCount = 0;
			        	$.each(data.changeCosts,function(){
			        		if(this.costTable.payStatus==0&&!this.costTable.remittanced&&!this.costTable.lend&&!this.costTable.bill&&this.costTable.status==3){
			        			var tr = $('<tr id="'+this.costTable.id+'" class="blue">'+
			        							'<td class="center sorting_1"><label><input class="ace" type="checkbox"><span class="lbl"></span></label></td>'+
				        						'<td>'+this.costTable.costDate+'</td>'+
				        						'<td>'+this.contentName+'</td>'+
				        						'<td>'+this.supplierName+'</td>'+
				        						'<td>'+this.costTable.cost+'</td>'+
				        						'<td>'+this.costTable.count+'</td>'+
				        						'<td>'+this.costTable.days+'</td>'+
				        						'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
				        						'<td><input style="width:100%;" type="text" value="'+this.costTable.realCost+'"></td>'+
				        						'<td>'+this.borrowUserName+'</td>'+
				        						'<td>'+this.costTable.remark+'</td>'+
				        						'<td>'+this.payStatus+'</td>'+
				        					'</tr>');
			        			canPays.append(tr);
			        			canPaysChangeCount++;
			        		}else if(!this.costTable.remittanced&&!this.costTable.lend&&!this.costTable.bill&&this.costTable.status==3){
			        			var tr = $('<tr class="blue">'+
				        						'<td>'+this.costTable.costDate+'</td>'+
				        						'<td>'+this.contentName+'</td>'+
				        						'<td>'+this.supplierName+'</td>'+
				        						'<td>'+this.costTable.cost+'</td>'+
				        						'<td>'+this.costTable.count+'</td>'+
				        						'<td>'+this.costTable.days+'</td>'+
				        						'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
				        						'<td>'+this.costTable.realCost+'</td>'+
				        						'<td>'+this.borrowUserName+'</td>'+
				        						'<td>'+this.costTable.remark+'</td>'+
				        						'<td>'+this.payStatus+'</td>'+
				        					'</tr>');
			        			isPays.append(tr);
			        			isPaysChangeCount++;
			        		}
			        	});
			        	var span = $('<span id="changeCostBlue" class="blue" style="">*蓝色为成本收入变更</span>');
			        	$("#changeCostBlue").remove()
			        	if(canPays.html()==""){
		        			$("#canPays").parent().html('<span class="red">无可付款项</span>');
		        		}else {
		        			$("#canPays").html(canPays.html());
		        			if(canPaysChangeCount!=0){
		        				canPaysP.after(span);
		        			}
		        		}
						if(isPays.html()==""){
							$("#isPays").parent().html('<span class="red">无付款记录</span>');
		        		}else{
		        			$("#isPays").html(isPays.html());
		        			if(isPaysChangeCount!=0){
		        				isPaysP.after(span);
		        			}
		        		}
			        }
				});
			}
		});
		/*提交付款申请*/
		$("#payApplication").click(function(){
			var tourId = $(this).parent().attr("id");
			var checkbox = $("#canPays").find("input:checked");
			if(checkbox.length==0){
				alert("没有选择要申请的付款项");
			}else{
				var costTables = new Array();
				var changeCostTables = new Array();
				$.each(checkbox,function(){
					var tr = $(this).parent().parent().parent();
					if(tr.attr("class")=="blue"){
						changeCostTables.push({id:tr.attr("id"),
											tourId:tourId,
											realCost:tr.children("td").eq(8).children("input").val()});
					}else{
						costTables.push({id:tr.attr("id"),
									tourId:tourId,
									realCost:tr.children("td").eq(8).children("input").val()});
					}
				});
				var full = {costTables:costTables,changeCostTables:changeCostTables};
				var myData = JSON.stringify(full);
				alert(myData)
				$.ajax({
			        type: "POST",  
			        contentType:"application/json;charset=utf-8",  
			        url:"/localtour/localTourManage/payApplication",  
			        data:myData,  
			        dataType: "json",  
			        async: false,  
			        success:function(data){
			        	
			        }  
				 });
			}
		});
		/*点击付款input金额自动填充*/
		$("#payModel").delegate("input:not(.ace)","click",function(){
			$(this).val(parseFloat($(this).parent().prev().text()));
		});
		
		
		/*预借发票*/
		$("#borrowInvoice").click(function(){
			var checkbox = $("#table").find("input:checked");
			if(checkbox.length==0){
				alert("请选择一个团队");
				$(this).attr("href","#");
			}else if(checkbox.length>1){
				alert("只能选择一个团队");
				$(this).attr("href","#");
			}else{
				$("#borrowInvoices").html("");
				$(this).attr("href","#borrowInvoiceModel");
				var tourId = checkbox.parent().parent().siblings().last().attr("id");
				$("#borrowInvoiceApplication").parent().attr("id",tourId);
				var myData = {tourId:tourId};
				$.ajax({
			        type: "GET",  
			        contentType:"application/json;charset=utf-8",  
			        url:"/localtour/localTourManage/findBorrowInvoice",  
			        data:myData,  
			        dataType: "json",
			        async: false,
			        success:function(data){
			        	$("#borrowInvoices").append('<tr class="borrowInvoice">'+
			        									'<td>'+this.invoiceTable.issueDate+'</td>'+
			        									'<td>'+this.invoiceTable.invoiceName+'</td>'+
			        									'<td>'+this.invoiceTable.invoiceContent+'</td>'+
			        									'<td>'+this.invoiceTable.invoiceAmount+'</td>'+
			        									'<td>'+this.invoiceTable.remark+'</td>'+
			        								'</tr>');
			        }
				});
			}
		});
		/*新增预借发票*/
		$(".addBorrowInvoice").click(function(){
			var date = (new Date()).toLocaleDateString();
			var tr = $('<tr>'+
							'<td><input style="width:100%;" class="form-control datepicker" type="text" value="'+date+'"></td>'+
							'<td><input style="width:100%;" class="form-control" type="text"></td>'+
							'<td><input style="width:100%;" class="form-control" type="text"></td>'+
							'<td><input style="width:100%;" class="form-control" type="text"></td>'+
							'<td><input style="width:100%;" class="form-control" type="text"></td>'+
							'<td><a class="red delLine" href="#"><i class="icon-trash bigger-130"></i></a></td>'+
						'</tr>');
			tr.find(".datepicker").datepicker({
    			showOtherMonths: true,
    			selectOtherMonths: false,
    		});
			$("#borrowInvoices").append(tr);
		});
		/*预借发票申请*/
		$("#borrowInvoiceApplication").click(function(){
			var tourId = $(this).parent().attr("id");
			var borrowInvoices = new Array();
			var trs = $("#borrowInvoices").children("tr").not(".borrowInvoice");
			$.each(trs,function(){
				var inputs = $(this).find("input");
				borrowInvoices.push({
					tourId:tourId,
					issueDate:inputs.eq(0).val(),
					invoiceName:inputs.eq(1).val(),
					invoiceContent:inputs.eq(2).val(),
					invoiceAmount:inputs.eq(3).val(),
					remark:inputs.eq(4).val()
				});
			});
			
		});
	});
	
