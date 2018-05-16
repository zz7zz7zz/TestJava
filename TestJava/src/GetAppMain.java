import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.zip.GZIPInputStream;

public class GetAppMain {
	
    private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(200);
	
    private static ConcurrentLinkedQueue<String> domain_2 = new ConcurrentLinkedQueue<>();
	private static ConcurrentLinkedQueue<String> domain_3 = new ConcurrentLinkedQueue<>();
	private static ConcurrentLinkedQueue<String> domain_num = new ConcurrentLinkedQueue<>();
	private static ConcurrentLinkedQueue<String> domain_num_char = new ConcurrentLinkedQueue<>();
	private static ConcurrentLinkedQueue<String> domain_char_num = new ConcurrentLinkedQueue<>();
	
	private static final String TAG_NUM = "num";
	private static final String TAG_NUM_CHAR = "num_char";
	private static final String TAG_CHAR_NUM = "char_num";
	private static final String TAG_2_CHAR = "2_char";
	private static final String TAG_3_CHAR = "3_char";
	
	private static HashMap<String, ConcurrentLinkedQueue<String>> domainMap = new HashMap<>();
	static {
		domainMap.put(TAG_NUM, domain_num);
		domainMap.put(TAG_NUM_CHAR, domain_num_char);
		domainMap.put(TAG_CHAR_NUM, domain_char_num);
		domainMap.put(TAG_2_CHAR, domain_2);
		domainMap.put(TAG_3_CHAR, domain_3);
	}
	
	public static void main(String []argc){
		
//		numDomain();
//		numCharDomain();
		charNumDomain();
//		twoCharDomain();
//		threeCharDomain();

		while(true){
			try {

				system_cls();
				
				System.out.println("字符/数字域名：");
				for (String string : domain_char_num) {
					System.out.println(string);
				}
				
//				System.out.println("数字域名：");
//				for (String string : domain_num) {
//					System.out.println(string);
//				}
//				
//				System.out.println("\r\n");
//				System.out.println("双字母域名：");
//				for (String string : domain_2) {
//					System.out.println(string);
//				}
//
//				System.out.println("\r\n");
//				System.out.println("三字母域名：");
//				for (String string : domain_3) {
//					System.out.println(string);
//				}
				
				int threadCount = ((ThreadPoolExecutor)fixedThreadPool).getActiveCount();
				System.out.println("\r\n");
				System.out.println("线程数：" +threadCount);
				
				if(threadCount == 0){
					Thread.sleep(20000);
					System.exit(0);
					return;
				}
				
				Thread.sleep(5000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void system_cls(){
		try {
			Robot r = new Robot();
	        r.mousePress(InputEvent.BUTTON3_MASK);       // 按下鼠标右键
	        r.mouseRelease(InputEvent.BUTTON3_MASK);    // 释放鼠标右键
	        r.keyPress(KeyEvent.VK_CONTROL);             // 按下Ctrl键
	        r.keyPress(KeyEvent.VK_R);                    // 按下R键
	        r.keyRelease(KeyEvent.VK_R);                  // 释放R键
	        r.keyRelease(KeyEvent.VK_CONTROL);            // 释放Ctrl键
	        r.delay(100);  
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
//		new ProcessBuilder("cmd", "/c", "cls");
	}
	
	
	//1-1000
	private static void numDomain(){
		for(int i = 10000;i<=100000;i++){
			asynCheck(TAG_NUM,""+i);
		}
	}

	private static void numCharDomain(){
		for(int i = 0;i<=9;i++){
			for(int j = 97;j<=122;j++){
				asynCheck(TAG_NUM_CHAR,i+""+(char)j);
			}
		}
	}
	
	private static void charNumDomain(){
		for(int j = 97;j<=122;j++){
			for(int i = 0;i<=9;i++){
				asynCheck(TAG_CHAR_NUM,(char)j +""+ i);
			}
		}
	}
	//aa-zz
	private static void twoCharDomain(){
		for(int i = 97;i<=122;i++){
			for(int j = 97;j<=122;j++){
				asynCheck(TAG_2_CHAR,(char)i + "" + (char)j);
			}
		}
	}
	
	//aaa-zzz
	private static void threeCharDomain(){
		for(int i = 97;i<=122;i++){
			for(int j = 97;j<=122;j++){
				for(int k = 97;k<=122;k++){
					asynCheck(TAG_3_CHAR,(char)i + "" + (char)j+ "" + (char)k);
				}
			}
		}
	}
	
	private static void asynCheck(final String tag,final String key){
		fixedThreadPool.submit(new Runnable() {
			
			@Override
			public void run() {
				check(tag,key);
			}
		});
	}
	
	private static void check(String tag,String key){

		try {
			URL url = new URL("https://domain-registry.appspot.com/check?domain="+key+".app");
			HttpURLConnection  urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");  
	        urlConnection.setDoOutput(true);  
	        urlConnection.setDoInput(true);  
	        urlConnection.setUseCaches(false);
	        
	        urlConnection.setRequestProperty("Accept", "*/*");
	        urlConnection.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
	        urlConnection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
	        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36");
	        urlConnection.setConnectTimeout(15000);
	        urlConnection.setReadTimeout(60000);

	        urlConnection.setRequestProperty("Referer", "https://www.get.app/");
	        
	        urlConnection.setRequestProperty("Origin", "https://www.get.app");
	        
	        urlConnection.setRequestProperty("Connection", "keep-alive");
	        urlConnection.setRequestProperty("Host", "domain-registry.appspot.com");
	        
	        int code = urlConnection.getResponseCode();
	        if(code == 200){
	        	GZIPInputStream mGZIPInputStream;
	        	InputStream in = urlConnection.getInputStream();
	        	InputStreamReader isr;
	        	String encoding = urlConnection.getContentEncoding();
	        	if("gzip".equals(encoding)){
	        		mGZIPInputStream = new GZIPInputStream(in);
	        		isr = new InputStreamReader(mGZIPInputStream,"UTF-8");
	        	}else{
		            isr = new InputStreamReader(in,"UTF-8");
	        	}
	        	
	            BufferedReader br = new BufferedReader(isr);
	            String line;
	            StringBuilder sb = new StringBuilder();
	            while((line = br.readLine()) != null)
	            {
	                sb.append(line);
	            }
	            br.close();
	            isr.close();
	            in.close();
	            
	            boolean result = sb.toString().contains("true");
	            if(result){
//	            	System.out.println("www."+key+".app");
	            	domainMap.get(tag).add("www."+key+".app");
//	            	System.out.println("\n");
	            }
//	            System.out.println(key + " result "+result);
	            if(result){
//	            	System.out.println("\n");
	            }
	        }else{
	            System.out.println(key + " result false " + code );
	        }

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
