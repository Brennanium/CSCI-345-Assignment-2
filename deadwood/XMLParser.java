package deadwood;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import deadwood.model.areas.*;
import deadwood.model.*;
import deadwood.model.Role;

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
      Node set;
      NodeList trailer = root.getElementsByTagName("trailer");
      NodeList office = root.getElementsByTagName("office");

      ArrayList<Area> areas = new ArrayList<Area>();
      Area area;
      String areaName;
      int takes;

      NodeList parts;
      Node part;
      String roleName;
      int rank;
      String description;
      ArrayList<Role> roles;
      
      for (int i=0; i<sets.getLength();i++){

         //set
         set = sets.item(i);
         areaName = set.getAttributes().getNamedItem("name").getNodeValue();
         takes = set.getAttributes().getNamedItem("takes").getChildNodes().getLength();
         
         //roles
         parts = set.getAttributes().getNamedItem("parts").getChildNodes();
         roles = new ArrayList<Role>();
         for (int j=0; j< parts.getLength(); j++){
            part = parts.item(j);

            roleName = part.getAttributes().getNamedItem("name").getNodeValue();
            rank = Integer.parseInt(part.getAttributes().getNamedItem("level").getNodeValue());
            description = part.getAttributes().getNamedItem("line").getTextContent();

            roles.add(new Role(roleName, rank, description, true));
         }
         
         
         
            
      }//for book nodes

      return new ArrayList<Area>(0);
   
   }// method


   //Thannaree
   public ArrayList<SceneCard> readSceneData(Document d){

      ArrayList<SceneCard> info = new ArrayList<SceneCard>();
      SceneCard scene;
   
      Element root = d.getDocumentElement();
      
      NodeList cards = root.getElementsByTagName("card");
      
      for (int i=0; i<cards.getLength();i++){
         //System.out.println("Printing information for book "+(i+1));
         
         //reads data from the nodes
         Node card = cards.item(i);
         
         String cardName = card.getAttributes().getNamedItem("name").getNodeValue();
         String image = card.getAttributes().getNamedItem("img").getNodeValue();
         int budget = Integer.parseInt(card.getAttributes().getNamedItem("budget").getNodeValue());
         //System.out.println("Category = "+bookCategory);
         
         //initialize new SceneCard
         scene = new SceneCard(cardName, 0, budget); 
         //add new scene to ArrayList
         info.add(scene);


         //reads data
                                    
         NodeList children = card.getChildNodes();
      
         /*
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
         */

            
      }//for book nodes

      return new ArrayList<SceneCard>(0);
   
   }// method

    



}//class