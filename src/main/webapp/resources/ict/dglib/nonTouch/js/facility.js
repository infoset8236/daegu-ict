$(function () {
  const facilityData = {
    B1F: {
      map: "/resources/ict/dglib/touch/img/facility/B1F.png",
      facilities: [
        { name: "Facility 1", label: "전시실", icon: "/resources/ict/dglib/touch/img/common/dummy.png", operatingHours: "09:00 ~ 18:00", target: "전체 이용객" },
        { name: "Facility 2", label: "북카페", icon: "/resources/ict/dglib/touch/img/common/dummy.png", operatingHours: "10:00 ~ 20:00", target: "성인 및 청소년" },
        { name: "Facility 3", label: "전시실", icon: "/resources/ict/dglib/touch/img/common/dummy.png", operatingHours: "09:00 ~ 18:00", target: "전체 이용객" },
        { name: "Facility 4", label: "북카페", icon: "/resources/ict/dglib/touch/img/common/dummy.png", operatingHours: "10:00 ~ 20:00", target: "성인 및 청소년" },
        { name: "Facility 5", label: "전시실", icon: "/resources/ict/dglib/touch/img/common/dummy.png", operatingHours: "09:00 ~ 18:00", target: "전체 이용객" },
        { name: "Facility 6", label: "북카페", icon: "/resources/ict/dglib/touch/img/common/dummy.png", operatingHours: "10:00 ~ 20:00", target: "성인 및 청소년" },
        { name: "Facility 7", label: "북카페", icon: "/resources/ict/dglib/touch/img/common/dummy.png", operatingHours: "10:00 ~ 20:00", target: "성인 및 청소년" }
      ]
    },
    "1F": { map: "/resources/ict/dglib/touch/img/facility/1F.png", facilities: [{ name: "Facility 1", label: "대강당", icon: "/resources/ict/dglib/touch/img/common/dummy.png", operatingHours: "09:00 ~ 21:00", target: "단체 예약 및 행사 참가자" }] },
    "2F": { map: "/resources/ict/dglib/touch/img/facility/2F.png", facilities: [{ name: "Facility 1", label: "대강당", icon: "/resources/ict/dglib/touch/img/common/dummy.png", operatingHours: "09:00 ~ 21:00", target: "단체 예약 및 행사 참가자" }] },
    "3F": { map: "/resources/ict/dglib/touch/img/facility/3F.png", facilities: [{ name: "Facility 1", label: "대강당", icon: "/resources/ict/dglib/touch/img/common/dummy.png", operatingHours: "09:00 ~ 21:00", target: "단체 예약 및 행사 참가자" }] },
    "4F": { map: "/resources/ict/dglib/touch/img/facility/4F.png", facilities: [{ name: "Facility 1", label: "대강당", icon: "/resources/ict/dglib/touch/img/common/dummy.png", operatingHours: "09:00 ~ 21:00", target: "단체 예약 및 행사 참가자" }] }
  };

  const $filterArea = $(".filterArea");
  const $facilityList = $(".facilityList");
  const $map = $("#floorMap");
  const floors = Object.keys(facilityData);
  let currentFloorIndex = 0;
  let firstLoad = true;

  floors.forEach(floor => $filterArea.append(`<div data-floor="${floor}">${floor}</div>`));

  function setActiveFloor(floor) {
    const $all = $filterArea.find("div");
    const $active = $all.filter(`[data-floor="${floor}"]`);

    $all.each(function () {
      gsap.to(this, { scale: 1, backgroundColor: "rgba(255,255,255,0)", color: "#8B95A1", duration: 0.1 });
    });
    gsap.to($active[0], { scale: 1.15, backgroundColor: "#191F28", color: "#ffffff", duration: 0.1 });
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
    for (let i = 0; i < facilities.length; i += 3) {
      html.push(`<div class="facilityItem">`);
      facilities.slice(i, i + 3).forEach((f, j) => {
        html.push(`
          <div class="facility" data-floor="${floor}" data-index="${i + j}">
            <img src="${f.icon}" alt="">
            <div>${f.name}</div>
            <div>${f.label}</div>
            <div>운영시간: ${f.operatingHours}</div>
            <div>운영대상: ${f.target}</div>
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
      dots: facilities.length > 3,
      infinite: true
    });
  }

  function autoCycleFloors() {
    const floor = floors[currentFloorIndex];
    setActiveFloor(floor);
    renderFacilities(floor);

    const facilities = facilityData[floor].facilities;
    const slidePages = Math.ceil(facilities.length / 3);
    const stayTime = slidePages * 8000;

    currentFloorIndex = (currentFloorIndex + 1) % floors.length;
    setTimeout(autoCycleFloors, stayTime);
  }

  autoCycleFloors();
});
