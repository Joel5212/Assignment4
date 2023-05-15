package presentation;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene; 
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class MenuScene extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Scene scene = menuScene(primaryStage);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Menu");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Scene menuScene(Stage primaryStage)
	{
		Button btnCustomer = new Button("Customer");
		Button btnOrder = new Button("Order");
		
		btnCustomer.setOnAction(e ->{
			Scene scene = CustomerScene.customerScene(primaryStage);
			primaryStage.setTitle("Customer");
			primaryStage.setScene(scene);
		});
		
		btnOrder.setOnAction(e ->{
			Scene scene = OrderScene.orderScene(primaryStage);
			primaryStage.setTitle("Order");
			primaryStage.setScene(scene);
			
			System.out.println("HELLO");
		});
		
		btnCustomer.setMinWidth(200);
		btnCustomer.setMinHeight(50);
		
		btnOrder.setMinWidth(200);
		btnOrder.setMinHeight(50);
		
		VBox vbox = new VBox(btnCustomer, btnOrder);
		
		vbox.setSpacing(50);
		
		vbox.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(vbox,900,600);
		return scene;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
