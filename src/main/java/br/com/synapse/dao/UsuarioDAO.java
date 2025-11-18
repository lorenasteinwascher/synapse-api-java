package br.com.synapse.dao;

import br.com.synapse.model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioDAO {

    private static final List<Usuario> BD = new ArrayList<>();
    private static Long SEQUENCE = 1L;

    static {
        BD.add(new Usuario(SEQUENCE++, "Lorena", "lorena@synapse.com", "mentor"));
        BD.add(new Usuario(SEQUENCE++, "Laura", "laura@synapse.com", "aluno"));
        BD.add(new Usuario(SEQUENCE++, "Isabelly", "isabelly@synapse.com", "aluno"));
    }

    public List<Usuario> findAll() {
        return new ArrayList<>(BD);
    }

    public Optional<Usuario> findById(Long id) {
        return BD.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    public Usuario save(Usuario usuario) {
        usuario.setId(SEQUENCE++);
        BD.add(usuario);
        return usuario;
    }

    public boolean update(Long id, Usuario atualizado) {
        Optional<Usuario> opt = findById(id);
        if (opt.isEmpty()) {
            return false;
        }
        Usuario existente = opt.get();
        existente.setNome(atualizado.getNome());
        existente.setEmail(atualizado.getEmail());
        existente.setTipoPerfil(atualizado.getTipoPerfil());
        return true;
    }

    public boolean delete(Long id) {
        return BD.removeIf(u -> u.getId().equals(id));
    }
}
