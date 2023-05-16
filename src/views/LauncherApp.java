package views;

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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Andrés Ramírez - José Salamanca
 */
public class LauncherApp extends javax.swing.JFrame {

    private ArrayList<ProcessSimulation> processSimulationList;
    private ManagerSimulation managerSimulation;

    private static final String TITLE = "REPORTE DE SIMULACION";
    private static final String NAME_DOCUMENT = "Resultados_Simulacion.pdf";
    private static final String USER_MANUAL = "MANUAL DE USUARIO.pdf";

    private DefaultTableModel defaultTableModelProcess;

    public LauncherApp() {
        processSimulationList = new ArrayList<>();
        managerSimulation = new ManagerSimulation(processSimulationList, 1);

        initComponents();

        this.cleanCellsProcess();

        this.initializeTableProcess();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setExtendedState(MAXIMIZED_BOTH);
    }


    private void initializeTableProcess() {
        defaultTableModelProcess = new DefaultTableModel();
        defaultTableModelProcess.setColumnIdentifiers(new String[]{"Nombre", "Tiempo", "Tamaño"});

        tblProcessList.setModel(defaultTableModelProcess);

        if (tblProcessList.getColumnModel().getColumnCount() > 0) {
            tblProcessList.getColumnModel().getColumn(0).setPreferredWidth(35);
            tblProcessList.getColumnModel().getColumn(1).setPreferredWidth(35);
            tblProcessList.getColumnModel().getColumn(2).setPreferredWidth(35);
        }

        tblProcessList.getTableHeader().setFont(new Font("SansSerif", 0, 14));

        DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
        defaultTableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        tblProcessList.getColumnModel().getColumn(0).setCellRenderer(defaultTableCellRenderer);
        tblProcessList.getColumnModel().getColumn(1).setCellRenderer(defaultTableCellRenderer);
        tblProcessList.getColumnModel().getColumn(2).setCellRenderer(defaultTableCellRenderer);

        tblProcessList.getTableHeader().setReorderingAllowed(false);
        tblProcessList.getTableHeader().setResizingAllowed(false);

        this.repaint();
    }

