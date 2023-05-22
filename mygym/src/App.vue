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
                                <img :src="getAvatar(me.id)" alt="user" />
                            </v-list-item-avatar>
                            <v-list-item-title class="mt-1">
                                {{ me.fullName }}
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
            userMenuItems: [
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
                },
                {
                    title: "Competitions",
                    icon: "mdi-trophy",
                    to: "/competitions"
                },
                {
                    title: "Machines",
                    icon: "mdi-dumbbell",
                    to: "/machines"
                },
                {
                    title: "Playlists",
                    icon: "mdi-music",
                    to: "/playlists"
                },
                {
                    title: "Videos",
                    icon: "mdi-video",
                    to: "/videos"
                },
                {
                    title: "Diets",
                    icon: "mdi-food",
                    to: "/diets"
                }
            ],
            trainerMenuItems: [
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
                    title: "Manage Classes",
                    icon: "mdi-calendar",
                    to: "/classes"
                },
                {
                    title: "Manage Competitions",
                    icon: "mdi-trophy",
                    to: "/competitions"
                },
                {
                    title: "Manage Machines",
                    icon: "mdi-dumbbell",
                    to: "/machines"
                },
                {
                    title: "Manage Playlists",
                    icon: "mdi-music",
                    to: "/playlists"
                },
                {
                    title: "Manage Videos",
                    icon: "mdi-video",
                    to: "/videos"
                },
                {
                    title: "Manage Diets",
                    icon: "mdi-food",
                    to: "/diets"
                }
            ]
        };
    },

    computed: {
        me() {
            return this.$store.state.user.me;
        },
        isLoggedIn() {
            return this.$store.state.user.loggedIn;
        },
        computedMenuItem() {
            return this.me.role === "trainer"
                ? this.trainerMenuItems
                : this.userMenuItems;
        }
    },
    watch: {},
    beforeCreate() {},
    created() {},
    mounted() {
        if (this.isLoggedIn) {
            this.fetchMe();
        }
    },
    methods: {
        logout() {
            this.$store
                .dispatch("user/logout")
                .then(() => {
                    this.drawer = false;
                })
                .catch((error) => {
                    console.log("Logout failed", error);
                })
                .finally(() => {
                    this.$router.push("/login");
                });
        },
        fetchMe() {
            return this.$store
                .dispatch("user/fetchMe")
                .then(() => {})
                .catch((error) => {
                    console.log("fetchMe catched error: ", error);
                });
        },
        getAvatar(seed) {
            return `https://api.dicebear.com/6.x/micah/svg?seed=${seed}&radius=50`;
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
