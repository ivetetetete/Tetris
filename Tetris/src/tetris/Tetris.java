package tetris;

import java.util.Random;
import java.util.Scanner;

public class Tetris {
//variables globals

    //Variables del tauler i les figures (arrays)
    static int tauler[][];
    static int figura[][];

    //Mesures del tauler
    static int fila;
    static int col;

    /*Guardem la ultima posicio de la figura quan la movem, prenen de referencia 
    la part inferior esquerra de la figura*/
    static int posCol;

    final static int DOS = 2;
    final static int TRES = 3;
    final static int filFigura = 4;

    //Per posar colors a les fitxes
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    //Per generar numeros random
    public static int DAU;

//Funcions
    /*Des del punt de referencia, anirem movent les peces per veure 
    on les podem col.locar  */
    //Primer la figura de la columna (vertical)
    static void caureColumna() {

        //Ens assegurem de que la ultima (primer fila en l'array) estigui buida
        boolean figuraColocada = false;
        if (tauler[1][posCol] != 0) {
            tauler[0][posCol] = 1;
        }
        for (int i = fila - 1; i > DOS; i--) {
            if (tauler[i][posCol] == 0 && tauler[i - 1][posCol] == 0
                    && tauler[i - DOS][posCol] == 0 && tauler[i - TRES][posCol] == 0 && figuraColocada == false) {
                tauler[i][posCol] = 1;
                tauler[i - 1][posCol] = 1;
                tauler[i - DOS][posCol] = 1;
                tauler[i - TRES][posCol] = 1;
                figuraColocada = true;
            }
        }
        figuraColocada = false;
    }

    //Segon la figura del quadrat
    static void caureQuadrat() {
        boolean figuraColocada = false;
        if (tauler[1][posCol] != 0 || tauler[1][posCol + 1] != 0) {
            tauler[0][posCol] = 1;
            tauler[0][posCol + 1] = 1;
        }
        for (int i = fila - 1; i >= DOS; i--) {

            if (tauler[i][posCol] == 0 && tauler[i][posCol + 1] == 0
                    && tauler[i - 1][posCol] == 0 && tauler[i - 1][posCol + 1] == 0 && figuraColocada == false) {
                tauler[i][posCol] = 1;
                tauler[i][posCol + 1] = 1;
                tauler[i - 1][posCol] = 1;
                tauler[i - 1][posCol + 1] = 1;
                figuraColocada = true;
            }
        }
        figuraColocada = false;
    }

    //La figura de la L
    static void caureL() {
        boolean figuraColocada = false;
        if (tauler[1][posCol] != 0 || tauler[1][posCol + 1] != 0 || tauler[1][posCol + DOS] != 0) {
            tauler[0][posCol] = 1;
            tauler[0][posCol + 1] = 1;
            tauler[0][posCol + DOS] = 1;
        }
        for (int i = fila - 1; i >= DOS; i--) {
            if (tauler[i][posCol] == 0 && tauler[i - 1][posCol] == 0
                    && tauler[i][posCol + 1] == 0 && tauler[i][posCol + DOS] == 0 && figuraColocada == false) {
                tauler[i][posCol] = 1;
                tauler[i - 1][posCol] = 1;
                tauler[i][posCol + 1] = 1;
                tauler[i][posCol + DOS] = 1;

                figuraColocada = true;
            }
        }
        figuraColocada = false;
    }

    //La figura de la J
    static void caureJ() {
        boolean figuraColocada = false;
        if (tauler[1][posCol] != 0 || tauler[1][posCol + 1] != 0) {
            tauler[0][posCol] = 1;
            tauler[0][posCol + 1] = 1;
        }
        for (int i = fila - 1; i >= DOS; i--) {
            if (tauler[i][posCol] == 0 && tauler[i][posCol + 1] == 0
                    && tauler[i - 1][posCol + 1] == 0 && tauler[i - DOS][posCol + 1] == 0 && figuraColocada == false) {
                tauler[i][posCol] = 1;
                tauler[i][posCol + 1] = 1;
                tauler[i - 1][posCol + 1] = 1;
                tauler[i - DOS][posCol + 1] = 1;
                figuraColocada = true;
            }
        }
        figuraColocada = false;
    }

    //Funció per veure quina figura toca en cada torn
    static void caure() {
        if (DAU == 1) {
            caureColumna();
        } else if (DAU == 2) {
            caureQuadrat();
        } else if (DAU == 3) {
            caureJ();
        } else {
            caureL();
        }

    }

