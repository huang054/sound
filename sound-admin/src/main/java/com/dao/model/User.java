package com.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

public class User implements Serializable {
    /**
     * 用户ID
     */
    @Id
    private Long id;

    /**
     * 用户地址
     */
    private String adress;

    /**
     * 百度帐号
     */
    @Column(name = "baidu_account")
    private String baiduAccount;

    /**
     * 是否绑定了设备
     */
    @Column(name = "bound_or_not")
    private Boolean boundOrNot;

    /**
     * 算力值
     */
    @Column(name = "calculating_force_value")
    private Long calculatingForceValue;

    /**
     * 插入时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 头像名
     */
    @Column(name = "head_file_name")
    private String headFileName;

    /**
     * 人头像
     */
    @Column(name = "header_url")
    private String headerUrl;

    /**
     * 邀请用户ID
     */
    @Column(name = "invite_user_id")
    private Long inviteUserId;

    /**
     * 关注公众号
     */
    @Column(name = "is_follow")
    private Boolean isFollow;

    /**
     * 完善资料
     */
    @Column(name = "is_perfect_information")
    private Boolean isPerfectInformation;

    /**
     * 签到
     */
    @Column(name = "is_sign_in")
    private Boolean isSignIn;

    /**
     * 用户昵称
     */
    private String name;

    /**
     * 绑定的设备id
     */
    @Column(name = "now_device_id")
    private String nowDeviceId;

    /**
     * 手机号账号
     */
    @Column(name = "phone_num")
    private String phoneNum;

    /**
     * 是否打开推送系统消息
     */
    @Column(name = "push_sys_msg")
    private Boolean pushSysMsg;

    /**
     * 性别
     */
    private String sex;

    /**
     * 签到总次数
     */
    @Column(name = "sign_in_total")
    private Integer signInTotal;

    /**
     * 第几位加入的
     */
    @Column(name = "the_first_few")
    private String theFirstFew;

    /**
     * 总的糖果数
     */
    private BigDecimal total;

    /**
     * 用户级别
     */
    @Column(name = "user_level")
    private Boolean userLevel;

    /**
     * 用户登录token保存
     */
    @Column(name = "user_token")
    private String userToken;

    /**
     * 昨日是否签到
     */
    @Column(name = "yesterday_is_sign_in")
    private Boolean yesterdayIsSignIn;

    /**
     * 昨日糖果数
     */
    @Column(name = "yesterday_total")
    private BigDecimal yesterdayTotal;

    /**
     * 生日
     */
    private Date birthday;

    private static final long serialVersionUID = 1L;

    /**
     * 获取用户ID
     *
     * @return id - 用户ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置用户ID
     *
     * @param id 用户ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户地址
     *
     * @return adress - 用户地址
     */
    public String getAdress() {
        return adress;
    }

    /**
     * 设置用户地址
     *
     * @param adress 用户地址
     */
    public void setAdress(String adress) {
        this.adress = adress;
    }

    /**
     * 获取百度帐号
     *
     * @return baidu_account - 百度帐号
     */
    public String getBaiduAccount() {
        return baiduAccount;
    }

    /**
     * 设置百度帐号
     *
     * @param baiduAccount 百度帐号
     */
    public void setBaiduAccount(String baiduAccount) {
        this.baiduAccount = baiduAccount;
    }

    /**
     * 获取是否绑定了设备
     *
     * @return bound_or_not - 是否绑定了设备
     */
    public Boolean getBoundOrNot() {
        return boundOrNot;
    }

    /**
     * 设置是否绑定了设备
     *
     * @param boundOrNot 是否绑定了设备
     */
    public void setBoundOrNot(Boolean boundOrNot) {
        this.boundOrNot = boundOrNot;
    }

    /**
     * 获取算力值
     *
     * @return calculating_force_value - 算力值
     */
    public Long getCalculatingForceValue() {
        return calculatingForceValue;
    }

    /**
     * 设置算力值
     *
     * @param calculatingForceValue 算力值
     */
    public void setCalculatingForceValue(Long calculatingForceValue) {
        this.calculatingForceValue = calculatingForceValue;
    }

