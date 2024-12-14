package com.atoudeft.controleur;

import com.atoudeft.client.Client;
import com.atoudeft.client.GestionnaireEvenementClient2;
import com.atoudeft.vue.PanneauConnexion;
import com.atoudeft.vue.PanneauOperationsCompte;
import com.atoudeft.vue.PanneauPrincipal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurOperationsCompte implements ActionListener {
    private Client client;
    private PanneauPrincipal PanneauPrincipal;

    public EcouteurOperationsCompte(Client client, PanneauPrincipal panneauPrincipal) {
        this.client = client;
        this.PanneauPrincipal = panneauPrincipal;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String action;
        Object source = e.getSource();
        if (source instanceof JButton) {

            action = ((JButton) source).getActionCommand();
            switch (action) {
                case "EPARGNE":
                    client.envoyer("EPARGNE");
                    break;
                case "DEPOT":
                    this.PanneauPrincipal.afficherPanneauDepot();
                    break;
                case "RETRAIT":
                   this.PanneauPrincipal.afficherPanneauRetrait();
                    break;
                case "TRANSFER":
                   this.PanneauPrincipal.afficherPanneauTransfer();
                    break;
                case "FACTURE":
                    this.PanneauPrincipal.afficherPanneauFacture();
                    break;
                case "HISTORIQUE":
                    // this.panneauPrincipal.afficherPanneauTransfer();
                    break;
            }

        }

    }
}
