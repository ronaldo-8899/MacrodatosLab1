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
    private DoubleWritable amount;


    public CustomWritable() {
        city = new Text();
        amount = new DoubleWritable();


    }

    public CustomWritable(Text city, DoubleWritable amount) {
        this.city = city;
        this.amount = amount;

    }

    public Text getCity() {
        return city;
    }

    public void setCity(Text city) {
        this.city = city;
    }

    public DoubleWritable getAmount() {
        return amount;
    }

    public void setAmount(DoubleWritable amount) {
        this.amount = amount;
    }

    public void readFields(DataInput in) throws IOException {
        city.readFields(in);
        amount.readFields(in);
    }

    public void write(DataOutput out) throws IOException {
        city.write(out);
        amount.write(out);
    }

    @Override
    public String toString() {
        return city.toString() + "\t" + amount.toString();
    }
}