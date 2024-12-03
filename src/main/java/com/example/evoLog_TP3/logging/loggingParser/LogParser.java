package com.example.evoLog_TP3.logging.loggingParser;

import java.io.*;
import java.util.*;
import java.util.regex.*;

import com.example.evoLog_TP3.logging.profile.ProfileGenerator;

public class LogParser {
    public static void main(String[] args) throws Exception {
        String filePath = "logs/application.log";
        ArrayList<LPS> LPSlist = new ArrayList<LPS>();
        ProfileGenerator generator;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Updated regex to optionally capture the price if it exists
            String pattern = "^(\\S+ \\S+) .*User action logged for user: ([\\w]+) in method: ([\\w]+)(?: with product price: ([\\d.]+))?";
            Pattern r = Pattern.compile(pattern);

            LPS.LPSBuilder lpsB;

            LPS lps;

            while ((line = br.readLine()) != null) {
                Matcher m = r.matcher(line);
                if (m.find()) {
                    lpsB = new LPS.LPSBuilder();

                    lpsB.setTimestamp(m.group(1));
                    lpsB.setUserId(m.group(2));
                    lpsB.setAction(m.group(3));

                    if (m.group(4) != null) { // If price is present in the log
                        double price = Double.parseDouble(m.group(4));
                        lpsB.setPrice(price);
                        lps = lpsB.buildPrice();
                    } else {
                        lps = lpsB.build();
                    }

                    LPSlist.add(lps);

                    System.out.println("Parsed Log: " + lps.toString());
                }
            }
        }
        generator = new ProfileGenerator(LPSlist);
        generator.processLogs();
    }
}
