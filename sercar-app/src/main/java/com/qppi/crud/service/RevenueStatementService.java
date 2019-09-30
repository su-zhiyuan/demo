package com.qppi.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.RevenueStatement;
import com.qppi.crud.dao.RevenueStatementMapper;

@Service
public class RevenueStatementService {

	@Autowired
	private RevenueStatementMapper revenueStatementMapper;

	public boolean add(RevenueStatement revenueStatement) {
		try {
			revenueStatementMapper.insert(revenueStatement);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
