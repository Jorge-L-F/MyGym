<template>
    <v-container class="fill-height" fluid>
        <!-- <v-text-field
        placeholder="E-mail"
        name="login"
        type="email"
        :rules="[rules.required]"
        :error-messages="error"
        outlined
        prepend-inner-icon="person"
        v-model="email"
    ></v-text-field> -->

        <!-- <v-text-field
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
    </v-text-field> -->
    </v-container>
</template>

<script>
export default {
    title: "Login",
    data: function () {
        return {
            showpassword: false,

            email: "",
            password: "",
            error: "",
            rules: {
                required: (value) => !!value || "Required."
            }
        };
    },
    computed: {},

    created() {},
    methods: {
        login() {
            this.$router.push("/");
            //TODO
        }
    }
};
</script>
