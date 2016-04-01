package com.cospotato.gugu;

import com.cospotato.gugu.model.PrintContent;

import java.util.HashSet;
import java.util.Set;

/**
 * 测试主程序
 * Created by CosPotato on 4/1/16.
 */
public class Main {

    /**
     * 咕咕机设备编号
     */
    private final static String memobirdId = "";
    /**
     * 自定义用户标识
     */
    private final static String userIdentify = "";

    private static int userId = 0;
    private static GuguApi api;
    private static int contentId = 0;

    public static void main(String[]args){
        api = new GuguApi();
        Set<PrintContent> contents = new HashSet<>();
        contents.add(new PrintContent("T", "\t\tPrint Test\n"));
        contents.add(new PrintContent("T", "-By CosPotato"));
        String content = "";
        try {
            content = GuguApi.parseContent(contents);
            userId = api.getUserId(memobirdId, userIdentify);
            if (!content.equals("") && userId > 0){
                contentId = api.print(memobirdId, userId, content);
                if (contentId > 0){
                    System.out.println(api.getPrintStatus(contentId));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
