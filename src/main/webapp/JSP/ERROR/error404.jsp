<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Error 404</title>
        <link rel="stylesheet" href="${estiloError}"/> 
    </head>
    <body style="display: flex; justify-content: center; align-items: center;">

        <div class="error">
            <a href="${contexto}">
                <img src="${contexto}/IMG/ERROR/404.jpeg"/>
            </a>
        </div>
    </body>
</html>
