package com.user.dto;

import lombok.Data;


@Data
public class Order {

    private String orderId;
    private String productId;
    private String productName;
    private String count;
    private String createTime;
    private String jdPrice;
    private String orderAmount;
    private String payAmount;
    private String orderStatus;
    private String account;
    private String customerName;
    private String customerAddress;
    private String customerMobile;
    private String remark;
    private String month;
    private int sheetNo;

}
