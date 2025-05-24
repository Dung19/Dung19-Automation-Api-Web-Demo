package com.jitsu.api.defs;

import com.jitsu.api.steps.GitHubApiSteps;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import org.json.JSONObject;

import java.util.List;

public class GitHubApiDef {
    @Steps
    GitHubApiSteps gitHubApiSteps;


    @When("^Get all repositories for SeleniumHQ$")
    public void getAllRepositories() {
        gitHubApiSteps.getAllRepositories();
    }
    @When("^call all repositories for SeleniumHQ$")
    public void callAPIGetAllRepositories() {
        gitHubApiSteps.callAPIGetAllRepositories();
    }
    @When("^Count total open issues across all repositories$")
    public void getTotalOpenIssues() {
        int totalIssues = gitHubApiSteps.getTotalOpenIssues();
        System.out.println("Total open issue:"+totalIssues);
        try {
            Serenity.recordReportData().withTitle("Total open issues").andContents(String.valueOf(totalIssues));
        } catch (NullPointerException ignored) {
        }
    }
    @When("^Sort repositories by update date with descending$")
    public void getRepositoriesSortedByUpdatedDate() {
        List<JSONObject> sortListRespositories = gitHubApiSteps.getRepositoriesSortedByUpdatedDate();
        System.out.println("sort repositories:" +sortListRespositories);
        try {
            Serenity.recordReportData().withTitle("Sort repositories by update date with descending:").andContents(sortListRespositories.toString());
        } catch (NullPointerException ignored) {
        }
    }

//    @When("^Find repository with the most watchers$")
//    public void getRepositoryWithMostWatchers() {
//        String mostWachers = gitHubApiSteps.getRepositoryWithMostWatchers();
//        System.out.println(mostWachers);
//    }
    @When("^Find repository with the most watchers$")
    public void getRepositoryWithMostWatchersFull() {
        JSONObject mostWachers = gitHubApiSteps.getRepositoryWithMostWatchers();
        System.out.println("repository have the most watchers:" +mostWachers);

        try {
            Serenity.recordReportData().withTitle("Repository have the most watchers:").andContents(mostWachers.toString());
        } catch (NullPointerException ignored) {
        }
    }

}
