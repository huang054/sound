/*
 * @Project Name: store
 * @File Name: ExcelUtils
 * @Package Name: com.yf.store.utils.util
 * @Date: 2018/6/7 15:25
 * @Creator: linshixing-1199
 * @line------------------------------
 * @修改人:
 * @修改时间:
 * @修改内容:
 */

package com.utils.util;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author chenpy
 * @desc excel导出
 * @date 2018/6/7 15:25
 * @see
 */
public class ExcelExportUtils {

    public static String DATE_FORMAT_YYYYMMDD = "yyyy-MM-dd";
    public static String DATE_FORMAT_YYYYMMDD_TIME = "yyyy-MM-dd HH:mm:ss";
    private static String AGENT_FIREFOX = "Firefox";
    private static String AGENT_CHROME = "Chrome";
    private static Integer ONE_THOUSAND = 1000;

    /**
     * 对文件流输出下载的中文文件名进行编码 屏蔽各种浏览器版本的差异性
     *
     * @param request
     * @param pFileName
     * @return
     * @throws Exception
     * @date 2017/9/28 16:15
     * @author chenpy-1072
     */
    private static String encodeFileName(HttpServletRequest request, String pFileName) throws Exception {
        String filename = null;
        String agent = request.getHeader("USER-AGENT");
        if (null != agent) {
            // Firefox
            if (-1 != agent.indexOf(AGENT_FIREFOX)) {
                filename = "=?UTF-8?B?"
                        + (new String(org.apache.commons.codec.binary.Base64.encodeBase64(pFileName.getBytes("UTF-8"))))
                        + "?=";
            }
            // Chrome
            else if (-1 != agent.indexOf(AGENT_CHROME)) {
                filename = new String(pFileName.getBytes(), "ISO8859-1");
            } else {// IE7+
                filename = java.net.URLEncoder.encode(pFileName, "UTF-8");
                filename = filename.replace("+", "%20");
            }
        } else {
            filename = pFileName;
        }
        return filename;
    }

    /**
     * 对象转map
     *
     * @param bean
     * @return
     * @date 2017/9/30 11:23
     * @author chenpy-1072
     */
    private static Map<String, Object> populateMap(Object bean) {
        Map<String, Object> objectMap = new HashMap<>();
        Class<?> klass = bean.getClass();
        boolean includeSuperClass = klass.getClassLoader() != null;
        Method[] methods = includeSuperClass ? klass.getMethods() : klass.getDeclaredMethods();
        for (int i = 0; i < methods.length; i += 1) {
            try {
                Method method = methods[i];
                if (Modifier.isPublic(method.getModifiers())) {
                    String name = method.getName();
                    String key = "";
                    if (name.startsWith("get")) {
                        if ("getClass".equals(name) || "getDeclaringClass".equals(name)) {
                            key = "";
                        } else {
                            key = name.substring(3);
                        }
                    } else if (name.startsWith("is")) {
                        key = name.substring(2);
                    }
                    if (key.length() > 0 && Character.isUpperCase(key.charAt(0))
                            && method.getParameterTypes().length == 0) {
                        if (key.length() == 1) {
                            key = key.toLowerCase();
                        } else if (!Character.isUpperCase(key.charAt(1))) {
                            key = key.substring(0, 1).toLowerCase() + key.substring(1);
                        }
                        Object result = method.invoke(bean, (Object[]) null);
                        if (result != null) {
                            objectMap.put(key, result);
                        }
                    }
                }
            } catch (Exception ignore) {
            }
        }
        return objectMap;
    }

