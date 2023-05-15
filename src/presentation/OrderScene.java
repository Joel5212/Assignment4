package presentation;
import persistence.*;

import java.util.List;

import domain.Customer;
import domain.Order;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.stage.Stage;
import persistence.CustomerDataAccess;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;

public class OrderScene {
	static Scene orderScene(Stage primaryStage){
		
		Label numberLbl = new Label("Number ");
		Label dateLbl = new Label("Date ");
		Label customerLbl = new Label("Customer ");
		Label itemLbl = new Label("Item ");
		Label priceLbl = new Label("Price ($) ");
		
		TextField numberTxtField = new TextField();
		TextField dateTxtField= new TextField();
		ComboBox customerCB = new ComboBox();
		customerCB.setPrefWidth(420);
		ComboBox itemCB = new ComboBox();
		TextField priceTxtField = new TextField();
		
		
		HBox hbox1 = new HBox(numberLbl, numberTxtField, dateLbl, dateTxtField);
		hbox1.setMargin(dateLbl, new Insets(0, 0, 0, 100));
		HBox hbox2 = new HBox(customerLbl, customerCB);
		HBox hbox3 = new HBox(itemLbl, itemCB, priceLbl, priceTxtField);
		hbox3.setMargin(priceLbl, new Insets(0, 0, 0, 145));
		
		
		Button btnSearch = new Button("Search");
		Button btnAdd = new Button("Add");
		Button btnUpdate = new Button("Update");
		Button btnDelete = new Button("Delete");
		Button btnBack = new Button("Back");
		
		List<Customer> customers = CustomerDataAccess.getListOfCustomers();
		
		for(Customer customer : customers)
		{
			customerCB.getItems().add(customer.getName());
		}
		
		itemCB.getItems().addAll("Caesar Salad", "Greek Salad", "Cobb Salad");
		
		
		
		
		HBox hbox4 = new HBox(btnBack, btnSearch, btnAdd, btnUpdate, btnDelete);
		hbox4.setSpacing(14);
		
		btnAdd.setOnAction(e ->{
			int numberAdd = Integer.valueOf(numberTxtField.getText());
			String dateAdd = dateTxtField.getText();
			String customerAdd = (String) customerCB.getValue();
			Customer customer = CustomerDataAccess.searchCustomer(customerAdd);
			String itemAdd = (String) itemCB.getValue();
			double priceAdd = Double.valueOf(priceTxtField.getText());
			
			boolean addedOrder = OrderDataAccess.addOrder(numberAdd, dateAdd, customer, itemAdd, priceAdd);
			
			showAddedAlert(addedOrder, customerAdd);
			
			numberTxtField.clear();
			dateTxtField.clear();
			customerCB.setValue(null);
			itemCB.setValue(null);
			priceTxtField.clear();
		});
		
		btnSearch.setOnAction(e ->{
			int numberSearch = Integer.valueOf(numberTxtField.getText());
			
			Order order = OrderDataAccess.searchOrder(numberSearch);
			
			if(order != null)
			{	
				dateTxtField.setText(order.getDate());
				customerCB.setValue(order.getCustomer().getName());
				itemCB.setValue(order.getItem());
				priceTxtField.setText(String.valueOf(order.getPrice()));
			}
			else
			{
				showNotFoundAlert();
			}
		});
		
		btnUpdate.setOnAction(e ->{
			
			int numberUpdate = Integer.valueOf(numberTxtField.getText());
			String dateUpdate = dateTxtField.getText();
			String customerAdd = (String) customerCB.getValue();
			Customer customer = CustomerDataAccess.searchCustomer(customerAdd);
			String itemUpdate = (String) itemCB.getValue();
			double priceUpdate = Double.valueOf(priceTxtField.getText());
			
			boolean updatedCustomer = OrderDataAccess.updateOrder(numberUpdate, dateUpdate, customer, itemUpdate, priceUpdate);
			
			showUpdatedAlert(updatedCustomer);
			
			numberTxtField.clear();
			dateTxtField.clear();
			customerCB.setValue(null);
			itemCB.setValue(null);
			priceTxtField.clear();
		});
		
		btnDelete.setOnAction(e ->{
			
			int numberDelete = Integer.valueOf(numberTxtField.getText());
			
			boolean deletedOrder = OrderDataAccess.deleteOrder(numberDelete);
			
			deletedCustomerAlert(deletedOrder);
			
			numberTxtField.clear();
			dateTxtField.clear();
			customerCB.setValue(null);
			itemCB.setValue(null);
			priceTxtField.clear();
		});
		
		btnBack.setOnAction(e -> {
			Scene scene = MenuScene.menuScene(primaryStage);
			primaryStage.setScene(scene);
		});
		
		
		
		VBox vbox = new VBox(hbox1, hbox2, hbox3, hbox4);
		
		vbox.setMargin(hbox1, new Insets(0, 0, 0, 200));
		vbox.setMargin(hbox2, new Insets(0, 0, 0, 200));
		vbox.setMargin(hbox3, new Insets(0, 0, 0, 200));
		vbox.setMargin(hbox4, new Insets(0, 0, 0, 400));
		
		vbox.setSpacing(50);

		vbox.setAlignment(Pos.CENTER_LEFT);
		
		Scene orderScene = new Scene(vbox, 900, 600);
		
		return orderScene;
	}
	
	private static void showAddedAlert(boolean addedCustomer, String name) {
		if(addedCustomer)
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Order Added!");
			alert.setContentText("Order for " + name + " is added into the system!");
			alert.showAndWait();
		}
		else
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Error!");
			alert.setContentText("There was a problem with adding the order into the system.");
			alert.showAndWait();
		}
	}
	
	private static void showNotFoundAlert() {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Error!");
			alert.setContentText("No records Found!");
			alert.showAndWait();
	}
	
	private static void showUpdatedAlert(boolean updatedCustomer) {
		if(updatedCustomer)
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Order Updated!");
			alert.setContentText("Order data Successfully Updated!");
			alert.showAndWait();
		}
		else
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Error!");
			alert.setContentText("There was a problem with updating the order!");
			alert.showAndWait();
		}
	}
	
	private static void deletedCustomerAlert(boolean updatedCustomer) {
		if(updatedCustomer)
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Order Deleted!");
			alert.setContentText("Order Data Successfully Deleted!");
			alert.showAndWait();
		}
		else
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Error!");
			alert.setContentText("There was a problem with deleting the order!");
			alert.showAndWait();
		}
	}
	
	
	
	
}