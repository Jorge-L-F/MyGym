<template>
  <v-app id="app">
    <v-app-bar v-if="isLoggedIn" app color="primary" dense>
      <v-app-bar-nav-icon @click.stop="drawer = !drawer">
        <v-icon>mdi-menu</v-icon>
      </v-app-bar-nav-icon>
      <v-toolbar-title class="ml-0 pl-4">
        <span text-color="white" link href="/">MyGym</span>
      </v-toolbar-title>

      <v-col></v-col>
    </v-app-bar>

    <!-- navigation drawer -->
    <v-navigation-drawer v-if="isLoggedIn" v-model="drawer" app>
      <v-list class="my-3">
        <div v-for="(item, i) in menuItems" :key="i">
          <v-list-item
            v-if="item.title === 'Profile'"
            :key="item.title"
            :to="item.to"
            link
            v-ripple="{ center: true }"
          >
            <v-row>
              <v-col cols="3">
                <v-avatar color="primary">
                  <v-icon color="white">mdi-account</v-icon>
                </v-avatar>
              </v-col>
              <v-col cols="9">
                <v-list-item-title>
                  {{ me.firstName }}
                  {{ me.lastName }}
                </v-list-item-title>
                <v-list-item-subtitle>{{ me.email }}</v-list-item-subtitle>
              </v-col>
            </v-row>
          </v-list-item>
        </div>
      </v-list>

      <v-spacer></v-spacer>

      <v-list dense>
        <div v-for="(item, i) in menuItems" :key="i">
          <v-list-item
            v-if="item.title !== 'Profile'"
            :key="item.title"
            :to="item.to"
            link
            v-ripple="{ center: true }"
          >
            <v-row>
              <v-col cols="2"
                ><v-icon>{{ item.icon }}</v-icon></v-col
              >
              <v-col cols="10"
                ><v-list-item-title>{{ item.title }}</v-list-item-title></v-col
              >
            </v-row>
          </v-list-item>
        </div>
      </v-list>
      <template #append>
        <div class="pa-2">
          <v-btn block text outlined rounded color="error" @click="logout"
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
    <v-footer class="d-flex flex-column">
      <div class="px-4 py-2 bg-grey text-center w-100">
        {{ new Date().getFullYear() }} — <strong>MyGym</strong>
      </div>
    </v-footer>
  </v-app>
</template>

<script>
export default {
  name: "App",
  data() {
    return {
      drawer: false,
      menuItems: [
        {
          title: "Home",
          icon: "mdi-home",
          to: "/",
        },
        {
          title: "Profile",
          icon: "person",
          to: "/profile",
        },
      ],
      me: {
        firstName: "John",
        lastName: "Doe",
        email: "johndoe@gmail.com",
      },
    };
  },

  computed: {
    isLoggedIn() {
      return true;
      // return localStorage.getItem("token") !== null;
    },
  },
  watch: {},
  beforeCreate() {},
  created() {},
  mounted() {},
  methods: {
    logout() {
      //TODO
    },
  },
};
</script>

<style>
#app {
  background-color: var(--v-background-base);
}

.v-btn {
  text-transform: none !important;
}

.v-timeline::before {
  top: 45px;
  height: calc(100% - 110px);
}
</style>

<style scoped>
.feedback-button {
  top: 50%;
  left: -52px;
  transform: rotate(90deg);
  z-index: 2;
}

.help-button {
  bottom: 40px;
  right: 10px;
}
</style>
