<%@ page import="models.Record" %>
<%@ page import="repository.UsersRepository" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: Ruslan
  Date: 21.11.2023
  Time: 18:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,400;1,500&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../styles/profile.css">
    <title>Профиль</title>
</head>

<body>
<main>
    <div class="circle"></div>
    <div class="register-form-container">
        <div class="profile-form-container">
            <div class="profile-container__name">
                <h1>Профиль</h1>
            </div>
            <div class="container__buttons">
                <div class="profile-container__avatar profile-button">
                    <h4><a href="/main_page" class="profile-button__used">Главная страница</a></h4>
                </div>
                <div class="profile-container__listOfDoctors profile-button">
                    <h4><a href="doctors.jsp" class="profile-button__used">Список врачей</a></h4>
                </div>
                <div class="profile-container__exit profile-button">
                    <h4><a href="/reg" class="profile-button__used">Выход</a></h4>
                </div>
            </div>
        </div>
        <div class="profile-form__content">
            <div class="profile-img">
                <div class="profile-img__avatar">
                    <img src="../pic/oZMrMHv8P64.jpg" class="avatar-img">
                    <div class="profile-img__avater__add">
                        <h2>Добавить фото</h2>
                    </div>
                </div>
            </div>
            <div class="profile-page__info">
                <div class="profile-form__name__block">
                    <div class="profile-page__name">
                        <h1>ФИО</h1>
                        <input type="text"  placeholder="Иванов Иван Иванович">
                    </div>
                    <div class="profile-page__name">
                        <h1>Номер телефона</h1>
                        <input type="text"  placeholder="(999) 999-99-99">
                    </div>
                    <div class="profile-page__name">
                        <h1>Почта</h1>
                        <input type="text"  placeholder="_______@gmail.com">
                    </div>
                    <div class="profile-page__name">
                        <h1>Наспортные данные</h1>
                        <input type="text"  placeholder="Серия и номер паспорта">
                    </div>
                    <div class="save profile-page__name">
                        <a href="#" class="button" style="font-size: 30px; color: #405d87; text-decoration: none;">
                            <h3 style="margin-top: 20px">Сохранить</h3>
                        </a>
                    </div>
                </div>
            </div>

            <div class="recorder-form-container">
                <h1 class="form-title">
                    Ваши записи к врачам
                </h1>
                    <div class="form-fields">
                        <% List<Record> records = (List<Record>) request.getAttribute("recordsForJsp");

                        for ( int i = 0; i < records.size(); i++) { %>
                        <div class="recorder-form-container__records">
                            <h3 class="info_records"><%= records.get(i).getIdDoctor()%></h3>
                            <h3 class="info_records"><%= records.get(i).getDate()%></h3>
                            <h3 class="info_records"><%= records.get(i).getTime()%></h3>
                        </div>
                        <% } %>
                    </div>
            </div>
        </div>
    </div>
</main>
</body>
</html>
