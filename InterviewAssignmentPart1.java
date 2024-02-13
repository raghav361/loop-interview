package com.loop.part1;

import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DecimalFormat;
import java.time.Duration;

public class InterviewAssignmentPart1 {

private static final DecimalFormat decimalformatter = new DecimalFormat("#####.00");
	
	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://app.tryloop.ai/chargebacks/stores/view");
		driver.manage().window().maximize();
	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[4]/main/div/div/div/div[2]/div/h6[1]/a")));
		
		WebElement signInWithPasswordLink = driver.findElement(By.xpath("/html/body/div[1]/div[4]/main/div/div/div/div[2]/div/h6[1]/a"));
        signInWithPasswordLink.click();
	
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[4]/main/div/div/div/div[2]/div/div[2]/div[1]/div/div/div/input")));
        
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
        
        WebElement historyByStoreOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[3]/div/div/ul/div/div[4]/ul/div/div/div/div/a[3]")));
        historyByStoreOption.click();
        
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[3]/main/div/div/div[4]/div/div[1]/div[3]/div/table/thead/tr/th[1]/div/div/h6")));
        
        Actions actions = new Actions(driver);
        
        WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[3]/main/div/div/div[4]/div/div[1]/div[3]/div/table")));
       
        List<WebElement> tableHeaders = table.findElements(By.xpath("/html/body/div[1]/div[3]/main/div/div/div[4]/div/div[1]/div[3]/div/table/thead"));
        List<String> tableHeaderData = new ArrayList<String>();
        for(WebElement thead : tableHeaders) {
        	List<WebElement> thDatas = thead.findElements(By.tagName("th"));
        	for(WebElement th : thDatas)
        		if(!(th.getText().trim().isEmpty()))
        			tableHeaderData.add(th.getText());
        }
        tableHeaderData.remove(0);
        tableHeaderData.remove(tableHeaderData.size() - 1);
        int noOfColumns = tableHeaderData.size();
        	 
        List<WebElement> grandTotalRow = table.findElements(By.xpath("/html/body/div[1]/div[3]/main/div/div/div[4]/div/div[1]/div[3]/div/table/tbody/tr[12]"));
        List<String> grandTotalData = new ArrayList<String>();
        for(WebElement grandTotal : grandTotalRow) {
        	List<WebElement> grandTotals = grandTotal.findElements(By.tagName("td"));
        	for(WebElement gt : grandTotals)
        		if(!(gt.getText().trim().isEmpty())) {
        			String data = gt.getText().replaceAll("[$]", "").replaceAll(",", "");
        			grandTotalData.add(data);
        		}	
        }
        
        grandTotalData.remove(0);
        grandTotalData.remove(grandTotalData.size() - 1);
        
        WebElement noOfRows = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[3]/main/div/div/div[4]/div/div[2]/div/h6[2]")));
        String[] noOfRowsStrings = noOfRows.getText().split(" ");
        int lastIndex = Integer.parseInt(noOfRowsStrings[noOfRowsStrings.length - 1]);
        
        List<WebElement> tableBody = table.findElements(By.xpath("/html/body/div[1]/div[3]/main/div/div/div[4]/div/div[1]/div[3]/div/table/tbody"));
        
        List<Double> calculatedGrandTotal = new ArrayList<Double>();
        
        for(int c = 1; c <= noOfColumns; c++) {
        	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[3]/main/div/div/div[4]/div/div[1]/div[3]/div/table")));
        	List<Double> pricesListOfEachMonth = new ArrayList<Double>();
            
            for(int i = lastIndex - 1; i > 0; i-=10) {	
            	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[3]/main/div/div/div[4]/div/div[1]/div[3]/div/table")));
                
            	List<String> tableDatasList = new ArrayList<String>();
                for(WebElement tableRows : tableBody) {
                	wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("td")));
                	List<WebElement> tableDatas = tableRows.findElements(By.tagName("td"));
                	for(WebElement tableData : tableDatas) {
                		if(!(tableData.getText().trim().isEmpty()))
                			tableDatasList.add(tableData.getText());
                	}
                }
                
                for(int k = c; k < tableDatasList.size() - 10; k+=10-1) {
                	String data = tableDatasList.get(k).replaceAll("[$]", "").replace(",", "");
                	double number = Double.parseDouble(data);
                	pricesListOfEachMonth.add(number);
                }
                
                WebElement nextBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[3]/main/div/div/div[4]/div/div[2]/div/button[2]")));
                WebElement backBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[3]/main/div/div/div[4]/div/div[2]/div/button[1]")));
                if(nextBtn.isEnabled())
                	actions.moveToElement(nextBtn).click().perform();
                else {
                	for(int j = lastIndex - 1; j > 0; j-=10)
                		actions.moveToElement(backBtn).click().perform();
                }
            } 
           
            double totalSumOfEachMonth = 0.0;
            for(Double number : pricesListOfEachMonth)
            	totalSumOfEachMonth += number;
            totalSumOfEachMonth = Double.parseDouble(decimalformatter.format(totalSumOfEachMonth));
        
            calculatedGrandTotal.add(totalSumOfEachMonth);
            pricesListOfEachMonth.removeAll(pricesListOfEachMonth);
            totalSumOfEachMonth = 0.0;
        }
        
        List<Double> actualGrandTotal = new ArrayList<Double>();
        
        for(String month : tableHeaderData)
        	System.out.print(month + "    ");
        
        System.out.println();
        
        for(String grandTotal : grandTotalData) {
        	double number = Double.parseDouble(grandTotal);
        	actualGrandTotal.add(number);
        	System.out.print(number + "\t");
        }
        
        System.out.println();
        
        for(Double grandTotal : calculatedGrandTotal)
        	System.out.print(grandTotal + "\t");
        
        System.out.println();
        
        for(int i = 0, j = 0; i < actualGrandTotal.size() && j < calculatedGrandTotal.size(); i++, j++) {
        	if(actualGrandTotal.get(i).equals(calculatedGrandTotal.get(j)))
        		System.out.print("CORRECT" + "    ");
        	else
        		System.out.print("INCORRECT" + "    ");
        }
        
        driver.quit();
	}
}
