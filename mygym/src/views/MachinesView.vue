<template>
    <v-container fluid class="pa-0">
        <h1 class="text-center mt-5">Machines</h1>
        <div class="mx-10 mt-5">
            <v-row class="fill-height">
                <v-col>
                    <v-data-table
                        :headers="headers"
                        :items="machines"
                        class="elevation-1"
                        @click:row="showMachine"
                    >
                        <template v-slot:top>
                            <v-toolbar flat>
                                <v-spacer></v-spacer>
                                <v-dialog v-model="dialog" max-width="500px">
                                    <template v-slot:activator="{ on, attrs }">
                                        <v-btn
                                            color="primary"
                                            dark
                                            class="mb-2"
                                            v-bind="attrs"
                                            v-on="on"
                                        >
                                            Add Machine
                                        </v-btn>
                                    </template>
                                    <v-card>
                                        <v-card-title>
                                            <span class="text-h5"
                                                >Add Machine</span
                                            >
                                        </v-card-title>

                                        <v-card-text>
                                            <v-container>
                                                <v-row>
                                                    <v-col
                                                        cols="12"
                                                        sm="6"
                                                        md="6"
                                                    >
                                                        <v-form
                                                            ref="form"
                                                            v-model="validate"
                                                            ><v-text-field
                                                                v-model="
                                                                    newItem.name
                                                                "
                                                                rules="
                                                                    [
                                                                        (v) =>
                                                                            !!v ||
                                                                            'Name is required',
                                                                    ]"
                                                                outlined
                                                                label="Machine name"
                                                            ></v-text-field
                                                        ></v-form>
                                                    </v-col>
                                                </v-row>
                                                <v-row>
                                                    <v-col
                                                        cols="12"
                                                        sm="6"
                                                        md="6"
                                                    >
                                                        <v-select
                                                            v-model="
                                                                newItem.type
                                                            "
                                                            :items="
                                                                typesOfMachines
                                                            "
                                                            outlined
                                                            label="Type"
                                                        ></v-select>
                                                    </v-col>
                                                </v-row>
                                            </v-container>
                                        </v-card-text>

                                        <v-card-actions>
                                            <v-spacer></v-spacer>
                                            <v-btn
                                                color="blue darken-1"
                                                text
                                                @click="close"
                                            >
                                                Cancel
                                            </v-btn>
                                            <v-btn
                                                color="blue darken-1"
                                                text
                                                @click="save"
                                            >
                                                Save
                                            </v-btn>
                                        </v-card-actions>
                                    </v-card>
                                </v-dialog>
                            </v-toolbar>
                        </template>
                    </v-data-table>
                </v-col>
            </v-row>
            <v-dialog
                v-model="selectedMachine"
                max-width="500px"
                @click:outside="selectedMachine = null"
            >
                <v-card>
                    <v-card-title>
                        <span class="text-h5">Add Machine</span>
                    </v-card-title>

                    <v-card-text>
                        <v-container>
                            <v-row>
                                <v-col cols="12" sm="6" md="6">
                                    <v-text-field
                                        v-model="newItem.name"
                                        disabled
                                        outlined
                                        label="Machine name"
                                    ></v-text-field>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="12" sm="6" md="6">
                                    <v-select
                                        v-model="newItem.type"
                                        :items="typesOfMachines"
                                        outlined
                                        disabled
                                        label="Type"
                                    ></v-select>
                                </v-col>
                            </v-row>
                        </v-container>
                    </v-card-text>

                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="blue darken-1" text @click="close">
                            Cancel
                        </v-btn>
                        <v-btn color="blue darken-1" text @click="save">
                            Save
                        </v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>
        </div>
    </v-container>
</template>

<script>
import api from "../api";
// import { nanoid } from "nanoid";

export default {
    title: "Machines",

    data() {
        return {
            dialog: false,
            headers: [
                {
                    text: "Name",
                    align: "start",
                    sortable: false,
                    value: "name"
                },
                { text: "Type", value: "type" },
                { text: "Available", value: "isAvailable" }
            ],
            machines: [],
            newItem: {
                id: null,
                name: "",
                type: "Treadmill",
                isAvailable: false
            },
            typesOfMachines: [
                "Treadmill",
                "Bike",
                "Elliptical",
                "Rowing",
                "Other"
            ],
            selectedMachine: null,
            validate: false
        };
    },

    computed: {
        me() {
            return this.$store.state.user.me;
        },
        role() {
            return this.$store.state.user.me?.role;
        }
    },
    watch: {
        dialog(val) {
            val || this.close();
        }
    },
    created() {},
    mounted() {
        api.getMachines().then((res) => {
            this.machines = res.data;
        });
    },
    methods: {
        refetchMachines() {
            api.getMachines().then((res) => {
                this.machines = res.data;
            });
        },
        close() {
            this.dialog = false;
        },
        save() {
            this.machines.push(this.newItem);
            this.close();
        },
        showMachine(machine) {
            this.selectedMachine = machine;
        }
    }
};
</script>
