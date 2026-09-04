package libro;

public class Libro {
    private String titulo;
    private String autor;
    private String categoria;
    private boolean disponibilidad;
    
    public Libro(String titulo, String autor, String categoria, boolean disponibilidad) {
        if(titulo.length() < 2 || titulo.length() > 100) {
            throw new IllegalArgumentException("El titulo es muy corto o largo.");
        }
        this.titulo = titulo;
            
        if(!autor.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s.-]+$")) {
            throw new IllegalArgumentException("El nombre no puede contener caracteres especiales");
        }
        
        this.autor = autor;
        
        this.categoria = categoria;
        this.disponibilidad = disponibilidad;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        if(titulo.length() < 2 || titulo.length() > 100) {
            throw new IllegalArgumentException("El titulo es muy corto o largo.");
        }
        this.titulo = titulo;
    }
    
    public String getAutor() {
        return autor;
    }
    
    public void setAutor(String autor) {
        if(!autor.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s.-]+$")) {
            throw new IllegalArgumentException("El nombre no puede contener caracteres especiales");
        }
        
        this.autor = autor;
    }
    
    public String getCategoria() {
        return categoria;
    }
    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public boolean getDisponibilidad() {
        return disponibilidad;
    }
    
    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
}
