/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.ejb.Stateful;

@Stateful
public class CarritoBean implements CarritoBeanLocal, Serializable {

    Map<String, List<Carrito>> lisMap = new HashMap<>();

    //crea un carrito de compras si no existe
    @Override
    public void agregarItem(String idSesion, String producto, int cantidad, float precio) {
        //verificar si el usuario tiene carrito 
        if (lisMap.containsKey(idSesion)) {
            for (Entry<String, List<Carrito>> e : lisMap.entrySet()) {
                if (e.getKey().equals(idSesion)) {
                    List<Carrito> carc = e.getValue();
                    //buscar si existe el producto
                    boolean existe = false;
                    
                    for (Carrito c : carc) {
                        if (c.getNombre().equals(producto)) {
                            existe = true;
                            break;
                        } else {
                            existe = false;
                        }
                    }
                    //actualizar la cantidad del producto  si ya existe un objeto con ese nombre
                    if (existe) {
                        for (Carrito c : carc) {
                            {
                                if (c.getNombre().equals(producto)) {
                                    c.setCantidad(cantidad + c.getCantidad());
                                    break;
                                }
                            }
                        }
                    } else //añade un nuevo item al carrito de compras para el usuario en session
                    {
                        Carrito car = new Carrito();
                        car.setNombre(producto);
                        car.setPrecio(precio);
                        car.setCantidad(cantidad);
                        carc.add(car);
                    }
                }
            }
        } //si el usuario no tiene carrito creamos uno 
        else {
            List<Carrito> carritos = new ArrayList<>();
            Carrito car = new Carrito();
            car.setNombre(producto);
            car.setCantidad(cantidad);
            car.setPrecio(precio);
            carritos.add(car);
            lisMap.put(idSesion, carritos);
        }
    }

    // Obtener el carrito del usuario utilizando el id de sesión
    @Override
    public List<Carrito> obtenerCarrito(String idSesion) {
        List<Carrito> carItem = new ArrayList<>();
        for (Entry<String, List<Carrito>> e : lisMap.entrySet()) {
            if (e.getKey().equals(idSesion)) {
                carItem = e.getValue();
            }
        }
        return carItem;
    }

    //Retorna el total a pagar por usuario en sesion
    @Override
    public double totalProductos(String idSesion) {
        double total = 0;
        List<Carrito> carItem;
        for (Entry<String, List<Carrito>> e : lisMap.entrySet()) {
            if (e.getKey().equals(idSesion)) {
                carItem = e.getValue();
                for (Carrito c : carItem) {
                    total += c.getTotal();
                }
            }
        }
        return total;
    }
}
