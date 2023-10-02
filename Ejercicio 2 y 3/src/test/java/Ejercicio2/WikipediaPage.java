package Ejercicio2;

import org.openqa.selenium.*;
import org.apache.commons.io.FileUtils;

import java.io.File;

public class WikipediaPage {
    private WebDriver driver;

    public WikipediaPage(WebDriver driver) {
        this.driver = driver;
    }

    private By contenidoTexto = By.xpath("//div[@id='bodyContent']/div[3]/div/p[33]");

    public boolean verificarTextoEnContenido(String texto) {
        WebElement bodyElement = driver.findElement(contenidoTexto);
        return bodyElement.getText().contains(texto);
    }

    public void tomarCapturaDePantalla(String destino, String xpath) {
        try {
            WebElement elemento = driver.findElement(By.xpath(xpath));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elemento);

            Thread.sleep(2000);

            File screenshotFile = elemento.getScreenshotAs(OutputType.FILE);
            File destinoArchivo = new File(destino);
            FileUtils.copyFile(screenshotFile, destinoArchivo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

