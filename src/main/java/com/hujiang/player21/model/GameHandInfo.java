package com.hujiang.player21.model;

import lombok.Data;

import java.util.List;

/**
 * Created by shuXu on 2016/5/28 0028.
 */
@Data
public class GameHandInfo {
    List<String> dealer;
    List<String> player;
    String result;
}
