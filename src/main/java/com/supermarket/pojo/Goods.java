package com.supermarket.pojo;

import java.util.Date;

public class Goods {

    private Integer gId;

    private String gName;

    private Double gPrice;

    private String gCount;

    private Date gCreattime;

    private Date gUpdatetime;

    public Goods() {
    }

    public Goods(String gName) {
        this.gName = gName;
    }

    public Goods(Integer gId, String gName, Double gPrice, String gCount, Date gCreattime, Date gUpdatetime) {
        this.gId = gId;
        this.gName = gName;
        this.gPrice = gPrice;
        this.gCount = gCount;
        this.gCreattime = gCreattime;
        this.gUpdatetime = gUpdatetime;
    }

    public Integer getgId() {
        return gId;
    }

    public void setgId(Integer gId) {
        this.gId = gId;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName == null ? null : gName.trim();
    }

    public Double getgPrice() {
        return gPrice;
    }

    public void setgPrice(Double gPrice) {
        this.gPrice = gPrice;
    }

    public String getgCount() {
        return gCount;
    }

    public void setgCount(String gCount) {
        this.gCount = gCount == null ? null : gCount.trim();
    }

    public Date getgCreattime() {
        return gCreattime;
    }

    public void setgCreattime(Date gCreattime) {
        this.gCreattime = gCreattime;
    }

    public Date getgUpdatetime() {
        return gUpdatetime;
    }

    public void setgUpdatetime(Date gUpdatetime) {
        this.gUpdatetime = gUpdatetime;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "gId=" + gId +
                ", gName='" + gName + '\'' +
                ", gPrice=" + gPrice +
                ", gCount='" + gCount + '\'' +
                ", gCreattime=" + gCreattime +
                ", gUpdatetime=" + gUpdatetime +
                '}';
    }
}