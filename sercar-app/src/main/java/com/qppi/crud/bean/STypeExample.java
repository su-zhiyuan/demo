package com.qppi.crud.bean;

import java.util.ArrayList;
import java.util.List;

public class STypeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public STypeExample() {
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

        public Criteria andStypeIdIsNull() {
            addCriterion("STYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andStypeIdIsNotNull() {
            addCriterion("STYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStypeIdEqualTo(String value) {
            addCriterion("STYPE_ID =", value, "stypeId");
            return (Criteria) this;
        }

        public Criteria andStypeIdNotEqualTo(String value) {
            addCriterion("STYPE_ID <>", value, "stypeId");
            return (Criteria) this;
        }

        public Criteria andStypeIdGreaterThan(String value) {
            addCriterion("STYPE_ID >", value, "stypeId");
            return (Criteria) this;
        }

        public Criteria andStypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("STYPE_ID >=", value, "stypeId");
            return (Criteria) this;
        }

        public Criteria andStypeIdLessThan(String value) {
            addCriterion("STYPE_ID <", value, "stypeId");
            return (Criteria) this;
        }

        public Criteria andStypeIdLessThanOrEqualTo(String value) {
            addCriterion("STYPE_ID <=", value, "stypeId");
            return (Criteria) this;
        }

        public Criteria andStypeIdLike(String value) {
            addCriterion("STYPE_ID like", value, "stypeId");
            return (Criteria) this;
        }

        public Criteria andStypeIdNotLike(String value) {
            addCriterion("STYPE_ID not like", value, "stypeId");
            return (Criteria) this;
        }

        public Criteria andStypeIdIn(List<String> values) {
            addCriterion("STYPE_ID in", values, "stypeId");
            return (Criteria) this;
        }

        public Criteria andStypeIdNotIn(List<String> values) {
            addCriterion("STYPE_ID not in", values, "stypeId");
            return (Criteria) this;
        }

        public Criteria andStypeIdBetween(String value1, String value2) {
            addCriterion("STYPE_ID between", value1, value2, "stypeId");
            return (Criteria) this;
        }

        public Criteria andStypeIdNotBetween(String value1, String value2) {
            addCriterion("STYPE_ID not between", value1, value2, "stypeId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNull() {
            addCriterion("COMPANY_ID is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNotNull() {
            addCriterion("COMPANY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdEqualTo(String value) {
            addCriterion("COMPANY_ID =", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotEqualTo(String value) {
            addCriterion("COMPANY_ID <>", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThan(String value) {
            addCriterion("COMPANY_ID >", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThanOrEqualTo(String value) {
            addCriterion("COMPANY_ID >=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThan(String value) {
            addCriterion("COMPANY_ID <", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThanOrEqualTo(String value) {
            addCriterion("COMPANY_ID <=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLike(String value) {
            addCriterion("COMPANY_ID like", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotLike(String value) {
            addCriterion("COMPANY_ID not like", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIn(List<String> values) {
            addCriterion("COMPANY_ID in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotIn(List<String> values) {
            addCriterion("COMPANY_ID not in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdBetween(String value1, String value2) {
            addCriterion("COMPANY_ID between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotBetween(String value1, String value2) {
            addCriterion("COMPANY_ID not between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andTypeNameIsNull() {
            addCriterion("TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTypeNameIsNotNull() {
            addCriterion("TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTypeNameEqualTo(String value) {
            addCriterion("TYPE_NAME =", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotEqualTo(String value) {
            addCriterion("TYPE_NAME <>", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameGreaterThan(String value) {
            addCriterion("TYPE_NAME >", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE_NAME >=", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLessThan(String value) {
            addCriterion("TYPE_NAME <", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLessThanOrEqualTo(String value) {
            addCriterion("TYPE_NAME <=", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLike(String value) {
            addCriterion("TYPE_NAME like", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotLike(String value) {
            addCriterion("TYPE_NAME not like", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameIn(List<String> values) {
            addCriterion("TYPE_NAME in", values, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotIn(List<String> values) {
            addCriterion("TYPE_NAME not in", values, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameBetween(String value1, String value2) {
            addCriterion("TYPE_NAME between", value1, value2, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotBetween(String value1, String value2) {
            addCriterion("TYPE_NAME not between", value1, value2, "typeName");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
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