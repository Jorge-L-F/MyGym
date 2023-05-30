<template>
    <v-container class="fill-height background2" fluid>
        <v-row>
            <v-col cols="7">
                <v-card class="rounded pa-4 elevation-3">
                    <h1 class="mb-4">Recommended Diet</h1>
                    <div class="d-flex flex-wrap">
                        <v-card
                            v-for="diet in this.availableDiets"
                            max-width="400"
                            class="ma-2"
                            :key="diet.id"
                        >
                            <v-img height="250" :src="diet.thumbnail"></v-img>
                            <v-card-title
                                ><h2 class="mt-2">
                                    {{ diet.name }}
                                </h2></v-card-title
                            >
                            <v-card-text>
                                <v-timeline align-top dense>
                                    <v-timeline-item
                                        small
                                        v-for="(dish, i) in diet.dishes"
                                        :key="i"
                                    >
                                        <v-row dense>
                                            <v-col
                                                class="d-flex align-item-center"
                                            >
                                                <v-img
                                                    :src="dish.thumbnail"
                                                    max-height="100"
                                                    max-width="100"
                                                    class="rounded"
                                                ></v-img>
                                            </v-col>
                                            <v-col>
                                                <strong>{{ dish.type }}</strong>
                                                <div class="text-caption">
                                                    {{ dish.description }}
                                                </div>
                                            </v-col>
                                        </v-row>
                                    </v-timeline-item>
                                </v-timeline>
                            </v-card-text>
                        </v-card>
                    </div>
                </v-card>
            </v-col>
            <v-col cols="5">
                <v-card class="rounded pa-4 elevation-3">
                    <h1>Selected Diet</h1>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
import api from "@/api";
//import { nanoid } from "nanoid";

export default {
    title: "Videos",
    data: function () {
        return {
            availableDiets: []
        };
    },
    watch: {},
    mounted() {
        api.getAllDiets().then((res) => {
            this.availableDiets = res.data;
            console.log(res);
        });
    },
    computed: {
        me() {
            return this.$store.state.user.me;
        }
    },
    beforeUpdate() {},
    methods: {}
};
</script>

<style></style>
