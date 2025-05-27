package uc.software.pasareladepagos.Servicios;

import org.springframework.stereotype.Service;
import uc.software.pasareladepagos.Persistencia.Entidades.Pago;
import uc.software.pasareladepagos.Persistencia.Entidades.Tarjeta;
import uc.software.pasareladepagos.Persistencia.Repositorios.PagoRepositorio;

@Service
public class PagoServicio {
    private final PagoRepositorio repo;

    public PagoServicio(PagoRepositorio repo) {
        this.repo = repo;
    }

    public Pago procesarPago(Tarjeta tarjeta, Double monto) {
        Pago pago = new Pago();
        pago.setTarjeta(tarjeta);
        pago.setMonto(monto);

        if (!validarNumeroTarjeta(tarjeta.getNumero())) {
            pago.setAprobado(false);
            pago.setMensaje("Número de tarjeta inválido.");
        } else if (!tarjeta.getMarca().equalsIgnoreCase("VISA") && !tarjeta.getMarca().equalsIgnoreCase("MASTERCARD")) {
            pago.setAprobado(false);
            pago.setMensaje("Solo se aceptan VISA o MASTERCARD.");
        } else if (!validarFecha(tarjeta.getFechaExpiracion())) {
            pago.setAprobado(false);
            pago.setMensaje("Fecha de expiración inválida.");
        } else if (!tarjeta.getCodigoSeguridad().matches("\\d{3,4}")) {
            pago.setAprobado(false);
            pago.setMensaje("Código de seguridad inválido.");
        } else {
            pago.setAprobado(true);
            pago.setMensaje("Pago aprobado.");
        }

        return repo.guardar(pago);
    }

    private boolean validarNumeroTarjeta(String numero) {
        return numero != null && numero.matches("\\d{16}");
    }

    private boolean validarFecha(String fecha) {
        // Formato MM/AA o MM/AAAA
        return fecha != null && fecha.matches("(0[1-9]|1[0-2])/\\d{2,4}");
    }
}