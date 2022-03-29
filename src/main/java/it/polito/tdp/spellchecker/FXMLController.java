

package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Dictionary model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private ComboBox<String> cmbChoice;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnSpellCheck;

    @FXML
    private TextArea txtResult;

    @FXML
    private TextArea txtUser;

    @FXML
    void doClearText(ActionEvent event) {
      txtUser.clear();
      txtResult.clear();
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	String s= txtUser.getText();
    	LinkedList<String> inputText=(LinkedList<String>) model.testoInList(s);
    	model.loadDictionary(cmbChoice.getValue());
    	long start,end ;
    	start=System.currentTimeMillis();
    	LinkedList<RichWord> ritorno=(LinkedList<RichWord>) model.spellCheckText(inputText);
    	end=System.currentTimeMillis();
        model.liberaDizionario();
        txtResult.setText(model.stampa(ritorno));
        //long p=System.nanoTime();
        txtResult.appendText("Prestazione della ricerca in ms: "+(end-start)+" ms");
        
    }

    @FXML
    void initialize() {
    	assert cmbChoice != null : "fx:id=\"cmbChoice\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtUser != null : "fx:id=\"txtUser\" was not injected: check your FXML file 'Scene.fxml'.";
        
        cmbChoice.getItems().clear();
        cmbChoice.getItems().add("Italian");
        cmbChoice.getItems().add("English");

    }

    public void setModel(Dictionary dizionario) {
    	this.model=dizionario;
    }
    
    
}
