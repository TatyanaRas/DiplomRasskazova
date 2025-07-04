package ru.iteco.fmhandroid.ui.test;

import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.helper.Data;
import ru.iteco.fmhandroid.ui.page.MainPage;
import ru.iteco.fmhandroid.ui.page.NewsPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class NewsTest extends NewsPage {
    NewsPage newsPage = new NewsPage();
    MainPage mainPage = new MainPage();

    @Before
    public void openEditingForm() {
        mainPage.clickAllNews();
        newsPage.clickPencilImage();
        newsPage.clickPlusImage();
    }

    @Test
    @DisplayName("Валидация пустое поле в форме добавления новости")
    @io.qameta.allure.kotlin.Description("Создание новости без заполнения полей")
    public void createNewsEmptyTest() {
        newsPage.emptyCategoryField();
        newsPage.emptyTitleField();
        newsPage.emptyDatePublication();
        newsPage.emptyTime();
        newsPage.emptyDescriptionField();
        newsPage.clickSaveButtonCategory();
        newsPage.warningMessage();
    }

    @Test
    @DisplayName("Валидация создания новости с невалидной категорией и валидными данными")
    @io.qameta.allure.kotlin.Description("Создание новости с невалидной категорией и валидными данными")
    public void creatingNewsInvalidCategoryTest() {
        newsPage.inputInvalidCategory(Data.getInvalidCategory());
        newsPage.inputTitle(Data.getTitle());
        newsPage.inputDate(Data.getDate());
        newsPage.inputTime(Data.getTime());
        newsPage.inputDescription(Data.getDescriptionCategory());
        newsPage.clickSaveButtonCategory();
        newsPage.errorDisplay();
    }

    @Test
    @DisplayName("Создание новости в форме Creating News раздела News")
    @io.qameta.allure.kotlin.Description("Создание новости, поля заполнены валидными данныим")
    public void creatingNewsTest() {
        newsPage.inputCategory(Data.getCategory());
        newsPage.inputTitle(Data.getTitle());
        newsPage.inputDate(Data.getDate());
        newsPage.inputTime(Data.getTime());
        newsPage.inputDescription(Data.getDescriptionCategory());
        newsPage.clickSaveButtonCategory();
        newsPage.createNews(Data.getTitle());

    }
}
