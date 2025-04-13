package entidades;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    private int codigoCliente;
    private String nombreCliente;
    private String nombreContacto;
    private String apellidoContacto;
    private String telefono;
    private String fax;
    private String lineaDireccion1;
    private String lineaDireccion2;
    private String ciudad;
    private String region;
    private String pais;
    private String codigoPostal;
    private Integer repVentas; // Puede ser nulo
    private Float limiteCredito; // Manejo de decimales para valores monetarios

    public String toString() {
        return codigoCliente + " - " + nombreCliente;
    }
}
