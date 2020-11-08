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
import java.util.stream.Collectors;

public class XMLParser {


   private ArrayList<Area> areas = new ArrayList<Area>();
   
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
      Node trailer = root.getAttributes().getNamedItem("trailer");
      Node office = root.getAttributes().getNamedItem("office");

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

            roles.add(new Role(roleName, rank, description, false));
         }
         
         //initialize Set
         area = new Set(areaName, takes, (Role[])roles.toArray());
         areas.add(area);
            
      }//for book nodes


      //tailers
      if(trailer != null)
         areas.add(new Trailers());

      
      //casting office
      if(office != null){
         areas.add(new CastingOffice());
         //setup upgrade
      }

      //add neighbors to sets
      NodeList neighborNodes;
      for (int i=0; i<sets.getLength();i++){
         set = sets.item(i);
         String name = set.getAttributes().getNamedItem("name").getNodeValue();
         area = areas.stream()
            .filter(a -> a.getName().equals(name))
            .findAny()
            .orElse(null);
         if(area == null) continue;


         neighborNodes = set.getAttributes().getNamedItem("neighbors").getChildNodes();

         addNeighbors(area, neighborNodes);
      }
      
      //add neighbors to trailers
      area = areas.stream()
         .filter(a -> a instanceof Trailers)
         .findAny()
         .orElse(null);
      if(area != null){
         neighborNodes = trailer.getAttributes().getNamedItem("neighbors").getChildNodes();

         addNeighbors(area, neighborNodes);
      }

      //add neighbors to casting office
      area = areas.stream()
         .filter(a -> a instanceof CastingOffice)
         .findAny()
         .orElse(null);
      if(area != null){
         neighborNodes = office.getAttributes().getNamedItem("neighbors").getChildNodes();

         addNeighbors(area, neighborNodes);
      }

      return areas;
   
   }

   private void addNeighbors(Area area, NodeList neighborNodes){
      Node neighborNode;
      ArrayList<Area> neighbors = new ArrayList<Area>();
      ArrayList<String> neighborNames = new ArrayList<String>();
      for (int i=0; i<neighborNodes.getLength();i++){
         neighborNode = neighborNodes.item(i);
         neighborNames.add(neighborNode.getAttributes().getNamedItem("name").getNodeValue());
      }

      neighbors = new ArrayList<Area>(
         areas.stream()
            .filter(a -> neighborNames.contains(a.getName()))
            .collect(Collectors.toList())
      );

      area.setNeighbors(neighbors);
   }


   //Thannaree
   public ArrayList<SceneCard> readSceneData(Document d){

      ArrayList<SceneCard> sceneInfo = new ArrayList<SceneCard>();
      SceneCard scene;
      ArrayList<Role> roleInfo = new ArrayList<Role>();
      ArrayList<Area> areas = new ArrayList<Area>();
      Area area;

      Element root = d.getDocumentElement();
      
      NodeList cards = root.getElementsByTagName("card");
      
      for (int i=0; i<cards.getLength();i++){
       
         //reads data from the nodes
         Node card = cards.item(i);
         
         String cardName = card.getAttributes().getNamedItem("name").getNodeValue();
         String image = card.getAttributes().getNamedItem("img").getNodeValue();
         int budget = Integer.parseInt(card.getAttributes().getNamedItem("budget").getNodeValue());
  
         //reads data
                                    
         NodeList children = card.getChildNodes();
         
         for (int j=1; j< children.getLength(); j++){
            
            Node sub = children.item(j);
            
            if("scene".equals(sub.getNodeName())){
               int sceneNum = Integer.parseInt(sub.getAttributes().getNamedItem("number").getNodeValue());
               String sceneDescr = sub.getTextContent();
            }
            
            else if("part".equals(sub.getNodeName())){
               String partName = sub.getAttributes().getNamedItem("name").getNodeValue();
               int partLevel = Integer.parseInt(sub.getAttributes().getNamedItem("level").getNodeValue());
            }
            else if("area".equals(sub.getNodeName())){
               int areaCoordX = Integer.parseInt(sub.getAttributes().getNamedItem("x").getNodeValue());
               int areaCoordY = Integer.parseInt(sub.getAttributes().getNamedItem("y").getNodeValue());
               int areaCoordH = Integer.parseInt(sub.getAttributes().getNamedItem("h").getNodeValue());
               int areaCoordW = Integer.parseInt(sub.getAttributes().getNamedItem("w").getNodeValue());  
            }
            else if("line".equals(sub.getNodeName())){
               String lineDescr = sub.getTextContent();
            }

            //initialize new SceneCard
            scene = new SceneCard(cardName, sceneNum, budget, sceneDescr); 
            //add new scene to ArrayList
            sceneInfo.add(scene);

            //add new role to ArrayList
            roleInfo.add(new Role(partName, partLevel, "", false));

            //add new coordinate to ArrayList
            areas.add(new Area(areaCoordX, areaCoordY, areaCoordH, areaCoordW));
         } //for childnodes
         
      }//for book nodes

      return scene;
   
   }// method

    



}//class