public class Personatge {
    static int vidaMax = 100;
    static int pVida = 100;
    static int pAtac = 10;
    static int pDef = 10;
    static int nivell = 1;
    static int pNivell = 0;
    
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
}
