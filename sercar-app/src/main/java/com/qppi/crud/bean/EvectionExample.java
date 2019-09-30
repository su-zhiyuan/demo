package com.qppi.crud.bean;

import java.util.ArrayList;
import java.util.List;

public class EvectionExample {
    protected String orderByClause;

    protected boolean distinct;
    
    protected int startRow;
    
    protected int pageSize;
   
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




    protected List<Criteria> oredCriteria;

    public EvectionExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andEvectionIdIsNull() {
            addCriterion("EVECTION_ID is null");
            return (Criteria) this;
        }

        public Criteria andEvectionIdIsNotNull() {
            addCriterion("EVECTION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andEvectionIdEqualTo(String value) {
            addCriterion("EVECTION_ID =", value, "evectionId");
            return (Criteria) this;
        }

        public Criteria andEvectionIdNotEqualTo(String value) {
            addCriterion("EVECTION_ID <>", value, "evectionId");
            return (Criteria) this;
        }

        public Criteria andEvectionIdGreaterThan(String value) {
            addCriterion("EVECTION_ID >", value, "evectionId");
            return (Criteria) this;
        }

        public Criteria andEvectionIdGreaterThanOrEqualTo(String value) {
            addCriterion("EVECTION_ID >=", value, "evectionId");
            return (Criteria) this;
        }

        public Criteria andEvectionIdLessThan(String value) {
            addCriterion("EVECTION_ID <", value, "evectionId");
            return (Criteria) this;
        }

        public Criteria andEvectionIdLessThanOrEqualTo(String value) {
            addCriterion("EVECTION_ID <=", value, "evectionId");
            return (Criteria) this;
        }

        public Criteria andEvectionIdLike(String value) {
            addCriterion("EVECTION_ID like", value, "evectionId");
            return (Criteria) this;
        }

        public Criteria andEvectionIdNotLike(String value) {
            addCriterion("EVECTION_ID not like", value, "evectionId");
            return (Criteria) this;
        }

        public Criteria andEvectionIdIn(List<String> values) {
            addCriterion("EVECTION_ID in", values, "evectionId");
            return (Criteria) this;
        }

        public Criteria andEvectionIdNotIn(List<String> values) {
            addCriterion("EVECTION_ID not in", values, "evectionId");
            return (Criteria) this;
        }

        public Criteria andEvectionIdBetween(String value1, String value2) {
            addCriterion("EVECTION_ID between", value1, value2, "evectionId");
            return (Criteria) this;
        }

        public Criteria andEvectionIdNotBetween(String value1, String value2) {
            addCriterion("EVECTION_ID not between", value1, value2, "evectionId");
            return (Criteria) this;
        }

        public Criteria andCauseIsNull() {
            addCriterion("cause is null");
            return (Criteria) this;
        }

        public Criteria andCauseIsNotNull() {
            addCriterion("cause is not null");
            return (Criteria) this;
        }

        public Criteria andCauseEqualTo(String value) {
            addCriterion("cause =", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseNotEqualTo(String value) {
            addCriterion("cause <>", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseGreaterThan(String value) {
            addCriterion("cause >", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseGreaterThanOrEqualTo(String value) {
            addCriterion("cause >=", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseLessThan(String value) {
            addCriterion("cause <", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseLessThanOrEqualTo(String value) {
            addCriterion("cause <=", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseLike(String value) {
            addCriterion("cause like", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseNotLike(String value) {
            addCriterion("cause not like", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseIn(List<String> values) {
            addCriterion("cause in", values, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseNotIn(List<String> values) {
            addCriterion("cause not in", values, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseBetween(String value1, String value2) {
            addCriterion("cause between", value1, value2, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseNotBetween(String value1, String value2) {
            addCriterion("cause not between", value1, value2, "cause");
            return (Criteria) this;
        }

        public Criteria andJtgjIsNull() {
            addCriterion("jtgj is null");
            return (Criteria) this;
        }

        public Criteria andJtgjIsNotNull() {
            addCriterion("jtgj is not null");
            return (Criteria) this;
        }

        public Criteria andJtgjEqualTo(String value) {
            addCriterion("jtgj =", value, "jtgj");
            return (Criteria) this;
        }

        public Criteria andJtgjNotEqualTo(String value) {
            addCriterion("jtgj <>", value, "jtgj");
            return (Criteria) this;
        }

        public Criteria andJtgjGreaterThan(String value) {
            addCriterion("jtgj >", value, "jtgj");
            return (Criteria) this;
        }

        public Criteria andJtgjGreaterThanOrEqualTo(String value) {
            addCriterion("jtgj >=", value, "jtgj");
            return (Criteria) this;
        }

        public Criteria andJtgjLessThan(String value) {
            addCriterion("jtgj <", value, "jtgj");
            return (Criteria) this;
        }

        public Criteria andJtgjLessThanOrEqualTo(String value) {
            addCriterion("jtgj <=", value, "jtgj");
            return (Criteria) this;
        }

        public Criteria andJtgjLike(String value) {
            addCriterion("jtgj like", value, "jtgj");
            return (Criteria) this;
        }

        public Criteria andJtgjNotLike(String value) {
            addCriterion("jtgj not like", value, "jtgj");
            return (Criteria) this;
        }

        public Criteria andJtgjIn(List<String> values) {
            addCriterion("jtgj in", values, "jtgj");
            return (Criteria) this;
        }

        public Criteria andJtgjNotIn(List<String> values) {
            addCriterion("jtgj not in", values, "jtgj");
            return (Criteria) this;
        }

        public Criteria andJtgjBetween(String value1, String value2) {
            addCriterion("jtgj between", value1, value2, "jtgj");
            return (Criteria) this;
        }

        public Criteria andJtgjNotBetween(String value1, String value2) {
            addCriterion("jtgj not between", value1, value2, "jtgj");
            return (Criteria) this;
        }

        public Criteria andWayIsNull() {
            addCriterion("way is null");
            return (Criteria) this;
        }

        public Criteria andWayIsNotNull() {
            addCriterion("way is not null");
            return (Criteria) this;
        }

        public Criteria andWayEqualTo(String value) {
            addCriterion("way =", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayNotEqualTo(String value) {
            addCriterion("way <>", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayGreaterThan(String value) {
            addCriterion("way >", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayGreaterThanOrEqualTo(String value) {
            addCriterion("way >=", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayLessThan(String value) {
            addCriterion("way <", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayLessThanOrEqualTo(String value) {
            addCriterion("way <=", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayLike(String value) {
            addCriterion("way like", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayNotLike(String value) {
            addCriterion("way not like", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayIn(List<String> values) {
            addCriterion("way in", values, "way");
            return (Criteria) this;
        }

        public Criteria andWayNotIn(List<String> values) {
            addCriterion("way not in", values, "way");
            return (Criteria) this;
        }

        public Criteria andWayBetween(String value1, String value2) {
            addCriterion("way between", value1, value2, "way");
            return (Criteria) this;
        }

        public Criteria andWayNotBetween(String value1, String value2) {
            addCriterion("way not between", value1, value2, "way");
            return (Criteria) this;
        }

        public Criteria andCitycfIsNull() {
            addCriterion("citycf is null");
            return (Criteria) this;
        }

        public Criteria andCitycfIsNotNull() {
            addCriterion("citycf is not null");
            return (Criteria) this;
        }

        public Criteria andCitycfEqualTo(String value) {
            addCriterion("citycf =", value, "citycf");
            return (Criteria) this;
        }

        public Criteria andCitycfNotEqualTo(String value) {
            addCriterion("citycf <>", value, "citycf");
            return (Criteria) this;
        }

        public Criteria andCitycfGreaterThan(String value) {
            addCriterion("citycf >", value, "citycf");
            return (Criteria) this;
        }

        public Criteria andCitycfGreaterThanOrEqualTo(String value) {
            addCriterion("citycf >=", value, "citycf");
            return (Criteria) this;
        }

        public Criteria andCitycfLessThan(String value) {
            addCriterion("citycf <", value, "citycf");
            return (Criteria) this;
        }

        public Criteria andCitycfLessThanOrEqualTo(String value) {
            addCriterion("citycf <=", value, "citycf");
            return (Criteria) this;
        }

        public Criteria andCitycfLike(String value) {
            addCriterion("citycf like", value, "citycf");
            return (Criteria) this;
        }

        public Criteria andCitycfNotLike(String value) {
            addCriterion("citycf not like", value, "citycf");
            return (Criteria) this;
        }

        public Criteria andCitycfIn(List<String> values) {
            addCriterion("citycf in", values, "citycf");
            return (Criteria) this;
        }

        public Criteria andCitycfNotIn(List<String> values) {
            addCriterion("citycf not in", values, "citycf");
            return (Criteria) this;
        }

        public Criteria andCitycfBetween(String value1, String value2) {
            addCriterion("citycf between", value1, value2, "citycf");
            return (Criteria) this;
        }

        public Criteria andCitycfNotBetween(String value1, String value2) {
            addCriterion("citycf not between", value1, value2, "citycf");
            return (Criteria) this;
        }

        public Criteria andCityddIsNull() {
            addCriterion("citydd is null");
            return (Criteria) this;
        }

        public Criteria andCityddIsNotNull() {
            addCriterion("citydd is not null");
            return (Criteria) this;
        }

        public Criteria andCityddEqualTo(String value) {
            addCriterion("citydd =", value, "citydd");
            return (Criteria) this;
        }

        public Criteria andCityddNotEqualTo(String value) {
            addCriterion("citydd <>", value, "citydd");
            return (Criteria) this;
        }

        public Criteria andCityddGreaterThan(String value) {
            addCriterion("citydd >", value, "citydd");
            return (Criteria) this;
        }

        public Criteria andCityddGreaterThanOrEqualTo(String value) {
            addCriterion("citydd >=", value, "citydd");
            return (Criteria) this;
        }

        public Criteria andCityddLessThan(String value) {
            addCriterion("citydd <", value, "citydd");
            return (Criteria) this;
        }

        public Criteria andCityddLessThanOrEqualTo(String value) {
            addCriterion("citydd <=", value, "citydd");
            return (Criteria) this;
        }

        public Criteria andCityddLike(String value) {
            addCriterion("citydd like", value, "citydd");
            return (Criteria) this;
        }

        public Criteria andCityddNotLike(String value) {
            addCriterion("citydd not like", value, "citydd");
            return (Criteria) this;
        }

        public Criteria andCityddIn(List<String> values) {
            addCriterion("citydd in", values, "citydd");
            return (Criteria) this;
        }

        public Criteria andCityddNotIn(List<String> values) {
            addCriterion("citydd not in", values, "citydd");
            return (Criteria) this;
        }

        public Criteria andCityddBetween(String value1, String value2) {
            addCriterion("citydd between", value1, value2, "citydd");
            return (Criteria) this;
        }

        public Criteria andCityddNotBetween(String value1, String value2) {
            addCriterion("citydd not between", value1, value2, "citydd");
            return (Criteria) this;
        }

        public Criteria andStarttimeIsNull() {
            addCriterion("starttime is null");
            return (Criteria) this;
        }

        public Criteria andStarttimeIsNotNull() {
            addCriterion("starttime is not null");
            return (Criteria) this;
        }

        public Criteria andStarttimeEqualTo(String value) {
            addCriterion("starttime =", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotEqualTo(String value) {
            addCriterion("starttime <>", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeGreaterThan(String value) {
            addCriterion("starttime >", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeGreaterThanOrEqualTo(String value) {
            addCriterion("starttime >=", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeLessThan(String value) {
            addCriterion("starttime <", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeLessThanOrEqualTo(String value) {
            addCriterion("starttime <=", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeLike(String value) {
            addCriterion("starttime like", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotLike(String value) {
            addCriterion("starttime not like", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeIn(List<String> values) {
            addCriterion("starttime in", values, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotIn(List<String> values) {
            addCriterion("starttime not in", values, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeBetween(String value1, String value2) {
            addCriterion("starttime between", value1, value2, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotBetween(String value1, String value2) {
            addCriterion("starttime not between", value1, value2, "starttime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNull() {
            addCriterion("endtime is null");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNotNull() {
            addCriterion("endtime is not null");
            return (Criteria) this;
        }

        public Criteria andEndtimeEqualTo(String value) {
            addCriterion("endtime =", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotEqualTo(String value) {
            addCriterion("endtime <>", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThan(String value) {
            addCriterion("endtime >", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThanOrEqualTo(String value) {
            addCriterion("endtime >=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThan(String value) {
            addCriterion("endtime <", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThanOrEqualTo(String value) {
            addCriterion("endtime <=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLike(String value) {
            addCriterion("endtime like", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotLike(String value) {
            addCriterion("endtime not like", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIn(List<String> values) {
            addCriterion("endtime in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotIn(List<String> values) {
            addCriterion("endtime not in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeBetween(String value1, String value2) {
            addCriterion("endtime between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotBetween(String value1, String value2) {
            addCriterion("endtime not between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andDaysIsNull() {
            addCriterion("days is null");
            return (Criteria) this;
        }

        public Criteria andDaysIsNotNull() {
            addCriterion("days is not null");
            return (Criteria) this;
        }

        public Criteria andDaysEqualTo(String value) {
            addCriterion("days =", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysNotEqualTo(String value) {
            addCriterion("days <>", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysGreaterThan(String value) {
            addCriterion("days >", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysGreaterThanOrEqualTo(String value) {
            addCriterion("days >=", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysLessThan(String value) {
            addCriterion("days <", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysLessThanOrEqualTo(String value) {
            addCriterion("days <=", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysLike(String value) {
            addCriterion("days like", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysNotLike(String value) {
            addCriterion("days not like", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysIn(List<String> values) {
            addCriterion("days in", values, "days");
            return (Criteria) this;
        }

        public Criteria andDaysNotIn(List<String> values) {
            addCriterion("days not in", values, "days");
            return (Criteria) this;
        }

        public Criteria andDaysBetween(String value1, String value2) {
            addCriterion("days between", value1, value2, "days");
            return (Criteria) this;
        }

        public Criteria andDaysNotBetween(String value1, String value2) {
            addCriterion("days not between", value1, value2, "days");
            return (Criteria) this;
        }

        public Criteria andTxrIsNull() {
            addCriterion("txr is null");
            return (Criteria) this;
        }

        public Criteria andTxrIsNotNull() {
            addCriterion("txr is not null");
            return (Criteria) this;
        }

        public Criteria andTxrEqualTo(String value) {
            addCriterion("txr =", value, "txr");
            return (Criteria) this;
        }

        public Criteria andTxrNotEqualTo(String value) {
            addCriterion("txr <>", value, "txr");
            return (Criteria) this;
        }

        public Criteria andTxrGreaterThan(String value) {
            addCriterion("txr >", value, "txr");
            return (Criteria) this;
        }

        public Criteria andTxrGreaterThanOrEqualTo(String value) {
            addCriterion("txr >=", value, "txr");
            return (Criteria) this;
        }

        public Criteria andTxrLessThan(String value) {
            addCriterion("txr <", value, "txr");
            return (Criteria) this;
        }

        public Criteria andTxrLessThanOrEqualTo(String value) {
            addCriterion("txr <=", value, "txr");
            return (Criteria) this;
        }

        public Criteria andTxrLike(String value) {
            addCriterion("txr like", value, "txr");
            return (Criteria) this;
        }

        public Criteria andTxrNotLike(String value) {
            addCriterion("txr not like", value, "txr");
            return (Criteria) this;
        }

        public Criteria andTxrIn(List<String> values) {
            addCriterion("txr in", values, "txr");
            return (Criteria) this;
        }

        public Criteria andTxrNotIn(List<String> values) {
            addCriterion("txr not in", values, "txr");
            return (Criteria) this;
        }

        public Criteria andTxrBetween(String value1, String value2) {
            addCriterion("txr between", value1, value2, "txr");
            return (Criteria) this;
        }

        public Criteria andTxrNotBetween(String value1, String value2) {
            addCriterion("txr not between", value1, value2, "txr");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andYl1IsNull() {
            addCriterion("yl1 is null");
            return (Criteria) this;
        }

        public Criteria andYl1IsNotNull() {
            addCriterion("yl1 is not null");
            return (Criteria) this;
        }

        public Criteria andYl1EqualTo(String value) {
            addCriterion("yl1 =", value, "yl1");
            return (Criteria) this;
        }

        public Criteria andYl1NotEqualTo(String value) {
            addCriterion("yl1 <>", value, "yl1");
            return (Criteria) this;
        }

        public Criteria andYl1GreaterThan(String value) {
            addCriterion("yl1 >", value, "yl1");
            return (Criteria) this;
        }

        public Criteria andYl1GreaterThanOrEqualTo(String value) {
            addCriterion("yl1 >=", value, "yl1");
            return (Criteria) this;
        }

        public Criteria andYl1LessThan(String value) {
            addCriterion("yl1 <", value, "yl1");
            return (Criteria) this;
        }

        public Criteria andYl1LessThanOrEqualTo(String value) {
            addCriterion("yl1 <=", value, "yl1");
            return (Criteria) this;
        }

        public Criteria andYl1Like(String value) {
            addCriterion("yl1 like", value, "yl1");
            return (Criteria) this;
        }

        public Criteria andYl1NotLike(String value) {
            addCriterion("yl1 not like", value, "yl1");
            return (Criteria) this;
        }

        public Criteria andYl1In(List<String> values) {
            addCriterion("yl1 in", values, "yl1");
            return (Criteria) this;
        }

        public Criteria andYl1NotIn(List<String> values) {
            addCriterion("yl1 not in", values, "yl1");
            return (Criteria) this;
        }

        public Criteria andYl1Between(String value1, String value2) {
            addCriterion("yl1 between", value1, value2, "yl1");
            return (Criteria) this;
        }

        public Criteria andYl1NotBetween(String value1, String value2) {
            addCriterion("yl1 not between", value1, value2, "yl1");
            return (Criteria) this;
        }

        public Criteria andYl2IsNull() {
            addCriterion("yl2 is null");
            return (Criteria) this;
        }

        public Criteria andYl2IsNotNull() {
            addCriterion("yl2 is not null");
            return (Criteria) this;
        }

        public Criteria andYl2EqualTo(String value) {
            addCriterion("yl2 =", value, "yl2");
            return (Criteria) this;
        }

        public Criteria andYl2NotEqualTo(String value) {
            addCriterion("yl2 <>", value, "yl2");
            return (Criteria) this;
        }

        public Criteria andYl2GreaterThan(String value) {
            addCriterion("yl2 >", value, "yl2");
            return (Criteria) this;
        }

        public Criteria andYl2GreaterThanOrEqualTo(String value) {
            addCriterion("yl2 >=", value, "yl2");
            return (Criteria) this;
        }

        public Criteria andYl2LessThan(String value) {
            addCriterion("yl2 <", value, "yl2");
            return (Criteria) this;
        }

        public Criteria andYl2LessThanOrEqualTo(String value) {
            addCriterion("yl2 <=", value, "yl2");
            return (Criteria) this;
        }

        public Criteria andYl2Like(String value) {
            addCriterion("yl2 like", value, "yl2");
            return (Criteria) this;
        }

        public Criteria andYl2NotLike(String value) {
            addCriterion("yl2 not like", value, "yl2");
            return (Criteria) this;
        }

        public Criteria andYl2In(List<String> values) {
            addCriterion("yl2 in", values, "yl2");
            return (Criteria) this;
        }

        public Criteria andYl2NotIn(List<String> values) {
            addCriterion("yl2 not in", values, "yl2");
            return (Criteria) this;
        }

        public Criteria andYl2Between(String value1, String value2) {
            addCriterion("yl2 between", value1, value2, "yl2");
            return (Criteria) this;
        }

        public Criteria andYl2NotBetween(String value1, String value2) {
            addCriterion("yl2 not between", value1, value2, "yl2");
            return (Criteria) this;
        }

        public Criteria andYl3IsNull() {
            addCriterion("yl3 is null");
            return (Criteria) this;
        }

        public Criteria andYl3IsNotNull() {
            addCriterion("yl3 is not null");
            return (Criteria) this;
        }

        public Criteria andYl3EqualTo(String value) {
            addCriterion("yl3 =", value, "yl3");
            return (Criteria) this;
        }

        public Criteria andYl3NotEqualTo(String value) {
            addCriterion("yl3 <>", value, "yl3");
            return (Criteria) this;
        }

        public Criteria andYl3GreaterThan(String value) {
            addCriterion("yl3 >", value, "yl3");
            return (Criteria) this;
        }

        public Criteria andYl3GreaterThanOrEqualTo(String value) {
            addCriterion("yl3 >=", value, "yl3");
            return (Criteria) this;
        }

        public Criteria andYl3LessThan(String value) {
            addCriterion("yl3 <", value, "yl3");
            return (Criteria) this;
        }

        public Criteria andYl3LessThanOrEqualTo(String value) {
            addCriterion("yl3 <=", value, "yl3");
            return (Criteria) this;
        }

        public Criteria andYl3Like(String value) {
            addCriterion("yl3 like", value, "yl3");
            return (Criteria) this;
        }

        public Criteria andYl3NotLike(String value) {
            addCriterion("yl3 not like", value, "yl3");
            return (Criteria) this;
        }

        public Criteria andYl3In(List<String> values) {
            addCriterion("yl3 in", values, "yl3");
            return (Criteria) this;
        }

        public Criteria andYl3NotIn(List<String> values) {
            addCriterion("yl3 not in", values, "yl3");
            return (Criteria) this;
        }

        public Criteria andYl3Between(String value1, String value2) {
            addCriterion("yl3 between", value1, value2, "yl3");
            return (Criteria) this;
        }

        public Criteria andYl3NotBetween(String value1, String value2) {
            addCriterion("yl3 not between", value1, value2, "yl3");
            return (Criteria) this;
        }

        public Criteria andYl4IsNull() {
            addCriterion("yl4 is null");
            return (Criteria) this;
        }

        public Criteria andYl4IsNotNull() {
            addCriterion("yl4 is not null");
            return (Criteria) this;
        }

        public Criteria andYl4EqualTo(String value) {
            addCriterion("yl4 =", value, "yl4");
            return (Criteria) this;
        }

        public Criteria andYl4NotEqualTo(String value) {
            addCriterion("yl4 <>", value, "yl4");
            return (Criteria) this;
        }

        public Criteria andYl4GreaterThan(String value) {
            addCriterion("yl4 >", value, "yl4");
            return (Criteria) this;
        }

        public Criteria andYl4GreaterThanOrEqualTo(String value) {
            addCriterion("yl4 >=", value, "yl4");
            return (Criteria) this;
        }

        public Criteria andYl4LessThan(String value) {
            addCriterion("yl4 <", value, "yl4");
            return (Criteria) this;
        }

        public Criteria andYl4LessThanOrEqualTo(String value) {
            addCriterion("yl4 <=", value, "yl4");
            return (Criteria) this;
        }

        public Criteria andYl4Like(String value) {
            addCriterion("yl4 like", value, "yl4");
            return (Criteria) this;
        }

        public Criteria andYl4NotLike(String value) {
            addCriterion("yl4 not like", value, "yl4");
            return (Criteria) this;
        }

        public Criteria andYl4In(List<String> values) {
            addCriterion("yl4 in", values, "yl4");
            return (Criteria) this;
        }

        public Criteria andYl4NotIn(List<String> values) {
            addCriterion("yl4 not in", values, "yl4");
            return (Criteria) this;
        }

        public Criteria andYl4Between(String value1, String value2) {
            addCriterion("yl4 between", value1, value2, "yl4");
            return (Criteria) this;
        }

        public Criteria andYl4NotBetween(String value1, String value2) {
            addCriterion("yl4 not between", value1, value2, "yl4");
            return (Criteria) this;
        }

        public Criteria andYl5IsNull() {
            addCriterion("yl5 is null");
            return (Criteria) this;
        }

        public Criteria andYl5IsNotNull() {
            addCriterion("yl5 is not null");
            return (Criteria) this;
        }

        public Criteria andYl5EqualTo(String value) {
            addCriterion("yl5 =", value, "yl5");
            return (Criteria) this;
        }

        public Criteria andYl5NotEqualTo(String value) {
            addCriterion("yl5 <>", value, "yl5");
            return (Criteria) this;
        }

        public Criteria andYl5GreaterThan(String value) {
            addCriterion("yl5 >", value, "yl5");
            return (Criteria) this;
        }

        public Criteria andYl5GreaterThanOrEqualTo(String value) {
            addCriterion("yl5 >=", value, "yl5");
            return (Criteria) this;
        }

        public Criteria andYl5LessThan(String value) {
            addCriterion("yl5 <", value, "yl5");
            return (Criteria) this;
        }

        public Criteria andYl5LessThanOrEqualTo(String value) {
            addCriterion("yl5 <=", value, "yl5");
            return (Criteria) this;
        }

        public Criteria andYl5Like(String value) {
            addCriterion("yl5 like", value, "yl5");
            return (Criteria) this;
        }

        public Criteria andYl5NotLike(String value) {
            addCriterion("yl5 not like", value, "yl5");
            return (Criteria) this;
        }

        public Criteria andYl5In(List<String> values) {
            addCriterion("yl5 in", values, "yl5");
            return (Criteria) this;
        }

        public Criteria andYl5NotIn(List<String> values) {
            addCriterion("yl5 not in", values, "yl5");
            return (Criteria) this;
        }

        public Criteria andYl5Between(String value1, String value2) {
            addCriterion("yl5 between", value1, value2, "yl5");
            return (Criteria) this;
        }

        public Criteria andYl5NotBetween(String value1, String value2) {
            addCriterion("yl5 not between", value1, value2, "yl5");
            return (Criteria) this;
        }

        public Criteria andYl6IsNull() {
            addCriterion("yl6 is null");
            return (Criteria) this;
        }

        public Criteria andYl6IsNotNull() {
            addCriterion("yl6 is not null");
            return (Criteria) this;
        }

        public Criteria andYl6EqualTo(String value) {
            addCriterion("yl6 =", value, "yl6");
            return (Criteria) this;
        }

        public Criteria andYl6NotEqualTo(String value) {
            addCriterion("yl6 <>", value, "yl6");
            return (Criteria) this;
        }

        public Criteria andYl6GreaterThan(String value) {
            addCriterion("yl6 >", value, "yl6");
            return (Criteria) this;
        }

        public Criteria andYl6GreaterThanOrEqualTo(String value) {
            addCriterion("yl6 >=", value, "yl6");
            return (Criteria) this;
        }

        public Criteria andYl6LessThan(String value) {
            addCriterion("yl6 <", value, "yl6");
            return (Criteria) this;
        }

        public Criteria andYl6LessThanOrEqualTo(String value) {
            addCriterion("yl6 <=", value, "yl6");
            return (Criteria) this;
        }

        public Criteria andYl6Like(String value) {
            addCriterion("yl6 like", value, "yl6");
            return (Criteria) this;
        }

        public Criteria andYl6NotLike(String value) {
            addCriterion("yl6 not like", value, "yl6");
            return (Criteria) this;
        }

        public Criteria andYl6In(List<String> values) {
            addCriterion("yl6 in", values, "yl6");
            return (Criteria) this;
        }

        public Criteria andYl6NotIn(List<String> values) {
            addCriterion("yl6 not in", values, "yl6");
            return (Criteria) this;
        }

        public Criteria andYl6Between(String value1, String value2) {
            addCriterion("yl6 between", value1, value2, "yl6");
            return (Criteria) this;
        }

        public Criteria andYl6NotBetween(String value1, String value2) {
            addCriterion("yl6 not between", value1, value2, "yl6");
            return (Criteria) this;
        }

        public Criteria andYl7IsNull() {
            addCriterion("yl7 is null");
            return (Criteria) this;
        }

        public Criteria andYl7IsNotNull() {
            addCriterion("yl7 is not null");
            return (Criteria) this;
        }

        public Criteria andYl7EqualTo(String value) {
            addCriterion("yl7 =", value, "yl7");
            return (Criteria) this;
        }

        public Criteria andYl7NotEqualTo(String value) {
            addCriterion("yl7 <>", value, "yl7");
            return (Criteria) this;
        }

        public Criteria andYl7GreaterThan(String value) {
            addCriterion("yl7 >", value, "yl7");
            return (Criteria) this;
        }

        public Criteria andYl7GreaterThanOrEqualTo(String value) {
            addCriterion("yl7 >=", value, "yl7");
            return (Criteria) this;
        }

        public Criteria andYl7LessThan(String value) {
            addCriterion("yl7 <", value, "yl7");
            return (Criteria) this;
        }

        public Criteria andYl7LessThanOrEqualTo(String value) {
            addCriterion("yl7 <=", value, "yl7");
            return (Criteria) this;
        }

        public Criteria andYl7Like(String value) {
            addCriterion("yl7 like", value, "yl7");
            return (Criteria) this;
        }

        public Criteria andYl7NotLike(String value) {
            addCriterion("yl7 not like", value, "yl7");
            return (Criteria) this;
        }

        public Criteria andYl7In(List<String> values) {
            addCriterion("yl7 in", values, "yl7");
            return (Criteria) this;
        }

        public Criteria andYl7NotIn(List<String> values) {
            addCriterion("yl7 not in", values, "yl7");
            return (Criteria) this;
        }

        public Criteria andYl7Between(String value1, String value2) {
            addCriterion("yl7 between", value1, value2, "yl7");
            return (Criteria) this;
        }

        public Criteria andYl7NotBetween(String value1, String value2) {
            addCriterion("yl7 not between", value1, value2, "yl7");
            return (Criteria) this;
        }

        public Criteria andYl8IsNull() {
            addCriterion("yl8 is null");
            return (Criteria) this;
        }

        public Criteria andYl8IsNotNull() {
            addCriterion("yl8 is not null");
            return (Criteria) this;
        }

        public Criteria andYl8EqualTo(String value) {
            addCriterion("yl8 =", value, "yl8");
            return (Criteria) this;
        }

        public Criteria andYl8NotEqualTo(String value) {
            addCriterion("yl8 <>", value, "yl8");
            return (Criteria) this;
        }

        public Criteria andYl8GreaterThan(String value) {
            addCriterion("yl8 >", value, "yl8");
            return (Criteria) this;
        }

        public Criteria andYl8GreaterThanOrEqualTo(String value) {
            addCriterion("yl8 >=", value, "yl8");
            return (Criteria) this;
        }

        public Criteria andYl8LessThan(String value) {
            addCriterion("yl8 <", value, "yl8");
            return (Criteria) this;
        }

        public Criteria andYl8LessThanOrEqualTo(String value) {
            addCriterion("yl8 <=", value, "yl8");
            return (Criteria) this;
        }

        public Criteria andYl8Like(String value) {
            addCriterion("yl8 like", value, "yl8");
            return (Criteria) this;
        }

        public Criteria andYl8NotLike(String value) {
            addCriterion("yl8 not like", value, "yl8");
            return (Criteria) this;
        }

        public Criteria andYl8In(List<String> values) {
            addCriterion("yl8 in", values, "yl8");
            return (Criteria) this;
        }

        public Criteria andYl8NotIn(List<String> values) {
            addCriterion("yl8 not in", values, "yl8");
            return (Criteria) this;
        }

        public Criteria andYl8Between(String value1, String value2) {
            addCriterion("yl8 between", value1, value2, "yl8");
            return (Criteria) this;
        }

        public Criteria andYl8NotBetween(String value1, String value2) {
            addCriterion("yl8 not between", value1, value2, "yl8");
            return (Criteria) this;
        }

        public Criteria andYl9IsNull() {
            addCriterion("yl9 is null");
            return (Criteria) this;
        }

        public Criteria andYl9IsNotNull() {
            addCriterion("yl9 is not null");
            return (Criteria) this;
        }

        public Criteria andYl9EqualTo(String value) {
            addCriterion("yl9 =", value, "yl9");
            return (Criteria) this;
        }

        public Criteria andYl9NotEqualTo(String value) {
            addCriterion("yl9 <>", value, "yl9");
            return (Criteria) this;
        }

        public Criteria andYl9GreaterThan(String value) {
            addCriterion("yl9 >", value, "yl9");
            return (Criteria) this;
        }

        public Criteria andYl9GreaterThanOrEqualTo(String value) {
            addCriterion("yl9 >=", value, "yl9");
            return (Criteria) this;
        }

        public Criteria andYl9LessThan(String value) {
            addCriterion("yl9 <", value, "yl9");
            return (Criteria) this;
        }

        public Criteria andYl9LessThanOrEqualTo(String value) {
            addCriterion("yl9 <=", value, "yl9");
            return (Criteria) this;
        }

        public Criteria andYl9Like(String value) {
            addCriterion("yl9 like", value, "yl9");
            return (Criteria) this;
        }

        public Criteria andYl9NotLike(String value) {
            addCriterion("yl9 not like", value, "yl9");
            return (Criteria) this;
        }

        public Criteria andYl9In(List<String> values) {
            addCriterion("yl9 in", values, "yl9");
            return (Criteria) this;
        }

        public Criteria andYl9NotIn(List<String> values) {
            addCriterion("yl9 not in", values, "yl9");
            return (Criteria) this;
        }

        public Criteria andYl9Between(String value1, String value2) {
            addCriterion("yl9 between", value1, value2, "yl9");
            return (Criteria) this;
        }

        public Criteria andYl9NotBetween(String value1, String value2) {
            addCriterion("yl9 not between", value1, value2, "yl9");
            return (Criteria) this;
        }

        public Criteria andYl10IsNull() {
            addCriterion("yl10 is null");
            return (Criteria) this;
        }

        public Criteria andYl10IsNotNull() {
            addCriterion("yl10 is not null");
            return (Criteria) this;
        }

        public Criteria andYl10EqualTo(String value) {
            addCriterion("yl10 =", value, "yl10");
            return (Criteria) this;
        }

        public Criteria andYl10NotEqualTo(String value) {
            addCriterion("yl10 <>", value, "yl10");
            return (Criteria) this;
        }

        public Criteria andYl10GreaterThan(String value) {
            addCriterion("yl10 >", value, "yl10");
            return (Criteria) this;
        }

        public Criteria andYl10GreaterThanOrEqualTo(String value) {
            addCriterion("yl10 >=", value, "yl10");
            return (Criteria) this;
        }

        public Criteria andYl10LessThan(String value) {
            addCriterion("yl10 <", value, "yl10");
            return (Criteria) this;
        }

        public Criteria andYl10LessThanOrEqualTo(String value) {
            addCriterion("yl10 <=", value, "yl10");
            return (Criteria) this;
        }

        public Criteria andYl10Like(String value) {
            addCriterion("yl10 like", value, "yl10");
            return (Criteria) this;
        }

        public Criteria andYl10NotLike(String value) {
            addCriterion("yl10 not like", value, "yl10");
            return (Criteria) this;
        }

        public Criteria andYl10In(List<String> values) {
            addCriterion("yl10 in", values, "yl10");
            return (Criteria) this;
        }

        public Criteria andYl10NotIn(List<String> values) {
            addCriterion("yl10 not in", values, "yl10");
            return (Criteria) this;
        }

        public Criteria andYl10Between(String value1, String value2) {
            addCriterion("yl10 between", value1, value2, "yl10");
            return (Criteria) this;
        }

        public Criteria andYl10NotBetween(String value1, String value2) {
            addCriterion("yl10 not between", value1, value2, "yl10");
            return (Criteria) this;
        }

        public Criteria andYl11IsNull() {
            addCriterion("yl11 is null");
            return (Criteria) this;
        }

        public Criteria andYl11IsNotNull() {
            addCriterion("yl11 is not null");
            return (Criteria) this;
        }

        public Criteria andYl11EqualTo(String value) {
            addCriterion("yl11 =", value, "yl11");
            return (Criteria) this;
        }

        public Criteria andYl11NotEqualTo(String value) {
            addCriterion("yl11 <>", value, "yl11");
            return (Criteria) this;
        }

        public Criteria andYl11GreaterThan(String value) {
            addCriterion("yl11 >", value, "yl11");
            return (Criteria) this;
        }

        public Criteria andYl11GreaterThanOrEqualTo(String value) {
            addCriterion("yl11 >=", value, "yl11");
            return (Criteria) this;
        }

        public Criteria andYl11LessThan(String value) {
            addCriterion("yl11 <", value, "yl11");
            return (Criteria) this;
        }

        public Criteria andYl11LessThanOrEqualTo(String value) {
            addCriterion("yl11 <=", value, "yl11");
            return (Criteria) this;
        }

        public Criteria andYl11Like(String value) {
            addCriterion("yl11 like", value, "yl11");
            return (Criteria) this;
        }

        public Criteria andYl11NotLike(String value) {
            addCriterion("yl11 not like", value, "yl11");
            return (Criteria) this;
        }

        public Criteria andYl11In(List<String> values) {
            addCriterion("yl11 in", values, "yl11");
            return (Criteria) this;
        }

        public Criteria andYl11NotIn(List<String> values) {
            addCriterion("yl11 not in", values, "yl11");
            return (Criteria) this;
        }

        public Criteria andYl11Between(String value1, String value2) {
            addCriterion("yl11 between", value1, value2, "yl11");
            return (Criteria) this;
        }

        public Criteria andYl11NotBetween(String value1, String value2) {
            addCriterion("yl11 not between", value1, value2, "yl11");
            return (Criteria) this;
        }

        public Criteria andYl12IsNull() {
            addCriterion("yl12 is null");
            return (Criteria) this;
        }

        public Criteria andYl12IsNotNull() {
            addCriterion("yl12 is not null");
            return (Criteria) this;
        }

        public Criteria andYl12EqualTo(String value) {
            addCriterion("yl12 =", value, "yl12");
            return (Criteria) this;
        }

        public Criteria andYl12NotEqualTo(String value) {
            addCriterion("yl12 <>", value, "yl12");
            return (Criteria) this;
        }

        public Criteria andYl12GreaterThan(String value) {
            addCriterion("yl12 >", value, "yl12");
            return (Criteria) this;
        }

        public Criteria andYl12GreaterThanOrEqualTo(String value) {
            addCriterion("yl12 >=", value, "yl12");
            return (Criteria) this;
        }

        public Criteria andYl12LessThan(String value) {
            addCriterion("yl12 <", value, "yl12");
            return (Criteria) this;
        }

        public Criteria andYl12LessThanOrEqualTo(String value) {
            addCriterion("yl12 <=", value, "yl12");
            return (Criteria) this;
        }

        public Criteria andYl12Like(String value) {
            addCriterion("yl12 like", value, "yl12");
            return (Criteria) this;
        }

        public Criteria andYl12NotLike(String value) {
            addCriterion("yl12 not like", value, "yl12");
            return (Criteria) this;
        }

        public Criteria andYl12In(List<String> values) {
            addCriterion("yl12 in", values, "yl12");
            return (Criteria) this;
        }

        public Criteria andYl12NotIn(List<String> values) {
            addCriterion("yl12 not in", values, "yl12");
            return (Criteria) this;
        }

        public Criteria andYl12Between(String value1, String value2) {
            addCriterion("yl12 between", value1, value2, "yl12");
            return (Criteria) this;
        }

        public Criteria andYl12NotBetween(String value1, String value2) {
            addCriterion("yl12 not between", value1, value2, "yl12");
            return (Criteria) this;
        }

        public Criteria andYl13IsNull() {
            addCriterion("yl13 is null");
            return (Criteria) this;
        }

        public Criteria andYl13IsNotNull() {
            addCriterion("yl13 is not null");
            return (Criteria) this;
        }

        public Criteria andYl13EqualTo(String value) {
            addCriterion("yl13 =", value, "yl13");
            return (Criteria) this;
        }

        public Criteria andYl13NotEqualTo(String value) {
            addCriterion("yl13 <>", value, "yl13");
            return (Criteria) this;
        }

        public Criteria andYl13GreaterThan(String value) {
            addCriterion("yl13 >", value, "yl13");
            return (Criteria) this;
        }

        public Criteria andYl13GreaterThanOrEqualTo(String value) {
            addCriterion("yl13 >=", value, "yl13");
            return (Criteria) this;
        }

        public Criteria andYl13LessThan(String value) {
            addCriterion("yl13 <", value, "yl13");
            return (Criteria) this;
        }

        public Criteria andYl13LessThanOrEqualTo(String value) {
            addCriterion("yl13 <=", value, "yl13");
            return (Criteria) this;
        }

        public Criteria andYl13Like(String value) {
            addCriterion("yl13 like", value, "yl13");
            return (Criteria) this;
        }

        public Criteria andYl13NotLike(String value) {
            addCriterion("yl13 not like", value, "yl13");
            return (Criteria) this;
        }

        public Criteria andYl13In(List<String> values) {
            addCriterion("yl13 in", values, "yl13");
            return (Criteria) this;
        }

        public Criteria andYl13NotIn(List<String> values) {
            addCriterion("yl13 not in", values, "yl13");
            return (Criteria) this;
        }

        public Criteria andYl13Between(String value1, String value2) {
            addCriterion("yl13 between", value1, value2, "yl13");
            return (Criteria) this;
        }

        public Criteria andYl13NotBetween(String value1, String value2) {
            addCriterion("yl13 not between", value1, value2, "yl13");
            return (Criteria) this;
        }

        public Criteria andYl14IsNull() {
            addCriterion("yl14 is null");
            return (Criteria) this;
        }

        public Criteria andYl14IsNotNull() {
            addCriterion("yl14 is not null");
            return (Criteria) this;
        }

        public Criteria andYl14EqualTo(String value) {
            addCriterion("yl14 =", value, "yl14");
            return (Criteria) this;
        }

        public Criteria andYl14NotEqualTo(String value) {
            addCriterion("yl14 <>", value, "yl14");
            return (Criteria) this;
        }

        public Criteria andYl14GreaterThan(String value) {
            addCriterion("yl14 >", value, "yl14");
            return (Criteria) this;
        }

        public Criteria andYl14GreaterThanOrEqualTo(String value) {
            addCriterion("yl14 >=", value, "yl14");
            return (Criteria) this;
        }

        public Criteria andYl14LessThan(String value) {
            addCriterion("yl14 <", value, "yl14");
            return (Criteria) this;
        }

        public Criteria andYl14LessThanOrEqualTo(String value) {
            addCriterion("yl14 <=", value, "yl14");
            return (Criteria) this;
        }

        public Criteria andYl14Like(String value) {
            addCriterion("yl14 like", value, "yl14");
            return (Criteria) this;
        }

        public Criteria andYl14NotLike(String value) {
            addCriterion("yl14 not like", value, "yl14");
            return (Criteria) this;
        }

        public Criteria andYl14In(List<String> values) {
            addCriterion("yl14 in", values, "yl14");
            return (Criteria) this;
        }

        public Criteria andYl14NotIn(List<String> values) {
            addCriterion("yl14 not in", values, "yl14");
            return (Criteria) this;
        }

        public Criteria andYl14Between(String value1, String value2) {
            addCriterion("yl14 between", value1, value2, "yl14");
            return (Criteria) this;
        }

        public Criteria andYl14NotBetween(String value1, String value2) {
            addCriterion("yl14 not between", value1, value2, "yl14");
            return (Criteria) this;
        }

        public Criteria andYl15IsNull() {
            addCriterion("yl15 is null");
            return (Criteria) this;
        }

        public Criteria andYl15IsNotNull() {
            addCriterion("yl15 is not null");
            return (Criteria) this;
        }

        public Criteria andYl15EqualTo(String value) {
            addCriterion("yl15 =", value, "yl15");
            return (Criteria) this;
        }

        public Criteria andYl15NotEqualTo(String value) {
            addCriterion("yl15 <>", value, "yl15");
            return (Criteria) this;
        }

        public Criteria andYl15GreaterThan(String value) {
            addCriterion("yl15 >", value, "yl15");
            return (Criteria) this;
        }

        public Criteria andYl15GreaterThanOrEqualTo(String value) {
            addCriterion("yl15 >=", value, "yl15");
            return (Criteria) this;
        }

        public Criteria andYl15LessThan(String value) {
            addCriterion("yl15 <", value, "yl15");
            return (Criteria) this;
        }

        public Criteria andYl15LessThanOrEqualTo(String value) {
            addCriterion("yl15 <=", value, "yl15");
            return (Criteria) this;
        }

        public Criteria andYl15Like(String value) {
            addCriterion("yl15 like", value, "yl15");
            return (Criteria) this;
        }

        public Criteria andYl15NotLike(String value) {
            addCriterion("yl15 not like", value, "yl15");
            return (Criteria) this;
        }

        public Criteria andYl15In(List<String> values) {
            addCriterion("yl15 in", values, "yl15");
            return (Criteria) this;
        }

        public Criteria andYl15NotIn(List<String> values) {
            addCriterion("yl15 not in", values, "yl15");
            return (Criteria) this;
        }

        public Criteria andYl15Between(String value1, String value2) {
            addCriterion("yl15 between", value1, value2, "yl15");
            return (Criteria) this;
        }

        public Criteria andYl15NotBetween(String value1, String value2) {
            addCriterion("yl15 not between", value1, value2, "yl15");
            return (Criteria) this;
        }

        public Criteria andYl16IsNull() {
            addCriterion("yl16 is null");
            return (Criteria) this;
        }

        public Criteria andYl16IsNotNull() {
            addCriterion("yl16 is not null");
            return (Criteria) this;
        }

        public Criteria andYl16EqualTo(String value) {
            addCriterion("yl16 =", value, "yl16");
            return (Criteria) this;
        }

        public Criteria andYl16NotEqualTo(String value) {
            addCriterion("yl16 <>", value, "yl16");
            return (Criteria) this;
        }

        public Criteria andYl16GreaterThan(String value) {
            addCriterion("yl16 >", value, "yl16");
            return (Criteria) this;
        }

        public Criteria andYl16GreaterThanOrEqualTo(String value) {
            addCriterion("yl16 >=", value, "yl16");
            return (Criteria) this;
        }

        public Criteria andYl16LessThan(String value) {
            addCriterion("yl16 <", value, "yl16");
            return (Criteria) this;
        }

        public Criteria andYl16LessThanOrEqualTo(String value) {
            addCriterion("yl16 <=", value, "yl16");
            return (Criteria) this;
        }

        public Criteria andYl16Like(String value) {
            addCriterion("yl16 like", value, "yl16");
            return (Criteria) this;
        }

        public Criteria andYl16NotLike(String value) {
            addCriterion("yl16 not like", value, "yl16");
            return (Criteria) this;
        }

        public Criteria andYl16In(List<String> values) {
            addCriterion("yl16 in", values, "yl16");
            return (Criteria) this;
        }

        public Criteria andYl16NotIn(List<String> values) {
            addCriterion("yl16 not in", values, "yl16");
            return (Criteria) this;
        }

        public Criteria andYl16Between(String value1, String value2) {
            addCriterion("yl16 between", value1, value2, "yl16");
            return (Criteria) this;
        }

        public Criteria andYl16NotBetween(String value1, String value2) {
            addCriterion("yl16 not between", value1, value2, "yl16");
            return (Criteria) this;
        }

        public Criteria andYl17IsNull() {
            addCriterion("yl17 is null");
            return (Criteria) this;
        }

        public Criteria andYl17IsNotNull() {
            addCriterion("yl17 is not null");
            return (Criteria) this;
        }

        public Criteria andYl17EqualTo(String value) {
            addCriterion("yl17 =", value, "yl17");
            return (Criteria) this;
        }

        public Criteria andYl17NotEqualTo(String value) {
            addCriterion("yl17 <>", value, "yl17");
            return (Criteria) this;
        }

        public Criteria andYl17GreaterThan(String value) {
            addCriterion("yl17 >", value, "yl17");
            return (Criteria) this;
        }

        public Criteria andYl17GreaterThanOrEqualTo(String value) {
            addCriterion("yl17 >=", value, "yl17");
            return (Criteria) this;
        }

        public Criteria andYl17LessThan(String value) {
            addCriterion("yl17 <", value, "yl17");
            return (Criteria) this;
        }

        public Criteria andYl17LessThanOrEqualTo(String value) {
            addCriterion("yl17 <=", value, "yl17");
            return (Criteria) this;
        }

        public Criteria andYl17Like(String value) {
            addCriterion("yl17 like", value, "yl17");
            return (Criteria) this;
        }

        public Criteria andYl17NotLike(String value) {
            addCriterion("yl17 not like", value, "yl17");
            return (Criteria) this;
        }

        public Criteria andYl17In(List<String> values) {
            addCriterion("yl17 in", values, "yl17");
            return (Criteria) this;
        }

        public Criteria andYl17NotIn(List<String> values) {
            addCriterion("yl17 not in", values, "yl17");
            return (Criteria) this;
        }

        public Criteria andYl17Between(String value1, String value2) {
            addCriterion("yl17 between", value1, value2, "yl17");
            return (Criteria) this;
        }

        public Criteria andYl17NotBetween(String value1, String value2) {
            addCriterion("yl17 not between", value1, value2, "yl17");
            return (Criteria) this;
        }

        public Criteria andYl18IsNull() {
            addCriterion("yl18 is null");
            return (Criteria) this;
        }

        public Criteria andYl18IsNotNull() {
            addCriterion("yl18 is not null");
            return (Criteria) this;
        }

        public Criteria andYl18EqualTo(String value) {
            addCriterion("yl18 =", value, "yl18");
            return (Criteria) this;
        }

        public Criteria andYl18NotEqualTo(String value) {
            addCriterion("yl18 <>", value, "yl18");
            return (Criteria) this;
        }

        public Criteria andYl18GreaterThan(String value) {
            addCriterion("yl18 >", value, "yl18");
            return (Criteria) this;
        }

        public Criteria andYl18GreaterThanOrEqualTo(String value) {
            addCriterion("yl18 >=", value, "yl18");
            return (Criteria) this;
        }

        public Criteria andYl18LessThan(String value) {
            addCriterion("yl18 <", value, "yl18");
            return (Criteria) this;
        }

        public Criteria andYl18LessThanOrEqualTo(String value) {
            addCriterion("yl18 <=", value, "yl18");
            return (Criteria) this;
        }

        public Criteria andYl18Like(String value) {
            addCriterion("yl18 like", value, "yl18");
            return (Criteria) this;
        }

        public Criteria andYl18NotLike(String value) {
            addCriterion("yl18 not like", value, "yl18");
            return (Criteria) this;
        }

        public Criteria andYl18In(List<String> values) {
            addCriterion("yl18 in", values, "yl18");
            return (Criteria) this;
        }

        public Criteria andYl18NotIn(List<String> values) {
            addCriterion("yl18 not in", values, "yl18");
            return (Criteria) this;
        }

        public Criteria andYl18Between(String value1, String value2) {
            addCriterion("yl18 between", value1, value2, "yl18");
            return (Criteria) this;
        }

        public Criteria andYl18NotBetween(String value1, String value2) {
            addCriterion("yl18 not between", value1, value2, "yl18");
            return (Criteria) this;
        }

        public Criteria andYl19IsNull() {
            addCriterion("yl19 is null");
            return (Criteria) this;
        }

        public Criteria andYl19IsNotNull() {
            addCriterion("yl19 is not null");
            return (Criteria) this;
        }

        public Criteria andYl19EqualTo(String value) {
            addCriterion("yl19 =", value, "yl19");
            return (Criteria) this;
        }

        public Criteria andYl19NotEqualTo(String value) {
            addCriterion("yl19 <>", value, "yl19");
            return (Criteria) this;
        }

        public Criteria andYl19GreaterThan(String value) {
            addCriterion("yl19 >", value, "yl19");
            return (Criteria) this;
        }

        public Criteria andYl19GreaterThanOrEqualTo(String value) {
            addCriterion("yl19 >=", value, "yl19");
            return (Criteria) this;
        }

        public Criteria andYl19LessThan(String value) {
            addCriterion("yl19 <", value, "yl19");
            return (Criteria) this;
        }

        public Criteria andYl19LessThanOrEqualTo(String value) {
            addCriterion("yl19 <=", value, "yl19");
            return (Criteria) this;
        }

        public Criteria andYl19Like(String value) {
            addCriterion("yl19 like", value, "yl19");
            return (Criteria) this;
        }

        public Criteria andYl19NotLike(String value) {
            addCriterion("yl19 not like", value, "yl19");
            return (Criteria) this;
        }

        public Criteria andYl19In(List<String> values) {
            addCriterion("yl19 in", values, "yl19");
            return (Criteria) this;
        }

        public Criteria andYl19NotIn(List<String> values) {
            addCriterion("yl19 not in", values, "yl19");
            return (Criteria) this;
        }

        public Criteria andYl19Between(String value1, String value2) {
            addCriterion("yl19 between", value1, value2, "yl19");
            return (Criteria) this;
        }

        public Criteria andYl19NotBetween(String value1, String value2) {
            addCriterion("yl19 not between", value1, value2, "yl19");
            return (Criteria) this;
        }

        public Criteria andYl20IsNull() {
            addCriterion("yl20 is null");
            return (Criteria) this;
        }

        public Criteria andYl20IsNotNull() {
            addCriterion("yl20 is not null");
            return (Criteria) this;
        }

        public Criteria andYl20EqualTo(String value) {
            addCriterion("yl20 =", value, "yl20");
            return (Criteria) this;
        }

        public Criteria andYl20NotEqualTo(String value) {
            addCriterion("yl20 <>", value, "yl20");
            return (Criteria) this;
        }

        public Criteria andYl20GreaterThan(String value) {
            addCriterion("yl20 >", value, "yl20");
            return (Criteria) this;
        }

        public Criteria andYl20GreaterThanOrEqualTo(String value) {
            addCriterion("yl20 >=", value, "yl20");
            return (Criteria) this;
        }

        public Criteria andYl20LessThan(String value) {
            addCriterion("yl20 <", value, "yl20");
            return (Criteria) this;
        }

        public Criteria andYl20LessThanOrEqualTo(String value) {
            addCriterion("yl20 <=", value, "yl20");
            return (Criteria) this;
        }

        public Criteria andYl20Like(String value) {
            addCriterion("yl20 like", value, "yl20");
            return (Criteria) this;
        }

        public Criteria andYl20NotLike(String value) {
            addCriterion("yl20 not like", value, "yl20");
            return (Criteria) this;
        }

        public Criteria andYl20In(List<String> values) {
            addCriterion("yl20 in", values, "yl20");
            return (Criteria) this;
        }

        public Criteria andYl20NotIn(List<String> values) {
            addCriterion("yl20 not in", values, "yl20");
            return (Criteria) this;
        }

        public Criteria andYl20Between(String value1, String value2) {
            addCriterion("yl20 between", value1, value2, "yl20");
            return (Criteria) this;
        }

        public Criteria andYl20NotBetween(String value1, String value2) {
            addCriterion("yl20 not between", value1, value2, "yl20");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}