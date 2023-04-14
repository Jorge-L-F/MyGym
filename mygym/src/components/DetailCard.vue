<template>
    <v-card :style="getBackgroundColor() + getColor()" class="rounded-lg pa-3 ma-2 font-weight-bold" min-width="150">
        <v-icon size="36" :color="this.color" tabindex="-1">
            {{ icon }}
        </v-icon>
        <p class="display-3 mt-2 font-weight-bold">
            {{ content }}
        </p>
        <h3>{{ title }}</h3>
    </v-card>
</template>
<script>
const DEFAULT_BACKGROUND_COLOR = "#fdfdfd";
const DEFAULT_COLOR = "#323232";

export default {
    name: "DetailCard",
    data: function() {
        return {}
    },
    methods: {
        getBackgroundColor() {
            console.log(this.backgroundColor)
            if(this.backgroundColor === undefined || this.backgroundColor.trim() === "") {
                return `backgroundColor: ${DEFAULT_BACKGROUND_COLOR};`;
            }

            return `backgroundColor: ${this.backgroundColor};`;
        },
        getColor() {
            if(this.color === undefined || this.color.trim() === "") {
                return `color: ${DEFAULT_COLOR};`;
            }

            return `color: ${this.color};`
        }
    },
    props: {
        title: {
            type: String,
            required: true
        },
        icon: {
            type: String,
            required: true
        },
        content: {
            type: String,
            required: true
        },
        backgroundColor: {
            type: String,
            required: false,
        },
        color: {
            type: String,
            required: false,
        }
    }
};
</script>
<style></style>
