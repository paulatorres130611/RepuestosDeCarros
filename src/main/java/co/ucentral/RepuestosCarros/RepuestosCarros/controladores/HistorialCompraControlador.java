package co.ucentral.RepuestosCarros.RepuestosCarros.controladores;

import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.Compra;
import co.ucentral.RepuestosCarros.RepuestosCarros.servicios.CompraServicio;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Controller
public class HistorialCompraControlador {

    private final CompraServicio compraServicio;

    @GetMapping("/compras/historial")
    public String mostrarHistorialCompras(
            @RequestParam(value = "desde", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,

            @RequestParam(value = "hasta", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta,

            @RequestParam(value = "q", required = false) String q,

            Model model) {

        List<Compra> compras = compraServicio.filtrarHistorialCompras(desde, hasta, q);
        BigDecimal totalCompras = compraServicio.calcularTotalHistorialCompras(compras);

        model.addAttribute("compras", compras);
        model.addAttribute("totalCompras", totalCompras);

        model.addAttribute("desde", desde);
        model.addAttribute("hasta", hasta);
        model.addAttribute("q", q);

        return "HistorialCompras";
    }
}