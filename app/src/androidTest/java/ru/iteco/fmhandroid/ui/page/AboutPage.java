package ru.iteco.fmhandroid.ui.page;

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
import static org.hamcrest.CoreMatchers.allOf;
import static ru.iteco.fmhandroid.ui.helper.Data.getLinkPrivacyPolicy;
import static ru.iteco.fmhandroid.ui.helper.Data.getLinkTermsOfUse;
import static ru.iteco.fmhandroid.ui.helper.PageLoading.waitDisplayed;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.core.app.ActivityScenario;
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

public class AboutPage extends AuthorizationAssistant {

private final int companyInfo = R.id.about_company_info_label_text_view;
private final int version = R.id.about_version_value_text_view;
private final int privacyPolicy = R.id.about_privacy_policy_value_text_view;
private final int termsOfUse = R.id.about_terms_of_use_value_text_view;
private final int menuImageButton = R.id.main_menu_image_button;
private  final String aboutApplication = "About";


public final int getCompanyInfoId() {
        return companyInfo;
    }

    public final int getVersionId() {
        return version;
    }

    public final int getPrivacyPolicyId() {
        return privacyPolicy;
    }

    public final int getTermsOfUseId()
    {
        return termsOfUse;
    }
public final int getMenuImageButtonId() {
        return menuImageButton;
}

public final String getAboutApplicationId() {
        return aboutApplication;
}

public void imageThreeStripes() {
    Allure.step("Шаг 2: Нажать на меню с изображением три полосочки");
   onView(withId(getMenuImageButtonId())).check(matches(isDisplayed())).perform(click());
}
public void clickTheTextAbout() {
        Allure.step("Шаг 3: Нажать на текст About");
    onView(withText("About")).check(matches(isDisplayed())).perform(click());
}

    public void viewYearNameApplication() {

        Allure.step("Шаг 4: Просмотреть год и название компании приложения");
        onView(withId(getCompanyInfoId())).check(matches(isDisplayed()));

    }

    public void versionApp() {

        Allure.step("Шаг 5: Посмотреть отображаемую версию в верхней части страницы");
        onView(withId(getVersionId())).check(matches(isDisplayed()));
    }

    public void privacyPolicyLink() {

        Allure.step("Шаг 6: Нажать на ссылку под надписью Privacy Policy");
        onView(withId(getPrivacyPolicyId())).check(matches(isDisplayed()));
        Intents.init();

        onView(withId(getPrivacyPolicyId())).perform(click());

        intended(hasData(getLinkPrivacyPolicy()));
        intended(hasAction(Intent.ACTION_VIEW));

        Intents.release();
    }
    public void termsOfUseLink() {

        Allure.step("Шаг 7: Нажать на ссылку под надписью Terms of use");
        onView(withId(getTermsOfUseId())).check(matches(isDisplayed()));
        Intents.init();

        onView(withId(getTermsOfUseId())).perform(click());

        intended(hasData(getLinkTermsOfUse()));
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