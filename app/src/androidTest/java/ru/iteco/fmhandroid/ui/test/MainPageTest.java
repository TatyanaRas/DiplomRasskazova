package ru.iteco.fmhandroid.ui.test;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.helper.PageLoading.waitDisplayed;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

@RunWith(AllureAndroidJUnit4.class)

public class MainPageTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);


    private final int allNews = R.id.all_news_text_view;
    public static ViewInteraction threeStripes = onView(withId(R.id.main_menu_image_button));
    public static ViewInteraction news_button = onView(withText("News"));
    public static ViewInteraction aboutButton = onView(withText("About"));

    public static ViewInteraction loveIsAllButton = onView(withId(R.id.our_mission_image_button));
    public static ViewInteraction textViewNews = onView((withText("News")));
    public static ViewInteraction textViewAbout = onView(withId(R.id.about_version_value_text_view));

    public static ViewInteraction textLoveIsAll = onView(withText("Love is all"));
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
    public void mainPage() {
        Allure.step("Шаг 1: Запуск приложения");
        onView(isRoot()).perform(waitDisplayed(R.id.container_list_news_include_on_fragment_main, 7000));
    }

    @Test
    @DisplayName("ТС-07.4 Раздел Main")
    @io.qameta.allure.kotlin.Description("Тест проверяет переход со страницы Main на страницу All News")

    public void mainAllNewsTest() {


        Allure.step("Шаг 2: Нажать на текст All news");

        onView(withId(allNews)).check(matches(isDisplayed())).perform(click());

    }

    @Test
    @DisplayName("ТС-07.5 Раздел Main")
    @io.qameta.allure.kotlin.Description("Тест проверяет переход со страницы Main на страницу News")

    public void mainNewsTest() {
        Allure.step("Нажать изображение три полосочки");
        threeStripes.check(matches(isDisplayed())).perform(click());

        Allure.step("Переход на страницу News");

        news_button.check(matches(isDisplayed())).perform(click());

        Allure.step("Отображение страницы News");

        textViewNews.check(matches(isDisplayed()));

    }

    @Test
    @DisplayName("ТС-07.6 Раздел Main")
    @io.qameta.allure.kotlin.Description("Тест проверяет переход со страницы Main на страницу About")
    public void mainAboutTest() {

        Allure.step("Нажать изображение три полосочки");
        threeStripes.check(matches(isDisplayed())).perform(click());

        Allure.step("Переход на страницу About");

        aboutButton.check(matches(isDisplayed())).perform(click());

        Allure.step("Отображение страницы About");

        textViewAbout.check(matches(isDisplayed()));

    }

    @Test
    @DisplayName("ТС-07.7 Раздел Main")
    @io.qameta.allure.kotlin.Description("Тест проверяет переход со страницы Main на страницу Love is all")
    public void mainLoveIsAllTest() {
        Allure.step("Нажать на изображение бабочки");
        loveIsAllButton.check(matches(isDisplayed())).perform(click());

        Allure.step("Отображение страницы Love is all");

        textLoveIsAll.check(matches(isDisplayed()));

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




