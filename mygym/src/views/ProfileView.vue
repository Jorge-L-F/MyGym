<template>
    <v-container class="fill-height background2" fluid>
        <v-row>
            <v-col cols="5">
                <v-card class="rounded pa-4 elevation-3">
                    <div class="d-flex justify-center mb-3">
                        <v-avatar size="200px">
                            <img :src="getAvatar(me.id)" />
                        </v-avatar>
                    </div>
                    <div>
                        <div class="d-flex align-center">
                            <h1>{{ me.fullName }}</h1>
                            <v-chip
                                class="primary text-center rounded-pill ml-2"
                            >
                                <span class="font-weight-bold">
                                    {{
                                        me.role.charAt(0).toUpperCase() +
                                        me.role.slice(1)
                                    }}
                                </span>
                            </v-chip>
                        </div>
                        <p class="text--secondary" style="margin-top: -0.5rem">
                            {{ me.email }}
                        </p>
                    </div>
                </v-card>
            </v-col>
            <v-col>
                <v-card class="rounded pa-4 elevation-3 w-50">
                    <h1>Details</h1>
                    <v-row class="mb-1">
                        <v-col class="d-flex justify-start">
                            <DetailCard
                                title="Weight"
                                icon="scale"
                                :content="me.weight"
                                backgroundColor="#86BBD8"
                                color="#ffffff"
                            ></DetailCard>
                            <DetailCard
                                title="Height"
                                icon="straighten"
                                :content="me.height"
                                backgroundColor="#0faa79"
                                color="#dfdfdf"
                            ></DetailCard>
                            <DetailCard
                                title="Age"
                                icon="calendar_month"
                                :content="me.age"
                                backgroundColor="#550aad"
                                color="#dfdfdf"
                            ></DetailCard>
                        </v-col>
                    </v-row>
                    <h1>Information</h1>
                    <v-form class="pa-2" v-model="valid">
                        <v-row>
                            <v-col cols="9">
                                <v-text-field
                                    placeholder="Full Name"
                                    name="fullname"
                                    type="text"
                                    :rules="[rules.required]"
                                    :error-messages="error"
                                    outlined
                                    hide-details
                                    prepend-inner-icon="badge"
                                    v-model="me.fullName"
                                ></v-text-field>
                            </v-col>
                        </v-row>

                        <v-row>
                            <v-col cols="3">
                                <v-text-field
                                    placeholder="Age"
                                    name="age"
                                    type="number"
                                    :rules="[rules.required]"
                                    :error-messages="error"
                                    outlined
                                    hide-details
                                    prepend-inner-icon="timer"
                                    v-model.number="me.age"
                                ></v-text-field>
                            </v-col>
                            <v-col cols="3">
                                <v-text-field
                                    placeholder="Height"
                                    name="height"
                                    type="number"
                                    :rules="[rules.required]"
                                    :error-messages="error"
                                    outlined
                                    hide-details
                                    prepend-inner-icon="straighten"
                                    v-model.number="me.height"
                                ></v-text-field>
                            </v-col>
                            <v-col cols="3">
                                <v-text-field
                                    placeholder="Weight"
                                    name="Weight"
                                    type="number"
                                    :rules="[rules.required]"
                                    :error-messages="error"
                                    outlined
                                    hide-details
                                    prepend-inner-icon="scale"
                                    v-model.number="me.weight"
                                ></v-text-field>
                            </v-col>
                        </v-row>

                        <v-row>
                            <v-col cols="9">
                                <v-select
                                    :items="genderOptions"
                                    :rules="[rules.required]"
                                    v-model="me.gender"
                                    label="Gender"
                                    outlined
                                    hide-details
                                >
                                    <template v-slot:prepend-inner>
                                        <v-icon color="primary" tabindex="-1">{{
                                            me.gender.toLowerCase()
                                        }}</v-icon>
                                    </template>
                                </v-select>
                            </v-col>
                        </v-row>
                        <v-row class="d-flex justify-center">
                            <v-col cols="5">
                                <v-btn block class="mt-2 bg-blue" outlined
                                    >Submit
                                </v-btn>
                            </v-col>
                        </v-row>
                    </v-form>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>
<script>
import DetailCard from "@/components/DetailCard.vue";

export default {
    title: "Profile",
    data: function () {
        return {
            classes: [],
            genderOptions: [
                {
                    text: "Male",
                    value: "male"
                },
                {
                    text: "Female",
                    value: "female"
                }
            ],
            valid: false,
            error: null,
            rules: {
                required: (value) => !!value || "Required.",
                validEmail: (value) =>
                    /.+@.+/.test(value) || "E-mail must be valid"
            }
        };
    },
    computed: {
        me() {
            console.log(JSON.parse(localStorage.getItem("me")));
            return JSON.parse(localStorage.getItem("me"));
        }
    },
    components: {
        DetailCard
    },
    methods: {
        getAvatar(seed) {
            return `https://api.dicebear.com/6.x/micah/svg?seed=${seed}&radius=50&backgroundType=gradientLinear&backgroundColor=b6e3f4`;
        }
    }
};
</script>
<style lang=""></style>
