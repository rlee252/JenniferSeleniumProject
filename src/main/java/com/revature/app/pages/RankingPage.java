package com.revature.app.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RankingPage {
	
	private WebDriver driver;
	
	private By nickName = By.className("nickname");
	private By score = By.xpath("//span[contains(text(), '%')]");
	private By duration = By.xpath("//*[contains(text(), '00:')]");
	private By playerReport = By.className("player-report");
	private By viewCode = (By.cssSelector("[ng-if=\"isSolutionVisible(player)\"]"));
	
	public RankingPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getNickNameText(int i) {
		return this.driver.findElements(nickName).get(i).getText();
		
	}
	
	public String getScoreText(int i) {
		return this.driver.findElements(score).get(i).getText();
	}
	
	public String getDurationText(int i) {
		return this.driver.findElements(duration).get(i).getText();
	}
	public int getViewCode(int i) {
		return this.driver.findElements(playerReport).get(i).findElements(viewCode).size();
	}
}
