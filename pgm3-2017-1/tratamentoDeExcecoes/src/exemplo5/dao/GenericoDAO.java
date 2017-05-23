package exemplo5.dao;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public abstract class GenericoDAO {

    String base = System.getProperty("user.home")+
            System.getProperty("file.separator");
    
    public <X> void salvarParaArquivo(ArrayList<X> lista, String nomeArq)
            throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream(nomeArq);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        XMLEncoder xmlenc = new XMLEncoder(bos);
        xmlenc.writeObject(lista);
        xmlenc.close();
    }

    public <Z> ArrayList<Z> lerDeArquivo(String nomeArq)
            throws FileNotFoundException {
        ArrayList<Z> ret = new ArrayList();
        FileInputStream fis = new FileInputStream(nomeArq);
        BufferedInputStream bis = new BufferedInputStream(fis);
        XMLDecoder xmldec = new XMLDecoder(bis);
        ret = (ArrayList<Z>) xmldec.readObject();
        return ret;
    }
    
    public abstract <K> void salvar(ArrayList<K> lista) throws FileNotFoundException;

    public abstract <V> ArrayList<V> ler() throws FileNotFoundException;
}
