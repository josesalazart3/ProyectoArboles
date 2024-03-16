package com.mycompany.proyecto_1;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//Clase objeto visual
public class ArbolVisual extends JPanel {

    private Arbol arbol;
    private final int radio = 15; // Reducimos el tamaño de los nodos del árbol
    private final int espacioEntreNodos = 70; // Espacio entre los nodos del árbol

    public ArbolVisual(Arbol arbol) {
        this.arbol = arbol;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (arbol != null && arbol.raiz != null) {
            int anchoArbol = calcularAncho(arbol.raiz) * espacioEntreNodos;
            dibujarNodo(g, getWidth() / 2, 30, arbol.raiz, anchoArbol / 2, getWidth() / 4, 0);
        }
    }

    private void dibujarNodo(Graphics g, int x, int y, Nodo nodo, int anchoSubarbol, int espacio, int nivel) {
        // Dibujar el nodo actual
        g.setColor(Color.WHITE);
        g.fillOval(x - radio, y - radio, 2 * radio, 2 * radio);
        g.setColor(Color.BLACK);
        g.drawOval(x - radio, y - radio, 2 * radio, 2 * radio);
        if (nodo.operador == ' ') {
            g.drawString(String.valueOf(nodo.valor), x - 6, y + 4);
        } else {
            g.drawString(String.valueOf(nodo.operador), x - 6, y + 4);
        }

        // Dibujar las ramas y los nodos hijos si existen
        if (nodo.izquierdo != null) {
            // Calcular las coordenadas del nodo hijo izquierdo
            int nuevoEspacio = anchoSubarbol / 2;
            int newX = x - nuevoEspacio;
            int newY = y + espacioEntreNodos;
            // Dibujar la rama
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(2)); // Establecer el grosor de la línea
            // Ajustar las coordenadas para que la línea termine justo en el borde del círculo
            int xLinea = x - (radio / 2);
            int yLinea = y - radio;
            g.drawLine(xLinea, yLinea, newX, newY);
            // Llamar recursivamente al método para dibujar el nodo hijo izquierdo
            dibujarNodo(g, newX, newY, nodo.izquierdo, nuevoEspacio, espacio / 2, nivel + 1);
        }
        if (nodo.derecho != null) {
            // Calcular las coordenadas del nodo hijo derecho
            int nuevoEspacio = anchoSubarbol / 2;
            int newX = x + nuevoEspacio;
            int newY = y + espacioEntreNodos;
            // Dibujar la rama
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(2)); // Establecer el grosor de la línea
            // Ajustar las coordenadas para que la línea termine justo en el borde del círculo
            int xLinea = x + (radio / 2);
            int yLinea = y - radio;
            g.drawLine(xLinea, yLinea, newX, newY);
            // Llamar recursivamente al método para dibujar el nodo hijo derecho
            dibujarNodo(g, newX, newY, nodo.derecho, nuevoEspacio, espacio / 2, nivel + 1);
        }
    }

    // Método para calcular el ancho del árbol
    private int calcularAncho(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        int izquierdo = calcularAncho(nodo.izquierdo);
        int derecho = calcularAncho(nodo.derecho);
        return Math.max(1, izquierdo + derecho);
    }

    public static void mostrarArbol(Arbol arbol, String titulo) {
        JFrame frame = new JFrame(titulo);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); // Aumentar el tamaño de la ventana
        frame.setLocationRelativeTo(null);
        ArbolVisual arbolVisual = new ArbolVisual(arbol);
        frame.add(arbolVisual);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Pedir al usuario que ingrese la expresión matemática con variables
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la expresion matematica con variables: ");
        String expresion = scanner.nextLine();

        // Mapa para almacenar los valores de las variables
        Map<Character, Integer> variables = new HashMap<>();
        for (char c : expresion.toCharArray()) {
            // Solicitar al usuario que ingrese el valor de cada variable encontrada en la expresion
            if (Character.isAlphabetic(c) && !variables.containsKey(c)) {
                System.out.print("Ingrese el valor para " + c + ": ");
                int valor = scanner.nextInt();
                variables.put(c, valor);
            }
        }

        // Evaluar la expresión y mostrar el resultado
        int resultado = Proyecto_1.evaluar(expresion, variables);
        System.out.println("El resultado de la expresion es: " + resultado);

        // Crear el árbol de expresiones y mostrar el recorrido postorden
        Arbol arbol = new Arbol();
        arbol.raiz = Proyecto_1.construirArbol(expresion, variables);
        System.out.println("Recorrido postorden del arbol de expresiones:");
        arbol.recorrerPostorden();

        // Mostrar el recorrido en notacion polaca del arbol de expresiones
        System.out.println("\nRecorrido en notacion polaca del arbol de expresiones:");
        arbol.recorrerPolaca();

        // Mostrar el árbol en una ventana
        mostrarArbol(arbol, "Árbol de Expresiones");

        scanner.close();
    }
}
