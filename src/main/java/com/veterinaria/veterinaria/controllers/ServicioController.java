/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.veterinaria.veterinaria.controllers;

import com.veterinaria.veterinaria.domain.Servicio;
import com.veterinaria.veterinaria.service.ServicioService;
import com.veterinaria.veterinaria.service.TipoServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @Autowired
    private TipoServicioService tipoServicioService;

    @GetMapping("/servicio/listado")
    public String listado(Model model) {
        var servicios = servicioService.listarServicios();
        model.addAttribute("servicios", servicios);
        return "servicio/listado";
    }

    @GetMapping("/servicio/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("servicio", new Servicio());
        model.addAttribute("tipos", tipoServicioService.listarTipos());
        return "servicio/modifica";
    }

    @GetMapping("/servicio/modificar/{id}")
    public String modificar(@PathVariable Integer id, Model model) {
        var servicio = servicioService.buscarServicio(id);
        model.addAttribute("servicio", servicio);
        model.addAttribute("tipos", tipoServicioService.listarTipos());
        return "servicio/modifica";
    }

    @PostMapping("/servicio/guardar")
    public String guardar(Servicio servicio,
                          RedirectAttributes redirectAttributes) {

        boolean esNuevo = servicio.getId() == null;

        servicioService.guardarServicio(servicio);

        if (esNuevo) {
            redirectAttributes.addFlashAttribute(
                    "mensaje", "Servicio guardado correctamente.");
        } else {
            redirectAttributes.addFlashAttribute(
                    "mensaje", "Servicio modificado correctamente.");
        }

        return "redirect:/servicio/listado";
    }

    @GetMapping("/servicio/eliminar/{id}")
    public String eliminar(@PathVariable Integer id,
                           RedirectAttributes redirectAttributes) {

        servicioService.eliminarServicio(id);

        redirectAttributes.addFlashAttribute(
                "mensaje", "Servicio eliminado correctamente.");

        return "redirect:/servicio/listado";
    }
}
