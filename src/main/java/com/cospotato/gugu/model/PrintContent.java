package com.cospotato.gugu.model;

/**
 * Created by CosPotato on 4/1/16.
 */
public class PrintContent {
    private String type;
    private Object content;

    public PrintContent(String type, Object content) {
        this.type = type;
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
