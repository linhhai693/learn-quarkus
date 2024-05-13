package org.agoncal.quarkus.starting.dto;

import jakarta.ws.rs.QueryParam;

public class DataFilterDTO {

    @QueryParam("page")
    private int page;
    @QueryParam("size")
    private int size;
    @QueryParam("keyWords")
    private String keyWords;

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
