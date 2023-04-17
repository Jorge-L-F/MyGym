<template>
    <v-container class="fill-height bg-grey" fluid>
        <v-form
            class="bg-white rounded mx-auto pa-4 elevation-3"
            v-model="valid"
            @submit.prevent="register"
        >
            <div class="d-flex flex-column align-center mt-2 mb-8">
                <img src="@/assets/logo.png" alt="Logo" class="" height="44" />
            </div>

            <v-row>
                <!-- Credentials -->
                <v-col>
                    <h2 class="mb-3">Credentials</h2>

                    <v-text-field
                        placeholder="Username"
                        name="username"
                        type="text"
                        :rules="[rules.required]"
                        :error-messages="error"
                        outlined
                        prepend-inner-icon="person"
                        v-model="username"
                    ></v-text-field>

                    <v-text-field
                        placeholder="E-mail"
                        name="email"
                        type="email"
                        :rules="[rules.required, rules.validEmail]"
                        :error-messages="error"
                        outlined
                        prepend-inner-icon="alternate_email"
                        v-model="email"
                    ></v-text-field>

                    <v-text-field
                        id="password"
                        placeholder="Password"
                        name="password"
                        :type="showpassword ? 'text' : 'password'"
                        :rules="[rules.required]"
                        :error-messages="error"
                        outlined
                        v-model="password"
                        prepend-inner-icon="lock"
                        @keydown.enter="login"
                    >
                        <template v-slot:append>
                            <v-icon
                                color="primary"
                                @click="showpassword = !showpassword"
                                tabindex="-1"
                                >{{
                                    showpassword
                                        ? "visibility"
                                        : "visibility_off"
                                }}</v-icon
                            >
                        </template>
                    </v-text-field>
                </v-col>
                <!-- Info -->
                <v-col>
                    <h2 class="mb-3">Information</h2>
                    <v-text-field
                        placeholder="Full Name"
                        name="fullname"
                        type="text"
                        :rules="[rules.required]"
                        :error-messages="error"
                        outlined
                        prepend-inner-icon="badge"
                        v-model="fullname"
                    ></v-text-field>
                    <v-text-field
                        placeholder="Age"
                        name="age"
                        type="number"
                        :rules="[rules.required]"
                        :error-messages="error"
                        outlined
                        prepend-inner-icon="timer"
                        v-model.number="age"
                    ></v-text-field>
                    <v-text-field
                        placeholder="Height"
                        name="height"
                        type="number"
                        :rules="[rules.required]"
                        :error-messages="error"
                        outlined
                        prepend-inner-icon="straighten"
                        v-model.number="height"
                    ></v-text-field>
                    <v-text-field
                        placeholder="Weight"
                        name="Weight"
                        type="number"
                        :rules="[rules.required]"
                        :error-messages="error"
                        outlined
                        prepend-inner-icon="scale"
                        v-model.number="weight"
                    ></v-text-field>
                    <v-select
                        :items="genderOptions"
                        :rules="[rules.required]"
                        v-model="gender"
                        label="Gender"
                        outlined
                    >
                        <template v-slot:append>
                            <v-icon color="primary" tabindex="-1">{{
                                gender.toLowerCase()
                            }}</v-icon>
                        </template>
                    </v-select>
                </v-col>
            </v-row>
            <v-btn block class="mt-2 bg-blue" @click="register" outlined
                >Submit</v-btn
            >
        </v-form>
    </v-container>
</template>

<script>
import { nanoid } from "nanoid";

export default {
    title: "Register",
    data: function () {
        return {
            showpassword: false,
            valid: false,
            username: "",
            email: "",
            password: "",
            confirmPassword: "",
            fullname: "",
            genderOptions: ["Male", "Female"],
            gender: "",
            age: "",
            height: "",
            weight: "",
            error: "",
            rules: {
                required: (value) => !!value || "Required.",
                validEmail: (value) =>
                    /.+@.+/.test(value) || "E-mail must be valid"
            }
        };
    },
    computed: {},

    created() {},
    methods: {
        helloWorld() {
            console.log("Siui");
        },
        register() {
            console.log("Register");
            if (this.valid) {
                const data = {
                    id: nanoid(),
                    fullName: this.fullname,
                    email: this.email,
                    password: this.password,
                    role: "user",
                    username: this.username,
                    gender: this.gender.toLowerCase(),
                    age: this.age,
                    height: this.height,
                    weight: this.weight
                };

                console.log("User: " + data);
                this.$store.dispatch("user/register", data).then((res) => {
                    if (res) {
                        this.$router.push({ name: "Login" });
                    } else {
                        //FIXME More appropriate error message
                        this.error = "Invalid email or password";
                    }
                });
            }
        }
    }
};
</script>

<style></style>