    /**
     * 获取插入时间
     *
     * @return create_time - 插入时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置插入时间
     *
     * @param createTime 插入时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取头像名
     *
     * @return head_file_name - 头像名
     */
    public String getHeadFileName() {
        return headFileName;
    }

    /**
     * 设置头像名
     *
     * @param headFileName 头像名
     */
    public void setHeadFileName(String headFileName) {
        this.headFileName = headFileName;
    }

    /**
     * 获取人头像
     *
     * @return header_url - 人头像
     */
    public String getHeaderUrl() {
        return headerUrl;
    }

    /**
     * 设置人头像
     *
     * @param headerUrl 人头像
     */
    public void setHeaderUrl(String headerUrl) {
        this.headerUrl = headerUrl;
    }

    /**
     * 获取邀请用户ID
     *
     * @return invite_user_id - 邀请用户ID
     */
    public Long getInviteUserId() {
        return inviteUserId;
    }

    /**
     * 设置邀请用户ID
     *
     * @param inviteUserId 邀请用户ID
     */
    public void setInviteUserId(Long inviteUserId) {
        this.inviteUserId = inviteUserId;
    }

    /**
     * 获取关注公众号
     *
     * @return is_follow - 关注公众号
     */
    public Boolean getIsFollow() {
        return isFollow;
    }

    /**
     * 设置关注公众号
     *
     * @param isFollow 关注公众号
     */
    public void setIsFollow(Boolean isFollow) {
        this.isFollow = isFollow;
    }

    /**
     * 获取完善资料
     *
     * @return is_perfect_information - 完善资料
     */
    public Boolean getIsPerfectInformation() {
        return isPerfectInformation;
    }

    /**
     * 设置完善资料
     *
     * @param isPerfectInformation 完善资料
     */
    public void setIsPerfectInformation(Boolean isPerfectInformation) {
        this.isPerfectInformation = isPerfectInformation;
    }

    /**
     * 获取签到
     *
     * @return is_sign_in - 签到
     */
    public Boolean getIsSignIn() {
        return isSignIn;
    }

    /**
     * 设置签到
     *
     * @param isSignIn 签到
     */
    public void setIsSignIn(Boolean isSignIn) {
        this.isSignIn = isSignIn;
    }

    /**
     * 获取用户昵称
     *
     * @return name - 用户昵称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置用户昵称
     *
     * @param name 用户昵称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取绑定的设备id
     *
     * @return now_device_id - 绑定的设备id
     */
    public String getNowDeviceId() {
        return nowDeviceId;
    }

    /**
     * 设置绑定的设备id
     *
     * @param nowDeviceId 绑定的设备id
     */
    public void setNowDeviceId(String nowDeviceId) {
        this.nowDeviceId = nowDeviceId;
    }

    /**
     * 获取手机号账号
     *
     * @return phone_num - 手机号账号
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * 设置手机号账号
     *
     * @param phoneNum 手机号账号
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    /**
     * 获取是否打开推送系统消息
     *
     * @return push_sys_msg - 是否打开推送系统消息
     */
    public Boolean getPushSysMsg() {
        return pushSysMsg;
    }

    /**
     * 设置是否打开推送系统消息
     *
     * @param pushSysMsg 是否打开推送系统消息
     */
    public void setPushSysMsg(Boolean pushSysMsg) {
        this.pushSysMsg = pushSysMsg;
    }

    /**
     * 获取性别
     *
     * @return sex - 性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex 性别
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取签到总次数
     *
     * @return sign_in_total - 签到总次数
     */
    public Integer getSignInTotal() {
        return signInTotal;
    }

    /**
     * 设置签到总次数
     *
     * @param signInTotal 签到总次数
     */
    public void setSignInTotal(Integer signInTotal) {
        this.signInTotal = signInTotal;
    }

    /**
     * 获取第几位加入的
     *
     * @return the_first_few - 第几位加入的
     */
    public String getTheFirstFew() {
        return theFirstFew;
    }

    /**
     * 设置第几位加入的
     *
     * @param theFirstFew 第几位加入的
     */
    public void setTheFirstFew(String theFirstFew) {
        this.theFirstFew = theFirstFew;
    }

