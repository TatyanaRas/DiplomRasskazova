package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static ru.iteco.fmhandroid.ui.helper.Data.descriptionCategory;
import static ru.iteco.fmhandroid.ui.helper.Data.invalidCategory;
import static ru.iteco.fmhandroid.ui.helper.Data.invalidCategory_errorMessage;
import static ru.iteco.fmhandroid.ui.helper.Data.warningMessage;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.core.app.ActivityScenario;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;


public class NewsPage extends AuthorizationAssistant {
    private final int editingId = R.id.edit_news_material_button;
    private final int categoryFieldId = R.id.news_item_category_text_auto_complete_text_view;
    private final int createButtonId = R.id.add_news_image_view;
    private final int titleFieldId = R.id.news_item_title_text_input_edit_text;
    private final int datePublicationId = R.id.news_item_publish_date_text_input_edit_text;
    private final int timePublicationId = R.id.news_item_publish_time_text_input_edit_text;
    private final int descriptionFieldId = R.id.news_item_description_text_input_edit_text;
    private final int saveButtonCategoryId = R.id.save_button;


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

    private final int getEditingId() {
        return editingId;
    }

    private final int getCreateButtonId() {
        return createButtonId;
    }

    private final int getCategoryFieldId() {
        return categoryFieldId;
    }

    private final int getTitleFieldId() {
        return titleFieldId;
    }

    private final int getDatePublicationId() {
        return datePublicationId;
    }

    private final int getTimePublicationId() {
        return timePublicationId;
    }

    private final int getDescriptionFieldId() {
        return descriptionFieldId;
    }

    private final int getSaveButtonCategoryId() {
        return saveButtonCategoryId;
    }

    private final String getWarningMessage() {
        return warningMessage;
    }

    public void clickPencilImage() {
        Allure.step("Шаг 1: Нажать на изображение карандаша");
        onView(withId(getEditingId()))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void clickPlusImage() {
        Allure.step("Шаг 2: Нажать на изображение плюсик");
        onView(withId(getCreateButtonId())).check(matches(isDisplayed()))
                .perform(click());
    }

    public void emptyCategoryField() {
        Allure.step("Шаг 5: Оставить поле Категория, пустое");
        onView(withId(getCategoryFieldId())).check(matches(isDisplayed()));

    }

    public void emptyTitleField() {
        Allure.step("Шаг 6: Оставить поле Title пустое '");
        onView(withId(getTitleFieldId())).check(matches(isDisplayed()));

    }

    public void emptyDatePublication() {
        Allure.step("Шаг 7: Оставить поле Publication date, пустое");
        onView(withId(getDatePublicationId())).check(matches(isDisplayed()));
    }

    public void emptyTime() {

        Allure.step("Шаг 8: Поле Time, пустое");
        onView(withId(getTimePublicationId())).check(matches(isDisplayed()));
    }

    public void emptyDescriptionField() {

        Allure.step("Шаг 9: Оставить поле Description, пустое");
        onView(withId(getDescriptionFieldId()))
                .check(matches(isDisplayed()));
    }

    public void clickSaveButtonCategory() {

        Allure.step("Шаг 10: Нажать на кнопку Save");
        onView(withId(getSaveButtonCategoryId())).check(matches(isDisplayed()))
                .perform(scrollTo(), click());
    }

    public void warningMessage() {

        Allure.step("Шаг 11: Отображение ошибки на странице");
        onView(withText(getWarningMessage()))
                .inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));

    }

    public void inputInvalidCategory(String category) {
        Allure.step("Шаг 5: Выбрать в поле Категория 'Объявление'");
        onView(withId(getCategoryFieldId())).check(matches(isDisplayed()))
                .perform(replaceText(invalidCategory), closeSoftKeyboard());

    }

    public void inputCategory(String category) {
        Allure.step("Шаг 5: Выбрать в поле Категория 'Объявление'");
        onView(withId(getCategoryFieldId())).check(matches(isDisplayed()))
                .perform(replaceText(category), closeSoftKeyboard());

    }

    public void inputTitle(String title) {
        Allure.step("Шаг 3: Вести в поле Title  'Advertisement'");
        onView(withId(getTitleFieldId())).check(matches(isDisplayed()))
                .perform(replaceText(title), closeSoftKeyboard());
    }

    public void inputDate(String date) {
        Allure.step("Шаг 4: Вести в поле Publication date, текущую дату");
        onView(withId(getDatePublicationId()))
                .check(matches(isDisplayed()))
                .perform(replaceText(date), closeSoftKeyboard());
    }

    public void inputTime(String time) {

        Allure.step("Шаг 5: Вести в поле Time, текущее время");
        onView(withId(getTimePublicationId()))
                .check(matches(isDisplayed()))
                .perform(replaceText(time), closeSoftKeyboard());

    }

    public void inputDescription(String description) {
        Allure.step("Шаг 6: Вести в поле Description, 'Russia Day is coming soon'");

        onView(withId(getDescriptionFieldId()))
                .check(matches(isDisplayed()))
                .perform(replaceText(descriptionCategory), closeSoftKeyboard());

    }

    public void errorDisplay() {

        Allure.step("Шаг 11: Отображение ошибки на странице");

        onView(withText(invalidCategory_errorMessage))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));

    }

    public void createNews(String title) {

        Allure.step("Шаг 8: Создание новости");

        onView(withId(R.id.news_list_recycler_view)).perform(actionOnItemAtPosition(8, click()));
        onView(allOf(withText(title), isDisplayed()));
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