package ru.iteco.fmhandroid.ui.test;

import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.page.AboutPage;
import ru.iteco.fmhandroid.ui.page.MainPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class AboutTest extends AboutPage {
    AboutPage aboutPage = new AboutPage();
    MainPage mainPage = new MainPage();

    @Before
    public void openEditingForm() {
        mainPage.viewMainPage();
        aboutPage.imageThreeStripes();
        aboutPage.clickTheTextAbout();
    }


    @Test
    @DisplayName("Навигация по разделу About")
    @io.qameta.allure.kotlin.Description("Тест проверяет отображение на странице About информацию о создании приложения")
    public void nameApplicationTest() {
        aboutPage.viewYearNameApplication();
    }

    @Test
    @DisplayName("Навигация по разделу About")
    @io.qameta.allure.kotlin.Description("Тест проверяет отображение на странице About версии приложения")
    public void versionAppTest() {
        aboutPage.versionApp();
    }

    @Test
    @DisplayName("Навигация по разделу About")
    @io.qameta.allure.kotlin.Description("Тест проверяет на странице About переход по ссылке Privacy Policy")
    public void privacyPolicyTest() {
        aboutPage.privacyPolicyLink();
    }

    @Test
    @DisplayName("Навигация по разделу About")
    @io.qameta.allure.kotlin.Description("Тест проверяет на странице About переход по ссылке Terms of use")
    public void termsUseTest() {
        aboutPage.termsOfUseLink();
    }

}