    //Final de partida quan alguna fitxa arriba al tope
    static boolean finalPartida() {
        boolean finalPartida = false;
        for (int i = 0; i < col; i++) {
            if (tauler[0][i] != 0) {
                finalPartida = true;
                System.out.println("S'ha acabat la partida!");
            }
        }
        return finalPartida;
    }

    //Enssenyem la figura en un array
    public static void MostrarFigura(int[][] figura) {

        for (int i = 0; i < filFigura; i++) {
            for (int j = 0; j < col; j++) {
                switch (figura[i][j]) {
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
                    case 4:
                        System.out.print(ANSI_PURPLE + "1");
                        break;
                }
                System.out.print(ANSI_RESET);
            }
            System.out.println();
        }
    }

    //Enssenyem el tauler del joc
    public static void MostrarTauler(int[][] tauler) {
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < col; j++) {

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
                    case 4:
                        System.out.print(ANSI_PURPLE + "1");
                        break;
                }
                System.out.print(ANSI_RESET);

            }

            System.out.println();
        }
    }

    //Funció per eliminar una fila quan estigui plena
    public static void ActualitzarTauler(int[][] tauler) {
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < col; j++) {
                if (tauler[i][j] != 0 && tauler[i + 1][j] == 0) {
                    tauler[i + 1][j] = tauler[i][j];
                    tauler[i][j] = 0;
                }

            }

        }

    }

    //Funció on es crea la figura
    public static int[][] NovaFigura(int[][] figura) {
        DAU = (int) (Math.random() * filFigura + 1);
        switch (DAU) {
            case 1:// PiezaI
                for (int i = 0; i < filFigura; i++) {
                    figura[i][0] = 1;
                }
                break;
            case 2:// PiezaO
                figura[3][0] = 2;
                figura[3][1] = 2;
                figura[2][0] = 2;
                figura[2][1] = 2;
                break;
            case 3:// PiezaJ
                figura[3][0] = 3;
                figura[3][1] = 3;
                figura[2][1] = 3;
                figura[1][1] = 3;
                break;
            case 4:// PiezaLTumbada
                figura[3][0] = 4;
                figura[2][0] = 4;
                figura[3][1] = 4;
                figura[3][2] = 4;
                break;
            default:
                break;
        }
        return figura;
    }

    //Funció per moure la figura a l'esquerra
    public static int[][] MoureEsquerra(int[][] figura) {
        boolean imposible = false;
        for (int i = 0; i < figura[0].length - 1 && !imposible; i++) {
            for (int j = 0; j < figura.length && !imposible; j++) {
                if (figura[j][i] != 0 && i > 0) {
                    figura[j][i - 1] = figura[j][i];
                    figura[j][i] = 0;
                    posCol = i - DOS;
                } else if (figura[j][i] != 0 && i == 0) {
                    imposible = true;
                }
            }
        }
        return figura;
    }

    //Funció per moure la figura a la dreta
    public static int[][] MoureDreta(int[][] figura) {
        boolean imposible = false;
        for (int i = figura[0].length - 1; i >= 0 && !imposible; i--) {
            for (int j = 0; j < figura.length && !imposible; j++) {
                if (figura[j][i] != 0 && i < figura[0].length - 1) {
                    figura[j][i + 1] = figura[j][i];
                    figura[j][i] = 0;
                    posCol = i + 1;
                } else if (figura[j][i] != 0 && i == figura[0].length - 1) {
                    imposible = true;
                }
            }
        }
        return figura;
    }

    public static int[][] MoureFiguraCostats(int[][] figura) {
        Scanner scan = new Scanner(System.in);
        boolean lanzar = false;
        while (!lanzar) {
            MostrarFigura(figura);
            System.out.println("a: moure esquerra, d: moure dreta, s: llençar peça");
            switch (scan.nextLine().toLowerCase()) {
                case "a":
                    figura = MoureEsquerra(figura);
                    break;
                case "d":
                    figura = MoureDreta(figura);
                    break;
                case "s":
                    lanzar = true;
                    break;
                default:
                    break;
            }
        }
        return figura;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //Demanem les mesures del tauler
        System.out.println("Introdueix l'amplada del taulell: ");
        col = scan.nextInt();
        System.out.println("Introdueix l'alzada del taulell: ");
        fila = scan.nextInt();
        tauler = new int[fila][col];

        //Mentres que la partida no s'acabi..
        do {
            //tauler de les figures
            figura = new int[filFigura][col];
            NovaFigura(figura);
            posCol = 0;
            MoureFiguraCostats(figura);
            caure();
            MostrarTauler(tauler); //tablero juego

            //ActualitzarTauler(tauler);
            System.out.println("--------------------");

        } while (finalPartida() != true);
    }

}
