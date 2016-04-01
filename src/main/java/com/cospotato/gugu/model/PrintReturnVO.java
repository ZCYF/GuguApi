package com.cospotato.gugu.model;

/**
 * Created by CosPotato on 4/1/16.
 */
public class PrintReturnVO {
    private int showapi_res_code;
    private String showapi_res_error;
    private int result;
    private String smartGuid;
    private int printcontentid;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getSmartGuid() {
        return smartGuid;
    }

    public void setSmartGuid(String smartGuid) {
        this.smartGuid = smartGuid;
    }

    public int getPrintcontentid() {
        return printcontentid;
    }

    public void setPrintcontentid(int printcontentid) {
        this.printcontentid = printcontentid;
    }
}
