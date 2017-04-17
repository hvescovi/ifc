package dao;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import modelo.Pessoa;

public class PessoaDAO {

    public ArrayList<Pessoa> carregaListaDePessoas() {
        ArrayList<Pessoa> pessoas = new ArrayList();
        try {
            FileInputStream fis = new FileInputStream("/home/friend/pessoas.xml");
            BufferedInputStream bis = new BufferedInputStream(fis);
            XMLDecoder xmldec = new XMLDecoder(bis);
            pessoas = (ArrayList<Pessoa>) xmldec.readObject();
        } catch (Exception ex) {
            System.out.println("ERRO: " + ex.getMessage());
        }
        return pessoas;
    }
    
    public void inserePessoa(Pessoa p) {
        ArrayList<Pessoa> pessoas = carregaListaDePessoas();
        pessoas.add(p);
        try {
            FileOutputStream fout = new FileOutputStream("/home/friend/pessoas.xml");
            BufferedOutputStream bos = new BufferedOutputStream(fout);
            XMLEncoder xmlenc = new XMLEncoder(bos);
            xmlenc.writeObject(pessoas);
            xmlenc.close();
        }
        catch (Exception ex) {
            System.out.println("ERRO: "+ex.getMessage());
        }
    }
}
