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
    
    private Text card;
    private DoubleWritable price;


    public CustomWritable() {
        card = new Text();
        price = new DoubleWritable();
    }

    public CustomWritable(Text card, DoubleWritable price) {
        this.card = card;
        this.price = price;

    }

    public Text getCard() {
        return card;
    }

    public void setCard(Text card) {
        this.card = card;
    }

    public DoubleWritable getPrice() {
        return price;
    }

    public void setPrice(DoubleWritable price) {
        this.price = price;
    }
    
    public void readFields(DataInput in) throws IOException {
        card.readFields(in);
        price.readFields(in);
    }

    public void write(DataOutput out) throws IOException {
        card.write(out);
        price.write(out);
    }

    @Override
    public String toString() {
        return card.toString() + "\t" + price.toString();
    }
}