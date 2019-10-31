import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.server.handler.SubmitElement;

public class automatizacion_login {
	
    public static WebDriver getChromeWebDriver() {
		
    		System.setProperty("webdriver.chrome.driver", "Selenium/chromedriver");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("window-size=1024,1024");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		
		WebDriver driver = new ChromeDriver();
		
		return driver;
	}
    
public static void main(String[] args)throws InterruptedException {
		
		// Abrir página de phpMyAdmin.
	
		WebDriver chrome = automatizacion_login.getChromeWebDriver();
		chrome.get("http://localhost:8888/phpMyAdmin/server_databases.php?db=");
		String actualTitle = chrome.getTitle();
		System.out.println("Actual title is: " + actualTitle);
		

		// ---- Crear la base de datos.
		
		
		WebElement db_nombre = chrome.findElement(By.id("text_create_db"));
		db_nombre.sendKeys("selenium_automation");
		Thread.sleep(2500);

		WebElement submit = chrome.findElement(By.id("buttonGo"));
		submit.click();
		Thread.sleep(3500);
		
	
		
		// ---- Crear tabla 'users'.
		
		WebElement tb_name = chrome.findElement(By.name("table"));
		tb_name.sendKeys("users");
		Thread.sleep(1000);
	
		WebElement btn_table = chrome.findElement(By.xpath("//input[@type='submit']"));
		btn_table.click();
		Thread.sleep(7000);
		
		
		
		
		// Primera columna.
		
		WebElement colUser = chrome.findElement(By.name("field_name[0]"));
		colUser.sendKeys("usuario");
		Thread.sleep(1000);


		WebElement colUserClick = chrome.findElement(By.name("field_type[0]"));
		colUserClick.click();
		Thread.sleep(1000);
		
		WebElement tipoUser = chrome.findElement(By.name("field_type[0]"));
		tipoUser.sendKeys("v");
		Thread.sleep(1000);
		
		WebElement largoUser = chrome.findElement(By.name("field_length[0]"));
		largoUser.sendKeys("50");
		Thread.sleep(1000);
		
		
		
		// Segunda columna.
		
		WebElement colPassword = chrome.findElement(By.name("field_name[1]"));
		colPassword.sendKeys("password");
		Thread.sleep(1000);


		WebElement colClickContra = chrome.findElement(By.name("field_type[1]"));
		colClickContra.click();
		Thread.sleep(1000);
		
		WebElement varcharContra = chrome.findElement(By.name("field_type[1]"));
		varcharContra.sendKeys("v");
		Thread.sleep(1000);
		
		WebElement lenghtCol2 = chrome.findElement(By.name("field_length[1]"));
		lenghtCol2.sendKeys("100");
		Thread.sleep(1000);
		 
		
		
		
		// Tercera Columna.
		
		WebElement colCorreo = chrome.findElement(By.name("field_name[2]"));
		colCorreo.sendKeys("correo");
		Thread.sleep(1000);
		
		WebElement colCorreoClick = chrome.findElement(By.name("field_type[2]"));
		colCorreoClick.click();
        Thread.sleep(1000);
        
        WebElement varcharCorreo = chrome.findElement(By.name("field_type[2]"));
        varcharCorreo.sendKeys("v");
        Thread.sleep(1000);
        
        WebElement largoCorreo = chrome.findElement(By.name("field_length[2]"));
        largoCorreo.sendKeys("100");
        Thread.sleep(1000);   

        
        
        
		// Cuarta columna.
		
        WebElement colNombre = chrome.findElement(By.name("field_name[3]"));
        colNombre.sendKeys("nombre");
		Thread.sleep(1000);
				
        WebElement colNombreClick = chrome.findElement(By.name("field_type[3]"));
        colNombreClick.click();
        Thread.sleep(1000);
        
        WebElement varcharNombre = chrome.findElement(By.name("field_type[3]"));
        varcharNombre.sendKeys("v");
        Thread.sleep(1000);
        
        WebElement largoNombre = chrome.findElement(By.name("field_length[3]"));
        largoNombre.sendKeys("100");
        Thread.sleep(1000);
		
        JavascriptExecutor jsx = (JavascriptExecutor)chrome;
		jsx.executeScript("window.scrollBy(0,450)", "");

		
		// Generar la tabla.
		
		WebElement guardarBtn = chrome.findElement(By.xpath("//input[@type='submit']"));
		guardarBtn.click();
		Thread.sleep(6000);
		
		
		// ---- Registrar a un usuario.
		chrome.get("http://localhost:8888/phpMyAdmin/tbl_change.php?db=selenium_automation&table=users");
		Thread.sleep(1500);
		
		// Rellenar formulario de la base de datos.
		WebElement usuariotxt = chrome.findElement(By.name("fields[multi_edit][0][f8032d5cae3de20fcec887f395ec9a6a]"));
		usuariotxt.sendKeys("alejandro");
		
		WebElement passwordtxt = chrome.findElement(By.name("fields[multi_edit][0][5f4dcc3b5aa765d61d8327deb882cf99]"));
		passwordtxt.sendKeys("Alejandro123");
		
		WebElement correotxt = chrome.findElement(By.name("fields[multi_edit][0][531ac50224f238df5d6efdaf36507cf2]"));
		correotxt.sendKeys("alejandro@correo.com");
		
		WebElement nombretxt = chrome.findElement(By.name("fields[multi_edit][0][7a675883b1c117e267470dce52eba518]"));
		nombretxt.sendKeys("Alejandro Vazquez");
		
		WebElement insertarBtn = chrome.findElement(By.xpath("//input[@type='submit']"));
		insertarBtn.click();
		
		
		
		// ----- Mover archivo de PHP Login a HTDOCS.
		
		File crearCarpeta = new File("/Applications/MAMP/htdocs/selenium-login");
		if(!crearCarpeta.exists()) {
			if(crearCarpeta.mkdir()) {
				System.out.println("Carpeta creada con exito");
			}
		} else {
			System.out.println("Carpeta existente, continuar con la ejecución");
		}
		
		File from = new File("/Users/alejadrovazquez/eclipse-workspace/PracticaExamen/login.php");
		File to = new File("/Applications/MAMP/htdocs/selenium-login/login.php");
		
		try {
			FileHandler.copy(from, to);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		// ---- Abrir archivo de login	
		
		chrome.get("http://localhost:8888/selenium-login/login.php");
		Thread.sleep(3500);
		
		WebElement usuarioLogin = chrome.findElement(By.name("username"));
		usuarioLogin.sendKeys("alejandro");
		
		WebElement passwordLogin = chrome.findElement(By.name("password"));
		passwordLogin.sendKeys("Alejandro123");
		
		WebElement btnLogin = chrome.findElement(By.xpath("//input[@type='submit']"));
		btnLogin.click();
		
		
	}

}
