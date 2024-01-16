package org.learning.springlamiapizzeriacrudrelazioni.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class Offerta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private LocalDate dataInizio;
    private LocalDate dataFine;
    @Column(nullable = false)
    private String titoloOfferta;
    @ManyToOne
    private Pizza pizza;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public String getTitoloOfferta() {
        return titoloOfferta;
    }

    public void setTitoloOfferta(String titoloOfferta) {
        this.titoloOfferta = titoloOfferta;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }
}