    /**
     * 导出Excel 2007 OOXML (.xlsx)格式
     *
     * @param title             标题行
     * @param headMap           属性-列头
     * @param list              数据集
     * @param dateFormatPattern 日期格式，传null值则默认 年月日
     */
    public static SXSSFWorkbook export(String title, Map<String, String> headMap, List list, String... dateFormatPattern) {
        String dateFormat = DATE_FORMAT_YYYYMMDD;
        if (!ArrayUtils.isEmpty(dateFormatPattern) && StringUtils.isNotEmpty(dateFormatPattern[0])) {
            dateFormat = dateFormatPattern[0];
        }
        // 声明一个工作薄
        SXSSFWorkbook workbook = new SXSSFWorkbook(ONE_THOUSAND);
        workbook.setCompressTempFiles(true);
        // 表头样式
        CellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
        Font titleFont = workbook.createFont();
        titleFont.setFontHeightInPoints((short) 20);
        titleFont.setBold(true);
        titleStyle.setFont(titleFont);
        // 列头样式
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        headerStyle.setBorderBottom(CellStyle.BORDER_THIN);
        headerStyle.setBorderLeft(CellStyle.BORDER_THIN);
        headerStyle.setBorderRight(CellStyle.BORDER_THIN);
        headerStyle.setBorderTop(CellStyle.BORDER_THIN);
        headerStyle.setFillForegroundColor(HSSFColor.WHITE.index);
        headerStyle.setAlignment(CellStyle.ALIGN_CENTER);
        Font headerFont = workbook.createFont();
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);
        // 单元格样式
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
        Font cellFont = workbook.createFont();
        cellFont.setBold(false);
        cellStyle.setFont(cellFont);
        // 生成一个(带标题)表格
        SXSSFSheet sheet = workbook.createSheet();
        // 设置列宽
        int minBytes = 30;
        int[] arrColWidth = new int[headMap.size()];
        // 产生表格标题行,以及设置列宽
        String[] properties = new String[headMap.size()];
        String[] headers = new String[headMap.size()];
        int ii = 0;
        for (Iterator<String> iter = headMap.keySet().iterator(); iter.hasNext(); ) {
            String fieldName = iter.next();
            properties[ii] = fieldName;
            headers[ii] = headMap.get(fieldName);
            int bytes = fieldName.getBytes().length;
            arrColWidth[ii] = bytes < minBytes ? minBytes : bytes;
            sheet.setColumnWidth(ii, arrColWidth[ii] * 150);
            ii++;
        }
        // 遍历集合数据，产生数据行
        // 表头 rowIndex=0
        SXSSFRow titleRow = sheet.createRow(0);
        titleRow.createCell(0).setCellValue(title);
        titleRow.getCell(0).setCellStyle(titleStyle);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headMap.size() - 1));
        // 列头 rowIndex =1
        SXSSFRow headerRow = sheet.createRow(1);
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
            headerRow.getCell(i).setCellStyle(headerStyle);
        }
        // 数据内容从 rowIndex=2开始
        if (CollectionUtils.isNotEmpty(list)) {
            int rowIndex = 2;
            for (Object obj : list) {
                if (obj == null) {
                    continue;
                }
                Map<String, Object> map = null;
                if (!(obj instanceof Map)) {
                    map = populateMap(obj);
                } else {
                    map = (Map) obj;
                }
                if (rowIndex == 65535 || rowIndex == 0) {
                    if (rowIndex != 0) {
                        // 如果数据超过了，则在第二页显示
                        sheet = workbook.createSheet();
                    }
                }
                SXSSFRow dataRow = sheet.createRow(rowIndex);
                for (int i = 0; i < properties.length; i++) {
                    SXSSFCell newCell = dataRow.createCell(i);
                    Object o = null;
                    if (map.containsKey(properties[i])) {
                        o = map.get(properties[i]);
                    }
                    String cellValue;
                    if (o == null) {
                        cellValue = "";
                    } else if (o instanceof Date) {
                        cellValue = new SimpleDateFormat(dateFormat).format(o);
                    } else if (o instanceof Float || o instanceof Double) {
                        cellValue = new BigDecimal(o.toString()).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
                    } else {
                        cellValue = o.toString();
                    }
                    // 处理常见数字类型,不支持double，避免精度损失
                    if (o instanceof Integer) {
                        newCell.setCellValue(Integer.parseInt(cellValue));
                    } else if (o instanceof Double) {
                        newCell.setCellValue(Double.valueOf(cellValue));
                    } else {
                        newCell.setCellValue(cellValue);
                    }
                    newCell.setCellStyle(cellStyle);
                }
                rowIndex++;
            }
        }
        // 自动调整宽度
        try {
            return workbook;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] exportByte(String title, Map<String, String> headMap, List list, String... dateFormatPattern) throws Exception {
        SXSSFWorkbook workbook = export(title, headMap, list, dateFormatPattern);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);
        workbook.close();
        return byteArrayOutputStream.toByteArray();
    }


    public static void export(String title, Map<String, String> headMap, List list, HttpServletRequest request, HttpServletResponse response, String... dateFormatPattern) throws Exception {
        SXSSFWorkbook workbook = export(title, headMap, list, dateFormatPattern);
        title = new StringBuilder(encodeFileName(request, title))
                .append(DateFormatUtils.format(new Date(), "yyyy-MM-dd")).toString();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + title + ".xlsx");
        response.setHeader("Pragma", "No-cache");
        OutputStream outputStream
                = response.getOutputStream();
        workbook.write(outputStream);
    }


    /**
     * 填充数据
     *
     * @param headers
     * @param list
     * @param dateFormat
     * @param workbook
     * @param headerStyle
     * @param cellStyle
     * @param sheet
     */
    private static void setData(String[] headers, List list, String dateFormat, SXSSFWorkbook workbook,
                                CellStyle headerStyle, CellStyle cellStyle, SXSSFSheet sheet) {
        for (int i = 0; i < headers.length; i++) {
            sheet.setColumnWidth(i, 30 * 256);
        }
        // 列头 rowIndex =1
        SXSSFRow headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
            headerRow.getCell(i).setCellStyle(headerStyle);
        }
        // 数据内容从 rowIndex=2开始
        if (CollectionUtils.isNotEmpty(list)) {
            int rowIndex = 1;
            for (Object obj : list) {
                if (obj == null) {
                    continue;
                }
                Map<String, Object> map = null;
                if (!(obj instanceof Map)) {
                    map = populateMap(obj);
                } else {
                    map = (Map) obj;
                }
                if (rowIndex == 65535 || rowIndex == 0) {
                    if (rowIndex != 0) {
                        // 如果数据超过了，则在第二页显示
                        sheet = workbook.createSheet();
                    }
                }
                SXSSFRow dataRow = sheet.createRow(rowIndex);
                for (int i = 0; i < headers.length; i++) {
                    SXSSFCell newCell = dataRow.createCell(i);
                    Object o = null;
                    if (map.containsKey(headers[i])) {
                        o = map.get(headers[i]);
                    }
                    String cellValue;
                    if (o == null) {
                        cellValue = "";
                    } else if (o instanceof Date) {
                        cellValue = new SimpleDateFormat(dateFormat).format(o);
                    } else if (o instanceof Float || o instanceof Double) {
                        cellValue = new BigDecimal(o.toString()).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
                    } else {
                        cellValue = o.toString();
                    }
                    // 处理常见数字类型,不支持double，避免精度损失
                    if (o instanceof Integer) {
                        newCell.setCellValue(Integer.parseInt(cellValue));
                    } else if (o instanceof Long) {
                        newCell.setCellValue(Long.parseLong(cellValue));
                    } else if (o instanceof Double) {
                        newCell.setCellValue(Double.valueOf(cellValue));
                    } else {
                        newCell.setCellValue(cellValue);
                    }
                    newCell.setCellStyle(cellStyle);
                }
                rowIndex++;
            }
        }
    }
}
