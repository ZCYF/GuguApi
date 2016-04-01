package com.cospotato.gugu;


import com.cospotato.gugu.model.BindReturnVO;
import com.cospotato.gugu.model.PrintContent;
import com.cospotato.gugu.model.PrintReturnVO;
import com.cospotato.gugu.model.PrintStatusReturnVO;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Set;

/**
 * 咕咕机API
 * Created by CosPotato on 4/1/16.
 */
public class GuguApi {

    private final String URL_GET_USER_ID = "http://open.memobird.cn/home/setuserbind";
    private final String URL_PRINT = "http://open.memobird.cn/home/printpaper";
    private final String URL_PRINT_STATUS = "http://open.memobird.cn/home/getprintstatus";

    /**
     * 开发者ak
     */
    private final String ak = "";

    private final Gson mGson;
    private final OkHttpClient mClient;

    public GuguApi() {
        mClient = new OkHttpClient();
        mGson = new Gson();
    }

    /**
     * 获取绑定的UserId
     *
     * @param memobirdId   咕咕机设备编号
     * @param useridentify 自定义标识
     * @return userId   用户I
     * @throws IOException
     */
    public int getUserId(String memobirdId, String useridentify) throws IOException {
        RequestBody requestBody = new FormBody.Builder()
                .add("ak", ak)
                .add("timestamp", getTimestamp())
                .add("memobird", memobirdId)
                .add("useridentifying", useridentify)
                .build();

        Request request = new Request.Builder()
                .url(URL_GET_USER_ID)
                .put(requestBody)
                .build();

        Response response = mClient.newCall(request).execute();
        BindReturnVO model = mGson.fromJson(response.body().string(), BindReturnVO.class);

        if (model.getShowapi_res_code() == 1) {
            System.out.println("Get User Id:" + model.getShowapi_userid());
            return model.getShowapi_userid();
        } else {
            System.out.println("Error:" + model.getShowapi_res_error());
        }

        return -1;
    }

    /**
     * 打印纸条
     *
     * @param memobirdId 目标咕咕机ID
     * @param userId     目标咕咕机绑定的userId
     * @param content    打印的内容
     * @return contentId    打印内容的编号
     * @throws IOException
     */
    public int print(String memobirdId, int userId, String content) throws IOException {
        RequestBody requestBody = new FormBody.Builder()
                .add("ak", ak)
                .add("printcontent", content)
                .add("timestamp", getTimestamp())
                .add("memobirdID", memobirdId)
                .add("userID", userId + "")
                .build();

        Request request = new Request.Builder()
                .url(URL_PRINT)
                .post(requestBody)
                .build();

        Response response = mClient.newCall(request).execute();
        PrintReturnVO model = mGson.fromJson(response.body().string(), PrintReturnVO.class);

        if (model.getShowapi_res_code() == 1) {
            System.out.println("Commit Success, contentId:" + model.getPrintcontentid());
            if (model.getResult() == 1) {
                System.out.println("Print Success!");
            } else {
                System.out.println("result:" + model.getResult());
            }
            return model.getPrintcontentid();
        } else {
            System.out.println("Error:" + model.getShowapi_res_error());
        }

        return -1;
    }

    public int getPrintStatus(int printContentId) throws IOException {
        RequestBody requestBody = new FormBody.Builder()
                .add("ak", ak)
                .add("timestamp", getTimestamp())
                .add("printcontentID", printContentId + "")
                .build();

        Request request = new Request.Builder()
                .url(URL_PRINT_STATUS)
                .post(requestBody)
                .build();

        Response response = mClient.newCall(request).execute();

        PrintStatusReturnVO model = mGson.fromJson(response.body().string(), PrintStatusReturnVO.class);
        if (model.getShowapi_res_code() == 1) {
            if (model.getPrintflag() == 1) {
                System.out.println("Printed, contentID:" + model.getPrintcontentID());
                return model.getPrintflag();
            } else {
                System.out.println("Not Printed yet, contentID:" + model.getPrintcontentID());
                return model.getPrintflag();
            }
        } else {
            System.out.println("Error:" + model.getShowapi_res_error());
        }

        return -1;
    }

    /**
     * 获取咕咕机API要求的时间戳
     *
     * @return 时间戳
     */
    private String getTimestamp() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(new Date().getTime());
        return time;
    }

    /**
     * 对打印内容进行编码转换
     *
     * @param params PrintContent集合
     * @return 编码后的字符串
     * @throws UnsupportedEncodingException
     */
    public static String parseContent(Set<PrintContent> params) throws UnsupportedEncodingException {

        StringBuffer sb = new StringBuffer();
        Base64.Encoder encoder = Base64.getEncoder();

        for (PrintContent entry : params) {
            if (entry.getType().equals("T")) {
                sb.append("T:");
                sb.append(new String(encoder.encode(((String) entry.getContent()).getBytes("GBK"))));
            } else {
                sb.append("P:");
                sb.append(new String(encoder.encode((byte[]) entry.getContent())));
            }
            sb.append("|");
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
}