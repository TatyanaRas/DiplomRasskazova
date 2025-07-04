package ru.iteco.fmhandroid.ui.page;

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

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;


public class MainPage extends AuthorizationAssistant {

    private final int newsMain = R.id.container_list_news_include_on_fragment_main;
    private final int allNews = R.id.all_news_text_view;
    private final int threeStripes = R.id.main_menu_image_button;
    private final String newsButton = "News";
    private final String aboutButton = "About";
    private final int loveIsAllButton = R.id.our_mission_image_button;
    private final String textViewNews = "News";
    private final int textViewAbout = R.id.about_version_value_text_view;
    private final String textLoveIsAll = "Love is all";

    public final int getNewsMainId() {
        return newsMain;
    }

    public final int getAllNews() {
        return allNews;
    }

    public final int getThreeStripesId() {
        return threeStripes;
    }

    public final String getNewsButtonId() {
        return newsButton;
    }

    public final String getAboutButtonId() {
        return aboutButton;
    }

    public final int getLoveIsAllButtonId() {
        return loveIsAllButton;
    }

    public final String getTextViewNewsId() {
        return textViewNews;
    }

    public final int getTextViewAboutId() {
        return textViewAbout;
    }

    public final String getTextLoveIsAllId() {
        return textLoveIsAll;
    }

    public void viewMainPage() {
        onView(isRoot()).perform(waitDisplayed(getNewsMainId(), 7000));
        onView(withId(getNewsMainId())).check(matches(isDisplayed()));
    }

    public void clickAllNews() {
        Allure.step("Шаг 2: Нажать кнопку все новости");
        onView(withId(getAllNews()))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void clickImageThreeStripes() {
        Allure.step(" Шаг 3: Нажать изображение три полосочки");
        onView(withId(getThreeStripesId())).check(matches(isDisplayed())).perform(click());
    }

    public void clickTextNews() {

        Allure.step("Шаг 4: Переход на страницу News");

        onView(withText(getNewsButtonId())).check(matches(isDisplayed())).perform(click());

    }

    public void viewPageNews() {
        Allure.step("Шаг 5: Отображение страницы News");

        onView(withText(getTextViewNewsId())).check(matches(isDisplayed()));

    }

    public void clickTextAbout() {

        Allure.step("Шаг6: Переход на страницу About");

        onView(withText(getAboutButtonId())).check(matches(isDisplayed())).perform(click());
    }

    public void viewPageAbout() {

        Allure.step("Шаг 7: Отображение страницы About");

        onView(withId(getTextViewAboutId())).check(matches(isDisplayed()));

    }

    public void clickImageButterfly() {
        Allure.step("Шаг 8: Нажать на изображение бабочки");
        onView(withId(getLoveIsAllButtonId())).check(matches(isDisplayed())).perform(click());
    }

    public void viewPageQuotes() {

        Allure.step("Шаг 9: Отображение страницы Love is all");

        onView(withText(getTextLoveIsAllId())).check(matches(isDisplayed()));

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


