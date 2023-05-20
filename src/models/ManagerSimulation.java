package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ManagerSimulation {
    private ArrayList<Partition> partitions;
    private ArrayList<Partition> auxPartitions;
    private ArrayList<ProcessSimulation> proccessSimulation;
    private ArrayList<ProcessSimulation> auxProccessSimulation;
    private ArrayList<Reports> reports;
    private ArrayList<FormatReportPartitions> reportPartition;
    private ArrayList<FormatReportPartitions> reportCondensacion;
    private ArrayList<FormatReportPartitions> reportpROCESOSCondensacion;
    private ArrayList<FormatReport> reportEndPartitions;
    private ArrayList<FormatReport> reportSalidaGeneral;
    private ArrayList<FormatReportSalidaPorPartition> salidaParticular;
    private int simulatorTime;

    public ManagerSimulation(ArrayList<ProcessSimulation> proccessSimulation, int simulatorTime) {
        this.proccessSimulation = proccessSimulation;
        this.simulatorTime = simulatorTime;
        partitions = new ArrayList<>();
        reports= new ArrayList<>();
        reportPartition= new ArrayList<>();
        reportCondensacion= new ArrayList<>();
        reportEndPartitions= new ArrayList<>();
        reportSalidaGeneral= new ArrayList<>();
        salidaParticular= new ArrayList<>();
        auxProccessSimulation= new ArrayList<>();
        auxPartitions= new ArrayList<>();
        reportpROCESOSCondensacion= new ArrayList<>();
    }
    public String getReportPartition() {
        String report = "";
        for (FormatReportPartitions frp : reportPartition) {
            if(frp.getName()!=null){
                report += frp.toString();
            }else{
                report+=frp.getSeparator()+"\n";
            }
        }
        return report;
    }
    public String getReportTerminacion() {
        String report = "";
        for (FormatReport frp : reportEndPartitions) {
                report += frp.getName()+"\n";
        }
        return report;
    }
    public String getReportTerminacionOrdenando() {
        String report = "";
        Collections.sort(auxPartitions);
        for (Partition frp : auxPartitions) {
            report += frp.getName()+"\n";
        }
        return report;
    }
    public String getReportSalidaGeneral() {
        String report = "";
        for (FormatReport frp : reportSalidaGeneral) {
            report += frp.getName()+"\n";
        }
        return report;
    }
    public String getProccessAdd() {
        String report = "";
        for (ProcessSimulation frp : proccessSimulation) {
            report += frp.toString();
        }
        return report;
    }
    public String getReportSalidaGeneralProcesos() {
        String report = "";
        Collections.sort(auxProccessSimulation);
       // System.out.println("csds"+ auxProccessSimulation.get(0).getProcesName()+" "+ auxProccessSimulation.get(0).getTime());
        for (ProcessSimulation p:auxProccessSimulation) {
            report+= p.getProcesName()+"\n";
        }

        return report;
    }

    public String getReportCondensacion() {
        String report = "";
        for (FormatReportPartitions frp : reportCondensacion) {
            if(frp.getName()!=null){
                report += frp.toString();
            }else{
                report+=frp.getSeparator()+"\n";
            }

        }
        return report;
    }
    public String getReportSalidaParticular() {
        String report = "";
        for (FormatReportSalidaPorPartition frp : salidaParticular) {
            report += "PARTICION: "+frp.getNamePartition()+"\n";
            for (FormatReport d: frp.getProcessEnd()) {
                report+= "Nombre"+"\n";
                report+= d.getName() +"\n\n";
            }
        }
        return report;
    }
public void copy(){
    for (ProcessSimulation p : proccessSimulation) {
        ProcessSimulation copy = new ProcessSimulation(p.getProcesName(), p.getTime(), p.getSize());
        auxProccessSimulation.add(copy);
    }
}
    public void assignPartitions(){
        int count=1;
        long sizeP=0l;
        for (ProcessSimulation ps: proccessSimulation) {
            long aux =sizeP;
            sizeP+= ps.getSize();
            Partition partition= new Partition("part"+count+ "/"+ ps.getProcesName(), ps.getSize(), ps, aux, sizeP);
            this.partitions.add(partition);
            count++;
        }
    }
    public void assignPartitionsAux(){
        int count=1;
        long sizeP=0l;
        for (ProcessSimulation pso: proccessSimulation) {
            long aux =sizeP;
            sizeP+= pso.getSize();
            ProcessSimulation ps= new ProcessSimulation(pso.getProcesName(), pso.getTime(), pso.getSize());
            Partition partition= new Partition("part"+count+ "/"+ ps.getProcesName(), ps.getSize(), ps, aux, sizeP);
            this.auxPartitions.add(partition);
            count++;
        }
    }
    public void reportPartitions(){
        for (Partition ps: partitions) {
            FormatReportPartitions frp= new FormatReportPartitions(ps.getName(), ps.getPartitionSize(), ps.getLowerLimit(), ps.getHighLimit());
            reportPartition.add(frp);
        }reportPartition.add(new FormatReportPartitions());
    }

    public void assignArrayReports(){
        for (Partition p: partitions) {
            Reports r= new Reports(p.getName(), new ArrayList<FormatReport>(), new ArrayList<FormatReport> (), new ArrayList<FormatReport> (), new ArrayList<FormatReport> (), new ArrayList<FormatReport>());
            reports.add(r);
        }
    }
    public String startSimulation () {
        this.copy();
        String auxreport= this.getProccessAdd();
        this.assignPartitions();
        this.assignPartitionsAux();
        this.assignArrayReports();
        this.reportPartitions();
        String result ="";
        int count=1;
        while (partitions.size() > 1) {
           prosesar();

            boolean s= false;
            boolean f= false;
           // Collections.sort(partitions);
            this.recolectorDeBasura();
            for (int i = 0; i < partitions.size(); i++) {

                Partition particionActual = partitions.get(i);
                Partition particionAnterior = (i > 0) ? partitions.get(i - 1) : null;
                if (particionActual.getProcessSimulations().getTime()<=0){
                    //this.reportPartitions();
                    if(particionAnterior != null && particionAnterior.getProcessSimulations().getTime()<=0){
                        this.reportPartitions();
                        String nuevoNombre =  "hueco"+count/*+ particionAnterior.getLowerLimit() +" - "+ particionActual.getHighLimit()+")";// particionAnterior.getName() + " - " + particionActual.getName();*/;

                        long nuevoTamanio = particionAnterior.getPartitionSize() + particionActual.getPartitionSize();
                        Partition nuevaParticion = new Partition(nuevoNombre, nuevoTamanio,  new ProcessSimulation(nuevoNombre, 0, nuevoTamanio), particionAnterior.getLowerLimit(), particionActual.getHighLimit());
                        if(!f) {
                            partitions.set(i, nuevaParticion);
                            reportCondensacion.add(new FormatReportPartitions(particionAnterior.getProcessSimulations().getProcesName(), particionAnterior.getProcessSimulations().getSize(), 0, 0));
                            reportCondensacion.add(new FormatReportPartitions(particionActual.getProcessSimulations().getProcesName(), particionActual.getProcessSimulations().getSize(), 0, 0));
                            FormatReportPartitions g=new FormatReportPartitions(nuevaParticion.getName(), nuevaParticion.getPartitionSize(), nuevaParticion.getLowerLimit(), nuevaParticion.getHighLimit());

                                    reportCondensacion.add(g);
                                    partitions.remove(i - 1);
                                    i--;
                                    s = true;
                                    f = false;
                                    count++;

                          /*  if( !reportCondensacion.contains(g)) {

                            }*/
                        }
                    }
                    Partition particionSiguiente = (i < partitions.size() - 1) ? partitions.get(i + 1) : null;
                    particionActual=partitions.get(i);
                    if(particionSiguiente != null && particionSiguiente.getProcessSimulations().getTime()<=0){
                        this.reportPartitions();
                        String nuevoNombre =  "hueco"+count/*+ particionActual.getLowerLimit() +" - "+ particionSiguiente.getHighLimit()+")";// particionActual.getName() + " - " + particionSiguiente.getName()*/;
                       //count++;
                        long nuevoTamanio = particionActual.getPartitionSize() + particionSiguiente.getPartitionSize();
                        Partition nuevaParticion = new Partition(nuevoNombre, nuevoTamanio, new ProcessSimulation(nuevoNombre, 0, nuevoTamanio), particionActual.getLowerLimit(), particionSiguiente.getHighLimit());
                        if(!f) {

                            reportCondensacion.add(new FormatReportPartitions(particionActual.getProcessSimulations().getProcesName(), particionActual.getProcessSimulations().getSize(), 0, 0));
                            reportCondensacion.add(new FormatReportPartitions(particionSiguiente.getProcessSimulations().getProcesName(), particionSiguiente.getProcessSimulations().getSize(), 0, 0));

                            partitions.remove(i + 1);
                            partitions.set(i, nuevaParticion);
                            FormatReportPartitions hj= new FormatReportPartitions(nuevaParticion.getName(), nuevaParticion.getPartitionSize(), nuevaParticion.getLowerLimit(), nuevaParticion.getHighLimit());

                            reportCondensacion.add(hj);
                            s = true;
                            f=false;
                            count++;
                        }
                    }

                }
            }
            if(s){

                    reportCondensacion.add(new FormatReportPartitions());

                s=false;
            }
            reportPartitions();

        }
      result+=report(auxreport);
        return result;
    }
    
    private String report(String reportsAdd){
        String result="";
        result += "\n*********************  PROCESOS AGREGADOS " + " ********************\n";
        result += "Nombre        Tiempo        Tamaño \n";
        result+=reportsAdd;
        result += "\n\n\n*********************  REPORTE DE PARTICIONES" + " ********************\n";
        result += "Nombre        Tamaño        D. INFERIOR        D. SUPERIOR \n";
        result+=this.getReportPartition();
        result += "\n\n\n*********************  REPORTE DE CONDENSACIONES" + " ********************\n";
        result += "Nombre        Tamaño        D. INFERIOR        D. SUPERIOR \n";
        result+=this.getReportCondensacion();
        result+="\n\n\n******************* ORDEN TERMINACION PARTICIONES: ******************\n";
        result+= "Nombre \n";
        //result+=this.getReportTerminacion();
        result+=this.getReportTerminacionOrdenando();
        result+="\n\n\n******************* LISTA DE PROCESOS SALIENTES (GENERAL): ******************\n";
        /*result+= "Nombre \n";
        result+=this.getReportSalidaGeneral();*/
        result+= "Nombre \n";
        result+= this.getReportSalidaGeneralProcesos();
        result += "\n\n\n****************** LISTA DE PROCESOS SALIENTES POR PARTICION : " +" ********************\n";
        result+= this.getReportSalidaParticular();
        for (Reports r:reports) {
            result+=r.reportFinal();
        } return result;
    }
    private void prosesar(){
        ManagerProcess managerProcess;
        for (Partition partition : partitions) {
            Reports f= null;
            for (Reports r:reports) {
                if(r.getNamePartition().equals(partition.getName())) {
                    f = r;
                }
            }
            if(f!=null) {
                managerProcess = new ManagerProcess(simulatorTime, f);
                if (partition.getProcessSimulations().getTime() > 0) {
                    managerProcess.defineRoute(partition.getProcessSimulations());
                }
                if(partition.getProcessSimulations().getTime()<=0){
                    reportEndPartitions.add(new FormatReport(partition.getName(), partition.getPartitionSize()));
                  //  reportSalidaGeneral.add(new FormatReport(partition.getProcessSimulations().getProcesName(), partition.getProcessSimulations().getTime()));
                    FormatReportSalidaPorPartition format = new FormatReportSalidaPorPartition (partition.getName());
                    format.getProcessEnd().add(new FormatReport(partition.getProcessSimulations().getProcesName(), partition.getProcessSimulations().getTime()));
                    salidaParticular.add(format);
                    partition.setName("hueco"/*/"+partition.getPartitionSize()*/);
                }
            }
        }
    }
    public void recolectorDeBasura(){
        long contador=0;
        List<Partition> partitionsToAdd = new ArrayList<>();
        List<Partition> partitionsToRemove = new ArrayList<>();
        boolean f= false;
        for (Partition p : partitions) {
            boolean cd = false;
           
            if(p.getProcessSimulations().getTime()>0){
                f=true;
            }
            if (p.getProcessSimulations().getTime() <= 0&&f) {
                Partition pa = new Partition(p.getName(), p.getPartitionSize(), new ProcessSimulation(p.getProcessSimulations().getProcesName(), p.getProcessSimulations().getTime(), p.getProcessSimulations().getSize()), p.getLowerLimit(), p.getHighLimit());
                for (Partition ps : partitions) {
                    if (ps.getProcessSimulations().getTime() <= 0 && !cd) {
                        partitionsToAdd.add(pa);
                        partitionsToRemove.add(p);
                        cd = true;
                    }
                }
            }
        }


        partitions.removeAll(partitionsToRemove);
        partitions.addAll(partitionsToAdd);
        for (Partition p:partitions  ) {
            System.out.println("_____"+p.getName() +" "+ p.getPartitionSize()+"  llllllllllllllllllll    "+p.getProcessSimulations().toString());
        } System.out.println("\n\n");
        for (Partition p :partitions) {
            p.setLowerLimit(contador);
            long a = p.getLowerLimit()+p.getPartitionSize();
            p.setHighLimit(a);
            contador=a;
        }
    }

}
