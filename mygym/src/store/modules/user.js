import * as types from "../types";
import api from "../../api";

const me = localStorage.getItem("me");

const state = me ? { me, loggedIn: true } : { me: null, loggedIn: false };

const getters = {};

const mutations = {
    [types.LOGIN](state) {
        state.loggedIn = true;
    },
    [types.LOGOUT](state) {
        state.loggedIn = false;
        state.me = null;
    },
    [types.SET_ME](state, me) {
        state.me = me;
    }
};

const actions = {
    //TODO
    async register(context, payload) {
        localStorage.clear();

        return new Promise(() => {
            const userData = payload;
            api.createUser(userData).then((response) => {
                if (response) {
                    //FIXME Log in the registered user
                    localStorage.setItem("me", JSON.stringify(userData));
                    context.commit(types.SET_ME, { ...userData });
                    context.commit(types.LOGIN);

                    return true;
                } else {
                    return false;
                }
            });
        });
    },

    async login(context, payload) {
        localStorage.clear();

        return new Promise(() => {
            api.getUserByEmail(payload.email).then((response) => {
                if (response.data) {
                    if (response.data.password !== payload.password) {
                        localStorage.setItem(
                            "me",
                            JSON.stringify(response.data[0])
                        );
                        context.commit(types.SET_ME, { ...response.data[0] });
                        context.commit(types.LOGIN);

                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            });
        });
    },

    async fetchMe(context) {
        return new Promise(() => {
            console.log("🚀 ~ file: user.js:104 ~ fetchMe ~ context:", context);
            let id = JSON.parse(localStorage.getItem("me")).id;
            api.getUser(id).then((response) => {
                localStorage.setItem("me", JSON.stringify(response.data));
                context.commit(types.SET_ME, { ...response.data });
            });
        });
    },

    async logout(context) {
        return new Promise(() => {
            localStorage.clear();
            context.commit(types.LOGOUT);
        });
    }
};

export default {
    namespaced: true,
    state,
    getters,
    mutations,
    actions
};
