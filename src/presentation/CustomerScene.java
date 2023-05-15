package presentation;
import presentation.*;
import persistence.*;
import domain.Customer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.stage.Stage;
import persistence.CustomerDataAccess;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CustomerScene {
	static Scene customerScene(Stage primaryStage){
		
		//Customer
		Label nameLbl = new Label("Name ");
		Label phoneNumLbl = new Label("Phone Number ");
		Label emailLbl = new Label("Email ");
		
		TextField nameTxtField = new TextField();
		nameTxtField.setPrefWidth(500);
		TextField phoneNumTxtField= new TextField();
		TextField emailTxtField = new TextField();
		
		HBox hbox1 = new HBox(nameLbl, nameTxtField);
		HBox hbox2 = new HBox(phoneNumLbl, phoneNumTxtField, emailLbl, emailTxtField);
		
		//Address
		Label streetLbl = new Label("Street");
		Label cityLbl = new Label("City");
		Label stateLbl = new Label("State");
		Label zipCodeLbl = new Label("Zip Code");
		
		TextField streetTxtField = new TextField();
		TextField cityTxtField= new TextField();
		TextField stateTxtField = new TextField();
		TextField zipCodeTxtField = new TextField();
		Text addressText = new Text("Address");
		addressText.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		
		HBox hbox3 = new HBox(streetLbl, streetTxtField, cityLbl, cityTxtField);
		HBox hbox4 = new HBox(stateLbl, stateTxtField, zipCodeLbl, zipCodeTxtField);
	
		Button btnSearch = new Button("Search");
		Button btnAdd = new Button("Add");
		Button btnUpdate = new Button("Update");
		Button btnDelete = new Button("Delete");
		Button btnBack = new Button("Back");
		
		
		HBox hbox5 = new HBox(btnBack, btnSearch, btnAdd, btnUpdate, btnDelete);
		hbox5.setSpacing(14);
		
		btnAdd.setOnAction(e ->{
			String nameAdd = nameTxtField.getText();
			String phoneNumAdd = phoneNumTxtField.getText();
			String emailAdd = emailTxtField.getText();
			String streetAdd = streetTxtField.getText();
			String cityAdd = cityTxtField.getText();
			String stateAdd = stateTxtField.getText();
			String zipCodeAdd = zipCodeTxtField.getText();
			
			boolean addedCustomer = CustomerDataAccess.addCustomer(nameAdd, phoneNumAdd, emailAdd, streetAdd, cityAdd, stateAdd, Integer.valueOf(zipCodeAdd));
			
			showAddedAlert(addedCustomer, nameAdd);
			
			nameTxtField.clear();
			phoneNumTxtField.clear();
			emailTxtField.clear();
			streetTxtField.clear();
			cityTxtField.clear();
			stateTxtField.clear();
			zipCodeTxtField.clear();
		});
		
		
		
		btnSearch.setOnAction(e ->{
			String name = nameTxtField.getText();
			
			Customer customer = CustomerDataAccess.searchCustomer(name);
			
			if(customer != null)
			{	
				phoneNumTxtField.setText(customer.getPhone());
				emailTxtField.setText(customer.getEmail());
				streetTxtField.setText(customer.getAddress().getStreet());
				cityTxtField.setText(customer.getAddress().getCity());
				stateTxtField.setText(customer.getAddress().getState());
				zipCodeTxtField.setText(String.valueOf(customer.getAddress().getZipCode()));
			}
			else
			{
				showNotFoundAlert();
			}
		});
		
		btnUpdate.setOnAction(e ->{
			
			String name = nameTxtField.getText();
			String phoneNumUpdate = phoneNumTxtField.getText();
			String emailUpdate = emailTxtField.getText();
			String streetUpdate = streetTxtField.getText();
			String cityUpdate = cityTxtField.getText();
			String stateUpdate = stateTxtField.getText();
			String zipCodeUpdate = zipCodeTxtField.getText();
			
			boolean updatedCustomer = CustomerDataAccess.updateCustomer(name, phoneNumUpdate, emailUpdate, streetUpdate, cityUpdate, stateUpdate, Integer.valueOf(zipCodeUpdate));
			
			showUpdatedAlert(updatedCustomer);
			
			nameTxtField.clear();
			phoneNumTxtField.clear();
			emailTxtField.clear();
			streetTxtField.clear();
			cityTxtField.clear();
			stateTxtField.clear();
			zipCodeTxtField.clear();
		});
		
		btnDelete.setOnAction(e ->{
			
			String name = nameTxtField.getText();
			
			boolean deletedCustomer = CustomerDataAccess.deleteCustomer(name);
			
			deletedCustomerAlert(deletedCustomer);
			
			nameTxtField.clear();
			phoneNumTxtField.clear();
			emailTxtField.clear();
			streetTxtField.clear();
			cityTxtField.clear();
			stateTxtField.clear();
			zipCodeTxtField.clear();
		});
		
		
		btnBack.setOnAction(e -> {
			Scene scene = MenuScene.menuScene(primaryStage);
			primaryStage.setScene(scene);
		});
		
		VBox vbox = new VBox(hbox1, hbox2, addressText, hbox3, hbox4, hbox5);
		hbox1.setMargin(phoneNumTxtField, new Insets(0, 100, 0, 0));
		hbox2.setMargin(streetTxtField, new Insets(0, 160, 0, 0));
		hbox3.setMargin(stateTxtField, new Insets(0, 150, 0, 0));
		
		vbox.setMargin(hbox1, new Insets(0, 0, 0, 200));
		vbox.setMargin(hbox2, new Insets(0, 0, 0, 200));
		vbox.setMargin(addressText, new Insets(0, 0, 0, 200));
		vbox.setMargin(hbox3, new Insets(0, 0, 0, 200));
		vbox.setMargin(hbox4, new Insets(0, 0, 0, 200));
		vbox.setMargin(hbox5, new Insets(0, 0, 0, 500));
		
		vbox.setSpacing(50);

		vbox.setAlignment(Pos.CENTER_LEFT);
		
		Scene customerScene = new Scene(vbox, 900, 600);
		
		return customerScene;
	}
	
	private static void showAddedAlert(boolean addedCustomer, String name) {
		if(addedCustomer)
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Customer Added!");
			alert.setContentText(name + " is added into the system!");
			alert.showAndWait();
		}
		else
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Error!");
			alert.setContentText("There was a problem with adding the customer into the system.");
			alert.showAndWait();
		}
	}
	
	private static void showNotFoundAlert() {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Error!");
			alert.setContentText("No Records Found!");
			alert.showAndWait();
	}
	
	private static void showUpdatedAlert(boolean updatedCustomer) {
		if(updatedCustomer)
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Customer Updated!");
			alert.setContentText("Customer Data Successfully Updated!");
			alert.showAndWait();
		}
		else
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Error!");
			alert.setContentText("There was a problem with updating the student!");
			alert.showAndWait();
		}
	}
	
	private static void deletedCustomerAlert(boolean updatedCustomer) {
		if(updatedCustomer)
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Customer Deleted!");
			alert.setContentText("Customer Data Successfully Deleted!");
			alert.showAndWait();
		}
		else
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Error!");
			alert.setContentText("There was a problem with deleting the student!");
			alert.showAndWait();
		}
	}
	
	
	
	
}