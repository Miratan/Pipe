package controllers;

import model.Activity;
import play.data.Form;
import play.libs.ws.WSClient;
import play.mvc.Controller;
import play.mvc.Result;
import services.ActivityService;
import views.html.activitydetails;
import views.html.home;
import views.html.listactivities;
import play.data.FormFactory;

import javax.inject.Inject;

public class ActivityController extends Controller {

    @Inject WSClient ws;

    @Inject ActivityService activityService;

    @Inject FormFactory formFactory;

    public Result home(){
        Form<Activity> activityForm = formFactory.form(Activity.class);
        return ok(home.render(activityService.loadActivities(), activityForm));
    }

    public Result listActivity() {
        Form<Activity> activityForm = formFactory.form(Activity.class);
        return ok(listactivities.render(activityService.loadActivities(), activityForm));
    }

    public Result deleteActivity(Long id){
        Form<Activity> activityForm = formFactory.form(Activity.class);
        activityService.delete(id);
        return ok(listactivities.render(activityService.loadActivities(), activityForm));
    }

    public Result editActivity(Long id){
        // carregar informações desta activity
        Form<Activity> activityForm = formFactory.form(Activity.class);
        Activity activity = activityService.edit(id);
        System.out.print(activity.getSubject());
        return ok(activitydetails.render(activityForm, activityService.loadActivityTypes()));
    }

}
