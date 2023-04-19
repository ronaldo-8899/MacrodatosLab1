package SalesCountry;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;  
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

class SalesCountryReducer extends MapReduceBase implements Reducer<Text, CustomWritable, Text, CustomWritable> {

    public void reduce(Text t_key, Iterator<CustomWritable> values, OutputCollector<Text, CustomWritable> output, Reporter reporter) throws IOException {
        Text key = t_key;
        long max = 0;
        String creationMax = "";
        String creation = "";
        
        while (values.hasNext()) {
            CustomWritable value = (CustomWritable) values.next();
            Text textCreation = (Text) value.getCreation();
            String formatter = "MM/dd/yy HH:mm";
            long data_number = 0;
            creation = textCreation.toString();
               
            try {
                Date fecha = new SimpleDateFormat(formatter).parse(creation);
                data_number = fecha.getTime();
                if (data_number > max) {
                    max = data_number;
                    creationMax = creation;
                }
            } catch (ParseException ex) {
                Logger.getLogger(SalesCountryReducer.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
    
        output.collect(key, new CustomWritable(new Text(creationMax)));
    }
}


