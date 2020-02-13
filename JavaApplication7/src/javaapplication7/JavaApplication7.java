/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication7;

/**
 *
 * @author fortanel
 */
public class JavaApplication7 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[][] cuadro = new int[10][10];
        String cadena = "";
        for(int i=0; i < 10;i++){
                for(int j=0; j < 10;j++){
                    cuadro[i][j] = j % 2;
                    cadena = cadena + cuadro[i][j];
                }
                cadena = cadena + "\n";
            }
        System.out.println(cadena);
        cuadro[0][0] = cuadro[0][1];
        cuadro[0][1] = cuadro[0][2];
        cuadro[0][2] = 1;
        cadena = "";
        for(int i=0; i < 10;i++){
                for(int j=0; j < 10;j++){
                    //cuadro[i][j] = j % 2;
                    cadena = cadena + cuadro[i][j];
                }
                cadena = cadena + "\n";
            }
        System.out.println(cadena);
        
    }
    
}
