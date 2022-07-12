package com.example.jms.app1.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Order {
    private User user;
    private GoodsType goodsType;
    private float volume;
    private int number;
    private double total;

    public Order(User user, GoodsType goodsType, float volume, int number) {
        this.user = user;
        this.goodsType = goodsType;
        this.volume = volume;
        this.number = number;
        switch (goodsType) {
            case ITEMS:
                total = number;
                break;
            case LIQUIDS:
                total = volume;
                break;
            default:
                total = 0;
        }
    }

}
