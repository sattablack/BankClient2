package com.atoudeft.controleur;

import com.atoudeft.client.Client;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-11-01
 */
public class EcouteurListeComptes extends MouseAdapter {

    private Client client;
    public EcouteurListeComptes(Client client) {
        this.client = client;
    }

    @Override
    public void mouseClicked(MouseEvent evt) {
        //à compléter
        Object source = evt.getSource();
        if (source instanceof JList) {
            JList jList = (JList) evt.getSource();

            if (evt.getClickCount() == 2) {
                Object elmt = jList.getModel().getElementAt(jList.getSelectedIndex());
                String selection = ((String) elmt).replaceAll(".*\\[(.*)\\].*", "$1");
                client.envoyer("SELECT " + selection.toLowerCase());
            }

        }
    }
}
