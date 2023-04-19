package SalesCountry;

import java.io.IOException;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

public class SalesMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, CustomWritable> {
    CustomWritable customWritable;
    Text card;
    DoubleWritable price;
    public void map(LongWritable key, Text value, OutputCollector<Text, CustomWritable> output, Reporter reporter) throws IOException {
        String valueString = value.toString();
        String[] SingleCountryData = valueString.split(",");
        if(!"Price".equals(SingleCountryData[2])){
            card = new Text(SingleCountryData[3]);
            price = new DoubleWritable(Double.parseDouble(SingleCountryData[2]));
            customWritable = new CustomWritable(card,price);
            output.collect(new Text(SingleCountryData[7]+"\t"+SingleCountryData[3]), customWritable);
        }
        
    }
}

class SalesMapper2 extends MapReduceBase implements Mapper<LongWritable, Text, Text, CustomWritable> {
    CustomWritable customWritable;
    Text card;
    DoubleWritable price;

    public void map(LongWritable key, Text value, OutputCollector<Text, CustomWritable> output, Reporter reporter) throws IOException {

        String[] rowData = value.toString().split("\t");
        card = new Text(rowData[1]);
        price = new DoubleWritable(Double.parseDouble(rowData[3]));
        customWritable = new CustomWritable(card,price);
        output.collect(new Text(rowData[0]), customWritable);
    }
}
