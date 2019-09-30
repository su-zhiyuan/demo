package com.qppi.crud.bean;

import java.util.ArrayList;
import java.util.List;

public class MessageInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MessageInfoExample() {
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

        public Criteria andMessageinfoIdIsNull() {
            addCriterion("MESSAGEINFO_ID is null");
            return (Criteria) this;
        }

        public Criteria andMessageinfoIdIsNotNull() {
            addCriterion("MESSAGEINFO_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMessageinfoIdEqualTo(String value) {
            addCriterion("MESSAGEINFO_ID =", value, "messageinfoId");
            return (Criteria) this;
        }

        public Criteria andMessageinfoIdNotEqualTo(String value) {
            addCriterion("MESSAGEINFO_ID <>", value, "messageinfoId");
            return (Criteria) this;
        }

        public Criteria andMessageinfoIdGreaterThan(String value) {
            addCriterion("MESSAGEINFO_ID >", value, "messageinfoId");
            return (Criteria) this;
        }

        public Criteria andMessageinfoIdGreaterThanOrEqualTo(String value) {
            addCriterion("MESSAGEINFO_ID >=", value, "messageinfoId");
            return (Criteria) this;
        }

        public Criteria andMessageinfoIdLessThan(String value) {
            addCriterion("MESSAGEINFO_ID <", value, "messageinfoId");
            return (Criteria) this;
        }

        public Criteria andMessageinfoIdLessThanOrEqualTo(String value) {
            addCriterion("MESSAGEINFO_ID <=", value, "messageinfoId");
            return (Criteria) this;
        }

        public Criteria andMessageinfoIdLike(String value) {
            addCriterion("MESSAGEINFO_ID like", value, "messageinfoId");
            return (Criteria) this;
        }

        public Criteria andMessageinfoIdNotLike(String value) {
            addCriterion("MESSAGEINFO_ID not like", value, "messageinfoId");
            return (Criteria) this;
        }

        public Criteria andMessageinfoIdIn(List<String> values) {
            addCriterion("MESSAGEINFO_ID in", values, "messageinfoId");
            return (Criteria) this;
        }

        public Criteria andMessageinfoIdNotIn(List<String> values) {
            addCriterion("MESSAGEINFO_ID not in", values, "messageinfoId");
            return (Criteria) this;
        }

        public Criteria andMessageinfoIdBetween(String value1, String value2) {
            addCriterion("MESSAGEINFO_ID between", value1, value2, "messageinfoId");
            return (Criteria) this;
        }

        public Criteria andMessageinfoIdNotBetween(String value1, String value2) {
            addCriterion("MESSAGEINFO_ID not between", value1, value2, "messageinfoId");
            return (Criteria) this;
        }

        public Criteria andMessageTypeIsNull() {
            addCriterion("MESSAGE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andMessageTypeIsNotNull() {
            addCriterion("MESSAGE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andMessageTypeEqualTo(String value) {
            addCriterion("MESSAGE_TYPE =", value, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeNotEqualTo(String value) {
            addCriterion("MESSAGE_TYPE <>", value, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeGreaterThan(String value) {
            addCriterion("MESSAGE_TYPE >", value, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeGreaterThanOrEqualTo(String value) {
            addCriterion("MESSAGE_TYPE >=", value, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeLessThan(String value) {
            addCriterion("MESSAGE_TYPE <", value, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeLessThanOrEqualTo(String value) {
            addCriterion("MESSAGE_TYPE <=", value, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeLike(String value) {
            addCriterion("MESSAGE_TYPE like", value, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeNotLike(String value) {
            addCriterion("MESSAGE_TYPE not like", value, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeIn(List<String> values) {
            addCriterion("MESSAGE_TYPE in", values, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeNotIn(List<String> values) {
            addCriterion("MESSAGE_TYPE not in", values, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeBetween(String value1, String value2) {
            addCriterion("MESSAGE_TYPE between", value1, value2, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeNotBetween(String value1, String value2) {
            addCriterion("MESSAGE_TYPE not between", value1, value2, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageContentIsNull() {
            addCriterion("MESSAGE_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andMessageContentIsNotNull() {
            addCriterion("MESSAGE_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andMessageContentEqualTo(String value) {
            addCriterion("MESSAGE_CONTENT =", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentNotEqualTo(String value) {
            addCriterion("MESSAGE_CONTENT <>", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentGreaterThan(String value) {
            addCriterion("MESSAGE_CONTENT >", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentGreaterThanOrEqualTo(String value) {
            addCriterion("MESSAGE_CONTENT >=", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentLessThan(String value) {
            addCriterion("MESSAGE_CONTENT <", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentLessThanOrEqualTo(String value) {
            addCriterion("MESSAGE_CONTENT <=", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentLike(String value) {
            addCriterion("MESSAGE_CONTENT like", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentNotLike(String value) {
            addCriterion("MESSAGE_CONTENT not like", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentIn(List<String> values) {
            addCriterion("MESSAGE_CONTENT in", values, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentNotIn(List<String> values) {
            addCriterion("MESSAGE_CONTENT not in", values, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentBetween(String value1, String value2) {
            addCriterion("MESSAGE_CONTENT between", value1, value2, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentNotBetween(String value1, String value2) {
            addCriterion("MESSAGE_CONTENT not between", value1, value2, "messageContent");
            return (Criteria) this;
        }

        public Criteria andSendUserIsNull() {
            addCriterion("SEND_USER is null");
            return (Criteria) this;
        }

        public Criteria andSendUserIsNotNull() {
            addCriterion("SEND_USER is not null");
            return (Criteria) this;
        }

        public Criteria andSendUserEqualTo(String value) {
            addCriterion("SEND_USER =", value, "sendUser");
            return (Criteria) this;
        }

        public Criteria andSendUserNotEqualTo(String value) {
            addCriterion("SEND_USER <>", value, "sendUser");
            return (Criteria) this;
        }

        public Criteria andSendUserGreaterThan(String value) {
            addCriterion("SEND_USER >", value, "sendUser");
            return (Criteria) this;
        }

        public Criteria andSendUserGreaterThanOrEqualTo(String value) {
            addCriterion("SEND_USER >=", value, "sendUser");
            return (Criteria) this;
        }

        public Criteria andSendUserLessThan(String value) {
            addCriterion("SEND_USER <", value, "sendUser");
            return (Criteria) this;
        }

        public Criteria andSendUserLessThanOrEqualTo(String value) {
            addCriterion("SEND_USER <=", value, "sendUser");
            return (Criteria) this;
        }

        public Criteria andSendUserLike(String value) {
            addCriterion("SEND_USER like", value, "sendUser");
            return (Criteria) this;
        }

        public Criteria andSendUserNotLike(String value) {
            addCriterion("SEND_USER not like", value, "sendUser");
            return (Criteria) this;
        }

        public Criteria andSendUserIn(List<String> values) {
            addCriterion("SEND_USER in", values, "sendUser");
            return (Criteria) this;
        }

        public Criteria andSendUserNotIn(List<String> values) {
            addCriterion("SEND_USER not in", values, "sendUser");
            return (Criteria) this;
        }

        public Criteria andSendUserBetween(String value1, String value2) {
            addCriterion("SEND_USER between", value1, value2, "sendUser");
            return (Criteria) this;
        }

        public Criteria andSendUserNotBetween(String value1, String value2) {
            addCriterion("SEND_USER not between", value1, value2, "sendUser");
            return (Criteria) this;
        }

        public Criteria andReceUserIsNull() {
            addCriterion("RECE_USER is null");
            return (Criteria) this;
        }

        public Criteria andReceUserIsNotNull() {
            addCriterion("RECE_USER is not null");
            return (Criteria) this;
        }

        public Criteria andReceUserEqualTo(String value) {
            addCriterion("RECE_USER =", value, "receUser");
            return (Criteria) this;
        }

        public Criteria andReceUserNotEqualTo(String value) {
            addCriterion("RECE_USER <>", value, "receUser");
            return (Criteria) this;
        }

        public Criteria andReceUserGreaterThan(String value) {
            addCriterion("RECE_USER >", value, "receUser");
            return (Criteria) this;
        }

        public Criteria andReceUserGreaterThanOrEqualTo(String value) {
            addCriterion("RECE_USER >=", value, "receUser");
            return (Criteria) this;
        }

        public Criteria andReceUserLessThan(String value) {
            addCriterion("RECE_USER <", value, "receUser");
            return (Criteria) this;
        }

        public Criteria andReceUserLessThanOrEqualTo(String value) {
            addCriterion("RECE_USER <=", value, "receUser");
            return (Criteria) this;
        }

        public Criteria andReceUserLike(String value) {
            addCriterion("RECE_USER like", value, "receUser");
            return (Criteria) this;
        }

        public Criteria andReceUserNotLike(String value) {
            addCriterion("RECE_USER not like", value, "receUser");
            return (Criteria) this;
        }

        public Criteria andReceUserIn(List<String> values) {
            addCriterion("RECE_USER in", values, "receUser");
            return (Criteria) this;
        }

        public Criteria andReceUserNotIn(List<String> values) {
            addCriterion("RECE_USER not in", values, "receUser");
            return (Criteria) this;
        }

        public Criteria andReceUserBetween(String value1, String value2) {
            addCriterion("RECE_USER between", value1, value2, "receUser");
            return (Criteria) this;
        }

        public Criteria andReceUserNotBetween(String value1, String value2) {
            addCriterion("RECE_USER not between", value1, value2, "receUser");
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