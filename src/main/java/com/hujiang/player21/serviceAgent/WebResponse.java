package com.hujiang.player21.serviceAgent;

import lombok.Builder;
import lombok.Data;

/**
 * Created by shuXu on 2016/5/28 0028.
 */
@Data
@Builder
public class WebResponse {
    Integer status;
    String result;
    public String toString() {
        return "WebResponse{status=" + status + ", result=\'" + this.result + '\'' + '}';
    }
}
