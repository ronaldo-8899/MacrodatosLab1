package consulta9;

import java.io.*;
import org.apache.hadoop.io.Writable;

public class LatLngWritable implements Writable {
    private double lat;
    private double lng;
    private String person;

    public LatLngWritable() {}

    public LatLngWritable(double lat, double lng, String person) {
        this.lat = lat;
        this.lng = lng;
        this.person = person;
    }

    public void write(DataOutput out) throws IOException {
        out.writeDouble(lat);
        out.writeDouble(lng);
        out.writeUTF(person);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        lat = in.readDouble();
        lng = in.readDouble();
        person = in.readUTF();
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getPerson() {
        return person;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void setPerson(String person) {
        this.person = person;
    }
    @Override
    public String toString() {
        return "(" + lat + ", " + lng + ": " + person + ")";
    }
}
