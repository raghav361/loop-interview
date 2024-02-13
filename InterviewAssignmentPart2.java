package com.loop.part2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;

import java.util.*;

public class InterviewAssignmentPart2 {

	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://app.tryloop.ai/chargebacks/stores/view");
		driver.manage().window().maximize();
		
		Actions actions = new Actions(driver);
        
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[4]/main/div/div/div/div[2]/div/h6[1]/a")));
		
        WebElement signInWithPasswordBtn = driver.findElement(By.xpath("/html/body/div[1]/div[4]/main/div/div/div/div[2]/div/h6[1]/a"));
        signInWithPasswordBtn.click();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div[4]/main/div/div/div/div[2]/div/h4")));
        
        WebElement emailInputBox = driver.findElement(By.xpath("/html/body/div[1]/div[4]/main/div/div/div/div[2]/div/div[2]/div[1]/div/div/div/input"));
        emailInputBox.sendKeys("qa-engineer-assignment@test.com");
        
        WebElement passwordInputBox = driver.findElement(By.xpath("/html/body/div[1]/div[4]/main/div/div/div/div[2]/div/div[2]/div[2]/div/div/div/input"));
        passwordInputBox.sendKeys("QApassword123$");
        
        WebElement loginBtn = driver.findElement(By.xpath("/html/body/div[1]/div[4]/main/div/div/div/div[2]/div/div[2]/div[4]/button"));
        loginBtn.click();
        
    	WebElement modal1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]")));
        WebElement modal2 = modal1.findElement(By.xpath("/html/body/div[4]/div[3]"));
        WebElement modal3 = modal2.findElement(By.xpath("/html/body/div[4]/div[3]/div"));
        WebElement modal4 = modal3.findElement(By.xpath("/html/body/div[4]/div[3]/div/div"));
        WebElement modal5 = modal4.findElement(By.xpath("/html/body/div[4]/div[3]/div/div/div[1]"));
        
        WebElement skipForNowBtn = modal5.findElement(By.xpath("/html/body/div[4]/div[3]/div/div/div[1]/button"));
        skipForNowBtn.click();
        
        WebElement chargebacksDropDown = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/ul/div/div[4]/ul"));
        chargebacksDropDown.click();
        
        WebElement transactionsOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[3]/div/div/ul/div/div[4]/ul/div/div/div/div/a[2]")));
        transactionsOption.click();
        
        WebElement locations = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[3]/main/div/div/header/div/div/button[1]")));
        locations.click();
        
        WebElement locationClearBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[6]/div[3]/div[1]/div[2]/button")));
        locationClearBtn.click();
                
        WebElement firstLocation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[6]/div[3]/div[2]/div/li[1]")));
        firstLocation.click();
        
        WebElement secondLocation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[6]/div[3]/div[2]/div/li[7]")));
        secondLocation.click();
        
        WebElement locationApplyBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[6]/div[3]/div[3]/button[2]")));
        locationApplyBtn.click();
        
        WebElement marketplaces = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[3]/main/div/div/header/div/div/button[3]")));
        marketplaces.click();
        
        WebElement marketplaceClearBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[6]/div[3]/div[1]/div[2]/button")));
        marketplaceClearBtn.click();
        
        WebElement marketplace = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[6]/div[3]/div[2]/div/li[2]")));
        marketplace.click();
        
        WebElement marketplaceApplyBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[6]/div[3]/div[3]/button[2]")));
        marketplaceApplyBtn.click();
        
        File file = new File("src/main/table.csv");
        FileWriter csvWriter = new FileWriter(file);
        
        WebElement noOfRows = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[3]/main/div/div/div[4]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div/h6[2]")));
        String[] noOfRowsStrings = noOfRows.getText().split(" ");
        int lastIndex = Integer.parseInt(noOfRowsStrings[noOfRowsStrings.length - 1]);
        
        int headerRowCount = 0;
        for(int i = lastIndex - 1; i > 0; i-=10) {
        	WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[3]/main/div/div/div[4]/div/div[2]/div[1]/div[2]/div[2]/div[1]/div[3]/div/table")));
        	List<WebElement> tableHeaders = table.findElements(By.xpath("/html/body/div[1]/div[3]/main/div/div/div[4]/div/div[2]/div[1]/div[2]/div[2]/div[1]/div[3]/div/table/thead"));
            List<WebElement> tableRows = table.findElements(By.tagName("tr"));
            
            if(headerRowCount < 1) {
            	for(WebElement thead : tableHeaders) {
                	List<WebElement> thDatas = thead.findElements(By.tagName("th"));
                	for(WebElement th : thDatas) {
                		String str = th.getText().toLowerCase().replaceAll(" ", "_");
                		csvWriter.append(str).append(",");
                	} 	
                }
            	headerRowCount++;
            }
        	
            
            for(WebElement trow : tableRows) {
            	List<WebElement> trDatas = trow.findElements(By.tagName("td"));
            	for(WebElement tr : trDatas) {
            		String str = tr.getText().replaceAll(" ", "_").replaceAll("[$]", "").replaceAll("[/]", "");
            			csvWriter.append(str).append(",");
            	}
            	csvWriter.append("\n");
            }
            
            WebElement nextBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[3]/main/div/div/div[4]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div/button[2]")));
            if(nextBtn.isEnabled())
            	actions.moveToElement(nextBtn).click().perform();
            else
            	break;
        }
        
        csvWriter.close();
        
        driver.quit();
	}

}
