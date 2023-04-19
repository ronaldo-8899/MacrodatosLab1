package SalesCountry;

import java.io.IOException;
import java.util.*;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

public class SalesCountryReducer extends MapReduceBase implements Reducer<Text, CustomWritable, Text, CustomWritable> {

    public void reduce(Text t_key, Iterator<CustomWritable> values, OutputCollector<Text, CustomWritable> output, Reporter reporter) throws IOException {
        Text key = t_key;
        CustomWritable customWritable;
        String card = "";
        double price = 0;
        double amount = 0;
        int count = 0;
        String aux = "";
        while (values.hasNext()) {
            CustomWritable value = (CustomWritable) values.next();
            Text textCard = (Text) value.getCard();
            DoubleWritable dwPrice = (DoubleWritable) value.getAmount();
            card = textCard.toString();
            price = dwPrice.get();
            amount += price;
            count +=1;
        }
        customWritable = new CustomWritable(new Text(card), new IntWritable(count),new DoubleWritable(amount));
        aux = key.toString();
        String[] auxi = aux.split("\t");
        
        output.collect(new Text(auxi[0]), customWritable);
    }
}
