import "material-design-icons-iconfont/dist/material-design-icons.css"; // Ensure you are using css-loader
import Vue from "vue";
import Vuetify from "vuetify/lib";

Vue.use(Vuetify);

export default new Vuetify({
    theme: {
        themes: {
            light: {
                primary: "#336699",
                secondary: "#86BBD8",
                success: "#C5E17A",
                info: "#3D9BC8",
                warning: "#FFEB3B",
                error: "#D32F2F",
                background: "#F6F8FA",
                background2: "#EEF0F2"
            }
        },
        light: true,
        dark: false,
        options: { customProperties: true }
    },
    icons: {
        iconfont: "md"
    }
});
