package org.feuyeux.xml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

public class ReadConfigFile {
    private String configFile;

    public ReadConfigFile(String configFile) {
        this.configFile = configFile;
    }

    public MyConfig readConfig() {
        String mode = null, unit = null, current = null, interactive = null;
        try {
            // First create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = new FileInputStream(configFile);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // Read the XML document
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    String localPart = event.asStartElement().getName().getLocalPart();
                    event = eventReader.nextEvent();
                    switch (localPart) {
                        case "mode":
                            mode = event.asCharacters().getData();
                        case "unit":
                            unit = event.asCharacters().getData();
                        case "current":
                            current = event.asCharacters().getData();
                        case "interactive":
                            interactive = event.asCharacters().getData();

                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        return new MyConfig(mode, unit, current, interactive);
    }
}
