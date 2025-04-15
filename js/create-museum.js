// Функция для разбиения строки "1,2,3" -> [1,2,3]
function parseIds(str) {
	if (!str || !str.trim()) return [];
	return str
	  .split(',')
	  .map((s) => parseInt(s.trim()))
	  .filter((id) => !isNaN(id));
  }
  
  // Селект и форма:
  const museumSelect = document.getElementById('museum-id');
  const museumForm = document.getElementById('museum-form');
  const deleteBtn = document.getElementById('delete-btn');
  const msg = document.getElementById('message');
  
  // Поля формы (чтобы не искать каждый раз по name)
  const f = {
	name: museumForm.name,
	description: museumForm.description,
	bornYear: museumForm.bornYear,
	iconPath: museumForm.iconPath,
	x: museumForm.x,
	y: museumForm.y,
	humanRepresentative: museumForm.humanRepresentative,
	directionRepresentative: museumForm.directionRepresentative,
  };
  
  // 1. Загрузка списка музеев для селекта
  async function loadMuseums() {
	try {
	  const res = await fetch('/museums?start=-9999&end=9999');
	  const museums = await res.json();
  
	  // Заполним селект
	  museums.forEach((m) => {
		const opt = document.createElement('option');
		opt.value = m.id;
		opt.textContent = `#${m.id} ${m.name}`;
		museumSelect.appendChild(opt);
	  });
	} catch (err) {
	  console.error('Ошибка при загрузке списка музеев:', err);
	}
  }
  
  // 2. При выборе ID из списка — загрузка данных музея
  museumSelect.addEventListener('change', async () => {
	const id = museumSelect.value;
	if (!id) {
	  // Новый музей
	  museumForm.reset(); // очистить поля
	  msg.textContent = '';
	  deleteBtn.style.display = 'none';
	  return;
	}
  
	try {
	  const res = await fetch(`/museums/${id}`);
	  if (!res.ok) {
		throw new Error('Ошибка загрузки музея');
	  }
	  const data = await res.json();
	  // Заполним поля:
	  f.name.value = data.name || '';
	  f.description.value = data.description || '';
	  f.bornYear.value = data.openYear ?? '';
	  f.iconPath.value = data.iconPath ?? '';
	  f.x.value = data.x ?? '';
	  f.y.value = data.y ?? '';
	  f.humanRepresentative.value = (data.humanRepresentative || [])
		.map((h) => h.id)
		.join(', ');
	  f.directionRepresentative.value = (data.directionRepresentative || [])
		.map((d) => d.id)
		.join(', ');
  
	  msg.textContent = '';
	  deleteBtn.style.display = 'inline-block';
	} catch (err) {
	  console.error('Ошибка загрузки музея:', err);
	  msg.textContent = 'Не удалось загрузить музей.';
	  deleteBtn.style.display = 'none';
	}
  });
  
  // 3. Сохранение (POST или PUT)
  museumForm.addEventListener('submit', async (e) => {
	e.preventDefault();
	const id = museumSelect.value ? parseInt(museumSelect.value) : null;
  
	const data = {
	  id: id,
	  name: f.name.value,
	  description: f.description.value,
	  openYear: parseInt(f.bornYear.value),
	  iconPath: f.iconPath.value,
	  x: parseFloat(f.x.value),
	  y: parseFloat(f.y.value),
	  humanRepresentative: parseIds(f.humanRepresentative.value),
	  directionRepresentative: parseIds(f.directionRepresentative.value),
	};
  
	// Если id есть — обновляем (PUT), иначе создаём (POST)
	const method = id ? 'PUT' : 'POST';
  
	try {
	  const res = await fetch('/museums', {
		method: method,
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify(data),
	  });
  
	  if (res.ok) {
		msg.textContent = id
		  ? '✅ Музей успешно обновлён!'
		  : '✅ Музей успешно создан!';
		msg.style.color = 'green';
  
		// при успехе можно сбросить поля или оставить как есть
		if (!id) {
		  museumForm.reset();
		  museumSelect.value = '';
		  deleteBtn.style.display = 'none';
		}
	  } else {
		msg.textContent = '❌ Ошибка при сохранении музея';
		msg.style.color = 'red';
	  }
	} catch (err) {
	  console.error(err);
	  msg.textContent = '❌ Ошибка соединения с сервером';
	  msg.style.color = 'red';
	}
  });
  
  // 4. Удаление
  deleteBtn.addEventListener('click', async () => {
	const id = museumSelect.value;
	if (!id) return;
  
	if (!confirm('Удалить этот музей?')) return;
  
	try {
	  const res = await fetch(`/museums/${id}`, {
		method: 'DELETE',
	  });
	  if (res.ok) {
		msg.textContent = '✅ Музей удалён';
		msg.style.color = 'green';
		// удаляем option из select
		const optToRemove = museumSelect.querySelector(`option[value="${id}"]`);
		if (optToRemove) optToRemove.remove();
		museumSelect.value = '';
		museumForm.reset();
		deleteBtn.style.display = 'none';
	  } else {
		msg.textContent = '❌ Ошибка при удалении музея';
		msg.style.color = 'red';
	  }
	} catch (err) {
	  console.error(err);
	  msg.textContent = '❌ Ошибка соединения при удалении';
	  msg.style.color = 'red';
	}
  });
  
  // Наконец, вызываем loadMuseums при старте:
  loadMuseums();
  