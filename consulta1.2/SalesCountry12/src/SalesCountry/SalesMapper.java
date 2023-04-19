package SalesCountry;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

public class SalesMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {

    IntWritable numero;
    public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {

        String valueString = value.toString();
        String[] SingleCountryData = valueString.split(",");
        if(!"Price".equals(SingleCountryData[2])){
            numero = new IntWritable(Integer.parseInt(SingleCountryData[2]));
        }else{
            numero = new IntWritable(0);
        }

        output.collect(new Text(SingleCountryData[6]), numero);
    }
}
