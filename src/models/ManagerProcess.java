package models;

import java.util.ArrayList;

public class ManagerProcess {
    private int simulatorTime;
    private ArrayList<FormatReport> listo;
    private ArrayList<FormatReport> despachar;
    private ArrayList<FormatReport> ejecucion;
    private ArrayList<FormatReport> expTiempo;


    public ManagerProcess(int simulatorTime, Reports reports) {
        this.simulatorTime = simulatorTime;
        listo = reports.getListo();
        despachar = reports.getDespachar();
        ejecucion = reports.getEjecucion();
        expTiempo = reports.getExpTiempo();
    }
    public String getProcessList() {
        String processListReport = "";
        for (FormatReport processSimulation : listo) {
            processListReport += processSimulation.toString();
        }
        return processListReport;
    }
    public String getdespachar() {
        String processListReport = "";
        for (FormatReport processSimulation : despachar) {
            processListReport += processSimulation.toString();
        }
        return processListReport;
    }
    public String getExpTiempo() {
        String processListReport = "";
        for (FormatReport processSimulation : expTiempo) {
            processListReport += processSimulation.toString();
        }
        return processListReport;
    }
    public String getEjecucion() {
        String processListReport = "";
        for (FormatReport processSimulation :ejecucion ) {
            processListReport += processSimulation.toString();
        }
        return processListReport;
    }
   /* public String getSalida() {
        String processListReport = "";
        for (FormatReport processSimulation : salida) {
            processListReport += processSimulation.getName()+"\n";
        }
        return processListReport;
    }*/

    public String reportFinal (ProcessSimulation processSimulation){
        defineRoute(processSimulation);
        String report="";
        report = "\n*********************  LISTA DE PROCESOS EN ESTADO DE LISTO: ********************\n";
        report += "Nombre        Tiempo \n";
        report+=getProcessList();
      //  report += "\n\n\n*************************** LISTA DE PROCESOS SALIENTES: *****************************\n";

        report+="\n\n\n************** LISTA DE PROCESOS EN TRANSICION DE DESPACHAR: ***************\n";
        report += "Nombre        Tiempo \n";
        report+= getdespachar();
        report+= "\n\n\n****************** LISTA DE PROCESOS EN ESTADO DE EJECUCION: *****************\n";
        report += "Nombre        Tiempo \n";
        report+= getEjecucion();
        report+="\n\n\n*********** LISTA DE PROCESOS EN TRANSICION DE TIEMPO EXPIRADO: **********\n";
        report += "Nombre        Tiempo \n";
        report+= getExpTiempo();
        return report;
    }
    public void defineRoute(ProcessSimulation processSimulation){
        listo.add(new FormatReport(processSimulation.getProcesName(), processSimulation.getTime()));
        despachar.add(new FormatReport(processSimulation.getProcesName(), processSimulation.getTime()));
        ProcessSimulation aux = processSimulation;
        aux.setTime(aux.getTime() - simulatorTime);
        if(processSimulation.getTime()>0) {
            ejecucion.add(new FormatReport(processSimulation.getProcesName(), processSimulation.getTime()));
            expTiempo.add(new FormatReport(processSimulation.getProcesName(), processSimulation.getTime()));
        }
    }
}
