package org.example.taskfinancemanagementsystem.service;

import org.example.taskfinancemanagementsystem.dto.TarefaDTO;
import org.example.taskfinancemanagementsystem.model.Tarefa;
import org.example.taskfinancemanagementsystem.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public List<TarefaDTO> getAllTarefas() {
        return tarefaRepository.findAll().stream()
                .map(tarefa -> {
                    TarefaDTO dto = new TarefaDTO();
                    dto.setId(tarefa.getId());
                    dto.setTitulo(tarefa.getTitulo());
                    dto.setDescricao(tarefa.getDescricao());
                    dto.setPrazo(tarefa.getPrazo());
                    dto.setPrioridade(tarefa.getPrioridade());
                    dto.setStatus(tarefa.getStatus());
                    return dto;
                })
                .collect(Collectors.toList());

    };

    public TarefaDTO getTarefaById(Long id) {
        Tarefa tarefa = tarefaRepository.findById(id).orElseThrow();
        TarefaDTO dto = new TarefaDTO();
        dto.setId(tarefa.getId());
        dto.setTitulo(tarefa.getTitulo());
        dto.setDescricao(tarefa.getDescricao());
        dto.setPrazo(tarefa.getPrazo());
        dto.setPrioridade(tarefa.getPrioridade());
        dto.setStatus(tarefa.getStatus());
        return dto;
    }
}
