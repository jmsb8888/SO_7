package models;

public class FormatReportPartitions {
    private String name;
    private long sizePartition;
    private long lowerLimit;
    private long highLimit;
    private String separator;

    public FormatReportPartitions(String name, long sizePartition, long lowerLimit, long highLimit) {
        this.name = name;
        this.sizePartition = sizePartition;
        this.lowerLimit = lowerLimit;
        this.highLimit = highLimit;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public FormatReportPartitions() {
        separator="-----------------------------------------------------------------------------------------------";
    }

    public long getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(long lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public long getHighLimit() {
        return highLimit;
    }

    public void setHighLimit(long highLimit) {
        this.highLimit = highLimit;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public long getSizePartition() {
        return sizePartition;
    }

    public void setSizePartition(long sizePartition) {
        this.sizePartition = sizePartition;
    }

    public String space () {

        long spac = name.length();
        long total = 30;
        String sapce = "";
        System.out.println(name+ " "+(total-spac));
        for (int i = 0; i < total - spac; i++) {
            sapce += " ";
        }
        return sapce;
    }
    public String space2(long val){

        long spac= String.valueOf(val).length();
        long total =0;
        if(spac>2){
            total= 29;
        }else{
             total= 30;
        }
       // System.out.println(val+ " "+String.valueOf(val).length());

        String sapce="";
         System.out.println(val+ " "+(total-spac));
        for (int i = 0; i < total-spac; i++) {
            sapce +=" ";
        }
        return sapce;
    }
    @Override
    public String toString () {
        return name + space() + sizePartition + space2(sizePartition)+ lowerLimit+ space2(lowerLimit)+ highLimit+ "\n";
    }
}
