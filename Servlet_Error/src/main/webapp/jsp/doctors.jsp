<%--
  Created by IntelliJ IDEA.
  User: Ruslan
  Date: 21.11.2023
  Time: 18:32
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
    <link rel="stylesheet" href="../styles/doctors.css">
    <title>Список врачей</title>
</head>

<body>
<main>
    <div class="circle"></div>
    <div class="register-form-container">
        <div class="profile-form-container">
            <div class="profile-container__name">
                <h1>Список врачей</h1>
            </div>
            <div class="container__buttons">
                <div class="profile-container__avatar profile-button">
                    <h4><a href="/main_page" class="profile-button__used">Главная страница</a></h4>
                </div>
                <div class="profile-container__listOfDoctors profile-button">
                    <h4><a href="../jsp/profile.jsp" class="profile-button__used">Профиль</a></h4>
                </div>
                <div class="profile-container__exit profile-button">
                    <h4><a href="/reg" class="profile-button__used">Выход</a></h4>
                </div>
            </div>
        </div>

        <div class="list-doctors">
            <div class="form-fields">
                <a href="doctors/first_doctor.jsp" class="recorder-form-container__records">
                    <img src="../pic/oZMrMHv8P64.jpg" alt="" style="margin-left: 15px; border-radius: 15px;">
                    <h3 class="info_records">Ишмаматов Алмаз Ишмаматович(врач-терапевт, ортопед, хирург)</h3>
                </a>

                <a href="doctors/second_doctor.jsp" class="recorder-form-container__records" >
                    <img src="../pic/oZMrMHv8P64.jpg" alt="" style="margin-left: 15px; border-radius: 15px;">
                    <h3 class="info_records">Абдуллоев Жавахир Джахангирович(врач-терапевт)</h3>
                </a>
                <a href="doctors/third_doctor.jsp" class="recorder-form-container__records">
                    <img src="../pic/oZMrMHv8P64.jpg" alt="" style="margin-left: 15px; border-radius: 15px;">
                    <h3 class="info_records">Хаматова Лиана(медсестра)</h3>
                </a>
                <a href="doctors/fourth_doctor.jsp" class="recorder-form-container__records">
                    <img src="../pic/oZMrMHv8P64.jpg" alt="" style="margin-left: 15px; border-radius: 15px;">
                    <h3 class="info_records">Вероника Ксения(медсестра)</h3>
                </a>
                <a href="doctors/fifth_doctor.jsp" class="recorder-form-container__records">
                    <img src="../pic/oZMrMHv8P64.jpg" alt="" style="margin-left: 15px; border-radius: 15px;">
                    <h3 class="info_records">Нурмиев Рафаэль(врач-терапевт)</h3>
                </a>
            </div>
        </div>
    </div>
</main>
</body>

</html>
