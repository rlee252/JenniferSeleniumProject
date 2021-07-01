package com.revature.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.opencsv.CSVWriter;
import com.revature.app.pages.LoginPage;
import com.revature.app.pages.RankingPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Main {

	public static void main(String[] args) throws InterruptedException, IOException {

		Scanner myObj = new Scanner(System.in);
		System.out.println("Enter url");
		String url = myObj.nextLine();
		System.out.println("Enter new file name");
		String fileName = myObj.nextLine();

		WebDriverManager.firefoxdriver().setup();
		//WebDriver driver = new FirefoxDriver();
		 FirefoxBinary firefoxBinary = new FirefoxBinary();
		 firefoxBinary.addCommandLineOptions("--headless");
		 FirefoxOptions firefoxOptions = new FirefoxOptions();
		 firefoxOptions.setBinary(firefoxBinary);
		 FirefoxDriver driver = new FirefoxDriver(firefoxOptions);
		driver.get(url);

		WebDriverWait wdw = new WebDriverWait(driver, 20);

		LoginPage loginPage = new LoginPage(driver);
		RankingPage rankingPage = new RankingPage(driver);

		Boolean acceptIsPresent = driver.findElements(By.xpath("//*[text()='Accept']")).size() > 0;

		wdw.until(ExpectedConditions.elementToBeClickable(loginPage.acceptElement()));

		if (acceptIsPresent)
			loginPage.clickAccept();

		wdw.until(ExpectedConditions.elementToBeClickable(loginPage.loginElement()));
		loginPage.clickLogin();

		wdw.until(ExpectedConditions.elementToBeClickable(loginPage.emailElement()));
		loginPage.typeEmail();
		loginPage.typePassword();
		loginPage.clickLogin2();

		Thread.sleep(2000);
		boolean viewCode = false;
		List<String[]> result = new ArrayList<String[]>();

		for (int i = 0; i < 100; i++) {
			int id = i + 1;
			if (rankingPage.getViewCode(i) > 0) {
				viewCode = true;
			} else {
				viewCode = false;
			}

			String[] playerRecord = { String.valueOf(id), rankingPage.getNickNameText(i), rankingPage.getScoreText(i),
					rankingPage.getDurationText(i), String.valueOf(viewCode) };

			result.add(playerRecord);
		}

		// setup
		String userHome = System.getProperty("user.home") + "/Desktop";
		File file = new File(userHome, fileName + ".csv");
		boolean fileResult = file.createNewFile();

		if (fileResult) {
			System.out.println("Created new file at " + file.getCanonicalPath());
		} else {
			System.out.println("File already exists at " + file.getCanonicalPath());
		}
		FileOutputStream fs = new FileOutputStream(file);
		OutputStreamWriter osw = new OutputStreamWriter(fs);
		CSVWriter csvWriter = new CSVWriter(osw);

		// Writing to file
		csvWriter.writeAll(result);

		// close stream
		csvWriter.close();
		osw.close();
		fs.close();

		driver.quit();
		System.out.println("Have a nice day");
	}
}
