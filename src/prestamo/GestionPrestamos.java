/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prestamo;

import auth.Auth;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import libro.GestionLibros;
import libro.Libro;
import usuario.GestionUsuarios;
import usuario.Usuario;

/**
 *
 * @author joeld
 */
public class GestionPrestamos {
    
    public static ArrayList<Prestamo> prestamos = new ArrayList<>();
    
    
    public static void buscarPrestamos() {
        ArrayList<Prestamo> prestamosEncontrado = new ArrayList<>();
        
        for(int i = 0; i < prestamos.size(); i++) {
            if(prestamos.get(i).getUsuario().getDni().toLowerCase().contains(
                    Auth.usuarioLogueado.getDni().toLowerCase())) {
                prestamosEncontrado.add(prestamos.get(i));
            }
        }
        
        imprimirPrestamosUsuario(prestamosEncontrado);
    }
    
    public static ArrayList<Prestamo> obtenerPrestamosActivos(ArrayList<Prestamo> prestamos) {
        
        ArrayList<Prestamo> prestamosActivos = new ArrayList<>();
        
        for (int i = 0; i < prestamos.size(); i++) {
            if(prestamos.get(i).getFechaDevolucionReal() == null &&
                    prestamos.get(i).getUsuario().equals(Auth.usuarioLogueado)) {
                
                prestamosActivos.add(prestamos.get(i));
            }
        }
        
        return prestamosActivos;
    }
    
    
    public static void imprimirPrestamosUsuario(ArrayList<Prestamo> prestamos) {
        
        if(prestamos.size() == 0)
            System.out.println("No tienes prestamos activos");
        else {
            System.out.printf("%s | %s | %s | %s | %s | %s | %s%n",
                    "Nombre", "Titulo", "Descripcion de Entrega","Descripcion de Devolucion",
                    "Fecha limite",
                    "Fecha de Devolucion", "Fecha de prestamo");

            for (int i = 0; i < prestamos.size(); i++) {
                System.out.printf("%d. %s | %s | %s | %s | %s | %s | %s%n",
                        i + 1,
                    prestamos.get(i).getUsuario().getNombre(),
                    prestamos.get(i).getLibro().getTitulo(),
                    prestamos.get(i).getDescripcionEntrega(),
                    prestamos.get(i).getDescripcionDevolucion(),
                    prestamos.get(i).getFechaDevolucionLimite().toString(),
                    prestamos.get(i).getFechaDevolucionReal() == null ? "Sin fecha":
                            prestamos.get(i).getFechaDevolucionReal().toString(),
                    prestamos.get(i).getFechaPrestamo().toString());
            }
        }
    }
    
    public static void imprimirPrestamosUsuariosActivos() {
        
        ArrayList<Prestamo> prestamosActivos  = obtenerPrestamosActivos(prestamos);
        
         if(prestamosActivos.size() == 0)
            throw new IllegalArgumentException("No tienes prestamos activos");
        else {
            System.out.printf("%s | %s | %s | %s | %s | %s | %s%n",
                    "Nombre", "Titulo", "Descripcion de Entrega","Descripcion de Devolucion",
                    "Fecha limite",
                    "Fecha de Devolucion", "Fecha de prestamo");
            for (int i = 0; i < prestamosActivos.size(); i++) {
                    System.out.printf("%d. %s | %s | %s | %s | %s | %s | %s%n",
                            i + 1,
                        prestamosActivos.get(i).getUsuario().getNombre(),
                        prestamosActivos.get(i).getLibro().getTitulo(),
                        prestamosActivos.get(i).getDescripcionEntrega(),
                        prestamosActivos.get(i).getDescripcionDevolucion(),
                        prestamosActivos.get(i).getFechaDevolucionLimite().toString(),
                        prestamosActivos.get(i).getFechaDevolucionReal() == null ? "Sin fecha":
                                prestamosActivos.get(i).getFechaDevolucionReal().toString(),
                        prestamosActivos.get(i).getFechaPrestamo().toString());
            }
        }
    }
    
