package com.miranda.agendavirtual.model;

import java.time.LocalDate;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.apache.logging.log4j.util.PropertySource.Comparator;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortComparator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString

public class Contacto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_contacto")
	private Long id;
	
	
	@NotBlank
	private String nombre;
	
	@PastOrPresent
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "fecha_nacimiento")
	private LocalDate fechaNacimiento;
	
	@Transient
	private String fechaNacimientoFormat;
	
	@Size(max = 15, message = "El tamaño debe ser entre 0 y 15")
	private String celular;
	
	@Email
	private String email;
	
	
	@Column(name = "fecha_registro")
	private LocalDateTime fechaRegistro;

	
	
	

	
	
}
