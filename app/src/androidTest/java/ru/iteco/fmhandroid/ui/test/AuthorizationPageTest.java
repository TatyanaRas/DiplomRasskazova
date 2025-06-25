package ru.iteco.fmhandroid.ui.test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.helper.Data.invalidLogin;
import static ru.iteco.fmhandroid.ui.helper.Data.invalidPassword;
import static ru.iteco.fmhandroid.ui.helper.Data.validLogin;
import static ru.iteco.fmhandroid.ui.helper.Data.validPassword;
import static ru.iteco.fmhandroid.ui.helper.PageLoading.waitDisplayed;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.helper.Data;


@RunWith(AllureAndroidJUnit4.class)

public class AuthorizationPageTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);


    public static ViewInteraction loginText = onView((Matchers.allOf(withHint("Login"), withParent(withParent(withId(R.id.login_text_input_layout))))));
    public static ViewInteraction passwordText = onView(Matchers.allOf(withHint("Password"), withParent(withParent(withId(R.id.password_text_input_layout)))));
    public static ViewInteraction signInButton = onView(Matchers.allOf(withId(R.id.enter_button), withText("SIGN IN"), withContentDescription("Save"), withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class)))));
    public static ViewInteraction image_Button = onView(withId(R.id.authorization_image_button));
    public static ViewInteraction Log_out = onView(withText("Log out"));

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
    public void authorizationValid() {
        Allure.step("Шаг 1: Запуск приложения. Открывается страница авторизации");
        try {
            onView(isRoot()).perform(waitDisplayed(R.id.login_text_input_layout, 7000));
        } catch (PerformException e) {
            Allure.step("Шаг 2: Запустить приложение. Открывается главная страница приложения");
            onView(isRoot()).perform(waitDisplayed(R.id.container_list_news_include_on_fragment_main, 7000));

            Allure.step("Шаг 2: Выход из приложения, изображение с человечком");
            ViewInteraction autoButton = image_Button;
            autoButton.check(matches(isDisplayed()));
            autoButton.perform(click());

            Allure.step("Шаг 3: Нажать на кнопку выход");
            ViewInteraction exitButton = Log_out;
            exitButton.check(matches(isDisplayed()));
            exitButton.perform(click());

        }

    }

    @Test
    @DisplayName("ТС-02 Авторизация с валидными данными")
    @io.qameta.allure.kotlin.Description("Проверка авторизации с валидными данными")

    public void authorizationValidTest() {


        Allure.step("Шаг 4: Ввести валидный логин");
        ViewInteraction loginField = loginText;
        loginField.check(matches(isDisplayed()));
        loginField.perform(click());
        onView(withHint("Login")).perform(typeText(validLogin), closeSoftKeyboard());


        Allure.step("Шаг 5: Ввести валидный пароль");
        ViewInteraction passwordField = passwordText;
        passwordField.check(matches(isDisplayed()));
        passwordField.perform(click());
        onView(withHint("Password")).perform(typeText(validPassword), closeSoftKeyboard());

        Allure.step("Шаг 6: Нажать на кнопку вход");
        ViewInteraction enterButton = signInButton;
        enterButton.check(matches(isDisplayed()));
        enterButton.perform(click());

        Allure.step("Успешная авторизация. Отображается главная страница приложения");
        ViewInteraction mainPage = onView(isRoot()).perform(waitDisplayed(R.id.container_list_news_include_on_fragment_main, 7000));
        mainPage.check(matches(isDisplayed()));

    }


    @Test
    @DisplayName("ТС-05.2 Авторизация с невалидными данными")
    @io.qameta.allure.kotlin.Description("Проверка авторизации: невалидный логин и валидный пароль")


    public void authorizationInValidLogTest() {

        Allure.step("Шаг 4: Ввести не валидный логин");
        ViewInteraction loginField = loginText;
        loginField.check(matches(isDisplayed()));
        loginField.perform(click());
        onView(withHint("Login")).perform(typeText(invalidLogin), closeSoftKeyboard());

        Allure.step("Шаг 5: Ввести валидный пароль");
        ViewInteraction passwordField = passwordText;
        passwordField.check(matches(isDisplayed()));
        passwordField.perform(click());
        onView(withHint("Password")).perform(typeText(validPassword), closeSoftKeyboard());

        Allure.step("Шаг 6: Нажать на кнопку вход");
        ViewInteraction enterButton = signInButton;
        enterButton.check(matches(isDisplayed()));
        enterButton.perform(click());


        Allure.step("Всплывающее сообщение об ошибке авторизации");
        onView(withText(Data.AUTHENTICATION_ERROR_MESSAGE)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("ТС-05.1 Авторизация с невалидными данными")
    @io.qameta.allure.kotlin.Description("Проверка авторизации: валидный логин и невалидный пароль")


    public void authorizationInValidPasTest() {

        Allure.step("Шаг 2: Ввести валидный логин");

        ViewInteraction loginField = loginText;
        loginField.check(matches(isDisplayed()));
        loginField.perform(click());
        onView(withHint("Login")).perform(typeText(validLogin), closeSoftKeyboard());

        Allure.step("Шаг 3: Ввести невалидный пароль");
        ViewInteraction passwordField = passwordText;
        passwordField.check(matches(isDisplayed()));
        passwordField.perform(click());
        onView(withHint("Password")).perform(typeText(invalidPassword), closeSoftKeyboard());

        Allure.step("Шаг 4: Нажать на кнопку вход");
        ViewInteraction enterButton = signInButton;
        enterButton.check(matches(isDisplayed()));
        enterButton.perform(click());


        Allure.step("Всплывающее сообщение об ошибке авторизации");
        onView(withText(Data.AUTHENTICATION_ERROR_MESSAGE)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("ТС-05.2 Авторизация с невалидными данными")

    @io.qameta.allure.kotlin.Description("Проверка авторизации: пустой логин и валидный пароль")


    public void authorizationEmptyLogValidPasTest() {


        Allure.step("Шаг 3: Ввести валидный пароль");
        ViewInteraction passwordField = passwordText;
        passwordField.check(matches(isDisplayed()));
        passwordField.perform(click());
        onView(withHint("Password")).perform(typeText(validPassword), closeSoftKeyboard());

        Allure.step("Шаг 4: Нажать на кнопку вход");
        ViewInteraction enterButton = signInButton;
        enterButton.check(matches(isDisplayed()));
        enterButton.perform(click());


        Allure.step("Всплывающее сообщение об ошибке авторизации");
        onView(withText(Data.EMPTY_FIELDS_ERROR_MESSAGE)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }


    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}



