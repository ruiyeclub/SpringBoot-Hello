package cn.ruiyeclub;

import cn.ruiyeclub.entity.ExcelModel;
import cn.ruiyeclub.utils.DataConvertUtil;
import cn.ruiyeclub.utils.ExcelConvertCsvUtil;
import cn.ruiyeclub.utils.ExcelUtil;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
class EasyExcelApplicationTests {

    @Test
    void readExcelTest() throws Exception {
        //读取excel
        File file = new File("D:\\2.xlsx");
        InputStream inputStream = new FileInputStream(file);
        //导入excle
        List<ExcelModel> datas = ExcelUtil.readExcel(inputStream, ExcelModel.class, ExcelTypeEnum.XLSX);
        log.info(datas.toString());
    }

    @Test
    void writeExcelTest() throws Exception {
        //单sheet,单table导出测试
        List<ExcelModel> excelModelList = new ArrayList<ExcelModel>();
        for (int i = 0; i < 5; i++) {
            ExcelModel excelModel = new ExcelModel("日期" + i, "上班时间" + i,
                    "下班时间" + i, "加班时长" + i, "备注" + i);
            excelModelList.add(excelModel);
        }
        File file1 = new File("D:\\2.xlsx");
        ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
        byte[] bytes = ExcelUtil.writeExcel(outputStream1, excelModelList, ExcelModel.class, ExcelTypeEnum.XLSX);
        FileOutputStream outputStream = new FileOutputStream(file1);
        outputStream.write(bytes);
    }

    @Test
    void convertExcelToCsvTest() throws Exception {
        //读取excel
        File file = new File("E:\\2.xlsx");
        File file1 = new File("E:\\3.csv");
        InputStream inputStream = new FileInputStream(file);
        byte[] bytes = DataConvertUtil.inputStreamTobyte2(inputStream);
        byte[] bytes1 = ExcelConvertCsvUtil.convertExcelToCsv(bytes);
        FileOutputStream outputStream = new FileOutputStream(file1);
        outputStream.write(bytes1);

    }

    @BeforeEach
    void testBefore() {
        log.info("测试开始!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    @AfterEach
    void testAfter() {
        log.info("测试结束!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

}