package org.dionizdev.stream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;

/**
 * @author Eduardo Jesús Díaz Dioniz
 * @version 1.0.1
 * @param <T> Indica el tipo de dato del indice del HashMap
 * @param <K> Indica el tipo de dato de la clave del HashMap
 */
public class ObjectMap<T,K> {
    
    private HashMap<T,K> map;
   
    /**
     * Constructor por defecto , genera el HashMap interno vacio.
     */
    public ObjectMap(){
        map = new HashMap<>();
    }
    /**
     * Añade un nuevo objeto al HashMap.
     * @param t Indica el tipo de dato del indice del HashMap
     * @param k Indica el tipo de dato de la clave del HashMap
     */
    public void add(T t, K k){
        map.put(t, k);
    }
    /**
     * Añade un nuevo objeto al HashMap.
     * @param t Indica el tipo de dato del indice del HashMap
     * @return Retorna el objeto almacenado con la clave pasada por parametro.
     */
    public K get(T t){
        return map.get(t);
    }
    /**
     * Carga los objetos al HashMap desde el flujo pasado por parametro.
     * @param os Flujo de salida de donde obtener los objetos a almacenar.
     */
    public void save(OutputStream os){
        try{
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(map);
            oos.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    /**
    * Carga los objetos desde un flujo de entrada, rellenando el HashMap
    * @param is  Flujo de entrada de donde obtener los objetos a cargar.
    */
    public void load(InputStream is){
        try{
            Object object;
            ObjectInputStream ois = new ObjectInputStream(is);
            object = ois.readObject();
            ois.close();
            
            if(object.getClass() == map.getClass()){
                map = (HashMap<T, K>) object;
            }else{
               throw new ClassCastException("Can't load object: "+object.getClass()+" not is a "+map.getClass()); 
            }
        }catch(IOException ioe){
            ioe.printStackTrace();
        }catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
