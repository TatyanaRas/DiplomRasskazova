package ru.iteco.fmhandroid.ui.test;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertEquals;
import static ru.iteco.fmhandroid.ui.helper.Data.description1;
import static ru.iteco.fmhandroid.ui.helper.Data.maxQuotes;
import static ru.iteco.fmhandroid.ui.helper.Data.quote1;
import static ru.iteco.fmhandroid.ui.helper.PageLoading.waitDisplayed;
import static ru.iteco.fmhandroid.ui.test.MainPageTest.loveIsAllButton;

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
import ru.iteco.fmhandroid.ui.helper.CustomViewMatcher;


@RunWith(AllureAndroidJUnit4.class)


public class OurMissionTest {


    private final int quoteBox = R.id.our_mission_item_list_recycler_view;
    private final int item = 1;

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);


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
    @DisplayName("ТС-")
    @io.qameta.allure.kotlin.Description("Просмотр заголовков и описание цитат")

    public void quotesTest() {

        Allure.step("Шаг 1: Нажать на изображение бабочки");
        loveIsAllButton.check(matches(isDisplayed())).perform(click());

        Allure.step("Шаг 2: Выбрать цитату");

        onView(withText(quote1)).check(matches(isDisplayed())).perform(click());


        Allure.step("Открылось описание цитаты");

        ViewInteraction viewInteraction = onView(allOf(withText(description1), isDisplayed()));

    }

    //Падает пока

    @Test
    @DisplayName("ТС-")
    @io.qameta.allure.kotlin.Description("Просмотр заголовков и описание цитат")

    public void quotesSizeTest() {

        Allure.step("Шаг 3: Посмотреть сколько цитат отображается на странице");


        onView(withId(quoteBox)).check(matches(isDisplayed())).check(matches(CustomViewMatcher.recyclerViewSizeMatcher(item)));
        assertEquals(true, equals(maxQuotes));

        // onView(withId(R.id.our_mission_item_list_recycler_view)).check(matches(hasChildCount(8)));
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
