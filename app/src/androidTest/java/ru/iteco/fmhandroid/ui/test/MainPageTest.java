package ru.iteco.fmhandroid.ui.test;


import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.page.MainPage;

@RunWith(AllureAndroidJUnit4.class)

public class MainPageTest extends MainPage {
    MainPage mainPage = new MainPage();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);


    @Before
    public void mainPage() {
        Allure.step("Шаг 1: Запуск приложения");
        mainPage.viewMainPage();
    }

    @Test
    @DisplayName("Навигация по разделу Main")
    @io.qameta.allure.kotlin.Description("Тест проверяет переход со страницы Main на страницу All News")

    public void mainAllNewsTest() {
        mainPage.clickAllNews();

    }

    @Test
    @DisplayName("Навигация по разделу Main")
    @io.qameta.allure.kotlin.Description("Тест проверяет переход со страницы Main на страницу News")

    public void mainNewsTest() {
        mainPage.clickImageThreeStripes();
        mainPage.clickTextNews();
        mainPage.viewPageNews();
    }

    @Test
    @DisplayName("Навигация по разделу Main")
    @io.qameta.allure.kotlin.Description("Тест проверяет переход со страницы Main на страницу About")
    public void mainAboutTest() {
        mainPage.clickImageThreeStripes();
        mainPage.clickTextAbout();
        mainPage.viewPageAbout();
    }

    @Test
    @DisplayName("Навигация по разделу Main")
    @io.qameta.allure.kotlin.Description("Тест проверяет переход со страницы Main на страницу Love is all")
    public void mainLoveIsAllTest() {
        mainPage.clickImageButterfly();
        mainPage.viewPageQuotes();

    }

}





