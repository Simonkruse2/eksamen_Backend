package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.RecipeDTO;
import dto.WeekPlanDTO;
import entities.Recipe;
import entities.WeekPlan;
import utils.EMF_Creator;
import facades.FacadeExample;
import facades.MenuFacade;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("food")
public class FoodResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/eksamen",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE);
    private static final FacadeExample FACADE1 = FacadeExample.getFacadeExample(EMF);
    private static final MenuFacade FACADE = MenuFacade.getMenuFacade(EMF);
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
        long count = FACADE1.getRenameMeCount();
        return "{\"count\":" + count + "}";  //Done manually so no need for a DTO
    }
    @RolesAllowed("admin")
    @Path("createRecipe")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public RecipeDTO createRecipe(RecipeDTO recipeDTO) {
        return FACADE.createRecipe(recipeDTO);
    }

    @RolesAllowed("admin")
    @Path("editRecipe/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public RecipeDTO editRecipe(RecipeDTO recipeDTO, @PathParam("id") int id) {
        return FACADE.editRecipe(recipeDTO, id);
    }

    @GET
    @Path("setup")
    @Produces({MediaType.APPLICATION_JSON})
    public String setup() {
        FACADE.setup();
        return "setup complete";
    }

    @RolesAllowed({"admin", "user"})
    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public List<RecipeDTO> all() {
        return FACADE.getAllRecipes();
    }

    @RolesAllowed({"admin", "user"})
    @GET
    @Path("find/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public RecipeDTO find(@PathParam("id") int id) {
        return FACADE.getRecipe(id);
    }

    @RolesAllowed({"admin", "user"})
    @GET
    @Path("weekPlanFind/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public WeekPlanDTO findWeekPlan(@PathParam("id") int id) {
        return FACADE.getWeekPlanDTO(id);
    }

    @RolesAllowed({"admin", "user"})
    @GET
    @Path("allWeekPlans")
    @Produces({MediaType.APPLICATION_JSON})
    public List<WeekPlanDTO> findWeekPlan() {
        return FACADE.getAllWeekPlans();
    }
    
    @RolesAllowed({"admin","user"})
    @Path("createWeekPlan")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public WeekPlan createWeekPlan(List<Recipe> recipe) {
        return FACADE.createWeekPlan(recipe);
    }
}
