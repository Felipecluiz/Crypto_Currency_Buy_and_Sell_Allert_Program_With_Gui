package trabfinal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Type;

public class GenericMetods {

    public int variation(String asset) throws Exception {

        ArrayList<String> ValeuBBandsLOW = new ArrayList<String>();
        ArrayList<String> ValeuBBandsHIGH = new ArrayList<String>();

        ArrayList<String> Valuedate = new ArrayList<String>();

        ArrayList<String> ValeuBBandsdate = new ArrayList<String>();

        ArrayList<String> ValueUSD = new ArrayList<String>();

        ////valor absoluto ativo
        URL genericValueURL = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + asset + "&interval=15min&outputsize=full&apikey=YJC40GEVI5PVOITX");

        ////bbands do ativo
        URL BBandsURL = new URL("https://www.alphavantage.co/query?function=BBANDS&symbol=" + asset + "&interval=5min&time_period=5&series_type=close&nbdevup=3&nbdevdn=3&apikey=YJC40GEVI5PVOITX");

        GenericMetods use = new GenericMetods();

        URLConnection BBands = BBandsURL.openConnection();
        BufferedReader bbandsGeneric = new BufferedReader(new InputStreamReader(BBands.getInputStream()));

        String inputMMovel = "";

        ////////////////////////valor do BBANDS low and upper
        while ((inputMMovel = bbandsGeneric.readLine()) != null) {

            if (inputMMovel.contains("Real Lower Band")) {
                inputMMovel = use.ClearBBandsLow(inputMMovel);
                ValeuBBandsLOW.add(inputMMovel);
            }
            if (inputMMovel.contains("Real Upper Band")) {
                inputMMovel = use.ClearBBandsUp(inputMMovel);
                ValeuBBandsHIGH.add(inputMMovel);
            }
            if (inputMMovel.contains("\"2018-") && !inputMMovel.contains("Last Refreshed")) {

                ValeuBBandsdate.add(inputMMovel.substring(9, 25) + ":00");
            }
        }
        //System.err.println("morreu no bbands");
        //System.out.println(ValeuBBandsdate.size()+" bbands date");

        //	for (int i = 0; i < ValeuBBandsdate.size()-1 ;i++){
        //	System.out.println(ValeuBBandsdate.get(i)+" data do bbands");
        //}
        bbandsGeneric.close();
        //System.out.println("roscaca");

        ////url da moeda, lendo
        URLConnection ValueofAsset = genericValueURL.openConnection();
        BufferedReader vAsset = new BufferedReader(new InputStreamReader(ValueofAsset.getInputStream()));
        int cont = 0;

        /////////////////////////////////////////valor do ativo, recebendo em json e gravando no arraylist
        String[] StringRec = null;
        while ((inputMMovel = vAsset.readLine()) != null) {
            //System.err.println(inputMMovel);
            if (inputMMovel.contains("\"2018-") && !inputMMovel.contains("Last Refreshed")) {

                Valuedate.add(inputMMovel.substring(9, 25) + ":00");///data do ativo
            }

            if (inputMMovel.contains("\"4. close\":")) {
                //System.err.println(inputMMovel+" valor da moeda");
                inputMMovel = use.ClearClose(inputMMovel);
                ValueUSD.add(inputMMovel); /////////////valor do ativo              
            }

        }
        //System.err.println(ValueUSD.size()+" valor da moeda");
        //System.out.println(Valuedate.size()+" data do valor");

        vAsset.close();

        //for (int i = 0; i < Valuedate.size()-1 ;i++){
        //	System.out.println(Valuedate.get(i)+" data do ETH");
        //}
        Graph grap = new Graph();
        // grap.setColor();

        ///se o preço for maior que o uuper bound= vende
        ///se o preço for menor que o lower bound=compra
        int stopFor = 0;
        System.err.printf("tamanho %d value data\n", Valuedate.size());
        System.err.printf("tamanho %d bbands data\n", ValeuBBandsdate.size());
        //Graph grap = new Graph();
        for (int i = 0; i < Valuedate.size(); i++) {

            for (int j = 0; j < ValeuBBandsdate.size(); j++) {

                if (Valuedate.get(i).equals(ValeuBBandsdate.get(j))) {
                    float a = Float.parseFloat(ValueUSD.get(i));
                    float b = Float.parseFloat(ValeuBBandsLOW.get(j));
                    float c = Float.parseFloat(ValeuBBandsHIGH.get(j));
                    if (a <= b) {
                        System.out.printf("bbands compra %s\n", asset);
                        //stopFor++;
                        //System.out.println(Valuedate.get(i));
                        //System.out.println(ValeuBBandsdate.get(j));
                        // grap.setColor();
                        break;
                    } else if (a >= c) {
                        System.out.printf("bbands vende %s\n", asset);
                        //stopFor++;
                        //System.out.println(Valuedate.get(i));
                        //System.out.println(ValeuBBandsdate.get(j));
                        //System.out.println(a);
                        //System.out.println(b);
                        //System.out.println(c);
                        //  grap.setColorSell();
                        break;
                    } else {
                        System.out.printf("bbands nada %s\n", asset);
                        //stopFor++;
                        //System.out.println(Valuedate.get(i));
                        //System.out.println(ValeuBBandsdate.get(j));
                        break;

                    }

                }
            }
            if (stopFor != 0) {
                break;
            }

        }

