package com.example.jms.app3.service;

import com.example.jms.app2.pojo.Summary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Service
public class SummaryService {
    private static final String FILE_NAME = "summary.txt";
    private static final Logger LOGGER = LoggerFactory.getLogger(SummaryService.class);

    @JmsListener(destination = "orders.summary")
    public void handleItem(@Payload Summary summary) {
        try {
            LOGGER.info(summary.toString());
            appendSummary(summary.toString());
        } catch (Exception e) {
            LOGGER.warn(e + ": " + e.getMessage() + ". " + e.getCause());
            throw new RuntimeException("Exception during handling item", e);
        }
    }

    private void appendSummary(String text) {
        File file = getOrCreateFile();
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file, true), StandardCharsets.UTF_8)) {
            writer.append((LocalDateTime.now().toString())).append("\n");
            writer.append(text).append("\n");
        } catch (IOException e) {
            LOGGER.warn("IOException when writing summary");
        }
        if (file.length() > 100000) {
            try {
                Files.deleteIfExists(Paths.get(FILE_NAME));
            } catch (IOException e) {
                LOGGER.warn(e.getMessage());
            }
        }
    }

    private File getOrCreateFile() {
        File file = null;
        if (!Files.exists(Paths.get(FILE_NAME))) {
            try {
                file = Files.createFile(Paths.get(FILE_NAME)).toFile();
            } catch (IOException e) {
                LOGGER.warn("IOException when creating summary");

            }
        } else {
            file = Paths.get(FILE_NAME).toFile();
        }
        return file;
    }
}
