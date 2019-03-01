public class turbopalindromo {
static int a = 0;
public static void main(String[] args){
        int contador = 0;
        for(int i =0; i <= 200000; i++) {
                if(esTurboPalindromo(i)) {
                        contador++;
                        System.out.println("El numero " + i + "  es turboPalindromo dado que al aplicar la funcion obtenemos " + a );
                }
        }
        System.out.println("El numero de turbospalindromos fue " + contador);

}

public static boolean esPalindromo(int[] arreglo){
        for(int i = 0; i < arreglo.length/2; i++) {
                if(arreglo[i] != arreglo[arreglo.length - 1 - i])
                        return false;
        }
        return true;
}

public static int[] rot(int[] arreglo, int indice){
        int[] resultado = new int[arreglo.length];
        for(int i =0; i < arreglo.length; i++) {
                resultado[(i + indice) % arreglo.length] = arreglo[i];
        }
        return resultado;

}

public static int[] generaArreglo(int numero){
        String[] apoyo = Integer.toString(numero).split("");
        int[] resultado = new int[apoyo.length];
        for(int i = 0; i < apoyo.length; i++) {
                resultado[i] = Integer.parseInt(apoyo[i]);
        }
        return resultado;
}

public static int generaNumero(int[] arreglo){
        String apoyo = "";
        for(int elemento : arreglo) {
                apoyo += elemento;
        }
        return Integer.parseInt(apoyo);
}

public static boolean esTurboPalindromo(int numero){
        int[] arreglo = generaArreglo(numero);
        int apoyo = numero;
        for(int i = 0; i < arreglo.length; i++) {
                apoyo += Math.pow((-1),i)*generaNumero(rot(arreglo,arreglo[i]));
        }
        a = apoyo;
        apoyo = Math.abs(apoyo);
        //System.out.println(apoyo); para ver el resultado.
        return esPalindromo(generaArreglo(apoyo));
}
}
