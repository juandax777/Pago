package uc.software.pasareladepagos.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uc.software.pasareladepagos.Persistencia.Entidades.Pago;
import uc.software.pasareladepagos.Persistencia.Entidades.PagoRequest;
import uc.software.pasareladepagos.Persistencia.Entidades.Tarjeta;
import uc.software.pasareladepagos.Servicios.PagoServicio;

@Controller
public class PagoControlador {

    @Autowired
    private PagoServicio pagoServicio;


    @GetMapping("/")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tarjeta", new Tarjeta());
        return "formulario";
    }


    @PostMapping("/procesarPago")
    public String procesarFormulario(
            @ModelAttribute Tarjeta tarjeta,
            @RequestParam Double monto,
            Model model
    ) {
        Pago pago = pagoServicio.procesarPago(tarjeta, monto);
        model.addAttribute("pago", pago);
        return "resultado";
    }


    @PostMapping("/api/pasarela/procesarPago")
    @ResponseBody
    public ResponseEntity<Pago> procesarPagoApi(@RequestBody PagoRequest request) {
        Tarjeta tarjeta = request.getTarjeta();
        Double monto = request.getMonto();
        Pago pago = pagoServicio.procesarPago(tarjeta, monto);

        return ResponseEntity.ok(pago);
    }
}
