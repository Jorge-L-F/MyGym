import "material-design-icons-iconfont/dist/material-design-icons.css";
import { createVuetify } from 'vuetify';
import 'vuetify/styles'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'

// export default createVuetify({
//   theme: {
//     themes: {
//       light: {
//         primary: "#1B6A99",
//         secondary: "#154D79",
//         success: "#C5E17A",
//         info: "#3D9BC8",
//         accent: "#B05C4B",
//         warning: "#ED8E83",
//         error: "#CB5858",
//         background: "#F6F8FA",
//         background2: "#EEF0F2"
//       }
//     },
//     options: { customProperties: true }
//   },
  
// });



export default createVuetify({
  components,
  directives,
  icons: {
    iconfont: "mdi"
  }
})
