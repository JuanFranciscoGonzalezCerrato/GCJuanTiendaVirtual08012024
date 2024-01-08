<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registro de Usuario</title>
        <script src="<%= request.getContextPath()%>/JS/registro.js" defer></script>
        <link rel="stylesheet" href="${contexto}/CSS/registroCSS.css">

    </head>


    <body>
        <div>
            <h1>Registro de Usuario</h1>
            <form action="<%=request.getContextPath()%>/InsertarUsuario" method="post" >


                <div>           
                    <label for="nombre">Nombre de usuario:</label>
                    <input type="text" id="nombre" name="nombre">

                    <label for="apellidos">Apellidos de usuario:</label>
                    <input type="text" id="apellidos" name="apellidos">

                    <label for="telefono">N&uacute;mero de teléfono:</label>
                    <input type="tel" id="telefono" name="telefono">

                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password">

                    <label for="password-confirm">Confirmar password:</label>
                    <input type="password" id="password-confirm" name="password-confirm">
                </div>
                <div>
                    <label for="email">Correo electrónico:</label>
                    <input type="email" name="email" id="email">


                    <label for="nif">NIF:</label>
                    <input type="text" id="nif" name="nif">

                    <label for="direccion">Direccion:</label>
                    <input type="text" id="direccion" name="direccion">

                    <label for="cp">Codigo Postal:</label>
                    <input type="text" id="cp" name="cp">

                    <label for="localidad">Localidad</label>
                    <input type="text" id="localidad" name="localidad">

                    <label for="provincia">Provincia</label>
                    <input type="text" id="provincia" name="provincia">
                </div>



                <input type="checkbox" id="condiciones" name="condiciones" required>
                <label for="condiciones">Acepto las condiciones de uso</label>

                <button type="submit" id="registrar" disabled>Registrar</button>
            </form>
        </div>


    </body>

</html>