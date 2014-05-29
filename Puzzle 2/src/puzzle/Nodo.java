package puzzle;

public class Nodo 
{
    int x=0;
    int y=0;
    String movHecho="";
    int distancia=0;
    Nodo hijos[];
    int tablero [][]=new int[4][4];
    int terminado=0;
    //sera uno si ya esta ordenado
    /*
    Cada Nodo va a tener como atributos su posicion en i j del tablero 
    la distancia asta el Nodo destino (distancia de manhatan 
    el Nodo raiz  al cual pertenece ,,,, esto nos servira para regresar en el recorrido 
   La raiz siempre sera en donde se encuentre el 0 
    
    )*/
    public void Nodo  ()
    {
        tablero=new int [4][4];
    }
}