    public void generatePdf(String text, String fileName) {
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

    private void restartSimulation() {
        processSimulationList = new ArrayList<>();
        managerSimulation = new ManagerSimulation(this.processSimulationList, 1);

        this.cleanCellsProcess();

        this.initializeTableProcess();
    }

    private void cleanCellsProcess() {
        txtNameProcess.setText("");
        txtTimeProcess.setText("");
        txtSizeProcess.setText("");
    }

    private void showProcess() {
        System.out.println("\nPROCESOS:");
        for (ProcessSimulation processSimulation : processSimulationList) {
            System.out.println(processSimulation.toString());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHeader = new javax.swing.JPanel();
        btnExit = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        pnlBodyProcess = new javax.swing.JPanel();
        srcProcessList = new javax.swing.JScrollPane();
        tblProcessList = new javax.swing.JTable();
        lblProcessList = new javax.swing.JLabel();
        lblNameProcess = new javax.swing.JLabel();
        txtNameProcess = new javax.swing.JTextField();
        lblTimeProcess = new javax.swing.JLabel();
        txtTimeProcess = new javax.swing.JTextField();
        lblSizeProcess = new javax.swing.JLabel();
        txtSizeProcess = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnStart = new javax.swing.JButton();
        pnlFooter = new javax.swing.JPanel();
        btnRestart = new javax.swing.JButton();
        btnOpenUserManual = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        pnlHeader.setBackground(new java.awt.Color(204, 204, 255));

        btnExit.setBackground(new java.awt.Color(255, 102, 102));
        btnExit.setFont(new Font("SansSerif", 1, 16)); // NOI18N
        btnExit.setText("SALIR");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        lblTitle.setFont(new Font("SansSerif", 1, 24)); // NOI18N
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setText("SIMULADOR DE PROCESOS   ");

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 1331, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlBodyProcess.setBackground(new java.awt.Color(209, 234, 249));

        tblProcessList.setModel(new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOMBRE", "TIEMPO", "TAMAÑO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProcessList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProcessListMouseClicked(evt);
            }
        });
        srcProcessList.setViewportView(tblProcessList);
        if (tblProcessList.getColumnModel().getColumnCount() > 0) {
            tblProcessList.getColumnModel().getColumn(0).setResizable(false);
            tblProcessList.getColumnModel().getColumn(1).setResizable(false);
            tblProcessList.getColumnModel().getColumn(2).setResizable(false);
        }

        lblProcessList.setFont(new Font("SansSerif", 1, 16)); // NOI18N
        lblProcessList.setHorizontalAlignment(SwingConstants.CENTER);
        lblProcessList.setText("TABLA DE PROCESOS");

        lblNameProcess.setFont(new Font("SansSerif", 0, 16)); // NOI18N
        lblNameProcess.setHorizontalAlignment(SwingConstants.CENTER);
        lblNameProcess.setText("Nombre:");

        txtNameProcess.setFont(new Font("SansSerif", 0, 14)); // NOI18N
        txtNameProcess.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNameProcess.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNameProcessKeyTyped(evt);
            }
        });

        lblTimeProcess.setFont(new Font("SansSerif", 0, 16)); // NOI18N
        lblTimeProcess.setHorizontalAlignment(SwingConstants.CENTER);
        lblTimeProcess.setText("Tiempo:");

        txtTimeProcess.setFont(new Font("SansSerif", 0, 14)); // NOI18N
        txtTimeProcess.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTimeProcess.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTimeProcessKeyTyped(evt);
            }
        });

        lblSizeProcess.setFont(new Font("SansSerif", 0, 16)); // NOI18N
        lblSizeProcess.setHorizontalAlignment(SwingConstants.CENTER);
        lblSizeProcess.setText("Tamaño:");

        txtSizeProcess.setFont(new Font("SansSerif", 0, 14)); // NOI18N
        txtSizeProcess.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSizeProcess.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSizeProcessKeyTyped(evt);
            }
        });

        btnAdd.setFont(new Font("SansSerif", 1, 14)); // NOI18N
        btnAdd.setText("AGREGAR PROCESO");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setFont(new Font("SansSerif", 1, 14)); // NOI18N
        btnDelete.setText("ELIMINAR PROCESO");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new Font("SansSerif", 1, 14)); // NOI18N
        btnUpdate.setText("ACTUALIZAR PROCESO");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnStart.setFont(new Font("SansSerif", 1, 14)); // NOI18N
        btnStart.setText("INICIAR SIMULACIÓN");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBodyProcessLayout = new javax.swing.GroupLayout(pnlBodyProcess);
        pnlBodyProcess.setLayout(pnlBodyProcessLayout);
        pnlBodyProcessLayout.setHorizontalGroup(
            pnlBodyProcessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyProcessLayout.createSequentialGroup()
                .addGap(323, 323, 323)
                .addGroup(pnlBodyProcessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBodyProcessLayout.createSequentialGroup()
                        .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(147, 147, 147))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBodyProcessLayout.createSequentialGroup()
                        .addGroup(pnlBodyProcessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBodyProcessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBodyProcessLayout.createSequentialGroup()
                                .addGroup(pnlBodyProcessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblTimeProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNameProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblSizeProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlBodyProcessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSizeProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlBodyProcessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtTimeProcess, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                        .addComponent(txtNameProcess, javax.swing.GroupLayout.Alignment.TRAILING)))))
                        .addGap(16, 16, 16)
                        .addGroup(pnlBodyProcessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblProcessList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(srcProcessList, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(324, Short.MAX_VALUE))
        );
        pnlBodyProcessLayout.setVerticalGroup(
            pnlBodyProcessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyProcessLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(lblProcessList, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBodyProcessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlBodyProcessLayout.createSequentialGroup()
                        .addGroup(pnlBodyProcessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNameProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNameProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlBodyProcessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTimeProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTimeProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlBodyProcessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSizeProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSizeProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(99, 99, 99)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54))
                    .addComponent(srcProcessList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        pnlFooter.setBackground(new java.awt.Color(255, 204, 204));

        btnRestart.setFont(new Font("SansSerif", 1, 14)); // NOI18N
        btnRestart.setText("REINICIAR SIMULACIÓN");
        btnRestart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestartActionPerformed(evt);
            }
        });

        btnOpenUserManual.setFont(new Font("SansSerif", 1, 14)); // NOI18N
        btnOpenUserManual.setText("ABRIR MANUAL DE USUARIO");
        btnOpenUserManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenUserManualActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlFooterLayout = new javax.swing.GroupLayout(pnlFooter);
        pnlFooter.setLayout(pnlFooterLayout);
        pnlFooterLayout.setHorizontalGroup(
            pnlFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFooterLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(btnRestart, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btnOpenUserManual, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlFooterLayout.setVerticalGroup(
            pnlFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFooterLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(pnlFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRestart, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOpenUserManual, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlFooter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlBodyProcess, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBodyProcess, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlFooter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void tblProcessListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProcessListMouseClicked
        txtNameProcess.setText(processSimulationList.get(tblProcessList.getSelectedRow()).getProcesName());
        txtTimeProcess.setText("" + processSimulationList.get(tblProcessList.getSelectedRow()).getTime());
        txtSizeProcess.setText("" + processSimulationList.get(tblProcessList.getSelectedRow()).getSize());
    }//GEN-LAST:event_tblProcessListMouseClicked

    private void txtNameProcessKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameProcessKeyTyped
        int key = evt.getKeyChar();

        boolean characters = (key >= 48 && key <= 57) || (key >= 65 && key <= 90)
                || (key >= 97 && key <= 122) || (key == 32) || (key >= 160 && key <= 165)
                || (key == 40) || (key == 41) || (key == 45) || (key == 130) || (key == 95);

        if (!characters) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNameProcessKeyTyped

    private void txtTimeProcessKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimeProcessKeyTyped
        int key = evt.getKeyChar();

        boolean numbers = key >= 48 && key <= 57;

        if (!numbers) {
            evt.consume();
        }
        
        if (txtTimeProcess.getText().trim().length() > 17) {
            evt.consume();
        }
    }//GEN-LAST:event_txtTimeProcessKeyTyped

    private void txtSizeProcessKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSizeProcessKeyTyped
        int key = evt.getKeyChar();

        boolean numbers = key >= 48 && key <= 57;

        if (!numbers) {
            evt.consume();
        }
        
        if (txtSizeProcess.getText().trim().length() > 17) {
            evt.consume();
        }
    }//GEN-LAST:event_txtSizeProcessKeyTyped

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (txtNameProcess.getText().equals("") || txtTimeProcess.getText().equals("") || txtSizeProcess.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Por favor, completar todos los campos del proceso", "ERROR AL CREAR EL PROCESO", JOptionPane.ERROR_MESSAGE);
        } else if (txtTimeProcess.getText().equals("0") || txtSizeProcess.getText().equals("0")) {
            JOptionPane.showMessageDialog(null, "El tiempo y tamaño del proceso deben ser mayor a 0", "ERROR AL CREAR EL PROCESO", JOptionPane.ERROR_MESSAGE);
        } else {
            for (ProcessSimulation processSimulation : processSimulationList) {
                if (processSimulation.getProcesName().equals(txtNameProcess.getText())) {
                    JOptionPane.showMessageDialog(null, "El nombre del proceso que desea agregar ya existe", "ERROR AL CREAR EL PROCESO", JOptionPane.ERROR_MESSAGE);
                    this.cleanCellsProcess();
                    return;
                }
            }
            String name = txtNameProcess.getText();
            long time = Long.parseLong(txtTimeProcess.getText());
            long size = Long.parseLong(txtSizeProcess.getText());
            this.processSimulationList.add(new ProcessSimulation(name, time, size));
            defaultTableModelProcess.addRow(new Object[]{txtNameProcess.getText(), txtTimeProcess.getText(), txtSizeProcess.getText()});
            tblProcessList.setModel(defaultTableModelProcess);
            this.cleanCellsProcess();
            JOptionPane.showMessageDialog(null, "El proceso ha sido creado correctamente", "PROCESO CREADO CORRECTAMENTE", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (this.processSimulationList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se encuentra ningún proceso registrado", "ERROR AL ELIMINAR EL PROCESO", JOptionPane.ERROR_MESSAGE);
        } else {
            int row = tblProcessList.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione el proceso que desea eliminar", "ERROR AL ELIMINAR EL PROCESO", JOptionPane.ERROR_MESSAGE);
            } else {
                for (ProcessSimulation processSimulation : processSimulationList) {
                    if (processSimulation.getProcesName().equals(txtNameProcess.getText())) {
                        int isDelete = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el proceso seleccionado?", "ELIMINAR PROCESO", JOptionPane.YES_NO_OPTION);
                        if (isDelete == JOptionPane.YES_OPTION) {
                            this.processSimulationList.remove(row);
                            defaultTableModelProcess.removeRow(row);
                            tblProcessList.setModel(defaultTableModelProcess);
                            this.cleanCellsProcess();
                            JOptionPane.showMessageDialog(null, "El proceso ha sido eliminado correctamente", "PROCESO ELIMINADO CORRECTAMENTE", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        String processNameToUpdate = txtNameProcess.getText();
        int row = tblProcessList.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione el proceso que desea actualizar", "ERROR AL ACTUALIZAR EL PROCESO", JOptionPane.ERROR_MESSAGE);
        } else if (this.processSimulationList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se encuentra ningún proceso registrado", "ERROR AL ACTUALIZAR EL PROCESO", JOptionPane.ERROR_MESSAGE);
        } else {
            for (ProcessSimulation processSimulation : processSimulationList) {
                if (processSimulation.getProcesName().equals(processNameToUpdate)) {
                    if (txtTimeProcess.getText().equals("") || txtSizeProcess.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Por favor, completar todos los campos del proceso", "ERROR AL ACTUALIZAR EL PROCESO", JOptionPane.ERROR_MESSAGE);
                    } else if (txtTimeProcess.getText().equals("0") || txtSizeProcess.getText().equals("0")) {
                        JOptionPane.showMessageDialog(null, "El tiempo y tamaño del proceso deben ser mayor a 0", "ERROR AL ACTUALIZAR EL PROCESO", JOptionPane.ERROR_MESSAGE);
                    } else {
                        processSimulation.setTime(Integer.parseInt(txtTimeProcess.getText()));
                        processSimulation.setSize(Integer.parseInt(txtSizeProcess.getText()));

                        Object[] data = new Object[defaultTableModelProcess.getColumnCount()];
                        for (int i = 0; i < defaultTableModelProcess.getColumnCount(); i++) {
                            data[i] = defaultTableModelProcess.getValueAt(row, i);
                        }

                        data[1] = txtTimeProcess.getText();
                        data[2] = txtSizeProcess.getText();

                        for (int i = 0; i < defaultTableModelProcess.getColumnCount(); i++) {
                            defaultTableModelProcess.setValueAt(data[i], row, i);
                        }
                        tblProcessList.setModel(defaultTableModelProcess);
                        this.cleanCellsProcess();
                        JOptionPane.showMessageDialog(null, "El proceso ha sido actualizado correctamente", "PROCESO ACTUALIZADO CORRECTAMENTE", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                }
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        if (this.processSimulationList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Para poder iniciar la simulación, mínimo debe haber registrado un proceso", "ERROR AL INICIAR LA SIMULACIÓN", JOptionPane.ERROR_MESSAGE);
        } else {
            this.showProcess();
            String result = managerSimulation.startSimulation();
            System.out.println(result);
            generatePdf(result, NAME_DOCUMENT);
            try {
                File path = new File(NAME_DOCUMENT);
                Desktop.getDesktop().open(path);
                this.restartSimulation();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnStartActionPerformed

    private void btnRestartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestartActionPerformed
        this.restartSimulation();
    }//GEN-LAST:event_btnRestartActionPerformed

    private void btnOpenUserManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenUserManualActionPerformed
        try {
            File path = new File(USER_MANUAL);
            Desktop.getDesktop().open(path);
        } catch (IOException ex) {}
    }//GEN-LAST:event_btnOpenUserManualActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnOpenUserManual;
    private javax.swing.JButton btnRestart;
    private javax.swing.JButton btnStart;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel lblNameProcess;
    private javax.swing.JLabel lblProcessList;
    private javax.swing.JLabel lblSizeProcess;
    private javax.swing.JLabel lblTimeProcess;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlBodyProcess;
    private javax.swing.JPanel pnlFooter;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JScrollPane srcProcessList;
    private javax.swing.JTable tblProcessList;
    private javax.swing.JTextField txtNameProcess;
    private javax.swing.JTextField txtSizeProcess;
    private javax.swing.JTextField txtTimeProcess;
    // End of variables declaration//GEN-END:variables
}
