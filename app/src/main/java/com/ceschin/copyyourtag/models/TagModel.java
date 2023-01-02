package com.ceschin.copyyourtag.models;

public class TagModel {
    private long id;
    private String tags;

    public TagModel(long id, String tags) {
        this.id = id;
        this.tags = tags;
    }

    public TagModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
