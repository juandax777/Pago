package uc.software.pasareladepagos.Persistencia.Repositorios;

import uc.software.pasareladepagos.Persistencia.Entidades.Pago;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class PagoRepositorio {
    private List<Pago> pagos = new ArrayList<>();
    private Long contador = 1L;

    public Pago guardar(Pago pago) {
        pago.setId(contador++);
        pagos.add(pago);
        return pago;
    }

    public List<Pago> listar() {
        return pagos;
    }
}