package ru.iteco.fmhandroid.ui.test;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.helper.Data;
import ru.iteco.fmhandroid.ui.page.MainPage;
import ru.iteco.fmhandroid.ui.page.OurMissionPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class OurMissionTest extends OurMissionPage {
    OurMissionPage ourMissionPage = new OurMissionPage();
    MainPage mainPage = new MainPage();

    @Rule
    public ActivityScenarioRule<AppActivity> activityRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void openButterflyForm() {

        mainPage.clickImageButterfly();
    }

    @Test
    @DisplayName("Валидация открытия описания цитат раздела Love is all (изображение бабочки)")
    @io.qameta.allure.kotlin.Description("Просмотр заголовков и описание цитат")

    public void quotesTest() {
        ourMissionPage.chooseQuote();
        ourMissionPage.viewВescriptionQuote();

    }


    @Test
    @DisplayName("Валидация колличества цитат в разделе Love is all (изображение бабочки)")
    @io.qameta.allure.kotlin.Description("Просмотр количества заголовков и описание цитат")

    public void quotesSizeTest() {

        ourMissionPage.quotesDisplayedPage(Data.getMaxQuotes());

    }

}
