package br.com.synapse.resource;

import br.com.synapse.bo.TrilhaBO;
import br.com.synapse.model.Trilha;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Path("/trilhas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TrilhaResource {

    private final TrilhaBO trilhaBO = new TrilhaBO();

    @GET
    public List<Trilha> listarTodos() {
        return trilhaBO.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Optional<Trilha> opt = trilhaBO.buscarPorId(id);
        if (opt.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(opt.get()).build();
    }

    @POST
    public Response criar(Trilha trilha) {
        try {
            Trilha salva = trilhaBO.salvar(trilha);
            URI uri = URI.create("/trilhas/" + salva.getId());
            return Response.created(uri).entity(salva).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, Trilha trilha) {
        try {
            boolean atualizado = trilhaBO.atualizar(id, trilha);
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
        boolean removido = trilhaBO.remover(id);
        if (!removido) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}
