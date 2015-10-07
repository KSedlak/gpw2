package pl.spring.demo.desktop.controller;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pl.spring.demo.desktop.App;

import pl.spring.demo.service.CompanyService;
import pl.spring.demo.sprinLoader.SceneMaker;

@Component
public class WelcomeController {

	@FXML
	Button enterButton;

	@Autowired  
	CompanyService c;
	

    
	public void setC(CompanyService c) {
		this.c = c;
	}

	@FXML
	public void enterButtonAction(ActionEvent event) throws IOException {
		Stage stage = (Stage) enterButton.getScene().getWindow();
		stage.setScene(SceneMaker.getSceneFromFXML("firstPage"));
		
		
		
		if(c==null){
			System.out.println("null");
       

	}
		else{
			c.findAll().stream().forEach(c->{
				System.out.println(c.getName());
			});
		}

}}
