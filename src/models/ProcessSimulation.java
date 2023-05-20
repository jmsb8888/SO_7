package models;

/**
 *
 * @author Andrés Ramírez - José Salamanca
 */
public class ProcessSimulation implements Comparable<ProcessSimulation> {
    
    private String procesName;
    private long time;
    private long size;
    
    public ProcessSimulation (String procesName, long time, long size) {
        this.procesName = procesName;
        this.time = time;
        this.size = size;
    }

    public String getProcesName () {
        return procesName;
    }

    public void setProcesName (String procesName) {
        this.procesName = procesName;
    }

    public long getTime () {
        return time;
    }

    public void setTime (long time) {
        this.time = time;
    }

    public long getSize () {
        return size;
    }

    public void setSize (int size) {
        this.size = size;
    }

   /* @Override
    public String toString() {
        return "[Nombre: " + this.procesName + " Tiempo: " + this.time + " Tamaño: " + this.size + "]";
    }*/
   public String space(){

       int spac= procesName.length();
       int total= 20;
       if(spac==2)total=total+1;
       String sapce="";
       for (int i = 0; i < total-spac; i++) {
           sapce +=" ";
       }
       return sapce;
   }
    public String space2(){

        int spac= String.valueOf(time).length();
        int total= 20;
        String sapce="";
        for (int i = 0; i < total-spac; i++) {
            sapce +=" ";
        }
        return sapce;
    }
    @Override
    public String toString() {
        return  procesName  + space() + time +space2()+String.valueOf(size)+ "\n";
    }

    @Override
    public int compareTo(ProcessSimulation o) {
         if (this.time < o.getTime()) {
            return -1;
        } else if (this.time > o.getTime()) {
            return 1;
        } else {
            return 0;
        }
    }
}
