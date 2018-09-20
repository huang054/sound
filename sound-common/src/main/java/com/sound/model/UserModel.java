package com.sound.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "userinfo") // 构建数据库表实体
@Table(name = "user", indexes = {@Index(name = "k0", columnList = "phoneNum")})
public class UserModel implements Serializable {

    private static final long serialVersionUID = -3946734305303957850L;

    /**
     * 主键
     */
    @ApiModelProperty(hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    @ApiModelProperty(hidden = true)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间'")
    private Date createTime;
    @NotNull
    @Column(columnDefinition = "varchar(255) default '' COMMENT '用户昵称'")
    private String name;

    @ApiModelProperty(hidden = true)
    @Column(columnDefinition = "varchar(255) default '' COMMENT '人头像'")
    private String headerUrl;
/*    @NotBlank(message="password不能为空")
    @Column(length = 32)
    private String password;//密码
*/

    @Column(columnDefinition = "varchar(64) default '' COMMENT '百度帐号'")
    private String baiduAccount;

    @Column(columnDefinition = "varchar(32) default '' COMMENT '手机号账号'")
    private String phoneNum;

    @Column(columnDefinition = "tinyint(1) default 0 COMMENT '是否绑定了设备'")
    private Boolean boundOrNot;

    @Column(columnDefinition = "tinyint(1) default 1 COMMENT '是否打开推送系统消息'")
    private Boolean pushSysMsg;

    @Column(columnDefinition = "varchar(255) default 0 COMMENT '绑定的设备id'")
    private String nowDeviceId;


    @Column(columnDefinition = "varchar(64) default '' COMMENT '性别'")
    private String sex;

    @Column(columnDefinition = "varchar(64) default '' COMMENT '用户地址'")
    private String adress;
    @Temporal(TemporalType.DATE)
    @ApiModelProperty(hidden = true)
    @Column(columnDefinition = "Timestamp COMMENT ''")
    private Date birthday;

    @Column(precision = 12, scale = 8, columnDefinition = " COMMENT '昨日糖果数'")
    private BigDecimal yesterdayTotal;

    @Column(precision = 12, scale = 8, columnDefinition = " COMMENT '总的糖果数'")
    private BigDecimal total;

    @Column(columnDefinition = "varchar(64) default '' COMMENT '第几位加入的'")
    private String theFirstFew;

    @Column(columnDefinition = "varchar(64) default '' COMMENT '用户登录token保存'")
    private String userToken;

    @Column(columnDefinition = "tinyint(1) default 1 COMMENT '用户级别'")
    private int userLevel;

    @Column(columnDefinition = "varchar(255) default '' COMMENT ''")
    private String headFileName;

    @Column(columnDefinition = "tinyint(1) default 0 COMMENT '签到'")
    private boolean isSignIn;

    @Column(columnDefinition = "tinyint(1) default 0 COMMENT '关注公众号'")
    private boolean isFollow;

    @Column(columnDefinition = "tinyint(1) default 0 COMMENT '完善资料'")
    private boolean isPerfectInformation;

    @Column(precision = 12, scale = 0)
    private BigDecimal calculatingForceValue;

    @Column(columnDefinition = "tinyint(1) default 0 COMMENT ''")
    private boolean yesterdayIsSignIn;

    @Column(columnDefinition = "int(11) DEFAULT 0 COMMENT ''")
    private int signInTotal;

    @Column(columnDefinition = "bigint(20) DEFAULT 0 COMMENT ''")
    private long inviteUserId;


    public long getInviteUserId() {
        return inviteUserId;
    }

    public void setInviteUserId(long inviteUserId) {
        this.inviteUserId = inviteUserId;
    }

    public BigDecimal getCalculatingForceValue() {
        return calculatingForceValue;
    }

    public void setCalculatingForceValue(BigDecimal calculatingForceValue) {
        this.calculatingForceValue = calculatingForceValue;
    }

    public boolean isYesterdayIsSignIn() {
        return yesterdayIsSignIn;
    }

    public void setYesterdayIsSignIn(boolean yesterdayIsSignIn) {
        this.yesterdayIsSignIn = yesterdayIsSignIn;
    }

    public int getSignInTotal() {
        return signInTotal;
    }

    public void setSignInTotal(int signInTotal) {
        this.signInTotal = signInTotal;
    }

    public boolean isSignIn() {
        return isSignIn;
    }

    public void setSignIn(boolean isSignIn) {
        this.isSignIn = isSignIn;
    }

    public boolean isFollow() {
        return isFollow;
    }

    public void setFollow(boolean isFollow) {
        this.isFollow = isFollow;
    }

    public boolean isPerfectInformation() {
        return isPerfectInformation;
    }

    public void setPerfectInformation(boolean isPerfectInformation) {
        this.isPerfectInformation = isPerfectInformation;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    public String getHeadFileName() {
        return headFileName;
    }

    public void setHeadFileName(String headFileName) {
        this.headFileName = headFileName;
    }

    public BigDecimal getYesterdayTotal() {
        return yesterdayTotal;
    }

    public void setYesterdayTotal(BigDecimal yesterdayTotal) {
        this.yesterdayTotal = yesterdayTotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getTheFirstFew() {
        return theFirstFew;
    }

    public void setTheFirstFew(String theFirstFew) {
        this.theFirstFew = theFirstFew;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Boolean getBoundOrNot() {
        return boundOrNot;
    }

    public void setBoundOrNot(Boolean boundOrNot) {
        this.boundOrNot = boundOrNot;
    }


    public String getBaiduAccount() {
        return baiduAccount;
    }

    public void setBaiduAccount(String baiduAccount) {
        this.baiduAccount = baiduAccount;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }


    public Boolean isPushSysMsg() {
        return pushSysMsg;
    }

    public void setPushSysMsg(Boolean pushSysMsg) {
        this.pushSysMsg = pushSysMsg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeaderUrl() {
        return headerUrl;
    }

    public void setHeaderUrl(String headerUrl) {
        this.headerUrl = headerUrl;
    }


    public Boolean getPushSysMsg() {
        return pushSysMsg;
    }

    public String getNowDeviceId() {
        return nowDeviceId;
    }

    public void setNowDeviceId(String nowDeviceId) {
        this.nowDeviceId = nowDeviceId;
    }

    @Override
    public String toString() {
        return "UserModel [id=" + id + ", createTime=" + createTime + ", name=" + name + ", headerUrl=" + headerUrl
                + ", baiduAccount=" + baiduAccount + ", phoneNum=" + phoneNum + ", boundOrNot=" + boundOrNot
                + ", pushSysMsg=" + pushSysMsg + ", nowDeviceId=" + nowDeviceId + ", sex=" + sex + ", adress=" + adress
                + ", birthday=" + birthday + ", yesterdayTotal=" + yesterdayTotal + ", total=" + total
                + ", theFirstFew=" + theFirstFew + ", userToken=" + userToken + ", userLevel=" + userLevel
                + ", headFileName=" + headFileName + ", isSignIn=" + isSignIn + ", isFollow=" + isFollow
                + ", isPerfectInformation=" + isPerfectInformation + ", calculatingForceValue=" + calculatingForceValue
                + ", yesterdayIsSignIn=" + yesterdayIsSignIn + ", signInTotal=" + signInTotal + ", inviteUserId="
                + inviteUserId + "]";
    }

}

