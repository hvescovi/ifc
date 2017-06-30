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
public class DadoDoGrafico {
    
    private String valorX;
    //private float valorY;
    //private float valorY2;
    private ArrayList<Float> pontosY = new ArrayList();

    public DadoDoGrafico(String x) {
        this.valorX = x;
    }
    
    public String getValorX() {
        return valorX;
    }

    public void setValorX(String valorX) {
        this.valorX = valorX;
    }

    public ArrayList<Float> getPontosY() {
        return pontosY;
    }

    public void setPontosY(ArrayList<Float> pontosY) {
        this.pontosY = pontosY;
    }

    

    
    
}
