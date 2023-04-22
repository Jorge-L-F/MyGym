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

function findObjectsByProperty(keyword, objectArray) {
    const resultArray = [];
    objectArray.forEach((object) => {
        if (Object.prototype.hasOwnProperty.call(object, keyword)) {
            resultArray.push(object[keyword]);
        }
    });
    return resultArray;
}

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

            //const user = this.$store.state.user.me;
            //TODO Change this to user.sensors
            const sensors = [
                {
                    calories: 1053.7142857142856,
                    heartRate: 0
                },
                {
                    calories: 699.4285714285714,
                    heartRate: 0
                }
            ];

            //TODO Change this label array
            const indexArray = Array(sensors.length)
                .fill()
                .map((_, index) => index);
            console.log(indexArray);

            const keys = Object.keys(sensors[0]);
            console.log(keys);

            let resultDataset = [];
            keys.forEach((key) => {
                resultDataset.push({
                    label: key,
                    backgroundColor: backgroundColors[key],
                    data: findObjectsByProperty(key, sensors)
                });
            });

            console.log(resultDataset);

            return {
                //Days of the week
                labels: indexArray,
                datasets: resultDataset
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
