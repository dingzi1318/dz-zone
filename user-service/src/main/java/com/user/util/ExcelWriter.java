package com.user.util;

import com.user.dto.Order;
import com.user.enums.OrderCellNum;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExcelWriter {

    private static List<String> CELL_HEADS = Stream.of(OrderCellNum.values()).sorted(Comparator.comparing(OrderCellNum::getExportRow))
            .map(OrderCellNum::getDesc).collect(Collectors.toList());

    /**
     * 生成Excel并写入数据信息
     *
     * @param dataList 数据列表
     * @return 写入数据后的工作簿对象
     */
    public static void exportData(Workbook workbook, List<Order> dataList, String sheetName) {

        // 生成Sheet表，写入第一行的列头
        Sheet sheet = buildDataSheet(workbook, sheetName);
        //构建每行的数据内容
        int rowNum = 1;
        for (Iterator<Order> it = dataList.iterator(); it.hasNext(); ) {
            Order data = it.next();
            if (data == null) {
                continue;
            }
            //输出行数据
            Row row = sheet.createRow(rowNum++);
            convertDataToRow(data, row);
        }
    }

    /**
     * 生成sheet表，并写入第一行数据（列头）
     *
     * @param workbook 工作簿对象
     * @return 已经写入列头的Sheet
     */
    private static Sheet buildDataSheet(Workbook workbook, String sheetName) {
        Sheet sheet = workbook.createSheet(sheetName);
        // 设置列头宽度
        for (int i = 0; i < CELL_HEADS.size(); i++) {
            sheet.setColumnWidth(i, 4000);
        }
        // 设置默认行高
        sheet.setDefaultRowHeight((short) 400);
        // 构建头单元格样式
        CellStyle cellStyle = buildHeadCellStyle(sheet.getWorkbook());
        // 写入第一行各列的数据
        Row head = sheet.createRow(0);
        for (int i = 0; i < CELL_HEADS.size(); i++) {
            Cell cell = head.createCell(i);
            cell.setCellValue(CELL_HEADS.get(i));
            cell.setCellStyle(cellStyle);
        }
        return sheet;
    }

    /**
     * 设置第一行列头的样式
     *
     * @param workbook 工作簿对象
     * @return 单元格样式对象
     */
    private static CellStyle buildHeadCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        //对齐方式设置
        style.setAlignment(HorizontalAlignment.CENTER);
        //边框颜色和宽度设置
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex()); // 下边框
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex()); // 左边框
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex()); // 右边框
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex()); // 上边框
        //设置背景颜色
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //粗体字设置
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        return style;
    }

    /**
     * 将数据转换成行
     *
     * @param order 源数据
     * @param row  行对象
     * @return
     */
    private static void convertDataToRow(Order order, Row row) {
        Cell cell;
        // 月份
        cell = row.createCell(OrderCellNum.MONTH.getExportRow());
        cell.setCellValue(order.getMonth());

        // 下单账号
        cell = row.createCell(OrderCellNum.ACCOUNT.getExportRow());
        cell.setCellValue(order.getAccount());

        // 客户姓名
        cell = row.createCell(OrderCellNum.CUSTOMER_NAME.getExportRow());
        cell.setCellValue(order.getCustomerName());

        // 客户地址
        cell = row.createCell(OrderCellNum.CUSTOMER_ADDRESS.getExportRow());
        cell.setCellValue(order.getCustomerAddress());

        // 联系电话
        cell = row.createCell(OrderCellNum.CUSTOMER_MOBILE.getExportRow());
        cell.setCellValue(order.getCustomerMobile());

        // 订单编号
        cell = row.createCell(OrderCellNum.ORDER_ID.getExportRow());
        cell.setCellValue(order.getOrderId());

        // 商品ID
        cell = row.createCell(OrderCellNum.PRODUCT_ID.getExportRow());
        cell.setCellValue(order.getProductId());

        // 商品名称
        cell = row.createCell(OrderCellNum.PRODUCT_NAME.getExportRow());
        cell.setCellValue(order.getProductName());

        // 订购数量
        cell = row.createCell(OrderCellNum.COUNT.getExportRow());
        cell.setCellValue(order.getCount());

        // 下单时间
        cell = row.createCell(OrderCellNum.ORDER_TIME.getExportRow());
        cell.setCellValue(order.getCreateTime());

        // 京东价
        cell = row.createCell(OrderCellNum.JD_PRICE.getExportRow());
        cell.setCellValue(order.getJdPrice());

        // 订单金额
        cell = row.createCell(OrderCellNum.ORDER_AMOUNT.getExportRow());
        cell.setCellValue(order.getOrderAmount());

        // 结算金额
        cell = row.createCell(OrderCellNum.SETTLE_AMOUNT.getExportRow());
        cell.setCellValue(order.getPayAmount());

        // 订单状态
        cell = row.createCell(OrderCellNum.ORDER_STATUS.getExportRow());
        cell.setCellValue(order.getOrderStatus());

        // 订单备注
        cell = row.createCell(OrderCellNum.REMARK.getExportRow());
        cell.setCellValue(order.getRemark());
    }
}
