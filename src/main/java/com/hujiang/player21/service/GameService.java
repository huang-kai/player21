package com.hujiang.player21.service;

import com.alibaba.fastjson.JSON;
import com.hujiang.player21.model.GameChoiceEnum;
import com.hujiang.player21.model.GameHandInfo;
import com.hujiang.player21.model.PlayerInfo;
import com.hujiang.player21.model.ResultEnum;
import com.hujiang.player21.serviceAgent.GameAgent;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shuXu on 2016/5/28 0028.
 */
public class GameService {
    private static Logger logger = Logger.getLogger(GameService.class);

    public void test()
    {
        PlayerInfo playerInfo= GameAgent.newPlayer();
    }
    public static void Run(int time){
        logger.info("start play,time:"+time);
        List<GameHandInfo> list= new ArrayList<GameHandInfo>();
        Integer winCount=0;
        Integer lostCount=0;
        for(int i=0;i<time;i++){
            GameHandInfo game=play();
            list.add(game);
            if(game.getResult().equals(ResultEnum.Win.toString())){
                winCount++;
            }else
                lostCount++;
            logger.info(JSON.toJSONString(game));
        }
        logger.info("win:"+winCount+",lost:"+lostCount);
    }
    static GameHandInfo play() {
        PlayerInfo player=GameAgent.newPlayer();
        GameHandInfo gameInfo=GameAgent.startGame(player.getPlayer());
        while (true){
            GameChoiceEnum result=Choose(gameInfo);
            switch (result){
                case Null:
                    break;
                case Hit:
                    gameInfo=GameAgent.hit(player.getPlayer());
                    break;
                case Stand:
                    gameInfo=GameAgent.stand(player.getPlayer());
                    break;
            }
            if(isEnd(gameInfo))
                break;
        }
        return gameInfo;
    }

    static boolean isEnd(GameHandInfo game){
        return game.getResult()!=null &&
                (game.getResult().equals(ResultEnum.Lost.toString())||game.getResult().equals(ResultEnum.Win.toString()));
    }
    // todo: ¸Ä½øËã·¨
    static GameChoiceEnum Choose(GameHandInfo gameInfo){
        return gameInfo.getPlayer().size()>2?GameChoiceEnum.Stand:GameChoiceEnum.Hit;
    }
}
