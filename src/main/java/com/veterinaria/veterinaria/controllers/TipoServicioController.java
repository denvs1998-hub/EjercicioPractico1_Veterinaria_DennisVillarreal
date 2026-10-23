/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package com.veterinaria.veterinaria.controllers;

import com.veterinaria.veterinaria.domain.TipoServicio;
import com.veterinaria.veterinaria.service.TipoServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TipoServicioController {

    @Autowired
    private TipoServicioService tipoServicioService;

    @GetMapping("/tipoServicio/listado")
    public String listado(Model model) {
        var tipos = tipoServicioService.listarTipos();
        model.addAttribute("tipos", tipos);
        return "tipoServicio/listado";
    }

    @GetMapping("/tipoServicio/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("tipoServicio", new TipoServicio());
        return "tipoServicio/modifica";
    }

    @GetMapping("/tipoServicio/modificar/{id}")
    public String modificar(@PathVariable Integer id, Model model) {
        var tipo = tipoServicioService.buscarTipo(id);
        model.addAttribute("tipoServicio", tipo);
        return "tipoServicio/modifica";
    }

    @PostMapping("/tipoServicio/guardar")
    public String guardar(TipoServicio tipoServicio,
                          RedirectAttributes redirectAttributes) {

        boolean esNuevo = tipoServicio.getId() == null;

        tipoServicioService.guardarTipo(tipoServicio);

        if (esNuevo) {
            redirectAttributes.addFlashAttribute(
                    "mensaje", "Tipo de servicio guardado correctamente.");
        } else {
            redirectAttributes.addFlashAttribute(
                    "mensaje", "Tipo de servicio modificado correctamente.");
        }

        return "redirect:/tipoServicio/listado";
    }

    @GetMapping("/tipoServicio/eliminar/{id}")
    public String eliminar(@PathVariable Integer id,
                           RedirectAttributes redirectAttributes) {

        tipoServicioService.eliminarTipo(id);

        redirectAttributes.addFlashAttribute(
                "mensaje", "Tipo de servicio eliminado correctamente.");

        return "redirect:/tipoServicio/listado";
    }
}

