<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Tienda Online</title>
        <c:set var="contexto" value="${pageContext.request.contextPath}" scope="application"/>
        <c:set var="estiloError" value="${contexto}/CSS/error.css" scope="application"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="CSS/estiloIndex.css"/>

        <style>
            * {
                padding: 0;
                margin: 0;
            }
            .modal {
                position: fixed;
                top: -100%;
                left: 0;
                right: 0;
                bottom: 0;
                background-color: #111111bd;
                display: flex;
                opacity: 0;
                pointer-events: none;
                transition: top 0.5s ease;
            }
            .modal--show {
                opacity: 1;
                pointer-events: unset;
                top: 0;
            }
            .modal_container {
                margin: auto;
                width: 90%;
                max-width: 600px;
                max-height: 90%;
                border-radius: 6px;
                padding: 3em 2.5em;
                display: grid;
                gap: 1em;
                place-items: center;
                grid-auto-columns: 100%;
                background-color: #f5f5f5;
            }
            .modal_title {
                font-size: 2.5rem;
            }
            .modal_parrafo {
                margin-bottom: 10px;
            }
            .modal_img {
                width: 90%;
                max-width: 300px;
            }
            .modal_close {
                text-decoration: none;
                color: #ffff;
                background-color: rgb(91, 91, 255);
                padding: 1em 3em;
                border: 1px solid #fff;
                border-radius: 6px;
                display: inline-block;
                font-weight: 300;
                transition: background-color .3s;
            }

            .modal_close:hover {
                color: rgb(91, 91, 255);
                background-color: #ffff;
            }
        </style>
    </head>

    <body>
        <header>
            <nav class="navbar navbar-expand-md navbar-dark bg-primary justify-content-center">
                <div class="container-fluid">
                    
                    <a class="navbar-brand" >
                        <img src="IMG/logo_tienda_navidad.jpeg" id="logo" class="img-fluid m-2" alt="logo-app">
                    </a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                            data-bs-target="#collapsibleNavId" aria-controls="collapsibleNavId" aria-expanded="false"
                            aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="collapsibleNavId">
                        <ul class="navbar-nav me-auto mb-2 mb-md-0">

                        </ul>

                        <div class="d-flex align-items-center">
                            <button class="log btn btn-outline-light ms-3" type="button">Login</button>
                            <form action="<%=request.getContextPath()%>/FrontController" method="post">
                                <button class="btn btn-outline-light ms-3" name="boton" value="Registrate" type="submit">Registrate</button>
                            </form>
                            <div class="d-flex align-items-center">

                                <a href="${contexto}/JSP/CARRITO/carrito.jsp"><button  class="btn btn-outline-light ms-3" type="button"><i  class="fa-solid fa-cart-shopping"></i></button></a>
                                <div id="cantCarr" class="ml-2 text-white border border-white rounded-circle pl-1 pr-1">0</div>
                            </div>

                        </div>
                    </div>
                </div>
            </nav>

        </header>
        <section class="modal">
            <div class="modal_container">
                <img src="IMG/modal.svg" class="modal_img">
                <h2 class="modal_title">Iniciar sesión</h2>
                <form action="<%=request.getContextPath()%>/FrontController" method="post">
                    <div class="form-group">
                        <label for="email">Correo electrónico:</label>
                        <input type="email" class="form-control" id="email" name="email" placeholder="Introduce tu correo" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Contraseña:</label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="Introduce tu contraseña" required>
                    </div>
                    <button type="submit" name="boton" value="" class="modal_closebtn btn-primary">Acceder</button>
                </form>

            </div>
        </section>

        <main class="d-flex flex-wrap justify-content-evenly " id="main">

            <c:forEach var="categoria" items="${listaCate}">
                <div class="card col-lg-3 col-md-5 col-12 cat" id="${categoria.idCategoria}">
                    <img src="IMG/Imagenes/categorias/${categoria.imagen}">
                    <div>
                        <h2>${categoria.nombre}</h2>
                    </div>
                </div>
            </c:forEach>
        </main>

        <footer class="footer text-center">
            <p style="margin-bottom: 0px;"> &#169; Juan Francisco Gonzalez Cerrato 2023</p>
        </footer>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
        <script src="${contexto}/JS/main.js" defer></script>
        <script src="${contexto}/JS/ajax.js" defer></script>

    </body>

</html>