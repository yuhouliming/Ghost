package com.example.dockerdemo.controller;

import com.example.dockerdemo.domain.ExcelData;
import com.example.dockerdemo.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/export",method = RequestMethod.GET)
public class ExcelController {

    @Autowired
    private HttpServletResponse response;


    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public void export(@RequestParam("start") int start, @RequestParam("size") int size, @RequestParam("userId") String userId) throws Exception {
        try {
            List<Map<String, String>> rowList = new ArrayList<>();
            ExcelData excelData = new ExcelData();
            List<String> titles = getExcelTitles(rowList);
            List<List<Object>> rows = this.getExcelRows(rowList, titles);
            excelData.setName("标注明细");
            excelData.setTitles(titles);
            excelData.setRows(rows);
            ExcelUtils.exportExcel(response, "标注明细导出" + ".xlsx", excelData);
        }catch (Exception e){
            //log.error("导出标注明细表格报错：{}", e);
        }
}
    private List<String> getExcelTitles(List<Map<String, String>> rowList) {
        List<String> rows = new ArrayList<>();
        if (rowList.size() < 1) {
            return rows;
        }

        Map<String, String> firstRow = rowList.get(0);
        rows.addAll(firstRow.keySet());
        return rows;
    }

    /**
     * 获取 Excel 行数据
     */
    private List<List<Object>> getExcelRows(List<Map<String, String>> rowList, List<String> titles) {
        List<List<Object>> rows = new ArrayList<>();
        for (Map<String, String> rowMap : rowList) {
            List<Object> row = new ArrayList<>();
            for (String title : titles) {
                row.add(rowMap.get(title) == null ? "" : rowMap.get(title));
            }
            rows.add(row);
        }
        return rows;
    }
}
