import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import router from '@/router/index.js'
import axios from 'axios'

createApp(App).use(router,axios).mount('#app');