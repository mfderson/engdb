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

import com.anderson.engdb.domain.Cliente;
import com.anderson.engdb.dto.ClienteNewDTO;
import com.anderson.engdb.dto.ClienteUpdtDTO;
import com.anderson.engdb.dto.ClienteViewDTO;
import com.anderson.engdb.resources.utils.CONSTANTS;
import com.anderson.engdb.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Integer id) {
		Cliente obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/cpf")
	public ResponseEntity<Cliente> findByCpf(@RequestParam(value = "value") String cpf) {
		Cliente obj = service.findByCpf(cpf);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto) {
		Cliente obj = service.fromDto(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody ClienteUpdtDTO objDto) {
		Cliente obj = service.fromDto(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/page")
	public ResponseEntity<Page<Cliente>> findAll(
			@PageableDefault(
					size = CONSTANTS.DEFAULT_PAGE_SIZE, 
					page = CONSTANTS.DEFAULT_PAGE_INITIAL,
					sort = "nome",
					direction = Sort.Direction.ASC) Pageable pageable) {
		
		Page<Cliente> page = service.findAll(pageable);
		
		return ResponseEntity.ok().body(page);
	}
	
	@GetMapping(value = "/pageWithVendedor")
	public ResponseEntity<Page<ClienteViewDTO>> findAllWithVendedor(
			@PageableDefault(
					size = CONSTANTS.DEFAULT_PAGE_SIZE, 
					page = CONSTANTS.DEFAULT_PAGE_INITIAL,
					sort = "nome",
					direction = Sort.Direction.ASC) Pageable pageable) {
		
		Page<Cliente> page = service.findAll(pageable);
		Page<ClienteViewDTO> pageDto = page.map(obj -> new ClienteViewDTO(obj));
		
		return ResponseEntity.ok().body(pageDto);
	}
	
	@GetMapping(value = "/vendedorId")
	public ResponseEntity<Page<ClienteViewDTO>> findAllClientesByVendedorId(
			@RequestParam(value = "value") Integer id,
			@PageableDefault(
					size = CONSTANTS.DEFAULT_PAGE_SIZE, 
					page = CONSTANTS.DEFAULT_PAGE_INITIAL,
					sort = "nome",
					direction = Sort.Direction.ASC) Pageable pageable) {
		
		Page<Cliente> page = service.findAllByVendedorId(id, pageable);
		Page<ClienteViewDTO> pageDto = page.map(obj -> new ClienteViewDTO(obj));
		
		return ResponseEntity.ok().body(pageDto);
	}
}
