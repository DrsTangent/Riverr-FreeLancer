package controllers;

import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.awt.Event;
import java.math.BigDecimal;
import java.math.BigInteger;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import relations.User;
import screeenComponents.completedOrderWrapper;
import screeenComponents.gigWrapper;
import screeenComponents.pendingOrderWrapper;

import java.util.ArrayList;

import DataManagment.dataRetrival;
import javafx.fxml.*;
import javafx.geometry.Insets;

public class userPanelController {
	
	//Over all Screen//
	@FXML
	VBox window;
	//Tab Pane//
	@FXML 
	TabPane tabPane;
	//Profile Tab//
	@FXML
	Tab profileTab;
	@FXML
	Label firstName;
	@FXML
	Label lastName;
	@FXML
	Label contactNumber;
	@FXML
	Label emailAddress;
	@FXML
	Label username;
	//
	@FXML 
	VBox gigsViewer;
	@FXML
	VBox gigContainer;
	@FXML
	VBox completedOrdersWindow;
	@FXML
	VBox currentOrdersWindow;
	
	
	
	//Orders Tab//
	public void ordersTabSelection()
	{
		loadOrders();
	}
	
	 void loadOrders()
	{
		/*Loading Pending Orders*/
		currentOrdersWindow.getChildren().clear();
		ArrayList<ArrayList<Object>> pendingOrders = dataRetrival.getPendingOrders();
		for(ArrayList<Object>  pendingOrder: pendingOrders)
		{
			pendingOrderWrapper pdOrder = new pendingOrderWrapper(
					((BigDecimal)pendingOrder.get(0)).intValue(),
					(String) pendingOrder.get(1), 
					(String) pendingOrder.get(2), 
					(String) pendingOrder.get(3), 
					(String) pendingOrder.get(4)
					);
			pdOrder.getCompleteButton().addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> loadOrders());
			currentOrdersWindow.getChildren().add(pdOrder);
		}
		
		/*Loading Completed Orders*/
		completedOrdersWindow.getChildren().clear();
		ArrayList<ArrayList<Object>> completedOrders = dataRetrival.getCompletedOrders();
		for(ArrayList<Object>  completedOrder: completedOrders)
		{
			completedOrderWrapper cOrder = new completedOrderWrapper(
					(String) completedOrder.get(1), 
					(String) completedOrder.get(2), 
					(String) completedOrder.get(3), 
					(String) completedOrder.get(4),
					(String) completedOrder.get(5)
					);
			completedOrdersWindow.getChildren().add(cOrder);
		}
	}
	
	//Functions//
	//Profile Tab//
	public void editProfile()
	{
		editProfileController.init();
	}
	public void changePass()
	{
		
	}
	public void profileTabSelection()
	{
		firstName.setText(User.getFirstName());
		lastName.setText(User.getLastName());
		contactNumber.setText(User.getContactNumber());
		emailAddress.setText(User.getEmailAddress());
		username.setText(User.getUserName());
		
	}

	//Gigs Tab//
	public void addGig()
	{
		addGigController.init();
		loadGigs();// Incase a new gig is added, it must be reloaded
	}
	public void gigTabSelection()
	{
		loadGigs();
	}
	
	public void loadGigs()
	{
		gigContainer.getChildren().clear();
		ArrayList<ArrayList<Object>> gigs = dataRetrival.getGigs();
		for (ArrayList<Object> gigView: gigs)
		{
			gigWrapper gigwrapper = new gigWrapper(((BigDecimal)gigView.get(0)).intValue(), (String) gigView.get(1),
					(String) gigView.get(2), ((BigDecimal)gigView.get(3)).floatValue());
			gigContainer.getChildren().add(gigwrapper);
		}
	}
	
	public void initialize()
	{
		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		selectionModel.select(profileTab);
	}
}
