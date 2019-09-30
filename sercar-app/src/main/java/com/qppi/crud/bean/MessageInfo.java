package com.qppi.crud.bean;

public class MessageInfo {
    private String messageinfoId;

    private String messageType;

    private String messageContent;

    private String sendUser;

    private String receUser;

    private String createBy;

    private String createTime;

    private String yl1;

    private String yl2;

    private String yl3;

    private String yl4;

    public String getMessageinfoId() {
        return messageinfoId;
    }

    public void setMessageinfoId(String messageinfoId) {
        this.messageinfoId = messageinfoId == null ? null : messageinfoId.trim();
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType == null ? null : messageType.trim();
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent == null ? null : messageContent.trim();
    }

    public String getSendUser() {
        return sendUser;
    }

    public void setSendUser(String sendUser) {
        this.sendUser = sendUser == null ? null : sendUser.trim();
    }

    public String getReceUser() {
        return receUser;
    }

    public void setReceUser(String receUser) {
        this.receUser = receUser == null ? null : receUser.trim();
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
}