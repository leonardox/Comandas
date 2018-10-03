package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modules.*;

public class Controller {
    private static final long serialVersionUID = 7813064106536867782L;
    public int iReturn;
    String retornos;

    Image imageAdd = new Image("resourses/images/add.png", 20, 20, false, false);
    Image imagePrint = new Image("resourses/images/print.png", 30, 30, false, false);
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

    @FXML
    Button almocoAddButton = new Button();

    @FXML
    Button almocoPrintButton = new Button();
    @FXML
    Button bebidasPrintButton = new Button();
    @FXML
    Button bebidasAddButton = new Button();
    @FXML
    Button sorvetesPrintButton = new Button();
    @FXML
    Button sorvetesAddButton = new Button();

    ObservableList<String> bebidasList = FXCollections.observableArrayList("REFRIGERANTE", "SUCOS");
    @FXML
    private void initialize(){
        bebidasComboBox.setValue("REFRIGERANTE");
        bebidasComboBox.setItems(bebidasList);
        almocoAddButton.setGraphic(new ImageView(imageAdd));
        almocoPrintButton.setGraphic(new ImageView(imagePrint));
        bebidasAddButton.setGraphic(new ImageView(imageAdd));
        bebidasPrintButton.setGraphic(new ImageView(imagePrint));
        sorvetesAddButton.setGraphic(new ImageView(imageAdd));
        sorvetesPrintButton.setGraphic(new ImageView(imagePrint));
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
