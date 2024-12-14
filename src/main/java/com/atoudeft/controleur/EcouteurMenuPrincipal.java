package com.atoudeft.controleur;

import com.atoudeft.client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2024-11-01
 */
public class EcouteurMenuPrincipal implements ActionListener {
    private Client client;
    private JFrame fenetre;

    public EcouteurMenuPrincipal(Client client, JFrame fenetre) {
        this.client = client;
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        Object source = evt.getSource();
        String action;
        String alias;
        int res;

        if (source instanceof JMenuItem) {
            action = ((JMenuItem)source).getActionCommand();
            switch (action) {
                case "CONNECTER":

                    if (!client.isConnecte()) {
                        if (!client.connecter()) {
                            JOptionPane.showMessageDialog(fenetre, "Le serveur ne répond pas");
                            break;
                        }
                    }
                    break;
                case "DECONNECTER":
                    if (!client.isConnecte())
                        break;
                    res = JOptionPane.showConfirmDialog(fenetre, "Vous allez vous déconnecter",
                            "Confirmation Déconnecter",
                            JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                    if (res == JOptionPane.OK_OPTION){
                        client.deconnecter();
                        // Question 1.1 Pour envoyer le statut que le client est disconnect et deconneter le Client
                        client.setConnecte(false);
                    }
                    break;
                case "CONFIGURER":

                    JPanel panneauConfig = new JPanel(new GridLayout(2, 2));

                    // Champs pour l'adresse IP et le port
                    JTextField champAdresseIP = new JTextField("127.0.0.1");
                    JTextField champPort = new JTextField("8888");

                    panneauConfig.add(new JLabel("Adresse IP :"));
                    panneauConfig.add(champAdresseIP);
                    panneauConfig.add(new JLabel("Port :"));
                    panneauConfig.add(champPort);

                    boolean valide = false;
                    while (!valide) {
                        int ress = JOptionPane.showConfirmDialog(
                                fenetre,
                                panneauConfig,
                                "Configuration serveur",
                                JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.PLAIN_MESSAGE
                        );

                        if (ress == JOptionPane.OK_OPTION) {
                            try {
                                // Récupération et validation des données saisies
                                String adresseIP = champAdresseIP.getText().trim();
                                int port = Integer.parseInt(champPort.getText().trim());

                                // Vous pouvez valider davantage les valeurs ici si nécessaire
                                if (port <= 0 || port > 65535) {
                                    throw new NumberFormatException("Le port doit être entre 1 et 65535.");
                                }

                                // Appliquer les configurations au client
                                client.setAdrServeur(adresseIP);
                                client.setPortServeur(port);
                                valide = true; // Sortir de la boucle
                            } catch (NumberFormatException ex) {
                                // Afficher un message d'erreur si le port n'est pas valide
                                JOptionPane.showMessageDialog(
                                        fenetre,
                                        "Veuillez entrer un numéro de port valide (entier entre 1 et 65535).",
                                        "Erreur",
                                        JOptionPane.ERROR_MESSAGE
                                );
                            }
                        } else {
                            // Si l'utilisateur annule, sortir de la boucle
                            valide = true;
                        }
                    }
                    break;

                case "QUITTER":
                    if (client.isConnecte()) {
                        res = JOptionPane.showConfirmDialog(fenetre, "Vous allez vous déconnecter",
                                "Confirmation Quitter",
                                JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                        if (res == JOptionPane.OK_OPTION){
                            client.deconnecter();
                            System.exit(0);
                        }
                    }
                    else
                        System.exit(0);
                    break;
            }
        }
    }
}