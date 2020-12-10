const defaultTheme = require('tailwindcss/defaultTheme')
module.exports = {
  darkMode: 'class',
  future: {
    removeDeprecatedGapUtilities: true,
  },
  theme: {
  extend:{
  fontFamily:{
  "raleway": ['Raleway', 'Helvetica', 'Arial', 'sans-serif'],
  "open-sans": ['Open Sans', 'Helvetica', 'Arial', 'sans-serif'],
  },

    "colors": {
      "lime": {
        "50": "#ecff87",
        "100": "#e2ff7d",
        "200": "#d8f873",
        "300": "#ceee69",
        "400": "#c4e45f",
        "500": "#bada55",
        "600": "#b0d04b",
        "700": "#a6c641",
        "800": "#9cbc37",
        "900": "#92b22d"
      }
    }
  }
  }
};