package com.aidan.aidanenvelopesavemoney.Model;

import java.util.UUID;

/**
 * Created by Aidan on 2016/10/2.
 */

public class Account {
    private float cost = 0;
    private String comment = "";
    private String envelopeName = "";
    private String id = "";
    public Account(){
        id = UUID.randomUUID().toString().substring(0,10);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getEnvelopeName() {
        return envelopeName;
    }

    public void setEnvelopeName(String envelopeName) {
        this.envelopeName = envelopeName;
    }



}
