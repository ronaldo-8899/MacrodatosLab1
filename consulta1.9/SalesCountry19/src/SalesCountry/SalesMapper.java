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
    DoubleWritable longitud;
    DoubleWritable latitud;
    DoubleWritable distance;
    public void map(LongWritable key, Text value, OutputCollector<Text, CustomWritable> output, Reporter reporter) throws IOException {
        String valueString = value.toString();
        String[] SingleCountryData = valueString.split(",");
        if(!"Price".equals(SingleCountryData[2])){
            name = new Text(SingleCountryData[4]);
            latitud = new DoubleWritable(Double.parseDouble(SingleCountryData[10]));
            longitud = new DoubleWritable(Double.parseDouble(SingleCountryData[11]));
            distance = new DoubleWritable(0);
            customWritable = new CustomWritable(name,latitud,longitud,distance);
            output.collect(new Text(SingleCountryData[7]+"\t"+SingleCountryData[4]), customWritable);
        }
        
    }
}

class SalesMapper2 extends MapReduceBase implements Mapper<LongWritable, Text, Text, CustomWritable> {
    CustomWritable customWritable;
    Text name;
    DoubleWritable longitud;
    DoubleWritable latitud;
    DoubleWritable distance;

    public void map(LongWritable key, Text value, OutputCollector<Text, CustomWritable> output, Reporter reporter) throws IOException {

        String[] rowData = value.toString().split("\t");
        name = new Text(rowData[1]);
        latitud = new DoubleWritable(Double.parseDouble(rowData[3]));
        longitud = new DoubleWritable(Double.parseDouble(rowData[4]));
        distance = new DoubleWritable(Double.parseDouble(rowData[5]));
        customWritable = new CustomWritable(name,latitud,longitud,distance);
        output.collect(new Text(rowData[0]), customWritable);
    }
}
