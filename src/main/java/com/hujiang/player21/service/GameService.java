package com.hujiang.player21.service;

import com.hujiang.player21.model.GameInfo;
import com.hujiang.player21.serviceAgent.GameAgent;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;


/**
 * Created by shuXu on 2016/5/28 0028.
 */
@Slf4j
public class GameService {
    private enum choice {
        Hit,
        Stand
    }

    private GameAgent agent = GameAgent.getINSTANCE();

    public void start(int time) {
        log.info("start play,time:" + time);
        int player = 0;
        try {
            player = agent.registPlayer().getPlayer();
            for (int i = 0; i < time; i++) {

                GameInfo info = play(player);
                log.info("Play time={} and result={}", i, info.getResult());
            }
        } catch (IOException e) {
            log.error("Play error", e);
        } finally {
            agent.removePlayer(player);
        }
    }

    private GameInfo play(int player) throws IOException {

        GameInfo gameInfo = agent.startGame(player);
        while (gameInfo.isContinue()) {
            switch (decide(gameInfo)) {
                case Hit:
                    gameInfo = agent.hit(player);
                    break;
                case Stand:
                    gameInfo = agent.stand(player);
            }
        }
        return gameInfo;
    }

    static choice decide(GameInfo gameInfo) {
        //TODO insert your code here.
        return gameInfo.getPlayer().size() > 2 ? choice.Hit : choice.Stand;
    }
}
