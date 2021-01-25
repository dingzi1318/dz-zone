package com.user.enums;

public enum OrderCellNum {

    MONTH(-1, "月份", 0),
    ACCOUNT(13,"下单账号",1),
    CUSTOMER_NAME(14, "客户姓名", 2),
    CUSTOMER_MOBILE(16,"联系电话",3),
    ORDER_ID(0, "订单编号", 4),
    CUSTOMER_ADDRESS(15,"客户地址", 5),
    PRODUCT_ID(1,"商品ID",6),
    PRODUCT_NAME(2,"商品名称",7),
    COUNT(3,"订购数量",8),
    ORDER_TIME(5,"下单时间",9),
    JD_PRICE(6,"京东价",10),
    ORDER_AMOUNT(7,"订单金额",11),
    SETTLE_AMOUNT(8,"结算金额",12),
    ORDER_STATUS(11,"订单状态",13),
    REMARK(17,"订单备注",14)
    ;

    private final int num;
    private final String desc;
    private final int exportRow;

    OrderCellNum(int num, String desc, int exportRow) {
        this.num = num;
        this.desc = desc;
        this.exportRow = exportRow;
    }

    public int getNum() {
        return num;
    }

    public String getDesc() {
        return desc;
    }

    public int getExportRow() {
        return exportRow;
    }
}
