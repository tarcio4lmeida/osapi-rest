package com.restapiworks.osworks.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.restapiworks.osworks.api.mordel.OrdemServicoModel;
import com.restapiworks.osworks.domain.model.OrdemServico;
import com.restapiworks.osworks.domain.repository.OrdemServicoRepository;
import com.restapiworks.osworks.domain.service.GestaoOrdemServicoService;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServico;
	
	@Autowired
	private OrdemServicoRepository  ordemServicoRepository;
	
	@Autowired(required=true)
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdemServico criar(@Valid @RequestBody OrdemServico ordemServico) {
		return gestaoOrdemServico.criar(ordemServico);
	}
	
	@GetMapping
	public List<OrdemServico> listar() {
		return ordemServicoRepository.findAll();
	}
	
	@GetMapping("/ordens-servico/{ordemServicoId}")
	public ResponseEntity<OrdemServicoModel> buscar(@PathVariable Long ordemServicoId) {
		 Optional<OrdemServico> ordemServico =  ordemServicoRepository.findById(ordemServicoId);
		 
		 if(ordemServico.isPresent()) {
			 OrdemServicoModel model = toModel(ordemServico.get());
			 return ResponseEntity.ok(model);
		 }
		 return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/ordens-servico/{ordemServicoId}")
	public ResponseEntity<OrdemServico> atualizar(@Valid @PathVariable Long ordemServicoId, @RequestBody OrdemServico ordemServico) {
		
		if (!ordemServicoRepository.existsById(ordemServicoId)){
			return ResponseEntity.notFound().build();
		}
		ordemServico.setId(ordemServicoId);
		ordemServico = gestaoOrdemServico.salvar(ordemServico);
		
		return ResponseEntity.ok(ordemServico);
	}
	
	@DeleteMapping("/ordens-servico/{ordemServicoId}")
	public ResponseEntity<Void> deletar(@PathVariable Long ordemServicoId) {
		
		if (!ordemServicoRepository.existsById(ordemServicoId)){
			return ResponseEntity.notFound().build();
		}
		gestaoOrdemServico.excluir(ordemServicoId);
		
		return ResponseEntity.noContent().build();
	}
	
	private OrdemServicoModel toModel (OrdemServico ordemServico) {
		return modelMapper.map(ordemServico,OrdemServicoModel.class);
	}
}
