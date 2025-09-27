package com.example.springproject.service;

import com.example.springproject.dto.TaskDTO;
import com.example.springproject.exception.ResourceNotFoundException;
import com.example.springproject.model.Task;
import com.example.springproject.model.User;
import com.example.springproject.repository.TaskRepository;
import com.example.springproject.security.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final SecurityUtils securityUtils;

    public TaskService(TaskRepository taskRepository, SecurityUtils securityUtils) {
        this.taskRepository = taskRepository;
        this.securityUtils = securityUtils;
    }

    /**
     * Converte a Entidade Task para o DTO de Task.
     */
    private TaskDTO convertToDTO(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setDueDate(task.getDueDate());
        dto.setPriority(task.getPriority().name());
        dto.setStatus(task.getStatus().name());
        return dto;
    }

    /**
     * Cria uma nova tarefa para o usuário autenticado.
     */
    @Transactional
    public TaskDTO createTask(TaskDTO taskDTO) {
        User currentUser = securityUtils.getAuthenticatedUser();

        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setDueDate(taskDTO.getDueDate());

        try {
            task.setPriority(Task.Priority.valueOf(taskDTO.getPriority().toUpperCase()));
            task.setStatus(Task.Status.valueOf(taskDTO.getStatus().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Prioridade ou Status inválido fornecido: " + e.getMessage());
        }

        task.setUser(currentUser);

        Task savedTask = taskRepository.save(task);
        return convertToDTO(savedTask);
    }

    /**
     * Retorna todas as tarefas do usuário autenticado.
     */
    public List<TaskDTO> getAllTasks() {
        User currentUser = securityUtils.getAuthenticatedUser();
        List<Task> tasks = taskRepository.findByUserId(currentUser.getId());

        return tasks.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retorna uma tarefa específica por ID (garantindo a posse pelo usuário).
     */
    public TaskDTO getTaskById(Long id) {
        User currentUser = securityUtils.getAuthenticatedUser();

        Task task = taskRepository.findByIdAndUserId(id, currentUser.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa", "ID", id));

        return convertToDTO(task);
    }

    /**
     * Atualiza uma tarefa existente (garantindo a posse pelo usuário).
     */
    @Transactional
    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
        User currentUser = securityUtils.getAuthenticatedUser();

        Task task = taskRepository.findByIdAndUserId(id, currentUser.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa", "ID", id));

        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setDueDate(taskDTO.getDueDate());

        try {
            task.setPriority(Task.Priority.valueOf(taskDTO.getPriority().toUpperCase()));
            task.setStatus(Task.Status.valueOf(taskDTO.getStatus().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Prioridade ou Status inválido fornecido.");
        }

        Task updatedTask = taskRepository.save(task);
        return convertToDTO(updatedTask);
    }

    /**
     * Exclui uma tarefa (garantindo a posse pelo usuário).
     */
    public void deleteTask(Long id) {
        User currentUser = securityUtils.getAuthenticatedUser();
        Task task = taskRepository.findByIdAndUserId(id, currentUser.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa", "ID", id));

        taskRepository.delete(task);
    }
}
