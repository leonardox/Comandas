package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import modules.*;

public class Controller {
    private Comanda comanda = new Comanda();

    @FXML
    private TextField pesoAlmocoTextField;

    @FXML
    private TextField valorAlmocoTextField;

    @FXML
    private TextField valorBebidasTextField;

    @FXML
    private TextField valorSorvetesTextField;

    @FXML
    private TextField pesoSorvetesTextField;

    @FXML
    protected ComboBox bebidasComboBox;

    ObservableList<String> bebidasList = FXCollections.observableArrayList("REFRIGERANTE", "SUCOS");
    @FXML
    private void initialize(){
        bebidasComboBox.setValue("REFRIGERANTE");
        bebidasComboBox.setItems(bebidasList);
    }

    @FXML
    protected void onAddAlmocoClicked(ActionEvent e) {
        Item item = new Item();
        item.setDescricption("ALMOÇO");
        item.setValor(Float.parseFloat(pesoAlmocoTextField.getText()) * Float.parseFloat(valorAlmocoTextField.getText()));
        comanda.addItem(item);
        pesoAlmocoTextField.setText("");
    }

    @FXML
    protected void onAddBebidasClicked(ActionEvent e) {
        Item item = new Item();
        item.setDescricption(bebidasComboBox.getItems().get(bebidasComboBox.getSelectionModel().getSelectedIndex()).toString());
        item.setValor(Float.parseFloat(valorBebidasTextField.getText()));
        comanda.addItem(item);
        valorBebidasTextField.setText("");
    }

    @FXML
    protected void onAddSorvetesClicked(ActionEvent e) {
        Item item = new Item();
        item.setDescricption("SORVETE");
        item.setValor(Float.parseFloat(pesoSorvetesTextField.getText()) * Float.parseFloat(valorSorvetesTextField.getText()));
        comanda.addItem(item);
        pesoSorvetesTextField.setText("");
    }

    @FXML
    protected void onPrintClicked(ActionEvent e) {
        System.out.println("Top Gourmet Açaiteria");
        System.out.println(comanda);
    }
}
