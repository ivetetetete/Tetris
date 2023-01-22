
package tetris;

import java.util.Scanner;
import static tetris.Tetris.MostrarTauler;

/**
 *
 * @author chave
 */
public class ControlDePiezas {
    
    public static int[][] NovaPieza(int[][] Pieza) {
        int dau = (int) (Math.random() * 4 + 1);
        switch (dau) {
            case 1:// PiezaI
                for (int i = 0; i < 4; i++) {
                    Pieza[i][0] = 1;
                }
                break;
            case 2:// PiezaO
                Pieza[3][0] = 2; Pieza[3][1] = 2; Pieza[2][0] = 2; Pieza[2][1] = 2;
                break;
            case 3:// PiezaJ
                Pieza[3][0] = 4; Pieza[3][1] = 4; Pieza[2][1] = 4; Pieza[1][1] = 4;
                break;
            case 4:// PiezaL
                Pieza[3][0] = 5; Pieza[2][0] = 5; Pieza[1][0] = 5; Pieza[3][1] = 5;
                break;
            default:
                break;
        }
        return Pieza;
    }

    public static int[][] MoureFiguraCostats(int[][] figura) {
        Scanner scan = new Scanner(System.in);
        boolean lanzar = false;
        while (!lanzar) {
            MostrarTauler(figura);
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
        
    public static int[][] MoureDreta(int[][] figura) {
        boolean imposible = false;
        for (int i = figura[0].length - 1; i >= 0 && !imposible; i--) {
            for (int j = 0; j < figura.length && !imposible; j++) {
                if (figura[j][i] != 0 && i < figura[0].length - 1) {
                    figura[j][i + 1] = figura[j][i];
                    figura[j][i] = 0;
                } else if (figura[j][i] != 0 && i == figura[0].length - 1) {
                    imposible = true;
                }
            }
        }
        return figura;
    }
        
    public static int[][] MoureEsquerra(int[][] figura) {
        boolean imposible = false;
        for (int i = 0; i < figura[0].length && !imposible; i++) {
            for (int j = 0; j < figura.length && !imposible; j++) {
                if (figura[j][i] != 0 && i > 0) {
                    figura[j][i - 1] = figura[j][i];
                    figura[j][i] = 0;
                } else if (figura[j][i] != 0 && i == 0) {
                    imposible = true;
                }
            }
        }
        return figura;
    }
    
    public static int[][] NovaFigura(int[][] figura) {
        int dau = (int) (Math.random() * 7 + 1);
	switch (dau) {
	case 1:// PiezaI
            for (int i = 0; i < 4; i++) {
                figura[i][0] = 1;
            }
            break;
        case 2:// PiezaO
            figura[3][0] = 2; figura[3][1] = 2; figura[2][0] = 2; figura[2][1] = 2;
            break;
        case 3:// PiezaJ
            figura[3][0] = 4; figura[3][1] = 4; figura[2][1] = 4; figura[1][1] = 4;
            break;
        case 4:// PiezaLTumbada
            figura[3][0] = 5; figura[2][0] = 5; figura[1][0] = 5; figura[3][1] = 5;
            break;
        default:
            break;
        }
        return figura;
	}
    
    public static void ActualitzarTauler(int[][] Tauler) {
        for (int j = Tauler.length - 2; j >= 0; j--) {
            for (int k = 0; k < Tauler[0].length; k++) {
                if (Tauler[j][k] != 0 && Tauler[j + 1][k] == 0) {
                    Tauler[j + 1][k] = Tauler[j][k];
                    Tauler[j][k] = 0;
				}
			}
		}
	}
}
