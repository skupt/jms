package com.example.jms.app2.pojo;

import lombok.Data;

@Data
public class SummaryItems implements Cloneable {
    private double allowedVolume = 100d;
    private int accepted;
    private double acceptedVolume;
    private int rejected;
    private double rejectedVolume;
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
