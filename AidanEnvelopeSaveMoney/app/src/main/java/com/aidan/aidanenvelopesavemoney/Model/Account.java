package com.aidan.aidanenvelopesavemoney.Model;

/**
 * Created by Aidan on 2016/10/2.
 */

public class Account {
    private int cost = 0;
    private String comment = "";
    private String envelopeName = "";

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
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
