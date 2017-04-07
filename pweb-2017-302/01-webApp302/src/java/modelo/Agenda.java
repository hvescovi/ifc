/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author friend
 */
public class Agenda {
    private ArrayList<Pessoa> agenda = new ArrayList();

    public ArrayList<Pessoa> getAgenda() {
        return agenda;
    }

    public void setAgenda(ArrayList<Pessoa> agenda) {
        this.agenda = agenda;
    }
    
    public void adicionaPessoa(Pessoa p) {
        this.agenda.add(p);
    }
}
