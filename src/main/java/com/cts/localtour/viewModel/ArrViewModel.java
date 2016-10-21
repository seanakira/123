package com.cts.localtour.viewModel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.localtour.entity.ArrTable;
import com.cts.localtour.entity.RegionTable;
import com.cts.localtour.service.BaseService;
@Component
public class ArrViewModel {
	private String origin;
	private String arrRegion;
	private ArrTable arrTable;
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseService baseService;
	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getArrRegion() {
		return arrRegion;
	}

	public void setArrRegion(String arrRegion) {
		this.arrRegion = arrRegion;
	}

	public ArrTable getArrTable() {
		return arrTable;
	}

	public void setArrTable(ArrTable arrTable) {
		this.arrTable = arrTable;
	}
	@SuppressWarnings("unchecked")
	public ArrayList<ArrViewModel> getAllArrDepViewModel(int tourId){
		ArrayList<ArrTable> arrTables = (ArrayList<ArrTable>) baseService.getAllByString("ArrTable", "tourId=?", tourId);
		ArrayList<ArrViewModel> arrs = new ArrayList<ArrViewModel>();
		for (int i = 0; i < arrTables.size(); i++) {
			ArrViewModel arr = new ArrViewModel();
			arr.setArrTable(arrTables.get(i));
			arr.setOrigin(((RegionTable)baseService.getById("RegionTable", arrTables.get(i).getOriginId())).getRegionName());
			arr.setArrRegion(((RegionTable)baseService.getById("RegionTable", arrTables.get(i).getArrRegionId())).getRegionName());
			arrs.add(arr);
		}
		return arrs;
	}
}
