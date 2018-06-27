/*


 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabfinal;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 *
 */

public class Temp {
Calc Mmovel = new Calc();
Timer timer;
    
    public Temp(){
}
    public Temp(int seconds,String coin,String compare) {
        
        
        
         int delay = seconds;   // delay de 5 seg.
         int interval = 30000;  // intervalo de 1 seg.
         Timer timer = new Timer();
           

           
         
         
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = 0;
            
            public void run() {
              
                System.out.println("Task scheduled."+i);
                i++;
                // colocar tarefas aqui ...
                if ( GuiTrabFinal.cont > 0) {
                    timer.cancel();
                    timer.purge();
                       return;
                     }

                
                StandardDeviation Bband = new StandardDeviation();
                
                
                //use.getButtonGroup1().isSelected(use.getButtonGroup1().getSelection())
               // System.out.println(use.getjRadioButton4());
 
                       
               if(coin.equals("ETH")){
              
                   if(compare.equals("MMovel")){
                       try {
                           Mmovel.calcMMETH();
                       } catch (Exception ex) {
                           ex.printStackTrace();
                       }
                       
                       
                   }
                   else if(compare.equals("boiler")){
                       try {
                           Bband.variation();
                       } catch (Exception ex) {
                           ex.printStackTrace();
                       }
                       
                   }
                   
               }
                     
                if(coin.equals("SIA")){
              
                   if(compare.equals("MMovel")){
                       try {
                           Mmovel.calcMMSIA();
                       } catch (Exception ex) {
                           ex.printStackTrace();
                       }
                       
                   }
                   else if(compare.equals("boiler")){
                       try {
                           Bband.variationSIA();
                       } catch (Exception ex) {
                          System.out.println("deu pau no boilinger do SIA");
                       }
                   }
                   
               }
              
                
                //System.out.println(use.getButtonGroup2().getSelection().getActionCommand());
                
                
            }
        }, delay, interval);
          
        }
       
 
   //  timer.cancel();



}



