package prestamo;

import java.time.LocalDate;
import libro.GestionLibros;
import libro.Libro;
import usuario.GestionUsuarios;
import usuario.Usuario;

public class Prestamo {
    private Libro libro;
    private Usuario usuario;
    private String descripcionEntrega;
    private String descripcionDevolucion;
    private LocalDate fechaDevolucionLimite;
    private LocalDate fechaDevolucionReal;
    private LocalDate fechaPrestamo;
    
    public Prestamo(Libro libro, Usuario usuario, String descripcion) {
        /**
         * Verificamos antes que el libro no se encuentre prestado.
         */
        if(libro.getDisponibilidad() == false)
            throw new IllegalArgumentException("El libro ya fue prestado.");
        
        // incrementamos la cantidad de prestamos activos
        usuario.incrementarPrestamo(); 
        
        libro.setDisponibilidad(false);
        
        this.libro = libro;
        this.usuario = usuario;
        this.fechaPrestamo = LocalDate.now();
        this.fechaDevolucionLimite = fechaPrestamo.plusDays(15);
        this.descripcionEntrega = descripcion;
        this.descripcionDevolucion = "Sin descripcion";
        this.fechaDevolucionReal = null;  
        
        GestionLibros.guardarLibros();
        GestionUsuarios.guardarUsuarios();
    }
    
    public Prestamo(Libro libro, Usuario usuario, String descripcion,
            String descripcionDevolucion,
            LocalDate fechaLimite, LocalDate fechaDevolucionReal, LocalDate fechaPrestamo) {
        
        this.libro = libro;
        this.usuario = usuario;
        this.descripcionEntrega = descripcion;
        this.descripcionDevolucion = descripcionDevolucion;
        this.fechaDevolucionLimite = fechaLimite;
        this.fechaDevolucionReal = fechaDevolucionReal;
        this.fechaPrestamo = fechaPrestamo;
    }
    
    public Libro getLibro() {
        return libro;
    }
    
    public void setLibro(Libro libro) {
        this.libro = libro;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String getDescripcionEntrega() {
        return descripcionEntrega;
    }
    
    public void setDesripcionEntrega(String descripcion) {
        this.descripcionEntrega = descripcion;
    }
    
    public String getDescripcionDevolucion() {
        return descripcionDevolucion;
    }
    
    public void setDescripcionDevolucion(String descripcion) {
        this.descripcionDevolucion = descripcion;
    }
    
    public LocalDate getFechaDevolucionLimite() {
        return fechaDevolucionLimite;
    }
    
    public void setFechaDevolucionLimite(LocalDate fechaDevolucionLimite) {
        this.fechaDevolucionLimite = fechaDevolucionLimite;
    }
    
    public LocalDate getFechaDevolucionReal() {
        return fechaDevolucionReal;
    }
    
    public void setFechaDevolucionReal(LocalDate fechaDevolucionReal) {
        this.fechaDevolucionReal = fechaDevolucionReal;
    }
    
    public void registrarDevolucion(LocalDate fechaDevolucionReal, String descripcionDevolucion) {
        if(this.fechaDevolucionReal != null)
           throw new IllegalStateException("Ya se registro la devolucion");
        
        if(fechaDevolucionReal.isBefore(fechaPrestamo)) {
            throw new IllegalArgumentException("La fecha no puede ser menor a la fecha de prestamo.");
        }
        this.libro.setDisponibilidad(true);
        this.fechaDevolucionReal = fechaDevolucionReal;
        this.descripcionDevolucion = descripcionDevolucion;
        usuario.decrementarPrestamo();
        
        GestionLibros.guardarLibros();
        GestionUsuarios.guardarUsuarios();
    }
    
    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }
    
    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }
    
    @Override
    public String toString() {
        String texto = 
        "Nombre del libro: %s%nNombre del prestatario: %s%nFecha de prestamo: %s%nFecha Limite: %s%nFecha Devolucion: %s%nDescripcion de Entrega: %s%nDescripcion de Devolucion: %s%n";
        
        return String.format(
                texto,
                getLibro().getTitulo(), getUsuario().getNombre(),
                 getFechaPrestamo().toString(),
                 getFechaDevolucionLimite().toString(),
                 getFechaDevolucionReal() == null ? "Sin fecha":
                 getFechaDevolucionReal().toString(),
                 getDescripcionEntrega() == null ? "Sin descripcion": getDescripcionEntrega(),
                 getDescripcionDevolucion() == null ? "Sin descripcion": getDescripcionDevolucion());
    }
    
    public String toCsv() {
        return String.join(";", libro.getTitulo(), 
                usuario.getDni(), 
                descripcionEntrega,
                descripcionDevolucion,
                fechaDevolucionLimite.toString(),
                getFechaDevolucionReal() == null ? "null": fechaDevolucionReal.toString(),
                fechaPrestamo.toString());
    }
}
