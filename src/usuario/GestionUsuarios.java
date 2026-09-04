package usuario;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GestionUsuarios {
    public static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    
    public static Usuario buscar(String dni) {
        Usuario usuarioEncontrado = null;
        
        for(int i = 0; i < usuarios.size(); i++) {
            if(dni.equals(usuarios.get(i).getDni())) {
                usuarioEncontrado = usuarios.get(i);
                break;
            }
        }
        
        return usuarioEncontrado;
    }
    
    public static void guardarUsuarios() {
        try(FileWriter fw = new FileWriter("usuarios.csv")) {
            fw.write("nombre;dni;telefono;direccion;limitePrestamos;prestamosActivos\n");
            for(Usuario usuario: usuarios) {
                fw.write(usuario.toCsv() + "\n");
            }
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void leerUsuarios() {
        String line;
        
        try(BufferedReader br = new BufferedReader(new FileReader("usuarios.csv"))) {
            
            line = br.readLine();
            
            while((line = br.readLine()) != null) {
                String[] valores = line.split(";");
                
                usuarios.add(new Usuario(valores[0],valores[1], valores[2],
                valores[3], Integer.parseInt(valores[4]), Integer.parseInt(valores[5])));
            }
        }catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
