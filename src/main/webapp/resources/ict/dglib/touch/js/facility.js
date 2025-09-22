$(function () {
    const facilityData = {
        B1F: {
            map: "/resources/ict/dglib/touch/img/facility/B1F.png",
            facilities: [
                { name: "Facility 1", label: "전시실", icon: "/resources/ict/dglib/touch/img/common/dummy.png", popupImg: "/resources/ict/dglib/touch/img/common/dummy.png", desc: ["다양한 전시가 열리는 공간", "문화 행사 진행"], caption: "전시실 안내" },
                { name: "Facility 2", label: "북카페", icon: "/resources/ict/dglib/touch/img/common/dummy.png", popupImg: "/resources/ict/dglib/touch/img/common/dummy.png", desc: ["커피와 함께 책을 즐길 수 있는 공간"], caption: "북카페 안내" },
                { name: "Facility 3", label: "전시실", icon: "/resources/ict/dglib/touch/img/common/dummy.png", popupImg: "/resources/ict/dglib/touch/img/common/dummy.png", desc: ["다양한 전시가 열리는 공간", "문화 행사 진행"], caption: "전시실 안내" },
                { name: "Facility 4", label: "북카페", icon: "/resources/ict/dglib/touch/img/common/dummy.png", popupImg: "/resources/ict/dglib/touch/img/common/dummy.png", desc: ["커피와 함께 책을 즐길 수 있는 공간"], caption: "북카페 안내" },
                { name: "Facility 5", label: "전시실", icon: "/resources/ict/dglib/touch/img/common/dummy.png", popupImg: "/resources/ict/dglib/touch/img/common/dummy.png", desc: ["다양한 전시가 열리는 공간", "문화 행사 진행"], caption: "전시실 안내" },
                { name: "Facility 6", label: "북카페", icon: "/resources/ict/dglib/touch/img/common/dummy.png", popupImg: "/resources/ict/dglib/touch/img/common/dummy.png", desc: ["커피와 함께 책을 즐길 수 있는 공간"], caption: "북카페 안내" },
                { name: "Facility 7", label: "북카페", icon: "/resources/ict/dglib/touch/img/common/dummy.png", popupImg: "/resources/ict/dglib/touch/img/common/dummy.png", desc: ["커피와 함께 책을 즐길 수 있는 공간"], caption: "북카페 안내" }
            ]
        },
        "1F": {
            map: "/resources/ict/dglib/touch/img/facility/1F.png",
            facilities: [
                { name: "Facility 1", label: "대강당", icon: "/resources/ict/dglib/touch/img/common/dummy.png", popupImg: "/resources/ict/dglib/touch/img/common/dummy.png", desc: ["대형 행사와 강연 진행 공간"], caption: "대강당 안내" }
            ]
        },
        "2F": {
            map: "/resources/ict/dglib/touch/img/facility/2F.png",
            facilities: [
                { name: "Facility 1", label: "대강당", icon: "/resources/ict/dglib/touch/img/common/dummy.png", popupImg: "/resources/ict/dglib/touch/img/common/dummy.png", desc: ["대형 행사와 강연 진행 공간"], caption: "대강당 안내" }
            ]
        },
        "3F": {
            map: "/resources/ict/dglib/touch/img/facility/3F.png",
            facilities: [
                { name: "Facility 1", label: "대강당", icon: "/resources/ict/dglib/touch/img/common/dummy.png", popupImg: "/resources/ict/dglib/touch/img/common/dummy.png", desc: ["대형 행사와 강연 진행 공간"], caption: "대강당 안내" }
            ]
        },
        "4F": {
            map: "/resources/ict/dglib/touch/img/facility/4F.png",
            facilities: [
                { name: "Facility 1", label: "대강당", icon: "/resources/ict/dglib/touch/img/common/dummy.png", popupImg: "/resources/ict/dglib/touch/img/common/dummy.png", desc: ["대형 행사와 강연 진행 공간"], caption: "대강당 안내" }
            ]
        }
    };

    const $filterArea = $(".filterArea");
    const $facilityList = $(".facilityList");
    const $popup = $("#facilityPopup");
    const $map = $("#floorMap");
    let firstLoad = true;

    Object.keys(facilityData).forEach(floor =>
        $filterArea.append(`<div data-floor="${floor}">${floor}</div>`)
    );

    function setActiveFloor(floor) {
        const $all = $filterArea.find("div");
        const $active = $all.filter(`[data-floor="${floor}"]`);

        $all.each(function() {
            gsap.to(this, {
                scale: 1,
                backgroundColor: "rgba(255,255,255,0)",
                color: "#8B95A1",
                duration: 0.1,
                ease: "power1.out"
            });
        });

        gsap.to($active[0], {
            scale: 1,
            backgroundColor: "#191F28",
            color: "#ffffff",
            duration: 0.1,
            ease: "power1.inOut",
            onComplete: () => {
                gsap.to($active[0], { scale: 1.15, duration: 0.1, ease: "power1.out" });
            }
        });
    }

    function renderFacilities(floor) {
        const { map, facilities } = facilityData[floor];

        if (firstLoad) {
            $map.attr("src", map);
            firstLoad = false;
        } else {
            $map.fadeOut(300, () => $map.attr("src", map).one("load", () => $map.fadeIn(300)));
        }

        const html = [];
        for (let i = 0; i < facilities.length; i += 6) {
            html.push(`<div class="facilityItem">`);
            facilities.slice(i, i + 6).forEach((f, j) => {
                html.push(`
          <div class="facility" data-floor="${floor}" data-index="${i + j}">
            <img src="${f.icon}" alt="">
            <div>${f.name}</div>
            <div>${f.label}</div>
          </div>`);
            });
            html.push(`</div>`);
        }

        if ($facilityList.hasClass("slick-initialized")) $facilityList.slick("unslick");
        $facilityList.html(html.join("")).slick({
            slidesToShow: 1,
            arrows: false,
            autoplay: true,
            autoplaySpeed: 8000,
            dots: facilities.length > 6,
            infinite: true
        });
    }

    function showPopup(floor, index) {
        const { name, label, popupImg, desc, caption } = facilityData[floor].facilities[index];
        $("#popupTitle").html(`<div>${name}</div><div>${label}</div>`);
        $("#popupImg").attr("src", popupImg);
        $("#popupDesc").html(desc.map(d => `<li>${d}</li>`).join(""));
        $("#popupCaption").text(caption);
        $popup.fadeIn();
    }

    $filterArea.on("click", "div", function () {
        const floor = $(this).data("floor");
        setActiveFloor(floor);
        renderFacilities(floor);
    });

    $facilityList.on("click", ".facility", function () {
        showPopup($(this).data("floor"), $(this).data("index"));
    });

    $popup.on("click", ".popup, .popupClose", () => $popup.fadeOut());

    setActiveFloor("1F");
    renderFacilities("1F");


    const mapImg = document.querySelector('.mapArea img');
    const mapArea = document.querySelector('.mapArea');

    function animateMap(x, y, rect) {
        const offsetX = (x / rect.width - 0.5) * 2;
        const offsetY = (y / rect.height - 0.5) * 2;

        gsap.to(mapImg, {
            x: offsetX * 40,
            y: offsetY * 40,
            rotateX: offsetY * 10,
            rotateY: offsetX * 10,
            duration: 0.5,
            ease: "power2.out"
        });
    }

    // PC용
    mapArea.addEventListener('mousemove', (e) => {
        const rect = mapArea.getBoundingClientRect();
        animateMap(e.clientX - rect.left, e.clientY - rect.top, rect);
    });

    // 모바일용
    mapArea.addEventListener('touchmove', (e) => {
        const rect = mapArea.getBoundingClientRect();
        const touch = e.touches[0]; // 첫 번째 손가락
        animateMap(touch.clientX - rect.left, touch.clientY - rect.top, rect);
    });

    // 영역 벗어나면 원래대로
    ["mouseleave", "touchend", "touchcancel"].forEach(evt => {
        mapArea.addEventListener(evt, () => {
            gsap.to(mapImg, {
                x: 0,
                y: 0,
                rotateX: 0,
                rotateY: 0,
                duration: 0.8,
                ease: "power3.out"
            });
        });
    });

});