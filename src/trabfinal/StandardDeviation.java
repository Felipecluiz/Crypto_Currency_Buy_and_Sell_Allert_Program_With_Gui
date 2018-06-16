package trabfinal;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import javax.swing.JFrame;
import org.jfree.ui.RefineryUtilities;

public class StandardDeviation {

	public int variation() throws Exception {

		ArrayList <String> ValeuBBandsLOW = new ArrayList<String>();
		ArrayList <String> ValeuBBandsHIGH = new ArrayList<String>();

		ArrayList <String> ValueETHdate = new ArrayList<String>();

		ArrayList <String> ValeuBBandsdate = new ArrayList<String>();

		ArrayList <String> ValueETHUSD = new ArrayList<String>();








		URL etherURL = new URL("https://www.alphavantage.co/query?"
				+ "function=DIGITAL_CURRENCY_INTRADAY&symbol=ETH&market=USD&apikey=YJC40GEVI5PVOITX&datatype=csv");

		URL BBandsURL = new URL("https://www.alphavantage.co/query?function=BBANDS&symbol=ETH&interval=5min&time_period=5&series_type=close&nbdevup=3&nbdevdn=3&apikey=YJC40GEVI5PVOITX");




		URLConnection BBands = BBandsURL.openConnection();
		BufferedReader bbandsETH = new BufferedReader(new InputStreamReader(BBands.getInputStream()));

		String inputMMovel = "";

		String rosca = "";
		int conta = 0;

		////////////////////////valor do BBANDS low and upper
		while ((inputMMovel = bbandsETH.readLine()) != null) {


			if (inputMMovel.contains("Real Lower Band")){
				ValeuBBandsLOW.add(inputMMovel.substring(32,39));               
			}
			if (inputMMovel.contains("Real Upper Band")){
				ValeuBBandsHIGH.add(inputMMovel.substring(32,39));               
			}
			if (inputMMovel.contains("\"2018-") && !inputMMovel.contains("Last Refreshed")){
				
				ValeuBBandsdate.add(inputMMovel.substring(9,25)+":00");
			}
		}


		//for (int i = 0; i < ValeuBBandsdate.size()-1 ;i++){
		//	System.out.println(ValeuBBandsdate.get(i)+" data do bbands");
//
		//}


		bbandsETH.close();
		//System.out.println("roscaca");
		/////////////////////////////////////////valor do eth
		URLConnection etherCon = etherURL.openConnection();
		BufferedReader etherRead = new BufferedReader(new InputStreamReader(etherCon.getInputStream()));
		int cont=0;
		String[] StringRec=null;
		while ((inputMMovel = etherRead.readLine()) != null) {

			if(cont>=1) {
				StringRec = inputMMovel.split(",");
				
				ValueETHdate.add(StringRec[0]);///data do eth
				ValueETHUSD.add(StringRec[1]);///valor do eth em dolar
			}

			cont++;
		}
		System.out.println("gravou no eth");
		cont=0;
		etherRead.close();
		//for (int i = 0; i < ValueETHdate.size()-1 ;i++){
			//System.out.println(ValueETHdate.get(i)+" data do ETH");

		//}



		///se o preço for maior que o uuper bound= vende
		///se o preço for menor que o lower bound=compra
                
                  
                viewChart chart = new viewChart("Chart", "eth",cleanValues(ValeuBBandsLOW),cleanValues(ValeuBBandsHIGH),cleanValues(ValueETHUSD),cleanDate(ValueETHdate));
                                chart.setVisible(false);
                                chart.pack( );          
                                RefineryUtilities.centerFrameOnScreen( chart );          
                                chart.setVisible( true ); //TODO closes last chart and opens new, or updates old one 
                                chart.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
                                
                
		System.out.println(ValueETHdate.get(0));
		System.out.println(ValeuBBandsdate.get(0));
		int stopFor=0;
		for(int i=0;i < ValueETHdate.size();i++) {
		
			for(int j=0;j < ValeuBBandsdate.size();j++) {
				
				
				if(ValueETHdate.get(i).equals(ValeuBBandsdate.get(j))){
				float a=Float.parseFloat(ValueETHUSD.get(i));
				float b=Float.parseFloat(ValeuBBandsLOW.get(j));
				float c=Float.parseFloat(ValeuBBandsHIGH.get(j));
				if(a <= b) {
					System.out.println("compra eth");
					stopFor++;
					System.out.println(ValueETHdate.get(i));
					System.out.println(ValeuBBandsdate.get(j));
					break;
				}
				else if(a >= c) {
					System.out.println("vende eth");
					stopFor++;
					System.out.println(ValueETHdate.get(i));
					System.out.println(ValeuBBandsdate.get(j));
					System.out.println(a);
					System.out.println(b);
										System.out.println(c);
					break;
				}
				else {
					System.out.println("nada eth");
					stopFor++;
					System.out.println(ValueETHdate.get(i));
					System.out.println(ValeuBBandsdate.get(j));
					break;
					
						}

				}
			}
			if(stopFor!=0) {
				break;
			}

		}


		



		return 1;
	}


	
	
	
	
	
	
