package com.restapiworks.osworks.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapiworks.osworks.domain.exception.NegocioException;
import com.restapiworks.osworks.domain.model.Cliente;
import com.restapiworks.osworks.domain.model.OrdemServico;
import com.restapiworks.osworks.domain.model.StatusOrdemServico;
import com.restapiworks.osworks.domain.repository.ClienteRepository;
import com.restapiworks.osworks.domain.repository.OrdemServicoRepository;

@Service
public class GestaoOrdemServicoService {
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;

	public OrdemServico criar(OrdemServico ordemServico) {
		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Cliente não encontrado"));

		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());
		
		return ordemServicoRepository.save(ordemServico);
	}
	
	public OrdemServico salvar(OrdemServico ordemServico) {
		return ordemServicoRepository.save(ordemServico);
	}
	
	public void excluir (Long clienteId) {
		ordemServicoRepository.deleteById(clienteId);
	}
}
