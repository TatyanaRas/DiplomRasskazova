package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static ru.iteco.fmhandroid.ui.helper.Data.validLogin;
import static ru.iteco.fmhandroid.ui.helper.Data.validPassword;
import static ru.iteco.fmhandroid.ui.helper.PageLoading.waitDisplayed;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.PerformException;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

public class AuthorizationAssistant {


    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private final int mainPage = R.id.container_list_news_include_on_fragment_main;
    private final int loginText = R.id.login_text_input_layout;
    private final int passwordText = R.id.password_text_input_layout;
    private final int signInButton = R.id.enter_button;
    private final int allNews = R.id.all_news_text_view;

    @Before
    public void bootablePage() {
        Allure.step("Шаг 1: Запустить приложение. Открывается главная страница приложения");
        try {
            onView(isRoot()).perform(waitDisplayed(mainPage, 7000));
            onView(withId(mainPage)).check(matches(isDisplayed()));

        } catch (PerformException e) {
            Allure.step("Шаг 1: Запустить приложение. Открывается страница авторизации");
            onView(isRoot()).perform(waitDisplayed(loginText, 7000));

            onView(withId(loginText)).perform(click());
            onView(withHint("Login")).perform(typeText(validLogin), closeSoftKeyboard());

            onView(withId(passwordText)).check(matches(isDisplayed())).perform(click());
            onView(withHint("Password")).perform(typeText(validPassword), closeSoftKeyboard());

            onView(withId(signInButton)).check(matches(isDisplayed())).perform(click());

            onView(isRoot()).perform(waitDisplayed(allNews, 7000));
        }
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
