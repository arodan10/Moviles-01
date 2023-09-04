package pe.edu.upeu.asistencia.dtos;




public record UsuarioCrearDto (String nombres, String apellidos, String correo, char[] password, String token) { }
