package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import model.ActivityTypes;
import model.POJO_2;
import play.Logger;
import play.libs.Json;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import play.mvc.*;

import views.html.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;


public class HomeController extends Controller {

    public static String API_KEY = "https://api.pipedrive.com/v1/deals?filter_id=1&start=0&api_token=3cdd6b481df8ea2e483f0a23d71181150ed3d3fd";
    public static String URL_ACTIVITY_1 = "https://api.pipedrive.com/v1/activities/1?api_token=3cdd6b481df8ea2e483f0a23d71181150ed3d3fd";

    public static String GET_ACTIVITY_TYPES = "https://api.pipedrive.com/v1/activityTypes?api_token=3cdd6b481df8ea2e483f0a23d71181150ed3d3fd";

    @Inject WSClient ws;

    public Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public Result home() {
        return ok(projectindex.render());
        //return ok(activitydetails.render("TESTANDO: ", ""+activityTypesList.get(3).getName().toString()));
    }



}
