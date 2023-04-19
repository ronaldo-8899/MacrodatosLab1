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
    private IntWritable count;
    private DoubleWritable amount;


    public CustomWritable() {
        card = new Text();
        count = new IntWritable();
        amount = new DoubleWritable();
    }

    public CustomWritable(Text card, IntWritable count, DoubleWritable amount) {
        this.card = card;
        this.count = count;
        this.amount = amount;

    }

    public Text getCard() {
        return card;
    }

    public void setCard(Text card) {
        this.card = card;
    }

    public IntWritable getCount() {
        return count;
    }

    public void setCount(IntWritable count) {
        this.count = count;
    }

    public DoubleWritable getAmount() {
        return amount;
    }

    public void setAmount(DoubleWritable amount) {
        this.amount = amount;
    }
    
    public void readFields(DataInput in) throws IOException {
        card.readFields(in);
        count.readFields(in);
        amount.readFields(in);
    }

    public void write(DataOutput out) throws IOException {
        card.write(out);
        count.write(out);
        amount.write(out);
    }

    @Override
    public String toString() {
        return card.toString() + "\t" + count.toString() + "\t" + amount.toString();
    }
}