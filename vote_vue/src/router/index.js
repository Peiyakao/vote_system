import { createRouter, createWebHistory } from "vue-router";

const routes = [
  {path: '/', name: 'Home', component: () => import('@/views/front.vue')},
  {path: '/back', name: 'back', component: () => import('@/views/back.vue')},
];

const router = createRouter({
  history: createWebHistory(),
  routes:routes
});

export default router;