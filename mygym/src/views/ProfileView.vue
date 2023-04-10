<template lang="">
    <v-container
    class="fill-height background2"
    fluid>

        <v-form
        class="background rounded mx-auto pa-4 elevation-3"
        v-model="valid"
        >
            <img src="https://api.dicebear.com/6.x/micah/svg?seed=siui&radius=50">
            <h1>{{me.fullName}}</h1>
            <p>{{me.email}}</p>
        </v-form>

    </v-container>
</template>
<script>
export default {
    title: "Profile",
    data: function() {
        return {
            valid: false,
        }
    },
    computed: {
        me() {
            console.log(JSON.parse(localStorage.getItem("me")))
            return JSON.parse(localStorage.getItem("me"));
        }
    },
}
</script>
<style lang="">
    
</style>