package puzzle;

import javax.swing.JOptionPane;

public class Puzzle {

    /*Raíz del árbol*/
   Nodo arbol = new Nodo();// lo meneamos a la parte recursiva para q no existan 
    int tableroInicial[][];
    int tableroFinal[][];
    //int intercambioFinal[][];

    //int intercambio[][];
    //temporal
    String MovimientosEchos="";
    int totMov=0;
    int contadorBorrar=1;
    int n=0;
    String movimientos[];
    int temporal [][];
    
    public void Puzzle(int configuracionInicial[][], int configuracionFinal[][]){
        int fila=0;
        int columna=0;
        tableroInicial=configuracionInicial;
        tableroFinal=configuracionFinal;
        
        for(int i=0; i<tableroInicial.length; i++){
           for(int j=0; j<tableroInicial[i].length; j++){
              if(tableroInicial[i][j]==0){
                  fila=i;
                  columna=j;
              }
           }
        }
    }
    
    //Devuelve el numero de fichas que no estan en su lugar
    public int distanciaHamming(Nodo raiz){
       int contador=0;
       for(int i=0; i< raiz.tablero.length; i++){
          for(int j=0; j<raiz.tablero[i].length; j++){
             if(raiz.tablero[i][j] == tableroFinal[i][j] /*  PARA Q NO COMPARE EL VACIO && (tableroInicial[i][j]!=0) */)
                 contador=contador;
             else
             contador++;
          }  
                  
       }
       return contador;
    }
    
    
    
    
        //Devuelve el numero de fichas que no estan en su lugar
    //decide si tiene solucion o no 
    public int distanciaHamming2(){
       int contador=0;
       for(int i=0; i< tableroInicial.length; i++){
          for(int j=0; j<tableroInicial[i].length; j++){
             if(tableroInicial[i][j] == tableroFinal[i][j] /*  PARA Q NO COMPARE EL VACIO && (tableroInicial[i][j]!=0) */)
                 contador=contador;
             else
             contador++;
          }  
                  
       }
       return contador;
    }
    
    
    //le asignamos la distancia entre la posi

   public int distanciaManhatan(Nodo raiz){ 
        int sumaManhatan=0; 
        for (int filaOrigen=0; filaOrigen<raiz.tablero.length; filaOrigen++){ 
           for (int columnaOrigen=0; columnaOrigen<raiz.tablero[filaOrigen].length; columnaOrigen++){ 
              if(tableroFinal[filaOrigen][columnaOrigen]!= raiz.tablero[filaOrigen][columnaOrigen]){ 
              //Se busca la posición correcta en la que deberia estar 
                 int filaDestino=0, columnaDestino=0; 
                 while(filaDestino<tableroFinal.length && (tableroFinal[filaDestino][columnaDestino]!= raiz.tablero[filaOrigen][columnaOrigen])){ 
                   columnaDestino++; 
                    if (columnaDestino >= tableroFinal[filaDestino].length) {
                        filaDestino++; 
                        columnaDestino=0;
                    } 
                       } 
        //Se calcula la distanciaManhatan y se hace la sumatoria de cada posicion 
        sumaManhatan+= Math.abs(filaDestino-filaOrigen) + Math.abs(columnaDestino-columnaOrigen); 
                } 
            } 
         } 
        return sumaManhatan; 
    } 
    
   
      public int [][] moverArriba(int [][] tab){
      temporal =tab;
      int fila=buscarEspacio(temporal)[0];
        int columna=buscarEspacio(temporal)[1]; 
          if(fila>0){
          int num= temporal[fila-1][columna];
          temporal[fila-1][columna]=0;
          temporal[fila][columna]=num;
       }
          n=0;
       return temporal;
    }
    
    public int [][] moverAbajo(int [][] tab){
        temporal =tab;
        int fila=buscarEspacio(temporal)[0];
        int columna=buscarEspacio(temporal)[1]; 
        if(fila<3){
          int num= temporal[fila+1][columna];
          temporal[fila+1][columna]=0;
          temporal[fila][columna]=num;
       }
        n=1;
       return temporal;
    }
    
    public int [][] moverDerecha(int [][] tab){
       temporal =tab;
       int fila=buscarEspacio(temporal)[0];
        int columna=buscarEspacio(temporal)[1]; 
         if(columna<3){
          int num= temporal[fila][columna+1];
          temporal[fila][columna+1]=0;
          temporal[fila][columna]=num;
       }
         n=2;
       return temporal;
    }
    
