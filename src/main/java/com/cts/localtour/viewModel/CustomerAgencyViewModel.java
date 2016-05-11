package com.cts.localtour.viewModel;

import com.cts.localtour.entity.CustomerAgencyTable;

public class CustomerAgencyViewModel {
	private String regionName;
	private CustomerAgencyTable customerAgencyTable;
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public CustomerAgencyTable getCustomerAgencyTable() {
		return customerAgencyTable;
	}
	public void setCustomerAgencyTable(CustomerAgencyTable customerAgencyTable) {
		this.customerAgencyTable = customerAgencyTable;
	}
	
}
