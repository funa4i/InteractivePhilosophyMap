function parseIds(str) {
	if (!str.trim()) return [];
	return str.split(',').map(s => parseInt(s.trim())).filter(id => !isNaN(id));
}

document.getElementById('museum-form').addEventListener('submit', async (e) => {
	e.preventDefault();
	const form = e.target;

	const data = {
		name: form.name.value,
		description: form.description.value,
		openYear: parseInt(form.bornYear.value),
		iconPath: form.iconPath.value,
		x: parseFloat(form.x.value),
		y: parseFloat(form.y.value),
		humanRepresentative: parseIds(form.humanRepresentative.value),
		directionRepresentative: parseIds(form.directionRepresentative.value) // ← ВАЖНО
	};

	try {
		const res = await fetch('/museums', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(data)
		});

		const msg = document.getElementById('message');
		if (res.ok) {
			msg.textContent = '✅ Музей успешно создан!';
			msg.style.color = 'lightgreen';
			form.reset();
		} else {
			msg.textContent = '❌ Ошибка при создании';
			msg.style.color = 'red';
		}
	} catch (err) {
		console.error(err);
		const msg = document.getElementById('message');
		msg.textContent = '❌ Ошибка соединения с сервером';
		msg.style.color = 'red';
	}
});