	public int variationSIA() throws Exception {

		ArrayList <String> ValeuBBandsLOW = new ArrayList<String>();
		ArrayList <String> ValeuBBandsHIGH = new ArrayList<String>();

		ArrayList <String> ValueSIAdate = new ArrayList<String>();

		ArrayList <String> ValeuBBandsdate = new ArrayList<String>();

		ArrayList <String> ValueSIAUSD = new ArrayList<String>();








		URL siaURL = new URL("https://www.alphavantage.co/query?function=DIGITAL_CURRENCY_INTRADAY&symbol=SC&market=EUR&apikey=YJC40GEVI5PVOITX&datatype=csv");

		URL BBandsURL = new URL("https://www.alphavantage.co/query?function=BBANDS&symbol=SC&interval=5min&time_period=5&series_type=close&nbdevup=3&nbdevdn=3&apikey=YJC40GEVI5PVOITX");




		URLConnection BBands = BBandsURL.openConnection();
		BufferedReader bbandsETH = new BufferedReader(new InputStreamReader(BBands.getInputStream()));

		String inputMMovel = "";

		String rosca = "";
		int conta = 0;

		////////////////////////valor do BBANDS low and upper
		while ((inputMMovel = bbandsETH.readLine()) != null) {


			if (inputMMovel.contains("Real Lower Band")){
				ValeuBBandsLOW.add(inputMMovel.substring(32,39));               
			}
			if (inputMMovel.contains("Real Upper Band")){
				ValeuBBandsHIGH.add(inputMMovel.substring(32,39));               
			}
			if (inputMMovel.contains("\"2018-") && !inputMMovel.contains("Last Refreshed")){
				
				ValeuBBandsdate.add(inputMMovel.substring(9,25)+":00");
			}
		}


		//for (int i = 0; i < ValeuBBandsdate.size()-1 ;i++){
		//	System.out.println(ValeuBBandsdate.get(i)+" data do bbands");
//
		//}


		bbandsETH.close();
		//System.out.println("roscaca");
		/////////////////////////////////////////valor da SIA
		URLConnection siaCon = siaURL.openConnection();
		BufferedReader siaRead = new BufferedReader(new InputStreamReader(siaCon.getInputStream()));
		int cont=0;
		String[] StringRec=null;
		while ((inputMMovel = siaRead.readLine()) != null) {

			if(cont>=1) {
				StringRec = inputMMovel.split(",");
				
				ValueSIAdate.add(StringRec[0]);///data do sia
				ValueSIAUSD.add(StringRec[1]);///valor do sia em dolar
			}

			cont++;
		}

		cont=0;
		siaRead.close();
	


		///se o preço for maior que o uuper bound= vende
		///se o preço for menor que o lower bound=compra
              
		System.out.println(ValueSIAdate.get(0));
		System.out.println(ValeuBBandsdate.get(0));
		int stopFor=0;
		for(int i=0;i < ValueSIAdate.size();i++) {
		
			for(int j=0;j < ValeuBBandsdate.size();j++) {
				
				
				if(ValueSIAdate.get(i).equals(ValeuBBandsdate.get(j))){
				float a=Float.parseFloat(ValueSIAUSD.get(i));
				float b=Float.parseFloat(ValeuBBandsLOW.get(j));
				float c=Float.parseFloat(ValeuBBandsHIGH.get(j));
				if(a <= b) {
					System.out.println("compra sia");
					stopFor++;
					System.out.println(ValueSIAdate.get(i));
					System.out.println(ValeuBBandsdate.get(j));
					break;
				}
				else if(a >= c) {
					System.out.println("vende sia");
					stopFor++;
					System.out.println(ValueSIAdate.get(i));
					System.out.println(ValeuBBandsdate.get(j));
					System.out.println(a);
					System.out.println(b);
										System.out.println(c);
					break;
				}
				else {
					System.out.println("nada sia");
					stopFor++;
					System.out.println(ValueSIAdate.get(i));
					System.out.println(ValeuBBandsdate.get(j));
					break;
					
						}

				}
			}
			if(stopFor!=0) {
				break;
			}

		}


		



		return 1;
	}
	
	       public ArrayList<Long> cleanDate(ArrayList<String> date){
            
            ArrayList<Long> cleanDates = new ArrayList<>();
            
            for(int i = 0; i < date.size(); i++){
               cleanDates.add(Long.parseLong( date.get(i).substring(2,4)  + date.get(i).substring(5,7)   + 
                                              date.get(i).substring(8,10) + date.get(i).substring(12,13) + 
                                              date.get(i).substring(14,16)));
                //System.out.println("rosca");
            }
            
            return cleanDates;
        }
        public ArrayList<Double> cleanValues(ArrayList<String> values){
            ArrayList<Double> cleanValues = new ArrayList<>();
            
            for(int i = 0; i < values.size(); i++){
               cleanValues.add(Double.parseDouble(values.get(i)));
            }System.out.println("rosca2");
            
            return cleanValues;
        }
        
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
