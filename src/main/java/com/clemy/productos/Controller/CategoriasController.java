package com.clemy.productos.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.clemy.productos.Repository.CategoriasRepository;
import com.clemy.productos.modelo.Categorias;




@Controller
public class CategoriasController {
	@Autowired
	private CategoriasRepository categoriaRepository;

	@GetMapping("/ListaCategorias")
	public String listarProductos(Model modelo) {
		List<Categorias> listarCategorias = categoriaRepository.findAll();
		modelo.addAttribute("listarCategorias", listarCategorias);	
		
		return"ListCategorias";
	}	
	
 @RequestMapping("/ListaCategorias")
 public String listarCategorias (Model model)
 {
	 List<Categorias> listarCategorias = categoriaRepository.findAll();
	model.addAttribute("listarCategorias", listarCategorias);	
		
	 return "ListCategorias";
 }
}
