package usuario;

import java.util.Random;

public class Usuario {
    private String nombre;
    private String dni;
    private String telefono;
    private String direccion;
    private int limitePrestamos;
    private int prestamosActivos;
    
    private Random rnd = new Random();
    
    public Usuario(String nombre, String dni, String telefono, String direccion,
            int limitePrestamos, int prestamosActivos) {
        this(nombre, dni, telefono, direccion);
        
        this.limitePrestamos = limitePrestamos;
        this.prestamosActivos = prestamosActivos;
    }
    
    public Usuario(String nombre, String dni, String telefono, String direccion) {
        this.nombre = nombre;
        if(dni.length() > 13 || dni.length() < 13 || !(dni.matches("[0-9]+"))) {
            throw new IllegalArgumentException("Dni incorrecto.");
        }
        this.dni = dni;
        
        if(!telefono.matches("[0-9]+")) {
            throw new IllegalArgumentException("Telefono Incorrecto");
        }
        this.telefono = telefono;
        
        this.direccion = direccion;
        
        limitePrestamos = rnd.nextInt(5, 11);
        
        prestamosActivos = 0;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDni() {
        return dni;
    }
    
    public void setDni(String dni) {
        if(dni.length() > 13 || dni.length() < 13 || !(dni.matches("[0-9]+"))) {
            throw new IllegalArgumentException("Dni incorrecto.");
        }
        this.dni = dni;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public int getLimitePrestamos() {
        return limitePrestamos;
    }
    
    public void setLimitePrestamos(int limitePrestamos) {
        this.limitePrestamos = limitePrestamos;
    }
    
    public int getPrestamosActivos() {
        return prestamosActivos;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String telefono) {
        if(!telefono.matches("[0-9]+")) {
            throw new IllegalArgumentException("Telefono Incorrecto");
        }
        this.telefono = telefono;
    }
    
    public void incrementarPrestamo() {
        if(prestamosActivos == limitePrestamos)
            throw new IllegalStateException("Se alcanzo el limite de prestamos");
        prestamosActivos += 1;
    }
    
    public void decrementarPrestamo() {
        if(prestamosActivos == 0)
           throw new IllegalStateException("No hay prestamos que devolver");
        prestamosActivos -= 1;
    }
    
    public String toCsv() {
        return String.join(";", nombre, dni, telefono, direccion,
                String.valueOf(limitePrestamos), String.valueOf(prestamosActivos));
    }
}
