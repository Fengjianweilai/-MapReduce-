package com.bigData.zjx;
import java.io.IOException;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class Removal {
    /**
     * Map
     */
    public static class RemovalMap extends Mapper<Object, Text, Text, Text>{
        public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {
            Text keyout = new Text();
            String line = value.toString();
            String[] words = line.split("\t");//劈分数据
            keyout.set(words[1]);
            context.write(keyout, new Text(""));//keyout为商品号，value为空字符串

        }
    }

    /**
     * Reduce
     */
    public static class RemovalReduce extends Reducer<Text, Text, Text, Text>{
        public void reduce(Text key,Iterable<Text> values, Context context)
            throws IOException, InterruptedException{
            context.write(key, new Text(""));//直接存放数据
        }
    }

    public static void main(String[] args)
        throws IOException, ClassNotFoundException, InterruptedException{
        Configuration conf = new Configuration();
        //获得一个job对象，用来完成一个mapreduce作业
        Job job = Job.getInstance(conf);
        //让程序找到主入口
        job.setJarByClass(Removal.class);
        //指定输入数据目录，指定数据计算完成后输出的目录
        FileInputFormat.addInputPath(job,new Path(args[1]));
        FileOutputFormat.setOutputPath(job,new Path(args[2]));
        //告诉调用哪个map方法和reduce方法
        job.setMapperClass(RemovalMap.class);
        job.setReducerClass(RemovalReduce.class);
        //指定map输出键值对的类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        //指定reduce输出键值对的类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        //提交job任务
        boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 : 1);
    }
}