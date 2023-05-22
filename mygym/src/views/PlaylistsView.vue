<template>
    <v-container class="fill-height background2" fluid>
        <v-row>
            <v-col cols="5">
                <v-card class="rounded pa-4 elevation-3">
                    <h1 class="mb-4">Adicionar Playlist</h1>
                    <v-form ref="updatePlaylistForm">
                        <v-text-field
                            placeholder="Playlist URL"
                            name="playlistURL"
                            type="text"
                            :rules="[]"
                            outlined
                            prepend-inner-icon="person"
                        ></v-text-field>

                        <v-btn
                            block
                            class="mt-2 bg-blue"
                            @click="register"
                            outlined
                            >Submit</v-btn
                        >
                    </v-form>
                </v-card>
            </v-col>
            <v-col cols="7">
                <v-card class="rounded pa-4 elevation-3 gap-3">
                    <div
                        v-for="playlist in embedPlaylists"
                        :key="playlist.title"
                        style="width: 100%"
                        class="overflow-auto"
                    >
                        <div v-html="playlist.html"></div>
                    </div>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
//import api from "@/api";
//import { nanoid } from "nanoid";

export default {
    title: "Playlist",
    data: function () {
        return {
            embedPlaylists: []
        };
    },
    mounted() {
        //TODO: Get all playlists from user
        let playlists = this.me.playlists || [];

        playlists.forEach((playlist) => {
            /* let url =
                "https://open.spotify.com/track/0sAdiWtInRUw0dCR58gbEn?si=b74bc7029b384c99";
 */
            var myHeaders = new Headers();
            myHeaders.append(
                "Cookie",
                "sp_landing=https%3A%2F%2Fopen.spotify.com%2Foembed%3Fsp_cid%3D683624adf4c9290596b481194426c465%26device%3Ddesktop; sp_t=683624adf4c9290596b481194426c465"
            );

            var requestOptions = {
                method: "GET",
                headers: myHeaders,
                redirect: "follow"
            };

            fetch(
                "https://open.spotify.com/oembed?url=" + playlist,
                requestOptions
            )
                .then((response) => response.text())
                .then((result) => {
                    this.embedPlaylists = [
                        ...this.embedPlaylists,
                        JSON.parse(result)
                    ];
                    //this.embedPlaylists.push(JSON.parse(result));
                })
                .catch((error) => console.log("error", error));
        });
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
    methods: {}
};
</script>

<style></style>
