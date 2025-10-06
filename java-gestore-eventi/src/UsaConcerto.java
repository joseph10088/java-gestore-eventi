
import CustomException.DisdiciException;
import CustomException.PrenotaException;
import concerto.Concerto;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class UsaConcerto {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Concerto c1 = null;

        while(c1 == null){
            try {

            System.out.print("inserisci il titolo del concerto: ");
            String titolo = scan.nextLine();

            System.out.print("inserisci l'anno dell'evento: ");
            int anno = scan.nextInt();
            
            System.out.print("inserisci il mese dell'evento: ");
            int mese = scan.nextInt();

            System.out.print("inserisci il giorno dell'evento: ");
            int giorno = scan.nextInt();

            System.out.print("inserisci il numero di posti totale dell'evento: ");
            int numeroPostiTot = scan.nextInt();

            System.out.print("inserisci l'ora dell'evento(orario): ");
            int ora = scan.nextInt();

            System.out.print("inserisci i minuti dell'evento(orario): ");
            int minuti = scan.nextInt();

            scan.nextLine();

            System.out.print("inserisci il prezzo del biglietto: ");
            String formattaPrezzo = scan.nextLine().trim().replace(",", ".");
            double prezzo = Double.parseDouble(formattaPrezzo);
            
            c1 = new Concerto(titolo, LocalDate.of(anno, mese, giorno), numeroPostiTot, LocalTime.of(ora, minuti), prezzo);
            } catch (IllegalArgumentException e) {
                System.err.println("errore: " + e.getMessage());
                System.out.println("\n rinserisci i dati");
            }
        }

        System.out.println("vuoi effettuare prenotazioni ?(si/no): ");
        String sceltaPrenota = scan.nextLine().trim().toLowerCase();
        if(sceltaPrenota.equals("si")) {
        System.out.print("quante prenotazioni vuoi prenotare ?: ");
        int numeroScelta = scan.nextInt();
        for (int i = 0; i <= numeroScelta; i++) {
            try {
                c1.prenota();
            } catch (PrenotaException e){
                System.err.println("errore: " + "prenotazione: " + (i + 1) + " fallita " +  e.getMessage());
                break;
            }
        }
        }

        scan.nextLine();

        System.out.println("prenotati: " + c1.getNumeroPostiPrenotati() + "disponibili: " + c1.getPostiDisponibili());

        System.out.println("vuoi disdire qualche prenotazione ?(si/no): ");
        String sceltaDisdici = scan.nextLine().trim().toLowerCase();
        if (sceltaDisdici.equals("si")) {
            System.out.print("quante disdette vuoi effettuare ?: ");
            int numeroScelta = scan.nextInt();
            for (int i = 0; i <= numeroScelta; i++) {
                try {
                    c1.disdici();
                } catch (DisdiciException e) {
                    System.err.println("errore: " + "disdetta: " + (i + 1) + " fallita " +  e.getMessage());
                    break;
                }
            }
        }
        
        // test metodi

        System.out.println("Data/ora formattata: " + c1.getDataOraFormattata());
        System.out.println("Prezzo formattato: " + c1.getPrezzoFormattato());
        System.out.println("toString(): " + c1);

        System.out.println("prenotati: " + c1.getNumeroPostiPrenotati() + "disponibili: " + c1.getPostiDisponibili());

        System.out.println(c1);

    }
}
