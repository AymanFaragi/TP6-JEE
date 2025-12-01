package net.codejava.catalogueejb;

import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface ICatalogueRemote {
    public void addProduit(Produit p);
    public List<Produit> getAllProduits();
    public List<Produit> getProduitsParMotCle(String mc);
    public Produit getProduit(Long id);
    public void removeProduit(Long id);
    public void updateProduit(Produit p);
}
