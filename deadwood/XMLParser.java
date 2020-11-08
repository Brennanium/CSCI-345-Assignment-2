package deadwood;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import deadwood.model.areas.*;
import deadwood.model.*;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;

public class XMLParser {

   
   // building a document from the XML file
   // returns a Document object after loading the book.xml file.
   public Document getDocFromFile(String filename) throws ParserConfigurationException {
 
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = null;
      
      try{
            doc = db.parse(filename);
      } catch (Exception ex){
            System.out.println("XML parse failure");
            ex.printStackTrace();
      }
      return doc;
      // exception handling
   
   }  
        
   //Brennan
   public ArrayList<Area> readAreaData(Document d){
   
      Element root = d.getDocumentElement();
      
      NodeList sets = root.getElementsByTagName("set");
      NodeList trailer = root.getElementsByTagName("trailer");
      NodeList office = root.getElementsByTagName("office");
      
      for (int i=0; i<sets.getLength();i++){
            
         System.out.println("Printing information for book "+(i+1));
         
         //reads data from the nodes
         Node set = sets.item(i);
         String bookCategory = set.getAttributes().getNamedItem("category").getNodeValue();
         System.out.println("Category = "+bookCategory);
         
         //reads data
                                    
         NodeList children = set.getChildNodes();
         
         for (int j=0; j< children.getLength(); j++){
            
            Node sub = children.item(j);
            
            if("title".equals(sub.getNodeName())){
               String bookLanguage = sub.getAttributes().getNamedItem("lang").getNodeValue();
               System.out.println("Language = "+bookLanguage);
               String title = sub.getTextContent();
               System.out.println("Title = "+title);
               
            }
            
            else if("author".equals(sub.getNodeName())){
               String authorName = sub.getTextContent();
               System.out.println(" Author = "+authorName);
               
            }
            else if("year".equals(sub.getNodeName())){
               String yearVal = sub.getTextContent();
               System.out.println(" Publication Year = "+yearVal);
               
            }
            else if("price".equals(sub.getNodeName())){
               String priceVal = sub.getTextContent();
               System.out.println(" Price = "+priceVal);
               
            }
                        
         
         } //for childnodes
         
         System.out.println("\n");
            
      }//for book nodes

      return new ArrayList<Area>(0);
   
   }// method


   //Thannaree
   public ArrayList<SceneCard> readSceneData(Document d){
   
      Element root = d.getDocumentElement();
      
      NodeList cards = root.getElementsByTagName("card");
      
      for (int i=0; i<cards.getLength();i++){
            
         //System.out.println("Printing information for book "+(i+1));
         
         //reads data from the nodes
         Node card = cards.item(i);
         String cardName = cards.getAttributes().getNamedItem("name").getNodeValue();
         String image = cards.getAttributes().getNamedItem("img").getNodeValue();
         String budget = cards.getAttributes().getNamedItem("budget").getNodeValue();
         //System.out.println("Category = "+bookCategory);
         
         //reads data
                                    
         NodeList children = card.getChildNodes();
         
         for (int j=0; j< children.getLength(); j++){
            
            Node sub = children.item(j);
            
            if("scene".equals(sub.getNodeName())){
               String sceneNum = sub.getAttributes().getNamedItem("number").getNodeValue();
               //System.out.println("Language = "+bookLanguage);
               String sceneDescr = sub.getTextContent();
               //System.out.println("Title = "+title);
               
            }
            
            else if("part".equals(sub.getNodeName())){
               String partName = sub.getAttributes().getNamedItem("name").getNodeValue();
               String partLevel = sub.getAttributes().getNamedItem("level").getNodeValue();
               //System.out.println(" Author = "+authorName);
               
            }
            else if("area".equals(sub.getNodeName())){
               String areaCoordX = sub.getAttributes().getNamedItem("x").getNodeValue();
               String areaCoordY = sub.getAttributes().getNamedItem("y").getNodeValue();
               String areaCoordH = sub.getAttributes().getNamedItem("h").getNodeValue();
               String areaCoordW = sub.getAttributes().getNamedItem("w").getNodeValue();
               //System.out.println(" Publication Year = "+yearVal);
               
            }
            else if("line".equals(sub.getNodeName())){
               String lineDescr = sub.getTextContent();
               //System.out.println(" Price = "+priceVal);
               
            }
                        
         
         } //for childnodes
         
         //System.out.println("\n");
            
      }//for book nodes

      return new ArrayList<SceneCard>(0);
   
   }// method

    



}//class