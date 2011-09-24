package org.springframework.samples.travel.selenium;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ScreenFlowTest extends SeleneseTestCase {
	private String browserStartCommand;

	@Before
	public void setUp() throws Exception {
		//browserStartCommand =
		// "*firefox /Users/sano/Applications/Firefox.app/Contents/MacOS/firefox-bin";
		browserStartCommand = "*safari";
		//browserStartCommand = "*firefox";
		selenium = new DefaultSelenium("localhost", 4444, browserStartCommand,
				"http://localhost:8080/travel/");
		selenium.start();
	}

	@Test
	public void testSearchFlow() throws Exception {
		//
		selenium.open("/travel/");
		assertEquals("Welcome to Spring Travel", selenium.getText("//h1"));
		captureEntirePageScreenshot("testSearchFlow01.png",
				"background=#ffffff;");

		//
		selenium.click("link=Start your Spring Travel experience");
		selenium.waitForPageToLoad("30000");
		assertEquals("Search Hotels", selenium.getText("//h1"));
		captureEntirePageScreenshot("testSearchFlow02.png",
				"background=#ffffff;");

		//
		selenium.click("searchString");
		selenium.type("searchString", "NY");
		selenium.click("//button[@type='submit']");
		selenium.waitForPageToLoad("30000");
		assertEquals("Hotel Results", selenium.getText("//h1"));
		assertEquals(4, selenium.getXpathCount(
				"//div[@id='hotelResults']/table/tbody/tr").intValue());
		// assertion by row
		assertEquals(
				"W Hotel Union Square, Manhattan NY, NY, USA 10011 View Hotel",
				selenium.getText("//div[@id='hotelResults']/table/tbody/tr[1]"));
		// assertion by column
		assertEquals("W Hotel", selenium
				.getText("//div[@id='hotelResults']/table/tbody/tr[1]/td[1]"));
		assertEquals("Union Square, Manhattan", selenium
				.getText("//div[@id='hotelResults']/table/tbody/tr[1]/td[2]"));
		assertEquals("NY, NY, USA", selenium
				.getText("//div[@id='hotelResults']/table/tbody/tr[1]/td[3]"));
		assertEquals("10011", selenium
				.getText("//div[@id='hotelResults']/table/tbody/tr[1]/td[4]"));
		captureEntirePageScreenshot("testSearchFlow03.png",
				"background=#ffffff");

		//
		selenium.click("changeSearchLink");
		selenium.waitForCondition(
				"selenium.isElementPresent(\"searchString\")", "30000");
		assertEquals("Hotel Results", selenium.getText("//h1"));
		captureEntirePageScreenshot("testSearchFlow04.png",
				"background=#ffffff");

		//
		selenium.type("searchString", "Ritz");
		selenium.click("//button[@type='submit']");
		selenium.waitForPageToLoad("30000");
		assertEquals("Hotel Results", selenium.getText("//h1"));
		assertEquals(2, selenium.getXpathCount(
				"//div[@id='hotelResults']/table/tbody/tr").intValue());
		// assertion by row
		assertEquals(
				"Ritz Carlton 1228 Sherbrooke St West Montreal, Quebec, Canada H3G1H6 View Hotel",
				selenium.getText("//div[@id='hotelResults']/table/tbody/tr[1]"));
		captureEntirePageScreenshot("testSearchFlow05.png",
				"background=#ffffff");

		//
		selenium.click("changeSearchLink");
		selenium.waitForCondition(
				"selenium.isElementPresent(\"searchString\")", "30000");
		assertEquals("Hotel Results", selenium.getText("//h1"));
		captureEntirePageScreenshot("testSearchFlow06.png",
				"background=#ffffff");

		//
		selenium.select("pageSize", "label=20");
		selenium.type("searchString", "LA");
		selenium.click("//button[@type='submit']");
		selenium.waitForPageToLoad("30000");
		assertEquals("Hotel Results", selenium.getText("//h1"));
		assertEquals(8, selenium.getXpathCount(
				"//div[@id='hotelResults']/table/tbody/tr").intValue());
		captureEntirePageScreenshot("testSearchFlow07.png",
				"background=#ffffff");
	}

	@Test
	public void testRegisterFlow() throws Exception {

		selenium.open("/travel/");
		assertEquals("Welcome to Spring Travel", selenium.getText("//h1"));
		captureEntirePageScreenshot("testRegisterFlow01.png",
				"background=#ffffff");

		selenium.click("//img[@alt='Spring Travel']");
		selenium.waitForPageToLoad("30000");
		assertEquals("Welcome to Spring Travel", selenium.getText("//h1"));
		captureEntirePageScreenshot("testRegisterFlow02.png",
				"background=#ffffff");

		selenium.click("link=Start your Spring Travel experience");
		selenium.waitForPageToLoad("30000");
		assertEquals("Search Hotels", selenium.getText("//h1"));
		captureEntirePageScreenshot("testRegisterFlow03.png",
				"background=#ffffff");

		selenium.click("searchString");
		selenium.type("searchString", "Ritz");
		selenium.click("//button[@type='submit']");
		selenium.waitForPageToLoad("30000");
		assertEquals("Hotel Results", selenium.getText("//h1"));
		captureEntirePageScreenshot("testRegisterFlow04.png",
				"background=#ffffff");

		selenium.click("//a[@href='hotels/19']");
		selenium.waitForPageToLoad("30000");
		assertEquals("Ritz Carlton", selenium.getText("//h1"));
		captureEntirePageScreenshot("testRegisterFlow05.png",
				"background=#ffffff");

		selenium.click("//button[@type='submit']");
		selenium.waitForPageToLoad("30000");
		assertEquals(true, selenium.isTextPresent("Login Information"));
		captureEntirePageScreenshot("testRegisterFlow06.png",
				"background=#ffffff");

		selenium.click("j_username");
		selenium.type("j_username", "scott");
		selenium.type("j_password", "rochester");
		selenium.click("submit");
		selenium.waitForPageToLoad("30000");
		assertEquals("Ritz Carlton", selenium
				.getText("//div[@id='bookingForm']/div/h3"));
		assertEquals("Book Hotel", selenium
				.getText("//form[@id='booking']/fieldset/legend"));
		captureEntirePageScreenshot("testRegisterFlow07.png",
				"background=#ffffff");

		selenium.type("checkinDate", (new SimpleDateFormat("MM/dd/yyyy"))
				.format(new Date()));
		selenium.type("checkoutDate", (new SimpleDateFormat("MM/dd/yyyy"))
				.format(new Date()));
		selenium.type("creditCard", "1111111110222226");
		selenium.type("creditCardName", "NSEG11");
		selenium.select("creditCardExpiryYear", "label=2011");
		selenium.click("proceed");
		selenium.waitForPageToLoad("30000");
		assertEquals("Ritz Carlton", selenium
				.getText("//div[@id='bookingForm']/div/h3"));
		assertEquals("Confirm Booking Details", selenium
				.getText("//form[@id='confirm']/fieldset/legend"));
		captureEntirePageScreenshot("testRegisterFlow08.png",
				"background=#ffffff");

		selenium.click("_eventId_confirm");
		selenium.waitForPageToLoad("30000");
		assertEquals("Search Hotels", selenium.getText("//h1"));
		captureEntirePageScreenshot("testRegisterFlow09.png",
				"background=#ffffff");

		selenium.click("link=Logout");
		selenium.waitForPageToLoad("30000");
		assertEquals("Logout", selenium.getText("//h1"));
		captureEntirePageScreenshot("testRegisterFlow10.png",
				"background=#ffffff");

		selenium.click("//img[@alt='Spring Travel']");
		selenium.waitForPageToLoad("30000");
		assertEquals("Welcome to Spring Travel", selenium.getText("//h1"));
		captureEntirePageScreenshot("testRegisterFlow11.png",
				"background=#ffffff");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}

	private void captureEntirePageScreenshot(String filename, String kwargs) {
		String captureImgDir = "/Users/sano/tmp/image";
		if (browserStartCommand.startsWith("*firefox")) {
			selenium.captureEntirePageScreenshot(captureImgDir + filename,
					kwargs);
		}
	}
}
