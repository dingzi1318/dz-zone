package com.user.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Sets;
import com.user.dto.ApiResult;
import com.user.dto.Order;
import com.user.enums.ExcelSheetName;
import com.user.util.ExcelReader;
import com.user.util.ExcelWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class ExcelController {

    @GetMapping(value = "/excel/transfer")
    public ApiResult transferExcel() {
        List<Order> orders = orderExcelReader();
        log.info("读取订单销售Excel，结果:{}", orders.size());

        // 3个月都下单的订单集合
        List<Order> allMonthOrderList = Lists.newArrayList();

        // 2个月都下单的订单集合
        List<Order> twoMonthOrderList = Lists.newArrayList();

        // 1个月下单的订单集合
        List<Order> oneMonthOrderList = Lists.newArrayList();

        // 数据处理
        handlerOrderList(orders, allMonthOrderList, twoMonthOrderList, oneMonthOrderList);

        // 导出到excel
        orderExcelWriter(allMonthOrderList, twoMonthOrderList, oneMonthOrderList);

        return ApiResult.success();
    }

    /**
     * 读取订单Excel
     *
     * @return
     */
    public List<Order> orderExcelReader() {
        // 设定Excel文件所在路径
        String excelFileName = "C:/Users/dingzi/Desktop/order.xls";
        // 读取Excel文件内容
        List<Order> orderList = ExcelReader.readExcel(excelFileName);
        return orderList;
    }

    /**
     * 业务处理
     *
     * @param orderList
     * @return
     */
    public void handlerOrderList(List<Order> orderList, List<Order> allMonthOrderList, List<Order> twoMonthOrderList, List<Order> oneMonthOrderList) {
        // 按月份分组
        Map<String, List<Order>> orderMapByMonth = orderList.stream().collect(Collectors.groupingBy(Order::getMonth));
        // 查分3个集合：1：3个月都下单了 2：2个月都下单了 3：只有1个月下单了

        // 11月订单
        List<Order> elevenOrderList = orderMapByMonth.get(ExcelSheetName.ONE.getMonth());
        Map<String, List<Order>> elevenCustomerOrderMap = elevenOrderList.stream().collect(Collectors.groupingBy(Order::getAccount));

        // 12月订单
        List<Order> decOrderList = orderMapByMonth.get(ExcelSheetName.TWO.getMonth());
        Map<String, List<Order>> decCustomerOrderMap = decOrderList.stream().collect(Collectors.groupingBy(Order::getAccount));

        // 1月份订单
        List<Order> janOrderList = orderMapByMonth.get(ExcelSheetName.THREE.getMonth());
        Map<String, List<Order>> janCustomerOrderMap = janOrderList.stream().collect(Collectors.groupingBy(Order::getAccount));

        // 取出所有的下单账号
        Set<String> account = orderList.stream().map(Order::getAccount).collect(Collectors.toSet());

        account.forEach(k -> {
            boolean existEleOrder = CollectionUtils.isNotEmpty(elevenCustomerOrderMap.get(k));
            boolean existDecOrder = CollectionUtils.isNotEmpty(decCustomerOrderMap.get(k));
            boolean existJanOrder = CollectionUtils.isNotEmpty(janCustomerOrderMap.get(k));
            // 3个月都下单了
            if (existEleOrder && existDecOrder && existJanOrder) {
                allMonthOrderList.addAll(elevenCustomerOrderMap.get(k));
                allMonthOrderList.addAll(decCustomerOrderMap.get(k));
                allMonthOrderList.addAll(janCustomerOrderMap.get(k));
            }
            // 2个月都下单了
            else if (existEleOrder && existDecOrder) {
                twoMonthOrderList.addAll(elevenCustomerOrderMap.get(k));
                twoMonthOrderList.addAll(decCustomerOrderMap.get(k));
            }

            else if (existEleOrder && existJanOrder) {
                twoMonthOrderList.addAll(elevenCustomerOrderMap.get(k));
                twoMonthOrderList.addAll(janCustomerOrderMap.get(k));
            }

            else if (existDecOrder && existJanOrder) {
                twoMonthOrderList.addAll(decCustomerOrderMap.get(k));
                twoMonthOrderList.addAll(janCustomerOrderMap.get(k));
            }
            // 只有一个月下单了
            else if (existEleOrder) {
                oneMonthOrderList.addAll(elevenCustomerOrderMap.get(k));
            }
            else if (existDecOrder) {
                oneMonthOrderList.addAll(decCustomerOrderMap.get(k));
            }
            else if (existJanOrder) {
                oneMonthOrderList.addAll(janCustomerOrderMap.get(k));
            }
        });

    }


    /**
     * 生成excel
     *
     * @return
     */
    public void orderExcelWriter(List<Order> orderList1, List<Order> orderList2, List<Order> orderList3) {
        // 如需生成xls的Excel，请使用下面的工作簿对象，注意后续输出时文件后缀名也需更改为xls
        Workbook workbook = new HSSFWorkbook();
        // 写入数据到工作簿对象内
        ExcelWriter.exportData(workbook, orderList1, "3个月均下单");
        ExcelWriter.exportData(workbook, orderList2, "2个月均下单");
        ExcelWriter.exportData(workbook, orderList3, "1个月下单");
        // 以文件的形式输出工作簿对象
        FileOutputStream fileOut = null;
        try {
            String exportFilePath = "C:/Users/dingzi/Desktop/order-static.xls";
            File exportFile = new File(exportFilePath);
            if (!exportFile.exists()) {
                exportFile.createNewFile();
            }

            fileOut = new FileOutputStream(exportFilePath);
            workbook.write(fileOut);
            fileOut.flush();
        } catch (Exception e) {
            log.warn("输出Excel时发生错误，错误原因：" + e.getMessage());
        } finally {
            try {
                if (null != fileOut) {
                    fileOut.close();
                }
                if (null != workbook) {
                    workbook.close();
                }
            } catch (IOException e) {
                log.warn("关闭输出流时发生错误，错误原因：" + e.getMessage());
            }
        }
    }

}
