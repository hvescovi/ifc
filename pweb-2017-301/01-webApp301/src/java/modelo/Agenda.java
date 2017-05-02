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
      Pessoa quemVai = null;
      
      // percorre a lista de pessoas
      for (Pessoa p: agenda) {
      
        // se a pessoa eh aquela que vai ser removida, sinaliza e sai do loop
        if (p.getCpf().equals(cpfApagar)) {
            quemVai = p;
            break;
        }
      }
      // se achou quem vai ser removido, remove
      if (quemVai != null) {
          agenda.remove(quemVai);
      }
    }
}
