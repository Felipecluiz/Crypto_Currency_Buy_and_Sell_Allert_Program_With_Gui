package trabfinal;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Calc {


	ArrayList <String> SCATR = new ArrayList<String>();
	ArrayList <String> ETHATR = new ArrayList<String>();

	ArrayList <String> SCATRdate = new ArrayList<String>();
	ArrayList <String> ETHATRdate = new ArrayList<String>();
	
	public int calcMMETH() throws Exception { ///////////////////////////////////////////////////////////////////////////inicio do metodo eth
		ArrayList <String> sMAETH40 = new ArrayList<String>();
		ArrayList <String> sMAETH10 = new ArrayList<String>();
		ArrayList <String> sMAETH40date = new ArrayList<String>();
		ArrayList <String> sMAETH10date = new ArrayList<String>();
		URL mMovelETH40URL = new URL("https://www.alphavantage.co/query?"
				+ "function=SMA&symbol=ETH&interval=1min&time_period=40&series_type=close&apikey=YJC40GEVI5PVOITX");
		URL mMovelETH10URL = new URL("https://www.alphavantage.co/query?"
				+ "function=SMA&symbol=ETH&interval=1min&time_period=10&series_type=close&apikey=YJC40GEVI5PVOITX");
		URL mMovelETHATR = new URL("https://www.alphavantage.co/query?function=ATR&symbol=ETH&interval=30min&"
				+ "time_period=20&series_type=close&apikey=YJC40GEVI5PVOITX");

		URLConnection mmvel40 = mMovelETH40URL.openConnection();
		BufferedReader mmvel40in = new BufferedReader(new InputStreamReader(mmvel40.getInputStream()));

		String inputMMovel = "";

		
		int conta = 0;

		//media movel ETH40
		while ((inputMMovel = mmvel40in.readLine()) != null) {
			///media movel ETH40

			if (inputMMovel.contains("\"SMA\": ")){///media movel ETH40 valor
				sMAETH40.add(inputMMovel.substring(20,27));               
			}
			if (inputMMovel.contains("\"2018-") && !inputMMovel.contains("Last Refreshed")){///media movel ETH40 data
				sMAETH40date.add(inputMMovel.substring(9,25));
			}
		}
		mmvel40in.close();
		
		
		URLConnection mmvel10 = mMovelETH10URL.openConnection();
		BufferedReader mmvel10in = new BufferedReader(new InputStreamReader(mmvel10.getInputStream()));
		//media movel ETH40
		while ((inputMMovel = mmvel10in.readLine()) != null) {
			///media movel ETH40

			if (inputMMovel.contains("\"SMA\": ")){///media movel ETH10 valor
				sMAETH10.add(inputMMovel.substring(20,27));               
			}
			if (inputMMovel.contains("\"2018-") && !inputMMovel.contains("Last Refreshed") ){///media movel ETH10 data
				sMAETH10date.add(inputMMovel.substring(9,25));
				
			}
		}
		
		
		mmvel10in.close();
//		   for (int i = 0; i < sMAETH10date.size()-1 ;i++){
//		        System.out.println(sMAETH10date.get(i));
//		        
//		     }
//		            for (int i = 0; i < sMAETH40date.size()-1 ;i++){
//	        System.out.println(sMAETH40date.get(i));        
//		     }
		
		//do(){
			
			//for(int i=0;i < sMAETH10date.size()-1;i++) {
		
		
			if(sMAETH10date.get(0).equals(sMAETH40date.get(0))) {
				float a=Float.parseFloat(sMAETH40.get(0)); 
					System.out.println("sma eth 40 1: "+sMAETH40.get(0));
				float b=Float.parseFloat(sMAETH10.get(0));
					System.out.println("sma eth 10 1: "+sMAETH10.get(0));
				
				float c=Float.parseFloat(sMAETH10.get(1)); 
					System.out.println("sma eth 10 2: "+sMAETH10.get(1));
				float d=Float.parseFloat(sMAETH40.get(1));
					System.out.println("sma eth 40 2: "+sMAETH40.get(1));
				if(a > b && c > d) {
					System.out.println("comprar eth");
				}else if(a < b && c < d) {
					System.out.println("vender eth");
				}
				else {
					System.out.println("nada eth");
				}
			}
			//}
			
			
			
			
		//}while(pau);
			return 1;

	}
	
	
	public int calcMMSIA() throws Exception {
		
		ArrayList <String> sMASC10 = new ArrayList<String>();
		ArrayList <String> sMASC40 = new ArrayList<String>();

		ArrayList <String> sMASC10date = new ArrayList<String>();
		ArrayList <String> sMASC40date = new ArrayList<String>();

        URL mMovelSC40URL = new URL("https://www.alphavantage.co/query?"
                + "function=SMA&symbol=SC&interval=1min&time_period=10&series_type=close&apikey=YJC40GEVI5PVOITX");
        URL mMovelSC10URL = new URL("https://www.alphavantage.co/query?"
                + "function=SMA&symbol=SC&interval=1min&time_period=10&series_type=close&apikey=YJC40GEVI5PVOITX");
                
        URL mMovelSCATR = new URL("https://www.alphavantage.co/query?function=ATR&symbol=SC&interval=30min&"
        		+ "time_period=20&series_type=close&apikey=YJC40GEVI5PVOITX");
        
		URLConnection mmvel40 = mMovelSC40URL.openConnection();
		BufferedReader mmvel40in = new BufferedReader(new InputStreamReader(mmvel40.getInputStream()));

		String inputMMovel;

		String rosca = "";
		int conta = 0;

		//media movel ETH40
		while ((inputMMovel = mmvel40in.readLine()) != null) {
			///media movel SIA 40

			if (inputMMovel.contains("\"SMA\": ")){///media movel valor
				sMASC40.add(inputMMovel.substring(20,27));               
			}
			if (inputMMovel.contains("\"2018-") && !inputMMovel.contains("Last Refreshed" )){///media movel  data
				sMASC40date.add(inputMMovel.substring(9,25));
			}
		}
		mmvel40in.close();
		
		URLConnection mmvel10 = mMovelSC10URL.openConnection();
		BufferedReader mmvel10in = new BufferedReader(new InputStreamReader(mmvel10.getInputStream()));
		//media movel ETH40
		while ((inputMMovel = mmvel10in.readLine()) != null) {
			///media movel ETH40

			if (inputMMovel.contains("\"SMA\": ")){///media movel  valor
				sMASC10.add(inputMMovel.substring(20,27));               
			}
			if (inputMMovel.contains("\"2018-") && !inputMMovel.contains("Last Refreshed" ) ){///media movel  data
				sMASC10date.add(inputMMovel.substring(9,25));
			}
		}
		mmvel10in.close();
//		   for (int i = 0; i < sMAETH10date.size()-1 ;i++){
//		        System.out.println(sMAETH10date.get(i));
//		        
//		     }
//		            for (int i = 0; i < sMAETH40date.size()-1 ;i++){
//		        System.out.println(sMAETH40date.get(i));        
//		     }
		
		//do(){
			
			
			if(sMASC40date.get(0).equals(sMASC10date.get(0))) {
				
				float a=Float.parseFloat(sMASC40.get(0)); 
				System.out.println("sma sia 40 1: "+sMASC40.get(0));
				float b=Float.parseFloat(sMASC10.get(0));
				System.out.println("sma sia 10 1: "+sMASC10.get(0));
				
				float c=Float.parseFloat(sMASC10.get(1)); 
				System.out.println("sma sia 10 2: "+sMASC10.get(1));
				float d=Float.parseFloat(sMASC40.get(1));
				System.out.println("sma sia 40 2: "+sMASC40.get(1));
				if(a > b && c > d) {
					System.out.println("comprar sia");
				}else if(a < b && c < d) {
					System.out.println("vender sia");
				}
				else {
					System.out.println("nada sia");
				}
			}
		
			
			
			
			return 1;
					

	}





}
