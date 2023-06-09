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
                                v-for="event in nextEvents"
                                :key="event.id"
                                size="small"
                            >
                                <v-card>
                                    <v-card-title class="blue lighten-2 mb-3">
                                        <v-icon
                                            size="large"
                                            class="me-4"
                                            icon="star"
                                        ></v-icon>
                                        <h2 class="font-weight-light">
                                            {{ event.type }}
                                        </h2>
                                    </v-card-title>
                                    <v-card-text>
                                        <span
                                            ><b>Start Time:</b>
                                            {{ event.start }}</span
                                        >
                                        <br />
                                        <span
                                            ><b>End Time:</b>
                                            {{ event.end }}</span
                                        >
                                        <br />
                                    </v-card-text>
                                </v-card>
                            </v-timeline-item>
                        </v-timeline>
                    </v-card>
                    <v-card class="pa-4 rounded mt-10" v-if="role == 'user'">
                        <h1>Competitions</h1>
                        <v-card-text>
                            <span
                                ><b>Competitions Registered:</b>
                                {{ numberOfCompetitions }}</span
                            >
                            <br />
                            <span
                                ><b>Competitions Won:</b>
                                {{ numberWinningCompetitions }}</span
                            >
                        </v-card-text>
                    </v-card>
                </v-col>
                <v-col md="7" sm="12">
                    <v-card class="pa-4 rounded">
                        <h1 class="mb-3">Biometric Record</h1>
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
            userEvents: [],
            numberOfCompetitions: 0,
            numberWinningCompetitions: 0
        };
    },

    computed: {
        me() {
            return this.$store.state.user.me;
        },
        role() {
            return this.me?.role;
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
        if (this.me?.role === "user") {
            api.getClassesOf(this.me.id).then((res) => {
                this.userEvents = res.data;
                this.nextEvents = this.userEvents.filter((event) => {
                    return (
                        new Date(event.start) > new Date() &&
                        event.isCompleted === false
                    );
                });
            });
            api.getCompetitionsOf(this.me.id).then((res) => {
                this.numberOfCompetitions = res.data.length;
            });
            api.getWinningCompetitionsOf(this.me.id).then((res) => {
                this.numberWinningCompetitions = res.data.length;
            });
        } else {
            api.getClassesOfTrainer(this.me.id).then((res) => {
                this.userEvents = res.data;
                this.nextEvents = this.userEvents.filter((event) => {
                    return (
                        new Date(event.start) > new Date() &&
                        event.isCompleted === false
                    );
                });
            });
        }
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
                .map((_, index) => "Aula " + parseInt(index + 1));

            if (sensors.length === 0) {
                return null;
            }
            const keys = Object.keys(sensors[0]);

            let resultDataset = [];
            keys.forEach((key) => {
                resultDataset.push({
                    label: key == "heartRate" ? "Heart Rate" : "Calories",
                    backgroundColor: backgroundColors[key],
                    data: this.findObjectsByProperty(key, sensors)
                });
            });
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
