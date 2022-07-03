package com.clemy.productos.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DireccionamientoController {
	
	@GetMapping("/")
	public String paginaInicio() {
		return "index"; 
	}
	@GetMapping("/Clemy")
	public String Inicio() {
		return "index";
	}
	@GetMapping("/indexDash")
	public String InicioDashboard() {
		return "Admin/inicioDash";
	}
	@GetMapping("/indexDashVendedor")
	public String DashboardVende() {
		return "vendedor/inicioDashVendedor";
	}
	@GetMapping("/IncioCliente")
	public String IncioCliente() {
		return "InicioCliente";
	}
	@GetMapping("/index")
	public String paginaInicial() {
		return "index";
	}

}
