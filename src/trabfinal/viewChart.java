/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabfinal;

import java.awt.Color; 
import java.awt.BasicStroke; 
import java.awt.BorderLayout;
import java.awt.Dimension;
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
import java.util.Collections;
public class viewChart extends JFrame {
    
    public viewChart(String a){
        super(a);
    };
    
    public viewChart( String applicationTitle, String chartTitle , ArrayList<Double> a, ArrayList<Double> b, ArrayList<Long> c) {
      super(applicationTitle);
      System.out.println("logo antes do ChartFacctory");
      JFreeChart xylineChart = ChartFactory.createXYLineChart(
         chartTitle ,
         "Category" ,
         "Score" ,
         createDataset2(a,b,c) ,
         PlotOrientation.VERTICAL ,
         true , true , false);
         System.out.println("logo depois do chartFactory");
      ChartPanel chartPanel = new ChartPanel( xylineChart, false );
      chartPanel.setPreferredSize( new Dimension( 640 , 480 ) );
      this.add(chartPanel, BorderLayout.CENTER);
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
        range.setTickUnit(new NumberTickUnit((value1 - value2)/50));
        }else{
            range.setTickUnit(new NumberTickUnit(( value2 -value1)/50 ));
            range.setRange( value1, value2 );
        }
        //range.setTickUnit(new NumberTickUnit((decideUpperBound(b) - decideLowerBound(a))/40));
        //range.setRange(14, 13);
   }
    public viewChart(String applicationTitle, String chartTitle , ArrayList<Double>
            topBand, ArrayList<Double> lowerBand, ArrayList<Double> close , ArrayList<Long> dates){
        super(applicationTitle);
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
         chartTitle ,
         "Category" ,
         "Score" ,
         createDataset3(topBand,lowerBand,close,dates) ,
         PlotOrientation.VERTICAL ,
         true , true , false);
         System.out.println("logo depois do chartFactory bbands");
      ChartPanel chartPanel = new ChartPanel( xylineChart, false );
      chartPanel.setPreferredSize( new Dimension( 640 , 480 ) );
      this.add(chartPanel, BorderLayout.CENTER);
      final XYPlot plot = xylineChart.getXYPlot( );
      
      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
      renderer.setSeriesPaint( 0 , Color.RED );
      renderer.setSeriesPaint( 1 , Color.BLUE );
      renderer.setSeriesPaint( 2 , Color.YELLOW );
      renderer.setSeriesStroke( 0 , new BasicStroke( 1.0f ) );
      renderer.setSeriesStroke( 1 , new BasicStroke( 1.0f ) );
      renderer.setSeriesStroke( 2 , new BasicStroke( 1.0f ) );
      plot.setRenderer( renderer ); 
      setContentPane( chartPanel ); 
      System.out.println("dentro do grafico2");
      NumberAxis domain = (NumberAxis) plot.getDomainAxis( );
      domain.setVerticalTickLabels( true );
      NumberAxis range = (NumberAxis) plot.getRangeAxis( );
        double value1 = decideUpperBoundBBands( topBand, lowerBand, close );
        double value2 = decidLowerBoundBBands( topBand, lowerBand, close );
        
            range.setRange( value2, value1 ); //TODO deal with making the size auto
            range.setTickUnit(new NumberTickUnit((value1 - value2)/50));
        
    }
   
    
   
   private XYDataset createDataset2( ArrayList<Double> valueA, ArrayList<Double> valueB,ArrayList<Long> date ) {
      
      final XYSeries lineA = new XYSeries( "SMA40" );          
       System.out.println(valueA.size( ));
      System.out.println(valueB.size( ));
       System.out.println(date.size( ));
       
       for (int i = 0; i < 50; i++){
         lineA.add( date.get( i ), valueA.get( i ) );
     }
       
      
      final XYSeries lineB = new XYSeries( "SMA10" );          
      
      for (int i = 0; i < 50; i++){
         lineB.add( date.get( i ), valueB.get( i ) );
     }
     // dataset.setTickUnit(new NumberTickUnit(0.4));
     // range.setTickUnit(new NumberTickUnit(0.3));
        
     
     
      final XYSeriesCollection dataset = new XYSeriesCollection( );          
      dataset.addSeries( lineA );          
      dataset.addSeries( lineB );          
      return dataset;
   }
  
   private XYDataset createDataset3( ArrayList<Double> valueA, ArrayList<Double> valueB, ArrayList<Double> valueC,ArrayList<Long> date ){
       
       final XYSeries lineA = new XYSeries( "UpperBand" );          
       System.out.println(valueA.size( ));
      System.out.println(valueB.size( ));
       System.out.println(date.size( ));
       
       for (int i = 0; i < 50; i++){
         lineA.add(date.get( i ), valueA.get( i ));
     }
       
      
      final XYSeries lineB = new XYSeries( "LowerBand" );          
      
      for (int i = 0; i < 50; i++){
         lineB.add(date.get( i ), valueB.get( i ));
     }
      
      final XYSeries lineC = new XYSeries( "Close prices" );          
      
      for (int i = 0; i < 50; i++){
         lineC.add(date.get( i ), valueC.get( i ));
     }

       
        final XYSeriesCollection dataset = new XYSeriesCollection( );
        dataset.addSeries( lineA );       
        dataset.addSeries( lineB ); 
        dataset.addSeries( lineC );
        return dataset;
   }
   
   
   final double decideLowerBound40( ArrayList<Double> valueA, ArrayList<Double> valueB ){
       double minA = Collections.min( valueA );
       double minB = Collections.min( valueB );
       if( minA > minB ){
           return minB;
       }else 
           return minA;
   }
   
   
   final double decideUpperBound40( ArrayList<Double> valueA, ArrayList<Double> valueB ){
       double maxA = Collections.max( valueA );
       double maxB = Collections.max( valueB );
        
       if( maxA > maxB ){
            return maxA;
       }
            return maxB;
     }
   
        final double decideUpperBoundBBands( ArrayList<Double> valueA, ArrayList<Double> valueB, ArrayList<Double> valueC ){
        ArrayList<Double> highest = new ArrayList<>();
        highest.add( Collections.max( valueA ) );
        highest.add( Collections.max( valueB ) );
        highest.add( Collections.max( valueC ) );
        
        return Collections.max(highest);
     }
        final double decidLowerBoundBBands( ArrayList<Double> valueA, ArrayList<Double> valueB, ArrayList<Double> valueC ){
        
        ArrayList<Double> lowest = new ArrayList<>();
        lowest.add( Collections.min( valueA ) );
        lowest.add( Collections.min( valueB ) );
        lowest.add( Collections.min( valueC ) );
        
        return Collections.min( lowest );
     }
        

}
