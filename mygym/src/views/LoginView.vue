<template>
    <v-container class="fill-height background2" fluid>
        <v-form
            class="background rounded mx-auto pa-4 elevation-3"
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
            <p v-if="!success" class="text-center red--text ma-0">
                {{ error }}
            </p>
            <v-btn block class="mt-2 bg-blue" outlined @click="login"
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
            success: true,
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
                console.log("login", this.email, this.password);
                this.$store
                    .dispatch("user/login", {
                        email: this.email,
                        password: this.password
                    })
                    .then((res) => {
                        if (res?.success) {
                            this.$router.push({ name: "Home" });
                        } else {
                            this.error = "Invalid email or password";
                            this.success = false;
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
