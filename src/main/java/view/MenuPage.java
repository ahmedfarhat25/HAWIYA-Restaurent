package view;

import control.MenuController;
import models.Dessert;
import models.Drink;
import models.MainDish;

import javax.swing.*;
import java.awt.*;

public class MenuPage extends JFrame {
    private MenuController menuController;

    public MenuPage() {
        super("HAWIYA - Menu");
        this.menuController = new MenuController(); // or pass from outside
        initUI();
    }

    private void initUI() {
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("HAWIYA Menu", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        // Display main dishes
        textArea.append("Main Dishes:\n");
        for (MainDish dish : menuController.getAllMainDishes()) {
            textArea.append(" - " + dish.getName() + " (EGP " + dish.getPrice() + ")\n");
        }
        textArea.append("\nDrinks:\n");
        for (Drink drink : menuController.getAllDrinks()) {
            textArea.append(" - " + drink.getName() + " (EGP " + drink.getPrice() + ")\n");
        }
        textArea.append("\nDesserts:\n");
        for (Dessert dessert : menuController.getAllDesserts()) {
            textArea.append(" - " + dessert.getName() + " (EGP " + dessert.getPrice() + ")\n");
        }

        panel.add(label, BorderLayout.NORTH);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        add(panel);
    }
}
