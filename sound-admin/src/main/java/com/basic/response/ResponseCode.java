package com.basic.response;

/**
 * 异常代码
 * 9xx 系统错误码
 * 10XX全局错误码
 * 11XX用户相关码
 * http 协议规范为主
 *
 * @author
 */
public enum ResponseCode {
    /**
     * 异常代码
     */
    CODE_200(200, "请求成功"),
    //系统未捕获异常
    CODE_400(400, "请求错误,请稍后重试"),
    //并发异常 并发时可能出现此类异常，其下有若干子类异常来标识乐观锁和获取锁失败这两类异常信息
    CODE_900(900, "请求过于频繁,请稍后再试"),
    CODE_901(901, "方法请求错误"),
    CODE_902(902, "请求过于频繁,请一分钟后再试"),

    CODE_1000(1000, "请求错误"),
    CODE_1001(1001, "参数错误"),
    CODE_1002(1002, "非法请求"),
    CODE_1003(1003, "请求失败，请重新操作"),
    CODE_1004(1004, "用户未登录"),
    CODE_1005(1005, "Token已过期"),
    CODE_1006(1006, "您没有足够权限操作，请联系管理员。"),
    CODE_1007(1007, "用户名和密码错误"),
    CODE_1008(1008, "用户已存在"),
    CODE_1009(1009, "账号或密码错误，请重试。"),
    CODE_1018(1018, "您的账号存在异常操作已被平台冻结！如有疑问，请联系客服。"),
    CODE_1101(1101, "用户不存在"),
    CODE_1102(1102, "操作失败，请刷新页面重试"),
    CODE_1103(1103, "操作异常，请联系管理员"),
    CODE_1104(1104, "请选择文件上传"),
    CODE_1105(1105, "上传文件失败，请联系管理员");

    private int code;
    private String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
