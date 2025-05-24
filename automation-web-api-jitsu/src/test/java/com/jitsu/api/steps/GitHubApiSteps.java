package com.jitsu.api.steps;



import com.jitsu.api.common.Constants;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GitHubApiSteps{

//    private static final String BASE_URL = "https://api.github.com/orgs/SeleniumHQ/repos";

    private List<JSONObject> repositories;

    @Step
    public void callAPIGetAllRepositories() {
        Response response;

        response = SerenityRest.rest().relaxedHTTPSValidation()
                .baseUri("https://api.github.com/orgs/")
                .when()
                .log().all()
                .get("SeleniumHQ/repos");
        System.out.println("response" + response.asString());
        Serenity.setSessionVariable("rest").to(response);
    }

    @Step
    public void getAllRepositories() {
        Response response = SerenityRest.given().get(Constants.BASE_URL_GIT);
        Serenity.setSessionVariable("rest").to(response);
        JSONArray jsonArray = new JSONArray(response.getBody().asString());
        repositories = jsonArray.toList().stream()
                .map(obj -> new JSONObject((java.util.Map<?, ?>) obj))
                .collect(Collectors.toList());
    }

    @Step("Count total open issues across all repositories")
    public int getTotalOpenIssues() {
        return repositories.stream()
                .mapToInt(repo -> repo.getInt("open_issues_count"))
                .sum();
    }

//    @Step
//    public List<String> getRepositoriesSortedByUpdatedDateShowName() {
//        return repositories.stream()
//                .sorted(Comparator.comparing(repo -> repo.getString("updated_at"), Comparator.reverseOrder()))
//                .map(repo -> repo.getString("name"))
//                .collect(Collectors.toList());
//    }
    @Step
    public List<JSONObject> getRepositoriesSortedByUpdatedDate() {
        return repositories.stream()
                .sorted(Comparator.comparing(repo -> repo.getString("updated_at"), Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }
//
//    @Step
//    public String getRepositoryWithMostWatchersShowName() {
//        return repositories.stream()
//                .max(Comparator.comparingInt(repo -> repo.getInt("watchers_count")))
//                .map(repo -> repo.getString("name"))
//                .orElse("No repositories found");
//    }

    @Step
    public JSONObject getRepositoryWithMostWatchers() {
        return repositories.stream()
                .max(Comparator.comparingInt(repo -> repo.getInt("watchers_count")))
                .orElse(null); // hoặc throw exception tùy ý
    }
}