    public static void imprimirPrestamos() {
        System.out.printf("%s | %s | %s | %s | %s | %s%n",
                "Nombre", "Titulo", "Descripcion", "Fecha limite",
                "Fecha de Devolucion", "Fecha de prestamo");
        
        for(int i = 0; i < prestamos.size(); i++) {
            System.out.printf("%s | %s | %s | %s | %s | %s | %s%n",
                prestamos.get(i).getUsuario().getNombre(),
                prestamos.get(i).getLibro().getTitulo(),
                prestamos.get(i).getDescripcionEntrega(),
                prestamos.get(i).getDescripcionDevolucion(),
                prestamos.get(i).getFechaDevolucionLimite().toString(),
                prestamos.get(i).getFechaDevolucionReal().toString(),
                prestamos.get(i).getFechaPrestamo().toString());
        }
    }
    
    public static void hacerPrestamo(Scanner entrada) {
        Libro libroEncontrado = null;
        
        
        GestionLibros.imprimirLibros();
        
        System.out.print("Que libro deseas: ");
        String libro = entrada.nextLine();
        
        libroEncontrado = GestionLibros.buscar(libro);
        
        System.out.print("Una descripcion de la entrega del libro: ");
        String descripcion = entrada.nextLine();
        
        if(libroEncontrado != null) {
            prestamos.add(new Prestamo(libroEncontrado, Auth.usuarioLogueado, descripcion));
            System.out.println("Prestamo realizado correctamente!");
        } else {
            System.out.println("No se encontro el libro o no esta disponible.");
        }
    } // fin del metodo hacerPrestamo
    
    public static void guardarPrestamos() {
        try(FileWriter fw = new FileWriter("prestamos.csv")) {
            fw.write(
                    "libro;usuario;Descripcion;Fecha Limite; Fecha de Devolucion;Fecha de Prestamo\n");
            
            for(Prestamo p: prestamos) {
                fw.write(p.toCsv() + "\n");
            }
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    } // fin del metodo guardarPrestamos
    
    public static void leerPrestamos() {
        String line;
        
        try(BufferedReader br = new BufferedReader(new FileReader("prestamos.csv"))) {
            
            line = br.readLine();
            
            while((line = br.readLine()) != null) {
                String[] valores = line.split(";");
                
                Libro libro = GestionLibros.buscar(valores[0]);
                Usuario usuario = GestionUsuarios.buscar(valores[1]);
                String descripcionEntrega = valores[2];
                String descripcionDevolucion = valores[3];
                LocalDate fecha1 = parseSeguro(valores[4]);
                LocalDate fecha2 = parseSeguro(valores[5]);
                LocalDate fecha3 = (valores.length > 6) ? 
                        parseSeguro(valores[6]) : null;
                
                prestamos.add(new Prestamo(libro, usuario, descripcionEntrega,
                            descripcionDevolucion, fecha1, fecha2, fecha3));
            }
        }catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static LocalDate parseSeguro(String fecha) {
        if(fecha == null) {
            return null;
        }
        
        // limpiamos el string de espacios que puedan molestar
        String fechaLimpia = fecha.trim();
        
        if(fechaLimpia.isEmpty() || fechaLimpia.equalsIgnoreCase("null")) {
            return null;
        }
        
        return LocalDate.parse(fechaLimpia);
    }
    
    public static void devolucion(Scanner entrada) {
        
        ArrayList<Prestamo> prestamosActivos = obtenerPrestamosActivos(prestamos);
        imprimirPrestamosUsuariosActivos();
        
        
        
        System.out.print("Ingresa el numero del prestamo que deseas devolver: ");
        int indicePrestamo = entrada.nextInt();
        entrada.nextLine();
       
        if(indicePrestamo > prestamosActivos.size() || indicePrestamo <= 0) {
            System.out.println("Opcion Invalida.");
        } else {
            System.out.println("Ingresa una descripcion del estado del libro en el que se devolvio:");
            String descripcion = entrada.nextLine();
            prestamosActivos.get(indicePrestamo - 1).registrarDevolucion(LocalDate.now(), descripcion);
        }
    }
}
