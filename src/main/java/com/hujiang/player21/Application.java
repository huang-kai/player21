package com.hujiang.player21;


import com.hujiang.player21.service.MyGameService;

/**
 * Created by Kyne on 2016/5/28 0028.
 */
public class Application {

    public static void main(String[] args) {
        int time=1000;
        try {
            time = Integer.parseInt(args[0]);
        }catch (Exception e){
            // nothing to do here
        }

        new MyGameService().start(time);
    }
}
