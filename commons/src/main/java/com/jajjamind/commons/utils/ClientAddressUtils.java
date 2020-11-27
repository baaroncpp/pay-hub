package com.jajjamind.commons.utils;


import java.io.*;
import java.util.*;
import java.net.*;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ClientAddressUtils
{
    static final Logger DEBUGLOG;
    static final String DEFAULT_INST_ID = "ServerManageDefault";
    private static ClientAddressUtils instance;
    private String localAddress;
    private String webInstanceId;
    private String appName;
    private boolean initialized;
    private String mcCode;
    
    private ClientAddressUtils() {
        this.localAddress = null;
        this.webInstanceId = null;
        this.appName = null;
        this.initialized = false;
        this.mcCode = "default";
    }
    
    public static ClientAddressUtils getInstance() {
        return ClientAddressUtils.instance;
    }
    
    public String getLocalAddress() {
        if (!this.initialized) {
            this.initialized = true;
            this.initLocalAddress();
        }
        return this.localAddress;
    }
    
    public String getWebInstanceId() {
        if (!this.initialized) {
            this.initialized = true;
            this.initLocalAddress();
        }
        return this.webInstanceId;
    }
    
    private void loadHostConfig(final Properties props) {
        final String userHome = System.getProperty("user.home");
        final File homeDir = new File(userHome, "hostConfig");
        final File cfgFile = new File(homeDir, "host_config.properties");
        FileInputStream fis = null;
        try {
            if (cfgFile.exists()) {
                fis = new FileInputStream(cfgFile);
                props.load(fis);
            }
        }
        catch (Exception e) {
            ClientAddressUtils.DEBUGLOG.error("Fail to read cfg: " + cfgFile, e);
        }
        finally {
            IOUtils.closeQuietly((InputStream)fis);
        }
    }
    
    private String findLocalFloatAddress(final String baseAddr) {
        String floatAddr = null;
        String normalAddr = null;
        try {
            final Enumeration<NetworkInterface> items = NetworkInterface.getNetworkInterfaces();
            if (null != items) {
                while (items.hasMoreElements()) {
                    final NetworkInterface item = items.nextElement();
                    final Enumeration<InetAddress> addresses = item.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        final InetAddress addr = addresses.nextElement();
                        if (addr.isLoopbackAddress()) {
                            continue;
                        }
                        if (!(addr instanceof Inet4Address)) {
                            continue;
                        }
                        if (addr.getHostAddress().equals(baseAddr)) {
                            return addr.getHostAddress();
                        }
                        normalAddr = addr.getHostAddress();
                        if (item.getName().indexOf(":0") == -1) {
                            continue;
                        }
                        floatAddr = addr.getHostAddress();
                    }
                }
            }
            if (floatAddr != null) {
                return floatAddr;
            }
            if (normalAddr != null) {
                return normalAddr;
            }
            final InetAddress localAddr = InetAddress.getLocalHost();
            return localAddr.getHostAddress();
        }
        catch (Exception e) {
            ClientAddressUtils.DEBUGLOG.error("Fail to obtain local adddress", e);
            return "127.0.0.1";
        }
    }
    
    private void initLocalAddress() {
        if (this.localAddress == null) {
            final Properties props = new Properties();
            this.loadHostConfig(props);
            final String ip = props.getProperty("fabricIp", "127.0.0.1");
            this.localAddress = this.findLocalFloatAddress(ip.trim());
        }
        if (this.webInstanceId == null) {
            this.webInstanceId = System.getProperty("SEEinstance", "ServerManageDefault");
        }
    }
    
    public static String getAddress() {
        return getInstance().getLocalAddress();
    }
    
    public static String getInstanceId() {
        return getInstance().getWebInstanceId();
    }
    
    public String getAppName() {
        return this.appName;
    }
    
    public void setAppName(final String appName) {
        this.appName = appName;
    }
    
    public String createMcCode() {
        ClientAddressUtils.DEBUGLOG.debug("Enter createMcCode()", new Object[0]);
        try {
            this.mcCode = InetAddress.getLocalHost().getHostName();
        }
        catch (UnknownHostException e) {
            ClientAddressUtils.DEBUGLOG.error("get host name is error", e);
        }
        StringBuffer buffer = new StringBuffer();
        buffer = buffer.append(this.mcCode).append(System.currentTimeMillis());
        this.mcCode = buffer.toString();
        ClientAddressUtils.DEBUGLOG.debug("Exit createMcCode() and McCode = " + this.mcCode, new Object[0]);
        return this.mcCode;
    }
    
    public String getMcCode() {
        return this.mcCode;
    }
    
    static {
        DEBUGLOG = LogManager.getLogger();
        ClientAddressUtils.instance = new ClientAddressUtils();
    }
}
