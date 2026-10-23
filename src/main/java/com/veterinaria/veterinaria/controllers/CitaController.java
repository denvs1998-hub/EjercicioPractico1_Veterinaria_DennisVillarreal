/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package com.veterinaria.veterinaria.controllers;

import com.veterinaria.veterinaria.domain.Cita;
import com.veterinaria.veterinaria.service.CitaService;
import com.veterinaria.veterinaria.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CitaController {

    @Autowired
    private CitaService citaService;

    @Autowired
    private ServicioService servicioService;

    @GetMapping("/cita/listado")
    public String listado(Model model) {
        var citas = citaService.listarCitas();
        model.addAttribute("citas", citas);
        return "cita/listado";
    }

    @GetMapping("/cita/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("cita", new Cita());
        model.addAttribute("servicios", servicioService.listarServicios());
        return "cita/modifica";
    }

    @GetMapping("/cita/modificar/{id}")
    public String modificar(@PathVariable Integer id, Model model) {
        var cita = citaService.buscarCita(id);
        model.addAttribute("cita", cita);
        model.addAttribute("servicios", servicioService.listarServicios());
        return "cita/modifica";
    }

    @PostMapping("/cita/guardar")
    public String guardar(Cita cita) {
        citaService.guardarCita(cita);
        return "redirect:/cita/listado";
    }

    @GetMapping("/cita/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        citaService.eliminarCita(id);
        return "redirect:/cita/listado";
    }
}
