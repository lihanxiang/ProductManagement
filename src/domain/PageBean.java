package domain;

import java.util.List;

public class PageBean {
    private int pageCode;           //页码
    private int pageRecord;         //每一页的记录数
    private int totalPage;          //总页数
    private int totalRecord;        //总记录
    private List<Object> beanList;  //当前页面记录
    private String URL;

    public void setPageCode(int pageCode) {
        this.pageCode = pageCode;
    }
    public int getPageCode() {
        return pageCode;
    }

    public void setPageRecord(int pageRecord) {
        this.pageRecord = pageRecord;
    }
    public int getPageRecord() {
        return pageRecord;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public int getTotalPage(){
        return totalPage;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }
    public int getTotalRecord() {
        return totalRecord;
    }

    public void setBeanList(List<Object> beanList) {
        this.beanList = beanList;
    }
    public List<Object> getBeanList() {
        return beanList;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
    public String getURL() {
        return URL;
    }
}
