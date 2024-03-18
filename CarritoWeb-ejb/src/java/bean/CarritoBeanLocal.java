/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Fredy UDB
 */
@Local
public interface CarritoBeanLocal {

    public void agregarItem(String idSesion, String producto, int cantidad, float precio);

    public List<Carrito> obtenerCarrito(String idSesion);

    public double totalProductos(String idSesion);
}
