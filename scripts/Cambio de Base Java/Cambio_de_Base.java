package com.mycompany.cambio_de_base;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Cambio_de_Base extends JFrame implements ActionListener{
    private final JTextField inputField;
    private final JComboBox<String> baseInput;
    private final JComboBox<String> baseOutput;
    private final JTextArea outputArea;
    private final JButton convertButton;
    private final JButton clearButton;

    public Cambio_de_Base() {
        setTitle("Conversor de Bases");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1)); // Aumentado para acomodar el botón de limpiar

        // Configurar el color de fondo y márgenes del panel de entrada
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(135, 206, 235)); // Color azul claro
        inputPanel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(Color.BLUE, 5), // Borde azul de 5 píxeles
            new EmptyBorder(10, 10, 10, 10) // Margen interno
        ));
        inputPanel.add(new JLabel("Número:"));
        inputField = new JTextField(15);
        inputPanel.add(inputField);

        // Configurar el color de fondo y márgenes del panel de base de conversión 
        JPanel baseInputPanel = new JPanel();
        baseInputPanel.setBackground(new Color(173, 216, 230)); // Color azul cielo
        baseInputPanel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(Color.BLUE, 5), // Borde azul de 5 píxeles
            new EmptyBorder(10, 10, 10, 10) // Margen interno
        ));
        baseInputPanel.add(new JLabel("Base de entrada:"));
        String[] bases = {"Binario", "Octal", "Decimal", "Hexadecimal"};
        baseInput = new JComboBox<>(bases);
        baseInputPanel.add(baseInput);

        // Configurar el color de fondo y márgenes del panel de base de salida
        JPanel baseOutputPanel = new JPanel();
        baseOutputPanel.setBackground(new Color(135, 206, 250)); // Color azul
        baseOutputPanel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(Color.BLUE, 5), // Borde azul de 5 píxeles
            new EmptyBorder(10, 10, 10, 10) // Margen interno
        ));
        baseOutputPanel.add(new JLabel("Base de salida:"));
        baseOutput = new JComboBox<>(bases);
        baseOutputPanel.add(baseOutput);

        // Panel para los botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Layout para alinear los botones

        // Botón para realizar la conversión
        convertButton = new JButton("Convertir");
        convertButton.setPreferredSize(new Dimension(100, 30)); // Tamaño del botón
        convertButton.addActionListener(this);
        buttonPanel.add(convertButton);

        // Botón para limpiar
        clearButton = new JButton("Limpiar");
        clearButton.setPreferredSize(new Dimension(100, 30)); // Tamaño del botón
        clearButton.addActionListener(this);
        buttonPanel.add(clearButton);

        // Área de salida
        outputArea = new JTextArea();
        outputArea.setEditable(false);

        // Añadir los paneles al marco
        add(inputPanel);
        add(baseInputPanel);
        add(baseOutputPanel);
        add(buttonPanel);  // Añadido el panel de botones
        add(new JScrollPane(outputArea));

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearButton) {
            // Limpiar los campos de entrada y salida
            inputField.setText("");
            outputArea.setText("");
        } else {
            try {
                String input = inputField.getText();
                int inputBase = getBase((String) baseInput.getSelectedItem());
                int outputBase = getBase((String) baseOutput.getSelectedItem());

                int decimalValue = Integer.parseInt(input, inputBase);
                String result = Integer.toString(decimalValue, outputBase).toUpperCase();

                outputArea.setText("Resultado: " + result);
            } catch (NumberFormatException ex) {
                outputArea.setText("Error: Entrada no válida.");
            }
        }
    }

    private int getBase(String base) {
        switch (base) {
            case "Binario":
                return 2;
            case "Octal":
                return 8;
            case "Decimal":
                return 10;
            case "Hexadecimal":
                return 16;
            default:
                return 10;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Cambio_de_Base::new);
    }
}
