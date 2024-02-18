package fr.kaplone.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Default {

    private final static String keySeparator = "app.separator.code";
    private final static String keyStringLength = "app.test.generator.string.length";
    private final static String keyStringMinValue = "app.test.generator.string.min.value";
    private final static String keyStringMaxValue = "app.test.generator.string.max.value";
    public static final char SEPARATOR = SeparatorEnum.valueOf(readConfig().get(keySeparator)).getValue();
    public static final int STRING_LENGTH = Integer.parseInt(readConfig().get(keyStringLength));
    public static final int STRING_MIN_VALUE = Integer.parseInt(readConfig().get(keyStringMinValue));
    public static final int STRING_MAX_VALUE = Integer.parseInt(readConfig().get(keyStringMaxValue));


    public static Map<String, String> readConfig(){
        String fileName = "src/main/resources/settings.cfg";
        List<String> list = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            list = br.lines().collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, String> config = new HashMap<>();
        for (String s : list){
            config.put(s.split("=")[0], s.split("=")[1]);
        }

        return config;
    }
}
