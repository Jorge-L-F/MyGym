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
    createSensorData(data, userId) {
        return api.patch(`/user/${userId}`, {
            sensors: [...data]
        });
    },

    //Classes methods
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
    // ... Competitions methods
    getCompetitions() {
        return api.get("/competitions");
    },
    getCompetition(id) {
        return api.get(`/competitions/${id}`);
    },
    createCompetition(competition) {
        return api.post("/competitions", competition);
    },
    updateCompetition(competition) {
        return api.patch(`/competitions/${competition.id}`, competition);
    },
    deleteCompetition(id) {
        return api.delete(`/competitions/${id}`);
    },
    getCompetitionsOf(userId) {
        return api.get(`/competitions?participants_like=${userId}`);
    },
    updateCompetitionParticipants(competitionId, participants) {
        return api.patch(`/competitions/${competitionId}`, {
            participants
        });
    }
};
