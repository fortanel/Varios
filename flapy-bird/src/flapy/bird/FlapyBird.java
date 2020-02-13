/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flapy.bird;

/**
 *
 * @author fortanel
 */
public class FlapyBird {

    
    
    
    public class Celda{
        int X;
        int Y;
        boolean ocupado = false;
        
        public Celda(int X, int Y){
            this.X = X;
            this.Y = Y;
        }
        
        public void ocupar(boolean estado){
            ocupado = estado;
        }
        
    public class ave{
        int x;
        int y;
        
        public ave(int x, int y){
            this.x = x;
            this.y = y;
        }
        
        public void mover(int direccion){
            y = y + direccion;
        }
    }
    
    public class mundo{
        int ancho;
        int alto;
        Celda[][] cuadricula;
        ave jugador;
        
        public mundo(int X, int Y, int ancho, int alto){
            this.ancho = ancho;
            this.alto = alto;
            jugador = new ave(X, Y);
            cuadricula = new Celda[ancho][alto];
            for(int i=0; i < ancho;i++){
                for(int j=0; j <alto;j++){
                    cuadricula[i][j] = new Celda(i,j); 
                }
            }
        }
        
        public void transicion(){
            
        }
        
    }
           
    }
    
    
    /**
     * @param args the command line arguments
     * 
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
