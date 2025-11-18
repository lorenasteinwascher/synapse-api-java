package br.com.synapse.resource;

import br.com.synapse.bo.UsuarioBO;
import br.com.synapse.model.Usuario;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    private final UsuarioBO usuarioBO = new UsuarioBO();

    @GET
    public List<Usuario> listarTodos() {
        return usuarioBO.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Optional<Usuario> opt = usuarioBO.buscarPorId(id);
        if (opt.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(opt.get()).build();
    }

    @POST
    public Response criar(Usuario usuario) {
        try {
            Usuario salvo = usuarioBO.salvar(usuario);
            URI uri = URI.create("/usuarios/" + salvo.getId());
            return Response.created(uri).entity(salvo).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, Usuario usuario) {
        try {
            boolean atualizado = usuarioBO.atualizar(id, usuario);
            if (!atualizado) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.noContent().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") Long id) {
        boolean removido = usuarioBO.remover(id);
        if (!removido) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}
