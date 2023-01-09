import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
        System.out.println("1- Joc nou");
        System.out.println("2- Sortir");
        int response = Integer.parseInt(scanner.nextLine());
        if (response == 1) triarPersonatge();
        if (response == 2) return;
        else{
            System.out.println("Opció no valida, intodueix els valors numerics");
            menu();
        }


    }
    private static void triarPersonatge() {
        System.out.println("Antes de començar, tria un personarge ");
        System.out.println("1- Cavaller. Portes una gran armadura per priotitzar la defensa");
        System.out.println("2- Mag. Priotizes l'atac, gran dany pero poca defensa");
        System.out.println("3- Caçador. Per tu, l'equilibri entre atac i defensa es la clau");
        int response = Integer.parseInt(scanner.nextLine());
        if (response == 1) Personatge.ferCavaller();
        if (response == 2) Personatge.ferMag();
        if (response == 3) Personatge.ferCaçador();

        System.out.println("Quin es el nom del teu personatge?");
        String nom = scanner.nextLine();
        Personatge.posarNom(nom);
        System.out.println("Que començi l' aventura de " + nom + "!");
    }
}