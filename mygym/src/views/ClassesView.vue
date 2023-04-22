<template>
    <v-container fluid class="pa-0">
        <h1 class="text-center mt-5">Classes</h1>
        <div class="mx-10 mt-5">
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
                            @click:day="createEventDialog"
                            @click:time="createEventDialog"
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
                                <v-card-title
                                    :class="vCardTitleAndColor.color"
                                    dark
                                    ><v-btn
                                        icon
                                        @click="editMode = true"
                                        v-if="
                                            !createMode &&
                                            role == 'trainer' &&
                                            selectedEventIsCompleted == false
                                        "
                                    >
                                        <v-icon>mdi-pencil</v-icon> </v-btn
                                    ><span class="ml-5"
                                        >{{
                                            vCardTitleAndColor.title
                                                ? vCardTitleAndColor.title
                                                : "Create New Event"
                                        }}
                                    </span>
                                </v-card-title>

                                <v-card-text class="mt-5">
                                    <v-form
                                        ref="form"
                                        v-model="valid"
                                        v-if="role == 'trainer'"
                                    >
                                        <v-select
                                            v-model="selectedEvent.type"
                                            :items="typesOfClasses"
                                            item-text="text"
                                            item-value="value"
                                            label="Type of class"
                                            :rules="[rules.required]"
                                            outlined
                                            :disabled="!editMode && !createMode"
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
                                                    :disabled="
                                                        !editMode && !createMode
                                                    "
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
                                                    v-model="
                                                        selectedEvent.start
                                                    "
                                                    :clearable="editMode"
                                                    label="Start Time"
                                                    v-on="on"
                                                    outlined
                                                    :rules="[rules.required]"
                                                    :disabled="
                                                        !editMode && !createMode
                                                    "
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
                                                    v-model="selectedEvent.end"
                                                    :clearable="editMode"
                                                    label="End Time"
                                                    v-on="on"
                                                    outlined
                                                    :rules="[rules.required]"
                                                    :disabled="
                                                        !editMode && !createMode
                                                    "
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
                                            v-if="!createMode"
                                            v-model="selectedEvent.isCompleted"
                                            label="Completed"
                                            :disabled="!editMode"
                                        ></v-checkbox>
                                    </v-form>
                                    <div v-else class="text-center">
                                        <span
                                            ><b>Date:</b>
                                            {{ selectedEvent.date }}</span
                                        >
                                        <br />
                                        <span
                                            ><b>Start Time:</b>
                                            {{ selectedEvent.start }}</span
                                        >
                                        <br />
                                        <span
                                            ><b>End Time:</b>
                                            {{ selectedEvent.end }}</span
                                        >
                                        <br />
                                        <div
                                            class="d-flex justify-center mt-3"
                                            v-if="!selectedEvent.isCompleted"
                                        >
                                            <v-btn
                                                v-if="!isParticipant"
                                                color="primary"
                                                @click="
                                                    updateParticipants(true)
                                                "
                                            >
                                                Register
                                            </v-btn>
                                            <v-btn
                                                v-else
                                                color="primary"
                                                @click="
                                                    updateParticipants(false)
                                                "
                                            >
                                                Unsubscribe
                                            </v-btn>
                                        </div>
                                    </div>
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
                                        :disabled="!valid"
                                        @click="editSelectedEvent()"
                                    >
                                        Save
                                    </v-btn>
                                    <v-btn
                                        v-if="createMode"
                                        text
                                        color="primary"
                                        :disabled="!valid"
                                        @click="createEvent()"
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
import { nanoid } from "nanoid";
import generateData from "@/simulateSensor";

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
            valid: false,
            disabled: false,
            menu: false,
            menuStartTime: false,
            menuEndTime: false,
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
                    color: "yellow darken-2"
                }
            ],
            rules: {
                required: (value) => !!value || "Required.",
                validEmail: (value) =>
                    /.+@.+/.test(value) || "E-mail must be valid"
            },
            createMode: false
        };
    },

    computed: {
        isParticipant() {
            if (this.selectedEvent)
                return this.selectedEvent?.participants?.includes(this.me?.id);
            return false;
        },
        selectedEventIsCompleted() {
            return this.eventsFormatted?.find(
                (event) => event.id === this.selectedEvent.id
            )?.isCompleted;
        },
        me() {
            return this.$store.state.user.me;
        },
        today() {
            return new Date();
        },
        vCardTitleAndColor() {
            let color = "grey";
            let title = null;
            if (this.selectedEvent) {
                if (this.selectedEvent.type)
                    color = this.typesOfClasses.find(
                        (type) => type.value === this.selectedEvent.type
                    ).color;
                if (this.selectedEvent.type)
                    title = this.typesOfClasses.find(
                        (type) => type.value === this.selectedEvent.type
                    ).text;
            }
            return { color: color, title: title };
        },
        eventsFormatted() {
            return this.events.map((event) => {
                // see if me.id is in event.participants

                return {
                    ...event,
                    name:
                        this.me?.role == "user" &&
                        event.participants.includes(this.me.id)
                            ? this.typesOfClasses.find(
                                  (type) => type.value === event.type
                              ).text + " REGISTERED"
                            : this.typesOfClasses.find(
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
            if (!this.selectedEvent || !this.selectedEvent.date) return null;

            const [year, month, day] = this.selectedEvent.date.split("-");
            return `${day}/${month}/${year}`;
        },
        role() {
            return this.$store.state.user.me?.role;
        }
    },
    watch: {
        selectedOpen() {
            this.$refs.form?.resetValidation();
            if (!this.selectedOpen) {
                this.editMode = false;
                this.createMode = false;
            }
            if (!this.createMode) {
                //find the event in the events array and reset this.selectedEvent
                const index = this.eventsFormatted.findIndex(
                    (event) => event.id === this.selectedEvent.id
                );
                if (index > -1) {
                    let event = this.eventsFormatted[index];
                    this.selectedEvent = {
                        ...event,
                        date: new Date(
                            new Date(event.start).getTime() -
                                new Date(event.start).getTimezoneOffset() *
                                    60 *
                                    1000
                        )
                            .toISOString()
                            .substr(0, 10),
                        start: new Date(
                            new Date(event.start).getTime() -
                                new Date(event.start).getTimezoneOffset() *
                                    60 *
                                    1000
                        )
                            .toISOString()
                            .substr(11, 5),
                        end: new Date(
                            new Date(event.end).getTime() -
                                new Date(event.end).getTimezoneOffset() *
                                    60 *
                                    1000
                        )
                            .toISOString()
                            .substr(11, 5)
                    };
                } else {
                    this.selectedEvent = {};
                }
            }
        }
    },
    created() {},
    mounted() {
        if (this.me.role === "user") {
            api.getAllClasses().then((res) => {
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
                this.selectedEvent = {
                    ...this.selectedEvent,
                    date: new Date(
                        new Date(this.selectedEvent.start).getTime() -
                            new Date(
                                this.selectedEvent.start
                            ).getTimezoneOffset() *
                                60 *
                                1000
                    )
                        .toISOString()
                        .substr(0, 10),
                    start: new Date(
                        new Date(this.selectedEvent.start).getTime() -
                            new Date(
                                this.selectedEvent.start
                            ).getTimezoneOffset() *
                                60 *
                                1000
                    )
                        .toISOString()
                        .substr(11, 5),
                    end: new Date(
                        new Date(this.selectedEvent.end).getTime() -
                            new Date(
                                this.selectedEvent.end
                            ).getTimezoneOffset() *
                                60 *
                                1000
                    )
                        .toISOString()
                        .substr(11, 5)
                };

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
        createEventDialog(day) {
            if (this.selectedOpen || this.me.role == "user") {
                this.selectedOpen = false;
                return;
            }

            this.selectedEvent = {
                id: nanoid(),
                trainer: this.me.id,
                participants: [],
                type: null,
                isCompleted: false,
                date: day.date,
                start: day.hasTime ? day.time : null,
                end: null
            };

            this.createMode = true;
            this.selectedElement = day.nativeEvent.target;
            requestAnimationFrame(() =>
                requestAnimationFrame(() => (this.selectedOpen = true))
            );
        },

        editSelectedEvent() {
            if (this.valid) {
                let event = {
                    id: this.selectedEvent.id,
                    trainer: this.selectedEvent.trainer,
                    participants: this.selectedEvent.participants,
                    type: this.selectedEvent.type,
                    isCompleted: this.selectedEvent.isCompleted,
                    start: new Date(
                        `${this.selectedEvent.date}T${this.selectedEvent.start}:00`
                    ).toISOString(),
                    end: new Date(
                        `${this.selectedEvent.date}T${this.selectedEvent.end}:00`
                    ).toISOString()
                };
                if (event.isCompleted) {
                    this.simulateSensorData(event);
                }
                api.updateClass(event).then(() => {
                    this.refetchEvents();
                    this.clearSelectedEvent();
                });
            }
        },
        clearSelectedEvent() {
            this.editMode = false;
            this.createMode = false;
            this.selectedOpen = false;
        },
        createEvent() {
            if (this.valid) {
                let event = {
                    id: this.selectedEvent.id,
                    trainer: this.selectedEvent.trainer,
                    participants: this.selectedEvent.participants,
                    type: this.selectedEvent.type,
                    isCompleted: false,
                    start: new Date(
                        `${this.selectedEvent.date}T${this.selectedEvent.start}:00`
                    ).toISOString(),
                    end: new Date(
                        `${this.selectedEvent.date}T${this.selectedEvent.end}:00`
                    ).toISOString()
                };
                api.createClass(event).then(() => {
                    this.refetchEvents();
                    this.clearSelectedEvent();
                });
            }
        },
        refetchEvents() {
            if (this.me.role === "user") {
                api.getAllClasses().then((res) => {
                    this.events = res.data;
                });
            } else {
                api.getClassesOfTrainer(this.me.id).then((res) => {
                    this.events = res.data;
                });
            }
            this.$refs.calendar.checkChange();
        },
        updateParticipants(toRegister) {
            // if true then register, else unregister
            if (toRegister) {
                this.selectedEvent.participants.push(this.me.id);
            } else {
                this.selectedEvent.participants =
                    this.selectedEvent.participants.filter(
                        (id) => id !== this.me.id
                    );
            }
            api.updateParticipants(
                this.selectedEvent.id,
                this.selectedEvent.participants
            ).then(() => {
                this.refetchEvents();
                this.clearSelectedEvent();
            });
        },
        simulateSensorData(classObj) {
            let durationInMinutes =
                (new Date(classObj.end) - new Date(classObj.start)) / 60000;
            classObj?.participants.forEach((participantId) => {
                let participant = null;
                api.getUser(participantId).then((res) => {
                    participant = res.data;
                    let simulatedData = generateData(
                        participant.gender,
                        participant.age,
                        participant.height,
                        participant.weight,
                        classObj.type,
                        durationInMinutes
                    );

                    participant.sensors =
                        participant.sensors.concat(simulatedData);

                    api.createSensorData(
                        participant.sensors,
                        participant.id
                    ).then(() => {});
                });
            });
        }
    }
};
</script>
