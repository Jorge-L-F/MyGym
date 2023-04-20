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
    updateUser(user) {
        return api.patch(`/user/${user.id}`, user);
    },

    //Classes methods
    listClasses() {
        return api.get("/class");
    },

    getClass(id) {
        return api.get(`/class?id=${id}`);
    },

    getClassesOf(userId) {
        return api.get(`/class?participants_like=${userId}`);
    },
    getAllClasses() {
        return api.get("/class");
    },
    getClassesOfTrainer(userId) {
        return api.get(`/class?trainer=${userId}`);
    },
    updateClass(classObj) {
        console.log(classObj);
        return api.patch(`/class/${classObj.id}`, classObj);
    },
    createClass(classObj) {
        return api.post("/class", classObj);
    },
    updateParticipants(classId, participants) {
        return api.patch(`/class/${classId}`, {
            participants
        });
    },
    // ... other methods
};
