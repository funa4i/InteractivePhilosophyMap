﻿function createCustomMarker() {
    const el = document.createElement('div');
    el.className = 'custom-marker';
    el.style.backgroundImage = 'url("img/person-svgrepo-com.svg")';
    el.style.width = '30px';
    el.style.height = '30px';
    el.style.backgroundSize = 'contain';
    el.style.backgroundRepeat = 'no-repeat';
    el.style.backgroundPosition = 'center';
    el.style.cursor = 'pointer';
    return el;
}

const map = new maplibregl.Map({
    container: 'map',
    style: 'https://api.maptiler.com/maps/0195d684-c321-7bba-89d7-40ae37a5cdbb/style.json?key=8ZDpHny63KytMqnBbVdT',
    center: [23.7275, 37.9838],
    zoom: 2
});

const philosophers = [
    {
        name: "Буслаев Р.В",
        coords: [23.7275, 37.9838],
        date_birth: "16.06.2005",
        date_death: "16.06.2005",
        school: "Классическая греческая философия",
        desc: "Немецкий философ, композитор, культурный критик и филолог, чьи работы оказали глубокое влияние на современную философию. Творческое наследие Ницше обрело популярность среди художников на рубеже XIX и XX веков, повлияв на развитие авангардного направления и символистов, русской философии Серебряного века, а также в послевоенные десятилетия XX века, оказав глубокое влияние на мыслителей XX и начала XXI веков по всей философии, особенно в школах континентальной философии, таких как экзистенциализм, постмодернизм и постструктурализм...",
        img: "https://sun9-6.userapi.com/impg/gZY2dY3mlHf9xb8BylJZkypraHb6EusQxonDcA/njJTaevyNi4.jpg?size=2560x1922&quality=95&sign=2453bfc9c44c02328eed68bf3cb5691e&type=album"
    },
    {
        name: "Буслаев Р.В",
        coords: [24.7275, 37.9838],
        date_birth: "16.06.2005",
        date_death: "16.06.2005",
        school: "Классическая греческая философия",
        desc: "Немецкий философ, композитор, культурный критик и филолог, чьи работы оказали глубокое влияние на современную философию. Творческое наследие Ницше обрело популярность среди художников на рубеже XIX и XX веков, повлияв на развитие авангардного направления и символистов, русской философии Серебряного века, а также в послевоенные десятилетия XX века, оказав глубокое влияние на мыслителей XX и начала XXI веков по всей философии, особенно в школах континентальной философии, таких как экзистенциализм, постмодернизм и постструктурализм...",
        img: "https://sun9-6.userapi.com/impg/gZY2dY3mlHf9xb8BylJZkypraHb6EusQxonDcA/njJTaevyNi4.jpg?size=2560x1922&quality=95&sign=2453bfc9c44c02328eed68bf3cb5691e&type=album"
    },
    {
        name: "Буслаев Р.В",
        coords: [25.7275, 37.9838],
        date_birth: "16.06.2005",
        date_death: "16.06.2005",
        school: "Классическая греческая философия",
        desc: "Немецкий философ, композитор, культурный критик и филолог, чьи работы оказали глубокое влияние на современную философию. Творческое наследие Ницше обрело популярность среди художников на рубеже XIX и XX веков, повлияв на развитие авангардного направления и символистов, русской философии Серебряного века, а также в послевоенные десятилетия XX века, оказав глубокое влияние на мыслителей XX и начала XXI веков по всей философии, особенно в школах континентальной философии, таких как экзистенциализм, постмодернизм и постструктурализм...",
        img: "https://sun9-6.userapi.com/impg/gZY2dY3mlHf9xb8BylJZkypraHb6EusQxonDcA/njJTaevyNi4.jpg?size=2560x1922&quality=95&sign=2453bfc9c44c02328eed68bf3cb5691e&type=album"
    },
    {
        name: "Буслаев Р.В",
        coords: [26.7275, 37.9838],
        date_birth: "16.06.2005",
        date_death: "16.06.2005",
        school: "Классическая греческая философия",
        desc: "Немецкий философ, композитор, культурный критик и филолог, чьи работы оказали глубокое влияние на современную философию. Творческое наследие Ницше обрело популярность среди художников на рубеже XIX и XX веков, повлияв на развитие авангардного направления и символистов, русской философии Серебряного века, а также в послевоенные десятилетия XX века, оказав глубокое влияние на мыслителей XX и начала XXI веков по всей философии, особенно в школах континентальной философии, таких как экзистенциализм, постмодернизм и постструктурализм...",
        img: "https://sun9-6.userapi.com/impg/gZY2dY3mlHf9xb8BylJZkypraHb6EusQxonDcA/njJTaevyNi4.jpg?size=2560x1922&quality=95&sign=2453bfc9c44c02328eed68bf3cb5691e&type=album"
    },
    {
        name: "Буслаев Р.В",
        coords: [27.7275, 37.9838],
        date_birth: "16.06.2005",
        date_death: "16.06.2005",
        school: "Классическая греческая философия",
        desc: "Немецкий философ, композитор, культурный критик и филолог, чьи работы оказали глубокое влияние на современную философию. Творческое наследие Ницше обрело популярность среди художников на рубеже XIX и XX веков, повлияв на развитие авангардного направления и символистов, русской философии Серебряного века, а также в послевоенные десятилетия XX века, оказав глубокое влияние на мыслителей XX и начала XXI веков по всей философии, особенно в школах континентальной философии, таких как экзистенциализм, постмодернизм и постструктурализм...",
        img: "https://sun9-6.userapi.com/impg/gZY2dY3mlHf9xb8BylJZkypraHb6EusQxonDcA/njJTaevyNi4.jpg?size=2560x1922&quality=95&sign=2453bfc9c44c02328eed68bf3cb5691e&type=album"
    },
    {
        name: "Буслаев Р.В",
        coords: [28.7275, 37.9838],
        date_birth: "16.06.2005",
        date_death: "16.06.2005",
        school: "Классическая греческая философия",
        desc: "Немецкий философ, композитор, культурный критик и филолог, чьи работы оказали глубокое влияние на современную философию. Творческое наследие Ницше обрело популярность среди художников на рубеже XIX и XX веков, повлияв на развитие авангардного направления и символистов, русской философии Серебряного века, а также в послевоенные десятилетия XX века, оказав глубокое влияние на мыслителей XX и начала XXI веков по всей философии, особенно в школах континентальной философии, таких как экзистенциализм, постмодернизм и постструктурализм...",
        img: "https://sun9-6.userapi.com/impg/gZY2dY3mlHf9xb8BylJZkypraHb6EusQxonDcA/njJTaevyNi4.jpg?size=2560x1922&quality=95&sign=2453bfc9c44c02328eed68bf3cb5691e&type=album"
    }
];

map.on('load', () => {
    philosophers.forEach(p => {
        new maplibregl.Marker({ element: createCustomMarker() })
            .setLngLat(p.coords)
            .setPopup(
                new maplibregl.Popup({ offset: 25 })
                    .setHTML(`
                        <div class="popup-content">
                          <img src="${p.img}" alt="${p.name}" />
                          <h3>${p.name}</h3>
                          <div class="dates">${p.date_birth} – ${p.date_death}</div>
                          <div class="desc">${p.desc}</div>
                          <div class="more"><a href="#">Подробнее...</a></div>
                        </div>
                    `)
            )
    .addTo(map);
    });
});