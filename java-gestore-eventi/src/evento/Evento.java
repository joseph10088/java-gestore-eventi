package evento;

import CustomException.DisdiciException;
import CustomException.PrenotaException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Evento {

    private String titolo;

    private LocalDate data;

    private final int numeroPostiTot;

    private int numeroPostiPrenotati;

    public Evento(String titolo, LocalDate data, int numeroPostiTot) {
        if(titolo == null || titolo.isBlank()){
            throw new IllegalArgumentException("il titolo e obbligatorio !");
        } else {
            this.titolo = titolo;
        }
        if(data == null){
            throw new IllegalArgumentException("La data non può essere nulla !");
        } else if(data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La data non può essere prima di oggi !");
        } else {
            this.data = data;
        }
        if(numeroPostiTot <= 0){
            throw new IllegalArgumentException("non e possibile avere minore oppure zero posti !");
        } else {
            this.numeroPostiTot = numeroPostiTot;
        }

        numeroPostiPrenotati = 0;
    }

    // getters & setters

    public String getTitolo() {
        return titolo;
    }

    public LocalDate getData() {
        return data;
    }

    public int getNumeroPostiTot() {
        return numeroPostiTot;
    }

    public int getNumeroPostiPrenotati() {
        return numeroPostiPrenotati;
    }

    public void setTitolo(String titolo) {
        if(titolo == null || titolo.isBlank()){
            throw new IllegalArgumentException("il titolo non puo essere vuoto !");
        }
        this.titolo = titolo;
    }

    public void setData(LocalDate data) {
        if(data == null){
            throw new IllegalArgumentException("La data non può essere nulla !");
        } else if(data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La data non può essere prima di oggi !");
        } else {
            this.data = data;
        }
    }

    // metodi

    public void prenota() throws PrenotaException{
        if(data.isBefore(LocalDate.now())) {
            throw new PrenotaException("non è possibile prenotare l'evento: " + getTitolo() + "e gia passato !");
        } 
        if(numeroPostiPrenotati >= numeroPostiTot){
            throw new PrenotaException("posti esauriti ! " + "disponibili: " + getPostiDisponibili()); 
        }
        numeroPostiPrenotati++;
    }

    public void disdici() throws DisdiciException {
        if(data.isBefore(LocalDate.now())){
            throw new DisdiciException("non e possibile disdire: l'evento e gia passato!");
        }
        if(numeroPostiPrenotati == 0){
            throw new DisdiciException("non e possibile disdire: non c'è nessuna prenotazione!");
        }
        numeroPostiPrenotati--;
    }

    public int getPostiDisponibili(){
        return numeroPostiTot - numeroPostiPrenotati;
    }
    
    // tostring

    @Override
    public String toString() {
        DateTimeFormatter formattata = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formattata) + " - " + titolo;
    }

    
}
