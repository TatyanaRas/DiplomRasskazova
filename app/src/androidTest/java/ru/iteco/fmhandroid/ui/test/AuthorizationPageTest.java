package ru.iteco.fmhandroid.ui.test;

import androidx.test.filters.LargeTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;

import ru.iteco.fmhandroid.ui.helper.Data;
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.page.MainPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class AuthorizationPageTest extends AuthorizationPage {

    AuthorizationPage authorizationPage = new AuthorizationPage();
    MainPage mainPage = new MainPage();

  @Test
  @DisplayName("Авторизация с валидными данными")
  @io.qameta.allure.kotlin.Description("Проверка авторизации с валидными данными")
  public void authorizationValidTest() {
      authorizationPage.inputValidLogin(Data.getValidLogin());
      authorizationPage.inputValidPassword(Data.getValidPassword());
      authorizationPage.signInButtonClick();
      mainPage.viewMainPage();
  }

    @Test
    @DisplayName("Авторизация с невалидным логином и валидным паролем")
    @io.qameta.allure.kotlin.Description("Проверка авторизации: невалидный логин и валидный пароль")
    public void authorizationInValidLogTest() {
        authorizationPage.inputInvalidLogin(Data.getInvalidLogin());
        authorizationPage.inputValidPassword(Data.getValidPassword());
        authorizationPage.signInButtonClick();
        authorizationPage.errorMessageInvalid();
  }

    @Test
    @DisplayName("Авторизация с валидным паролем и поле Login пустое")
    @io.qameta.allure.kotlin.Description("Проверка авторизации: поле Login пустое и валидный пароль")
    public void authorizationEmptyLogValidPasTes() {
        authorizationPage.emptyLogin();
        authorizationPage.inputInvalidPassword(Data.getInvalidPassword());
        authorizationPage.signInButtonClick();
        authorizationPage.errorMessageEmpty();
    }

    @Test
    @DisplayName("Авторизация с невалидным паролем и валидным логином")
    @io.qameta.allure.kotlin.Description("Проверка авторизации: валидный логин и невалидный пароль")
    public void authorizationInValidPasTest() {
        authorizationPage.inputValidLogin(Data.getValidLogin());
        authorizationPage.inputInvalidPassword(Data.getInvalidPassword());
        authorizationPage.signInButtonClick();
        authorizationPage.errorMessageInvalid();
    }


    @Test
    @DisplayName("Авторизация с пустое поле Password и валидным логином")
    @io.qameta.allure.kotlin.Description("Проверка авторизации: валидный логин и пустое поле Password")
    public void authorizationEmptyPasValidLogTes() {
        authorizationPage.inputValidLogin(Data.getValidLogin());
        authorizationPage.emptyPassword();
        authorizationPage.signInButtonClick();
        authorizationPage.errorMessageEmpty();
    }

    @Test
    @DisplayName("Авторизация с пустыми полями Login и Password")
    @io.qameta.allure.kotlin.Description("Проверка авторизации: оставить пустые поля Login и Password")
    public void authorizationEmptyPasAndLogTes() {
        authorizationPage.emptyLogin();
        authorizationPage.emptyPassword();
        authorizationPage.signInButtonClick();
        authorizationPage.errorMessageEmpty();
    }
}





