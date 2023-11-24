<%--
  Created by IntelliJ IDEA.
  User: Ruslan
  Date: 21.11.2023
  Time: 18:50
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
  <link rel="stylesheet" href="../../styles/doctors_page.css">
  <title>Профиль</title>
</head>

<body>
<main>
  <div class="circle"></div>
  <div class="register-form-container">
    <div class="profile-form-container">
      <div class="profile-container__name">
        <h1>Ишмаматов Алмаз Ишмаматов</h1>
      </div>
      <div class="container__buttons">
        <div class="profile-container__avatar profile-button">
          <h4><a href="/main_page" class="profile-button__used">Главная страница</a></h4>
        </div>
        <div class="profile-container__listOfDoctors profile-button">
          <h4><a href="../doctors.jsp" class="profile-button__used">Список врачей</a></h4>
        </div>
        <div class="profile-container__exit profile-button">
          <h4><a href="../profile.jsp" class="profile-button__used">Профиль</a></h4>
        </div>
      </div>
    </div>


    <div class="all-info">
      <div class="profile-img">
        <div class="profile-img__avatar">
          <img src="../../pic/oZMrMHv8P64.jpg" class="avatar-img">
        </div>
      </div>

      <div class="info__about-doctor">
        <h2 class="info__about-doctor__title" style="font-size: 45px; padding-bottom: 15px;"><strong>Информация о враче</strong></h2>
        <h3 class="info__about-doctor__title">Профессиональные навыки:</h3>
        <h3 class="info__about-doctor__title">- Лечение кариеса и его осложнения(пульпит, периодонтит) современными стоматологическими материалами. Эстетичекское восстановление всех групп зубов;</h3>
        <h3 class="info__about-doctor__title">- Профессиональная гигиена полости рта;</h3>
        <h3 class="info__about-doctor__title">- Удаление зубов разной степени сложности</h3>
        <h3 class="info__about-doctor__title">- Протезировние зубов современными искусственными коронками и съемными протезами;</h3>
      </div>

      <div class="save profile-page__name">
        <a href="../record.jsp" class="button" style="font-size: 30px; color: #405d87; text-decoration: none;">
          <h3 style="margin-top: 20px">Записаться на прием</h3>
        </a>
      </div>
    </div>



  </div>
</main>
</body>

</html>
