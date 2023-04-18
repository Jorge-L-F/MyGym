import * as types from "../types";
import api from "../../api";

const me = JSON.parse(localStorage.getItem("me"));

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
    async login(context, payload) {
        try {
            localStorage.clear();

            const response = await api.getUserByEmail(payload.email);
            if (
                response.data &&
                response.data[0].password === payload.password
            ) {
                localStorage.setItem("me", JSON.stringify(response.data[0]));
                context.commit(types.SET_ME, { ...response.data[0] });
                context.commit(types.LOGIN);

                return { success: true };
            } else {
                return { success: false, message: "Invalid email or password" };
            }
        } catch (error) {
            return { success: false, message: error.message };
        }
    },

    async fetchMe(context) {
        return new Promise(() => {
            console.log(
                "🚀 ~ file: user.js:104 ~ fetchMe ~ state.user.me.id:",
                state
            );
            let id = state.me.id;
            api.getUser(id).then((response) => {
                localStorage.setItem("me", JSON.stringify(response.data));
                context.commit(types.SET_ME, { ...response.data });
            });
        });
    },

    async logout(context) {
        try {
            localStorage.clear();
            context.commit(types.LOGOUT);
            return { success: true };
        } catch (error) {
            return { success: false, message: error.message };
        }
    }
};

export default {
    namespaced: true,
    state,
    getters,
    mutations,
    actions
};
