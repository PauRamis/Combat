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

    public static int punts(int nivellHabilitat) {
        int punts = 0;
        for (int i = 0; i < nivellHabilitat; i++) {
            if(Math.random() % 2 == 0){
                punts++;
            }
        }
        return punts;
    }

    public static void mirarNivell(){
        if (pNivell > 100){
            nivell += 1;
            pNivell -= 100;
            pujarDeNivell();
        }
    }

    public static void pujarDeNivell(){
        System.out.println("Pujes de nivell!");
        vidaMax += 10;
        pVida = vidaMax;
        pAtac += 5;
        pDef += 5;
    }

    public static void dany(int punts){
        System.out.println(nomPersonatge + " ha rebut " + punts + " punts de dany!");
        pVida -= punts;
        if (pVida <= 0) System.out.println(nomPersonatge + "ha mort!!");;
    }

    public static void curar(int punts){
        System.out.println(nomPersonatge + " és cura " + punts + " de vida!");
        pVida += punts;
        if (pVida > vidaMax) pVida = vidaMax;
    }

    //Es tria aleatoriament l'atribut a penalitzar, minim de 1.
    public static void penalitzacio(int punts){
        if (punts % 2 == 0){
            System.out.println(nomPersonatge + " ha perdut " + punts + " d'atac!");
            pAtac -= punts;
            if (pAtac < 1) pAtac = 1;
        } else {
            System.out.println(nomPersonatge + " ha perdut " + punts + " de defensa!");
            pDef -= punts;
            if (pDef < 1) pDef = 1;
        }
    }

     private static void gameOver() {
         System.out.println("Fi de la partida");
    }
}
