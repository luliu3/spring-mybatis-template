package com.luliu.rest.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luliu3 on 2016/8/5.
 */
public class Page<T> implements Serializable{

    private static final long serialVersionUID = 1L;
    public static final int DEFAULT_SIZE = 10; // 默认页面大小
    private List<T> dataList; // 被分页的数据列表
    private int recordCount; // 总数据数
    private int pageSize; // 每页的数据数
    private int pageCount; // 总页数
    private int pageIndex; // 页索引（基于1）
    private List<Integer> pageList; // 页索引列表

    public Page(List<T> dataList, int recordCount, int pageSize, int pageCount, int pageIndex, List<Integer> pageList) {
        this.dataList = dataList;
        this.recordCount = recordCount;
        this.pageSize = pageSize;
        this.pageCount = pageCount;
        this.pageIndex = pageIndex;
        this.pageList = pageList;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public List<Integer> getPageList() {
        return pageList;
    }

    public void setPageList(List<Integer> pageList) {
        this.pageList = pageList;
    }

    public static List<Integer> pageList(int pageCount, int pageIndex) {
        int start = 1;
        int end = 8;
        if (end < pageCount) {
            if (pageIndex >= 7) {
                start = pageIndex - 3;
                end = pageIndex + 3;
            }
            if (start >= pageCount - 7) {
                start = pageCount - 7;
                end = pageCount;
            }
        }
        else{
            end = pageCount;
        }
        List<Integer> pageList = new ArrayList<Integer>();
        for (int i = start; i <= end; i++) {
            pageList.add(i);
        }
        return pageList;
    }

    public static int pageCount(int recordCount, int pageSize) {
        return (recordCount / pageSize + ((recordCount % pageSize) == 0 ? 0 : 1));
    }

    public static int pageIndex(int pageCount, int pageIndex) {
        pageIndex = pageIndex <= 0 ? 1 : pageIndex;
        pageIndex = pageIndex > pageCount ? pageCount : pageIndex;
        return pageIndex;
    }

    @Override
    public String toString() {
        return "Page{" +
                "dataList=" + dataList +
                ", pageSize=" + pageSize +
                ", pageIndex=" + pageIndex +
                ", pageCount=" + pageCount +
                ", recordCount=" + recordCount +
                ", pageList=" + pageList +
                '}';
    }
}
