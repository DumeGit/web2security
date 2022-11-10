// eslint-disable-next-line @typescript-eslint/no-var-requires
const defaultTheme = require("tailwindcss/defaultTheme");

module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}",
  ],
  theme: {
    extend: {
      fontFamily: {
        sans: ['"Inter var"', '"Inter"', ...defaultTheme.fontFamily.sans],
      },
      spacing: {
        "96": "24rem",
      },
    },
  },
};
