package SalesCountry;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Date;
import org.apache.hadoop.io.Text;

public class CustomWritable implements Writable {

    private Text creation;

    public CustomWritable() {
        creation = new Text();
    }

    public CustomWritable(Text creation) {
        this.creation = creation;
    }

    public Text getCreation() {
        return creation;
    }

    public void setCreation(Text creation) {
        this.creation = creation;
    }

    public void readFields(DataInput in) throws IOException {
        creation.readFields(in);
    }

    public void write(DataOutput out) throws IOException {
        creation.write(out);
    }

    @Override
    public String toString() {
        return creation.toString();
    }
}