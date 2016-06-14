package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import model.Activity;
import model.ActivityDTO;
import model.ActivityTypes;
import play.data.Form;
import play.libs.Json;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

/**
 * Created by Boss on 13/06/2016.
 */
public class ActivityService {

    public static String GET_ACTIVITY_TYPES = "https://api.pipedrive.com/v1/activityTypes?api_token=3cdd6b481df8ea2e483f0a23d71181150ed3d3fd";

    public static String NEW_ACTIVITY = "https://api.pipedrive.com/v1/activities?api_token=3cdd6b481df8ea2e483f0a23d71181150ed3d3fd";

    public static String LIST_ACTIVITIES = "https://api.pipedrive.com/v1/activities?start=0&api_token=3cdd6b481df8ea2e483f0a23d71181150ed3d3fd";

    public static String DELETE = "https://api.pipedrive.com/v1/activities/";

    public static String EDIT_ACTIVITY = "https://api.pipedrive.com/v1/activities/";

    @Inject WSClient ws;

    public List<ActivityTypes> loadActivityTypes() {
        List<ActivityTypes> activityTypes = new ArrayList<>();

        CompletionStage<JsonNode> jsonPromise = ws.url(GET_ACTIVITY_TYPES).get().thenApply(WSResponse::asJson);
        JsonNode jsonNode = null;
        try {
            jsonNode = jsonPromise.toCompletableFuture().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for (JsonNode data : jsonNode.withArray("data")) {
            ActivityTypes activityType = Json.fromJson(data, ActivityTypes.class);
            activityTypes.add(activityType);
        }
        System.out.print(activityTypes.get(0).getKey_string());
        return activityTypes;
    }

    public void newActivity(Form<Activity> form) {
        JsonNode jsonNode = Json.toJson(form.data());
        ws.url(NEW_ACTIVITY).post(jsonNode);
    }

    public List<Activity> loadActivities() {

        CompletionStage<JsonNode> jsonPromise = ws.url(LIST_ACTIVITIES).get().thenApply(WSResponse::asJson);
        JsonNode jsonNode = null;
        try {
            jsonNode = jsonPromise.toCompletableFuture().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        List<Activity> activities = new ArrayList<>();
        for (JsonNode data : jsonNode.withArray("data")) {
            Activity activity = Json.fromJson(data, Activity.class);
            activities.add(activity);
        }
        return activities;
    }

    public void delete(Long id) {

        ws.url(DELETE + id).setQueryParameter("api_token", "3cdd6b481df8ea2e483f0a23d71181150ed3d3fd")
                .delete().thenApply(WSResponse::asJson);
        System.out.print("deletou");
        // TODO verificar pq depois de excluir um e logo em seguida tentar excluir outro dá erro..
    }


    public Activity edit(Long id) {

        CompletionStage<JsonNode> jsonPromise = ws.url(EDIT_ACTIVITY + id)
                .setQueryParameter("api_token", "3cdd6b481df8ea2e483f0a23d71181150ed3d3fd")
                .get().thenApply(WSResponse::asJson);
        JsonNode jsonNode = null;
        try {
            jsonNode = jsonPromise.toCompletableFuture().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        ActivityDTO activityDTO = new ActivityDTO();
        for (JsonNode data : jsonNode.findPath("data")) {
            activityDTO = Json.fromJson(data, ActivityDTO.class);
        }

        return new Activity(activityDTO);
    }

}
