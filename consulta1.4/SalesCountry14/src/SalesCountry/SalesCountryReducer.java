package SalesCountry;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

class SalesCountryReducer extends MapReduceBase implements Reducer<Text, Text, Text, Text> {

    public void reduce(Text t_key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
        Text key = t_key;
        int max = 0;
        
        while (values.hasNext()) {
            // replace type of value with the actual type of our value
            String dateString = "";
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy HH:mm");
            Date fecha = null;
            int data_number;

            try {
                fecha = formatter.parse(dateString);
                data_number = (int) fecha.getTime();
            } catch (ParseException e) {
                data_number = -1;
            }

            if (data_number > max) {
                max = data_number;
            }
            
        }
        Date date = new Date(max);

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");

        String dateString = formatter.format(date);
    
        output.collect(key, new Text(dateString));
    }
}


