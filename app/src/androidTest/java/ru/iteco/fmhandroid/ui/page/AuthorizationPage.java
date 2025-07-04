package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.helper.Data.emptyFieldsErrorMessage;
import static ru.iteco.fmhandroid.ui.helper.Data.invalidErrorMessage;
import static ru.iteco.fmhandroid.ui.helper.PageLoading.waitDisplayed;

import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.PerformException;

import org.hamcrest.Matchers;
import org.junit.Before;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;


public class AuthorizationPage extends AuthorizationAssistant {
    private final int mainPage = R.id.container_list_news_include_on_fragment_main;
    private final int loginText = R.id.login_text_input_layout;
    private final int passwordText = R.id.password_text_input_layout;
    private final int signInButton = R.id.enter_button;
    private final int imageButton = R.id.authorization_image_button;
    private final String LogOut = "Log out";

    private View decorView;

    @Before
    public void setUp() {
        mActivityScenarioRule.getScenario().onActivity(new ActivityScenario.ActivityAction<AppActivity>() {
            @Override
            public void perform(AppActivity activity) {
                decorView = activity.getWindow().getDecorView();
            }
        });
    }

    @Before
    public void authorizationMain() {
        Allure.step("Шаг 1: Запуск приложения. Открывается страница авторизации");
        try {
            onView(isRoot()).perform(waitDisplayed(loginText, 7000));
        } catch (PerformException e) {
            Allure.step("Шаг 1: Запустить приложение. Открывается главная страница");
            onView(isRoot()).perform(waitDisplayed(mainPage, 7000));

            Allure.step("Шаг 2: Нажать на изображение человечка" + imageButton);
            onView(withId(imageButton)).check(matches(isDisplayed())).perform(click());

            Allure.step("Шаг 3: Нажать на кнопку выхода" + LogOut);
            onView(withText(LogOut)).check(matches(isDisplayed())).perform(click());
        }
    }

    public final int getLoginTextId() {
        return loginText;
    }

    public final int getPasswordTextId() {
        return passwordText;
    }

    public final int getSignInButton() {
        return signInButton;
    }

    public final String getErrorMessageEmpty() {
        return emptyFieldsErrorMessage;
    }

    public final String getErrorMessageInvalid() {
        return invalidErrorMessage;
    }

    public void inputValidLogin(String validLogin) {
        Allure.step("Шаг 4: Ввести в поле Login валидные данные");
        onView(withId(getLoginTextId()))
                .check(matches(isDisplayed()))
                .perform(click());
        onView(withHint("Login")).perform(typeText(validLogin), closeSoftKeyboard());
    }

    public void inputValidPassword(String validPassword) {
        Allure.step("Шаг 5: Ввести в поле Password валидные данные");
        onView(withId(getPasswordTextId()))
                .check(matches(isDisplayed()))
                .perform(click());
        onView(withHint("Password")).perform(typeText(validPassword), closeSoftKeyboard());
    }

    public void signInButtonClick() {
        Allure.step("Шаг 6: Нажать на кнопку SIGN IN");
        onView(withId(getSignInButton()))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void inputInvalidLogin(String invalidLogin) {
        Allure.step("Шаг 7: Ввести в поле Login невалидные данные");
        onView(withId(getLoginTextId()))
                .check(matches(isDisplayed()))
                .perform(click());
        onView(withHint("Login")).perform(typeText(invalidLogin), closeSoftKeyboard());
    }

    public void inputInvalidPassword(String invalidPassword) {
        Allure.step("Шаг 8: Ввести в поле Password невалидные данные");
        onView(withId(getPasswordTextId()))
                .check(matches(isDisplayed()))
                .perform(click());
        onView(withHint("Password")).perform(typeText(invalidPassword), closeSoftKeyboard());

    }

    public void emptyLogin() {
        Allure.step("Шаг 9: Оставить поле Login пустым");
        onView(withId(getLoginTextId()))
                .check(matches(isDisplayed()));
    }

    public void emptyPassword() {
        Allure.step("Шаг 10: Оставить поле Password пустым");
        onView(withId(getPasswordTextId()))
                .check(matches(isDisplayed()));
    }

    public void errorMessageEmpty() {
        Allure.step("Отображение всплывающего сообщения об ошибке авторизации");
        onView(withText(getErrorMessageEmpty()))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }

    public void errorMessageInvalid() {
        Allure.step("Отображение всплывающего сообщения об ошибке авторизации");
        onView(withText(getErrorMessageInvalid()))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }

}


