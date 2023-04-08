<template>
    <v-container fluid class="pa-0">
        <h1 class="text-center mt-10">Welcome back, {{ me.fullName }}!</h1>
        <div max-width="50%" class="d-flex justify-center mt-15">
            <v-card width="50%" elevation="10">
                <v-card-text>
                    <p>Here are your upcoming classes:</p>
                    <ul>
                        <li>Yoga - Monday at 6pm</li>
                        <li>Zumba - Tuesday at 7pm</li>
                        <li>Spin - Wednesday at 6:30am</li>
                    </ul>
                    <p>Don't forget to check in when you arrive!</p>
                </v-card-text>
            </v-card>
        </div>
        <v-row class="mt-5 px-10"
            ><v-col cols="6">
                <v-card elevation="10">
                    <v-card-title class="text-center">
                        <h2>My Profile</h2>
                    </v-card-title>
                    <v-card-text>
                        <p>Here is your profile information:</p>
                        <ul>
                            <li>Name: {{ me.fullName }}</li>
                            <li>Email: {{ me.email }}</li>
                        </ul>
                    </v-card-text>
                </v-card>
            </v-col>
            <v-col cols="6">
                <v-card elevation="10">
                    <v-card-title class="text-center">
                        <h2>My Classes</h2>
                    </v-card-title>
                    <v-card-text>
                        <p>
                            Here are the classes you are currently enrolled in:
                        </p>
                        <ul>
                            <li>Yoga - Monday at 6pm</li>
                            <li>Zumba - Tuesday at 7pm</li>
                            <li>Spin - Wednesday at 6:30am</li>
                        </ul>
                    </v-card-text>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
export default {
    title: "Home",

    data() {
        return {};
    },

    computed: {
        me() {
            return JSON.parse(localStorage.getItem("me"));
        }
    },

    watch: {},
    created() {},
    mounted() {},
    methods: {}
};
</script>
