package org.example.taskfinancemanagementsystem.controller;

import org.example.taskfinancemanagementsystem.dto.TransacaoDTO;
import org.example.taskfinancemanagementsystem.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/transactions/")
public class TransacaoController {


    @Autowired
    private TransacaoService transacaoService;

    public List<TransacaoDTO> getAllTransacoes(){
        return transacaoService.getAllTransacoes();
    }

    @GetMapping("/{id}")
    public TransacaoDTO getTransacaoById(@PathVariable Long id){
        return transacaoService.getTransacaoById(id);
    }

}
