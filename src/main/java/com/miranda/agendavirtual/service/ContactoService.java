package com.miranda.agendavirtual.service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.miranda.agendavirtual.model.Contacto;
import com.miranda.agendavirtual.repository.ContactoRepository;
import com.miranda.agendavirtual.utils.ConvertDate;

@Service
public class ContactoService {
	

	@Autowired
	private ContactoRepository contactoRepository;

	public List<Contacto> getContactos() {
		 
		return contactoRepository.findAll(Sort.by("fechaRegistro").ascending());

	}
	
	public Page<Contacto> getContactosPage( String busqueda, Pageable pageable) {
		
		Page<Contacto> contactos;
		
		if(busqueda !=null && !busqueda.isBlank()) {
			System.out.println("palabra a buscar: "+busqueda);
			contactos=contactoRepository.findByNombreContaining(busqueda, pageable);
		}else {
			contactos = contactoRepository.findAll(pageable);
		}
		 
		return contactos;

	}

	public Contacto findById(Long id) {

		Contacto c = contactoRepository.findById(id).orElse(null);
		if(c!=null && c.getFechaNacimiento()!=null) c.setFechaNacimientoFormat(ConvertDate.toString(c.getFechaNacimiento()));
		return c;

	}

	public void guardarContacto(Contacto contacto) {
		System.out.println("Contacto recibido en guardar: "+contacto);
	
		if(contacto.getFechaNacimientoFormat() !=null && !contacto.getFechaNacimientoFormat().isBlank()) {
			contacto.setFechaNacimiento(ConvertDate.toLocalDate(contacto.getFechaNacimientoFormat()));
		}
		
		
		contacto.setFechaRegistro(LocalDateTime.now());
		System.out.println("Contacto a agregar: " + contacto);
		contactoRepository.save(contacto);

	}

	public void eliminarContacto(Long id) {
		contactoRepository.deleteById(id);

	}

	public void actualizarContacto(Contacto contacto, Long id) {
		 //recuperamos contaco por id
		Contacto contactoRegistrado = contactoRepository.findById(id).orElse(null);

		if (contactoRegistrado != null) {
			contacto.setFechaRegistro(contactoRegistrado.getFechaRegistro());		
		}
		if(contacto.getFechaNacimientoFormat()!=null && !contacto.getFechaNacimientoFormat().isBlank()) {
			contacto.setFechaNacimiento(ConvertDate.toLocalDate(contacto.getFechaNacimientoFormat()));
		}
		System.out.println("Contacto a actualizar: " + contacto);

		contactoRepository.save(contacto);

	}
	
	

}
