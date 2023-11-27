<%--
  Created by IntelliJ IDEA.
  User: Ruslan
  Date: 21.11.2023
  Time: 18:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,400;1,500&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="../styles/record.css">
  <title>Запись к врачу</title>
</head>

<body>
<main>
  <div class="circle"></div>
  <div class="register-form-container">
    <div class="profile-form-container">
      <div class="profile-container__name">
        <h1>Запись к врачу</h1>
      </div>
      <div class="container__buttons">
        <div class="profile-container__avatar profile-button">
          <h4><a href="/main_page" class="profile-button__used">Главная страница</a></h4>
        </div>
        <div class="profile-container__listOfDoctors profile-button">
          <h4><a href="../jsp/doctors.jsp" class="profile-button__used">Список врачей</a></h4>
        </div>
        <div class="profile-container__exit profile-button">
          <h4><a href="/reg" class="profile-button__used">Выход</a></h4>
        </div>
      </div>
    </div>

    <div class="main__recorder-page">
      <div class="recorder-page">
        <form action="/rec" method="post">
          <input class="profile-page__name" type="text" name="userName" placeholder="ФИО пациента" style="color: black;">
          <div class="doctors profile-page__name">
            <select id="targetCurrency" name="doctorName">
              <option>Ishamatov Almaz</option>
              <option>Abdulloev Zhavakhir</option>
              <option>Khamatova Liana</option>
              <option>Veronica Ksenia</option>
              <option>Nurmiev Rafael</option>
            </select>
          </div>
          <input class="profile-page__name" type="date" name="date" placeholder="Дата" style="color: black;">
          <input class="profile-page__name" type="time" name="time" placeholder="Время" style="color: black;">
          <input class="profile-page__name" type="text" name="email"  placeholder="Email" style="color: black;">

        <div class="save profile-page__name">
          <a href="#" class="button" style="font-size: 30px; color: #405d87; text-decoration: none;">
            <input style="margin-top: 20px" class="button" type="submit" value="Send" placeholder="Записаться">
          </a>
        </div>
        </form>
        <h4 class="some-info">Записаться на консультацию можно по телефону 8(953)481-17-45 или 8(8553)35-17-45</h4>
      </div>
    </div>
  </div>
</main>
</body>

</html>