        return 1;
    }

    public void SMA(String coin) throws Exception {

        ArrayList<String> sMA10 = new ArrayList<String>();
        ArrayList<String> sMA40 = new ArrayList<String>();

        ArrayList<String> sMA10date = new ArrayList<String>();
        ArrayList<String> sMA40date = new ArrayList<String>();

        ////sma absoluto ativo
        URL SMA10 = new URL("https://www.alphavantage.co/query?function=SMA&symbol=" + coin + "&interval=1min&time_period=10&series_type=close&apikey=YJC40GEVI5PVOITX");

        URL SMA40 = new URL("https://www.alphavantage.co/query?function=SMA&symbol=" + coin + "&interval=1min&time_period=40&series_type=close&apikey=YJC40GEVI5PVOITX");

        URLConnection SMAcon = SMA10.openConnection();
        BufferedReader SMAGeneric = new BufferedReader(new InputStreamReader(SMAcon.getInputStream()));

        String inputMMovel = "";

        GenericMetods use = new GenericMetods();
        ////////////////////////sma 10

        while ((inputMMovel = SMAGeneric.readLine()) != null) {

            if (inputMMovel.contains("\"SMA\"")) {
                inputMMovel = use.ClearSMA(inputMMovel);
                sMA10.add(inputMMovel);
            }

            if (inputMMovel.contains("\"2018-") && !inputMMovel.contains("Last Refreshed")) {
                sMA10date.add(inputMMovel.substring(9, 25));
            }

        }

        ////sma 40
        URLConnection SMA_40 = SMA40.openConnection();
        BufferedReader SMARead40 = new BufferedReader(new InputStreamReader(SMA_40.getInputStream()));

        ////sma 40
        while ((inputMMovel = SMARead40.readLine()) != null) {

            if (inputMMovel.contains("\"SMA\"")) {
                inputMMovel = use.ClearSMA(inputMMovel);
                sMA40.add(inputMMovel);
            }

            if (inputMMovel.contains("\"2018-") && !inputMMovel.contains("Last Refreshed")) {
                sMA40date.add(inputMMovel.substring(9, 25));
            }

        }

        Graph grap = new Graph();
        grap.clic();
        if (sMA40date.get(0).equals(sMA10date.get(0))) {

            float a = Float.parseFloat(sMA40.get(0));
            //	System.out.printf("sma %s 40 1: %s\n ",coin,sMA40.get(0));
            float b = Float.parseFloat(sMA10.get(0));
            //System.out.printf("sma %s 10 1: %s\n ",coin,sMA10.get(0));

            float c = Float.parseFloat(sMA10.get(1));
            //System.out.printf("sma %s 10 2: %s\n ",coin,sMA10.get(1));
            float d = Float.parseFloat(sMA40.get(1));
            //System.out.printf("sma %s 40 2: %s\n ",coin,sMA40.get(1));
            if (a > b && c > d) {
                System.out.printf("sma comprar %s \n ", coin);

                // grap.setColor();
            } else if (a < b && c < d) {
                System.out.printf("sma vender %s \n ", coin);
                //grap.setColorSell();
            } else {
                System.out.printf("sma nada %s \n ", coin);
            }
        }

    }

    public String ClearSMA(String rec) {

        while (rec.contains("SMA") || rec.contains("\"") || rec.contains(":") || rec.contains(" ") || rec.contains("}") || rec.contains("{")) {
            rec = rec.replace("SMA", "");
            rec = rec.replace("\"", "");
            rec = rec.replace(":", "");
            rec = rec.replace(" ", "");
            rec = rec.replace("{", "");
            rec = rec.replace("}", "");
            //System.err.println(rec);
        }

        return rec;

    }

    public String ClearClose(String rec) {
        rec = rec.replaceFirst("4.", "");

        while (rec.contains("close") || rec.contains("\"") || rec.contains(":") || rec.contains(" ") || rec.contains("}") || rec.contains("{")) {

            rec = rec.replace("close", "");
            rec = rec.replace("\"", "");
            rec = rec.replace(":", "");
            rec = rec.replace(" ", "");
            rec = rec.replace("{", "");
            rec = rec.replace("}", "");
            rec = rec.replace(",", "");
            //System.err.println(rec);
        }

        return rec;

    }

    public String ClearBBandsUp(String rec) {

        while (rec.contains(" ")) {

            rec = rec.replace("RealUpperBand", "");
            rec = rec.replace("\"Real Upper Band\"", "");
            rec = rec.replace("\"", "");
            rec = rec.replace(":", "");
            rec = rec.replace(" ", "");
            rec = rec.replace("{", "");
            rec = rec.replace("}", "");
            rec = rec.replace(",", "");
            //System.err.println(rec);
        }

        return rec;

    }

    public String ClearBBandsLow(String rec) {

        while (rec.contains(" ")) {

            rec = rec.replace("\"Real", "");
            rec = rec.replace("Lower", "");
            rec = rec.replace("Band\"", "");

            rec = rec.replace("\"", "");
            rec = rec.replace(":", "");

            rec = rec.replace("{", "");
            rec = rec.replace("}", "");
            rec = rec.replace(",", "");
            rec = rec.replace(" ", "");

            //System.err.println(rec);
        }

        return rec;

    }

}