    public int [][] moverIzquierda(int [][] tab){
        temporal =tab;
        int fila=buscarEspacio(temporal)[0];
        int columna=buscarEspacio(temporal)[1];
        if(columna>0){
          int num= temporal[fila][columna-1];
          temporal[fila][columna-1]=0;
          temporal[fila][columna]=num;
       }
        n=3;
       return temporal;
    }
    
    public int [][] regresar(int tab [][]){
        if(n==0) return moverAbajo(tab);
        if(n==1) return moverArriba(tab);
        if(n==2) return moverIzquierda(tab);
        if(n==3) return moverDerecha(tab);
        return tab;
    }
    
    //verifica si el puzzle tiene o no solucion para no hacer calculos innecesarios
    public void solucion(){
       if(distanciaHamming2()%2 == 0)
           JOptionPane.showMessageDialog(null, "El puzzle tiene solucion");
       else
           JOptionPane.showMessageDialog(null, "El puzzle no tiene solucion!!");
    }
    
    //metodo que decide si empieza a resolver o no el puzzle
    public boolean decide(){
      if(distanciaHamming2()%2 ==0)
          return true;
      else
          return false;
    }
       
      public int [] buscarEspacio(int tablero [][])
      {
        int [] coord=new int[2];
       for (int f=0; f<tablero.length; f++){ 
           for (int c=0; c<tablero[f].length; c++){
               
               if(tablero[f][c]==0)
               {
               coord[0]=f;
               coord[1]=c;
               }
               
           }
       
       }
      return coord;
      }
   
              
  public int posibleMov(Nodo raiz)
      {
       int x=buscarEspacio(raiz.tablero)[0];
        int y=buscarEspacio(raiz.tablero)[1];    
         int nhijos=0;
          //verifica si se encuentra en una esquina solo tiene 2 movimientos
      if(((x==0)&&((y==0)||(y==3))) || ((x==3)&& ((y==0)||(y==3))))
      {
     nhijos=2;
      }
      // si se encuentra en un borde del tablero
      else if(((x==0)&&((y==1)||(y==2)))||   ((x==1)&& ((y==0)||(y==3))) ||   ((x==2)&& ((y==0)||(y==3))) ||   ((x==3)&& ((y==1)||(y==2)))    )
      {
      nhijos=3;
      }
      //sino se cumple ninguna de las condiciones significa que se encuentra entre los 4 cuadros del centro
      else if(((x==1)&&((y==1)||(y==2)))||((x==2)&&((y==1)||(y==2)))){
      nhijos=4;
      }
      return nhijos;
      }
  
 public void movimiento()  
 {
for(int i=0; i<tableroInicial.length; i++){
    for(int j=0; j<tableroInicial[i].length; j++){
arbol.tablero[i][j]=tableroInicial[i][j];
    }
}
   //totMov++;     
  movPosicion(arbol);
 //imprime(mejorMovimiento(arbol));
 //return mejorMovimiento(arbol);
 
 
 }
 
 //devolvemos el tablero del nodo con la menor distancia
 public Nodo mejorMovimiento(Nodo raiz)
 {
int mayor=raiz.hijos[0].distancia;
int contador=0;
  for(int i=0;i<raiz.hijos.length;i++)
 {
 if(raiz.hijos[i].distancia<mayor)   
{ 
    mayor=raiz.hijos[i].distancia;
    contador=i;
}   

 }
MovimientosEchos=MovimientosEchos+raiz.hijos[contador].movHecho;
return raiz.hijos[contador];
 
 
 }

