
package puzzle;

import javax.swing.JOptionPane;


public class Prueba {

    public static void main(String[] args) {
        int inicial[][] = new int[4][4];
        int finall[][] = new int[4][4];

        inicial[0][0] = 1;
        inicial[0][1] = 2;
        inicial[0][2] = 3;
        inicial[0][3] = 4;
        inicial[1][0] = 5;
        inicial[1][1] = 6;
        inicial[1][2] = 7;
        inicial[1][3] = 8;
        inicial[2][0] = 9;
        inicial[2][1] = 10;
        inicial[2][2] = 11;
        inicial[2][3] = 12;
        inicial[3][0] = 0;
        inicial[3][1] = 13;
        inicial[3][2] = 14;
        inicial[3][3] = 15;

        finall[0][0] = 1;
        finall[0][1] = 2;
        finall[0][2] = 3;
        finall[0][3] = 4;
        finall[1][0] = 5;
        finall[1][1] = 6;
        finall[1][2] = 7;
        finall[1][3] = 8;
        finall[2][0] = 9;
        finall[2][1] = 10;
        finall[2][2] = 11;
        finall[2][3] = 12;
        finall[3][0] = 13;
        finall[3][1] = 14;
        finall[3][2] = 15;
        finall[3][3] = 0;
        
        Nodo a = new Nodo();
        a.tablero = inicial;

        Puzzle j = new Puzzle();

        j.Puzzle(inicial, finall);
        j.solucion();
        j.movPosicion(a);
    }
}
