package SalesCountry;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

public class SalesMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, CustomWritable> {
    CustomWritable customWritable;
    Text city ;
    DoubleWritable amount;
    public void map(LongWritable key, Text value, OutputCollector<Text, CustomWritable> output, Reporter reporter) throws IOException {
        String valueString = value.toString();
        String[] SingleCountryData = valueString.split(",");
        if(!"Price".equals(SingleCountryData[2])){
            city = new Text(SingleCountryData[5]);
            amount = new DoubleWritable(Integer.parseInt(SingleCountryData[2]));
            customWritable = new CustomWritable(city,amount);
            output.collect(new Text(SingleCountryData[7]+"\t"+SingleCountryData[5]), customWritable);
        }    
    }
}

class SalesMapper2 extends MapReduceBase implements Mapper<LongWritable, Text, Text, CustomWritable> {

    public void map(LongWritable key, Text value, OutputCollector<Text, CustomWritable> output, Reporter reporter) throws IOException {
        CustomWritable customWritable;
        Text city = new Text("");
        DoubleWritable amount = new DoubleWritable(0);

        String[] rowData = value.toString().split("\t");
        city = new Text(rowData[1]);
        amount = new DoubleWritable(Double.parseDouble(rowData[3]));
        customWritable = new CustomWritable(city,amount);
        output.collect(new Text(rowData[0]), customWritable);
    }
}
