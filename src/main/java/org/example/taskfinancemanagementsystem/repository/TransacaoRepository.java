package org.example.taskfinancemanagementsystem.repository;

import org.example.taskfinancemanagementsystem.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao,Long> {
}
