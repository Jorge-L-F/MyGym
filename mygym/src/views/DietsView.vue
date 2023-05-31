<template>
    <v-container class="fill-height background2" fluid>
        <v-row>
            <v-col cols="7">
                <v-card class="rounded pa-4 elevation-3">
                    <h1 class="mb-4">Recommended Diet</h1>
                    <v-card max-width="400" class="ma-2">
                        <v-img
                            height="250"
                            :src="this.recommendedDiet.thumbnail"
                        ></v-img>
                        <v-card-title
                            ><h2 class="mt-2">
                                {{ this.recommendedDiet.name }}
                            </h2>
                            <v-icon color="orange" class="pa-4"
                                >mdi-star</v-icon
                            ></v-card-title
                        >
                        <v-card-text>
                            <v-timeline align-top dense>
                                <v-timeline-item
                                    small
                                    v-for="(dish, i) in this.recommendedDiet
                                        .dishes"
                                    :key="i"
                                >
                                    <v-row dense>
                                        <v-col class="d-flex align-item-center">
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

                    <h1 class="mb-4 mt-8">Available Diets</h1>
                    <div class="d-flex flex-wrap">
                        <v-card
                            v-for="diet in this.otherDiets"
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
                    <!-- <v-card max-width="400" class="ma-2">
                        <v-img
                            height="250"
                            :src="this.selectedDiet?.thumbnail"
                        ></v-img>
                        <v-card-title
                            ><h2 class="mt-2">
                                {{ this.selectedDiet?.name }}
                            </h2></v-card-title
                        >
                        <v-card-text>
                            <v-timeline align-top dense>
                                <v-timeline-item
                                    small
                                    v-for="(dish, i) in this.selectedDiet
                                        ?.dishes"
                                    :key="i"
                                >
                                    <v-row dense>
                                        <v-col class="d-flex align-item-center">
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
                    </v-card> -->
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
            availableDiets: [],
            recommendedDiet: null,
            otherDiets: [],
            selectedDiet: null
        };
    },
    watch: {},
    async mounted() {
        await api.getAllDiets().then((res) => {
            this.availableDiets = res.data;
        });

        let index = this.randomIntFromInterval(
            0,
            this.availableDiets.length - 1
        );
        this.recommendedDiet = this.availableDiets[index];

        this.otherDiets = this.availableDiets.filter(
            (diet) => diet.id !== this.recommendedDiet.id
        );
        this.selectedDiet = this.me.diet;
    },
    computed: {
        me() {
            return this.$store.state.user.me;
        }
    },
    beforeUpdate() {},
    methods: {
        randomIntFromInterval(min, max) {
            min = Math.ceil(min);
            max = Math.floor(max);
            return Math.floor(Math.random() * (max - min + 1)) + min;
        }
    }
};
</script>

<style></style>
