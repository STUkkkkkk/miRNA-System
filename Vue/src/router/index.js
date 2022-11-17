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
import Layout_User from "../../layout/Layout_User";
const routes = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect: '/tourist',
    children:[
        {
         path: '/user',
         name: 'User',
         component: ()=>import("../views/User"),
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
            component: ()=>import("../views/Article"),
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
      path: '/resetPassword',
      name: 'ResetPassword' ,
      component: ()=> import("../views/ResetPassword")
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
        component: ()=>import("../views/SearchDisease"),
        meta: {
            keepAlive : true, //缓存
        }
    },
    {
        path: '/searchMirna',
        name: 'SearchMirna',
        component: ()=>import("../views/SearchMirna"),
        meta: {
            keepAlive : true, //缓存
        }
    },
    {
        path: '/community',
        name: 'Community',
        component: ()=>import("../views/Community"),
    },
    {
      path: '/articleInformation',
      name: 'ArticleInformation',
      component: ()=>import("../views/ArticleInformation")
    },
    {
        path: '/blog_info',
        name: 'Blog_info',
        component: ()=>import("../views/blog/Blog_info")
    },

    {
        path: '/user_layout',
        name: 'Layout_User',
        component: Layout_User,
        children: [
            {
                path: '/user_info',
                name: 'User_Info',
                component: ()=>import("../views/User_Info"),
                meta: {
                    index:0,
                }
            },
            {
              path: '/search_info',
              name: 'Search_Info',
              component: ()=>import("../views/Search_Info"),
                meta: {
                    index:1,
                }
            },
            {
                path: '/buy_info',
                name: 'Buy_Info',
                component: ()=>import("../views/Buy_Info"),
                meta: {
                    index:2,
                }
            },
            {
                path: '/send_Blog',
                name: 'Send_Blog',
                component: ()=>import("../views/Send_Blog"),
                meta: {
                    index:3,
                }
            },
        ]
    }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
