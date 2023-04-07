<template>
<div class="h-screen w-screen overflow-y-hidden d-flex justify-center align-center">
  <v-card 
  class="mx-auto pa-4 elevation-3 bg-white"
  variant="tonal"
  width="300"
  rounded>
      <v-card-item>
        <h1 class="text-center">Login</h1>
      </v-card-item>
      <v-form>
        <v-text-field
          variant="outlined" 
          v-model="username"
          label="Username"
          :rules="usernameRules"
          class="rounded-lg"
        ></v-text-field>
        <v-text-field
          variant="outlined" 
          v-model="password"
          label="Password"
          :rules="passwordRules"
          hint="Enter your password to access this website"
          type="password"
          class="rounded"
        ></v-text-field>
        <v-btn 
        type="submit" 
        block 
        class="mt-2 bg-blue-darken-1"
        variant="outlined" 
        >Submit</v-btn>
      </v-form>
  </v-card>
</div>

</template>

<script>
  export default {
    name: "LoginView",
    data() {
      return {
        username: "",
        usernameRules: [
          //FIXME Add submit rules
          value => {
            if (value.length >= 5) {
              return true;
            }

            return "Username must be at least 5 characters";
          }
        ],
        password: "",
        passwordRules: [
          value => {
            if (value.length >= 5) {
              return true;
            }

            return "Password must be at least 5 characters";
          }
        ]
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

.background {
  height: 100vh;
  display: flex;
  align-items: center;
}
</style>