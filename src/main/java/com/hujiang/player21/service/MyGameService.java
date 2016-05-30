package com.hujiang.player21.service;

import com.hujiang.player21.model.GameInfo;

/**
 * Created by Kyne on 16/5/30.
 */
public class MyGameService extends GameService{

    @Override
   choice decide(GameInfo gameInfo) {
        //TODO insert your code here.
        return gameInfo.getPlayer().size() > 2 ? choice.Hit : choice.Stand;
    }
}
