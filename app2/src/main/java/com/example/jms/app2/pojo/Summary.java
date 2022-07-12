package com.example.jms.app2.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Summary implements Cloneable {
    private SummaryLiquids summaryLiquids = new SummaryLiquids();
    private SummaryItems summaryItems = new SummaryItems();

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
