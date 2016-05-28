package com.hujiang.player21;


import com.hujiang.player21.service.GameService;
import com.hujiang.player21.serviceAgent.WebClient;
import org.apache.log4j.Logger;

/**
 * Created by shuXu on 2016/5/28 0028.
 */
public class Application {
    private static Logger logger = Logger.getLogger(Application.class);

    public static void main(String[] args) {
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
        System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient", "stdout");
        //GameService.test();
        GameService.Run(1000);
    }
}
