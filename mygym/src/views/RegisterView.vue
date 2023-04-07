<template>
    <div class="h-screen w-screen overflow-y-hidden d-flex justify-center align-center">
      <v-card 
      class="mx-auto pa-4 elevation-3 bg-white"
      variant="tonal"
      width="300"
      rounded>
          <v-card-item>
            <h1 class="text-center">Register</h1>
          </v-card-item>
          <v-form>
            <h3 class="mb-3">Credentials</h3>
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
            <v-text-field
              variant="outlined" 
              v-model="confirmPassword"
              label="Confirm Password"
              :rules="confirmPasswordRules"
              hint="Enter your password again."
              type="password"
              class="rounded"
            ></v-text-field>
            <h3 class="mb-3">Information</h3>
            <v-text-field
              variant="outlined" 
              v-model="fullName"
              label="Full Name"
              :rules="fullNameRules"
              class="rounded-lg"
            ></v-text-field>
            <v-select
              label="Gender"
              :items="['Male', 'Female']"
              variant="outlined"
            ></v-select>
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
        name: "RegisterView",
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
                if (value.length < 5) {
                  return "Password must be at least 5 characters";
                }
    
                return true;
              }
            ],
            confirmPassword: "",
            confirmPasswordRules: [
              value => {
                if (value.length < 5) {
                  return "Password must be at least 5 characters";
                }
    
                if (value !== this.password) {
                  return "Password must be the same as before";
                }
    
                return true;
              } 
            ],
            fullName: "",
            fullNameRules: [
              value => {
                if (value.length < 5) {
                  return "This field must be at least 5 characters";
                }
    
                if(!value.includes(" ")){
                  return "Must have multiple names in this field";
                }
    
                return true;
              } 
            ],
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
    
    </style>