package br.com.synapse.dao;

import br.com.synapse.model.Trilha;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TrilhaDAO {

    private static final List<Trilha> BD = new ArrayList<>();
    private static Long SEQUENCE = 1L;

    static {
        BD.add(new Trilha(SEQUENCE++, "Trilha Java",
                "Introdução à linguagem Java e boas práticas.", "iniciante"));
        BD.add(new Trilha(SEQUENCE++, "Trilha Cloud",
                "Fundamentos de cloud e arquitetura de soluções.", "intermediario"));
    }

    public List<Trilha> findAll() {
        return new ArrayList<>(BD);
    }

    public Optional<Trilha> findById(Long id) {
        return BD.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }

    public Trilha save(Trilha trilha) {
        trilha.setId(SEQUENCE++);
        BD.add(trilha);
        return trilha;
    }

    public boolean update(Long id, Trilha trilhaAtualizada) {
        Optional<Trilha> opt = findById(id);
        if (opt.isEmpty()) {
            return false;
        }
        Trilha existente = opt.get();
        existente.setNome(trilhaAtualizada.getNome());
        existente.setDescricao(trilhaAtualizada.getDescricao());
        existente.setNivel(trilhaAtualizada.getNivel());
        return true;
    }

    public boolean delete(Long id) {
        return BD.removeIf(t -> t.getId().equals(id));
    }
}
