



// Ждём загрузки всей страницы
window.addEventListener('DOMContentLoaded', () => {
	let philosophers = [];
	let philosophersMarkers = [];
	let museums = [];
	let museumMarkers = [];
	let activeSchools = new Set();

	async function loadPhilosophersFromBackend() {
		try {
			const [philosophersRes, directionsRes] = await Promise.all([
				fetch('/humans?start=-9999&end=9999'),
				fetch('/directions?start=-9999&end=9999')
			]);
	
			philosophers = await philosophersRes.json();
			const directions = await directionsRes.json();
	
			// Привязка философов к школам
			const schoolMap = {};
			directions.forEach(dir => {
				(dir.humanFollowers || []).forEach(follower => {
					schoolMap[follower.id] = dir.name;
				});
			});
	
			philosophers.forEach(p => {
				p.school = schoolMap[p.id] || 'Без школы';
				p.followHumans = (p.followHumans || []).map(h => h.id);
				p.humanFollowers = (p.humanFollowers || []).map(h => h.id);
				p.museumPresented = (p.museumPresented || []).map(m => m.id);
			});
			
	
			placeMarkers();
			setupSchoolFilters();
			filterMarkers();
		} catch (e) {
			console.error('Ошибка при загрузке философов:', e);
		}
	}	

	loadPhilosophersFromBackend().then(loadMuseums);

	function createCustomMarker() {
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

	const rangeMin = document.getElementById('range-min');
	const rangeMax = document.getElementById('range-max');
	const display = document.getElementById('range-display');
	const minGap = 1;

	function parseYear(year) {
		if (year == null || isNaN(year)) return 0;
		return parseInt(year);
	}

    function truncateText(text, maxLength = 100) {
        return text.length > maxLength ? text.slice(0, maxLength) + '…' : text;
    }

	function formatYear(year) {
		if (!year && year !== 0) return '—';
		return year < 0 ? `${Math.abs(year)} до н.э.` : `${year}`;
	}	

	function updateSlider() {
		let min = parseInt(rangeMin.value);
		let max = parseInt(rangeMax.value);
		if (max - min < minGap) {
			if (event.target === rangeMin) {
				rangeMin.value = max - minGap;
			} else {
				rangeMax.value = min + minGap;
			}
		}
		display.textContent = `${formatYear(rangeMin.value)} - ${formatYear(rangeMax.value)}`;
		filterMarkers();
	}

	async function loadMuseums() {
		try {
			const res = await fetch('/museums?start=-9999&end=9999');
			museums = await res.json();
			console.log(museums);
			placeMuseumMarkers();
			filterMarkers();
		} catch (e) {
			console.error('Ошибка при загрузке музеев:', e);
		}
	}
	
	function createMuseumMarker() {
		const el = document.createElement('div');
		el.className = 'custom-museum-marker';
		el.style.backgroundImage = 'url("img/museum-svgrepo-com.svg")';
		el.style.width = '30px';
		el.style.height = '30px';
		el.style.backgroundSize = 'contain';
		el.style.backgroundRepeat = 'no-repeat';
		el.style.backgroundPosition = 'center';
		el.style.cursor = 'pointer';
		return el;
	}
	
	function placeMuseumMarkers() {
		museumMarkers.forEach(({ el }) => el.remove());
		museumMarkers = [];
	
		museums.forEach(m => {
			console.log(m);
	
			// Генерация HTML со списком философов
			const linkedPhilosophers = (m.humanRepresentative || [])
				.map(h => {
					return `<a href="#" class="museum-human-link" data-id="${h.id}">${h.name}</a>`;
				})
				.join(', ');
	
			const markerEl = createMuseumMarker();
			const marker = new maplibregl.Marker({ element: markerEl })
				.setLngLat([m.x, m.y])
				.setPopup(
					new maplibregl.Popup({ offset: 25 }).setHTML(`
						<div class="popup-content">
							<h3>${m.name}</h3>
							<div class="desc">${m.description || '–'}</div>
							<div class="coords">[${m.x}, ${m.y}]</div>
							<div class="museum-philosophers">
								<strong>Философы:</strong> ${linkedPhilosophers || '—'}
							</div>
						</div>
					`)
				)
				.addTo(map);
	
			museumMarkers.push({ museum: m, marker, el: markerEl });
		});
	}	

	document.body.addEventListener('click', (e) => {
		if (e.target.classList.contains('museum-human-link')) {
			e.preventDefault();
			const id = +e.target.dataset.id;
			const philosopher = philosophers.find(p => p.id === id);
			if (philosopher) {
				map.flyTo({ center: [philosopher.x, philosopher.y], zoom: 5 });
				const found = philosophersMarkers.find(m => m.philosopher.id === id);
				if (found) found.marker.togglePopup();
			}
		}
	});	

	rangeMin.addEventListener('input', updateSlider);
	rangeMax.addEventListener('input', updateSlider);
	updateSlider();

	let showPhilosophers = true;
	let showMuseums = true;

	document.getElementById('toggle-philosophers').addEventListener('click', function () {
		showPhilosophers = !showPhilosophers;
		this.classList.toggle('active');
		filterMarkers();
	});
	document.getElementById('toggle-museums').addEventListener('click', function () {
		showMuseums = !showMuseums;
		this.classList.toggle('active');
		filterMarkers();
	});
	const sidebar = document.getElementById('filter-sidebar tour-hide');
    document.getElementById('toggle-filter').addEventListener('click', () => {
    	sidebar.classList.toggle('open');
    });

	function filterMarkers() {
		const startYear = parseInt(rangeMin.value);
		const endYear = parseInt(rangeMax.value);

		philosophersMarkers.forEach(({ philosopher, marker, el }) => {
			const birth = parseYear(philosopher.bornYear);
			const death = parseYear(philosopher.dieYear);
			const matchSchool = activeSchools.has(philosopher.school);

			const visible = showPhilosophers &&
				(birth <= endYear && death >= startYear) &&
				matchSchool;

			const isVisible = el.style.display !== 'none';

			if (visible && !isVisible) {
				el.style.display = 'block';
				marker.setLngLat([philosopher.x, philosopher.y]);
			}
			if (!visible && isVisible) {
				el.style.display = 'none';
			}
		});

		museums.forEach((m, i) => {
			const el = museumMarkers[i]?.el;
			const marker = museumMarkers[i]?.marker;
			if (!el || !marker) return;
		
			const year = parseYear(m.openYear);
			const visible = showMuseums && year >= startYear && year <= endYear;
		
			const isVisible = el.style.display !== 'none';
		
			if (visible && !isVisible) {
				el.style.display = 'block';
				marker.setLngLat([m.x, m.y]);
			}
			if (!visible && isVisible) {
				el.style.display = 'none';
			}
		});
	}

    function getVisiblePhilosophers() {
        const startYear = parseInt(rangeMin.value);
        const endYear = parseInt(rangeMax.value);
    
        return philosophersMarkers
            .filter(({ philosopher, el }) => {
				const birth = parseYear(philosopher.bornYear);
				const death = parseYear(philosopher.dieYear);				
                const matchSchool = activeSchools.has(philosopher.school);
                const visible = showPhilosophers &&
                    (birth <= endYear && death >= startYear) &&
                    matchSchool;
    
                return visible;
            });
    }

	function placeMarkers() {
		philosophersMarkers = [];
	
		console.log('Загружено философов:', philosophers);

		philosophers.forEach(p => {
			const markerEl = createCustomMarker();
			const marker = new maplibregl.Marker({ element: markerEl })
				.setLngLat([p.x, p.y])
				.setPopup(
					new maplibregl.Popup({ offset: 25 })
						.setHTML(`
							<div class="popup-content">
								<img src="${p.iconPath}" alt="${p.name}" />
								<h3>${p.name}</h3>
								<div class="dates">${formatYear(p.bornYear)} – ${formatYear(p.dieYear)}</div>
								<div class="desc">${truncateText(p.description, 400)}</div>
								<div class="more"><a href="#" class="show-details" data-id="${p.id}">Подробнее...</a></div>
							</div>
						`)
				)
				.addTo(map);
	
			philosophersMarkers.push({ philosopher: p, marker, el: markerEl });
		});
		filterMarkers();
	}
	
	const schoolList = document.getElementById('school-list');

	function setupSchoolFilters() {
		const allSchools = [...new Set(philosophers.map(p => p.school).filter(Boolean))];
		activeSchools = new Set(allSchools);
		schoolList.innerHTML = '';
	
		allSchools.forEach(school => {
			const li = document.createElement('li');
			const checkbox = document.createElement('input');
			checkbox.type = 'checkbox';
			checkbox.checked = true;
			checkbox.id = `chk-${school}`;
			checkbox.addEventListener('change', () => {
				if (checkbox.checked) {
					activeSchools.add(school);
				} else {
					activeSchools.delete(school);
				}
				filterMarkers();
			});
	
			const label = document.createElement('label');
			label.htmlFor = checkbox.id;
			label.textContent = school;
	
			li.appendChild(checkbox);
			li.appendChild(label);
			schoolList.appendChild(li);
		});
	}

	// Поиск
	const searchInput = document.getElementById('search-input');
	const searchResults = document.getElementById('search-results');

	searchInput.addEventListener('input', () => {
		const rawQuery = searchInput.value.trim();
		searchResults.innerHTML = '';
		if (rawQuery === '') return;
	
		let queryType = 'name';
		let query = rawQuery.toLowerCase();
	
		if (rawQuery.startsWith('#')) {
			queryType = 'school';
			query = query.slice(1).trim().toLowerCase();
		} else if (rawQuery.startsWith('$')) {
			queryType = 'followers';
			query = query.slice(1).trim().toLowerCase();
		}
	
		const matched = philosophers.filter(p => {
			if (queryType === 'name') return p.name.toLowerCase().includes(query);
			if (queryType === 'school') return (p.school || '').toLowerCase().includes(query);
			if (queryType === 'followers') return (p.description || '').toLowerCase().includes(query);
			return false;
		});
	
		matched.forEach(p => {
			const card = document.createElement('div');
			card.className = 'search-card';
			card.innerHTML = `
				<img src="${p.iconPath}" alt="${p.name}" />
				<div class="info">
					<strong>${p.name}</strong><br />
					<small>${p.school}</small><br />
					<small>${formatYear(p.bornYear)} – ${formatYear(p.dieYear)}</small>
				</div>
			`;
			card.addEventListener('click', () => {
				map.flyTo({ center: [p.x, p.y], zoom: 5 });
				const found = philosophersMarkers.find(m => m.philosopher.id === p.id);
				if (found) found.marker.togglePopup();
				searchResults.innerHTML = '';
			});
			searchResults.appendChild(card);
		});
	});	

	document.addEventListener('click', (event) => {
		const isClickInside =
			searchInput.contains(event.target) ||
			searchResults.contains(event.target);
		if (!isClickInside) {
			searchResults.innerHTML = '';
		}
	});

	// Подробнее
	const detailBox = document.getElementById('philosopher-details');
	const closeBtn = document.getElementById('close-details');

	closeBtn.addEventListener('click', () => {
		detailBox.classList.add('hidden');
	});

	document.body.addEventListener('click', (e) => {
		if (e.target.classList.contains('show-details')) {
			e.preventDefault();
			const id = +e.target.dataset.id;
			const p = philosophers.find(p => p.id === id);
			if (p) showDetails(p);
		}
	});

	function showDetails(p) {
		document.getElementById('details-icon').src = p.iconPath || '';
		document.getElementById('details-name').textContent = p.name;
		document.getElementById('details-born-died').textContent = `${formatYear(p.bornYear)} – ${formatYear(p.dieYear)}`;
		document.getElementById('details-desc').textContent = p.description || '–';
		document.getElementById('details-coords').textContent = `[${p.x}, ${p.y}]`;
		document.getElementById('details-museums').textContent =
			(p.museumPresented?.map(m => m.name).join(', ')) || '–';
	
		renderInfluenceGraph(p, false);
	
		const expandBtn = document.getElementById('expand-graph');
		if (expandBtn) {
			expandBtn.style.display = 'block';
			expandBtn.onclick = () => {
				renderInfluenceGraph(p, true);
				expandBtn.style.display = 'none';
			};
		}
	
		detailBox.classList.remove('hidden');
	}
	

    //Экскурсия
    let tourIndex = 0;
    let tourTimer = null;
    let isTourActive = false;
    let visibleTourMarkers = [];

    const btnStartTour = document.getElementById('start-tour');
    const btnStopTour = document.getElementById('stop-tour');

    btnStartTour.addEventListener('click', () => {
    	visibleTourMarkers = getVisiblePhilosophers();

    	if (visibleTourMarkers.length === 0) {
    		alert('Нет философов для экскурсии по текущим фильтрам!');
    		return;
    	}

    	isTourActive = true;
    	btnStartTour.style.display = 'none';
    	btnStopTour.style.display = 'inline-block';
    	tourIndex = 0;
    	startTour();
    });

    btnStopTour.addEventListener('click', stopTour);

    function startTour() {
		if (!isTourActive || tourIndex >= visibleTourMarkers.length) {
			stopTour();
			return;
		}
	
		hideUIForTour();
	
		const { philosopher, marker } = visibleTourMarkers[tourIndex];
	
		map.flyTo({ center: [philosopher.x, philosopher.y], zoom: 8 });
	
		setTimeout(() => {
			marker.togglePopup();
		}, 1000);
	
		tourTimer = setTimeout(() => {
			marker.togglePopup();
			tourIndex++;
			startTour();
		}, 4000);
	}
	

    function stopTour() {
    	isTourActive = false;
    	clearTimeout(tourTimer);
    	btnStartTour.style.display = 'inline-block';
    	btnStopTour.style.display = 'none';
        showUIAfterTour();
    }

    function hideUIForTour() {
        document.querySelectorAll('.tour-hide').forEach(el => {
            el.style.display = 'none';
        });
    }
    
    function showUIAfterTour() {
        document.querySelectorAll('.tour-hide').forEach(el => {
            el.style.display = '';
        });
    }

    function renderInfluenceGraph(p, expand = false) {
        const container = document.getElementById('influence-graph');
        container.innerHTML = '';
    
        const nodes = [];
        const edges = [];
    
        const visited = new Set();
        nodes.push({ id: p.id, label: p.name, color: '#ffcc00' }); // 🟡 центральный
    
        visited.add(p.id);
    
        // === 1 уровень: предшественники
        (p.followHumans || []).forEach(id => {
            const target = philosophers.find(ph => ph.id === id);
            if (target && !visited.has(target.id)) {
                nodes.push({ id: target.id, label: target.name, color: '#ff6666' }); // 🔴
                edges.push({ from: target.id, to: p.id });
                visited.add(target.id);
            }
        });
    
        // === 1 уровень: наследники
        (p.humanFollowers || []).forEach(id => {
            const target = philosophers.find(ph => ph.id === id);
            if (target && !visited.has(target.id)) {
                nodes.push({ id: target.id, label: target.name, color: '#66ccff' }); // 🔵
                edges.push({ from: p.id, to: target.id });
                visited.add(target.id);
            }
    
            // === 2 уровень: наследники наследников и тд (если expand = true)
            if (expand && target?.humanFollowers) {
                target.humanFollowers.forEach(subId => {
                    addFollowersRecursively(subId, target.id);
                });
            }            
        });
    
        const data = {
            nodes: new vis.DataSet(nodes),
            edges: new vis.DataSet(edges)
        };
    
        const options = {
            nodes: {
                shape: 'dot',
                size: 20,
                font: { size: 16 }
            },
            edges: {
                arrows: 'to'
            },
            interaction: {
                hover: true
            },
            physics: {
                stabilization: true
            }
        };
    
        const network = new vis.Network(container, data, options);
    
        network.on('click', function (params) {
			if (params.nodes.length > 0) {
				const id = params.nodes[0];
				const philosopher = philosophers.find(p => p.id === id);
				if (philosopher) {
					closeAllPopups();
					map.flyTo({ center: [philosopher.x, philosopher.y], zoom: 5 });
					const found = philosophersMarkers.find(m => m.philosopher.id === id);
					if (found) found.marker.togglePopup();
					detailBox.classList.add('hidden');
				}
			}
		});		

        function addFollowersRecursively(philosopherId, fromId) {
            const p = philosophers.find(ph => ph.id === philosopherId);
            if (!p || visited.has(p.id)) return;
        
            visited.add(p.id);
        
            nodes.push({
                id: p.id,
                label: p.name,
                color: '#88e188'
            });
            edges.push({ from: fromId, to: p.id });
        
            if (p.humanFollowers) {
                p.humanFollowers.forEach(followerId => {
                    addFollowersRecursively(followerId, p.id);
                });
            }
        }
    }     

    function closeAllPopups() {
        philosophersMarkers.forEach(({ marker }) => {
            marker.getPopup()?.remove();
        });
    }
    
});

