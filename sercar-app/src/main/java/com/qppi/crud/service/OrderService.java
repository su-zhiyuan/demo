package com.qppi.crud.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.CarInfo;
import com.qppi.crud.bean.Contacts;
import com.qppi.crud.bean.Dispatching;
import com.qppi.crud.bean.DispatchingExample;
import com.qppi.crud.bean.Dsmx;
import com.qppi.crud.bean.DsmxExample;
import com.qppi.crud.bean.GoodsRC;
import com.qppi.crud.bean.GoodsRCExample;
import com.qppi.crud.bean.InfoRelayExample;
import com.qppi.crud.bean.InfoRelayWithBLOBs;
import com.qppi.crud.bean.Order;
import com.qppi.crud.bean.OrderExample;
import com.qppi.crud.bean.PartOffer;
import com.qppi.crud.bean.PartOfferExample;
import com.qppi.crud.bean.Pomx;
import com.qppi.crud.bean.PomxExample;
import com.qppi.crud.bean.Purchase;
import com.qppi.crud.bean.PurchaseExample;
import com.qppi.crud.bean.ReceptionExample;
import com.qppi.crud.bean.ReceptionWithBLOBs;
import com.qppi.crud.bean.SType;
import com.qppi.crud.bean.Settlement;
import com.qppi.crud.bean.SettlementExample;
import com.qppi.crud.bean.UserInfo;
import com.qppi.crud.bean.UserInfoExample;
import com.qppi.crud.dao.CarInfoMapper;
import com.qppi.crud.dao.DispatchingMapper;
import com.qppi.crud.dao.DsmxMapper;
import com.qppi.crud.dao.GoodsRCMapper;
import com.qppi.crud.dao.InfoRelayMapper;
import com.qppi.crud.dao.OrderMapper;
import com.qppi.crud.dao.PartOfferMapper;
import com.qppi.crud.dao.PomxMapper;
import com.qppi.crud.dao.PurchaseMapper;
import com.qppi.crud.dao.ReceptionMapper;
import com.qppi.crud.dao.STypeMapper;
import com.qppi.crud.dao.SettlementMapper;
import com.qppi.crud.dao.UserInfoMapper;
import com.qppi.crud.utils.MySelfUtil;

