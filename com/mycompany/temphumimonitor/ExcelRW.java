/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.temphumimonitor;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.converters.DefaultConverterLoader;
import com.alibaba.excel.enums.CellExtraTypeEnum;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.util.ListUtils;
import static java.lang.Math.log;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author jason
 */
public class ExcelRW {
    /**
    * 单次缓存的数据量
    */
     public static final int BATCH_COUNT = 100;
    /**
     *临时存储
     */
     private ArrayList<ManifestData> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
     private ArrayList dataList = new ArrayList();

     public ArrayList getDataList() {
        return dataList;
     }
        /**
     * 最简单的写
     * <p>1. 创建excel对应的实体对象 参照{@link com.alibaba.easyexcel.test.demo.write.DemoData}
     * <p>2. 直接写即可
     */
    @Test
    public void simpleWrite() {
        Date date = new Date();
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formatdate = formatter.format(date);
        String fileName = "/home/jason/" + "1队" + formatdate + "温湿度.xlsx";
        // 这里 需要指定写用哪个class去读，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
//        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data()); // 写入时间约3s
        System.out.println("finish!");
    }
    /*
    * 加载配置文件
    */
    public void simpleRead() {
        // 写法1：JDK8+ ,不用额外写一个DemoDataListener
        String fileName = "/home/jason/系统配置.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, ManifestData.class, new ReadListener<ManifestData>() {
            @Override
            public void invoke(ManifestData data, AnalysisContext context) {
                System.out.println(data.getAddress());
                cachedDataList.add(data);
                if (cachedDataList.size() >= BATCH_COUNT) {
                    saveData();
                    System.out.println("BATCH!");
                    // 存储完成清理 list
                    cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
                }
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                System.out.println("ANALYSED!");
                saveData();
                System.out.println("doAfterAllAnalysed！");
            }

            /**
             * 加上存储数据库
             */
            private void saveData() {
                dataList = cachedDataList;
                System.out.println("加载配置文件%d条数据！" + cachedDataList.size()); 
            }
        }).sheet().doRead();
    }
    private List<DemoData> data() {
        List<DemoData> list = ListUtils.newArrayList();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }
}
