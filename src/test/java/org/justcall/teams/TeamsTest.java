package org.justcall.teams;

import io.qameta.allure.Description;
import org.justcall.base.BaseTest;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TeamsTest extends BaseTest {
    @Test
    @Description("This test adds a new member to the team and verifies that the member is successfully invited.")
    public void addMemberToTeamsTest() {
        Teams teams = PageFactory.initElements(driver, Teams.class);
        teams.addMemberToTeams();
        Assert.assertEquals(teams.getSuccessMsgText(),"Member Invited");

    }

}
