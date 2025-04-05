function parseIds(str) {
	if (!str.trim()) return [];
	return str.split(',').map(s => parseInt(s.trim())).filter(id => !isNaN(id));
}

document.getElementById('app').innerHTML = `
	<h1>Создание философа</h1>
	<form id="philosopher-form">
		<p>Имя: <input name="name" required /></p>
		<p>Описание: <textarea name="description" required></textarea></p>
		<p>Год рождения: <input name="bornDate" type="number" required /></p>
        <p>Год смерти: <input name="dieDate" type="number" /></p>
		<p>Иконка (URL): <input name="iconPath" type="url" /></p>
		<p>X: <input name="x" type="number" step="0.01" required /></p>
		<p>Y: <input name="y" type="number" step="0.01" required /></p>
		<p>ID последователей (через запятую): <input name="humanFollowers" /></p>
		<p>ID кого наследует (через запятую): <input name="followHumans" /></p>
		<p>ID музеев (через запятую): <input name="museumPresentedId" /></p>
		<button type="submit">Создать</button>
	</form>
	<p id="message"></p>
`;

document.getElementById('philosopher-form').addEventListener('submit', async (e) => {
	e.preventDefault();

	const form = e.target;
	const data = {
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
            method: 'POST',
            mode: 'cors',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });
        

		const msg = document.getElementById('message');
		if (res.ok) {
			msg.textContent = '✅ Философ успешно создан!';
			msg.style.color = 'green';
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
