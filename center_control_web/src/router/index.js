import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/home/',
    name: 'home',
    component: () => import("@/views/HomePage.vue")
  },
  {
    path:"/",
    name:"root",
    redirect:"/home/"
  },
  {
    path:'/sub/',
    name:'sub',
    component: () => import('@/views/SubPage.vue')
  },
  {
    path:'/login/',
    name:'login',
    component: () => import('@/views/LoginPage.vue')
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
