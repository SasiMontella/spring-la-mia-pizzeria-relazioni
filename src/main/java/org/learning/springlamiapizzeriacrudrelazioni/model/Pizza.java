package org.learning.springlamiapizzeriacrudrelazioni.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;

@Entity
@Table(name = "pizzas")
public class Pizza {
    @NotEmpty(message = "Dai un nome alla tua pizza")
    @Size(min = 6, max = 15)
    @Column(nullable = false)
    private String name;
    @NotEmpty(message = "Inserisci la descrizione del prodotto")
    @Size(min = 1, max = 200)
    private String description;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String photo;
    @NotNull(message = "Inserisci un prezzo adatto")
    @Min(1)
    private BigDecimal price;
    @OneToMany(mappedBy = "pizza")
    private List<Offerta> offerte;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Offerta> getOfferte() {
        return offerte;
    }

    public void setOfferte(List<Offerta> offerte) {
        this.offerte = offerte;
    }
}
