package functions;

//import pageobjects.BasePage;

public class Constants {
	
    //If true, kill the browser at the end of each test
    public static boolean bKillBrowser = true;

	public static class Database {
		public final static String dbConnectionString= "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=polo-scan.mfltest.co.uk)(PORT=1560))(CONNECT_DATA=(SERVER=DEDICATED)(SERVICE_NAME=polomdp.mfltest.co.uk)))";
		//"jdbc:oracle:thin:@//<host>:<port>/<service_name> ";
		public final static String dbUsername= "luke";
		public final static String dbPassword= "password1";
	}
	
    public static class BrowserToLaunch {
        //public static String BrowserChoice = "IE";
        public final static String BrowserChoice = "FF";
    }
    
    public static class Url {
        //public static String BrowserChoice = "FF";
        public final static String GoogleHome = "http://www.google.com";
    }
    
    public static class PageTitles {
    	public final static String SOME_TITLE= "Page title here";
    	public final static String HOME_PAGE= "Google";
    }
    
    public static class IEDriverServer {
    	public final static String IE_DRIVER_SERVER= "src\\test\\resources\\IEDriverServer.exe";
    }
    
    public static class CRDriverServer {
    	public static final String CR_DRIVER_SERVER = "src\\test\\resources\\chromedriver.exe";
    }
}