    /**
     * 获取总的糖果数
     *
     * @return total - 总的糖果数
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * 设置总的糖果数
     *
     * @param total 总的糖果数
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * 获取用户级别
     *
     * @return user_level - 用户级别
     */
    public Boolean getUserLevel() {
        return userLevel;
    }

    /**
     * 设置用户级别
     *
     * @param userLevel 用户级别
     */
    public void setUserLevel(Boolean userLevel) {
        this.userLevel = userLevel;
    }

    /**
     * 获取用户登录token保存
     *
     * @return user_token - 用户登录token保存
     */
    public String getUserToken() {
        return userToken;
    }

    /**
     * 设置用户登录token保存
     *
     * @param userToken 用户登录token保存
     */
    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    /**
     * 获取昨日是否签到
     *
     * @return yesterday_is_sign_in - 昨日是否签到
     */
    public Boolean getYesterdayIsSignIn() {
        return yesterdayIsSignIn;
    }

    /**
     * 设置昨日是否签到
     *
     * @param yesterdayIsSignIn 昨日是否签到
     */
    public void setYesterdayIsSignIn(Boolean yesterdayIsSignIn) {
        this.yesterdayIsSignIn = yesterdayIsSignIn;
    }

    /**
     * 获取昨日糖果数
     *
     * @return yesterday_total - 昨日糖果数
     */
    public BigDecimal getYesterdayTotal() {
        return yesterdayTotal;
    }

    /**
     * 设置昨日糖果数
     *
     * @param yesterdayTotal 昨日糖果数
     */
    public void setYesterdayTotal(BigDecimal yesterdayTotal) {
        this.yesterdayTotal = yesterdayTotal;
    }

