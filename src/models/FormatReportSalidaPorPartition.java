package models;

import java.util.ArrayList;

public class FormatReportSalidaPorPartition {
    private String namePartition;
    private ArrayList<FormatReport> processEnd;

    public FormatReportSalidaPorPartition(String namePartition) {
        this.namePartition = namePartition;
        processEnd= new ArrayList<>();
    }

    public String getNamePartition() {
        return namePartition;
    }

    public void setNamePartition(String namePartition) {
        this.namePartition = namePartition;
    }

    public ArrayList<FormatReport> getProcessEnd() {
        return processEnd;
    }

    public void setProcessEnd(ArrayList<FormatReport> processEnd) {
        this.processEnd = processEnd;
    }
}
