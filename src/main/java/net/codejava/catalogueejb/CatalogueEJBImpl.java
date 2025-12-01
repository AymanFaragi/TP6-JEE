package net.codejava.catalogueejb;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless(name = "CATAL")
public class CatalogueEJBImpl implements ICatalogueRemote, ICatalogueLocal {

    @PersistenceContext(unitName="CatalogueEJB")
    private EntityManager em;

    @Override
    public void addProduit(Produit p) {
        em.persist(p);
    }

    @Override
    public List<Produit> getAllProduits() {
        return em.createQuery("SELECT p FROM Produit p", Produit.class)
                .getResultList();
    }

    @Override
    public List<Produit> getProduitsParMotCle(String mc) {
        return em.createQuery("SELECT p FROM Produit p WHERE p.nomProduit LIKE :x", Produit.class)
                .setParameter("x", "%" + mc + "%")
                .getResultList();
    }

    @Override
    public Produit getProduit(Long id) {
        return em.find(Produit.class, id);
    }

    @Override
    public void removeProduit(Long id) {
        Produit p = em.find(Produit.class, id);
        if (p != null) {
            em.remove(p);
        }
    }

    @Override
    public void updateProduit(Produit p) {
        em.merge(p);
    }
}
