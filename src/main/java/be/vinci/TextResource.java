package be.vinci;

import jakarta.inject.Singleton;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.commons.text.StringEscapeUtils;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
@Path("/texts")
public class TextResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Text> getAll(
            @DefaultValue("") @QueryParam("level") String level){
        var texts = Json.parse();
        if(!level.isBlank()){
            return texts.stream()
                    .filter(text -> text.getLevel().contentEquals(level))
                    .collect(Collectors.toList());
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

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Text createOne(Text text){
        if(text == null || text.getContent() == null || text.getContent().isBlank()
        || text.getLevel() == null){
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST).entity("Lacks of mandatory info or unauthorized text level")
                            .type("text/plain").build());
        }
        var texts = Json.parse();
        text.setId(texts.size() + 1);
        text.setContent(StringEscapeUtils.escapeHtml4(text.getContent()));
        texts.add(text);
        Json.serialize(texts);
        return text;
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Text deleteOne(@PathParam("id") int id){
        if(id==0){
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST).entity("Lacks of mandatory id")
                            .type("text/plain").build());
        }
        var texts = Json.parse();
        Text textToDelete = texts.stream()
                .filter(text -> text.getId() == id)
                .findAny().orElse(null);
        if(textToDelete == null){
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND).entity("Ressource not found")
                            .type("text/plain").build());
        }
        texts.remove(textToDelete);
        Json.serialize(texts);
        return textToDelete;
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Text updateText(Text text, @PathParam("id") int id){
        System.out.println(text);
        if( text == null || text.getContent() == null || text.getContent().isBlank()
        || text.getLevel() == null){
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST).entity("Lacks of mandatory info or unauthorized text level")
                            .type("text/plain").build());
        }

        var texts = Json.parse();
        Text textToUpdate = texts.stream()
                .filter(t -> t.getId() == id).findAny().orElse(null);
        if(textToUpdate == null){
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND).entity("Resources not found")
                            .type("text/plain").build());
        }
        text.setId(texts.size() + 1);
        text.setContent(StringEscapeUtils.escapeHtml4(text.getContent()));
        texts.remove(text);
        texts.add(text);
        Json.serialize(texts);
        return text;
    }
}
