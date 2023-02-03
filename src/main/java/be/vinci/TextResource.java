package be.vinci;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

public class TextResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Text> getAll(
            @DefaultValue("null") @QueryParam("level") String level){
        var texts = Json.parse();
        if(level != null){
            List<Text> textFiltered = texts.stream()
                    .filter(text -> text.getLevel().equals(level))
                    .toList();
            return textFiltered;
        }
        return texts;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Text getOne(@PathParam("id") int id ){
        var texts = Json.parse();
        Text textFound = texts.stream().filter(text -> text.getId() == id)
                .findAny().orElse(null);
        if(textFound == null){
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("Ressources not found").type("text/plain").build());
        }
        return textFound;
    }
}
