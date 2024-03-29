/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalmanfilterapplication;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author NK
 */

public class VisualizationFrame extends javax.swing.JFrame {
    
    private Double[] R; // Real Values
    private Double[] Z; // Measured Values
    private Double[] X; // Filter Values
    private Double[] K; // Kalman Gain
    private Double[] P; // State Noise
    private Double s1; // standard deviation σ1 of system noise
    private Double s2; // standard deviation σ2 of measurement noise
    private Double f; // φ parameter
    private Double rzed; // mean deviation between real and measured values
    private Double rxed; // mean deviation between real and filter values
    boolean fileLoaded; // if real and measured values have already loaded from a *.txt file to R,Z arrays
    boolean filteredOk; // if filter calculations have already completed ... and so X,P,K arrays have values
    
    /**
     * Creates new form VisualizationFrame
     */
    
    public VisualizationFrame() {
        
        initComponents(); // initialize the VisualizationFrame's form
        fileLoaded = false; // initially no file has loaded
        filteredOk = false; // initially no filter calculations have completed
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        VisualizationPanel = new javax.swing.JPanel();
        MenuBar = new javax.swing.JMenuBar();
        FileMenu = new javax.swing.JMenu();
        LoadMeasurementsMenuItem = new javax.swing.JMenuItem();
        EditMenu = new javax.swing.JMenu();
        SetParametersMenuItem = new javax.swing.JMenuItem();
        ViewMenu = new javax.swing.JMenu();
        ShowDeviationValuesMenuItem = new javax.swing.JMenuItem();
        ShowStatisticsMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Kalman Filter Visualization");
        setResizable(false);

        javax.swing.GroupLayout VisualizationPanelLayout = new javax.swing.GroupLayout(VisualizationPanel);
        VisualizationPanel.setLayout(VisualizationPanelLayout);
        VisualizationPanelLayout.setHorizontalGroup(
            VisualizationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1052, Short.MAX_VALUE)
        );
        VisualizationPanelLayout.setVerticalGroup(
            VisualizationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 578, Short.MAX_VALUE)
        );

        FileMenu.setText("File");

        LoadMeasurementsMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        LoadMeasurementsMenuItem.setText("Load Measurements From File...");
        LoadMeasurementsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoadMeasurementsMenuItemActionPerformed(evt);
            }
        });
        FileMenu.add(LoadMeasurementsMenuItem);

        MenuBar.add(FileMenu);

        EditMenu.setText("Edit");

        SetParametersMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        SetParametersMenuItem.setText("Set Parameters...");
        SetParametersMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SetParametersMenuItemActionPerformed(evt);
            }
        });
        EditMenu.add(SetParametersMenuItem);

        MenuBar.add(EditMenu);

        ViewMenu.setText("View");

        ShowDeviationValuesMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        ShowDeviationValuesMenuItem.setText("Show Deviation Values...");
        ShowDeviationValuesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowDeviationValuesMenuItemActionPerformed(evt);
            }
        });
        ViewMenu.add(ShowDeviationValuesMenuItem);

        ShowStatisticsMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK));
        ShowStatisticsMenuItem.setText("Show Statistics...");
        ShowStatisticsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowStatisticsMenuItemActionPerformed(evt);
            }
        });
        ViewMenu.add(ShowStatisticsMenuItem);

        MenuBar.add(ViewMenu);

        setJMenuBar(MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(VisualizationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(VisualizationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
        
    @SuppressWarnings("null")
    private void LoadMeasurementsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoadMeasurementsMenuItemActionPerformed
        // create a file chooser
        JFileChooser chooser = new JFileChooser();
        
        // Removes All Files filter
        chooser.setAcceptAllFileFilterUsed(false);
        // Creates a new filter for .txt files
        FileFilter filter = new FileNameExtensionFilter("Text File (.txt)","txt");
        chooser.addChoosableFileFilter(filter);
        
        // if cancel button in the file chooser is pressed then return from this function
        if(chooser.showOpenDialog(null) == JFileChooser.CANCEL_OPTION){
            return;
        }
        
        // get the selected file
        File file = chooser.getSelectedFile();
        
        ArrayList<Double> ListR = new ArrayList<>();
        ArrayList<Double> ListZ = new ArrayList<>();
        
        
        Scanner scanfile;
        Scanner scanline;
        
        try {
            scanfile = new Scanner(file);
            // scan file for lines
            while(scanfile.hasNextLine()){

                String line = scanfile.nextLine();
                scanline = new Scanner(line);
                scanline.useDelimiter(" "); // delimeter between scans in a line is space " "

                // scan each line for Doubles
                int i = 0;
                while (scanline.hasNext()) {

                    switch (i++) {
                        case 0:
                            ListR.add(Double.parseDouble(scanline.next())); // the first double of line is added to ListR
                            break;
                        case 1:
                            ListZ.add(Double.parseDouble(scanline.next())); // the second double of line is added to ListΖ
                            break;
                        default:
                            break;
                    }
                }
                
                scanline.close();
            }
            scanfile.close();
        }
        catch (FileNotFoundException | NumberFormatException ex) {
            
            ErrorsJFrame errorsJFrame = new ErrorsJFrame(ex.toString());
            errorsJFrame.setVisible(true);
            return;
        }
        
        // if the file has correct and adequate values for filter calculations
        if(ListR.size()==ListZ.size() && ListR.size()>1){
            int size = ListR.size();
            R = ListR.toArray(new Double[size]); // conversion from List to Array
            Z = ListZ.toArray(new Double[size]); // conversion from List to Array
            fileLoaded = true; // real and measured values have loaded from a *.txt file to R,Z arrays
            X = new Double[size];
            K = new Double[size];
            P = new Double[size];
                
            // create a SetParamsFrame to set parameters ... i have created my own constructor...see there!
            SetParamsFrame PFrame = new SetParamsFrame(this,s1,s2,f,fileLoaded);
            // hide VisualizationFrame
            this.setVisible(false);
            // show SetParamsFrame
            PFrame.setVisible(true);
        }
        else {
            ErrorsJFrame errorsJFrame = new ErrorsJFrame("The measurements' file does not follow the specified format!\nMaybe a real value or a measurement is missing.\nThe file must contain at least two lines of values!");
            errorsJFrame.setVisible(true);
        }
            
    }//GEN-LAST:event_LoadMeasurementsMenuItemActionPerformed

    private void SetParametersMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SetParametersMenuItemActionPerformed
        // create a SetParamsFrame
        SetParamsFrame PFrame = new SetParamsFrame(this,s1,s2,f,fileLoaded); 
        // hide VisualizationFrame
        this.setVisible(false);
        // show VisualizationFrame
        PFrame.setVisible(true);
    }//GEN-LAST:event_SetParametersMenuItemActionPerformed

    private void ShowDeviationValuesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowDeviationValuesMenuItemActionPerformed
        // create a DeviationFrame...i have created my own constructor...see there!
        DeviationFrame DFrame = new DeviationFrame(rzed,rxed);
        // show DeviationFrame
        DFrame.setVisible(true);
    }//GEN-LAST:event_ShowDeviationValuesMenuItemActionPerformed

    private void ShowStatisticsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowStatisticsMenuItemActionPerformed
        // create StatisticsFrame
        StatisticsFrame SFrame = new StatisticsFrame();
        // if filter calculations have already completed. This means that P,K arrays have values
        if(filteredOk){
            for(int i=1; i<P.length ; i++){
                SFrame.getStateNoiseTextArea().append(P[i].toString()+"\n");
            }
            for(int i=1; i<K.length ; i++){
                SFrame.getKalmanGainTextArea().append(K[i].toString()+"\n");
            }
        }
        // show StatisticsFrame
        SFrame.setVisible(true);
    }//GEN-LAST:event_ShowStatisticsMenuItemActionPerformed

    public void setS1(Double s1) {
        this.s1 = s1;
    }

    public void setS2(Double s2) {
        this.s2 = s2;
    }

    public void setF(Double f) {
        this.f = f;
    }
    
    public void FilterCalculations(){
        X[0] = Z[0];
        P[0] = Math.pow(s2,2); // here i consider that the initial state error comes from measurement noise
        for(int i=1; i < R.length ; i++){
            // first step
            X[i] = f*X[i-1];
            P[i] = f*P[i-1]*f+Math.pow(s1,2);
            // second step
            K[i] = P[i]/(P[i]+Math.pow(s2,2));
            X[i] = X[i]+K[i]*(Z[i]-X[i]);
            P[i] = (1-K[i])*P[i];
        }
        filteredOk = true;
    }
    
    public void EukleidianDistanceCalculations(){
        double sum = 0;
        for(int i = 0; i < R.length ; i++){
            sum += Math.abs(R[i]-Z[i]);
        }
        rzed = sum/(R.length + 1);
        
        sum = 0;
        // here i consider that X[0] is not a filter value so i start from i=1
        for(int i = 1; i < R.length ; i++){
            sum += Math.abs(R[i]-X[i]);
        }
        rxed = sum/R.length;
    }
    
    /* Here to create Visualization function's code i used information and code from next sites:
      1)  http://www.codejava.net/java-se/graphics/using-jfreechart-to-draw-xy-line-chart-with-xydataset
      2)  http://www.java2s.com/Code/Java/Chart/JFreeChartLineChartDemo6.htm
    
      Also from here i downloaded the latest version jfree libraries (1.0.19) and inserted them into my netbeans project:
          http://sourceforge.net/projects/jfreechart/files/1.%20JFreeChart/
    */
    public void Visualization(){
        // create dataset...
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries RLine = new XYSeries("Real Values",false,false);
        XYSeries ZLine = new XYSeries("Measurements Values",false,false);
        XYSeries XLine = new XYSeries("Filter Values",false,false);
        
        for(int i = 0; i < R.length ; i++){
            RLine.add(i,R[i]);
            ZLine.add(i,Z[i]);
            XLine.add(i,X[i]);
        }
        
        dataset.addSeries(RLine);
        dataset.addSeries(ZLine);
        dataset.addSeries(XLine);
        
        // create the chart...
        String chartTitle = "";
        String xAxisLabel = "Time";
        String yAxisLabel = "Power";
        
        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,xAxisLabel,yAxisLabel,dataset);
        // create the chartpanel and set its size equal to visualizationpanel's size
        ChartPanel chartpanel = new ChartPanel(chart);
        chartpanel.setSize(VisualizationPanel.getSize());
        
        // get a reference to the plot for further customization...
        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0,Color.BLACK);
        renderer.setSeriesPaint(1,Color.RED);
        renderer.setSeriesPaint(2,Color.BLUE);
        renderer.setSeriesShapesVisible(0,true);
        renderer.setSeriesShapesVisible(1,true);
        renderer.setSeriesShapesVisible(2,true);
        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.DARK_GRAY);
        //plot.setRangeGridlinesVisible(false);
        //plot.setRangeGridlinePaint(Color.BLACK);
        //plot.setDomainGridlinesVisible(false);
        //plot.setDomainGridlinePaint(Color.BLACK);

        // add chartpanel to visualizationpanel and refresh
        VisualizationPanel.removeAll();
        VisualizationPanel.add(chartpanel, BorderLayout.CENTER);
        VisualizationPanel.validate();
    }
    
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
            java.util.logging.Logger.getLogger(VisualizationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisualizationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisualizationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisualizationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new VisualizationFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu EditMenu;
    private javax.swing.JMenu FileMenu;
    private javax.swing.JMenuItem LoadMeasurementsMenuItem;
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JMenuItem SetParametersMenuItem;
    private javax.swing.JMenuItem ShowDeviationValuesMenuItem;
    private javax.swing.JMenuItem ShowStatisticsMenuItem;
    private javax.swing.JMenu ViewMenu;
    private javax.swing.JPanel VisualizationPanel;
    // End of variables declaration//GEN-END:variables

}
