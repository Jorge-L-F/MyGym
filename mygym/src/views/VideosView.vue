<template>
    <v-container class="fill-height background2" fluid>
        <v-row>
            <v-col cols="5">
                <v-card class="rounded pa-4 elevation-3">
                    <h1 class="mb-4">Adicionar Video</h1>
                    <v-form ref="updateVideoForm">
                        <v-text-field
                            placeholder="Video URL"
                            name="videoURL"
                            type="text"
                            :rules="[rules.required, rules.isEmpty]"
                            v-model="videoLink"
                            outlined
                            prepend-inner-icon="person"
                        ></v-text-field>

                        <v-btn
                            block
                            class="mt-2 bg-blue"
                            @click="updateVideos"
                            outlined
                            >Submit</v-btn
                        >
                    </v-form>
                </v-card>
            </v-col>
            <v-col cols="7">
                <v-card class="rounded pa-4 elevation-3 gap-3">
                    <div
                        v-for="video in this.me.videos"
                        :key="video"
                        style="width: 100%"
                    >
                        <v-row>
                            <v-col cols="10">
                                <a :href="video">{{ video }}</a>
                                <!-- <iframe width="100%" height="315" :src="video">
                                </iframe> -->
                            </v-col>
                            <v-col cols="2">
                                <v-btn
                                    variant="text"
                                    @click="deleteVideo(video)"
                                >
                                    <v-icon size="x-large">delete</v-icon>
                                </v-btn>
                            </v-col>
                        </v-row>
                    </div>
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
            rules: {
                required: (value) => !!value || "Required.",
                isEmpty: (value) => value.trim() !== "" || "Required."
                /*                 validSpotifyLink: (value) =>
                    value.includes("https://open.spotify.com/") ||
                    "Invalid link.",
                alreadyOnList: (value) =>
                    this.me.playlists.indexOf(value) == -1 ||
                    "Already on this list." */
            },
            videoLink: ""
        };
    },
    watch: {
        me: {
            immediate: true,
            handler(val) {
                if (val) {
                    this.playlists = [...val.playlists];
                }
            }
        }
    },
    mounted() {
        //TODO: Get all videos from user
    },
    computed: {
        me() {
            return this.$store.state.user.me;
        }
    },
    beforeUpdate() {
        /* api.getEmbed(
            "https://open.spotify.com/track/2ZtgxuTO7gEGh5XM1IJEh7?si=8888be4fa509404d"
        ).then((res) => {
            console.log(res);
        }); */
    },
    methods: {
        getYTApi() {
            const scriptElement = document.createElement("script");
            scriptElement.src = "https://www.youtube.com/iframe_api";
            document.head.appendChild(scriptElement);
        },
        updateVideos() {
            if (this.videoLink.trim() == "") return;

            let newVideos = [...this.me.videos, this.videoLink];

            this.me.videos = newVideos;

            api.updateUser(this.me).then((res) => {
                console.log(res);
            });
        },
        deleteVideo(video) {
            let newVideos = this.me.videos.filter((value) => value != video);

            this.me.videos = newVideos;

            api.updateUser(this.me).then((res) => {
                console.log(res);
            });
        }
    }
};
</script>

<style></style>
