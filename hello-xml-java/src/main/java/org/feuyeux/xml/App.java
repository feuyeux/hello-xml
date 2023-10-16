package org.feuyeux.xml;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        WriteConfigFile configFile = new WriteConfigFile("xml/my_config.xml");
        try {
            configFile.saveConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ReadConfigFile read = new ReadConfigFile("xml/my_config.xml");
        MyConfig config = read.readConfig();
        System.out.println(config);
    }
}
