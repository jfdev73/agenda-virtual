package com.miranda.agendavirtual.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miranda.agendavirtual.model.Contacto;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Long>{

	Page<Contacto> findByNombreContaining(String nombre, Pageable pageable);
}
