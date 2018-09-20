package com.utils;

import com.utils.util.GsonUtil;
import org.springframework.http.MediaType;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2017/5/13.
 */
public class ResponseJsonUtils {

    /**
     * 通过ServletResponse返回JSon数据
     *
     * @param servletResponse response
     * @param httpStatus      HTTP状态码
     * @param object          数据
     */
    public static void sendMsgWithServletResponse(ServletResponse servletResponse, int httpStatus, Object object) {
        servletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        servletResponse.setCharacterEncoding("UTF-8");
        //401状态
        ((HttpServletResponse) servletResponse).setStatus(httpStatus);
        PrintWriter printWriter = null;
        String json = GsonUtil.toJson(object);
        try {
            printWriter = servletResponse.getWriter();
            printWriter.append(json);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            printWriter.close();
        }
    }
}
