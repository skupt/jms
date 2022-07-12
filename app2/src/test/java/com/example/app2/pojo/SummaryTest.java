package com.example.app2.pojo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SummaryTest {

    @Test
    void shouldMakeCloneObject() throws CloneNotSupportedException {
        Summary origin = new Summary();
        origin.getSummaryItems().setAcceptedVolume(1);
        origin.getSummaryItems().setAccepted(2);
        origin.getSummaryItems().setRejectedVolume(3);
        origin.getSummaryItems().setRejected(4);
        origin.getSummaryLiquids().setAccepted(5);
        origin.getSummaryLiquids().setAcceptedVolume(6);
        origin.getSummaryLiquids().setRejected(7);
        origin.getSummaryLiquids().setRejectedVolume(8);
        origin.getSummaryItems().setAllowedVolume(9);
        origin.getSummaryLiquids().setAllowedVolume(10);

        Summary backup = (Summary) origin.clone();

        String expected = "Summary(summaryLiquids=SummaryLiquids(allowedVolume=10.0, accepted=5, acceptedVolume=6.0, " +
                "rejected=7, rejectedVolume=8.0), summaryItems=SummaryItems(allowedVolume=9.0, accepted=2, " +
                "acceptedVolume=1.0, rejected=4, rejectedVolume=3.0))";
        Assertions.assertEquals(expected, backup.toString());

    }
}
