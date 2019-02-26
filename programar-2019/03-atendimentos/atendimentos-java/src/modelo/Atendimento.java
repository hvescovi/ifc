package modelo;

import java.util.ArrayList;

public class Atendimento {

    public String data;
    public String hora;
    public Cliente cli;
    ArrayList<Servico> servicos = new ArrayList();
    ArrayList<Venda> vendas = new ArrayList();
    
    public String toString(){
        
        String s = data+ " às "+hora + "; atendido: "+cli;
        
        for (Servico serv : servicos) {
            s += "\n"+serv;
        }
        for (Venda v : vendas){
            s+= "\n"+v;
        }
        
        s+= "\nTotal: "+ Total();
        
        return s;
    }
    
    public float Total() {
        float total = 0;
        
        for (Servico serv : servicos) {
            total += serv.preco;
        }
        for (Venda v : vendas){
            total += (v.prod.preco)*(v.qtd);
        }
        
        return total;
    }
    
    // TESTE DO ATENDIMENTO
    public static void main(String[] args) {
        
        Cliente manel = new Cliente();
        manel.cpf = "123.456.789-30";
        manel.nome = "Manoel da Silva";
        
        Servico s = new Servico();
        s.descricao = "Troca de lâmpada";
        s.preco = 10;
        
        Servico s2 = new Servico();
        s2.descricao = "Reparo de tomada";
        s2.preco = 20;
        
        Produto lampada = new Produto();
        lampada.descricao = "Lampada de led";
        lampada.preco = (float) 20.0;
        lampada.qtdEstoque = 20;
        lampada.unidade = "unidade";
        
        Venda v = new Venda();
        v.prod = lampada;
        v.qtd = 2;
        
        Atendimento a1 = new Atendimento();
        a1.data = "25/02/2019";
        a1.hora = "21:33";
        a1.cli = manel;
        a1.servicos.add(s);
        a1.servicos.add(s2);
        a1.vendas.add(v);
        
        System.out.println(a1);
    }
}
