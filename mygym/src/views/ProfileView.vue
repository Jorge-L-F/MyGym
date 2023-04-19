<template>
    <v-container class="fill-height background2" fluid>
        <v-row>
            <v-col cols="5">
                <v-card class="rounded pa-4 elevation-3">
                    <div
                        class="d-flex flex-column align-center justify-center mb-3"
                    >
                        <v-avatar size="200px">
                            <img :src="getAvatar(me.id)" />
                        </v-avatar>
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
                            <p
                                class="text--secondary"
                                style="margin-top: -0.5rem"
                            >
                                {{ me.email }}
                            </p>
                        </div>
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
                                    v-model="fullName"
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
                                    v-model.number="age"
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
                                    v-model.number="height"
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
                                    v-model.number="weight"
                                ></v-text-field>
                            </v-col>
                        </v-row>

                        <v-row>
                            <v-col cols="9">
                                <v-select
                                    :items="genderOptions"
                                    :rules="[rules.required]"
                                    v-model="gender"
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
                        <v-row v-if="hasChanges" class="d-flex justify-center">
                            <v-col cols="5">
                                <v-btn
                                    block
                                    class="mt-2 bg-blue"
                                    outlined
                                    @click="update"
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
import api from "@/api";

export default {
    title: "Profile",

    watch: {
        me: {
            immediate: true,
            handler(val) {
                if (val) {
                    this.fullName = val.fullName;
                    this.age = val.age;
                    this.height = val.height;
                    this.weight = val.weight;
                    this.gender = val.gender;
                }
            }
        }
        /*me(value) {
            if (value) {
                this.weight = value.weight;
            }
        }*/
    },

    data: function () {
        return {
            fullName: null,
            age: null,
            height: null,
            weight: null,
            gender: null,

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
            return this.$store.state.user.me;
        },
        hasChanges() {
            //console.log("Has Changes!");
            const target = this.me;
            return (
                target.fullName !== this.fullName ||
                target.age !== this.age ||
                target.height !== this.height ||
                target.weight !== this.weight ||
                target.gender !== this.gender
            );
        }
    },
    components: {
        DetailCard
    },
    methods: {
        getAvatar(seed) {
            return `https://api.dicebear.com/6.x/micah/svg?seed=${seed}&radius=50&backgroundType=gradientLinear&backgroundColor=b6e3f4`;
        },
        update() {
            console.log("Updating user");

            if (!this.valid) {
                return;
            }

            const target = this.me;
            const user = {
                id: target.id,
                fullName: this.fullName,
                email: target.email,
                password: target.password,
                role: target.role,
                username: target.username,
                gender: this.gender,
                age: this.age,
                height: this.height,
                weight: this.weight
            };

            api.updateUser(user).then((res) => {
                //TODO Set the new me variable!
                console.log(res);
            });
        }
    }
};
</script>
<style lang=""></style>
