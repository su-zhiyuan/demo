package com.qppi.crud.bean;

import java.util.List;

public class Order {
    private String orderId;

    private String createBy;

    private String createTime;

    private String status;

    private String carId;

    private String carNum1;

    private String carNum2;

    private String carNum3;

    private String serviceType;

    private String contactId;

    private String serviceConsultant;

    private String inspector;

    private String partTotal;

    private String hoursTotal;

    private String total;

    private String inTime;

    private String outTime;

    private String remark;

    private String yl1;

    private String yl2;

    private String yl3;

    private String yl4;

    private String yl5;

    private String yl6;

    private String yl7;

    private String yl8;

    private String yl9;

    private String yl10;

    private String yl11;

    private String yl12;

    private String yl13;

    private String yl14;

    private String yl15;

    private String yl16;

    private String yl17;

    private String yl18;

    private String yl19;

    private String yl20;
    
    private CarInfo carInfo;
    
	private Reception reception;
    
    private Settlement settlement;
    
    private List<InfoRelayWithBLOBs> infoRelays;
    
    private List<Dispatching> dispatchings;
    
    private List<PartOffer> partOffers;
    
    private List<Purchase> purchases;
    
    private UserInfo userInfo;
    
    private Contacts contacts;
    
    
    protected int startRow;
    
    protected int pageSize;
    
    protected String keyword;

    public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Contacts getContacts() {
		return contacts;
	}

	public void setContacts(Contacts contacts) {
		this.contacts = contacts;
	}

	public CarInfo getCarInfo() {
		return carInfo;
	}

	public void setCarInfo(CarInfo carInfo) {
		this.carInfo = carInfo;
	}

	public Reception getReception() {
		return reception;
	}

	public void setReception(Reception reception) {
		this.reception = reception;
	}

	public Settlement getSettlement() {
		return settlement;
	}

	public void setSettlement(Settlement settlement) {
		this.settlement = settlement;
	}

	public List<InfoRelayWithBLOBs> getInfoRelays() {
		return infoRelays;
	}

	public void setInfoRelays(List<InfoRelayWithBLOBs> infoRelays) {
		this.infoRelays = infoRelays;
	}

	public List<Dispatching> getDispatchings() {
		return dispatchings;
	}

	public void setDispatchings(List<Dispatching> dispatchings) {
		this.dispatchings = dispatchings;
	}

	public List<PartOffer> getPartOffers() {
		return partOffers;
	}

	public void setPartOffers(List<PartOffer> partOffers) {
		this.partOffers = partOffers;
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}



    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId == null ? null : carId.trim();
    }

    public String getCarNum1() {
        return carNum1;
    }

    public void setCarNum1(String carNum1) {
        this.carNum1 = carNum1 == null ? null : carNum1.trim();
    }

    public String getCarNum2() {
        return carNum2;
    }

    public void setCarNum2(String carNum2) {
        this.carNum2 = carNum2 == null ? null : carNum2.trim();
    }

    public String getCarNum3() {
        return carNum3;
    }

    public void setCarNum3(String carNum3) {
        this.carNum3 = carNum3 == null ? null : carNum3.trim();
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType == null ? null : serviceType.trim();
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId == null ? null : contactId.trim();
    }

    public String getServiceConsultant() {
        return serviceConsultant;
    }

    public void setServiceConsultant(String serviceConsultant) {
        this.serviceConsultant = serviceConsultant == null ? null : serviceConsultant.trim();
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector == null ? null : inspector.trim();
    }

    public String getPartTotal() {
        return partTotal;
    }

    public void setPartTotal(String partTotal) {
        this.partTotal = partTotal == null ? null : partTotal.trim();
    }

    public String getHoursTotal() {
        return hoursTotal;
    }

    public void setHoursTotal(String hoursTotal) {
        this.hoursTotal = hoursTotal == null ? null : hoursTotal.trim();
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total == null ? null : total.trim();
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime == null ? null : inTime.trim();
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime == null ? null : outTime.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getYl1() {
        return yl1;
    }

    public void setYl1(String yl1) {
        this.yl1 = yl1 == null ? null : yl1.trim();
    }

    public String getYl2() {
        return yl2;
    }

    public void setYl2(String yl2) {
        this.yl2 = yl2 == null ? null : yl2.trim();
    }

    public String getYl3() {
        return yl3;
    }

    public void setYl3(String yl3) {
        this.yl3 = yl3 == null ? null : yl3.trim();
    }

    public String getYl4() {
        return yl4;
    }

    public void setYl4(String yl4) {
        this.yl4 = yl4 == null ? null : yl4.trim();
    }

    public String getYl5() {
        return yl5;
    }

    public void setYl5(String yl5) {
        this.yl5 = yl5 == null ? null : yl5.trim();
    }

    public String getYl6() {
        return yl6;
    }

    public void setYl6(String yl6) {
        this.yl6 = yl6 == null ? null : yl6.trim();
    }

    public String getYl7() {
        return yl7;
    }

    public void setYl7(String yl7) {
        this.yl7 = yl7 == null ? null : yl7.trim();
    }

    public String getYl8() {
        return yl8;
    }

    public void setYl8(String yl8) {
        this.yl8 = yl8 == null ? null : yl8.trim();
    }

    public String getYl9() {
        return yl9;
    }

    public void setYl9(String yl9) {
        this.yl9 = yl9 == null ? null : yl9.trim();
    }

    public String getYl10() {
        return yl10;
    }

    public void setYl10(String yl10) {
        this.yl10 = yl10 == null ? null : yl10.trim();
    }

    public String getYl11() {
        return yl11;
    }

    public void setYl11(String yl11) {
        this.yl11 = yl11 == null ? null : yl11.trim();
    }

    public String getYl12() {
        return yl12;
    }

    public void setYl12(String yl12) {
        this.yl12 = yl12 == null ? null : yl12.trim();
    }

    public String getYl13() {
        return yl13;
    }

    public void setYl13(String yl13) {
        this.yl13 = yl13 == null ? null : yl13.trim();
    }

    public String getYl14() {
        return yl14;
    }

    public void setYl14(String yl14) {
        this.yl14 = yl14 == null ? null : yl14.trim();
    }

    public String getYl15() {
        return yl15;
    }

    public void setYl15(String yl15) {
        this.yl15 = yl15 == null ? null : yl15.trim();
    }

    public String getYl16() {
        return yl16;
    }

    public void setYl16(String yl16) {
        this.yl16 = yl16 == null ? null : yl16.trim();
    }

    public String getYl17() {
        return yl17;
    }

    public void setYl17(String yl17) {
        this.yl17 = yl17 == null ? null : yl17.trim();
    }

    public String getYl18() {
        return yl18;
    }

    public void setYl18(String yl18) {
        this.yl18 = yl18 == null ? null : yl18.trim();
    }

    public String getYl19() {
        return yl19;
    }

    public void setYl19(String yl19) {
        this.yl19 = yl19 == null ? null : yl19.trim();
    }

    public String getYl20() {
        return yl20;
    }

    public void setYl20(String yl20) {
        this.yl20 = yl20 == null ? null : yl20.trim();
    }
}