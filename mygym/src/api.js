import axios from "axios";

const api = axios.create({
    baseURL: "http://localhost:3000",
    headers: {
        "Content-Type": "application/json",
        Accept: "application/json"
    }
});

const spotifyEmbedApi = axios.create({
    baseURL: "https://open.spotify.com/oembed",
    headers: {
        "Access-Control-Allow-Origin": "*",
        //"Cookie": "sp_landing=https%3A%2F%2Fopen.spotify.com%2Foembed%3Fsp_cid%3D683624adf4c9290596b481194426c465%26device%3Ddesktop; sp_t=683624adf4c9290596b481194426c465"
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

    //Playlist methods
    getEmbed(playlistLink) {
        return spotifyEmbedApi.get(`?url=${playlistLink}`, {
            redirect: "follow"
        });
    },

    // ... other methods
};
