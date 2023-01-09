public class Personatge {
    static String nomPersonatge;
    static int vidaMax = 100;
    static int pVida = 100;
    static int pAtac = 10;
    static int pDef = 10;
    static int nivell = 1;
    static int pNivell = 0;

    public static void posarNom(String nom){
        nomPersonatge = nom;
    }
    
    public static void ferCavaller(){
        vidaMax = 125;
        pVida = 125;
        pDef = 30;
    }

    public static void ferMag(){
        pAtac = 30;
    }

    public static void ferCaçador(){
        vidaMax = 115;
        pVida = 115;
        pDef = 15;
        pAtac = 20;
    }

    public static void pujarDeNivell(){
        vidaMax += 10;
        pVida = vidaMax;
        pAtac += 5;
        pDef += 5;
    }

    public static void dany(int punts){
        pVida -= punts;
        if (pVida <= 0) gameOver();
    }

    public static void curar(int punts){
        pVida += punts;
        if (pVida > vidaMax) pVida = vidaMax;
    }

    public static void penalitzacio(int punts){
        pAtac -= punts;
        if (pAtac < 1) pAtac = 1;

    }

     private static void gameOver() {
    }
}
