package SalesCountry;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

public class SalesCountryDriver {

    public static void main(String[] args) {
        JobClient my_client = new JobClient();
        JobConf job_conf1 = new JobConf(SalesCountryDriver.class);
        job_conf1.setJobName("Sale1");
        job_conf1.setOutputKeyClass(Text.class);
        job_conf1.setOutputValueClass(SalesCountry.CustomWritable.class);
        job_conf1.setMapperClass(SalesCountry.SalesMapper.class);
        job_conf1.setReducerClass(SalesCountry.SalesCountryReducer.class);
        job_conf1.setInputFormat(TextInputFormat.class);
        job_conf1.setOutputFormat(TextOutputFormat.class);

        FileInputFormat.setInputPaths(job_conf1, new Path(args[0]));
        FileOutputFormat.setOutputPath(job_conf1, new Path(args[1]));
        my_client.setConf(job_conf1);
        //#########################################


        try {
            JobClient.runJob(job_conf1);

        } catch (Exception e) {
            e.printStackTrace();  
        }

    }
}
