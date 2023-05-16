package models;

public class FormatReport {
    private String name;
    private long time;

    public FormatReport (String name, long time) {
        this.name = name;
        this.time = time;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public long getTime () {
        return time;
    }

    public void setTime (long time) {
        this.time = time;
    }

    public String space () {

        long spac = name.length();
        long total = 20;
        String sapce = "";
        for (int i = 0; i < total - spac; i++) {
            sapce += " ";
        }
        return sapce;
    }

    @Override
    public String toString () {
        return name + space() + time + "\n";
    }
}
