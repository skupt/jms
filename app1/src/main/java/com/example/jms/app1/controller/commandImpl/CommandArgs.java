package com.example.jms.app1.controller.commandImpl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandArgs {
    private String userName = null;
    private String goodsType = null;
    private Float volume = null;
    private Integer number = null;
}
