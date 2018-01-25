package page;

import java.util.List;

//pageBean<Object>类是为了数据能够分页显示而设计的

public class PageBean<Object> {
    private int pageCode;           //页码
    private int pageRecord;         //页面的记录数
    private int totalPage;          //总页数
    private int totalRecord;        //总记录
    private List<Object> beanList;  //当前页面内容
    private String url;

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
    //getTotalPage需要另行计算，例如一页10个记录，总共11个记录就需要2页
    public int getTotalPage(){
        totalPage = totalRecord/pageRecord;
        return totalRecord % pageRecord == 0 ? totalPage : totalPage + 1;
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

    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }
}
