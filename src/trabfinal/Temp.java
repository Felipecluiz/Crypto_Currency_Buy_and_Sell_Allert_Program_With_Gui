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


    Timer timer;
    // int matar=60000;

    public Temp() {
    }

    public Temp(String coin, String compare) {
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

              

                int delay = 2000;   // delay de 5 seg.
                int interval = 6000;  // intervalo de 1 seg.
                // int delay =0;
                //int interval=0;

                timer.scheduleAtFixedRate(new TimerTask() {

                    int i = 0;

                    public void run() {

                        if (GuiTrabFinal.cont == 0) {
                            timer.cancel();
                            System.out.println("matar ando");
                        }

                        if (GuiTrabFinal.cont != 0 && GuiTrabFinal.contMata == 60) {
                            System.out.println("Task scheduled." + i);
                            i++;
                            // colocar tarefas aqui ...
                            GuiTrabFinal.contMata = 0;

                            GenericMetods callMetods = new GenericMetods();

                            if (compare.equals("MMovel")) {
                                try {
                                    callMetods.SMA(coin);
                                } catch (Exception ex) {
                                    System.out.println("deu pau no mmovel do ETH");
                                }

                            } else if (compare.equals("boiler")) {
                                try {
                                    callMetods.variation(coin);
                                } catch (Exception ex) {
                                    System.out.println("deu pau no boiler do ETH");
                                }

                            }

                        }

                    }

                }, 1000, 60000);

                GuiTrabFinal.contMata++;
            }
        }, 1000, 1000);

    }

}

/**
 * Simple demo that uses java.util.Timer to schedule a task to execute once 5
 * seconds have passed.
 */
