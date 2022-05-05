package parkingrush;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

        

/**
 *
 * @author Anna Šimánková
 */
public class APP extends Application {

    Point2D offset; //event source, objekt na kterém to bude provedeno
    Node selected;  //cestu, kterou bude událost při zveřejnění cestovat
    Point2D translateStart; //poskytuje další klasifikaci událostem
    double rotace;
    int minX = 0;
    int maxX = 510;
    int xVelikost;
    int yVelikost;
    

    @Override
    public void start(Stage stage) throws Exception {
        Pane root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        Pane pane = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        Pane grid = FXMLLoader.load(getClass().getResource("FXML.fxml"));
          
        Scene scene = new Scene(root);
       
        stage.getIcons().add(new Image("file:C:\\parking\\src\\img\\icon.png"));
        stage.setTitle("Parking Lot");
       
       
        

        
       root.setOnMousePressed(e -> {                    //při stisknutí myši
        Node target = (Node) e.getTarget();             //cesta získá cíl
        if (target != root&&target != grid ) {                           //pokud se target nerovná root
          
            selected = target;                          //tak select se rovná target
            offset = new Point2D(e.getX(), e.getY());   //offset se nastaví na 2D těleso e s delkou a sirkou x, y
            double orientace= target.getRotate(); 
            rotace=orientace;
            yVelikost = (int) target.getBoundsInParent().getHeight();
            xVelikost = (int) target.getBoundsInParent().getWidth();
            
                 
                
            translateStart = new Point2D(selected.getTranslateX(), selected.getTranslateY());  //translateStart nastaví na nový 2D objekt kde selected(target)získa Translate na delku a sirku X, Y
      System.out.println(translateStart);  
        } else {
            selected = null;   //jinak null
        }
       
        e.consume();  //Marks this Event as consumed
    });
    root.setOnMouseDragged(evt -> {  
        
        
      
        if ((selected !=null)&&(rotace==90.0)) 
        {  
             double hodnota= (evt.getX() - offset.getX() + translateStart.getX());
             if (hodnota+xVelikost<=505&&hodnota>=0)
           selected.setTranslateX(evt.getX() - offset.getX() + translateStart.getX());   //cesta nastaví X u daného objektu a přidá translateStart pro X
        }
        else if ((selected !=null)&&(rotace==0.0))
            
             {  
                double hodnota= (evt.getY() - offset.getY() + translateStart.getY());
                 
                 if (hodnota+yVelikost<=505&&hodnota>=0)
          selected.setTranslateY(evt.getY() - offset.getY() + translateStart.getY());   //cesta nastaví Y u daného objektu a přidá translateStart pro Y
            
            System.out.println(xVelikost);  
             System.out.println(yVelikost);  
           System.out.println(selected.getTranslateY());  
System.out.println(translateStart.getY());
System.out.println(" ");
      }
        

               
evt.consume();
               

    });
    

        stage.setScene(scene);
        stage.show(); 
  }
      
          public void checkCollisions() {

       
            }
        
  
  
    public static void main(String[] args) {
        launch(args);
    }

}