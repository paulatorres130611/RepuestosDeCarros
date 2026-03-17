package co.ucentral.RepuestosCarros.RepuestosCarros.controladores;

import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.Venta;
import co.ucentral.RepuestosCarros.RepuestosCarros.servicios.VentaServicio;
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
public class HistorialVentaControlador {

    private final VentaServicio ventaServicio;

    @GetMapping("/ventas/historial")
    public String mostrarHistorialVentas(
            @RequestParam(value = "desde", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,

            @RequestParam(value = "hasta", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta,

            @RequestParam(value = "q", required = false) String q,

            Model model) {

        List<Venta> ventas = ventaServicio.filtrarHistorialVentas(desde, hasta, q);
        BigDecimal totalVentas = ventaServicio.calcularTotalHistorialVentas(ventas);

        model.addAttribute("ventas", ventas);
        model.addAttribute("totalVentas", totalVentas);
        model.addAttribute("desde", desde);
        model.addAttribute("hasta", hasta);
        model.addAttribute("q", q);

        return "HistorialVentas";
    }
}