package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import screeenComponents.errorAlert;
import screeenComponents.helperMethods;

public class editGigController {
	//GIGTITLE
	@FXML
	Label gigTitle;
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
	
	public void editGig()
	{
		
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
