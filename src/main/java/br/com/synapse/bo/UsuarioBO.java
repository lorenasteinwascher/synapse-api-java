package br.com.synapse.bo;

import br.com.synapse.dao.UsuarioDAO;
import br.com.synapse.model.Usuario;

import java.util.List;
import java.util.Optional;

public class UsuarioBO {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public List<Usuario> listarTodos() {
        return usuarioDAO.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioDAO.findById(id);
    }

    public Usuario salvar(Usuario usuario) {
        validar(usuario);
        return usuarioDAO.save(usuario);
    }

    public boolean atualizar(Long id, Usuario usuario) {
        validar(usuario);
        return usuarioDAO.update(id, usuario);
    }

    public boolean remover(Long id) {
        return usuarioDAO.delete(id);
    }

    private void validar(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome do usuário é obrigatório.");
        }
        if (usuario.getEmail() == null || usuario.getEmail().isBlank()) {
            throw new IllegalArgumentException("E-mail é obrigatório.");
        }
        if (!usuario.getEmail().contains("@")) {
            throw new IllegalArgumentException("E-mail inválido.");
        }
        if (usuario.getTipoPerfil() == null || usuario.getTipoPerfil().isBlank()) {
            throw new IllegalArgumentException("Tipo de perfil é obrigatório.");
        }
    }
}
