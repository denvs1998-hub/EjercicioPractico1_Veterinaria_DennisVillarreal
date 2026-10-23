/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */


package com.veterinaria.veterinaria.service;

import com.veterinaria.veterinaria.domain.Cita;
import java.util.List;

public interface CitaService {

    public List<Cita> listarCitas();

    public Cita buscarCita(Integer id);

    public void guardarCita(Cita cita);

    public void eliminarCita(Integer id);
}
