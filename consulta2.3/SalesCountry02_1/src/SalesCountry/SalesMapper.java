package SalesCountry;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.*;

public class SalesMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, CustomWritable> {
    CustomWritable customWritable;
    Text textCreacion;
    
    public void map(LongWritable key, Text value, OutputCollector<Text, CustomWritable> output, Reporter reporter) throws IOException {
        String valueString = value.toString();
        String[] SingleCountryData = valueString.split(",");
        if(!"Account_Created".equals(SingleCountryData[8]) && "United State".equals(SingleCountryData[7])){
            textCreacion = new Text(SingleCountryData[8]);
            customWritable = new CustomWritable(textCreacion);
            output.collect(new Text(SingleCountryData[7]), customWritable);
        }
    }
}
