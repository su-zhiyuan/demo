package com.qppi.crud.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.Dictionaries;
import com.qppi.crud.bean.DictionariesExample;
import com.qppi.crud.dao.DictionariesMapper;

@Service
public class DictionariesService {

	@Autowired
	private DictionariesMapper dictionariesMapper;
	
	public String selectURL() {
		String path = "path";
		String URL = "";
		DictionariesExample example = new DictionariesExample();
		DictionariesExample.Criteria criteria = example.createCriteria();
		criteria.andBianmaEqualTo(path);
		List<Dictionaries> list = dictionariesMapper.selectByExample(example);
		if(list.size()>0){
			String dictionariesId = list.get(0).getDictionariesId();
			List<Dictionaries> listson = selectSon(dictionariesId);
			for (Dictionaries dictionaries : listson) {
				String bz = dictionaries.getBz();
				String bianma = dictionaries.getBianma();
				if(bianma.equals("fuwupath")){
					URL = bz;
				}
			}
		}
		return URL;
	}
	
	public String selectPATH() {
		String path = "path";
		String PATH = "";
		DictionariesExample example = new DictionariesExample();
		DictionariesExample.Criteria criteria = example.createCriteria();
		criteria.andBianmaEqualTo(path);
		List<Dictionaries> list = dictionariesMapper.selectByExample(example);
		if(list.size()>0){
			String dictionariesId = list.get(0).getDictionariesId();
			List<Dictionaries> listson = selectSon(dictionariesId);
			for (Dictionaries dictionaries : listson) {
				String bz = dictionaries.getBz();
				String bianma = dictionaries.getBianma();
				if(bianma.equals("bendipath")){
					PATH = bz;
				}
			}
		}
		return PATH;
	}

	//根据父级ID查找子集
	public List<Dictionaries> selectSon(String dictionariesId) {
		DictionariesExample example = new DictionariesExample();
		DictionariesExample.Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(dictionariesId);
		List<Dictionaries> list = dictionariesMapper.selectByExample(example);
		return list;
	}

	public String selectWxURL() {
		String path = "path";
		String WxPATH = "";
		DictionariesExample example = new DictionariesExample();
		DictionariesExample.Criteria criteria = example.createCriteria();
		criteria.andBianmaEqualTo(path);
		List<Dictionaries> list = dictionariesMapper.selectByExample(example);
		if(list.size()>0){
			String dictionariesId = list.get(0).getDictionariesId();
			List<Dictionaries> listson = selectSon(dictionariesId);
			for (Dictionaries dictionaries : listson) {
				String bz = dictionaries.getBz();
				String bianma = dictionaries.getBianma();
				if(bianma.equals("wxpath")){
					WxPATH = bz;
				}
			}
		}
		return WxPATH;
	}

	public String selectYingWen(String roletype,String companyId) {
		String path = "jsleixin";
		String YW = "";
		DictionariesExample example = new DictionariesExample();
		DictionariesExample.Criteria criteria = example.createCriteria();
		criteria.andBianmaEqualTo(path);
		List<Dictionaries> list = dictionariesMapper.selectByExample(example);
		//查出公司
		if(list.size()>0){
			String dictionariesId = list.get(0).getDictionariesId();
			List<Dictionaries> listson = selectSon(dictionariesId);
			for (Dictionaries dic : listson) {
				//找公司下的职位
				if(dic.getNameEn().equals(companyId)){
					String id = dic.getDictionariesId();
					List<Dictionaries> son = selectSon(id);
					if(son.size()>0){
						for (Dictionaries d : son) {
							String name = d.getName();
							String nameEn = d.getNameEn();
							if(name.equals(roletype)){
								YW = nameEn;
							}
						}
					}
				}
			}
		}
		return YW;
	}

	public List<Dictionaries> selectAllRoot() {
		String path = "root";
		DictionariesExample example = new DictionariesExample();
		DictionariesExample.Criteria criteria = example.createCriteria();
		criteria.andBianmaEqualTo(path);
		List<Dictionaries> list = dictionariesMapper.selectByExample(example);
		if(list.size()>0){
			String dictionariesId = list.get(0).getDictionariesId();
			List<Dictionaries> listson = selectSon(dictionariesId);
			return listson;
		}
		return null;
	}

