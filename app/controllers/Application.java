package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import com.google.gson.JsonElement;

import models.*;

public class Application extends Controller {

    public static void index() {
    	NyTimes NytimesModel = new NyTimes();
    	NytimesModel.fetchNyTimesData();
        render(NytimesModel);
    }

}