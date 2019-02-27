package com.anderson.engdb.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anderson.engdb.domain.Vendedor;
import com.anderson.engdb.services.VendedorService;

@RestController
@RequestMapping(value = "/vendedores")
public class VendedorResource {

	@Autowired
	private VendedorService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Vendedor> findById(@PathVariable Integer id) {
		Vendedor obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
