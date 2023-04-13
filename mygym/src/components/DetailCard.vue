<template>
    <v-card :style="getColor()" class="rounded-lg pa-3 ma-2 font-weight-bold" width="170">
        <v-icon size="36" color="black" tabindex="-1">
            {{ icon }}
        </v-icon>
        <p class="display-3 mt-2 font-weight-bold">
            {{ content }}
        </p>
        <h3>{{ title }}</h3>
    </v-card>
</template>
<script>
export default {
    name: "DetailCard",
    data: function() {
        return {}
    },
    methods: {
        getColor() {
            return `backgroundColor: ${this.color};`;
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
        color: {
            type: String,
            required: false,
        }
    }
};
</script>
<style></style>
