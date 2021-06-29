package assign4;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.geometry.Pos;

public class gobang extends Application {
	private char whoseTurn = '●'; // Indicate which player has a turn, initially it is the ●(Black) player
	private Cell[][] cell =  new Cell[20][20]; // Create and initialize cell
	private Label lblStatus = new Label("●'s turn to play");// Create and initialize a status label
	
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
    	GridPane pane = new GridPane();     	
        for (int i = 0; i < 20; i++) {
          for (int j = 0; j < 20; j++)    {     	         	
          	pane.add(cell[i][j] = new Cell(), j, i);
          }
          }

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setBottom(lblStatus);
        borderPane.setAlignment(lblStatus, Pos.CENTER);
       
       // Create a scene and place it in the stage
        Scene scene = new Scene(borderPane, 450, 170);
        primaryStage.setTitle("Gobang"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage   
    }
    //Become all black line
    public void becomeblack() {
        for (int i = 0; i < 20; i++)
          for (int j = 0; j < 20; j++)
           cell[i][j].BlackLine();
      }
    /** Determine if the cell are all occupied */
    public boolean isFull() {
      for (int i = 0; i < 20; i++)
        for (int j = 0; j < 20; j++)
          if (cell[i][j].getToken() == ' ')
            return false;
      return true;
    }
    /** Determine if the player with the specified token wins */ 
    public boolean isWon(char token) {
        for (int i = 0; i < 20; i++) {
          for(int j =0;j < 16;j++) {
              if (cell[i][j].getToken() == token
                  && cell[i][j+1].getToken() == token
                  && cell[i][j+2].getToken() == token
                  && cell[i][j+3].getToken() == token
                  && cell[i][j+4].getToken() == token) {
                return true;
              }
          }
        }
    		for (int j = 0; j < 20; j++) {
    			for(int i =0;i < 16;i++) {
    		      if (cell[i][j].getToken() ==  token
    		          && cell[i+1][j].getToken() == token
    		          && cell[i+2][j].getToken() == token && cell[i+3][j].getToken() == token 
    		          && cell[i+4][j].getToken() == token) {
    		        return true;
    		      }
    			}
    		}
    		
    		for (int i = 0; i < 16; i++) {
        		for(int j =0;j < 16;j++) {
        	      if (cell[i][j].getToken() == token
        	          && cell[i+1][j+1].getToken() == token
        	          && cell[i+2][j+2].getToken() == token
        	          && cell[i+3][j+3].getToken() == token
        	          && cell[i+4][j+4].getToken() == token) {
        	    	  
        	        return true;
        	    	  
        	      }
        		}
        	}
    		
    		for (int i = 0; i < 16; i++) {
        		for(int j = 19;j >= 4 ; j--) {
        	      if (cell[i][j].getToken() == token
        	          && cell[i+1][j-1].getToken() == token
        	          && cell[i+2][j-2].getToken() == token
        	          && cell[i+3][j-3].getToken() == token
        	          && cell[i+4][j-4].getToken() == token) {
        	    	  
        	        return true;
        	    	  
        	      }
        		}
        	}
    		
    	return false;
    }
    /** An inner class for a cell */
    public class Cell extends Pane {	
    	// Token used for this cell
        private char token = ' ';

        public Cell() {
          setStyle("-fx-border-color: black"); 
          this.setPrefSize(2000, 2000);
          this.setOnMouseClicked(e -> handleMouseClick());
        }  
        public char getToken() {
            return token;
          }
        
        public void BlackLine() {
        	setStyle("-fx-border-color: black"); 
          }
        
        public void setToken(char c) {
        	setStyle("-fx-border-color: red"); 
            token = c;
            if (token == '●') {
            	 Ellipse ellipse = new Ellipse(this.getWidth() / 2, 
                  this.getHeight() / 2, this.getWidth() / 2 - 10, 
                  this.getHeight() / 2 - 10);
                ellipse.centerXProperty().bind(
                  this.widthProperty().divide(2));
                ellipse.centerYProperty().bind(
                  this.heightProperty().divide(2));
                ellipse.radiusXProperty().bind(
                  this.widthProperty().divide(2).subtract(10));        
                ellipse.radiusYProperty().bind(
                  this.heightProperty().divide(2).subtract(10));   
                ellipse.setStroke(Color.BLACK);
                ellipse.setFill(Color.BLACK);
                  
                getChildren().add(ellipse); // Add the ellipse to the pane
                       
            }
            else if (token == 'O') {
              Ellipse ellipse = new Ellipse(this.getWidth() / 2, 
                this.getHeight() / 2, this.getWidth() / 2 - 10, 
                this.getHeight() / 2 - 10);
              ellipse.centerXProperty().bind(
                this.widthProperty().divide(2));
              ellipse.centerYProperty().bind(
                  this.heightProperty().divide(2));
              ellipse.radiusXProperty().bind(
                  this.widthProperty().divide(2).subtract(10));        
              ellipse.radiusYProperty().bind(
                  this.heightProperty().divide(2).subtract(10));   
              ellipse.setStroke(Color.BLACK);
              ellipse.setFill(Color.WHITE);
              
              getChildren().add(ellipse); // Add the ellipse to the pane
              
            }            
          }

          /* Handle a mouse click event */
          private void handleMouseClick() {
        	 
            // If cell is empty and game is not over
            if (token == ' ' && whoseTurn != ' ') {
              becomeblack();
              setToken(whoseTurn); // Set token in the cell
              // Check game status
              if (isWon(whoseTurn)) {
                lblStatus.setText(whoseTurn + " won! The game is over");
                whoseTurn = ' '; // Game is over
              }
              else if (isFull()) {
                lblStatus.setText("Draw! The game is over");
                whoseTurn = ' '; // Game is over
              }
              else {           	 
                // Change the turn
                whoseTurn = (whoseTurn == '●') ? 'O' : '●';
                // Display whose turn
                lblStatus.setText(whoseTurn+  "'s turn");
           
              }
            }
          }
        }
    
    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
	      launch(args);
    }
}
