package ru.iteco.fmhandroid.ui.test;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static ru.iteco.fmhandroid.ui.helper.Data.category;
import static ru.iteco.fmhandroid.ui.helper.Data.date;
import static ru.iteco.fmhandroid.ui.helper.Data.descriptionCategory;
import static ru.iteco.fmhandroid.ui.helper.Data.invalidCategory;
import static ru.iteco.fmhandroid.ui.helper.Data.invalidCategory_errorMessage;
import static ru.iteco.fmhandroid.ui.helper.Data.time;
import static ru.iteco.fmhandroid.ui.helper.Data.title;
import static ru.iteco.fmhandroid.ui.helper.Data.warningMessage;
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


public class NewsTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);


    public static ViewInteraction editing = onView((withId(R.id.edit_news_material_button)));

    public static ViewInteraction categoryField = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    private final int createButton = R.id.add_news_image_view;
    private final int titleField = R.id.news_item_title_text_input_edit_text;
    private final int datePublication = R.id.news_item_publish_date_text_input_edit_text;
    private final int timePublication = R.id.news_item_publish_time_text_input_edit_text;
    private final int descriptionField = R.id.news_item_description_text_input_edit_text;

    public static ViewInteraction saveButtonCategory = onView(withId(R.id.save_button));

    public static ViewInteraction saveButton = onView(allOf(withId(R.id.save_button)));


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

        Allure.step("Шаг 2: Нажать на текст All news");
        ViewInteraction aboutApplication = onView(withId(R.id.all_news_text_view)).check(matches(isDisplayed()));
        aboutApplication.perform(click());

        Allure.step("Шаг 3: Нажать на изображение карандаша");
        ViewInteraction materialButton = editing;
        materialButton.check(matches(isDisplayed()));
        materialButton.perform(click());
    }

    @Test
    @DisplayName("ТС-18.4")
    @io.qameta.allure.kotlin.Description("Создание новости без заполнения полей")

    public void creatingNewsEmptyFieldTest() {


        Allure.step("Шаг 4: Нажать на изображение плюсик");
        onView(withId(createButton)).check(matches(isDisplayed()))
                .perform(click());


        Allure.step("Шаг 5: Оставить поле Категория, пустое");
        ViewInteraction chooseCategory = categoryField;
        chooseCategory.check(matches(isDisplayed()));


        Allure.step("Шаг 6: Оставить поле Title пустое '");
        onView(withId(titleField)).check(matches(isDisplayed()));


        Allure.step("Шаг 7: Оставить поле Publication date, пустое");
        onView(withId(datePublication))
                .check(matches(isDisplayed()));


        Allure.step("Шаг 8: Поле Time, пустое");
        onView(withId(timePublication))
                .check(matches(isDisplayed()));


        Allure.step("Шаг 9: Оставить поле Description, пустое");
        onView(withId(descriptionField))
                .check(matches(isDisplayed()));


        Allure.step("Шаг 10: Нажать на кнопку Save");


        saveButtonCategory
                .check(matches(isDisplayed()))
                .perform(scrollTo(), click());


        Allure.step("Шаг 11: Отображение ошибки на странице");
        onView(withText(warningMessage))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));

    }

    @Test
    @DisplayName("ТС-18.1")
    @io.qameta.allure.kotlin.Description("Создание новости с невалидной категорией и валидными данными")

    public void creatingNewsInvalidCategoryTest() {

        Allure.step("Шаг 4: Нажать на изображение плюсик");
        onView(withId(createButton)).check(matches(isDisplayed()))
                .perform(click());


        Allure.step("Шаг 5: Выбрать в поле Категория 'Объявление'");
        ViewInteraction chooseCategory = categoryField;
        chooseCategory.check(matches(isDisplayed()))
                .perform(replaceText(invalidCategory), closeSoftKeyboard());


        Allure.step("Шаг 3: Вести в поле Title  'Advertisement'");
        onView(withId(titleField)).check(matches(isDisplayed()))
                .perform(replaceText(title), closeSoftKeyboard());


        Allure.step("Шаг 4: Вести в поле Publication date, текущую дату");
        onView(withId(datePublication))
                .check(matches(isDisplayed()))
                .perform(replaceText(date), closeSoftKeyboard());


        Allure.step("Шаг 5: Вести в поле Time, текущее время");
        onView(withId(timePublication))
                .check(matches(isDisplayed()))
                .perform(replaceText(time), closeSoftKeyboard());


        Allure.step("Шаг 6: Вести в поле Description, 'Russia Day is coming soon'");

        onView(withId(descriptionField))
                .check(matches(isDisplayed()))
                .perform(replaceText(descriptionCategory), closeSoftKeyboard());


        Allure.step("Шаг 7: Нажать на кнопку Save");


        saveButton.check(matches(isDisplayed())).perform(scrollTo(), click());


        Allure.step("Шаг 11: Отображение ошибки на странице");

        onView(withText(invalidCategory_errorMessage))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));


    }

    @Test
    @DisplayName("ТС-20")
    @io.qameta.allure.kotlin.Description("Создание новости")


    public void creatingNewsTest() {

        Allure.step("Шаг 4: Нажать на изображение плюсик");
        onView(withId(createButton)).check(matches(isDisplayed()))
                .perform(click());


        Allure.step("Шаг 5: Выбрать в поле Категория 'Объявление'");
        ViewInteraction chooseCategory = categoryField;
        chooseCategory.check(matches(isDisplayed()))
                .perform(replaceText(category), closeSoftKeyboard());


        Allure.step("Шаг 3: Вести в поле Title  'Advertisement'");
        onView(withId(titleField)).check(matches(isDisplayed()))
                .perform(replaceText(title), closeSoftKeyboard());


        Allure.step("Шаг 4: Вести в поле Publication date, текущую дату");
        onView(withId(datePublication))
                .check(matches(isDisplayed()))
                .perform(replaceText(date), closeSoftKeyboard());


        Allure.step("Шаг 5: Вести в поле Time, текущее время");
        onView(withId(timePublication))
                .check(matches(isDisplayed()))
                .perform(replaceText(time), closeSoftKeyboard());


        Allure.step("Шаг 6: Вести в поле Description, 'Russia Day is coming soon'");

        onView(withId(descriptionField))
                .check(matches(isDisplayed()))
                .perform(replaceText(descriptionCategory), closeSoftKeyboard());


        Allure.step("Шаг 7: Нажать на кнопку Save");


        saveButton.check(matches(isDisplayed())).perform(scrollTo(), click());


        Allure.step("Шаг 8: Создание новости");
        onView(withId(R.id.news_list_recycler_view)).perform(actionOnItemAtPosition(8, click()));


        Allure.step("Шаг 8: Отображение созданной новости");

        ViewInteraction viewInteraction = onView(allOf(withText(title), isDisplayed()));

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
