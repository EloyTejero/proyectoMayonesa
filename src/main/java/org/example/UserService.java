package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.example.enums.ROL;

/**
 *
 * @author Eloy
 */
public class UserService {
    private static UserService instancia;
    private final Map<Integer, Usuario> credenciales;
    
    public static UserService userServiceGetInstance(){
        if(instancia==null){
            instancia = new UserService();
        }
        return instancia;
    }
    
    private UserService() {
        credenciales = new HashMap<>();
    }
    
    public Usuario login(Integer dni, String password) {
        String storedPassword=null;
        if(credenciales.get(dni)!=null){
            storedPassword = credenciales.get(dni).getContraseña();  
            if(password.equals(storedPassword)){
              return credenciales.get(dni);
            }
        }
        
        return null;
    }
    
    public void addUser(Usuario user){
        if(getUserByDni(user.getDni())==null){
            credenciales.put(user.getDni(), user);
        }
        //throw error si ya existe el usuario
    }
    
    public void deleteUser(Usuario user){
        credenciales.remove(user.getDni(),user);
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
    
    public Usuario getUserByDni(int dni){
        return credenciales.get(dni);
    }
    
    public Collection<Usuario> getUsers(){
        return credenciales.values();
    }
}
