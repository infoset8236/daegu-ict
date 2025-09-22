$(document).ready(function () {
    // 1. 뉴스 슬라이드 초기화
    function initNewsSlider() {
        $('.newsList').each(function () {
            $(this).slick({
                slidesToShow: 1,
                arrows: false,
                autoplay: true,
                autoplaySpeed: 8000,
                dots: false,
                infinite: true,
                vertical: true
            });
        });
    }

    // 2. 미세먼지 API
    function loadDustStatus() {
        $.ajax({
            url: 'https://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty'
               + '?sidoName=%EA%B2%BD%EB%B6%81&pageNo=1&numOfRows=100&returnType=JSON'
               + '&serviceKey=sJ1NoVQBK2nsNP4b1R0%2BsSWiFRByMlaG2VdTRnnLsUtnlpsworjKd4fvoET7YMk1xjMt%2BCIwqJu7%2BRyahrIhnQ%3D%3D&ver=1.3',
            dataType: "json",
            type: "GET",
            success: function (data) {
                var dustStatus = data.response.body.items[0].pm10Grade1h;
                var text = "보통";
                if (dustStatus === '1') text = "좋음";
                else if (dustStatus === '2') text = "보통";
                else if (dustStatus === '3') text = "나쁨";
                else if (dustStatus === '4') text = "매우나쁨";
                $('#dust_condi_txt').html(text);
            },
            error: function () {
                $('#dust_condi_txt').html('보통');
            }
        });
    }

    // 3. 날씨 + 식중독 지수
    function loadWeather() {
        const apiUrl = 'https://api.openweathermap.org/data/2.5/forecast?q=Daegu'
                     + '&appid=3bcf7eca7fc5d5df252135e43043a0a7&units=metric&lang=kr';

        const weatherIcons = {
            "01d": "/resources/ict/dglib/nonTouch/img/life/01d.png",
            "01n": "/resources/ict/dglib/nonTouch/img/life/01n.png",
            "02d": "/resources/ict/dglib/nonTouch/img/life/02d.png",
            "02n": "/resources/ict/dglib/nonTouch/img/life/02n.png",
            "03d": "/resources/ict/dglib/nonTouch/img/life/03d.png",
            "03n": "/resources/ict/dglib/nonTouch/img/life/03d.png",
            "04d": "/resources/ict/dglib/nonTouch/img/life/04d.png",
            "04n": "/resources/ict/dglib/nonTouch/img/life/04d.png",
            "09d": "/resources/ict/dglib/nonTouch/img/life/09d.png",
            "09n": "/resources/ict/dglib/nonTouch/img/life/09d.png",
            "10d": "/resources/ict/dglib/nonTouch/img/life/10d.png",
            "10n": "/resources/ict/dglib/nonTouch/img/life/10n.png",
            "11d": "/resources/ict/dglib/nonTouch/img/life/11d.png",
            "11n": "/resources/ict/dglib/nonTouch/img/life/11n.png",
            "13d": "/resources/ict/dglib/nonTouch/img/life/13d.png",
            "13n": "/resources/ict/dglib/nonTouch/img/life/13n.png",
            "50d": "/resources/ict/dglib/nonTouch/img/life/50d.png",
            "50n": "/resources/ict/dglib/nonTouch/img/life/50n.png"
        };

        $.ajax({
            url: apiUrl,
            dataType: "json",
            type: "GET",
            success: function (data) {
                const currentTemp = parseFloat(data.list[0].main.temp.toFixed(1));
                const feelsLike = parseFloat(data.list[0].main.feels_like.toFixed(1));
                const humidity = data.list[0].main.humidity;
                const iconCode = data.list[0].weather[0].icon;
                const iconUrl = weatherIcons[iconCode] || '/resources/ict/dglib/nonTouch/img/life/03n.png';
                const description = data.list[0].weather[0].description;

                $('#current_temp').html(currentTemp + '˚');
                $('#feels_like').html(feelsLike + '˚');
                $('#today_icon').attr('src', iconUrl);
                $('#current_condition').html(description);

                // 📌 식중독 지수 계산
                let foodRisk = "안전";
                if (currentTemp >= 30) foodRisk = "매우 위험";
                else if (currentTemp >= 25) foodRisk = "주의";
                else if (currentTemp >= 20) foodRisk = "보통";
                else if (currentTemp >= 10) foodRisk = "낮음";

                if (humidity >= 80 && foodRisk !== "매우 위험") {
                    if (foodRisk === "주의") foodRisk = "매우 위험";
                    else if (foodRisk === "보통") foodRisk = "주의";
                    else if (foodRisk === "낮음") foodRisk = "보통";
                }
                $('#food_condi_txt').html(foodRisk);

                // 📌 주간 예보
                var forecastHtml = '';
                let dayCount = 0;
                const today = new Date().getDate();
                let todayAdded = false;

                for (let i = 0; i < data.list.length; i++) {
                    const forecastDate = new Date(data.list[i].dt * 1000);
                    const forecastTemp = parseFloat(data.list[i].main.temp.toFixed(1));
                    const forecastIcon = weatherIcons[data.list[i].weather[0].icon]
                                         || '/resources/ict/dglib/nonTouch/img/life/03n.png';
                    const weekDay = ['일', '월', '화', '수', '목', '금', '토'][forecastDate.getDay()];
                    const dateStr = weekDay + ' '
                        + (forecastDate.getMonth() + 1).toString().padStart(2, '0')
                        + '.' + forecastDate.getDate().toString().padStart(2, '0');

                    // 오늘 데이터: 정오가 없으면 가장 첫 번째(현재 이후) 데이터로 대체
                    if (forecastDate.getDate() === today && !todayAdded) {
                        forecastHtml += `
                            <div class="weeklyItem">
                                <img src="${forecastIcon}" alt="">
                                <div class="weeklyInfo">
                                    <div>${dateStr}</div>
                                    <div>${forecastTemp}˚</div>
                                </div>
                            </div>
                        `;
                        todayAdded = true;
                        dayCount++;
                        continue;
                    }

                    // 내일부터는 정오 데이터만 선택
                    if (forecastDate.getHours() === 12 && forecastDate.getDate() !== today) {
                        forecastHtml += `
                            <div class="weeklyItem">
                                <img src="${forecastIcon}" alt="">
                                <div class="weeklyInfo">
                                    <div>${dateStr}</div>
                                    <div>${forecastTemp}˚</div>
                                </div>
                            </div>
                        `;
                        dayCount++;
                    }

                    if (dayCount >= 6) break; // ✅ 오늘 포함 총 6개
                }

                $('#forecast_frame').html(forecastHtml);
            }
        });
    }

    // 4. 뉴스 API (백엔드 JSON)
    function loadNews() {
        $.ajax({
            url: "/api/etc/news.do",
            type: "GET",
            dataType: "json",
            success: function (data) {
                if (data.result !== "SUCCESS") {
                    $("#newsList").html("<div class='news'>뉴스 없음</div>");
                    return;
                }

                var $list = $("#newsList");
                $list.empty();

                $.each(data.list, function (index, news) {
                    var html = `
                        <div class="news">
                            <div>${news.title}</div>
                            <div>${news.pubDate}</div>
                        </div>
                    `;
                    $list.append(html);
                });

                // 뉴스 slick 슬라이드 적용
                initNewsSlider();
            },
            error: function () {
                $("#newsList").html("<div class='news'>뉴스 로딩 실패</div>");
            }
        });
    }

    loadDustStatus();
    loadWeather();
    loadNews();
});
