package modelo;

public class Livro {
    
    private int idLivro;
    private String titulo;
    private String autores;
    private String ano;
   
    public Livro() {
    }
    
    public Livro(int idLivro, String titulo, String autores, String ano) {
        this.idLivro = idLivro;
        this.titulo = titulo;
        this.autores = autores;
        this.ano = ano;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }
    
    
}
