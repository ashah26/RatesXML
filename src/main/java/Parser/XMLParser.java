package Parser;

import Currency.CurrencyPair;
import jdk.internal.org.xml.sax.SAXParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/*** This class loads the url and parses xml ***/

public class XMLParser {

    CurrencyPair currencyPair =null;
    final String urlTo = "https://rates.fxcm.com/RatesXML";

    BufferedReader bufferedReader = null;
    HttpURLConnection httpURLConnection;
    URLConnection urlConnection=null;
    URL url;
    DocumentBuilder documentBuilder = null;
    DocumentBuilderFactory documentBuilderFactory = null;
    Document document;
    InputStream inputStream = null;

    public XMLParser(CurrencyPair currencyPair){
        this.currencyPair = currencyPair;

        try{
            // create a new DocumentBuilderFactory
            documentBuilderFactory = DocumentBuilderFactory.newInstance();

            // use the factory to create a documentBuilder
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        }catch (ParserConfigurationException ex){
            ex.printStackTrace();
        }


    }
    /*** This method returns xml tree element for the given Currency Pair ***/
    public Element getNodeElement(Document document){
        Element element = null;

        NodeList nodeList = document.getElementsByTagName("Rate");

        for(int i=0; i< nodeList.getLength();i++){

            Node node = nodeList.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE){
                element = (Element) node;
                String symbol = element.getAttribute("Symbol");
                if(symbol.equals(currencyPair.getCurrencyPairName())){
                    return  element;
                }
            }
        }
        System.out.println("Currency Pair is:"+currencyPair.getCurrencyPairName()+" does not exist");
        System.exit(0);
        return element;

    }

    /*** This method returns a DOM document object for the given url ***/

    public Document getDocument(){


        try {
            // set up url connection
            url = new URL(urlTo);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.addRequestProperty("User-Agent","Chrome");
            inputStream = httpURLConnection.getInputStream();
            document = documentBuilder.parse(inputStream);
            document.getDocumentElement().normalize();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return  document;
    }


}
