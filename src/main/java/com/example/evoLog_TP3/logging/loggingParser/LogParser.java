package com.example.evoLog_TP3.logging.loggingParser;

import java.io.*;
import java.util.*;
import java.util.regex.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LogParser {
    private static final String LOG_PATTERN =
            "(\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}[+-]\\d{2}:\\d{2})\\s+INFO\\s+\\d+\\s+---\\s+\\[.*\\]\\s+.*\\s+:\\s+User action logged for user: (\\w+) in method: (\\w+)";

    public static List<LPS> parseLogs(String logFilePath) throws IOException {
        List<LPS> parsedLogs = new ArrayList<>();
        Pattern pattern = Pattern.compile(LOG_PATTERN);

        try (BufferedReader reader = new BufferedReader(new FileReader(logFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    LPS logEntry = new LPS.LPSBuilder()
                            .setTimestamp(matcher.group(1))
                            .setUserId(matcher.group(2))
                            .setAction(matcher.group(3))
                            .build();
                    parsedLogs.add(logEntry);
                }
            }
        }
        return parsedLogs;
    }
}