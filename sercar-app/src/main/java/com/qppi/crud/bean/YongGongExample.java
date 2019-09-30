package com.qppi.crud.bean;

import java.util.ArrayList;
import java.util.List;

public class YongGongExample {
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

    public YongGongExample() {
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

        public Criteria andYonggongIdIsNull() {
            addCriterion("YONGGONG_ID is null");
            return (Criteria) this;
        }

        public Criteria andYonggongIdIsNotNull() {
            addCriterion("YONGGONG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andYonggongIdEqualTo(String value) {
            addCriterion("YONGGONG_ID =", value, "yonggongId");
            return (Criteria) this;
        }

        public Criteria andYonggongIdNotEqualTo(String value) {
            addCriterion("YONGGONG_ID <>", value, "yonggongId");
            return (Criteria) this;
        }

        public Criteria andYonggongIdGreaterThan(String value) {
            addCriterion("YONGGONG_ID >", value, "yonggongId");
            return (Criteria) this;
        }

        public Criteria andYonggongIdGreaterThanOrEqualTo(String value) {
            addCriterion("YONGGONG_ID >=", value, "yonggongId");
            return (Criteria) this;
        }

        public Criteria andYonggongIdLessThan(String value) {
            addCriterion("YONGGONG_ID <", value, "yonggongId");
            return (Criteria) this;
        }

        public Criteria andYonggongIdLessThanOrEqualTo(String value) {
            addCriterion("YONGGONG_ID <=", value, "yonggongId");
            return (Criteria) this;
        }

        public Criteria andYonggongIdLike(String value) {
            addCriterion("YONGGONG_ID like", value, "yonggongId");
            return (Criteria) this;
        }

        public Criteria andYonggongIdNotLike(String value) {
            addCriterion("YONGGONG_ID not like", value, "yonggongId");
            return (Criteria) this;
        }

        public Criteria andYonggongIdIn(List<String> values) {
            addCriterion("YONGGONG_ID in", values, "yonggongId");
            return (Criteria) this;
        }

        public Criteria andYonggongIdNotIn(List<String> values) {
            addCriterion("YONGGONG_ID not in", values, "yonggongId");
            return (Criteria) this;
        }

        public Criteria andYonggongIdBetween(String value1, String value2) {
            addCriterion("YONGGONG_ID between", value1, value2, "yonggongId");
            return (Criteria) this;
        }

        public Criteria andYonggongIdNotBetween(String value1, String value2) {
            addCriterion("YONGGONG_ID not between", value1, value2, "yonggongId");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("CREATE_BY is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("CREATE_BY is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("CREATE_BY =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("CREATE_BY <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("CREATE_BY >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_BY >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("CREATE_BY <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("CREATE_BY <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("CREATE_BY like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("CREATE_BY not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("CREATE_BY in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("CREATE_BY not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("CREATE_BY between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
            addCriterion("CREATE_BY not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(String value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(String value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(String value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(String value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(String value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLike(String value) {
            addCriterion("CREATE_TIME like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotLike(String value) {
            addCriterion("CREATE_TIME not like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<String> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<String> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(String value1, String value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(String value1, String value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andZpBumenIsNull() {
            addCriterion("ZP_BUMEN is null");
            return (Criteria) this;
        }

        public Criteria andZpBumenIsNotNull() {
            addCriterion("ZP_BUMEN is not null");
            return (Criteria) this;
        }

        public Criteria andZpBumenEqualTo(String value) {
            addCriterion("ZP_BUMEN =", value, "zpBumen");
            return (Criteria) this;
        }

        public Criteria andZpBumenNotEqualTo(String value) {
            addCriterion("ZP_BUMEN <>", value, "zpBumen");
            return (Criteria) this;
        }

        public Criteria andZpBumenGreaterThan(String value) {
            addCriterion("ZP_BUMEN >", value, "zpBumen");
            return (Criteria) this;
        }

        public Criteria andZpBumenGreaterThanOrEqualTo(String value) {
            addCriterion("ZP_BUMEN >=", value, "zpBumen");
            return (Criteria) this;
        }

        public Criteria andZpBumenLessThan(String value) {
            addCriterion("ZP_BUMEN <", value, "zpBumen");
            return (Criteria) this;
        }

        public Criteria andZpBumenLessThanOrEqualTo(String value) {
            addCriterion("ZP_BUMEN <=", value, "zpBumen");
            return (Criteria) this;
        }

        public Criteria andZpBumenLike(String value) {
            addCriterion("ZP_BUMEN like", value, "zpBumen");
            return (Criteria) this;
        }

        public Criteria andZpBumenNotLike(String value) {
            addCriterion("ZP_BUMEN not like", value, "zpBumen");
            return (Criteria) this;
        }

        public Criteria andZpBumenIn(List<String> values) {
            addCriterion("ZP_BUMEN in", values, "zpBumen");
            return (Criteria) this;
        }

        public Criteria andZpBumenNotIn(List<String> values) {
            addCriterion("ZP_BUMEN not in", values, "zpBumen");
            return (Criteria) this;
        }

        public Criteria andZpBumenBetween(String value1, String value2) {
            addCriterion("ZP_BUMEN between", value1, value2, "zpBumen");
            return (Criteria) this;
        }

        public Criteria andZpBumenNotBetween(String value1, String value2) {
            addCriterion("ZP_BUMEN not between", value1, value2, "zpBumen");
            return (Criteria) this;
        }

        public Criteria andZpZhiweiIsNull() {
            addCriterion("ZP_ZHIWEI is null");
            return (Criteria) this;
        }

        public Criteria andZpZhiweiIsNotNull() {
            addCriterion("ZP_ZHIWEI is not null");
            return (Criteria) this;
        }

        public Criteria andZpZhiweiEqualTo(String value) {
            addCriterion("ZP_ZHIWEI =", value, "zpZhiwei");
            return (Criteria) this;
        }

        public Criteria andZpZhiweiNotEqualTo(String value) {
            addCriterion("ZP_ZHIWEI <>", value, "zpZhiwei");
            return (Criteria) this;
        }

        public Criteria andZpZhiweiGreaterThan(String value) {
            addCriterion("ZP_ZHIWEI >", value, "zpZhiwei");
            return (Criteria) this;
        }

        public Criteria andZpZhiweiGreaterThanOrEqualTo(String value) {
            addCriterion("ZP_ZHIWEI >=", value, "zpZhiwei");
            return (Criteria) this;
        }

        public Criteria andZpZhiweiLessThan(String value) {
            addCriterion("ZP_ZHIWEI <", value, "zpZhiwei");
            return (Criteria) this;
        }

        public Criteria andZpZhiweiLessThanOrEqualTo(String value) {
            addCriterion("ZP_ZHIWEI <=", value, "zpZhiwei");
            return (Criteria) this;
        }

        public Criteria andZpZhiweiLike(String value) {
            addCriterion("ZP_ZHIWEI like", value, "zpZhiwei");
            return (Criteria) this;
        }

        public Criteria andZpZhiweiNotLike(String value) {
            addCriterion("ZP_ZHIWEI not like", value, "zpZhiwei");
            return (Criteria) this;
        }

        public Criteria andZpZhiweiIn(List<String> values) {
            addCriterion("ZP_ZHIWEI in", values, "zpZhiwei");
            return (Criteria) this;
        }

        public Criteria andZpZhiweiNotIn(List<String> values) {
            addCriterion("ZP_ZHIWEI not in", values, "zpZhiwei");
            return (Criteria) this;
        }

        public Criteria andZpZhiweiBetween(String value1, String value2) {
            addCriterion("ZP_ZHIWEI between", value1, value2, "zpZhiwei");
            return (Criteria) this;
        }

        public Criteria andZpZhiweiNotBetween(String value1, String value2) {
            addCriterion("ZP_ZHIWEI not between", value1, value2, "zpZhiwei");
            return (Criteria) this;
        }

        public Criteria andZpRenshuIsNull() {
            addCriterion("ZP_RENSHU is null");
            return (Criteria) this;
        }

        public Criteria andZpRenshuIsNotNull() {
            addCriterion("ZP_RENSHU is not null");
            return (Criteria) this;
        }

        public Criteria andZpRenshuEqualTo(String value) {
            addCriterion("ZP_RENSHU =", value, "zpRenshu");
            return (Criteria) this;
        }

        public Criteria andZpRenshuNotEqualTo(String value) {
            addCriterion("ZP_RENSHU <>", value, "zpRenshu");
            return (Criteria) this;
        }

        public Criteria andZpRenshuGreaterThan(String value) {
            addCriterion("ZP_RENSHU >", value, "zpRenshu");
            return (Criteria) this;
        }

        public Criteria andZpRenshuGreaterThanOrEqualTo(String value) {
            addCriterion("ZP_RENSHU >=", value, "zpRenshu");
            return (Criteria) this;
        }

        public Criteria andZpRenshuLessThan(String value) {
            addCriterion("ZP_RENSHU <", value, "zpRenshu");
            return (Criteria) this;
        }

        public Criteria andZpRenshuLessThanOrEqualTo(String value) {
            addCriterion("ZP_RENSHU <=", value, "zpRenshu");
            return (Criteria) this;
        }

        public Criteria andZpRenshuLike(String value) {
            addCriterion("ZP_RENSHU like", value, "zpRenshu");
            return (Criteria) this;
        }

        public Criteria andZpRenshuNotLike(String value) {
            addCriterion("ZP_RENSHU not like", value, "zpRenshu");
            return (Criteria) this;
        }

        public Criteria andZpRenshuIn(List<String> values) {
            addCriterion("ZP_RENSHU in", values, "zpRenshu");
            return (Criteria) this;
        }

        public Criteria andZpRenshuNotIn(List<String> values) {
            addCriterion("ZP_RENSHU not in", values, "zpRenshu");
            return (Criteria) this;
        }

        public Criteria andZpRenshuBetween(String value1, String value2) {
            addCriterion("ZP_RENSHU between", value1, value2, "zpRenshu");
            return (Criteria) this;
        }

        public Criteria andZpRenshuNotBetween(String value1, String value2) {
            addCriterion("ZP_RENSHU not between", value1, value2, "zpRenshu");
            return (Criteria) this;
        }

        public Criteria andZpTimeIsNull() {
            addCriterion("ZP_TIME is null");
            return (Criteria) this;
        }

        public Criteria andZpTimeIsNotNull() {
            addCriterion("ZP_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andZpTimeEqualTo(String value) {
            addCriterion("ZP_TIME =", value, "zpTime");
            return (Criteria) this;
        }

        public Criteria andZpTimeNotEqualTo(String value) {
            addCriterion("ZP_TIME <>", value, "zpTime");
            return (Criteria) this;
        }

        public Criteria andZpTimeGreaterThan(String value) {
            addCriterion("ZP_TIME >", value, "zpTime");
            return (Criteria) this;
        }

        public Criteria andZpTimeGreaterThanOrEqualTo(String value) {
            addCriterion("ZP_TIME >=", value, "zpTime");
            return (Criteria) this;
        }

        public Criteria andZpTimeLessThan(String value) {
            addCriterion("ZP_TIME <", value, "zpTime");
            return (Criteria) this;
        }

        public Criteria andZpTimeLessThanOrEqualTo(String value) {
            addCriterion("ZP_TIME <=", value, "zpTime");
            return (Criteria) this;
        }

        public Criteria andZpTimeLike(String value) {
            addCriterion("ZP_TIME like", value, "zpTime");
            return (Criteria) this;
        }

        public Criteria andZpTimeNotLike(String value) {
            addCriterion("ZP_TIME not like", value, "zpTime");
            return (Criteria) this;
        }

        public Criteria andZpTimeIn(List<String> values) {
            addCriterion("ZP_TIME in", values, "zpTime");
            return (Criteria) this;
        }

        public Criteria andZpTimeNotIn(List<String> values) {
            addCriterion("ZP_TIME not in", values, "zpTime");
            return (Criteria) this;
        }

        public Criteria andZpTimeBetween(String value1, String value2) {
            addCriterion("ZP_TIME between", value1, value2, "zpTime");
            return (Criteria) this;
        }

        public Criteria andZpTimeNotBetween(String value1, String value2) {
            addCriterion("ZP_TIME not between", value1, value2, "zpTime");
            return (Criteria) this;
        }

        public Criteria andZpYuanyinIsNull() {
            addCriterion("ZP_YUANYIN is null");
            return (Criteria) this;
        }

        public Criteria andZpYuanyinIsNotNull() {
            addCriterion("ZP_YUANYIN is not null");
            return (Criteria) this;
        }

        public Criteria andZpYuanyinEqualTo(String value) {
            addCriterion("ZP_YUANYIN =", value, "zpYuanyin");
            return (Criteria) this;
        }

        public Criteria andZpYuanyinNotEqualTo(String value) {
            addCriterion("ZP_YUANYIN <>", value, "zpYuanyin");
            return (Criteria) this;
        }

        public Criteria andZpYuanyinGreaterThan(String value) {
            addCriterion("ZP_YUANYIN >", value, "zpYuanyin");
            return (Criteria) this;
        }

        public Criteria andZpYuanyinGreaterThanOrEqualTo(String value) {
            addCriterion("ZP_YUANYIN >=", value, "zpYuanyin");
            return (Criteria) this;
        }

        public Criteria andZpYuanyinLessThan(String value) {
            addCriterion("ZP_YUANYIN <", value, "zpYuanyin");
            return (Criteria) this;
        }

        public Criteria andZpYuanyinLessThanOrEqualTo(String value) {
            addCriterion("ZP_YUANYIN <=", value, "zpYuanyin");
            return (Criteria) this;
        }

        public Criteria andZpYuanyinLike(String value) {
            addCriterion("ZP_YUANYIN like", value, "zpYuanyin");
            return (Criteria) this;
        }

        public Criteria andZpYuanyinNotLike(String value) {
            addCriterion("ZP_YUANYIN not like", value, "zpYuanyin");
            return (Criteria) this;
        }

        public Criteria andZpYuanyinIn(List<String> values) {
            addCriterion("ZP_YUANYIN in", values, "zpYuanyin");
            return (Criteria) this;
        }

        public Criteria andZpYuanyinNotIn(List<String> values) {
            addCriterion("ZP_YUANYIN not in", values, "zpYuanyin");
            return (Criteria) this;
        }

        public Criteria andZpYuanyinBetween(String value1, String value2) {
            addCriterion("ZP_YUANYIN between", value1, value2, "zpYuanyin");
            return (Criteria) this;
        }

        public Criteria andZpYuanyinNotBetween(String value1, String value2) {
            addCriterion("ZP_YUANYIN not between", value1, value2, "zpYuanyin");
            return (Criteria) this;
        }

        public Criteria andYqSexIsNull() {
            addCriterion("YQ_SEX is null");
            return (Criteria) this;
        }

        public Criteria andYqSexIsNotNull() {
            addCriterion("YQ_SEX is not null");
            return (Criteria) this;
        }

        public Criteria andYqSexEqualTo(String value) {
            addCriterion("YQ_SEX =", value, "yqSex");
            return (Criteria) this;
        }

        public Criteria andYqSexNotEqualTo(String value) {
            addCriterion("YQ_SEX <>", value, "yqSex");
            return (Criteria) this;
        }

        public Criteria andYqSexGreaterThan(String value) {
            addCriterion("YQ_SEX >", value, "yqSex");
            return (Criteria) this;
        }

        public Criteria andYqSexGreaterThanOrEqualTo(String value) {
            addCriterion("YQ_SEX >=", value, "yqSex");
            return (Criteria) this;
        }

        public Criteria andYqSexLessThan(String value) {
            addCriterion("YQ_SEX <", value, "yqSex");
            return (Criteria) this;
        }

        public Criteria andYqSexLessThanOrEqualTo(String value) {
            addCriterion("YQ_SEX <=", value, "yqSex");
            return (Criteria) this;
        }

        public Criteria andYqSexLike(String value) {
            addCriterion("YQ_SEX like", value, "yqSex");
            return (Criteria) this;
        }

        public Criteria andYqSexNotLike(String value) {
            addCriterion("YQ_SEX not like", value, "yqSex");
            return (Criteria) this;
        }

        public Criteria andYqSexIn(List<String> values) {
            addCriterion("YQ_SEX in", values, "yqSex");
            return (Criteria) this;
        }

        public Criteria andYqSexNotIn(List<String> values) {
            addCriterion("YQ_SEX not in", values, "yqSex");
            return (Criteria) this;
        }

        public Criteria andYqSexBetween(String value1, String value2) {
            addCriterion("YQ_SEX between", value1, value2, "yqSex");
            return (Criteria) this;
        }

        public Criteria andYqSexNotBetween(String value1, String value2) {
            addCriterion("YQ_SEX not between", value1, value2, "yqSex");
            return (Criteria) this;
        }

        public Criteria andYqAgeIsNull() {
            addCriterion("YQ_AGE is null");
            return (Criteria) this;
        }

        public Criteria andYqAgeIsNotNull() {
            addCriterion("YQ_AGE is not null");
            return (Criteria) this;
        }

        public Criteria andYqAgeEqualTo(String value) {
            addCriterion("YQ_AGE =", value, "yqAge");
            return (Criteria) this;
        }

        public Criteria andYqAgeNotEqualTo(String value) {
            addCriterion("YQ_AGE <>", value, "yqAge");
            return (Criteria) this;
        }

        public Criteria andYqAgeGreaterThan(String value) {
            addCriterion("YQ_AGE >", value, "yqAge");
            return (Criteria) this;
        }

        public Criteria andYqAgeGreaterThanOrEqualTo(String value) {
            addCriterion("YQ_AGE >=", value, "yqAge");
            return (Criteria) this;
        }

        public Criteria andYqAgeLessThan(String value) {
            addCriterion("YQ_AGE <", value, "yqAge");
            return (Criteria) this;
        }

        public Criteria andYqAgeLessThanOrEqualTo(String value) {
            addCriterion("YQ_AGE <=", value, "yqAge");
            return (Criteria) this;
        }

        public Criteria andYqAgeLike(String value) {
            addCriterion("YQ_AGE like", value, "yqAge");
            return (Criteria) this;
        }

        public Criteria andYqAgeNotLike(String value) {
            addCriterion("YQ_AGE not like", value, "yqAge");
            return (Criteria) this;
        }

        public Criteria andYqAgeIn(List<String> values) {
            addCriterion("YQ_AGE in", values, "yqAge");
            return (Criteria) this;
        }

        public Criteria andYqAgeNotIn(List<String> values) {
            addCriterion("YQ_AGE not in", values, "yqAge");
            return (Criteria) this;
        }

        public Criteria andYqAgeBetween(String value1, String value2) {
            addCriterion("YQ_AGE between", value1, value2, "yqAge");
            return (Criteria) this;
        }

        public Criteria andYqAgeNotBetween(String value1, String value2) {
            addCriterion("YQ_AGE not between", value1, value2, "yqAge");
            return (Criteria) this;
        }

        public Criteria andYqXueliIsNull() {
            addCriterion("YQ_XUELI is null");
            return (Criteria) this;
        }

        public Criteria andYqXueliIsNotNull() {
            addCriterion("YQ_XUELI is not null");
            return (Criteria) this;
        }

        public Criteria andYqXueliEqualTo(String value) {
            addCriterion("YQ_XUELI =", value, "yqXueli");
            return (Criteria) this;
        }

        public Criteria andYqXueliNotEqualTo(String value) {
            addCriterion("YQ_XUELI <>", value, "yqXueli");
            return (Criteria) this;
        }

        public Criteria andYqXueliGreaterThan(String value) {
            addCriterion("YQ_XUELI >", value, "yqXueli");
            return (Criteria) this;
        }

        public Criteria andYqXueliGreaterThanOrEqualTo(String value) {
            addCriterion("YQ_XUELI >=", value, "yqXueli");
            return (Criteria) this;
        }

        public Criteria andYqXueliLessThan(String value) {
            addCriterion("YQ_XUELI <", value, "yqXueli");
            return (Criteria) this;
        }

        public Criteria andYqXueliLessThanOrEqualTo(String value) {
            addCriterion("YQ_XUELI <=", value, "yqXueli");
            return (Criteria) this;
        }

        public Criteria andYqXueliLike(String value) {
            addCriterion("YQ_XUELI like", value, "yqXueli");
            return (Criteria) this;
        }

        public Criteria andYqXueliNotLike(String value) {
            addCriterion("YQ_XUELI not like", value, "yqXueli");
            return (Criteria) this;
        }

        public Criteria andYqXueliIn(List<String> values) {
            addCriterion("YQ_XUELI in", values, "yqXueli");
            return (Criteria) this;
        }

        public Criteria andYqXueliNotIn(List<String> values) {
            addCriterion("YQ_XUELI not in", values, "yqXueli");
            return (Criteria) this;
        }

        public Criteria andYqXueliBetween(String value1, String value2) {
            addCriterion("YQ_XUELI between", value1, value2, "yqXueli");
            return (Criteria) this;
        }

        public Criteria andYqXueliNotBetween(String value1, String value2) {
            addCriterion("YQ_XUELI not between", value1, value2, "yqXueli");
            return (Criteria) this;
        }

        public Criteria andYqXingziIsNull() {
            addCriterion("YQ_XINGZI is null");
            return (Criteria) this;
        }

        public Criteria andYqXingziIsNotNull() {
            addCriterion("YQ_XINGZI is not null");
            return (Criteria) this;
        }

        public Criteria andYqXingziEqualTo(String value) {
            addCriterion("YQ_XINGZI =", value, "yqXingzi");
            return (Criteria) this;
        }

        public Criteria andYqXingziNotEqualTo(String value) {
            addCriterion("YQ_XINGZI <>", value, "yqXingzi");
            return (Criteria) this;
        }

        public Criteria andYqXingziGreaterThan(String value) {
            addCriterion("YQ_XINGZI >", value, "yqXingzi");
            return (Criteria) this;
        }

        public Criteria andYqXingziGreaterThanOrEqualTo(String value) {
            addCriterion("YQ_XINGZI >=", value, "yqXingzi");
            return (Criteria) this;
        }

        public Criteria andYqXingziLessThan(String value) {
            addCriterion("YQ_XINGZI <", value, "yqXingzi");
            return (Criteria) this;
        }

        public Criteria andYqXingziLessThanOrEqualTo(String value) {
            addCriterion("YQ_XINGZI <=", value, "yqXingzi");
            return (Criteria) this;
        }

        public Criteria andYqXingziLike(String value) {
            addCriterion("YQ_XINGZI like", value, "yqXingzi");
            return (Criteria) this;
        }

        public Criteria andYqXingziNotLike(String value) {
            addCriterion("YQ_XINGZI not like", value, "yqXingzi");
            return (Criteria) this;
        }

        public Criteria andYqXingziIn(List<String> values) {
            addCriterion("YQ_XINGZI in", values, "yqXingzi");
            return (Criteria) this;
        }

        public Criteria andYqXingziNotIn(List<String> values) {
            addCriterion("YQ_XINGZI not in", values, "yqXingzi");
            return (Criteria) this;
        }

        public Criteria andYqXingziBetween(String value1, String value2) {
            addCriterion("YQ_XINGZI between", value1, value2, "yqXingzi");
            return (Criteria) this;
        }

        public Criteria andYqXingziNotBetween(String value1, String value2) {
            addCriterion("YQ_XINGZI not between", value1, value2, "yqXingzi");
            return (Criteria) this;
        }

        public Criteria andYqJingyanIsNull() {
            addCriterion("YQ_JINGYAN is null");
            return (Criteria) this;
        }

        public Criteria andYqJingyanIsNotNull() {
            addCriterion("YQ_JINGYAN is not null");
            return (Criteria) this;
        }

        public Criteria andYqJingyanEqualTo(String value) {
            addCriterion("YQ_JINGYAN =", value, "yqJingyan");
            return (Criteria) this;
        }

        public Criteria andYqJingyanNotEqualTo(String value) {
            addCriterion("YQ_JINGYAN <>", value, "yqJingyan");
            return (Criteria) this;
        }

        public Criteria andYqJingyanGreaterThan(String value) {
            addCriterion("YQ_JINGYAN >", value, "yqJingyan");
            return (Criteria) this;
        }

        public Criteria andYqJingyanGreaterThanOrEqualTo(String value) {
            addCriterion("YQ_JINGYAN >=", value, "yqJingyan");
            return (Criteria) this;
        }

        public Criteria andYqJingyanLessThan(String value) {
            addCriterion("YQ_JINGYAN <", value, "yqJingyan");
            return (Criteria) this;
        }

        public Criteria andYqJingyanLessThanOrEqualTo(String value) {
            addCriterion("YQ_JINGYAN <=", value, "yqJingyan");
            return (Criteria) this;
        }

        public Criteria andYqJingyanLike(String value) {
            addCriterion("YQ_JINGYAN like", value, "yqJingyan");
            return (Criteria) this;
        }

        public Criteria andYqJingyanNotLike(String value) {
            addCriterion("YQ_JINGYAN not like", value, "yqJingyan");
            return (Criteria) this;
        }

        public Criteria andYqJingyanIn(List<String> values) {
            addCriterion("YQ_JINGYAN in", values, "yqJingyan");
            return (Criteria) this;
        }

        public Criteria andYqJingyanNotIn(List<String> values) {
            addCriterion("YQ_JINGYAN not in", values, "yqJingyan");
            return (Criteria) this;
        }

        public Criteria andYqJingyanBetween(String value1, String value2) {
            addCriterion("YQ_JINGYAN between", value1, value2, "yqJingyan");
            return (Criteria) this;
        }

        public Criteria andYqJingyanNotBetween(String value1, String value2) {
            addCriterion("YQ_JINGYAN not between", value1, value2, "yqJingyan");
            return (Criteria) this;
        }

        public Criteria andYqZhizeIsNull() {
            addCriterion("YQ_ZHIZE is null");
            return (Criteria) this;
        }

        public Criteria andYqZhizeIsNotNull() {
            addCriterion("YQ_ZHIZE is not null");
            return (Criteria) this;
        }

        public Criteria andYqZhizeEqualTo(String value) {
            addCriterion("YQ_ZHIZE =", value, "yqZhize");
            return (Criteria) this;
        }

        public Criteria andYqZhizeNotEqualTo(String value) {
            addCriterion("YQ_ZHIZE <>", value, "yqZhize");
            return (Criteria) this;
        }

        public Criteria andYqZhizeGreaterThan(String value) {
            addCriterion("YQ_ZHIZE >", value, "yqZhize");
            return (Criteria) this;
        }

        public Criteria andYqZhizeGreaterThanOrEqualTo(String value) {
            addCriterion("YQ_ZHIZE >=", value, "yqZhize");
            return (Criteria) this;
        }

        public Criteria andYqZhizeLessThan(String value) {
            addCriterion("YQ_ZHIZE <", value, "yqZhize");
            return (Criteria) this;
        }

        public Criteria andYqZhizeLessThanOrEqualTo(String value) {
            addCriterion("YQ_ZHIZE <=", value, "yqZhize");
            return (Criteria) this;
        }

        public Criteria andYqZhizeLike(String value) {
            addCriterion("YQ_ZHIZE like", value, "yqZhize");
            return (Criteria) this;
        }

        public Criteria andYqZhizeNotLike(String value) {
            addCriterion("YQ_ZHIZE not like", value, "yqZhize");
            return (Criteria) this;
        }

        public Criteria andYqZhizeIn(List<String> values) {
            addCriterion("YQ_ZHIZE in", values, "yqZhize");
            return (Criteria) this;
        }

        public Criteria andYqZhizeNotIn(List<String> values) {
            addCriterion("YQ_ZHIZE not in", values, "yqZhize");
            return (Criteria) this;
        }

        public Criteria andYqZhizeBetween(String value1, String value2) {
            addCriterion("YQ_ZHIZE between", value1, value2, "yqZhize");
            return (Criteria) this;
        }

        public Criteria andYqZhizeNotBetween(String value1, String value2) {
            addCriterion("YQ_ZHIZE not between", value1, value2, "yqZhize");
            return (Criteria) this;
        }

        public Criteria andSpBumenIsNull() {
            addCriterion("SP_BUMEN is null");
            return (Criteria) this;
        }

        public Criteria andSpBumenIsNotNull() {
            addCriterion("SP_BUMEN is not null");
            return (Criteria) this;
        }

        public Criteria andSpBumenEqualTo(String value) {
            addCriterion("SP_BUMEN =", value, "spBumen");
            return (Criteria) this;
        }

        public Criteria andSpBumenNotEqualTo(String value) {
            addCriterion("SP_BUMEN <>", value, "spBumen");
            return (Criteria) this;
        }

        public Criteria andSpBumenGreaterThan(String value) {
            addCriterion("SP_BUMEN >", value, "spBumen");
            return (Criteria) this;
        }

        public Criteria andSpBumenGreaterThanOrEqualTo(String value) {
            addCriterion("SP_BUMEN >=", value, "spBumen");
            return (Criteria) this;
        }

        public Criteria andSpBumenLessThan(String value) {
            addCriterion("SP_BUMEN <", value, "spBumen");
            return (Criteria) this;
        }

        public Criteria andSpBumenLessThanOrEqualTo(String value) {
            addCriterion("SP_BUMEN <=", value, "spBumen");
            return (Criteria) this;
        }

        public Criteria andSpBumenLike(String value) {
            addCriterion("SP_BUMEN like", value, "spBumen");
            return (Criteria) this;
        }

        public Criteria andSpBumenNotLike(String value) {
            addCriterion("SP_BUMEN not like", value, "spBumen");
            return (Criteria) this;
        }

        public Criteria andSpBumenIn(List<String> values) {
            addCriterion("SP_BUMEN in", values, "spBumen");
            return (Criteria) this;
        }

        public Criteria andSpBumenNotIn(List<String> values) {
            addCriterion("SP_BUMEN not in", values, "spBumen");
            return (Criteria) this;
        }

        public Criteria andSpBumenBetween(String value1, String value2) {
            addCriterion("SP_BUMEN between", value1, value2, "spBumen");
            return (Criteria) this;
        }

        public Criteria andSpBumenNotBetween(String value1, String value2) {
            addCriterion("SP_BUMEN not between", value1, value2, "spBumen");
            return (Criteria) this;
        }

        public Criteria andSpBmtimeIsNull() {
            addCriterion("SP_BMTIME is null");
            return (Criteria) this;
        }

        public Criteria andSpBmtimeIsNotNull() {
            addCriterion("SP_BMTIME is not null");
            return (Criteria) this;
        }

        public Criteria andSpBmtimeEqualTo(String value) {
            addCriterion("SP_BMTIME =", value, "spBmtime");
            return (Criteria) this;
        }

        public Criteria andSpBmtimeNotEqualTo(String value) {
            addCriterion("SP_BMTIME <>", value, "spBmtime");
            return (Criteria) this;
        }

        public Criteria andSpBmtimeGreaterThan(String value) {
            addCriterion("SP_BMTIME >", value, "spBmtime");
            return (Criteria) this;
        }

        public Criteria andSpBmtimeGreaterThanOrEqualTo(String value) {
            addCriterion("SP_BMTIME >=", value, "spBmtime");
            return (Criteria) this;
        }

        public Criteria andSpBmtimeLessThan(String value) {
            addCriterion("SP_BMTIME <", value, "spBmtime");
            return (Criteria) this;
        }

        public Criteria andSpBmtimeLessThanOrEqualTo(String value) {
            addCriterion("SP_BMTIME <=", value, "spBmtime");
            return (Criteria) this;
        }

        public Criteria andSpBmtimeLike(String value) {
            addCriterion("SP_BMTIME like", value, "spBmtime");
            return (Criteria) this;
        }

        public Criteria andSpBmtimeNotLike(String value) {
            addCriterion("SP_BMTIME not like", value, "spBmtime");
            return (Criteria) this;
        }

        public Criteria andSpBmtimeIn(List<String> values) {
            addCriterion("SP_BMTIME in", values, "spBmtime");
            return (Criteria) this;
        }

        public Criteria andSpBmtimeNotIn(List<String> values) {
            addCriterion("SP_BMTIME not in", values, "spBmtime");
            return (Criteria) this;
        }

        public Criteria andSpBmtimeBetween(String value1, String value2) {
            addCriterion("SP_BMTIME between", value1, value2, "spBmtime");
            return (Criteria) this;
        }

        public Criteria andSpBmtimeNotBetween(String value1, String value2) {
            addCriterion("SP_BMTIME not between", value1, value2, "spBmtime");
            return (Criteria) this;
        }

        public Criteria andSpRenshiIsNull() {
            addCriterion("SP_RENSHI is null");
            return (Criteria) this;
        }

        public Criteria andSpRenshiIsNotNull() {
            addCriterion("SP_RENSHI is not null");
            return (Criteria) this;
        }

        public Criteria andSpRenshiEqualTo(String value) {
            addCriterion("SP_RENSHI =", value, "spRenshi");
            return (Criteria) this;
        }

        public Criteria andSpRenshiNotEqualTo(String value) {
            addCriterion("SP_RENSHI <>", value, "spRenshi");
            return (Criteria) this;
        }

        public Criteria andSpRenshiGreaterThan(String value) {
            addCriterion("SP_RENSHI >", value, "spRenshi");
            return (Criteria) this;
        }

        public Criteria andSpRenshiGreaterThanOrEqualTo(String value) {
            addCriterion("SP_RENSHI >=", value, "spRenshi");
            return (Criteria) this;
        }

        public Criteria andSpRenshiLessThan(String value) {
            addCriterion("SP_RENSHI <", value, "spRenshi");
            return (Criteria) this;
        }

        public Criteria andSpRenshiLessThanOrEqualTo(String value) {
            addCriterion("SP_RENSHI <=", value, "spRenshi");
            return (Criteria) this;
        }

        public Criteria andSpRenshiLike(String value) {
            addCriterion("SP_RENSHI like", value, "spRenshi");
            return (Criteria) this;
        }

        public Criteria andSpRenshiNotLike(String value) {
            addCriterion("SP_RENSHI not like", value, "spRenshi");
            return (Criteria) this;
        }

        public Criteria andSpRenshiIn(List<String> values) {
            addCriterion("SP_RENSHI in", values, "spRenshi");
            return (Criteria) this;
        }

        public Criteria andSpRenshiNotIn(List<String> values) {
            addCriterion("SP_RENSHI not in", values, "spRenshi");
            return (Criteria) this;
        }

        public Criteria andSpRenshiBetween(String value1, String value2) {
            addCriterion("SP_RENSHI between", value1, value2, "spRenshi");
            return (Criteria) this;
        }

        public Criteria andSpRenshiNotBetween(String value1, String value2) {
            addCriterion("SP_RENSHI not between", value1, value2, "spRenshi");
            return (Criteria) this;
        }

        public Criteria andSpRstimeIsNull() {
            addCriterion("SP_RSTIME is null");
            return (Criteria) this;
        }

        public Criteria andSpRstimeIsNotNull() {
            addCriterion("SP_RSTIME is not null");
            return (Criteria) this;
        }

        public Criteria andSpRstimeEqualTo(String value) {
            addCriterion("SP_RSTIME =", value, "spRstime");
            return (Criteria) this;
        }

        public Criteria andSpRstimeNotEqualTo(String value) {
            addCriterion("SP_RSTIME <>", value, "spRstime");
            return (Criteria) this;
        }

        public Criteria andSpRstimeGreaterThan(String value) {
            addCriterion("SP_RSTIME >", value, "spRstime");
            return (Criteria) this;
        }

        public Criteria andSpRstimeGreaterThanOrEqualTo(String value) {
            addCriterion("SP_RSTIME >=", value, "spRstime");
            return (Criteria) this;
        }

        public Criteria andSpRstimeLessThan(String value) {
            addCriterion("SP_RSTIME <", value, "spRstime");
            return (Criteria) this;
        }

        public Criteria andSpRstimeLessThanOrEqualTo(String value) {
            addCriterion("SP_RSTIME <=", value, "spRstime");
            return (Criteria) this;
        }

        public Criteria andSpRstimeLike(String value) {
            addCriterion("SP_RSTIME like", value, "spRstime");
            return (Criteria) this;
        }

        public Criteria andSpRstimeNotLike(String value) {
            addCriterion("SP_RSTIME not like", value, "spRstime");
            return (Criteria) this;
        }

        public Criteria andSpRstimeIn(List<String> values) {
            addCriterion("SP_RSTIME in", values, "spRstime");
            return (Criteria) this;
        }

        public Criteria andSpRstimeNotIn(List<String> values) {
            addCriterion("SP_RSTIME not in", values, "spRstime");
            return (Criteria) this;
        }

        public Criteria andSpRstimeBetween(String value1, String value2) {
            addCriterion("SP_RSTIME between", value1, value2, "spRstime");
            return (Criteria) this;
        }

        public Criteria andSpRstimeNotBetween(String value1, String value2) {
            addCriterion("SP_RSTIME not between", value1, value2, "spRstime");
            return (Criteria) this;
        }

        public Criteria andSpJingliIsNull() {
            addCriterion("SP_JINGLI is null");
            return (Criteria) this;
        }

        public Criteria andSpJingliIsNotNull() {
            addCriterion("SP_JINGLI is not null");
            return (Criteria) this;
        }

        public Criteria andSpJingliEqualTo(String value) {
            addCriterion("SP_JINGLI =", value, "spJingli");
            return (Criteria) this;
        }

        public Criteria andSpJingliNotEqualTo(String value) {
            addCriterion("SP_JINGLI <>", value, "spJingli");
            return (Criteria) this;
        }

        public Criteria andSpJingliGreaterThan(String value) {
            addCriterion("SP_JINGLI >", value, "spJingli");
            return (Criteria) this;
        }

        public Criteria andSpJingliGreaterThanOrEqualTo(String value) {
            addCriterion("SP_JINGLI >=", value, "spJingli");
            return (Criteria) this;
        }

        public Criteria andSpJingliLessThan(String value) {
            addCriterion("SP_JINGLI <", value, "spJingli");
            return (Criteria) this;
        }

        public Criteria andSpJingliLessThanOrEqualTo(String value) {
            addCriterion("SP_JINGLI <=", value, "spJingli");
            return (Criteria) this;
        }

        public Criteria andSpJingliLike(String value) {
            addCriterion("SP_JINGLI like", value, "spJingli");
            return (Criteria) this;
        }

        public Criteria andSpJingliNotLike(String value) {
            addCriterion("SP_JINGLI not like", value, "spJingli");
            return (Criteria) this;
        }

        public Criteria andSpJingliIn(List<String> values) {
            addCriterion("SP_JINGLI in", values, "spJingli");
            return (Criteria) this;
        }

        public Criteria andSpJingliNotIn(List<String> values) {
            addCriterion("SP_JINGLI not in", values, "spJingli");
            return (Criteria) this;
        }

        public Criteria andSpJingliBetween(String value1, String value2) {
            addCriterion("SP_JINGLI between", value1, value2, "spJingli");
            return (Criteria) this;
        }

        public Criteria andSpJingliNotBetween(String value1, String value2) {
            addCriterion("SP_JINGLI not between", value1, value2, "spJingli");
            return (Criteria) this;
        }

        public Criteria andSpJltimeIsNull() {
            addCriterion("SP_JLTIME is null");
            return (Criteria) this;
        }

        public Criteria andSpJltimeIsNotNull() {
            addCriterion("SP_JLTIME is not null");
            return (Criteria) this;
        }

        public Criteria andSpJltimeEqualTo(String value) {
            addCriterion("SP_JLTIME =", value, "spJltime");
            return (Criteria) this;
        }

        public Criteria andSpJltimeNotEqualTo(String value) {
            addCriterion("SP_JLTIME <>", value, "spJltime");
            return (Criteria) this;
        }

        public Criteria andSpJltimeGreaterThan(String value) {
            addCriterion("SP_JLTIME >", value, "spJltime");
            return (Criteria) this;
        }

        public Criteria andSpJltimeGreaterThanOrEqualTo(String value) {
            addCriterion("SP_JLTIME >=", value, "spJltime");
            return (Criteria) this;
        }

        public Criteria andSpJltimeLessThan(String value) {
            addCriterion("SP_JLTIME <", value, "spJltime");
            return (Criteria) this;
        }

        public Criteria andSpJltimeLessThanOrEqualTo(String value) {
            addCriterion("SP_JLTIME <=", value, "spJltime");
            return (Criteria) this;
        }

        public Criteria andSpJltimeLike(String value) {
            addCriterion("SP_JLTIME like", value, "spJltime");
            return (Criteria) this;
        }

        public Criteria andSpJltimeNotLike(String value) {
            addCriterion("SP_JLTIME not like", value, "spJltime");
            return (Criteria) this;
        }

        public Criteria andSpJltimeIn(List<String> values) {
            addCriterion("SP_JLTIME in", values, "spJltime");
            return (Criteria) this;
        }

        public Criteria andSpJltimeNotIn(List<String> values) {
            addCriterion("SP_JLTIME not in", values, "spJltime");
            return (Criteria) this;
        }

        public Criteria andSpJltimeBetween(String value1, String value2) {
            addCriterion("SP_JLTIME between", value1, value2, "spJltime");
            return (Criteria) this;
        }

        public Criteria andSpJltimeNotBetween(String value1, String value2) {
            addCriterion("SP_JLTIME not between", value1, value2, "spJltime");
            return (Criteria) this;
        }

        public Criteria andSpBmadviceIsNull() {
            addCriterion("SP_BMADVICE is null");
            return (Criteria) this;
        }

        public Criteria andSpBmadviceIsNotNull() {
            addCriterion("SP_BMADVICE is not null");
            return (Criteria) this;
        }

        public Criteria andSpBmadviceEqualTo(String value) {
            addCriterion("SP_BMADVICE =", value, "spBmadvice");
            return (Criteria) this;
        }

        public Criteria andSpBmadviceNotEqualTo(String value) {
            addCriterion("SP_BMADVICE <>", value, "spBmadvice");
            return (Criteria) this;
        }

        public Criteria andSpBmadviceGreaterThan(String value) {
            addCriterion("SP_BMADVICE >", value, "spBmadvice");
            return (Criteria) this;
        }

        public Criteria andSpBmadviceGreaterThanOrEqualTo(String value) {
            addCriterion("SP_BMADVICE >=", value, "spBmadvice");
            return (Criteria) this;
        }

        public Criteria andSpBmadviceLessThan(String value) {
            addCriterion("SP_BMADVICE <", value, "spBmadvice");
            return (Criteria) this;
        }

        public Criteria andSpBmadviceLessThanOrEqualTo(String value) {
            addCriterion("SP_BMADVICE <=", value, "spBmadvice");
            return (Criteria) this;
        }

        public Criteria andSpBmadviceLike(String value) {
            addCriterion("SP_BMADVICE like", value, "spBmadvice");
            return (Criteria) this;
        }

        public Criteria andSpBmadviceNotLike(String value) {
            addCriterion("SP_BMADVICE not like", value, "spBmadvice");
            return (Criteria) this;
        }

        public Criteria andSpBmadviceIn(List<String> values) {
            addCriterion("SP_BMADVICE in", values, "spBmadvice");
            return (Criteria) this;
        }

        public Criteria andSpBmadviceNotIn(List<String> values) {
            addCriterion("SP_BMADVICE not in", values, "spBmadvice");
            return (Criteria) this;
        }

        public Criteria andSpBmadviceBetween(String value1, String value2) {
            addCriterion("SP_BMADVICE between", value1, value2, "spBmadvice");
            return (Criteria) this;
        }

        public Criteria andSpBmadviceNotBetween(String value1, String value2) {
            addCriterion("SP_BMADVICE not between", value1, value2, "spBmadvice");
            return (Criteria) this;
        }

        public Criteria andSpRsadviceIsNull() {
            addCriterion("SP_RSADVICE is null");
            return (Criteria) this;
        }

        public Criteria andSpRsadviceIsNotNull() {
            addCriterion("SP_RSADVICE is not null");
            return (Criteria) this;
        }

        public Criteria andSpRsadviceEqualTo(String value) {
            addCriterion("SP_RSADVICE =", value, "spRsadvice");
            return (Criteria) this;
        }

        public Criteria andSpRsadviceNotEqualTo(String value) {
            addCriterion("SP_RSADVICE <>", value, "spRsadvice");
            return (Criteria) this;
        }

        public Criteria andSpRsadviceGreaterThan(String value) {
            addCriterion("SP_RSADVICE >", value, "spRsadvice");
            return (Criteria) this;
        }

        public Criteria andSpRsadviceGreaterThanOrEqualTo(String value) {
            addCriterion("SP_RSADVICE >=", value, "spRsadvice");
            return (Criteria) this;
        }

        public Criteria andSpRsadviceLessThan(String value) {
            addCriterion("SP_RSADVICE <", value, "spRsadvice");
            return (Criteria) this;
        }

        public Criteria andSpRsadviceLessThanOrEqualTo(String value) {
            addCriterion("SP_RSADVICE <=", value, "spRsadvice");
            return (Criteria) this;
        }

        public Criteria andSpRsadviceLike(String value) {
            addCriterion("SP_RSADVICE like", value, "spRsadvice");
            return (Criteria) this;
        }

        public Criteria andSpRsadviceNotLike(String value) {
            addCriterion("SP_RSADVICE not like", value, "spRsadvice");
            return (Criteria) this;
        }

        public Criteria andSpRsadviceIn(List<String> values) {
            addCriterion("SP_RSADVICE in", values, "spRsadvice");
            return (Criteria) this;
        }

        public Criteria andSpRsadviceNotIn(List<String> values) {
            addCriterion("SP_RSADVICE not in", values, "spRsadvice");
            return (Criteria) this;
        }

        public Criteria andSpRsadviceBetween(String value1, String value2) {
            addCriterion("SP_RSADVICE between", value1, value2, "spRsadvice");
            return (Criteria) this;
        }

        public Criteria andSpRsadviceNotBetween(String value1, String value2) {
            addCriterion("SP_RSADVICE not between", value1, value2, "spRsadvice");
            return (Criteria) this;
        }

        public Criteria andSpJladviceIsNull() {
            addCriterion("SP_JLADVICE is null");
            return (Criteria) this;
        }

        public Criteria andSpJladviceIsNotNull() {
            addCriterion("SP_JLADVICE is not null");
            return (Criteria) this;
        }

        public Criteria andSpJladviceEqualTo(String value) {
            addCriterion("SP_JLADVICE =", value, "spJladvice");
            return (Criteria) this;
        }

        public Criteria andSpJladviceNotEqualTo(String value) {
            addCriterion("SP_JLADVICE <>", value, "spJladvice");
            return (Criteria) this;
        }

        public Criteria andSpJladviceGreaterThan(String value) {
            addCriterion("SP_JLADVICE >", value, "spJladvice");
            return (Criteria) this;
        }

        public Criteria andSpJladviceGreaterThanOrEqualTo(String value) {
            addCriterion("SP_JLADVICE >=", value, "spJladvice");
            return (Criteria) this;
        }

        public Criteria andSpJladviceLessThan(String value) {
            addCriterion("SP_JLADVICE <", value, "spJladvice");
            return (Criteria) this;
        }

        public Criteria andSpJladviceLessThanOrEqualTo(String value) {
            addCriterion("SP_JLADVICE <=", value, "spJladvice");
            return (Criteria) this;
        }

        public Criteria andSpJladviceLike(String value) {
            addCriterion("SP_JLADVICE like", value, "spJladvice");
            return (Criteria) this;
        }

        public Criteria andSpJladviceNotLike(String value) {
            addCriterion("SP_JLADVICE not like", value, "spJladvice");
            return (Criteria) this;
        }

        public Criteria andSpJladviceIn(List<String> values) {
            addCriterion("SP_JLADVICE in", values, "spJladvice");
            return (Criteria) this;
        }

        public Criteria andSpJladviceNotIn(List<String> values) {
            addCriterion("SP_JLADVICE not in", values, "spJladvice");
            return (Criteria) this;
        }

        public Criteria andSpJladviceBetween(String value1, String value2) {
            addCriterion("SP_JLADVICE between", value1, value2, "spJladvice");
            return (Criteria) this;
        }

        public Criteria andSpJladviceNotBetween(String value1, String value2) {
            addCriterion("SP_JLADVICE not between", value1, value2, "spJladvice");
            return (Criteria) this;
        }

        public Criteria andYl1IsNull() {
            addCriterion("YL1 is null");
            return (Criteria) this;
        }

        public Criteria andYl1IsNotNull() {
            addCriterion("YL1 is not null");
            return (Criteria) this;
        }

        public Criteria andYl1EqualTo(String value) {
            addCriterion("YL1 =", value, "yl1");
            return (Criteria) this;
        }

        public Criteria andYl1NotEqualTo(String value) {
            addCriterion("YL1 <>", value, "yl1");
            return (Criteria) this;
        }

        public Criteria andYl1GreaterThan(String value) {
            addCriterion("YL1 >", value, "yl1");
            return (Criteria) this;
        }

        public Criteria andYl1GreaterThanOrEqualTo(String value) {
            addCriterion("YL1 >=", value, "yl1");
            return (Criteria) this;
        }

        public Criteria andYl1LessThan(String value) {
            addCriterion("YL1 <", value, "yl1");
            return (Criteria) this;
        }

        public Criteria andYl1LessThanOrEqualTo(String value) {
            addCriterion("YL1 <=", value, "yl1");
            return (Criteria) this;
        }

        public Criteria andYl1Like(String value) {
            addCriterion("YL1 like", value, "yl1");
            return (Criteria) this;
        }

        public Criteria andYl1NotLike(String value) {
            addCriterion("YL1 not like", value, "yl1");
            return (Criteria) this;
        }

        public Criteria andYl1In(List<String> values) {
            addCriterion("YL1 in", values, "yl1");
            return (Criteria) this;
        }

        public Criteria andYl1NotIn(List<String> values) {
            addCriterion("YL1 not in", values, "yl1");
            return (Criteria) this;
        }

        public Criteria andYl1Between(String value1, String value2) {
            addCriterion("YL1 between", value1, value2, "yl1");
            return (Criteria) this;
        }

        public Criteria andYl1NotBetween(String value1, String value2) {
            addCriterion("YL1 not between", value1, value2, "yl1");
            return (Criteria) this;
        }

        public Criteria andYl2IsNull() {
            addCriterion("YL2 is null");
            return (Criteria) this;
        }

        public Criteria andYl2IsNotNull() {
            addCriterion("YL2 is not null");
            return (Criteria) this;
        }

        public Criteria andYl2EqualTo(String value) {
            addCriterion("YL2 =", value, "yl2");
            return (Criteria) this;
        }

        public Criteria andYl2NotEqualTo(String value) {
            addCriterion("YL2 <>", value, "yl2");
            return (Criteria) this;
        }

        public Criteria andYl2GreaterThan(String value) {
            addCriterion("YL2 >", value, "yl2");
            return (Criteria) this;
        }

        public Criteria andYl2GreaterThanOrEqualTo(String value) {
            addCriterion("YL2 >=", value, "yl2");
            return (Criteria) this;
        }

        public Criteria andYl2LessThan(String value) {
            addCriterion("YL2 <", value, "yl2");
            return (Criteria) this;
        }

        public Criteria andYl2LessThanOrEqualTo(String value) {
            addCriterion("YL2 <=", value, "yl2");
            return (Criteria) this;
        }

        public Criteria andYl2Like(String value) {
            addCriterion("YL2 like", value, "yl2");
            return (Criteria) this;
        }

        public Criteria andYl2NotLike(String value) {
            addCriterion("YL2 not like", value, "yl2");
            return (Criteria) this;
        }

        public Criteria andYl2In(List<String> values) {
            addCriterion("YL2 in", values, "yl2");
            return (Criteria) this;
        }

        public Criteria andYl2NotIn(List<String> values) {
            addCriterion("YL2 not in", values, "yl2");
            return (Criteria) this;
        }

        public Criteria andYl2Between(String value1, String value2) {
            addCriterion("YL2 between", value1, value2, "yl2");
            return (Criteria) this;
        }

        public Criteria andYl2NotBetween(String value1, String value2) {
            addCriterion("YL2 not between", value1, value2, "yl2");
            return (Criteria) this;
        }

        public Criteria andYl3IsNull() {
            addCriterion("YL3 is null");
            return (Criteria) this;
        }

        public Criteria andYl3IsNotNull() {
            addCriterion("YL3 is not null");
            return (Criteria) this;
        }

        public Criteria andYl3EqualTo(String value) {
            addCriterion("YL3 =", value, "yl3");
            return (Criteria) this;
        }

        public Criteria andYl3NotEqualTo(String value) {
            addCriterion("YL3 <>", value, "yl3");
            return (Criteria) this;
        }

        public Criteria andYl3GreaterThan(String value) {
            addCriterion("YL3 >", value, "yl3");
            return (Criteria) this;
        }

        public Criteria andYl3GreaterThanOrEqualTo(String value) {
            addCriterion("YL3 >=", value, "yl3");
            return (Criteria) this;
        }

        public Criteria andYl3LessThan(String value) {
            addCriterion("YL3 <", value, "yl3");
            return (Criteria) this;
        }

        public Criteria andYl3LessThanOrEqualTo(String value) {
            addCriterion("YL3 <=", value, "yl3");
            return (Criteria) this;
        }

        public Criteria andYl3Like(String value) {
            addCriterion("YL3 like", value, "yl3");
            return (Criteria) this;
        }

        public Criteria andYl3NotLike(String value) {
            addCriterion("YL3 not like", value, "yl3");
            return (Criteria) this;
        }

        public Criteria andYl3In(List<String> values) {
            addCriterion("YL3 in", values, "yl3");
            return (Criteria) this;
        }

        public Criteria andYl3NotIn(List<String> values) {
            addCriterion("YL3 not in", values, "yl3");
            return (Criteria) this;
        }

        public Criteria andYl3Between(String value1, String value2) {
            addCriterion("YL3 between", value1, value2, "yl3");
            return (Criteria) this;
        }

        public Criteria andYl3NotBetween(String value1, String value2) {
            addCriterion("YL3 not between", value1, value2, "yl3");
            return (Criteria) this;
        }

        public Criteria andYl4IsNull() {
            addCriterion("YL4 is null");
            return (Criteria) this;
        }

        public Criteria andYl4IsNotNull() {
            addCriterion("YL4 is not null");
            return (Criteria) this;
        }

        public Criteria andYl4EqualTo(String value) {
            addCriterion("YL4 =", value, "yl4");
            return (Criteria) this;
        }

        public Criteria andYl4NotEqualTo(String value) {
            addCriterion("YL4 <>", value, "yl4");
            return (Criteria) this;
        }

        public Criteria andYl4GreaterThan(String value) {
            addCriterion("YL4 >", value, "yl4");
            return (Criteria) this;
        }

        public Criteria andYl4GreaterThanOrEqualTo(String value) {
            addCriterion("YL4 >=", value, "yl4");
            return (Criteria) this;
        }

        public Criteria andYl4LessThan(String value) {
            addCriterion("YL4 <", value, "yl4");
            return (Criteria) this;
        }

        public Criteria andYl4LessThanOrEqualTo(String value) {
            addCriterion("YL4 <=", value, "yl4");
            return (Criteria) this;
        }

        public Criteria andYl4Like(String value) {
            addCriterion("YL4 like", value, "yl4");
            return (Criteria) this;
        }

        public Criteria andYl4NotLike(String value) {
            addCriterion("YL4 not like", value, "yl4");
            return (Criteria) this;
        }

        public Criteria andYl4In(List<String> values) {
            addCriterion("YL4 in", values, "yl4");
            return (Criteria) this;
        }

        public Criteria andYl4NotIn(List<String> values) {
            addCriterion("YL4 not in", values, "yl4");
            return (Criteria) this;
        }

        public Criteria andYl4Between(String value1, String value2) {
            addCriterion("YL4 between", value1, value2, "yl4");
            return (Criteria) this;
        }

        public Criteria andYl4NotBetween(String value1, String value2) {
            addCriterion("YL4 not between", value1, value2, "yl4");
            return (Criteria) this;
        }

        public Criteria andYl5IsNull() {
            addCriterion("YL5 is null");
            return (Criteria) this;
        }

        public Criteria andYl5IsNotNull() {
            addCriterion("YL5 is not null");
            return (Criteria) this;
        }

        public Criteria andYl5EqualTo(String value) {
            addCriterion("YL5 =", value, "yl5");
            return (Criteria) this;
        }

        public Criteria andYl5NotEqualTo(String value) {
            addCriterion("YL5 <>", value, "yl5");
            return (Criteria) this;
        }

        public Criteria andYl5GreaterThan(String value) {
            addCriterion("YL5 >", value, "yl5");
            return (Criteria) this;
        }

        public Criteria andYl5GreaterThanOrEqualTo(String value) {
            addCriterion("YL5 >=", value, "yl5");
            return (Criteria) this;
        }

        public Criteria andYl5LessThan(String value) {
            addCriterion("YL5 <", value, "yl5");
            return (Criteria) this;
        }

        public Criteria andYl5LessThanOrEqualTo(String value) {
            addCriterion("YL5 <=", value, "yl5");
            return (Criteria) this;
        }

        public Criteria andYl5Like(String value) {
            addCriterion("YL5 like", value, "yl5");
            return (Criteria) this;
        }

        public Criteria andYl5NotLike(String value) {
            addCriterion("YL5 not like", value, "yl5");
            return (Criteria) this;
        }

        public Criteria andYl5In(List<String> values) {
            addCriterion("YL5 in", values, "yl5");
            return (Criteria) this;
        }

        public Criteria andYl5NotIn(List<String> values) {
            addCriterion("YL5 not in", values, "yl5");
            return (Criteria) this;
        }

        public Criteria andYl5Between(String value1, String value2) {
            addCriterion("YL5 between", value1, value2, "yl5");
            return (Criteria) this;
        }

        public Criteria andYl5NotBetween(String value1, String value2) {
            addCriterion("YL5 not between", value1, value2, "yl5");
            return (Criteria) this;
        }

        public Criteria andYl6IsNull() {
            addCriterion("YL6 is null");
            return (Criteria) this;
        }

        public Criteria andYl6IsNotNull() {
            addCriterion("YL6 is not null");
            return (Criteria) this;
        }

        public Criteria andYl6EqualTo(String value) {
            addCriterion("YL6 =", value, "yl6");
            return (Criteria) this;
        }

        public Criteria andYl6NotEqualTo(String value) {
            addCriterion("YL6 <>", value, "yl6");
            return (Criteria) this;
        }

        public Criteria andYl6GreaterThan(String value) {
            addCriterion("YL6 >", value, "yl6");
            return (Criteria) this;
        }

        public Criteria andYl6GreaterThanOrEqualTo(String value) {
            addCriterion("YL6 >=", value, "yl6");
            return (Criteria) this;
        }

        public Criteria andYl6LessThan(String value) {
            addCriterion("YL6 <", value, "yl6");
            return (Criteria) this;
        }

        public Criteria andYl6LessThanOrEqualTo(String value) {
            addCriterion("YL6 <=", value, "yl6");
            return (Criteria) this;
        }

        public Criteria andYl6Like(String value) {
            addCriterion("YL6 like", value, "yl6");
            return (Criteria) this;
        }

        public Criteria andYl6NotLike(String value) {
            addCriterion("YL6 not like", value, "yl6");
            return (Criteria) this;
        }

        public Criteria andYl6In(List<String> values) {
            addCriterion("YL6 in", values, "yl6");
            return (Criteria) this;
        }

        public Criteria andYl6NotIn(List<String> values) {
            addCriterion("YL6 not in", values, "yl6");
            return (Criteria) this;
        }

        public Criteria andYl6Between(String value1, String value2) {
            addCriterion("YL6 between", value1, value2, "yl6");
            return (Criteria) this;
        }

        public Criteria andYl6NotBetween(String value1, String value2) {
            addCriterion("YL6 not between", value1, value2, "yl6");
            return (Criteria) this;
        }

        public Criteria andYl7IsNull() {
            addCriterion("YL7 is null");
            return (Criteria) this;
        }

        public Criteria andYl7IsNotNull() {
            addCriterion("YL7 is not null");
            return (Criteria) this;
        }

        public Criteria andYl7EqualTo(String value) {
            addCriterion("YL7 =", value, "yl7");
            return (Criteria) this;
        }

        public Criteria andYl7NotEqualTo(String value) {
            addCriterion("YL7 <>", value, "yl7");
            return (Criteria) this;
        }

        public Criteria andYl7GreaterThan(String value) {
            addCriterion("YL7 >", value, "yl7");
            return (Criteria) this;
        }

        public Criteria andYl7GreaterThanOrEqualTo(String value) {
            addCriterion("YL7 >=", value, "yl7");
            return (Criteria) this;
        }

        public Criteria andYl7LessThan(String value) {
            addCriterion("YL7 <", value, "yl7");
            return (Criteria) this;
        }

        public Criteria andYl7LessThanOrEqualTo(String value) {
            addCriterion("YL7 <=", value, "yl7");
            return (Criteria) this;
        }

        public Criteria andYl7Like(String value) {
            addCriterion("YL7 like", value, "yl7");
            return (Criteria) this;
        }

        public Criteria andYl7NotLike(String value) {
            addCriterion("YL7 not like", value, "yl7");
            return (Criteria) this;
        }

        public Criteria andYl7In(List<String> values) {
            addCriterion("YL7 in", values, "yl7");
            return (Criteria) this;
        }

        public Criteria andYl7NotIn(List<String> values) {
            addCriterion("YL7 not in", values, "yl7");
            return (Criteria) this;
        }

        public Criteria andYl7Between(String value1, String value2) {
            addCriterion("YL7 between", value1, value2, "yl7");
            return (Criteria) this;
        }

        public Criteria andYl7NotBetween(String value1, String value2) {
            addCriterion("YL7 not between", value1, value2, "yl7");
            return (Criteria) this;
        }

        public Criteria andYl8IsNull() {
            addCriterion("YL8 is null");
            return (Criteria) this;
        }

        public Criteria andYl8IsNotNull() {
            addCriterion("YL8 is not null");
            return (Criteria) this;
        }

        public Criteria andYl8EqualTo(String value) {
            addCriterion("YL8 =", value, "yl8");
            return (Criteria) this;
        }

        public Criteria andYl8NotEqualTo(String value) {
            addCriterion("YL8 <>", value, "yl8");
            return (Criteria) this;
        }

        public Criteria andYl8GreaterThan(String value) {
            addCriterion("YL8 >", value, "yl8");
            return (Criteria) this;
        }

        public Criteria andYl8GreaterThanOrEqualTo(String value) {
            addCriterion("YL8 >=", value, "yl8");
            return (Criteria) this;
        }

        public Criteria andYl8LessThan(String value) {
            addCriterion("YL8 <", value, "yl8");
            return (Criteria) this;
        }

        public Criteria andYl8LessThanOrEqualTo(String value) {
            addCriterion("YL8 <=", value, "yl8");
            return (Criteria) this;
        }

        public Criteria andYl8Like(String value) {
            addCriterion("YL8 like", value, "yl8");
            return (Criteria) this;
        }

        public Criteria andYl8NotLike(String value) {
            addCriterion("YL8 not like", value, "yl8");
            return (Criteria) this;
        }

        public Criteria andYl8In(List<String> values) {
            addCriterion("YL8 in", values, "yl8");
            return (Criteria) this;
        }

        public Criteria andYl8NotIn(List<String> values) {
            addCriterion("YL8 not in", values, "yl8");
            return (Criteria) this;
        }

        public Criteria andYl8Between(String value1, String value2) {
            addCriterion("YL8 between", value1, value2, "yl8");
            return (Criteria) this;
        }

        public Criteria andYl8NotBetween(String value1, String value2) {
            addCriterion("YL8 not between", value1, value2, "yl8");
            return (Criteria) this;
        }

        public Criteria andYl9IsNull() {
            addCriterion("YL9 is null");
            return (Criteria) this;
        }

        public Criteria andYl9IsNotNull() {
            addCriterion("YL9 is not null");
            return (Criteria) this;
        }

        public Criteria andYl9EqualTo(String value) {
            addCriterion("YL9 =", value, "yl9");
            return (Criteria) this;
        }

        public Criteria andYl9NotEqualTo(String value) {
            addCriterion("YL9 <>", value, "yl9");
            return (Criteria) this;
        }

        public Criteria andYl9GreaterThan(String value) {
            addCriterion("YL9 >", value, "yl9");
            return (Criteria) this;
        }

        public Criteria andYl9GreaterThanOrEqualTo(String value) {
            addCriterion("YL9 >=", value, "yl9");
            return (Criteria) this;
        }

        public Criteria andYl9LessThan(String value) {
            addCriterion("YL9 <", value, "yl9");
            return (Criteria) this;
        }

        public Criteria andYl9LessThanOrEqualTo(String value) {
            addCriterion("YL9 <=", value, "yl9");
            return (Criteria) this;
        }

        public Criteria andYl9Like(String value) {
            addCriterion("YL9 like", value, "yl9");
            return (Criteria) this;
        }

        public Criteria andYl9NotLike(String value) {
            addCriterion("YL9 not like", value, "yl9");
            return (Criteria) this;
        }

        public Criteria andYl9In(List<String> values) {
            addCriterion("YL9 in", values, "yl9");
            return (Criteria) this;
        }

        public Criteria andYl9NotIn(List<String> values) {
            addCriterion("YL9 not in", values, "yl9");
            return (Criteria) this;
        }

        public Criteria andYl9Between(String value1, String value2) {
            addCriterion("YL9 between", value1, value2, "yl9");
            return (Criteria) this;
        }

        public Criteria andYl9NotBetween(String value1, String value2) {
            addCriterion("YL9 not between", value1, value2, "yl9");
            return (Criteria) this;
        }

        public Criteria andYl10IsNull() {
            addCriterion("YL10 is null");
            return (Criteria) this;
        }

        public Criteria andYl10IsNotNull() {
            addCriterion("YL10 is not null");
            return (Criteria) this;
        }

        public Criteria andYl10EqualTo(String value) {
            addCriterion("YL10 =", value, "yl10");
            return (Criteria) this;
        }

        public Criteria andYl10NotEqualTo(String value) {
            addCriterion("YL10 <>", value, "yl10");
            return (Criteria) this;
        }

        public Criteria andYl10GreaterThan(String value) {
            addCriterion("YL10 >", value, "yl10");
            return (Criteria) this;
        }

        public Criteria andYl10GreaterThanOrEqualTo(String value) {
            addCriterion("YL10 >=", value, "yl10");
            return (Criteria) this;
        }

        public Criteria andYl10LessThan(String value) {
            addCriterion("YL10 <", value, "yl10");
            return (Criteria) this;
        }

        public Criteria andYl10LessThanOrEqualTo(String value) {
            addCriterion("YL10 <=", value, "yl10");
            return (Criteria) this;
        }

        public Criteria andYl10Like(String value) {
            addCriterion("YL10 like", value, "yl10");
            return (Criteria) this;
        }

        public Criteria andYl10NotLike(String value) {
            addCriterion("YL10 not like", value, "yl10");
            return (Criteria) this;
        }

        public Criteria andYl10In(List<String> values) {
            addCriterion("YL10 in", values, "yl10");
            return (Criteria) this;
        }

        public Criteria andYl10NotIn(List<String> values) {
            addCriterion("YL10 not in", values, "yl10");
            return (Criteria) this;
        }

        public Criteria andYl10Between(String value1, String value2) {
            addCriterion("YL10 between", value1, value2, "yl10");
            return (Criteria) this;
        }

        public Criteria andYl10NotBetween(String value1, String value2) {
            addCriterion("YL10 not between", value1, value2, "yl10");
            return (Criteria) this;
        }

        public Criteria andYl11IsNull() {
            addCriterion("YL11 is null");
            return (Criteria) this;
        }

        public Criteria andYl11IsNotNull() {
            addCriterion("YL11 is not null");
            return (Criteria) this;
        }

        public Criteria andYl11EqualTo(String value) {
            addCriterion("YL11 =", value, "yl11");
            return (Criteria) this;
        }

        public Criteria andYl11NotEqualTo(String value) {
            addCriterion("YL11 <>", value, "yl11");
            return (Criteria) this;
        }

        public Criteria andYl11GreaterThan(String value) {
            addCriterion("YL11 >", value, "yl11");
            return (Criteria) this;
        }

        public Criteria andYl11GreaterThanOrEqualTo(String value) {
            addCriterion("YL11 >=", value, "yl11");
            return (Criteria) this;
        }

        public Criteria andYl11LessThan(String value) {
            addCriterion("YL11 <", value, "yl11");
            return (Criteria) this;
        }

        public Criteria andYl11LessThanOrEqualTo(String value) {
            addCriterion("YL11 <=", value, "yl11");
            return (Criteria) this;
        }

        public Criteria andYl11Like(String value) {
            addCriterion("YL11 like", value, "yl11");
            return (Criteria) this;
        }

        public Criteria andYl11NotLike(String value) {
            addCriterion("YL11 not like", value, "yl11");
            return (Criteria) this;
        }

        public Criteria andYl11In(List<String> values) {
            addCriterion("YL11 in", values, "yl11");
            return (Criteria) this;
        }

        public Criteria andYl11NotIn(List<String> values) {
            addCriterion("YL11 not in", values, "yl11");
            return (Criteria) this;
        }

        public Criteria andYl11Between(String value1, String value2) {
            addCriterion("YL11 between", value1, value2, "yl11");
            return (Criteria) this;
        }

        public Criteria andYl11NotBetween(String value1, String value2) {
            addCriterion("YL11 not between", value1, value2, "yl11");
            return (Criteria) this;
        }

        public Criteria andYl12IsNull() {
            addCriterion("YL12 is null");
            return (Criteria) this;
        }

        public Criteria andYl12IsNotNull() {
            addCriterion("YL12 is not null");
            return (Criteria) this;
        }

        public Criteria andYl12EqualTo(String value) {
            addCriterion("YL12 =", value, "yl12");
            return (Criteria) this;
        }

        public Criteria andYl12NotEqualTo(String value) {
            addCriterion("YL12 <>", value, "yl12");
            return (Criteria) this;
        }

        public Criteria andYl12GreaterThan(String value) {
            addCriterion("YL12 >", value, "yl12");
            return (Criteria) this;
        }

        public Criteria andYl12GreaterThanOrEqualTo(String value) {
            addCriterion("YL12 >=", value, "yl12");
            return (Criteria) this;
        }

        public Criteria andYl12LessThan(String value) {
            addCriterion("YL12 <", value, "yl12");
            return (Criteria) this;
        }

        public Criteria andYl12LessThanOrEqualTo(String value) {
            addCriterion("YL12 <=", value, "yl12");
            return (Criteria) this;
        }

        public Criteria andYl12Like(String value) {
            addCriterion("YL12 like", value, "yl12");
            return (Criteria) this;
        }

        public Criteria andYl12NotLike(String value) {
            addCriterion("YL12 not like", value, "yl12");
            return (Criteria) this;
        }

        public Criteria andYl12In(List<String> values) {
            addCriterion("YL12 in", values, "yl12");
            return (Criteria) this;
        }

        public Criteria andYl12NotIn(List<String> values) {
            addCriterion("YL12 not in", values, "yl12");
            return (Criteria) this;
        }

        public Criteria andYl12Between(String value1, String value2) {
            addCriterion("YL12 between", value1, value2, "yl12");
            return (Criteria) this;
        }

        public Criteria andYl12NotBetween(String value1, String value2) {
            addCriterion("YL12 not between", value1, value2, "yl12");
            return (Criteria) this;
        }

        public Criteria andYl13IsNull() {
            addCriterion("YL13 is null");
            return (Criteria) this;
        }

        public Criteria andYl13IsNotNull() {
            addCriterion("YL13 is not null");
            return (Criteria) this;
        }

        public Criteria andYl13EqualTo(String value) {
            addCriterion("YL13 =", value, "yl13");
            return (Criteria) this;
        }

        public Criteria andYl13NotEqualTo(String value) {
            addCriterion("YL13 <>", value, "yl13");
            return (Criteria) this;
        }

        public Criteria andYl13GreaterThan(String value) {
            addCriterion("YL13 >", value, "yl13");
            return (Criteria) this;
        }

        public Criteria andYl13GreaterThanOrEqualTo(String value) {
            addCriterion("YL13 >=", value, "yl13");
            return (Criteria) this;
        }

        public Criteria andYl13LessThan(String value) {
            addCriterion("YL13 <", value, "yl13");
            return (Criteria) this;
        }

        public Criteria andYl13LessThanOrEqualTo(String value) {
            addCriterion("YL13 <=", value, "yl13");
            return (Criteria) this;
        }

        public Criteria andYl13Like(String value) {
            addCriterion("YL13 like", value, "yl13");
            return (Criteria) this;
        }

        public Criteria andYl13NotLike(String value) {
            addCriterion("YL13 not like", value, "yl13");
            return (Criteria) this;
        }

        public Criteria andYl13In(List<String> values) {
            addCriterion("YL13 in", values, "yl13");
            return (Criteria) this;
        }

        public Criteria andYl13NotIn(List<String> values) {
            addCriterion("YL13 not in", values, "yl13");
            return (Criteria) this;
        }

        public Criteria andYl13Between(String value1, String value2) {
            addCriterion("YL13 between", value1, value2, "yl13");
            return (Criteria) this;
        }

        public Criteria andYl13NotBetween(String value1, String value2) {
            addCriterion("YL13 not between", value1, value2, "yl13");
            return (Criteria) this;
        }

        public Criteria andYl14IsNull() {
            addCriterion("YL14 is null");
            return (Criteria) this;
        }

        public Criteria andYl14IsNotNull() {
            addCriterion("YL14 is not null");
            return (Criteria) this;
        }

        public Criteria andYl14EqualTo(String value) {
            addCriterion("YL14 =", value, "yl14");
            return (Criteria) this;
        }

        public Criteria andYl14NotEqualTo(String value) {
            addCriterion("YL14 <>", value, "yl14");
            return (Criteria) this;
        }

        public Criteria andYl14GreaterThan(String value) {
            addCriterion("YL14 >", value, "yl14");
            return (Criteria) this;
        }

        public Criteria andYl14GreaterThanOrEqualTo(String value) {
            addCriterion("YL14 >=", value, "yl14");
            return (Criteria) this;
        }

        public Criteria andYl14LessThan(String value) {
            addCriterion("YL14 <", value, "yl14");
            return (Criteria) this;
        }

        public Criteria andYl14LessThanOrEqualTo(String value) {
            addCriterion("YL14 <=", value, "yl14");
            return (Criteria) this;
        }

        public Criteria andYl14Like(String value) {
            addCriterion("YL14 like", value, "yl14");
            return (Criteria) this;
        }

        public Criteria andYl14NotLike(String value) {
            addCriterion("YL14 not like", value, "yl14");
            return (Criteria) this;
        }

        public Criteria andYl14In(List<String> values) {
            addCriterion("YL14 in", values, "yl14");
            return (Criteria) this;
        }

        public Criteria andYl14NotIn(List<String> values) {
            addCriterion("YL14 not in", values, "yl14");
            return (Criteria) this;
        }

        public Criteria andYl14Between(String value1, String value2) {
            addCriterion("YL14 between", value1, value2, "yl14");
            return (Criteria) this;
        }

        public Criteria andYl14NotBetween(String value1, String value2) {
            addCriterion("YL14 not between", value1, value2, "yl14");
            return (Criteria) this;
        }

        public Criteria andYl15IsNull() {
            addCriterion("YL15 is null");
            return (Criteria) this;
        }

        public Criteria andYl15IsNotNull() {
            addCriterion("YL15 is not null");
            return (Criteria) this;
        }

        public Criteria andYl15EqualTo(String value) {
            addCriterion("YL15 =", value, "yl15");
            return (Criteria) this;
        }

        public Criteria andYl15NotEqualTo(String value) {
            addCriterion("YL15 <>", value, "yl15");
            return (Criteria) this;
        }

        public Criteria andYl15GreaterThan(String value) {
            addCriterion("YL15 >", value, "yl15");
            return (Criteria) this;
        }

        public Criteria andYl15GreaterThanOrEqualTo(String value) {
            addCriterion("YL15 >=", value, "yl15");
            return (Criteria) this;
        }

        public Criteria andYl15LessThan(String value) {
            addCriterion("YL15 <", value, "yl15");
            return (Criteria) this;
        }

        public Criteria andYl15LessThanOrEqualTo(String value) {
            addCriterion("YL15 <=", value, "yl15");
            return (Criteria) this;
        }

        public Criteria andYl15Like(String value) {
            addCriterion("YL15 like", value, "yl15");
            return (Criteria) this;
        }

        public Criteria andYl15NotLike(String value) {
            addCriterion("YL15 not like", value, "yl15");
            return (Criteria) this;
        }

        public Criteria andYl15In(List<String> values) {
            addCriterion("YL15 in", values, "yl15");
            return (Criteria) this;
        }

        public Criteria andYl15NotIn(List<String> values) {
            addCriterion("YL15 not in", values, "yl15");
            return (Criteria) this;
        }

        public Criteria andYl15Between(String value1, String value2) {
            addCriterion("YL15 between", value1, value2, "yl15");
            return (Criteria) this;
        }

        public Criteria andYl15NotBetween(String value1, String value2) {
            addCriterion("YL15 not between", value1, value2, "yl15");
            return (Criteria) this;
        }

        public Criteria andYl16IsNull() {
            addCriterion("YL16 is null");
            return (Criteria) this;
        }

        public Criteria andYl16IsNotNull() {
            addCriterion("YL16 is not null");
            return (Criteria) this;
        }

        public Criteria andYl16EqualTo(String value) {
            addCriterion("YL16 =", value, "yl16");
            return (Criteria) this;
        }

        public Criteria andYl16NotEqualTo(String value) {
            addCriterion("YL16 <>", value, "yl16");
            return (Criteria) this;
        }

        public Criteria andYl16GreaterThan(String value) {
            addCriterion("YL16 >", value, "yl16");
            return (Criteria) this;
        }

        public Criteria andYl16GreaterThanOrEqualTo(String value) {
            addCriterion("YL16 >=", value, "yl16");
            return (Criteria) this;
        }

        public Criteria andYl16LessThan(String value) {
            addCriterion("YL16 <", value, "yl16");
            return (Criteria) this;
        }

        public Criteria andYl16LessThanOrEqualTo(String value) {
            addCriterion("YL16 <=", value, "yl16");
            return (Criteria) this;
        }

        public Criteria andYl16Like(String value) {
            addCriterion("YL16 like", value, "yl16");
            return (Criteria) this;
        }

        public Criteria andYl16NotLike(String value) {
            addCriterion("YL16 not like", value, "yl16");
            return (Criteria) this;
        }

        public Criteria andYl16In(List<String> values) {
            addCriterion("YL16 in", values, "yl16");
            return (Criteria) this;
        }

        public Criteria andYl16NotIn(List<String> values) {
            addCriterion("YL16 not in", values, "yl16");
            return (Criteria) this;
        }

        public Criteria andYl16Between(String value1, String value2) {
            addCriterion("YL16 between", value1, value2, "yl16");
            return (Criteria) this;
        }

        public Criteria andYl16NotBetween(String value1, String value2) {
            addCriterion("YL16 not between", value1, value2, "yl16");
            return (Criteria) this;
        }

        public Criteria andYl17IsNull() {
            addCriterion("YL17 is null");
            return (Criteria) this;
        }

        public Criteria andYl17IsNotNull() {
            addCriterion("YL17 is not null");
            return (Criteria) this;
        }

        public Criteria andYl17EqualTo(String value) {
            addCriterion("YL17 =", value, "yl17");
            return (Criteria) this;
        }

        public Criteria andYl17NotEqualTo(String value) {
            addCriterion("YL17 <>", value, "yl17");
            return (Criteria) this;
        }

        public Criteria andYl17GreaterThan(String value) {
            addCriterion("YL17 >", value, "yl17");
            return (Criteria) this;
        }

        public Criteria andYl17GreaterThanOrEqualTo(String value) {
            addCriterion("YL17 >=", value, "yl17");
            return (Criteria) this;
        }

        public Criteria andYl17LessThan(String value) {
            addCriterion("YL17 <", value, "yl17");
            return (Criteria) this;
        }

        public Criteria andYl17LessThanOrEqualTo(String value) {
            addCriterion("YL17 <=", value, "yl17");
            return (Criteria) this;
        }

        public Criteria andYl17Like(String value) {
            addCriterion("YL17 like", value, "yl17");
            return (Criteria) this;
        }

        public Criteria andYl17NotLike(String value) {
            addCriterion("YL17 not like", value, "yl17");
            return (Criteria) this;
        }

        public Criteria andYl17In(List<String> values) {
            addCriterion("YL17 in", values, "yl17");
            return (Criteria) this;
        }

        public Criteria andYl17NotIn(List<String> values) {
            addCriterion("YL17 not in", values, "yl17");
            return (Criteria) this;
        }

        public Criteria andYl17Between(String value1, String value2) {
            addCriterion("YL17 between", value1, value2, "yl17");
            return (Criteria) this;
        }

        public Criteria andYl17NotBetween(String value1, String value2) {
            addCriterion("YL17 not between", value1, value2, "yl17");
            return (Criteria) this;
        }

        public Criteria andYl18IsNull() {
            addCriterion("YL18 is null");
            return (Criteria) this;
        }

        public Criteria andYl18IsNotNull() {
            addCriterion("YL18 is not null");
            return (Criteria) this;
        }

        public Criteria andYl18EqualTo(String value) {
            addCriterion("YL18 =", value, "yl18");
            return (Criteria) this;
        }

        public Criteria andYl18NotEqualTo(String value) {
            addCriterion("YL18 <>", value, "yl18");
            return (Criteria) this;
        }

        public Criteria andYl18GreaterThan(String value) {
            addCriterion("YL18 >", value, "yl18");
            return (Criteria) this;
        }

        public Criteria andYl18GreaterThanOrEqualTo(String value) {
            addCriterion("YL18 >=", value, "yl18");
            return (Criteria) this;
        }

        public Criteria andYl18LessThan(String value) {
            addCriterion("YL18 <", value, "yl18");
            return (Criteria) this;
        }

        public Criteria andYl18LessThanOrEqualTo(String value) {
            addCriterion("YL18 <=", value, "yl18");
            return (Criteria) this;
        }

        public Criteria andYl18Like(String value) {
            addCriterion("YL18 like", value, "yl18");
            return (Criteria) this;
        }

        public Criteria andYl18NotLike(String value) {
            addCriterion("YL18 not like", value, "yl18");
            return (Criteria) this;
        }

        public Criteria andYl18In(List<String> values) {
            addCriterion("YL18 in", values, "yl18");
            return (Criteria) this;
        }

        public Criteria andYl18NotIn(List<String> values) {
            addCriterion("YL18 not in", values, "yl18");
            return (Criteria) this;
        }

        public Criteria andYl18Between(String value1, String value2) {
            addCriterion("YL18 between", value1, value2, "yl18");
            return (Criteria) this;
        }

        public Criteria andYl18NotBetween(String value1, String value2) {
            addCriterion("YL18 not between", value1, value2, "yl18");
            return (Criteria) this;
        }

        public Criteria andYl19IsNull() {
            addCriterion("YL19 is null");
            return (Criteria) this;
        }

        public Criteria andYl19IsNotNull() {
            addCriterion("YL19 is not null");
            return (Criteria) this;
        }

        public Criteria andYl19EqualTo(String value) {
            addCriterion("YL19 =", value, "yl19");
            return (Criteria) this;
        }

        public Criteria andYl19NotEqualTo(String value) {
            addCriterion("YL19 <>", value, "yl19");
            return (Criteria) this;
        }

        public Criteria andYl19GreaterThan(String value) {
            addCriterion("YL19 >", value, "yl19");
            return (Criteria) this;
        }

        public Criteria andYl19GreaterThanOrEqualTo(String value) {
            addCriterion("YL19 >=", value, "yl19");
            return (Criteria) this;
        }

        public Criteria andYl19LessThan(String value) {
            addCriterion("YL19 <", value, "yl19");
            return (Criteria) this;
        }

        public Criteria andYl19LessThanOrEqualTo(String value) {
            addCriterion("YL19 <=", value, "yl19");
            return (Criteria) this;
        }

        public Criteria andYl19Like(String value) {
            addCriterion("YL19 like", value, "yl19");
            return (Criteria) this;
        }

        public Criteria andYl19NotLike(String value) {
            addCriterion("YL19 not like", value, "yl19");
            return (Criteria) this;
        }

        public Criteria andYl19In(List<String> values) {
            addCriterion("YL19 in", values, "yl19");
            return (Criteria) this;
        }

        public Criteria andYl19NotIn(List<String> values) {
            addCriterion("YL19 not in", values, "yl19");
            return (Criteria) this;
        }

        public Criteria andYl19Between(String value1, String value2) {
            addCriterion("YL19 between", value1, value2, "yl19");
            return (Criteria) this;
        }

        public Criteria andYl19NotBetween(String value1, String value2) {
            addCriterion("YL19 not between", value1, value2, "yl19");
            return (Criteria) this;
        }

        public Criteria andYl20IsNull() {
            addCriterion("YL20 is null");
            return (Criteria) this;
        }

        public Criteria andYl20IsNotNull() {
            addCriterion("YL20 is not null");
            return (Criteria) this;
        }

        public Criteria andYl20EqualTo(String value) {
            addCriterion("YL20 =", value, "yl20");
            return (Criteria) this;
        }

        public Criteria andYl20NotEqualTo(String value) {
            addCriterion("YL20 <>", value, "yl20");
            return (Criteria) this;
        }

        public Criteria andYl20GreaterThan(String value) {
            addCriterion("YL20 >", value, "yl20");
            return (Criteria) this;
        }

        public Criteria andYl20GreaterThanOrEqualTo(String value) {
            addCriterion("YL20 >=", value, "yl20");
            return (Criteria) this;
        }

        public Criteria andYl20LessThan(String value) {
            addCriterion("YL20 <", value, "yl20");
            return (Criteria) this;
        }

        public Criteria andYl20LessThanOrEqualTo(String value) {
            addCriterion("YL20 <=", value, "yl20");
            return (Criteria) this;
        }

        public Criteria andYl20Like(String value) {
            addCriterion("YL20 like", value, "yl20");
            return (Criteria) this;
        }

        public Criteria andYl20NotLike(String value) {
            addCriterion("YL20 not like", value, "yl20");
            return (Criteria) this;
        }

        public Criteria andYl20In(List<String> values) {
            addCriterion("YL20 in", values, "yl20");
            return (Criteria) this;
        }

        public Criteria andYl20NotIn(List<String> values) {
            addCriterion("YL20 not in", values, "yl20");
            return (Criteria) this;
        }

        public Criteria andYl20Between(String value1, String value2) {
            addCriterion("YL20 between", value1, value2, "yl20");
            return (Criteria) this;
        }

        public Criteria andYl20NotBetween(String value1, String value2) {
            addCriterion("YL20 not between", value1, value2, "yl20");
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