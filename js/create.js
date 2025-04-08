function parseIds(str) {
	if (!str.trim()) return [];
	return str.split(',').map(s => parseInt(s.trim())).filter(id => !isNaN(id));
}

document.getElementById('app').innerHTML = `
	<h1>Создание / редактирование философа</h1>
	<form id="philosopher-form">
		<p>ID философа:
			<select id="philosopher-id">
				<option value="">— Новый философ —</option>
			</select>
		</p>
		<p>Имя: <input name="name" required /></p>
		<p>Описание: <textarea name="description" required></textarea></p>
		<p>Год рождения: <input name="bornDate" type="number" required /></p>
		<p>Год смерти: <input name="dieDate" type="number" /></p>
		<p>Иконка (URL): <input name="iconPath" type="url" /></p>
		<p>X: <input name="x" type="number" step="0.00001" required /></p>
		<p>Y: <input name="y" type="number" step="0.00001" /></p>
		<p>ID последователей (через запятую): <input name="humanFollowers" /></p>
		<p>ID кого наследует (через запятую): <input name="followHumans" /></p>
		<p>ID музеев (через запятую): <input name="museumPresentedId" /></p>
		<button type="submit">Сохранить</button>
	<p id="message"></p>
`;

// Элементы
const form = document.getElementById('philosopher-form');
const select = document.getElementById('philosopher-id');
const deleteBtn = document.getElementById('delete-btn');
const msg = document.getElementById('message');

// Загрузка всех философов для выбора
async function loadPhilosophers() {
	try {
		const res = await fetch('/humans?start=-10000&end=3000');
		const philosophers = await res.json();
		philosophers.forEach(p => {
			const opt = document.createElement('option');
			opt.value = p.id;
			opt.textContent = `#${p.id} ${p.name}`;
			select.appendChild(opt);
		});
	} catch (err) {
		console.error('Ошибка загрузки философов:', err);
	}
}

// Обработка выбора ID
select.addEventListener('change', async () => {
	const id = select.value;
	if (!id) {
		form.reset();
		deleteBtn.style.display = 'none';
		msg.textContent = '';
		return;
	}

	try {
		const res = await fetch(`/humans/${id}`);
		const data = await res.json();

		form.name.value = data.name;
		form.description.value = data.description;
		form.bornDate.value = data.bornYear;
		form.dieDate.value = data.dieYear || '';
		form.iconPath.value = data.iconPath || '';
		form.x.value = data.x;
		form.y.value = data.y;
		form.humanFollowers.value = (data.humanFollowers || []).map(h => h.id).join(', ');
		form.followHumans.value = (data.followHumans || []).map(h => h.id).join(', ');
		form.museumPresentedId.value = (data.museumPresentedId || []).map(m => m.id).join(', ');

		deleteBtn.style.display = 'inline-block';
		msg.textContent = '';
	} catch (err) {
		console.error('Ошибка загрузки:', err);
	}
});

// Отправка формы (создание или обновление)
form.addEventListener('submit', async (e) => {
	e.preventDefault();

	const data = {
		id: select.value ? parseInt(select.value) : null,
		name: form.name.value,
		description: form.description.value,
		bornYear: parseInt(form.bornDate.value),
		dieYear: parseInt(form.dieDate.value),
		iconPath: form.iconPath.value,
		x: parseFloat(form.x.value),
		y: parseFloat(form.y.value),
		humanFollowers: parseIds(form.humanFollowers.value),
		followHumans: parseIds(form.followHumans.value),
		museumPresented: parseIds(form.museumPresentedId.value)
	};

	try {
		const res = await fetch('/humans', {
			method: data.id ? 'PUT' : 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify(data)
		});

		if (res.ok) {
			msg.textContent = data.id ? '✅ Данные философа обновлены!' : '✅ Философ успешно создан!';
			msg.style.color = 'green';
			form.reset();
			select.value = '';
			deleteBtn.style.display = 'none';
		} else {
			msg.textContent = '❌ Ошибка при сохранении';
			msg.style.color = 'red';
		}
	} catch (err) {
		console.error(err);
		msg.textContent = '❌ Ошибка соединения с сервером';
		msg.style.color = 'red';
	}
});

// Инициализация
loadPhilosophers();
