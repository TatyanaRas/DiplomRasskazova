package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.helper.Data.description1;
import static ru.iteco.fmhandroid.ui.helper.Data.quote1;
import static ru.iteco.fmhandroid.ui.helper.PageLoading.waitDisplayed;
//import static ru.iteco.fmhandroid.ui.test.MainPageTest.loveIsAllButton;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.helper.CustomViewMatcher;


public class OurMissionPage extends AuthorizationAssistant {

    private final int quoteBlock = R.id.our_mission_item_list_recycler_view;
        public final int getQuoteBlockId() {
        return quoteBlock;
    }

    @Before
    public void mainPage() {
        Allure.step("Шаг 1: Запуск приложения");
        onView(isRoot()).perform(waitDisplayed(R.id.container_list_news_include_on_fragment_main, 7000));
    }

       public void chooseQuote() {
        Allure.step("Шаг 2: Выбрать цитату");

        onView(withText(quote1)).check(matches(isDisplayed())).perform(click());
    }

    public void viewВescriptionQuote() {

        Allure.step("Открылось описание цитаты");

        ViewInteraction viewInteraction = onView(allOf(withText(description1), isDisplayed()));

    }

    public void quotesDisplayedPage(int item) {

        Allure.step("Шаг 3: Посмотреть сколько цитат отображается на странице");


        onView(withId(getQuoteBlockId())).check(matches(isDisplayed()))
                .check(matches(CustomViewMatcher.recyclerViewSizeMatcher(item)));
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
