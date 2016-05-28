package com.hujiang.player21.serviceAgent;

import com.alibaba.fastjson.JSON;
import com.hujiang.player21.model.GameInfo;
import com.hujiang.player21.model.PlayerInfo;
import com.squareup.okhttp.*;
import lombok.extern.slf4j.Slf4j;
import rx.Observable;

import java.io.IOException;

/**
 * Created by shuXu on 2016/5/28 0028.
 */
@Slf4j
public class GameAgent {
    private static final String DEAR_HOST = "http://192.168.36.103:8002";
    public static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();

    private static GameAgent INSTANCE = new GameAgent();

    private GameAgent() {
        ;
    }

    public static GameAgent getINSTANCE() {
        return INSTANCE;
    }

    public PlayerInfo registPlayer() throws IOException {
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, "");
        PlayerInfo result = (PlayerInfo) Observable.create(sub -> {
            Request request = new Request.Builder()
                    .url(DEAR_HOST + "/player")
                    .post(body)
                    .build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
                sub.onNext(JSON.parseObject(response.body().string(), PlayerInfo.class));
                sub.onCompleted();
            } catch (IOException e) {
                log.error("Registor player error", e);
                sub.onError(e);
                return;
            }


        }).retry(2).toBlocking().first();
        return result;
    }

    public GameInfo startGame(int player) {
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, "");
        GameInfo result = (GameInfo) Observable.create(sub -> {
            Request request = new Request.Builder()
                    .url(DEAR_HOST + "/game/start" + "?player=" + player)
                    .post(body)
                    .build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
                sub.onNext(JSON.parseObject(response.body().string(), GameInfo.class));
                sub.onCompleted();
            } catch (IOException e) {
                log.error("Start game error", e);
                sub.onError(e);
                return;
            }

        }).retry(2).toBlocking().first();
        return result;
    }

    public GameInfo hit(int player) {
        GameInfo result = (GameInfo) Observable.create(sub -> {
            Request request = new Request.Builder()
                    .url(DEAR_HOST + "/game/hit" + "?player=" + player)
                    .get()
                    .build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
                sub.onNext(JSON.parseObject(response.body().string(), GameInfo.class));
                sub.onCompleted();
            } catch (IOException e) {
                log.error("Start game error", e);
                sub.onError(e);
                return;
            }

        }).retry(2).toBlocking().first();
        return result;
    }

    public GameInfo stand(int player) {
        GameInfo result = (GameInfo) Observable.create(sub -> {
            Request request = new Request.Builder()
                    .url(DEAR_HOST + "/game/stand" + "?player=" + player)
                    .get()
                    .build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
                sub.onNext(JSON.parseObject(response.body().string(), GameInfo.class));
                sub.onCompleted();
            } catch (IOException e) {
                log.error("Start game error", e);
                sub.onError(e);
                return;
            }

        }).retry(2).toBlocking().first();
        return result;
    }

    public void removePlayer(int player) {
        Observable.create(sub -> {
            Request request = new Request.Builder()
                    .url(DEAR_HOST + "/player" + "?player=" + player)
                    .delete()
                    .build();
            try {
                sub.onNext(client.newCall(request).execute());
                sub.onCompleted();
            } catch (IOException e) {
                log.error("Start game error", e);
                sub.onError(e);
                return;
            }

        }).retry(2).toBlocking().first();
    }
}
