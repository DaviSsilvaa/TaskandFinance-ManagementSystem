package com.example.springproject.service;

import com.example.springproject.dto.TaskDTO;
import com.example.springproject.model.Task;
import com.example.springproject.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository; // Repositório para acessar o banco de dados

    // Método para obter todas as tarefas
    public List<TaskDTO> getAllTasks() {
        List<Task> tasks = taskRepository.findAll(); // Busca todas as tarefas do banco
        return tasks.stream()
                .map(this::convertToDTO) // Converte para DTO
                .collect(Collectors.toList());
    }

    // Método para criar uma nova tarefa
    public TaskDTO createTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setDeadline(taskDTO.getDeadline());
        task.setPriority(taskDTO.getPriority());
        task.setStatus(taskDTO.getStatus());

        Task savedTask = taskRepository.save(task); // Salva a tarefa no banco
        return convertToDTO(savedTask); // Retorna o DTO da tarefa salva
    }

    // Método para obter uma tarefa por ID
    public TaskDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        return convertToDTO(task);
    }

    // Método para atualizar uma tarefa
    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setDeadline(taskDTO.getDeadline());
        task.setPriority(taskDTO.getPriority());
        task.setStatus(taskDTO.getStatus());

        Task updatedTask = taskRepository.save(task);
        return convertToDTO(updatedTask);
    }

    // Método para deletar uma tarefa
    public String deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        taskRepository.delete(task);
        return "Tarefa deletada com sucesso!";
    }

    // Método para converter a entidade Task para o DTO TaskDTO
    private TaskDTO convertToDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setDeadline(task.getDeadline());
        taskDTO.setPriority(task.getPriority());
        taskDTO.setStatus(task.getStatus());
        return taskDTO;
    }
}
