package excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 13:12 2021/11/8
 * @Modified By:
 */
public class TestEasyExcel {
    public static void main(String[] args) {
//        //实现excel写操作
//        //1.设置文件夹的地址和文件名称
//        String filename = "e:\\write.xlsx";
//
//        //2.调用easyexcel里面的方法实现写操作
//        EasyExcel.write(filename, DemoData.class).sheet(1,"学生列表").doWrite(getData());

        //实现excel读操作
        String filename = "e:\\write.xlsx";
        EasyExcel.read(filename, DemoData.class, new ExcelListener()).sheet().doRead();

    }

    private static List<DemoData> getData() {
        List<DemoData> list = new ArrayList<>();
        for(int i = 0; i < 10; i++)
        {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("lucy"+i);
            list.add(data);
        }
        return list;
    }


}
