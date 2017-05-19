package com.equipos.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dao.DAOEquipo;
import modelos.Equipo;

@Controller
public class EquiposController {

	
	@Autowired
	DAOEquipo daoe;
	
	
	
	@RequestMapping("/login")
	public ModelAndView hacerLogin(HttpSession sesion, HttpServletRequest request,
			@RequestParam(value = "nombre") String nombre, @RequestParam(value = "password") String password) {

		ModelAndView mv = new ModelAndView("login");


		return mv;

	}

	@RequestMapping("/listado")
	public ModelAndView listado(HttpSession sesion) {

		ModelAndView mv = null;

			mv = new ModelAndView("listado");

			
		List<Equipo> lista = daoe.listar();
		mv.addObject("lista", lista);
		mv.addObject("tipos",Equipo.TipoEquipo.values());
		

		return mv;
	}

	@RequestMapping("/altaequipo")
	public ModelAndView altaequipo(HttpSession sesion, HttpServletResponse response,
			@RequestParam(value = "tipo") String tipo, @RequestParam(value = "ubicacion") String ubicacion,
			@RequestParam(value = "modelo") String modelo,
			@RequestParam(value = "fechaInstalacion") String fechaInstalacion) {

		
		
		SimpleDateFormat parser = new SimpleDateFormat("d/M/y");
		try {
			Date d = parser.parse(fechaInstalacion);
			Equipo e = new Equipo(-1, Equipo.TipoEquipo.valueOf(tipo), ubicacion, modelo, d);
			

			daoe.create(e);
		} catch (ParseException pe) {
			pe.printStackTrace();
		}

		return listado(sesion);
		
	}

	@RequestMapping("/borrar")
	public ModelAndView borrar(
			HttpSession sesion, 
			@RequestParam(value = "id") int id) {

		daoe.delete(id);
		return listado(sesion);
		
	}

}
