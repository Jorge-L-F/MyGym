<template lang="">
    <v-container
    class="fill-height background2"
    fluid>

        <v-form
        class="background rounded mx-auto pa-4 elevation-3"
        v-model="valid"
        >
        
            <h1>{{me.email}}</h1>

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