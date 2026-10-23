/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */


package com.veterinaria.veterinaria.service;

import com.veterinaria.veterinaria.domain.TipoServicio;
import java.util.List;

public interface TipoServicioService {

    public List<TipoServicio> listarTipos();

    public TipoServicio buscarTipo(Integer id);

    public void guardarTipo(TipoServicio tipo);

    public void eliminarTipo(Integer id);
}
