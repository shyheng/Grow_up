import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "../components/login/Login";
import Home from "../components/login/Home";
import Grow_index from "../components/grow/Grow_index";
import All_index from "../components/all/All_index";
import Grow_home from "../components/grow/Grow_home";
import Work from "../components/grow/Work";
import Plan from "../components/grow/Plan";
import Healthy from "../components/grow/Healthy";
import Diary_person from "../components/grow/Diary_person";
import Target from "../components/grow/Target";
import Property from "../components/grow/Property";
import Personal from "../components/grow/Personal";
import Agreement from "../components/login/Agreement";
import Regedit from "../components/login/Regedit";

Vue.use(VueRouter)

const routes = [
    {
        path: "/",
        redirect: "/login"
    },
    {
        path: "/login",
        component: Login
    },
    {
        path: "/home",
        component: Home
    },
    {
        path: "/grow_index",
        component: Grow_index,
        redirect: "/grow_home",
        children: [
            {path: "/grow_home", component: Grow_home},
            {path: "/work", component: Work},
            {path: "/plan", component: Plan},
            {path: "/healthy", component: Healthy},
            {path: "/diary_person", component: Diary_person},
            {path: "/target", component: Target},
            {path: "/property", component: Property},
        ]
    },
    {
        path: "/all_index",
        component: All_index
    },
    {
        path: "/personal",
        component: Personal
    },
    {
        path: "/agreement",
        component: Agreement
    },
    {
        path: "/regedit",
        component: Regedit
    },
]

const router = new VueRouter({
    routes,
    mode: "history"
})

export default router
