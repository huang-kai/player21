package com.hujiang.player21.serviceAgent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kyne on 16/5/28.
 */
public class GameAgentTest {

    int playerId;
    GameAgent agent =GameAgent.getINSTANCE();
    @Before
    public void setUp() throws Exception {

        playerId = agent.registPlayer().getPlayer();
        System.out.println("registor player: "+playerId);
    }

    @After
    public void tearDown() throws Exception {
        agent.removePlayer(playerId);
        System.out.println("remove player: "+playerId);
    }

    @Test
    public void startGame() throws Exception {
        System.out.println(agent.startGame(1));
    }


}