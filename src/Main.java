import java.util.Scanner;
import java.util.logging.SocketHandler;

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
        else {
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
        else if (response == 2) jugador.ferMag();
        else if (response == 3) jugador.ferCaçador();
        else if (response == 666) jugador.ferAdmin();
        else {
            System.out.println("Per favor, tria una de les 3 classes jugables");
            ferPersonatge();
        }

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
        int combats = 0;
        boolean result; //t= victoria; f= empat; La derrota romp el bucle
        while (victories < 10) {
            combats ++;
            System.out.println("Comença el combat " + combats);

            if (ronda(jugador, ferEnemic())) { //Si guanyam, obtenim Exp i es suma una victoria
                victories++;
                System.out.println("Victories: " + victories);
                jugador.guanyarExp();
            }
            jugador.pVida = jugador.vidaMax; //Retornem les estadistiques al jugador després de cada ronda
            jugador.pAtac += jugador.pAtacPerduts;
            jugador.pAtacPerduts = 0;
            jugador.pDef += jugador.pDefPerduts;
            jugador.pDefPerduts = 0;
        }

    }

    private static Personatge ferEnemic() {
        Personatge enemic = new Personatge();
        int eClass = (int) ((Math.random() * 3) + 1);
        if (eClass == 1) {
            enemic.ferCavaller();
            System.out.println("Un cavaller oscur et desafia!");
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
        for (int i = 1; i < 11; i++) {
            if (jugador.pVida <= 0) {
                System.out.println("Has mort! Fi de la partida :(");
                menu();
                System.exit(0);
            }
            if (enemic.pVida <= 0) {
                System.out.println("Enhorabona! Has guanyat!");
                return true;
            }
            System.out.println("TURN " + i);
            jugador.mostrarEstat();
            enemic.mostrarEstat();
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
        System.out.println("Empat! Escapes sense pena ni gloria. Però no tardes en trobar un altre oponent");
        return false;
    }

    private static void resoldreCombat(int accioJugador, int accioEnemic, Personatge jugador, Personatge enemic) {
        if (accioJugador == 1) {
            if (accioEnemic == 1) {
                System.out.println("Vos atacau mutuament");
                jugador.dany(enemic.punts(enemic.pAtac));
                enemic.dany(jugador.punts(jugador.pAtac));
            }
            if (accioEnemic == 2) {
                System.out.println("L'enemic bloquetja el teu atac i es cura");
                enemic.curar(enemic.punts(enemic.pDef));
            }
            if (accioEnemic == 3) {
                System.out.println("Consegueixes ferir-lo");
                enemic.dany(jugador.punts(jugador.pAtac));
            }
            if (accioEnemic == 4) {
                System.out.println("Consegueixes ferir-lo");
                enemic.dany(jugador.punts(jugador.pAtac));
            }
        }
        if (accioJugador == 2) {
            if (accioEnemic == 1) {
                System.out.println("Bloquetjes l'atac de l'enemic i et cures");
                jugador.curar(jugador.punts(jugador.pDef));
            }
            if (accioEnemic == 2) {
                System.out.println("Vos cubriu i aprofitau per curar les ferides obertes");
                jugador.curar(jugador.punts(jugador.pDef));
                enemic.curar(enemic.punts(enemic.pDef));
            }
            if (accioEnemic == 3) {
                System.out.println("L'enemic t'engana i et fa un atac doble");
                jugador.dany(enemic.punts(enemic.pAtac));
                jugador.dany(enemic.punts(enemic.pAtac));
            }
            if (accioEnemic == 4) {
                System.out.println("L'enemic et fa una maniobra i et penalitza");
                jugador.penalitzacio(enemic.punts(enemic.pDef));
            }
        }
        if (accioJugador == 3) {
            if (accioEnemic == 1) {
                System.out.println("L'enemic t'ataca");
                jugador.dany(enemic.punts(enemic.pAtac));
            }
            if (accioEnemic == 2) {
                System.out.println("Enganes a l'enemic i li fas un atac doble");
                enemic.dany(jugador.punts(jugador.pAtac));
                enemic.dany(jugador.punts(jugador.pAtac));
            }
            if (accioEnemic == 3) {
                System.out.println("Creuau atacs violentament, rebent el doble de dany tots danys");
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
                System.out.println("L'enemic t'ataca");
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