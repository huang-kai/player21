package com.hujiang.player21.serviceAgent;

import cn.dreampie.client.ClientRequest;
import cn.dreampie.client.ClientResult;
import com.alibaba.fastjson.JSON;
import com.hujiang.player21.model.GameHandInfo;
import com.hujiang.player21.model.PlayerInfo;

/**
 * Created by shuXu on 2016/5/28 0028.
 */
public class GameAgent {
    static String host="http://192.168.36.103:8002";
    /*
    static HttpRestClient _client=new HttpRestClient(host);
    public static PlayerInfo newPlayer(){
        ClientRequest request=new ClientRequest("/player");
        ClientResult result= _client.build(request).post();
        return JSON.parseObject(result.getResult(), PlayerInfo.class);
    }
    public static GameHandInfo startGame(int player){
        ClientRequest request=new ClientRequest("/game/start?player="+player);
        ClientResult result= _client.build(request).post();
        return JSON.parseObject(result.getResult(), GameHandInfo.class);
    }
    public static GameHandInfo hit(int player){
        ClientRequest request=new ClientRequest("/game/hit?player="+player);
        ClientResult result= _client.build(request).get();
        return JSON.parseObject(result.getResult(), GameHandInfo.class);
    }
    public static GameHandInfo stand(int player){
        ClientRequest request=new ClientRequest("/game/stand?player="+player);
        ClientResult result= _client.build(request).get();
        return JSON.parseObject(result.getResult(), GameHandInfo.class);
    }
    public static void removePlayer(int player){
        ClientRequest request=new ClientRequest("/player?player="+player);
        request.setJsonParam("dd");
        ClientResult result= _client.build(request).post();
    }
    */

    public static PlayerInfo newPlayer(){
        WebResponse response=WebClient.post(host+"/player");
        return JSON.parseObject(response.getResult(), PlayerInfo.class);
    }
    public static GameHandInfo startGame(int player){
        WebResponse response=WebClient.post(host +"/game/start?player="+player);
        return JSON.parseObject(response.getResult(), GameHandInfo.class);
    }
    public static GameHandInfo hit(int player){
        WebResponse response=WebClient.get(host + "/game/hit?player=" + player);
        return JSON.parseObject(response.getResult(), GameHandInfo.class);
    }
    public static GameHandInfo stand(int player){
        WebResponse response=WebClient.get(host + "/game/stand?player=" + player);
        return JSON.parseObject(response.getResult(), GameHandInfo.class);
    }
    public static void removePlayer(int player){
        WebResponse response=WebClient.post(host + "/player?player=" + player);
    }
}
