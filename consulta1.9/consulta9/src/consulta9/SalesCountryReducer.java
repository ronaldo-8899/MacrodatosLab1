package consulta9;

import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

public class SalesCountryReducer extends MapReduceBase implements Reducer<Text, LatLngWritable, Text, Text> {
   
    public void reduce(Text key, Iterator<LatLngWritable> values, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {

        Double latmin = 360.0;
        Double lat=0.0, lng=0.0;
        String nombre=" ";
        
        while (values.hasNext()) {
            LatLngWritable current = values.next();
            if (current.getLat() < latmin) {
                lat = current.getLat();
                lng = current.getLng();
                nombre = current.getPerson();
                latmin = lat;
            }
        } 
        LatLngWritable aea = new LatLngWritable(lat,lng,nombre);
        output.collect(key, new Text(aea.toString()));
      
    }
}
