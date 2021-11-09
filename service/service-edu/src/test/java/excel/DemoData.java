package excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 13:10 2021/11/8
 * @Modified By:
 */
@Data
public class DemoData {
    //设置excel表头名称
    @ExcelProperty(value = "学生编号", index = 0)
    private Integer sno;

    //
    @ExcelProperty(value = "学生姓名", index = 1)
    private String sname;
}
