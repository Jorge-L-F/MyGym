<template>
    <v-container fluid class="pa-0">
        <h1 class="text-center mt-10">Classes</h1>
        <div class="mx-10">
            <v-row class="fill-height">
                <v-col>
                    <v-sheet height="64">
                        <v-toolbar flat>
                            <v-btn
                                outlined
                                class="mr-4"
                                color="grey darken-2"
                                @click="setToday"
                            >
                                Today
                            </v-btn>
                            <v-btn
                                fab
                                text
                                small
                                color="grey darken-2"
                                @click="prev"
                            >
                                <v-icon small> mdi-chevron-left </v-icon>
                            </v-btn>
                            <v-btn
                                fab
                                text
                                small
                                color="grey darken-2"
                                @click="next"
                            >
                                <v-icon small> mdi-chevron-right </v-icon>
                            </v-btn>
                            <v-toolbar-title v-if="$refs.calendar">
                                {{ $refs.calendar.title }}
                            </v-toolbar-title>
                            <v-spacer></v-spacer>
                            <v-menu bottom right>
                                <template v-slot:activator="{ on, attrs }">
                                    <v-btn
                                        outlined
                                        color="grey darken-2"
                                        v-bind="attrs"
                                        v-on="on"
                                    >
                                        <span>{{ typeToLabel[type] }}</span>
                                        <v-icon right> mdi-menu-down </v-icon>
                                    </v-btn>
                                </template>
                                <v-list>
                                    <v-list-item @click="type = 'day'">
                                        <v-list-item-title
                                            >Day</v-list-item-title
                                        >
                                    </v-list-item>
                                    <v-list-item @click="type = 'week'">
                                        <v-list-item-title
                                            >Week</v-list-item-title
                                        >
                                    </v-list-item>
                                    <v-list-item @click="type = 'month'">
                                        <v-list-item-title
                                            >Month</v-list-item-title
                                        >
                                    </v-list-item>
                                </v-list>
                            </v-menu>
                        </v-toolbar>
                    </v-sheet>
                    <v-sheet height="600">
                        <v-calendar
                            ref="calendar"
                            v-model="focus"
                            color="primary"
                            :events="eventsFormatted"
                            :type="type"
                            @click:event="showEvent"
                            @click:more="viewDay"
                            @click:date="viewDay"
                            @click:day="createEvent"
                            @click:time="createEvent"
                        ></v-calendar>
                        <v-menu
                            v-model="selectedOpen"
                            :close-on-content-click="false"
                            :activator="selectedElement"
                            offset-x
                        >
                            <v-card
                                color="grey lighten-4"
                                min-width="350px"
                                max-width="600px"
                                flat
                            >
                                <v-card-title :class="selectedEvent.color" dark
                                    ><v-btn icon @click="editMode = true">
                                        <v-icon>mdi-pencil</v-icon> </v-btn
                                    ><span class="ml-5">{{
                                        selectedEvent
                                            ? selectedEvent.name
                                            : "Create New Event"
                                    }}</span>
                                </v-card-title>

                                <v-card-text class="mt-5">
                                    <v-select
                                        v-model="selectedEvent.type"
                                        :items="typesOfClasses"
                                        item-text="text"
                                        item-value="value"
                                        label="Type of class"
                                        :rules="[rules.required]"
                                        outlined
                                        :disabled="!editMode"
                                    ></v-select>

                                    <v-menu
                                        ref="menu"
                                        v-model="menu"
                                        :close-on-content-click="false"
                                        transition="scale-transition"
                                        offset-y
                                        min-width="auto"
                                    >
                                        <template v-slot:activator="{ on }">
                                            <v-text-field
                                                v-model="dateFormatted"
                                                :clearable="editMode"
                                                label="Date"
                                                v-on="on"
                                                outlined
                                                :rules="[rules.required]"
                                                :disabled="!editMode"
                                            >
                                            </v-text-field>
                                        </template>
                                        <v-date-picker
                                            v-model="selectedEvent.date"
                                            no-title
                                            @input="menu = false"
                                        >
                                        </v-date-picker>
                                    </v-menu>
                                    <v-menu
                                        ref="menuStartTime"
                                        v-model="menuStartTime"
                                        :close-on-content-click="false"
                                        transition="scale-transition"
                                        offset-y
                                        min-width="auto"
                                    >
                                        <template v-slot:activator="{ on }">
                                            <v-text-field
                                                v-model="selectedStartTime"
                                                :clearable="editMode"
                                                label="Start Time"
                                                v-on="on"
                                                outlined
                                                :rules="[rules.required]"
                                                :disabled="!editMode"
                                            >
                                            </v-text-field>
                                        </template>
                                        <v-time-picker
                                            v-model="selectedEvent.start"
                                            format="24hr"
                                            scrollable
                                            @input="menuStartTime = false"
                                        ></v-time-picker>
                                    </v-menu>
                                    <v-menu
                                        ref="menuEndTime"
                                        v-model="menuEndTime"
                                        :close-on-content-click="false"
                                        transition="scale-transition"
                                        offset-y
                                        min-width="auto"
                                    >
                                        <template v-slot:activator="{ on }">
                                            <v-text-field
                                                v-model="selectedEndTime"
                                                :clearable="editMode"
                                                label="Start Time"
                                                v-on="on"
                                                outlined
                                                :rules="[rules.required]"
                                                :disabled="!editMode"
                                            >
                                            </v-text-field>
                                        </template>
                                        <v-time-picker
                                            v-model="selectedEvent.end"
                                            format="24hr"
                                            scrollable
                                            @input="menuEndTime = false"
                                        ></v-time-picker>
                                    </v-menu>
                                    <v-checkbox
                                        v-model="selectedEvent.completed"
                                        label="Completed"
                                        :disabled="!editMode"
                                    ></v-checkbox>
                                </v-card-text>
                                <v-card-actions>
                                    <v-btn
                                        text
                                        color="primary"
                                        @click="clearSelectedEvent()"
                                    >
                                        Cancel
                                    </v-btn>
                                    <v-spacer></v-spacer>
                                    <v-btn
                                        v-if="editMode"
                                        text
                                        color="primary"
                                        @click="editSelectedEvent()"
                                    >
                                        Save
                                    </v-btn>
                                </v-card-actions>
                            </v-card>
                        </v-menu>
                    </v-sheet>
                </v-col>
            </v-row>
        </div>
    </v-container>
