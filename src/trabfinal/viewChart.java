/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabfinal;

import java.awt.Color; 
import java.awt.BasicStroke; 
import java.util.ArrayList;
import javax.swing.JFrame;
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

public class viewChart extends JFrame {

    
   public viewChart( String applicationTitle, String chartTitle , ArrayList<Double> a, ArrayList<Double> b, ArrayList<Long> c) {
      super(applicationTitle);
      System.out.println("logo antes do ChartFacctory");
      JFreeChart xylineChart = ChartFactory.createXYLineChart(
         chartTitle ,
         "Category" ,
         "Score" ,
         createDataset(a,b,c) ,
         PlotOrientation.VERTICAL ,
         true , true , false);
         System.out.println("logo depois do chartFactory");
      ChartPanel chartPanel = new ChartPanel( xylineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
      final XYPlot plot = xylineChart.getXYPlot( );
      
      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
      renderer.setSeriesPaint( 0 , Color.RED );
      renderer.setSeriesPaint( 1 , Color.BLUE );
      //renderer.setSeriesPaint( 2 , Color.YELLOW );
      renderer.setSeriesStroke( 0 , new BasicStroke( 1.0f ) );
      renderer.setSeriesStroke( 1 , new BasicStroke( 1.0f ) );
      //renderer.setSeriesStroke( 2 , new BasicStroke( 1.0f ) );
      plot.setRenderer( renderer ); 
      setContentPane( chartPanel ); 
      System.out.println("dentro do grafico");
        NumberAxis domain = (NumberAxis) plot.getDomainAxis();
      //  domain.setRange(23, 24);
       // domain.setTickUnit(new NumberTickUnit(0.1));
        domain.setVerticalTickLabels(true);
        NumberAxis range = (NumberAxis) plot.getRangeAxis();
        double value1 = decideUpperBound40(a,b);
        double value2 = decideLowerBound40(a,b);
        if( value1 > value2 ){
            range.setRange( value2, value1 ); //TODO deal with making the size auto
        range.setTickUnit(new NumberTickUnit((value1 - value2)/40));
        }else{
            range.setTickUnit(new NumberTickUnit(( value2 -value1)/40 ));
            range.setRange( value1, value2 );
        }
        //range.setTickUnit(new NumberTickUnit((decideUpperBound(b) - decideLowerBound(a))/40));
        //range.setRange(14, 13);
   }
   
    
   
   private XYDataset createDataset(ArrayList<Double> valueA, ArrayList<Double> valueB,ArrayList<Long> date) {
      
      final XYSeries lineA = new XYSeries( "SMA40" );          
       System.out.println(valueA.size());
      System.out.println(valueB.size());
       System.out.println(date.size());
       
       for (int i = 0; i < 50; i++){
         lineA.add(date.get(i), valueA.get(i));
     }
       
      
      final XYSeries lineB = new XYSeries( "SMA10" );          
      
      for (int i = 0; i < 50; i++){
         lineB.add(date.get(i), valueB.get(i));
     }
     // dataset.setTickUnit(new NumberTickUnit(0.4));
     // range.setTickUnit(new NumberTickUnit(0.3));
        
     
     
      final XYSeriesCollection dataset = new XYSeriesCollection( );          
      dataset.addSeries(lineA);          
      dataset.addSeries(lineB);          
      return dataset;
   }
   
   final double decideLowerBound40(ArrayList<Double> valueA, ArrayList<Double> valueB){
       
       double highestValueA = valueA.get(0);
       for (int i = 0; i < 50; i++){
            if( highestValueA < valueA.get(i) ){
                highestValueA = valueA.get(i);
              }
       }
        

       double highestValueB = valueB.get(0);
       for (int i = 0; i < 50; i++){
            if( highestValueB < valueB.get(i) ){
                highestValueB = valueB.get(i);
              }
       }
       if( highestValueB > highestValueA ){
        return highestValueB;  
       }else{ 
           return highestValueA;
       }
       }
   
   
   
   final double decideUpperBound40(ArrayList<Double> valueA, ArrayList<Double> valueB){
       double lowestValueA = valueA.get(0);
       for (int i = 0; i < 50; i++){
            if( lowestValueA > valueA.get(i) ){
                lowestValueA = valueA.get(i);
              }  
       }
          double lowestValueB = valueB.get(0);
       for (int i = 0; i < 50; i++){
            if( lowestValueB > valueB.get(i) ){
                lowestValueB = valueB.get(i);
              }  
       }
        if( lowestValueB < lowestValueA ){
            return lowestValueB;
        }else {
        return lowestValueA;      
        }
       
     }
   }

//   public static void main( String[ ] args ) {
//      viewChart chart = new viewChart("Browser Usage Statistics",
//         "Which Browser are you using?");
//      chart.pack( );          
//      RefineryUtilities.centerFrameOnScreen( chart );          
//      chart.setVisible( true ); 
//   }

