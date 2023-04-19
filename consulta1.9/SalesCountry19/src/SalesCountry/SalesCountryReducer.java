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
        String name = "";
        double longitud = 0;
        double latitud = 0;
        double distance = 0;
        
        while (values.hasNext()) {
            CustomWritable value = (CustomWritable) values.next();
            Text textName = (Text) value.getName();
            DoubleWritable dwLongitud = (DoubleWritable) value.getLongitud();
            DoubleWritable dwLatitud = (DoubleWritable) value.getLatitud();
            DoubleWritable dwDistance = (DoubleWritable) value.getDistance();
            name = textName.toString();
            longitud = dwLongitud.get();
            latitud = dwLatitud.get();
            distance = dwDistance.get();
            distance = distanciaAlSur(latitud,longitud);
        }
        customWritable = new CustomWritable(new Text(name), new DoubleWritable(latitud), new DoubleWritable(longitud), new DoubleWritable(distance));
        output.collect(key, customWritable);
    }
    public static double distanciaAlSur(double latitud, double longitud) {
        double latitudSur = -90.0; // Latitud del polo sur
        double longitudSur = 0.0;  // Longitud del polo sur
        int radioTierra = 6371;    // Radio de la Tierra en kilómetros
        double dLatitud = Math.toRadians(latitud - latitudSur);
        double dLongitud = Math.toRadians(longitud - longitudSur);
        double a = Math.sin(dLatitud/2) * Math.sin(dLatitud/2) + 
            Math.cos(Math.toRadians(latitud)) * Math.cos(Math.toRadians(latitudSur)) *
            Math.sin(dLongitud/2) * Math.sin(dLongitud/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double distancia = radioTierra * c;
        return distancia;
    }
}

class SalesReducer2 extends MapReduceBase implements Reducer<Text, CustomWritable, Text, Text> {
    public void reduce(Text t_key, Iterator<CustomWritable> values, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
        
        
        Text key = t_key;
        CustomWritable customWritable;
        String name = "";
        String nameMin = "";
        double latitud = 0;
        double latitudMin = 0;
        double longitud = 0;
        double LongitudMin = 0;
        double distance = 0;
        double distanceMin = 99999;

        while (values.hasNext()) {
            CustomWritable value = (CustomWritable) values.next();
            Text textName = (Text) value.getName();
            DoubleWritable dwLatitud = (DoubleWritable) value.getLatitud();
            DoubleWritable dwLongitud = (DoubleWritable) value.getLongitud();
            DoubleWritable dwDistance = (DoubleWritable) value.getDistance();
            name = textName.toString();
            latitud = dwLatitud.get();
            longitud = dwLongitud.get();
            distance = dwDistance.get();
            if (distance < distanceMin) {
                nameMin = name;
                latitudMin = latitud;
                LongitudMin = longitud;
                distanceMin = distance;
            }
        }
        customWritable = new CustomWritable(new Text(nameMin), new DoubleWritable(latitudMin), new DoubleWritable(LongitudMin), new DoubleWritable(distanceMin));
        //output.collect(key, customWritable);
        output.collect(key, new Text(nameMin));
    }
}


