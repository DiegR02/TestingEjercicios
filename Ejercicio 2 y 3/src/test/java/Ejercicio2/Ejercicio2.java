package Ejercicio2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Ejercicio2 {

    @Test
    public void test1() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.google.com/");

        GooglePage googlePage = new GooglePage(driver);

        //Paso 1
        driver.get("https://www.google.com/");
        googlePage.aceptarCookies();
        System.out.println("Se ha realizado el primer paso");

        //Paso 2
        googlePage.buscarEnGoogle("Automatización");

        //Paso 3
        Thread.sleep(3000);
        googlePage.hacerClickEnResultado();

        WikipediaPage wikipediaPage = new WikipediaPage(driver);

        //Paso 4
        googlePage.hacerClickEnEnlaceWikipedia();

        //Paso 5
        String buscarTexto = "primer proceso";
        boolean textoEncontrado = wikipediaPage.verificarTextoEnContenido(buscarTexto);

        //Paso 6
        if (textoEncontrado) {
            System.out.println("Se encontró el texto '" + buscarTexto + "' en la página de Wikipedia.");
            String ruta= "screenshot/captura.png";
            String xpath= "//div[@id='bodyContent']/div[3]/div/p[33]";
            wikipediaPage.tomarCapturaDePantalla(ruta, xpath);
        } else {
            System.out.println("No se encontró el texto '" + buscarTexto + "' en la página de Wikipedia.");
        }

        driver.quit();
    }
}
