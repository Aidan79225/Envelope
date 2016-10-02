package com.aidan.aidanenvelopesavemoney.Model;

/**
 * Created by Aidan on 2016/10/2.
 */

public class Account {
    private float cost = 0;
    private String comment = "";
    private String envelopeName = "";

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
