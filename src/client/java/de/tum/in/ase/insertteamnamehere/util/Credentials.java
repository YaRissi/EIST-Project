package de.tum.in.ase.insertteamnamehere.util;

import com.github.cliftonlabs.json_simple.JsonException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Credentials {
    Map<String, String> map = new HashMap<>();
    private final File  file = new File("src/client/resources/data/login.txt");

    public Credentials() {
        map = readLogin();
    }

    public String encodeSHA(String text) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return Arrays.toString(digest.digest(text.getBytes(StandardCharsets.UTF_8)));
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public boolean checkLogin(String username, String password) throws IOException, JsonException, NoSuchAlgorithmException {
        Map<String, String> result = readTxt(file);
        if(result.containsKey(username)){
            String passwordRight = map.get(username);
            return passwordRight.equals(encodeSHA(password));
        }
        return false;
    }

    public void addLogin(String username, String password) throws NoSuchAlgorithmException {
        password = encodeSHA(password);
        map.put(username,password);
        writeLogin(map);
    }

    public void writeLogin(Map<String, String> map) {
        writeTxt(map, file);
    }

    public Map<String, String> readLogin() {
        return readTxt(file);
    }

    public Map<String, String> readTxt(File file) {
        Map<String, String> map = new HashMap<>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(file));

            String line = null;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                String name = parts[0].trim();
                String number = parts[1].trim();
                if (!name.equals("") && !number.equals(""))
                    map.put(name, number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                }
            }
        }

        return map;

    }

    public void writeTxt(Map<String, String> map, File file) {
        BufferedWriter bf = null;

        try {
            bf = new BufferedWriter(new FileWriter(file));

            for (Map.Entry<String, String> entry : map.entrySet()) {
                bf.write(entry.getKey() + ":" + entry.getValue());
                bf.newLine();
            }
            bf.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bf.close();
            } catch (Exception e) {
            }
        }
    }


}
