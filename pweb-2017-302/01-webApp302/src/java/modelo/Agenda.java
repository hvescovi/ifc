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

    public void removePessoaPeloCPF(String cpfApagar) {
        Pessoa apagar = null;

        // percorre a agenda
        for (Pessoa p : agenda) {
            // se a pessoa atual eh a procurada, marca pra remover, e sai da busca
            if (p.getCpf().equals(cpfApagar)) {
                apagar = p;
                break;
            }
        }
        // se alguem foi marcado pra remocao, remove
        if (apagar != null) {
            agenda.remove(apagar);
        }
    }
}
