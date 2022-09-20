import { createRouter, createWebHistory } from 'vue-router'
import Layout from "../../layout/Layout";
import User from "../views/User";
import Register from "../views/Register";
import Disease from "../views/Disease";
import People from "../views/People";
import Index from "../views/Index";
import MiRNA from "../views/MiRNA";
import Tourist from "../views/Tourist";
import About from "../views/About";
import Test from "../views/Test";
import SearchDisease from "../views/SearchDisease";
import Article from "../views/Article";
const routes = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect: '/user',
    children:[
        {
         path: '/user',
         name: 'User',
         component: ()=>import("../views/User")
        },
        {
        path: '/disease',
        name: 'Disease',
        component: ()=>import("../views/Disease")
        },
        {
            path: '/people',
            name: 'People',
            component: ()=>import("../views/People")
        },
        {
            path: '/mirna',
            name: 'MiRNA',
            component: ()=>import("../views/MiRNA")
        },
        {
            path: '/article',
            name: 'Article',
            component: ()=>import("../views/Article")
        },
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: ()=>import("../views/Login")
  },
  {
    path: '/register',
    name: 'Register',
    component: ()=>import("../views/Register")
  },
    {
        path: '/index',
        name: 'Index',
        component: ()=>import("../views/Index")
    },
    {
        path: '/tourist',
        name: 'Tourist',
        component: ()=>import("../views/Tourist")
    },
    {
        path: '/about',
        name: 'About',
        component: ()=>import("../views/About")
    },
    {
        path: '/test',
        name: 'Test',
        component: ()=>import("../views/Test")
    },
    {
        path: '/searchDisease',
        name: 'SearchDisease',
        component: ()=>import("../views/SearchDisease")
    }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
