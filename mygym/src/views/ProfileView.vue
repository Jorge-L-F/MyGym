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
                        <p class="text--secondary">{{ me.email }}</p>
                    </div>
                </v-card>
            </v-col>
            <v-col>
                <v-card class="rounded pa-4 elevation-3">
                    <h1>Details</h1>
                    <v-row>
                        <v-col class="d-flex justify-center">
                            <DetailCard
                                title="Weight"
                                icon="scale"
                                :content="me.weight"
                            ></DetailCard>
                            <DetailCard
                                title="Height"
                                icon="straighten"
                                :content="me.height"
                                backgroundColor="#00ff00"
                                color="#323232"
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
                    <h1>Latest classes</h1>
                    <div class="d-flex"></div>
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
        return {};
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
            return `https://api.dicebear.com/6.x/micah/svg?seed=${seed}&radius=50`;
        }
    }
};
</script>
<style lang=""></style>
