package consulta9;

import java.io.IOException;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

public class SalesMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, LatLngWritable> {

    public void map(LongWritable key, Text value, OutputCollector<Text, LatLngWritable> output, Reporter reporter) throws IOException {
        String line = value.toString();
        String[] fields = line.split(",");
        String person = fields[5];
        String country = fields[8];
        double lat = 360.0;
        double lng = 360.0;
        if (!fields[11].equals("Latitude")) {
              lat = Double.parseDouble(fields[11]);
        }
        if (!fields[12].equals("Longitude")) {
              lng = Double.parseDouble(fields[12]);
        }
        if(!"Country".equals(country)){
          LatLngWritable latLngWritable = new LatLngWritable(lat, lng, person);

          output.collect(new Text(country), latLngWritable);
        }
    }
}
