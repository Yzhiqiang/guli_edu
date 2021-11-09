package excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.context.AnalysisContextImpl;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 18:06 2021/11/8
 * @Modified By:
 */
public class ExcelListener extends AnalysisEventListener<DemoData> {
    //一行一行读取excel内容
    @Override
    public void invoke(DemoData data, AnalysisContext analysisContext) {
        System.out.println("****"+data);
    }

    //读取表头中的内容
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头："+headMap);
    }

    //读取完成后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
