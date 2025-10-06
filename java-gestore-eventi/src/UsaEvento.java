
import CustomException.DisdiciException;
import CustomException.PrenotaException;
import evento.Evento;
import java.time.LocalDate;
import java.util.Scanner;

public class UsaEvento {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Evento e1 = null;

        while(e1 == null){
        try {
            System.out.print("Iserisci un titolo: ");
            String titolo = scan.nextLine();

            System.out.print("Iserisci l'anno dell'evento: ");
            int anno = scan.nextInt();

            System.out.print("Iserisci il mese(formato numero numero) dell'evento: ");
            int mese = scan.nextInt();

            System.out.print("Iserisci il giorno dell'evento: ");
            int giorno = scan.nextInt();

            System.out.print("Iserisci il numero di posti disponibili per l'evento: ");
            int posti = scan.nextInt();

            scan.nextLine();
        
            e1 = new Evento(titolo, LocalDate.of(anno, mese, giorno), posti);

            } catch (IllegalArgumentException e) {
                System.err.println("\nErrore: " + e.getMessage());
                System.out.println("Rinserisci i dati \n");
            }
        }

        System.out.print("Vuoi effettuare qualche prenotazione(Si/No)?: ");
        String scelta = scan.nextLine().trim().toLowerCase();

        if(scelta.equals("si")){
            System.out.print("Quante prenotazioni vuoi effettuare ?: ");
            int numScelta = scan.nextInt();
            try {
                for (int i = 0; i < numScelta; i++) {
                    e1.prenota();
                }
            } catch (PrenotaException e) {
                System.err.println("errore: " + e.getMessage());
                scan.close();
                return;
            }
        }

        System.out.println("prenotazioni effettuate: " + e1.getNumeroPostiPrenotati() + " posti disponibili: " + e1.getPostiDisponibili());
        System.out.print("");

        scan.nextLine();

        System.out.print("Vuoi disdire qualche prenotazione(Si/No) ?: ");
        scelta = scan.nextLine().trim().toLowerCase();

        if(scelta.equals("si")){
            System.out.println("quante prenotazioni vuoi disdire ?: ");
            int numSceltaDisdici = scan.nextInt();
            try {
                for (int i = 0; i < numSceltaDisdici; i++) {
                    e1.disdici();
                }
            } catch (DisdiciException e) {
                System.out.println("ERRORE: " + e.getMessage());
                scan.close();
                return;
            }
        }
        

        System.out.println("prenotazioni effettuate: " + e1.getNumeroPostiPrenotati() + " posti disponibili: " + e1.getPostiDisponibili());

        scan.close();

    }       
}
