package com.anderson.engdb.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.anderson.engdb.domain.Vendedor;
import com.anderson.engdb.dto.VendedorNewDTO;
import com.anderson.engdb.dto.VendedorUpdtDTO;
import com.anderson.engdb.dto.VendedorViewDTO;
import com.anderson.engdb.resources.utils.CONSTANTS;
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
	
	@GetMapping(value = "/cpf")
	public ResponseEntity<Vendedor> findByCpf(@RequestParam(value = "value") String cpf) {
		Vendedor obj = service.findByCpf(cpf);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody VendedorNewDTO objDto) {
		Vendedor obj = service.fromDto(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody VendedorUpdtDTO objDto) {
		Vendedor obj = service.fromDto(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/page")
	public ResponseEntity<Page<VendedorViewDTO>> findAll(
			@PageableDefault(
					size = CONSTANTS.DEFAULT_PAGE_SIZE, 
					page = CONSTANTS.DEFAULT_PAGE_INITIAL,
					sort = "nome",
					direction = Sort.Direction.ASC) Pageable pageable) {
		
		Page<Vendedor> page = service.findAll(pageable);
		Page<VendedorViewDTO> pageDto = page.map(obj -> new VendedorViewDTO(obj));
		
		return ResponseEntity.ok().body(pageDto);
	}
}
