package SalesCountry;

import java.io.IOException;
import java.util.*;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

public class SalesCountryReducer extends MapReduceBase implements Reducer<Text, CustomWritable, Text, CustomWritable> {

    public void reduce(Text t_key, Iterator<CustomWritable> values, OutputCollector<Text, CustomWritable> output, Reporter reporter) throws IOException {
        Text key = t_key;
        CustomWritable customWritable;
        String city = "";
        int countMax = 0;
        int count = 0;
        while (values.hasNext()) {
            CustomWritable value = (CustomWritable) values.next();
            Text textCity = (Text) value.getCity();
            IntWritable iwCount = (IntWritable) value.getCount();
            city = textCity.toString();
            count = iwCount.get();
            countMax += count;
        }
        customWritable = new CustomWritable(new Text(city),new IntWritable(countMax));
        output.collect(key, customWritable);
    }
}

class SalesReducer2 extends MapReduceBase implements Reducer<Text, CustomWritable, Text, CustomWritable> {

    public void reduce(Text t_key, Iterator<CustomWritable> values, OutputCollector<Text, CustomWritable> output, Reporter reporter) throws IOException {
        Text key = t_key;
        CustomWritable customWritable;
        String city = "";
        String cityMax = "";
        int countMax = 0;
        int count = 0;
        while (values.hasNext()) {
            CustomWritable value = (CustomWritable) values.next();
            Text textCity = (Text) value.getCity();
            IntWritable iwCount = (IntWritable) value.getCount();
            city = textCity.toString();
            count = iwCount.get();
            if (count > countMax) {
                countMax = count;
                cityMax = city;
            }
        }
        customWritable = new CustomWritable(new Text(cityMax),new IntWritable(countMax));
        output.collect(key, customWritable);
    }
}
