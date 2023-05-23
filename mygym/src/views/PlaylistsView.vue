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
                            :rules="[
                                rules.required,
                                rules.validSpotifyLink,
                                rules.alreadyOnList
                            ]"
                            v-model="spotifyLink"
                            outlined
                            prepend-inner-icon="person"
                        ></v-text-field>

                        <v-btn
                            block
                            class="mt-2 bg-blue"
                            @click="updatePlaylists"
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
                    >
                        <v-row>
                            <v-col cols="10">
                                <div v-html="playlist.html"></div>
                            </v-col>
                            <v-col cols="2">
                                <v-btn
                                    variant="text"
                                    @click="deletePlaylist(playlist.url)"
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
    title: "Playlist",
    data: function () {
        return {
            embedPlaylists: [],
            rules: {
                required: (value) => !!value || "Required.",
                validSpotifyLink: (value) =>
                    value.includes("https://open.spotify.com/") ||
                    "Invalid link.",
                alreadyOnList: (value) =>
                    this.me.playlists.indexOf(value) == -1 ||
                    "Already on this list."
            },
            spotifyLink: ""
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
                    let resultPlaylist = JSON.parse(result);
                    resultPlaylist = {
                        ...resultPlaylist,
                        ...{ url: playlist }
                    };

                    console.log(resultPlaylist);

                    this.embedPlaylists = [
                        ...this.embedPlaylists,
                        resultPlaylist
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
    methods: {
        updatePlaylists() {
            let newPlaylists = [...this.me.playlists, this.spotifyLink];

            const target = this.me;
            const user = {
                id: target.id,
                fullName: target.fullName,
                email: target.email,
                password: target.password,
                role: target.role,
                username: target.username,
                gender: target.gender,
                age: target.age,
                height: target.height,
                weight: target.weight,
                sensors: target.sensors,
                competitions: target.competitions,
                competitionsWins: target.competitionsWins,
                playlists: newPlaylists
            };

            api.updateUser(user).then((res) => {
                //TODO Set the new me variable!
                console.log(res);
            });
        },
        deletePlaylist(playlist) {
            let newPlaylists = this.me.playlists.filter(
                (value) => value != playlist
            );

            console.log(newPlaylists);

            const target = this.me;
            const user = {
                id: target.id,
                fullName: target.fullName,
                email: target.email,
                password: target.password,
                role: target.role,
                username: target.username,
                gender: target.gender,
                age: target.age,
                height: target.height,
                weight: target.weight,
                sensors: target.sensors,
                competitions: target.competitions,
                competitionsWins: target.competitionsWins,
                playlists: newPlaylists
            };

            api.updateUser(user).then((res) => {
                //TODO Set the new me variable!
                console.log(res);
            });

            this.embedPlaylists = this.embedPlaylists.filter(
                (value) => value.url != playlist
            );
        }
    }
};
</script>

<style></style>
