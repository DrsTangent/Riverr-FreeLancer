package controllers;

import DataManagment.dataEntry;
import DataManagment.dataRetrival;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import relations.User;
import screeenComponents.customDialogBox;
import screeenComponents.errorAlert;
import screeenComponents.helperMethods;

public class addGigController {
	
	private static customDialogBox dialog;
	//GIGTITLE
	@FXML
	TextField gigTitle;
	@FXML
	TextArea gigDescription;
	
	//Basic//
	@FXML
	CheckBox basicCheckBox;
	@FXML 
	TextField basicPrice;
	@FXML 
	TextArea basicDescription;
	
	//Basic//
	@FXML
	CheckBox standardCheckBox;
	@FXML 
	TextField standardPrice;
	@FXML 
	TextArea standardDescription;
	
	//Basic//
	@FXML
	CheckBox premiumCheckBox;
	@FXML 
	TextField premiumPrice;
	@FXML 
	TextArea premiumDescription;
	
	public static void init()
	{
		dialog = new customDialogBox("Add New Gig", "../layouts/gigAdder.fxml");
		dialog.showAndWait();
	}
	
	public void addGig()
	{
		if(isValidGig())
		{
			int gigID = dataRetrival.getUniqueGigID();
			dataEntry.addGig(gigID, gigTitle.getText(), gigDescription.getText(),
					User.getID(), "1");
			if(basicCheckBox.isSelected())
			{
				dataEntry.basicPackage(dataRetrival.getUniquePackageID(), gigID, basicDescription.getText(), Double.parseDouble(basicPrice.getText()));
			}
			if(standardCheckBox.isSelected())
			{
				dataEntry.standardPackage(dataRetrival.getUniquePackageID(), gigID, standardDescription.getText(), Double.parseDouble(standardPrice.getText()));
			}
			if(premiumCheckBox.isSelected())
			{
				dataEntry.premiumPackage(dataRetrival.getUniquePackageID(), gigID, standardDescription.getText(), Double.parseDouble(premiumPrice.getText()));
			}
			dialog.close();
		}
	}
	
	//Functions as follow
	private boolean isValidGig()
	{
		if(gigTitle.getText().isBlank())
		{
			errorAlert.showError("Gig Title is Empty", "Gig Title must be entered");
			return false;
		}
		else if(gigDescription.getText().isBlank())
		{
			errorAlert.showError("Gig Description is Empty", "Gig must have a description");
			return false;
		}
		else if(!basicCheckBox.isSelected() && !standardCheckBox.isSelected()  && !premiumCheckBox.isSelected() )
		{
			errorAlert.showError("No Package is Selected", "A Package for a gig must be entered");
			return false;
		}
		else if(!isValidBasic() && !isValidStandard() && !isValidPremium())
		{
			return false;
		}
		else
			return true;
			
	}
	
	private boolean isValidBasic()
	{
		if(basicCheckBox.isSelected())
		{
			if(basicPrice.getText().isBlank())
			{
				errorAlert.showError("Basic Price is Empty", "Please enter a value for basic package type");
				return false;
			}
		}
		return true;
	}
	
	private boolean isValidStandard()
	{
		if(standardCheckBox.isSelected())
		{
			if(standardPrice.getText().isBlank())
			{
				errorAlert.showError("Standard Price is Empty", "Please enter a value for Standard package type");
				return false;
			}
		}
		return true;
	}
	
	private boolean isValidPremium()
	{
		if(premiumCheckBox.isSelected())
		{
			if(premiumPrice.getText().isBlank())
			{
				errorAlert.showError("Premium Price is Empty", "Please enter a value for Premium package type");
				return false;
			}
		}
		return true;
	}
	
	public void initialize()
	{
		helperMethods.onlyNumberTextField(basicPrice);
		helperMethods.onlyNumberTextField(standardPrice);
		helperMethods.onlyNumberTextField(premiumPrice);
	}
}
