package com.example.ady.findingyourgitbubprofile.model.github;

/**
 * Created by Ady on 11/19/2017.
 */

public class Item {
private String repo, full_name, id;

    public Item(String repo, String full_name, String id) {
        this.repo = repo;
        this.full_name = full_name;
        this.id = id;
    }

    public String getRepo() {
        return repo;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
