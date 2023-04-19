package consulta9;

import java.io.IOException;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

public class SalesMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, LatLngWritable> {

    public void map(LongWritable key, Text value, OutputCollector<Text, LatLngWritable> output, Reporter reporter) throws IOException {
        String line = value.toString();
        String[] fields = line.split(",");
        String person = fields[4];
        String country = fields[7];
        double lat = 360.0;
        double lng = 360.0;
        if (!fields[10].equals("Latitude")) {
              lat = Double.parseDouble(fields[10]);
        }
        if (!fields[11].equals("Longitude")) {
              lng = Double.parseDouble(fields[11]);
        }
        if(!"Country".equals(country)){
          LatLngWritable latLngWritable = new LatLngWritable(lat, lng, person);

          output.collect(new Text(country), latLngWritable);
        }
    }
}
