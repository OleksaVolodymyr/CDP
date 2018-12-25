package com.epam.lab;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.epam.lab.model.User;
import com.epam.lab.pages.GmailLoginPage;

//@Listeners({ HTMLReporter.class,CustomTestListener.class })
public class GmailLoginFailTest {

	@Test()
	public void test(User userModel) throws InterruptedException {
		GmailLoginPage loginPage = new GmailLoginPage();
		loginPage.enterLoginAndSubmit(userModel.getLogin());
		loginPage.enterPasswordAndSubmit(userModel.getPassword());
		Assert.assertFalse(!loginPage.isLogged(userModel.getLogin() + "@gmail.com"));
	}

	/*@DataProvider(name = "users", parallel = true)
	public Iterator<User> getDataFromXML() throws URISyntaxException {
		return Parser.XMLParse("../users.xml", new UsersModel()).getUsers().iterator();
	}

	@AfterMethod
	public void close() {
		WebDriverSingelton.getInstance().quit();
	}*/
}
