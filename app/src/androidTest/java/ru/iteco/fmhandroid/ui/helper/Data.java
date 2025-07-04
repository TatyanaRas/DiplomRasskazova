package ru.iteco.fmhandroid.ui.helper;

import ru.iteco.fmhandroid.ui.page.AuthorizationAssistant;

public class Data extends AuthorizationAssistant {

    //для страницы авторизации
    public static final String validLogin = "login2";
    public static final String validPassword = "password2";

    public static final String invalidLogin = "login1";
    public static final String invalidPassword = "password1";

    public static final String invalidErrorMessage = "Something went wrong. Try again later.";
    public static final String emptyFieldsErrorMessage = "Login and password cannot be empty";


    //для создания новости
    public static String category = "Объявление";
    public static String invalidCategory = "Дедлайн";
    public static String title = "Advertisement";
    public static final String date = "20.06.2025";
    public static String time = "11:58";
    public static String descriptionCategory = "June 12, 2025 — Russia Day";
    public static final String warningMessage = "Fill empty fields";
    public static String invalidCategory_errorMessage = "Saving failed. Try again later.";
    public static final int maxQuotes = 8;


// для просмотра цитат


    public static final String quote1 = "«Хоспис для меня - это то, каким должен быть мир.\"";


    public static final String description1 = "\"Ну, идеальное устройство мира в моих глазах. Где никто не оценивает, никто не осудит, где говоришь, и тебя слышат, где, если страшно, тебя обнимут и возьмут за руку, а если холодно тебя согреют.” Юля Капис, волонтер";
// Ссылки для страницы About

    public static final String linkPrivacyPolicy = "https://vhospice.org/#/privacy-policy/";
    public static final String linkTermsOfUse = "https://vhospice.org/#/terms-of-use";


    public static String getValidLogin() {
        return validLogin;
    }

    public static String getValidPassword() {
        return validPassword;
    }

    public static String getInvalidLogin() {
        return invalidLogin;
    }

    public static String getInvalidPassword() {
        return invalidPassword;
    }


    public static int getMaxQuotes() {
        return maxQuotes;
    }

    public static String getCategory() {
        return category;
    }

    public static String getInvalidCategory() {
        return invalidCategory;
    }

    public static String getTitle() {
        return title;
    }

    public static String getDate() {
        return date;
    }

    public static String getTime() {
        return time;
    }

    public static String getDescriptionCategory() {
        return descriptionCategory;
    }
    public static String getLinkPrivacyPolicy() {
        return linkPrivacyPolicy;
    }
    public static String getLinkTermsOfUse() {
        return linkTermsOfUse;
    }
}