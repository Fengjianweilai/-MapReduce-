package com.bigData.zjx.removal;
import com.bigData.zjx.utils.DateUtils;
import com.bigData.zjx.utils.GenerateData;
import com.bigData.zjx.utils.IoUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateGoodsData {
    public static void main(String[] args){
        createRemovalData();
    }
    private static void createRemovalData(){
        String title = "用户id    商品id    日期";
        IoUtils.WriteStringToFile("E:\\code\\java_project\\BigData_zjx\\data.txt",getRemovalData(),title);
    }
    private static List<String> getRemovalData(){
        List<String> datas = new ArrayList<String>();
        for(int i=0;i<100000;i++){
            String userId = Long.valueOf(GenerateData.randomNumber(10000,19999)).toString();
            String goodsId = Long.valueOf(GenerateData.randomNumber(10000,19999)).toString();
            Date date = GenerateData.randomData("2022-12-01 00:00:00","2022-12-12 23:59:59");
            String total = userId + "\t" +goodsId + "\t" + DateUtils.dateToString(date, DateUtils.DateFormat.ALL_TIME) + "\n";
            datas.add(total);
        }
        return datas;
    }
}
