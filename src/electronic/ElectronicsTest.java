package electronic;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class ElectronicsTest extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }


    @Test
    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully() {
        mouseHoverToElement(By.linkText("Electronics"));
        mouseHoverToElementAndClick(By.linkText("Cell phones"));
        Assert Assert = null;
        Assert.assertEquals(getTextFromElement(By.xpath("//h1[text()='Cell phones']")), "Cell phones");
    }

    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() {
        mouseHoverToElement(By.linkText("Electronics"));
        mouseHoverToElementAndClick(By.linkText("Cell phones"));
        Assert.assertEquals(getTextFromElement(By.xpath("//h1[text()='Cell phones']")), "Cell phones");

        clickOnElement(By.className("viewmode-icon-list"));
        clickOnElement(By.linkText("Nokia Lumia 1020"));
        Assert.assertEquals(getTextFromElement(By.xpath("//h1[contains(text(),'Nokia Lumia 1020')]")), "Nokia Lumia 1020");
        Assert.assertEquals(getTextFromElement(By.xpath("//span[@id='price-value-1']")), "$349.00");

        clearTextFromField(By.id("product_enteredQuantity_20"));
        sendTextToElement(By.id("product_enteredQuantity_20"), "2");
        clickOnElement(By.id("add-to-cart-button-20"));

        Assert.assertTrue(getTextFromElement(By.className("content")).contains("The product has been added to your shopping cart"));
        clickOnElement(By.className("close"));

        mouseHoverToElement(By.linkText("Shopping cart"));
        clickOnElement(By.linkText("GO TO CART"));

        Assert.assertEquals(getTextFromElement(By.xpath("//h1[contains(text(),'Shopping cart')]")), "Shopping cart");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@class='qty-input']")).getAttribute("value"), "2");
        Assert.assertEquals(getTextFromElement(By.xpath("//span[@class='product-subtotal']")), "$698.00");

        clickOnElement(By.id("termsofservice"));
        clickOnElement(By.id("checkout"));

        Assert.assertEquals(getTextFromElement(By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]")), "Welcome, Please Sign In!");

        clickOnElement(By.xpath("//button[contains(text(),'Register')]"));

        Assert.assertEquals(getTextFromElement(By.xpath("//h1[contains(text(),'Register')]")), "Register");

        sendTextToElement(By.id("FirstName"), "John");
        sendTextToElement(By.id("LastName"), "Doe");
        sendTextToElement(By.id("Email"), "johndoe@example.com");
        sendTextToElement(By.id("Password"), "password123");
        sendTextToElement(By.id("ConfirmPassword"), "password123");
        clickOnElement(By.id("register-button"));

        Assert.assertEquals(getTextFromElement(By.className("result")), "Your registration completed");
        clickOnElement(By.xpath("//a[contains(text(),'Continue')]"));

        Assert.assertEquals(getTextFromElement(By.xpath("//h1[contains(text(),'Shopping cart')]")), "Shopping cart");

        clickOnElement(By.id("termsofservice"));
        clickOnElement(By.id("checkout"));

        // Fill the mandatory fields for checkout
        sendTextToElement(By.id("BillingNewAddress_FirstName"), "John");
        sendTextToElement(By.id("BillingNewAddress_LastName"), "Doe");
        sendTextToElement(By.id("BillingNewAddress_Email"), "johndoe@example.com");
        selectByVisibleTextFromDropDown(By.id("BillingNewAddress_CountryId"), "United States");
        sendTextToElement(By.id("BillingNewAddress_City"), "New York");
        sendTextToElement(By.id("BillingNewAddress_Address1"), "123 Main St");
        sendTextToElement(By.id("BillingNewAddress_ZipPostalCode"), "10001");
        sendTextToElement(By.id("BillingNewAddress_PhoneNumber"), "1234567890");
        clickOnElement(By.xpath("//button[@onclick='Billing.save()']"));

        clickOnElement(By.id("shippingoption_2")); // 2nd Day Air
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));

        clickOnElement(By.id("paymentmethod_1")); // Credit Card
        clickOnElement(By.xpath("//button[@class='button-1 payment-method-next-step-button']"));

        selectByVisibleTextFromDropDown(By.id("CreditCardType"), "Visa");
        sendTextToElement(By.id("CardholderName"), "John Doe");
        sendTextToElement(By.id("CardNumber"), "4111111111111111");
        selectByVisibleTextFromDropDown(By.id("ExpireMonth"), "12");
        selectByVisibleTextFromDropDown(By.id("ExpireYear"), "2025");
        sendTextToElement(By.id("CardCode"), "123");
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));

        Assert.assertEquals(getTextFromElement(By.xpath("//span[@id='payment-method']//span[@class='value']")), "Credit Card");
        Assert.assertEquals(getTextFromElement(By.xpath("//span[@id='shipping-method']//span[@class='value']")), "2nd Day Air");
        Assert.assertEquals(getTextFromElement(By.xpath("//span[@class='product-subtotal']")), "$698.00");

        clickOnElement(By.xpath("//button[@class='button-1 confirm-order-next-step-button']"));

        Assert.assertEquals(getTextFromElement(By.xpath("//h1[contains(text(),'Thank you')]")), "Thank you");
        Assert.assertTrue(getTextFromElement(By.xpath("//div[@class='section order-completed']//strong")).contains("Your order has been successfully processed!"));
        clickOnElement(By.xpath("//button[@class='button-1 order-completed-continue-button']"));

        Assert.assertEquals(getTextFromElement(By.xpath("//h2[contains(text(),'Welcome to our store')]")), "Welcome to our store");
        clickOnElement(By.linkText("Log out"));

        Assert.assertEquals(driver.getCurrentUrl(), "https://demo.nopcommerce.com/");
    }
    @After
    //close the browser
    public void tearDown() {
        closeBrowser();
    }
}
