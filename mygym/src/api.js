import axios from "axios";

const api = axios.create({
    baseURL: "http://localhost:3000",
    headers: {
        "Content-Type": "application/json",
        Accept: "application/json"
    }
});

export default {
    getUsers() {
        return api.get("/user");
    },
    getUser(id) {
        return api.get(`/user/${id}`);
    },
    getUserByEmail(email) {
        console.log("🚀 ~ file: api.js:19 ~ getUserByEmail ~ email:", email)
        return api.get(`/user?email=${email}`);
    },
    createUser(user) {
        return api.post("/user", user);
    }

    // ... other methods
};
