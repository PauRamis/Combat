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
        System.out.println("En aquest joc has de guanyar 10 combats seguits sense morir.");
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
        for (int i = 1; i <= 10; i++) {
            System.out.println("Comença el combat " + i);
            if (ronda(jugador, ferEnemic())) return true;
        }
        return false;
    }

    private static Personatge ferEnemic() {
        Personatge enemic = new Personatge();
        int eClass = (int) ((Math.random() * 3) + 1);
        if (eClass == 1) {
            enemic.ferCavaller();
            System.out.println("Un cavaller oscur et desafafia!");
            enemic.nomPersonatge = "Cavaller oscur";
        }
        if (eClass == 2) {
            enemic.ferMag();
            System.out.println("T'enfrentes a un mag maligne!");
            enemic.nomPersonatge = "Mag maligne";
        }
        if (eClass == 3) {
            enemic.ferCaçador();
            System.out.println("Un caçador fantasmal t'ataca desde les ombres!");
            enemic.nomPersonatge = "Caçador fantasmal";
        }
        return enemic;
    }

    private static boolean ronda(Personatge jugador, Personatge enemic) {
        for (int i = 0; i < 10; i++) {
            jugador.mostrarEstat();
            enemic.mostrarEstat();
            if (jugador.pVida <= 0) {
                System.out.println("Has mort! Fi de la partida :(");
                menu();
            }
            if (enemic.pVida <= 0) {
                System.out.println("Enhorabona! Has guanyat!");
                return true;
            }
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
            int accioEnemic = (int) ((Math.random() * 4) + 1);
            resoldreCombat(accioJugador, accioEnemic, jugador, enemic);
        }
        return false;
    }

    private static void resoldreCombat(int accioJugador, int accioEnemic, Personatge jugador, Personatge enemic) {
        if (accioJugador == 1) {
            if (accioEnemic == 1) {
                jugador.dany(enemic.punts(enemic.pAtac));
                enemic.dany(jugador.punts(jugador.pAtac));
            }
            if (accioEnemic == 2) {
                enemic.curar(enemic.punts(enemic.pDef));
            }
            if (accioEnemic == 3) {
                enemic.dany(jugador.punts(jugador.pAtac));
            }
            if (accioEnemic == 4) {
                enemic.dany(jugador.punts(jugador.pAtac));
            }
        }
        if (accioJugador == 2) {
            if (accioEnemic == 1) {
                jugador.curar(jugador.punts(jugador.pDef));
            }
            if (accioEnemic == 2) {
                jugador.curar(jugador.punts(jugador.pDef));
                enemic.curar(enemic.punts(enemic.pDef));
            }
            if (accioEnemic == 3) {
                jugador.dany(enemic.punts(enemic.pAtac));
                jugador.dany(enemic.punts(enemic.pAtac));
            }
            if (accioEnemic == 4) {
                jugador.penalitzacio(enemic.punts(enemic.pDef));
            }
        }
        if (accioJugador == 3) {
            if (accioEnemic == 1) {
                jugador.dany(enemic.punts(enemic.pAtac));
            }
            if (accioEnemic == 2) {
                enemic.dany(jugador.punts(jugador.pAtac));
                enemic.dany(jugador.punts(jugador.pAtac));
            }
            if (accioEnemic == 3) {
                jugador.dany(enemic.punts(enemic.pAtac));
                jugador.dany(enemic.punts(enemic.pAtac));
                enemic.dany(jugador.punts(jugador.pAtac));
                enemic.dany(jugador.punts(jugador.pAtac));
            }
            if (accioEnemic == 4) {
                jugador.penalitzacio(enemic.punts(enemic.pDef));
            }
        }
        if (accioJugador == 4) {
            if (accioEnemic == 1) {
                jugador.dany(enemic.punts(enemic.pAtac));
            }
            if (accioEnemic == 2) {
                enemic.penalitzacio(jugador.punts(jugador.pDef));
            }
            if (accioEnemic == 3) {
                enemic.penalitzacio(jugador.punts(jugador.pDef));
            }
            if (accioEnemic == 4) {
                jugador.penalitzacio(enemic.punts(enemic.pDef));
                enemic.penalitzacio(jugador.punts(jugador.pDef));
            }
        }
    }


}