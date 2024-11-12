package com.example.evoLog_TP3.logging.profile;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ProfileGenerator {
    private Map<String, ReadProfile> readProfiles = new HashMap<>();
    private Map<String, WriteProfile> writeProfiles = new HashMap<>();
    private Map<String, SearchProfile> searchProfiles = new HashMap<>();

    // Process each log entry
    public void processLog(String userId, String action) {
        if (action.contains("read")) {
            readProfiles.putIfAbsent(userId, new ReadProfile(userId));
            readProfiles.get(userId).incrementReadOperations();
        } else if (action.contains("write")) {
            writeProfiles.putIfAbsent(userId, new WriteProfile(userId));
            writeProfiles.get(userId).incrementWriteOperations();
        } else if (action.contains("search")) {
            searchProfiles.putIfAbsent(userId, new SearchProfile(userId));
            searchProfiles.get(userId).incrementSearchOperations();
        }
    }

    public void saveProfilesAsJson() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("read_profiles.json"), readProfiles.values());
        mapper.writeValue(new File("write_profiles.json"), writeProfiles.values());
        mapper.writeValue(new File("search_profiles.json"), searchProfiles.values());
    }

}