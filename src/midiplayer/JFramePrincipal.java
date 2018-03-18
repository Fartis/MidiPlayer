/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package midiplayer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Manuel David Villalba Escamilla
 */
public class JFramePrincipal extends javax.swing.JFrame {
    
    ArrayList<File> bibliotecaMidi = new ArrayList<>();
    DefaultListModel<String> model = new DefaultListModel<>();
    MidiPlayer reproductor;
    int midiActual = -1;

    /**
     * Creates new form JFramePrincipal
     */
    public JFramePrincipal() {
        initComponents();
        jListMidis.setModel(model);
        
        jListMidis.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                midiActual =  jListMidis.getSelectedIndex();        
                if (evt.getClickCount() == 2) {

                    // Double-click detected
                    reproducirAction(2);
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonPlay = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButtonAbrirFile = new javax.swing.JButton();
        jLabelNameFichero = new javax.swing.JLabel();
        jLabelEstado = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListMidis = new javax.swing.JList();
        jButtonAbrirDirectory = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonPlay.setText("Reproducir");
        jButtonPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlayActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("MidiPlayer");

        jButtonAbrirFile.setText("Abrir");
        jButtonAbrirFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAbrirFileActionPerformed(evt);
            }
        });

        jLabelNameFichero.setText("No se ha seleccionado un fichero");

        jListMidis.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jListMidis);

        jButtonAbrirDirectory.setText("Abrir carpeta");
        jButtonAbrirDirectory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAbrirDirectoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonAbrirFile, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonAbrirDirectory))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonPlay)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelEstado))
                            .addComponent(jLabelNameFichero))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonPlay)
                    .addComponent(jLabelEstado))
                .addGap(18, 18, 18)
                .addComponent(jLabelNameFichero)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAbrirFile)
                    .addComponent(jButtonAbrirDirectory))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAbrirFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAbrirFileActionPerformed
        // TODO add your handling code here:
        //Creamos el objeto JFileChooser
        JFileChooser fc=new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("MIDI File","mid");
        fc.setFileFilter(filter);

        //Abrimos la ventana, guardamos la opcion seleccionada por el usuario
        int seleccion=fc.showOpenDialog(this);

        //Si el usuario, pincha en aceptar
        if(seleccion==JFileChooser.APPROVE_OPTION){

            //Seleccionamos el fichero
            this.bibliotecaMidi.add(fc.getSelectedFile());
            
            model = new DefaultListModel<>();

            for (File fileMidi : bibliotecaMidi) {
                model.addElement(fileMidi.getName());
            }
            
            jListMidis.setModel(model);
            
        }
    }//GEN-LAST:event_jButtonAbrirFileActionPerformed

    private void jButtonPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPlayActionPerformed
        reproducirAction(1);
    }//GEN-LAST:event_jButtonPlayActionPerformed

    private void jButtonAbrirDirectoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAbrirDirectoryActionPerformed
        // TODO add your handling code here:
        //Creamos el objeto JFileChooser
        JFileChooser fc=new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("MIDI File","mid");
        fc.setFileFilter(filter);
        fc.setMultiSelectionEnabled(true);

        //Abrimos la ventana, guardamos la opcion seleccionada por el usuario
        int seleccion=fc.showOpenDialog(this);

        //Si el usuario, pincha en aceptar
        if(seleccion==JFileChooser.APPROVE_OPTION){
            
            File[] files = fc.getSelectedFiles();

            //Seleccionamos el fichero
            for (File fileMidi : files) {
                this.bibliotecaMidi.add(fileMidi);
            }
            
            model = new DefaultListModel<>();

            for (File fileMidi : bibliotecaMidi) {
                model.addElement(fileMidi.getName());
            }
            
            jListMidis.setModel(model);
            
        }
    }//GEN-LAST:event_jButtonAbrirDirectoryActionPerformed

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
            java.util.logging.Logger.getLogger(JFramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new JFramePrincipal().setVisible(true);
        });
    }
    
    public void reproducirAction(int procedencia){
        if(midiActual<0){
            jLabelEstado.setText("No se ha seleccionado ninguna canción");
        }else{
            if(reproductor!=null){
                if(procedencia == 2){
                    reproductor.stop();
                }
                if(reproductor.isPaused() || procedencia == 2){
                    reproductor.reproducir(reproductor.getSequence(bibliotecaMidi.get(midiActual).getAbsolutePath().toLowerCase()));
                    this.jLabelNameFichero.setText(this.bibliotecaMidi.get(midiActual).getAbsolutePath());
                    jLabelEstado.setText("Play");
                }else{
                    reproductor.stop();
                    jLabelEstado.setText("Stop");
                }
            }else{            
                try {
                    // TODO add your handling code here:
                    reproductor = new MidiPlayer(bibliotecaMidi.get(midiActual));
                    reproductor.reproducir(reproductor.getSequence(bibliotecaMidi.get(midiActual).getAbsolutePath().toLowerCase()));                
                    this.jLabelNameFichero.setText(this.bibliotecaMidi.get(midiActual).getAbsolutePath());
                    jLabelEstado.setText("Play");
                } catch (MidiUnavailableException ex) {
                    jLabelEstado.setText(ex.getMessage());
                } catch (InvalidMidiDataException ex) {
                    jLabelEstado.setText(ex.getMessage());
                } catch (IOException ex) {
                    jLabelEstado.setText(ex.getMessage());
                }
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAbrirDirectory;
    private javax.swing.JButton jButtonAbrirFile;
    private javax.swing.JButton jButtonPlay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelEstado;
    private javax.swing.JLabel jLabelNameFichero;
    private javax.swing.JList jListMidis;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