	public String selectQuanXian(String roletype) {
		DictionariesExample example = new DictionariesExample();
		DictionariesExample.Criteria criteria = example.createCriteria();
		if(null != roletype && !"".equals(roletype)){
			criteria.andNameEqualTo(roletype);
			List<Dictionaries> list = dictionariesMapper.selectByExample(example);
			if(list.size()>0){
				return list.get(0).getNameEn();
			}
		}
		return null;
	}

	public Dictionaries selectByBianma(String quanxian) {
		DictionariesExample example = new DictionariesExample();
		DictionariesExample.Criteria criteria = example.createCriteria();
		criteria.andBianmaEqualTo(quanxian);
		List<Dictionaries> list = dictionariesMapper.selectByExample(example);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public Map<String,Dictionaries> selectAllXiangxi(String companyId,String zuishangji) {
		Map<String,Dictionaries> reMap = new HashMap<String,Dictionaries>();
		
		DictionariesExample example = new DictionariesExample();
		DictionariesExample.Criteria criteria = example.createCriteria();
		criteria.andBianmaEqualTo(zuishangji);
		List<Dictionaries> list = dictionariesMapper.selectByExample(example);
		if(list.size()>0){
			String dictionariesId = list.get(0).getDictionariesId();
			List<Dictionaries> listson = selectSon(dictionariesId);
			for (Dictionaries dic : listson) {
				String fuheName = dic.getBianma();
				String fuheId = dic.getDictionariesId();
				List<Dictionaries> son = selectSon(fuheId);
				for (Dictionaries d : son) {
					if(d.getNameEn().equals(companyId)){
						reMap.put(fuheName, d);
					}
				}
			}
		}
		return reMap;
	}

	public List<Dictionaries> getRoleByCompanyId(String companyId) {
		String path = "jsleixin";
		DictionariesExample example = new DictionariesExample();
		DictionariesExample.Criteria criteria = example.createCriteria();
		criteria.andBianmaEqualTo(path);
		List<Dictionaries> list = dictionariesMapper.selectByExample(example);
		//查出公司
		if(list.size()>0){
			String dictionariesId = list.get(0).getDictionariesId();
			List<Dictionaries> listson = selectSon(dictionariesId);
			for (Dictionaries dic : listson) {
				//找公司下的职位
				if(dic.getNameEn().equals(companyId)){
					String id = dic.getDictionariesId();
					List<Dictionaries> son = selectSon(id);
					return son;
				}
			}
		}
		return null;
	}

	public String selectRiZhiURL() {
		String path = "path";
		String URL = "";
		DictionariesExample example = new DictionariesExample();
		DictionariesExample.Criteria criteria = example.createCriteria();
		criteria.andBianmaEqualTo(path);
		List<Dictionaries> list = dictionariesMapper.selectByExample(example);
		if(list.size()>0){
			String dictionariesId = list.get(0).getDictionariesId();
			List<Dictionaries> listson = selectSon(dictionariesId);
			for (Dictionaries dictionaries : listson) {
				String bz = dictionaries.getBz();
				String bianma = dictionaries.getBianma();
				if(bianma.equals("rizhipath")){
					URL = bz;
				}
			}
		}
		return URL;
	}

	public List<Dictionaries> selectBumenById(String companyId) {
		String path = "gsid";
		DictionariesExample example = new DictionariesExample();
		DictionariesExample.Criteria criteria = example.createCriteria();
		criteria.andBianmaEqualTo(path);
		List<Dictionaries> list = dictionariesMapper.selectByExample(example);
		//查出公司
		if(list.size()>0){
			String dictionariesId = list.get(0).getDictionariesId();
			List<Dictionaries> listson = selectSon(dictionariesId);
			for (Dictionaries dic : listson) {
				//找公司下的职位
				if(dic.getNameEn().equals(companyId)){
					String id = dic.getDictionariesId();
					List<Dictionaries> son = selectSon(id);
					return son;
				}
			}
		}
		return null;
	}

	
}
