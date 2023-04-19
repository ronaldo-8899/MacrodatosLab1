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
        String card = "";
        int countMax = 0;
        int count = 0;
        while (values.hasNext()) {
            CustomWritable value = (CustomWritable) values.next();
            Text textCard = (Text) value.getCard();
            IntWritable iwCount = (IntWritable) value.getCount();
            card = textCard.toString();
            count = iwCount.get();
            countMax += count;
        }
        customWritable = new CustomWritable(new Text(card),new IntWritable(countMax));
        output.collect(key, customWritable);
    }
}

class SalesReducer2 extends MapReduceBase implements Reducer<Text, CustomWritable, Text, CustomWritable> {

    public void reduce(Text t_key, Iterator<CustomWritable> values, OutputCollector<Text, CustomWritable> output, Reporter reporter) throws IOException {
        Text key = t_key;
        CustomWritable customWritable;
        String card = "";
        String cardMax = "";
        String cardMin = "";
        String cardRe = "";
        String cardAux = "";
        int countMax = 0;
        int count = 0;
        int countRe = 0;
        int countMin = 999999;
        int countAux = 0;
        while (values.hasNext()) {
            CustomWritable value = (CustomWritable) values.next();
            Text textCity = (Text) value.getCard();
            IntWritable iwCount = (IntWritable) value.getCount();
            card = textCity.toString();
            count = iwCount.get();
            cardAux = card;
            countAux = count;
            if (count > countMax) {
                countMax = count;
                cardMax = card;
            }
         
            if (count < countMin) {
                countMin = count;
                cardMin = card;
            }
            
            if(count > countMin && count < countMax){
                countRe = count;
                cardRe = card;
            }
        }
        if(cardRe == "" && countRe == 0){
            cardRe = cardAux;
            countRe = countAux;
        }
        customWritable = new CustomWritable(new Text(cardRe),new IntWritable(countRe));
        output.collect(key, customWritable);
    }
}
