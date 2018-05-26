package es.codeurjc.ais.tictactoe;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.ChromeDriverManager;

	class pruebasdesistema {
		WebDriver driver1, driver2;

/*		¡No conseguimos que se ejecute automaticamente, por tanto, ejecutar manualmente WebApp!
 		@BeforeClass
		static void setUpBeforeClass() throws Exception {
			ChromeDriverManager.getInstance().setup();
			WebApp.start();
			
		}

		@AfterClass
		static void tearDownAfterClass() throws Exception {
			WebApp.stop();
		}*/

		@BeforeEach
		void setUp() throws Exception {
			ChromeDriverManager.getInstance().setup();
			driver1 = new ChromeDriver();
			driver2 = new ChromeDriver();
			driver1.get("http://localhost:8080/");
			driver2.get("http://localhost:8080/");
			driver1.findElement(By.id("nickname")).sendKeys("Plam");
			driver1.findElement(By.id("startBtn")).click();
			driver2.findElement(By.id("nickname")).sendKeys("Cris");
			driver2.findElement(By.id("startBtn")).click();

		}

		@AfterEach
		void tearDown() throws Exception {
			driver1.quit();
			driver2.quit();
		}

		
		@Test
		void testPlayer1Winner() {
			driver1.findElement(By.id("cell-0")).click();
			driver2.findElement(By.id("cell-1")).click();
			driver1.findElement(By.id("cell-3")).click();
			driver2.findElement(By.id("cell-2")).click();
			driver1.findElement(By.id("cell-6")).click();

			assertEquals(driver1.switchTo().alert().getText(),"Plam wins! Cris looses.");
			assertEquals(driver2.switchTo().alert().getText(),"Plam wins! Cris looses.");
		}
		
		@Test
		void testPlayer2Winner() {
			driver1.findElement(By.id("cell-0")).click();
			driver2.findElement(By.id("cell-3")).click();
			driver1.findElement(By.id("cell-1")).click();
			driver2.findElement(By.id("cell-4")).click();
			driver1.findElement(By.id("cell-6")).click();
			driver2.findElement(By.id("cell-5")).click();

			assertEquals(driver1.switchTo().alert().getText(),"Cris wins! Plam looses.");
			assertEquals(driver2.switchTo().alert().getText(),"Cris wins! Plam looses.");
		}
		
		@Test
		void testDraw() {
			driver1.findElement(By.id("cell-0")).click();
			driver2.findElement(By.id("cell-1")).click();
			driver1.findElement(By.id("cell-2")).click();
			driver2.findElement(By.id("cell-4")).click();
			driver1.findElement(By.id("cell-3")).click();
			driver2.findElement(By.id("cell-8")).click();
			driver1.findElement(By.id("cell-5")).click();
			driver2.findElement(By.id("cell-6")).click();
			driver1.findElement(By.id("cell-7")).click();

			assertEquals(driver1.switchTo().alert().getText(),"Draw!");
			assertEquals(driver2.switchTo().alert().getText(),"Draw!");
		}

	}


