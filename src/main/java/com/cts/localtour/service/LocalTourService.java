package com.cts.localtour.service;

import java.util.ArrayList;
import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.localtour.DAO.LocalTourDAO;
import com.cts.localtour.entity.SupplierBusinessTable;
import com.cts.localtour.entity.UserTable;
import com.cts.localtour.entity.LocalTourTable;
import com.cts.localtour.viewModel.SimpleLocalTourViewModel;

@SuppressWarnings("rawtypes")
@Service
public class LocalTourService extends BaseService{
	@Autowired
	private LocalTourDAO localTourDAO;
	@SuppressWarnings("unchecked")
	public ArrayList<SimpleLocalTourViewModel> getAll(String key, int page, int maxResults) {
		if(key.equals("")){
			ArrayList<LocalTourTable> localTours = this.getAllByTableName("LocalTourTable", page, maxResults);
			return setMd(localTours);
		}else{
			Hashtable<String, String> param = new Hashtable<String, String>();
			param.put("tourNO", "%"+key+"%");
			param.put("tourName", "%"+key+"%");
			ArrayList<LocalTourTable> localTours = this.getAllByParam("LocalTourTable", "tourNO like :tourNO or tourName like :tourName", param, page, maxResults);
			return setMd(localTours);
		}
	}
	public ArrayList<SimpleLocalTourViewModel> setMd(ArrayList<LocalTourTable> localTours){
		ArrayList<SimpleLocalTourViewModel> simpleLocalTourViewModels = new ArrayList<SimpleLocalTourViewModel>();
		for (int i = 0; i < localTours.size(); i++) {
			SimpleLocalTourViewModel simpleLocalTourViewModel = new SimpleLocalTourViewModel();
//			String regionName = ((ArrayList<RegionTable>) this.getByWhere("RegionTable", "id", localTours.get(i).getRegionId()+"")).get(0).getRegionName();
			String userName = ((UserTable)this.getById("UserTable", localTours.get(i).getUserId())).getUserName();
			
			simpleLocalTourViewModel.setLocalTourTable(localTours.get(i));
			int days = (int) ((localTours.get(i).getEndTime().getTime()-localTours.get(i).getStartTime().getTime())/1000/60/60/24)+1;
			simpleLocalTourViewModel.setDays(days);
			simpleLocalTourViewModel.setUserName(userName);
			if(localTours.get(i).getStatus()       ==0){
				simpleLocalTourViewModel.setStatus("新建");
			}else if (localTours.get(i).getStatus()==1) {
				simpleLocalTourViewModel.setStatus("已提交");
			}else if (localTours.get(i).getStatus()==2) {
				simpleLocalTourViewModel.setStatus("已报送");
			}else if (localTours.get(i).getStatus()==3) {
				simpleLocalTourViewModel.setStatus("可借款");
			}else if (localTours.get(i).getStatus()==4) {
				simpleLocalTourViewModel.setStatus("进行中");
			}else if (localTours.get(i).getStatus()==5) {
				simpleLocalTourViewModel.setStatus("已结束");
			}else if (localTours.get(i).getStatus()==6) {
				simpleLocalTourViewModel.setStatus("结算中");
			}else if (localTours.get(i).getStatus()==7) {
				simpleLocalTourViewModel.setStatus("已结算");
			}
			simpleLocalTourViewModels.add(simpleLocalTourViewModel);
		}
		return simpleLocalTourViewModels;
	}

	@SuppressWarnings("unchecked")
	public int getCounts(String key) {
		if(key.equals("")){
			return this.getCountsAll("LocalTourTable");
		}else{
			String where = "tourNO like :tourNO or tourName like :tourName";
			Hashtable<String, String> param = new Hashtable<String, String>();
			param.put("tourNO", "%"+key+"%");
			param.put("tourName", "%"+key+"%");
			return this.getCountsByParam("LocalTourTable", where, param);
		}
	}

	public void del(int id) {
		this.changeEnable("LocalTourTable", false, id);
	}
	
	public void recover(int id){
		this.changeEnable("LocalTourTable", true, id);
	}
	
	public void changeStatus(int id, int status) {
		this.updateByParam("LocalTourTable", "status=?", "id="+id, status);
	}

	public void update(LocalTourTable supplier) {
//		this.updateByParam("LocalTourTable", "supplierName=?,regionId=?,phone=?", "id="+supplier.getId(), supplier.getSupplierName(),supplier.getRegionId(),supplier.getPhone());
	}
	
	@SuppressWarnings("unchecked")
	public int add(LocalTourTable supplier){
		try {
			return ((LocalTourTable)localTourDAO.add(supplier)).getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	public void addSupplierBusiness(int supplierId, String supplierScopeIds){
		try {
			String ids[] = supplierScopeIds.split(",");
			for (int i = 0; i < ids.length; i++) {
				SupplierBusinessTable supplierBusinessTable = new SupplierBusinessTable();
				supplierBusinessTable.setSupplierId(supplierId);
				supplierBusinessTable.setSupplierScopeId(Integer.parseInt(ids[i]));
				this.add(supplierBusinessTable);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteSupplierBusiness(int supplierId) {
		this.deleteByString("SupplierBusinessTable", "supplierId=?", supplierId);
	}
	
}
