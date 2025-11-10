package com.exemplo.crudmongo.service;

import com.exemplo.crudmongo.Model.Curso;
import com.exemplo.crudmongo.repository.Cursorepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    private final Cursorepository repository;

    public CursoService(Cursorepository repository) {
        this.repository = repository;
    }

    public List<Curso> listarTodos() {
        return repository.findAll();
    }

    public Curso salvar(Curso curso) {
        return repository.save(curso);
    }

    public Curso atualizar(Long id, Curso cursoAtualizado) {
        Optional<Curso> opt = repository.findById(id);
        if (opt.isPresent()) {
            Curso c = opt.get();
            c.setNome(cursoAtualizado.getNome());
            c.setCargaHoraria(cursoAtualizado.getCargaHoraria());
            c.setAtivo(cursoAtualizado.getAtivo());
            return repository.save(c);
        }
        return null;
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
