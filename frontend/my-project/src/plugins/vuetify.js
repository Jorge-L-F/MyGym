import "material-design-icons-iconfont/dist/material-design-icons.css";
import { createVuetify } from "vuetify";
import "vuetify/styles";
import * as components from "vuetify/components";
import * as directives from "vuetify/directives";

// export default createVuetify({
//

// });

export default createVuetify({
  components,
  directives,
  icons: {
    iconfont: "mdi",
  },
  theme: {
    themes: {
      light: {
        colors: {
          primary: "#336699",
          secondary: "#86BBD8",
          success: "#9EE493",
          info: "#3D9BC8",
          accent: "#B05C4B",
          warning: "#ED8E83",
          error: "#CB5858",
          background: "#F6F8FA",
          background2: "#EEF0F2",
        },
      },
    },
    options: { customProperties: true },
  },
});
