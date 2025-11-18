package br.com.synapse.bo;

import br.com.synapse.dao.TrilhaDAO;
import br.com.synapse.model.Trilha;

import java.util.List;
import java.util.Optional;

public class TrilhaBO {

    private final TrilhaDAO trilhaDAO = new TrilhaDAO();

    public List<Trilha> listarTodos() {
        return trilhaDAO.findAll();
    }

    public Optional<Trilha> buscarPorId(Long id) {
        return trilhaDAO.findById(id);
    }

    public Trilha salvar(Trilha trilha) {
        validar(trilha);
        return trilhaDAO.save(trilha);
    }

    public boolean atualizar(Long id, Trilha trilha) {
        validar(trilha);
        return trilhaDAO.update(id, trilha);
    }

    public boolean remover(Long id) {
        return trilhaDAO.delete(id);
    }

    private void validar(Trilha trilha) {
        if (trilha.getNome() == null || trilha.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome da trilha é obrigatório.");
        }
        if (trilha.getDescricao() == null || trilha.getDescricao().isBlank()) {
            throw new IllegalArgumentException("Descrição da trilha é obrigatória.");
        }
        if (trilha.getNivel() == null || trilha.getNivel().isBlank()) {
            throw new IllegalArgumentException("Nível da trilha é obrigatório.");
        }
    }
}
