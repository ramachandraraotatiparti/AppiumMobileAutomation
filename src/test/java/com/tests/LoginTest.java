package com.tests;

import base.BaseTest;
import com.automate.constants.StringConstants;
import com.automate.customannotations.FrameworkAnnotation;
import com.automate.entity.TestData;
import com.automate.enums.CategoryType;
import com.automate.pages.LoginPage;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class LoginTest extends BaseTest {

  LoginPage loginPage;

  @BeforeMethod
  public void initialize() {
    loginPage = new LoginPage();
  }

  @Test(description = "Incorrect Username and Password combination")
  public void invalidLogin(TestData data) {
    loginPage.setUsername(data.getLoginData().getLoginUsername())
      .setPassword(data.getLoginData().getLoginPassword())
      .tapOnLogin();
    String invalidLoginErrorMessage = loginPage.getErrorText();

    Assert.assertEquals(invalidLoginErrorMessage, StringConstants.INVALID_LOGIN_ERROR_MESSAGE, "Assertion for Invalid login error");
  }

  @Test(description = "Correct Username and Password combination")
  public void validLogin(TestData data) {
    String productPageTitle = loginPage.setUsername(data.getLoginData().getLoginUsername())
      .setPassword(data.getLoginData().getLoginPassword())
      .tapOnLogin()
      .getProductPageTitle();

    Assert.assertEquals(productPageTitle, StringConstants.PRODUCT_PAGE_TITLE, "Assertion for valid login");
  }
}