 public void movPosicion(Nodo raiz)
 { 
     int x=buscarEspacio(raiz.tablero)[0];
     int y=buscarEspacio(raiz.tablero)[1]; 
     arbol.tablero=raiz.tablero;
     int tmp=posibleMov(arbol);
      /*Inicializamos el nodo.*/
       arbol.hijos = new Nodo[tmp];
       
       //se inicializan cada uno de los nodos
       for(int i=0; i<tmp; i++){
         arbol.hijos[i]= new Nodo();
        // raiz.hijos[i].tablero= new int [4][4];
        //agregado por si falla
        // raiz.hijos[i].tablero=raiz.tablero;
       }
        
         int terminado = distanciaManhatan(raiz);

         //verificamos si ya esta terminado
        // while(terminado!=0)
         //
         if(terminado==0){ 
             
             System.out.println("ok");
             System.exit(0);
         }
        
         
         //crear mejor uhn metodo ke aga la comprobacion de las matrices
    else{
       
 //cndicionamos dependiendo de en donde se encuentre el espacio
      if(tmp==2) {
          
     //esquina superior izquierda     
     if(x==0 && y==0){
        arbol.hijos[0].tablero = moverAbajo(arbol.tablero);
        arbol.hijos[0].distancia = distanciaManhatan(arbol.hijos[0]);
        arbol.hijos[0].movHecho = "Abajo";
        moverArriba(arbol.tablero);
        
        arbol.hijos[1].tablero = moverDerecha(arbol.tablero);
        arbol.hijos[1].distancia = distanciaManhatan(arbol.hijos[1]);
        arbol.hijos[1].movHecho = "Derecha";
        moverIzquierda(arbol.tablero);
     }
        
     //esquina superior derecha
     if(x==0 && y==3){         
         arbol.hijos[0].tablero = moverAbajo(arbol.tablero);
        arbol.hijos[0].distancia = distanciaManhatan(arbol.hijos[0]);
        arbol.hijos[0].movHecho = "Abajo";
        moverArriba(arbol.tablero);
        
        arbol.hijos[1].tablero = moverIzquierda(arbol.tablero);
        arbol.hijos[1].distancia = distanciaManhatan(arbol.hijos[1]);
        arbol.hijos[1].movHecho = "Izquierda";
        moverDerecha(arbol.tablero);
     }
     
     //esquina inferior izquierda
     if(x==3 && y==0){         
        arbol.hijos[0].tablero = moverArriba(arbol.tablero);
        arbol.hijos[0].distancia = distanciaManhatan(arbol.hijos[0]);
        arbol.hijos[0].movHecho = "Arriba";
        moverAbajo(arbol.tablero);
        
        arbol.hijos[1].tablero = moverDerecha(arbol.tablero);
        arbol.hijos[1].distancia = distanciaManhatan(arbol.hijos[1]);
        arbol.hijos[1].movHecho = "Derecha";
        moverIzquierda(arbol.tablero);
        
     }
     
     //Esquina inferior derecha
     if(x==3 && y==3){ 
        arbol.hijos[0].tablero = moverArriba(arbol.tablero);
        arbol.hijos[0].distancia = distanciaManhatan(arbol.hijos[0]);
        arbol.hijos[0].movHecho = "Arriba";
        moverAbajo(arbol.tablero);
        
        arbol.hijos[1].tablero = moverIzquierda(arbol.tablero);
        arbol.hijos[1].distancia = distanciaManhatan(arbol.hijos[1]);
        arbol.hijos[1].movHecho = "Izquierda";
        moverDerecha(arbol.tablero);
      }
   }   //fin de tmp 2
     
    if(tmp==3){
        if(x==0 && (y==1 || y==2)){
            arbol.hijos[0].tablero = moverIzquierda(arbol.tablero);
            arbol.hijos[0].distancia = distanciaManhatan(arbol.hijos[0]);
            arbol.hijos[0].movHecho = "Izquierda";
            moverDerecha(arbol.tablero);

            arbol.hijos[1].tablero = moverDerecha(arbol.tablero);
            arbol.hijos[1].distancia = distanciaManhatan(arbol.hijos[1]);
            arbol.hijos[1].movHecho = "Derecha";
            moverIzquierda(arbol.tablero);

            arbol.hijos[2].tablero = moverAbajo(arbol.tablero);
            arbol.hijos[2].distancia = distanciaManhatan(arbol.hijos[2]);
            arbol.hijos[2].movHecho = "Abajo";
            moverArriba(arbol.tablero);
        }
        
        if((x==1 || x==2) && y==0){
            arbol.hijos[0].tablero = moverAbajo(arbol.tablero);
             arbol.hijos[0].distancia = distanciaManhatan(arbol.hijos[0]);
             arbol.hijos[0].movHecho = "Abajo";
             moverArriba(arbol.tablero);

             arbol.hijos[1].tablero = moverIzquierda(arbol.tablero);
             arbol.hijos[1].distancia = distanciaManhatan(arbol.hijos[1]);
             arbol.hijos[1].movHecho = "Izquierda";
             moverDerecha(arbol.tablero);

             arbol.hijos[2].tablero = moverDerecha(arbol.tablero);
             arbol.hijos[2].distancia = distanciaManhatan(arbol.hijos[2]);
             arbol.hijos[2].movHecho = "Derecha";
             moverIzquierda(arbol.tablero);
        }
        
        if((x==1 || x==2) && y==3){
            arbol.hijos[0].tablero = moverAbajo(arbol.tablero);
            arbol.hijos[0].distancia = distanciaManhatan(arbol.hijos[0]);
            arbol.hijos[0].movHecho = "Abajo";
            moverArriba(arbol.tablero);

            arbol.hijos[1].tablero = moverArriba(arbol.tablero);
            arbol.hijos[1].distancia = distanciaManhatan(arbol.hijos[1]);
            arbol.hijos[1].movHecho = "Arriba";
            moverAbajo(arbol.tablero);

            arbol.hijos[2].tablero = moverIzquierda(arbol.tablero);
            arbol.hijos[2].distancia = distanciaManhatan(arbol.hijos[2]);
            arbol.hijos[2].movHecho = "Izquierda";
            moverDerecha(arbol.tablero);
        }
        
        //cualquiera de los dos cuadros de en medio de la fila 3     
        if(x==3 && (y==1 || y==2)){
          
            arbol.hijos[0].tablero = moverIzquierda(arbol.tablero);
            arbol.hijos[0].distancia = distanciaManhatan(arbol.hijos[0]);
            arbol.hijos[0].movHecho = "Izquierda";
            moverDerecha(arbol.tablero);

            arbol.hijos[1].tablero = moverDerecha(arbol.tablero);
            arbol.hijos[1].distancia = distanciaManhatan(arbol.hijos[1]);
            arbol.hijos[1].movHecho = "Derecha";
            moverIzquierda(arbol.tablero);

            arbol.hijos[2].tablero = moverArriba(arbol.tablero);
            arbol.hijos[2].distancia = distanciaManhatan(arbol.hijos[2]);
            arbol.hijos[2].movHecho = "Arriba";
            moverAbajo(arbol.tablero);
        }
    }  //fin de tmp 3
    
    if(tmp==4){
        movimientos = new String [4];
          
          arbol.hijos[0].tablero = moverIzquierda(arbol.tablero);
        arbol.hijos[0].distancia = distanciaManhatan(arbol.hijos[0]);
        arbol.hijos[0].movHecho = "Izquierda";
        moverDerecha(arbol.tablero);
        arbol.hijos[1].tablero = moverDerecha(arbol.tablero);
        arbol.hijos[1].distancia = distanciaManhatan(arbol.hijos[1]);
        arbol.hijos[1].movHecho = "Derecha";
        moverIzquierda(arbol.tablero);
        arbol.hijos[2].tablero = moverArriba(arbol.tablero);
        arbol.hijos[2].distancia = distanciaManhatan(arbol.hijos[2]);
        arbol.hijos[2].movHecho = "Arriba";
               moverAbajo(arbol.tablero);
               
        arbol.hijos[3].tablero = moverAbajo(arbol.tablero);
        arbol.hijos[3].distancia = distanciaManhatan(arbol.hijos[3]);
        arbol.hijos[3].movHecho = "Abajo";
               moverArriba(arbol.tablero);
       
     }
    
    int d=arbol.hijos[0].distancia;
    int indice=0;
        for(int i = 0;i<tmp;i++){
            
            if(arbol.hijos[i].distancia<d){
                d=arbol.hijos[i].distancia;
                indice = i;
            }
        }
        
        if(arbol.hijos[indice].movHecho=="Abajo"){
                         moverAbajo(arbol.tablero);
        }
        if(arbol.hijos[indice].movHecho=="Arriba"){
                         moverArriba(arbol.tablero);
        }
        if(arbol.hijos[indice].movHecho=="Derecha"){
                         moverDerecha(arbol.tablero);
        }
        if(arbol.hijos[indice].movHecho=="Izquierda"){
                         moverIzquierda(arbol.tablero);
        }
        System.out.println(arbol.hijos[indice].movHecho);
             for(int i=0;i<4;i++){
                 for(int j=0;j<4;j++){
                     System.out.print(arbol.hijos[indice].tablero[i][j]+"\t");
                 }
                 System.out.println("");
             }
             System.out.println("------------");
             movPosicion(arbol);
    
    }   //fin del else

 }   //fin del metodo
 

 public int totalMovimientos()
 {
 return totMov;
 }
 
    public void imprime(Nodo raiz){
       // rellenaOrdenado();
        for(int i=0; i<raiz.tablero.length; i++){
           for(int j=0; j<raiz.tablero[i].length; j++){
              System.out.println(i+" "+j+"  ------> "+ raiz.tablero[i][j]);
           }
        }   
    }
    
    public void imprime3(Nodo raiz){
       // rellenaOrdenado();
        for(int i=0; i<raiz.tablero.length; i++){
           for(int j=0; j<raiz.tablero[i].length; j++){
              System.out.println(i+" "+j+"  ------> "+ raiz.tablero[i][j]);
           }
        }   
    }
    
}
