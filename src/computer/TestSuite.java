package computer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import utilities.Utility;

import java.util.ArrayList;
import java.util.List;

public class TestSuite extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyProductArrangeInAlphaBaticalOrder() throws InterruptedException {

//      1.1 Click on Computer Menu.
//      1.2 Click on Desktop
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Computers']")))
                .moveToElement(driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Desktops']"))).click().build().perform();

//      1.3 Select Sort By position "Name: Z to A"
        WebElement element = driver.findElement(By.xpath("//select[@id='products-orderby']"));
        Select select = new Select(element);
        select.selectByVisibleText("Name: Z to A");

//      1.4 Verify the Product will arrange in Descending order.
        Thread.sleep(5000);
        List<WebElement> productList = driver.findElements(By.xpath("//h2[@class='product-title']//a"));
        List<String> productNames = new ArrayList<>();
        for (WebElement ele : productList) {
            productNames.add(ele.getText());
        }
    }


    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() {
        // 2.1 Click on Computer Menu.
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Computers']"));
        //2.2 Click on Desktop
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Desktops']"));
        //2.3 Select Sort By position "Name: A to Z"
        selectByVisibleTextFromDropDown(By.id("products-orderby"), "Name: A to Z");
        //2.4 Click on "Add To Cart"
        clickOnElement(By.xpath("//button[contains(text(),'Add to cart')]"));
        //2.5 Verify the Text "Build your own computer"
        Assert.assertEquals(getTextFromElement(By.xpath("//h1[contains(text(),'Build your own computer')]")), "Build your own computer");
        //2.6 Select "2.2 GHz Intel Pentium Dual-Core E2200" using Select class
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='product_attribute_1']"), "2.2 GHz Intel Pentium Dual-Core E2200");
        //2.7.Select "8GB [+$60.00]" using Select class.
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='product_attribute_2']"), "8GB [+$60.00]");
        //2.8 Select HDD radio "400 GB [+$100.00]"
        driver.findElement(By.xpath("//label[normalize-space()='400 GB "));
        //2.9 Select OS radio "Vista Premium [+$60.00]"
        driver.findElement(By.xpath("//label[normalize-space()='Vista Home "));
        // 2.10 Check Two Check boxes "Microsoft Office [+$50.00]" and "Total Commander
        driver.findElement(By.xpath("//label[normalize-space()='Microsoft Office "));
        driver.findElement(By.xpath("//label[normalize-space()='Total Commander "));
        //2.11 Verify the price "$1,475.00"
        Assert.assertEquals(getTextFromElement(By.id("price-value-1")), "$1,475.00");
        //2.12 Click on "ADD TO CARD" Button.
        driver.findElement(By.xpath("//button[@id='add-to-cart-button-1']"));
            /*//2.13 Verify the Message "The product has been added to your shopping cart" on Top
            green Bar
            After that close the bar clicking on the cross button*/
        Assert.assertTrue(getTextFromElement(By.className("content")).contains("The product has been added to your shopping cart"));
        clickOnElement(By.className("close"));
        // Step 2.14 to Step 2.37
        mouseHoverToElement(By.linkText("Shopping cart"));
        clickOnElement(By.linkText("GO TO CART"));

        Assert.assertEquals(getTextFromElement(By.xpath("//h1[contains(text(),'Shopping cart')]")), "Shopping cart");

        clearTextFromField(By.xpath("//input[@class='qty-input']"));
        sendTextToElement(By.xpath("//input[@class='qty-input']"), "2");
        clickOnElement(By.name("updatecart"));

        Assert.assertEquals(getTextFromElement(By.xpath("//span[@class='product-subtotal']")), "$2,950.00");

        clickOnElement(By.id("termsofservice"));
        clickOnElement(By.id("checkout"));

        Assert.assertEquals(getTextFromElement(By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]")), "Welcome, Please Sign In!");

        clickOnElement(By.xpath("//button[contains(text(),'Checkout as Guest')]"));

        // Fill the mandatory fields for guest checkout
        sendTextToElement(By.id("BillingNewAddress_FirstName"), "rajvekariya04@gmail.com");
        sendTextToElement(By.id("BillingNewAddress_LastName"), "raj123");
        sendTextToElement(By.id("BillingNewAddress_Email"), "rajvekariya04@gmail.com");
        selectByVisibleTextFromDropDown(By.id("BillingNewAddress_CountryId"), "United Kingdom");
        sendTextToElement(By.id("BillingNewAddress_City"), "London");
        sendTextToElement(By.id("BillingNewAddress_Address1"), "209");
        sendTextToElement(By.id("BillingNewAddress_ZipPostalCode"), "UB60LR");
        sendTextToElement(By.id("BillingNewAddress_PhoneNumber"), "07771699649");
        clickOnElement(By.xpath("//button[@onclick='Billing.save()']"));

        clickOnElement(By.id("shippingoption_1")); // Next Day Air
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));

        clickOnElement(By.id("paymentmethod_1")); // Credit Card
        clickOnElement(By.xpath("//button[@class='button-1 payment-method-next-step-button']"));

        selectByVisibleTextFromDropDown(By.id("CreditCardType"), "Master card");
        sendTextToElement(By.id("CardholderName"), "Raj Vekariya");
        sendTextToElement(By.id("CardNumber"), "5555 5555 5555 4444");
        selectByVisibleTextFromDropDown(By.id("ExpireMonth"), "12");
        selectByVisibleTextFromDropDown(By.id("ExpireYear"), "2025");
        sendTextToElement(By.id("CardCode"), "123");
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));

        Assert.assertEquals(getTextFromElement(By.xpath("//span[@id='payment-method']//span[@class='value']")), "Credit Card");
        Assert.assertEquals(getTextFromElement(By.xpath("//span[@id='shipping-method']//span[@class='value']")), "Next Day Air");
        Assert.assertEquals(getTextFromElement(By.xpath("//span[@class='product-subtotal']")), "$2,950.00");

        clickOnElement(By.xpath("//button[@class='button-1 confirm-order-next-step-button']"));

        Assert.assertEquals(getTextFromElement(By.xpath("//h1[contains(text(),'Thank you')]")), "Thank you");
        Assert.assertTrue(getTextFromElement(By.xpath("//div[@class='section order-completed']//strong")).contains("Your order has been successfully processed!"));
        clickOnElement(By.xpath("//button[@class='button-1 order-completed-continue-button']"));

        Assert.assertEquals(getTextFromElement(By.xpath("//h2[contains(text(),'Welcome to our store')]")), "Welcome to our store");
    }

    @After
    //close the browser
    public void tearDown() {
        closeBrowser();
    }
}





