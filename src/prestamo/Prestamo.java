package prestamo;

import java.time.LocalDate;
import libro.Libro;
import usuario.Usuario;

public class Prestamo {
    private Libro libro;
    private Usuario usuario;
    private String descripcion;
    private LocalDate fechaDevolucionLimite;
    private LocalDate fechaDevolucionReal;
    private LocalDate fechaPrestamo;
    
    public Prestamo(Libro libro, Usuario usuario) {
        usuario.incrementarPrestamo();
        
        this.libro = libro;
        this.usuario = usuario;
        fechaPrestamo = LocalDate.now();
        fechaDevolucionLimite = fechaPrestamo.plusDays(15);
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
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDesripcion(String descripcion) {
        this.descripcion = descripcion;
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
    
    public void registrarDevolucion(LocalDate fechaDevolucionReal, String descripcion) {
        if(this.fechaDevolucionReal != null)
           throw new IllegalStateException("Ya se registro la devolucion");
        
        if(fechaDevolucionReal.isBefore(fechaPrestamo)) {
            throw new IllegalArgumentException("La fecha no puede ser menor a la fecha de prestamo.");
        }
        this.fechaDevolucionReal = fechaDevolucionReal;
        this.descripcion = descripcion;
        usuario.decrementarPrestamo();
    }
    
    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }
    
    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }
    
    @Override
    public String toString() {
        
        return String.format(
                "Nombre del libro: %s%nNombre del prestatario: %s%nFecha de prestamo: %s%nFecha Limite: %s%nFecha Devolucion: %s%nDescripcion: %s%n",
                getLibro().getTitulo(), getUsuario().getNombre(),
                 getFechaPrestamo().toString(),
                 getFechaDevolucionLimite().toString(),
                 getFechaDevolucionReal() == LocalDate.MIN ? "Sin fecha":
                 getFechaDevolucionReal().toString(),
                 getDescripcion() == null ? "Sin descripcion": getDescripcion());
    }
    
    
}
