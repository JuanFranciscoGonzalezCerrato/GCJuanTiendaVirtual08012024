<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="es.albarregas.beans.Carrito" %>
<%@ page import="es.albarregas.beans.Producto" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Productos en el carrito</title>
        <link rel="stylesheet" href="${contexto}/CSS/carrito.css"/>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h1 class="mt-4 mb-4">Productos en el carrito</h1>

            <c:if test="${sessionScope.carrito != null}">
                <c:set var="carrito" value="${sessionScope.carrito}" scope="page" />
                <c:set var="agrupado" value="${carrito.productos.entrySet()}" />

                <table class="table table-bordered">
                    <thead class="table table-light">
                        <tr>
                            <th scope="col">Imagen</th>
                            <th scope="col">Nombre</th>
                            <th scope="col">Precio</th>
                            <th scope="col">Cantidad</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="entry" items="${agrupado}">
                            <c:set var="producto" value="${entry.key}" />
                            <c:set var="cantidad" value="${entry.value}" />
                            <tr>
                                <td><img src="${contexto}/IMG/Imagenes/productos/${producto.imagen}.jpg" alt="Imagen del producto"></td>
                                <td>${producto.nombre}</td>
                                <td>${producto.precio}</td>
                                <td>${cantidad}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <form action="${contexto}/BorrarCarrito" method="post">
                    <button type="submit" class="btn btn-danger">Borrar Carrito</button>
                </form>
            </c:if>

            <c:if test="${empty sessionScope.carrito}">
                <p>No hay productos en el carrito</p>
            </c:if>
                <form action="<%=request.getContextPath()%>/FrontController" method="post">
                    <button type="submit" name="volver" value="true" class="modal_closebtn btn-primary">Volver</button>
                </form>

        </div>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
