package com.example.app2.pojo;

import lombok.Data;

@Data
public class SummaryLiquids implements Cloneable {
    private double allowedVolume = 100d;
    private int accepted;
    private double acceptedVolume;
    private int rejected;
    private double rejectedVolume;

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
