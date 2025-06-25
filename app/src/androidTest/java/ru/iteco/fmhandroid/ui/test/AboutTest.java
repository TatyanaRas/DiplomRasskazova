package ru.iteco.fmhandroid.ui.test;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.helper.PageLoading.waitDisplayed;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class AboutTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);


    @Before
    public void mainPage() {
        Allure.step("Шаг 1: Запуск приложения");
        onView(isRoot()).perform(waitDisplayed(R.id.container_list_news_include_on_fragment_main, 7000));

        Allure.step("Шаг 2: Нажать на меню с изображением три полосочки");
        ViewInteraction aboutPage = onView(withId(R.id.main_menu_image_button));
        aboutPage.check(matches(isDisplayed()));
        aboutPage.perform(click());

        Allure.step("Шаг 3: Нажать на текст About");
        ViewInteraction aboutApplication = onView(withText("About")).check(matches(isDisplayed()));
        aboutApplication.perform(click());
    }

    @Test
    @DisplayName("ТС-36.4 Раздел About")
    @io.qameta.allure.kotlin.Description("Тест проверяет отображение на странице About информацию о создании приложения")

    public void aboutTest() {

        Allure.step("Шаг 4: Просмотреть год и название компании приложения");
        ViewInteraction textView = onView(withId(R.id.about_company_info_label_text_view));
        textView.check(matches(isDisplayed()));

    }

    @Test
    @DisplayName(value = "Тест-кейс 36.5: Отображение версии приложения")
    @io.qameta.allure.kotlin.Description(value = "Тест проверяет отображение версии приложения")
    public void versionAppTest() {

        Allure.step("Шаг 5: Посмотреть отображаемую версию в верхней части страницы");
        ViewInteraction version = onView(withId(R.id.about_version_value_text_view));
        version.check(matches(isDisplayed()));
    }

    @Test//тест падает с ошибкой приватности
    @DisplayName("ТС-36.2 Раздел About. Ссылка Privacy Policy")
    @io.qameta.allure.kotlin.Description("Тест проверяет ссылку")
    public void privacyPolicyTest() {


        Allure.step("Шаг 6: Нажать на ссылку под надписью Privacy Policy");
        ViewInteraction link = onView(withId(R.id.about_privacy_policy_value_text_view));
        link.check(matches(isDisplayed()));
        Intents.init();

        link.perform(click());

        intended(hasData("https://vhospice.org/#/privacy-policy"));
        intended(hasAction(Intent.ACTION_VIEW));

        Intents.release();
    }

    @Test
    @DisplayName("ТС-36.3 Раздел About. Ссылка Terms of use")
    @io.qameta.allure.kotlin.Description("Тест проверяет ссылку")
    public void termsUseTest() {


        Allure.step("Шаг 7: Нажать на ссылку под надписью Terms of use");
        ViewInteraction link = onView(withId(R.id.about_terms_of_use_value_text_view));
        link.check(matches(isDisplayed()));
        Intents.init();

        link.perform(click());

        intended(hasData("https://vhospice.org/#/terms-of-use"));
        intended(hasAction(Intent.ACTION_VIEW));

        Intents.release();

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
