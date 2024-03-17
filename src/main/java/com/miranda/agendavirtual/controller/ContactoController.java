package com.miranda.agendavirtual.controller;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.miranda.agendavirtual.model.Contacto;
import com.miranda.agendavirtual.repository.ContactoRepository;
import com.miranda.agendavirtual.service.ContactoService;

@Controller
public class ContactoController {

	@Autowired
	private ContactoService contactoService;
	


	@GetMapping({ "", "/" })
	public String home(@PageableDefault(size = 5, sort="fechaRegistro") Pageable pageable, Model model
			, @RequestParam(required = false) String busqueda ) {
		
		Page<Contacto> contactoPage= contactoService.getContactosPage( busqueda, pageable);
		
		model.addAttribute("contactoPage", contactoPage);
		return "home";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model model) {

		model.addAttribute("contacto", new Contacto());
		return "nuevo";
	}

	@PostMapping("/save")
	public String crear(@Validated Contacto contacto, BindingResult br, RedirectAttributes ra, Model model) {
		String mensaje = "El contacto se ha creado correctamente";
		
		 if (contacto.getId()!= null) { contactoService.actualizarContacto(contacto,
		 contacto.getId()); mensaje ="El contacto se ha actualizado correctamente";
		 }else { contactoService.guardarContacto(contacto);
		  
		  }
		 

		if (br.hasErrors()) {
			model.addAttribute("contacto", contacto);
			return "nuevo";
		}

		ra.addFlashAttribute("msjExito", mensaje);

		return "redirect:/";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Long id, Model model) {
		Contacto contacto = contactoService.findById(id);
		model.addAttribute("contacto", contacto);
		return "nuevo";
	}

	@PostMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Long id, Model model, RedirectAttributes ra) {
		contactoService.eliminarContacto(id);
		ra.addFlashAttribute("msjExito", "Contacto eliminado correctamente");
		return "redirect:/";
	}

}
