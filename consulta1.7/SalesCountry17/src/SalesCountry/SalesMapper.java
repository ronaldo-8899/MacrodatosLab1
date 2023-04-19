package SalesCountry;

import java.io.IOException;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

public class SalesMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, CustomWritable> {
    CustomWritable customWritable;
    Text name;
    DoubleWritable amount;
    public void map(LongWritable key, Text value, OutputCollector<Text, CustomWritable> output, Reporter reporter) throws IOException {
        String valueString = value.toString();
        String[] SingleCountryData = valueString.split(",");
        if(!"Price".equals(SingleCountryData[2])){
            name = new Text(SingleCountryData[4]);
            amount = new DoubleWritable(Double.parseDouble(SingleCountryData[2]));
            customWritable = new CustomWritable(name,amount);
            output.collect(new Text(SingleCountryData[7]+"\t"+SingleCountryData[4]), customWritable);
        }
        
    }
}

class SalesMapper2 extends MapReduceBase implements Mapper<LongWritable, Text, Text, CustomWritable> {

    public void map(LongWritable key, Text value, OutputCollector<Text, CustomWritable> output, Reporter reporter) throws IOException {
        CustomWritable customWritable;
        Text name;
        DoubleWritable amount;

        String[] rowData = value.toString().split("\t");
        name = new Text(rowData[1]);
        amount = new DoubleWritable(Double.parseDouble(rowData[3]));
        customWritable = new CustomWritable(name,amount);
        output.collect(new Text(rowData[0]), customWritable);
    }
}
