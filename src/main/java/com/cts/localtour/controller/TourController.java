package com.cts.localtour.controller;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cts.localtour.entity.ArrTable;
import com.cts.localtour.entity.CostTable;
import com.cts.localtour.entity.DepartTable;
import com.cts.localtour.entity.GuideTimeTable;
import com.cts.localtour.entity.IncomeTable;
import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.entity.TripTable;
import com.cts.localtour.service.ArrService;
import com.cts.localtour.service.CostService;
import com.cts.localtour.service.DepartService;
import com.cts.localtour.service.GuideTimeService;
import com.cts.localtour.service.IncomeService;
import com.cts.localtour.service.LocalTourService;
import com.cts.localtour.service.TripService;
import com.cts.localtour.viewModel.CreateInfoViewModel;
import com.cts.localtour.viewModel.FullLocalTourViewModel;
import com.cts.localtour.viewModel.SimpleLocalTourViewModel;

@Controller
public class TourController {
	@Autowired
	private LocalTourService localTourService;
	@Autowired
	private GuideTimeService guideTimeService;
	@Autowired
	private ArrService arrService;
	@Autowired
	private DepartService departService;
	@Autowired
	private TripService tripService;
	@Autowired
	private CostService costService;
	@Autowired
	private IncomeService incomeService;
	@RequestMapping("/localTourManage")
	public String getSupplierAll(@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="15") int maxResults,@RequestParam(defaultValue="") String key, Model md){
		int counts = localTourService.getCounts(key);
		int pageMax = counts/maxResults;
		if(counts%maxResults>0){
			pageMax++;
		}
		if(page>pageMax){
			page=pageMax;
		}
		if(page<1){
			page=1;
		}
		ArrayList<SimpleLocalTourViewModel> localTours = localTourService.getAll(key,page,maxResults);
		md.addAttribute("localTours", localTours);
		md.addAttribute("counts", counts);
		md.addAttribute("pageMax", pageMax);
		md.addAttribute("pageNo", page);
		md.addAttribute("key", key);
		return "/tourManage/localTourManage";
	}
	
	@RequestMapping("/localTourManage/getCreateInfo")
	public @ResponseBody CreateInfoViewModel getCreateInfo(){
		return localTourService.getCreateInfo();
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/localTourManage/save")
	public @ResponseBody int save(@RequestBody FullLocalTourViewModel full){
		LocalTourTable localTour = full.getLocalTourTable();
		ArrayList<GuideTimeTable> guideTimeTables = full.getGuideTimeTables();
		ArrayList<ArrTable> arrTables = full.getArrTables();
		ArrayList<DepartTable> departTables = full.getDepartTables();
		ArrayList<TripTable> tripTables = full.getTripTables();
		ArrayList<CostTable> costTables = full.getCostTables();
		ArrayList<IncomeTable> incomeTables = full.getIncomeTables();
		if(localTour.getAdultNo()==0||localTour.getBusinessTypeId()==0||localTour.getCustomerAgencyId()==0||localTour.getEndTime()==null||localTour.getOrganizor().equals("")||localTour.getRegionId()==0||localTour.getStartTime()==null||localTour.getTourName().equals("")||localTour.getTourNo().equals("")||localTour.getTourTypeId()==0||localTour.getVisitorTypeId()==0){
			return 0;
		}else{
			for (int i = 0; i < costTables.size(); i++) {
				if(costTables.get(i).getSupplierId()==0){
					return 0;
				}
			}
			for (int i = 0; i < incomeTables.size(); i++) {
				if(incomeTables.get(i).getCustomerAgencyId()==0){
					return 0;
				}
			}
			int tourId = localTourService.addLocalTour(localTour);
			if(tourId!=0){
				/*����������Ϣ*/
				if(!guideTimeTables.isEmpty()){
					for (int i = 0; i < guideTimeTables.size(); i++) {
						guideTimeTables.get(i).setTourId(tourId);
						guideTimeService.add(guideTimeTables.get(i));
					}
				}
				/*����ִ��뿪��Ϣ*/
				if(!arrTables.isEmpty()){
					for (int i = 0; i < arrTables.size(); i++) {
						arrTables.get(i).setTourId(tourId);
						arrService.add(arrTables.get(i));
					}
				}
				if(!departTables.isEmpty()){
					for (int i = 0; i < departTables.size(); i++) {
						departTables.get(i).setTourId(tourId);
						departService.add(departTables.get(i));
					}
				}
				/*�����г�*/
				if(!tripTables.isEmpty()){
					for (int i = 0; i < tripTables.size(); i++) {
						tripTables.get(i).setTourId(tourId);
						tripService.add(tripTables.get(i));
					}
				}
				/*����ɱ�*/
				if(!costTables.isEmpty()){
					for (int i = 0; i < costTables.size(); i++) {
						costTables.get(i).setTourId(tourId);
						costService.add(costTables.get(i));
					}
				}
				/*��������*/
				if(!incomeTables.isEmpty()){
					for (int i = 0; i < incomeTables.size(); i++) {
						incomeTables.get(i).setTourId(tourId);
						incomeService.add(incomeTables.get(i));
					}
				}
			}
			return tourId;
		}
	}
	
	@RequestMapping("/localTourManage/del")
	public @ResponseBody boolean delLocalTour(@RequestParam int id){
		localTourService.del(id);
		return true;
	}
	
	@RequestMapping("/localTourManage/recover")
	public @ResponseBody boolean recoverLocalTour(@RequestParam int id){
		localTourService.recover(id);
		return true;
	}
	
	@RequestMapping("/localTourManage/chanageStatus/{status}")
	public @ResponseBody boolean auditingLocalTour(@RequestParam int id ,@PathVariable int status){
		localTourService.changeStatus(id,status);
		return true;
	}
	
	@RequestMapping("/localTourManage/find")
	public @ResponseBody FullLocalTourViewModel find(@RequestParam int tourId){
		return localTourService.find(tourId);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/localTourManage/update")
	public @ResponseBody Integer update(@RequestBody FullLocalTourViewModel full){
		LocalTourTable localTour = full.getLocalTourTable();
		ArrayList<GuideTimeTable> guideTimeTables = full.getGuideTimeTables();
		ArrayList<ArrTable> arrTables = full.getArrTables();
		ArrayList<DepartTable> departTables = full.getDepartTables();
		Hashtable<String, String> delIds = full.getDelIds();
		ArrayList<TripTable> tripTables = full.getTripTables();
		ArrayList<CostTable> costTables = full.getCostTables();
		ArrayList<IncomeTable> incomeTables = full.getIncomeTables();
		if(localTour.getAdultNo()==0||localTour.getBusinessTypeId()==0||localTour.getCustomerAgencyId()==0||localTour.getEndTime()==null||localTour.getOrganizor().equals("")||localTour.getRegionId()==0||localTour.getStartTime()==null||localTour.getTourName().equals("")||localTour.getTourNo().equals("")||localTour.getTourTypeId()==0||localTour.getVisitorTypeId()==0){
			return -1;
		}else{
			if(!localTourService.updateLocalTour(localTour)){
				return -1;
			}
			
			/*����������Ϣ*/
			guideTimeService.deleteByString("GuideTimeTable", "tourId=?", localTour.getId());
			if(!guideTimeTables.isEmpty()){
				for (int i = 0; i < guideTimeTables.size(); i++) {
					guideTimeService.add(guideTimeTables.get(i));
				}
			}
			/*���µִ��뿪��Ϣ*/
			if(!arrTables.isEmpty()){
				for (int i = 0; i < arrTables.size(); i++) {
					if(arrTables.get(i).getArrRegionId()==0||arrTables.get(i).getOriginId()==0){
						return -2;
					}else{
						arrService.merge(arrTables.get(i));
					}
				}
			}
			if(!departTables.isEmpty()){
				for (int i = 0; i < departTables.size(); i++) {
					if(departTables.get(i).getDepartRegionId()==0||departTables.get(i).getDestId()==0){
						return -3;
					}else{
						departService.merge(departTables.get(i));
					}
				}
			}
			/*�����г�*/
			if(!tripTables.isEmpty()){
				for (int i = 0; i < tripTables.size(); i++) {
					tripService.merge(tripTables.get(i));
				}
			}
			/*���³ɱ�*/
			if(!costTables.isEmpty()){
				for (int i = 0; i < costTables.size(); i++) {
					if(costTables.get(i).getContentId()==null||costTables.get(i).getSupplierId()==0){
						return -4;
					}else{
						costService.merge(costTables.get(i));
					}
				}
			}
			/*��������*/
			System.out.println(incomeTables.get(0).getCustomerAgencyId());
			if(!incomeTables.isEmpty()){
				for (int i = 0; i < incomeTables.size(); i++) {
					if(incomeTables.get(i).getCustomerAgencyId()==0){
						return -5;
					}else{
						incomeService.merge(incomeTables.get(i));
					}
				}
			}
			if(!delIds.isEmpty()){
				for (String tableName : delIds.keySet()) {
					if(!delIds.get(tableName).equals("")){
						localTourService.delByIds(tableName, delIds.get(tableName));
					}
				}
			}
		}
		return 0;
	}
}
