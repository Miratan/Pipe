package controllers;

import model.Activity;
import model.ActivityTypes;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import services.ActivityService;
import views.html.addActivity;
import views.html.projectindex;

import static play.data.Form.*;

import javax.inject.Inject;
import java.util.List;

public class PipeController extends Controller {

    @Inject
    ActivityService activityService;

    public Result index() {
        List<ActivityTypes> activityTypes = activityService.loadActivityTypes();
        return ok(addActivity.render(form(Activity.class), activityTypes));
    }

    public Result addActivity() {

        Form<Activity> form = form(Activity.class).bindFromRequest();
        if(form.hasErrors()) {
            // TODO identificar o erro
            List<ActivityTypes> activityTypes = activityService.loadActivityTypes();
            return ok(addActivity.render(form(Activity.class), activityTypes));
        } else {
            activityService.newActivity(form);
            // TODO mensagem que salvou a nova atividade
            return ok(projectindex.render());
        }
    }

}
