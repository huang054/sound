package com.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "音频赠送币信息")
public class AudioGiftInfo {

    @ApiModelProperty(value = "币种ID")
    private Long coinId;

    @ApiModelProperty(value = "币种名称")
    private String coinName;

    @ApiModelProperty(value = "赠送币数量")
    private Integer giftCoinCount;

    public Long getCoinId() {
        return coinId;
    }

    public void setCoinId(Long coinId) {
        this.coinId = coinId;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public Integer getGiftCoinCount() {
        return giftCoinCount;
    }

    public void setGiftCoinCount(Integer giftCoinCount) {
        this.giftCoinCount = giftCoinCount;
    }

    @Override
    public String toString() {
        return "AudioGiftInfo{" +
                "coinId=" + coinId +
                ", coinName='" + coinName + '\'' +
                ", giftCoinCount=" + giftCoinCount +
                '}';
    }
}
