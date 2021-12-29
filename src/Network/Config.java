/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author mthuan
 */
public class Config {
    public static final String HOST = "127.0.0.1";
    public static final int PORT = 8888;
    public static int corePoolSize = 50;
    public static int maximumPoolSize = 100;
    public static long keepAliveTime = 200;
    public static TimeUnit unit = TimeUnit.SECONDS;
}
