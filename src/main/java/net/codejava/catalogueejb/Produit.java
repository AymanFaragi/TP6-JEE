package net.codejava.catalogueejb;

import jakarta.persistence.*;

@Entity
@Table(name = "PRODUITS")
public class Produit implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomProduit;
    private double prix;

    public Produit() {
        super();
    }
    public Produit(String nomProduit, double prix) {
        this.nomProduit = nomProduit;
        this.prix = prix;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}
