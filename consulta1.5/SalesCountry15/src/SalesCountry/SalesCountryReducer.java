package SalesCountry;

import java.io.IOException;
import java.util.*;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

public class SalesCountryReducer extends MapReduceBase implements Reducer<Text, CustomWritable, Text, CustomWritable> {

    public void reduce(Text t_key, Iterator<CustomWritable> values, OutputCollector<Text, CustomWritable> output, Reporter reporter) throws IOException {
        Text key = t_key;
        CustomWritable customWritable;
        String city = "";
        double amountMax = 0;
        double amount = 0;
        while (values.hasNext()) {
            CustomWritable value = (CustomWritable) values.next();
            Text textCity = (Text) value.getCity();
            DoubleWritable dwAmount = (DoubleWritable) value.getAmount();
            city = textCity.toString();
            amount = dwAmount.get();
            amountMax += amount;
        }
        customWritable = new CustomWritable(new Text(city),new DoubleWritable(amountMax));
        output.collect(key, customWritable);
    }
}

class SalesReducer2 extends MapReduceBase implements Reducer<Text, CustomWritable, Text, CustomWritable> {

    public void reduce(Text t_key, Iterator<CustomWritable> values, OutputCollector<Text, CustomWritable> output, Reporter reporter) throws IOException {
        Text key = t_key;
        CustomWritable customWritable;
        String city = "";
        double amountMax = 0;
        double amount = 0;
        String cityMax = "";
        while (values.hasNext()) {
            CustomWritable value = (CustomWritable) values.next();
            Text textCity = (Text) value.getCity();
            DoubleWritable dwAmount = (DoubleWritable) value.getAmount();
            city = textCity.toString();
            amount = dwAmount.get();
            if (amount > amountMax) {
                amountMax = amount;
                cityMax = city;
            }
        }
        customWritable = new CustomWritable(new Text(cityMax),new DoubleWritable(amountMax));
        output.collect(key, customWritable);
    }
}
