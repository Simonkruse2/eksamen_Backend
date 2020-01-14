package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.RecipeDTO;
import utils.EMF_Creator;
import facades.FacadeExample;
import facades.MenuFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("food")
public class RenameMeResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/eksamen",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE);
    private static final FacadeExample FACADE = FacadeExample.getFacadeExample(EMF);
    private static final MenuFacade FACADE1 = MenuFacade.getMenuFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount() {
        long count = FACADE.getRenameMeCount();
        return "{\"count\":" + count + "}";  //Done manually so no need for a DTO
    }

    @GET
    @Path("setup")
    @Produces({MediaType.APPLICATION_JSON})
    public String setup() {
        FACADE1.setup();
        return "setup complete";
    }

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public List<RecipeDTO> all() {

        return FACADE1.getAllRecipes();
    }

    @GET
    @Path("find/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public RecipeDTO find(@PathParam("id") int id) {
        return FACADE1.getRecipe(id);
    }
//    public String setup() {
//        
//        Person person = new Person("Lars", "Larsen", "80808080", "lars@larsen.dk");
//        Address address = new Address("street 10", "City 10", "2400");
//        Hobby hobby = new Hobby("Fodbold", "boldsport");
//        person.setHobby(hobby);
//
//        person.setAddress(address);
//
//        PersonDTO personDTO = new PersonDTO(person);
//
//        System.out.println(personDTO);
//        FACADE.createPerson(personDTO);
//        return "{\"msg\":\"Setup Complete\"}";
//    }

}
