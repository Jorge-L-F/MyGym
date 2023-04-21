<template>
    <v-container fluid class="pa-0">
        <div v-if="me">
            <h1 class="text-center mt-10 mb-4">
                Welcome back, {{ me.fullName }}!
            </h1>
            <v-row class="pa-3">
                <v-col md="5" sm="12">
                    <v-card class="pa-4 rounded">
                        <h1>Next Classes</h1>
                        <v-timeline side="start" density="compact">
                            <v-timeline-item
                                v-for="item in items"
                                :key="item.id"
                                size="small"
                            >
                                <v-card>
                                    <v-card-title v-bind:class="item.color">
                                        <v-icon
                                            size="large"
                                            class="me-4"
                                            :icon="item.icon"
                                        ></v-icon>
                                        <h2 class="font-weight-light">
                                            Title 1
                                        </h2>
                                    </v-card-title>
                                    <v-card-text>
                                        Lorem ipsum dolor sit amet, no nam
                                        oblique veritus. Commune scaevola
                                        imperdiet nec ut, sed euismod convenire
                                        principes at. Est et nobis iisque
                                        percipit.
                                    </v-card-text>
                                </v-card>
                            </v-timeline-item>
                        </v-timeline>
                    </v-card>
                </v-col>
                <v-col md="7" sm="12">
                    <v-card class="pa-4 rounded">
                        <h1 class="mb-3">Biometric Record</h1>
                        <BarChart :chartData="biometricData"></BarChart>
                    </v-card>
                </v-col>
            </v-row>
        </div>
        <div v-else>
            <v-btn @click="goto('Login')"> Login </v-btn>
            <v-btn @click="goto('Register')"> Register </v-btn>
        </div>
    </v-container>
</template>

<script>
//import LineChart from "@/components/chart/LineChart.vue";
import BarChart from "@/components/chart/BarChart.vue";

export default {
    title: "Home",
    components: {
        //LineChart
        BarChart
    },
    data() {
        return {};
    },

    computed: {
        me() {
            return this.$store.state.user.me;
        },
        biometricData() {
            const backgroundColors = {
                heartRate: "rgba(100, 0, 255, 0.45)",
                calories: "rgba(200, 100, 0, 0.45)",
                weightLoss: "rgba(0, 143, 120, 0.32)"
            };

            const user = this.$store.state.user.me;
            const sensors = user.sensors;

            let resultDataset = {};

            sensors.forEach((value) => {
                const keys = Object.keys(value);
                keys.forEach((key) => {
                    console.log(resultDataset.key);

                    //console.log(value);
                    //resultDataset.data.push(value[key]);
                });
            });

            console.log(resultDataset);

            return {
                //Days of the week
                labels: [0, 1],
                datasets: Array.from(resultDataset)
            };
        },
        items() {
            return [
                {
                    id: 1,
                    color: "bg-purple-lighten-2",
                    icon: "mdi-information"
                },
                {
                    id: 2,
                    color: "bg-amber-lighten-1",
                    icon: "mdi-alert-circle"
                }
            ];
        }
    },

    watch: {},
    created() {},
    mounted() {},
    methods: {
        goto(routeName) {
            this.$router.push({ name: routeName });
        }
    }
};
</script>
