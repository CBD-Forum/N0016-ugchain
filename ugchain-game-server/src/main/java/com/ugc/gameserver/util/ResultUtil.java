package com.ugc.gameserver.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ugc.gameserver.domain.result.ErrorInfo;
import com.ugc.gameserver.domain.result.Meta;
import com.ugc.gameserver.domain.result.Result;

/**
 * Created by yuanshichao on 2016/11/17.
 */
public class ResultUtil {
    private static final String LEFT_BRACKET = "(";
    private static final String RIGHT_BRACKET = ")";
    private static ObjectMapper objectMapper = new ObjectMapper();
    public static Result success(Object data) {
        Meta meta = new Meta(200, "success");
        return new Result(meta, data);
    }
    public static String successCallBack(String callback,Object data) {
        Meta meta = new Meta(200, "success");
        Result result = new Result(meta,data);
        StringBuilder builder = new StringBuilder();
        try {
            builder.append(callback).append(LEFT_BRACKET).append(objectMapper.writeValueAsString(result)).append(RIGHT_BRACKET);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static Result success() {
        return success(null);
    }

    public static Result buildErrorResult(ErrorInfo info) {
        Meta meta = new Meta(info.getErrorCode(), info.getErrorMessage());
        return new Result(meta);
    }
    public static String buildErrorResultCallBack(ErrorInfo info,String callback) {
        Meta meta = new Meta(info.getErrorCode(), info.getErrorMessage());
        StringBuilder builder = new StringBuilder();
        try {
            builder.append(callback).append(LEFT_BRACKET).append(objectMapper.writeValueAsString(new Result(meta))).append(RIGHT_BRACKET);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

}
