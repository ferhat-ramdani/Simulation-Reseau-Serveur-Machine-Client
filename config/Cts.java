package config;

import java.net.InetAddress;
//A decillion : 10^33 , "1000000000000000000000000000000000"
//A billion : 10^9 , "1000000000"

public class Cts {
    public static final int ID = 2002;
    public static final int PORT1 = 8080;
    public static final int PORT2 = 8083;
    public static final int NB_CORES = Runtime.getRuntime().availableProcessors();
    public static final String INTERVAL_SIZE = "100000";
    public static final String START = "10000000000000000";
    public static final String HOST_NAME = gethost();

    private static String gethost() {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            return ip.getHostName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    
}