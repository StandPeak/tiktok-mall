package com.uestc.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * projectName: xmstore
 *
 * @author: 赵伟风
 * time: 2022/3/24 22:29 周四
 * description: 返回结果通用对象  Map
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class R implements Serializable {

    /**
     * 通用成功状态码
     */
    public static final String SUCCESS_CODE = "001";
    /**
     * 失败状态码
     */
    public static final String FAIL_CODE = "004";
    /**
     * 未登录
     */
    public static final String USER_NO_LOGIN = "401";

    private String code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String msg;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long total;

    /**
     * 成功
     * @param msg
     * @param total
     * @param data
     * @return
     */
    public static R ok(String msg, Object data, Long total) {

        return new R(SUCCESS_CODE, msg,data,total);
    }

    /**
     * 成功
     * @param msg
     * @param data
     * @return
     */
    public static R ok(String msg,Object data){

        return ok(msg, data, null);
    }

    /**
     * 成功
     * @return
     */
    public static R ok(String msg){

        return ok(msg, null);
    }

    /**
     * 成功
     * @param data
     * @return
     */
    public static R ok(Object data) {

        return ok(null, data);
    }

    /**
     * 失败
     * @param msg
     * @param data
     * @param total
     * @return
     */
    public static R fail(String msg, Object data, Long total) {

        return new R(FAIL_CODE, msg,data,total);
    }

    /**
     * 失败
     * @param msg
     * @param data
     * @return
     */
    public static R fail(String msg, Object data) {

        return fail(msg,data,null);
    }

    /**
     * 失败
     * @return
     */
    /**
     * 成功
     * @return
     */
    public static R fail(String msg){

        return fail(msg, null);
    }

    /**
     * 成功
     * @param data
     * @return
     */
    public static R fail(Object data) {

        return fail(null, data);
    }

    public static Map retMap(String code, String msg, Object data, String key){

        Map map = new HashMap();
        map.put("code",code);
        if (!StringUtils.isEmpty(msg)){
            map.put("msg",msg);
        }

        //自定义map数据传入 返回的key和值
        if (data != null && !StringUtils.isEmpty(key)){
            map.put(key,data);
        }
        return map;
    }


    public static void main(String[] args) {

        Map map = retMap("001", null, "赵伟风", "user");

        //Map ok = ok("user", "赵伟风");
        //Map ok1 = ok("添加成功！");

        System.out.println("map = " + map);
    }



}
