
import CustomException.DisdiciException;
import CustomException.PrenotaException;
import evento.Evento;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UsaEvento {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Evento e1 = null;

        while(e1 == null) {
        try {
            System.out.print("Inserisci un titolo: ");
            String titolo = scan.nextLine();

            System.out.print("Inserisci l'anno dell'evento: ");
            int anno = scan.nextInt();

            System.out.print("Inserisci il mese(formato numero) dell'evento: ");
            int mese = scan.nextInt();

            System.out.print("Inserisci il giorno dell'evento: ");
            int giorno = scan.nextInt();

            System.out.print("Inserisci il numero di posti disponibili per l'evento: ");
            int posti = scan.nextInt();

            scan.nextLine();
        
            e1 = new Evento(titolo, LocalDate.of(anno, mese, giorno), posti);

            } catch (IllegalArgumentException e) {
                System.err.println("\nErrore: " + e.getMessage());
                System.out.println("Rinserisci i dati \n");
                scan.nextLine();
            } catch (InputMismatchException e){
                System.err.println("errore: inserire numeri interi dove richiesto !");
                System.out.println("rinserisci i dati \n");
                scan.nextLine();
            } catch (DateTimeException e){
                System.err.println("\nErrore: " + e.getMessage());
                System.out.println("rinserisci i dati !");
            }
        }

        // prenotazioni
        System.out.print("Vuoi effettuare qualche prenotazione ?(SI/NO): ");
        String scelta = scan.nextLine().trim().toLowerCase();

        while(scelta.equals("si") || scelta.equals("s")){
            try {
                System.out.print( "posti disponibili: " + e1.getPostiDisponibili() + " quante prenotazioni vuoi effettuare ?: ");
                int num = scan.nextInt();

                if(num <= 0){
                    throw new PrenotaException("il numero non puo essere 0 o inferiore a 0 !");
                } else if(num > e1.getNumeroPostiTot()){
                    throw new PrenotaException("il numero e maggiore del numero di posti disponibili !");
                }

                for (int i = 0; i < num; i++) {
                    e1.prenota();
                }

                scan.nextLine();

            break;

            } catch (PrenotaException e) {

                System.err.println("errore: " + e.getMessage());

                scan.nextLine();

            } catch (InputMismatchException ex) {
                System.err.println("Errore: inserire un numero intero (no testo, no decimali)");
                scan.nextLine();
            }
        }

        System.out.println("prenotazioni effettuate: " + e1.getNumeroPostiPrenotati() + " disponibili: " + e1.getPostiDisponibili());

        // disdette
        System.out.print("vuoi effettuare qualche disdetta ?(SI/NO)): ");
        scelta = scan.nextLine().trim().toLowerCase();

        while(scelta.equals("si") || scelta.equals("s")){
            System.out.println("posti prenotati: " + e1.getNumeroPostiPrenotati() + " quante disdette vuoi prenotare ?: ");
            try {
                int num = scan.nextInt();

                if(num <= 0){
                    throw new DisdiciException("il numero non puo essere 0 o minore di 0 !");
                } else if(num > e1.getNumeroPostiPrenotati()){
                    throw new DisdiciException("il numero di disdette e superiore al numero di posti prenotati !");
                }

                    for (int i = 0; i < num; i++) {
                        e1.disdici();
                    }
                
                break;

            } catch (DisdiciException e) {

                System.err.println("errore: " + e.getMessage());

                scan.nextLine();

            } catch (InputMismatchException ex) {
                System.err.println("Errore: inserire un numero intero (no testo, no decimali).");
                scan.nextLine();
            }

        System.out.println("posti disponibili: " + e1.getPostiDisponibili() + " posti prenotati: " + e1.getNumeroPostiPrenotati());

        System.out.println(e1);

        scan.close();
    }
    }
}