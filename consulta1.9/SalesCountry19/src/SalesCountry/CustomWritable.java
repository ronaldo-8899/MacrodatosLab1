package SalesCountry;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Date;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;

public class CustomWritable implements Writable {
    
    private Text name;
    private DoubleWritable latitud;
    private DoubleWritable longitud;
    private DoubleWritable distance;


    public CustomWritable() {
        name = new Text();
        longitud = new DoubleWritable();
        latitud = new DoubleWritable();
        distance = new DoubleWritable();
    }

    public CustomWritable(Text name, DoubleWritable latitud, DoubleWritable longitud, DoubleWritable distance) {
        this.name = name;
        this.latitud = latitud;
        this.longitud = longitud;
        this.distance = distance;

    }

    public Text getName() {
        return name;
    }

    public void setName(Text name) {
        this.name = name;
    }

    public DoubleWritable getLatitud() {
        return latitud;
    }

    public void setLatitud(DoubleWritable latitud) {
        this.latitud = latitud;
    }

    public DoubleWritable getLongitud() {
        return longitud;
    }

    public void setLongitud(DoubleWritable longitud) {
        this.longitud = longitud;
    }

    public DoubleWritable getDistance() {
        return distance;
    }

    public void setDistance(DoubleWritable distance) {
        this.distance = distance;
    }
    
    public void readFields(DataInput in) throws IOException {
        name.readFields(in);
        latitud.readFields(in);
        longitud.readFields(in);
        distance.readFields(in);
    }

    public void write(DataOutput out) throws IOException {
        name.write(out);
        latitud.write(out);
        longitud.write(out);
        distance.write(out);
    }

    @Override
    public String toString() {
        return name.toString() + "\t" + latitud.toString() + "\t" + longitud.toString() + "\t" + distance.toString();
    }
}