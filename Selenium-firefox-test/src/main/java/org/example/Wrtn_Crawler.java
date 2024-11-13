package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Wrtn_Crawler {
    public static void main(String[] args) throws IOException, InterruptedException {
        // 1. GeckoDriver 경로 설정
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\User\\Desktop\\Selenium-firefox-test\\geckodriver.exe");

        // 2. Firefox 드라이버 초기화
        WebDriver driver = new FirefoxDriver();

        try {
            // 3. 원하는 웹페이지로 이동
            driver.get("https://wrtn.ai/?utm_source=google.adwords&utm_medium=cpc&utm_campaign=UA_web_total_pmax&utm_content=pmax_0906&utm_term=&ad_group=pmax_0906&ad_creative=&gad_source=1&gclid=CjwKCAiAudG5BhAREiwAWMlSjDn-RmGysv7LpmOTMSGkoiSZ1TvBmD3Gnfs8dOFqfoDegHbMI9KzWRoC2UEQAvD_BwE");
            Thread.sleep(3000);

            // 4. 웹 페이지에서 웹 모달 삭제
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("var element = document.getElementById('web-modal'); if (element) { element.remove(); }");
            System.out.println("웹 모달이 삭제되었습니다.");

            // 5. auto_input.txt 파일을 읽어들여 입력할 텍스트를 가져오기
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\Selenium-firefox-test\\auto_input.txt"));
            String line;
            String[] inputText = new String[3];  // 질문의 최대 개수는 3개로 설정, 추가 질문은 필요 시 배열 크기 조정

            int i = 0;
            while ((line = br.readLine()) != null && i < 3) {
                inputText[i] = line;  // 파일에서 읽은 질문을 배열에 저장
                i++;
            }
            br.close();

            // 6. 첫 번째 질문에 대해 기존 입력창과 버튼 사용
            WebElement textarea1 = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div/div/div/div[1]/div/div[2]/div/div[3]/div[3]/div[2]/div[1]/div/textarea"));
            WebElement button1 = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div/div/div/div[1]/div/div[2]/div/div[3]/div[3]/div[2]/div[2]/div/button"));

            // 7. 첫 번째 질문 입력 및 버튼 클릭
            textarea1.clear();
            textarea1.sendKeys(inputText[0]);  // 첫 번째 텍스트 입력
            System.out.println("첫 번째 입력된 텍스트: " + inputText[0]);
            button1.click();  // 첫 번째 버튼 클릭
            System.out.println("첫 번째 버튼이 클릭되었습니다.");
            Thread.sleep(5000);
            String xpath1 = "/html/body/div[1]/div[1]/div[2]/div[2]/div/div/div[1]/div/div/div/div/div[1]/div/div/div/div[3]/div/div[1]/div[1]/div[3]/div[2]/div/p";
            WebElement resultElement1 = driver.findElement(By.xpath(xpath1));
            String resultText1 = resultElement1.getText();
            System.out.println("출력된 값: " + resultText1);
            Thread.sleep(5000);  // 10초 대기

            // 8. 두 번째부터는 '//*[@id="rich-textarea"]' 입력창에 텍스트 입력
            WebElement textarea2 = driver.findElement(By.xpath("//*[@id=\"rich-textarea\"]"));
            WebElement button2 = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div/div[1]/div/div/div/div/div[4]/div/div[1]/div[1]/div[2]"));

            // 9. 두 번째부터 텍스트 입력과 버튼 클릭 반복
            int ii=5;
            for (int j = 1; j < inputText.length; j++) {
                // 각 질문 입력 및 버튼 클릭
                textarea2.clear();
                textarea2.sendKeys(inputText[j]);  // 텍스트 입력
                System.out.println((j + 1) + " 번째 입력된 텍스트: " + inputText[j]);
                button2.click();  // 버튼 클릭
                System.out.println((j + 1) + " 번째 버튼이 클릭되었습니다.");
                Thread.sleep(5000);
                String xpath2 = "/html/body/div[1]/div[1]/div[2]/div[2]/div/div/div[1]/div/div/div/div/div[1]/div/div/div/div[" + ii + "]/div/div[1]/div[1]/div[3]/div[2]/div/p";
                WebElement resultElement2 = driver.findElement(By.xpath(xpath2));
                String resultText2 = resultElement2.getText();
                System.out.println("출력된 값: " + resultText2);
                ii=ii+2;
                Thread.sleep(5000);  // 10초 대기

            }

            // 11. 웹페이지 제목 가져오기
            String pageTitle = driver.getTitle();
            System.out.println("페이지 제목: " + pageTitle);

        } finally {
            // 12. 브라우저 종료
//            driver.quit(); // 브라우저 종료
        }
    }
}
