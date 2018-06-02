/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabfinal;

import java.awt.Color; 
import java.awt.BasicStroke; 
import java.util.ArrayList;

import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 
import org.jfree.chart.plot.XYPlot; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

public class viewChart extends ApplicationFrame {

    
   public viewChart( String applicationTitle, String chartTitle ,ArrayList<Double> a, ArrayList<Double> b,ArrayList<Long> c) {
      super(applicationTitle);
      
      JFreeChart xylineChart = ChartFactory.createXYLineChart(
         chartTitle ,
         "Category" ,
         "Score" ,
         createDataset(a,b,c) ,
         PlotOrientation.VERTICAL ,
         true , true , false);
         
      ChartPanel chartPanel = new ChartPanel( xylineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
      final XYPlot plot = xylineChart.getXYPlot( );
      
      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
      renderer.setSeriesPaint( 0 , Color.RED );
      renderer.setSeriesPaint( 1 , Color.GREEN );
      renderer.setSeriesPaint( 2 , Color.YELLOW );
      renderer.setSeriesStroke( 0 , new BasicStroke( 1.0f ) );
      renderer.setSeriesStroke( 1 , new BasicStroke( 1.0f ) );
      renderer.setSeriesStroke( 2 , new BasicStroke( 1.0f ) );
      plot.setRenderer( renderer ); 
      setContentPane( chartPanel ); 
      
        NumberAxis domain = (NumberAxis) plot.getDomainAxis();
      //  domain.setRange(23, 24);
       // domain.setTickUnit(new NumberTickUnit(0.1));
        domain.setVerticalTickLabels(true);
        NumberAxis range = (NumberAxis) plot.getRangeAxis();
        range.setRange(23.6, 23.7); //TODO deal with making the size auto
        range.setTickUnit(new NumberTickUnit(0.01));
   }
   
    
   
   private XYDataset createDataset(ArrayList<Double> valueA, ArrayList<Double> valueB,ArrayList<Long> date) {
      
      final XYSeries lineA = new XYSeries( "SMA40" );          
       System.out.println(valueA.size());
      System.out.println(valueB.size());
       System.out.println(date.size());
       
       for (int i = 0; i < 40; i++){
         lineA.add(date.get(i), valueA.get(i));
     }
       
      
      final XYSeries lineB = new XYSeries( "SMA10" );          
      
      for (int i = 0; i < 40; i++){
         lineB.add(date.get(i), valueB.get(i));
     }
     // dataset.setTickUnit(new NumberTickUnit(0.4));
     // range.setTickUnit(new NumberTickUnit(0.3));
        
     
     
      final XYSeriesCollection dataset = new XYSeriesCollection( );          
      dataset.addSeries(lineA);          
      dataset.addSeries(lineB);          
      return dataset;
   }

//   public static void main( String[ ] args ) {
//      viewChart chart = new viewChart("Browser Usage Statistics",
//         "Which Browser are you using?");
//      chart.pack( );          
//      RefineryUtilities.centerFrameOnScreen( chart );          
//      chart.setVisible( true ); 
//   }
}