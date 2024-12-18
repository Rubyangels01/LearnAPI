package com.example.learnapi.activity;

import java.util.ArrayList;
import java.util.HashMap;

public class CsvDataManager {
    private static CsvDataManager instance;
    private HashMap<Integer, String> dataMap;

    // Private constructor
    private CsvDataManager() {
        dataMap = new HashMap<>();
    }

    // Get Singleton instance
    public static CsvDataManager getInstance() {
        if (instance == null) {
            instance = new CsvDataManager();
        }
        return instance;
    }

    // Load data into the map
    public void loadData(ArrayList<String[]> csvData) {
        for (String[] row : csvData) {
            try {
                int id = Integer.parseInt(row[0]);
                String mess = row[1];
                dataMap.put(id, mess);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Get message by ID
    public String getMessageById(int id) {
        return dataMap.getOrDefault(id, "Message not found");
    }
}
