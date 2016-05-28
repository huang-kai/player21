package com.hujiang.player21;


import com.hujiang.player21.service.GameService;

/**
 * Created by shuXu on 2016/5/28 0028.
 */
public class Application {

    public static void main(String[] args) {
        int time=100;
        try {
            time = Integer.parseInt(args[0]);
        }catch (Exception e){
            // nothing to do here
        }

        new GameService().start(time);
    }
}
