package uc.software.pasareladepagos.Persistencia.Entidades;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PagoRequest {
    private Tarjeta tarjeta;
    private Double monto;


}
