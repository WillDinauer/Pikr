package com.example.pikr;

public class Picture {
    private String uri;
    private String filePath;
    public Picture(String uri, String filePath){
        this.uri = uri;
        this.filePath = filePath;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
