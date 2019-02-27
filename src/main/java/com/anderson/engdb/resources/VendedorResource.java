package com.anderson.engdb.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/vendedores")
public class VendedorResource {

	@GetMapping
	public String listar() {
		return "REST funcionando!";
	}
}
