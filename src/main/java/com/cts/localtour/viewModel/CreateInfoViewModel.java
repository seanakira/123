package com.cts.localtour.viewModel;

import java.util.ArrayList;

import com.cts.localtour.entity.BusinessTypeTable;
import com.cts.localtour.entity.CustomerAgencyTable;
import com.cts.localtour.entity.RegionTable;
import com.cts.localtour.entity.TourTypeTable;
import com.cts.localtour.entity.VisitorTypeTable;

public class CreateInfoViewModel {
	private ArrayList<BusinessTypeTable> businessTypes;
	private ArrayList<TourTypeTable> tourTypes;
	private ArrayList<RegionTable> regions;
	private ArrayList<VisitorTypeTable> visitorTypes;
	private ArrayList<CustomerAgencyTable> customerAgencys;
	public ArrayList<BusinessTypeTable> getBusinessTypes() {
		return businessTypes;
	}
	public void setBusinessTypes(ArrayList<BusinessTypeTable> businessTypes) {
		this.businessTypes = businessTypes;
	}
	public ArrayList<TourTypeTable> getTourTypes() {
		return tourTypes;
	}
	public void setTourTypes(ArrayList<TourTypeTable> tourTypes) {
		this.tourTypes = tourTypes;
	}
	public ArrayList<RegionTable> getRegions() {
		return regions;
	}
	public void setRegions(ArrayList<RegionTable> regions) {
		this.regions = regions;
	}
	public ArrayList<VisitorTypeTable> getVisitorTypes() {
		return visitorTypes;
	}
	public void setVisitorTypes(ArrayList<VisitorTypeTable> visitorTypes) {
		this.visitorTypes = visitorTypes;
	}
	public ArrayList<CustomerAgencyTable> getCustomerAgencys() {
		return customerAgencys;
	}
	public void setCustomerAgencys(ArrayList<CustomerAgencyTable> customerAgencys) {
		this.customerAgencys = customerAgencys;
	}
}
