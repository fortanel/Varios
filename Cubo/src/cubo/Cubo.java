/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cubo;

import org.ejml.simple.SimpleMatrix;
import processing.core.PApplet;
import processing.core.PFont;


 /**
 * @author fortanel
 */
public class Cubo extends PApplet {

    PFont fuente; // Fuente para mostrar texto en pantalla.
    
    float angulo = 0;
    int alto = 30;         // Altura (en celdas) de la cuadricula. 
    int ancho = 30;        // Anchura (en celdas) de la cuadricula. 
    int celda = 20;          // Tamanio de cada celda cuadrada (en pixeles).
    int numCeldas = alto * ancho; // Numero de Celdas.

    SimpleMatrix A = new SimpleMatrix(3, 1);
    SimpleMatrix B = new SimpleMatrix(3, 1);
    SimpleMatrix C = new SimpleMatrix(3, 1);
    SimpleMatrix D = new SimpleMatrix(3, 1);
    SimpleMatrix E = new SimpleMatrix(3, 1);
    SimpleMatrix F = new SimpleMatrix(3, 1);
    SimpleMatrix G = new SimpleMatrix(3, 1);
    SimpleMatrix H = new SimpleMatrix(3, 1);
    
    // Matrices de rotacion y proyeccion.
    SimpleMatrix proyeccion = new SimpleMatrix(2,3);
    SimpleMatrix rotacionZ = new SimpleMatrix(3,3);
    SimpleMatrix rotacionX = new SimpleMatrix(3,3);
    SimpleMatrix rotacionY = new SimpleMatrix(3,3);
    
    
    public SimpleMatrix rotaZ(SimpleMatrix matriz){
        rotacionZ.setRow(0, 0, Math.cos(angulo),-Math.sin(angulo),0);
        rotacionZ.setRow(1, 0, Math.sin(angulo),Math.cos(angulo),0);
        rotacionZ.setRow(2, 0, 0,0,1);
        return rotacionZ.mult(matriz);
    }
    
    public SimpleMatrix rotaX(SimpleMatrix matriz){
        rotacionX.setRow(0, 0, 1,0,0);
        rotacionX.setRow(1, 0, 0,Math.cos(angulo),-Math.sin(angulo));
        rotacionX.setRow(2, 0, 0,Math.sin(angulo),Math.cos(angulo));
        return rotacionX.mult(matriz);
    }
   
    public SimpleMatrix rotaY(SimpleMatrix matriz){
        rotacionY.setRow(0, 0, Math.cos(angulo),0,Math.sin(angulo));
        rotacionY.setRow(1, 0, 0,1,0);
        rotacionY.setRow(2, 0,-Math.sin(angulo),0,Math.cos(angulo));
        return rotacionY.mult(matriz);
    }
    
    
    public void inicia() {
        A.setColumn(0, 0, -100, 100, 100);
        B.setColumn(0, 0, 100, 100, 100);
        C.setColumn(0, 0, -100, -100, 100);
        D.setColumn(0, 0, 100, -100, 100);
        E.setColumn(0, 0, -100, 100, -100);
        F.setColumn(0, 0, 100,100, -100);
        G.setColumn(0, 0, -100, -100, -100);
        H.setColumn(0, 0, 100, -100, -100);
        
        proyeccion.setRow(0, 0, 1,0,0);
        proyeccion.setRow(1, 0, 0,1,0);
    }    

    @Override
    public void setup() {
        size(500,500);
        background(255);
        fuente = createFont("Arial", 12, true);
        inicia();
    }
    
    @Override
    public void draw() {        
        SimpleMatrix puntoA = proyeccion.mult(rotaX(rotaY(rotaZ(A))));
        SimpleMatrix puntoB = proyeccion.mult(rotaX(rotaY(rotaZ(B))));
        SimpleMatrix puntoC = proyeccion.mult(rotaX(rotaY(rotaZ(C))));
        SimpleMatrix puntoD = proyeccion.mult(rotaX(rotaY(rotaZ(D))));
        SimpleMatrix puntoE = proyeccion.mult(rotaX(rotaY(rotaZ(E))));
        SimpleMatrix puntoF = proyeccion.mult(rotaX(rotaY(rotaZ(F))));
        SimpleMatrix puntoG = proyeccion.mult(rotaX(rotaY(rotaZ(G))));
        SimpleMatrix puntoH = proyeccion.mult(rotaX(rotaY(rotaZ(H))));

        background(0);
        stroke(255, 255, 255, 255);
        strokeWeight(2);
        // Notese que es necesario trasladar los puntos en 250 en cada coordenada, ya que el sistema de coordenas maejnado
        // en esta paqueteria no esta centrada en el 0, y las matrices de rotacion, hacen la rotacion desde los ejes X,Y y Z 
        // respectivamente centrados en el 0.
        line((int)puntoA.get(0, 0) + 250, (int)puntoA.get(1, 0) + 250,(int)puntoB.get(0, 0) + 250, (int)puntoB.get(1, 0) + 250);
        line((int)puntoA.get(0, 0) + 250, (int)puntoA.get(1, 0) + 250,(int)puntoC.get(0, 0) + 250, (int)puntoC.get(1, 0) + 250);
        line((int)puntoC.get(0, 0) + 250, (int)puntoC.get(1, 0) + 250,(int)puntoD.get(0, 0) + 250, (int)puntoD.get(1, 0) + 250);
        line((int)puntoD.get(0, 0) + 250, (int)puntoD.get(1, 0) + 250,(int)puntoB.get(0, 0) + 250, (int)puntoB.get(1, 0) + 250);
        
        line((int)puntoE.get(0, 0) + 250, (int)puntoE.get(1, 0) + 250,(int)puntoF.get(0, 0) + 250, (int)puntoF.get(1, 0) + 250);
        line((int)puntoE.get(0, 0) + 250, (int)puntoE.get(1, 0) + 250,(int)puntoG.get(0, 0) + 250, (int)puntoG.get(1, 0) + 250);
        line((int)puntoG.get(0, 0) + 250, (int)puntoG.get(1, 0) + 250,(int)puntoH.get(0, 0) + 250, (int)puntoH.get(1, 0) + 250);
        line((int)puntoH.get(0, 0) + 250, (int)puntoH.get(1, 0) + 250,(int)puntoF.get(0, 0) + 250, (int)puntoF.get(1, 0) + 250);
        
        line((int)puntoA.get(0, 0) + 250, (int)puntoA.get(1, 0) + 250,(int)puntoE.get(0, 0) + 250, (int)puntoE.get(1, 0) + 250);
        line((int)puntoB.get(0, 0) + 250, (int)puntoB.get(1, 0) + 250,(int)puntoF.get(0, 0) + 250, (int)puntoF.get(1, 0) + 250);
        line((int)puntoC.get(0, 0) + 250, (int)puntoC.get(1, 0) + 250,(int)puntoG.get(0, 0) + 250, (int)puntoG.get(1, 0) + 250);
        line((int)puntoD.get(0, 0) + 250, (int)puntoD.get(1, 0) + 250,(int)puntoH.get(0, 0) + 250, (int)puntoH.get(1, 0) + 250);
        angulo += 0.01;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        PApplet.main(new String[]{"cubo.Cubo"});
    }
}
