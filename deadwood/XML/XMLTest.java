package deadwood.XML;

// Example Code for parsing XML file
// Dr. Moushumi Sharmin
// CSCI 345

import java.util.ArrayList;

import org.w3c.dom.Document;
import deadwood.model.*;
import deadwood.model.areas.*;


public class XMLTest{

   public static void main(String args[]) throws Exception {
   
      Document doc1 = null;
      Document doc2 = null;
      XMLParser parsing = new XMLParser();
      ArrayList<SceneCard> scenes;
      ArrayList<Area> areas;

      //try{
      
         doc1 = parsing.getDocFromFile("./CSCI-345-Assignment-2/deadwood/XML/cards.xml");
         scenes = parsing.readSceneData(doc1);

         /* scenes.stream()
            .forEach(s -> System.out.println(s.getSceneName())); */

         doc2 = parsing.getDocFromFile("./CSCI-345-Assignment-2/deadwood/XML/board.xml");
         areas = parsing.readAreaData(doc2);
         
         /* areas.stream()
            .forEach((Area a) -> {
               System.out.print(a.getName() + "\t\t neighbors: ");
               a.getNeighbors().forEach(b -> System.out.print(b.getName() + " "));
               System.out.println();
            }); */
      
      /* }catch (Exception e){
      
         System.out.println("Error = "+e + "\n" + e.getStackTrace());
      
      } */
      
   
   }
}