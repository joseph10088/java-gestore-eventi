package concerto;

import evento.Evento;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Concerto extends Evento{

    private LocalTime ora;

    private double prezzo;

    public Concerto(String titolo, LocalDate data, int numeroPostiTot, LocalTime ora, double prezzo) {
        super(titolo, data, numeroPostiTot);
        if(getData().isBefore(LocalDate.now())){
            throw new IllegalArgumentException("non e possibile modificare l'ora l'evento e gia passato !");
        } else if(ora == null){
            throw new IllegalArgumentException("l'ora non puo essere nulla !");
        } else {
            this.ora = ora;
        }
        if(prezzo < 0){
            throw new IllegalArgumentException("il prezzo non può essere negativo");
        }
        this.prezzo = prezzo;
    }
    

    public LocalTime getOra() {
        return ora;
    }


    public void setOra(LocalTime ora) {
        if(getData().isBefore(LocalDate.now())){
            throw new IllegalArgumentException("non e possibile modificare l'ora l'evento e gia passato !");
        } else if(ora == null){
            throw new IllegalArgumentException("l'ora non puo essere nulla !");
        }
        this.ora = ora;
    }


    public double getPrezzo() {
        return prezzo;
    }


    public void setPrezzo(double prezzo) {
        if(prezzo < 0){
            throw new IllegalArgumentException("il prezzo non può essere negativo");
        }
        this.prezzo = prezzo;
    }

    public String getDataOraFormattata() {
        DateTimeFormatter formattaData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formattaOra = DateTimeFormatter.ofPattern("HH:mm");
        return getData().format(formattaData) + " " + ora.format(formattaOra);
    }


    public String getPrezzoFormattato() {
        NumberFormat prezzoItaliano = NumberFormat.getCurrencyInstance(Locale.ITALY);
        return prezzoItaliano.format(prezzo);
    }

    @Override
    public String toString() {
        return getDataOraFormattata() + " - " + getTitolo() + " - " + getPrezzoFormattato();
    }
}
