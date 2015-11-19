/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import handler.DBHandler;
import handler.DrawHandler;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.ListModel;
import model.Billet;
import model.Film;
import model.Forestilling;
import model.Sal;

/**
 *
 * @author Morten Ricki Rasmussen
 */
public class MainFrame extends javax.swing.JFrame {

    private DBHandler db;
    private DrawHandler dh;
    private ArrayList<Integer> selectedForestilling;
    private ArrayList<Film> film;
    private ArrayList<Sal> sale;
    private ArrayList<Forestilling> forestillinger;
    private ArrayList<Billet> billetter;
    private ListModel listModel;

    /**
     * Creates new form MainFrame
     */
    private void updateArrays() {
        listModel = new DefaultListModel();

        try {
            db.loadArrayLists(sale, film, forestillinger, billetter);
        } catch (SQLException | ClassNotFoundException ex) {
            errorLabelHeader.setText("Uventet fejl");
            errorLabel1.setText("Der er muligvis ikke forbindelse til databasen");
            errorLabel2.setText("Check om der er forbindelse til internettet");
            errorLabel3.setText(ex.getMessage());
            updateDialog(errorDialog);
        }

        filmCombo.removeAllItems();
        String alleForestillinger = "Alle forestillinger";
        filmCombo.addItem(alleForestillinger);

        for (Film film : film) {
            filmCombo.addItem(film.getTitel());
        }
    }

    public void updateDialog(JDialog dialog) {
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    public void updateForestillinger() {
        forestillingsPanel.removeAll();

        for (Forestilling forestilling : forestillinger) {
            if (filmCombo.getSelectedIndex() == 0) {
                FilmUdvalgPanel f = new FilmUdvalgPanel(forestilling, valgAfPladsPanel, filmUdvalgsPanel, selectedForestilling);
                f.setVisible(true);
                forestillingsPanel.add(f);
            } else {
                if (filmCombo.getSelectedItem() == forestilling.getFilmTitel()) {
                    FilmUdvalgPanel f = new FilmUdvalgPanel(forestilling, valgAfPladsPanel, filmUdvalgsPanel, selectedForestilling);
                    f.setVisible(true);
                    forestillingsPanel.add(f);
                }
            }
        }

        if (!(jDateChooser1.getDate() == null)) {
            forestillingsPanel.removeAll();
            Date dateFromDateChooser = jDateChooser1.getDate();
            String dateString = String.format("%1$td-%1$tm-%1$tY", dateFromDateChooser);
            if (filmCombo.getSelectedIndex() == 0) {
                for (Forestilling forestilling : forestillinger) {
                    if (dateString.equals(forestilling.getDato())) {
                        FilmUdvalgPanel f = new FilmUdvalgPanel(forestilling, valgAfPladsPanel, filmUdvalgsPanel, selectedForestilling);
                        f.setVisible(true);
                        forestillingsPanel.add(f);
                    }
                }
            } else {
                for (Forestilling forestilling : forestillinger) {
                    if (dateString.equals(forestilling.getDato()) && filmCombo.getSelectedItem() == forestilling.getFilmTitel()) {
                        FilmUdvalgPanel f = new FilmUdvalgPanel(forestilling, valgAfPladsPanel, filmUdvalgsPanel, selectedForestilling);
                        f.setVisible(true);
                        forestillingsPanel.add(f);
                    }
                }
            }
        }

        forestillingsPanel.revalidate();
        forestillingsPanel.repaint();
    }

    public void drawLastSelectedForestilling(Graphics g) {
        dh.drawForestillingsSal(forestillinger.get(selectedForestilling.get(0)), sale.get(forestillinger.get(selectedForestilling.get(0)).getSalId() - 1), billetter, g, pladsPanelAntalBilleterComboBox.getSelectedIndex(), salPanel.getWidth());
    }

    public MainFrame() {
        try {
            db = new DBHandler();
            dh = new DrawHandler();
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
        forsidePanel.setVisible(true);
        valgAfPladsPanel.setVisible(false);
        filmUdvalgsPanel.setVisible(false);

        selectedForestilling = new ArrayList<>();
        film = new ArrayList<>();
        sale = new ArrayList<>();
        forestillinger = new ArrayList<>();
        billetter = new ArrayList<>();

        updateArrays();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        adminDialog = new javax.swing.JDialog();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        dbIPField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        dbPortField = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        dbDatabasenavnField = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        dbBrugernavnField = new javax.swing.JTextField();
        dbIndstillingerButton = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        dbPasswordField = new javax.swing.JPasswordField();
        jPanel3 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        titelField = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        spilletidField = new javax.swing.JTextField();
        dbIndstillingerButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        tilføjForestillingTimeField = new javax.swing.JTextField();
        tilføjForestillingMinutField = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        tilføjForestillingDato = new com.toedter.calendar.JDateChooser();
        fundendeFilmCombo = new javax.swing.JComboBox();
        jLabel27 = new javax.swing.JLabel();
        findFilmField = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        salCombo = new javax.swing.JComboBox();
        jButton5 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        addSalNavnField = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        addSalRowsCountField = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        addSalSeatCountField = new javax.swing.JTextField();
        addSalButton = new javax.swing.JButton();
        errorDialog = new javax.swing.JDialog();
        errorLabelHeader = new javax.swing.JLabel();
        errorLabel1 = new javax.swing.JLabel();
        errorLabel2 = new javax.swing.JLabel();
        errorDialogButton = new javax.swing.JButton();
        errorLabel3 = new javax.swing.JLabel();
        fileChooser = new javax.swing.JDialog();
        jFileChooser1 = new javax.swing.JFileChooser();
        adminLoginDialog = new javax.swing.JDialog();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        adminBrugernavnField = new javax.swing.JTextField();
        adminPasswordField = new javax.swing.JPasswordField();
        jButton6 = new javax.swing.JButton();
        headerPanel = new javax.swing.JPanel();
        tilFosidenButton = new javax.swing.JButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        forsidePanel = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        filmUdvalgsPanel = new javax.swing.JPanel();
        filmudValgsScroll = new javax.swing.JScrollPane();
        forestillingsPanel = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        filmCombo = new javax.swing.JComboBox();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        valgAfPladsPanel = new javax.swing.JPanel();
        salPanel = new javax.swing.JPanel(){
            public void paint(Graphics g){
                super.paint(g);
                drawLastSelectedForestilling(g);
            }
        };
        pladsPanelTitle = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        pladsPanelDato = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        pladsPanelTidspunkt = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        pladsPanelAntalBilleterComboBox = new javax.swing.JComboBox();
        pladsPanelTelefonnummerField = new javax.swing.JTextField();
        pladsPanelBestilButton = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        footerPanel = new javax.swing.JPanel();
        footerAdresse = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        footerOmOs = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        footerSocial = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        facebookLabel = new javax.swing.JLabel();
        footerAdmin = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jLabel12.setText("IP/Hostname");

        dbPortField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dbPortFieldKeyTyped(evt);
            }
        });