@Service
public class OrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private CarInfoMapper carInfoMapper;
	@Autowired
	private ReceptionMapper receptionMapper;
	@Autowired
	private InfoRelayMapper infoRelayMapper;
	@Autowired
	private DispatchingMapper dispatchingMapper;
	@Autowired
	private DsmxMapper dsmxMapper;
	@Autowired
	private PartOfferMapper partOfferMapper;
	@Autowired
	private PomxMapper pomxMapper;
	@Autowired
	private PurchaseMapper purchaseMapper;
	@Autowired
	private SettlementMapper settlementMapper;
	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private GoodsRCMapper goodsRCMapper;
	@Autowired
	private ContactsService contactsService;
	@Autowired
	private STypeMapper sTypeMapper;

	public boolean add(Order order) {
		try{
			orderMapper.insertSelective(order);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(Order order) {
		try{
			orderMapper.updateByPrimaryKeySelective(order);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public List<Order> getList(Order order,int page,String keyword) {
			
			order.setPageSize(10);
			order.setKeyword(keyword);
			order.setStartRow((page-1)*10);
			List<Order> list = orderMapper.selectOrder(order);
			
			UserInfoExample userExample = new UserInfoExample();
			UserInfoExample.Criteria userCriteria = userExample.createCriteria();
			for (Order or : list) {
				String creater = or.getCreateBy();
				userCriteria.andUsernameEqualTo(creater);
				List<UserInfo> list2 = userInfoMapper.selectByExample(userExample);
				if(list2.size()>0){
					or.setUserInfo(list2.get(0));
				}
			}
			return list;
	}
	
	public Order queryOrder(Order order){
		order = orderMapper.selectByPrimaryKey(order.getOrderId());
		if(order != null){
			//设置汽车信息
			CarInfo carInfo = carInfoMapper.selectByPrimaryKey(order.getCarId());
			if(carInfo != null){
				String carNum1 = carInfo.getCarNum1();
				if(carNum1.contains("无法获取")){
					carInfo.setCarNum1("无法获取");
				}
				order.setCarInfo(carInfo);
			}
			
			Contacts contacts = contactsService.selectById(order.getContactId());
			if(contacts != null){
				order.setContacts(contacts);
			}

			//设置接车单
			ReceptionExample exampleRec = new ReceptionExample();
			ReceptionExample.Criteria criteriaRec = exampleRec.createCriteria();
			criteriaRec.andOrderIdEqualTo(order.getOrderId());
			List<ReceptionWithBLOBs> listRec = receptionMapper.selectByExampleWithBLOBs(exampleRec);
			if(listRec.size() == 1){
				ReceptionWithBLOBs re = listRec.get(0);
				/*byte[] mileageImg = re.getMileageImg();
				if(mileageImg != null && !"".equals(mileageImg)){
					String imgPath = new String(mileageImg);
					re.setYl5(imgPath);
				}*/
				byte[] authorizationVoucher = re.getAuthorizationVoucher();
				if(authorizationVoucher != null){
					String s = new String(authorizationVoucher);
					String replaceAll = s.replaceAll("[+]", "%2B");
					re.setYl20(replaceAll);
				}
				order.setReception(re);
			}
			
			//设置信息反馈单
			InfoRelayExample exampleInf = new InfoRelayExample();
			InfoRelayExample.Criteria criteriaInf = exampleInf.createCriteria();
			criteriaInf.andOrderIdEqualTo(order.getOrderId());
			List<InfoRelayWithBLOBs> listInf = infoRelayMapper.selectByExampleWithBLOBs(exampleInf);
			if(listInf.size() > 0){
				order.setInfoRelays(listInf);
			}
			
			//设置配件报价单
			PartOfferExample examplePar = new PartOfferExample();
			PartOfferExample.Criteria criteriaPar = examplePar.createCriteria();
			criteriaPar.andOrderIdEqualTo(order.getOrderId());
			List<PartOffer> listPar = partOfferMapper.selectByExample(examplePar);
			
			List<String> pList = new ArrayList<String>();
			double count = 0;
			if(listPar.size() > 0){
				for(PartOffer par : listPar){
					
					String partofferId = par.getPartofferId();
					pList.add(partofferId);
					
					String total = par.getTotal();
					
					if(!"null".equals(total) && total != null){
						double parseInt = Double.parseDouble(total);
						count = count + parseInt;
					}
					
					PomxExample examplePMX = new PomxExample();
					PomxExample.Criteria criteriaPMX = examplePMX.createCriteria();
					criteriaPMX.andPartOfferIdEqualTo(par.getPartofferId());
					List<Pomx> listPMX = pomxMapper.selectByExample(examplePMX);
					if(listPMX.size() > 0){
						par.setPomxs(listPMX);
					}
					DsmxExample exampleDMX = new DsmxExample();
					DsmxExample.Criteria criteriaDMX = exampleDMX.createCriteria();
					criteriaDMX.andYl1EqualTo(par.getPartofferId());
					List<Dsmx> listDMX = dsmxMapper.selectByExample(exampleDMX);
					if(listDMX.size() > 0){
						par.setDsmxs(listDMX);
					}
					
					String basepath = par.getYl7();
					if(basepath != null){
						String str = MySelfUtil.GetImageStr(basepath);
						String replaceAll = str.replaceAll("[+]", "%2B");
						par.setYl20("data:image/png;base64,"+replaceAll);
					}
				}
				order.setTotal(count+"");
				order.setPartOffers(listPar);
			}
			
			//设置派工单
			DispatchingExample exampleDis = new DispatchingExample();
			DispatchingExample.Criteria criteriaDis = exampleDis.createCriteria();
			criteriaDis.andOrderIdEqualTo(order.getOrderId());
			List<Dispatching> listDis = dispatchingMapper.selectByExample(exampleDis);
			if(listDis.size() > 0){
				for(Dispatching dis : listDis){
					DsmxExample exampleDs = new DsmxExample();
					DsmxExample.Criteria criteriaDs = exampleDs.createCriteria();
					criteriaDs.andDispatchingIdEqualTo(dis.getDispatchingId());
					List<Dsmx> dsmxs = dsmxMapper.selectByExample(exampleDs);
					if(dsmxs.size() > 0){
						dis.setDsmxs(dsmxs);
					}
					dis.setParidList(pList);
				}
				order.setDispatchings(listDis);
			}
			
			//设置采购单
			PurchaseExample examplePur = new PurchaseExample();
			PurchaseExample.Criteria criteriaPur = examplePur.createCriteria();
			criteriaPur.andOrderIdEqualTo(order.getOrderId());
			List<Purchase> listPur = purchaseMapper.selectByExample(examplePur);
			if(listPur.size() > 0){
				for(Purchase pur : listPur){
					GoodsRCExample exampleGood = new GoodsRCExample();
					GoodsRCExample.Criteria criteriaGood = exampleGood.createCriteria();
					criteriaGood.andCaigourcIdEqualTo(pur.getPurchaseId());
					List<GoodsRC> list = goodsRCMapper.selectByExample(exampleGood);
					if(list.size() > 0){
						pur.setGoods(list);
					}
				}
				order.setPurchases(listPur);
			}
			
			//设置结算单
			SettlementExample exampleSet = new SettlementExample();
			SettlementExample.Criteria criteriaSet = exampleSet.createCriteria();
			criteriaSet.andOrderIdEqualTo(order.getOrderId());
			List<Settlement> listSet = settlementMapper.selectByExample(exampleSet);
			System.err.println(listSet.size());
			if(listSet.size() > 0){
				Settlement settlement = listSet.get(0);
				List<Dsmx> dsmxs = new ArrayList<Dsmx>();
				String dsmxIdStr = settlement.getYl3();
				if(dsmxIdStr.contains("-")){
					String[] split = dsmxIdStr.split("-");
					for (String dsmxId : split) {
						if(dsmxId != null && !"".equals(dsmxId)){
							Dsmx dsmx = dsmxMapper.selectByPrimaryKey(dsmxId);
							dsmxs.add(dsmx);
						}
					}
				}else{
					Dsmx dsmx = dsmxMapper.selectByPrimaryKey(dsmxIdStr);
					dsmxs.add(dsmx);
				}
				List<Pomx> pomxs = new ArrayList<Pomx>();
				String pomxIdStr = settlement.getYl4();
				if(pomxIdStr.contains("-")){
					String[] split = pomxIdStr.split("-");
					for (String pomxId : split) {
						if(pomxId != null && !"".equals(pomxId)){
							Pomx pomx = pomxMapper.selectByPrimaryKey(pomxId);
							pomxs.add(pomx);
						}
					}
				}else{
					Pomx pomx = pomxMapper.selectByPrimaryKey(pomxIdStr);
					pomxs.add(pomx);
				}
				settlement.setDsmxs(dsmxs);
				settlement.setPomxs(pomxs);
				
				List<String> pidList = new ArrayList<String>();
				List<String> disStypeList = new ArrayList<String>();
				PartOfferExample parexample = new PartOfferExample();
				PartOfferExample.Criteria parcriteria = parexample.createCriteria();
				parcriteria.andOrderIdEqualTo(order.getOrderId());
				List<PartOffer> parList = partOfferMapper.selectByExample(parexample);
				for (PartOffer partOffer : parList) {
					String partofferId = partOffer.getPartofferId();
					pidList.add(partofferId);
					String yl4 = partOffer.getYl4();
					if(yl4!= null){
						SType sType = sTypeMapper.selectByPrimaryKey(yl4);
						if(sType != null){
							disStypeList.add(sType.getTypeName());
						}
					}
				}
				
				List<String> newTypeList = MySelfUtil.removeDuplicate(disStypeList);
				
				List<String> disList = new ArrayList<String>();
				List<String> disNameList = new ArrayList<String>();
				DispatchingExample disExample = new DispatchingExample();
				DispatchingExample.Criteria disCriteria = disExample.createCriteria();
				disCriteria.andOrderIdEqualTo(order.getOrderId());
				List<Dispatching> dispatchings = dispatchingMapper.selectByExample(disExample);
				for (Dispatching dispatching : dispatchings) {
					String dispatchingId = dispatching.getDispatchingId();
					disList.add(dispatchingId);
					String yl2 = dispatching.getYl2();
					disNameList.add(yl2);
				}
				
				settlement.setDisList(disList);
				settlement.setPidList(pidList);
				settlement.setDisName(disNameList);
				settlement.setDisType(newTypeList);
				
				order.setSettlement(settlement);
			}else {
				order.setSettlement(null);
			}
			
			UserInfoExample userExample = new UserInfoExample();
			UserInfoExample.Criteria userCriteria = userExample.createCriteria();
			String creater = order.getCreateBy();
			userCriteria.andUsernameEqualTo(creater);
			List<UserInfo> list2 = userInfoMapper.selectByExample(userExample);
			if(list2.size()>0){
				order.setUserInfo(list2.get(0));
			}
			return order;
		}
		return null;
	}

	public Order selectById(String orderId) {
		return orderMapper.selectByPrimaryKey(orderId);
	}

	//根据联系人查订单
	public List<Order> selectByContast(String contactId) {
		OrderExample example = new OrderExample();
		example.setOrderByClause("CREATE_TIME desc");
		OrderExample.Criteria criteria = example.createCriteria();
		criteria.andContactIdEqualTo(contactId);
		List<Order> list = orderMapper.selectExample(example);
		return list;
	}

	public List<Order> selectByWX(String wxid) {
		OrderExample example = new OrderExample();
		OrderExample.Criteria criteria = example.createCriteria();
		criteria.andYl3EqualTo(wxid);
		List<Order> list = orderMapper.selectExample(example);
		return list;
	}

	public boolean deleteReception(Order order) {
		try {
			orderMapper.deleteByPrimaryKey(order.getOrderId());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	public List<Order> selectByStatus(Order order) {
		OrderExample example = new OrderExample();
		example.setOrderByClause("CREATE_TIME desc");
		OrderExample.Criteria criteria = example.createCriteria();
		String status = order.getStatus();
		if(status != null && !"".equals(status)){
			criteria.andStatusEqualTo(status);
		}
		String yl10 = order.getYl10();
		if(yl10 != null && !"".equals(yl10)){
			criteria.andYl10EqualTo(yl10);
		}
		List<Order> list = orderMapper.selectExample(example);
		return list;
	}

	public List<Order> selectByCePai(Order order) {
		OrderExample example = new OrderExample();
		OrderExample.Criteria criteria = example.createCriteria();
		
		String cepai = order.getCarNum1();
		if(cepai != null && !"".equals(cepai)){
			criteria.andCarNum1EqualTo(cepai);
		}
		String fadongji = order.getCarNum2();
		if(fadongji != null && !"".equals(fadongji)){
			criteria.andCarNum2EqualTo(fadongji);
		}
		String cejia = order.getCarNum3();
		if(cejia != null && !"".equals(cejia)){
			criteria.andCarNum3EqualTo(cejia);
		}
		String contactId = order.getContactId();
		if(contactId != null && !"".equals(contactId)){
			criteria.andContactIdEqualTo(contactId);
		}
		String status = order.getStatus();
		if(status != null && !"".equals(status)){
			criteria.andStatusEqualTo(status);
		}
		List<Order> list = orderMapper.selectExample(example);
		return list;
	}

	public List<Order> selectByPerson(UserInfo userInfo) {
		OrderExample example = new OrderExample();
		OrderExample.Criteria criteria = example.createCriteria();
		
		String username = userInfo.getUsername();
		if(username != null && !"".equals(username)){
			criteria.andYl15EqualTo(username);
		}
		String yl10 = userInfo.getYl10();
		if(yl10 != null && !"".equals(yl10)){
			criteria.andYl10EqualTo(yl10);
		}
		return orderMapper.selectExample(example);
	}
}