    /**
     * 获取生日
     *
     * @return birthday - 生日
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置生日
     *
     * @param birthday 生日
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", adress=").append(adress);
        sb.append(", baiduAccount=").append(baiduAccount);
        sb.append(", boundOrNot=").append(boundOrNot);
        sb.append(", calculatingForceValue=").append(calculatingForceValue);
        sb.append(", createTime=").append(createTime);
        sb.append(", headFileName=").append(headFileName);
        sb.append(", headerUrl=").append(headerUrl);
        sb.append(", inviteUserId=").append(inviteUserId);
        sb.append(", isFollow=").append(isFollow);
        sb.append(", isPerfectInformation=").append(isPerfectInformation);
        sb.append(", isSignIn=").append(isSignIn);
        sb.append(", name=").append(name);
        sb.append(", nowDeviceId=").append(nowDeviceId);
        sb.append(", phoneNum=").append(phoneNum);
        sb.append(", pushSysMsg=").append(pushSysMsg);
        sb.append(", sex=").append(sex);
        sb.append(", signInTotal=").append(signInTotal);
        sb.append(", theFirstFew=").append(theFirstFew);
        sb.append(", total=").append(total);
        sb.append(", userLevel=").append(userLevel);
        sb.append(", userToken=").append(userToken);
        sb.append(", yesterdayIsSignIn=").append(yesterdayIsSignIn);
        sb.append(", yesterdayTotal=").append(yesterdayTotal);
        sb.append(", birthday=").append(birthday);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAdress() == null ? other.getAdress() == null : this.getAdress().equals(other.getAdress()))
            && (this.getBaiduAccount() == null ? other.getBaiduAccount() == null : this.getBaiduAccount().equals(other.getBaiduAccount()))
            && (this.getBoundOrNot() == null ? other.getBoundOrNot() == null : this.getBoundOrNot().equals(other.getBoundOrNot()))
            && (this.getCalculatingForceValue() == null ? other.getCalculatingForceValue() == null : this.getCalculatingForceValue().equals(other.getCalculatingForceValue()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getHeadFileName() == null ? other.getHeadFileName() == null : this.getHeadFileName().equals(other.getHeadFileName()))
            && (this.getHeaderUrl() == null ? other.getHeaderUrl() == null : this.getHeaderUrl().equals(other.getHeaderUrl()))
            && (this.getInviteUserId() == null ? other.getInviteUserId() == null : this.getInviteUserId().equals(other.getInviteUserId()))
            && (this.getIsFollow() == null ? other.getIsFollow() == null : this.getIsFollow().equals(other.getIsFollow()))
            && (this.getIsPerfectInformation() == null ? other.getIsPerfectInformation() == null : this.getIsPerfectInformation().equals(other.getIsPerfectInformation()))
            && (this.getIsSignIn() == null ? other.getIsSignIn() == null : this.getIsSignIn().equals(other.getIsSignIn()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getNowDeviceId() == null ? other.getNowDeviceId() == null : this.getNowDeviceId().equals(other.getNowDeviceId()))
            && (this.getPhoneNum() == null ? other.getPhoneNum() == null : this.getPhoneNum().equals(other.getPhoneNum()))
            && (this.getPushSysMsg() == null ? other.getPushSysMsg() == null : this.getPushSysMsg().equals(other.getPushSysMsg()))
            && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
            && (this.getSignInTotal() == null ? other.getSignInTotal() == null : this.getSignInTotal().equals(other.getSignInTotal()))
            && (this.getTheFirstFew() == null ? other.getTheFirstFew() == null : this.getTheFirstFew().equals(other.getTheFirstFew()))
            && (this.getTotal() == null ? other.getTotal() == null : this.getTotal().equals(other.getTotal()))
            && (this.getUserLevel() == null ? other.getUserLevel() == null : this.getUserLevel().equals(other.getUserLevel()))
            && (this.getUserToken() == null ? other.getUserToken() == null : this.getUserToken().equals(other.getUserToken()))
            && (this.getYesterdayIsSignIn() == null ? other.getYesterdayIsSignIn() == null : this.getYesterdayIsSignIn().equals(other.getYesterdayIsSignIn()))
            && (this.getYesterdayTotal() == null ? other.getYesterdayTotal() == null : this.getYesterdayTotal().equals(other.getYesterdayTotal()))
            && (this.getBirthday() == null ? other.getBirthday() == null : this.getBirthday().equals(other.getBirthday()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAdress() == null) ? 0 : getAdress().hashCode());
        result = prime * result + ((getBaiduAccount() == null) ? 0 : getBaiduAccount().hashCode());
        result = prime * result + ((getBoundOrNot() == null) ? 0 : getBoundOrNot().hashCode());
        result = prime * result + ((getCalculatingForceValue() == null) ? 0 : getCalculatingForceValue().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getHeadFileName() == null) ? 0 : getHeadFileName().hashCode());
        result = prime * result + ((getHeaderUrl() == null) ? 0 : getHeaderUrl().hashCode());
        result = prime * result + ((getInviteUserId() == null) ? 0 : getInviteUserId().hashCode());
        result = prime * result + ((getIsFollow() == null) ? 0 : getIsFollow().hashCode());
        result = prime * result + ((getIsPerfectInformation() == null) ? 0 : getIsPerfectInformation().hashCode());
        result = prime * result + ((getIsSignIn() == null) ? 0 : getIsSignIn().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getNowDeviceId() == null) ? 0 : getNowDeviceId().hashCode());
        result = prime * result + ((getPhoneNum() == null) ? 0 : getPhoneNum().hashCode());
        result = prime * result + ((getPushSysMsg() == null) ? 0 : getPushSysMsg().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getSignInTotal() == null) ? 0 : getSignInTotal().hashCode());
        result = prime * result + ((getTheFirstFew() == null) ? 0 : getTheFirstFew().hashCode());
        result = prime * result + ((getTotal() == null) ? 0 : getTotal().hashCode());
        result = prime * result + ((getUserLevel() == null) ? 0 : getUserLevel().hashCode());
        result = prime * result + ((getUserToken() == null) ? 0 : getUserToken().hashCode());
        result = prime * result + ((getYesterdayIsSignIn() == null) ? 0 : getYesterdayIsSignIn().hashCode());
        result = prime * result + ((getYesterdayTotal() == null) ? 0 : getYesterdayTotal().hashCode());
        result = prime * result + ((getBirthday() == null) ? 0 : getBirthday().hashCode());
        return result;
    }
}