        jLabel14.setText("Port");

        jLabel15.setText("Databasenavn");

        jLabel16.setText("Brugernavn");

        dbIndstillingerButton.setText("Ændre indstillinger");
        dbIndstillingerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dbIndstillingerButtonActionPerformed(evt);
            }
        });

        jLabel17.setText("Password");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))
                        .addGap(0, 307, Short.MAX_VALUE))
                    .addComponent(dbBrugernavnField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dbDatabasenavnField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dbPortField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dbIPField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dbPasswordField))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(dbIndstillingerButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dbIPField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dbPortField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dbDatabasenavnField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dbBrugernavnField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dbPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dbIndstillingerButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Database indstillinger", jPanel1);

        jLabel22.setText("Titel");

        jLabel23.setText("Spilletid");

        spilletidField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                spilletidFieldKeyTyped(evt);
            }
        });

        dbIndstillingerButton1.setText("Tilføj film");
        dbIndstillingerButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dbIndstillingerButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Tilføj plakat");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spilletidField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(titelField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23)
                            .addComponent(jButton3))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(dbIndstillingerButton1)
                .addContainerGap(229, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(titelField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spilletidField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(dbIndstillingerButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tilføj film", jPanel3);

        jLabel24.setText("Dato");

        jLabel25.setText("Tidspunkt");

        tilføjForestillingTimeField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tilføjForestillingTimeField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tilføjForestillingTimeFieldKeyTyped(evt);
            }
        });

        tilføjForestillingMinutField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tilføjForestillingMinutFieldKeyTyped(evt);
            }
        });

        jLabel26.setText(":");

        jLabel27.setText("Find film");

        jButton4.setText("Søg efter titel");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel28.setText("Sal");

        jButton5.setText("Tilføj forestlling");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tilføjForestillingDato, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                        .addGap(155, 155, 155))
                    .addComponent(fundendeFilmCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(tilføjForestillingTimeField)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tilføjForestillingMinutField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(findFilmField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4))
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                    .addComponent(salCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tilføjForestillingDato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tilføjForestillingTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tilføjForestillingMinutField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addGap(18, 18, 18)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(findFilmField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fundendeFilmCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(salCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tilføj forestilling", jPanel2);

        jLabel32.setText("Sal navn");

        jLabel33.setText("Antal rækker");

        jLabel34.setText("Antal sædder");

        addSalButton.setText("Tilføj sal");
        addSalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSalButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addSalNavnField)
                    .addComponent(addSalRowsCountField)
                    .addComponent(addSalSeatCountField)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32)
                            .addComponent(jLabel33)
                            .addComponent(jLabel34))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(178, Short.MAX_VALUE)
                .addComponent(addSalButton)
                .addGap(177, 177, 177))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addSalNavnField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addSalRowsCountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addSalSeatCountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(addSalButton)
                .addContainerGap(126, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tilføj sal", jPanel4);

        javax.swing.GroupLayout adminDialogLayout = new javax.swing.GroupLayout(adminDialog.getContentPane());
        adminDialog.getContentPane().setLayout(adminDialogLayout);
        adminDialogLayout.setHorizontalGroup(
            adminDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        adminDialogLayout.setVerticalGroup(
            adminDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminDialogLayout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        errorLabelHeader.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        errorLabelHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        errorLabelHeader.setText("Fejl!");

        errorLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        errorLabel1.setText("FejlLabel1");

        errorLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        errorLabel2.setText("FejlLabel2");

        errorDialogButton.setText("Færdig");
        errorDialogButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                errorDialogButtonActionPerformed(evt);
            }
        });

        errorLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        errorLabel3.setText("FejlLabel3");

        javax.swing.GroupLayout errorDialogLayout = new javax.swing.GroupLayout(errorDialog.getContentPane());
        errorDialog.getContentPane().setLayout(errorDialogLayout);
        errorDialogLayout.setHorizontalGroup(
            errorDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, errorDialogLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(errorDialogButton)
                .addGap(162, 162, 162))
            .addGroup(errorDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(errorDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(errorLabelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(errorLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(errorLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(errorLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
                .addContainerGap())
        );
        errorDialogLayout.setVerticalGroup(
            errorDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(errorDialogLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(errorLabelHeader)
                .addGap(18, 18, 18)
                .addComponent(errorLabel1)
                .addGap(18, 18, 18)
                .addComponent(errorLabel2)
                .addGap(18, 18, 18)
                .addComponent(errorLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(errorDialogButton)
                .addContainerGap())
        );

        jFileChooser1.setDialogTitle("Vælg plakat");
        jFileChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooser1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout fileChooserLayout = new javax.swing.GroupLayout(fileChooser.getContentPane());
        fileChooser.getContentPane().setLayout(fileChooserLayout);
        fileChooserLayout.setHorizontalGroup(
            fileChooserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 582, Short.MAX_VALUE)
            .addGroup(fileChooserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(fileChooserLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        fileChooserLayout.setVerticalGroup(
            fileChooserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 397, Short.MAX_VALUE)
            .addGroup(fileChooserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(fileChooserLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Log in");

        jLabel30.setText("Brugernavn");

        jLabel31.setText("Kodeord");

        adminPasswordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                adminPasswordFieldKeyPressed(evt);
            }
        });

        jButton6.setText("Log in");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout adminLoginDialogLayout = new javax.swing.GroupLayout(adminLoginDialog.getContentPane());
        adminLoginDialog.getContentPane().setLayout(adminLoginDialogLayout);
        adminLoginDialogLayout.setHorizontalGroup(
            adminLoginDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminLoginDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(adminLoginDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(adminBrugernavnField)
                    .addGroup(adminLoginDialogLayout.createSequentialGroup()
                        .addGroup(adminLoginDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(adminPasswordField))
                .addContainerGap())
            .addGroup(adminLoginDialogLayout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(jButton6)
                .addContainerGap(106, Short.MAX_VALUE))
        );
        adminLoginDialogLayout.setVerticalGroup(
            adminLoginDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminLoginDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29)
                .addGap(18, 18, 18)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adminBrugernavnField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(adminPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BioTrio");
        setMaximumSize(new java.awt.Dimension(950, 700));
        setMinimumSize(new java.awt.Dimension(950, 700));

        tilFosidenButton.setText("Til fosiden");
        tilFosidenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tilFosidenButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tilFosidenButton, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tilFosidenButton, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        forsidePanel.setBackground(new java.awt.Color(255, 255, 255));
        forsidePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel21.setBackground(new java.awt.Color(255, 255, 255));
        jLabel21.setFont(new java.awt.Font("Wide Latin", 1, 48)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("BIO TRIO");

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rescources/Bio Trio forside banner.png"))); // NOI18N

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jButton2.setText("Gå til filmudvalg");
        jButton2.setMaximumSize(new java.awt.Dimension(926, 67));
        jButton2.setMinimumSize(new java.awt.Dimension(926, 67));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout forsidePanelLayout = new javax.swing.GroupLayout(forsidePanel);
        forsidePanel.setLayout(forsidePanelLayout);
        forsidePanelLayout.setHorizontalGroup(
            forsidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(forsidePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(forsidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, forsidePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        forsidePanelLayout.setVerticalGroup(
            forsidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(forsidePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addGap(18, 18, 18)
                .addComponent(jLabel20)
                .addGap(33, 33, 33)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        filmUdvalgsPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        filmudValgsScroll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        filmudValgsScroll.getVerticalScrollBar().setUnitIncrement(10);

        forestillingsPanel.setLayout(new javax.swing.BoxLayout(forestillingsPanel, javax.swing.BoxLayout.Y_AXIS));
        filmudValgsScroll.setViewportView(forestillingsPanel);

        jLabel18.setText("Vælg dato:");

        jLabel19.setText("Vælg film");

        filmCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filmComboActionPerformed(evt);
            }
        });

        jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser1PropertyChange(evt);
            }
        });

        javax.swing.GroupLayout filmUdvalgsPanelLayout = new javax.swing.GroupLayout(filmUdvalgsPanel);
        filmUdvalgsPanel.setLayout(filmUdvalgsPanelLayout);
        filmUdvalgsPanelLayout.setHorizontalGroup(
            filmUdvalgsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(filmudValgsScroll)
            .addGroup(filmUdvalgsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(115, 115, 115)
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addComponent(filmCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(436, Short.MAX_VALUE))
        );
        filmUdvalgsPanelLayout.setVerticalGroup(
            filmUdvalgsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, filmUdvalgsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(filmUdvalgsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(filmUdvalgsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(filmCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filmudValgsScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        valgAfPladsPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        valgAfPladsPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                valgAfPladsPanelComponentShown(evt);
            }
        });

        salPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                salPanelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout salPanelLayout = new javax.swing.GroupLayout(salPanel);
        salPanel.setLayout(salPanelLayout);
        salPanelLayout.setHorizontalGroup(
            salPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 571, Short.MAX_VALUE)
        );
        salPanelLayout.setVerticalGroup(
            salPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        pladsPanelTitle.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        pladsPanelTitle.setText("titel");

        jLabel35.setText("Dato:");

        pladsPanelDato.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        pladsPanelDato.setText("jLabel35");

        jLabel36.setText("Tidspunkt:");

        pladsPanelTidspunkt.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        pladsPanelTidspunkt.setText("jLabel35");

        jLabel37.setText("Antal billetter:");

        pladsPanelAntalBilleterComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4" }));

        pladsPanelTelefonnummerField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pladsPanelTelefonnummerFieldKeyTyped(evt);
            }
        });

        pladsPanelBestilButton.setText("Bestil billetter");
        pladsPanelBestilButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pladsPanelBestilButtonActionPerformed(evt);
            }
        });

        jLabel38.setText("Telefonnummer:");

        javax.swing.GroupLayout valgAfPladsPanelLayout = new javax.swing.GroupLayout(valgAfPladsPanel);
        valgAfPladsPanel.setLayout(valgAfPladsPanelLayout);
        valgAfPladsPanelLayout.setHorizontalGroup(
            valgAfPladsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(valgAfPladsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(valgAfPladsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pladsPanelTitle)
                    .addComponent(pladsPanelDato)
                    .addComponent(pladsPanelTidspunkt)
                    .addComponent(jLabel35)
                    .addComponent(jLabel36)
                    .addGroup(valgAfPladsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pladsPanelAntalBilleterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pladsPanelBestilButton)
                    .addComponent(jLabel38)
                    .addComponent(pladsPanelTelefonnummerField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 285, Short.MAX_VALUE)
                .addComponent(salPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        valgAfPladsPanelLayout.setVerticalGroup(
            valgAfPladsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(valgAfPladsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(valgAfPladsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(valgAfPladsPanelLayout.createSequentialGroup()
                        .addComponent(pladsPanelTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pladsPanelDato)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pladsPanelTidspunkt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(valgAfPladsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(pladsPanelAntalBilleterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pladsPanelTelefonnummerField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pladsPanelBestilButton)
                        .addGap(0, 247, Short.MAX_VALUE))
                    .addComponent(salPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(forsidePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addGap(1, 1, 1)
                    .addComponent(filmUdvalgsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(1, 1, 1)))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(valgAfPladsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(forsidePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(filmUdvalgsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(valgAfPladsPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1.setLayer(forsidePanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(filmUdvalgsPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(valgAfPladsPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        footerPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        footerAdresse.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        footerAdresse.setPreferredSize(new java.awt.Dimension(225, 189));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Adresse");

        jLabel5.setText("Femøvej 3");

        jLabel6.setText("4700");

        jLabel7.setText("Næstved");

        javax.swing.GroupLayout footerAdresseLayout = new javax.swing.GroupLayout(footerAdresse);
        footerAdresse.setLayout(footerAdresseLayout);
        footerAdresseLayout.setHorizontalGroup(
            footerAdresseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(footerAdresseLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(footerAdresseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(footerAdresseLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(footerAdresseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))))
                .addContainerGap(151, Short.MAX_VALUE))
        );
        footerAdresseLayout.setVerticalGroup(
            footerAdresseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(footerAdresseLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        footerOmOs.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        footerOmOs.setPreferredSize(new java.awt.Dimension(225, 189));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Om os");

        jLabel8.setText("Vi er den mest moderne biograf i");

        jLabel9.setText("Næstved og omegen.");

        jLabel10.setText("bedste faciliteter");

        jLabel11.setText("Derfor er vi også den biograf som har de");

        javax.swing.GroupLayout footerOmOsLayout = new javax.swing.GroupLayout(footerOmOs);
        footerOmOs.setLayout(footerOmOsLayout);
        footerOmOsLayout.setHorizontalGroup(
            footerOmOsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(footerOmOsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(footerOmOsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(footerOmOsLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(footerOmOsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        footerOmOsLayout.setVerticalGroup(
            footerOmOsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(footerOmOsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        footerSocial.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        footerSocial.setPreferredSize(new java.awt.Dimension(225, 0));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Sociale medier");

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rescources/logo-twitter.png"))); // NOI18N
        jLabel13.setText("Følg os på Twitter");

        facebookLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rescources/facebook-logo.png"))); // NOI18N
        facebookLabel.setText("Følg os på Facebook");

        javax.swing.GroupLayout footerSocialLayout = new javax.swing.GroupLayout(footerSocial);
        footerSocial.setLayout(footerSocialLayout);
        footerSocialLayout.setHorizontalGroup(
            footerSocialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(footerSocialLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(footerSocialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(facebookLabel)
                    .addComponent(jLabel3)
                    .addComponent(jLabel13))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        footerSocialLayout.setVerticalGroup(
            footerSocialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(footerSocialLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(19, 19, 19)
                .addComponent(facebookLabel)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        footerAdmin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        footerAdmin.setPreferredSize(new java.awt.Dimension(225, 0));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Admin");

        jButton1.setText("Admin Panel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout footerAdminLayout = new javax.swing.GroupLayout(footerAdmin);
        footerAdmin.setLayout(footerAdminLayout);
        footerAdminLayout.setHorizontalGroup(
            footerAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(footerAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(footerAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(footerAdminLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        footerAdminLayout.setVerticalGroup(
            footerAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(footerAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout footerPanelLayout = new javax.swing.GroupLayout(footerPanel);
        footerPanel.setLayout(footerPanelLayout);
        footerPanelLayout.setHorizontalGroup(
            footerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(footerPanelLayout.createSequentialGroup()
                .addComponent(footerAdresse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(footerOmOs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(footerSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(footerAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE))
        );
        footerPanelLayout.setVerticalGroup(
            footerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(footerOmOs, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
            .addComponent(footerAdresse, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
            .addComponent(footerSocial, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
            .addComponent(footerAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
            .addComponent(footerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(headerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(headerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(footerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        updateDialog(adminLoginDialog);

        salCombo.removeAll();
        for (Sal sal1 : sale) {
            salCombo.addItem(sal1);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        updateArrays();

        filmUdvalgsPanel.setVisible(true);
        forsidePanel.setVisible(false);
        valgAfPladsPanel.setVisible(false);

        forestillingsPanel.removeAll();

        for (Forestilling forestilling1 : forestillinger) {
            FilmUdvalgPanel forestilling = new FilmUdvalgPanel(forestilling1, valgAfPladsPanel, filmUdvalgsPanel, selectedForestilling);
            forestilling.setVisible(true);
            forestillingsPanel.add(forestilling);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void tilFosidenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tilFosidenButtonActionPerformed
        forsidePanel.setVisible(true);
        valgAfPladsPanel.setVisible(false);
        filmUdvalgsPanel.setVisible(false);
        pladsPanelAntalBilleterComboBox.setSelectedIndex(0);
        pladsPanelTelefonnummerField.setText("");
    }//GEN-LAST:event_tilFosidenButtonActionPerformed

    private void errorDialogButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_errorDialogButtonActionPerformed
        errorDialog.setVisible(false);
        if (errorLabelHeader.getText() == "Succes") {
            adminDialog.setVisible(false);
        }
        if (errorLabelHeader.getText().equals("Bestilling Fuldført")){
            valgAfPladsPanel.setVisible(false);
            forsidePanel.setVisible(true);
            pladsPanelAntalBilleterComboBox.setSelectedIndex(0);
            pladsPanelTelefonnummerField.setText("");
        }
    }//GEN-LAST:event_errorDialogButtonActionPerformed

    private void filmComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filmComboActionPerformed
        updateForestillinger();
    }//GEN-LAST:event_filmComboActionPerformed

    private void dbIndstillingerButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dbIndstillingerButton1ActionPerformed
        try {
            String titel = titelField.getText();
            int spileltid = Integer.parseInt(spilletidField.getText());
            db.addFilm(titel, spileltid);
            File file = jFileChooser1.getSelectedFile();
            File target = new File(System.getProperty("user.dir") + "/src/rescources", titel + ".jpg");
            Files.copy(file.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
            errorLabelHeader.setText("Succes");
            errorLabel1.setText("Filmen " + titel + " blev succesfuldt tilføjet til databasen");
            errorLabel2.setText("Du kan nu tilføje forestillinger med den film");
            errorLabel3.setText("");
            updateDialog(errorDialog);
        } catch (IOException ex) {
            errorLabelHeader.setText("Filen blev ikke fundet");
            errorLabel1.setText("Plakaten du prøvede at tilføje blev ikke fundet");
            errorLabel2.setText("Prøv venligst igen, eller brug en anden fil");
            errorLabel3.setText("");
            updateDialog(errorDialog);
        } catch (SQLException | ClassNotFoundException ex) {
            errorLabelHeader.setText("Der skete en uventet fejæ");
            errorLabel1.setText("Prøv venligst igen, eller kontakt systemadministratoren");
            errorLabel2.setText("Oplys følgende");
            errorLabel3.setText(ex.getMessage());
            updateDialog(errorDialog);
        }
    }//GEN-LAST:event_dbIndstillingerButton1ActionPerformed

    private void spilletidFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_spilletidFieldKeyTyped
        char number = evt.getKeyChar();
        if (!Character.isDigit(number)) {
            evt.consume();
        }
    }//GEN-LAST:event_spilletidFieldKeyTyped

    private void dbIndstillingerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dbIndstillingerButtonActionPerformed
        String pwd = "";
        for (int i = 0; i < dbPasswordField.getPassword().length; i++) {
            pwd = pwd + dbPasswordField.getPassword()[i];
        }

        try {
            db.updateDBConn(dbBrugernavnField.getText(), pwd, dbIPField.getText() + ":" + dbPortField.getText(), dbDatabasenavnField.getText());
            errorLabelHeader.setText("Succes");
            errorLabel1.setText("Du Gjorde Det!");
            errorLabel2.setText("Der er nu forbindelse til en anden database");
            errorLabel3.setText("");
            updateDialog(errorDialog);
        } catch (SQLException | ClassNotFoundException | FileNotFoundException | UnsupportedEncodingException ex) {
            errorLabelHeader.setText("Der skete en fejl");
            errorLabel1.setText("Kontakt systemadministratoren og oplys følgende");
            errorLabel2.setText(ex.getMessage());
            errorLabel3.setText("");
            updateDialog(errorDialog);
        }

        if ("Succes".equals(errorLabelHeader.getText())) {
            dbBrugernavnField.setText("");
            dbIPField.setText("");
            dbDatabasenavnField.setText("");
            dbPasswordField.setText("");
            dbPortField.setText("");
        }

        updateArrays();
    }//GEN-LAST:event_dbIndstillingerButtonActionPerformed

    private void dbPortFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dbPortFieldKeyTyped
        char number = evt.getKeyChar();
        if (!Character.isDigit(number)) {
            evt.consume();
        }
    }//GEN-LAST:event_dbPortFieldKeyTyped

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        updateDialog(fileChooser);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jFileChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser1ActionPerformed
        fileChooser.setVisible(false);
    }//GEN-LAST:event_jFileChooser1ActionPerformed

    private void tilføjForestillingTimeFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tilføjForestillingTimeFieldKeyTyped
        char number = evt.getKeyChar();
        if (!Character.isDigit(number)) {
            evt.consume();
        }
    }//GEN-LAST:event_tilføjForestillingTimeFieldKeyTyped

    private void tilføjForestillingMinutFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tilføjForestillingMinutFieldKeyTyped
        char number = evt.getKeyChar();
        if (!Character.isDigit(number)) {
            evt.consume();
        }
    }//GEN-LAST:event_tilføjForestillingMinutFieldKeyTyped

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        ArrayList<Film> fundendeFilm;
        try {
            fundendeFilm = db.searchForFilm(findFilmField.getText());
            fundendeFilmCombo.removeAll();
            for (Film fundendeFilm1 : fundendeFilm) {
                fundendeFilmCombo.addItem(fundendeFilm1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            java.sql.Date dato = new java.sql.Date(tilføjForestillingDato.getDate().getTime());
            String tidspunkt = tilføjForestillingTimeField.getText() + ":" + tilføjForestillingMinutField.getText();
            Film film = (Film) fundendeFilmCombo.getSelectedItem();
            int film_id = film.getId();
            Sal sal = (Sal) salCombo.getSelectedItem();
            int sal_id = sal.getId();

            db.addForestilling(dato, tidspunkt, film_id, sal_id);

            errorLabelHeader.setText("Succes");
            errorLabel1.setText("Forestillingen med " + film.getTitel() + " blev succesfuldt tilføjet til databasen");
            errorLabel2.setText("Folk kan nu bestille billeter til forestillingen");
            errorLabel3.setText("");
            updateDialog(errorDialog);
        } catch (SQLException | ClassNotFoundException ex) {
            errorLabelHeader.setText("Uventet Fejl");
            errorLabel1.setText("Der opstod en uventet fejl, prøv igen eller");
            errorLabel2.setText("kontakt systemadministratoren og oplys følgende");
            errorLabel3.setText(ex.getMessage());
            updateDialog(errorDialog);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        adminBrugernavnField.requestFocus();
        String userName = "admin";
        String password = "admin";
        String inputPassword = "";
        for (int i = 0; i < adminPasswordField.getPassword().length; i++) {
            inputPassword = inputPassword + adminPasswordField.getPassword()[i];
        }

        if (adminBrugernavnField.getText().equalsIgnoreCase(userName) && inputPassword.equals(password)) {
            updateDialog(adminDialog);
            adminLoginDialog.setVisible(false);
            adminBrugernavnField.setText("");
        } else {
            errorLabel1.setText("Begge inputfelter er forkerte");
            errorLabel2.setText("");
            errorLabel3.setText("");
            updateDialog(errorDialog);

        }
        adminPasswordField.setText("");

    }//GEN-LAST:event_jButton6ActionPerformed

    private void adminPasswordFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_adminPasswordFieldKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            jButton6.doClick();
        }
    }//GEN-LAST:event_adminPasswordFieldKeyPressed

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser1PropertyChange
        if (!(jDateChooser1.getDate() == null)) {
            updateForestillinger();
        }
    }//GEN-LAST:event_jDateChooser1PropertyChange

    private void addSalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSalButtonActionPerformed
        try {
            String name = addSalNavnField.getText();
            int rows = Integer.parseInt(addSalRowsCountField.getText());
            int seats = Integer.parseInt(addSalSeatCountField.getText());
            db.addSal(name, rows, seats);
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addSalButtonActionPerformed

    private void valgAfPladsPanelComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_valgAfPladsPanelComponentShown
        salPanel.repaint();
        pladsPanelTitle.setText(forestillinger.get(selectedForestilling.get(0)).getFilmTitel());
        pladsPanelDato.setText(forestillinger.get(selectedForestilling.get(0)).getDato());
        pladsPanelTidspunkt.setText(forestillinger.get(selectedForestilling.get(0)).getTidspunkt());
    }//GEN-LAST:event_valgAfPladsPanelComponentShown

    private void salPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salPanelMousePressed
        dh.setSeatsPoint(evt.getPoint());
        salPanel.repaint();
    }//GEN-LAST:event_salPanelMousePressed

    private void pladsPanelBestilButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pladsPanelBestilButtonActionPerformed
        updateArrays();
        drawLastSelectedForestilling(salPanel.getGraphics());
        if (!pladsPanelTelefonnummerField.getText().isEmpty()) {
            try {
                boolean succeeded = dh.bestilBilletter(Integer.parseInt(pladsPanelTelefonnummerField.getText()));
                if (succeeded == false){
                    errorLabelHeader.setText("Bestilling fejlede");
                    errorLabel1.setText("Har du husket at vælge pladser?");
                    errorLabel2.setText("Har du indtastet et gyldigt telefonnummer?");
                    errorLabel3.setText("");
                }else{
                    errorLabelHeader.setText("Bestilling Fuldført");
                    errorLabel1.setText("Dato: "+forestillinger.get(selectedForestilling.get(0)).getDato());
                    errorLabel2.setText("Tidspunkt: "+forestillinger.get(selectedForestilling.get(0)).getTidspunkt());
                    errorLabel3.setText("");
                }
            } catch (SQLException | ClassNotFoundException ex) {
                errorLabelHeader.setText("Uventet fejl");
                errorLabel1.setText("Kontakt system administrator");
                errorLabel2.setText("Oplys følgende til administratoren:");
                errorLabel3.setText(ex.getMessage());
            }
        }else{
            errorLabelHeader.setText("Bestilling fejlede");
            errorLabel1.setText("Indtast venligst et gyldigt telefonnumer.");
            errorLabel2.setText("Et gyldigt telefonnummer består af 8 tal.");
            errorLabel3.setText("");
        }
        updateDialog(errorDialog);
    }//GEN-LAST:event_pladsPanelBestilButtonActionPerformed

    private void pladsPanelTelefonnummerFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pladsPanelTelefonnummerFieldKeyTyped
        char number = evt.getKeyChar();
        if (!Character.isDigit(number) || pladsPanelTelefonnummerField.getText().length() >= 8) {
            evt.consume();
        }
    }//GEN-LAST:event_pladsPanelTelefonnummerFieldKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addSalButton;
    private javax.swing.JTextField addSalNavnField;
    private javax.swing.JTextField addSalRowsCountField;
    private javax.swing.JTextField addSalSeatCountField;
    private javax.swing.JTextField adminBrugernavnField;
    private javax.swing.JDialog adminDialog;
    private javax.swing.JDialog adminLoginDialog;
    private javax.swing.JPasswordField adminPasswordField;
    private javax.swing.JTextField dbBrugernavnField;
    private javax.swing.JTextField dbDatabasenavnField;
    private javax.swing.JTextField dbIPField;
    private javax.swing.JButton dbIndstillingerButton;
    private javax.swing.JButton dbIndstillingerButton1;
    private javax.swing.JPasswordField dbPasswordField;
    private javax.swing.JTextField dbPortField;
    private javax.swing.JDialog errorDialog;
    private javax.swing.JButton errorDialogButton;
    private javax.swing.JLabel errorLabel1;
    private javax.swing.JLabel errorLabel2;
    private javax.swing.JLabel errorLabel3;
    private javax.swing.JLabel errorLabelHeader;
    private javax.swing.JLabel facebookLabel;
    private javax.swing.JDialog fileChooser;
    private javax.swing.JComboBox filmCombo;
    private javax.swing.JPanel filmUdvalgsPanel;
    private javax.swing.JScrollPane filmudValgsScroll;
    private javax.swing.JTextField findFilmField;
    private javax.swing.JPanel footerAdmin;
    private javax.swing.JPanel footerAdresse;
    private javax.swing.JPanel footerOmOs;
    private javax.swing.JPanel footerPanel;
    private javax.swing.JPanel footerSocial;
    private javax.swing.JPanel forestillingsPanel;
    private javax.swing.JPanel forsidePanel;
    private javax.swing.JComboBox fundendeFilmCombo;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JComboBox pladsPanelAntalBilleterComboBox;
    private javax.swing.JButton pladsPanelBestilButton;
    private javax.swing.JLabel pladsPanelDato;
    private javax.swing.JTextField pladsPanelTelefonnummerField;
    private javax.swing.JLabel pladsPanelTidspunkt;
    private javax.swing.JLabel pladsPanelTitle;
    private javax.swing.JComboBox salCombo;
    private javax.swing.JPanel salPanel;
    private javax.swing.JTextField spilletidField;
    private javax.swing.JButton tilFosidenButton;
    private com.toedter.calendar.JDateChooser tilføjForestillingDato;
    private javax.swing.JTextField tilføjForestillingMinutField;
    private javax.swing.JTextField tilføjForestillingTimeField;
    private javax.swing.JTextField titelField;
    private javax.swing.JPanel valgAfPladsPanel;
    // End of variables declaration//GEN-END:variables
}
