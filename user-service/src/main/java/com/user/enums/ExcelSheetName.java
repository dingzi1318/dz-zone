package com.user.enums;

import java.util.stream.Stream;

public enum ExcelSheetName {

    ONE(0, "11月份"),
    TWO(1, "12月份"),
    THREE(2, "1月份"),
    ;

    private final int order;

    private final String month;

    ExcelSheetName(int order, String month) {
        this.order = order;
        this.month = month;
    }

    public int getOrder() {
        return order;
    }

    public String getMonth() {
        return month;
    }

    public static ExcelSheetName get(int num) {
        return Stream.of(ExcelSheetName.values()).filter(k -> k.getOrder() == num).findAny().orElse(null);
    }
}
