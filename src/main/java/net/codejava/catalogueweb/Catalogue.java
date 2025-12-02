package net.codejava.catalogueweb;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.ejb.EJB;
import net.codejava.catalogueejb.ICatalogueRemote;
import net.codejava.catalogueejb.Produit;

import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class Catalogue implements Serializable {

    @EJB
    private ICatalogueRemote metier;

    private String nom;
    private Double prix;
    private Long id;
    private String motcle;
    private String result;

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public Double getPrix() { return prix; }
    public void setPrix(Double prix) { this.prix = prix; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMotcle() { return motcle; }
    public void setMotcle(String motcle) { this.motcle = motcle; }

    public String getResult() { return result; }

    public void addProduit() {
        if (nom != null && prix != null) {
            metier.addProduit(new Produit(nom, prix));
            result = "<p>Produit ajoute avec succes</p>";
        }
    }

    public void listProduits() {
        List<Produit> produits = metier.getAllProduits();
        result = toHtmlTable(produits);
    }

    public void searchProduits() {
        if (motcle != null) {
            List<Produit> produits = metier.getProduitsParMotCle(motcle);
            result = toHtmlTable(produits);
        }
    }

    public void deleteProduit() {
        if (id != null) {
            metier.removeProduit(id);
            result = "<p>Produit supprime</p>";
        }
    }

    public void updateProduit() {
        if (id != null && nom != null && prix != null) {
            Produit p = new Produit(nom, prix);
            p.setId(id);
            metier.updateProduit(p);
            result = "<p>Produit modifie</p>";
        }
    }


    private String toHtmlTable(List<Produit> produits) {
        StringBuilder sb = new StringBuilder();
        sb.append("<table border='1' cellpadding='6'>");
        sb.append("<tr><th>ID</th><th>Nom</th><th>Prix</th></tr>");
        for (Produit p : produits) {
            sb.append("<tr>")
                    .append("<td>").append(p.getId()).append("</td>")
                    .append("<td>").append(p.getNomProduit()).append("</td>")
                    .append("<td>").append(p.getPrix()).append("</td>")
                    .append("</tr>");
        }
        sb.append("</table>");
        return sb.toString();
    }
    public String goToSearch() {

        List<Produit> produits = metier.getAllProduits();

        if (produits == null || produits.isEmpty()) {
            FacesMessage msg = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Impossible de naviguer: aucun produit dans le catalogue",
                    null
            );
            FacesContext.getCurrentInstance().addMessage(null, msg);

            return null;
        }


        return "page2?faces-redirect=true";
    }
}
