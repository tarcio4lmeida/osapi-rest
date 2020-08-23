package com.restapiworks.osworks.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapiworks.osworks.domain.model.Cliente;


@RestController
public class ClienteController {
	
	@GetMapping("/clientes")
	public List<Cliente> listar() {
		Cliente cliente1 = new Cliente();
		cliente1.setId(1L);
		cliente1.setNome("Tarcio");
		cliente1.setTelefone("(98) 98840-6918");
		cliente1.setEmail("tarcioalmeidalima@gmail.com");
		
		Cliente cliente2 = new Cliente();
		cliente2.setId(2L);
		cliente2.setNome("José China");
		cliente2.setTelefone("(98) 98840-6918");
		cliente2.setEmail("jcampos@hotmail.com");
		
		return Arrays.asList(cliente1, cliente2);
	}
}
