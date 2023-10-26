package JavaNetworking;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

public class NetworkInterfacesDisplay {

    // Network Interface: Systems often run with multiple active network connections
    // 显示系统上同时运行的不同网络连接
    public void testNetworkInterfaces() throws SocketException {
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netIf : Collections.list(nets)) {
            System.out.println("Display name: " + netIf.getDisplayName());
            System.out.println("Name: " + netIf.getName());
            for (NetworkInterface subIf : Collections.list(netIf.getSubInterfaces())) {
                System.out.println("Sub Interface Display name: " + subIf.getDisplayName());
                System.out.println("Sub Interface Name: " + subIf.getName());
            }
        }
    }
}







