package es.albarregas.beans;

import java.util.Date;

public class Usuario {

    int IdUsuario;
    String Email;
    String Password;
    String Nombre;
    String Apellidos;
    String NIF;
    String Telefono;
    String Direccion;
    String CodigoPostal;
    String Localidad;
    String Provincia;
    Date UltimoAcceso;
    //String Avatar;
    

    public Usuario() {
    }

    public Usuario(int IdUsuario, String Email, String Password, String Nombre, String Apellidos, String NIF, String Telefono, String Direccion, String CodigoPostal, String Localidad, String Provincia, Date UltimoAcceso) {
        this.IdUsuario = IdUsuario;
        this.Email = Email;
        this.Password = Password;
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.NIF = NIF;
        this.Telefono = Telefono;
        this.Direccion = Direccion;
        this.CodigoPostal = CodigoPostal;
        this.Localidad = Localidad;
        this.Provincia = Provincia;
        this.UltimoAcceso = UltimoAcceso;
       // this.Avatar = Avatar;
    }

    public String getProvincia() {
        return Provincia;
    }

    public void setProvincia(String Provincia) {
        this.Provincia = Provincia;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getCodigoPostal() {
        return CodigoPostal;
    }

    public void setCodigoPostal(String CodigoPostal) {
        this.CodigoPostal = CodigoPostal;
    }

    public String getLocalidad() {
        return Localidad;
    }

    public void setLocalidad(String Localidad) {
        this.Localidad = Localidad;
    }

    public Date getUltimoAcceso() {
        return UltimoAcceso;
    }

    public void setUltimoAcceso(Date UltimoAcceso) {
        this.UltimoAcceso = UltimoAcceso;
    }

  

}
