package paieMaster;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.table.TableModel;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

/**
 * @Titre du projet : La Paie
 * @Heures requise pour le projet : 10 heures
 * @Code du projet : OOJF
 * @Ponderation : 60%
 */
public class detailDePaie extends javax.swing.JFrame {
    //déclaration des variable tableau et instances employé selectionnez
    public ArrayList<employe> ListEmploye = new ArrayList<>();
    public ArrayList<departement> ListDepartement = new ArrayList<>();
    public employe selectedEmp = null;
    
    
    public detailDePaie() {
        initComponents();
        Connection c = null;
        Statement stmt = null;
        Statement stmt1 = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:paieMaster.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            
            stmt1 = c.createStatement();
            ResultSet rs1 = stmt1.executeQuery( "SELECT * FROM DEPARTEMENT;" );
            
            while ( rs1.next() ) {
                String id = rs1.getString("ID_DEPT");
                String  nom = rs1.getString("NOM_DEPT");
                BigDecimal taux = rs1.getBigDecimal("TAUX");
                departement monDep = new departement(id, nom, taux);
                ListDepartement.add(monDep);
            }

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM EMPLOYE;" );
            
            while ( rs.next() ) {
                int id = rs.getInt("NO_EMPLOYEE");
                String  prenom = rs.getString("PRENOM");
                String  nom = rs.getString("NOM");
                String deptNo = rs.getString("DEPT");
                departement myDept = new departement();
                
                Iterator<departement> itr = ListDepartement.iterator();
                while (itr.hasNext()) {
                    departement element = itr.next();
                    if (element.monId.equals(deptNo)){
                        myDept = element;
                    }
                }
                employe monEmp = new employe(id, prenom, nom, myDept);
                //System.out.printf(monEmp.monNom + " - " +  monEmp.getTaux() + " " + "%n");
                ListEmploye.add(monEmp);
            }
        } catch ( ClassNotFoundException | SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        
        //init des entete du tableau
        String[] columnNames = {"No Employé", "Prénom", "Nom"};
        
        // table a 2 dimensions pour affichage des data dans le tableau
        Object[][] data = new Object[ListEmploye.size()][];
        for (int i = 0; i < ListEmploye.size(); i++) {
            data[i] = ListEmploye.get(i).toObjectArray();
        }
        
        // init du tableau avec entete et data
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            data,
            columnNames
        ));
        jScrollPane1.setViewportView(jTable1);
        
        //System.out.printf("valeur chosi:" + value);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
    }
    
    //Click sur table load les valeur dans le formulaire 
     private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {                                     
        int index = jTable1.getSelectedRow();
        TableModel model = jTable1.getModel();
        
        String id = model.getValueAt(index, 0).toString();
        String prenom = model.getValueAt(index, 1).toString();
        String nom = model.getValueAt(index, 2).toString();
        int searchID = Integer.parseInt(id);
        selectedEmp = getSelectedEmp(searchID);
            
        SelectIDTextBox.setText(id);
        SelectedNomTextBox.setText(nom);
        SelectedPrenomTextBox.setText(prenom);
        SelectedTauxTextBox.setText(selectedEmp.getTaux().toString());
        SelectedDeptTextBox.setText(selectedEmp.getDept());
        //System.out.printf(selectedEmp.getDept() + "%n");
        
        if (selectedEmp.getDept().equalsIgnoreCase("VENTES")){
            TotalVenteTextBox.setEditable(true);
            
        } else {
            TotalVenteTextBox.setEditable(false);
            TotalVenteTextBox.setText("0.00");
        }
     }
        
     private employe getSelectedEmp(int searchID){
         employe myEmp = null;
         
         for (employe e : ListEmploye){
            if (e.monId == searchID){
                myEmp = e;
                break;
            }
        }
         return myEmp;
     }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        BoutonCalculerDeduction = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        SelectIDTextBox = new javax.swing.JTextField();
        SelectedNomTextBox = new javax.swing.JTextField();
        SelectedPrenomTextBox = new javax.swing.JTextField();
        SelectedTauxTextBox = new javax.swing.JTextField();
        SelectedDeptTextBox = new javax.swing.JTextField();
        NombreHeureTextBox = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        SalaireBrutTextBox = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        SemaineDeTravailTextBox = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        SalaireOverTextBox = new javax.swing.JTextField();
        salaireBrutTotalTextBox = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        impotProvincialTextBox = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        impotFederalTextBox = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        totalDedctionTextBox = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        RRCTextBox = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        SalaireNetTextBox = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        AssEmploiTextBox = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        AssEmplEmployeurTExtBox = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        TotalVenteTextBox = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        CommissionTextBox = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        AssEmploiTextBoxEmp = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1200, 800));

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel1.setText("MasterPaie - Fleur de Lys");

        jLabel2.setText("liste des employées");

        BoutonCalculerDeduction.setText("Calculé Déduction");
        BoutonCalculerDeduction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonCalculerDeductionActionPerformed(evt);
            }
        });

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        SelectIDTextBox.setEditable(false);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${name}"), SelectIDTextBox, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        SelectedNomTextBox.setEditable(false);

        SelectedPrenomTextBox.setEditable(false);

        SelectedDeptTextBox.setEditable(false);

        jLabel3.setText("nombre d'heures");

        SalaireBrutTextBox.setEditable(false);
        SalaireBrutTextBox.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        jLabel4.setText("Salaire temp régulier");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        SemaineDeTravailTextBox.setText("44");

        jLabel5.setText("Semaine de travail :");

        jLabel6.setText("Salaire temp surplus");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        SalaireOverTextBox.setEditable(false);
        SalaireOverTextBox.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        salaireBrutTotalTextBox.setEditable(false);
        salaireBrutTotalTextBox.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        jLabel7.setText("Salaire total (brut)");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        impotProvincialTextBox.setEditable(false);
        impotProvincialTextBox.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        jLabel8.setText("Impot provincial");

        impotFederalTextBox.setEditable(false);
        impotFederalTextBox.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        jLabel9.setText("Impot fédéral");

        totalDedctionTextBox.setEditable(false);
        totalDedctionTextBox.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        jLabel10.setText("Total déduction");

        RRCTextBox.setEditable(false);
        RRCTextBox.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        jLabel11.setText("RRC");

        SalaireNetTextBox.setEditable(false);
        SalaireNetTextBox.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Salaire NET");

        jLabel13.setText("Assurance Emploi");

        AssEmploiTextBox.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel14.setText("Assurance emploie (employeur)");

        AssEmplEmployeurTExtBox.setEditable(false);
        AssEmplEmployeurTExtBox.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        jLabel16.setText("Total des ventes");

        TotalVenteTextBox.setEditable(false);

        jLabel15.setText("Commission ");

        CommissionTextBox.setEditable(false);
        CommissionTextBox.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        jLabel17.setText("Taux Horaire");

        jLabel18.setText("Assurance RRC (employeur)");

        AssEmploiTextBoxEmp.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(BoutonCalculerDeduction)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel16)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(SelectIDTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(SelectedPrenomTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel3))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(SelectedNomTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(SelectedDeptTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel17))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(23, 23, 23)
                                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(68, 68, 68)
                                                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(AssEmploiTextBoxEmp, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                                .addGap(86, 86, 86))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(AssEmplEmployeurTExtBox, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 88, Short.MAX_VALUE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(SelectedTauxTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                                    .addComponent(NombreHeureTextBox)
                                    .addComponent(TotalVenteTextBox))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(SalaireBrutTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(salaireBrutTotalTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(CommissionTextBox)
                                            .addComponent(SalaireOverTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))))
                                .addGap(48, 48, 48)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(RRCTextBox)
                                    .addComponent(totalDedctionTextBox, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(impotProvincialTextBox)
                                    .addComponent(impotFederalTextBox)
                                    .addComponent(AssEmploiTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(75, 75, 75)
                                            .addComponent(jLabel5)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(SemaineDeTravailTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(SalaireNetTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SelectIDTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SelectedNomTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SelectedTauxTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SelectedDeptTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SalaireBrutTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(SemaineDeTravailTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(impotProvincialTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NombreHeureTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(SalaireOverTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(impotFederalTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(SelectedPrenomTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(RRCTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(TotalVenteTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15)
                        .addComponent(CommissionTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BoutonCalculerDeduction)
                    .addComponent(AssEmploiTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel18)
                    .addComponent(AssEmploiTextBoxEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalDedctionTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(salaireBrutTotalTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(SalaireNetTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(AssEmplEmployeurTExtBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // Calculé les déduction - Click sur bouton
    private void BoutonCalculerDeductionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonCalculerDeductionActionPerformed
        // init variable local
        BigDecimal salaireBrut = new BigDecimal(0);
        BigDecimal salaireBrutOver = new BigDecimal(0);
        BigDecimal salaireBrutTotal = new BigDecimal(0);
        BigDecimal totaldeduction = new BigDecimal(0);
        BigDecimal salaireNet = new BigDecimal(0);
        BigDecimal nbrHeures = new BigDecimal(0);
        BigDecimal totalCommission = new BigDecimal(0);
        BigDecimal nbrHrsLimite = new BigDecimal(0);
        BigDecimal taux = selectedEmp.getTaux();
        Boolean valid = true;
        double totalVentes = 0;
        
        // Validation du nombre d'heures par semaine pour over time
        try {
            nbrHrsLimite = BigDecimal.valueOf(Double.parseDouble((SemaineDeTravailTextBox.getText())));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Vous devez entré un nombre d'heures par semaine valide");
        } finally {
            if (nbrHrsLimite.compareTo(BigDecimal.ZERO)<=0) {
                valid = false;
                JOptionPane.showMessageDialog(null,"Le nombre d'heures par semaine ne peut pas etre zéro ou négatif");
            }
        }
        
        // Validation du nombre d'heure travailler
        try {
            nbrHeures = BigDecimal.valueOf(Double.parseDouble((NombreHeureTextBox.getText())));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Vous devez entré un nombre d'heures travaillé");
            valid = false;
        } finally {
            if (nbrHeures.compareTo(BigDecimal.ZERO)<=0) {
                valid = false;
                JOptionPane.showMessageDialog(null,"Le nombre d'heures travaillé ne peut pas etre zéro ou négatif");
            }
        }
        
        // Validation du taux horaire
        try {
            taux = BigDecimal.valueOf(Double.parseDouble((SelectedTauxTextBox.getText())));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Vous devez entré un salaire employée");
            valid = false;
        } finally {
            if (taux.compareTo(BigDecimal.ZERO)<=0) {
                valid = false;
                JOptionPane.showMessageDialog(null,"Le taux ne peut pas etre zéro ou négatif");
            }
        }
        //BigDecimal taux = selectedEmp.getTaux();
        //System.out.printf(SelectedDeptTextBox.getText());
        
        // Nombre validé procédé avec les calculs
        if (valid) { 
            if (SelectedDeptTextBox.getText().contains("VENTES")) {
             try {
                totalVentes = Double.parseDouble(TotalVenteTextBox.getText());
             } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Vous devez entré un chiffre de ventes");
             }
             
             if (nbrHrsLimite.compareTo(nbrHeures) <= 0){
                salaireBrut = nbrHrsLimite.multiply(taux);
                salaireBrutOver = (new BigDecimal(0));
                totalCommission = commission.calculCommission(totalVentes);
                salaireBrutTotal = salaireBrut.add(totalCommission);
                
            } else { 
                salaireBrut = nbrHeures.multiply(taux);
                totalCommission = commission.calculCommission(totalVentes);
                salaireBrutTotal = salaireBrut.add(totalCommission);

            }   
            } else {
                if (nbrHrsLimite.compareTo(nbrHeures) <= 0){
                    salaireBrut = nbrHrsLimite.multiply(taux);
                    salaireBrutOver = nbrHeures.subtract(nbrHrsLimite).multiply(taux.multiply(new BigDecimal(1.5)));
                    totalCommission = new BigDecimal(0);
                    salaireBrutTotal = salaireBrutOver.add(salaireBrut);
                } else { 
                    salaireBrut = nbrHeures.multiply(taux);
                    salaireBrutTotal = salaireBrut;
                }
            } 
        } else {
            
            //JOptionPane.showMessageDialog(null,"Erreur de traitement validé les données");
        }
        
        
        
        
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);
        df.setGroupingUsed(false);
        String normalresult = df.format(salaireBrut)+"$";
        String overResult = df.format(salaireBrutOver);
        String totalResult = df.format(salaireBrutTotal)+"$";
        SalaireBrutTextBox.setText(normalresult);
        salaireBrutTotalTextBox.setText(totalResult);
        if (nbrHrsLimite.compareTo(nbrHeures) <= 0){
            SalaireOverTextBox.setText(overResult +"$");
        } else {
            SalaireOverTextBox.setText("0.00$");
        }
        
        Impots impotFederal = new Federal(salaireBrutTotal);
        Impots impotProvincial = new Provincial(salaireBrutTotal);
        Deduction rrc = new Deduction(salaireBrut);
        Deduction AssEmpl = new Deduction(salaireBrut);
        
        totaldeduction = impotProvincial.deductionImpot().add(impotFederal.deductionImpot()).add(impotProvincial.deductionImpot()).add(rrc.getDeductionRRC().add(AssEmpl.getDeductionAssEmploie()));
        salaireNet = salaireBrutTotal.subtract(totaldeduction);
        
        impotProvincialTextBox.setText(df.format(impotProvincial.deductionImpot())+"$");
        impotFederalTextBox.setText(df.format(impotFederal.deductionImpot())+"$");
        RRCTextBox.setText(df.format(rrc.getDeductionRRC())+"$");
        AssEmploiTextBoxEmp.setText(df.format(AssEmpl.getDeductionRRC())+"$");
        AssEmploiTextBox.setText(df.format(AssEmpl.getDeductionAssEmploie())+"$");
        AssEmplEmployeurTExtBox.setText(df.format(AssEmpl.getDeductionAssEmploieEmp())+"$");
        totalDedctionTextBox.setText(df.format(totaldeduction)+"$");
        SalaireNetTextBox.setText(df.format(salaireNet)+"$");
        CommissionTextBox.setText(df.format(totalCommission)+"$");
        //System.out.printf("Impot provincial: " + df.format(impotProvincial.deductionImpot()) + "%n");
        //System.out.printf("Impot fédérale: " + df.format(impotFederal.deductionImpot()) + "%n");
        //System.out.printf(impotProvincial.TotalDeduction.toString() + "%n");
        
        
        
    }//GEN-LAST:event_BoutonCalculerDeductionActionPerformed

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jScrollPane1MouseClicked

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
            java.util.logging.Logger.getLogger(detailDePaie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(detailDePaie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(detailDePaie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(detailDePaie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new detailDePaie().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AssEmplEmployeurTExtBox;
    private javax.swing.JTextField AssEmploiTextBox;
    private javax.swing.JTextField AssEmploiTextBoxEmp;
    private javax.swing.JButton BoutonCalculerDeduction;
    private javax.swing.JTextField CommissionTextBox;
    private javax.swing.JTextField NombreHeureTextBox;
    private javax.swing.JTextField RRCTextBox;
    private javax.swing.JTextField SalaireBrutTextBox;
    private javax.swing.JTextField SalaireNetTextBox;
    private javax.swing.JTextField SalaireOverTextBox;
    private javax.swing.JTextField SelectIDTextBox;
    private javax.swing.JTextField SelectedDeptTextBox;
    private javax.swing.JTextField SelectedNomTextBox;
    private javax.swing.JTextField SelectedPrenomTextBox;
    private javax.swing.JTextField SelectedTauxTextBox;
    private javax.swing.JTextField SemaineDeTravailTextBox;
    private javax.swing.JTextField TotalVenteTextBox;
    private javax.swing.JTextField impotFederalTextBox;
    private javax.swing.JTextField impotProvincialTextBox;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField salaireBrutTotalTextBox;
    private javax.swing.JTextField totalDedctionTextBox;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables


}
