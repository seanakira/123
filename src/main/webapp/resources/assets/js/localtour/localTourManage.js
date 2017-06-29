/*查看*/
function check(data){
	var td = $("#find").find(".tourInfo");
	td.eq(0).text(data.localTourTable.tourNo);
	td.eq(1).text(data.localTourTable.tourName);
	td.eq(2).text(data.tourInfo.businessTypeName);
	td.eq(3).text(data.tourInfo.tourTypeName);
	td.eq(4).text(data.tourInfo.regionName);
	td.eq(5).text(data.tourInfo.visitorTypeName);
	td.eq(6).text(data.tourInfo.customerAgencyName);
	td.eq(7).text(data.localTourTable.organizor);
	td.eq(8).text(data.localTourTable.qpGuideNo==null?0:data.localTourTable.qpGuideNo);
	td.eq(9).text(data.localTourTable.adultNo);
	td.eq(10).text(data.localTourTable.childrenNo==null?0:data.localTourTable.childrenNo);
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
	td.eq(16).text((data.localTourTable.isNewCustomer==null?"":data.localTourTable.isNewCustomer?"是":"否"));
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
							date.getFullYear()+'/'+(date.getMonth()+1)+'/'+date.getDate()+
						'</a>'+
				  	  '</li>');
			var flag = false;
			$.each(data.tripTables,function(){
				if(this.number==int){
					td.eq(17).text(this.trip);
	        		td.eq(18).text(this.meal);
	        		td.eq(19).text(this.stay);
	        		td.eq(20).text(this.traffic);
	        		td.eq(21).text(this.remark);
	        		div.append('<div id="day'+this.number+'" class="tab-pane in active">'+tripModel.html()+'</div>');
	        		flag=true;
				}
			});
			if(flag==false){
				td.eq(17).text("");
        		td.eq(18).text("");
        		td.eq(19).text("");
        		td.eq(20).text("");
        		td.eq(21).text("");
        		div.append('<div id="day'+int+'" class="tab-pane in active">'+tripModel.html()+'</div>');
			}
		}else{
			ul.append('<li>'+
					'<a data-toggle="tab" href="#day'+int+'">'+
						date.getFullYear()+'/'+(date.getMonth()+1)+'/'+date.getDate()+
					'</a>'+
			  	  '</li>');
			var flag = false;
			$.each(data.tripTables,function(){
				if(this.number==int){
					td.eq(17).text(this.trip);
	        		td.eq(18).text(this.meal);
	        		td.eq(19).text(this.stay);
	        		td.eq(20).text(this.traffic);
	        		td.eq(21).text(this.remark);
	        		div.append('<div id="day'+this.number+'" class="tab-pane">'+tripModel.html()+'</div>');
	        		flag=true;
				}
			});
			if(flag==false){
				td.eq(17).text("");
        		td.eq(18).text("");
        		td.eq(19).text("");
        		td.eq(20).text("");
        		td.eq(21).text("");
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
	var flightSum = 0;
	var hotelSum = 0;
	var mealSum = 0;
	var ticketSum = 0;
	var shuttleSum = 0;
	var ticketsSum = 0;
	var comprehensiveSum = 0;
	var otherSum = 0;
	
	var reimbursementFlightSum = 0;
	var reimbursementHotelSum = 0;
	var reimbursementMealSum = 0;
	var reimbursementTicketSum = 0;
	var reimbursementShuttleSum = 0;
	var reimbursementTicketsSum = 0;
	var reimbursementComprehensiveSum = 0;
	var reimbursementOtherSum = 0;
	
	var isReimbursement = false;
	if(data.localTourTable.status>6){
		isReimbursement = true;
	}
	
	$.each(data.costs,function(){
		var tbody;
		if(this.costTable.supplierScopeId==1){
			tbody = flight;
			flightSum = flightSum + this.costTable.cost*this.costTable.count*this.costTable.days;
			reimbursementFlightSum = reimbursementFlightSum + (this.costTable.reimbursement==null?0:this.costTable.reimbursement);
		}else if(this.costTable.supplierScopeId==2){
			tbody = hotel;
			hotelSum = hotelSum + this.costTable.cost*this.costTable.count*this.costTable.days;
			reimbursementHotelSum = reimbursementHotelSum + (this.costTable.reimbursement==null?0:this.costTable.reimbursement);
		}else if(this.costTable.supplierScopeId==3){	
			tbody = meal;
			mealSum = mealSum + this.costTable.cost*this.costTable.count*this.costTable.days;
			reimbursementMealSum = reimbursementMealSum + (this.costTable.reimbursement==null?0:this.costTable.reimbursement);
		}else if(this.costTable.supplierScopeId==4){
			tbody = ticket;
			ticketSum = ticketSum + this.costTable.cost*this.costTable.count*this.costTable.days;
			reimbursementTicketSum = reimbursementTicketSum + (this.costTable.reimbursement==null?0:this.costTable.reimbursement);
		}else if(this.costTable.supplierScopeId==5){
			tbody = shuttle;
			shuttleSum = shuttleSum + this.costTable.cost*this.costTable.count*this.costTable.days;
			reimbursementShuttleSum = reimbursementShuttleSum + (this.costTable.reimbursement==null?0:this.costTable.reimbursement);
		}else if(this.costTable.supplierScopeId==6){
			tbody = tickets;
			ticketsSum = ticketsSum + this.costTable.cost*this.costTable.count*this.costTable.days;
			reimbursementTicketsSum = reimbursementTicketsSum + (this.costTable.reimbursement==null?0:this.costTable.reimbursement);
		}else if(this.costTable.supplierScopeId==7){
			tbody = comprehensive;
			comprehensiveSum = comprehensiveSum + this.costTable.cost*this.costTable.count*this.costTable.days;
			reimbursementComprehensiveSum = reimbursementComprehensiveSum + (this.costTable.reimbursement==null?0:this.costTable.reimbursement);
		}else if(this.costTable.supplierScopeId==8){
			tbody = other;
			otherSum = otherSum + this.costTable.cost*this.costTable.count*this.costTable.days;
			reimbursementOtherSum = reimbursementOtherSum + (this.costTable.reimbursement==null?0:this.costTable.reimbursement);
		}
		tbody.append('<tr>'+
				'<td>'+(this.costTable.costDate==null?"":this.costTable.costDate)+'</td>'+
				'<td>'+this.contentName+'</td>'+
				'<td>'+this.supplierName+'</td>'+
				'<td>'+this.costTable.cost+'</td>'+
				'<td>'+this.costTable.count+'</td>'+
				'<td>'+this.costTable.days+'</td>'+
				'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
				'<td>'+(this.costTable.remittanced?this.costTable.realCost:"")+'</td>'+
				(isReimbursement?('<td>'+this.costTable.reimbursement.toFixed(2)+'</td>'):'')+
				'<td>'+this.borrowUserName+'</td>'+
				'<td>'+this.costTable.remark+'</td>'+
		'</tr>');
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
		var tbody;
		if(this.costTable.supplierScopeId==1){
			tbody = flight;
			flightSum = flightSum + this.costTable.cost*this.costTable.count*this.costTable.days;
			reimbursementFlightSum = reimbursementFlightSum + (this.costTable.reimbursement==null?0:this.costTable.reimbursement);
		}else if(this.costTable.supplierScopeId==2){
			tbody = hotel;
			hotelSum = hotelSum + this.costTable.cost*this.costTable.count*this.costTable.days;
			reimbursementHotelSum = reimbursementHotelSum + (this.costTable.reimbursement==null?0:this.costTable.reimbursement);
		}else if(this.costTable.supplierScopeId==3){	
			tbody = meal;
			mealSum = mealSum + this.costTable.cost*this.costTable.count*this.costTable.days;
			reimbursementMealSum = reimbursementMealSum + (this.costTable.reimbursement==null?0:this.costTable.reimbursement);
		}else if(this.costTable.supplierScopeId==4){
			tbody = ticket;
			ticketSum = ticketSum + this.costTable.cost*this.costTable.count*this.costTable.days;
			reimbursementTicketSum = reimbursementTicketSum + (this.costTable.reimbursement==null?0:this.costTable.reimbursement);
		}else if(this.costTable.supplierScopeId==5){
			tbody = shuttle;
			shuttleSum = shuttleSum + this.costTable.cost*this.costTable.count*this.costTable.days;
			reimbursementShuttleSum = reimbursementShuttleSum + (this.costTable.reimbursement==null?0:this.costTable.reimbursement);
		}else if(this.costTable.supplierScopeId==6){
			tbody = tickets;
			ticketsSum = ticketsSum + this.costTable.cost*this.costTable.count*this.costTable.days;
			reimbursementTicketsSum = reimbursementTicketsSum + (this.costTable.reimbursement==null?0:this.costTable.reimbursement);
		}else if(this.costTable.supplierScopeId==7){
			tbody = comprehensive;
			comprehensiveSum = comprehensiveSum + this.costTable.cost*this.costTable.count*this.costTable.days;
			reimbursementComprehensiveSum = reimbursementComprehensiveSum + (this.costTable.reimbursement==null?0:this.costTable.reimbursement);
		}else if(this.costTable.supplierScopeId==8){
			tbody = other;
			otherSum = otherSum + this.costTable.cost*this.costTable.count*this.costTable.days;
			reimbursementOtherSum = reimbursementOtherSum + (this.costTable.reimbursement==null?0:this.costTable.reimbursement);
		}
		tbody.append('<tr class="blue">'+
				'<td>'+(this.costTable.costDate==null?"":this.costTable.costDate)+'</td>'+
				'<td>'+this.contentName+'</td>'+
				'<td>'+this.supplierName+'</td>'+
				'<td>'+this.costTable.cost+'</td>'+
				'<td>'+this.costTable.count+'</td>'+
				'<td>'+this.costTable.days+'</td>'+
				'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
				'<td>'+(this.costTable.remittanced?this.costTable.realCost:"")+'</td>'+
				(isReimbursement?('<td>'+this.costTable.reimbursement.toFixed(2)+'</td>'):'')+
				'<td>'+this.borrowUserName+'</td>'+
				'<td>'+this.costTable.remark+'</td>'+
		'</tr>');
	});
	
	if(isReimbursement){
		if(data.reimbursementCosts.length > 0){
    		$("#costs2").find("#reimbursementCostRed").attr("style","");
    	}else{
    		$("#costs2").find("#reimbursementCostRed").attr("style","display:none");
    	}
		$.each(data.reimbursementCosts,function(){
    		var tbody;
    		if(this.costTable.supplierScopeId==1){
    			tbody = flight;
    			reimbursementFlightSum = reimbursementFlightSum + (this.costTable.reimbursement==null?0:this.costTable.reimbursement);
    		}else if(this.costTable.supplierScopeId==2){
    			tbody = hotel;
    			reimbursementHotelSum = reimbursementHotelSum + (this.costTable.reimbursement==null?0:this.costTable.reimbursement);
    		}else if(this.costTable.supplierScopeId==3){	
    			tbody = meal;
    			reimbursementMealSum = reimbursementMealSum + (this.costTable.reimbursement==null?0:this.costTable.reimbursement);
    		}else if(this.costTable.supplierScopeId==4){
    			tbody = ticket;
    			reimbursementTicketSum = reimbursementTicketSum + (this.costTable.reimbursement==null?0:this.costTable.reimbursement);
    		}else if(this.costTable.supplierScopeId==5){
    			tbody = shuttle;
    			reimbursementShuttleSum = reimbursementShuttleSum + (this.costTable.reimbursement==null?0:this.costTable.reimbursement);
    		}else if(this.costTable.supplierScopeId==6){
    			tbody = tickets;
    			reimbursementTicketsSum = reimbursementTicketsSum + (this.costTable.reimbursement==null?0:this.costTable.reimbursement);
    		}else if(this.costTable.supplierScopeId==7){
    			tbody = comprehensive;
    			reimbursementComprehensiveSum = reimbursementComprehensiveSum + (this.costTable.reimbursement==null?0:this.costTable.reimbursement);
    		}else if(this.costTable.supplierScopeId==8){
    			tbody = other;
    			reimbursementOtherSum = reimbursementOtherSum + (this.costTable.reimbursement==null?0:this.costTable.reimbursement);
    		}
    		tbody.append('<tr class="red">'+
					'<td>'+(this.costTable.costDate==null?"":this.costTable.costDate)+'</td>'+
					'<td>'+this.contentName+'</td>'+
					'<td>'+this.supplierName+'</td>'+
					'<td>'+this.costTable.cost+'</td>'+
					'<td>'+this.costTable.count+'</td>'+
					'<td>'+this.costTable.days+'</td>'+
					'<td>'+(this.costTable.cost*this.costTable.count*this.costTable.days).toFixed(2)+'</td>'+
					'<td>'+(this.costTable.remittanced?this.costTable.reimbursement:"")+'</td>'+
					(isReimbursement?('<td>'+this.costTable.reimbursement.toFixed(2)+'</td>'):'')+
					'<td></td>'+
					'<td>'+this.costTable.remark+'</td>'+
			'</tr>');
    	});
	}else{
		$("#costs2").find("#reimbursementCostRed").attr("style","display:none");
	}
	
	flight.append('<tr><td>机票合计</td><td>'+flightSum.toFixed(2)+'</td>'+(reimbursementFlightSum==0?'':'<td>机票报账合计</td><td>'+reimbursementFlightSum.toFixed(2)+'</td>')+'</tr>');
	hotel.append('<tr><td>订房合计</td><td>'+hotelSum.toFixed(2)+'</td>'+(reimbursementHotelSum==0?'':'<td>订房报账合计</td><td>'+reimbursementHotelSum.toFixed(2)+'</td>')+'</tr>');
	meal.append('<tr><td>订餐合计</td><td>'+mealSum.toFixed(2)+'</td>'+(reimbursementMealSum==0?'':'<td>订餐报账合计</td><td>'+reimbursementMealSum.toFixed(2)+'</td>')+'</tr>');
	ticket.append('<tr><td>门票合计</td><td>'+ticketSum.toFixed(2)+'</td>'+(reimbursementTicketSum==0?'':'<td>门票报账合计</td><td>'+reimbursementTicketSum.toFixed(2)+'</td>')+'</tr>');
	shuttle.append('<tr><td>订车合计</td><td>'+shuttleSum.toFixed(2)+'</td>'+(reimbursementShuttleSum==0?'':'<td>订车报账合计</td><td>'+reimbursementShuttleSum.toFixed(2)+'</td>')+'</tr>');
	tickets.append('<tr><td>票务合计</td><td>'+ticketsSum.toFixed(2)+'</td>'+(reimbursementTicketsSum==0?'':'<td>票务报账合计</td><td>'+reimbursementTicketsSum.toFixed(2)+'</td>')+'</tr>');
	comprehensive.append('<tr><td>综费合计</td><td>'+comprehensiveSum.toFixed(2)+'</td>'+(reimbursementComprehensiveSum==0?'':'<td>综费报账合计</td><td>'+reimbursementComprehensiveSum.toFixed(2)+'</td>')+'</tr>');
	other.append('<tr><td>其他合计</td><td>'+otherSum.toFixed(2)+'</td>'+(reimbursementOtherSum==0?'':'<td>其他报账合计</td><td>'+reimbursementOtherSum.toFixed(2)+'</td>')+'</tr>');
	
	var tbody = $("#incomes2").find("tbody");
	tbody.html("");
	var incomeSum = 0;
	var reimbursementIncomeSum = 0;
	
	$.each(data.incomes,function(){
		var realIncome = this.incomeTable.realIncome==null?0:this.incomeTable.realIncome;
		tbody.append('<tr>'+
							'<td>'+(this.incomeTable.incomeDate==null?"":this.incomeTable.incomeDate)+'</td>'+
							'<td>'+this.customerAgencyName+'</td>'+
							'<td>'+this.incomeTable.income+'</td>'+
							'<td>'+realIncome+'</td>'+
							'<td>'+this.incomeTable.remark+'</td>'+
					'</tr>');
		incomeSum = incomeSum + this.incomeTable.income;
		reimbursementIncomeSum = reimbursementIncomeSum + this.incomeTable.income;
	});
	if(data.changeIncomes.length > 0){
		$("#incomes2").find("#changeIncomeBlue").attr("style","");
	}else{
		$("#incomes2").find("#changeIncomeBlue").attr("style","display:none");
	}
	
	$.each(data.changeIncomes,function(){
		var realIncome = this.incomeTable.realIncome==null?0:this.incomeTable.realIncome;
		tbody.append('<tr class="blue">'+
							'<td>'+(this.incomeTable.incomeDate==null?"":this.incomeTable.incomeDate)+'</td>'+
							'<td>'+this.customerAgencyName+'</td>'+
							'<td>'+this.incomeTable.income+'</td>'+
							'<td>'+realIncome+'</td>'+
							'<td>'+this.incomeTable.remark+'</td>'+
					'</tr>');
		incomeSum = incomeSum + this.incomeTable.income;
		reimbursementIncomeSum = reimbursementIncomeSum + this.incomeTable.income;
	});
	
	if(isReimbursement){
		if(data.reimbursementIncomes.length > 0){
    		$("#incomes2").find("#reimbursementIncomeRed").attr("style","");
    	}else{
    		$("#incomes2").find("#reimbursementIncomeRed").attr("style","display:none");
    	}
		$.each(data.reimbursementIncomes,function(){
    		tbody.append('<tr class="red">'+
    							'<td></td>'+
    							'<td>'+this.customerAgencyName+'</td>'+
    							'<td>'+this.incomeTable.income+'</td>'+
    							'<td></td>'+
    							'<td>'+this.incomeTable.remark+'</td>'+
    					'</tr>');
    		reimbursementIncomeSum = reimbursementIncomeSum + this.incomeTable.income;
    	});
	}else{
		$("#incomes2").find("#reimbursementIncomeRed").attr("style","display:none");
	}
	
	$("#totalAll").html('<table class="table table-striped table-bordered table-hover no-margin-bottom no-border-top" style="text-align: left;"><tbody><tr><td>预估成本</td>'+
									'<td>'+(flightSum+hotelSum+mealSum+ticketSum+shuttleSum+ticketsSum+comprehensiveSum+otherSum).toFixed(2)+'</td>'+
									'<td>预估收入</td>'+
									'<td>'+incomeSum.toFixed(2)+'</td>'+
									'<td>预估毛利</td>'+
									'<td>'+(incomeSum-(flightSum+hotelSum+mealSum+ticketSum+shuttleSum+ticketsSum+comprehensiveSum+otherSum)).toFixed(2)+'</td>'+
									'<td>预估人均毛利</td>'+
									'<td>'+((incomeSum-(flightSum+hotelSum+mealSum+ticketSum+shuttleSum+ticketsSum+comprehensiveSum+otherSum))/((data.localTourTable.adultNo==null?0:data.localTourTable.adultNo) + (data.localTourTable.childrenNo==null?0:data.localTourTable.childrenNo))).toFixed(2)+'</td>'+
									'<td>预估毛利率</td>'+
									'<td>'+((incomeSum-(flightSum+hotelSum+mealSum+ticketSum+shuttleSum+ticketsSum+comprehensiveSum+otherSum))/incomeSum*100).toFixed(2)+'%</td>'+
						'</tr></tbody></table>');
	
	/* 重置th */
	if($("#costs2").find("thead").find("tr th:nth-child(9)").text().indexOf("报账金额")>-1){
		$("#costs2").find("thead").find("tr th:nth-child(9)").remove();
	}
	if(isReimbursement){
		/* 设置th */
		$("#costs2").find("thead").find("tr th:nth-child(8)").after('<th style="width: 20%;">报账金额</th>');
		/* 设置总计 */
		$("#totalAll").find("tbody").append('<tr><td>报账成本</td>'+
				'<td>'+(reimbursementFlightSum +reimbursementHotelSum +reimbursementMealSum +reimbursementTicketSum +reimbursementShuttleSum +reimbursementTicketsSum +reimbursementComprehensiveSum +reimbursementOtherSum).toFixed(2)+'</td>'+
				'<td>报账收入</td>'+
				'<td>'+reimbursementIncomeSum.toFixed(2)+'</td>'+
				'<td>报账毛利</td>'+
				'<td>'+(reimbursementIncomeSum-(reimbursementFlightSum +reimbursementHotelSum +reimbursementMealSum +reimbursementTicketSum +reimbursementShuttleSum +reimbursementTicketsSum +reimbursementComprehensiveSum +reimbursementOtherSum)).toFixed(2)+'</td>'+
				'<td>报账人均毛利</td>'+
				'<td>'+((reimbursementIncomeSum-(reimbursementFlightSum +reimbursementHotelSum +reimbursementMealSum +reimbursementTicketSum +reimbursementShuttleSum +reimbursementTicketsSum +reimbursementComprehensiveSum +reimbursementOtherSum))/((data.localTourTable.adultNo==null?0:data.localTourTable.adultNo) + (data.localTourTable.childrenNo==null?0:data.localTourTable.childrenNo))).toFixed(2)+'</td>'+
				'<td>报账毛利率</td>'+
				'<td>'+((reimbursementIncomeSum-(reimbursementFlightSum +reimbursementHotelSum +reimbursementMealSum +reimbursementTicketSum +reimbursementShuttleSum +reimbursementTicketsSum +reimbursementComprehensiveSum +reimbursementOtherSum))/incomeSum*100).toFixed(2)+'%</td>'+
		'</tr>');
	}
	/* 设置成本至基本信息 */
	$("#tourInfo2").children("#arrDepTable").next().next().next().next().remove();
	$("#tourInfo2").children("#arrDepTable").next().next().next().remove();
	$("#tourInfo2").children("#arrDepTable").next().next().remove();
	$("#tourInfo2").children("#arrDepTable").next().remove();
	var costDiv = $("#flight2").parent().clone();
	costDiv.attr({"style":"","class":"tab-content no-border padding-6"});
	costDiv.find("th").attr("style","width:8%;background-color: beige;");
	costDiv.find("th:nth-child(3)").attr("style","width:15%;background-color: beige;");
	costDiv.children("div").attr({"id":"","class":""});
	$.each(costDiv.children("div"),function(){
		if($(this).find("tr").length==2){
			$(this).remove();
		}
	});
	costDiv.prepend("<h5>成本：</h5>");
	$("#tourInfo2").append(costDiv);
	/* 设置收入至基本信息 */
	var incomeDiv = $("#incomes2").clone();
	incomeDiv.attr({"style":"","class":"tab-content no-border padding-6","id":""});
	incomeDiv.find("th").attr("style","background-color: beige;");
	incomeDiv.prepend("<h5>收入：</h5>");
	costDiv.after(incomeDiv);
	/* 设置借款 */
	var loanDiv = $('<div class="tab-content no-border padding-6" style=""><h5>借款：</h5><div class="tabbable tabs-left"><div class="tab-content no-padding" style="z-index: 1400;"><table class="table table-striped table-bordered table-hover incomeTable"><thead><tr><th style="width: 10%;background-color: beige;">日期</th><th style="width: 10%;background-color: beige;">借款额</th><th style="width: 10%;background-color: beige;">明细备注</th><th style="width: 10%;background-color: beige;">财务操作人</th><th style="width: 10%;background-color: beige;">状态</th></tr></thead><tbody></tbody></table></div></div></div>');
	$.each(data.loans,function(){
		loanDiv.find("tbody").append('<tr><td>'+this.loanTable.loanDate+'</td><td>'+this.loanTable.loanAmount+'</td><td>'+this.loanTable.remark+'</td><td>'+this.lenderRealName+'</td><td>'+this.status+'</td></tr>');
	});
	costDiv.after(loanDiv);
	/*设置发票*/
	var invoiceDiv = $('<div class="tab-content no-border padding-6" style=""><h5>发票：</h5><div class="tabbable tabs-left"><div class="tab-content no-padding" style="z-index: 1400;"><table class="table table-striped table-bordered table-hover incomeTable"><thead><tr><th style="width: 10%;background-color: beige;">日期</th><th style="width: 10%;background-color: beige;">票号</th><th style="width: 10%;background-color: beige;">抬头</th><th style="width: 10%;background-color: beige;">内容</th><th style="width: 10%;background-color: beige;">金额</th></tr></thead><tbody></tbody></table></div></div></div>');
	$.each(data.invoices,function(){
		invoiceDiv.find("tbody").append('<tr><td>'+this.invoiceTable.issueDate+'</td><td>'+this.invoiceTable.invoiceNo+'</td><td>'+this.customerAgencyName+'</td><td>'+this.invoiceTable.invoiceContent+'</td><td>'+this.invoiceTable.invoiceAmount+'</td></tr>');
	});
	$.each(data.loanInvoices,function(){
		invoiceDiv.find("tbody").append('<tr><td>'+this.loanInvoiceTable.issueDate+'</td><td>'+this.loanInvoiceTable.invoiceNo+'</td><td>'+this.customerAgencyName+'</td><td>'+this.loanInvoiceTable.invoiceContent+'</td><td>'+this.loanInvoiceTable.invoiceAmount+'</td></tr>');
	})
	incomeDiv.after(invoiceDiv);
}