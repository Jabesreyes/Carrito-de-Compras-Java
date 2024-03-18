<%@ page language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <body>
        <div class="container border">
            <div class="row">
                <div class="col-12 border">
                    <h2>carrito de compras</h2>
                    <hr>
                    <h6>INGRESO DE PRODUCTOS</h6>
                    <form method="post" action="Carrito?op=pc">
                        <div class="border" style="padding: 10px;">
                            <label for="nombre">Nombre del producto:</label>
                            <input required="true" class="form-control" name="nombre" id="nombre" placeholder="Nombre">
                            <label for="precio">Precio del producto:</label>
                            <input required="true" type="number" step="0.01" class="form-control" name="precio" id="precio" placeholder="Precio">
                            <label for="cantidad">Cantidad:</label>
                            <input required="true" class="form-control" type="number" name="cantidad" id="cantidad" placeholder="Cantidad">
                            <br/>
                            <div class="mx-auto" style="width: 200px;">
                                <input type="submit" name="agregar" class="btn btn-dark" value="AGREGAR"/>
                                <input type="reset" name="reset" class="btn btn-ligh" value="RESET"/>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-12 border">
                    <h6>ESTADO DEL CARRITO DE COMPRA</h6>
                    <table class="table border table-hover">
                        <thead class="thead-light">
                            <tr>
                                <th>Nombre del producto</th>
                                <th>Precio</th>
                                <th>Unidades</th>
                                <th>Precio producto</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${carrito}" var="c">
                                <tr>
                                    <td>${c.nombre}</td>
                                    <td>$<fmt:formatNumber type="number" maxFractionDigits="2" value="${c.precio}"/></td>
                                    <td>${c.cantidad}</td>
                                    <td>$<fmt:formatNumber type="number" maxFractionDigits="2" value="${c.total}"/></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <h6 class="float-right">Total: $ <fmt:formatNumber type="number" maxFractionDigits="2" value="${totalProductos}"/></h6>
                </div>
            </div>
        </div>
    </body>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>
