import { defineConfig } from 'vite';

export default defineConfig({
	server: {
		port: 5180,
		proxy: {
			'/humans': {
				target: 'http://localhost:8006',
				changeOrigin: true,
			},
			'/directions': {
				target: 'http://localhost:8006',
				changeOrigin: true,
			}
			,
			'/museums': {
				target: 'http://localhost:8006',
				changeOrigin: true,
			}
		}
	}
});
