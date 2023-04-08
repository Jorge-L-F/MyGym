<template>
    <v-app id="app">
        <v-app-bar
            v-if="isLoggedIn"
            :clipped-left="$vuetify.breakpoint.lgAndUp"
            app
            color="primary"
            dark
        >
            <v-app-bar-nav-icon @click.stop="drawer = !drawer">
                <template>
                    <v-icon>menu</v-icon>
                </template>
            </v-app-bar-nav-icon>
            <v-toolbar-title class="ml-0 pl-4">
                <a href="/"
                    ><img
                        src="@/assets/logo.png"
                        alt="Logo"
                        class="mt-1"
                        height="28"
                /></a>
            </v-toolbar-title>
            <v-spacer />

            <!-- clock -->
            <v-toolbar-title class="ml-0 pl-4">
                <div class="d-flex flex-column align-center">
                    <v-list-item-subtitle>{{
                        new Date().toLocaleDateString()
                    }}</v-list-item-subtitle>
                </div>
            </v-toolbar-title>
        </v-app-bar>

        <!-- navigation drawer -->
        <v-navigation-drawer
            v-if="isLoggedIn"
            v-model="drawer"
            :clipped="$vuetify.breakpoint.lgAndUp"
            app
        >
            <v-list class="my-3" v-if="me">
                <div v-for="(item, i) in computedMenuItem" :key="i">
                    <v-list-item
                        v-if="item.title === 'Profile'"
                        :key="item.title"
                        :to="item.to"
                        link
                        class="d-flex justify-center text-center"
                        v-ripple="{ center: true }"
                    >
                        <div class="d-flex flex-column align-center">
                            <v-list-item-avatar color="primary">
                                <img src="@/assets/user.png" alt="user" />
                            </v-list-item-avatar>
                            <v-list-item-title class="mt-1">
                                {{ me.firstName }} {{ me.lastName }}
                            </v-list-item-title>
                            <v-list-item-subtitle>{{
                                me.email
                            }}</v-list-item-subtitle>
                        </div>
                    </v-list-item>
                </div>
            </v-list>

            <v-spacer></v-spacer>

            <v-list dense>
                <div v-for="(item, i) in computedMenuItem" :key="i">
                    <v-list-item
                        v-if="item.title !== 'Profile'"
                        :key="item.title"
                        :to="item.to"
                        link
                        v-ripple="{ center: true }"
                    >
                        <v-list-item-action>
                            <v-icon>{{ item.icon }}</v-icon>
                        </v-list-item-action>
                        <v-list-item-content>
                            <v-list-item-title>{{
                                item.title
                            }}</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                </div>
            </v-list>
            <template v-slot:append>
                <div class="pa-2 mb-10">
                    <v-btn block rounded color="error" @click="logout"
                        >Logout</v-btn
                    >
                </div>
            </template>
        </v-navigation-drawer>

        <!-- body -->
        <v-main>
            <!-- Router view -->
            <router-view></router-view>
        </v-main>

        <!-- footer -->
        <v-footer padless>
            <v-card class="flex" flat tile>
                <v-card-text class="py-2 grey--text text-center">
                    {{ new Date().getFullYear() }} — <strong>MyGym</strong>
                </v-card-text>
            </v-card>
        </v-footer>
    </v-app>
</template>

<script>
export default {
    name: "App",

    data() {
        return {
            drawer: false,
            isLoggedIn: true,
            menuItems: [
                {
                    title: "Profile",
                    icon: "person",
                    to: "/profile"
                },
                {
                    title: "Home",
                    icon: "mdi-home",
                    to: "/"
                },

                {
                    title: "Classes",
                    icon: "mdi-calendar",
                    to: "/classes"
                }
            ]
        };
    },

    computed: {
        me() {
            return {
                firstName: "John",
                lastName: "Doe",
                email: "johnEmail@email.com"
            };
        },
        computedMenuItem() {
            return this.menuItems;
        }
    },
    watch: {},
    beforeCreate() {},
    created() {},
    mounted() {},
    methods: {
        logout() {
            this.isLoggedIn = false;
            this.$router.push("/login");
            //logout
        }
    }
};
</script>

<style>
#app {
    background-color: var(--v-background-base);
    font-family: Avenir, Helvetica, Arial, sans-serif;
}

.v-btn {
    text-transform: none !important;
}
</style>