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
    Text card;
    IntWritable count;
    DoubleWritable amount;
    public void map(LongWritable key, Text value, OutputCollector<Text, CustomWritable> output, Reporter reporter) throws IOException {
        String valueString = value.toString();
        String[] SingleCountryData = valueString.split(",");
        if(!"Price".equals(SingleCountryData[2])){
            card = new Text(SingleCountryData[3]);
            count = new IntWritable(1);
            amount = new DoubleWritable(Double.parseDouble(SingleCountryData[2]));
            customWritable = new CustomWritable(card,count,amount);
            output.collect(new Text(SingleCountryData[7]+"\t"+SingleCountryData[3]), customWritable);
        }
        
    }
}
