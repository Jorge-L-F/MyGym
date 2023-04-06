<template>
  <h1>Users</h1>
  <v-row>
    <v-col v-for="user in users" :key="user.id" cols="12" sm="6" md="6">
      <v-card class="elevation-10">
        {{ user.name }}
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
export default {
  name: "App",
  data() {
    return {
      users: [],
    };
  },
  mounted() {
    fetch("https://jsonplaceholder.typicode.com/users")
      .then((response) => response.json())
      .then((json) => {
        this.users = json;
      });
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
