package com.cospotato.gugu.model;

/**
 * Created by CosPotato on 4/1/16.
 */
public class PrintStatusReturnVO {
    private int showapi_res_code;
    private String showapi_res_error;
    private int printflag;
    private String printcontentID;

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

    public int getPrintflag() {
        return printflag;
    }

    public void setPrintflag(int printflag) {
        this.printflag = printflag;
    }

    public String getPrintcontentID() {
        return printcontentID;
    }

    public void setPrintcontentID(String printcontentID) {
        this.printcontentID = printcontentID;
    }
}
