function parseIds(str) {
	if (!str.trim()) return [];
	return str.split(',').map(s => parseInt(s.trim())).filter(id => !isNaN(id));
}

document.getElementById('direction-form').addEventListener('submit', async (e) => {
	e.preventDefault();
	const form = e.target;
	const data = {
		name: form.name.value,
		description: form.description.value,
		bornYear: parseInt(form.bornYear.value),
		humanFollowers: parseIds(form.humanFollowers.value),
		museumPresented: parseIds(form.museumPresented.value)
	};

	try {
		const res = await fetch('/directions', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(data)
		});

		const msg = document.getElementById('message');
		if (res.ok) {
			msg.textContent = '✅ Направление успешно добавлено!';
			msg.style.color = 'lightgreen';
			form.reset();
		} else {
			msg.textContent = '❌ Ошибка при создании направления';
			msg.style.color = 'red';
		}
	} catch (err) {
		console.error(err);
		document.getElementById('message').textContent = '❌ Ошибка соединения с сервером';
	}
});