</template>

<script>
import api from "../api";

export default {
    title: "Classes",

    data() {
        return {
            focus: "",
            type: "month",
            typeToLabel: {
                month: "Month",
                week: "Week",
                day: "Day"
            },
            disabled: false,
            menu: false,
            menuStartTime: false,
            selectedStartTime: null,
            menuEndTime: false,
            selectedIsCompleted: false,
            selectedEndTime: null,
            selectedDate: null,
            selectedEvent: {},
            selectedElement: null,
            selectedOpen: false,
            events: [],
            editMode: false,
            typesOfClasses: [
                {
                    text: "Yoga",
                    value: "yoga",
                    color: "blue"
                },
                {
                    text: "Pilates",
                    value: "pilates",
                    color: "indigo"
                },
                {
                    text: "Crossfit",
                    value: "crossfit",
                    color: "deep-purple"
                },
                {
                    text: "Skipping Rope",
                    value: "skippingRope",
                    color: "cyan"
                },
                {
                    text: "Zumba",
                    value: "zumba",
                    color: "green"
                },
                {
                    text: "Rowing",
                    value: "rowing",
                    color: "orange"
                },
                {
                    text: "Boxing",
                    value: "boxing",
                    color: "error"
                },
                {
                    text: "Weight Lifting",
                    value: "weightLifting",
                    color: "grey darken-1"
                },
                {
                    text: "Running",
                    value: "running",
                    color: "blue"
                },
                {
                    text: "Swimming",
                    value: "swimming",
                    color: "orange"
                },
                {
                    text: "Cycling",
                    value: "cycling",
                    color: "yellow"
                }
            ],
            rules: {
                required: (value) => !!value || "Required.",
                validEmail: (value) =>
                    /.+@.+/.test(value) || "E-mail must be valid"
            }
        };
    },

    computed: {
        me() {
            return this.$store.state.user.me;
        },
        today() {
            return new Date();
        },
        eventsFormatted() {
            return this.events.map((event) => {
                return {
                    ...event,
                    name: this.typesOfClasses.find(
                        (type) => type.value === event.type
                    ).text,
                    start: new Date(event.start),
                    end: new Date(event.end),
                    color: this.typesOfClasses.find(
                        (type) => type.value === event.type
                    ).color,
                    timed: true
                };
            });
        },
        dateFormatted() {
            if (!this.selectedEvent || this.selectedEvent.date) return null;

            const [year, month, day] = this.selectedEvent.date.split("-");
            return `${day}/${month}/${year}`;
        }
    },
    watch: {
        selectedOpen(val) {
            if (!val) {
                // this.selectedEvent = null;
            } else {
                this.selectedEvent = {
                    ...this.selectedEvent,
                    date: this.selectedEvent.start.toISOString().substr(0, 10),
                    start: this.selectedEvent.start.toISOString().substr(11, 5),
                    end: this.selectedEvent.end.toISOString().substr(11, 5)
                };
            }
        }
    },
    created() {},
    mounted() {
        if (this.me.role === "user") {
            api.getClassesOf(this.me.id).then((res) => {
                this.events = res.data;
            });
        } else {
            api.getClassesOfTrainer(this.me.id).then((res) => {
                this.events = res.data;
            });
        }
        this.$refs.calendar.checkChange();
    },
    methods: {
        viewDay({ date }) {
            this.focus = date;
            this.type = "day";
        },
        setToday() {
            this.focus = "";
        },
        prev() {
            this.$refs.calendar.prev();
        },
        next() {
            this.$refs.calendar.next();
        },
        showEvent({ nativeEvent, event }) {
            const open = () => {
                this.selectedEvent = event;
                this.selectedElement = nativeEvent.target;
                requestAnimationFrame(() =>
                    requestAnimationFrame(() => (this.selectedOpen = true))
                );
            };

            if (this.selectedOpen) {
                this.selectedOpen = false;
                requestAnimationFrame(() =>
                    requestAnimationFrame(() => open())
                );
            } else {
                open();
            }

            nativeEvent.stopPropagation();
        },
        createEvent(day) {
            console.log(day);
        },
        editSelectedEvent() {
            //TODO clear selected
            // const event = this.selectedEvent;
            // const start = this.selectedStartTime;
            // const end = this.selectedEndTime;
            // const isCompleted = this.selectedIsCompleted;
            // if (start && end) {
            //     event.start = start;
            //     event.end = end;
            // }
            // if (isCompleted) {
            //     event.isCompleted = isCompleted;
            // }
            // this.$refs.calendar.updateEvent(event);
        },
        clearSelectedEvent() {
            // this.selectedEvent = {};
            // this.selectedStartTime = null;
            // this.selectedEndTime = null;
            // this.selectedIsCompleted = false;
            this.selectedOpen = false;
        }
        // updateRange({ start, end }) {
        //     const events = [];

        //     const min = new Date(`${start.date}T00:00:00`);
        //     const max = new Date(`${end.date}T23:59:59`);
        //     const days = (max.getTime() - min.getTime()) / 86400000;
        //     const eventCount = this.rnd(days, days + 20);

        //     for (let i = 0; i < eventCount; i++) {
        //         const allDay = this.rnd(0, 3) === 0;
        //         const firstTimestamp = this.rnd(min.getTime(), max.getTime());
        //         const first = new Date(
        //             firstTimestamp - (firstTimestamp % 900000)
        //         );
        //         const secondTimestamp = this.rnd(2, allDay ? 288 : 8) * 900000;
        //         const second = new Date(first.getTime() + secondTimestamp);

        //         events.push({
        //             name: this.names[this.rnd(0, this.names.length - 1)],
        //             start: first,
        //             end: second,
        //             color: this.colors[this.rnd(0, this.colors.length - 1)],
        //             timed: !allDay
        //         });
        //     }

        //     this.events = events;
        // },
        // rnd(a, b) {
        //     return Math.floor((b - a + 1) * Math.random()) + a;
        // }
    }
};
</script>
