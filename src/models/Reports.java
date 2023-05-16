package models;

import java.util.ArrayList;

public class Reports {
    private String namePartition;
    private ArrayList<FormatReport> listo;
    private ArrayList<FormatReport> despachar;
    private ArrayList<FormatReport> ejecucion;
    private ArrayList<FormatReport> expTiempo;


    public Reports(String namePartition, ArrayList<FormatReport> listo, ArrayList<FormatReport> despachar, ArrayList<FormatReport> ejecucion, ArrayList<FormatReport> expTiempo, ArrayList<FormatReport> salida) {
        this.listo = listo;
        this.despachar = despachar;
        this.ejecucion = ejecucion;
        this.expTiempo = expTiempo;
        this.namePartition= namePartition;
    }

    public String getNamePartition() {
        return namePartition;
    }

    public void setNamePartition(String namePartition) {
        this.namePartition = namePartition;
    }

    public ArrayList<FormatReport> getListo() {
        return listo;
    }

    public void setListo(ArrayList<FormatReport> listo) {
        this.listo = listo;
    }

    public ArrayList<FormatReport> getDespachar() {
        return despachar;
    }

    public void setDespachar(ArrayList<FormatReport> despachar) {
        this.despachar = despachar;
    }

    public ArrayList<FormatReport> getEjecucion() {
        return ejecucion;
    }

    public void setEjecucion(ArrayList<FormatReport> ejecucion) {
        this.ejecucion = ejecucion;
    }

    public ArrayList<FormatReport> getExpTiempo() {
        return expTiempo;
    }

    public void setExpTiempo(ArrayList<FormatReport> expTiempo) {
        this.expTiempo = expTiempo;
    }


    public String getStringProcessList() {
        String processListReport = "";
        for (FormatReport processSimulation : listo) {
            processListReport += processSimulation.toString();
        }
        return processListReport;
    }
    public String getStringdespachar() {
        String processListReport = "";
        for (FormatReport processSimulation : despachar) {
            processListReport += processSimulation.toString();
        }
        return processListReport;
    }
    public String getStringExpTiempo() {
        String processListReport = "";
        for (FormatReport processSimulation : expTiempo) {
            processListReport += processSimulation.toString();
        }
        return processListReport;
    }
    public String getStringEjecucion() {
        String processListReport = "";
        for (FormatReport processSimulation :ejecucion ) {
            processListReport += processSimulation.toString();
        }
        return processListReport;
    }
    /*public String getStringSalida() {
        String processListReport = "";
        for (FormatReport processSimulation : salida) {
            processListReport += processSimulation.getName()+"\n";
        }
        return processListReport;
    }*/

    public String reportFinal () {
        String report = "";
        report += "\n\n*********************  REPORTE DE PARTICION :"+ namePartition+ "********************\n";
        report += "\n*********************  LISTA DE PROCESOS EN ESTADO DE LISTO: ********************\n";
        report += "Nombre        Tiempo \n";
        report += getStringProcessList();
       // report += "\n\n\n*************************** LISTA DE PROCESOS SALIENTES: *****************************\n";
        report += "\n\n\n************** LISTA DE PROCESOS EN TRANSICION DE DESPACHAR: ***************\n";
        report += "Nombre        Tiempo \n";
        report += getStringdespachar();
        report += "\n\n\n****************** LISTA DE PROCESOS EN ESTADO DE EJECUCION: *****************\n";
        report += "Nombre        Tiempo \n";
        report += getStringEjecucion();
        report += "\n\n\n*********** LISTA DE PROCESOS EN TRANSICION DE TIEMPO EXPIRADO: **********\n";
        report += "Nombre        Tiempo \n";
        report += getStringExpTiempo();
        return report;
    }
}
