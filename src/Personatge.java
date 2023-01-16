public class Personatge {
    String nomPersonatge;
    int vidaMax = 100;
    int pVida = 100;
    int pAtac = 30;
    int pDef = 30;
    int nivell = 1;
    int pNivell = 0;

    public void posarNom(String nom) {
        this.nomPersonatge = nom;
    }

    public void ferCavaller() {
        this.vidaMax = 125;
        this.pVida = this.vidaMax;
        this.pDef = 50;
    }

    public void ferCaçador() {
        this.vidaMax = 115;
        this.pVida = this.vidaMax;
        this.pAtac = 50;
    }

    public void ferMag() {
        this.pDef = 15;
        this.pAtac = 70;
    }

    //Classe secreta, feta principalment per proves
    public void ferAdmin(){
        this.vidaMax = 999;
        this.pVida = 999;
        this.pDef = 99;
        this.pAtac = 99;
    }

    public void mostrarEstat() {
        System.out.println("");
        System.out.println(nomPersonatge);
        System.out.print("Vida: " + this.pVida + "/" + this.vidaMax + "  ");
        System.out.print("Atac: " + this.pAtac + "  ");
        System.out.print("Defensa: " + this.pDef + "  ");
        System.out.println("Nivell: " + this.nivell);
    }

    public int punts(int nivellHabilitat) {
        int punts = 0;
        for (int i = 0; i < nivellHabilitat; i++) {
            if (Math.random() < 0.5) {
                punts++;
            }
        }
        return punts;
    }

    public void guanyarExp() {
        this.pNivell += 40;
        System.out.println("Guanyes 40 punts d'experienca");
        mirarNivell();
    }

    public void mirarNivell() {
        if (this.pNivell > 100) {
            this.nivell += 1;
            this.pNivell -= 100;
            pujarDeNivell();
        }
    }

    public void pujarDeNivell() {
        System.out.println("Pujes de nivell!");
        this.vidaMax += 10;
        this.pVida = vidaMax;
        this.pAtac += 5;
        this.pDef += 5;
    }

    public void dany(int punts) {
        System.out.println(this.nomPersonatge + " ha rebut " + punts + " punts de dany!");
        this.pVida -= punts;
        if (this.pVida <= 0) System.out.println(this.nomPersonatge + "ha mort!!");
        ;
    }

    public void curar(int punts) {
        System.out.println(this.nomPersonatge + " és cura " + punts + " de vida!");
        this.pVida += punts;
        if (this.pVida > this.vidaMax) this.pVida = this.vidaMax;
    }

    //Es tria aleatoriament l'atribut a penalitzar, minim de 1.
    public void penalitzacio(int punts) {
        if (punts % 2 == 0) {
            System.out.println(nomPersonatge + " ha perdut " + punts + " d'atac!");
            this.pAtac -= punts;
            if (this.pAtac < 1) this.pAtac = 1;
        } else {
            System.out.println(nomPersonatge + " ha perdut " + punts + " de defensa!");
            this.pDef -= punts;
            if (this.pDef < 1) this.pDef = 1;
        }
    }

}
