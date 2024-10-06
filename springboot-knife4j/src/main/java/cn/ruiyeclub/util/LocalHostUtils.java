package cn.ruiyeclub.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.*;
import java.util.Enumeration;

/**
 * <p>
 *    java获取本地虚拟机IP
 * </p>
 *
 * @author liuxingyu01
 * @since 2023-04-06 15:11:53
 */
public class LocalHostUtils {
    private static final Logger log = LoggerFactory.getLogger(LocalHostUtils.class);

    /**
     * 获取本机ip
     *
     * @return String
     */
    public static String getLocalHost() {
        String ipLocalHost = null;
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                if (netInterface.isUp()) {
                    Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        InetAddress address = addresses.nextElement();
                        if (address instanceof Inet4Address && !address.isLoopbackAddress()) {
                            String hostAddress = address.getHostAddress();
                            if (!"127.0.0.1".equals(hostAddress) && !"/127.0.0.1".equals(hostAddress)) {
                                // 得到本地IP
                                ipLocalHost = address.toString().split("[/]")[1];
                            }
                        }
                    }
                }
            }
        } catch (SocketException e) {
            log.error("Cannot get first non-loopback ip address：", e);
        }
        if (ipLocalHost != null && !ipLocalHost.isEmpty()) {
            return ipLocalHost;
        }
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException("Unable to retrieve localhost");
        }
    }


    /**
     * 获取本机InetAddress
     *
     * @return String
     */
    public static InetAddress getInetAddress() {
        InetAddress inetAddress = null;
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                if (netInterface.isUp()) {
                    Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        InetAddress temp = addresses.nextElement();
                        if (temp instanceof Inet4Address && !temp.isLoopbackAddress()) {
                            String host = temp.getHostAddress();
                            if (!"0.0.0.0".equals(host) && !"127.0.0.1".equals(host)) {
                                inetAddress = temp;
                            }
                        }
                    }
                }
            }
        } catch (SocketException e) {
            log.error("Cannot get first non-loopback address：", e);
        }
        if (inetAddress != null) {
            return inetAddress;
        }
        try {
            return InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException("Unable to retrieve localhost");
        }
    }

    public static void main(String[] args) {
        System.out.println(getLocalHost());
        System.out.println(getInetAddress().getHostAddress());
    }
}
