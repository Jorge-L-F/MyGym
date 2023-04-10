<template>
    <v-container
    class="fill-height background2 d-flex justify-center"
    fluid>

        <v-card
        class="background rounded pa-4 elevation-3 ma-4"
        >
            <div class="d-flex justify-center mb-3">
                <img :src="getAvatar(me.username)" width="200">
            </div>
            <div>
                <div class="d-flex align-center">
                    <h1>{{me.fullName}}</h1>
                    <v-chip class="primary text-center rounded-pill ml-2">
                        <span class="font-weight-bold">
                            {{me.role.charAt(0).toUpperCase() + me.role.slice(1)}}
                        </span>
                    </v-chip>
                </div>
                <p class="text--secondary">{{me.email}}</p>
            </div>
        </v-card>

        <v-card
        class="background rounded pa-4 elevation-3 ma-4"
        >
            <v-container>
                <v-row>
                    <v-col cols="6">
                        <v-card
                        class="secondary rounded-lg pa-3 ma-2 font-weight-bold"
                        width="170"
                        height="170">
                            <v-icon
                                    size="36"
                                    color="black"
                                    tabindex="-1"
                                    >scale
                                    </v-icon>
                            <p class="display-3 mt-2 font-weight-bold">{{me.height}}</p>
                            <h3>Height</h3>
                        </v-card>
                    </v-col>
                    <v-col cols="6">
                        <v-card
                        class="success rounded-lg pa-3 ma-2 font-weight-bold"
                        width="170"
                        height="170">
                            <v-icon
                                    size="36"
                                    color="black"
                                    tabindex="-1"
                                    >straighten
                                    </v-icon>
                            <p class="display-3 mt-2 font-weight-bold">{{me.weight}}</p>
                            <h3>Height</h3>
                        </v-card>
                    </v-col>
                </v-row>
            </v-container>
            
        </v-card>
    </v-container>
</template>
<script>
export default {
    title: "Profile",
    data: function() {
        return {
        }
    },
    computed: {
        me() {
            console.log(JSON.parse(localStorage.getItem("me")))
            return JSON.parse(localStorage.getItem("me"));
        }
    },
    methods: {
        getAvatar(seed) {
            return `https://api.dicebear.com/6.x/micah/svg?seed=${seed}&radius=50`;
        } 
    },
}
</script>
<style lang="">
    
</style>