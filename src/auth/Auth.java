package auth;

import java.util.Scanner;
import usuario.GestionUsuarios;
import usuario.Usuario;

/**
 *
 * @author joeld
 */
public class Auth {
    
    public static Usuario usuarioLogueado = null;
    
    public static void iniciarSesion(Scanner entrada) {
        
        System.out.print("Ingresa tu dni: ");
        String dni = entrada.nextLine();
        
        usuarioLogueado = GestionUsuarios.buscar(dni);
        
        if(usuarioLogueado == null) {
            throw new IllegalArgumentException("Usuario no encontrado. Registrate");
        }
    }
    
    public static void registrar(Scanner entrada) {
        System.out.print("Ingresa tu nombre: ");
        String nombre = entrada.nextLine();
        
        System.out.print("Ingresa tu dni: ");
        String dni = entrada.nextLine();
        
        System.out.print("Ingresa tu numero de telefono(ej: 33872282): ");
        String telefono = entrada.nextLine();
        
        System.out.print("Ingresa tu direccion: ");
        String direccion = entrada.nextLine();
        
        GestionUsuarios.usuarios.add(
                new Usuario(nombre, dni, telefono, direccion));
    }
}
