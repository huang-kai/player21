package com.hujiang.player21.service;

import com.hujiang.player21.model.GameInfo;
import com.hujiang.player21.serviceAgent.GameAgent;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;


/**
 * Created by shuXu on 2016/5/28 0028.
 */
@Slf4j
public abstract class GameService {

    protected enum choice {
        Hit,
        Stand
    }

    private GameAgent agent = GameAgent.getINSTANCE();

    public void start(int time) {
        log.info("start play,time:" + time);
        int player = 0;
        try {
            player = agent.registPlayer().getPlayer();
            if (time==0) {
                for (;;) {
                    GameInfo info = play(player);
                    log.info("Player={}, result={}", player, info.getResult());
                    Thread.sleep(100);
                }
            }else {
                for (int i = 0; i < time; i++) {
                    GameInfo info = play(player);
                    log.info("Player={}, result={}", player, info.getResult());
                    Thread.sleep(100);
                }
            }
        } catch (IOException e) {
            log.error("Play error", e);
        } catch (InterruptedException e) {
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

    abstract choice decide(GameInfo gameInfo);
}
