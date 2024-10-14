package com.enviopack.reports;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestResultsXmlParser {

    public List<String> parseTestNamesFromXml(String xmlFilePath) {
        List<String> testNames = new ArrayList<>();

        try {
            File file = new File(xmlFilePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("test");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String testName = element.getAttribute("name");  
                    testNames.add(testName);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return testNames;
    }
}
