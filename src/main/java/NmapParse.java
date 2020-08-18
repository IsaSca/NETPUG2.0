import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
public class NmapParse {
  File xmlFile;

  public File getXmlFile() {
    return xmlFile;
  }

  public DocumentBuilderFactory getXmlFactory() {
    return xmlFactory;
  }

  public DocumentBuilder getXmlBuilder() {
    return xmlBuilder;
  }

  public Document getXmlDoc() {
    return xmlDoc;
  }

  DocumentBuilderFactory xmlFactory = DocumentBuilderFactory.newInstance();
  DocumentBuilder xmlBuilder = xmlFactory.newDocumentBuilder();
  Document xmlDoc;

  public NmapParse(String xmlPath) throws ParserConfigurationException, IOException, SAXException {
    xmlFile = new File(xmlPath);
    xmlDoc = xmlBuilder.parse(xmlFile);
    xmlDoc.getDocumentElement().normalize();
  }

  public NodeList getDetails() {
    return xmlDoc.getElementsByTagName("host");
  }
}
