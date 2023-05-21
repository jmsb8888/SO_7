import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import models.ManagerSimulation;
import models.ProcessSimulation;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static com.itextpdf.kernel.pdf.tagging.StandardRoles.TITLE;

public class test {
    public static void main(String[] args) {
      ProcessSimulation p1= new ProcessSimulation("p1", 10, 10);
        ProcessSimulation p2= new ProcessSimulation("p2", 5, 15);
        ProcessSimulation p3= new ProcessSimulation("p3", 15, 10);
       ProcessSimulation p4= new ProcessSimulation("p4", 4, 5);
        //ProcessSimulation p5= new ProcessSimulation("p5", 5, 15);

     /*   ArrayList<ProcessSimulation> ps= new ArrayList<>();
        ps.add(p1);
        ps.add(p2);
        ps.add(p3);
      //  ps.add(p4);
        //ps.add(p5);*/

        ProcessSimulation P11= new ProcessSimulation("P11" ,5 ,11);
        ProcessSimulation P15= new ProcessSimulation( "P15" ,7, 15);
        ProcessSimulation P18= new ProcessSimulation("P18" ,8 ,18);
        ProcessSimulation P6= new ProcessSimulation( "P6" ,3 ,6);
        ProcessSimulation P9= new ProcessSimulation("P9" ,4, 9);
        ProcessSimulation P20= new ProcessSimulation("P20" ,2 ,20);
        ProcessSimulation P13= new ProcessSimulation("P13" ,3, 13);
        ProcessSimulation P14= new ProcessSimulation("P14" ,2, 14);
      /*  ProcessSimulation p8= new ProcessSimulation("p8", 7, 15);
        ProcessSimulation p9= new ProcessSimulation("p9", 4, 14);
        ProcessSimulation p10= new ProcessSimulation("p10", 3, 7);*/
        ArrayList<ProcessSimulation> ps= new ArrayList<>();



     //prueba yop
     ps.add(P11);
        ps.add(P15);
        ps.add(P18);
        ps.add(P6);
        ps.add(P9);
        ps.add(P20);
        ps.add(P13);
       // ps.add(P14);


       /* ps.add(p8);
        ps.add(p9);
        ps.add(p10);*/
/*
//prueba andrea

        ps.add(p1);
        ps.add(p2);
        ps.add(p3);
        ps.add(p4);*/



        ManagerSimulation m= new ManagerSimulation(ps, 1);
        String prueba = m.startSimulation();
        System.out.println( prueba);
        generatePdf(prueba, "prueba.pdf");
    }
    public static void generatePdf(String text, String fileName) {
        File file = new File(fileName);

        try (PdfWriter pdfWriter = new PdfWriter(file)) {

            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);

            PdfFont font = PdfFontFactory.createFont("Helvetica");
            Paragraph paragraph = new Paragraph("\n\n" + text).setFont(font).setFontSize(12f);
            Paragraph title = new Paragraph(TITLE).setFont(font).setFontSize(20f);

            paragraph.setTextAlignment(TextAlignment.JUSTIFIED);
            title.setTextAlignment(TextAlignment.CENTER);

            document.add(title);
            document.add(paragraph);

            document.close();
            pdfDocument.close();

            System.out.println("PDF creado");

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "El archivo de reporte de la simulación se encuentra abierto\nPor favor ciérrelo e inténtelo nuevamente", "ERROR AL REALIZAR LA SIMULACION", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.out.println("2");
        }
    }
}
