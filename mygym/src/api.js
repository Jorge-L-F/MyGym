import axios from "axios";

const api = axios.create({
    baseURL: "http://localhost:3000",
    headers: {
        "Content-Type": "application/json",
        Accept: "application/json"
    }
});

export default {

    //User methods
    getUsers() {
        return api.get("/user");
    },
    getUser(id) {
        return api.get(`/user/${id}`);
    },
    getUserByEmail(email) {
        return api.get(`/user?email=${email}`);
    },
    createUser(user) {
        return api.post("/user", user);
    },

    //Classes methods
    listClasses() {
        return api.get("/class");
    },

    getClass(id) {
        return api.get(`/class?id=${id}`)
    },

    getClassesOf(userId) {
        return api.get(`/class?participants_like=${userId}`)
    }

    // ... other methods
};
