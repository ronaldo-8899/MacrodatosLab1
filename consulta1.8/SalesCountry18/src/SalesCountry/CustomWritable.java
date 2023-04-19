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
    
    private Text city;
    private IntWritable count;


    public CustomWritable() {
        city = new Text();
        count = new IntWritable();


    }

    public CustomWritable(Text city, IntWritable count) {
        this.city = city;
        this.count = count;

    }

    public Text getCity() {
        return city;
    }

    public void setCity(Text city) {
        this.city = city;
    }

    public IntWritable getCount() {
        return count;
    }

    public void setCount(IntWritable count) {
        this.count = count;
    }

    public void readFields(DataInput in) throws IOException {
        city.readFields(in);
        count.readFields(in);
    }

    public void write(DataOutput out) throws IOException {
        city.write(out);
        count.write(out);
    }

    @Override
    public String toString() {
        return city.toString() + "\t" + count.toString();
    }
}