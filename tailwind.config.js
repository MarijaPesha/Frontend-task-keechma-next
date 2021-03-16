const defaultTheme = require('tailwindcss/defaultTheme')
const colors = require('tailwindcss/colors')

module.exports = {
    darkMode: 'class',
    future: {
        removeDeprecatedGapUtilities: true,
    },
    theme: {
        extend: {
            fontFamily: {
                "raleway": ['Raleway', 'Helvetica', 'Arial', 'sans-serif'],
                "open-sans": ['Open Sans', 'Helvetica', 'Arial', 'sans-serif'],
            },
            borderColor: ['active'],
            colors: {
                "teal": {
                    "50": "#F0FDFA",
                    "100": "#CCFBF1",
                    "200": "#99F6E4",
                    "300": "#5EEAD4",
                    "400": "#2DD4BF",
                    "500": "#14B8A6",
                    "600": "#0D9488",
                    "700": "#0F766E",
                    "800": "#115E59",
                    "900": "#134E4A"
                }
            }
        }
    }
};