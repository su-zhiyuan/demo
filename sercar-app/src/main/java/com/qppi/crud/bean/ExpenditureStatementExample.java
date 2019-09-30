package com.qppi.crud.bean;

import java.util.ArrayList;
import java.util.List;

public class ExpenditureStatementExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExpenditureStatementExample() {
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

        public Criteria andExpenditurestatementIdIsNull() {
            addCriterion("EXPENDITURESTATEMENT_ID is null");
            return (Criteria) this;
        }

        public Criteria andExpenditurestatementIdIsNotNull() {
            addCriterion("EXPENDITURESTATEMENT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andExpenditurestatementIdEqualTo(String value) {
            addCriterion("EXPENDITURESTATEMENT_ID =", value, "expenditurestatementId");
            return (Criteria) this;
        }

        public Criteria andExpenditurestatementIdNotEqualTo(String value) {
            addCriterion("EXPENDITURESTATEMENT_ID <>", value, "expenditurestatementId");
            return (Criteria) this;
        }

        public Criteria andExpenditurestatementIdGreaterThan(String value) {
            addCriterion("EXPENDITURESTATEMENT_ID >", value, "expenditurestatementId");
            return (Criteria) this;
        }

        public Criteria andExpenditurestatementIdGreaterThanOrEqualTo(String value) {
            addCriterion("EXPENDITURESTATEMENT_ID >=", value, "expenditurestatementId");
            return (Criteria) this;
        }

        public Criteria andExpenditurestatementIdLessThan(String value) {
            addCriterion("EXPENDITURESTATEMENT_ID <", value, "expenditurestatementId");
            return (Criteria) this;
        }

        public Criteria andExpenditurestatementIdLessThanOrEqualTo(String value) {
            addCriterion("EXPENDITURESTATEMENT_ID <=", value, "expenditurestatementId");
            return (Criteria) this;
        }

        public Criteria andExpenditurestatementIdLike(String value) {
            addCriterion("EXPENDITURESTATEMENT_ID like", value, "expenditurestatementId");
            return (Criteria) this;
        }

        public Criteria andExpenditurestatementIdNotLike(String value) {
            addCriterion("EXPENDITURESTATEMENT_ID not like", value, "expenditurestatementId");
            return (Criteria) this;
        }

        public Criteria andExpenditurestatementIdIn(List<String> values) {
            addCriterion("EXPENDITURESTATEMENT_ID in", values, "expenditurestatementId");
            return (Criteria) this;
        }

        public Criteria andExpenditurestatementIdNotIn(List<String> values) {
            addCriterion("EXPENDITURESTATEMENT_ID not in", values, "expenditurestatementId");
            return (Criteria) this;
        }

        public Criteria andExpenditurestatementIdBetween(String value1, String value2) {
            addCriterion("EXPENDITURESTATEMENT_ID between", value1, value2, "expenditurestatementId");
            return (Criteria) this;
        }

        public Criteria andExpenditurestatementIdNotBetween(String value1, String value2) {
            addCriterion("EXPENDITURESTATEMENT_ID not between", value1, value2, "expenditurestatementId");
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

        public Criteria andSubjectPaymentIsNull() {
            addCriterion("SUBJECT_PAYMENT is null");
            return (Criteria) this;
        }

        public Criteria andSubjectPaymentIsNotNull() {
            addCriterion("SUBJECT_PAYMENT is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectPaymentEqualTo(String value) {
            addCriterion("SUBJECT_PAYMENT =", value, "subjectPayment");
            return (Criteria) this;
        }

        public Criteria andSubjectPaymentNotEqualTo(String value) {
            addCriterion("SUBJECT_PAYMENT <>", value, "subjectPayment");
            return (Criteria) this;
        }

        public Criteria andSubjectPaymentGreaterThan(String value) {
            addCriterion("SUBJECT_PAYMENT >", value, "subjectPayment");
            return (Criteria) this;
        }

        public Criteria andSubjectPaymentGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJECT_PAYMENT >=", value, "subjectPayment");
            return (Criteria) this;
        }

        public Criteria andSubjectPaymentLessThan(String value) {
            addCriterion("SUBJECT_PAYMENT <", value, "subjectPayment");
            return (Criteria) this;
        }

        public Criteria andSubjectPaymentLessThanOrEqualTo(String value) {
            addCriterion("SUBJECT_PAYMENT <=", value, "subjectPayment");
            return (Criteria) this;
        }

        public Criteria andSubjectPaymentLike(String value) {
            addCriterion("SUBJECT_PAYMENT like", value, "subjectPayment");
            return (Criteria) this;
        }

        public Criteria andSubjectPaymentNotLike(String value) {
            addCriterion("SUBJECT_PAYMENT not like", value, "subjectPayment");
            return (Criteria) this;
        }

        public Criteria andSubjectPaymentIn(List<String> values) {
            addCriterion("SUBJECT_PAYMENT in", values, "subjectPayment");
            return (Criteria) this;
        }

        public Criteria andSubjectPaymentNotIn(List<String> values) {
            addCriterion("SUBJECT_PAYMENT not in", values, "subjectPayment");
            return (Criteria) this;
        }

        public Criteria andSubjectPaymentBetween(String value1, String value2) {
            addCriterion("SUBJECT_PAYMENT between", value1, value2, "subjectPayment");
            return (Criteria) this;
        }

        public Criteria andSubjectPaymentNotBetween(String value1, String value2) {
            addCriterion("SUBJECT_PAYMENT not between", value1, value2, "subjectPayment");
            return (Criteria) this;
        }

        public Criteria andTotalIsNull() {
            addCriterion("TOTAL is null");
            return (Criteria) this;
        }

        public Criteria andTotalIsNotNull() {
            addCriterion("TOTAL is not null");
            return (Criteria) this;
        }

        public Criteria andTotalEqualTo(String value) {
            addCriterion("TOTAL =", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotEqualTo(String value) {
            addCriterion("TOTAL <>", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalGreaterThan(String value) {
            addCriterion("TOTAL >", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalGreaterThanOrEqualTo(String value) {
            addCriterion("TOTAL >=", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalLessThan(String value) {
            addCriterion("TOTAL <", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalLessThanOrEqualTo(String value) {
            addCriterion("TOTAL <=", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalLike(String value) {
            addCriterion("TOTAL like", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotLike(String value) {
            addCriterion("TOTAL not like", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalIn(List<String> values) {
            addCriterion("TOTAL in", values, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotIn(List<String> values) {
            addCriterion("TOTAL not in", values, "total");
            return (Criteria) this;
        }

        public Criteria andTotalBetween(String value1, String value2) {
            addCriterion("TOTAL between", value1, value2, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotBetween(String value1, String value2) {
            addCriterion("TOTAL not between", value1, value2, "total");
            return (Criteria) this;
        }

        public Criteria andLsh1IsNull() {
            addCriterion("LSH1 is null");
            return (Criteria) this;
        }

        public Criteria andLsh1IsNotNull() {
            addCriterion("LSH1 is not null");
            return (Criteria) this;
        }

        public Criteria andLsh1EqualTo(String value) {
            addCriterion("LSH1 =", value, "lsh1");
            return (Criteria) this;
        }

        public Criteria andLsh1NotEqualTo(String value) {
            addCriterion("LSH1 <>", value, "lsh1");
            return (Criteria) this;
        }

        public Criteria andLsh1GreaterThan(String value) {
            addCriterion("LSH1 >", value, "lsh1");
            return (Criteria) this;
        }

        public Criteria andLsh1GreaterThanOrEqualTo(String value) {
            addCriterion("LSH1 >=", value, "lsh1");
            return (Criteria) this;
        }

        public Criteria andLsh1LessThan(String value) {
            addCriterion("LSH1 <", value, "lsh1");
            return (Criteria) this;
        }

        public Criteria andLsh1LessThanOrEqualTo(String value) {
            addCriterion("LSH1 <=", value, "lsh1");
            return (Criteria) this;
        }

        public Criteria andLsh1Like(String value) {
            addCriterion("LSH1 like", value, "lsh1");
            return (Criteria) this;
        }

        public Criteria andLsh1NotLike(String value) {
            addCriterion("LSH1 not like", value, "lsh1");
            return (Criteria) this;
        }

        public Criteria andLsh1In(List<String> values) {
            addCriterion("LSH1 in", values, "lsh1");
            return (Criteria) this;
        }

        public Criteria andLsh1NotIn(List<String> values) {
            addCriterion("LSH1 not in", values, "lsh1");
            return (Criteria) this;
        }

        public Criteria andLsh1Between(String value1, String value2) {
            addCriterion("LSH1 between", value1, value2, "lsh1");
            return (Criteria) this;
        }

        public Criteria andLsh1NotBetween(String value1, String value2) {
            addCriterion("LSH1 not between", value1, value2, "lsh1");
            return (Criteria) this;
        }

        public Criteria andLsh2IsNull() {
            addCriterion("LSH2 is null");
            return (Criteria) this;
        }

        public Criteria andLsh2IsNotNull() {
            addCriterion("LSH2 is not null");
            return (Criteria) this;
        }

        public Criteria andLsh2EqualTo(String value) {
            addCriterion("LSH2 =", value, "lsh2");
            return (Criteria) this;
        }

        public Criteria andLsh2NotEqualTo(String value) {
            addCriterion("LSH2 <>", value, "lsh2");
            return (Criteria) this;
        }

        public Criteria andLsh2GreaterThan(String value) {
            addCriterion("LSH2 >", value, "lsh2");
            return (Criteria) this;
        }

        public Criteria andLsh2GreaterThanOrEqualTo(String value) {
            addCriterion("LSH2 >=", value, "lsh2");
            return (Criteria) this;
        }

        public Criteria andLsh2LessThan(String value) {
            addCriterion("LSH2 <", value, "lsh2");
            return (Criteria) this;
        }

        public Criteria andLsh2LessThanOrEqualTo(String value) {
            addCriterion("LSH2 <=", value, "lsh2");
            return (Criteria) this;
        }

        public Criteria andLsh2Like(String value) {
            addCriterion("LSH2 like", value, "lsh2");
            return (Criteria) this;
        }

        public Criteria andLsh2NotLike(String value) {
            addCriterion("LSH2 not like", value, "lsh2");
            return (Criteria) this;
        }

        public Criteria andLsh2In(List<String> values) {
            addCriterion("LSH2 in", values, "lsh2");
            return (Criteria) this;
        }

        public Criteria andLsh2NotIn(List<String> values) {
            addCriterion("LSH2 not in", values, "lsh2");
            return (Criteria) this;
        }

        public Criteria andLsh2Between(String value1, String value2) {
            addCriterion("LSH2 between", value1, value2, "lsh2");
            return (Criteria) this;
        }

        public Criteria andLsh2NotBetween(String value1, String value2) {
            addCriterion("LSH2 not between", value1, value2, "lsh2");
            return (Criteria) this;
        }

        public Criteria andHappenDateIsNull() {
            addCriterion("HAPPEN_DATE is null");
            return (Criteria) this;
        }

        public Criteria andHappenDateIsNotNull() {
            addCriterion("HAPPEN_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andHappenDateEqualTo(String value) {
            addCriterion("HAPPEN_DATE =", value, "happenDate");
            return (Criteria) this;
        }

        public Criteria andHappenDateNotEqualTo(String value) {
            addCriterion("HAPPEN_DATE <>", value, "happenDate");
            return (Criteria) this;
        }

        public Criteria andHappenDateGreaterThan(String value) {
            addCriterion("HAPPEN_DATE >", value, "happenDate");
            return (Criteria) this;
        }

        public Criteria andHappenDateGreaterThanOrEqualTo(String value) {
            addCriterion("HAPPEN_DATE >=", value, "happenDate");
            return (Criteria) this;
        }

        public Criteria andHappenDateLessThan(String value) {
            addCriterion("HAPPEN_DATE <", value, "happenDate");
            return (Criteria) this;
        }

        public Criteria andHappenDateLessThanOrEqualTo(String value) {
            addCriterion("HAPPEN_DATE <=", value, "happenDate");
            return (Criteria) this;
        }

        public Criteria andHappenDateLike(String value) {
            addCriterion("HAPPEN_DATE like", value, "happenDate");
            return (Criteria) this;
        }

        public Criteria andHappenDateNotLike(String value) {
            addCriterion("HAPPEN_DATE not like", value, "happenDate");
            return (Criteria) this;
        }

        public Criteria andHappenDateIn(List<String> values) {
            addCriterion("HAPPEN_DATE in", values, "happenDate");
            return (Criteria) this;
        }

        public Criteria andHappenDateNotIn(List<String> values) {
            addCriterion("HAPPEN_DATE not in", values, "happenDate");
            return (Criteria) this;
        }

        public Criteria andHappenDateBetween(String value1, String value2) {
            addCriterion("HAPPEN_DATE between", value1, value2, "happenDate");
            return (Criteria) this;
        }

        public Criteria andHappenDateNotBetween(String value1, String value2) {
            addCriterion("HAPPEN_DATE not between", value1, value2, "happenDate");
            return (Criteria) this;
        }

        public Criteria andExpendNameIsNull() {
            addCriterion("EXPEND_NAME is null");
            return (Criteria) this;
        }

        public Criteria andExpendNameIsNotNull() {
            addCriterion("EXPEND_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andExpendNameEqualTo(String value) {
            addCriterion("EXPEND_NAME =", value, "expendName");
            return (Criteria) this;
        }

        public Criteria andExpendNameNotEqualTo(String value) {
            addCriterion("EXPEND_NAME <>", value, "expendName");
            return (Criteria) this;
        }

        public Criteria andExpendNameGreaterThan(String value) {
            addCriterion("EXPEND_NAME >", value, "expendName");
            return (Criteria) this;
        }

        public Criteria andExpendNameGreaterThanOrEqualTo(String value) {
            addCriterion("EXPEND_NAME >=", value, "expendName");
            return (Criteria) this;
        }

        public Criteria andExpendNameLessThan(String value) {
            addCriterion("EXPEND_NAME <", value, "expendName");
            return (Criteria) this;
        }

        public Criteria andExpendNameLessThanOrEqualTo(String value) {
            addCriterion("EXPEND_NAME <=", value, "expendName");
            return (Criteria) this;
        }

        public Criteria andExpendNameLike(String value) {
            addCriterion("EXPEND_NAME like", value, "expendName");
            return (Criteria) this;
        }

        public Criteria andExpendNameNotLike(String value) {
            addCriterion("EXPEND_NAME not like", value, "expendName");
            return (Criteria) this;
        }

        public Criteria andExpendNameIn(List<String> values) {
            addCriterion("EXPEND_NAME in", values, "expendName");
            return (Criteria) this;
        }

        public Criteria andExpendNameNotIn(List<String> values) {
            addCriterion("EXPEND_NAME not in", values, "expendName");
            return (Criteria) this;
        }

        public Criteria andExpendNameBetween(String value1, String value2) {
            addCriterion("EXPEND_NAME between", value1, value2, "expendName");
            return (Criteria) this;
        }

        public Criteria andExpendNameNotBetween(String value1, String value2) {
            addCriterion("EXPEND_NAME not between", value1, value2, "expendName");
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