package uc.software.pasareladepagos.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import uc.software.pasareladepagos.Persistencia.Entidades.Pago;
import uc.software.pasareladepagos.Persistencia.Entidades.Tarjeta;
import uc.software.pasareladepagos.Servicios.PagoServicio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String procesarPago(@ModelAttribute Tarjeta tarjeta, @RequestParam Double monto, Model model) {
        Pago pago = pagoServicio.procesarPago(tarjeta, monto);
        model.addAttribute("pago", pago);
        return "resultado";
    }
}