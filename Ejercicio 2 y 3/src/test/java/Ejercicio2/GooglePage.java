package Ejercicio2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GooglePage {
    private WebDriver driver;

    public GooglePage(WebDriver driver) {
        this.driver = driver;
    }

    private By aceptarTodoButton = By.xpath("//div[text()='Aceptar todo']");
    private By searchBar = By.xpath("//textarea[@title='Buscar']");
    private By searchResultLink = By.xpath("//div[@role='presentation']/ul/li[1]");
    private By wikipediaLink = By.xpath("//cite[text()='https://es.wikipedia.org']/span[text()=' › wiki › Automatización']");

    public void aceptarCookies() {
        driver.findElement(aceptarTodoButton).click();
    }

    public void buscarEnGoogle(String texto) {
        WebElement textarea = driver.findElement(searchBar);
        textarea.sendKeys(texto);
    }

    public void hacerClickEnResultado() throws InterruptedException {
        driver.findElement(searchResultLink).click();
    }

    public void hacerClickEnEnlaceWikipedia() {
        driver.findElement(wikipediaLink).click();
    }
}
