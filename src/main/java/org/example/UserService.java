package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.example.enums.ROL;

/**
 *
 * @author Eloy
 */
public class UserService {
    private static UserService instancia;
    private final Map<String, Usuario> credenciales;
    
    public static UserService userServiceGetInstance(){
        if(instancia==null){
            instancia = new UserService();
        }
        return instancia;
    }
    
    private UserService() {
        credenciales = new HashMap<>();
    }
    
    public Usuario login(String nombre, String password) {
        String storedPassword=null;
        if(credenciales.get(nombre)!=null){
            storedPassword = credenciales.get(nombre).getContraseña();  
            if(password.equals(storedPassword)){
              return credenciales.get(nombre);
            }
        }
        
        return null;
    }
    
    public void addUser(Usuario user){
        if(getUser(user.getNombre())==null){
            credenciales.put(user.getNombre(), user);
        }
        //throw error si ya existe el usuario
    }
    
    public void deleteUser(Usuario user){
        credenciales.remove(user.getNombre(),user);
    }
    
    public void cargaUsers(ArrayList<Usuario>usuarios){
        for(int i=0;i<usuarios.size();i++){
            addUser(usuarios.get(i));
        }
    }
    
    public Usuario getUser(String nombre){
        return credenciales.get(nombre);
    }
    
    public boolean isAdmin(Usuario user){
        return user.getRol() == ROL.GERENTE || user.getRol() == ROL.SUPERVISOR;
    }
}
