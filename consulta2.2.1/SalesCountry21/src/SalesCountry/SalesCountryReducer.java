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
        
        while (values.hasNext()) {
            CustomWritable value = (CustomWritable) values.next();
            Text textCard = (Text) value.getCard();
            DoubleWritable dwPrice = (DoubleWritable) value.getPrice();
            card = textCard.toString();
            price = dwPrice.get();
            amount += price;
        }
        customWritable = new CustomWritable(new Text(card), new DoubleWritable(amount));
        output.collect(key, customWritable);
    }
}

class SalesReducer2 extends MapReduceBase implements Reducer<Text, CustomWritable, Text, Text> {
    public void reduce(Text t_key, Iterator<CustomWritable> values, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
        Text key = t_key;
        CustomWritable customWritable;
        String card = "";
        String cardAux = "";
        double price = 0;
        double amount = 0;
        Boolean aa = false;
        while (values.hasNext()) {
            CustomWritable value = (CustomWritable) values.next();
            Text textCard = (Text) value.getCard();
            DoubleWritable dwPrice = (DoubleWritable) value.getPrice();
            card = textCard.toString();
            price = dwPrice.get();
            if (card == "Visa") {
                amount = price;
                cardAux = card;

            }
        }
        output.collect(key, new Text(card+"\t"+price));
    }
}
