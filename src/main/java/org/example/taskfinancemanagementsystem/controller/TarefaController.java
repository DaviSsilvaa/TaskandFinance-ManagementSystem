package org.example.taskfinancemanagementsystem.controller;


import org.example.taskfinancemanagementsystem.dto.TarefaDTO;
import org.example.taskfinancemanagementsystem.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tasks/")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public List<TarefaDTO> getAllTarefa(){
        return tarefaService.getAllTarefas();
    }

    @GetMapping("/{id}")
    public TarefaDTO getTarefaById(@PathVariable Long id){
        return tarefaService.getTarefaById(id);
    }




}
