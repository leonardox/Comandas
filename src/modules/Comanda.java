package modules;

import java.util.List;
import java.util.ArrayList;

public class Comanda {

    private List<Item> items = new ArrayList<>();

    public float getValor() {
        float valor = 0;
        for (int i = 0; i < items.size(); i++) {
            valor += items.get(i).getValor();
        }
        return valor;
    }
    public void addItem(Item item) {
        items.add(item);
    }
    public String toString() {
        String result = "";
        float total = 0;
        for (int i = 0; i < items.size(); i++) {
            result += items.get(i).getDescricption() + "    valor: R$ " + String.valueOf(items.get(i).getValor()) + "\n";
            total += items.get(i).getValor();
        }
        result += "---------------------------------------- \n         Total: R$ " + total + "\n";
        return result;
    }
}
