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

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.List;

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
        //System.out.println("Top Gourmet Açaiteria");
        //System.out.println(comanda);
        List<Item> items = comanda.getItems();
        float total = 0;
        DecimalFormat df = new DecimalFormat("0.00");
        String nota = "          TOP GOURMET AÇAITERIA \n"
                    + "------------------------------------------ \n"
                    + "Descrição                             R$ \n";
        for (int i = 0; i < items.size(); i++){
            switch (items.get(i).getDescricption()){
                case "ALMOÇO":
                    nota += "ALMOÇO                               " + df.format(items.get(i).getValor()) + "\n";
                    break;
                case "SUCOS":
                    nota += "SUCOS                                " + df.format(items.get(i).getValor()) + "\n";
                    break;
                case "REFRIGERANTE":
                    nota += "REFRIGERANTE                         " + df.format(items.get(i).getValor()) + "\n";
                    break;
                case "SORVETE":
                    nota += "SORVETE                              " + df.format(items.get(i).getValor()) + "\n";
                    break;
            }
            total += items.get(i).getValor();
        }
        nota += "------------------------------------------ \n"
                + "                             TOTAL: " + df.format(total) + "\n\n"
                + "         OBRIGADO, VOLTE SEMPRE!         ";
        imprimir(nota);
        System.out.println(nota);
    }


    private void imprimir(String comanda) {
        try {
            System.out.println("IMPRIMINDO");
            InputStream prin = new ByteArrayInputStream(comanda.getBytes());
            DocFlavor docFlavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
            SimpleDoc simpleDoc = new SimpleDoc(prin, docFlavor, null);
            PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
            PrintRequestAttributeSet printAttributes = new HashPrintRequestAttributeSet();

            printAttributes.add(new JobName("Impressao", null));
            printAttributes.add(OrientationRequested.PORTRAIT);
            printAttributes.add(MediaSizeName.ISO_A4);

            DocPrintJob printJob = printService.createPrintJob();

            try {
                printJob.print(simpleDoc, (PrintRequestAttributeSet) printAttributes);
            } catch (Exception e) {
                System.out.printf("NÃO FOI POSSÍVEL REALIZAR A IMPRESSÃO!!!");
            }

            prin.close();

        } catch (Exception e) {
            System.out.println("erooooo");
        }
    }
}
