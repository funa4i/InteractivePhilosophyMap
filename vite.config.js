import { defineConfig } from 'vite';

export default defineConfig({
	server: {
		port: 5173,
		proxy: {
			'/humans': {
				target: 'http://localhost:8006',
				changeOrigin: true,
			}
		}
	}
});
