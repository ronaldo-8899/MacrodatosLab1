package SalesCountry;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

public class SalesMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, CustomWritable> {
    CustomWritable customWritable;
    Text city;
    IntWritable count;

    public void map(LongWritable key, Text value, OutputCollector<Text, CustomWritable> output, Reporter reporter) throws IOException {
        String valueString = value.toString();
        String[] SingleCountryData = valueString.split(",");
        if(!"Price".equals(SingleCountryData[2])){
            city = new Text(SingleCountryData[5]);
            count = new IntWritable(1);
            customWritable = new CustomWritable(city,count);
            output.collect(new Text(SingleCountryData[7]+"\t"+SingleCountryData[5]), customWritable);
        }
    }
}

class SalesMapper2 extends MapReduceBase implements Mapper<LongWritable, Text, Text, CustomWritable> {
    CustomWritable customWritable;
    Text city;
    IntWritable count;

    public void map(LongWritable key, Text value, OutputCollector<Text, CustomWritable> output, Reporter reporter) throws IOException {

        String[] rowData = value.toString().split("\t");
        city = new Text(rowData[1]);
        count = new IntWritable(Integer.parseInt(rowData[3]));
        customWritable = new CustomWritable(city,count);
        output.collect(new Text(rowData[0]),customWritable);
    }
}
