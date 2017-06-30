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
    
    private String tituloX;
    private ArrayList<Float> pontos = new ArrayList();    
    
    public DadoDoGrafico(String titulo, float[] valores){
        this.tituloX = titulo;
        for (float f : valores) {
            pontos.add(f);
        }
    }
    
    public DadoDoGrafico(String x, float y){
        this.tituloX = x;
        this.pontos.add(y);
    }
    
    public DadoDoGrafico(String x, float y1, float y2){
        this.tituloX = x;
        this.pontos.add(y1);
        this.pontos.add(y2);
    }
    
    public String getTituloX() {
        return tituloX;
    }

    public void setTituloX(String tituloX) {
        this.tituloX = tituloX;
    }

    public ArrayList<Float> getPontos() {
        return pontos;
    }

    public void setPontos(ArrayList<Float> pontos) {
        this.pontos = pontos;
    }

    
    
}
