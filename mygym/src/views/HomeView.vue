<template>
    <v-container fluid style="height: 90vh" class="pa-0">
        <div v-if="me">
            <h1 class="text-center mt-10 mb-4">
                Welcome back, {{ me.fullName }}!
            </h1>
            <v-row class="pa-3">
                <v-col md="5" sm="12">
                    <v-card class="pa-4 rounded">
                        <h1>Next Classes</h1>

                        <v-timeline side="end" density="compact">
                            <v-timeline-item
                                v-for="item in nextEvents"
                                :key="item.id"
                                size="small"
                            >
                                <v-card>
                                    <v-card-title class="bg-indigo-lighten-1">
                                        <v-icon
                                            size="large"
                                            class="me-4"
                                            icon="star"
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
                        <!--
                            
                        -->
                        <BarChart
                            v-if="!noSensorData"
                            :chartData="biometricData"
                        ></BarChart>
                        <span v-else>No sensor data</span>
                    </v-card>
                </v-col>
            </v-row>
        </div>
        <div v-else class="fill-height pa-0 w-100 bgImage">
            <v-container class="fill-height" fluid>
                <v-card class="background rounded mx-auto pa-10 elevation-3">
                    <div class="d-flex flex-column align-center mt-2 mb-16">
                        <img
                            src="@/assets/logo.png"
                            alt="Logo"
                            class=""
                            height="70"
                        />
                    </div>
                    <v-row class="mb-1">
                        <v-col class="d-flex justify-space-around">
                            <v-btn
                                class="pa-7"
                                elevation="8"
                                outlined
                                @click="goto('Login')"
                            >
                                Login
                            </v-btn>
                            <v-btn
                                class="pa-7"
                                elevation="8"
                                outlined
                                @click="goto('Register')"
                            >
                                Register
                            </v-btn>
                        </v-col>
                    </v-row>
                </v-card>
            </v-container>
        </div>
    </v-container>
</template>

<style scoped>
.bgImage {
    background: url("../assets/gymBackground2.png");
    background-size: cover;
    height: 100vh;
}
</style>

<script>
//import LineChart from "@/components/chart/LineChart.vue";
import BarChart from "@/components/chart/BarChart.vue";
import api from "@/api";

export default {
    title: "Home",
    components: {
        //LineChart
        BarChart
    },
    data() {
        return {
            biometricData: {
                labels: [],
                datasets: []
            },
            nextEvents: [],
            userEvents: []
        };
    },

    computed: {
        me() {
            return this.$store.state.user.me;
        },
        items() {
            return [
                {
                    id: 1,
                    color: "bg-purple-lighten-2"
                },
                {
                    id: 2,
                    color: "bg-amber-lighten-1"
                }
            ];
        },
        noSensorData() {
            return this.me?.sensors.length === 0;
        }
    },

    watch: {},
    created() {
        this.biometricData = this.loadBiometricData();
    },
    mounted() {
        if (this.me.role === "user") {
            api.getAllClasses().then((res) => {
                this.userEvents = res.data;
            });
        } else {
            api.getClassesOfTrainer(this.me.id).then((res) => {
                this.userEvents = res.data;
            });
        }

        const now = new Date();
        this.nextEvents = this.userEvents.filter(
            (event) => new Date(event.start) >= now
        );
        console.log(
            "Next events:",
            this.userEvents.filter((event) => new Date(event.start) >= now)
        );
    },
    methods: {
        goto(routeName) {
            this.$router.push({ name: routeName });
        },
        loadBiometricData() {
            const backgroundColors = {
                heartRate: "rgba(100, 0, 255, 0.45)",
                calories: "rgba(200, 100, 0, 0.45)"
            };

            let sensors = this.me?.sensors;

            //TODO Change this label array
            const indexArray = Array(sensors.length)
                .fill()
                .map((_, index) => index);
            console.log(indexArray);

            if (sensors.length === 0) {
                return null;
            }
            const keys = Object.keys(sensors[0]);
            console.log(keys);

            let resultDataset = [];
            keys.forEach((key) => {
                resultDataset.push({
                    label: key,
                    backgroundColor: backgroundColors[key],
                    data: this.findObjectsByProperty(key, sensors)
                });
            });

            console.log(resultDataset);

            return {
                //Days of the week
                labels: indexArray,
                datasets: resultDataset
            };
        },
        findObjectsByProperty(keyword, objectArray) {
            const resultArray = [];
            objectArray.forEach((object) => {
                if (Object.prototype.hasOwnProperty.call(object, keyword)) {
                    resultArray.push(object[keyword]);
                }
            });
            return resultArray;
        }
    }
};
</script>
