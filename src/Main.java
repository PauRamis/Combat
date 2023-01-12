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
        if (response == 1) ferPersonatge();
        else if (response == 2) return;
        else{
            System.out.println("Opció no valida, intodueix els valors numerics");
            menu();
        }
    }

    private static void ferPersonatge() {
        Personatge jugador = new Personatge();
        System.out.println("Antes de començar, tria un personarge ");
        System.out.println("1- Cavaller. Portes una gran armadura per priotitzar la defensa");
        System.out.println("2- Mag. Priotizes l'atac, gran dany pero poca defensa");
        System.out.println("3- Caçador. Per tu, l'equilibri entre atac i defensa es la clau");

        int response = Integer.parseInt(scanner.nextLine());
        if (response == 1) jugador.ferCavaller();
        if (response == 2) jugador.ferMag();
        if (response == 3) jugador.ferCaçador();

        System.out.println("Quin es el nom del teu personatge?");
        String nom = scanner.nextLine();
        jugador.posarNom(nom);
        System.out.println("Que començi l' aventura de " + nom + "!");
        joc(jugador);
    }

    private static void joc(Personatge jugador) {
        System.out.println("Que comenci el joc! Has de guanyar 10 combats seguits sense morir.");
        System.out.println("Cada combat sirá de maxim de 10 rondes, si empatau, quedará com nul i jugarás un altre");
        int victories = 0;
        boolean result; //t= victoria; f= empat; La derrota romp el bucle
        while (victories < 10){
            result = combat(jugador);

            if (result){
                victories++;
                jugador.pNivell += 40;
                jugador.mirarNivell();
            }
            else System.out.println("Has perdut!"); //Aplicar Game Over

        }

    }

    private static boolean combat(Personatge jugador) {
        Personatge enemic = new Personatge();
        for (int i = 0; i < 10; i++) {
            System.out.println("Comença el combat " + i);
            if (ronda(jugador, enemic)) return true;
        }
        return false;
    }

    private static boolean ronda(Personatge jugador, Personatge enemic) {
        for (int i = 0; i < 10; i++) {
            jugador.mostrarEstat();
            System.out.println("Quina estrategia vols usar?");
            System.out.println("1- Atac");
            System.out.println("2- Defensa");
            System.out.println("3- Engany");
            System.out.println("4- Maniobra");
            int accioJugador = Integer.parseInt(scanner.nextLine());
            if (accioJugador < 1 || accioJugador > 4) {
                System.out.println("Tria una acció valida");
                ronda(jugador, enemic);
            }
            int accioEnemic = (int) Math.random()*4;
            resoldreCombat(accioJugador, accioEnemic, jugador, enemic);
        }
        return false;
    }

    private static void resoldreCombat(int accioJugador, int accioEnemic, Personatge jugador, Personatge enemic) {
        if (accioJugador == 1) {
            if (accioEnemic == 1) {
                jugador.dany(enemic.punts());
            }
            if (accioEnemic == 2) {

            }
            if (accioEnemic == 3) {

            }
            if (accioEnemic == 4) {

            }
        }
        if (accioJugador == 2) {
            if (accioEnemic == 1) {

            }
            if (accioEnemic == 2) {

            }
            if (accioEnemic == 3) {

            }
            if (accioEnemic == 4) {

            }
        }
        if (accioJugador == 3) {
            if (accioEnemic == 1) {

            }
            if (accioEnemic == 2) {

            }
            if (accioEnemic == 3) {

            }
            if (accioEnemic == 4) {

            }
        }
        if (accioJugador == 4) {
            if (accioEnemic == 1) {

            }
            if (accioEnemic == 2) {

            }
            if (accioEnemic == 3) {

            }
            if (accioEnemic == 4) {

            }
        }
    }


}