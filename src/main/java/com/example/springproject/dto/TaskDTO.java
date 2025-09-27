package com.example.springproject.dto;

import com.example.springproject.model.Task;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public class TaskDTO {

    private Long id;

    @NotBlank(message = "O título não pode ser vazio.")
    @Size(max = 100, message = "O título deve ter no máximo 100 caracteres.")
    private String title;

    private String description;
    private LocalDate dueDate;

    @NotNull(message = "A prioridade é obrigatória.")
    private String priority;

    @NotNull(message = "O status é obrigatório.")
    private String status;


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
