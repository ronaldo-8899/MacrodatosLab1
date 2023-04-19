package SalesCountry;
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;
public class SalesMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
    //private final static IntWritable one = new IntWritable(1);
    IntWritable numero;
    Text txttotal = new Text("United States  NY Visa");
    public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
        String valueString = value.toString();
        String[] SingleCountryData = valueString.split(",");
        if("United States".equals(SingleCountryData[7]) && "NY".equals(SingleCountryData[6]) && "Visa".equals(SingleCountryData[3])){
            numero = new IntWritable(Integer.parseInt(SingleCountryData[2]));
        }else{
            numero = new IntWritable(0);
        }
        output.collect(txttotal, numero);
    }
}
