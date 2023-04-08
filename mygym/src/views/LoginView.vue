<template>
    <v-container class="fill-height bg-grey" fluid>
        <v-form
            class="bg-white rounded mx-auto pa-4 elevation-3"
            v-model="valid"
        >
            <div class="d-flex flex-column align-center mt-2 mb-8">
                <img src="@/assets/logo.png" alt="Logo" class="" height="44" />
            </div>

            <v-text-field
                placeholder="E-mail"
                name="email"
                type="email"
                :rules="[rules.required, rules.validEmail]"
                :error-messages="error"
                outlined
                prepend-inner-icon="person"
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
                            showpassword ? "visibility" : "visibility_off"
                        }}</v-icon
                    >
                </template>
            </v-text-field>

            <v-btn
                type="submit"
                block
                class="mt-2 bg-blue"
                outlined
                @click="login"
                >Submit</v-btn
            >
        </v-form>
    </v-container>
</template>

<script>
export default {
    title: "Login",
    data: function () {
        return {
            showpassword: false,
            valid: false,
            email: "",
            password: "",
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
        login() {
            if (this.valid) {
                this.$store
                    .dispatch("user/login", {
                        email: this.email,
                        password: this.password
                    })
                    .then((res) => {
                        if (res) {
                            this.$router.push({ name: "Home" });
                        } else {
                            this.error = "Invalid email or password";
                        }
                    });
            }
        }
    }
};
</script>
<!--
-->
<style></style>
