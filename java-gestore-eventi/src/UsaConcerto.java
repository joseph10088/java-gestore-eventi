
import CustomException.DisdiciException;
import CustomException.PrenotaException;
import concerto.Concerto;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
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

            System.out.print("inserisci l'ora dell'evento(ora): ");
            int ora = scan.nextInt();

            System.out.print("inserisci i minuti dell'evento(minuti): ");
            int minuti = scan.nextInt();

            scan.nextLine();
            System.out.print("inserisci il prezzo del biglietto: ");
            String formattaPrezzo = scan.nextLine().trim().replace(",", ".");
            double prezzo = Double.parseDouble(formattaPrezzo);
            
            c1 = new Concerto(titolo, LocalDate.of(anno, mese, giorno), numeroPostiTot, LocalTime.of(ora, minuti), prezzo);
            } catch (IllegalArgumentException e) {
                System.err.println("errore: " + e.getMessage());
                scan.nextLine();
                System.out.println("\n rinserisci i dati");
            } catch (InputMismatchException e){
                System.err.println("errore: inserire numeri interi dove richiesto !");
                scan.nextLine();
                System.out.println("\n rinserisci i dati");
            } catch (DateTimeException e){
                System.err.println("errore: " + e.getMessage());
                scan.nextLine();
                System.out.println("\n rinserisci i dati");
            }
        }

        System.out.print("vuoi effettuare prenotazioni ?(si/no): ");
        String sceltaPrenota = scan.nextLine().trim().toLowerCase();

        while(sceltaPrenota.equals("si") || sceltaPrenota.equals("s")){
            try{
                System.out.println("\nposti disponibili: " + c1.getPostiDisponibili() +" quante prenotazioni vuoi effettuare ?: ");
                int num = scan.nextInt();
                if(num <= 0){
                    throw new PrenotaException("hai inserito un numero inferiore ai posti disponibili !");
                } else if(num > c1.getPostiDisponibili()){
                    throw new PrenotaException("hai inserito un numero superiore ai posti disponibili !");
                }
                for (int i = 0; i < num; i++) {
                    c1.prenota();
                }
                break;
            } catch (PrenotaException e){
                System.err.println("errore: " + e.getMessage());
                scan.nextLine();
            } catch (InputMismatchException e){
                System.err.println("errore: inserire un numero intero (no testo, no decimali)");
                scan.nextLine();
            }
        }

        System.out.println("\nprenotati: " + c1.getNumeroPostiPrenotati() + " disponibili: " + c1.getPostiDisponibili());

        System.out.print("vuoi disdire qualche prenotazione ?(si/no): ");
        String sceltaDisdici = scan.nextLine().trim().toLowerCase();
        while(sceltaDisdici.equals("si") || sceltaDisdici.equals("s")){
            try{
                System.out.println("\nprenotazioni effettuate: " + c1.getNumeroPostiPrenotati() + " quante ne vuoi disdire?: ");
                int num = scan.nextInt();
                if(num <= 0){
                    throw new DisdiciException("il valore inserito e inferiore ai posti prenotati !");
                } else if(num > c1.getNumeroPostiPrenotati()){
                    throw new DisdiciException("il numero e superiore ai posti prenotati !");
                }
                for (int i = 0; i < num; i++) {
                    c1.disdici();
                }
                scan.nextLine();
                break;
            } catch (DisdiciException e){
                System.err.println("errore: " + e.getMessage());
                scan.nextLine();
            } catch (InputMismatchException e){
                System.err.println("errore: inserire un numero intero (no testo, no decimali)");
                scan.nextLine();
            }
        }
        
        // test metodi
        System.out.println("\nData/ora formattata: " + c1.getDataOraFormattata());
        System.out.println("\nPrezzo formattato: " + c1.getPrezzoFormattato());
        System.out.println("\ntoString: " + c1);

        System.out.println("\nprenotati: " + c1.getNumeroPostiPrenotati() + " disponibili: " + c1.getPostiDisponibili());

        System.out.println(c1);

        scan.close();

    }
}
