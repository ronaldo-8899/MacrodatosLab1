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
    private DoubleWritable amount;


    public CustomWritable() {
        name = new Text();
        amount = new DoubleWritable();


    }

    public CustomWritable(Text name, DoubleWritable amount) {
        this.name = name;
        this.amount = amount;

    }

    public Text getName() {
        return name;
    }

    public void setName(Text name) {
        this.name = name;
    }

    public DoubleWritable getAmount() {
        return amount;
    }

    public void setAmount(DoubleWritable amount) {
        this.amount = amount;
    }

    public void readFields(DataInput in) throws IOException {
        name.readFields(in);
        amount.readFields(in);
    }

    public void write(DataOutput out) throws IOException {
        name.write(out);
        amount.write(out);
    }

    @Override
    public String toString() {
        return name.toString() + "\t" + amount.toString();
    }
}