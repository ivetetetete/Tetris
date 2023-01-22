package tetris;

import java.util.Random;
import java.util.Scanner;

public class Tetris {
//variables globales

    static int tauler[][];
    static int figura[][];

    //Medidas del tablero
    static int fila;
    static int col;

    /*Cuando se mueve la ficha, aqui guardanmos la ultima posición
    que será el lado izquierdo de la parte de abajo  */
    static int posFila;
    static int posCol;

    final static int DOS = 2;
    final static int TRES = 3;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";

//funciones
//figuras
   
//mover
    static void caerCuadrado() {
        for (int i = fila - 1; i > 0; i++) {
            if (figura[i][posCol] == 0 && figura[i][posCol + 1] == 0
                    && figura[i - 1][posCol] == 0 && figura[i - 1][posCol + 1] == 0) {
                tauler[i][posCol] = 1;
                tauler[i][posCol + 1] = 1;
                tauler[i - 1][posCol] = 1;
                tauler[i - 1][posCol + 1] = 1;
            }
        }
    }

    /*Desde el punto de referencia de la figura (extremo inferior izquierdo), iremos
    haciendo la figura para ver donde se puede colocar */
    static void caerColumna() {
        for (int i = fila - 1; i > 0; i++) {
            if (figura[i][posCol] == 0 && figura[i - 1][posCol] == 0
                    && figura[i - DOS][posCol] == 0 && figura[i - TRES][posCol] == 0) {
                tauler[i][posCol] = 1;
                tauler[i - 1][posCol] = 1;
                tauler[i - DOS][posCol] = 1;
                tauler[i - TRES][posCol] = 1;
            }
        }
    }

    static void caerLlado() {
        for (int i = fila - 1; i > 0; i--) {
            if (figura[i][posCol] == 0 && figura[i - 1][posCol] == 0
                    && figura[i][posCol + 1] == 0 && figura[i][posCol + DOS] == 0) {
                tauler[i][posCol] = 1;
                tauler[i - 1][posCol] = 1;
                tauler[i][posCol + 1] = 1;
                tauler[i][posCol + DOS] = 1;
            }
        }
    }

    static void caerL() {
        for (int i = fila - 1; i > 0; i--) {
            if (figura[i][posCol] == 0 && figura[i][posCol + 1] == 0
                    && figura[i - 1][posCol + 1] == 0 && figura[i - DOS][posCol + 1] == 0) {
                tauler[i][posCol] = 1;
                tauler[i][posCol + 1] = 1;
                tauler[i - 1][posCol + 1] = 1;
                tauler[i - DOS][posCol + 1] = 1;
            }
        }
    }

    static void caer() {
        Random rnd = new Random();
        int numero = rnd.nextInt(5);

        if (numero == 1) {
            caerCuadrado();
        } else if (numero == 2) {
            caerColumna();
        } else if (numero == 3) {
            caerLlado();
        } else {
            caerL();
        }

    }

    static void limite() {
        for (int i = 0; i < col; i++) {
            if (tauler[0][i] != 0) {
                System.out.println("S'ha acabat el joc i has perdut!");
            }
        }
    }

    //eliminar fila si está llena
//llegar tope
    public static void MostrarTauler(int[][] tauler) {

        for (int i = 0; i < tauler.length; i++) {
            for (int j = 0; j < tauler[0].length; j++) {
                switch (tauler[i][j]) {
                    case 0:
                        System.out.print("0");
                        break;
                    case 1:
                        System.out.print(ANSI_CYAN + "1");
                        break;
                    case 2:
                        System.out.print(ANSI_YELLOW + "1");
                        break;
                    case 3:
                        System.out.print(ANSI_GREEN + "1");
                        break;
                }
                System.out.print(ANSI_RESET);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Introdueix l'amplada del taulell: ");
        int amplada = scan.nextInt();
        System.out.println("Introdueix l'alçada del taulell: ");
        int alcada = scan.nextInt();
        int[][] tauler = new int[alcada][amplada];
        boolean finalPartida = false;
        while (!finalPartida) {
            int[][] Figura = new int[4][amplada];
            ControlDePiezas.NovaFigura(Figura);
            ControlDePiezas.MoureFiguraCostats(Figura);
            /*Poner las funciones de bajar figura*/
            caer();
            
            MostrarTauler(tauler);
            System.out.println("--------------------");

        }
    }
}
//caer

