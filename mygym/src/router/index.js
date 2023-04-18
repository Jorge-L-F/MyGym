import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import LoginView from "../views/LoginView.vue";
import RegisterView from "../views/RegisterView.vue";
import ProfileView from "../views/ProfileView.vue";
import ClassesView from "../views/ClassesView.vue";

Vue.use(VueRouter);

const routes = [
    {
        path: "/",
        name: "Home",
        component: HomeView
    },
    {
        path: "/login",
        name: "Login",
        component: LoginView
    },
    {
        path: "/register",
        name: "Register",
        component: RegisterView
    },
    {
        path: "/profile",
        name: "Profile",
        component: ProfileView
    },
    {
        path: "/classes",
        name: "Classes",
        component: ClassesView
    }
];

const router = new VueRouter({
    //mode: 'history',
    //base: process.env.BASE_URL,
    routes
});

export default router;
