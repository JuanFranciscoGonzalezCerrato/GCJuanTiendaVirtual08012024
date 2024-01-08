$(document).ready(function () {
    $('.cat').on('click', function (e) {
        $.ajax({
            type: "post",
            url: "Ajax",
            data: {
                accion: "productos",
                cat: $(this).attr("id")
            },
            success: function (respuesta) {
                $("#main").html("");
                $("#main").append('<a style="height: 40px;" name="volver" id="btnVolver" class="btn btn-primary volver">Volver</a>');
                $.each(respuesta, function (i, resp) {
                    $("#main").append('<div class="card col-lg-3 col-md-5 col-12 prod" id="' + resp.idProducto +
                            '"><img src="IMG/Imagenes/productos/' + resp.imagen + '.jpg"><div><h2>' + resp.nombre +
                            '</h2></div><div><p style="color:black">' + resp.descripcion +
                            '</p></div> <button type="button" name="addCarrito" value="' + resp.idProducto +
                            '" class="btn-primary addProd">A&ntilde;adir</button></div>');
                });
                $('.volver').on('click', function (e){
                    window.location.reload();
                });
                $('.addProd').on('click', function (e) {
                    var idProducto = $(this).val();
                    $.ajax({
                        type: "post",
                        url: "Ajax",
                        data: {
                            accion: "addDelCarrito",
                            idProd: idProducto,
                            ope: true
                        },
                        success: function (respuesta) {
                            console.log(respuesta.cantTotal);
                            $("#cantCarr").html("");
                            $("#cantCarr").append(respuesta.cantTotal);
                        },
                        error: function () {
                            console.log("ERROR!!");
                        }
                    });
                });
                $('.addProd').on('click', function (e) {
                    var idProducto = $(this).val();
                    $.ajax({
                        type: "post",
                        url: "Ajax",
                        data: {
                            accion: "addDelCarrito",
                            idProd: idProducto,
                            ope: true
                        },
                        success: function (respuesta) {
                            console.log(respuesta.cantTotal);
                            $("#cantCarr").html("");
                            $("#cantCarr").append(respuesta.cantTotal);
                        },
                        error: function () {
                            console.log("ERROR!!");
                        }
                    });
                });

            },
            error: function () {
                console.log("ERROR!!");
            }
        });
    });

    $(document).on('click', '#btnVolver', function (e) {
        e.preventDefault();
        $("#main").html(""); 
    });
});
