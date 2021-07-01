package com.revature.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	private WebDriver driver;
	
	private By accept = By.xpath("//*[text()='Accept']");
	
	private By emailInputBar = By.cssSelector("input[name=\"email\"]");
	private String user = System.getenv("codingames_user");
	private String password = System.getenv("codingames_password");
	
	private By passwordInputBar = By.cssSelector("input[name=\"password\"]");

	private By login = By.xpath("//*[text()='Log In']");
	private By login2 = By.cssSelector("button[type=\"submit\"]");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public By acceptElement() {
		return accept;
	}
	
	public By loginElement() {
		return login;
	}
	
	public By emailElement() {
		return emailInputBar;
	}
	
	public void clickAccept() {
		this.driver.findElement(accept).click();
	}
	
	public void clickLogin() {
		this.driver.findElement(login).click();
	}
	
	public void typeEmail() {
		this.driver.findElement(emailInputBar).sendKeys(user);;
	}
	
	public void typePassword() {
		this.driver.findElement(passwordInputBar).sendKeys(password);;
	}
	
	public void clickLogin2() {
		this.driver.findElement(login2).click();
	}
	
}
