package org.example.taskfinancemanagementsystem.service;


import org.example.taskfinancemanagementsystem.dto.TransacaoDTO;
import org.example.taskfinancemanagementsystem.model.Transacao;
import org.example.taskfinancemanagementsystem.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    public List<TransacaoDTO> getAllTransacoes(){
        return transacaoRepository.findAll().stream()
                .map(transacao -> {
                    TransacaoDTO dto = new TransacaoDTO();
                    dto.setId(transacao.getId());
                    dto.setDescricao(transacao.getDescricao());
                    dto.setValor(transacao.getValor());
                    dto.setCategoria(transacao.getCategoria());
                    dto.setData(transacao.getData());
                    return dto;
                })
                .collect(Collectors.toList());
    }


    public TransacaoDTO getTransacaoById(Long id){
        Transacao transacao = transacaoRepository.findById(id).orElseThrow();
        TransacaoDTO dto = new TransacaoDTO();
        dto.setId(transacao.getId());
        dto.setDescricao(transacao.getDescricao());
        dto.setValor(transacao.getValor());
        dto.setCategoria(transacao.getCategoria());
        dto.setData(transacao.getData());
        return dto;
    }

}
