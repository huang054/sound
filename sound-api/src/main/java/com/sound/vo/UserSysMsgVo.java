package com.sound.vo;


import java.util.List;

/**
 * Created by Administrator on 2018/5/9.
 */
public class UserSysMsgVo {
    private int noReadNum; //未读消息数

    private int hasReadNum;

    private List<SystemMsgVo> msgList;

    public int getNoReadNum() {
        return noReadNum;
    }

    public void setNoReadNum(int noReadNum) {
        this.noReadNum = noReadNum;
    }

    public int getHasReadNum() {
        return hasReadNum;
    }

    public void setHasReadNum(int hasReadNum) {
        this.hasReadNum = hasReadNum;
    }

    public List<SystemMsgVo> getMsgList() {
        return msgList;
    }

    public void setMsgList(List<SystemMsgVo> msgList) {
        this.msgList = msgList;
    }

	@Override
	public String toString() {
		return "UserSysMsgVo [noReadNum=" + noReadNum + ", hasReadNum=" + hasReadNum + ", msgList=" + msgList + "]";
	}
    
}
